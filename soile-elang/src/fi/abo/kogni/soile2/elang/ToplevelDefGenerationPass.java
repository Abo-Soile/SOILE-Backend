package fi.abo.kogni.soile2.elang;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.stringtemplate.v4.ST;

import fi.abo.kogni.soile2.elang.ElangParser.FunctionBodyContext;
import fi.abo.kogni.soile2.elang.ElangParser.FunctionDefContext;
import fi.abo.kogni.soile2.elang.ElangParser.FvarDefContext;
import fi.abo.kogni.soile2.elang.ElangParser.FvarDefsContext;
import fi.abo.kogni.soile2.elang.ElangParser.GvarDefContext;
import fi.abo.kogni.soile2.elang.ElangParser.StmtContext;
import fi.abo.kogni.soile2.elang.ElangParser.ValDefContext;
import fi.abo.kogni.soile2.elang.ElangParser.VarDefContext;
import fi.abo.kogni.soile2.elang.ElangParser.VarDefsContext;

public class ToplevelDefGenerationPass extends ProcessorVisitorPass<Integer> {
    
    @Override
    public Integer visitValDef(ValDefContext ctx) {
        ST st = template("defval");
        String name = ctx.identifier().getText();
        String value = nodeData(ctx.expr()).jsref;
        st.add("name", name);
        st.add("value", value);
        codeOutput().addToplevelDef(st);
        return NOTUSED;
    }

    @Override
    public Integer visitGvarDef(GvarDefContext ctx) {
        ST st = template("defgvar");
        String name = ctx.identifier().getText();
        String value = nodeData(ctx.expr()).jsref;
        st.add("name", name);
        st.add("value", value);
        codeOutput().addToplevelDef(st);
        return NOTUSED;
    }

    @Override
    public Integer visitFunctionDef(FunctionDefContext ctx) {
        symbolTable().intoLocalScope(ctx);
        ST fdefTmpl = template("fdefWithoutFvars");
        List<FvarDefContext> fvarDefs = getFvars(ctx);
        if (! fvarDefs.isEmpty()) {
            fdefTmpl = template("fdefWithFvars");
            Iterator<FvarDefContext> it = fvarDefs.iterator();
            while (it.hasNext()) {
                FvarDefContext fvarDef = it.next();
                String name = fvarDef.identifier().getText();
                String value = nodeData(fvarDef.expr()).jsref;
                fdefTmpl.addAggr("fvarDefs.{name, value}", name, value);
            }
        }
        List<VarDefContext> varDefs = getVars(ctx);
        if (! varDefs.isEmpty()) {
            Iterator<VarDefContext> it = varDefs.iterator();
            while (it.hasNext()) {
                VarDefContext varDef = it.next();
                String name = varDef.identifier().getText();
                String value = nodeData(varDef.expr()).jsref;
                fdefTmpl.addAggr("varDefs.{name, value}", name, value);
            }
        }
        String funcName = ctx.identifier().getText();
        String paramList = nodeData(ctx).paramList;
        fdefTmpl.add("name", funcName);
        fdefTmpl.add("params", paramList);
        List<StmtContext> stmts = ctx.functionBody().block().stmt();
        Iterator<StmtContext> it = stmts.iterator();
        while (it.hasNext()) {
            StmtContext stmt = it.next();
            String jsStmt = nodeData(stmt).jsstmt;
            fdefTmpl.addAggr("stmts.{stmt}", jsStmt);
        }
        symbolTable().intoGlobalScope();
        codeOutput().addToplevelDef(fdefTmpl);
        return NOTUSED;
    }

    private List<VarDefContext> getVars(FunctionDefContext ctx) {
        FunctionBodyContext body = ctx.functionBody();
        VarDefsContext vars = body.varDefs();
        if (vars == null) {
            return new ArrayList<>();
        }
        return vars.varDef();
    }

    private List<FvarDefContext> getFvars(FunctionDefContext ctx) {
        FunctionBodyContext body = ctx.functionBody();
        FvarDefsContext fvars = body.fvarDefs();
        if (fvars == null) {
            return new ArrayList<>();
        }
        return fvars.fvarDef();
    }

    private static final int NOTUSED = 0;
}
