package fi.abo.kogni.soile2.verticles;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;

import fi.abo.kogni.soile2.handlers.VerticleMessageHandler;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;

public final class DiskIOVerticle extends SoileVerticle {

	
	Logger privateLogger = Logger.getLogger("DiskIOVerticle");
	
    @Override
    public void start() {
        handler = this.new Handler();
        handler.init();
        registerMessageHandler(getAddress("disk_io"), handler);
    }
    
    private class Handler extends VerticleMessageHandler {

        public Handler() {
            super();
            this.append = false;
        }

        @Override
        public void handle(Message<JsonObject> message) {
            JsonObject json = message.body();
            File file = new File(json.getString("dirname"),
                                 json.getString("filename")).getAbsoluteFile();
            String data = json.getString("data");
            
            try {
                FileUtils.writeStringToFile(file, data, utf8, append);
            } catch (IOException e) {
                String msg = String.format("DiskIOVerticle unable to write to file '%s': %s.", 
                        file.getAbsolutePath(), e.getMessage());
                privateLogger.log(Level.SEVERE,msg);
            }
        }

        @Override
        public void init() {
            utf8 = Charset.forName("UTF-8");
        }
        
        private Charset utf8;
        private boolean append;
        
    }
    
    private Handler handler;

}
