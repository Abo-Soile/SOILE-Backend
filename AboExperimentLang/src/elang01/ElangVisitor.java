// Generated from /Users/tuope/NetBeansProjects/AboExperimentLang/src/elang01/Elang.g4 by ANTLR 4.0
package fi.abo.kogni.soile2.elang;
import org.antlr.v4.runtime.tree.*;
import org.antlr.v4.runtime.Token;

public interface ElangVisitor<T> extends ParseTreeVisitor<T> {
	T visitGvarDef(ElangParser.GvarDefContext ctx);

	T visitNullLiteral(ElangParser.NullLiteralContext ctx);

	T visitIfElseStatement(ElangParser.IfElseStatementContext ctx);

	T visitPhaseSetVarValue(ElangParser.PhaseSetVarValueContext ctx);

	T visitWhileStatement(ElangParser.WhileStatementContext ctx);

	T visitYesNoLiteral(ElangParser.YesNoLiteralContext ctx);

	T visitExprFcall(ElangParser.ExprFcallContext ctx);

	T visitFunctionCall(ElangParser.FunctionCallContext ctx);

	T visitAssignStatement(ElangParser.AssignStatementContext ctx);

	T visitFile(ElangParser.FileContext ctx);

	T visitFvarDef(ElangParser.FvarDefContext ctx);

	T visitExprIndexing(ElangParser.ExprIndexingContext ctx);

	T visitFunctionDefParam(ElangParser.FunctionDefParamContext ctx);

	T visitExprLiteral(ElangParser.ExprLiteralContext ctx);

	T visitTransitionDefs(ElangParser.TransitionDefsContext ctx);

	T visitObjectLiteral(ElangParser.ObjectLiteralContext ctx);

	T visitBreakStatement(ElangParser.BreakStatementContext ctx);

	T visitPhaseFunctionBody(ElangParser.PhaseFunctionBodyContext ctx);

	T visitNumber(ElangParser.NumberContext ctx);

	T visitNothingLiteral(ElangParser.NothingLiteralContext ctx);

	T visitTransitionSrcRepeat(ElangParser.TransitionSrcRepeatContext ctx);

	T visitStmtAssign(ElangParser.StmtAssignContext ctx);

	T visitTransitionSrcId(ElangParser.TransitionSrcIdContext ctx);

	T visitReturnStatement(ElangParser.ReturnStatementContext ctx);

	T visitTransitions(ElangParser.TransitionsContext ctx);

	T visitIntermezzoPhaseDef(ElangParser.IntermezzoPhaseDefContext ctx);

	T visitExprId(ElangParser.ExprIdContext ctx);

	T visitInformationPhase(ElangParser.InformationPhaseContext ctx);

	T visitPhaseSetVar(ElangParser.PhaseSetVarContext ctx);

	T visitIdentifier(ElangParser.IdentifierContext ctx);

	T visitStmtWhile(ElangParser.StmtWhileContext ctx);

	T visitArray(ElangParser.ArrayContext ctx);

	T visitFunctionDef(ElangParser.FunctionDefContext ctx);

	T visitIntermezzoPhase(ElangParser.IntermezzoPhaseContext ctx);

	T visitPair(ElangParser.PairContext ctx);

	T visitStmtIfElse(ElangParser.StmtIfElseContext ctx);

	T visitTransitionTo(ElangParser.TransitionToContext ctx);

	T visitBlock(ElangParser.BlockContext ctx);

	T visitNumberLiteral(ElangParser.NumberLiteralContext ctx);

	T visitObject(ElangParser.ObjectContext ctx);

	T visitStmtBreak(ElangParser.StmtBreakContext ctx);

	T visitExprPropref(ElangParser.ExprProprefContext ctx);

	T visitStmtIf(ElangParser.StmtIfContext ctx);

	T visitPhaseFunctionDef(ElangParser.PhaseFunctionDefContext ctx);

	T visitInteractionPhaseDef(ElangParser.InteractionPhaseDefContext ctx);

	T visitFunctionIdentifier(ElangParser.FunctionIdentifierContext ctx);

	T visitFunctionCallParams(ElangParser.FunctionCallParamsContext ctx);

	T visitPhaseDefs(ElangParser.PhaseDefsContext ctx);

	T visitStringLiteral(ElangParser.StringLiteralContext ctx);

	T visitContinueStatement(ElangParser.ContinueStatementContext ctx);

	T visitArrayLiteral(ElangParser.ArrayLiteralContext ctx);

	T visitStmtContinue(ElangParser.StmtContinueContext ctx);

	T visitVarDefs(ElangParser.VarDefsContext ctx);

	T visitTransition(ElangParser.TransitionContext ctx);

	T visitFunctionBody(ElangParser.FunctionBodyContext ctx);

	T visitFunctionCallStatement(ElangParser.FunctionCallStatementContext ctx);

	T visitOtherTransitions(ElangParser.OtherTransitionsContext ctx);

	T visitToplevelStmt(ElangParser.ToplevelStmtContext ctx);

	T visitTransitionCondFcall(ElangParser.TransitionCondFcallContext ctx);

	T visitFinalTransition(ElangParser.FinalTransitionContext ctx);

	T visitFunctionDefParams(ElangParser.FunctionDefParamsContext ctx);

	T visitVarDef(ElangParser.VarDefContext ctx);

	T visitStmtReturn(ElangParser.StmtReturnContext ctx);

	T visitValDef(ElangParser.ValDefContext ctx);

	T visitFvarDefs(ElangParser.FvarDefsContext ctx);

	T visitIfStatement(ElangParser.IfStatementContext ctx);

	T visitString(ElangParser.StringContext ctx);

	T visitTransitionCondVar(ElangParser.TransitionCondVarContext ctx);

	T visitInteractionPhase(ElangParser.InteractionPhaseContext ctx);

	T visitBooleanLiteral(ElangParser.BooleanLiteralContext ctx);

	T visitStartTransition(ElangParser.StartTransitionContext ctx);

	T visitInformationPhaseDef(ElangParser.InformationPhaseDefContext ctx);
}