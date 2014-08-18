package fi.abo.kogni.soile2.elang;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.*;

public class CodeGeneratorTest extends TestCase {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGenerate() throws Exception {

        System.out.println(new File(".").getAbsolutePath());

        //URL templatePath = this.getClass().getResource("elang.stg");
        //System.out.println(new File(templatePath.toURI()).getAbsolutePath());

        String templatePath = "./soile-elang/tests/resources/elang.stg";
        //System.out.println(templatePath.toString());
        //String templatePath = "./tests/resources/elang.stg";
        STGroup templateGroup = new STGroupFile(templatePath);

        CodeGenerator codeGen = new CodeGenerator(templateGroup);

        StringBuilder code = new StringBuilder();
        StringBuilder errors = new StringBuilder();
        codeGen.codeOutputTo(code);
        codeGen.errorMessagesTo(errors);

        String codeString = readFile("./soile-elang/tests/resources/codeTest1.elang", Charset.defaultCharset());

        StringReader input = new StringReader(codeString);
        codeGen.generate(input);

        //System.out.println(code.toString());
        System.out.println(errors.toString());
    }

    @Test
    public void testRandom() throws Exception {
        System.out.println("Random test is random");
        assertEquals(1,1);
    }

    static String readFile(String path, Charset encoding)
            throws IOException
    {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }
}