package fi.abo.kogni.soile2.elang;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.IdentityHashMap;

import org.antlr.v4.runtime.ParserRuleContext;

import fi.abo.kogni.soile2.elang.ElangParser.BreakStatementContext;
import fi.abo.kogni.soile2.elang.ElangParser.ContinueStatementContext;
import fi.abo.kogni.soile2.elang.ElangParser.FileContext;
import fi.abo.kogni.soile2.elang.ElangParser.FinalTransitionContext;
import fi.abo.kogni.soile2.elang.ElangParser.FunctionCallContext;
import fi.abo.kogni.soile2.elang.ElangParser.FunctionDefContext;
import fi.abo.kogni.soile2.elang.ElangParser.InformationPhaseContext;
import fi.abo.kogni.soile2.elang.ElangParser.InteractionPhaseContext;
import fi.abo.kogni.soile2.elang.ElangParser.IntermezzoPhaseContext;
import fi.abo.kogni.soile2.elang.ElangParser.PhaseFunctionDefContext;
import fi.abo.kogni.soile2.elang.ElangParser.ReturnStatementContext;
import fi.abo.kogni.soile2.elang.ElangParser.StartTransitionContext;
import fi.abo.kogni.soile2.elang.ElangParser.TransitionSrcIdContext;
import fi.abo.kogni.soile2.elang.ElangParser.TransitionToContext;
import fi.abo.kogni.soile2.elang.ElangParser.WhileStatementContext;

/*
 * The purpose of this translator pass is to verify
 * that the program code conforms to certain rules.
 *  o 'wait' is called only inside sub-phases.
 *  o 'continue' and 'break' require an enclosing
 *    while-statement.
 *  o 'return' cannot be called in sub-phases.
 *  o No phase name is defined more than once.
 *  o 'interaction-phase' must define an 'iteration'
 *    sub-phase.
 *  o 'interaction-phase' contains only valid sub-phase
 *    names.
 *  o Every phase name is used in at least one transition
 *    rule definition.
 *  o No transition rule contains undefined phase names.
 *  o Start phase does not appear as target phase in 
 *    any transition rule definition.
 *  o Final phase does not appear as source phase in 
 *    any transition rule definition.
 */
public class RuleConformancePass extends ProcessorListenerPass {

    public RuleConformancePass(IdentityHashMap<ParserRuleContext, NodeData> nodeData) {
        super(nodeData);
        this.subphases = new HashSet<>();
        this.phaseNames = new HashSet<>();
        this.interactionPhaseNames = new HashSet<>();
        this.transitionSourceNames = new HashSet<>();
        this.transitionTargetNames = new HashSet<>();
        this.startPhase = "";
        this.endPhase = "";
        this.inFunctionDef = false;
        this.waitAllowed = false;
        this.timeoutAllowed = false;
        this.whileStmts = 0;
    }

    @Override
    public void exitFile(FileContext ctx) {
        HashSet<String> transitionNames = new HashSet<>();
        transitionNames.addAll(transitionSourceNames);
        transitionNames.addAll(transitionTargetNames);
        transitionNames.add(startPhase);
        transitionNames.add(endPhase);
        
        if (interactionPhaseNames.contains(startPhase)) {
            String m = String.format("Start phase '%s' cannot be an interaction phase.", endPhase);
            addError(m);
            return;
        }
        if (interactionPhaseNames.contains(endPhase)) {
            String m = String.format("Final phase '%s' cannot be an interaction phase.", endPhase);
            addError(m);
            return;
        }
        
        if (transitionSourceNames.contains(endPhase)) {
            String m = "Final phase appears as a source phase in a transition rule definition.";
            addError(m);
            return;
        }
        if (transitionTargetNames.contains(startPhase)) {
            String m = "Start phase appears as a target phase in a transition rule definition.";
            addError(m);
            return;
        }
        if (! phaseNames.containsAll(transitionNames)) {
            String m = "Some transition rules contain undefined phase names.";
            addError(m);
            return;
        }
        if (! transitionNames.containsAll(phaseNames)) {
            String m = "Some phase names do not appear in transition rules.";
            addError(m);
            return;
        }
    }

    @Override
    public void enterInteractionPhase(InteractionPhaseContext ctx) {
        waitAllowed = true;
        subphases.clear();
        String name = ctx.ID().getText();
        interactionPhaseNames.add(name);
        interactionPhaseName = name;
        definePhaseName(name);
    }

    @Override
    public void enterInformationPhase(InformationPhaseContext ctx) {
        timeoutAllowed = true;
        definePhaseName(ctx.ID().getText());
    }

    @Override
    public void enterIntermezzoPhase(IntermezzoPhaseContext ctx) {
        waitAllowed = true;
        definePhaseName(ctx.ID().getText());
    }

    @Override
    public void exitInteractionPhase(InteractionPhaseContext ctx) {
        waitAllowed = false;
        if (! subphases.contains("iteration")) {
            addError("No 'iteration' defined for " + interactionPhaseName + ".");
            return;
        }
    }

    @Override
    public void exitInformationPhase(InformationPhaseContext ctx) {
        timeoutAllowed = false;
    }

    @Override
    public void exitIntermezzoPhase(IntermezzoPhaseContext ctx) {
        waitAllowed = false;
    }

    @Override
    public void enterPhaseFunctionDef(PhaseFunctionDefContext ctx) {
        String name = ctx.ID().getText();
        if (! allowedSubphaseNames.contains(name)) {
            addError(String.format("Unknow subphase name '%s' in '%s'.", name, interactionPhaseName));
            return;
        }
        subphases.add(name);
    }

    @Override
    public void enterTransitionSrcId(TransitionSrcIdContext ctx) {
        transitionSourceNames.add(ctx.ID().getText());
    }

    @Override
    public void enterTransitionTo(TransitionToContext ctx) {
        transitionTargetNames.add(ctx.ID().getText());
    }

    @Override
    public void enterFinalTransition(FinalTransitionContext ctx) {
        endPhase = ctx.identifier().getText();
    }

    @Override
    public void enterStartTransition(StartTransitionContext ctx) {
        startPhase = ctx.identifier().getText();
    }

    @Override
    public void enterFunctionDef(FunctionDefContext ctx) {
        inFunctionDef = true;
    }

    @Override
    public void exitFunctionDef(FunctionDefContext ctx) {
        inFunctionDef = false;
    }

    @Override
    public void enterFunctionCall(FunctionCallContext ctx) {
        String name = ctx.functionIdentifier().getText();
        if (name.compareTo("wait") == 0 && waitAllowed == false) {
            addError("Function 'wait' cannot be called outside a subphase.");
            return;
        }
        else if (name.compareTo("timeout") == 0 && timeoutAllowed == false) {
            addError("Function 'timeout' can be called only inside a intermezzo or information phase.");
            return;
        }
    }

    @Override
    public void enterWhileStatement(WhileStatementContext ctx) {
        ++whileStmts;
    }

    @Override
    public void exitWhileStatement(WhileStatementContext ctx) {
        --whileStmts;
    }

    @Override
    public void enterContinueStatement(ContinueStatementContext ctx) {
        if (! (whileStmts > 0)) {
            addError("No surrounding 'while' statement for a 'continue' statement.");
        }
    }

    @Override
    public void enterBreakStatement(BreakStatementContext ctx) {
        if (! (whileStmts > 0)) {
            addError("No surrounding 'while' statement for a 'break' statement.");
        }
    }

    @Override
    public void enterReturnStatement(ReturnStatementContext ctx) {
        if (! inFunctionDef) {
            addError("'return' statement must be used only inside a function definition.");
        }
    }

    private boolean isSpecialFunc(String name) {
        if (name.compareTo("wait") == 0
                || name.compareTo("timeout") == 0) {
            return true;
        }
        return false;
    }
    
    private void definePhaseName(String name) {
        if (phaseNames.contains(name)) {
            String m = String.format("Phase name '%s' defined multiple times.", name);
            addError(m);
            return;
        }
        phaseNames.add(name);
    }
    
    private static final HashSet<String> allowedSubphaseNames =
            new HashSet<>();
    
    private HashSet<String> subphases;
    private HashSet<String> phaseNames;
    private HashSet<String> interactionPhaseNames;
    private HashSet<String> transitionSourceNames;
    private HashSet<String> transitionTargetNames;
    private String startPhase;
    private String endPhase;
    private String interactionPhaseName;
    private boolean inFunctionDef;
    private boolean waitAllowed;
    private boolean timeoutAllowed;
    private int whileStmts;
    
    static {
        allowedSubphaseNames.add(InteractionPhaseDef.ENTERPHASE);
        allowedSubphaseNames.add(InteractionPhaseDef.BEFOREITERATION);
        allowedSubphaseNames.add(InteractionPhaseDef.ITERATION);
        allowedSubphaseNames.add(InteractionPhaseDef.AFTERITERATION);
        allowedSubphaseNames.add(InteractionPhaseDef.LEAVEPHASE);
    }
    
}
