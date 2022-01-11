package fi.abo.kogni.soile2.elang;

import java.util.ArrayDeque;
import java.util.IdentityHashMap;

import org.antlr.v4.runtime.ParserRuleContext;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;

import fi.abo.kogni.soile2.elang.ElangParser.BlockContext;
import fi.abo.kogni.soile2.elang.ElangParser.FileContext;
import fi.abo.kogni.soile2.elang.ElangParser.FunctionCallContext;
import fi.abo.kogni.soile2.elang.ElangParser.FunctionDefContext;
import fi.abo.kogni.soile2.elang.ElangParser.PhaseFunctionDefContext;
import fi.abo.kogni.soile2.elang.ElangParser.StmtAssignContext;
import fi.abo.kogni.soile2.elang.ElangParser.StmtBreakContext;
import fi.abo.kogni.soile2.elang.ElangParser.StmtContext;
import fi.abo.kogni.soile2.elang.ElangParser.StmtContinueContext;
import fi.abo.kogni.soile2.elang.ElangParser.StmtIfContext;
import fi.abo.kogni.soile2.elang.ElangParser.StmtIfElseContext;
import fi.abo.kogni.soile2.elang.ElangParser.StmtReturnContext;
import fi.abo.kogni.soile2.elang.ElangParser.StmtWhileContext;

/**
 * The purpose of this translator pass is to 
 * render the statements for the final 
 * code generation pass.
 */
public class StmtRenderPass extends ProcessorListenerPass {

    public StmtRenderPass(IdentityHashMap<ParserRuleContext, NodeData> nodeData) {
        super(nodeData);
        this.blockStack = new ArrayDeque<>();
        this.gc4b = new IdentityHashMap<>();
    }

    @Override
    public void useTemplate(STGroup tmpl) {
        this.tmpl = tmpl;
    }
    
    @Override
    public void enterFile(FileContext ctx) {
        getSymbolTable().intoGlobalScope();
    }

    @Override
    public void exitFile(FileContext ctx) {
        // Clear data.
        this.blockStack.clear();
        this.gc4b.clear();
    }

    @Override
    public void enterPhaseFunctionDef(PhaseFunctionDefContext ctx) {
        getSymbolTable().intoLocalScope(ctx);
    }

    @Override
    public void exitPhaseFunctionDef(PhaseFunctionDefContext ctx) {
        getSymbolTable().intoGlobalScope();
    }

    @Override
    public void enterFunctionDef(FunctionDefContext ctx) {
        getSymbolTable().intoLocalScope(ctx);
    }

    @Override
    public void exitFunctionDef(FunctionDefContext ctx) {
        getSymbolTable().intoGlobalScope();
    }
    
    @Override
    public void enterBlock(BlockContext ctx) {
        gc4b.put(ctx, new StringBuilder());
        blockStack.addFirst(ctx);
    }

    @Override
    public void exitBlock(BlockContext ctx) {
        blockStack.removeFirst();
    }

    @Override
    public void enterStmtAssign(StmtAssignContext ctx) {
        ST st = tmpl.getInstanceOf("stmtAssign");
        String idName = ctx.identifier().getText();
        st.add("name", getSymbolTable().jsref(idName));
        st.add("value", getNodeData(ctx.expr()).jsref);
        addStmtCode(ctx, st);
    }

    @Override
    public void enterFunctionCall(FunctionCallContext ctx) {
        if (ctx.getParent() instanceof StmtContext) {
            // This function call is a statement
            ST st = tmpl.getInstanceOf("stmtFunccall");
            String name = ctx.functionIdentifier().identifier().getText();
            String params = getNodeData(ctx.functionCallParams()).jsref;
            st.add("name", getSymbolTable().jsref(name));
            st.add("params", params);
            addStmtCode(ctx, st);
        }
    }

    @Override
    public void exitStmtBreak(StmtBreakContext ctx) {
        addStmtCode(ctx, tmpl.getInstanceOf("stmtBreak"));
    }

    @Override
    public void exitStmtContinue(StmtContinueContext ctx) {
        addStmtCode(ctx, tmpl.getInstanceOf("stmtContinue"));
    }

    @Override
    public void exitStmtReturn(StmtReturnContext ctx) {
        ST st = tmpl.getInstanceOf("stmtReturn");
        st.add("expr", getNodeData(ctx.expr()).jsref);
        addStmtCode(ctx, st);
    }

    @Override
    public void exitStmtIf(StmtIfContext ctx) {
        ST st = tmpl.getInstanceOf("stmtIf");
        String expr = getNodeData(ctx.expr()).jsref;
        BlockContext block = ctx.block();
        String ifBlock = getBlockCode(block);
        st.add("expr", expr);
        st.add("ifBlock", ifBlock);
        addStmtCode(ctx, st);
        clearBlockCode(block);
    }

    @Override
    public void exitStmtIfElse(StmtIfElseContext ctx) {
        ST st = tmpl.getInstanceOf("stmtIfElse");
        String expr = getNodeData(ctx.expr()).jsref;
        BlockContext ifBlock = ctx.block(0);
        BlockContext elseBlock = ctx.block(1);
        st.add("expr", expr);
        st.add("ifBlock", getBlockCode(ifBlock));
        st.add("elseBlock", getBlockCode(elseBlock));
        addStmtCode(ctx, st);
        clearBlockCode(ifBlock);
        clearBlockCode(elseBlock);
    }

    @Override
    public void exitStmtWhile(StmtWhileContext ctx) {
        ST st = tmpl.getInstanceOf("stmtWhile");
        BlockContext block = ctx.block();
        st.add("expr", getNodeData(ctx.expr()).jsref);
        st.add("block", getBlockCode(block));
        addStmtCode(ctx, st);
        clearBlockCode(block);
    }
    
    private void addStmtCode(ParserRuleContext ctx, ST st) {
        NodeData nd = getNodeData(ctx);
        String code = st.render();
        nd.jsstmt = code;
        if (ctx.getParent() instanceof StmtContext) {
            getNodeData(ctx.getParent()).jsstmt = code;
        }
        //System.out.println("Addstmtcode lineno: " + Integer.toString(ctx.getStart().getLine()));
        BlockContext block = blockStack.getFirst();
        gc4b.get(block).append(code);
    }

    private void clearBlockCode(BlockContext ctx) {
        StringBuilder sb = gc4b.get(ctx);
        if (sb.length() > 0) {
            sb.delete(0, sb.length());
        }
    }
    
    private String getBlockCode(BlockContext ctx) {
        if (gc4b.containsKey(ctx)) {
            return gc4b.get(ctx).toString();
        }
        return "";
    }
    
    private STGroup tmpl;
    private ArrayDeque<BlockContext> blockStack;
    
    /**
     * Generated Code for Block (gc4b).
     */
    private IdentityHashMap<BlockContext, StringBuilder> gc4b;

}
