package fi.abo.kogni.soile2.verticles;

import fi.abo.kogni.soile2.handlers.VerticleMessageHandler;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;

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
