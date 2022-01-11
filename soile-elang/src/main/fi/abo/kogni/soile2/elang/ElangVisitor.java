// Generated from C:/Users/Danno/soileIDEA/soile2/soile-elang/src/fi/abo/kogni/soile2/elang\Elang.g4 by ANTLR 4.5.1
package fi.abo.kogni.soile2.elang;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link ElangParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface ElangVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link ElangParser#file}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFile(ElangParser.FileContext ctx);
	/**
	 * Visit a parse tree produced by {@link ElangParser#toplevelStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitToplevelStmt(ElangParser.ToplevelStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link ElangParser#gvarDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGvarDef(ElangParser.GvarDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link ElangParser#valDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValDef(ElangParser.ValDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link ElangParser#functionDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionDef(ElangParser.FunctionDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link ElangParser#functionDefParams}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionDefParams(ElangParser.FunctionDefParamsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ElangParser#functionDefParam}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionDefParam(ElangParser.FunctionDefParamContext ctx);
	/**
	 * Visit a parse tree produced by {@link ElangParser#functionBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionBody(ElangParser.FunctionBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link ElangParser#fvarDefs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFvarDefs(ElangParser.FvarDefsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ElangParser#fvarDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFvarDef(ElangParser.FvarDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link ElangParser#varDefs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDefs(ElangParser.VarDefsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ElangParser#varDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDef(ElangParser.VarDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link ElangParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(ElangParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprFcall}
	 * labeled alternative in {@link ElangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprFcall(ElangParser.ExprFcallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprPropref}
	 * labeled alternative in {@link ElangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprPropref(ElangParser.ExprProprefContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprIndexing}
	 * labeled alternative in {@link ElangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprIndexing(ElangParser.ExprIndexingContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprLiteral}
	 * labeled alternative in {@link ElangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprLiteral(ElangParser.ExprLiteralContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprId}
	 * labeled alternative in {@link ElangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprId(ElangParser.ExprIdContext ctx);
	/**
	 * Visit a parse tree produced by {@link ElangParser#identifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifier(ElangParser.IdentifierContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StringLiteral}
	 * labeled alternative in {@link ElangParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringLiteral(ElangParser.StringLiteralContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NumberLiteral}
	 * labeled alternative in {@link ElangParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumberLiteral(ElangParser.NumberLiteralContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ObjectLiteral}
	 * labeled alternative in {@link ElangParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitObjectLiteral(ElangParser.ObjectLiteralContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ArrayLiteral}
	 * labeled alternative in {@link ElangParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayLiteral(ElangParser.ArrayLiteralContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BooleanLiteral}
	 * labeled alternative in {@link ElangParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanLiteral(ElangParser.BooleanLiteralContext ctx);
	/**
	 * Visit a parse tree produced by the {@code YesNoLiteral}
	 * labeled alternative in {@link ElangParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitYesNoLiteral(ElangParser.YesNoLiteralContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NullLiteral}
	 * labeled alternative in {@link ElangParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNullLiteral(ElangParser.NullLiteralContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NothingLiteral}
	 * labeled alternative in {@link ElangParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNothingLiteral(ElangParser.NothingLiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link ElangParser#functionCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionCall(ElangParser.FunctionCallContext ctx);
	/**
	 * Visit a parse tree produced by {@link ElangParser#functionIdentifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionIdentifier(ElangParser.FunctionIdentifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link ElangParser#functionCallParams}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionCallParams(ElangParser.FunctionCallParamsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ElangParser#object}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitObject(ElangParser.ObjectContext ctx);
	/**
	 * Visit a parse tree produced by {@link ElangParser#array}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArray(ElangParser.ArrayContext ctx);
	/**
	 * Visit a parse tree produced by {@link ElangParser#pair}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPair(ElangParser.PairContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ReturnStatement}
	 * labeled alternative in {@link ElangParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnStatement(ElangParser.ReturnStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ContinueStatement}
	 * labeled alternative in {@link ElangParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContinueStatement(ElangParser.ContinueStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BreakStatement}
	 * labeled alternative in {@link ElangParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBreakStatement(ElangParser.BreakStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AssignStatement}
	 * labeled alternative in {@link ElangParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignStatement(ElangParser.AssignStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IfElseStatement}
	 * labeled alternative in {@link ElangParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfElseStatement(ElangParser.IfElseStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IfStatement}
	 * labeled alternative in {@link ElangParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStatement(ElangParser.IfStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code WhileStatement}
	 * labeled alternative in {@link ElangParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileStatement(ElangParser.WhileStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code FunctionCallStatement}
	 * labeled alternative in {@link ElangParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionCallStatement(ElangParser.FunctionCallStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link ElangParser#stmtAssign}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmtAssign(ElangParser.StmtAssignContext ctx);
	/**
	 * Visit a parse tree produced by {@link ElangParser#stmtContinue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmtContinue(ElangParser.StmtContinueContext ctx);
	/**
	 * Visit a parse tree produced by {@link ElangParser#stmtBreak}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmtBreak(ElangParser.StmtBreakContext ctx);
	/**
	 * Visit a parse tree produced by {@link ElangParser#stmtReturn}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmtReturn(ElangParser.StmtReturnContext ctx);
	/**
	 * Visit a parse tree produced by {@link ElangParser#stmtIfElse}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmtIfElse(ElangParser.StmtIfElseContext ctx);
	/**
	 * Visit a parse tree produced by {@link ElangParser#stmtIf}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmtIf(ElangParser.StmtIfContext ctx);
	/**
	 * Visit a parse tree produced by {@link ElangParser#stmtWhile}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmtWhile(ElangParser.StmtWhileContext ctx);
	/**
	 * Visit a parse tree produced by {@link ElangParser#phaseDefs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPhaseDefs(ElangParser.PhaseDefsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code InformationPhaseDef}
	 * labeled alternative in {@link ElangParser#phaseDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInformationPhaseDef(ElangParser.InformationPhaseDefContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IntermezzoPhaseDef}
	 * labeled alternative in {@link ElangParser#phaseDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntermezzoPhaseDef(ElangParser.IntermezzoPhaseDefContext ctx);
	/**
	 * Visit a parse tree produced by the {@code InteractionPhaseDef}
	 * labeled alternative in {@link ElangParser#phaseDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInteractionPhaseDef(ElangParser.InteractionPhaseDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link ElangParser#informationPhase}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInformationPhase(ElangParser.InformationPhaseContext ctx);
	/**
	 * Visit a parse tree produced by {@link ElangParser#intermezzoPhase}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntermezzoPhase(ElangParser.IntermezzoPhaseContext ctx);
	/**
	 * Visit a parse tree produced by {@link ElangParser#interactionPhase}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInteractionPhase(ElangParser.InteractionPhaseContext ctx);
	/**
	 * Visit a parse tree produced by {@link ElangParser#phaseSetVar}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPhaseSetVar(ElangParser.PhaseSetVarContext ctx);
	/**
	 * Visit a parse tree produced by {@link ElangParser#phaseSetVarValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPhaseSetVarValue(ElangParser.PhaseSetVarValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link ElangParser#phaseFunctionDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPhaseFunctionDef(ElangParser.PhaseFunctionDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link ElangParser#phaseFunctionBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPhaseFunctionBody(ElangParser.PhaseFunctionBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link ElangParser#transitionDefs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTransitionDefs(ElangParser.TransitionDefsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ElangParser#transitions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTransitions(ElangParser.TransitionsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ElangParser#startTransition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStartTransition(ElangParser.StartTransitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link ElangParser#otherTransitions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOtherTransitions(ElangParser.OtherTransitionsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ElangParser#finalTransition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFinalTransition(ElangParser.FinalTransitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link ElangParser#transition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTransition(ElangParser.TransitionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code TransitionSrcRepeat}
	 * labeled alternative in {@link ElangParser#transitionSrc}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTransitionSrcRepeat(ElangParser.TransitionSrcRepeatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code TransitionSrcId}
	 * labeled alternative in {@link ElangParser#transitionSrc}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTransitionSrcId(ElangParser.TransitionSrcIdContext ctx);
	/**
	 * Visit a parse tree produced by {@link ElangParser#transitionTo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTransitionTo(ElangParser.TransitionToContext ctx);
	/**
	 * Visit a parse tree produced by the {@code TransitionCondFcall}
	 * labeled alternative in {@link ElangParser#transitionCond}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTransitionCondFcall(ElangParser.TransitionCondFcallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code TransitionCondVar}
	 * labeled alternative in {@link ElangParser#transitionCond}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTransitionCondVar(ElangParser.TransitionCondVarContext ctx);
	/**
	 * Visit a parse tree produced by {@link ElangParser#number}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumber(ElangParser.NumberContext ctx);
	/**
	 * Visit a parse tree produced by {@link ElangParser#string}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitString(ElangParser.StringContext ctx);
}