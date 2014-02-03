// Generated from Elang.g4 by ANTLR 4.0
package fi.abo.kogni.soile2.elang;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ElangLexer extends Lexer {
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
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"DOCQUOTE", "'1+'", "'1-'", "'+'", "'-'", "'*'", "'/'", "'<'", "'>'", 
		"'='", "'!'", "'-\"-'", "'<-'", "'->'", "'{'", "'}'", "'['", "']'", "'break'", 
		"':'", "','", "'continue'", "'do'", "'.'", "'else'", "'end'", "'false'", 
		"'function'", "'fvar'", "'gvar'", "'#'", "'if'", "'|->'", "'iteration'", 
		"'null'", "'('", "')'", "'?'", "'\"'", "'return'", "'then'", "'transition'", 
		"'true'", "'val'", "'var'", "'while'", "ID", "NUMBER", "STRING", "COMMENT", 
		"WS", "NL"
	};
	public static final String[] ruleNames = {
		"DOCQUOTE", "SFN_ONEPLUS", "SFN_ONEMINUS", "SFN_PLUS", "SFN_MINUS", "SFN_MULTIPLY", 
		"SFN_DIVIDE", "SFN_LESSTHAN", "SFN_GREATERTHAN", "SFN_EQUAL", "SFN_NEGATE", 
		"T_TRANNAMEREPEAT", "T_ARROWLEFT", "T_ARROWRIGHT", "T_BRACELEFT", "T_BRACERIGHT", 
		"T_BRACKETLEFT", "T_BRACKETRIGHT", "T_BREAK", "T_COLON", "T_COMMA", "T_CONTINUE", 
		"T_DO", "T_DOT", "T_ELSE", "T_END", "T_FALSE", "T_FUNCTION", "T_FVAR", 
		"T_GVAR", "T_HASH", "T_IF", "T_INITTRANS", "T_ITERATION", "T_NULL", "T_PARENLEFT", 
		"T_PARENRIGHT", "T_QMARK", "T_QUOTE", "T_RETURN", "T_THEN", "T_TRANSITION", 
		"T_TRUE", "T_VAL", "T_VAR", "T_WHILE", "ID", "NUMBER", "STRING", "COMMENT", 
		"WS", "NL", "ESC", "ID_CHAR", "ID_LETTER", "DIGIT"
	};


	public ElangLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Elang.g4"; }

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
		case 49: COMMENT_action((RuleContext)_localctx, actionIndex); break;

		case 50: WS_action((RuleContext)_localctx, actionIndex); break;
		}
	}
	private void WS_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 1: _channel = HIDDEN;  break;
		}
	}
	private void COMMENT_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0: skip();  break;
		}
	}

	public static final String _serializedATN =
		"\2\4\66\u015f\b\1\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b"+
		"\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20"+
		"\t\20\4\21\t\21\4\22\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27"+
		"\t\27\4\30\t\30\4\31\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36"+
		"\t\36\4\37\t\37\4 \t \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4"+
		"(\t(\4)\t)\4*\t*\4+\t+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62"+
		"\t\62\4\63\t\63\4\64\t\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\3"+
		"\2\3\2\3\2\3\2\3\3\3\3\3\3\3\4\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b"+
		"\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\17"+
		"\3\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22\3\23\3\23\3\24\3\24\3\24\3\24"+
		"\3\24\3\24\3\25\3\25\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27"+
		"\3\27\3\30\3\30\3\30\3\31\3\31\3\32\3\32\3\32\3\32\3\32\3\33\3\33\3\33"+
		"\3\33\3\34\3\34\3\34\3\34\3\34\3\34\3\35\3\35\3\35\3\35\3\35\3\35\3\35"+
		"\3\35\3\35\3\36\3\36\3\36\3\36\3\36\3\37\3\37\3\37\3\37\3\37\3 \3 \3!"+
		"\3!\3!\3\"\3\"\3\"\3\"\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#\3$\3$\3$\3$\3$\3"+
		"%\3%\3&\3&\3\'\3\'\3(\3(\3)\3)\3)\3)\3)\3)\3)\3*\3*\3*\3*\3*\3+\3+\3+"+
		"\3+\3+\3+\3+\3+\3+\3+\3+\3,\3,\3,\3,\3,\3-\3-\3-\3-\3.\3.\3.\3.\3/\3/"+
		"\3/\3/\3/\3/\3\60\3\60\3\60\7\60\u0127\n\60\f\60\16\60\u012a\13\60\3\61"+
		"\5\61\u012d\n\61\3\61\6\61\u0130\n\61\r\61\16\61\u0131\3\62\3\62\3\62"+
		"\7\62\u0137\n\62\f\62\16\62\u013a\13\62\3\62\3\62\3\63\3\63\7\63\u0140"+
		"\n\63\f\63\16\63\u0143\13\63\3\63\3\63\3\63\3\63\3\64\6\64\u014a\n\64"+
		"\r\64\16\64\u014b\3\64\3\64\3\65\5\65\u0151\n\65\3\65\3\65\3\66\3\66\3"+
		"\66\3\67\3\67\5\67\u015a\n\67\38\38\39\39\3\u0141:\3\3\1\5\4\1\7\5\1\t"+
		"\6\1\13\7\1\r\b\1\17\t\1\21\n\1\23\13\1\25\f\1\27\r\1\31\16\1\33\17\1"+
		"\35\20\1\37\21\1!\22\1#\23\1%\24\1\'\25\1)\26\1+\27\1-\30\1/\31\1\61\32"+
		"\1\63\33\1\65\34\1\67\35\19\36\1;\37\1= \1?!\1A\"\1C#\1E$\1G%\1I&\1K\'"+
		"\1M(\1O)\1Q*\1S+\1U,\1W-\1Y.\1[/\1]\60\1_\61\1a\62\1c\63\1e\64\2g\65\3"+
		"i\66\1k\2\1m\2\1o\2\1q\2\1\3\2\6\4$$^^\5\13\f\16\17\"\"\4$$^^\bC\\c|\u00c6"+
		"\u00c7\u00d8\u00d8\u00e6\u00e7\u00f8\u00f8\u0164\2\3\3\2\2\2\2\5\3\2\2"+
		"\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21"+
		"\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2"+
		"\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3"+
		"\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3"+
		"\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3"+
		"\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2"+
		"\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2"+
		"Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2\2e\3"+
		"\2\2\2\2g\3\2\2\2\2i\3\2\2\2\3s\3\2\2\2\5w\3\2\2\2\7z\3\2\2\2\t}\3\2\2"+
		"\2\13\177\3\2\2\2\r\u0081\3\2\2\2\17\u0083\3\2\2\2\21\u0085\3\2\2\2\23"+
		"\u0087\3\2\2\2\25\u0089\3\2\2\2\27\u008b\3\2\2\2\31\u008d\3\2\2\2\33\u0091"+
		"\3\2\2\2\35\u0094\3\2\2\2\37\u0097\3\2\2\2!\u0099\3\2\2\2#\u009b\3\2\2"+
		"\2%\u009d\3\2\2\2\'\u009f\3\2\2\2)\u00a5\3\2\2\2+\u00a7\3\2\2\2-\u00a9"+
		"\3\2\2\2/\u00b2\3\2\2\2\61\u00b5\3\2\2\2\63\u00b7\3\2\2\2\65\u00bc\3\2"+
		"\2\2\67\u00c0\3\2\2\29\u00c6\3\2\2\2;\u00cf\3\2\2\2=\u00d4\3\2\2\2?\u00d9"+
		"\3\2\2\2A\u00db\3\2\2\2C\u00de\3\2\2\2E\u00e2\3\2\2\2G\u00ec\3\2\2\2I"+
		"\u00f1\3\2\2\2K\u00f3\3\2\2\2M\u00f5\3\2\2\2O\u00f7\3\2\2\2Q\u00f9\3\2"+
		"\2\2S\u0100\3\2\2\2U\u0105\3\2\2\2W\u0110\3\2\2\2Y\u0115\3\2\2\2[\u0119"+
		"\3\2\2\2]\u011d\3\2\2\2_\u0123\3\2\2\2a\u012c\3\2\2\2c\u0133\3\2\2\2e"+
		"\u013d\3\2\2\2g\u0149\3\2\2\2i\u0150\3\2\2\2k\u0154\3\2\2\2m\u0159\3\2"+
		"\2\2o\u015b\3\2\2\2q\u015d\3\2\2\2st\5O(\2tu\5O(\2uv\5O(\2v\4\3\2\2\2"+
		"wx\7\63\2\2xy\7-\2\2y\6\3\2\2\2z{\7\63\2\2{|\7/\2\2|\b\3\2\2\2}~\7-\2"+
		"\2~\n\3\2\2\2\177\u0080\7/\2\2\u0080\f\3\2\2\2\u0081\u0082\7,\2\2\u0082"+
		"\16\3\2\2\2\u0083\u0084\7\61\2\2\u0084\20\3\2\2\2\u0085\u0086\7>\2\2\u0086"+
		"\22\3\2\2\2\u0087\u0088\7@\2\2\u0088\24\3\2\2\2\u0089\u008a\7?\2\2\u008a"+
		"\26\3\2\2\2\u008b\u008c\7#\2\2\u008c\30\3\2\2\2\u008d\u008e\7/\2\2\u008e"+
		"\u008f\7$\2\2\u008f\u0090\7/\2\2\u0090\32\3\2\2\2\u0091\u0092\7>\2\2\u0092"+
		"\u0093\7/\2\2\u0093\34\3\2\2\2\u0094\u0095\7/\2\2\u0095\u0096\7@\2\2\u0096"+
		"\36\3\2\2\2\u0097\u0098\7}\2\2\u0098 \3\2\2\2\u0099\u009a\7\177\2\2\u009a"+
		"\"\3\2\2\2\u009b\u009c\7]\2\2\u009c$\3\2\2\2\u009d\u009e\7_\2\2\u009e"+
		"&\3\2\2\2\u009f\u00a0\7d\2\2\u00a0\u00a1\7t\2\2\u00a1\u00a2\7g\2\2\u00a2"+
		"\u00a3\7c\2\2\u00a3\u00a4\7m\2\2\u00a4(\3\2\2\2\u00a5\u00a6\7<\2\2\u00a6"+
		"*\3\2\2\2\u00a7\u00a8\7.\2\2\u00a8,\3\2\2\2\u00a9\u00aa\7e\2\2\u00aa\u00ab"+
		"\7q\2\2\u00ab\u00ac\7p\2\2\u00ac\u00ad\7v\2\2\u00ad\u00ae\7k\2\2\u00ae"+
		"\u00af\7p\2\2\u00af\u00b0\7w\2\2\u00b0\u00b1\7g\2\2\u00b1.\3\2\2\2\u00b2"+
		"\u00b3\7f\2\2\u00b3\u00b4\7q\2\2\u00b4\60\3\2\2\2\u00b5\u00b6\7\60\2\2"+
		"\u00b6\62\3\2\2\2\u00b7\u00b8\7g\2\2\u00b8\u00b9\7n\2\2\u00b9\u00ba\7"+
		"u\2\2\u00ba\u00bb\7g\2\2\u00bb\64\3\2\2\2\u00bc\u00bd\7g\2\2\u00bd\u00be"+
		"\7p\2\2\u00be\u00bf\7f\2\2\u00bf\66\3\2\2\2\u00c0\u00c1\7h\2\2\u00c1\u00c2"+
		"\7c\2\2\u00c2\u00c3\7n\2\2\u00c3\u00c4\7u\2\2\u00c4\u00c5\7g\2\2\u00c5"+
		"8\3\2\2\2\u00c6\u00c7\7h\2\2\u00c7\u00c8\7w\2\2\u00c8\u00c9\7p\2\2\u00c9"+
		"\u00ca\7e\2\2\u00ca\u00cb\7v\2\2\u00cb\u00cc\7k\2\2\u00cc\u00cd\7q\2\2"+
		"\u00cd\u00ce\7p\2\2\u00ce:\3\2\2\2\u00cf\u00d0\7h\2\2\u00d0\u00d1\7x\2"+
		"\2\u00d1\u00d2\7c\2\2\u00d2\u00d3\7t\2\2\u00d3<\3\2\2\2\u00d4\u00d5\7"+
		"i\2\2\u00d5\u00d6\7x\2\2\u00d6\u00d7\7c\2\2\u00d7\u00d8\7t\2\2\u00d8>"+
		"\3\2\2\2\u00d9\u00da\7%\2\2\u00da@\3\2\2\2\u00db\u00dc\7k\2\2\u00dc\u00dd"+
		"\7h\2\2\u00ddB\3\2\2\2\u00de\u00df\7~\2\2\u00df\u00e0\7/\2\2\u00e0\u00e1"+
		"\7@\2\2\u00e1D\3\2\2\2\u00e2\u00e3\7k\2\2\u00e3\u00e4\7v\2\2\u00e4\u00e5"+
		"\7g\2\2\u00e5\u00e6\7t\2\2\u00e6\u00e7\7c\2\2\u00e7\u00e8\7v\2\2\u00e8"+
		"\u00e9\7k\2\2\u00e9\u00ea\7q\2\2\u00ea\u00eb\7p\2\2\u00ebF\3\2\2\2\u00ec"+
		"\u00ed\7p\2\2\u00ed\u00ee\7w\2\2\u00ee\u00ef\7n\2\2\u00ef\u00f0\7n\2\2"+
		"\u00f0H\3\2\2\2\u00f1\u00f2\7*\2\2\u00f2J\3\2\2\2\u00f3\u00f4\7+\2\2\u00f4"+
		"L\3\2\2\2\u00f5\u00f6\7A\2\2\u00f6N\3\2\2\2\u00f7\u00f8\7$\2\2\u00f8P"+
		"\3\2\2\2\u00f9\u00fa\7t\2\2\u00fa\u00fb\7g\2\2\u00fb\u00fc\7v\2\2\u00fc"+
		"\u00fd\7w\2\2\u00fd\u00fe\7t\2\2\u00fe\u00ff\7p\2\2\u00ffR\3\2\2\2\u0100"+
		"\u0101\7v\2\2\u0101\u0102\7j\2\2\u0102\u0103\7g\2\2\u0103\u0104\7p\2\2"+
		"\u0104T\3\2\2\2\u0105\u0106\7v\2\2\u0106\u0107\7t\2\2\u0107\u0108\7c\2"+
		"\2\u0108\u0109\7p\2\2\u0109\u010a\7u\2\2\u010a\u010b\7k\2\2\u010b\u010c"+
		"\7v\2\2\u010c\u010d\7k\2\2\u010d\u010e\7q\2\2\u010e\u010f\7p\2\2\u010f"+
		"V\3\2\2\2\u0110\u0111\7v\2\2\u0111\u0112\7t\2\2\u0112\u0113\7w\2\2\u0113"+
		"\u0114\7g\2\2\u0114X\3\2\2\2\u0115\u0116\7x\2\2\u0116\u0117\7c\2\2\u0117"+
		"\u0118\7n\2\2\u0118Z\3\2\2\2\u0119\u011a\7x\2\2\u011a\u011b\7c\2\2\u011b"+
		"\u011c\7t\2\2\u011c\\\3\2\2\2\u011d\u011e\7y\2\2\u011e\u011f\7j\2\2\u011f"+
		"\u0120\7k\2\2\u0120\u0121\7n\2\2\u0121\u0122\7g\2\2\u0122^\3\2\2\2\u0123"+
		"\u0128\5o8\2\u0124\u0127\5m\67\2\u0125\u0127\5q9\2\u0126\u0124\3\2\2\2"+
		"\u0126\u0125\3\2\2\2\u0127\u012a\3\2\2\2\u0128\u0126\3\2\2\2\u0128\u0129"+
		"\3\2\2\2\u0129`\3\2\2\2\u012a\u0128\3\2\2\2\u012b\u012d\7/\2\2\u012c\u012b"+
		"\3\2\2\2\u012c\u012d\3\2\2\2\u012d\u012f\3\2\2\2\u012e\u0130\5q9\2\u012f"+
		"\u012e\3\2\2\2\u0130\u0131\3\2\2\2\u0131\u012f\3\2\2\2\u0131\u0132\3\2"+
		"\2\2\u0132b\3\2\2\2\u0133\u0138\7$\2\2\u0134\u0137\5k\66\2\u0135\u0137"+
		"\n\2\2\2\u0136\u0134\3\2\2\2\u0136\u0135\3\2\2\2\u0137\u013a\3\2\2\2\u0138"+
		"\u0136\3\2\2\2\u0138\u0139\3\2\2\2\u0139\u013b\3\2\2\2\u013a\u0138\3\2"+
		"\2\2\u013b\u013c\7$\2\2\u013cd\3\2\2\2\u013d\u0141\5? \2\u013e\u0140\13"+
		"\2\2\2\u013f\u013e\3\2\2\2\u0140\u0143\3\2\2\2\u0141\u0142\3\2\2\2\u0141"+
		"\u013f\3\2\2\2\u0142\u0144\3\2\2\2\u0143\u0141\3\2\2\2\u0144\u0145\5i"+
		"\65\2\u0145\u0146\3\2\2\2\u0146\u0147\b\63\2\2\u0147f\3\2\2\2\u0148\u014a"+
		"\t\3\2\2\u0149\u0148\3\2\2\2\u014a\u014b\3\2\2\2\u014b\u0149\3\2\2\2\u014b"+
		"\u014c\3\2\2\2\u014c\u014d\3\2\2\2\u014d\u014e\b\64\3\2\u014eh\3\2\2\2"+
		"\u014f\u0151\7\17\2\2\u0150\u014f\3\2\2\2\u0150\u0151\3\2\2\2\u0151\u0152"+
		"\3\2\2\2\u0152\u0153\7\f\2\2\u0153j\3\2\2\2\u0154\u0155\7^\2\2\u0155\u0156"+
		"\t\4\2\2\u0156l\3\2\2\2\u0157\u015a\5o8\2\u0158\u015a\7a\2\2\u0159\u0157"+
		"\3\2\2\2\u0159\u0158\3\2\2\2\u015an\3\2\2\2\u015b\u015c\t\5\2\2\u015c"+
		"p\3\2\2\2\u015d\u015e\4\62;\2\u015er\3\2\2\2\r\2\u0126\u0128\u012c\u0131"+
		"\u0136\u0138\u0141\u014b\u0150\u0159";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
	}
}