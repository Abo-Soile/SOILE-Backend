// Generated from soile-qmarkup/spec/TypeSpec.g4 by ANTLR 4.0

package fi.abo.kogni.soile2.qmarkup.typespec;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class TypeSpecLexer extends Lexer {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__9=1, T__8=2, T__7=3, T__6=4, T__5=5, T__4=6, T__3=7, T__2=8, T__1=9, 
		T__0=10, TYPE_INTEGER=11, TYPE_FLOAT=12, TYPE_STRING=13, TYPE_BOOLEAN=14, 
		BOOLEAN=15, INTEGER=16, FLOAT=17, STRING=18, REQUIRED=19, ID=20, WS=21;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"']'", "'{'", "')'", "'command'", "','", "'['", "'*'", "':'", "'('", "'}'", 
		"'Integer'", "'Float'", "'String'", "'Boolean'", "BOOLEAN", "INTEGER", 
		"FLOAT", "STRING", "'required'", "ID", "WS"
	};
	public static final String[] ruleNames = {
		"T__9", "T__8", "T__7", "T__6", "T__5", "T__4", "T__3", "T__2", "T__1", 
		"T__0", "TYPE_INTEGER", "TYPE_FLOAT", "TYPE_STRING", "TYPE_BOOLEAN", "BOOLEAN", 
		"INTEGER", "FLOAT", "STRING", "REQUIRED", "ID", "INT", "DIGIT", "WS"
	};


	public TypeSpecLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "TypeSpec.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 22: WS_action((RuleContext)_localctx, actionIndex); break;
		}
	}
	private void WS_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0: skip();  break;
		}
	}

	public static final String _serializedATN =
		"\2\4\27\u00b7\b\1\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b"+
		"\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20"+
		"\t\20\4\21\t\21\4\22\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27"+
		"\t\27\4\30\t\30\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3"+
		"\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\f\3\f\3"+
		"\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3"+
		"\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3"+
		"\20\3\20\3\20\3\20\5\20r\n\20\3\21\5\21u\n\21\3\21\3\21\3\22\6\22z\n\22"+
		"\r\22\16\22{\3\22\3\22\7\22\u0080\n\22\f\22\16\22\u0083\13\22\3\22\6\22"+
		"\u0086\n\22\r\22\16\22\u0087\5\22\u008a\n\22\3\23\3\23\7\23\u008e\n\23"+
		"\f\23\16\23\u0091\13\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3"+
		"\24\3\24\3\25\3\25\7\25\u00a0\n\25\f\25\16\25\u00a3\13\25\3\26\3\26\3"+
		"\26\7\26\u00a8\n\26\f\26\16\26\u00ab\13\26\5\26\u00ad\n\26\3\27\3\27\3"+
		"\30\6\30\u00b2\n\30\r\30\16\30\u00b3\3\30\3\30\2\31\3\3\1\5\4\1\7\5\1"+
		"\t\6\1\13\7\1\r\b\1\17\t\1\21\n\1\23\13\1\25\f\1\27\r\1\31\16\1\33\17"+
		"\1\35\20\1\37\21\1!\22\1#\23\1%\24\1\'\25\1)\26\1+\2\1-\2\1/\27\2\3\2"+
		"\t\7\"\"\62;C\\aac|\5C\\aac|\6\62;C\\aac|\3\63;\3\62;\3\62;\5\13\f\17"+
		"\17\"\"\u00bf\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2"+
		"\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2"+
		"\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2"+
		"\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2/\3\2\2\2\3\61\3\2"+
		"\2\2\5\63\3\2\2\2\7\65\3\2\2\2\t\67\3\2\2\2\13?\3\2\2\2\rA\3\2\2\2\17"+
		"C\3\2\2\2\21E\3\2\2\2\23G\3\2\2\2\25I\3\2\2\2\27K\3\2\2\2\31S\3\2\2\2"+
		"\33Y\3\2\2\2\35`\3\2\2\2\37q\3\2\2\2!t\3\2\2\2#\u0089\3\2\2\2%\u008b\3"+
		"\2\2\2\'\u0094\3\2\2\2)\u009d\3\2\2\2+\u00ac\3\2\2\2-\u00ae\3\2\2\2/\u00b1"+
		"\3\2\2\2\61\62\7_\2\2\62\4\3\2\2\2\63\64\7}\2\2\64\6\3\2\2\2\65\66\7+"+
		"\2\2\66\b\3\2\2\2\678\7e\2\289\7q\2\29:\7o\2\2:;\7o\2\2;<\7c\2\2<=\7p"+
		"\2\2=>\7f\2\2>\n\3\2\2\2?@\7.\2\2@\f\3\2\2\2AB\7]\2\2B\16\3\2\2\2CD\7"+
		",\2\2D\20\3\2\2\2EF\7<\2\2F\22\3\2\2\2GH\7*\2\2H\24\3\2\2\2IJ\7\177\2"+
		"\2J\26\3\2\2\2KL\7K\2\2LM\7p\2\2MN\7v\2\2NO\7g\2\2OP\7i\2\2PQ\7g\2\2Q"+
		"R\7t\2\2R\30\3\2\2\2ST\7H\2\2TU\7n\2\2UV\7q\2\2VW\7c\2\2WX\7v\2\2X\32"+
		"\3\2\2\2YZ\7U\2\2Z[\7v\2\2[\\\7t\2\2\\]\7k\2\2]^\7p\2\2^_\7i\2\2_\34\3"+
		"\2\2\2`a\7D\2\2ab\7q\2\2bc\7q\2\2cd\7n\2\2de\7g\2\2ef\7c\2\2fg\7p\2\2"+
		"g\36\3\2\2\2hi\7v\2\2ij\7t\2\2jk\7w\2\2kr\7g\2\2lm\7h\2\2mn\7c\2\2no\7"+
		"n\2\2op\7u\2\2pr\7g\2\2qh\3\2\2\2ql\3\2\2\2r \3\2\2\2su\7/\2\2ts\3\2\2"+
		"\2tu\3\2\2\2uv\3\2\2\2vw\5+\26\2w\"\3\2\2\2xz\5-\27\2yx\3\2\2\2z{\3\2"+
		"\2\2{y\3\2\2\2{|\3\2\2\2|}\3\2\2\2}\u0081\7\60\2\2~\u0080\5-\27\2\177"+
		"~\3\2\2\2\u0080\u0083\3\2\2\2\u0081\177\3\2\2\2\u0081\u0082\3\2\2\2\u0082"+
		"\u008a\3\2\2\2\u0083\u0081\3\2\2\2\u0084\u0086\5-\27\2\u0085\u0084\3\2"+
		"\2\2\u0086\u0087\3\2\2\2\u0087\u0085\3\2\2\2\u0087\u0088\3\2\2\2\u0088"+
		"\u008a\3\2\2\2\u0089y\3\2\2\2\u0089\u0085\3\2\2\2\u008a$\3\2\2\2\u008b"+
		"\u008f\7$\2\2\u008c\u008e\t\2\2\2\u008d\u008c\3\2\2\2\u008e\u0091\3\2"+
		"\2\2\u008f\u008d\3\2\2\2\u008f\u0090\3\2\2\2\u0090\u0092\3\2\2\2\u0091"+
		"\u008f\3\2\2\2\u0092\u0093\7$\2\2\u0093&\3\2\2\2\u0094\u0095\7t\2\2\u0095"+
		"\u0096\7g\2\2\u0096\u0097\7s\2\2\u0097\u0098\7w\2\2\u0098\u0099\7k\2\2"+
		"\u0099\u009a\7t\2\2\u009a\u009b\7g\2\2\u009b\u009c\7f\2\2\u009c(\3\2\2"+
		"\2\u009d\u00a1\t\3\2\2\u009e\u00a0\t\4\2\2\u009f\u009e\3\2\2\2\u00a0\u00a3"+
		"\3\2\2\2\u00a1\u009f\3\2\2\2\u00a1\u00a2\3\2\2\2\u00a2*\3\2\2\2\u00a3"+
		"\u00a1\3\2\2\2\u00a4\u00ad\7\62\2\2\u00a5\u00a9\t\5\2\2\u00a6\u00a8\t"+
		"\6\2\2\u00a7\u00a6\3\2\2\2\u00a8\u00ab\3\2\2\2\u00a9\u00a7\3\2\2\2\u00a9"+
		"\u00aa\3\2\2\2\u00aa\u00ad\3\2\2\2\u00ab\u00a9\3\2\2\2\u00ac\u00a4\3\2"+
		"\2\2\u00ac\u00a5\3\2\2\2\u00ad,\3\2\2\2\u00ae\u00af\t\7\2\2\u00af.\3\2"+
		"\2\2\u00b0\u00b2\t\b\2\2\u00b1\u00b0\3\2\2\2\u00b2\u00b3\3\2\2\2\u00b3"+
		"\u00b1\3\2\2\2\u00b3\u00b4\3\2\2\2\u00b4\u00b5\3\2\2\2\u00b5\u00b6\b\30"+
		"\2\2\u00b6\60\3\2\2\2\16\2qt{\u0081\u0087\u0089\u008f\u00a1\u00a9\u00ac"+
		"\u00b3";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
	}
}