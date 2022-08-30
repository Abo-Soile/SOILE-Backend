// Generated from /Users/tuope/NetBeansProjects/AboExperimentLang/src/demo/DemoLangV001.g4 by ANTLR 4.0
package demo;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class DemoLangV001Parser extends Parser {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		SFN_ONEPLUS=1, SFN_ONEMINUS=2, SFN_PLUS=3, SFN_MINUS=4, SFN_MULTIPLY=5, 
		SFN_DIVIDE=6, SFN_LESSTHAN=7, SFN_GREATERTHAN=8, SFN_EQUAL=9, SFN_NEGATE=10, 
		T_ARROWLEFT=11, T_ARROWRIGHT=12, T_BRACELEFT=13, T_BRACERIGHT=14, T_BRACKETLEFT=15, 
		T_BRACKETRIGHT=16, T_BREAK=17, T_COLON=18, T_COMMA=19, T_CONTINUE=20, 
		T_DO=21, T_DOT=22, T_ELSE=23, T_END=24, T_FALSE=25, T_FUNCTION=26, T_FVAR=27, 
		T_GVAR=28, T_IF=29, T_ITERATION=30, T_MAP=31, T_NULL=32, T_PARENLEFT=33, 
		T_PARENRIGHT=34, T_QMARK=35, T_RETURN=36, T_THEN=37, T_TRUE=38, T_VAL=39, 
		T_VAR=40, T_WHILE=41, ID=42, NUMBER=43, STRING=44, WS=45;
	public static final String[] tokenNames = {
		"<INVALID>", "'1+'", "'1-'", "'+'", "'-'", "'*'", "'/'", "'<'", "'>'", 
		"'='", "'!'", "'<-'", "'->'", "'{'", "'}'", "'['", "']'", "'break'", "':'", 
		"','", "'continue'", "'do'", "'.'", "'else'", "'end'", "'false'", "'function'", 
		"'fvar'", "'gvar'", "'if'", "'iteration'", "'Map'", "'null'", "'('", "')'", 
		"'?'", "'return'", "'then'", "'true'", "'val'", "'var'", "'while'", "ID", 
		"NUMBER", "STRING", "WS"
	};
	public static final int
		RULE_script = 0, RULE_iteration = 1, RULE_globalDefs = 2, RULE_gvarDef = 3, 
		RULE_functionDef = 4, RULE_functionDefParams = 5, RULE_functionDefParam = 6, 
		RULE_functionBody = 7, RULE_block = 8, RULE_expr = 9, RULE_identifier = 10, 
		RULE_literal = 11, RULE_functionCall = 12, RULE_functionIdentifier = 13, 
		RULE_functionCallParams = 14, RULE_object = 15, RULE_array = 16, RULE_pair = 17, 
		RULE_booleanValue = 18, RULE_stmt = 19, RULE_stmtVarDef = 20, RULE_stmtAssign = 21, 
		RULE_stmtContinue = 22, RULE_stmtBreak = 23, RULE_stmtReturn = 24, RULE_stmtIf = 25, 
		RULE_stmtWhile = 26;
	public static final String[] ruleNames = {
		"script", "iteration", "globalDefs", "gvarDef", "functionDef", "functionDefParams", 
		"functionDefParam", "functionBody", "block", "expr", "identifier", "literal", 
		"functionCall", "functionIdentifier", "functionCallParams", "object", 
		"array", "pair", "booleanValue", "stmt", "stmtVarDef", "stmtAssign", "stmtContinue", 
		"stmtBreak", "stmtReturn", "stmtIf", "stmtWhile"
	};

	@Override
	public String getGrammarFileName() { return "DemoLangV001.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public DemoLangV001Parser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ScriptContext extends ParserRuleContext {
		public GlobalDefsContext globalDefs() {
			return getRuleContext(GlobalDefsContext.class,0);
		}
		public TerminalNode EOF() { return getToken(DemoLangV001Parser.EOF, 0); }
		public IterationContext iteration() {
			return getRuleContext(IterationContext.class,0);
		}
		public ScriptContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_script; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DemoLangV001Listener ) ((DemoLangV001Listener)listener).enterScript(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DemoLangV001Listener ) ((DemoLangV001Listener)listener).exitScript(this);
		}
	}

	public final ScriptContext script() throws RecognitionException {
		ScriptContext _localctx = new ScriptContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_script);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(55);
			_la = _input.LA(1);
			if (_la==T_FUNCTION || _la==T_GVAR) {
				{
				setState(54); globalDefs();
				}
			}

			setState(57); iteration();
			setState(58); match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IterationContext extends ParserRuleContext {
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public IterationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_iteration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DemoLangV001Listener ) ((DemoLangV001Listener)listener).enterIteration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DemoLangV001Listener ) ((DemoLangV001Listener)listener).exitIteration(this);
		}
	}

	public final IterationContext iteration() throws RecognitionException {
		IterationContext _localctx = new IterationContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_iteration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(60); match(T_ITERATION);
			setState(61); block();
			setState(62); match(T_END);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class GlobalDefsContext extends ParserRuleContext {
		public List<FunctionDefContext> functionDef() {
			return getRuleContexts(FunctionDefContext.class);
		}
		public List<GvarDefContext> gvarDef() {
			return getRuleContexts(GvarDefContext.class);
		}
		public GvarDefContext gvarDef(int i) {
			return getRuleContext(GvarDefContext.class,i);
		}
		public FunctionDefContext functionDef(int i) {
			return getRuleContext(FunctionDefContext.class,i);
		}
		public GlobalDefsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_globalDefs; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DemoLangV001Listener ) ((DemoLangV001Listener)listener).enterGlobalDefs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DemoLangV001Listener ) ((DemoLangV001Listener)listener).exitGlobalDefs(this);
		}
	}

	public final GlobalDefsContext globalDefs() throws RecognitionException {
		GlobalDefsContext _localctx = new GlobalDefsContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_globalDefs);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(66); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(66);
				switch (_input.LA(1)) {
				case T_GVAR:
					{
					setState(64); gvarDef();
					}
					break;
				case T_FUNCTION:
					{
					setState(65); functionDef();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(68); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T_FUNCTION || _la==T_GVAR );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class GvarDefContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public GvarDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_gvarDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DemoLangV001Listener ) ((DemoLangV001Listener)listener).enterGvarDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DemoLangV001Listener ) ((DemoLangV001Listener)listener).exitGvarDef(this);
		}
	}

	public final GvarDefContext gvarDef() throws RecognitionException {
		GvarDefContext _localctx = new GvarDefContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_gvarDef);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(70); match(T_GVAR);
			setState(71); identifier();
			setState(72); match(T_ARROWLEFT);
			setState(73); expr();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionDefContext extends ParserRuleContext {
		public FunctionBodyContext functionBody() {
			return getRuleContext(FunctionBodyContext.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public FunctionDefParamsContext functionDefParams() {
			return getRuleContext(FunctionDefParamsContext.class,0);
		}
		public FunctionDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DemoLangV001Listener ) ((DemoLangV001Listener)listener).enterFunctionDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DemoLangV001Listener ) ((DemoLangV001Listener)listener).exitFunctionDef(this);
		}
	}

	public final FunctionDefContext functionDef() throws RecognitionException {
		FunctionDefContext _localctx = new FunctionDefContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_functionDef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(75); match(T_FUNCTION);
			setState(76); identifier();
			setState(77); match(T_PARENLEFT);
			setState(79);
			_la = _input.LA(1);
			if (_la==ID) {
				{
				setState(78); functionDefParams();
				}
			}

			setState(81); match(T_PARENRIGHT);
			setState(82); functionBody();
			setState(83); match(T_END);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionDefParamsContext extends ParserRuleContext {
		public FunctionDefParamContext functionDefParam(int i) {
			return getRuleContext(FunctionDefParamContext.class,i);
		}
		public List<FunctionDefParamContext> functionDefParam() {
			return getRuleContexts(FunctionDefParamContext.class);
		}
		public FunctionDefParamsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionDefParams; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DemoLangV001Listener ) ((DemoLangV001Listener)listener).enterFunctionDefParams(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DemoLangV001Listener ) ((DemoLangV001Listener)listener).exitFunctionDefParams(this);
		}
	}

	public final FunctionDefParamsContext functionDefParams() throws RecognitionException {
		FunctionDefParamsContext _localctx = new FunctionDefParamsContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_functionDefParams);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(85); functionDefParam();
			setState(89);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ID) {
				{
				{
				setState(86); functionDefParam();
				}
				}
				setState(91);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionDefParamContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(DemoLangV001Parser.ID, 0); }
		public FunctionDefParamContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionDefParam; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DemoLangV001Listener ) ((DemoLangV001Listener)listener).enterFunctionDefParam(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DemoLangV001Listener ) ((DemoLangV001Listener)listener).exitFunctionDefParam(this);
		}
	}

	public final FunctionDefParamContext functionDefParam() throws RecognitionException {
		FunctionDefParamContext _localctx = new FunctionDefParamContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_functionDefParam);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(92); match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionBodyContext extends ParserRuleContext {
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public FunctionBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionBody; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DemoLangV001Listener ) ((DemoLangV001Listener)listener).enterFunctionBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DemoLangV001Listener ) ((DemoLangV001Listener)listener).exitFunctionBody(this);
		}
	}

	public final FunctionBodyContext functionBody() throws RecognitionException {
		FunctionBodyContext _localctx = new FunctionBodyContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_functionBody);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(94); block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BlockContext extends ParserRuleContext {
		public StmtContext stmt(int i) {
			return getRuleContext(StmtContext.class,i);
		}
		public List<StmtContext> stmt() {
			return getRuleContexts(StmtContext.class);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DemoLangV001Listener ) ((DemoLangV001Listener)listener).enterBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DemoLangV001Listener ) ((DemoLangV001Listener)listener).exitBlock(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(97); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(96); stmt();
				}
				}
				setState(99); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << SFN_ONEPLUS) | (1L << SFN_ONEMINUS) | (1L << SFN_PLUS) | (1L << SFN_MINUS) | (1L << SFN_MULTIPLY) | (1L << SFN_DIVIDE) | (1L << SFN_LESSTHAN) | (1L << SFN_GREATERTHAN) | (1L << SFN_EQUAL) | (1L << SFN_NEGATE) | (1L << T_BREAK) | (1L << T_CONTINUE) | (1L << T_IF) | (1L << T_RETURN) | (1L << T_VAR) | (1L << T_WHILE) | (1L << ID))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprContext extends ParserRuleContext {
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
	 
		public ExprContext() { }
		public void copyFrom(ExprContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ExprIndexingContext extends ExprContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public ExprIndexingContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DemoLangV001Listener ) ((DemoLangV001Listener)listener).enterExprIndexing(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DemoLangV001Listener ) ((DemoLangV001Listener)listener).exitExprIndexing(this);
		}
	}
	public static class ExprIdContext extends ExprContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public ExprIdContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DemoLangV001Listener ) ((DemoLangV001Listener)listener).enterExprId(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DemoLangV001Listener ) ((DemoLangV001Listener)listener).exitExprId(this);
		}
	}
	public static class ExprFcallContext extends ExprContext {
		public FunctionCallContext functionCall() {
			return getRuleContext(FunctionCallContext.class,0);
		}
		public ExprFcallContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DemoLangV001Listener ) ((DemoLangV001Listener)listener).enterExprFcall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DemoLangV001Listener ) ((DemoLangV001Listener)listener).exitExprFcall(this);
		}
	}
	public static class ExprProprefContext extends ExprContext {
		public IdentifierContext identifier(int i) {
			return getRuleContext(IdentifierContext.class,i);
		}
		public List<IdentifierContext> identifier() {
			return getRuleContexts(IdentifierContext.class);
		}
		public ExprProprefContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DemoLangV001Listener ) ((DemoLangV001Listener)listener).enterExprPropref(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DemoLangV001Listener ) ((DemoLangV001Listener)listener).exitExprPropref(this);
		}
	}
	public static class ExprLiteralContext extends ExprContext {
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public ExprLiteralContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DemoLangV001Listener ) ((DemoLangV001Listener)listener).enterExprLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DemoLangV001Listener ) ((DemoLangV001Listener)listener).exitExprLiteral(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_expr);
		try {
			setState(113);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				_localctx = new ExprFcallContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(101); functionCall();
				}
				break;

			case 2:
				_localctx = new ExprIndexingContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(102); identifier();
				setState(103); match(T_BRACKETLEFT);
				setState(104); expr();
				setState(105); match(T_BRACKETRIGHT);
				}
				break;

			case 3:
				_localctx = new ExprProprefContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(107); identifier();
				setState(108); match(T_DOT);
				setState(109); identifier();
				}
				break;

			case 4:
				_localctx = new ExprIdContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(111); identifier();
				}
				break;

			case 5:
				_localctx = new ExprLiteralContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(112); literal();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IdentifierContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(DemoLangV001Parser.ID, 0); }
		public IdentifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_identifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DemoLangV001Listener ) ((DemoLangV001Listener)listener).enterIdentifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DemoLangV001Listener ) ((DemoLangV001Listener)listener).exitIdentifier(this);
		}
	}

	public final IdentifierContext identifier() throws RecognitionException {
		IdentifierContext _localctx = new IdentifierContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_identifier);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(115); match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LiteralContext extends ParserRuleContext {
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
	 
		public LiteralContext() { }
		public void copyFrom(LiteralContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class StringLiteralContext extends LiteralContext {
		public TerminalNode STRING() { return getToken(DemoLangV001Parser.STRING, 0); }
		public StringLiteralContext(LiteralContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DemoLangV001Listener ) ((DemoLangV001Listener)listener).enterStringLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DemoLangV001Listener ) ((DemoLangV001Listener)listener).exitStringLiteral(this);
		}
	}
	public static class ObjectLiteralContext extends LiteralContext {
		public ObjectContext object() {
			return getRuleContext(ObjectContext.class,0);
		}
		public ObjectLiteralContext(LiteralContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DemoLangV001Listener ) ((DemoLangV001Listener)listener).enterObjectLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DemoLangV001Listener ) ((DemoLangV001Listener)listener).exitObjectLiteral(this);
		}
	}
	public static class ArrayLiteralContext extends LiteralContext {
		public ArrayContext array() {
			return getRuleContext(ArrayContext.class,0);
		}
		public ArrayLiteralContext(LiteralContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DemoLangV001Listener ) ((DemoLangV001Listener)listener).enterArrayLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DemoLangV001Listener ) ((DemoLangV001Listener)listener).exitArrayLiteral(this);
		}
	}
	public static class NullLiteralContext extends LiteralContext {
		public NullLiteralContext(LiteralContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DemoLangV001Listener ) ((DemoLangV001Listener)listener).enterNullLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DemoLangV001Listener ) ((DemoLangV001Listener)listener).exitNullLiteral(this);
		}
	}
	public static class NumberLiteralContext extends LiteralContext {
		public TerminalNode NUMBER() { return getToken(DemoLangV001Parser.NUMBER, 0); }
		public NumberLiteralContext(LiteralContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DemoLangV001Listener ) ((DemoLangV001Listener)listener).enterNumberLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DemoLangV001Listener ) ((DemoLangV001Listener)listener).exitNumberLiteral(this);
		}
	}
	public static class BooleanLiteralContext extends LiteralContext {
		public BooleanValueContext booleanValue() {
			return getRuleContext(BooleanValueContext.class,0);
		}
		public BooleanLiteralContext(LiteralContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DemoLangV001Listener ) ((DemoLangV001Listener)listener).enterBooleanLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DemoLangV001Listener ) ((DemoLangV001Listener)listener).exitBooleanLiteral(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_literal);
		try {
			setState(123);
			switch (_input.LA(1)) {
			case STRING:
				_localctx = new StringLiteralContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(117); match(STRING);
				}
				break;
			case NUMBER:
				_localctx = new NumberLiteralContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(118); match(NUMBER);
				}
				break;
			case T_BRACELEFT:
				_localctx = new ObjectLiteralContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(119); object();
				}
				break;
			case T_BRACKETLEFT:
				_localctx = new ArrayLiteralContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(120); array();
				}
				break;
			case T_FALSE:
			case T_TRUE:
				_localctx = new BooleanLiteralContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(121); booleanValue();
				}
				break;
			case T_NULL:
				_localctx = new NullLiteralContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(122); match(T_NULL);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionCallContext extends ParserRuleContext {
		public FunctionCallParamsContext functionCallParams() {
			return getRuleContext(FunctionCallParamsContext.class,0);
		}
		public FunctionIdentifierContext functionIdentifier() {
			return getRuleContext(FunctionIdentifierContext.class,0);
		}
		public FunctionCallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionCall; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DemoLangV001Listener ) ((DemoLangV001Listener)listener).enterFunctionCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DemoLangV001Listener ) ((DemoLangV001Listener)listener).exitFunctionCall(this);
		}
	}

	public final FunctionCallContext functionCall() throws RecognitionException {
		FunctionCallContext _localctx = new FunctionCallContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_functionCall);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(125); functionIdentifier();
			setState(126); match(T_PARENLEFT);
			setState(128);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << SFN_ONEPLUS) | (1L << SFN_ONEMINUS) | (1L << SFN_PLUS) | (1L << SFN_MINUS) | (1L << SFN_MULTIPLY) | (1L << SFN_DIVIDE) | (1L << SFN_LESSTHAN) | (1L << SFN_GREATERTHAN) | (1L << SFN_EQUAL) | (1L << SFN_NEGATE) | (1L << T_BRACELEFT) | (1L << T_BRACKETLEFT) | (1L << T_FALSE) | (1L << T_NULL) | (1L << T_TRUE) | (1L << ID) | (1L << NUMBER) | (1L << STRING))) != 0)) {
				{
				setState(127); functionCallParams();
				}
			}

			setState(130); match(T_PARENRIGHT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionIdentifierContext extends ParserRuleContext {
		public FunctionIdentifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionIdentifier; }
	 
		public FunctionIdentifierContext() { }
		public void copyFrom(FunctionIdentifierContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class RegularFIDContext extends FunctionIdentifierContext {
		public TerminalNode ID() { return getToken(DemoLangV001Parser.ID, 0); }
		public RegularFIDContext(FunctionIdentifierContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DemoLangV001Listener ) ((DemoLangV001Listener)listener).enterRegularFID(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DemoLangV001Listener ) ((DemoLangV001Listener)listener).exitRegularFID(this);
		}
	}
	public static class SpecialFIDContext extends FunctionIdentifierContext {
		public SpecialFIDContext(FunctionIdentifierContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DemoLangV001Listener ) ((DemoLangV001Listener)listener).enterSpecialFID(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DemoLangV001Listener ) ((DemoLangV001Listener)listener).exitSpecialFID(this);
		}
	}

	public final FunctionIdentifierContext functionIdentifier() throws RecognitionException {
		FunctionIdentifierContext _localctx = new FunctionIdentifierContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_functionIdentifier);
		try {
			setState(143);
			switch (_input.LA(1)) {
			case SFN_ONEPLUS:
				_localctx = new SpecialFIDContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(132); match(SFN_ONEPLUS);
				}
				break;
			case SFN_ONEMINUS:
				_localctx = new SpecialFIDContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(133); match(SFN_ONEMINUS);
				}
				break;
			case SFN_PLUS:
				_localctx = new SpecialFIDContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(134); match(SFN_PLUS);
				}
				break;
			case SFN_MINUS:
				_localctx = new SpecialFIDContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(135); match(SFN_MINUS);
				}
				break;
			case SFN_MULTIPLY:
				_localctx = new SpecialFIDContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(136); match(SFN_MULTIPLY);
				}
				break;
			case SFN_DIVIDE:
				_localctx = new SpecialFIDContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(137); match(SFN_DIVIDE);
				}
				break;
			case SFN_LESSTHAN:
				_localctx = new SpecialFIDContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(138); match(SFN_LESSTHAN);
				}
				break;
			case SFN_GREATERTHAN:
				_localctx = new SpecialFIDContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(139); match(SFN_GREATERTHAN);
				}
				break;
			case SFN_EQUAL:
				_localctx = new SpecialFIDContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(140); match(SFN_EQUAL);
				}
				break;
			case SFN_NEGATE:
				_localctx = new SpecialFIDContext(_localctx);
				enterOuterAlt(_localctx, 10);
				{
				setState(141); match(SFN_NEGATE);
				}
				break;
			case ID:
				_localctx = new RegularFIDContext(_localctx);
				enterOuterAlt(_localctx, 11);
				{
				setState(142); match(ID);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionCallParamsContext extends ParserRuleContext {
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public FunctionCallParamsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionCallParams; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DemoLangV001Listener ) ((DemoLangV001Listener)listener).enterFunctionCallParams(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DemoLangV001Listener ) ((DemoLangV001Listener)listener).exitFunctionCallParams(this);
		}
	}

	public final FunctionCallParamsContext functionCallParams() throws RecognitionException {
		FunctionCallParamsContext _localctx = new FunctionCallParamsContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_functionCallParams);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(145); expr();
			setState(149);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << SFN_ONEPLUS) | (1L << SFN_ONEMINUS) | (1L << SFN_PLUS) | (1L << SFN_MINUS) | (1L << SFN_MULTIPLY) | (1L << SFN_DIVIDE) | (1L << SFN_LESSTHAN) | (1L << SFN_GREATERTHAN) | (1L << SFN_EQUAL) | (1L << SFN_NEGATE) | (1L << T_BRACELEFT) | (1L << T_BRACKETLEFT) | (1L << T_FALSE) | (1L << T_NULL) | (1L << T_TRUE) | (1L << ID) | (1L << NUMBER) | (1L << STRING))) != 0)) {
				{
				{
				setState(146); expr();
				}
				}
				setState(151);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ObjectContext extends ParserRuleContext {
		public List<PairContext> pair() {
			return getRuleContexts(PairContext.class);
		}
		public PairContext pair(int i) {
			return getRuleContext(PairContext.class,i);
		}
		public ObjectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_object; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DemoLangV001Listener ) ((DemoLangV001Listener)listener).enterObject(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DemoLangV001Listener ) ((DemoLangV001Listener)listener).exitObject(this);
		}
	}

	public final ObjectContext object() throws RecognitionException {
		ObjectContext _localctx = new ObjectContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_object);
		int _la;
		try {
			setState(165);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(152); match(T_BRACELEFT);
				setState(153); pair();
				setState(158);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T_COMMA) {
					{
					{
					setState(154); match(T_COMMA);
					setState(155); pair();
					}
					}
					setState(160);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(161); match(T_BRACERIGHT);
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(163); match(T_BRACELEFT);
				setState(164); match(T_BRACERIGHT);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArrayContext extends ParserRuleContext {
		public LiteralContext literal(int i) {
			return getRuleContext(LiteralContext.class,i);
		}
		public List<LiteralContext> literal() {
			return getRuleContexts(LiteralContext.class);
		}
		public ArrayContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_array; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DemoLangV001Listener ) ((DemoLangV001Listener)listener).enterArray(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DemoLangV001Listener ) ((DemoLangV001Listener)listener).exitArray(this);
		}
	}

	public final ArrayContext array() throws RecognitionException {
		ArrayContext _localctx = new ArrayContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_array);
		int _la;
		try {
			setState(179);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(167); match(T_BRACKETLEFT);
				setState(168); literal();
				setState(172);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T_BRACELEFT) | (1L << T_BRACKETLEFT) | (1L << T_FALSE) | (1L << T_NULL) | (1L << T_TRUE) | (1L << NUMBER) | (1L << STRING))) != 0)) {
					{
					{
					setState(169); literal();
					}
					}
					setState(174);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(175); match(T_BRACKETRIGHT);
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(177); match(T_BRACKETLEFT);
				setState(178); match(T_BRACKETRIGHT);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PairContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public PairContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pair; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DemoLangV001Listener ) ((DemoLangV001Listener)listener).enterPair(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DemoLangV001Listener ) ((DemoLangV001Listener)listener).exitPair(this);
		}
	}

	public final PairContext pair() throws RecognitionException {
		PairContext _localctx = new PairContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_pair);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(181); identifier();
			setState(182); match(T_COLON);
			setState(183); expr();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BooleanValueContext extends ParserRuleContext {
		public BooleanValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_booleanValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DemoLangV001Listener ) ((DemoLangV001Listener)listener).enterBooleanValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DemoLangV001Listener ) ((DemoLangV001Listener)listener).exitBooleanValue(this);
		}
	}

	public final BooleanValueContext booleanValue() throws RecognitionException {
		BooleanValueContext _localctx = new BooleanValueContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_booleanValue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(185);
			_la = _input.LA(1);
			if ( !(_la==T_FALSE || _la==T_TRUE) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StmtContext extends ParserRuleContext {
		public StmtAssignContext stmtAssign() {
			return getRuleContext(StmtAssignContext.class,0);
		}
		public FunctionCallContext functionCall() {
			return getRuleContext(FunctionCallContext.class,0);
		}
		public StmtContinueContext stmtContinue() {
			return getRuleContext(StmtContinueContext.class,0);
		}
		public StmtVarDefContext stmtVarDef() {
			return getRuleContext(StmtVarDefContext.class,0);
		}
		public StmtBreakContext stmtBreak() {
			return getRuleContext(StmtBreakContext.class,0);
		}
		public StmtWhileContext stmtWhile() {
			return getRuleContext(StmtWhileContext.class,0);
		}
		public StmtReturnContext stmtReturn() {
			return getRuleContext(StmtReturnContext.class,0);
		}
		public StmtIfContext stmtIf() {
			return getRuleContext(StmtIfContext.class,0);
		}
		public StmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DemoLangV001Listener ) ((DemoLangV001Listener)listener).enterStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DemoLangV001Listener ) ((DemoLangV001Listener)listener).exitStmt(this);
		}
	}

	public final StmtContext stmt() throws RecognitionException {
		StmtContext _localctx = new StmtContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_stmt);
		try {
			setState(195);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(187); stmtReturn();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(188); stmtContinue();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(189); stmtBreak();
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(190); stmtVarDef();
				}
				break;

			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(191); stmtAssign();
				}
				break;

			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(192); stmtIf();
				}
				break;

			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(193); stmtWhile();
				}
				break;

			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(194); functionCall();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StmtVarDefContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public StmtVarDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stmtVarDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DemoLangV001Listener ) ((DemoLangV001Listener)listener).enterStmtVarDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DemoLangV001Listener ) ((DemoLangV001Listener)listener).exitStmtVarDef(this);
		}
	}

	public final StmtVarDefContext stmtVarDef() throws RecognitionException {
		StmtVarDefContext _localctx = new StmtVarDefContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_stmtVarDef);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(197); match(T_VAR);
			setState(198); identifier();
			setState(199); match(T_ARROWLEFT);
			setState(200); expr();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StmtAssignContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public StmtAssignContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stmtAssign; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DemoLangV001Listener ) ((DemoLangV001Listener)listener).enterStmtAssign(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DemoLangV001Listener ) ((DemoLangV001Listener)listener).exitStmtAssign(this);
		}
	}

	public final StmtAssignContext stmtAssign() throws RecognitionException {
		StmtAssignContext _localctx = new StmtAssignContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_stmtAssign);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(202); identifier();
			setState(203); match(T_ARROWLEFT);
			setState(204); expr();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StmtContinueContext extends ParserRuleContext {
		public StmtContinueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stmtContinue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DemoLangV001Listener ) ((DemoLangV001Listener)listener).enterStmtContinue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DemoLangV001Listener ) ((DemoLangV001Listener)listener).exitStmtContinue(this);
		}
	}

	public final StmtContinueContext stmtContinue() throws RecognitionException {
		StmtContinueContext _localctx = new StmtContinueContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_stmtContinue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(206); match(T_CONTINUE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StmtBreakContext extends ParserRuleContext {
		public StmtBreakContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stmtBreak; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DemoLangV001Listener ) ((DemoLangV001Listener)listener).enterStmtBreak(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DemoLangV001Listener ) ((DemoLangV001Listener)listener).exitStmtBreak(this);
		}
	}

	public final StmtBreakContext stmtBreak() throws RecognitionException {
		StmtBreakContext _localctx = new StmtBreakContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_stmtBreak);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(208); match(T_BREAK);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StmtReturnContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public StmtReturnContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stmtReturn; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DemoLangV001Listener ) ((DemoLangV001Listener)listener).enterStmtReturn(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DemoLangV001Listener ) ((DemoLangV001Listener)listener).exitStmtReturn(this);
		}
	}

	public final StmtReturnContext stmtReturn() throws RecognitionException {
		StmtReturnContext _localctx = new StmtReturnContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_stmtReturn);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(210); match(T_RETURN);
			setState(211); expr();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StmtIfContext extends ParserRuleContext {
		public BlockContext block(int i) {
			return getRuleContext(BlockContext.class,i);
		}
		public List<BlockContext> block() {
			return getRuleContexts(BlockContext.class);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public StmtIfContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stmtIf; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DemoLangV001Listener ) ((DemoLangV001Listener)listener).enterStmtIf(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DemoLangV001Listener ) ((DemoLangV001Listener)listener).exitStmtIf(this);
		}
	}

	public final StmtIfContext stmtIf() throws RecognitionException {
		StmtIfContext _localctx = new StmtIfContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_stmtIf);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(213); match(T_IF);
			setState(214); expr();
			setState(215); match(T_THEN);
			setState(216); block();
			setState(219);
			_la = _input.LA(1);
			if (_la==T_ELSE) {
				{
				setState(217); match(T_ELSE);
				setState(218); block();
				}
			}

			setState(221); match(T_END);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StmtWhileContext extends ParserRuleContext {
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public StmtWhileContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stmtWhile; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DemoLangV001Listener ) ((DemoLangV001Listener)listener).enterStmtWhile(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DemoLangV001Listener ) ((DemoLangV001Listener)listener).exitStmtWhile(this);
		}
	}

	public final StmtWhileContext stmtWhile() throws RecognitionException {
		StmtWhileContext _localctx = new StmtWhileContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_stmtWhile);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(223); match(T_WHILE);
			setState(224); expr();
			setState(225); match(T_DO);
			setState(226); block();
			setState(227); match(T_END);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\2\3/\u00e8\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4"+
		"\t\t\t\4\n\t\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20"+
		"\4\21\t\21\4\22\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27"+
		"\4\30\t\30\4\31\t\31\4\32\t\32\4\33\t\33\4\34\t\34\3\2\5\2:\n\2\3\2\3"+
		"\2\3\2\3\3\3\3\3\3\3\3\3\4\3\4\6\4E\n\4\r\4\16\4F\3\5\3\5\3\5\3\5\3\5"+
		"\3\6\3\6\3\6\3\6\5\6R\n\6\3\6\3\6\3\6\3\6\3\7\3\7\7\7Z\n\7\f\7\16\7]\13"+
		"\7\3\b\3\b\3\t\3\t\3\n\6\nd\n\n\r\n\16\ne\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\5\13t\n\13\3\f\3\f\3\r\3\r\3\r\3\r\3\r"+
		"\3\r\5\r~\n\r\3\16\3\16\3\16\5\16\u0083\n\16\3\16\3\16\3\17\3\17\3\17"+
		"\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\5\17\u0092\n\17\3\20\3\20\7\20"+
		"\u0096\n\20\f\20\16\20\u0099\13\20\3\21\3\21\3\21\3\21\7\21\u009f\n\21"+
		"\f\21\16\21\u00a2\13\21\3\21\3\21\3\21\3\21\5\21\u00a8\n\21\3\22\3\22"+
		"\3\22\7\22\u00ad\n\22\f\22\16\22\u00b0\13\22\3\22\3\22\3\22\3\22\5\22"+
		"\u00b6\n\22\3\23\3\23\3\23\3\23\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25"+
		"\3\25\3\25\5\25\u00c6\n\25\3\26\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27"+
		"\3\30\3\30\3\31\3\31\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\33\3\33\5\33"+
		"\u00de\n\33\3\33\3\33\3\34\3\34\3\34\3\34\3\34\3\34\3\34\2\35\2\4\6\b"+
		"\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\66\2\3\4\33\33((\u00f3"+
		"\29\3\2\2\2\4>\3\2\2\2\6D\3\2\2\2\bH\3\2\2\2\nM\3\2\2\2\fW\3\2\2\2\16"+
		"^\3\2\2\2\20`\3\2\2\2\22c\3\2\2\2\24s\3\2\2\2\26u\3\2\2\2\30}\3\2\2\2"+
		"\32\177\3\2\2\2\34\u0091\3\2\2\2\36\u0093\3\2\2\2 \u00a7\3\2\2\2\"\u00b5"+
		"\3\2\2\2$\u00b7\3\2\2\2&\u00bb\3\2\2\2(\u00c5\3\2\2\2*\u00c7\3\2\2\2,"+
		"\u00cc\3\2\2\2.\u00d0\3\2\2\2\60\u00d2\3\2\2\2\62\u00d4\3\2\2\2\64\u00d7"+
		"\3\2\2\2\66\u00e1\3\2\2\28:\5\6\4\298\3\2\2\29:\3\2\2\2:;\3\2\2\2;<\5"+
		"\4\3\2<=\7\1\2\2=\3\3\2\2\2>?\7 \2\2?@\5\22\n\2@A\7\32\2\2A\5\3\2\2\2"+
		"BE\5\b\5\2CE\5\n\6\2DB\3\2\2\2DC\3\2\2\2EF\3\2\2\2FD\3\2\2\2FG\3\2\2\2"+
		"G\7\3\2\2\2HI\7\36\2\2IJ\5\26\f\2JK\7\r\2\2KL\5\24\13\2L\t\3\2\2\2MN\7"+
		"\34\2\2NO\5\26\f\2OQ\7#\2\2PR\5\f\7\2QP\3\2\2\2QR\3\2\2\2RS\3\2\2\2ST"+
		"\7$\2\2TU\5\20\t\2UV\7\32\2\2V\13\3\2\2\2W[\5\16\b\2XZ\5\16\b\2YX\3\2"+
		"\2\2Z]\3\2\2\2[Y\3\2\2\2[\\\3\2\2\2\\\r\3\2\2\2][\3\2\2\2^_\7,\2\2_\17"+
		"\3\2\2\2`a\5\22\n\2a\21\3\2\2\2bd\5(\25\2cb\3\2\2\2de\3\2\2\2ec\3\2\2"+
		"\2ef\3\2\2\2f\23\3\2\2\2gt\5\32\16\2hi\5\26\f\2ij\7\21\2\2jk\5\24\13\2"+
		"kl\7\22\2\2lt\3\2\2\2mn\5\26\f\2no\7\30\2\2op\5\26\f\2pt\3\2\2\2qt\5\26"+
		"\f\2rt\5\30\r\2sg\3\2\2\2sh\3\2\2\2sm\3\2\2\2sq\3\2\2\2sr\3\2\2\2t\25"+
		"\3\2\2\2uv\7,\2\2v\27\3\2\2\2w~\7.\2\2x~\7-\2\2y~\5 \21\2z~\5\"\22\2{"+
		"~\5&\24\2|~\7\"\2\2}w\3\2\2\2}x\3\2\2\2}y\3\2\2\2}z\3\2\2\2}{\3\2\2\2"+
		"}|\3\2\2\2~\31\3\2\2\2\177\u0080\5\34\17\2\u0080\u0082\7#\2\2\u0081\u0083"+
		"\5\36\20\2\u0082\u0081\3\2\2\2\u0082\u0083\3\2\2\2\u0083\u0084\3\2\2\2"+
		"\u0084\u0085\7$\2\2\u0085\33\3\2\2\2\u0086\u0092\7\3\2\2\u0087\u0092\7"+
		"\4\2\2\u0088\u0092\7\5\2\2\u0089\u0092\7\6\2\2\u008a\u0092\7\7\2\2\u008b"+
		"\u0092\7\b\2\2\u008c\u0092\7\t\2\2\u008d\u0092\7\n\2\2\u008e\u0092\7\13"+
		"\2\2\u008f\u0092\7\f\2\2\u0090\u0092\7,\2\2\u0091\u0086\3\2\2\2\u0091"+
		"\u0087\3\2\2\2\u0091\u0088\3\2\2\2\u0091\u0089\3\2\2\2\u0091\u008a\3\2"+
		"\2\2\u0091\u008b\3\2\2\2\u0091\u008c\3\2\2\2\u0091\u008d\3\2\2\2\u0091"+
		"\u008e\3\2\2\2\u0091\u008f\3\2\2\2\u0091\u0090\3\2\2\2\u0092\35\3\2\2"+
		"\2\u0093\u0097\5\24\13\2\u0094\u0096\5\24\13\2\u0095\u0094\3\2\2\2\u0096"+
		"\u0099\3\2\2\2\u0097\u0095\3\2\2\2\u0097\u0098\3\2\2\2\u0098\37\3\2\2"+
		"\2\u0099\u0097\3\2\2\2\u009a\u009b\7\17\2\2\u009b\u00a0\5$\23\2\u009c"+
		"\u009d\7\25\2\2\u009d\u009f\5$\23\2\u009e\u009c\3\2\2\2\u009f\u00a2\3"+
		"\2\2\2\u00a0\u009e\3\2\2\2\u00a0\u00a1\3\2\2\2\u00a1\u00a3\3\2\2\2\u00a2"+
		"\u00a0\3\2\2\2\u00a3\u00a4\7\20\2\2\u00a4\u00a8\3\2\2\2\u00a5\u00a6\7"+
		"\17\2\2\u00a6\u00a8\7\20\2\2\u00a7\u009a\3\2\2\2\u00a7\u00a5\3\2\2\2\u00a8"+
		"!\3\2\2\2\u00a9\u00aa\7\21\2\2\u00aa\u00ae\5\30\r\2\u00ab\u00ad\5\30\r"+
		"\2\u00ac\u00ab\3\2\2\2\u00ad\u00b0\3\2\2\2\u00ae\u00ac\3\2\2\2\u00ae\u00af"+
		"\3\2\2\2\u00af\u00b1\3\2\2\2\u00b0\u00ae\3\2\2\2\u00b1\u00b2\7\22\2\2"+
		"\u00b2\u00b6\3\2\2\2\u00b3\u00b4\7\21\2\2\u00b4\u00b6\7\22\2\2\u00b5\u00a9"+
		"\3\2\2\2\u00b5\u00b3\3\2\2\2\u00b6#\3\2\2\2\u00b7\u00b8\5\26\f\2\u00b8"+
		"\u00b9\7\24\2\2\u00b9\u00ba\5\24\13\2\u00ba%\3\2\2\2\u00bb\u00bc\t\2\2"+
		"\2\u00bc\'\3\2\2\2\u00bd\u00c6\5\62\32\2\u00be\u00c6\5.\30\2\u00bf\u00c6"+
		"\5\60\31\2\u00c0\u00c6\5*\26\2\u00c1\u00c6\5,\27\2\u00c2\u00c6\5\64\33"+
		"\2\u00c3\u00c6\5\66\34\2\u00c4\u00c6\5\32\16\2\u00c5\u00bd\3\2\2\2\u00c5"+
		"\u00be\3\2\2\2\u00c5\u00bf\3\2\2\2\u00c5\u00c0\3\2\2\2\u00c5\u00c1\3\2"+
		"\2\2\u00c5\u00c2\3\2\2\2\u00c5\u00c3\3\2\2\2\u00c5\u00c4\3\2\2\2\u00c6"+
		")\3\2\2\2\u00c7\u00c8\7*\2\2\u00c8\u00c9\5\26\f\2\u00c9\u00ca\7\r\2\2"+
		"\u00ca\u00cb\5\24\13\2\u00cb+\3\2\2\2\u00cc\u00cd\5\26\f\2\u00cd\u00ce"+
		"\7\r\2\2\u00ce\u00cf\5\24\13\2\u00cf-\3\2\2\2\u00d0\u00d1\7\26\2\2\u00d1"+
		"/\3\2\2\2\u00d2\u00d3\7\23\2\2\u00d3\61\3\2\2\2\u00d4\u00d5\7&\2\2\u00d5"+
		"\u00d6\5\24\13\2\u00d6\63\3\2\2\2\u00d7\u00d8\7\37\2\2\u00d8\u00d9\5\24"+
		"\13\2\u00d9\u00da\7\'\2\2\u00da\u00dd\5\22\n\2\u00db\u00dc\7\31\2\2\u00dc"+
		"\u00de\5\22\n\2\u00dd\u00db\3\2\2\2\u00dd\u00de\3\2\2\2\u00de\u00df\3"+
		"\2\2\2\u00df\u00e0\7\32\2\2\u00e0\65\3\2\2\2\u00e1\u00e2\7+\2\2\u00e2"+
		"\u00e3\5\24\13\2\u00e3\u00e4\7\27\2\2\u00e4\u00e5\5\22\n\2\u00e5\u00e6"+
		"\7\32\2\2\u00e6\67\3\2\2\2\239DFQ[es}\u0082\u0091\u0097\u00a0\u00a7\u00ae"+
		"\u00b5\u00c5\u00dd";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
	}
}