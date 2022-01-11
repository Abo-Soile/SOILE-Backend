package fi.abo.kogni.soile2.elang;

import fi.abo.kogni.soile2.elang.ElangParser.BlockContext;
import fi.abo.kogni.soile2.elang.ElangParser.InformationPhaseContext;

public class InformationPhaseDef extends PhaseDef {
    public InformationPhaseDef(String name, InformationPhaseContext ctx) {
        super(ctx);
        setName(name);
        this.phaseFunctionBody = null;
    }

    @Override
    public boolean isInformationPhase() {
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
