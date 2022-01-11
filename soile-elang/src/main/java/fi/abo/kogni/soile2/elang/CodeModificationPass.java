package fi.abo.kogni.soile2.elang;

import java.util.ArrayList;

import fi.abo.kogni.soile2.elang.ElangParser.FunctionCallContext;
import fi.abo.kogni.soile2.elang.ElangParser.FunctionCallParamsContext;

/*
 * The purpose of this processing pass is
 *  o to add extra parameters for some calls 
 *    of built-in functions
 */

public class CodeModificationPass extends ProcessorListenerPass {

    public CodeModificationPass() {
        super();
    }

    @Override
    public void exitFunctionCall(FunctionCallContext ctx) {
        String name = ctx.functionIdentifier().getText();
        ArrayList<FunctionCallParam> genParams = new ArrayList<FunctionCallParam>();
        getNodeData(ctx).params = genParams;
        FunctionCallParamsContext paramsCtx = ctx.functionCallParams();
        if (paramsCtx != null) {
            getNodeData(paramsCtx).params = genParams;
        }
    }

}
