package demo;

import java.io.FileInputStream;
import java.io.InputStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class Test001 {
    
    public static class BlockLengthCounter extends DemoLangV001BaseListener {

        @Override
        public void enterBlock(DemoLangV001Parser.BlockContext ctx) {
            System.out.println("Block length: " + ctx.getChildCount());
        }

        @Override
        public void enterStmt(DemoLangV001Parser.StmtContext ctx) {
            System.out.println("Rule index: " + ctx.getRuleIndex());
        }
        
    }
    
    public static void main(String[] args) throws Exception {
        String filename = "/Users/tuope/temp/demoinput001";
        InputStream is = new FileInputStream(filename);
        ANTLRInputStream input = new ANTLRInputStream(is);
        DemoLangV001Lexer lexer = new DemoLangV001Lexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        DemoLangV001Parser parser = new DemoLangV001Parser(tokens);
        RuleContext tree = parser.script();
        ParseTreeWalker walker = new ParseTreeWalker();
        BlockLengthCounter counter = new BlockLengthCounter();
        walker.walk(counter, tree);
    }
    
}
