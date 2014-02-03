package fi.kogni.abo.soile2.handlers;

import org.vertx.java.core.Handler;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.json.JsonObject;

public abstract class BaseHandler implements Handler<Message<JsonObject>> {

    @Override
    public abstract void handle(Message<JsonObject> message);
    
    public abstract void init();
    
    public void deinit() { /* empty */ }

}
