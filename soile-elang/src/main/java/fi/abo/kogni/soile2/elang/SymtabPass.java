package fi.abo.kogni.soile2.elang;

import java.util.IdentityHashMap;

import org.antlr.v4.runtime.ParserRuleContext;

import fi.abo.kogni.soile2.elang.ElangParser.FunctionDefContext;
import fi.abo.kogni.soile2.elang.ElangParser.FunctionDefParamContext;
import fi.abo.kogni.soile2.elang.ElangParser.FvarDefContext;
import fi.abo.kogni.soile2.elang.ElangParser.GvarDefContext;
import fi.abo.kogni.soile2.elang.ElangParser.IdentifierContext;
import fi.abo.kogni.soile2.elang.ElangParser.PhaseFunctionDefContext;
import fi.abo.kogni.soile2.elang.ElangParser.ValDefContext;
import fi.abo.kogni.soile2.elang.ElangParser.VarDefContext;

/*
 * The purpose of this translator pass is to
 * create a symbol table.
 */
public class SymtabPass extends ProcessorListenerPass {

    public SymtabPass() {
        this(new IdentityHashMap<ParserRuleContext, NodeData>());
    }

    public SymtabPass(IdentityHashMap<ParserRuleContext, NodeData> nodeData) {
        super(nodeData);
    }
    
    @Override
    public void enterPhaseFunctionDef(PhaseFunctionDefContext ctx) {
        getSymbolTable().newLocalScope(ctx);
        getSymbolTable().intoLocalScope(ctx);
    }

    @Override
    public void exitPhaseFunctionDef(PhaseFunctionDefContext ctx) {
        getSymbolTable().intoGlobalScope();
    }

    @Override
    public void enterFunctionDef(FunctionDefContext ctx) {
        String fname = ctx.identifier().getText();
        if (getSymbolTable().defined(fname)) {
            String msg = String.format("Cannot define function '%s': variable '%s' already defined.", fname, fname);
            addError(msg);
            return;
        }
        getSymbolTable().definefn(fname);
        
        // Functions are considered a scope.
        getSymbolTable().newLocalScope(ctx);
        getSymbolTable().intoLocalScope(ctx);
    }

    @Override
    public void exitFunctionDef(FunctionDefContext ctx) {
        getSymbolTable().intoGlobalScope();
    }
    
    @Override
    public void enterFunctionDefParam(FunctionDefParamContext ctx) {
        String name = ctx.ID().getText();
        getSymbolTable().defineparam(name);
    }

    @Override
    public void enterGvarDef(GvarDefContext ctx) {
        IdentifierContext id = ctx.identifier();
        String name = id.getText();
        if (getSymbolTable().defined(name)) {
            String msg = String.format("Cannot define gvar '%s': variable '%s' already defined.", name, name);
            addError(msg);
            return;
        }
        getSymbolTable().definegvar(name);
    }

    @Override
    public void enterValDef(ValDefContext ctx) {
        IdentifierContext id = ctx.identifier();
        String name = id.getText();
        if (getSymbolTable().defined(name)) {
            String msg = String.format("Cannot define val '%s': variable '%s' already defined.", name, name);
            addError(msg);
            return;
        }
        getSymbolTable().defineval(name);
    }

    @Override
    public void enterFvarDef(FvarDefContext ctx) {
        IdentifierContext id = ctx.identifier();
        String name = id.getText();
        if (getSymbolTable().defined(name)) {
            String msg = String.format("Cannot define fvar '%s': variable '%s' already defined.", name, name);
            addError(msg);
            return;
        }
        getSymbolTable().definefvar(name);
    }

    @Override
    public void enterVarDef(VarDefContext ctx) {
        IdentifierContext id = ctx.identifier();
        String name = id.getText();
        if (getSymbolTable().defined(name)) {
            String msg = String.format("Cannot define var '%s': variable '%s' already defined.", name, name);
            addError(msg);
            return;
        }
        getSymbolTable().definevar(name);
    }

}
