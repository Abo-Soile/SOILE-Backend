package fi.abo.kogni.soile2.elang;

import fi.abo.kogni.soile2.elang.ElangParser.AssignStatementContext;
import fi.abo.kogni.soile2.elang.ElangParser.BlockContext;
import fi.abo.kogni.soile2.elang.ElangParser.BreakStatementContext;
import fi.abo.kogni.soile2.elang.ElangParser.ContinueStatementContext;
import fi.abo.kogni.soile2.elang.ElangParser.ExprContext;
import fi.abo.kogni.soile2.elang.ElangParser.FileContext;
import fi.abo.kogni.soile2.elang.ElangParser.FunctionCallContext;
import fi.abo.kogni.soile2.elang.ElangParser.FunctionCallParamsContext;
import fi.abo.kogni.soile2.elang.ElangParser.FunctionCallStatementContext;
import fi.abo.kogni.soile2.elang.ElangParser.IfElseStatementContext;
import fi.abo.kogni.soile2.elang.ElangParser.IfStatementContext;
import fi.abo.kogni.soile2.elang.ElangParser.PhaseFunctionBodyContext;
import fi.abo.kogni.soile2.elang.ElangParser.PhaseFunctionDefContext;
import fi.abo.kogni.soile2.elang.ElangParser.StmtAssignContext;
import fi.abo.kogni.soile2.elang.ElangParser.StmtContext;
import fi.abo.kogni.soile2.elang.ElangParser.StmtIfContext;
import fi.abo.kogni.soile2.elang.ElangParser.StmtIfElseContext;
import fi.abo.kogni.soile2.elang.ElangParser.StmtWhileContext;
import fi.abo.kogni.soile2.elang.ElangParser.TransitionCondContext;
import fi.abo.kogni.soile2.elang.ElangParser.VarDefContext;
import fi.abo.kogni.soile2.elang.ElangParser.VarDefsContext;
import fi.abo.kogni.soile2.elang.ElangParser.WhileStatementContext;
import fi.abo.kogni.soile2.elang.ProgramInstruction.Assign;
import fi.abo.kogni.soile2.elang.ProgramInstruction.Branching;
import fi.abo.kogni.soile2.elang.ProgramInstruction.Def;
import fi.abo.kogni.soile2.elang.ProgramInstruction.Eoc;
import fi.abo.kogni.soile2.elang.ProgramInstruction.Fcall;
import fi.abo.kogni.soile2.elang.ProgramInstruction.Goto;
import fi.abo.kogni.soile2.elang.ProgramInstruction.If;
import fi.abo.kogni.soile2.elang.ProgramInstruction.Opcode;
import fi.abo.kogni.soile2.elang.ProgramInstruction.Suspend;
import fi.abo.kogni.soile2.elang.ProgramInstruction.Undef;
import fi.abo.kogni.soile2.elang.ProgramInstruction.Wait;
import fi.abo.kogni.soile2.elang.ProgramInstruction.While;
import fi.abo.kogni.soile2.elang.SymbolTable.Symbol;
import fi.abo.kogni.soile2.elang.SymbolTable.SymbolType;
import fi.abo.kogni.soile2.elang.Transition.Cond;
import fi.abo.kogni.soile2.elang.Transition.Phase;
import fi.abo.kogni.soile2.elang.Transition.Rule;
import it.unimi.dsi.fastutil.ints.Int2ObjectAVLTreeMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.objects.Object2IntAVLTreeMap;
import it.unimi.dsi.fastutil.objects.Object2IntMap;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.stringtemplate.v4.ST;

public class InstructionArrayGenerationPass extends ProcessorVisitorPass<Integer> {

    public InstructionArrayGenerationPass() {
        super();
        this.nextIndexValue = 0;
        this.nextTmpIndexValue = Integer.MIN_VALUE;
        this.startPiIndex = 0;
        this.pfVardefs = new ArrayList<>();
        this.piArray = new ProgramInstructionArray();
        this.breakStmtStack = new ArrayDeque<>();
        this.whileStmtStack = new ArrayDeque<>();
        this.phaseData = new PhaseData();
        this.transitionTable = new TransitionTable();
        this.pendingIfs = new ArrayDeque<>();
        this.pendingGotos = new ArrayDeque<>();
        this.tmpidx2name = new Int2ObjectAVLTreeMap<String>();
        this.name2idx = new Object2IntAVLTreeMap<String>();

        this.inInterationBlock = false;

        this.jumpOffset = 0;
    }
    
    public void usePhaseData(PhaseData data) {
        this.phaseData = data;
    }
    
    public void useTransitionTable(TransitionTable table) {
        this.transitionTable = table;
    }
    
    @Override
    public Integer visitFile(FileContext ctx) {
        Iterator<PhaseDef> it = phaseData.iterator();
        while (it.hasNext()) {
            doPhaseDef(it.next());
        }
        fixPiIndices();
        ProgramInstruction[] arr = piArray.toArray();
        for (ProgramInstruction pi : arr) {
            String cmd = pi.getCommand();
            codeOutput().addProgramInstruction(cmd);
        }
        codeOutput().setStartIndex(startPiIndex);
        return 0; /* Return value makes no difference. */
    }

    private void doPhaseDef(PhaseDef def) {
        if (def instanceof InteractionPhaseDef) {
            InteractionPhaseDef phase = (InteractionPhaseDef) def;
            doInteractionPhase(phase);
        }
        else if (def instanceof IntermezzoPhaseDef) {
            IntermezzoPhaseDef phase = (IntermezzoPhaseDef) def;
            doIntermezzoPhase(phase);
        }
        else if (def instanceof InformationPhaseDef) {
            InformationPhaseDef phase = (InformationPhaseDef) def;
            doInformationPhase(phase);
        }
    }
    
    private void doInformationPhase(InformationPhaseDef phase) {
        String phaseName = phase.getName();
        int phaseIndex = doBlock(phase.getBody());
        Suspend suspend = new ProgramInstruction.Suspend(nextIndex());
        piSuspend(suspend);
        piArray.add(suspend);
        if (transitionTable.isStartPhase(phaseName)) {
            startPiIndex = phaseIndex;
        }
        name2idx.put(phaseName, phaseIndex);
        if (transitionTable.isFinalPhase(phaseName)) {
            eoc();
        }
        else {
            doTransitionRules(phaseName);
        }
    }

    private void doIntermezzoPhase(IntermezzoPhaseDef phase) {
        String phaseName = phase.getName();
        int phaseIndex = doBlock(phase.getBody());
        name2idx.put(phaseName, phaseIndex);
        if (transitionTable.isStartPhase(phaseName)) {
            startPiIndex = phaseIndex;
        }
        if (transitionTable.isFinalPhase(phaseName)) {
            eoc();
        }
        else {
            doTransitionRules(phaseName);
        }
    }

    private void doInteractionPhase(InteractionPhaseDef phase) {
        int iterationIndex = -1;
        String phaseName = phase.getName();
        
        pfVardefs.clear();
        ProgramInstructionIndex defIndex = nextIndex();
        Def def = new ProgramInstruction.Def(defIndex);
        int phaseIndex = defIndex.getValue();

        if (phase.enterphaseDefined()) {
            doPhaseFunctionDef(phase.enterphase());
        }

        if (phase.beforeiterationDefined()) {
            iterationIndex = doPhaseFunctionDef(phase.beforeiteration());
        }
        
        // 'iteration' subphase is always present.
        {
            this.inInterationBlock = true;
            int idx = doPhaseFunctionDef(phase.iteration());
            if (iterationIndex < 0) {
                iterationIndex = idx;
            }
        }
        
        name2idx.put(phaseName, phaseIndex);

        if (phase.afteriterationDefined()) {
            doPhaseFunctionDef(phase.afteriteration());
        }
        
        /*
         * Add "machine code" for testing whether 
         * to go back to "iteration" sub-phase, or to
         * move on to "leavephase" sub-phase. The
         * deciding factor is whether we have more
         * stimuli.
         */
        moreStimuliIf(iterationIndex);

        if (phase.leavephaseDefined()) {
            doPhaseFunctionDef(phase.leavephase());
        }

        if (! pfVardefs.isEmpty()) {
            piDef(def, pfVardefs);
            ProgramInstruction.Undef undef = new ProgramInstruction.Undef(nextIndex());
            piArray.add(undef);
            piUndef(undef);
        }

        /*
            This makes sure that the number of instructions is the same even
            if there aren't any variable definitions in an iteration phase
         */

        else {
            piDef(def, pfVardefs);
            ProgramInstruction.Undef undef = new ProgramInstruction.Undef(nextIndex());
            piArray.add(undef);
            piUndef(undef);
            jumpOffset -= 0;
        }

        piArray.add(def);
        
        doTransitionRules(phaseName);
    }

    private int doPhaseFunctionDef(PhaseFunctionDefContext ctx) {
        symbolTable().intoLocalScope(ctx);
        PhaseFunctionBodyContext body = ctx.phaseFunctionBody();
        VarDefsContext varDefs = body.varDefs();
        if (varDefs != null) {
            pfVardefs.addAll(varDefs.varDef());
        }
        BlockContext block = body.block();
        int blockIndex = doBlock(block);
        symbolTable().intoGlobalScope();
        return blockIndex;
    }
    

    private int doBlock(BlockContext block) {
        int blockIndex = Integer.MAX_VALUE;
        List<StmtContext> stmts = block.stmt();
        Iterator<StmtContext> it = stmts.iterator();
        if(inInterationBlock){
            for(VarDefContext def : pfVardefs) {
                ProgramInstructionIndex index = nextIndex();
                Assign assign = new ProgramInstruction.Assign(index);

                String name = def.identifier().getText();
                assign.setName(name);
                assign.setHost(template("abspathVars").render());
                assign.setValue(nodeData(def.expr()).jsref);

                piArray.add(assign);
                piAssign(assign);

                int idx = index.getValue();
                blockIndex = Math.min(blockIndex, idx);
            }
            inInterationBlock = false;
        }

        while (it.hasNext()) {
            StmtContext stmt = it.next();

            if (stmt instanceof BreakStatementContext) {
                int idx = doStmtBreak((BreakStatementContext) stmt);
                blockIndex = Math.min(blockIndex, idx);
            }
            else if (stmt instanceof ContinueStatementContext) {
                int idx = doContinueStatement((ContinueStatementContext) stmt);
                blockIndex = Math.min(blockIndex, idx);
            }
            else if (stmt instanceof AssignStatementContext) {
                int idx = doAssignStatement((AssignStatementContext) stmt);
                blockIndex = Math.min(blockIndex, idx);
            }
            else if (stmt instanceof FunctionCallStatementContext) {
                int idx = doFunctionCallStatement((FunctionCallStatementContext) stmt);
                blockIndex = Math.min(blockIndex, idx);
            }
            else if (stmt instanceof WhileStatementContext) {
                int idx = doWhileStatement((WhileStatementContext) stmt);
                blockIndex = Math.min(blockIndex, idx);
            }
            else if (stmt instanceof IfStatementContext) {
                int idx = doIfStatement((IfStatementContext) stmt);
                blockIndex = Math.min(blockIndex, idx);
            }
            else if (stmt instanceof IfElseStatementContext) {
                int idx = doIfElseStatement((IfElseStatementContext) stmt);
                blockIndex = Math.min(blockIndex, idx);
            }
        }
        /*System.out.println(this.jumpOffset);
        return blockIndex + this.jumpOffset;*/

        return blockIndex;
    }

    private int doStmtBreak(BreakStatementContext ctx) {
        ProgramInstructionIndex gotoIndex = nextIndex();
        Goto instrGoto = new ProgramInstruction.Goto(gotoIndex);
        breakStmtStack.push(instrGoto);
        return gotoIndex.getValue();
    }
    
    private int doContinueStatement(ContinueStatementContext ctx) {
        ProgramInstructionIndex gotoIndex = nextIndex();
        Goto instrGoto = new ProgramInstruction.Goto(gotoIndex);
        While instrWhile = whileStmtStack.getFirst();
        instrGoto.setJmp(instrWhile.getIndexValue());
        piArray.add(instrGoto);
        piGoto(instrGoto);
        return gotoIndex.getValue();
    }
    
    private int doAssignStatement(AssignStatementContext ctx) {
        StmtAssignContext sactx = ctx.stmtAssign();
        ProgramInstructionIndex index = nextIndex();
        Assign assign = new ProgramInstruction.Assign(index);
        String name = sactx.identifier().getText();
        assign.setName(name);
        assign.setHost(hostingObject(name));
        assign.setValue(nodeData(sactx.expr()).jsref);
        piArray.add(assign);
        piAssign(assign);
        return index.getValue();
    }
    
    private int doFunctionCallStatement(FunctionCallStatementContext ctx) {
        FunctionCallContext fcctx = ctx.functionCall();
        String funcName = fcctx.functionIdentifier().getText();
        if (funcName.compareTo("wait") == 0) {
            return doWaitCall(ctx);
        }
        FunctionCallParamsContext fcParams = fcctx.functionCallParams();
        ProgramInstructionIndex index = nextIndex();
        Fcall fc = new ProgramInstruction.Fcall(index);
        fc.setName(funcName);
        fc.setHost(functionHost(funcName));
        fc.setParams(nodeData(fcParams).jsref);
        piArray.add(fc);
        piFcall(fc);
        return index.getValue();
    }

    private int doWhileStatement(WhileStatementContext ctx) {
        StmtWhileContext swctx = ctx.stmtWhile();
        ProgramInstructionIndex whileIndex = nextIndex();
        While instrWhile = new ProgramInstruction.While(whileIndex);
        instrWhile.setCond(nodeData(swctx.expr()).jsref);
        piArray.add(instrWhile);
        whileStmtStack.push(instrWhile);
        doBlock(swctx.block());
        Goto instrGoto = new ProgramInstruction.Goto(nextIndex());
        instrGoto.setJmp(whileIndex.getValue());
        piArray.add(instrGoto);
        instrWhile.setJmp(currentIndex());
        while (! breakStmtStack.isEmpty()) {
            Goto breakStmt = breakStmtStack.pop();
            breakStmt.setJmp(instrWhile.getJmp());
            piArray.add(breakStmt);
            piGoto(breakStmt);
        }
        piWhile(instrWhile);
        piGoto(instrGoto);
        whileStmtStack.pop();
        return whileIndex.getValue();
    }

    private int doIfStatement(IfStatementContext ctx) {
        StmtIfContext ifctx = ctx.stmtIf();
        ExprContext expr = ifctx.expr();
        BlockContext ifblock = ifctx.block();
        return doIfElse(expr, ifblock, null /* else block is null */);
    }

    private int doIfElseStatement(IfElseStatementContext ctx) {
        StmtIfElseContext ifelsectx = ctx.stmtIfElse();
        ExprContext expr = ifelsectx.expr();
        BlockContext ifblock = ifelsectx.block(0);
        BlockContext elseblock = ifelsectx.block(1);
        return doIfElse(expr, ifblock, elseblock);
    }
    
    private int doIfElse(
            ExprContext expr,
            BlockContext ifBlock, 
            BlockContext elseBlock) {
        
        boolean hasElseBlock = (elseBlock != null);
        ProgramInstructionIndex ifIndex = nextIndex();
        If instrIf = new ProgramInstruction.If(ifIndex);
        piArray.add(instrIf);
        instrIf.setCond(nodeData(expr).jsref);
        doBlock(ifBlock);
        
        if (hasElseBlock) {
            ProgramInstructionIndex gotoIndex = nextIndex();
            Goto instrGoto = new ProgramInstruction.Goto(gotoIndex);
            piArray.add(instrGoto);
            instrIf.setJmp(currentIndex());
            doBlock(elseBlock);
            instrGoto.setJmp(currentIndex());
            piGoto(instrGoto);
        }
        else {
            instrIf.setJmp(currentIndex());
        }
        piIf(instrIf);
        return ifIndex.getValue();
    }

    private int doWaitCall(FunctionCallStatementContext ctx) {
        ExprContext expr;
        ProgramInstructionIndex index = nextIndex();
        Wait wait = new ProgramInstruction.Wait(index);
        try {
            expr = ctx.functionCall().functionCallParams().expr(0);
            wait.setMs(nodeData(expr).jsref);
        }catch (Exception e) {
            //System.out.println(e.toString());
            //wait.setMs("");
            //Setting wait to a really large number, in practise infinite.
            wait.setMs(999999999);
        }
        //wait.setMs(nodeData(expr).jsref);
        piArray.add(wait);
        piWait(wait);
        return index.getValue();
    }

    private ProgramInstructionIndex nextIndex() {
        ProgramInstructionIndex pi = new ProgramInstructionIndex(nextIndexValue);
        ++nextIndexValue;
        return pi;
    }
    
    private ProgramInstructionIndex nextTmpIndex() {
        int idx = nextTmpIndexValue;
        ++nextTmpIndexValue;
        return new ProgramInstructionIndex(idx);
    }
    
    private int currentIndex() {
        return nextIndexValue;
    }
    
    private int phaseIndex(Phase p) {
        String name = p.getName();
        if (name2idx.containsKey(name)) {
            return name2idx.get(name);
        }
        ProgramInstructionIndex idx = nextTmpIndex();
        tmpidx2name.put(idx.getValue(), name);
        return idx.getValue();
    }
    
    private String functionHost(String funcName) {
        SymbolTable symtab = symbolTable();
        if (symtab.isbuiltin(funcName)) {
            return template("abspathBin").render();
        }
        return template("abspathFns").render();
    }
    
    private String hostingObject(String name) throws NullPointerException {
        Symbol s = symbolTable().lookup(name);
        if (s.varType == SymbolType.GVAR) {
            return template("abspathGvars").render();
        }
        if (s.varType == SymbolType.VAL) {
            return template("abspathVals").render();
        }
        if (s.varType == SymbolType.FN) {
            return template("abspathFns").render();
        }
        if (s.varType == SymbolType.VAR) {
            return template("abspathVars").render();
        }
        return "";
    }
    
    private ST piTemplate(ProgramInstruction pi) {
        Opcode opcode = pi.getOpcode();
        String name = String.format("pi%s", opcode.getOpcodeName());
        ST st = template(name);
        
        // Opcode is always needed so we might as well do it here.
        st.add("opcode", pi.getOpcode().code());
        return st;
    }
    
    private void piAssign(Assign assign) {
        ST st = piTemplate(assign);
        st.add("host", assign.getHost());
        st.add("name", assign.getName());
        st.add("value", assign.getValue());
        assign.setCommand(st.render());
    }

    private void piFcall(Fcall fc) {
        ST st = piTemplate(fc);
        st.add("host", fc.getHost());
        st.add("name", fc.getName());
        st.add("params", fc.getParams());
        fc.setCommand(st.render());
    }

    private void piIf(If instrIf) {
        pendingIfs.addLast(instrIf);
    }

    private void piWhile(While instrWhile) {
        ST st = piTemplate(instrWhile);
        st.add("cond", instrWhile.getCond());
        st.add("jmp", instrWhile.getJmp());
        instrWhile.setCommand(st.render());
    }

    private void piGoto(Goto instrGoto) {
        pendingGotos.addLast(instrGoto);
    }

    private void piWait(Wait wait) {
        ST st = piTemplate(wait);
        st.add("ms", wait.getMs());
        wait.setCommand(st.render());
    }

    private void piSuspend(Suspend suspend) {
        ST st = piTemplate(suspend);
        suspend.setCommand(st.render());
    }

    private void piDef(Def def, List<VarDefContext> varDefs) {
        ST st = piTemplate(def);
        Iterator<VarDefContext> it = varDefs.iterator();
        while (it.hasNext()) {
            VarDefContext varDef = it.next();
            String name = varDef.identifier().getText();
            String value = nodeData(varDef.expr()).jsref;
            st.addAggr("varDefs.{name, value}", name, value);
        }

        def.setCommand(st.render());
    }

    private void piUndef(Undef undef) {
        ST st = piTemplate(undef);
        undef.setCommand(st.render());
    }

    private void piEoc(Eoc eoc) {
        ST st = piTemplate(eoc);
        eoc.setCommand(st.render());
    }

    private String condExpr(Cond cond) {
        TransitionCondContext ctx = cond.context();
        NodeData nd = nodeData(ctx);
        ST templ = template("transitionRuleCond");
        templ.add("cond", nd.jsref);
        return templ.render();
    }
    
    private void moreStimuliIf(int idx) {
        ProgramInstructionIndex ifIndex = nextIndex();
        If ifInstr = new ProgramInstruction.If(ifIndex);
        ProgramInstructionIndex gotoIndex = nextIndex();
        Goto instrGoto = new ProgramInstruction.Goto(gotoIndex);
        ifInstr.setJmp(currentIndex());
        instrGoto.setJmp(idx);
        ST templ = template("abspathRtFunc");
        templ.add("name", "stimuli.hasmore");
        ST ftempl = template("funccall");
        ftempl.add("name", templ.render());
        ifInstr.setCond(ftempl.render());
        piIf(ifInstr);
        piGoto(instrGoto);
        piArray.add(ifInstr);
        piArray.add(instrGoto);
    }

    private void doTransitionRules(String phaseName) {
        if (transitionTable.noTransitionsFrom(phaseName)) {
            /*
             * If no transitions are defined from the phase P, 
             * assume an implicit transition from P to final phase.
             */
            addTransitionRule(transitionTable.getFinalPhase());
        }
        else {
            ArrayList<Rule> rules = transitionTable.getRules(phaseName);
            Phase srcPhase = rules.get(0).getFrom();
            Iterator<Rule> it = rules.iterator();
            boolean alwaysTrueCond = false;
            while (it.hasNext()) {
                Rule r = it.next();
                if (r.alwaysTrue()) {
                    alwaysTrueCond = true;
                    addTransitionRule(r.getTo());
                    
                    /*
                     * If a transition rule has a condition which
                     * always evaluates to true, there is no point
                     * in processing subsequent rules.
                     */
                    break;
                }
                addTransitionRule(r.getCond(), r.getTo());
            }
            if (! alwaysTrueCond) {
                /*
                 * If there is no transition condition which always
                 * evaluates to true, we will create an implicit 
                 * transition rule from phase P to itself, which is
                 * always true.
                 */
                addTransitionRule(srcPhase);
            }
        }
    }
    
    private void addTransitionRule(Phase target) {
        addTransitionRule(Transition.ALWAYS_TRUE, target);
    }
    
    private void addTransitionRule(Cond cond, Phase target) {
        if (cond.alwaysTrue()) {
            /*
             * As the transition condition is always true, we
             * can do a little optimization and avoid an "If"
             * statement, and use a simple Goto statement instead.
             */
            Goto instrGoto = new ProgramInstruction.Goto(nextIndex());
            instrGoto.setJmp(phaseIndex(target));
            piGoto(instrGoto);
            piArray.add(instrGoto);
        }
        else {
            If ifInstr = new ProgramInstruction.If(nextIndex());
            Goto instrGoto = new ProgramInstruction.Goto(nextIndex());
            ifInstr.setCond(condExpr(cond));
            ifInstr.setJmp(currentIndex());
            instrGoto.setJmp(phaseIndex(target));
            piIf(ifInstr);
            piGoto(instrGoto);
            piArray.add(ifInstr);
            piArray.add(instrGoto);
        }
    }
    
    private void fixPiIndices() {
        Iterator<ProgramInstruction> it = piArray.iterator();
        while (it.hasNext()) {
            ProgramInstruction instr = it.next();
            if (! (instr instanceof ProgramInstruction.Branching)) {
                continue;
            }
            Branching binstr = (ProgramInstruction.Branching) instr;
            int to = binstr.getJmp();
            if (! tmpidx2name.containsKey(to)) {
                continue;
            }
            String phaseName = tmpidx2name.get(to);
            int phaseIdx = name2idx.get(phaseName);
            binstr.setJmp(phaseIdx);
        }
        
        while (! pendingIfs.isEmpty()) {
            If instrIf = pendingIfs.removeFirst();
            ST st = piTemplate(instrIf);
            st.add("cond", instrIf.getCond());
            st.add("jmp", instrIf.getJmp());
            instrIf.setCommand(st.render());
        }
        while (! pendingGotos.isEmpty()) {
            Goto instrGoto = pendingGotos.removeFirst();
            ST st = piTemplate(instrGoto);
            st.add("jmp", instrGoto.getJmp());
            instrGoto.setCommand(st.render());
        }
    }
    
    private void eoc() {
        Fcall fcall = new ProgramInstruction.Fcall(nextIndex());
        String fname1 = "showmsg";
        fcall.setHost(functionHost(fname1));
        fcall.setName(fname1);
        fcall.setParams(quote(""));
        piArray.add(fcall);
        piFcall(fcall);
        Eoc eoc = new ProgramInstruction.Eoc(nextIndex());
        piArray.add(eoc);
        piEoc(eoc);
    }
    
    private Fcall fcall(String host, String name, String params) {
        Fcall pi = new ProgramInstruction.Fcall(nextIndex());
        pi.setName(name);
        pi.setHost(host);
        pi.setParams(params);
        return pi;
    }
    
    private Fcall fcall(String host, String name, String[] params) {
        return fcall(host, name, StringUtils.join(params, ','));
    }
    
    private Fcall fcall(String host, String name, Collection<String> params) {
        return fcall(host, name, params.<String>toArray(new String[]{}));
    }
    
    private String quote(String s) {
        StringBuilder sb = new StringBuilder();
        sb.append('"');
        sb.append(s);
        sb.append('"');
        return sb.toString();
    }
    
    private int nextIndexValue;
    private int nextTmpIndexValue;
    private int startPiIndex;
    private ProgramInstructionArray piArray;
    private ArrayList<VarDefContext> pfVardefs;
    private ArrayDeque<While> whileStmtStack;
    private ArrayDeque<Goto> breakStmtStack;
    private PhaseData phaseData;
    private TransitionTable transitionTable;
    private ArrayDeque<ProgramInstruction.If> pendingIfs;
    private ArrayDeque<ProgramInstruction.Goto> pendingGotos;
    private Int2ObjectMap<String> tmpidx2name;
    private Object2IntMap<String> name2idx;

    private boolean inInterationBlock;

    private int jumpOffset;
}
