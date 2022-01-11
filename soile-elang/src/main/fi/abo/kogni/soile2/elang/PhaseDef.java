package fi.abo.kogni.soile2.elang;

import org.antlr.v4.runtime.ParserRuleContext;

public abstract class PhaseDef {
    
    public PhaseDef(ParserRuleContext ctx) {
        this.ctx = ctx;
    }
    
    public boolean isInteractionPhase() {
        return false;
    }
    
    public boolean isInformationPhase() {
        return false;
    }
    
    public boolean isIntermezzoPhase() {
        return false;
    }
    
    public ParserRuleContext getContext() {
        return this.ctx;
    }
    
    public String getName() {
        return name;
    }

    protected void setName(String name) {
        this.name = name;
    }

    private ParserRuleContext ctx;
    private String name;
    
}
