package fi.abo.kogni.soile2.http_server.auth;

import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;

public class UpdateUserFromDBHandler implements Handler<RoutingContext> {

	@Override
	public void handle(RoutingContext context) {
		if(context.user() != null)
		{
			
		}
	}

}
