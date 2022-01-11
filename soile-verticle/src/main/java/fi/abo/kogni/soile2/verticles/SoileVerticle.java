package fi.abo.kogni.soile2.verticles;

import io.vertx.core.eventbus.EventBus;
import io.vertx.core.json.JsonObject;
import fi.abo.kogni.soile2.handlers.VerticleMessageHandler;
import fi.abo.kogni.soile2.handlers.VerticleReplyHandler;
import io.vertx.core.AbstractVerticle;

public abstract class SoileVerticle extends AbstractVerticle {

    public SoileVerticle() {
        super();
    }
    
    @Override
    public void stop() {
    	try
    	{
    		super.stop();
    	}
    	catch(Exception e)
    	{
    		//pass for now
    	}
        VerticleMessageHandler handler = getMessageHandler();
        if (handler != null) {
            handler.deinit();
        }
    }

    /**
     * Get the "shared" configuration of the verticle.
     * 
     * @deprecated use {@link #getSharedConfig()} instead.
     */
    @Deprecated
    public JsonObject getShared() {
        return getShared(config());
    }
    
    /**
     * Get the "shared" configuration of the verticle.
     * 
     * @deprecated use {@link #getSharedConfig()} instead.
     */
    @Deprecated
    public JsonObject getShared(JsonObject conf) {
        return conf.getJsonObject("shared");
    }
    
    public JsonObject getSharedConfig() {
        return config().getJsonObject("shared");
    }

    public JsonObject getVerticleConfig() {
        return config().getJsonObject("config");
    }

    /**
     * Get the "private" configuration of the verticle 
     * (as opposed to "shared" configuration).
     * 
     * @deprecated use {@link #getVerticleConfig()} instead.
     */
    @Deprecated
    public JsonObject getConfig() {
        return config();
    }

    /**
     * Get the "private" configuration of the verticle 
     * (as opposed to "shared" configuration).
     * 
     * @deprecated use {@link #getVerticleConfig()} instead.
     */
    @Deprecated
    public JsonObject getConfig(JsonObject conf) {
        return conf.getJsonObject("config");
    }
    
    public JsonObject getAddresses() {
        JsonObject shared = getSharedConfig();
        return shared.getJsonObject("addresses");
    }
    
    public String getAddress(String address) {
        JsonObject addresses = getAddresses();
        return addresses.getString(address);
    }
    
    public JsonObject getDirectories() {
        JsonObject shared = getSharedConfig();
        return shared.getJsonObject("directories");
    }
    
    public String getDirectoryName(String directory) {
        JsonObject directories = getDirectories();
        return directories.getString(directory);
    }
    
    public EventBus getEventBus() {
        return vertx.eventBus();
    }    
    
    public void setMessageHandler(VerticleMessageHandler handler) {
        this.msgHandler = handler;
    }
    
    public VerticleMessageHandler getMessageHandler() {
        return this.msgHandler;
    }
    
    public void registerMessageHandler(String address, VerticleMessageHandler handler) {
        setMessageHandler(handler);
        getEventBus().consumer(address, handler);
    }
    
    public void sendMessage(String address, JsonObject message) {
        getEventBus().send(address, message);
    }
    
    public void sendMessage(String address, 
                            JsonObject message, 
                            VerticleReplyHandler replyHandler) {
        getEventBus().request(address, message, replyHandler);
    }
    
    private VerticleMessageHandler msgHandler;

}
