package fi.abo.kogni.soile2.verticles;

import java.nio.ByteBuffer;

import io.vertx.core.AsyncResult;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;
import fi.abo.kogni.soile2.handlers.VerticleMessageHandler;
import fi.abo.kogni.soile2.qmarkup.InputReader;
import fi.abo.kogni.soile2.qmarkup.QuestionnaireBuilder;
import fi.abo.kogni.soile2.qmarkup.typespec.MalformedCommandException;
import fi.abo.kogni.soile2.utils.generator.IdGenerator;



// This verticle handles generation, verification and storage/update of qustioneers.
// Message format: {"action": <action>, "markup": <markup>, "id":<id>}
//
// Action can be either render or save. The collection will be updated if a ID is provided,
// if not a new "form" is created. Id's are built using a self implemented method that
// generates a sh256 hash based on the form input, whould most likely be better to use mongos
// inbuilt _id but this should work for now
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

    //Saves the questioneer to mongo
    private void saveToMongo(String id, String markup,String questionnaire) {
        JsonObject msg = new JsonObject();

        JsonObject data = new JsonObject();
        data.put("_id", id);
        data.put("form", questionnaire);
        data.put("markup", markup);

        msg.put("action","save");
        msg.put("collection", "forms");
        msg.put("document",data);
        /* Debugging.
        vertx.eventBus().request("vertx.mongo-persistor", msg, new io.vertx.core.Handler<AsyncResult<Message<JsonObject>>>() {
            @Override
            public void handle(AsyncResult<Message<JsonObject>> message) {
                System.out.println(message.result().body().toString());
            }
        });*/
                sendMessage("vertx.mongo-persistor", msg);
    }

    private class Handler extends VerticleMessageHandler {
        
    	private QuestionnaireBuilder builder;
        private IdGenerator generator;
        private String directory;
        
        public Handler(String directory) {
            super();
            this.directory = directory;
            generator = IdGenerator.shortIdGenerator();
        }

        @Override
        public void init() {
            //Template template = new Template(directory);
            this.builder = new QuestionnaireBuilder(directory);
            generator.seed(1024);
            generator.init();
        }

        @Override
        public void handle(Message<JsonObject> message) {
            JsonObject json = message.body();
            JsonObject reply = new JsonObject();
            reply.put("id", "");
            String markup = json.getString("markup");
            String action = json.getString("action");

            //Checking if id exists
            Boolean hasID = true;
            if(json.getString("id")==null) {
              hasID = false;
            }

            InputReader reader = new InputReader(markup);
            reader.addListener(builder);
            builder.questionnaireId("questionnaire-id");
            //This key is currently completely unused
            builder.encryptionKey("vr7DlZqAyY061Y9M");

            ByteBuffer buffer = ByteBuffer.allocate(8);
            buffer.putLong(0, System.currentTimeMillis());
            generator.update(buffer);
            String id = generator.getId();

            try {
                reader.processInput();
                builder.finish();
                String output = builder.output();

                reply.put("id", id);
                if (action.equals("save")){
                    //saveToDisk(id, output);
                    if(hasID) {
                        saveToMongo(json.getString("id"), markup, output);
                        System.out.println("Saving with id " + json.getString("id"));
                    }else{
                        saveToMongo(id, markup, output);
                        System.out.println("Saving with generated");
                    }
                    reply.put("form", output);
                }
                if(action.equals("render")){
                    reply.put("form", output);
                }

            } catch (MalformedCommandException e) {
                reply.put("error", e.getMessage());
                if(hasID) {
                    saveToMongo(json.getString(("id")), markup, "");
                }else {
                    saveToMongo(id, markup, "");
                }
            }

            builder.reset();
            generator.reset();

            message.reply(reply);
            }

    }

    private Handler handler;
}
