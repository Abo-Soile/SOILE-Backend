package fi.abo.kogni.soile2.elang;

import java.util.IdentityHashMap;
import java.util.List;

import org.antlr.v4.runtime.ParserRuleContext;

import fi.abo.kogni.soile2.elang.ElangParser.FinalTransitionContext;
import fi.abo.kogni.soile2.elang.ElangParser.InformationPhaseContext;
import fi.abo.kogni.soile2.elang.ElangParser.InteractionPhaseContext;
import fi.abo.kogni.soile2.elang.ElangParser.IntermezzoPhaseContext;
import fi.abo.kogni.soile2.elang.ElangParser.PhaseFunctionDefContext;
import fi.abo.kogni.soile2.elang.ElangParser.StartTransitionContext;
import fi.abo.kogni.soile2.elang.ElangParser.TransitionContext;
import fi.abo.kogni.soile2.elang.ElangParser.TransitionSrcIdContext;
import fi.abo.kogni.soile2.elang.Transition.Cond;
import fi.abo.kogni.soile2.elang.Transition.Phase;
import fi.abo.kogni.soile2.elang.Transition.Rule;

public class PhaseTransitionPass extends ProcessorListenerPass {
    
    public PhaseTransitionPass(
            IdentityHashMap<ParserRuleContext, NodeData> nodeData) {
        super(nodeData);
    }
    
    @Override
    public void enterInteractionPhase(InteractionPhaseContext ctx) {
        String name = ctx.ID().getText();
        InteractionPhaseDef def = new InteractionPhaseDef(name, ctx);
        phaseData.addDef(name, def);
        transitionTable.addPhase(name);
        List<PhaseFunctionDefContext> defs = ctx.phaseFunctionDef();
        for (int i = 0; i < defs.size(); ++i) {
            PhaseFunctionDefContext f = defs.get(i);
            String subphaseName = f.ID().getText();
            def.define(subphaseName, f);
        }
    }

    @Override
    public void enterInformationPhase(InformationPhaseContext ctx) {
        String name = ctx.ID().getText();
        InformationPhaseDef def = new InformationPhaseDef(name, ctx);
        def.setBody(ctx.block());
        phaseData.addDef(name, def);
    }

    @Override
    public void enterIntermezzoPhase(IntermezzoPhaseContext ctx) {
        String name = ctx.ID().getText();
        IntermezzoPhaseDef def = new IntermezzoPhaseDef(name, ctx);
        def.setBody(ctx.block());
        phaseData.addDef(name, def);
    }

    @Override
    public void enterFinalTransition(FinalTransitionContext ctx) {
        String name = ctx.identifier().getText();
        transitionTable.setFinalPhase(name);
    }

    @Override
    public void enterStartTransition(StartTransitionContext ctx) {
        String name = ctx.identifier().getText();
        transitionTable.setStartPhase(name);
        srcPhase = name;
    }

    @Override
    public void exitTransition(TransitionContext ctx) {
        Phase src = new Phase(srcPhase);
        Phase to = new Phase(ctx.transitionTo().ID().getText());
        Rule rule = new Rule(src, to);
        if (ctx.transitionCond() != null) {
            Cond cond = new Cond();
            cond.context(ctx.transitionCond());
            rule.setCond(cond);
        }
        transitionTable.addRule(srcPhase, rule);
    }

    @Override
    public void enterTransitionSrcId(TransitionSrcIdContext ctx) {
        String name = ctx.ID().getText();
        transitionTable.addPhase(name);
        srcPhase = name;
    }

    public void usePhaseData(PhaseData data) {
        phaseData = data;
    }
    
    public void useTransitionTable(TransitionTable table) {
        transitionTable = table;
    }
    
    private PhaseData phaseData;
    private TransitionTable transitionTable;
    private String srcPhase;
}
