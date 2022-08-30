// Generated from Elang.g4 by ANTLR 4.0
package fi.abo.kogni.soile2.elang;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ElangParser extends Parser {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		DOCQUOTE=1, SFN_ONEPLUS=2, SFN_ONEMINUS=3, SFN_PLUS=4, SFN_MINUS=5, SFN_MULTIPLY=6, 
		SFN_DIVIDE=7, SFN_LESSTHAN=8, SFN_GREATERTHAN=9, SFN_EQUAL=10, SFN_NEGATE=11, 
		T_TRANNAMEREPEAT=12, T_ARROWLEFT=13, T_ARROWRIGHT=14, T_BRACELEFT=15, 
		T_BRACERIGHT=16, T_BRACKETLEFT=17, T_BRACKETRIGHT=18, T_BREAK=19, T_COLON=20, 
		T_COMMA=21, T_CONTINUE=22, T_DO=23, T_DOT=24, T_ELSE=25, T_END=26, T_FALSE=27, 
		T_FUNCTION=28, T_FVAR=29, T_GVAR=30, T_HASH=31, T_IF=32, T_INITTRANS=33, 
		T_ITERATION=34, T_NULL=35, T_PARENLEFT=36, T_PARENRIGHT=37, T_QMARK=38, 
		T_QUOTE=39, T_RETURN=40, T_THEN=41, T_TRANSITION=42, T_TRUE=43, T_VAL=44, 
		T_VAR=45, T_WHILE=46, ID=47, NUMBER=48, STRING=49, COMMENT=50, WS=51, 
		NL=52;
	public static final String[] tokenNames = {
		"<INVALID>", "DOCQUOTE", "'1+'", "'1-'", "'+'", "'-'", "'*'", "'/'", "'<'", 
		"'>'", "'='", "'!'", "'-\"-'", "'<-'", "'->'", "'{'", "'}'", "'['", "']'", 
		"'break'", "':'", "','", "'continue'", "'do'", "'.'", "'else'", "'end'", 
		"'false'", "'function'", "'fvar'", "'gvar'", "'#'", "'if'", "'|->'", "'iteration'", 
		"'null'", "'('", "')'", "'?'", "'\"'", "'return'", "'then'", "'transition'", 
		"'true'", "'val'", "'var'", "'while'", "ID", "NUMBER", "STRING", "COMMENT", 
		"WS", "NL"
	};
	public static final int
		RULE_file = 0, RULE_toplevelStmt = 1, RULE_gvarDef = 2, RULE_valDef = 3, 
		RULE_functionDef = 4, RULE_functionDefParams = 5, RULE_functionDefParam = 6, 
		RULE_functionBody = 7, RULE_fvarDefs = 8, RULE_fvarDef = 9, RULE_varDefs = 10, 
		RULE_varDef = 11, RULE_block = 12, RULE_expr = 13, RULE_identifier = 14, 
		RULE_literal = 15, RULE_functionCall = 16, RULE_functionIdentifier = 17, 
		RULE_functionCallParams = 18, RULE_object = 19, RULE_array = 20, RULE_pair = 21, 
		RULE_stmt = 22, RULE_stmtAssign = 23, RULE_stmtContinue = 24, RULE_stmtBreak = 25, 
		RULE_stmtReturn = 26, RULE_stmtIf = 27, RULE_stmtWhile = 28, RULE_iteration = 29, 
		RULE_iterationBody = 30, RULE_number = 31, RULE_string = 32;
	public static final String[] ruleNames = {
		"file", "toplevelStmt", "gvarDef", "valDef", "functionDef", "functionDefParams", 
		"functionDefParam", "functionBody", "fvarDefs", "fvarDef", "varDefs", 
		"varDef", "block", "expr", "identifier", "literal", "functionCall", "functionIdentifier", 
		"functionCallParams", "object", "array", "pair", "stmt", "stmtAssign", 
		"stmtContinue", "stmtBreak", "stmtReturn", "stmtIf", "stmtWhile", "iteration", 
		"iterationBody", "number", "string"
	};

	@Override
	public String getGrammarFileName() { return "Elang.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public ElangParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class FileContext extends ParserRuleContext {
		public ToplevelStmtContext toplevelStmt(int i) {
			return getRuleContext(ToplevelStmtContext.class,i);
		}
		public TerminalNode EOF() { return getToken(ElangParser.EOF, 0); }
		public List<ToplevelStmtContext> toplevelStmt() {
			return getRuleContexts(ToplevelStmtContext.class);
		}
		public IterationContext iteration() {
			return getRuleContext(IterationContext.class,0);
		}
		public FileContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_file; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).enterFile(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).exitFile(this);
		}
	}

	public final FileContext file() throws RecognitionException {
		FileContext _localctx = new FileContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_file);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(69);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << SFN_ONEPLUS) | (1L << SFN_ONEMINUS) | (1L << SFN_PLUS) | (1L << SFN_MINUS) | (1L << SFN_MULTIPLY) | (1L << SFN_DIVIDE) | (1L << SFN_LESSTHAN) | (1L << SFN_GREATERTHAN) | (1L << SFN_EQUAL) | (1L << SFN_NEGATE) | (1L << T_FUNCTION) | (1L << T_GVAR) | (1L << T_VAL) | (1L << ID))) != 0)) {
				{
				{
				setState(66); toplevelStmt();
				}
				}
				setState(71);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(72); iteration();
			setState(73); match(EOF);
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

	public static class ToplevelStmtContext extends ParserRuleContext {
		public FunctionDefContext functionDef() {
			return getRuleContext(FunctionDefContext.class,0);
		}
		public FunctionCallContext functionCall() {
			return getRuleContext(FunctionCallContext.class,0);
		}
		public GvarDefContext gvarDef() {
			return getRuleContext(GvarDefContext.class,0);
		}
		public ValDefContext valDef() {
			return getRuleContext(ValDefContext.class,0);
		}
		public ToplevelStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_toplevelStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).enterToplevelStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).exitToplevelStmt(this);
		}
	}

	public final ToplevelStmtContext toplevelStmt() throws RecognitionException {
		ToplevelStmtContext _localctx = new ToplevelStmtContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_toplevelStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(79);
			switch (_input.LA(1)) {
			case T_GVAR:
				{
				setState(75); gvarDef();
				}
				break;
			case T_VAL:
				{
				setState(76); valDef();
				}
				break;
			case T_FUNCTION:
				{
				setState(77); functionDef();
				}
				break;
			case SFN_ONEPLUS:
			case SFN_ONEMINUS:
			case SFN_PLUS:
			case SFN_MINUS:
			case SFN_MULTIPLY:
			case SFN_DIVIDE:
			case SFN_LESSTHAN:
			case SFN_GREATERTHAN:
			case SFN_EQUAL:
			case SFN_NEGATE:
			case ID:
				{
				setState(78); functionCall();
				}
				break;
			default:
				throw new NoViableAltException(this);
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
			if ( listener instanceof ElangListener ) ((ElangListener)listener).enterGvarDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).exitGvarDef(this);
		}
	}

	public final GvarDefContext gvarDef() throws RecognitionException {
		GvarDefContext _localctx = new GvarDefContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_gvarDef);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(81); match(T_GVAR);
			setState(82); identifier();
			setState(83); match(T_ARROWLEFT);
			setState(84); expr(0);
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

	public static class ValDefContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public ValDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_valDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).enterValDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).exitValDef(this);
		}
	}

	public final ValDefContext valDef() throws RecognitionException {
		ValDefContext _localctx = new ValDefContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_valDef);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(86); match(T_VAL);
			setState(87); identifier();
			setState(88); match(T_ARROWLEFT);
			setState(89); expr(0);
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
			if ( listener instanceof ElangListener ) ((ElangListener)listener).enterFunctionDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).exitFunctionDef(this);
		}
	}

	public final FunctionDefContext functionDef() throws RecognitionException {
		FunctionDefContext _localctx = new FunctionDefContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_functionDef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(91); match(T_FUNCTION);
			setState(92); identifier();
			setState(93); match(T_PARENLEFT);
			setState(95);
			_la = _input.LA(1);
			if (_la==ID) {
				{
				setState(94); functionDefParams();
				}
			}

			setState(97); match(T_PARENRIGHT);
			setState(98); functionBody();
			setState(99); match(T_END);
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
			if ( listener instanceof ElangListener ) ((ElangListener)listener).enterFunctionDefParams(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).exitFunctionDefParams(this);
		}
	}

	public final FunctionDefParamsContext functionDefParams() throws RecognitionException {
		FunctionDefParamsContext _localctx = new FunctionDefParamsContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_functionDefParams);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(101); functionDefParam();
			setState(105);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ID) {
				{
				{
				setState(102); functionDefParam();
				}
				}
				setState(107);
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
		public TerminalNode ID() { return getToken(ElangParser.ID, 0); }
		public FunctionDefParamContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionDefParam; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).enterFunctionDefParam(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).exitFunctionDefParam(this);
		}
	}

	public final FunctionDefParamContext functionDefParam() throws RecognitionException {
		FunctionDefParamContext _localctx = new FunctionDefParamContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_functionDefParam);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(108); match(ID);
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
		public FvarDefsContext fvarDefs() {
			return getRuleContext(FvarDefsContext.class,0);
		}
		public VarDefsContext varDefs() {
			return getRuleContext(VarDefsContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public FunctionBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionBody; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).enterFunctionBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).exitFunctionBody(this);
		}
	}

	public final FunctionBodyContext functionBody() throws RecognitionException {
		FunctionBodyContext _localctx = new FunctionBodyContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_functionBody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(111);
			_la = _input.LA(1);
			if (_la==T_FVAR) {
				{
				setState(110); fvarDefs();
				}
			}

			setState(114);
			_la = _input.LA(1);
			if (_la==T_VAR) {
				{
				setState(113); varDefs();
				}
			}

			setState(116); block();
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

	public static class FvarDefsContext extends ParserRuleContext {
		public List<FvarDefContext> fvarDef() {
			return getRuleContexts(FvarDefContext.class);
		}
		public FvarDefContext fvarDef(int i) {
			return getRuleContext(FvarDefContext.class,i);
		}
		public FvarDefsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fvarDefs; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).enterFvarDefs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).exitFvarDefs(this);
		}
	}

	public final FvarDefsContext fvarDefs() throws RecognitionException {
		FvarDefsContext _localctx = new FvarDefsContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_fvarDefs);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(119); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(118); fvarDef();
				}
				}
				setState(121); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T_FVAR );
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

	public static class FvarDefContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public FvarDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fvarDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).enterFvarDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).exitFvarDef(this);
		}
	}

	public final FvarDefContext fvarDef() throws RecognitionException {
		FvarDefContext _localctx = new FvarDefContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_fvarDef);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(123); match(T_FVAR);
			setState(124); identifier();
			setState(125); match(T_ARROWLEFT);
			setState(126); expr(0);
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

	public static class VarDefsContext extends ParserRuleContext {
		public VarDefContext varDef(int i) {
			return getRuleContext(VarDefContext.class,i);
		}
		public List<VarDefContext> varDef() {
			return getRuleContexts(VarDefContext.class);
		}
		public VarDefsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varDefs; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).enterVarDefs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).exitVarDefs(this);
		}
	}

	public final VarDefsContext varDefs() throws RecognitionException {
		VarDefsContext _localctx = new VarDefsContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_varDefs);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(129); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(128); varDef();
				}
				}
				setState(131); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T_VAR );
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

	public static class VarDefContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public VarDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).enterVarDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).exitVarDef(this);
		}
	}

	public final VarDefContext varDef() throws RecognitionException {
		VarDefContext _localctx = new VarDefContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_varDef);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(133); match(T_VAR);
			setState(134); identifier();
			setState(135); match(T_ARROWLEFT);
			setState(136); expr(0);
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
			if ( listener instanceof ElangListener ) ((ElangListener)listener).enterBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).exitBlock(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(139); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(138); stmt();
				}
				}
				setState(141); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << SFN_ONEPLUS) | (1L << SFN_ONEMINUS) | (1L << SFN_PLUS) | (1L << SFN_MINUS) | (1L << SFN_MULTIPLY) | (1L << SFN_DIVIDE) | (1L << SFN_LESSTHAN) | (1L << SFN_GREATERTHAN) | (1L << SFN_EQUAL) | (1L << SFN_NEGATE) | (1L << T_BREAK) | (1L << T_CONTINUE) | (1L << T_IF) | (1L << T_RETURN) | (1L << T_WHILE) | (1L << ID))) != 0) );
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
		public int _p;
		public ExprContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public ExprContext(ParserRuleContext parent, int invokingState, int _p) {
			super(parent, invokingState);
			this._p = _p;
		}
		@Override public int getRuleIndex() { return RULE_expr; }
	 
		public ExprContext() { }
		public void copyFrom(ExprContext ctx) {
			super.copyFrom(ctx);
			this._p = ctx._p;
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
			if ( listener instanceof ElangListener ) ((ElangListener)listener).enterExprIndexing(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).exitExprIndexing(this);
		}
	}
	public static class ExprIdContext extends ExprContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public ExprIdContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).enterExprId(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).exitExprId(this);
		}
	}
	public static class ExprFcallContext extends ExprContext {
		public FunctionCallContext functionCall() {
			return getRuleContext(FunctionCallContext.class,0);
		}
		public ExprFcallContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).enterExprFcall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).exitExprFcall(this);
		}
	}
	public static class ExprLiteralContext extends ExprContext {
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public ExprLiteralContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).enterExprLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).exitExprLiteral(this);
		}
	}
	public static class ExprProprefContext extends ExprContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public ExprProprefContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).enterExprPropref(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).exitExprPropref(this);
		}
	}

	public final ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState, _p);
		ExprContext _prevctx = _localctx;
		int _startState = 26;
		enterRecursionRule(_localctx, RULE_expr);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(152);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				{
				_localctx = new ExprFcallContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(144); functionCall();
				}
				break;

			case 2:
				{
				_localctx = new ExprIndexingContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(145); identifier();
				setState(146); match(T_BRACKETLEFT);
				setState(147); expr(0);
				setState(148); match(T_BRACKETRIGHT);
				}
				break;

			case 3:
				{
				_localctx = new ExprIdContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(150); identifier();
				}
				break;

			case 4:
				{
				_localctx = new ExprLiteralContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(151); literal();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(159);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ExprProprefContext(new ExprContext(_parentctx, _parentState, _p));
					pushNewRecursionContext(_localctx, _startState, RULE_expr);
					setState(154);
					if (!(5 >= _localctx._p)) throw new FailedPredicateException(this, "5 >= $_p");
					setState(155); match(T_DOT);
					setState(156); identifier();
					}
					} 
				}
				setState(161);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class IdentifierContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(ElangParser.ID, 0); }
		public IdentifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_identifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).enterIdentifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).exitIdentifier(this);
		}
	}

	public final IdentifierContext identifier() throws RecognitionException {
		IdentifierContext _localctx = new IdentifierContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_identifier);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(162); match(ID);
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
		public StringContext string() {
			return getRuleContext(StringContext.class,0);
		}
		public StringLiteralContext(LiteralContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).enterStringLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).exitStringLiteral(this);
		}
	}
	public static class ObjectLiteralContext extends LiteralContext {
		public ObjectContext object() {
			return getRuleContext(ObjectContext.class,0);
		}
		public ObjectLiteralContext(LiteralContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).enterObjectLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).exitObjectLiteral(this);
		}
	}
	public static class ArrayLiteralContext extends LiteralContext {
		public ArrayContext array() {
			return getRuleContext(ArrayContext.class,0);
		}
		public ArrayLiteralContext(LiteralContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).enterArrayLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).exitArrayLiteral(this);
		}
	}
	public static class NullLiteralContext extends LiteralContext {
		public NullLiteralContext(LiteralContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).enterNullLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).exitNullLiteral(this);
		}
	}
	public static class NumberLiteralContext extends LiteralContext {
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public NumberLiteralContext(LiteralContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).enterNumberLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).exitNumberLiteral(this);
		}
	}
	public static class BooleanLiteralContext extends LiteralContext {
		public BooleanLiteralContext(LiteralContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).enterBooleanLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).exitBooleanLiteral(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_literal);
		try {
			setState(171);
			switch (_input.LA(1)) {
			case STRING:
				_localctx = new StringLiteralContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(164); string();
				}
				break;
			case NUMBER:
				_localctx = new NumberLiteralContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(165); number();
				}
				break;
			case T_BRACELEFT:
				_localctx = new ObjectLiteralContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(166); object();
				}
				break;
			case T_BRACKETLEFT:
				_localctx = new ArrayLiteralContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(167); array();
				}
				break;
			case T_TRUE:
				_localctx = new BooleanLiteralContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(168); match(T_TRUE);
				}
				break;
			case T_FALSE:
				_localctx = new BooleanLiteralContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(169); match(T_FALSE);
				}
				break;
			case T_NULL:
				_localctx = new NullLiteralContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(170); match(T_NULL);
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
			if ( listener instanceof ElangListener ) ((ElangListener)listener).enterFunctionCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).exitFunctionCall(this);
		}
	}

	public final FunctionCallContext functionCall() throws RecognitionException {
		FunctionCallContext _localctx = new FunctionCallContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_functionCall);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(173); functionIdentifier();
			setState(174); match(T_PARENLEFT);
			setState(176);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << SFN_ONEPLUS) | (1L << SFN_ONEMINUS) | (1L << SFN_PLUS) | (1L << SFN_MINUS) | (1L << SFN_MULTIPLY) | (1L << SFN_DIVIDE) | (1L << SFN_LESSTHAN) | (1L << SFN_GREATERTHAN) | (1L << SFN_EQUAL) | (1L << SFN_NEGATE) | (1L << T_BRACELEFT) | (1L << T_BRACKETLEFT) | (1L << T_FALSE) | (1L << T_NULL) | (1L << T_TRUE) | (1L << ID) | (1L << NUMBER) | (1L << STRING))) != 0)) {
				{
				setState(175); functionCallParams();
				}
			}

			setState(178); match(T_PARENRIGHT);
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
		public TerminalNode ID() { return getToken(ElangParser.ID, 0); }
		public RegularFIDContext(FunctionIdentifierContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).enterRegularFID(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).exitRegularFID(this);
		}
	}
	public static class SpecialFIDContext extends FunctionIdentifierContext {
		public SpecialFIDContext(FunctionIdentifierContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).enterSpecialFID(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).exitSpecialFID(this);
		}
	}

	public final FunctionIdentifierContext functionIdentifier() throws RecognitionException {
		FunctionIdentifierContext _localctx = new FunctionIdentifierContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_functionIdentifier);
		try {
			setState(191);
			switch (_input.LA(1)) {
			case SFN_ONEPLUS:
				_localctx = new SpecialFIDContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(180); match(SFN_ONEPLUS);
				}
				break;
			case SFN_ONEMINUS:
				_localctx = new SpecialFIDContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(181); match(SFN_ONEMINUS);
				}
				break;
			case SFN_PLUS:
				_localctx = new SpecialFIDContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(182); match(SFN_PLUS);
				}
				break;
			case SFN_MINUS:
				_localctx = new SpecialFIDContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(183); match(SFN_MINUS);
				}
				break;
			case SFN_MULTIPLY:
				_localctx = new SpecialFIDContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(184); match(SFN_MULTIPLY);
				}
				break;
			case SFN_DIVIDE:
				_localctx = new SpecialFIDContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(185); match(SFN_DIVIDE);
				}
				break;
			case SFN_LESSTHAN:
				_localctx = new SpecialFIDContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(186); match(SFN_LESSTHAN);
				}
				break;
			case SFN_GREATERTHAN:
				_localctx = new SpecialFIDContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(187); match(SFN_GREATERTHAN);
				}
				break;
			case SFN_EQUAL:
				_localctx = new SpecialFIDContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(188); match(SFN_EQUAL);
				}
				break;
			case SFN_NEGATE:
				_localctx = new SpecialFIDContext(_localctx);
				enterOuterAlt(_localctx, 10);
				{
				setState(189); match(SFN_NEGATE);
				}
				break;
			case ID:
				_localctx = new RegularFIDContext(_localctx);
				enterOuterAlt(_localctx, 11);
				{
				setState(190); match(ID);
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
			if ( listener instanceof ElangListener ) ((ElangListener)listener).enterFunctionCallParams(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).exitFunctionCallParams(this);
		}
	}

	public final FunctionCallParamsContext functionCallParams() throws RecognitionException {
		FunctionCallParamsContext _localctx = new FunctionCallParamsContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_functionCallParams);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(193); expr(0);
			setState(197);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << SFN_ONEPLUS) | (1L << SFN_ONEMINUS) | (1L << SFN_PLUS) | (1L << SFN_MINUS) | (1L << SFN_MULTIPLY) | (1L << SFN_DIVIDE) | (1L << SFN_LESSTHAN) | (1L << SFN_GREATERTHAN) | (1L << SFN_EQUAL) | (1L << SFN_NEGATE) | (1L << T_BRACELEFT) | (1L << T_BRACKETLEFT) | (1L << T_FALSE) | (1L << T_NULL) | (1L << T_TRUE) | (1L << ID) | (1L << NUMBER) | (1L << STRING))) != 0)) {
				{
				{
				setState(194); expr(0);
				}
				}
				setState(199);
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
			if ( listener instanceof ElangListener ) ((ElangListener)listener).enterObject(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).exitObject(this);
		}
	}

	public final ObjectContext object() throws RecognitionException {
		ObjectContext _localctx = new ObjectContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_object);
		int _la;
		try {
			setState(210);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(200); match(T_BRACELEFT);
				setState(202); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(201); pair();
					}
					}
					setState(204); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==ID );
				setState(206); match(T_BRACERIGHT);
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(208); match(T_BRACELEFT);
				setState(209); match(T_BRACERIGHT);
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
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ArrayContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_array; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).enterArray(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).exitArray(this);
		}
	}

	public final ArrayContext array() throws RecognitionException {
		ArrayContext _localctx = new ArrayContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_array);
		int _la;
		try {
			setState(222);
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(212); match(T_BRACKETLEFT);
				setState(214); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(213); expr(0);
					}
					}
					setState(216); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << SFN_ONEPLUS) | (1L << SFN_ONEMINUS) | (1L << SFN_PLUS) | (1L << SFN_MINUS) | (1L << SFN_MULTIPLY) | (1L << SFN_DIVIDE) | (1L << SFN_LESSTHAN) | (1L << SFN_GREATERTHAN) | (1L << SFN_EQUAL) | (1L << SFN_NEGATE) | (1L << T_BRACELEFT) | (1L << T_BRACKETLEFT) | (1L << T_FALSE) | (1L << T_NULL) | (1L << T_TRUE) | (1L << ID) | (1L << NUMBER) | (1L << STRING))) != 0) );
				setState(218); match(T_BRACKETRIGHT);
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(220); match(T_BRACKETLEFT);
				setState(221); match(T_BRACKETRIGHT);
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
			if ( listener instanceof ElangListener ) ((ElangListener)listener).enterPair(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).exitPair(this);
		}
	}

	public final PairContext pair() throws RecognitionException {
		PairContext _localctx = new PairContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_pair);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(224); identifier();
			setState(225); match(T_COLON);
			setState(226); expr(0);
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
			if ( listener instanceof ElangListener ) ((ElangListener)listener).enterStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).exitStmt(this);
		}
	}

	public final StmtContext stmt() throws RecognitionException {
		StmtContext _localctx = new StmtContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_stmt);
		try {
			setState(235);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(228); stmtReturn();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(229); stmtContinue();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(230); stmtBreak();
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(231); stmtAssign();
				}
				break;

			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(232); stmtIf();
				}
				break;

			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(233); stmtWhile();
				}
				break;

			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(234); functionCall();
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
			if ( listener instanceof ElangListener ) ((ElangListener)listener).enterStmtAssign(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).exitStmtAssign(this);
		}
	}

	public final StmtAssignContext stmtAssign() throws RecognitionException {
		StmtAssignContext _localctx = new StmtAssignContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_stmtAssign);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(237); identifier();
			setState(238); match(T_ARROWLEFT);
			setState(239); expr(0);
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
			if ( listener instanceof ElangListener ) ((ElangListener)listener).enterStmtContinue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).exitStmtContinue(this);
		}
	}

	public final StmtContinueContext stmtContinue() throws RecognitionException {
		StmtContinueContext _localctx = new StmtContinueContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_stmtContinue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(241); match(T_CONTINUE);
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
			if ( listener instanceof ElangListener ) ((ElangListener)listener).enterStmtBreak(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).exitStmtBreak(this);
		}
	}

	public final StmtBreakContext stmtBreak() throws RecognitionException {
		StmtBreakContext _localctx = new StmtBreakContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_stmtBreak);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(243); match(T_BREAK);
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
			if ( listener instanceof ElangListener ) ((ElangListener)listener).enterStmtReturn(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).exitStmtReturn(this);
		}
	}

	public final StmtReturnContext stmtReturn() throws RecognitionException {
		StmtReturnContext _localctx = new StmtReturnContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_stmtReturn);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(245); match(T_RETURN);
			setState(246); expr(0);
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
			if ( listener instanceof ElangListener ) ((ElangListener)listener).enterStmtIf(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).exitStmtIf(this);
		}
	}

	public final StmtIfContext stmtIf() throws RecognitionException {
		StmtIfContext _localctx = new StmtIfContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_stmtIf);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(248); match(T_IF);
			setState(249); expr(0);
			setState(250); match(T_THEN);
			setState(251); block();
			setState(254);
			_la = _input.LA(1);
			if (_la==T_ELSE) {
				{
				setState(252); match(T_ELSE);
				setState(253); block();
				}
			}

			setState(256); match(T_END);
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
			if ( listener instanceof ElangListener ) ((ElangListener)listener).enterStmtWhile(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).exitStmtWhile(this);
		}
	}

	public final StmtWhileContext stmtWhile() throws RecognitionException {
		StmtWhileContext _localctx = new StmtWhileContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_stmtWhile);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(258); match(T_WHILE);
			setState(259); expr(0);
			setState(260); match(T_DO);
			setState(261); block();
			setState(262); match(T_END);
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
		public IterationBodyContext iterationBody() {
			return getRuleContext(IterationBodyContext.class,0);
		}
		public TerminalNode T_END() { return getToken(ElangParser.T_END, 0); }
		public TerminalNode T_ITERATION() { return getToken(ElangParser.T_ITERATION, 0); }
		public IterationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_iteration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).enterIteration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).exitIteration(this);
		}
	}

	public final IterationContext iteration() throws RecognitionException {
		IterationContext _localctx = new IterationContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_iteration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(264); match(T_ITERATION);
			setState(265); iterationBody();
			setState(266); match(T_END);
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

	public static class IterationBodyContext extends ParserRuleContext {
		public VarDefsContext varDefs() {
			return getRuleContext(VarDefsContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public IterationBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_iterationBody; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).enterIterationBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).exitIterationBody(this);
		}
	}

	public final IterationBodyContext iterationBody() throws RecognitionException {
		IterationBodyContext _localctx = new IterationBodyContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_iterationBody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(269);
			_la = _input.LA(1);
			if (_la==T_VAR) {
				{
				setState(268); varDefs();
				}
			}

			setState(271); block();
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

	public static class NumberContext extends ParserRuleContext {
		public TerminalNode NUMBER() { return getToken(ElangParser.NUMBER, 0); }
		public NumberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_number; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).enterNumber(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).exitNumber(this);
		}
	}

	public final NumberContext number() throws RecognitionException {
		NumberContext _localctx = new NumberContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_number);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(273); match(NUMBER);
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

	public static class StringContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(ElangParser.STRING, 0); }
		public StringContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_string; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).enterString(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).exitString(this);
		}
	}

	public final StringContext string() throws RecognitionException {
		StringContext _localctx = new StringContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_string);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(275); match(STRING);
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 13: return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0: return 5 >= _localctx._p;
		}
		return true;
	}

	public static final String _serializedATN =
		"\2\3\66\u0118\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b"+
		"\4\t\t\t\4\n\t\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t"+
		"\20\4\21\t\21\4\22\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t"+
		"\27\4\30\t\30\4\31\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t"+
		"\36\4\37\t\37\4 \t \4!\t!\4\"\t\"\3\2\7\2F\n\2\f\2\16\2I\13\2\3\2\3\2"+
		"\3\2\3\3\3\3\3\3\3\3\5\3R\n\3\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5"+
		"\3\6\3\6\3\6\3\6\5\6b\n\6\3\6\3\6\3\6\3\6\3\7\3\7\7\7j\n\7\f\7\16\7m\13"+
		"\7\3\b\3\b\3\t\5\tr\n\t\3\t\5\tu\n\t\3\t\3\t\3\n\6\nz\n\n\r\n\16\n{\3"+
		"\13\3\13\3\13\3\13\3\13\3\f\6\f\u0084\n\f\r\f\16\f\u0085\3\r\3\r\3\r\3"+
		"\r\3\r\3\16\6\16\u008e\n\16\r\16\16\16\u008f\3\17\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\3\17\3\17\5\17\u009b\n\17\3\17\3\17\3\17\7\17\u00a0\n\17\f"+
		"\17\16\17\u00a3\13\17\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\5\21"+
		"\u00ae\n\21\3\22\3\22\3\22\5\22\u00b3\n\22\3\22\3\22\3\23\3\23\3\23\3"+
		"\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\5\23\u00c2\n\23\3\24\3\24\7\24"+
		"\u00c6\n\24\f\24\16\24\u00c9\13\24\3\25\3\25\6\25\u00cd\n\25\r\25\16\25"+
		"\u00ce\3\25\3\25\3\25\3\25\5\25\u00d5\n\25\3\26\3\26\6\26\u00d9\n\26\r"+
		"\26\16\26\u00da\3\26\3\26\3\26\3\26\5\26\u00e1\n\26\3\27\3\27\3\27\3\27"+
		"\3\30\3\30\3\30\3\30\3\30\3\30\3\30\5\30\u00ee\n\30\3\31\3\31\3\31\3\31"+
		"\3\32\3\32\3\33\3\33\3\34\3\34\3\34\3\35\3\35\3\35\3\35\3\35\3\35\5\35"+
		"\u0101\n\35\3\35\3\35\3\36\3\36\3\36\3\36\3\36\3\36\3\37\3\37\3\37\3\37"+
		"\3 \5 \u0110\n \3 \3 \3!\3!\3\"\3\"\3\"\2#\2\4\6\b\n\f\16\20\22\24\26"+
		"\30\32\34\36 \"$&(*,.\60\62\64\668:<>@B\2\2\u0123\2G\3\2\2\2\4Q\3\2\2"+
		"\2\6S\3\2\2\2\bX\3\2\2\2\n]\3\2\2\2\fg\3\2\2\2\16n\3\2\2\2\20q\3\2\2\2"+
		"\22y\3\2\2\2\24}\3\2\2\2\26\u0083\3\2\2\2\30\u0087\3\2\2\2\32\u008d\3"+
		"\2\2\2\34\u009a\3\2\2\2\36\u00a4\3\2\2\2 \u00ad\3\2\2\2\"\u00af\3\2\2"+
		"\2$\u00c1\3\2\2\2&\u00c3\3\2\2\2(\u00d4\3\2\2\2*\u00e0\3\2\2\2,\u00e2"+
		"\3\2\2\2.\u00ed\3\2\2\2\60\u00ef\3\2\2\2\62\u00f3\3\2\2\2\64\u00f5\3\2"+
		"\2\2\66\u00f7\3\2\2\28\u00fa\3\2\2\2:\u0104\3\2\2\2<\u010a\3\2\2\2>\u010f"+
		"\3\2\2\2@\u0113\3\2\2\2B\u0115\3\2\2\2DF\5\4\3\2ED\3\2\2\2FI\3\2\2\2G"+
		"E\3\2\2\2GH\3\2\2\2HJ\3\2\2\2IG\3\2\2\2JK\5<\37\2KL\7\1\2\2L\3\3\2\2\2"+
		"MR\5\6\4\2NR\5\b\5\2OR\5\n\6\2PR\5\"\22\2QM\3\2\2\2QN\3\2\2\2QO\3\2\2"+
		"\2QP\3\2\2\2R\5\3\2\2\2ST\7 \2\2TU\5\36\20\2UV\7\17\2\2VW\5\34\17\2W\7"+
		"\3\2\2\2XY\7.\2\2YZ\5\36\20\2Z[\7\17\2\2[\\\5\34\17\2\\\t\3\2\2\2]^\7"+
		"\36\2\2^_\5\36\20\2_a\7&\2\2`b\5\f\7\2a`\3\2\2\2ab\3\2\2\2bc\3\2\2\2c"+
		"d\7\'\2\2de\5\20\t\2ef\7\34\2\2f\13\3\2\2\2gk\5\16\b\2hj\5\16\b\2ih\3"+
		"\2\2\2jm\3\2\2\2ki\3\2\2\2kl\3\2\2\2l\r\3\2\2\2mk\3\2\2\2no\7\61\2\2o"+
		"\17\3\2\2\2pr\5\22\n\2qp\3\2\2\2qr\3\2\2\2rt\3\2\2\2su\5\26\f\2ts\3\2"+
		"\2\2tu\3\2\2\2uv\3\2\2\2vw\5\32\16\2w\21\3\2\2\2xz\5\24\13\2yx\3\2\2\2"+
		"z{\3\2\2\2{y\3\2\2\2{|\3\2\2\2|\23\3\2\2\2}~\7\37\2\2~\177\5\36\20\2\177"+
		"\u0080\7\17\2\2\u0080\u0081\5\34\17\2\u0081\25\3\2\2\2\u0082\u0084\5\30"+
		"\r\2\u0083\u0082\3\2\2\2\u0084\u0085\3\2\2\2\u0085\u0083\3\2\2\2\u0085"+
		"\u0086\3\2\2\2\u0086\27\3\2\2\2\u0087\u0088\7/\2\2\u0088\u0089\5\36\20"+
		"\2\u0089\u008a\7\17\2\2\u008a\u008b\5\34\17\2\u008b\31\3\2\2\2\u008c\u008e"+
		"\5.\30\2\u008d\u008c\3\2\2\2\u008e\u008f\3\2\2\2\u008f\u008d\3\2\2\2\u008f"+
		"\u0090\3\2\2\2\u0090\33\3\2\2\2\u0091\u0092\b\17\1\2\u0092\u009b\5\"\22"+
		"\2\u0093\u0094\5\36\20\2\u0094\u0095\7\23\2\2\u0095\u0096\5\34\17\2\u0096"+
		"\u0097\7\24\2\2\u0097\u009b\3\2\2\2\u0098\u009b\5\36\20\2\u0099\u009b"+
		"\5 \21\2\u009a\u0091\3\2\2\2\u009a\u0093\3\2\2\2\u009a\u0098\3\2\2\2\u009a"+
		"\u0099\3\2\2\2\u009b\u00a1\3\2\2\2\u009c\u009d\6\17\2\3\u009d\u009e\7"+
		"\32\2\2\u009e\u00a0\5\36\20\2\u009f\u009c\3\2\2\2\u00a0\u00a3\3\2\2\2"+
		"\u00a1\u009f\3\2\2\2\u00a1\u00a2\3\2\2\2\u00a2\35\3\2\2\2\u00a3\u00a1"+
		"\3\2\2\2\u00a4\u00a5\7\61\2\2\u00a5\37\3\2\2\2\u00a6\u00ae\5B\"\2\u00a7"+
		"\u00ae\5@!\2\u00a8\u00ae\5(\25\2\u00a9\u00ae\5*\26\2\u00aa\u00ae\7-\2"+
		"\2\u00ab\u00ae\7\35\2\2\u00ac\u00ae\7%\2\2\u00ad\u00a6\3\2\2\2\u00ad\u00a7"+
		"\3\2\2\2\u00ad\u00a8\3\2\2\2\u00ad\u00a9\3\2\2\2\u00ad\u00aa\3\2\2\2\u00ad"+
		"\u00ab\3\2\2\2\u00ad\u00ac\3\2\2\2\u00ae!\3\2\2\2\u00af\u00b0\5$\23\2"+
		"\u00b0\u00b2\7&\2\2\u00b1\u00b3\5&\24\2\u00b2\u00b1\3\2\2\2\u00b2\u00b3"+
		"\3\2\2\2\u00b3\u00b4\3\2\2\2\u00b4\u00b5\7\'\2\2\u00b5#\3\2\2\2\u00b6"+
		"\u00c2\7\4\2\2\u00b7\u00c2\7\5\2\2\u00b8\u00c2\7\6\2\2\u00b9\u00c2\7\7"+
		"\2\2\u00ba\u00c2\7\b\2\2\u00bb\u00c2\7\t\2\2\u00bc\u00c2\7\n\2\2\u00bd"+
		"\u00c2\7\13\2\2\u00be\u00c2\7\f\2\2\u00bf\u00c2\7\r\2\2\u00c0\u00c2\7"+
		"\61\2\2\u00c1\u00b6\3\2\2\2\u00c1\u00b7\3\2\2\2\u00c1\u00b8\3\2\2\2\u00c1"+
		"\u00b9\3\2\2\2\u00c1\u00ba\3\2\2\2\u00c1\u00bb\3\2\2\2\u00c1\u00bc\3\2"+
		"\2\2\u00c1\u00bd\3\2\2\2\u00c1\u00be\3\2\2\2\u00c1\u00bf\3\2\2\2\u00c1"+
		"\u00c0\3\2\2\2\u00c2%\3\2\2\2\u00c3\u00c7\5\34\17\2\u00c4\u00c6\5\34\17"+
		"\2\u00c5\u00c4\3\2\2\2\u00c6\u00c9\3\2\2\2\u00c7\u00c5\3\2\2\2\u00c7\u00c8"+
		"\3\2\2\2\u00c8\'\3\2\2\2\u00c9\u00c7\3\2\2\2\u00ca\u00cc\7\21\2\2\u00cb"+
		"\u00cd\5,\27\2\u00cc\u00cb\3\2\2\2\u00cd\u00ce\3\2\2\2\u00ce\u00cc\3\2"+
		"\2\2\u00ce\u00cf\3\2\2\2\u00cf\u00d0\3\2\2\2\u00d0\u00d1\7\22\2\2\u00d1"+
		"\u00d5\3\2\2\2\u00d2\u00d3\7\21\2\2\u00d3\u00d5\7\22\2\2\u00d4\u00ca\3"+
		"\2\2\2\u00d4\u00d2\3\2\2\2\u00d5)\3\2\2\2\u00d6\u00d8\7\23\2\2\u00d7\u00d9"+
		"\5\34\17\2\u00d8\u00d7\3\2\2\2\u00d9\u00da\3\2\2\2\u00da\u00d8\3\2\2\2"+
		"\u00da\u00db\3\2\2\2\u00db\u00dc\3\2\2\2\u00dc\u00dd\7\24\2\2\u00dd\u00e1"+
		"\3\2\2\2\u00de\u00df\7\23\2\2\u00df\u00e1\7\24\2\2\u00e0\u00d6\3\2\2\2"+
		"\u00e0\u00de\3\2\2\2\u00e1+\3\2\2\2\u00e2\u00e3\5\36\20\2\u00e3\u00e4"+
		"\7\26\2\2\u00e4\u00e5\5\34\17\2\u00e5-\3\2\2\2\u00e6\u00ee\5\66\34\2\u00e7"+
		"\u00ee\5\62\32\2\u00e8\u00ee\5\64\33\2\u00e9\u00ee\5\60\31\2\u00ea\u00ee"+
		"\58\35\2\u00eb\u00ee\5:\36\2\u00ec\u00ee\5\"\22\2\u00ed\u00e6\3\2\2\2"+
		"\u00ed\u00e7\3\2\2\2\u00ed\u00e8\3\2\2\2\u00ed\u00e9\3\2\2\2\u00ed\u00ea"+
		"\3\2\2\2\u00ed\u00eb\3\2\2\2\u00ed\u00ec\3\2\2\2\u00ee/\3\2\2\2\u00ef"+
		"\u00f0\5\36\20\2\u00f0\u00f1\7\17\2\2\u00f1\u00f2\5\34\17\2\u00f2\61\3"+
		"\2\2\2\u00f3\u00f4\7\30\2\2\u00f4\63\3\2\2\2\u00f5\u00f6\7\25\2\2\u00f6"+
		"\65\3\2\2\2\u00f7\u00f8\7*\2\2\u00f8\u00f9\5\34\17\2\u00f9\67\3\2\2\2"+
		"\u00fa\u00fb\7\"\2\2\u00fb\u00fc\5\34\17\2\u00fc\u00fd\7+\2\2\u00fd\u0100"+
		"\5\32\16\2\u00fe\u00ff\7\33\2\2\u00ff\u0101\5\32\16\2\u0100\u00fe\3\2"+
		"\2\2\u0100\u0101\3\2\2\2\u0101\u0102\3\2\2\2\u0102\u0103\7\34\2\2\u0103"+
		"9\3\2\2\2\u0104\u0105\7\60\2\2\u0105\u0106\5\34\17\2\u0106\u0107\7\31"+
		"\2\2\u0107\u0108\5\32\16\2\u0108\u0109\7\34\2\2\u0109;\3\2\2\2\u010a\u010b"+
		"\7$\2\2\u010b\u010c\5> \2\u010c\u010d\7\34\2\2\u010d=\3\2\2\2\u010e\u0110"+
		"\5\26\f\2\u010f\u010e\3\2\2\2\u010f\u0110\3\2\2\2\u0110\u0111\3\2\2\2"+
		"\u0111\u0112\5\32\16\2\u0112?\3\2\2\2\u0113\u0114\7\62\2\2\u0114A\3\2"+
		"\2\2\u0115\u0116\7\63\2\2\u0116C\3\2\2\2\30GQakqt{\u0085\u008f\u009a\u00a1"+
		"\u00ad\u00b2\u00c1\u00c7\u00ce\u00d4\u00da\u00e0\u00ed\u0100\u010f";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
	}
}