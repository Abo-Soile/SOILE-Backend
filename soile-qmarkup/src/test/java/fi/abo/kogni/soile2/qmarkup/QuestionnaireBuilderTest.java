package fi.abo.kogni.soile2.qmarkup;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

// import com.sun.org.apache.xpath.internal.operations.Bool;
import fi.abo.kogni.soile2.qmarkup.typespec.MalformedCommandException;
import junit.framework.TestCase;

public class QuestionnaireBuilderTest extends TestCase {

    QuestionnaireBuilder builder;
    Boolean succeeded;

    @Before
    public void setUp() throws Exception {
       String template = "questionnaire_embedded.stg";
       builder = new QuestionnaireBuilder(template);

       succeeded = true;
    }

    @After
    public void tearDown() throws Exception {
        builder.reset();
    }

    @Test
    public void testTitle() throws Exception {
        String result = buildForm("titleTest.qmarkup");
        System.out.println(result);
        assertTrue(succeeded);
    }

    @Test
         public void testAllWidgets() throws Exception {
        String result = buildForm("fullWidgetTest.qmarkup");
        //System.out.println(result);
        assertTrue(succeeded);
    }

    @Test
    public void testPilotForm() throws Exception {
        String result = buildForm("pilotform.qmarkup");

        assertTrue(succeeded);
    }

    @Test
    public void testFailure() throws Exception {
        String result = buildForm("failTest.qmarkup");
        //System.out.println(result);
        assertFalse(succeeded);
    }

    @Test
    public void testIf() throws Exception {
        String result = buildForm("testIf.qmarkup");
        System.out.println(result);
        assertTrue(succeeded);

    }

    @Test
    public void testTemplateIf() throws Exception {
        String result = buildForm("testTemplateIf.qmarkup");
        System.out.println(result);
        assertTrue(succeeded);

    }

    @Test
    public void testNumberFieldEmpty() throws Exception {
        String result = buildForm("testNumberField.qmarkup");
        System.out.println(result);
        assertTrue(succeeded);

    }

    @Test
    public void testRadioOptional() throws Exception {
        String result = buildForm("testRadioOptional.qmarkup");
        System.out.println(result);
        assertTrue(succeeded);

    }


    public String buildForm(String testPath) throws IOException {
    	URL resource = this.getClass().getClassLoader().getResource(testPath);
        InputReader reader = new InputReader(readFile(resource.getFile(), Charset.defaultCharset()));
        reader.addListener(builder);

        String output ="";

        try {
            reader.processInput();
            builder.finish();
            output = builder.output();
        } catch (MalformedCommandException e) {
            succeeded = false;
            output = e.getMessage();
        }

        return output;

    }

    static String readFile(String path, Charset encoding)
            throws IOException
    {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }
}