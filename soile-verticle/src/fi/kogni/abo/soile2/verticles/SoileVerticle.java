package fi.kogni.abo.soile2.verticles;

import org.vertx.java.core.eventbus.EventBus;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.core.logging.Logger;
import org.vertx.java.platform.Verticle;

import fi.kogni.abo.soile2.handlers.VerticleMessageHandler;
import fi.kogni.abo.soile2.handlers.VerticleReplyHandler;

public abstract class SoileVerticle extends Verticle {

    public SoileVerticle() {
        super();
    }
    
    @Override
    public void stop() {
        super.stop();
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
        return getShared(getContainer().config());
    }
    
    /**
     * Get the "shared" configuration of the verticle.
     * 
     * @deprecated use {@link #getSharedConfig()} instead.
     */
    @Deprecated
    public JsonObject getShared(JsonObject conf) {
        return conf.getObject("shared");
    }
    
    public JsonObject getSharedConfig() {
        return getContainer().config().getObject("shared");
    }

    public JsonObject getVerticleConfig() {
        return getContainer().config().getObject("config");
    }

    /**
     * Get the "private" configuration of the verticle 
     * (as opposed to "shared" configuration).
     * 
     * @deprecated use {@link #getVerticleConfig()} instead.
     */
    @Deprecated
    public JsonObject getConfig() {
        return getConfig(container.config());
    }

    /**
     * Get the "private" configuration of the verticle 
     * (as opposed to "shared" configuration).
     * 
     * @deprecated use {@link #getVerticleConfig()} instead.
     */
    @Deprecated
    public JsonObject getConfig(JsonObject conf) {
        return conf.getObject("config");
    }
    
    public JsonObject getAddresses() {
        JsonObject shared = getSharedConfig();
        return shared.getObject("addresses");
    }
    
    public String getAddress(String address) {
        JsonObject addresses = getAddresses();
        return addresses.getString(address);
    }
    
    public JsonObject getDirectories() {
        JsonObject shared = getSharedConfig();
        return shared.getObject("directories");
    }
    
    public String getDirectoryName(String directory) {
        JsonObject directories = getDirectories();
        return directories.getString(directory);
    }
    
    public EventBus getEventBus() {
        return vertx.eventBus();
    }
    
    public Logger getLogger() {
        return container.logger();
    }
    
    public void setMessageHandler(VerticleMessageHandler handler) {
        this.msgHandler = handler;
    }
    
    public VerticleMessageHandler getMessageHandler() {
        return this.msgHandler;
    }
    
    public void registerMessageHandler(String address, VerticleMessageHandler handler) {
        setMessageHandler(handler);
        getEventBus().registerHandler(address, handler);
    }
    
    public void sendMessage(String address, JsonObject message) {
        getEventBus().send(address, message);
    }
    
    public void sendMessage(String address, 
                            JsonObject message, 
                            VerticleReplyHandler replyHandler) {
        getEventBus().send(address, message, replyHandler);
    }
    
    private VerticleMessageHandler msgHandler;

}
