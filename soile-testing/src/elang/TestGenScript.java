package elang;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.IdentityHashMap;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

import fi.abo.kogni.soile2.elang.Builtin;
import fi.abo.kogni.soile2.elang.CodeModificationPass;
import fi.abo.kogni.soile2.elang.ElangLexer;
import fi.abo.kogni.soile2.elang.ElangParser;
import fi.abo.kogni.soile2.elang.GeneratedCodeOutput;
import fi.abo.kogni.soile2.elang.InstructionArrayGenerationPass;
import fi.abo.kogni.soile2.elang.JsexprPass;
import fi.abo.kogni.soile2.elang.NodeData;
import fi.abo.kogni.soile2.elang.ParseTreeProcessor;
import fi.abo.kogni.soile2.elang.ParseTreeProcessorException;
import fi.abo.kogni.soile2.elang.PhaseData;
import fi.abo.kogni.soile2.elang.PhaseTransitionPass;
import fi.abo.kogni.soile2.elang.ProcessorListenerPass;
import fi.abo.kogni.soile2.elang.ProcessorVisitorPass;
import fi.abo.kogni.soile2.elang.RuleConformancePass;
import fi.abo.kogni.soile2.elang.StmtRenderPass;
import fi.abo.kogni.soile2.elang.SymbolTable;
import fi.abo.kogni.soile2.elang.SymtabPass;
import fi.abo.kogni.soile2.elang.ToplevelDefGenerationPass;
import fi.abo.kogni.soile2.elang.TransitionTable;

public class TestGenScript {
    
    public void setInputFile(String inputFilename) {
        this.inputFilename = inputFilename;
    }
    
    public void setTemplate(String filename) {
        this.template = new STGroupFile(filename);
    }

    public STGroup getTemplate() {
        return this.template;
    }

    public void run() throws IOException {
        SymbolTable symtab = new SymbolTable();
        IdentityHashMap<ParserRuleContext, NodeData> nodeData = 
                new IdentityHashMap<>();
        PhaseData phaseData = new PhaseData();
        TransitionTable transitionTable = new TransitionTable();
        Builtin.addBuiltins(symtab, getTemplate());
        InputStream is = new FileInputStream(inputFilename);
        ANTLRInputStream input = new ANTLRInputStream(is);
        ElangLexer lexer = new ElangLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ElangParser parser = new ElangParser(tokens);
        ParseTree tree = parser.file();
        ParseTreeWalker walker = new ParseTreeWalker();
        ParseTreeProcessor processor = new ParseTreeProcessor(tree);
        processor.walker(walker);
        GeneratedCodeOutput codeOutput = new GeneratedCodeOutput(getTemplate());
        try {
            ProcessorListenerPass stp = new SymtabPass(nodeData);
            stp.setAndResetSymbolTable(symtab);
            stp.useTemplate(getTemplate());
            processor.walk(stp);
            
            ProcessorListenerPass cmodp = new CodeModificationPass();
            cmodp.setAndResetSymbolTable(symtab);
            cmodp.setNodeData(nodeData);
            processor.walk(cmodp);
            
            ProcessorListenerPass jsep = new JsexprPass(nodeData);
            jsep.useTemplate(getTemplate());
            jsep.setSymbolTable(symtab);
            processor.walk(jsep);
            
            ProcessorListenerPass rcp = new RuleConformancePass(nodeData);
            processor.walk(rcp);
            
            ProcessorListenerPass srp = new StmtRenderPass(nodeData);
            srp.useTemplate(getTemplate());
            srp.setSymbolTable(symtab);
            processor.walk(srp);
            
            PhaseTransitionPass phasetran = new PhaseTransitionPass(nodeData);
            phasetran.usePhaseData(phaseData);
            phasetran.useTransitionTable(transitionTable);
            processor.walk(phasetran);
            
            ProcessorVisitorPass<Integer> tlgen =
                    new ToplevelDefGenerationPass();
            initVisitor(tlgen, symtab, nodeData, codeOutput);
            processor.visit(tlgen);
            
            InstructionArrayGenerationPass iagen = 
                    new InstructionArrayGenerationPass();
            initVisitor(iagen, symtab, nodeData, codeOutput);
            iagen.usePhaseData(phaseData);
            iagen.useTransitionTable(transitionTable);
            processor.visit(iagen);
            
            System.out.println(codeOutput.toCode());
        } catch (ParseTreeProcessorException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }
    
    private void initVisitor(
            ProcessorVisitorPass<Integer> visitor,
            SymbolTable symtab,
            IdentityHashMap<ParserRuleContext, 
            NodeData> nodeData,
            GeneratedCodeOutput codeOutput) {
        symtab.intoGlobalScope();
        visitor.nodeData(nodeData);
        visitor.symbolTable(symtab);
        visitor.templateGroup(getTemplate());
        visitor.outputTo(codeOutput);
    }

    private STGroup template;
    private String inputFilename;
    
}
