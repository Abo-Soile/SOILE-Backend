package fi.abo.kogni.soile2.elang;

import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;

import org.antlr.v4.runtime.ParserRuleContext;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;

import fi.abo.kogni.soile2.elang.ElangParser.ArrayContext;
import fi.abo.kogni.soile2.elang.ElangParser.ArrayLiteralContext;
import fi.abo.kogni.soile2.elang.ElangParser.BooleanLiteralContext;
import fi.abo.kogni.soile2.elang.ElangParser.ExprContext;
import fi.abo.kogni.soile2.elang.ElangParser.ExprFcallContext;
import fi.abo.kogni.soile2.elang.ElangParser.ExprIdContext;
import fi.abo.kogni.soile2.elang.ElangParser.ExprIndexingContext;
import fi.abo.kogni.soile2.elang.ElangParser.ExprLiteralContext;
import fi.abo.kogni.soile2.elang.ElangParser.ExprProprefContext;
import fi.abo.kogni.soile2.elang.ElangParser.FunctionCallContext;
import fi.abo.kogni.soile2.elang.ElangParser.FunctionCallParamsContext;
import fi.abo.kogni.soile2.elang.ElangParser.FunctionDefContext;
import fi.abo.kogni.soile2.elang.ElangParser.FunctionDefParamContext;
import fi.abo.kogni.soile2.elang.ElangParser.FunctionDefParamsContext;
import fi.abo.kogni.soile2.elang.ElangParser.FvarDefContext;
import fi.abo.kogni.soile2.elang.ElangParser.GvarDefContext;
import fi.abo.kogni.soile2.elang.ElangParser.IdentifierContext;
import fi.abo.kogni.soile2.elang.ElangParser.NothingLiteralContext;
import fi.abo.kogni.soile2.elang.ElangParser.NullLiteralContext;
import fi.abo.kogni.soile2.elang.ElangParser.NumberLiteralContext;
import fi.abo.kogni.soile2.elang.ElangParser.ObjectContext;
import fi.abo.kogni.soile2.elang.ElangParser.ObjectLiteralContext;
import fi.abo.kogni.soile2.elang.ElangParser.PairContext;
import fi.abo.kogni.soile2.elang.ElangParser.PhaseFunctionDefContext;
import fi.abo.kogni.soile2.elang.ElangParser.StmtContext;
import fi.abo.kogni.soile2.elang.ElangParser.StringLiteralContext;
import fi.abo.kogni.soile2.elang.ElangParser.TransitionCondFcallContext;
import fi.abo.kogni.soile2.elang.ElangParser.TransitionCondVarContext;
import fi.abo.kogni.soile2.elang.ElangParser.TransitionDefsContext;
import fi.abo.kogni.soile2.elang.ElangParser.ValDefContext;
import fi.abo.kogni.soile2.elang.ElangParser.VarDefContext;
import fi.abo.kogni.soile2.elang.ElangParser.YesNoLiteralContext;
import fi.abo.kogni.soile2.elang.SymbolTable.Symbol;

/*
 * The purpose of this translator pass is to
 * compute representations of "JavaScript expressions".
 */
public class JsexprPass extends ProcessorListenerPass {

    public JsexprPass(IdentityHashMap<ParserRuleContext, NodeData> nodeData) {
        super(nodeData);
    }
    
    @Override
    public void useTemplate(STGroup tmpl) {
        this.tmpl = tmpl;
        this.stgvar     = this.tmpl.getInstanceOf("ref2gvar");
        this.stval      = this.tmpl.getInstanceOf("ref2val");
        this.stfvar     = this.tmpl.getInstanceOf("ref2fvar");
        this.stfn       = this.tmpl.getInstanceOf("ref2fn");
        this.stparam    = this.tmpl.getInstanceOf("ref2param");
        this.stExprIndexing = this.tmpl.getInstanceOf("exprIndexing");
        this.stExprPropRef = this.tmpl.getInstanceOf("exprPropRef");
        this.stExprFuncCall = this.tmpl.getInstanceOf("exprFuncCall");
        this.varRef = this.new VarRef(
                this.tmpl.getInstanceOf("ref2varLong"),
                this.tmpl.getInstanceOf("ref2varShort"));
    }

    @Override
    public void enterPhaseFunctionDef(PhaseFunctionDefContext ctx) {
        getSymbolTable().intoLocalScope(ctx);
        varRef.toLongMode();
    }

    @Override
    public void exitPhaseFunctionDef(PhaseFunctionDefContext ctx) {
        getSymbolTable().intoGlobalScope();
        varRef.toDefaultMode();
    }

    @Override
    public void enterTransitionDefs(TransitionDefsContext ctx) {
        getSymbolTable().intoGlobalScope();
        varRef.toLongMode();
    }

    @Override
    public void exitTransitionDefs(TransitionDefsContext ctx) {
        varRef.toDefaultMode();
    }

    @Override
    public void enterNullLiteral(NullLiteralContext ctx) {
        NodeData nd = getNodeData(ctx);
        nd.jsref = ctx.getText();
    }

    @Override
    public void enterNumberLiteral(NumberLiteralContext ctx) {
        NodeData nd = getNodeData(ctx);
        nd.jsref = ctx.number().NUMBER().getText();
    }

    @Override
    public void enterBooleanLiteral(BooleanLiteralContext ctx) {
        NodeData nd = getNodeData(ctx);
        nd.jsref = ctx.getText();
    }

    @Override
    public void enterYesNoLiteral(YesNoLiteralContext ctx) {
        NodeData nd = getNodeData(ctx);
        if (ctx.getText().compareTo("yes") == 0) {
            nd.jsref = "true";
        }
        else {
            nd.jsref = "false";
        }
    }

    @Override
    public void enterNothingLiteral(NothingLiteralContext ctx) {
        NodeData nd = getNodeData(ctx);
        nd.jsref = "null";
    }

    @Override
    public void enterStringLiteral(StringLiteralContext ctx) {
        NodeData nd = getNodeData(ctx);
        nd.jsref = ctx.string().STRING().getText();
    }

    @Override
    public void exitObjectLiteral(ObjectLiteralContext ctx) {
        copyNodeData(ctx.object(), ctx);
    }

    @Override
    public void exitObject(ObjectContext ctx) {
        StringBuilder sb = new StringBuilder();
        sb.append('{');
        List<PairContext> pairs = ctx.pair();
        if (! pairs.isEmpty()) {
            Iterator<PairContext> it = pairs.iterator();
            while (it.hasNext()) {
                PairContext pair = it.next();
                ExprContext expr = pair.expr();
                sb.append('"');
                sb.append(pair.ID().getText());
                sb.append('"');
                sb.append(':');
                sb.append(getNodeData(expr).jsref);
                sb.append(',');
            }
            sb.deleteCharAt(sb.length()-1);
        }
        sb.append('}');
        NodeData nd = getNodeData(ctx);
        nd.jsref = sb.toString();
    }

    @Override
    public void exitArrayLiteral(ArrayLiteralContext ctx) {
        copyNodeData(ctx.array(), ctx);
    }

    @Override
    public void exitArray(ArrayContext ctx) {
        NodeData nd = getNodeData(ctx);
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        List<ExprContext> lst = ctx.expr();
        if (! lst.isEmpty()) {
            Iterator<ExprContext> it = lst.iterator();
            while (it.hasNext()) {
                ExprContext expr = it.next();
                sb.append(getNodeData(expr).jsref);
                sb.append(',');
            }
            sb.deleteCharAt(sb.length()-1);
        }
        sb.append(']');
        nd.jsref = sb.toString();
    }

    @Override
    public void exitExprLiteral(ExprLiteralContext ctx) {
        copyNodeData(ctx.literal(), ctx);
    }

    @Override
    public void exitExprId(ExprIdContext ctx) {
        NodeData nd = getNodeData(ctx);
        String id = ctx.identifier().getText();
        nd.jsref = getSymbolTable().jsref(id);
        
        // We copy the node data from THIS context to
        // the identifier context.
        copyNodeData(ctx, ctx.identifier());
    }

    @Override
    public void exitExprFcall(ExprFcallContext ctx) {
        copyNodeData(ctx.functionCall(), ctx);
    }

    @Override
    public void exitExprIndexing(ExprIndexingContext ctx) {
        NodeData nd = getNodeData(ctx);
        String id = ctx.identifier().getText();
        stExprIndexing.add("name", getSymbolTable().jsref(id));
        stExprIndexing.add("key", getNodeData(ctx.expr()).jsref);
        nd.jsref = stExprIndexing.render();
        stExprIndexing.remove("name");
        stExprIndexing.remove("key");
    }

    @Override
    public void exitExprPropref(ExprProprefContext ctx) {
        NodeData nd = getNodeData(ctx);
        String expr = getNodeData(ctx.expr()).jsref;
        String key = ctx.ID().getText();
        stExprPropRef.add("name", expr);
        stExprPropRef.add("key", key);
        nd.jsref = stExprPropRef.render();
        stExprPropRef.remove("name");
        stExprPropRef.remove("key");
    }

    @Override
    public void exitFunctionCall(FunctionCallContext ctx) {
        String name = ctx.functionIdentifier().getText();
        name = getSymbolTable().jsref(name);
        String params = getNodeData(ctx.functionCallParams()).jsref;
        stExprFuncCall.add("name", name);
        stExprFuncCall.add("params", params);
        String jsref = stExprFuncCall.render();
        getNodeData(ctx).jsref = jsref;
        stExprFuncCall.remove("name");
        stExprFuncCall.remove("params");
        
        if (parentIsStatement(ctx)) {
            getNodeData(ctx.getParent()).jsref = jsref;
        }
    }

    @Override
    public void exitFunctionCallParams(FunctionCallParamsContext ctx) {
        NodeData nd = getNodeData(ctx);
        ArrayList<FunctionCallParam> genParams = nd.params;
        StringBuilder sb = new StringBuilder();
        List<ExprContext> params = ctx.expr();
        
         // Generated parameters are added first.
        if (genParams != null && ! genParams.isEmpty()) {
            Iterator<FunctionCallParam> it = genParams.iterator();
            while (it.hasNext()) {
                sb.append(it.next().jsref());
                sb.append(',');
            }
        }
        if (! params.isEmpty()) {
            Iterator<ExprContext> it = params.iterator();
            while (it.hasNext()) {
                String s = getNodeData(it.next()).jsref;
                sb.append(s);
                sb.append(',');
            }
            sb.deleteCharAt(sb.length()-1);
        }
        nd.jsref = sb.toString();
    }

    @Override
    public void enterFunctionDef(FunctionDefContext ctx) {
        String fname = ctx.identifier().getText();
        Symbol s = getSymbolTable().lookup(fname);
        s.jsref = ref2function(fname);
        
        /*
         * It is possible that function definition 
         * does not define any parameters. To avoid
         * NullPointerExceptions, define an empty 
         * parameter list here. It will be overridden 
         * in 'exitFunctionDefParams'.
         */
        getNodeData(ctx).paramList = "";
        getSymbolTable().intoLocalScope(ctx);
        varRef.toShortMode();
    }

    @Override
    public void exitFunctionDef(FunctionDefContext ctx) {
        getSymbolTable().intoGlobalScope();
        varRef.toDefaultMode();
    }
    
    @Override
    public void exitFunctionDefParams(FunctionDefParamsContext ctx) {
        List<FunctionDefParamContext> params = ctx.functionDefParam();
        getNodeData(ctx).paramList = "";
        if (! params.isEmpty()) {
            Iterator<FunctionDefParamContext> it = params.iterator();
            StringBuilder sb = new StringBuilder();
            while (it.hasNext()) {
                sb.append(getNodeData(it.next()).jsref);
                sb.append(',');
            }
            // Ignore the last comma.
            String paramListStr = sb.substring(0, sb.length()-1);
            NodeData fnd = getNodeData(ctx.getParent());
            fnd.paramList = paramListStr;
            getNodeData(ctx).paramList = paramListStr;
        }
     }

    @Override
    public void enterFunctionDefParam(FunctionDefParamContext ctx) {
        String name = ctx.ID().getText();
        Symbol s = getSymbolTable().lookup(name);
        String param = ref2param(name);
        getSymbolTable().jsref(s, param);
        getNodeData(ctx).jsref = param;
    }

    @Override
    public void enterGvarDef(GvarDefContext ctx) {
        IdentifierContext id = ctx.identifier();
        String name = id.getText();
        Symbol s = getSymbolTable().lookup(name);
        String ref = ref2gvar(name);
        getNodeData(id).jsref = ref;
        getSymbolTable().jsref(s, ref);
    }

    @Override
    public void enterValDef(ValDefContext ctx) {
        IdentifierContext id = ctx.identifier();
        String name = id.getText();
        Symbol s = getSymbolTable().lookup(name);
        String ref = ref2val(name);
        getNodeData(id).jsref = ref;
        getSymbolTable().jsref(s, ref);
    }

    @Override
    public void enterFvarDef(FvarDefContext ctx) {
        IdentifierContext id = ctx.identifier();
        String name = id.getText();
        Symbol s = getSymbolTable().lookup(name);
        String ref = ref2fvar(name);
        getNodeData(id).jsref = ref;
        getSymbolTable().jsref(s, ref);
    }

    @Override
    public void enterVarDef(VarDefContext ctx) {
        IdentifierContext id = ctx.identifier();
        String name = id.getText();
        Symbol s = getSymbolTable().lookup(name);
        String ref = ref2var(name);
        getNodeData(id).jsref = ref;
        getSymbolTable().jsref(s, ref);
    }

    @Override
    public void exitTransitionCondFcall(TransitionCondFcallContext ctx) {
        String jsref = getNodeData(ctx.functionCall()).jsref;
        getNodeData(ctx).jsref = jsref;
    }

    @Override
    public void exitTransitionCondVar(TransitionCondVarContext ctx) {
        String name = ctx.identifier().getText();
        String jsref = getSymbolTable().jsref(name);
        getNodeData(ctx).jsref = jsref;
    }

    private String ref2gvar(String name) {
        return jsref(stgvar, name);
    }

    private String ref2val(String name) {
        return jsref(stval, name);
    }

    private String ref2function(String name) {
        return jsref(stfn, name);
    }

    private String ref2fvar(String name) {
        return jsref(stfvar, name);
    }

    private String ref2var(String name) {
        return varRef.ref(name);
    }

    private String ref2param(String name) {
        return jsref(stparam, name);
    }

    private String jsref(ST st, String name) {
        st.add("name", name);
        String output = st.render();
        st.remove("name");
        return output;
    }
    
    private boolean parentIsStatement(ParserRuleContext ctx) {
        return (ctx.getParent() instanceof StmtContext);
    }
    
    private STGroup tmpl;
    private ST stgvar;
    private ST stval;
    private ST stfvar;
    private ST stfn;
    private ST stparam;
    private ST stExprIndexing;
    private ST stExprPropRef;
    private ST stExprFuncCall;
    private VarRef varRef;
    
    /*
     * References to 'var' variables are different 
     * depending on the context (function or a
     * sub-phase). This class gives a pleasant 
     * interface to work with, and frees the rest
     * of the code from a bunch of if-else 
     * statements.
     */
    private final class VarRef {
        
        public VarRef(ST stLong, ST stShort) {
            super();
            this.stLong = stLong;
            this.stShort = stShort;
            this.toDefaultMode();
        }
        
        public void toDefaultMode() {
            toShortMode();     // Short mode is the default.
        }
        
        public void toLongMode() {
            this.st = this.stLong;
        }
        
        public void toShortMode() {
            this.st = this.stShort;
        }
        
        public String ref(String name) {
            st.add("name", name);
            String ref = st.render();
            st.remove("name");
            return ref;
        }
        
        private ST st;
        private ST stLong;
        private ST stShort;
    }
    

}
