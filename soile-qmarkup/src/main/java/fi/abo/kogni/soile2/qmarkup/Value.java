package fi.abo.kogni.soile2.qmarkup;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public abstract class Value {
    
    public static Value parse(String s) {
        try {
            return parse(new ANTLRInputStream(new StringReader(s)));
        } catch (IOException e) {
            return null;
        }
    }
    
    public static Value parse(Reader reader) {
        try {
            return parse(new ANTLRInputStream(reader));
        } catch (IOException e) {
            return null;
        }
    }
    
    public static Value parse(InputStream stream) {
        try {
            return parse(new ANTLRInputStream(stream));
        } catch (IOException e) {
            return null;
        }
    }
    
    private static Value parse(ANTLRInputStream input) {
        CommandLexer lexer = new CommandLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        CommandParser parser = new CommandParser(tokens);
        parser.setBuildParseTree(true); 
        ParseTree tree = parser.command();
        ParseTreeWalker walker = new ParseTreeWalker();
        ObjectParser op = new ObjectParser();
        walker.walk(op, tree);
        Value value = op.getValue(tree);
        return value;
    }
    
    public Value getValue(Object index) {
        return this;
    }
    
    public abstract void setValue(String index, Object value);
    
    public final void setValue(StringValue index, Object value) {
        setValue(index.toString(), value);
    }
    
    public abstract Object asJavaObject();
    
}
