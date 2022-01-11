// Generated from Elang.g4 by ANTLR 4.9.3
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
	static { RuntimeMetaData.checkVersion("4.9.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, DOCQUOTE=4, SFN_PLUS=5, SFN_MINUS=6, SFN_MULTIPLY=7, 
		SFN_DIVIDE=8, SFN_LESSTHAN=9, SFN_GREATERTHAN=10, SFN_EQUAL=11, SFN_NEGATE=12, 
		T_TRANSNAMEREPEAT=13, T_ARROWLEFT=14, T_ARROWRIGHT=15, T_BRACELEFT=16, 
		T_BRACERIGHT=17, T_BRACKETLEFT=18, T_BRACKETRIGHT=19, T_BREAK=20, T_COLON=21, 
		T_COMMA=22, T_CONTINUE=23, T_DO=24, T_DOT=25, T_ELSE=26, T_END=27, T_FALSE=28, 
		T_FUNCTION=29, T_FVAR=30, T_GVAR=31, T_HASH=32, T_IF=33, T_NO=34, T_NOTHING=35, 
		T_NULL=36, T_PARENLEFT=37, T_PARENRIGHT=38, T_QMARK=39, T_QUOTE=40, T_RETURN=41, 
		T_THEN=42, T_TRANSFINAL=43, T_TRANSITION=44, T_TRANSSTART=45, T_TRUE=46, 
		T_VAL=47, T_VAR=48, T_WHILE=49, T_YES=50, ID=51, NUMBER=52, STRING=53, 
		COMMENT=54, WS=55, NL=56;
	public static final int
		RULE_file = 0, RULE_toplevelStmt = 1, RULE_gvarDef = 2, RULE_valDef = 3, 
		RULE_functionDef = 4, RULE_functionDefParams = 5, RULE_functionDefParam = 6, 
		RULE_functionBody = 7, RULE_fvarDefs = 8, RULE_fvarDef = 9, RULE_varDefs = 10, 
		RULE_varDef = 11, RULE_block = 12, RULE_expr = 13, RULE_identifier = 14, 
		RULE_literal = 15, RULE_functionCall = 16, RULE_functionIdentifier = 17, 
		RULE_functionCallParams = 18, RULE_object = 19, RULE_array = 20, RULE_pair = 21, 
		RULE_stmt = 22, RULE_stmtAssign = 23, RULE_stmtContinue = 24, RULE_stmtBreak = 25, 
		RULE_stmtReturn = 26, RULE_stmtIfElse = 27, RULE_stmtIf = 28, RULE_stmtWhile = 29, 
		RULE_phaseDefs = 30, RULE_phaseDef = 31, RULE_informationPhase = 32, RULE_intermezzoPhase = 33, 
		RULE_interactionPhase = 34, RULE_phaseSetVar = 35, RULE_phaseSetVarValue = 36, 
		RULE_phaseFunctionDef = 37, RULE_phaseFunctionBody = 38, RULE_transitionDefs = 39, 
		RULE_transitions = 40, RULE_startTransition = 41, RULE_otherTransitions = 42, 
		RULE_finalTransition = 43, RULE_transition = 44, RULE_transitionSrc = 45, 
		RULE_transitionTo = 46, RULE_transitionCond = 47, RULE_number = 48, RULE_string = 49;
	private static String[] makeRuleNames() {
		return new String[] {
			"file", "toplevelStmt", "gvarDef", "valDef", "functionDef", "functionDefParams", 
			"functionDefParam", "functionBody", "fvarDefs", "fvarDef", "varDefs", 
			"varDef", "block", "expr", "identifier", "literal", "functionCall", "functionIdentifier", 
			"functionCallParams", "object", "array", "pair", "stmt", "stmtAssign", 
			"stmtContinue", "stmtBreak", "stmtReturn", "stmtIfElse", "stmtIf", "stmtWhile", 
			"phaseDefs", "phaseDef", "informationPhase", "intermezzoPhase", "interactionPhase", 
			"phaseSetVar", "phaseSetVarValue", "phaseFunctionDef", "phaseFunctionBody", 
			"transitionDefs", "transitions", "startTransition", "otherTransitions", 
			"finalTransition", "transition", "transitionSrc", "transitionTo", "transitionCond", 
			"number", "string"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'information-phase'", "'intermezzo-phase'", "'interaction-phase'", 
			null, "'+'", "'-'", "'*'", "'/'", "'<'", "'>'", "'='", "'!'", "'-\"-'", 
			"'<-'", "'->'", "'{'", "'}'", "'['", "']'", "'break'", "':'", "','", 
			"'continue'", "'do'", "'.'", "'else'", "'end'", "'false'", "'function'", 
			"'fvar'", "'gvar'", "'#'", "'if'", "'no'", "'nothing'", "'null'", "'('", 
			"')'", "'?'", "'\"'", "'return'", "'then'", "'final'", "'transition'", 
			"'start'", "'true'", "'val'", "'var'", "'while'", "'yes'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, "DOCQUOTE", "SFN_PLUS", "SFN_MINUS", "SFN_MULTIPLY", 
			"SFN_DIVIDE", "SFN_LESSTHAN", "SFN_GREATERTHAN", "SFN_EQUAL", "SFN_NEGATE", 
			"T_TRANSNAMEREPEAT", "T_ARROWLEFT", "T_ARROWRIGHT", "T_BRACELEFT", "T_BRACERIGHT", 
			"T_BRACKETLEFT", "T_BRACKETRIGHT", "T_BREAK", "T_COLON", "T_COMMA", "T_CONTINUE", 
			"T_DO", "T_DOT", "T_ELSE", "T_END", "T_FALSE", "T_FUNCTION", "T_FVAR", 
			"T_GVAR", "T_HASH", "T_IF", "T_NO", "T_NOTHING", "T_NULL", "T_PARENLEFT", 
			"T_PARENRIGHT", "T_QMARK", "T_QUOTE", "T_RETURN", "T_THEN", "T_TRANSFINAL", 
			"T_TRANSITION", "T_TRANSSTART", "T_TRUE", "T_VAL", "T_VAR", "T_WHILE", 
			"T_YES", "ID", "NUMBER", "STRING", "COMMENT", "WS", "NL"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Elang.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public ElangParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class FileContext extends ParserRuleContext {
		public PhaseDefsContext phaseDefs() {
			return getRuleContext(PhaseDefsContext.class,0);
		}
		public TerminalNode EOF() { return getToken(ElangParser.EOF, 0); }
		public List<ToplevelStmtContext> toplevelStmt() {
			return getRuleContexts(ToplevelStmtContext.class);
		}
		public ToplevelStmtContext toplevelStmt(int i) {
			return getRuleContext(ToplevelStmtContext.class,i);
		}
		public TransitionDefsContext transitionDefs() {
			return getRuleContext(TransitionDefsContext.class,0);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ElangVisitor ) return ((ElangVisitor<? extends T>)visitor).visitFile(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FileContext file() throws RecognitionException {
		FileContext _localctx = new FileContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_file);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(103);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T_FUNCTION) | (1L << T_GVAR) | (1L << T_VAL))) != 0)) {
				{
				{
				setState(100);
				toplevelStmt();
				}
				}
				setState(105);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(106);
			phaseDefs();
			setState(108);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T_TRANSITION) {
				{
				setState(107);
				transitionDefs();
				}
			}

			setState(110);
			match(EOF);
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
		public GvarDefContext gvarDef() {
			return getRuleContext(GvarDefContext.class,0);
		}
		public ValDefContext valDef() {
			return getRuleContext(ValDefContext.class,0);
		}
		public FunctionDefContext functionDef() {
			return getRuleContext(FunctionDefContext.class,0);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ElangVisitor ) return ((ElangVisitor<? extends T>)visitor).visitToplevelStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ToplevelStmtContext toplevelStmt() throws RecognitionException {
		ToplevelStmtContext _localctx = new ToplevelStmtContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_toplevelStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(115);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T_GVAR:
				{
				setState(112);
				gvarDef();
				}
				break;
			case T_VAL:
				{
				setState(113);
				valDef();
				}
				break;
			case T_FUNCTION:
				{
				setState(114);
				functionDef();
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
		public TerminalNode T_GVAR() { return getToken(ElangParser.T_GVAR, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode T_ARROWLEFT() { return getToken(ElangParser.T_ARROWLEFT, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ElangVisitor ) return ((ElangVisitor<? extends T>)visitor).visitGvarDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GvarDefContext gvarDef() throws RecognitionException {
		GvarDefContext _localctx = new GvarDefContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_gvarDef);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(117);
			match(T_GVAR);
			setState(118);
			identifier();
			setState(119);
			match(T_ARROWLEFT);
			setState(120);
			expr(0);
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
		public TerminalNode T_VAL() { return getToken(ElangParser.T_VAL, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode T_ARROWLEFT() { return getToken(ElangParser.T_ARROWLEFT, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ElangVisitor ) return ((ElangVisitor<? extends T>)visitor).visitValDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ValDefContext valDef() throws RecognitionException {
		ValDefContext _localctx = new ValDefContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_valDef);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(122);
			match(T_VAL);
			setState(123);
			identifier();
			setState(124);
			match(T_ARROWLEFT);
			setState(125);
			expr(0);
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
		public TerminalNode T_FUNCTION() { return getToken(ElangParser.T_FUNCTION, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode T_PARENLEFT() { return getToken(ElangParser.T_PARENLEFT, 0); }
		public TerminalNode T_PARENRIGHT() { return getToken(ElangParser.T_PARENRIGHT, 0); }
		public FunctionBodyContext functionBody() {
			return getRuleContext(FunctionBodyContext.class,0);
		}
		public TerminalNode T_END() { return getToken(ElangParser.T_END, 0); }
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ElangVisitor ) return ((ElangVisitor<? extends T>)visitor).visitFunctionDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionDefContext functionDef() throws RecognitionException {
		FunctionDefContext _localctx = new FunctionDefContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_functionDef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(127);
			match(T_FUNCTION);
			setState(128);
			identifier();
			setState(129);
			match(T_PARENLEFT);
			setState(131);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ID) {
				{
				setState(130);
				functionDefParams();
				}
			}

			setState(133);
			match(T_PARENRIGHT);
			setState(134);
			functionBody();
			setState(135);
			match(T_END);
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
		public List<FunctionDefParamContext> functionDefParam() {
			return getRuleContexts(FunctionDefParamContext.class);
		}
		public FunctionDefParamContext functionDefParam(int i) {
			return getRuleContext(FunctionDefParamContext.class,i);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ElangVisitor ) return ((ElangVisitor<? extends T>)visitor).visitFunctionDefParams(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionDefParamsContext functionDefParams() throws RecognitionException {
		FunctionDefParamsContext _localctx = new FunctionDefParamsContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_functionDefParams);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(137);
			functionDefParam();
			setState(141);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ID) {
				{
				{
				setState(138);
				functionDefParam();
				}
				}
				setState(143);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ElangVisitor ) return ((ElangVisitor<? extends T>)visitor).visitFunctionDefParam(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionDefParamContext functionDefParam() throws RecognitionException {
		FunctionDefParamContext _localctx = new FunctionDefParamContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_functionDefParam);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(144);
			match(ID);
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
		public FvarDefsContext fvarDefs() {
			return getRuleContext(FvarDefsContext.class,0);
		}
		public VarDefsContext varDefs() {
			return getRuleContext(VarDefsContext.class,0);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ElangVisitor ) return ((ElangVisitor<? extends T>)visitor).visitFunctionBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionBodyContext functionBody() throws RecognitionException {
		FunctionBodyContext _localctx = new FunctionBodyContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_functionBody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(147);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T_FVAR) {
				{
				setState(146);
				fvarDefs();
				}
			}

			setState(150);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T_VAR) {
				{
				setState(149);
				varDefs();
				}
			}

			setState(152);
			block();
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ElangVisitor ) return ((ElangVisitor<? extends T>)visitor).visitFvarDefs(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FvarDefsContext fvarDefs() throws RecognitionException {
		FvarDefsContext _localctx = new FvarDefsContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_fvarDefs);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(155); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(154);
				fvarDef();
				}
				}
				setState(157); 
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
		public TerminalNode T_FVAR() { return getToken(ElangParser.T_FVAR, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode T_ARROWLEFT() { return getToken(ElangParser.T_ARROWLEFT, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ElangVisitor ) return ((ElangVisitor<? extends T>)visitor).visitFvarDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FvarDefContext fvarDef() throws RecognitionException {
		FvarDefContext _localctx = new FvarDefContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_fvarDef);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(159);
			match(T_FVAR);
			setState(160);
			identifier();
			setState(161);
			match(T_ARROWLEFT);
			setState(162);
			expr(0);
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
		public List<VarDefContext> varDef() {
			return getRuleContexts(VarDefContext.class);
		}
		public VarDefContext varDef(int i) {
			return getRuleContext(VarDefContext.class,i);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ElangVisitor ) return ((ElangVisitor<? extends T>)visitor).visitVarDefs(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarDefsContext varDefs() throws RecognitionException {
		VarDefsContext _localctx = new VarDefsContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_varDefs);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(165); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(164);
				varDef();
				}
				}
				setState(167); 
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
		public TerminalNode T_VAR() { return getToken(ElangParser.T_VAR, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode T_ARROWLEFT() { return getToken(ElangParser.T_ARROWLEFT, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ElangVisitor ) return ((ElangVisitor<? extends T>)visitor).visitVarDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarDefContext varDef() throws RecognitionException {
		VarDefContext _localctx = new VarDefContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_varDef);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(169);
			match(T_VAR);
			setState(170);
			identifier();
			setState(171);
			match(T_ARROWLEFT);
			setState(172);
			expr(0);
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
		public List<StmtContext> stmt() {
			return getRuleContexts(StmtContext.class);
		}
		public StmtContext stmt(int i) {
			return getRuleContext(StmtContext.class,i);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ElangVisitor ) return ((ElangVisitor<? extends T>)visitor).visitBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(175); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(174);
				stmt();
				}
				}
				setState(177); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T_BREAK) | (1L << T_CONTINUE) | (1L << T_IF) | (1L << T_RETURN) | (1L << T_WHILE) | (1L << ID))) != 0) );
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ElangVisitor ) return ((ElangVisitor<? extends T>)visitor).visitExprFcall(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExprProprefContext extends ExprContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode T_DOT() { return getToken(ElangParser.T_DOT, 0); }
		public TerminalNode ID() { return getToken(ElangParser.ID, 0); }
		public ExprProprefContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).enterExprPropref(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).exitExprPropref(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ElangVisitor ) return ((ElangVisitor<? extends T>)visitor).visitExprPropref(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExprIndexingContext extends ExprContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode T_BRACKETLEFT() { return getToken(ElangParser.T_BRACKETLEFT, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode T_BRACKETRIGHT() { return getToken(ElangParser.T_BRACKETRIGHT, 0); }
		public ExprIndexingContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).enterExprIndexing(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).exitExprIndexing(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ElangVisitor ) return ((ElangVisitor<? extends T>)visitor).visitExprIndexing(this);
			else return visitor.visitChildren(this);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ElangVisitor ) return ((ElangVisitor<? extends T>)visitor).visitExprLiteral(this);
			else return visitor.visitChildren(this);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ElangVisitor ) return ((ElangVisitor<? extends T>)visitor).visitExprId(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 26;
		enterRecursionRule(_localctx, 26, RULE_expr, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(188);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				{
				_localctx = new ExprFcallContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(180);
				functionCall();
				}
				break;
			case 2:
				{
				_localctx = new ExprIndexingContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(181);
				identifier();
				setState(182);
				match(T_BRACKETLEFT);
				setState(183);
				expr(0);
				setState(184);
				match(T_BRACKETRIGHT);
				}
				break;
			case 3:
				{
				_localctx = new ExprIdContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(186);
				identifier();
				}
				break;
			case 4:
				{
				_localctx = new ExprLiteralContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(187);
				literal();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(195);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ExprProprefContext(new ExprContext(_parentctx, _parentState));
					pushNewRecursionContext(_localctx, _startState, RULE_expr);
					setState(190);
					if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
					setState(191);
					match(T_DOT);
					setState(192);
					match(ID);
					}
					} 
				}
				setState(197);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ElangVisitor ) return ((ElangVisitor<? extends T>)visitor).visitIdentifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IdentifierContext identifier() throws RecognitionException {
		IdentifierContext _localctx = new IdentifierContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_identifier);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(198);
			match(ID);
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
	public static class NothingLiteralContext extends LiteralContext {
		public TerminalNode T_NOTHING() { return getToken(ElangParser.T_NOTHING, 0); }
		public NothingLiteralContext(LiteralContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).enterNothingLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).exitNothingLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ElangVisitor ) return ((ElangVisitor<? extends T>)visitor).visitNothingLiteral(this);
			else return visitor.visitChildren(this);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ElangVisitor ) return ((ElangVisitor<? extends T>)visitor).visitObjectLiteral(this);
			else return visitor.visitChildren(this);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ElangVisitor ) return ((ElangVisitor<? extends T>)visitor).visitStringLiteral(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BooleanLiteralContext extends LiteralContext {
		public TerminalNode T_TRUE() { return getToken(ElangParser.T_TRUE, 0); }
		public TerminalNode T_FALSE() { return getToken(ElangParser.T_FALSE, 0); }
		public BooleanLiteralContext(LiteralContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).enterBooleanLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).exitBooleanLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ElangVisitor ) return ((ElangVisitor<? extends T>)visitor).visitBooleanLiteral(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class YesNoLiteralContext extends LiteralContext {
		public TerminalNode T_YES() { return getToken(ElangParser.T_YES, 0); }
		public TerminalNode T_NO() { return getToken(ElangParser.T_NO, 0); }
		public YesNoLiteralContext(LiteralContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).enterYesNoLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).exitYesNoLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ElangVisitor ) return ((ElangVisitor<? extends T>)visitor).visitYesNoLiteral(this);
			else return visitor.visitChildren(this);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ElangVisitor ) return ((ElangVisitor<? extends T>)visitor).visitArrayLiteral(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NullLiteralContext extends LiteralContext {
		public TerminalNode T_NULL() { return getToken(ElangParser.T_NULL, 0); }
		public NullLiteralContext(LiteralContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).enterNullLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).exitNullLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ElangVisitor ) return ((ElangVisitor<? extends T>)visitor).visitNullLiteral(this);
			else return visitor.visitChildren(this);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ElangVisitor ) return ((ElangVisitor<? extends T>)visitor).visitNumberLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_literal);
		try {
			setState(210);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case STRING:
				_localctx = new StringLiteralContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(200);
				string();
				}
				break;
			case NUMBER:
				_localctx = new NumberLiteralContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(201);
				number();
				}
				break;
			case T_BRACELEFT:
				_localctx = new ObjectLiteralContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(202);
				object();
				}
				break;
			case T_BRACKETLEFT:
				_localctx = new ArrayLiteralContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(203);
				array();
				}
				break;
			case T_TRUE:
				_localctx = new BooleanLiteralContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(204);
				match(T_TRUE);
				}
				break;
			case T_FALSE:
				_localctx = new BooleanLiteralContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(205);
				match(T_FALSE);
				}
				break;
			case T_YES:
				_localctx = new YesNoLiteralContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(206);
				match(T_YES);
				}
				break;
			case T_NO:
				_localctx = new YesNoLiteralContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(207);
				match(T_NO);
				}
				break;
			case T_NULL:
				_localctx = new NullLiteralContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(208);
				match(T_NULL);
				}
				break;
			case T_NOTHING:
				_localctx = new NothingLiteralContext(_localctx);
				enterOuterAlt(_localctx, 10);
				{
				setState(209);
				match(T_NOTHING);
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
		public FunctionIdentifierContext functionIdentifier() {
			return getRuleContext(FunctionIdentifierContext.class,0);
		}
		public TerminalNode T_PARENLEFT() { return getToken(ElangParser.T_PARENLEFT, 0); }
		public TerminalNode T_PARENRIGHT() { return getToken(ElangParser.T_PARENRIGHT, 0); }
		public FunctionCallParamsContext functionCallParams() {
			return getRuleContext(FunctionCallParamsContext.class,0);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ElangVisitor ) return ((ElangVisitor<? extends T>)visitor).visitFunctionCall(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionCallContext functionCall() throws RecognitionException {
		FunctionCallContext _localctx = new FunctionCallContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_functionCall);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(212);
			functionIdentifier();
			setState(213);
			match(T_PARENLEFT);
			setState(215);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T_BRACELEFT) | (1L << T_BRACKETLEFT) | (1L << T_FALSE) | (1L << T_NO) | (1L << T_NOTHING) | (1L << T_NULL) | (1L << T_TRUE) | (1L << T_YES) | (1L << ID) | (1L << NUMBER) | (1L << STRING))) != 0)) {
				{
				setState(214);
				functionCallParams();
				}
			}

			setState(217);
			match(T_PARENRIGHT);
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
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public FunctionIdentifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionIdentifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).enterFunctionIdentifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).exitFunctionIdentifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ElangVisitor ) return ((ElangVisitor<? extends T>)visitor).visitFunctionIdentifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionIdentifierContext functionIdentifier() throws RecognitionException {
		FunctionIdentifierContext _localctx = new FunctionIdentifierContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_functionIdentifier);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(219);
			identifier();
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
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ElangVisitor ) return ((ElangVisitor<? extends T>)visitor).visitFunctionCallParams(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionCallParamsContext functionCallParams() throws RecognitionException {
		FunctionCallParamsContext _localctx = new FunctionCallParamsContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_functionCallParams);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(221);
			expr(0);
			setState(225);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T_BRACELEFT) | (1L << T_BRACKETLEFT) | (1L << T_FALSE) | (1L << T_NO) | (1L << T_NOTHING) | (1L << T_NULL) | (1L << T_TRUE) | (1L << T_YES) | (1L << ID) | (1L << NUMBER) | (1L << STRING))) != 0)) {
				{
				{
				setState(222);
				expr(0);
				}
				}
				setState(227);
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
		public TerminalNode T_BRACELEFT() { return getToken(ElangParser.T_BRACELEFT, 0); }
		public TerminalNode T_BRACERIGHT() { return getToken(ElangParser.T_BRACERIGHT, 0); }
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ElangVisitor ) return ((ElangVisitor<? extends T>)visitor).visitObject(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ObjectContext object() throws RecognitionException {
		ObjectContext _localctx = new ObjectContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_object);
		int _la;
		try {
			setState(238);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(228);
				match(T_BRACELEFT);
				setState(230); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(229);
					pair();
					}
					}
					setState(232); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==ID );
				setState(234);
				match(T_BRACERIGHT);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(236);
				match(T_BRACELEFT);
				setState(237);
				match(T_BRACERIGHT);
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
		public TerminalNode T_BRACKETLEFT() { return getToken(ElangParser.T_BRACKETLEFT, 0); }
		public TerminalNode T_BRACKETRIGHT() { return getToken(ElangParser.T_BRACKETRIGHT, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ElangVisitor ) return ((ElangVisitor<? extends T>)visitor).visitArray(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayContext array() throws RecognitionException {
		ArrayContext _localctx = new ArrayContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_array);
		int _la;
		try {
			setState(250);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(240);
				match(T_BRACKETLEFT);
				setState(242); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(241);
					expr(0);
					}
					}
					setState(244); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T_BRACELEFT) | (1L << T_BRACKETLEFT) | (1L << T_FALSE) | (1L << T_NO) | (1L << T_NOTHING) | (1L << T_NULL) | (1L << T_TRUE) | (1L << T_YES) | (1L << ID) | (1L << NUMBER) | (1L << STRING))) != 0) );
				setState(246);
				match(T_BRACKETRIGHT);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(248);
				match(T_BRACKETLEFT);
				setState(249);
				match(T_BRACKETRIGHT);
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
		public TerminalNode ID() { return getToken(ElangParser.ID, 0); }
		public TerminalNode T_COLON() { return getToken(ElangParser.T_COLON, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ElangVisitor ) return ((ElangVisitor<? extends T>)visitor).visitPair(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PairContext pair() throws RecognitionException {
		PairContext _localctx = new PairContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_pair);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(252);
			match(ID);
			setState(253);
			match(T_COLON);
			setState(254);
			expr(0);
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
		public StmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stmt; }
	 
		public StmtContext() { }
		public void copyFrom(StmtContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class BreakStatementContext extends StmtContext {
		public StmtBreakContext stmtBreak() {
			return getRuleContext(StmtBreakContext.class,0);
		}
		public BreakStatementContext(StmtContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).enterBreakStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).exitBreakStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ElangVisitor ) return ((ElangVisitor<? extends T>)visitor).visitBreakStatement(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IfStatementContext extends StmtContext {
		public StmtIfContext stmtIf() {
			return getRuleContext(StmtIfContext.class,0);
		}
		public IfStatementContext(StmtContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).enterIfStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).exitIfStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ElangVisitor ) return ((ElangVisitor<? extends T>)visitor).visitIfStatement(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ReturnStatementContext extends StmtContext {
		public StmtReturnContext stmtReturn() {
			return getRuleContext(StmtReturnContext.class,0);
		}
		public ReturnStatementContext(StmtContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).enterReturnStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).exitReturnStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ElangVisitor ) return ((ElangVisitor<? extends T>)visitor).visitReturnStatement(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IfElseStatementContext extends StmtContext {
		public StmtIfElseContext stmtIfElse() {
			return getRuleContext(StmtIfElseContext.class,0);
		}
		public IfElseStatementContext(StmtContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).enterIfElseStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).exitIfElseStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ElangVisitor ) return ((ElangVisitor<? extends T>)visitor).visitIfElseStatement(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FunctionCallStatementContext extends StmtContext {
		public FunctionCallContext functionCall() {
			return getRuleContext(FunctionCallContext.class,0);
		}
		public FunctionCallStatementContext(StmtContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).enterFunctionCallStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).exitFunctionCallStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ElangVisitor ) return ((ElangVisitor<? extends T>)visitor).visitFunctionCallStatement(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class WhileStatementContext extends StmtContext {
		public StmtWhileContext stmtWhile() {
			return getRuleContext(StmtWhileContext.class,0);
		}
		public WhileStatementContext(StmtContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).enterWhileStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).exitWhileStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ElangVisitor ) return ((ElangVisitor<? extends T>)visitor).visitWhileStatement(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AssignStatementContext extends StmtContext {
		public StmtAssignContext stmtAssign() {
			return getRuleContext(StmtAssignContext.class,0);
		}
		public AssignStatementContext(StmtContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).enterAssignStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).exitAssignStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ElangVisitor ) return ((ElangVisitor<? extends T>)visitor).visitAssignStatement(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ContinueStatementContext extends StmtContext {
		public StmtContinueContext stmtContinue() {
			return getRuleContext(StmtContinueContext.class,0);
		}
		public ContinueStatementContext(StmtContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).enterContinueStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).exitContinueStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ElangVisitor ) return ((ElangVisitor<? extends T>)visitor).visitContinueStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StmtContext stmt() throws RecognitionException {
		StmtContext _localctx = new StmtContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_stmt);
		try {
			setState(264);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				_localctx = new ReturnStatementContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(256);
				stmtReturn();
				}
				break;
			case 2:
				_localctx = new ContinueStatementContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(257);
				stmtContinue();
				}
				break;
			case 3:
				_localctx = new BreakStatementContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(258);
				stmtBreak();
				}
				break;
			case 4:
				_localctx = new AssignStatementContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(259);
				stmtAssign();
				}
				break;
			case 5:
				_localctx = new IfElseStatementContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(260);
				stmtIfElse();
				}
				break;
			case 6:
				_localctx = new IfStatementContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(261);
				stmtIf();
				}
				break;
			case 7:
				_localctx = new WhileStatementContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(262);
				stmtWhile();
				}
				break;
			case 8:
				_localctx = new FunctionCallStatementContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(263);
				functionCall();
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
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode T_ARROWLEFT() { return getToken(ElangParser.T_ARROWLEFT, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ElangVisitor ) return ((ElangVisitor<? extends T>)visitor).visitStmtAssign(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StmtAssignContext stmtAssign() throws RecognitionException {
		StmtAssignContext _localctx = new StmtAssignContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_stmtAssign);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(266);
			identifier();
			setState(267);
			match(T_ARROWLEFT);
			setState(268);
			expr(0);
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
		public TerminalNode T_CONTINUE() { return getToken(ElangParser.T_CONTINUE, 0); }
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ElangVisitor ) return ((ElangVisitor<? extends T>)visitor).visitStmtContinue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StmtContinueContext stmtContinue() throws RecognitionException {
		StmtContinueContext _localctx = new StmtContinueContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_stmtContinue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(270);
			match(T_CONTINUE);
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
		public TerminalNode T_BREAK() { return getToken(ElangParser.T_BREAK, 0); }
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ElangVisitor ) return ((ElangVisitor<? extends T>)visitor).visitStmtBreak(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StmtBreakContext stmtBreak() throws RecognitionException {
		StmtBreakContext _localctx = new StmtBreakContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_stmtBreak);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(272);
			match(T_BREAK);
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
		public TerminalNode T_RETURN() { return getToken(ElangParser.T_RETURN, 0); }
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ElangVisitor ) return ((ElangVisitor<? extends T>)visitor).visitStmtReturn(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StmtReturnContext stmtReturn() throws RecognitionException {
		StmtReturnContext _localctx = new StmtReturnContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_stmtReturn);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(274);
			match(T_RETURN);
			setState(275);
			expr(0);
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

	public static class StmtIfElseContext extends ParserRuleContext {
		public TerminalNode T_IF() { return getToken(ElangParser.T_IF, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode T_THEN() { return getToken(ElangParser.T_THEN, 0); }
		public List<BlockContext> block() {
			return getRuleContexts(BlockContext.class);
		}
		public BlockContext block(int i) {
			return getRuleContext(BlockContext.class,i);
		}
		public TerminalNode T_ELSE() { return getToken(ElangParser.T_ELSE, 0); }
		public TerminalNode T_END() { return getToken(ElangParser.T_END, 0); }
		public StmtIfElseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stmtIfElse; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).enterStmtIfElse(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).exitStmtIfElse(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ElangVisitor ) return ((ElangVisitor<? extends T>)visitor).visitStmtIfElse(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StmtIfElseContext stmtIfElse() throws RecognitionException {
		StmtIfElseContext _localctx = new StmtIfElseContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_stmtIfElse);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(277);
			match(T_IF);
			setState(278);
			expr(0);
			setState(279);
			match(T_THEN);
			setState(280);
			block();
			setState(281);
			match(T_ELSE);
			setState(282);
			block();
			setState(283);
			match(T_END);
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
		public TerminalNode T_IF() { return getToken(ElangParser.T_IF, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode T_THEN() { return getToken(ElangParser.T_THEN, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public TerminalNode T_END() { return getToken(ElangParser.T_END, 0); }
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ElangVisitor ) return ((ElangVisitor<? extends T>)visitor).visitStmtIf(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StmtIfContext stmtIf() throws RecognitionException {
		StmtIfContext _localctx = new StmtIfContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_stmtIf);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(285);
			match(T_IF);
			setState(286);
			expr(0);
			setState(287);
			match(T_THEN);
			setState(288);
			block();
			setState(289);
			match(T_END);
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
		public TerminalNode T_WHILE() { return getToken(ElangParser.T_WHILE, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode T_DO() { return getToken(ElangParser.T_DO, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public TerminalNode T_END() { return getToken(ElangParser.T_END, 0); }
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ElangVisitor ) return ((ElangVisitor<? extends T>)visitor).visitStmtWhile(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StmtWhileContext stmtWhile() throws RecognitionException {
		StmtWhileContext _localctx = new StmtWhileContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_stmtWhile);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(291);
			match(T_WHILE);
			setState(292);
			expr(0);
			setState(293);
			match(T_DO);
			setState(294);
			block();
			setState(295);
			match(T_END);
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

	public static class PhaseDefsContext extends ParserRuleContext {
		public List<PhaseDefContext> phaseDef() {
			return getRuleContexts(PhaseDefContext.class);
		}
		public PhaseDefContext phaseDef(int i) {
			return getRuleContext(PhaseDefContext.class,i);
		}
		public PhaseDefsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_phaseDefs; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).enterPhaseDefs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).exitPhaseDefs(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ElangVisitor ) return ((ElangVisitor<? extends T>)visitor).visitPhaseDefs(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PhaseDefsContext phaseDefs() throws RecognitionException {
		PhaseDefsContext _localctx = new PhaseDefsContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_phaseDefs);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(298); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(297);
				phaseDef();
				}
				}
				setState(300); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__1) | (1L << T__2))) != 0) );
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

	public static class PhaseDefContext extends ParserRuleContext {
		public PhaseDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_phaseDef; }
	 
		public PhaseDefContext() { }
		public void copyFrom(PhaseDefContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class IntermezzoPhaseDefContext extends PhaseDefContext {
		public IntermezzoPhaseContext intermezzoPhase() {
			return getRuleContext(IntermezzoPhaseContext.class,0);
		}
		public IntermezzoPhaseDefContext(PhaseDefContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).enterIntermezzoPhaseDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).exitIntermezzoPhaseDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ElangVisitor ) return ((ElangVisitor<? extends T>)visitor).visitIntermezzoPhaseDef(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class InteractionPhaseDefContext extends PhaseDefContext {
		public InteractionPhaseContext interactionPhase() {
			return getRuleContext(InteractionPhaseContext.class,0);
		}
		public InteractionPhaseDefContext(PhaseDefContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).enterInteractionPhaseDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).exitInteractionPhaseDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ElangVisitor ) return ((ElangVisitor<? extends T>)visitor).visitInteractionPhaseDef(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class InformationPhaseDefContext extends PhaseDefContext {
		public InformationPhaseContext informationPhase() {
			return getRuleContext(InformationPhaseContext.class,0);
		}
		public InformationPhaseDefContext(PhaseDefContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).enterInformationPhaseDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).exitInformationPhaseDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ElangVisitor ) return ((ElangVisitor<? extends T>)visitor).visitInformationPhaseDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PhaseDefContext phaseDef() throws RecognitionException {
		PhaseDefContext _localctx = new PhaseDefContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_phaseDef);
		try {
			setState(305);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
				_localctx = new InformationPhaseDefContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(302);
				informationPhase();
				}
				break;
			case T__1:
				_localctx = new IntermezzoPhaseDefContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(303);
				intermezzoPhase();
				}
				break;
			case T__2:
				_localctx = new InteractionPhaseDefContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(304);
				interactionPhase();
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

	public static class InformationPhaseContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(ElangParser.ID, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public TerminalNode T_END() { return getToken(ElangParser.T_END, 0); }
		public InformationPhaseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_informationPhase; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).enterInformationPhase(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).exitInformationPhase(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ElangVisitor ) return ((ElangVisitor<? extends T>)visitor).visitInformationPhase(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InformationPhaseContext informationPhase() throws RecognitionException {
		InformationPhaseContext _localctx = new InformationPhaseContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_informationPhase);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(307);
			match(T__0);
			setState(308);
			match(ID);
			setState(309);
			block();
			setState(310);
			match(T_END);
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

	public static class IntermezzoPhaseContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(ElangParser.ID, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public TerminalNode T_END() { return getToken(ElangParser.T_END, 0); }
		public IntermezzoPhaseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_intermezzoPhase; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).enterIntermezzoPhase(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).exitIntermezzoPhase(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ElangVisitor ) return ((ElangVisitor<? extends T>)visitor).visitIntermezzoPhase(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IntermezzoPhaseContext intermezzoPhase() throws RecognitionException {
		IntermezzoPhaseContext _localctx = new IntermezzoPhaseContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_intermezzoPhase);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(312);
			match(T__1);
			setState(313);
			match(ID);
			setState(314);
			block();
			setState(315);
			match(T_END);
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

	public static class InteractionPhaseContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(ElangParser.ID, 0); }
		public TerminalNode T_END() { return getToken(ElangParser.T_END, 0); }
		public List<PhaseFunctionDefContext> phaseFunctionDef() {
			return getRuleContexts(PhaseFunctionDefContext.class);
		}
		public PhaseFunctionDefContext phaseFunctionDef(int i) {
			return getRuleContext(PhaseFunctionDefContext.class,i);
		}
		public InteractionPhaseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_interactionPhase; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).enterInteractionPhase(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).exitInteractionPhase(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ElangVisitor ) return ((ElangVisitor<? extends T>)visitor).visitInteractionPhase(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InteractionPhaseContext interactionPhase() throws RecognitionException {
		InteractionPhaseContext _localctx = new InteractionPhaseContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_interactionPhase);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(317);
			match(T__2);
			setState(318);
			match(ID);
			setState(320); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(319);
				phaseFunctionDef();
				}
				}
				setState(322); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==ID );
			setState(324);
			match(T_END);
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

	public static class PhaseSetVarContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(ElangParser.ID, 0); }
		public TerminalNode T_COLON() { return getToken(ElangParser.T_COLON, 0); }
		public PhaseSetVarValueContext phaseSetVarValue() {
			return getRuleContext(PhaseSetVarValueContext.class,0);
		}
		public PhaseSetVarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_phaseSetVar; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).enterPhaseSetVar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).exitPhaseSetVar(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ElangVisitor ) return ((ElangVisitor<? extends T>)visitor).visitPhaseSetVar(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PhaseSetVarContext phaseSetVar() throws RecognitionException {
		PhaseSetVarContext _localctx = new PhaseSetVarContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_phaseSetVar);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(326);
			match(ID);
			setState(327);
			match(T_COLON);
			setState(328);
			phaseSetVarValue();
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

	public static class PhaseSetVarValueContext extends ParserRuleContext {
		public StringContext string() {
			return getRuleContext(StringContext.class,0);
		}
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public TerminalNode T_TRUE() { return getToken(ElangParser.T_TRUE, 0); }
		public TerminalNode T_FALSE() { return getToken(ElangParser.T_FALSE, 0); }
		public PhaseSetVarValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_phaseSetVarValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).enterPhaseSetVarValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).exitPhaseSetVarValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ElangVisitor ) return ((ElangVisitor<? extends T>)visitor).visitPhaseSetVarValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PhaseSetVarValueContext phaseSetVarValue() throws RecognitionException {
		PhaseSetVarValueContext _localctx = new PhaseSetVarValueContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_phaseSetVarValue);
		try {
			setState(334);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case STRING:
				enterOuterAlt(_localctx, 1);
				{
				setState(330);
				string();
				}
				break;
			case NUMBER:
				enterOuterAlt(_localctx, 2);
				{
				setState(331);
				number();
				}
				break;
			case T_TRUE:
				enterOuterAlt(_localctx, 3);
				{
				setState(332);
				match(T_TRUE);
				}
				break;
			case T_FALSE:
				enterOuterAlt(_localctx, 4);
				{
				setState(333);
				match(T_FALSE);
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

	public static class PhaseFunctionDefContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(ElangParser.ID, 0); }
		public PhaseFunctionBodyContext phaseFunctionBody() {
			return getRuleContext(PhaseFunctionBodyContext.class,0);
		}
		public TerminalNode T_END() { return getToken(ElangParser.T_END, 0); }
		public PhaseFunctionDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_phaseFunctionDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).enterPhaseFunctionDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).exitPhaseFunctionDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ElangVisitor ) return ((ElangVisitor<? extends T>)visitor).visitPhaseFunctionDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PhaseFunctionDefContext phaseFunctionDef() throws RecognitionException {
		PhaseFunctionDefContext _localctx = new PhaseFunctionDefContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_phaseFunctionDef);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(336);
			match(ID);
			setState(337);
			phaseFunctionBody();
			setState(338);
			match(T_END);
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

	public static class PhaseFunctionBodyContext extends ParserRuleContext {
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public VarDefsContext varDefs() {
			return getRuleContext(VarDefsContext.class,0);
		}
		public PhaseFunctionBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_phaseFunctionBody; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).enterPhaseFunctionBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).exitPhaseFunctionBody(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ElangVisitor ) return ((ElangVisitor<? extends T>)visitor).visitPhaseFunctionBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PhaseFunctionBodyContext phaseFunctionBody() throws RecognitionException {
		PhaseFunctionBodyContext _localctx = new PhaseFunctionBodyContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_phaseFunctionBody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(341);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T_VAR) {
				{
				setState(340);
				varDefs();
				}
			}

			setState(343);
			block();
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

	public static class TransitionDefsContext extends ParserRuleContext {
		public TerminalNode T_TRANSITION() { return getToken(ElangParser.T_TRANSITION, 0); }
		public TransitionsContext transitions() {
			return getRuleContext(TransitionsContext.class,0);
		}
		public TerminalNode T_END() { return getToken(ElangParser.T_END, 0); }
		public TransitionDefsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_transitionDefs; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).enterTransitionDefs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).exitTransitionDefs(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ElangVisitor ) return ((ElangVisitor<? extends T>)visitor).visitTransitionDefs(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TransitionDefsContext transitionDefs() throws RecognitionException {
		TransitionDefsContext _localctx = new TransitionDefsContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_transitionDefs);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(345);
			match(T_TRANSITION);
			setState(346);
			transitions();
			setState(347);
			match(T_END);
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

	public static class TransitionsContext extends ParserRuleContext {
		public StartTransitionContext startTransition() {
			return getRuleContext(StartTransitionContext.class,0);
		}
		public List<TerminalNode> T_COMMA() { return getTokens(ElangParser.T_COMMA); }
		public TerminalNode T_COMMA(int i) {
			return getToken(ElangParser.T_COMMA, i);
		}
		public OtherTransitionsContext otherTransitions() {
			return getRuleContext(OtherTransitionsContext.class,0);
		}
		public FinalTransitionContext finalTransition() {
			return getRuleContext(FinalTransitionContext.class,0);
		}
		public TransitionsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_transitions; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).enterTransitions(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).exitTransitions(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ElangVisitor ) return ((ElangVisitor<? extends T>)visitor).visitTransitions(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TransitionsContext transitions() throws RecognitionException {
		TransitionsContext _localctx = new TransitionsContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_transitions);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(349);
			startTransition();
			setState(350);
			match(T_COMMA);
			setState(351);
			otherTransitions();
			setState(352);
			match(T_COMMA);
			setState(353);
			finalTransition();
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

	public static class StartTransitionContext extends ParserRuleContext {
		public TerminalNode T_TRANSSTART() { return getToken(ElangParser.T_TRANSSTART, 0); }
		public TerminalNode T_PARENLEFT() { return getToken(ElangParser.T_PARENLEFT, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode T_PARENRIGHT() { return getToken(ElangParser.T_PARENRIGHT, 0); }
		public StartTransitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_startTransition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).enterStartTransition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).exitStartTransition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ElangVisitor ) return ((ElangVisitor<? extends T>)visitor).visitStartTransition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StartTransitionContext startTransition() throws RecognitionException {
		StartTransitionContext _localctx = new StartTransitionContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_startTransition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(355);
			match(T_TRANSSTART);
			setState(356);
			match(T_PARENLEFT);
			setState(357);
			identifier();
			setState(358);
			match(T_PARENRIGHT);
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

	public static class OtherTransitionsContext extends ParserRuleContext {
		public List<TransitionContext> transition() {
			return getRuleContexts(TransitionContext.class);
		}
		public TransitionContext transition(int i) {
			return getRuleContext(TransitionContext.class,i);
		}
		public List<TerminalNode> T_COMMA() { return getTokens(ElangParser.T_COMMA); }
		public TerminalNode T_COMMA(int i) {
			return getToken(ElangParser.T_COMMA, i);
		}
		public OtherTransitionsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_otherTransitions; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).enterOtherTransitions(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).exitOtherTransitions(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ElangVisitor ) return ((ElangVisitor<? extends T>)visitor).visitOtherTransitions(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OtherTransitionsContext otherTransitions() throws RecognitionException {
		OtherTransitionsContext _localctx = new OtherTransitionsContext(_ctx, getState());
		enterRule(_localctx, 84, RULE_otherTransitions);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(360);
			transition();
			setState(365);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(361);
					match(T_COMMA);
					setState(362);
					transition();
					}
					} 
				}
				setState(367);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
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

	public static class FinalTransitionContext extends ParserRuleContext {
		public TerminalNode T_TRANSFINAL() { return getToken(ElangParser.T_TRANSFINAL, 0); }
		public TerminalNode T_PARENLEFT() { return getToken(ElangParser.T_PARENLEFT, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode T_PARENRIGHT() { return getToken(ElangParser.T_PARENRIGHT, 0); }
		public FinalTransitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_finalTransition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).enterFinalTransition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).exitFinalTransition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ElangVisitor ) return ((ElangVisitor<? extends T>)visitor).visitFinalTransition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FinalTransitionContext finalTransition() throws RecognitionException {
		FinalTransitionContext _localctx = new FinalTransitionContext(_ctx, getState());
		enterRule(_localctx, 86, RULE_finalTransition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(368);
			match(T_TRANSFINAL);
			setState(369);
			match(T_PARENLEFT);
			setState(370);
			identifier();
			setState(371);
			match(T_PARENRIGHT);
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

	public static class TransitionContext extends ParserRuleContext {
		public TransitionSrcContext transitionSrc() {
			return getRuleContext(TransitionSrcContext.class,0);
		}
		public TerminalNode T_ARROWRIGHT() { return getToken(ElangParser.T_ARROWRIGHT, 0); }
		public TransitionToContext transitionTo() {
			return getRuleContext(TransitionToContext.class,0);
		}
		public TerminalNode T_IF() { return getToken(ElangParser.T_IF, 0); }
		public TransitionCondContext transitionCond() {
			return getRuleContext(TransitionCondContext.class,0);
		}
		public TransitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_transition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).enterTransition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).exitTransition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ElangVisitor ) return ((ElangVisitor<? extends T>)visitor).visitTransition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TransitionContext transition() throws RecognitionException {
		TransitionContext _localctx = new TransitionContext(_ctx, getState());
		enterRule(_localctx, 88, RULE_transition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(373);
			transitionSrc();
			setState(374);
			match(T_ARROWRIGHT);
			setState(375);
			transitionTo();
			setState(378);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T_IF) {
				{
				setState(376);
				match(T_IF);
				setState(377);
				transitionCond();
				}
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

	public static class TransitionSrcContext extends ParserRuleContext {
		public TransitionSrcContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_transitionSrc; }
	 
		public TransitionSrcContext() { }
		public void copyFrom(TransitionSrcContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class TransitionSrcIdContext extends TransitionSrcContext {
		public TerminalNode ID() { return getToken(ElangParser.ID, 0); }
		public TransitionSrcIdContext(TransitionSrcContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).enterTransitionSrcId(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).exitTransitionSrcId(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ElangVisitor ) return ((ElangVisitor<? extends T>)visitor).visitTransitionSrcId(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TransitionSrcRepeatContext extends TransitionSrcContext {
		public TerminalNode T_TRANSNAMEREPEAT() { return getToken(ElangParser.T_TRANSNAMEREPEAT, 0); }
		public TransitionSrcRepeatContext(TransitionSrcContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).enterTransitionSrcRepeat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).exitTransitionSrcRepeat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ElangVisitor ) return ((ElangVisitor<? extends T>)visitor).visitTransitionSrcRepeat(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TransitionSrcContext transitionSrc() throws RecognitionException {
		TransitionSrcContext _localctx = new TransitionSrcContext(_ctx, getState());
		enterRule(_localctx, 90, RULE_transitionSrc);
		try {
			setState(382);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T_TRANSNAMEREPEAT:
				_localctx = new TransitionSrcRepeatContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(380);
				match(T_TRANSNAMEREPEAT);
				}
				break;
			case ID:
				_localctx = new TransitionSrcIdContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(381);
				match(ID);
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

	public static class TransitionToContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(ElangParser.ID, 0); }
		public TransitionToContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_transitionTo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).enterTransitionTo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).exitTransitionTo(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ElangVisitor ) return ((ElangVisitor<? extends T>)visitor).visitTransitionTo(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TransitionToContext transitionTo() throws RecognitionException {
		TransitionToContext _localctx = new TransitionToContext(_ctx, getState());
		enterRule(_localctx, 92, RULE_transitionTo);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(384);
			match(ID);
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

	public static class TransitionCondContext extends ParserRuleContext {
		public TransitionCondContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_transitionCond; }
	 
		public TransitionCondContext() { }
		public void copyFrom(TransitionCondContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class TransitionCondVarContext extends TransitionCondContext {
		public TerminalNode T_PARENLEFT() { return getToken(ElangParser.T_PARENLEFT, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode T_PARENRIGHT() { return getToken(ElangParser.T_PARENRIGHT, 0); }
		public TransitionCondVarContext(TransitionCondContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).enterTransitionCondVar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).exitTransitionCondVar(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ElangVisitor ) return ((ElangVisitor<? extends T>)visitor).visitTransitionCondVar(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TransitionCondFcallContext extends TransitionCondContext {
		public TerminalNode T_PARENLEFT() { return getToken(ElangParser.T_PARENLEFT, 0); }
		public FunctionCallContext functionCall() {
			return getRuleContext(FunctionCallContext.class,0);
		}
		public TerminalNode T_PARENRIGHT() { return getToken(ElangParser.T_PARENRIGHT, 0); }
		public TransitionCondFcallContext(TransitionCondContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).enterTransitionCondFcall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ElangListener ) ((ElangListener)listener).exitTransitionCondFcall(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ElangVisitor ) return ((ElangVisitor<? extends T>)visitor).visitTransitionCondFcall(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TransitionCondContext transitionCond() throws RecognitionException {
		TransitionCondContext _localctx = new TransitionCondContext(_ctx, getState());
		enterRule(_localctx, 94, RULE_transitionCond);
		try {
			setState(394);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,28,_ctx) ) {
			case 1:
				_localctx = new TransitionCondFcallContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(386);
				match(T_PARENLEFT);
				setState(387);
				functionCall();
				setState(388);
				match(T_PARENRIGHT);
				}
				break;
			case 2:
				_localctx = new TransitionCondVarContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(390);
				match(T_PARENLEFT);
				setState(391);
				identifier();
				setState(392);
				match(T_PARENRIGHT);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ElangVisitor ) return ((ElangVisitor<? extends T>)visitor).visitNumber(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NumberContext number() throws RecognitionException {
		NumberContext _localctx = new NumberContext(_ctx, getState());
		enterRule(_localctx, 96, RULE_number);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(396);
			match(NUMBER);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ElangVisitor ) return ((ElangVisitor<? extends T>)visitor).visitString(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StringContext string() throws RecognitionException {
		StringContext _localctx = new StringContext(_ctx, getState());
		enterRule(_localctx, 98, RULE_string);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(398);
			match(STRING);
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
		case 13:
			return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 5);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3:\u0193\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\3\2\7\2"+
		"h\n\2\f\2\16\2k\13\2\3\2\3\2\5\2o\n\2\3\2\3\2\3\3\3\3\3\3\5\3v\n\3\3\4"+
		"\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\5\6\u0086\n\6\3\6"+
		"\3\6\3\6\3\6\3\7\3\7\7\7\u008e\n\7\f\7\16\7\u0091\13\7\3\b\3\b\3\t\5\t"+
		"\u0096\n\t\3\t\5\t\u0099\n\t\3\t\3\t\3\n\6\n\u009e\n\n\r\n\16\n\u009f"+
		"\3\13\3\13\3\13\3\13\3\13\3\f\6\f\u00a8\n\f\r\f\16\f\u00a9\3\r\3\r\3\r"+
		"\3\r\3\r\3\16\6\16\u00b2\n\16\r\16\16\16\u00b3\3\17\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\3\17\3\17\5\17\u00bf\n\17\3\17\3\17\3\17\7\17\u00c4\n\17\f"+
		"\17\16\17\u00c7\13\17\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\3\21\3\21\5\21\u00d5\n\21\3\22\3\22\3\22\5\22\u00da\n\22\3\22\3\22\3"+
		"\23\3\23\3\24\3\24\7\24\u00e2\n\24\f\24\16\24\u00e5\13\24\3\25\3\25\6"+
		"\25\u00e9\n\25\r\25\16\25\u00ea\3\25\3\25\3\25\3\25\5\25\u00f1\n\25\3"+
		"\26\3\26\6\26\u00f5\n\26\r\26\16\26\u00f6\3\26\3\26\3\26\3\26\5\26\u00fd"+
		"\n\26\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\5\30"+
		"\u010b\n\30\3\31\3\31\3\31\3\31\3\32\3\32\3\33\3\33\3\34\3\34\3\34\3\35"+
		"\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\36\3\36\3\36\3\36\3\36\3\36\3\37"+
		"\3\37\3\37\3\37\3\37\3\37\3 \6 \u012d\n \r \16 \u012e\3!\3!\3!\5!\u0134"+
		"\n!\3\"\3\"\3\"\3\"\3\"\3#\3#\3#\3#\3#\3$\3$\3$\6$\u0143\n$\r$\16$\u0144"+
		"\3$\3$\3%\3%\3%\3%\3&\3&\3&\3&\5&\u0151\n&\3\'\3\'\3\'\3\'\3(\5(\u0158"+
		"\n(\3(\3(\3)\3)\3)\3)\3*\3*\3*\3*\3*\3*\3+\3+\3+\3+\3+\3,\3,\3,\7,\u016e"+
		"\n,\f,\16,\u0171\13,\3-\3-\3-\3-\3-\3.\3.\3.\3.\3.\5.\u017d\n.\3/\3/\5"+
		"/\u0181\n/\3\60\3\60\3\61\3\61\3\61\3\61\3\61\3\61\3\61\3\61\5\61\u018d"+
		"\n\61\3\62\3\62\3\63\3\63\3\63\2\3\34\64\2\4\6\b\n\f\16\20\22\24\26\30"+
		"\32\34\36 \"$&(*,.\60\62\64\668:<>@BDFHJLNPRTVXZ\\^`bd\2\2\2\u0191\2i"+
		"\3\2\2\2\4u\3\2\2\2\6w\3\2\2\2\b|\3\2\2\2\n\u0081\3\2\2\2\f\u008b\3\2"+
		"\2\2\16\u0092\3\2\2\2\20\u0095\3\2\2\2\22\u009d\3\2\2\2\24\u00a1\3\2\2"+
		"\2\26\u00a7\3\2\2\2\30\u00ab\3\2\2\2\32\u00b1\3\2\2\2\34\u00be\3\2\2\2"+
		"\36\u00c8\3\2\2\2 \u00d4\3\2\2\2\"\u00d6\3\2\2\2$\u00dd\3\2\2\2&\u00df"+
		"\3\2\2\2(\u00f0\3\2\2\2*\u00fc\3\2\2\2,\u00fe\3\2\2\2.\u010a\3\2\2\2\60"+
		"\u010c\3\2\2\2\62\u0110\3\2\2\2\64\u0112\3\2\2\2\66\u0114\3\2\2\28\u0117"+
		"\3\2\2\2:\u011f\3\2\2\2<\u0125\3\2\2\2>\u012c\3\2\2\2@\u0133\3\2\2\2B"+
		"\u0135\3\2\2\2D\u013a\3\2\2\2F\u013f\3\2\2\2H\u0148\3\2\2\2J\u0150\3\2"+
		"\2\2L\u0152\3\2\2\2N\u0157\3\2\2\2P\u015b\3\2\2\2R\u015f\3\2\2\2T\u0165"+
		"\3\2\2\2V\u016a\3\2\2\2X\u0172\3\2\2\2Z\u0177\3\2\2\2\\\u0180\3\2\2\2"+
		"^\u0182\3\2\2\2`\u018c\3\2\2\2b\u018e\3\2\2\2d\u0190\3\2\2\2fh\5\4\3\2"+
		"gf\3\2\2\2hk\3\2\2\2ig\3\2\2\2ij\3\2\2\2jl\3\2\2\2ki\3\2\2\2ln\5> \2m"+
		"o\5P)\2nm\3\2\2\2no\3\2\2\2op\3\2\2\2pq\7\2\2\3q\3\3\2\2\2rv\5\6\4\2s"+
		"v\5\b\5\2tv\5\n\6\2ur\3\2\2\2us\3\2\2\2ut\3\2\2\2v\5\3\2\2\2wx\7!\2\2"+
		"xy\5\36\20\2yz\7\20\2\2z{\5\34\17\2{\7\3\2\2\2|}\7\61\2\2}~\5\36\20\2"+
		"~\177\7\20\2\2\177\u0080\5\34\17\2\u0080\t\3\2\2\2\u0081\u0082\7\37\2"+
		"\2\u0082\u0083\5\36\20\2\u0083\u0085\7\'\2\2\u0084\u0086\5\f\7\2\u0085"+
		"\u0084\3\2\2\2\u0085\u0086\3\2\2\2\u0086\u0087\3\2\2\2\u0087\u0088\7("+
		"\2\2\u0088\u0089\5\20\t\2\u0089\u008a\7\35\2\2\u008a\13\3\2\2\2\u008b"+
		"\u008f\5\16\b\2\u008c\u008e\5\16\b\2\u008d\u008c\3\2\2\2\u008e\u0091\3"+
		"\2\2\2\u008f\u008d\3\2\2\2\u008f\u0090\3\2\2\2\u0090\r\3\2\2\2\u0091\u008f"+
		"\3\2\2\2\u0092\u0093\7\65\2\2\u0093\17\3\2\2\2\u0094\u0096\5\22\n\2\u0095"+
		"\u0094\3\2\2\2\u0095\u0096\3\2\2\2\u0096\u0098\3\2\2\2\u0097\u0099\5\26"+
		"\f\2\u0098\u0097\3\2\2\2\u0098\u0099\3\2\2\2\u0099\u009a\3\2\2\2\u009a"+
		"\u009b\5\32\16\2\u009b\21\3\2\2\2\u009c\u009e\5\24\13\2\u009d\u009c\3"+
		"\2\2\2\u009e\u009f\3\2\2\2\u009f\u009d\3\2\2\2\u009f\u00a0\3\2\2\2\u00a0"+
		"\23\3\2\2\2\u00a1\u00a2\7 \2\2\u00a2\u00a3\5\36\20\2\u00a3\u00a4\7\20"+
		"\2\2\u00a4\u00a5\5\34\17\2\u00a5\25\3\2\2\2\u00a6\u00a8\5\30\r\2\u00a7"+
		"\u00a6\3\2\2\2\u00a8\u00a9\3\2\2\2\u00a9\u00a7\3\2\2\2\u00a9\u00aa\3\2"+
		"\2\2\u00aa\27\3\2\2\2\u00ab\u00ac\7\62\2\2\u00ac\u00ad\5\36\20\2\u00ad"+
		"\u00ae\7\20\2\2\u00ae\u00af\5\34\17\2\u00af\31\3\2\2\2\u00b0\u00b2\5."+
		"\30\2\u00b1\u00b0\3\2\2\2\u00b2\u00b3\3\2\2\2\u00b3\u00b1\3\2\2\2\u00b3"+
		"\u00b4\3\2\2\2\u00b4\33\3\2\2\2\u00b5\u00b6\b\17\1\2\u00b6\u00bf\5\"\22"+
		"\2\u00b7\u00b8\5\36\20\2\u00b8\u00b9\7\24\2\2\u00b9\u00ba\5\34\17\2\u00ba"+
		"\u00bb\7\25\2\2\u00bb\u00bf\3\2\2\2\u00bc\u00bf\5\36\20\2\u00bd\u00bf"+
		"\5 \21\2\u00be\u00b5\3\2\2\2\u00be\u00b7\3\2\2\2\u00be\u00bc\3\2\2\2\u00be"+
		"\u00bd\3\2\2\2\u00bf\u00c5\3\2\2\2\u00c0\u00c1\f\7\2\2\u00c1\u00c2\7\33"+
		"\2\2\u00c2\u00c4\7\65\2\2\u00c3\u00c0\3\2\2\2\u00c4\u00c7\3\2\2\2\u00c5"+
		"\u00c3\3\2\2\2\u00c5\u00c6\3\2\2\2\u00c6\35\3\2\2\2\u00c7\u00c5\3\2\2"+
		"\2\u00c8\u00c9\7\65\2\2\u00c9\37\3\2\2\2\u00ca\u00d5\5d\63\2\u00cb\u00d5"+
		"\5b\62\2\u00cc\u00d5\5(\25\2\u00cd\u00d5\5*\26\2\u00ce\u00d5\7\60\2\2"+
		"\u00cf\u00d5\7\36\2\2\u00d0\u00d5\7\64\2\2\u00d1\u00d5\7$\2\2\u00d2\u00d5"+
		"\7&\2\2\u00d3\u00d5\7%\2\2\u00d4\u00ca\3\2\2\2\u00d4\u00cb\3\2\2\2\u00d4"+
		"\u00cc\3\2\2\2\u00d4\u00cd\3\2\2\2\u00d4\u00ce\3\2\2\2\u00d4\u00cf\3\2"+
		"\2\2\u00d4\u00d0\3\2\2\2\u00d4\u00d1\3\2\2\2\u00d4\u00d2\3\2\2\2\u00d4"+
		"\u00d3\3\2\2\2\u00d5!\3\2\2\2\u00d6\u00d7\5$\23\2\u00d7\u00d9\7\'\2\2"+
		"\u00d8\u00da\5&\24\2\u00d9\u00d8\3\2\2\2\u00d9\u00da\3\2\2\2\u00da\u00db"+
		"\3\2\2\2\u00db\u00dc\7(\2\2\u00dc#\3\2\2\2\u00dd\u00de\5\36\20\2\u00de"+
		"%\3\2\2\2\u00df\u00e3\5\34\17\2\u00e0\u00e2\5\34\17\2\u00e1\u00e0\3\2"+
		"\2\2\u00e2\u00e5\3\2\2\2\u00e3\u00e1\3\2\2\2\u00e3\u00e4\3\2\2\2\u00e4"+
		"\'\3\2\2\2\u00e5\u00e3\3\2\2\2\u00e6\u00e8\7\22\2\2\u00e7\u00e9\5,\27"+
		"\2\u00e8\u00e7\3\2\2\2\u00e9\u00ea\3\2\2\2\u00ea\u00e8\3\2\2\2\u00ea\u00eb"+
		"\3\2\2\2\u00eb\u00ec\3\2\2\2\u00ec\u00ed\7\23\2\2\u00ed\u00f1\3\2\2\2"+
		"\u00ee\u00ef\7\22\2\2\u00ef\u00f1\7\23\2\2\u00f0\u00e6\3\2\2\2\u00f0\u00ee"+
		"\3\2\2\2\u00f1)\3\2\2\2\u00f2\u00f4\7\24\2\2\u00f3\u00f5\5\34\17\2\u00f4"+
		"\u00f3\3\2\2\2\u00f5\u00f6\3\2\2\2\u00f6\u00f4\3\2\2\2\u00f6\u00f7\3\2"+
		"\2\2\u00f7\u00f8\3\2\2\2\u00f8\u00f9\7\25\2\2\u00f9\u00fd\3\2\2\2\u00fa"+
		"\u00fb\7\24\2\2\u00fb\u00fd\7\25\2\2\u00fc\u00f2\3\2\2\2\u00fc\u00fa\3"+
		"\2\2\2\u00fd+\3\2\2\2\u00fe\u00ff\7\65\2\2\u00ff\u0100\7\27\2\2\u0100"+
		"\u0101\5\34\17\2\u0101-\3\2\2\2\u0102\u010b\5\66\34\2\u0103\u010b\5\62"+
		"\32\2\u0104\u010b\5\64\33\2\u0105\u010b\5\60\31\2\u0106\u010b\58\35\2"+
		"\u0107\u010b\5:\36\2\u0108\u010b\5<\37\2\u0109\u010b\5\"\22\2\u010a\u0102"+
		"\3\2\2\2\u010a\u0103\3\2\2\2\u010a\u0104\3\2\2\2\u010a\u0105\3\2\2\2\u010a"+
		"\u0106\3\2\2\2\u010a\u0107\3\2\2\2\u010a\u0108\3\2\2\2\u010a\u0109\3\2"+
		"\2\2\u010b/\3\2\2\2\u010c\u010d\5\36\20\2\u010d\u010e\7\20\2\2\u010e\u010f"+
		"\5\34\17\2\u010f\61\3\2\2\2\u0110\u0111\7\31\2\2\u0111\63\3\2\2\2\u0112"+
		"\u0113\7\26\2\2\u0113\65\3\2\2\2\u0114\u0115\7+\2\2\u0115\u0116\5\34\17"+
		"\2\u0116\67\3\2\2\2\u0117\u0118\7#\2\2\u0118\u0119\5\34\17\2\u0119\u011a"+
		"\7,\2\2\u011a\u011b\5\32\16\2\u011b\u011c\7\34\2\2\u011c\u011d\5\32\16"+
		"\2\u011d\u011e\7\35\2\2\u011e9\3\2\2\2\u011f\u0120\7#\2\2\u0120\u0121"+
		"\5\34\17\2\u0121\u0122\7,\2\2\u0122\u0123\5\32\16\2\u0123\u0124\7\35\2"+
		"\2\u0124;\3\2\2\2\u0125\u0126\7\63\2\2\u0126\u0127\5\34\17\2\u0127\u0128"+
		"\7\32\2\2\u0128\u0129\5\32\16\2\u0129\u012a\7\35\2\2\u012a=\3\2\2\2\u012b"+
		"\u012d\5@!\2\u012c\u012b\3\2\2\2\u012d\u012e\3\2\2\2\u012e\u012c\3\2\2"+
		"\2\u012e\u012f\3\2\2\2\u012f?\3\2\2\2\u0130\u0134\5B\"\2\u0131\u0134\5"+
		"D#\2\u0132\u0134\5F$\2\u0133\u0130\3\2\2\2\u0133\u0131\3\2\2\2\u0133\u0132"+
		"\3\2\2\2\u0134A\3\2\2\2\u0135\u0136\7\3\2\2\u0136\u0137\7\65\2\2\u0137"+
		"\u0138\5\32\16\2\u0138\u0139\7\35\2\2\u0139C\3\2\2\2\u013a\u013b\7\4\2"+
		"\2\u013b\u013c\7\65\2\2\u013c\u013d\5\32\16\2\u013d\u013e\7\35\2\2\u013e"+
		"E\3\2\2\2\u013f\u0140\7\5\2\2\u0140\u0142\7\65\2\2\u0141\u0143\5L\'\2"+
		"\u0142\u0141\3\2\2\2\u0143\u0144\3\2\2\2\u0144\u0142\3\2\2\2\u0144\u0145"+
		"\3\2\2\2\u0145\u0146\3\2\2\2\u0146\u0147\7\35\2\2\u0147G\3\2\2\2\u0148"+
		"\u0149\7\65\2\2\u0149\u014a\7\27\2\2\u014a\u014b\5J&\2\u014bI\3\2\2\2"+
		"\u014c\u0151\5d\63\2\u014d\u0151\5b\62\2\u014e\u0151\7\60\2\2\u014f\u0151"+
		"\7\36\2\2\u0150\u014c\3\2\2\2\u0150\u014d\3\2\2\2\u0150\u014e\3\2\2\2"+
		"\u0150\u014f\3\2\2\2\u0151K\3\2\2\2\u0152\u0153\7\65\2\2\u0153\u0154\5"+
		"N(\2\u0154\u0155\7\35\2\2\u0155M\3\2\2\2\u0156\u0158\5\26\f\2\u0157\u0156"+
		"\3\2\2\2\u0157\u0158\3\2\2\2\u0158\u0159\3\2\2\2\u0159\u015a\5\32\16\2"+
		"\u015aO\3\2\2\2\u015b\u015c\7.\2\2\u015c\u015d\5R*\2\u015d\u015e\7\35"+
		"\2\2\u015eQ\3\2\2\2\u015f\u0160\5T+\2\u0160\u0161\7\30\2\2\u0161\u0162"+
		"\5V,\2\u0162\u0163\7\30\2\2\u0163\u0164\5X-\2\u0164S\3\2\2\2\u0165\u0166"+
		"\7/\2\2\u0166\u0167\7\'\2\2\u0167\u0168\5\36\20\2\u0168\u0169\7(\2\2\u0169"+
		"U\3\2\2\2\u016a\u016f\5Z.\2\u016b\u016c\7\30\2\2\u016c\u016e\5Z.\2\u016d"+
		"\u016b\3\2\2\2\u016e\u0171\3\2\2\2\u016f\u016d\3\2\2\2\u016f\u0170\3\2"+
		"\2\2\u0170W\3\2\2\2\u0171\u016f\3\2\2\2\u0172\u0173\7-\2\2\u0173\u0174"+
		"\7\'\2\2\u0174\u0175\5\36\20\2\u0175\u0176\7(\2\2\u0176Y\3\2\2\2\u0177"+
		"\u0178\5\\/\2\u0178\u0179\7\21\2\2\u0179\u017c\5^\60\2\u017a\u017b\7#"+
		"\2\2\u017b\u017d\5`\61\2\u017c\u017a\3\2\2\2\u017c\u017d\3\2\2\2\u017d"+
		"[\3\2\2\2\u017e\u0181\7\17\2\2\u017f\u0181\7\65\2\2\u0180\u017e\3\2\2"+
		"\2\u0180\u017f\3\2\2\2\u0181]\3\2\2\2\u0182\u0183\7\65\2\2\u0183_\3\2"+
		"\2\2\u0184\u0185\7\'\2\2\u0185\u0186\5\"\22\2\u0186\u0187\7(\2\2\u0187"+
		"\u018d\3\2\2\2\u0188\u0189\7\'\2\2\u0189\u018a\5\36\20\2\u018a\u018b\7"+
		"(\2\2\u018b\u018d\3\2\2\2\u018c\u0184\3\2\2\2\u018c\u0188\3\2\2\2\u018d"+
		"a\3\2\2\2\u018e\u018f\7\66\2\2\u018fc\3\2\2\2\u0190\u0191\7\67\2\2\u0191"+
		"e\3\2\2\2\37inu\u0085\u008f\u0095\u0098\u009f\u00a9\u00b3\u00be\u00c5"+
		"\u00d4\u00d9\u00e3\u00ea\u00f0\u00f6\u00fc\u010a\u012e\u0133\u0144\u0150"+
		"\u0157\u016f\u017c\u0180\u018c";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}