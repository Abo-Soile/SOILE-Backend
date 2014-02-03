package fi.abo.kogni.soile2.elang;

import java.io.IOException;
import java.io.Reader;
import java.util.IdentityHashMap;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.stringtemplate.v4.STGroup;

public class CodeGenerator {
    
    public CodeGenerator(STGroup template) {
        super();
        this.template = template;
    }
    
    public void codeOutputTo(StringBuilder sb) {
        this.codeOutput = sb;
    }

    public void errorMessagesTo(StringBuilder sb) {
        this.errorMessages = sb;
    }

    public void generate(Reader input) {
        SymbolTable symtab = new SymbolTable();
        IdentityHashMap<ParserRuleContext, NodeData> nodeData = new IdentityHashMap<>();
        PhaseData phaseData = new PhaseData();
        TransitionTable transitionTable = new TransitionTable();
        Builtin.addBuiltins(symtab, template);
        try {
            ANTLRInputStream inputStream = new ANTLRInputStream(input);
            ElangLexer lexer = new ElangLexer(inputStream);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            ElangParser parser = new ElangParser(tokens);
            ParseTree tree = parser.file();
            ParseTreeWalker walker = new ParseTreeWalker();
            ParseTreeProcessor processor = new ParseTreeProcessor(tree);
            processor.walker(walker);
            
            GeneratedCodeOutput generatedOutput = new GeneratedCodeOutput(template);
            ProcessorListenerPass stp = new SymtabPass(nodeData);
            stp.setAndResetSymbolTable(symtab);
            stp.useTemplate(template);
            processor.walk(stp);
            
            ProcessorListenerPass cmodp = new CodeModificationPass();
            cmodp.setAndResetSymbolTable(symtab);
            cmodp.setNodeData(nodeData);
            processor.walk(cmodp);
            
            ProcessorListenerPass jsep = new JsexprPass(nodeData);
            jsep.useTemplate(template);
            jsep.setSymbolTable(symtab);
            processor.walk(jsep);
            
            ProcessorListenerPass rcp = new RuleConformancePass(nodeData);
            processor.walk(rcp);
            
            ProcessorListenerPass srp = new StmtRenderPass(nodeData);
            srp.useTemplate(template);
            srp.setSymbolTable(symtab);
            processor.walk(srp);
            
            PhaseTransitionPass phasetran = new PhaseTransitionPass(nodeData);
            phasetran.usePhaseData(phaseData);
            phasetran.useTransitionTable(transitionTable);
            processor.walk(phasetran);
            
            ProcessorVisitorPass<Integer> tlgen =
                    new ToplevelDefGenerationPass();
            initVisitor(tlgen, symtab, nodeData, generatedOutput);
            processor.visit(tlgen);
            
            InstructionArrayGenerationPass iagen = 
                    new InstructionArrayGenerationPass();
            initVisitor(iagen, symtab, nodeData, generatedOutput);
            iagen.usePhaseData(phaseData);
            iagen.useTransitionTable(transitionTable);
            processor.visit(iagen);
            this.codeOutput.append(generatedOutput.toCode());
        } catch (ParseTreeProcessorException e) {
            this.errorMessages.append(e.getMessage());
        } catch (IOException e) {
            this.errorMessages.append("Problem opening the template file.");
        }
    }
    
    private void initVisitor(
            ProcessorVisitorPass<Integer> visitor,
            SymbolTable symtab,
            IdentityHashMap<ParserRuleContext, 
            NodeData> nodeData,
            GeneratedCodeOutput generatedOutput) {
        symtab.intoGlobalScope();
        visitor.nodeData(nodeData);
        visitor.symbolTable(symtab);
        visitor.templateGroup(template);
        visitor.outputTo(generatedOutput);
    }

    private STGroup template;
    private StringBuilder codeOutput;
    private StringBuilder errorMessages;

}
