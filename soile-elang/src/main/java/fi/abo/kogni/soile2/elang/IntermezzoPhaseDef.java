package fi.abo.kogni.soile2.elang;

import fi.abo.kogni.soile2.elang.ElangParser.BlockContext;
import fi.abo.kogni.soile2.elang.ElangParser.IntermezzoPhaseContext;

public class IntermezzoPhaseDef extends PhaseDef {
    public IntermezzoPhaseDef(String name, IntermezzoPhaseContext ctx) {
        super(ctx);
        setName(name);
        this.phaseFunctionBody = null;
    }

    @Override
    public boolean isIntermezzoPhase() {
        return true;
    }

    public void setBody(BlockContext body) {
        phaseFunctionBody = body;
    }
    
    public BlockContext getBody() {
        return phaseFunctionBody;
    }
    
    private BlockContext phaseFunctionBody;
}
