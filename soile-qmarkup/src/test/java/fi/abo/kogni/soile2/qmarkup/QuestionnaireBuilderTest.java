package fi.abo.kogni.soile2.qmarkup;

// import com.sun.org.apache.xpath.internal.operations.Bool;
import fi.abo.kogni.soile2.qmarkup.typespec.MalformedCommandException;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

public class QuestionnaireBuilderTest extends TestCase {

    QuestionnaireBuilder builder;
    Boolean succeeded;

    @Before
    public void setUp() throws Exception {
       String template = ".\\soile-qmarkup\\tests\\resources\\questionnaire_embedded.stg";
       builder = new QuestionnaireBuilder(template);

       succeeded = true;
    }

    @After
    public void tearDown() throws Exception {
        builder.reset();
    }

    @Test
    public void testTitle() throws Exception {
        String result = buildForm(".\\soile-qmarkup\\tests\\resources\\titleTest.qmarkup");
        System.out.println(result);
        assertTrue(succeeded);
    }

    @Test
         public void testAllWidgets() throws Exception {
        String result = buildForm(".\\soile-qmarkup\\tests\\resources\\fullWidgetTest.qmarkup");
        //System.out.println(result);
        assertTrue(succeeded);
    }

    @Test
    public void testPilotForm() throws Exception {
        String result = buildForm(".\\soile-qmarkup\\tests\\resources\\pilotform.qmarkup");
        System.out.println(result);
        assertTrue(succeeded);
    }

    @Test
    public void testFailure() throws Exception {
        String result = buildForm(".\\soile-qmarkup\\tests\\resources\\failTest.qmarkup");
        //System.out.println(result);
        assertFalse(succeeded);
    }

    @Test
    public void testIf() throws Exception {
        String result = buildForm(".\\soile-qmarkup\\tests\\resources\\testIf.qmarkup");
        System.out.println(result);
        assertFalse(succeeded);

    }

    @Test
    public void testTemplateIf() throws Exception {
        String result = buildForm(".\\soile-qmarkup\\tests\\resources\\testTemplateIf.qmarkup");
        System.out.println(result);
        assertTrue(succeeded);

    }

    @Test
    public void testNumberFieldEmpty() throws Exception {
        String result = buildForm(".\\soile-qmarkup\\tests\\resources\\testNumberField.qmarkup");
        System.out.println(result);
        assertTrue(succeeded);

    }

    @Test
    public void testRadioOptional() throws Exception {
        String result = buildForm(".\\soile-qmarkup\\tests\\resources\\testRadioOptional.qmarkup");
        System.out.println(result);
        assertTrue(succeeded);

    }


    public String buildForm(String testPath) throws IOException {
        InputReader reader = new InputReader(readFile(testPath, Charset.defaultCharset()));
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