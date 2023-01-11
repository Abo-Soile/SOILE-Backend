package fi.abo.kogni.soile2.qmarkup.verticle;

import java.net.URL;

import fi.abo.kogni.soile2.qmarkup.InputReader;
import fi.abo.kogni.soile2.qmarkup.QuestionnaireBuilder;
import fi.abo.kogni.soile2.qmarkup.typespec.MalformedCommandException;
import fi.abo.kogni.soile2.utils.generator.IdGenerator;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;



// This verticle handles generation, verification of questionnaires.

public final class QuestionnaireRenderVerticle extends AbstractVerticle {

	private QuestionnaireBuilder builder;
	private IdGenerator generator;	
	private String address;
	public QuestionnaireRenderVerticle(String address)
	{
		this.address = address;
	}
	
	@Override
	public void start() {		
		URL template = QuestionnaireRenderVerticle.class.getClassLoader().getResource("questionnaire_embedded.stg");
	    builder = new QuestionnaireBuilder(template);
		generator = IdGenerator.shortIdGenerator();		
		generator.seed(1024);
		generator.init();		    
		vertx.eventBus().consumer(address, this::handle);		
	}
	// only one of this should ever be called simultaneously.
	// we might want to put in a few of these as worker verticles.
	private synchronized void handle(Message<JsonObject> message) {
		JsonObject json = message.body();
		JsonObject reply = new JsonObject();
		String markup = json.getString("code");
		try{						            
			InputReader reader = new InputReader(markup);
			reader.addListener(builder);
			builder.questionnaireId("questionnaire-id");
			//This key is currently completely unused
			builder.encryptionKey("vr7DlZqAyY061Y9M");		
			reader.processInput();
			builder.finish();
			String output = builder.output();
			reply.put("code", output);
		} catch (MalformedCommandException e) {
			reply.put("errors", e.getMessage());                
		}
		finally
		{
			builder.reset();
			generator.reset();	
		}
		message.reply(reply);
	}

}


