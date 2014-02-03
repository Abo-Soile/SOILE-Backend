package fi.kogni.abo.soile2.verticles;

import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.json.JsonObject;

import fi.kogni.abo.soile2.handlers.VerticleMessageHandler;

public final class GarbageCollectorVerticle extends SoileVerticle {

    @Override
    public void start() {
        handler = this.new Handler();
        handler.init();
    }
    
    private class Handler extends VerticleMessageHandler {

        @Override
        public void handle(Message<JsonObject> message) {
        }

        @Override
        public void init() {
        }
        
    }
    
    private Handler handler;

}
