package fi.abo.kogni.soile2.http_server;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fi.abo.kogni.soile2.datamanagement.git.GitManager;
import fi.abo.kogni.soile2.datamanagement.git.GitResourceManager;
import fi.abo.kogni.soile2.http_server.auth.JWTTokenCreator;
import fi.abo.kogni.soile2.http_server.auth.SoileAuthentication;
import fi.abo.kogni.soile2.http_server.auth.SoileAuthenticationBuilder;
import fi.abo.kogni.soile2.http_server.auth.SoileAuthorization;
import fi.abo.kogni.soile2.http_server.auth.SoileCookieCreationHandler;
import fi.abo.kogni.soile2.http_server.auth.SoileAuthorization.PermissionType;
import fi.abo.kogni.soile2.http_server.auth.SoileAuthorization.Roles;
import fi.abo.kogni.soile2.http_server.auth.SoileAuthorization.TargetElementType;
import fi.abo.kogni.soile2.http_server.auth.SoileIDBasedAuthorizationHandler;
import fi.abo.kogni.soile2.http_server.auth.SoileFormLoginHandler;
import fi.abo.kogni.soile2.http_server.routes.ElementRouter;
import fi.abo.kogni.soile2.http_server.routes.ProjectInstanceRouter;
import fi.abo.kogni.soile2.http_server.routes.TaskRouter;
import fi.abo.kogni.soile2.http_server.routes.UserRouter;
import fi.abo.kogni.soile2.projecthandling.apielements.APIExperiment;
import fi.abo.kogni.soile2.projecthandling.apielements.APIProject;
import fi.abo.kogni.soile2.projecthandling.participant.ParticipantHandler;
import fi.abo.kogni.soile2.projecthandling.projectElements.ElementManager;
import fi.abo.kogni.soile2.projecthandling.projectElements.Experiment;
import fi.abo.kogni.soile2.projecthandling.projectElements.Project;
import fi.abo.kogni.soile2.projecthandling.projectElements.instance.impl.ProjectInstanceHandler;
import fi.abo.kogni.soile2.utils.DebugRouter;
import fi.abo.kogni.soile2.utils.MessageResponseHandler;
import fi.abo.kogni.soile2.utils.SoileCommUtils;
import fi.abo.kogni.soile2.utils.SoileConfigLoader;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
import io.vertx.core.eventbus.ReplyException;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.authorization.PermissionBasedAuthorization;
import io.vertx.ext.mongo.MongoClient;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.JWTAuthHandler;
import io.vertx.ext.web.handler.LoggerHandler;
import io.vertx.ext.web.handler.SessionHandler;
import io.vertx.ext.web.openapi.RouterBuilder;
import io.vertx.ext.web.sstore.LocalSessionStore;
import io.vertx.ext.web.validation.RequestParameters;
import io.vertx.ext.web.validation.ValidationHandler;

public class SoileRouteBuilding extends AbstractVerticle{

	private static final Logger LOGGER = LogManager.getLogger(SoileRouteBuilding.class);
	private MongoClient client;
	private SoileCookieCreationHandler cookieHandler;
	private Router soileRouter;
	private SoileAuthenticationBuilder handler;
	private SoileAuthorization soileAuthorization;
	private GitManager gitManager;
	private GitResourceManager resourceManager;
	private ParticipantHandler partHandler;
	private ProjectInstanceHandler projHandler;
	@Override
	public void start(Promise<Void> startPromise) throws Exception {
		cookieHandler = new SoileCookieCreationHandler(vertx.eventBus());	
		this.client = MongoClient.createShared(vertx, config().getJsonObject("db"));
		gitManager = new GitManager(vertx.eventBus());
		resourceManager = new GitResourceManager(vertx.eventBus());
		soileAuthorization = new SoileAuthorization(client);
		projHandler = new ProjectInstanceHandler(client, vertx.eventBus());
		partHandler = new ParticipantHandler(client, projHandler, vertx);
		LOGGER.debug("Starting Routerbuilder");
		RouterBuilder.create(vertx, config().getString("api"))
					 .compose(this::setupAuth)
					 .compose(this::setupLogin)
					 .compose(this::addHandlers)
					 .compose(this::setupTaskAPI)
					 .compose(this::setupExperimentAPI)
					 .compose(this::setupProjectAPI)
					 .compose(this::setupProjectexecutionAPI)
					 .compose(this::setupUserAPI)
					 .onSuccess( routerBuilder ->
					 {
						// add Debug, Logger and Session Handlers.						
						soileRouter = routerBuilder.createRouter();
						startPromise.complete();
					 })
					 .onFailure(fail ->
					 {
						 LOGGER.error("Failed Starting router with error:");
						 LOGGER.error(fail);
						 startPromise.fail(fail.getCause());
					 }
					 );
		
		
	}
	@Override
	public void stop(Promise<Void> stopPromise)
	{
		stopPromise.complete();
	}
	
	public Router getRouter()
	{
		return this.soileRouter;
	}
	/**
	 * Set up auth handling
	 * @param builder the Routerbuilder to be used.
	 * @return the routerbuilder in a future for composite use
	 */
	Future<RouterBuilder> setupAuth(RouterBuilder builder)
	{	
		handler = new SoileAuthenticationBuilder();
		builder.securityHandler("cookieAuth",handler.getCookieAuthProvider(vertx, client, cookieHandler))
			   .securityHandler("JWTAuth", JWTAuthHandler.create(handler.getJWTAuthProvider(vertx)))
			   .securityHandler("tokenAuth", handler.getTokenAuthProvider(partHandler));
		return Future.<RouterBuilder>succeededFuture(builder);
	}
	
	Future<RouterBuilder> addHandlers(RouterBuilder builder)
	{
		builder.rootHandler(LoggerHandler.create());
		builder.rootHandler(SessionHandler.create(LocalSessionStore.create(vertx)));
		builder.rootHandler(BodyHandler.create());
		builder.rootHandler(new DebugRouter());
		return Future.<RouterBuilder>succeededFuture(builder);
	}
	
	Future<RouterBuilder> setupLogin(RouterBuilder builder)
	{
		builder.operation("registerUser").handler(handleUserManagerCommand("registerUser", MessageResponseHandler.createDefaultHandler(201)));
		SoileFormLoginHandler formLoginHandler = new SoileFormLoginHandler(new SoileAuthentication(client), "username", "password",new JWTTokenCreator(handler,vertx), cookieHandler);
		builder.operation("loginUser").handler(formLoginHandler::handle);
		builder.operation("testAuth").handler(this::testAuth);
		return Future.<RouterBuilder>succeededFuture(builder);
	}
	
	
	Handler<RoutingContext> handleUserManagerCommand(String command, MessageResponseHandler messageHandler)
	{
		Handler<RoutingContext> handler = new Handler<RoutingContext>() {

			@Override
			public void handle(RoutingContext routingContext) {
				RequestParameters params = routingContext.get(ValidationHandler.REQUEST_CONTEXT_KEY);
				JsonObject body = params.body().getJsonObject();
				vertx.eventBus().request(SoileCommUtils.getEventBusCommand(SoileConfigLoader.USERMGR_CFG, command),body).onSuccess( response ->
				{
					if(response.body() instanceof JsonObject)
					{
						
						messageHandler.handle(((JsonObject)response.body()), routingContext);
						routingContext.response().end();
					}
				}).onFailure( failure -> {
					
					if(failure.getCause() instanceof ReplyException)
					{
						ReplyException err = (ReplyException)failure.getCause();
						routingContext.response()
							.setStatusCode(err.failureCode())
							.setStatusMessage(err.getMessage())
							.end();
					}
					else
					{
						routingContext.response()
						.setStatusCode(500)
						.end();
						LOGGER.error("Something went wrong when trying to register a new user");					
						LOGGER.error(failure.getCause());
					}
				});
				
			}
		};		
		return handler;			
	}
	
	public void testAuth(RoutingContext ctx)
	{
		LOGGER.debug("AuthTest got a request");
		if(ctx.user() != null)
		{
			LOGGER.debug(ctx.user());
			LOGGER.debug(ctx.user().principal().encodePrettily());
			LOGGER.debug(ctx.user().attributes().encodePrettily());
			LOGGER.debug(ctx.user().authorizations().toString());
			ctx.request().response()
			.putHeader(HttpHeaders.CONTENT_TYPE, "application/json")
			.end(new JsonObject().put("authenticated", true).put("user", ctx.user().principal().getString("username")).encodePrettily());
		}
		else
		{
			ctx.request().response()
			.putHeader(HttpHeaders.CONTENT_TYPE, "application/json")
			.end(new JsonObject().put("authenticated", false).put("user", null).encodePrettily());
		}
	}	
	
	
	public Future<RouterBuilder> setupTaskAPI(RouterBuilder builder)
	{
		TaskRouter router = new TaskRouter(gitManager, client, resourceManager, vertx.eventBus(), soileAuthorization);
		builder.operation("getTaskList").handler(router::getElementList);
		builder.operation("getVersionsForTask").handler(router::getVersionList);
		builder.operation("createTask").handler(router::create);
		builder.operation("getTask").handler(router::getElement);
		builder.operation("updateTask").handler(router::writeElement);
		builder.operation("getResource").handler(router::getResource);
		builder.operation("putResource").handler(router::postResource);
		return Future.<RouterBuilder>succeededFuture(builder);
	}
	
	public Future<RouterBuilder> setupExperimentAPI(RouterBuilder builder)
	{
		ElementRouter<Experiment> router = new ElementRouter<Experiment>(new ElementManager<Experiment>(Experiment::new, APIExperiment::new, client, gitManager), soileAuthorization, vertx.eventBus(), client );
		builder.operation("getExperimentList").handler(router::getElementList);
		builder.operation("getVersionsForExperiment").handler(router::getVersionList);
		builder.operation("createExperiment").handler(router::create);
		builder.operation("getExperiment").handler(router::getElement);
		builder.operation("updateExperiment").handler(router::writeElement);
		return Future.<RouterBuilder>succeededFuture(builder);
	}
	
	public Future<RouterBuilder> setupProjectAPI(RouterBuilder builder)
	{
		ElementRouter<Project> router = new ElementRouter<Project>(new ElementManager<Project>(Project::new, APIProject::new, client, gitManager), soileAuthorization, vertx.eventBus(), client );
		builder.operation("getProjectList").handler(router::getElementList);
		builder.operation("getVersionsForProject").handler(router::getVersionList);
		builder.operation("createProject").handler(router::create);
		builder.operation("getProject").handler(router::getElement);
		builder.operation("updateProject").handler(router::writeElement);
		return Future.<RouterBuilder>succeededFuture(builder);
	}
		
	public Future<RouterBuilder> setupProjectexecutionAPI(RouterBuilder builder)
	{
		ProjectInstanceRouter router = new ProjectInstanceRouter(soileAuthorization, vertx, client, partHandler, projHandler);
		builder.operation("listDownloadData").handler(router::listDownloadData);
		builder.operation("startProject").handler(router::startProject);
		builder.operation("getRunningProjectList").handler(router::getRunningProjectList);
		builder.operation("stopProject").handler(router::stopProject);
		builder.operation("deleteProject").handler(router::deleteProject);
		builder.operation("getProjectResults").handler(router::getProjectResults);
		builder.operation("downloadResults").handler(router::downloadResults);
		builder.operation("downloadTest").handler(router::downloadTest);
		builder.operation("submitResults").handler(router::submitResults);
		builder.operation("getTaskType").handler(router::getTaskType);
		builder.operation("runTask").handler(router::runTask);
		builder.operation("signUpForProject").handler(router::signUpForProject);
		builder.operation("uploadData").handler(router::uploadData);			
		return Future.<RouterBuilder>succeededFuture(builder);
	}
	
	public Future<RouterBuilder> setupUserAPI(RouterBuilder builder)
	{
		UserRouter router = new UserRouter(soileAuthorization, vertx, client);
		builder.operation("listUsers").handler(router::listUsers);
		builder.operation("createUser").handler(router::createUser);
		builder.operation("removeUser").handler(router::removeUser);
		builder.operation("getUserInfo").handler(router::getUserInfo);
		builder.operation("setUserInfo").handler(router::setUserInfo);
		builder.operation("setPassword").handler(router::setPassword);
		builder.operation("setRole").handler(router::setRole);
		builder.operation("permissionChange").handler(router::permissionChange);
		builder.operation("permissionOrRoleRequest").handler(router::permissionOrRoleRequest);				
		return Future.<RouterBuilder>succeededFuture(builder);
	}
}
