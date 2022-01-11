// Generated from C:/Users/Danno/soileIDEA/soile2/soile-elang/src/fi/abo/kogni/soile2/elang\Elang.g4 by ANTLR 4.5.1
package fi.abo.kogni.soile2.elang;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ElangParser}.
 */
public interface ElangListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ElangParser#file}.
	 * @param ctx the parse tree
	 */
	void enterFile(ElangParser.FileContext ctx);
	/**
	 * Exit a parse tree produced by {@link ElangParser#file}.
	 * @param ctx the parse tree
	 */
	void exitFile(ElangParser.FileContext ctx);
	/**
	 * Enter a parse tree produced by {@link ElangParser#toplevelStmt}.
	 * @param ctx the parse tree
	 */
	void enterToplevelStmt(ElangParser.ToplevelStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link ElangParser#toplevelStmt}.
	 * @param ctx the parse tree
	 */
	void exitToplevelStmt(ElangParser.ToplevelStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link ElangParser#gvarDef}.
	 * @param ctx the parse tree
	 */
	void enterGvarDef(ElangParser.GvarDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link ElangParser#gvarDef}.
	 * @param ctx the parse tree
	 */
	void exitGvarDef(ElangParser.GvarDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link ElangParser#valDef}.
	 * @param ctx the parse tree
	 */
	void enterValDef(ElangParser.ValDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link ElangParser#valDef}.
	 * @param ctx the parse tree
	 */
	void exitValDef(ElangParser.ValDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link ElangParser#functionDef}.
	 * @param ctx the parse tree
	 */
	void enterFunctionDef(ElangParser.FunctionDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link ElangParser#functionDef}.
	 * @param ctx the parse tree
	 */
	void exitFunctionDef(ElangParser.FunctionDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link ElangParser#functionDefParams}.
	 * @param ctx the parse tree
	 */
	void enterFunctionDefParams(ElangParser.FunctionDefParamsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ElangParser#functionDefParams}.
	 * @param ctx the parse tree
	 */
	void exitFunctionDefParams(ElangParser.FunctionDefParamsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ElangParser#functionDefParam}.
	 * @param ctx the parse tree
	 */
	void enterFunctionDefParam(ElangParser.FunctionDefParamContext ctx);
	/**
	 * Exit a parse tree produced by {@link ElangParser#functionDefParam}.
	 * @param ctx the parse tree
	 */
	void exitFunctionDefParam(ElangParser.FunctionDefParamContext ctx);
	/**
	 * Enter a parse tree produced by {@link ElangParser#functionBody}.
	 * @param ctx the parse tree
	 */
	void enterFunctionBody(ElangParser.FunctionBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link ElangParser#functionBody}.
	 * @param ctx the parse tree
	 */
	void exitFunctionBody(ElangParser.FunctionBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link ElangParser#fvarDefs}.
	 * @param ctx the parse tree
	 */
	void enterFvarDefs(ElangParser.FvarDefsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ElangParser#fvarDefs}.
	 * @param ctx the parse tree
	 */
	void exitFvarDefs(ElangParser.FvarDefsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ElangParser#fvarDef}.
	 * @param ctx the parse tree
	 */
	void enterFvarDef(ElangParser.FvarDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link ElangParser#fvarDef}.
	 * @param ctx the parse tree
	 */
	void exitFvarDef(ElangParser.FvarDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link ElangParser#varDefs}.
	 * @param ctx the parse tree
	 */
	void enterVarDefs(ElangParser.VarDefsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ElangParser#varDefs}.
	 * @param ctx the parse tree
	 */
	void exitVarDefs(ElangParser.VarDefsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ElangParser#varDef}.
	 * @param ctx the parse tree
	 */
	void enterVarDef(ElangParser.VarDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link ElangParser#varDef}.
	 * @param ctx the parse tree
	 */
	void exitVarDef(ElangParser.VarDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link ElangParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(ElangParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link ElangParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(ElangParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprFcall}
	 * labeled alternative in {@link ElangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprFcall(ElangParser.ExprFcallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprFcall}
	 * labeled alternative in {@link ElangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprFcall(ElangParser.ExprFcallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprPropref}
	 * labeled alternative in {@link ElangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprPropref(ElangParser.ExprProprefContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprPropref}
	 * labeled alternative in {@link ElangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprPropref(ElangParser.ExprProprefContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprIndexing}
	 * labeled alternative in {@link ElangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprIndexing(ElangParser.ExprIndexingContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprIndexing}
	 * labeled alternative in {@link ElangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprIndexing(ElangParser.ExprIndexingContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprLiteral}
	 * labeled alternative in {@link ElangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprLiteral(ElangParser.ExprLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprLiteral}
	 * labeled alternative in {@link ElangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprLiteral(ElangParser.ExprLiteralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprId}
	 * labeled alternative in {@link ElangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprId(ElangParser.ExprIdContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprId}
	 * labeled alternative in {@link ElangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprId(ElangParser.ExprIdContext ctx);
	/**
	 * Enter a parse tree produced by {@link ElangParser#identifier}.
	 * @param ctx the parse tree
	 */
	void enterIdentifier(ElangParser.IdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link ElangParser#identifier}.
	 * @param ctx the parse tree
	 */
	void exitIdentifier(ElangParser.IdentifierContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StringLiteral}
	 * labeled alternative in {@link ElangParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterStringLiteral(ElangParser.StringLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StringLiteral}
	 * labeled alternative in {@link ElangParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitStringLiteral(ElangParser.StringLiteralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NumberLiteral}
	 * labeled alternative in {@link ElangParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterNumberLiteral(ElangParser.NumberLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NumberLiteral}
	 * labeled alternative in {@link ElangParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitNumberLiteral(ElangParser.NumberLiteralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ObjectLiteral}
	 * labeled alternative in {@link ElangParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterObjectLiteral(ElangParser.ObjectLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ObjectLiteral}
	 * labeled alternative in {@link ElangParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitObjectLiteral(ElangParser.ObjectLiteralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ArrayLiteral}
	 * labeled alternative in {@link ElangParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterArrayLiteral(ElangParser.ArrayLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ArrayLiteral}
	 * labeled alternative in {@link ElangParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitArrayLiteral(ElangParser.ArrayLiteralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BooleanLiteral}
	 * labeled alternative in {@link ElangParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterBooleanLiteral(ElangParser.BooleanLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BooleanLiteral}
	 * labeled alternative in {@link ElangParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitBooleanLiteral(ElangParser.BooleanLiteralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code YesNoLiteral}
	 * labeled alternative in {@link ElangParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterYesNoLiteral(ElangParser.YesNoLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code YesNoLiteral}
	 * labeled alternative in {@link ElangParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitYesNoLiteral(ElangParser.YesNoLiteralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NullLiteral}
	 * labeled alternative in {@link ElangParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterNullLiteral(ElangParser.NullLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NullLiteral}
	 * labeled alternative in {@link ElangParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitNullLiteral(ElangParser.NullLiteralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NothingLiteral}
	 * labeled alternative in {@link ElangParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterNothingLiteral(ElangParser.NothingLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NothingLiteral}
	 * labeled alternative in {@link ElangParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitNothingLiteral(ElangParser.NothingLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link ElangParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void enterFunctionCall(ElangParser.FunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link ElangParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void exitFunctionCall(ElangParser.FunctionCallContext ctx);
	/**
	 * Enter a parse tree produced by {@link ElangParser#functionIdentifier}.
	 * @param ctx the parse tree
	 */
	void enterFunctionIdentifier(ElangParser.FunctionIdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link ElangParser#functionIdentifier}.
	 * @param ctx the parse tree
	 */
	void exitFunctionIdentifier(ElangParser.FunctionIdentifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link ElangParser#functionCallParams}.
	 * @param ctx the parse tree
	 */
	void enterFunctionCallParams(ElangParser.FunctionCallParamsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ElangParser#functionCallParams}.
	 * @param ctx the parse tree
	 */
	void exitFunctionCallParams(ElangParser.FunctionCallParamsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ElangParser#object}.
	 * @param ctx the parse tree
	 */
	void enterObject(ElangParser.ObjectContext ctx);
	/**
	 * Exit a parse tree produced by {@link ElangParser#object}.
	 * @param ctx the parse tree
	 */
	void exitObject(ElangParser.ObjectContext ctx);
	/**
	 * Enter a parse tree produced by {@link ElangParser#array}.
	 * @param ctx the parse tree
	 */
	void enterArray(ElangParser.ArrayContext ctx);
	/**
	 * Exit a parse tree produced by {@link ElangParser#array}.
	 * @param ctx the parse tree
	 */
	void exitArray(ElangParser.ArrayContext ctx);
	/**
	 * Enter a parse tree produced by {@link ElangParser#pair}.
	 * @param ctx the parse tree
	 */
	void enterPair(ElangParser.PairContext ctx);
	/**
	 * Exit a parse tree produced by {@link ElangParser#pair}.
	 * @param ctx the parse tree
	 */
	void exitPair(ElangParser.PairContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ReturnStatement}
	 * labeled alternative in {@link ElangParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterReturnStatement(ElangParser.ReturnStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ReturnStatement}
	 * labeled alternative in {@link ElangParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitReturnStatement(ElangParser.ReturnStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ContinueStatement}
	 * labeled alternative in {@link ElangParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterContinueStatement(ElangParser.ContinueStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ContinueStatement}
	 * labeled alternative in {@link ElangParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitContinueStatement(ElangParser.ContinueStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BreakStatement}
	 * labeled alternative in {@link ElangParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterBreakStatement(ElangParser.BreakStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BreakStatement}
	 * labeled alternative in {@link ElangParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitBreakStatement(ElangParser.BreakStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AssignStatement}
	 * labeled alternative in {@link ElangParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterAssignStatement(ElangParser.AssignStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AssignStatement}
	 * labeled alternative in {@link ElangParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitAssignStatement(ElangParser.AssignStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IfElseStatement}
	 * labeled alternative in {@link ElangParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterIfElseStatement(ElangParser.IfElseStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IfElseStatement}
	 * labeled alternative in {@link ElangParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitIfElseStatement(ElangParser.IfElseStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IfStatement}
	 * labeled alternative in {@link ElangParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterIfStatement(ElangParser.IfStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IfStatement}
	 * labeled alternative in {@link ElangParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitIfStatement(ElangParser.IfStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code WhileStatement}
	 * labeled alternative in {@link ElangParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterWhileStatement(ElangParser.WhileStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code WhileStatement}
	 * labeled alternative in {@link ElangParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitWhileStatement(ElangParser.WhileStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code FunctionCallStatement}
	 * labeled alternative in {@link ElangParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterFunctionCallStatement(ElangParser.FunctionCallStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code FunctionCallStatement}
	 * labeled alternative in {@link ElangParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitFunctionCallStatement(ElangParser.FunctionCallStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link ElangParser#stmtAssign}.
	 * @param ctx the parse tree
	 */
	void enterStmtAssign(ElangParser.StmtAssignContext ctx);
	/**
	 * Exit a parse tree produced by {@link ElangParser#stmtAssign}.
	 * @param ctx the parse tree
	 */
	void exitStmtAssign(ElangParser.StmtAssignContext ctx);
	/**
	 * Enter a parse tree produced by {@link ElangParser#stmtContinue}.
	 * @param ctx the parse tree
	 */
	void enterStmtContinue(ElangParser.StmtContinueContext ctx);
	/**
	 * Exit a parse tree produced by {@link ElangParser#stmtContinue}.
	 * @param ctx the parse tree
	 */
	void exitStmtContinue(ElangParser.StmtContinueContext ctx);
	/**
	 * Enter a parse tree produced by {@link ElangParser#stmtBreak}.
	 * @param ctx the parse tree
	 */
	void enterStmtBreak(ElangParser.StmtBreakContext ctx);
	/**
	 * Exit a parse tree produced by {@link ElangParser#stmtBreak}.
	 * @param ctx the parse tree
	 */
	void exitStmtBreak(ElangParser.StmtBreakContext ctx);
	/**
	 * Enter a parse tree produced by {@link ElangParser#stmtReturn}.
	 * @param ctx the parse tree
	 */
	void enterStmtReturn(ElangParser.StmtReturnContext ctx);
	/**
	 * Exit a parse tree produced by {@link ElangParser#stmtReturn}.
	 * @param ctx the parse tree
	 */
	void exitStmtReturn(ElangParser.StmtReturnContext ctx);
	/**
	 * Enter a parse tree produced by {@link ElangParser#stmtIfElse}.
	 * @param ctx the parse tree
	 */
	void enterStmtIfElse(ElangParser.StmtIfElseContext ctx);
	/**
	 * Exit a parse tree produced by {@link ElangParser#stmtIfElse}.
	 * @param ctx the parse tree
	 */
	void exitStmtIfElse(ElangParser.StmtIfElseContext ctx);
	/**
	 * Enter a parse tree produced by {@link ElangParser#stmtIf}.
	 * @param ctx the parse tree
	 */
	void enterStmtIf(ElangParser.StmtIfContext ctx);
	/**
	 * Exit a parse tree produced by {@link ElangParser#stmtIf}.
	 * @param ctx the parse tree
	 */
	void exitStmtIf(ElangParser.StmtIfContext ctx);
	/**
	 * Enter a parse tree produced by {@link ElangParser#stmtWhile}.
	 * @param ctx the parse tree
	 */
	void enterStmtWhile(ElangParser.StmtWhileContext ctx);
	/**
	 * Exit a parse tree produced by {@link ElangParser#stmtWhile}.
	 * @param ctx the parse tree
	 */
	void exitStmtWhile(ElangParser.StmtWhileContext ctx);
	/**
	 * Enter a parse tree produced by {@link ElangParser#phaseDefs}.
	 * @param ctx the parse tree
	 */
	void enterPhaseDefs(ElangParser.PhaseDefsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ElangParser#phaseDefs}.
	 * @param ctx the parse tree
	 */
	void exitPhaseDefs(ElangParser.PhaseDefsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code InformationPhaseDef}
	 * labeled alternative in {@link ElangParser#phaseDef}.
	 * @param ctx the parse tree
	 */
	void enterInformationPhaseDef(ElangParser.InformationPhaseDefContext ctx);
	/**
	 * Exit a parse tree produced by the {@code InformationPhaseDef}
	 * labeled alternative in {@link ElangParser#phaseDef}.
	 * @param ctx the parse tree
	 */
	void exitInformationPhaseDef(ElangParser.InformationPhaseDefContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IntermezzoPhaseDef}
	 * labeled alternative in {@link ElangParser#phaseDef}.
	 * @param ctx the parse tree
	 */
	void enterIntermezzoPhaseDef(ElangParser.IntermezzoPhaseDefContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IntermezzoPhaseDef}
	 * labeled alternative in {@link ElangParser#phaseDef}.
	 * @param ctx the parse tree
	 */
	void exitIntermezzoPhaseDef(ElangParser.IntermezzoPhaseDefContext ctx);
	/**
	 * Enter a parse tree produced by the {@code InteractionPhaseDef}
	 * labeled alternative in {@link ElangParser#phaseDef}.
	 * @param ctx the parse tree
	 */
	void enterInteractionPhaseDef(ElangParser.InteractionPhaseDefContext ctx);
	/**
	 * Exit a parse tree produced by the {@code InteractionPhaseDef}
	 * labeled alternative in {@link ElangParser#phaseDef}.
	 * @param ctx the parse tree
	 */
	void exitInteractionPhaseDef(ElangParser.InteractionPhaseDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link ElangParser#informationPhase}.
	 * @param ctx the parse tree
	 */
	void enterInformationPhase(ElangParser.InformationPhaseContext ctx);
	/**
	 * Exit a parse tree produced by {@link ElangParser#informationPhase}.
	 * @param ctx the parse tree
	 */
	void exitInformationPhase(ElangParser.InformationPhaseContext ctx);
	/**
	 * Enter a parse tree produced by {@link ElangParser#intermezzoPhase}.
	 * @param ctx the parse tree
	 */
	void enterIntermezzoPhase(ElangParser.IntermezzoPhaseContext ctx);
	/**
	 * Exit a parse tree produced by {@link ElangParser#intermezzoPhase}.
	 * @param ctx the parse tree
	 */
	void exitIntermezzoPhase(ElangParser.IntermezzoPhaseContext ctx);
	/**
	 * Enter a parse tree produced by {@link ElangParser#interactionPhase}.
	 * @param ctx the parse tree
	 */
	void enterInteractionPhase(ElangParser.InteractionPhaseContext ctx);
	/**
	 * Exit a parse tree produced by {@link ElangParser#interactionPhase}.
	 * @param ctx the parse tree
	 */
	void exitInteractionPhase(ElangParser.InteractionPhaseContext ctx);
	/**
	 * Enter a parse tree produced by {@link ElangParser#phaseSetVar}.
	 * @param ctx the parse tree
	 */
	void enterPhaseSetVar(ElangParser.PhaseSetVarContext ctx);
	/**
	 * Exit a parse tree produced by {@link ElangParser#phaseSetVar}.
	 * @param ctx the parse tree
	 */
	void exitPhaseSetVar(ElangParser.PhaseSetVarContext ctx);
	/**
	 * Enter a parse tree produced by {@link ElangParser#phaseSetVarValue}.
	 * @param ctx the parse tree
	 */
	void enterPhaseSetVarValue(ElangParser.PhaseSetVarValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link ElangParser#phaseSetVarValue}.
	 * @param ctx the parse tree
	 */
	void exitPhaseSetVarValue(ElangParser.PhaseSetVarValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link ElangParser#phaseFunctionDef}.
	 * @param ctx the parse tree
	 */
	void enterPhaseFunctionDef(ElangParser.PhaseFunctionDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link ElangParser#phaseFunctionDef}.
	 * @param ctx the parse tree
	 */
	void exitPhaseFunctionDef(ElangParser.PhaseFunctionDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link ElangParser#phaseFunctionBody}.
	 * @param ctx the parse tree
	 */
	void enterPhaseFunctionBody(ElangParser.PhaseFunctionBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link ElangParser#phaseFunctionBody}.
	 * @param ctx the parse tree
	 */
	void exitPhaseFunctionBody(ElangParser.PhaseFunctionBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link ElangParser#transitionDefs}.
	 * @param ctx the parse tree
	 */
	void enterTransitionDefs(ElangParser.TransitionDefsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ElangParser#transitionDefs}.
	 * @param ctx the parse tree
	 */
	void exitTransitionDefs(ElangParser.TransitionDefsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ElangParser#transitions}.
	 * @param ctx the parse tree
	 */
	void enterTransitions(ElangParser.TransitionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ElangParser#transitions}.
	 * @param ctx the parse tree
	 */
	void exitTransitions(ElangParser.TransitionsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ElangParser#startTransition}.
	 * @param ctx the parse tree
	 */
	void enterStartTransition(ElangParser.StartTransitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ElangParser#startTransition}.
	 * @param ctx the parse tree
	 */
	void exitStartTransition(ElangParser.StartTransitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ElangParser#otherTransitions}.
	 * @param ctx the parse tree
	 */
	void enterOtherTransitions(ElangParser.OtherTransitionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ElangParser#otherTransitions}.
	 * @param ctx the parse tree
	 */
	void exitOtherTransitions(ElangParser.OtherTransitionsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ElangParser#finalTransition}.
	 * @param ctx the parse tree
	 */
	void enterFinalTransition(ElangParser.FinalTransitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ElangParser#finalTransition}.
	 * @param ctx the parse tree
	 */
	void exitFinalTransition(ElangParser.FinalTransitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ElangParser#transition}.
	 * @param ctx the parse tree
	 */
	void enterTransition(ElangParser.TransitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ElangParser#transition}.
	 * @param ctx the parse tree
	 */
	void exitTransition(ElangParser.TransitionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code TransitionSrcRepeat}
	 * labeled alternative in {@link ElangParser#transitionSrc}.
	 * @param ctx the parse tree
	 */
	void enterTransitionSrcRepeat(ElangParser.TransitionSrcRepeatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code TransitionSrcRepeat}
	 * labeled alternative in {@link ElangParser#transitionSrc}.
	 * @param ctx the parse tree
	 */
	void exitTransitionSrcRepeat(ElangParser.TransitionSrcRepeatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code TransitionSrcId}
	 * labeled alternative in {@link ElangParser#transitionSrc}.
	 * @param ctx the parse tree
	 */
	void enterTransitionSrcId(ElangParser.TransitionSrcIdContext ctx);
	/**
	 * Exit a parse tree produced by the {@code TransitionSrcId}
	 * labeled alternative in {@link ElangParser#transitionSrc}.
	 * @param ctx the parse tree
	 */
	void exitTransitionSrcId(ElangParser.TransitionSrcIdContext ctx);
	/**
	 * Enter a parse tree produced by {@link ElangParser#transitionTo}.
	 * @param ctx the parse tree
	 */
	void enterTransitionTo(ElangParser.TransitionToContext ctx);
	/**
	 * Exit a parse tree produced by {@link ElangParser#transitionTo}.
	 * @param ctx the parse tree
	 */
	void exitTransitionTo(ElangParser.TransitionToContext ctx);
	/**
	 * Enter a parse tree produced by the {@code TransitionCondFcall}
	 * labeled alternative in {@link ElangParser#transitionCond}.
	 * @param ctx the parse tree
	 */
	void enterTransitionCondFcall(ElangParser.TransitionCondFcallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code TransitionCondFcall}
	 * labeled alternative in {@link ElangParser#transitionCond}.
	 * @param ctx the parse tree
	 */
	void exitTransitionCondFcall(ElangParser.TransitionCondFcallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code TransitionCondVar}
	 * labeled alternative in {@link ElangParser#transitionCond}.
	 * @param ctx the parse tree
	 */
	void enterTransitionCondVar(ElangParser.TransitionCondVarContext ctx);
	/**
	 * Exit a parse tree produced by the {@code TransitionCondVar}
	 * labeled alternative in {@link ElangParser#transitionCond}.
	 * @param ctx the parse tree
	 */
	void exitTransitionCondVar(ElangParser.TransitionCondVarContext ctx);
	/**
	 * Enter a parse tree produced by {@link ElangParser#number}.
	 * @param ctx the parse tree
	 */
	void enterNumber(ElangParser.NumberContext ctx);
	/**
	 * Exit a parse tree produced by {@link ElangParser#number}.
	 * @param ctx the parse tree
	 */
	void exitNumber(ElangParser.NumberContext ctx);
	/**
	 * Enter a parse tree produced by {@link ElangParser#string}.
	 * @param ctx the parse tree
	 */
	void enterString(ElangParser.StringContext ctx);
	/**
	 * Exit a parse tree produced by {@link ElangParser#string}.
	 * @param ctx the parse tree
	 */
	void exitString(ElangParser.StringContext ctx);
}