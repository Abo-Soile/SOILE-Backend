// Generated from /Users/tuope/NetBeansProjects/AboExperimentLang/src/demo/DemoLangV001.g4 by ANTLR 4.0
package demo;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class DemoLangV001Lexer extends Lexer {
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
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"'1+'", "'1-'", "'+'", "'-'", "'*'", "'/'", "'<'", "'>'", "'='", "'!'", 
		"'<-'", "'->'", "'{'", "'}'", "'['", "']'", "'break'", "':'", "','", "'continue'", 
		"'do'", "'.'", "'else'", "'end'", "'false'", "'function'", "'fvar'", "'gvar'", 
		"'if'", "'iteration'", "'Map'", "'null'", "'('", "')'", "'?'", "'return'", 
		"'then'", "'true'", "'val'", "'var'", "'while'", "ID", "NUMBER", "STRING", 
		"WS"
	};
	public static final String[] ruleNames = {
		"SFN_ONEPLUS", "SFN_ONEMINUS", "SFN_PLUS", "SFN_MINUS", "SFN_MULTIPLY", 
		"SFN_DIVIDE", "SFN_LESSTHAN", "SFN_GREATERTHAN", "SFN_EQUAL", "SFN_NEGATE", 
		"T_ARROWLEFT", "T_ARROWRIGHT", "T_BRACELEFT", "T_BRACERIGHT", "T_BRACKETLEFT", 
		"T_BRACKETRIGHT", "T_BREAK", "T_COLON", "T_COMMA", "T_CONTINUE", "T_DO", 
		"T_DOT", "T_ELSE", "T_END", "T_FALSE", "T_FUNCTION", "T_FVAR", "T_GVAR", 
		"T_IF", "T_ITERATION", "T_MAP", "T_NULL", "T_PARENLEFT", "T_PARENRIGHT", 
		"T_QMARK", "T_RETURN", "T_THEN", "T_TRUE", "T_VAL", "T_VAR", "T_WHILE", 
		"ID", "NUMBER", "STRING", "WS", "ESC", "ID_CHAR", "ID_LETTER", "DIGIT"
	};


	public DemoLangV001Lexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "DemoLangV001.g4"; }

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
		case 44: WS_action((RuleContext)_localctx, actionIndex); break;
		}
	}
	private void WS_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0: _channel = HIDDEN;  break;
		}
	}

	public static final String _serializedATN =
		"\2\4/\u012a\b\1\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t"+
		"\b\4\t\t\t\4\n\t\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20"+
		"\t\20\4\21\t\21\4\22\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27"+
		"\t\27\4\30\t\30\4\31\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36"+
		"\t\36\4\37\t\37\4 \t \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4"+
		"(\t(\4)\t)\4*\t*\4+\t+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62"+
		"\t\62\3\2\3\2\3\2\3\3\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b"+
		"\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\f\3\r\3\r\3\r\3\16\3\16\3\17\3\17"+
		"\3\20\3\20\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\24\3\24"+
		"\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\27\3\27"+
		"\3\30\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\32\3\32"+
		"\3\32\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\34\3\34\3\34\3\34"+
		"\3\34\3\35\3\35\3\35\3\35\3\35\3\36\3\36\3\36\3\37\3\37\3\37\3\37\3\37"+
		"\3\37\3\37\3\37\3\37\3\37\3 \3 \3 \3 \3!\3!\3!\3!\3!\3\"\3\"\3#\3#\3$"+
		"\3$\3%\3%\3%\3%\3%\3%\3%\3&\3&\3&\3&\3&\3\'\3\'\3\'\3\'\3\'\3(\3(\3(\3"+
		"(\3)\3)\3)\3)\3*\3*\3*\3*\3*\3*\3+\3+\3+\7+\u0102\n+\f+\16+\u0105\13+"+
		"\3,\5,\u0108\n,\3,\6,\u010b\n,\r,\16,\u010c\3-\3-\3-\7-\u0112\n-\f-\16"+
		"-\u0115\13-\3-\3-\3.\6.\u011a\n.\r.\16.\u011b\3.\3.\3/\3/\3/\3\60\3\60"+
		"\5\60\u0125\n\60\3\61\3\61\3\62\3\62\2\63\3\3\1\5\4\1\7\5\1\t\6\1\13\7"+
		"\1\r\b\1\17\t\1\21\n\1\23\13\1\25\f\1\27\r\1\31\16\1\33\17\1\35\20\1\37"+
		"\21\1!\22\1#\23\1%\24\1\'\25\1)\26\1+\27\1-\30\1/\31\1\61\32\1\63\33\1"+
		"\65\34\1\67\35\19\36\1;\37\1= \1?!\1A\"\1C#\1E$\1G%\1I&\1K\'\1M(\1O)\1"+
		"Q*\1S+\1U,\1W-\1Y.\1[/\2]\2\1_\2\1a\2\1c\2\1\3\2\6\4$$^^\5\13\f\16\17"+
		"\"\"\4$$^^\bC\\c|\u00c6\u00c7\u00d8\u00d8\u00e6\u00e7\u00f8\u00f8\u012d"+
		"\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2"+
		"\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2"+
		"\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2"+
		"\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2"+
		"\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3"+
		"\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2"+
		"\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2"+
		"U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\3e\3\2\2\2\5h\3\2\2\2\7k\3"+
		"\2\2\2\tm\3\2\2\2\13o\3\2\2\2\rq\3\2\2\2\17s\3\2\2\2\21u\3\2\2\2\23w\3"+
		"\2\2\2\25y\3\2\2\2\27{\3\2\2\2\31~\3\2\2\2\33\u0081\3\2\2\2\35\u0083\3"+
		"\2\2\2\37\u0085\3\2\2\2!\u0087\3\2\2\2#\u0089\3\2\2\2%\u008f\3\2\2\2\'"+
		"\u0091\3\2\2\2)\u0093\3\2\2\2+\u009c\3\2\2\2-\u009f\3\2\2\2/\u00a1\3\2"+
		"\2\2\61\u00a6\3\2\2\2\63\u00aa\3\2\2\2\65\u00b0\3\2\2\2\67\u00b9\3\2\2"+
		"\29\u00be\3\2\2\2;\u00c3\3\2\2\2=\u00c6\3\2\2\2?\u00d0\3\2\2\2A\u00d4"+
		"\3\2\2\2C\u00d9\3\2\2\2E\u00db\3\2\2\2G\u00dd\3\2\2\2I\u00df\3\2\2\2K"+
		"\u00e6\3\2\2\2M\u00eb\3\2\2\2O\u00f0\3\2\2\2Q\u00f4\3\2\2\2S\u00f8\3\2"+
		"\2\2U\u00fe\3\2\2\2W\u0107\3\2\2\2Y\u010e\3\2\2\2[\u0119\3\2\2\2]\u011f"+
		"\3\2\2\2_\u0124\3\2\2\2a\u0126\3\2\2\2c\u0128\3\2\2\2ef\7\63\2\2fg\7-"+
		"\2\2g\4\3\2\2\2hi\7\63\2\2ij\7/\2\2j\6\3\2\2\2kl\7-\2\2l\b\3\2\2\2mn\7"+
		"/\2\2n\n\3\2\2\2op\7,\2\2p\f\3\2\2\2qr\7\61\2\2r\16\3\2\2\2st\7>\2\2t"+
		"\20\3\2\2\2uv\7@\2\2v\22\3\2\2\2wx\7?\2\2x\24\3\2\2\2yz\7#\2\2z\26\3\2"+
		"\2\2{|\7>\2\2|}\7/\2\2}\30\3\2\2\2~\177\7/\2\2\177\u0080\7@\2\2\u0080"+
		"\32\3\2\2\2\u0081\u0082\7}\2\2\u0082\34\3\2\2\2\u0083\u0084\7\177\2\2"+
		"\u0084\36\3\2\2\2\u0085\u0086\7]\2\2\u0086 \3\2\2\2\u0087\u0088\7_\2\2"+
		"\u0088\"\3\2\2\2\u0089\u008a\7d\2\2\u008a\u008b\7t\2\2\u008b\u008c\7g"+
		"\2\2\u008c\u008d\7c\2\2\u008d\u008e\7m\2\2\u008e$\3\2\2\2\u008f\u0090"+
		"\7<\2\2\u0090&\3\2\2\2\u0091\u0092\7.\2\2\u0092(\3\2\2\2\u0093\u0094\7"+
		"e\2\2\u0094\u0095\7q\2\2\u0095\u0096\7p\2\2\u0096\u0097\7v\2\2\u0097\u0098"+
		"\7k\2\2\u0098\u0099\7p\2\2\u0099\u009a\7w\2\2\u009a\u009b\7g\2\2\u009b"+
		"*\3\2\2\2\u009c\u009d\7f\2\2\u009d\u009e\7q\2\2\u009e,\3\2\2\2\u009f\u00a0"+
		"\7\60\2\2\u00a0.\3\2\2\2\u00a1\u00a2\7g\2\2\u00a2\u00a3\7n\2\2\u00a3\u00a4"+
		"\7u\2\2\u00a4\u00a5\7g\2\2\u00a5\60\3\2\2\2\u00a6\u00a7\7g\2\2\u00a7\u00a8"+
		"\7p\2\2\u00a8\u00a9\7f\2\2\u00a9\62\3\2\2\2\u00aa\u00ab\7h\2\2\u00ab\u00ac"+
		"\7c\2\2\u00ac\u00ad\7n\2\2\u00ad\u00ae\7u\2\2\u00ae\u00af\7g\2\2\u00af"+
		"\64\3\2\2\2\u00b0\u00b1\7h\2\2\u00b1\u00b2\7w\2\2\u00b2\u00b3\7p\2\2\u00b3"+
		"\u00b4\7e\2\2\u00b4\u00b5\7v\2\2\u00b5\u00b6\7k\2\2\u00b6\u00b7\7q\2\2"+
		"\u00b7\u00b8\7p\2\2\u00b8\66\3\2\2\2\u00b9\u00ba\7h\2\2\u00ba\u00bb\7"+
		"x\2\2\u00bb\u00bc\7c\2\2\u00bc\u00bd\7t\2\2\u00bd8\3\2\2\2\u00be\u00bf"+
		"\7i\2\2\u00bf\u00c0\7x\2\2\u00c0\u00c1\7c\2\2\u00c1\u00c2\7t\2\2\u00c2"+
		":\3\2\2\2\u00c3\u00c4\7k\2\2\u00c4\u00c5\7h\2\2\u00c5<\3\2\2\2\u00c6\u00c7"+
		"\7k\2\2\u00c7\u00c8\7v\2\2\u00c8\u00c9\7g\2\2\u00c9\u00ca\7t\2\2\u00ca"+
		"\u00cb\7c\2\2\u00cb\u00cc\7v\2\2\u00cc\u00cd\7k\2\2\u00cd\u00ce\7q\2\2"+
		"\u00ce\u00cf\7p\2\2\u00cf>\3\2\2\2\u00d0\u00d1\7O\2\2\u00d1\u00d2\7c\2"+
		"\2\u00d2\u00d3\7r\2\2\u00d3@\3\2\2\2\u00d4\u00d5\7p\2\2\u00d5\u00d6\7"+
		"w\2\2\u00d6\u00d7\7n\2\2\u00d7\u00d8\7n\2\2\u00d8B\3\2\2\2\u00d9\u00da"+
		"\7*\2\2\u00daD\3\2\2\2\u00db\u00dc\7+\2\2\u00dcF\3\2\2\2\u00dd\u00de\7"+
		"A\2\2\u00deH\3\2\2\2\u00df\u00e0\7t\2\2\u00e0\u00e1\7g\2\2\u00e1\u00e2"+
		"\7v\2\2\u00e2\u00e3\7w\2\2\u00e3\u00e4\7t\2\2\u00e4\u00e5\7p\2\2\u00e5"+
		"J\3\2\2\2\u00e6\u00e7\7v\2\2\u00e7\u00e8\7j\2\2\u00e8\u00e9\7g\2\2\u00e9"+
		"\u00ea\7p\2\2\u00eaL\3\2\2\2\u00eb\u00ec\7v\2\2\u00ec\u00ed\7t\2\2\u00ed"+
		"\u00ee\7w\2\2\u00ee\u00ef\7g\2\2\u00efN\3\2\2\2\u00f0\u00f1\7x\2\2\u00f1"+
		"\u00f2\7c\2\2\u00f2\u00f3\7n\2\2\u00f3P\3\2\2\2\u00f4\u00f5\7x\2\2\u00f5"+
		"\u00f6\7c\2\2\u00f6\u00f7\7t\2\2\u00f7R\3\2\2\2\u00f8\u00f9\7y\2\2\u00f9"+
		"\u00fa\7j\2\2\u00fa\u00fb\7k\2\2\u00fb\u00fc\7n\2\2\u00fc\u00fd\7g\2\2"+
		"\u00fdT\3\2\2\2\u00fe\u0103\5a\61\2\u00ff\u0102\5_\60\2\u0100\u0102\5"+
		"c\62\2\u0101\u00ff\3\2\2\2\u0101\u0100\3\2\2\2\u0102\u0105\3\2\2\2\u0103"+
		"\u0101\3\2\2\2\u0103\u0104\3\2\2\2\u0104V\3\2\2\2\u0105\u0103\3\2\2\2"+
		"\u0106\u0108\7/\2\2\u0107\u0106\3\2\2\2\u0107\u0108\3\2\2\2\u0108\u010a"+
		"\3\2\2\2\u0109\u010b\5c\62\2\u010a\u0109\3\2\2\2\u010b\u010c\3\2\2\2\u010c"+
		"\u010a\3\2\2\2\u010c\u010d\3\2\2\2\u010dX\3\2\2\2\u010e\u0113\7$\2\2\u010f"+
		"\u0112\5]/\2\u0110\u0112\n\2\2\2\u0111\u010f\3\2\2\2\u0111\u0110\3\2\2"+
		"\2\u0112\u0115\3\2\2\2\u0113\u0111\3\2\2\2\u0113\u0114\3\2\2\2\u0114\u0116"+
		"\3\2\2\2\u0115\u0113\3\2\2\2\u0116\u0117\7$\2\2\u0117Z\3\2\2\2\u0118\u011a"+
		"\t\3\2\2\u0119\u0118\3\2\2\2\u011a\u011b\3\2\2\2\u011b\u0119\3\2\2\2\u011b"+
		"\u011c\3\2\2\2\u011c\u011d\3\2\2\2\u011d\u011e\b.\2\2\u011e\\\3\2\2\2"+
		"\u011f\u0120\7^\2\2\u0120\u0121\t\4\2\2\u0121^\3\2\2\2\u0122\u0125\5a"+
		"\61\2\u0123\u0125\7a\2\2\u0124\u0122\3\2\2\2\u0124\u0123\3\2\2\2\u0125"+
		"`\3\2\2\2\u0126\u0127\t\5\2\2\u0127b\3\2\2\2\u0128\u0129\4\62;\2\u0129"+
		"d\3\2\2\2\13\2\u0101\u0103\u0107\u010c\u0111\u0113\u011b\u0124";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
	}
}