package fi.abo.kogni.soile2.elang;

import java.util.ArrayList;
import java.util.Iterator;

import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class TreeWalkerChain {
    
    public TreeWalkerChain() {
        super();
        this.passes = new ArrayList<>();
    }
    
    public void addPass(ProcessorListenerPass p) {
        passes.add(p);
    }
    
    public void setWalker(ParseTreeWalker w) {
        this.walker = w;
    }
    
    public void setTreeRoot(ParseTree tree) {
        this.root = tree;
    }
    
    public void walk() throws ParseTreeProcessorException {
        Iterator<ProcessorListenerPass> it = passes.iterator();
        while (it.hasNext()) {
            ProcessorListenerPass p = (ProcessorListenerPass) it.next();
            walker.walk(p, root);
            if (! p.errorFree()) {
                String msg = p.nextErrorMessage();
                throw new ParseTreeProcessorException(msg);
            }
        }
    }

    private ArrayList<ProcessorListenerPass> passes;
    private ParseTreeWalker walker;
    private ParseTree root;
}
