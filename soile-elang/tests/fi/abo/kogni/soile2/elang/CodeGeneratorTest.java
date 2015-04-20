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

    CodeGenerator codeGen;
    StringBuilder code;
    StringBuilder errors;

    String templatePath = "./soile-elang/tests/resources/elang.stg";
    STGroup templateGroup = new STGroupFile(templatePath);

    @Before
    public void setUp() throws Exception {
        codeGen = new CodeGenerator(templateGroup);

        code = new StringBuilder();
        errors = new StringBuilder();

        codeGen.codeOutputTo(code);
        codeGen.errorMessagesTo(errors);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGenerate() throws Exception {

        String codeString = readFile("./soile-elang/tests/resources/codeTest1.elang", Charset.defaultCharset());

        StringReader input = new StringReader(codeString);
        codeGen.generate(input);


        assertEquals(errors.toString(), "");
        System.out.println(errors.toString());
    }

    @Test
    public void testVar() throws Exception {

        String codeString = readFile("./soile-elang/tests/resources/testVar.elang", Charset.defaultCharset());

        StringReader input = new StringReader(codeString);
        codeGen.generate(input);

        //System.out.println(code.toString());
        System.out.println(errors.toString());
        assertEquals(errors.toString(), "");
        System.out.println("Exiting variable test");
    }

    @Test
    public void testUnassignedVar() throws Exception {

        String codeString = readFile("./soile-elang/tests/resources/unassignedVariable.elang", Charset.defaultCharset());

        StringReader input = new StringReader(codeString);
        codeGen.generate(input);

        //System.out.println(code.toString());
        System.out.println(errors.toString());
        assertEquals(errors.toString(), "Variable arrs on line 10 is undefined");
        System.out.println("Exiting unassigned variable test");
    }

    @Test
    public void testUndefinedFunction() throws Exception {
        String codeString = readFile("./soile-elang/tests/resources/undefinedFunction.elang", Charset.defaultCharset());
        StringReader input = new StringReader(codeString);
        codeGen.generate(input);

        //System.out.println(errors.toString());

        assertEquals(errors.toString(), "Function clickd on line 2 is undefined");
    }

    @Test
    public void testAlreadyAssignedFunction() throws Exception {
        String codeString = readFile("./soile-elang/tests/resources/alreadyAssignedFunction.elang", Charset.defaultCharset());
        StringReader input = new StringReader(codeString);
        codeGen.generate(input);

        //System.out.println(errors.toString());

        assertEquals(errors.toString(), "Cannot define function 'helptext': variable 'helptext' already defined.");
    }

    /*
        This test reproduces a bug in the compiler where jumps in the iteration phase are
        incorrect, +1 bigger than they should be.
    */

    @Test
    public void testBuggyJumps() throws Exception {
        String codeString = readFile("./soile-elang/tests/resources/buggedJumps.elang", Charset.defaultCharset());

        StringReader input = new StringReader(codeString);
        codeGen.generate(input);

        //System.out.println(errors.toString());
    }

    @Test
    public void testBuggyJumps2() throws Exception {
        String codeString = readFile("./soile-elang/tests/resources/buggyJumps2.elang", Charset.defaultCharset());

        StringReader input = new StringReader(codeString);
        codeGen.generate(input);

        System.out.println(code.toString());

        //System.out.println(errors.toString());
    }

    public void testWait() throws Exception {
        String codeString = readFile("./soile-elang/tests/resources/testWait.elang", Charset.defaultCharset());

        StringReader input = new StringReader(codeString);
        codeGen.generate(input);

        System.out.println(code.toString());
        System.out.println(errors.toString());

        assertEquals(errors.toString(), "");

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