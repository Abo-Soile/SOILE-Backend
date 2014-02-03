package fi.abo.kogni.soile2.elang;

import java.util.ArrayDeque;
import java.util.IdentityHashMap;

import org.antlr.v4.runtime.ParserRuleContext;
import org.stringtemplate.v4.STGroup;

public abstract class ProcessorListenerPass extends ElangBaseListener {
    public ProcessorListenerPass() {
        this(new IdentityHashMap<ParserRuleContext, NodeData>());
    }
    
    public ProcessorListenerPass(IdentityHashMap<ParserRuleContext, NodeData> nodeData) {
        super();
        this.nodeData = nodeData;
        this.errors = new ArrayDeque<>();
    }
    
    public final IdentityHashMap<ParserRuleContext,NodeData> getNodeData() {
        return nodeData;
    }
    
    public final void setNodeData(IdentityHashMap<ParserRuleContext, NodeData> nodeData) {
        this.nodeData = nodeData;
    }
    
    public final void setSymbolTable(SymbolTable symtab) {
        this.symtab = symtab;
    }
    
    public final void setAndResetSymbolTable(SymbolTable symtab) {
        setSymbolTable(symtab);
        symtab.intoGlobalScope();
    }
    
    public final boolean errorFree() {
        return errors.isEmpty();
    }
    
    public final String nextErrorMessage() {
        if (! errors.isEmpty()) {
            return errors.removeFirst();
        }
        return "";
    }
    
    public void useTemplate(STGroup tmpl) {}

    protected final void newNodeData(ParserRuleContext key) {
        if (nodeData.containsKey(key) == false) {
            nodeData.put(key, new NodeData());
        }
    }
    
    protected final NodeData getNodeData(ParserRuleContext key) {
        newNodeData(key);
        return nodeData.get(key);
    }
    
    protected final void moveNodeData(ParserRuleContext from, ParserRuleContext to) {
        if (nodeData.containsKey(from)) {
            NodeData nd = nodeData.remove(from);
            nodeData.put(to, nd);
        }
    }

    protected final void copyNodeData(ParserRuleContext from, ParserRuleContext to) {
        if (nodeData.containsKey(from)) {
            NodeData nd = nodeData.get(from);
            nodeData.put(to, nd);
        }
    }

    protected final void addError(String msg) {
        errors.addLast(msg);
    }
    
    protected final void clearErrors() {
        errors.clear();
    }

    protected final SymbolTable getSymbolTable() {
        return symtab;
    }

    private IdentityHashMap<ParserRuleContext, NodeData> nodeData;
    private SymbolTable symtab;
    private ArrayDeque<String> errors;
}
