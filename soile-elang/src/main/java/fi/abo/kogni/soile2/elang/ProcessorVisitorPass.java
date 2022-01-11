package fi.abo.kogni.soile2.elang;

import java.util.IdentityHashMap;

import org.antlr.v4.runtime.ParserRuleContext;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;

public abstract class ProcessorVisitorPass<T> extends ElangBaseVisitor<T> {
    
    public final void symbolTable(SymbolTable symtab) {
        this.symtab = symtab;
    }

    public final SymbolTable symbolTable() {
        return this.symtab;
    }
    
    public final void outputTo(GeneratedCodeOutput output) {
        this.codeOutput = output;
    }
    
    public final GeneratedCodeOutput codeOutput() {
        return this.codeOutput;
    }

    public final void templateGroup(STGroup tmpl) {
        this.tmpl = tmpl;
    }
    
    public final ST template(String name) {
        return tmpl.getInstanceOf(name);
    }

    public final void nodeData(IdentityHashMap<ParserRuleContext, NodeData> nd) {
        this.nodeData = nd;
    }
    
    public final NodeData nodeData(ParserRuleContext ctx) {
        return nodeData.get(ctx);
    }
    
    private IdentityHashMap<ParserRuleContext, NodeData> nodeData;
    private STGroup tmpl;
    private SymbolTable symtab;
    private GeneratedCodeOutput codeOutput;
    
}
