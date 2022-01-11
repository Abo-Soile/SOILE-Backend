package fi.abo.kogni.soile2.qmarkup;

import java.io.StringWriter;
import java.util.BitSet;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.DefaultErrorStrategy;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import fi.abo.kogni.soile2.qmarkup.CommandLexer;
import fi.abo.kogni.soile2.qmarkup.CommandParser;
import fi.abo.kogni.soile2.qmarkup.ObjectParser;
import fi.abo.kogni.soile2.qmarkup.Value;
import fi.abo.kogni.soile2.qmarkup.typespec.MalformedCommandException;



public class InputParser {
    
    public InputParser(StringWriter channel) {
        super();
        this.errorChannel = channel;
        this.errorListener = new ErrorListener();
        this.errorStrategy = new ErrorStrategy();
    }

    public Value parse(String s) {
        char[] chars = s.toCharArray();
        int length = chars.length;
        clearChannel();
        ANTLRInputStream input = new ANTLRInputStream(chars, length);
        CommandLexer lexer = new CommandLexer(input);
        lexer.removeErrorListeners();
        lexer.addErrorListener(errorListener);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        CommandParser parser = new CommandParser(tokens);
        parser.setBuildParseTree(true);
        parser.removeErrorListeners();
        parser.addErrorListener(errorListener);
        parser.setErrorHandler(errorStrategy);
        ParseTree tree = parser.command();
        ParseTreeWalker walker = new ParseTreeWalker();
        ObjectParser op = new ObjectParser();
        walker.walk(op, tree);
        Value value = op.getValue(tree);
        return value;
    }
    
    private void clearChannel() {
        StringBuffer buffer = errorChannel.getBuffer();
        buffer.delete(0, buffer.length());
    }
    
    private class ErrorListener extends BaseErrorListener {

        public void reportAmbiguity(Parser recognizer, DFA dfa, int startIndex,
                int stopIndex, BitSet ambigAlts, ATNConfigSet configs) {}

        public void reportAttemptingFullContext(Parser recognizer, DFA dfa,
                int startIndex, int stopIndex, ATNConfigSet configs) {}

        public void reportContextSensitivity(Parser recognizer, DFA dfa,
                int startIndex, int stopIndex, ATNConfigSet configs) {}

        @Override
        public void syntaxError(Recognizer<?, ?> recognizer,
                Object offendingSymbol, int line, int charPositionInLine,
                String msg, RecognitionException e) {
            String m = String.format("Syntax error: %s.\n", msg);
            errorChannel.write(m);
        }
        
    }
    
    private class ErrorStrategy extends DefaultErrorStrategy {

        @Override
        public void recover(Parser recognizer, RecognitionException e) {
            throw new MalformedCommandException(e.getMessage());
        }

        @Override
        public Token recoverInline(Parser recognizer)
                throws RecognitionException {
            throw new MalformedCommandException();
        }

        @Override
        public void sync(Parser p) {}
        
    }

    private StringWriter errorChannel;
    private ErrorListener errorListener;
    private ErrorStrategy errorStrategy;
}
