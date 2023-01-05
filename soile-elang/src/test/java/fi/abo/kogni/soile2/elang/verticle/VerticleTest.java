package fi.abo.kogni.soile2.elang.verticle;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import fi.abo.kogni.soile2.elang.CodeGeneratorTest;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;

@RunWith(VertxUnitRunner.class)
public class VerticleTest {
	
	Vertx vertx;
	String verticleAddress = "expLangAddr";
	@Before
	public void setup(TestContext context)
	{
		vertx = Vertx.vertx();
		Async deployVerticle = context.async();
		ExperimentLanguageVerticle vert = new ExperimentLanguageVerticle(verticleAddress);
		vertx.deployVerticle(vert).onSuccess(deployed -> {
			deployVerticle.complete();
		})
		.onFailure(err -> context.fail(err));
	}
	@Test
	public void testWait(TestContext context)
	{
		try
		{
			String Code = readFile("testWait.elang", Charset.defaultCharset());
			JsonObject codeRequest = new JsonObject().put("code",Code);
			Async codeReAsync = context.async();
			vertx.eventBus().request(verticleAddress, codeRequest)
			.onSuccess( response -> {
				JsonObject reply = (JsonObject) response.body();
				System.out.println(reply.encodePrettily());
				codeReAsync.complete();
			})
			.onFailure(err -> context.fail(err));
		}
		catch(IOException e)
		{
			context.fail(e);
		}
	
	}

	@Test
	public void testFirstTask(TestContext context)
	{
		try
		{
			String Code = readFile("FirstTask.elang", Charset.defaultCharset());
			JsonObject codeRequest = new JsonObject().put("code",Code);
			Async codeReAsync = context.async();
			vertx.eventBus().request(verticleAddress, codeRequest)
			.onSuccess( response -> {
				JsonObject reply = (JsonObject) response.body();
				System.out.println(reply.encodePrettily());
				codeReAsync.complete();
			})
			.onFailure(err -> context.fail(err));
		}
		catch(IOException e)
		{
			context.fail(e);
		}
	
	}
	
	@After
	public void shutdown(TestContext context)
	{
		vertx.close();
	}
	
	
    static String readFile(String path, Charset encoding)    
            throws IOException
    {
    	URL resource = VerticleTest.class.getClassLoader().getResource(path);
        byte[] encoded = Files.readAllBytes(Paths.get(resource.getPath()));
        return new String(encoded, encoding);
    }
}
