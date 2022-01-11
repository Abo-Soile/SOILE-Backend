package fi.abo.kogni.soile2.handlers;

import io.vertx.core.Handler;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;

public abstract class BaseHandler implements Handler<Message<JsonObject>> {

    @Override
    public abstract void handle(Message<JsonObject> message);
    
    public abstract void init();
    
    public void deinit() { /* empty */ }

}
