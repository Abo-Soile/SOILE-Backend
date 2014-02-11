package fi.kogni.abo.soile2.verticles;

import java.nio.ByteBuffer;

import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.json.JsonObject;

import fi.abo.kogni.soile2.qmarkup.InputReader;
import fi.abo.kogni.soile2.qmarkup.QuestionnaireBuilder;
import fi.abo.kogni.soile2.qmarkup.Template;
import fi.abo.kogni.soile2.qmarkup.typespec.MalformedCommandException;
import fi.abo.kogni.soile2.utils.generator.IdGenerator;
import fi.kogni.abo.soile2.handlers.VerticleMessageHandler;

public final class QuestionnaireRenderVerticle extends SoileVerticle {

    public QuestionnaireRenderVerticle() {
        super();
    }

    @Override
    public void start() {
        JsonObject config = getVerticleConfig();
        this.handler = this.new Handler(config.getString("templates"));
        this.handler.init();
        String address = getAddress("questionnaire_render");
        registerMessageHandler(address, handler);
    }
    //Saves the questioneer to disk
    private void saveToDisk(String id, String questionnaire) {
        JsonObject msg = new JsonObject();
        msg.putString("dirname", getDirectoryName("questionnaires"));
        msg.putString("filename", id);
        msg.putString("data", questionnaire);
        sendMessage(getAddress("disk_io"), msg);
        
        // TODO Send a message to garbage collector verticle, too.
    }

    //Saves the questioneer to mongo
    private void saveToMongo(String id, String questionnaire) {
        JsonObject msg = new JsonObject();

        JsonObject data = new JsonObject();
        data.putString("form", questionnaire);

        msg.putString("action","save");
        msg.putString("collection", "forms");
        msg.putObject("document",data);

        sendMessage("vertx.mongo-persistor", msg);
    }
    
    private class Handler extends VerticleMessageHandler {

        public Handler(String directory) {
            super();
            this.directory = directory;
            generator = IdGenerator.shortIdGenerator();
        }

        @Override
        public void init() {
            Template template = new Template(directory);
            this.builder = new QuestionnaireBuilder(template);
            generator.seed(1024);
            generator.init();
        }
        
        @Override
        public void handle(Message<JsonObject> message) {
            JsonObject json = message.body();
            JsonObject reply = new JsonObject();
            reply.putString("id", "");
            String markup = json.getString("markup");
            InputReader reader = new InputReader(markup);
            reader.addListener(builder);
            builder.questionnaireId("questionnaire-id");
            builder.encryptionKey("vr7DlZqAyY061Y9M");
            
            try {
                reader.processInput();
                builder.finish();
                String output = builder.output();
                ByteBuffer buffer = ByteBuffer.allocate(8);
                buffer.putLong(0, System.currentTimeMillis());
                generator.update(buffer);
                String id = generator.getId();
                reply.putString("id", id);
                saveToDisk(id, output);
                saveToMongo(id, output);
                builder.reset();
                generator.reset();
            } catch (MalformedCommandException e) {
                reply.putString("error", e.getMessage());
                builder.reset();
                generator.reset();
            }

            message.reply(reply);
        }
        
        private QuestionnaireBuilder builder;
        private IdGenerator generator;
        private String directory;
    }
    
    private Handler handler;
}
