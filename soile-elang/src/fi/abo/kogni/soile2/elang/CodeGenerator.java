package fi.abo.kogni.soile2.elang;

import java.io.IOException;
import java.io.Reader;
import java.util.IdentityHashMap;
import java.util.LinkedList;
import java.util.List;

import org.antlr.v4.runtime.*;
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

            //Registering custom error listener
            CustomErrorListener customErrorListener = new CustomErrorListener();
            parser.addErrorListener(customErrorListener);

            ParseTree tree = parser.file();

            //Potential errors are added to the errorstring
            for(String err:customErrorListener.getErrors()) {
                this.errorMessages.append(err);
                this.errorMessages.append(System.lineSeparator());
            }

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
        } catch (NullPointerException e) {
            this.errorMessages.append("Undefined error, make sure that variables are defined correctly before assignments.");
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


class CustomErrorListener extends BaseErrorListener {
    public List<String> errors = new LinkedList<String>();
    public static final CustomErrorListener INSTANCE = new CustomErrorListener();


    @Override
    public void syntaxError(Recognizer<?, ?> recognizer,
                            Object offendingSymbol,
                            int line,
                            int charPositionInLine,
                            String msg,
                            RecognitionException e)
    {
        //System.err.println("line " + line + ":" + charPositionInLine + " " + msg);
        errors.add("line " + line + ":" + charPositionInLine + " " + msg);
    }

    public List<String> getErrors() {
        return errors;
    }
}
