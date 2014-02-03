// Generated from /Users/tuope/NetBeansProjects/AboExperimentLang/src/elang01/Elang.g4 by ANTLR 4.0
package fi.abo.kogni.soile2.elang;
import org.antlr.v4.runtime.tree.*;
import org.antlr.v4.runtime.Token;

public interface ElangListener extends ParseTreeListener {
	void enterGvarDef(ElangParser.GvarDefContext ctx);
	void exitGvarDef(ElangParser.GvarDefContext ctx);

	void enterNullLiteral(ElangParser.NullLiteralContext ctx);
	void exitNullLiteral(ElangParser.NullLiteralContext ctx);

	void enterIfElseStatement(ElangParser.IfElseStatementContext ctx);
	void exitIfElseStatement(ElangParser.IfElseStatementContext ctx);

	void enterPhaseSetVarValue(ElangParser.PhaseSetVarValueContext ctx);
	void exitPhaseSetVarValue(ElangParser.PhaseSetVarValueContext ctx);

	void enterWhileStatement(ElangParser.WhileStatementContext ctx);
	void exitWhileStatement(ElangParser.WhileStatementContext ctx);

	void enterYesNoLiteral(ElangParser.YesNoLiteralContext ctx);
	void exitYesNoLiteral(ElangParser.YesNoLiteralContext ctx);

	void enterExprFcall(ElangParser.ExprFcallContext ctx);
	void exitExprFcall(ElangParser.ExprFcallContext ctx);

	void enterFunctionCall(ElangParser.FunctionCallContext ctx);
	void exitFunctionCall(ElangParser.FunctionCallContext ctx);

	void enterAssignStatement(ElangParser.AssignStatementContext ctx);
	void exitAssignStatement(ElangParser.AssignStatementContext ctx);

	void enterFile(ElangParser.FileContext ctx);
	void exitFile(ElangParser.FileContext ctx);

	void enterFvarDef(ElangParser.FvarDefContext ctx);
	void exitFvarDef(ElangParser.FvarDefContext ctx);

	void enterExprIndexing(ElangParser.ExprIndexingContext ctx);
	void exitExprIndexing(ElangParser.ExprIndexingContext ctx);

	void enterFunctionDefParam(ElangParser.FunctionDefParamContext ctx);
	void exitFunctionDefParam(ElangParser.FunctionDefParamContext ctx);

	void enterExprLiteral(ElangParser.ExprLiteralContext ctx);
	void exitExprLiteral(ElangParser.ExprLiteralContext ctx);

	void enterTransitionDefs(ElangParser.TransitionDefsContext ctx);
	void exitTransitionDefs(ElangParser.TransitionDefsContext ctx);

	void enterObjectLiteral(ElangParser.ObjectLiteralContext ctx);
	void exitObjectLiteral(ElangParser.ObjectLiteralContext ctx);

	void enterBreakStatement(ElangParser.BreakStatementContext ctx);
	void exitBreakStatement(ElangParser.BreakStatementContext ctx);

	void enterPhaseFunctionBody(ElangParser.PhaseFunctionBodyContext ctx);
	void exitPhaseFunctionBody(ElangParser.PhaseFunctionBodyContext ctx);

	void enterNumber(ElangParser.NumberContext ctx);
	void exitNumber(ElangParser.NumberContext ctx);

	void enterNothingLiteral(ElangParser.NothingLiteralContext ctx);
	void exitNothingLiteral(ElangParser.NothingLiteralContext ctx);

	void enterTransitionSrcRepeat(ElangParser.TransitionSrcRepeatContext ctx);
	void exitTransitionSrcRepeat(ElangParser.TransitionSrcRepeatContext ctx);

	void enterStmtAssign(ElangParser.StmtAssignContext ctx);
	void exitStmtAssign(ElangParser.StmtAssignContext ctx);

	void enterTransitionSrcId(ElangParser.TransitionSrcIdContext ctx);
	void exitTransitionSrcId(ElangParser.TransitionSrcIdContext ctx);

	void enterReturnStatement(ElangParser.ReturnStatementContext ctx);
	void exitReturnStatement(ElangParser.ReturnStatementContext ctx);

	void enterTransitions(ElangParser.TransitionsContext ctx);
	void exitTransitions(ElangParser.TransitionsContext ctx);

	void enterIntermezzoPhaseDef(ElangParser.IntermezzoPhaseDefContext ctx);
	void exitIntermezzoPhaseDef(ElangParser.IntermezzoPhaseDefContext ctx);

	void enterExprId(ElangParser.ExprIdContext ctx);
	void exitExprId(ElangParser.ExprIdContext ctx);

	void enterInformationPhase(ElangParser.InformationPhaseContext ctx);
	void exitInformationPhase(ElangParser.InformationPhaseContext ctx);

	void enterPhaseSetVar(ElangParser.PhaseSetVarContext ctx);
	void exitPhaseSetVar(ElangParser.PhaseSetVarContext ctx);

	void enterIdentifier(ElangParser.IdentifierContext ctx);
	void exitIdentifier(ElangParser.IdentifierContext ctx);

	void enterStmtWhile(ElangParser.StmtWhileContext ctx);
	void exitStmtWhile(ElangParser.StmtWhileContext ctx);

	void enterArray(ElangParser.ArrayContext ctx);
	void exitArray(ElangParser.ArrayContext ctx);

	void enterFunctionDef(ElangParser.FunctionDefContext ctx);
	void exitFunctionDef(ElangParser.FunctionDefContext ctx);

	void enterIntermezzoPhase(ElangParser.IntermezzoPhaseContext ctx);
	void exitIntermezzoPhase(ElangParser.IntermezzoPhaseContext ctx);

	void enterPair(ElangParser.PairContext ctx);
	void exitPair(ElangParser.PairContext ctx);

	void enterStmtIfElse(ElangParser.StmtIfElseContext ctx);
	void exitStmtIfElse(ElangParser.StmtIfElseContext ctx);

	void enterTransitionTo(ElangParser.TransitionToContext ctx);
	void exitTransitionTo(ElangParser.TransitionToContext ctx);

	void enterBlock(ElangParser.BlockContext ctx);
	void exitBlock(ElangParser.BlockContext ctx);

	void enterNumberLiteral(ElangParser.NumberLiteralContext ctx);
	void exitNumberLiteral(ElangParser.NumberLiteralContext ctx);

	void enterObject(ElangParser.ObjectContext ctx);
	void exitObject(ElangParser.ObjectContext ctx);

	void enterStmtBreak(ElangParser.StmtBreakContext ctx);
	void exitStmtBreak(ElangParser.StmtBreakContext ctx);

	void enterExprPropref(ElangParser.ExprProprefContext ctx);
	void exitExprPropref(ElangParser.ExprProprefContext ctx);

	void enterStmtIf(ElangParser.StmtIfContext ctx);
	void exitStmtIf(ElangParser.StmtIfContext ctx);

	void enterPhaseFunctionDef(ElangParser.PhaseFunctionDefContext ctx);
	void exitPhaseFunctionDef(ElangParser.PhaseFunctionDefContext ctx);

	void enterInteractionPhaseDef(ElangParser.InteractionPhaseDefContext ctx);
	void exitInteractionPhaseDef(ElangParser.InteractionPhaseDefContext ctx);

	void enterFunctionIdentifier(ElangParser.FunctionIdentifierContext ctx);
	void exitFunctionIdentifier(ElangParser.FunctionIdentifierContext ctx);

	void enterFunctionCallParams(ElangParser.FunctionCallParamsContext ctx);
	void exitFunctionCallParams(ElangParser.FunctionCallParamsContext ctx);

	void enterPhaseDefs(ElangParser.PhaseDefsContext ctx);
	void exitPhaseDefs(ElangParser.PhaseDefsContext ctx);

	void enterStringLiteral(ElangParser.StringLiteralContext ctx);
	void exitStringLiteral(ElangParser.StringLiteralContext ctx);

	void enterContinueStatement(ElangParser.ContinueStatementContext ctx);
	void exitContinueStatement(ElangParser.ContinueStatementContext ctx);

	void enterArrayLiteral(ElangParser.ArrayLiteralContext ctx);
	void exitArrayLiteral(ElangParser.ArrayLiteralContext ctx);

	void enterStmtContinue(ElangParser.StmtContinueContext ctx);
	void exitStmtContinue(ElangParser.StmtContinueContext ctx);

	void enterVarDefs(ElangParser.VarDefsContext ctx);
	void exitVarDefs(ElangParser.VarDefsContext ctx);

	void enterTransition(ElangParser.TransitionContext ctx);
	void exitTransition(ElangParser.TransitionContext ctx);

	void enterFunctionBody(ElangParser.FunctionBodyContext ctx);
	void exitFunctionBody(ElangParser.FunctionBodyContext ctx);

	void enterFunctionCallStatement(ElangParser.FunctionCallStatementContext ctx);
	void exitFunctionCallStatement(ElangParser.FunctionCallStatementContext ctx);

	void enterOtherTransitions(ElangParser.OtherTransitionsContext ctx);
	void exitOtherTransitions(ElangParser.OtherTransitionsContext ctx);

	void enterToplevelStmt(ElangParser.ToplevelStmtContext ctx);
	void exitToplevelStmt(ElangParser.ToplevelStmtContext ctx);

	void enterTransitionCondFcall(ElangParser.TransitionCondFcallContext ctx);
	void exitTransitionCondFcall(ElangParser.TransitionCondFcallContext ctx);

	void enterFinalTransition(ElangParser.FinalTransitionContext ctx);
	void exitFinalTransition(ElangParser.FinalTransitionContext ctx);

	void enterFunctionDefParams(ElangParser.FunctionDefParamsContext ctx);
	void exitFunctionDefParams(ElangParser.FunctionDefParamsContext ctx);

	void enterVarDef(ElangParser.VarDefContext ctx);
	void exitVarDef(ElangParser.VarDefContext ctx);

	void enterStmtReturn(ElangParser.StmtReturnContext ctx);
	void exitStmtReturn(ElangParser.StmtReturnContext ctx);

	void enterValDef(ElangParser.ValDefContext ctx);
	void exitValDef(ElangParser.ValDefContext ctx);

	void enterFvarDefs(ElangParser.FvarDefsContext ctx);
	void exitFvarDefs(ElangParser.FvarDefsContext ctx);

	void enterIfStatement(ElangParser.IfStatementContext ctx);
	void exitIfStatement(ElangParser.IfStatementContext ctx);

	void enterString(ElangParser.StringContext ctx);
	void exitString(ElangParser.StringContext ctx);

	void enterTransitionCondVar(ElangParser.TransitionCondVarContext ctx);
	void exitTransitionCondVar(ElangParser.TransitionCondVarContext ctx);

	void enterInteractionPhase(ElangParser.InteractionPhaseContext ctx);
	void exitInteractionPhase(ElangParser.InteractionPhaseContext ctx);

	void enterBooleanLiteral(ElangParser.BooleanLiteralContext ctx);
	void exitBooleanLiteral(ElangParser.BooleanLiteralContext ctx);

	void enterStartTransition(ElangParser.StartTransitionContext ctx);
	void exitStartTransition(ElangParser.StartTransitionContext ctx);

	void enterInformationPhaseDef(ElangParser.InformationPhaseDefContext ctx);
	void exitInformationPhaseDef(ElangParser.InformationPhaseDefContext ctx);
}