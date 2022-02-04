package fi.abo.kogni.soile2.http_server.authentication;

import static io.vertx.ext.auth.impl.Codec.base64Encode;

import java.security.SecureRandom;

import fi.abo.kogni.soile2.http_server.utils.SoileCommUtils;
import fi.abo.kogni.soile2.http_server.utils.SoileConfigLoader;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.Vertx;
import io.vertx.core.http.Cookie;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.User;
import io.vertx.ext.auth.authentication.AuthenticationProvider;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.Session;
import io.vertx.ext.web.handler.HttpException;
import io.vertx.ext.web.handler.impl.AuthenticationHandlerImpl;
import io.vertx.ext.web.impl.RoutingContextInternal;

public class SoileAccessHandler extends AuthenticationHandlerImpl<AuthenticationProvider> implements Handler<RoutingContext>{

	private final JsonObject userConfig;
	private final JsonObject sessionConfig;
	private final JsonObject commConfig;
	private final JsonObject dbFields;
	private final Vertx vertx;
	private final SecureRandom random = new SecureRandom();


	public SoileAccessHandler(Vertx vertx, SoileAuthentication mAuthen, JsonObject config)
	{		
		super(mAuthen);
		this.userConfig = config.getJsonObject(SoileConfigLoader.USERMGR_CFG);
		this.sessionConfig = config.getJsonObject(SoileConfigLoader.SESSION_CFG);
		this.commConfig = config.getJsonObject(SoileConfigLoader.COMMUNICATION_CFG);
		this.dbFields = config.getJsonObject(SoileConfigLoader.DB_FIELDS);
		this.vertx = vertx;
	}


	@Override
	public void authenticate(RoutingContext context, Handler<AsyncResult<User>> handler) {

		System.out.println(handler.getClass().toString());
		HttpServerRequest req = context.request();		
		//first, we will look into whether this user is already logged in. 
		if (req.method() != HttpMethod.POST) {
			handler.handle(Future.failedFuture(new HttpException(405))); // Must be a POST
		} else {
			if (!((RoutingContextInternal) context).seenHandler(RoutingContextInternal.BODY_HANDLER)) {
				handler.handle(Future.failedFuture("BodyHandler is required to process POST requests"));
			} else {
				MultiMap formAttribs = req.formAttributes();
				if(formAttribs == null)
				{
					//Again we expect a form for authentication.
					handler.handle(Future.failedFuture(new HttpException(405)));
				}
				//TODO: Make these settings, also in the dustjs/js code! 
				String username = formAttribs.get(SoileCommUtils.getCommunicationField(commConfig, "usernameField"));
				String password = formAttribs.get(SoileCommUtils.getCommunicationField(commConfig, "passwordField"));
				String userType = formAttribs.get(SoileCommUtils.getCommunicationField(commConfig, "userTypeField"));
				boolean remember = formAttribs.get("remember") == null ? false : formAttribs.get("remember") == "1";			
				context.session().put("remember", remember);
				if (username == null || password == null) {
					handler.handle(Future.failedFuture(new HttpException(405)));
				} else {					
					System.out.println("Trying to auth user " + username +" with password " + password + " and type " + userType );
					authProvider.authenticate(new SoileCredentials(username, password, userType, dbFields), authn -> {
						if (authn.failed()) {
							System.out.println("Handling invalid auth: ");
							//authn.cause().printStackTrace(System.out);
							System.out.println(handler.getClass());
							handler.handle(Future.failedFuture(new HttpException(401, authn.cause())));
							System.out.println("Handling finished");
						} else {
							if(context.user() != null)
							{
								// this is a new login!, we clear any old one.
								context.clearUser();			
							}
							handler.handle(authn);
							
						}	            
					});
				}
			}
		}
	}

	/**
	 * Here the cookie to keep the login active is being set, along with forwarding to the url requested
	 */
	@Override
	public void postAuthentication(RoutingContext ctx) {
		// if this has now an assigned user, we will store this user.
			
		if(ctx.session().<Boolean>remove("remember") && ctx.user() != null)
		{
			// store this session
			storeSessionCookie(ctx);			
		}
		HttpServerRequest req = ctx.request();
		Session session = ctx.session();
		if (session != null) {
			String returnURL = session.remove("return_url");
			if (returnURL != null) {
				// Now redirect back to the original url
				ctx.redirect(returnURL);
				return;
			}
		}   
		// Just show a basic page
		req.response()
		.putHeader(HttpHeaders.CONTENT_TYPE, "text/html; charset=utf-8")
		.end("<html><body><h1>Login successful</h1></body></html>");

	}
	
	
	public void storeSessionCookie(RoutingContext ctx)
	{
		final byte[] rand = new byte[64];
	    random.nextBytes(rand);
	    String token = base64Encode(rand);
	    // we don't need any reply here.
		vertx.eventBus()
			 .send(SoileCommUtils.getEventBusCommand(userConfig, "addSession")
				   ,new JsonObject().put(sessionConfig.getString("sessionID"),token));
		// now build the cookie to store on the remote system. 		
		String cookiecontent = token + ":" 
							   + ctx.user().principal().getString(sessionConfig.getString("usernameField")) + ":" 
							   + ctx.user().principal().getString(sessionConfig.getString("userTypeField")); 
		Cookie cookie = Cookie.cookie(sessionConfig.getString("sessionCookieID"),cookiecontent);
		ctx.response().addCookie(cookie);
	}
	
	@Override
	  protected void processException(RoutingContext ctx, Throwable exception) {
	    if (exception != null) {
	      if (exception instanceof HttpException) {
	        final int statusCode = ((HttpException) exception).getStatusCode();
	        final String payload = ((HttpException) exception).getPayload();

	        switch (statusCode) {
	          case 302:
	            ctx.response()
	              .putHeader(HttpHeaders.LOCATION, payload)
	              .setStatusCode(302)
	              .end("Redirecting to " + payload + ".");
	            return;
	          case 401:
	        	// we will reroute the request to the login page. 
	            String header = authenticateHeader(ctx);
	            if (header != null && !"XMLHttpRequest".equals(ctx.request().getHeader("X-Requested-With"))) {
	              ctx.response()
	                .putHeader("WWW-Authenticate", header);
	            }
	            ctx.redirect("/login");	            
	            return;
	          default:
	            ctx.fail(statusCode, exception);
	            return;
	        }
	      }
	    }

	    // fallback 500
	    ctx.fail(exception);
	  }	

}
