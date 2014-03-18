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
		"INTEGER", "FLOAT", "STRING", "REQUIRED", "ID", "INT", "WS"
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
		case 21: WS_action((RuleContext)_localctx, actionIndex); break;
		}
	}
	private void WS_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0: skip();  break;
		}
	}

	public static final String _serializedATN =
		"\2\4\27\u00b8\b\1\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b"+
		"\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20"+
		"\t\20\4\21\t\21\4\22\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27"+
		"\t\27\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6"+
		"\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f"+
		"\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3"+
		"\17\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3"+
		"\20\3\20\5\20p\n\20\3\21\5\21s\n\21\3\21\3\21\3\22\7\22x\n\22\f\22\16"+
		"\22{\13\22\3\22\3\22\7\22\177\n\22\f\22\16\22\u0082\13\22\3\22\3\22\3"+
		"\22\3\22\7\22\u0088\n\22\f\22\16\22\u008b\13\22\5\22\u008d\n\22\3\23\3"+
		"\23\7\23\u0091\n\23\f\23\16\23\u0094\13\23\3\23\3\23\3\24\3\24\3\24\3"+
		"\24\3\24\3\24\3\24\3\24\3\24\3\25\3\25\7\25\u00a3\n\25\f\25\16\25\u00a6"+
		"\13\25\3\26\3\26\3\26\7\26\u00ab\n\26\f\26\16\26\u00ae\13\26\5\26\u00b0"+
		"\n\26\3\27\6\27\u00b3\n\27\r\27\16\27\u00b4\3\27\3\27\2\30\3\3\1\5\4\1"+
		"\7\5\1\t\6\1\13\7\1\r\b\1\17\t\1\21\n\1\23\13\1\25\f\1\27\r\1\31\16\1"+
		"\33\17\1\35\20\1\37\21\1!\22\1#\23\1%\24\1\'\25\1)\26\1+\2\1-\27\2\3\2"+
		"\13\3\63;\3\62;\3\62;\7\"\"\62;C\\aac|\5C\\aac|\6\62;C\\aac|\3\63;\3\62"+
		";\5\13\f\17\17\"\"\u00c1\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2"+
		"\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25"+
		"\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2"+
		"\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2-\3\2\2"+
		"\2\3/\3\2\2\2\5\61\3\2\2\2\7\63\3\2\2\2\t\65\3\2\2\2\13=\3\2\2\2\r?\3"+
		"\2\2\2\17A\3\2\2\2\21C\3\2\2\2\23E\3\2\2\2\25G\3\2\2\2\27I\3\2\2\2\31"+
		"Q\3\2\2\2\33W\3\2\2\2\35^\3\2\2\2\37o\3\2\2\2!r\3\2\2\2#\u008c\3\2\2\2"+
		"%\u008e\3\2\2\2\'\u0097\3\2\2\2)\u00a0\3\2\2\2+\u00af\3\2\2\2-\u00b2\3"+
		"\2\2\2/\60\7_\2\2\60\4\3\2\2\2\61\62\7}\2\2\62\6\3\2\2\2\63\64\7+\2\2"+
		"\64\b\3\2\2\2\65\66\7e\2\2\66\67\7q\2\2\678\7o\2\289\7o\2\29:\7c\2\2:"+
		";\7p\2\2;<\7f\2\2<\n\3\2\2\2=>\7.\2\2>\f\3\2\2\2?@\7]\2\2@\16\3\2\2\2"+
		"AB\7,\2\2B\20\3\2\2\2CD\7<\2\2D\22\3\2\2\2EF\7*\2\2F\24\3\2\2\2GH\7\177"+
		"\2\2H\26\3\2\2\2IJ\7K\2\2JK\7p\2\2KL\7v\2\2LM\7g\2\2MN\7i\2\2NO\7g\2\2"+
		"OP\7t\2\2P\30\3\2\2\2QR\7H\2\2RS\7n\2\2ST\7q\2\2TU\7c\2\2UV\7v\2\2V\32"+
		"\3\2\2\2WX\7U\2\2XY\7v\2\2YZ\7t\2\2Z[\7k\2\2[\\\7p\2\2\\]\7i\2\2]\34\3"+
		"\2\2\2^_\7D\2\2_`\7q\2\2`a\7q\2\2ab\7n\2\2bc\7g\2\2cd\7c\2\2de\7p\2\2"+
		"e\36\3\2\2\2fg\7v\2\2gh\7t\2\2hi\7w\2\2ip\7g\2\2jk\7h\2\2kl\7c\2\2lm\7"+
		"n\2\2mn\7u\2\2np\7g\2\2of\3\2\2\2oj\3\2\2\2p \3\2\2\2qs\7/\2\2rq\3\2\2"+
		"\2rs\3\2\2\2st\3\2\2\2tu\5+\26\2u\"\3\2\2\2vx\t\2\2\2wv\3\2\2\2x{\3\2"+
		"\2\2yw\3\2\2\2yz\3\2\2\2z|\3\2\2\2{y\3\2\2\2|\u0080\7\60\2\2}\177\t\3"+
		"\2\2~}\3\2\2\2\177\u0082\3\2\2\2\u0080~\3\2\2\2\u0080\u0081\3\2\2\2\u0081"+
		"\u008d\3\2\2\2\u0082\u0080\3\2\2\2\u0083\u0084\7\62\2\2\u0084\u0085\7"+
		"\60\2\2\u0085\u0089\3\2\2\2\u0086\u0088\t\4\2\2\u0087\u0086\3\2\2\2\u0088"+
		"\u008b\3\2\2\2\u0089\u0087\3\2\2\2\u0089\u008a\3\2\2\2\u008a\u008d\3\2"+
		"\2\2\u008b\u0089\3\2\2\2\u008cy\3\2\2\2\u008c\u0083\3\2\2\2\u008d$\3\2"+
		"\2\2\u008e\u0092\7$\2\2\u008f\u0091\t\5\2\2\u0090\u008f\3\2\2\2\u0091"+
		"\u0094\3\2\2\2\u0092\u0090\3\2\2\2\u0092\u0093\3\2\2\2\u0093\u0095\3\2"+
		"\2\2\u0094\u0092\3\2\2\2\u0095\u0096\7$\2\2\u0096&\3\2\2\2\u0097\u0098"+
		"\7t\2\2\u0098\u0099\7g\2\2\u0099\u009a\7s\2\2\u009a\u009b\7w\2\2\u009b"+
		"\u009c\7k\2\2\u009c\u009d\7t\2\2\u009d\u009e\7g\2\2\u009e\u009f\7f\2\2"+
		"\u009f(\3\2\2\2\u00a0\u00a4\t\6\2\2\u00a1\u00a3\t\7\2\2\u00a2\u00a1\3"+
		"\2\2\2\u00a3\u00a6\3\2\2\2\u00a4\u00a2\3\2\2\2\u00a4\u00a5\3\2\2\2\u00a5"+
		"*\3\2\2\2\u00a6\u00a4\3\2\2\2\u00a7\u00b0\7\62\2\2\u00a8\u00ac\t\b\2\2"+
		"\u00a9\u00ab\t\t\2\2\u00aa\u00a9\3\2\2\2\u00ab\u00ae\3\2\2\2\u00ac\u00aa"+
		"\3\2\2\2\u00ac\u00ad\3\2\2\2\u00ad\u00b0\3\2\2\2\u00ae\u00ac\3\2\2\2\u00af"+
		"\u00a7\3\2\2\2\u00af\u00a8\3\2\2\2\u00b0,\3\2\2\2\u00b1\u00b3\t\n\2\2"+
		"\u00b2\u00b1\3\2\2\2\u00b3\u00b4\3\2\2\2\u00b4\u00b2\3\2\2\2\u00b4\u00b5"+
		"\3\2\2\2\u00b5\u00b6\3\2\2\2\u00b6\u00b7\b\27\2\2\u00b7.\3\2\2\2\16\2"+
		"ory\u0080\u0089\u008c\u0092\u00a4\u00ac\u00af\u00b4";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
	}
}