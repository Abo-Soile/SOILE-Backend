package fi.abo.kogni.soile2.elang;

import fi.abo.kogni.soile2.elang.ElangParser.InteractionPhaseContext;
import fi.abo.kogni.soile2.elang.ElangParser.PhaseFunctionDefContext;

public final class InteractionPhaseDef extends PhaseDef {
    
    public InteractionPhaseDef(String name, InteractionPhaseContext ctx) {
        super(ctx);
        setName(name);
        this.defs = new PhaseFunctionDefContext[LENGTH];
        this.flags = new boolean[LENGTH];
        flags[IDX_ENTERPHASE] = false;
        flags[IDX_BEFOREITERATION] = false;
        flags[IDX_ITERATION] = true;    // Always defined.
        flags[IDX_AFTERITERATION] = false;
        flags[IDX_LEAVEPHASE] = false;
    }
    
    @Override
    public boolean isInteractionPhase() {
        return true;
    }

    public void define(String name, PhaseFunctionDefContext f) {
        if (name.compareTo(ENTERPHASE) == 0) {
            enterphase(f);
            return;
        }
        if (name.compareTo(BEFOREITERATION) == 0) {
            beforeiteration(f);
            return;
        }
        if (name.compareTo(ITERATION) == 0) {
            iteration(f);
            return;
        }
        if (name.compareTo(AFTERITERATION) == 0) {
            afteriteration(f);
            return;
        }
        if (name.compareTo(LEAVEPHASE) == 0) {
            leavephase(f);
            return;
        }
    }
    
    public void enterphase(PhaseFunctionDefContext f) {
        defs[IDX_ENTERPHASE] = f;
        flags[IDX_ENTERPHASE] = true;
    }
    
    public boolean enterphaseDefined() {
        return flags[IDX_ENTERPHASE];
    }
    
    public PhaseFunctionDefContext enterphase() {
        return defs[IDX_ENTERPHASE];
    }
    
    public void beforeiteration(PhaseFunctionDefContext f) {
        defs[IDX_BEFOREITERATION] = f;
        flags[IDX_BEFOREITERATION] = true;
    }
    
    public PhaseFunctionDefContext beforeiteration() {
        return defs[IDX_BEFOREITERATION];
    }
    
    public boolean beforeiterationDefined() {
        return flags[IDX_BEFOREITERATION];
    }
    
    public void iteration(PhaseFunctionDefContext f) {
        defs[IDX_ITERATION] = f;
        flags[IDX_ITERATION] = true;
    }
    
    public PhaseFunctionDefContext iteration() {
        return defs[IDX_ITERATION];
    }
    
    public void afteriteration(PhaseFunctionDefContext f) {
        defs[IDX_AFTERITERATION] = f;
        flags[IDX_AFTERITERATION] = true;
    }
    
    public PhaseFunctionDefContext afteriteration() {
        return defs[IDX_AFTERITERATION];
    }
    
    public boolean afteriterationDefined() {
        return flags[IDX_AFTERITERATION];
    }
    
    public void leavephase(PhaseFunctionDefContext f) {
        defs[IDX_LEAVEPHASE] = f;
        flags[IDX_LEAVEPHASE] = true;
    }
    
    public PhaseFunctionDefContext leavephase() {
        return defs[IDX_LEAVEPHASE];
    }
    
    public boolean leavephaseDefined() {
        return flags[IDX_LEAVEPHASE];
    }
    
    public static final String ENTERPHASE = "enterphase";
    public static final String BEFOREITERATION = "beforeiteration";
    public static final String ITERATION = "iteration";
    public static final String AFTERITERATION = "afteriteration";
    public static final String LEAVEPHASE = "leavephase";

    private static final int IDX_ENTERPHASE = 0;
    private static final int IDX_BEFOREITERATION = 1;
    private static final int IDX_ITERATION = 2;
    private static final int IDX_AFTERITERATION = 3;
    private static final int IDX_LEAVEPHASE = 4;
    private static final int LENGTH = 5;
    
    private PhaseFunctionDefContext[] defs;
    private boolean[] flags;
}
