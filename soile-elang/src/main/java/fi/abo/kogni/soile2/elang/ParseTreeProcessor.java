package fi.abo.kogni.soile2.elang;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class ParseTreeProcessor {
    
    public ParseTreeProcessor(ParseTree tree) {
        super();
        this.tree = tree;
    }
    
    public void walker(ParseTreeWalker ptw) {
        this.ptWalker = ptw;
    }
    
    public void walk(ProcessorListenerPass listener) 
            throws ParseTreeProcessorException {
        this.walk(this.ptWalker, listener);
     }
     
    public void walk(ParseTreeWalker walker, ProcessorListenerPass listener) 
           throws ParseTreeProcessorException {
        walker.walk(listener, tree);
        if (! listener.errorFree()) {
            String msg = listener.nextErrorMessage();
            throw new ParseTreeProcessorException(msg);
        }
    }
    
    public <T> T visit(ProcessorVisitorPass<T> visitor) {
        return visitor.visit(tree);
    }

    private ParseTree tree;
    private ParseTreeWalker ptWalker;
}
