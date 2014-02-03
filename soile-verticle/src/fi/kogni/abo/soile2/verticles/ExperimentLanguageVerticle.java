package fi.kogni.abo.soile2.verticles;

import java.io.StringReader;

import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.json.JsonObject;

import fi.abo.kogni.soile2.elang.CodeGenerator;
import fi.kogni.abo.soile2.handlers.VerticleMessageHandler;

public class ExperimentLanguageVerticle extends SoileVerticle {

    public ExperimentLanguageVerticle() {
        super();
    }

    @Override
    public void start() {
        super.start();
        JsonObject config = getVerticleConfig();
        String address = getAddress("experiment_language");
        String templateFile = config.getString("code-gen-template");
        STGroup templateGroup = new STGroupFile(templateFile);
        Handler handler = this.new Handler(templateGroup);
        handler.init();
        registerMessageHandler(address, handler);
    }

    private class Handler extends VerticleMessageHandler {

        public Handler(STGroup tmpl) {
            this.template = tmpl;
        }

        @Override
        public void handle(Message<JsonObject> message) {
            JsonObject json = message.body();
            JsonObject reply = new JsonObject();
            CodeGenerator codeGen = new CodeGenerator(template);
            StringBuilder code = new StringBuilder(); 
            StringBuilder errors = new StringBuilder();
            codeGen.codeOutputTo(code);
            codeGen.errorMessagesTo(errors);
            StringReader input = new StringReader(json.getString("code"));
            codeGen.generate(input);
            if (errors.length() > 0) {
                reply.putString("errors", errors.toString());
            }
            else {
                reply.putString("code", code.toString());
            }
            message.reply(reply);
        }

        @Override
        public void init() {
        }
        
        private STGroup template;
    }
}
