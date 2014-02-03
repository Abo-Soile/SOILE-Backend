// Generated from Elang.g4 by ANTLR 4.0
package fi.abo.kogni.soile2.elang;
import org.antlr.v4.runtime.tree.*;
import org.antlr.v4.runtime.Token;

public interface ElangListener extends ParseTreeListener {
	void enterIterationBody(ElangParser.IterationBodyContext ctx);
	void exitIterationBody(ElangParser.IterationBodyContext ctx);

	void enterSpecialFID(ElangParser.SpecialFIDContext ctx);
	void exitSpecialFID(ElangParser.SpecialFIDContext ctx);

	void enterFunctionDef(ElangParser.FunctionDefContext ctx);
	void exitFunctionDef(ElangParser.FunctionDefContext ctx);

	void enterGvarDef(ElangParser.GvarDefContext ctx);
	void exitGvarDef(ElangParser.GvarDefContext ctx);

	void enterNullLiteral(ElangParser.NullLiteralContext ctx);
	void exitNullLiteral(ElangParser.NullLiteralContext ctx);

	void enterPair(ElangParser.PairContext ctx);
	void exitPair(ElangParser.PairContext ctx);

	void enterBlock(ElangParser.BlockContext ctx);
	void exitBlock(ElangParser.BlockContext ctx);

	void enterNumberLiteral(ElangParser.NumberLiteralContext ctx);
	void exitNumberLiteral(ElangParser.NumberLiteralContext ctx);

	void enterStmtBreak(ElangParser.StmtBreakContext ctx);
	void exitStmtBreak(ElangParser.StmtBreakContext ctx);

	void enterObject(ElangParser.ObjectContext ctx);
	void exitObject(ElangParser.ObjectContext ctx);

	void enterExprFcall(ElangParser.ExprFcallContext ctx);
	void exitExprFcall(ElangParser.ExprFcallContext ctx);

	void enterIteration(ElangParser.IterationContext ctx);
	void exitIteration(ElangParser.IterationContext ctx);

	void enterStmtIf(ElangParser.StmtIfContext ctx);
	void exitStmtIf(ElangParser.StmtIfContext ctx);

	void enterExprPropref(ElangParser.ExprProprefContext ctx);
	void exitExprPropref(ElangParser.ExprProprefContext ctx);

	void enterFunctionCall(ElangParser.FunctionCallContext ctx);
	void exitFunctionCall(ElangParser.FunctionCallContext ctx);

	void enterFvarDef(ElangParser.FvarDefContext ctx);
	void exitFvarDef(ElangParser.FvarDefContext ctx);

	void enterFile(ElangParser.FileContext ctx);
	void exitFile(ElangParser.FileContext ctx);

	void enterExprIndexing(ElangParser.ExprIndexingContext ctx);
	void exitExprIndexing(ElangParser.ExprIndexingContext ctx);

	void enterFunctionDefParam(ElangParser.FunctionDefParamContext ctx);
	void exitFunctionDefParam(ElangParser.FunctionDefParamContext ctx);

	void enterFunctionCallParams(ElangParser.FunctionCallParamsContext ctx);
	void exitFunctionCallParams(ElangParser.FunctionCallParamsContext ctx);

	void enterExprLiteral(ElangParser.ExprLiteralContext ctx);
	void exitExprLiteral(ElangParser.ExprLiteralContext ctx);

	void enterStringLiteral(ElangParser.StringLiteralContext ctx);
	void exitStringLiteral(ElangParser.StringLiteralContext ctx);

	void enterObjectLiteral(ElangParser.ObjectLiteralContext ctx);
	void exitObjectLiteral(ElangParser.ObjectLiteralContext ctx);

	void enterArrayLiteral(ElangParser.ArrayLiteralContext ctx);
	void exitArrayLiteral(ElangParser.ArrayLiteralContext ctx);

	void enterStmtContinue(ElangParser.StmtContinueContext ctx);
	void exitStmtContinue(ElangParser.StmtContinueContext ctx);

	void enterVarDefs(ElangParser.VarDefsContext ctx);
	void exitVarDefs(ElangParser.VarDefsContext ctx);

	void enterStmt(ElangParser.StmtContext ctx);
	void exitStmt(ElangParser.StmtContext ctx);

	void enterFunctionBody(ElangParser.FunctionBodyContext ctx);
	void exitFunctionBody(ElangParser.FunctionBodyContext ctx);

	void enterNumber(ElangParser.NumberContext ctx);
	void exitNumber(ElangParser.NumberContext ctx);

	void enterToplevelStmt(ElangParser.ToplevelStmtContext ctx);
	void exitToplevelStmt(ElangParser.ToplevelStmtContext ctx);

	void enterStmtReturn(ElangParser.StmtReturnContext ctx);
	void exitStmtReturn(ElangParser.StmtReturnContext ctx);

	void enterVarDef(ElangParser.VarDefContext ctx);
	void exitVarDef(ElangParser.VarDefContext ctx);

	void enterFunctionDefParams(ElangParser.FunctionDefParamsContext ctx);
	void exitFunctionDefParams(ElangParser.FunctionDefParamsContext ctx);

	void enterRegularFID(ElangParser.RegularFIDContext ctx);
	void exitRegularFID(ElangParser.RegularFIDContext ctx);

	void enterStmtAssign(ElangParser.StmtAssignContext ctx);
	void exitStmtAssign(ElangParser.StmtAssignContext ctx);

	void enterFvarDefs(ElangParser.FvarDefsContext ctx);
	void exitFvarDefs(ElangParser.FvarDefsContext ctx);

	void enterValDef(ElangParser.ValDefContext ctx);
	void exitValDef(ElangParser.ValDefContext ctx);

	void enterString(ElangParser.StringContext ctx);
	void exitString(ElangParser.StringContext ctx);

	void enterExprId(ElangParser.ExprIdContext ctx);
	void exitExprId(ElangParser.ExprIdContext ctx);

	void enterIdentifier(ElangParser.IdentifierContext ctx);
	void exitIdentifier(ElangParser.IdentifierContext ctx);

	void enterBooleanLiteral(ElangParser.BooleanLiteralContext ctx);
	void exitBooleanLiteral(ElangParser.BooleanLiteralContext ctx);

	void enterStmtWhile(ElangParser.StmtWhileContext ctx);
	void exitStmtWhile(ElangParser.StmtWhileContext ctx);

	void enterArray(ElangParser.ArrayContext ctx);
	void exitArray(ElangParser.ArrayContext ctx);
}