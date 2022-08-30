package fi.abo.kogni.soile2.handlers;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;

public abstract class VerticleReplyHandler implements Handler<AsyncResult<Message<JsonObject>>> {
	
    @Override
    public abstract void handle(AsyncResult<Message<JsonObject>> message);
    
    public abstract void init();
    
    public void deinit() { /* empty */ }

}
