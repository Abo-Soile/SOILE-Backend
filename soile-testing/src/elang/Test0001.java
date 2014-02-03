package elang;

import java.io.FileInputStream;
import java.io.InputStream;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import fi.abo.kogni.soile2.elang.ElangLexer;
import fi.abo.kogni.soile2.elang.ElangParser;
import fi.abo.kogni.soile2.elang.SymtabPass;

public class Test0001 {

    public static void main(String[] args) throws Exception {
        String filename = "/Users/tuope/programming/soile/explang/demoinput002";
        InputStream is = new FileInputStream(filename);
        ANTLRInputStream input = new ANTLRInputStream(is);
        ElangLexer lexer = new ElangLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ElangParser parser = new ElangParser(tokens);
        RuleContext tree = parser.file();
        ParseTreeWalker walker = new ParseTreeWalker();
        SymtabPass p1 = new SymtabPass();
        walker.walk(p1, tree);
    }

}
