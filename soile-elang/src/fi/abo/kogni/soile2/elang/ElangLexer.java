// Generated from C:/Users/Danno/soileIDEA/soile2/soile-elang/src/fi/abo/kogni/soile2/elang\Elang.g4 by ANTLR 4.5.1
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
	static { RuntimeMetaData.checkVersion("4.5.1", RuntimeMetaData.VERSION); }

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
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "DOCQUOTE", "SFN_PLUS", "SFN_MINUS", "SFN_MULTIPLY", 
		"SFN_DIVIDE", "SFN_LESSTHAN", "SFN_GREATERTHAN", "SFN_EQUAL", "SFN_NEGATE", 
		"T_TRANSNAMEREPEAT", "T_ARROWLEFT", "T_ARROWRIGHT", "T_BRACELEFT", "T_BRACERIGHT", 
		"T_BRACKETLEFT", "T_BRACKETRIGHT", "T_BREAK", "T_COLON", "T_COMMA", "T_CONTINUE", 
		"T_DO", "T_DOT", "T_ELSE", "T_END", "T_FALSE", "T_FUNCTION", "T_FVAR", 
		"T_GVAR", "T_HASH", "T_IF", "T_NO", "T_NOTHING", "T_NULL", "T_PARENLEFT", 
		"T_PARENRIGHT", "T_QMARK", "T_QUOTE", "T_RETURN", "T_THEN", "T_TRANSFINAL", 
		"T_TRANSITION", "T_TRANSSTART", "T_TRUE", "T_VAL", "T_VAR", "T_WHILE", 
		"T_YES", "ID", "NUMBER", "STRING", "COMMENT", "WS", "NL", "ESC", "ID_CHAR", 
		"ID_LETTER", "DIGIT"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'information-phase'", "'intermezzo-phase'", "'interaction-phase'", 
		null, "'+'", "'-'", "'*'", "'/'", "'<'", "'>'", "'='", "'!'", "'-\"-'", 
		"'<-'", "'->'", "'{'", "'}'", "'['", "']'", "'break'", "':'", "','", "'continue'", 
		"'do'", "'.'", "'else'", "'end'", "'false'", "'function'", "'fvar'", "'gvar'", 
		"'#'", "'if'", "'no'", "'nothing'", "'null'", "'('", "')'", "'?'", "'\"'", 
		"'return'", "'then'", "'final'", "'transition'", "'start'", "'true'", 
		"'val'", "'var'", "'while'", "'yes'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
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


	public ElangLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Elang.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2:\u01a3\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3"+
		"\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\5\3\5\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3"+
		"\f\3\f\3\r\3\r\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\20\3\20\3\20\3\21"+
		"\3\21\3\22\3\22\3\23\3\23\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3\26"+
		"\3\26\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\31\3\31"+
		"\3\31\3\32\3\32\3\33\3\33\3\33\3\33\3\33\3\34\3\34\3\34\3\34\3\35\3\35"+
		"\3\35\3\35\3\35\3\35\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\37"+
		"\3\37\3\37\3\37\3\37\3 \3 \3 \3 \3 \3!\3!\3\"\3\"\3\"\3#\3#\3#\3$\3$\3"+
		"$\3$\3$\3$\3$\3$\3%\3%\3%\3%\3%\3&\3&\3\'\3\'\3(\3(\3)\3)\3*\3*\3*\3*"+
		"\3*\3*\3*\3+\3+\3+\3+\3+\3,\3,\3,\3,\3,\3,\3-\3-\3-\3-\3-\3-\3-\3-\3-"+
		"\3-\3-\3.\3.\3.\3.\3.\3.\3/\3/\3/\3/\3/\3\60\3\60\3\60\3\60\3\61\3\61"+
		"\3\61\3\61\3\62\3\62\3\62\3\62\3\62\3\62\3\63\3\63\3\63\3\63\3\64\3\64"+
		"\3\64\7\64\u016b\n\64\f\64\16\64\u016e\13\64\3\65\5\65\u0171\n\65\3\65"+
		"\6\65\u0174\n\65\r\65\16\65\u0175\3\66\3\66\3\66\7\66\u017b\n\66\f\66"+
		"\16\66\u017e\13\66\3\66\3\66\3\67\3\67\7\67\u0184\n\67\f\67\16\67\u0187"+
		"\13\67\3\67\3\67\3\67\3\67\38\68\u018e\n8\r8\168\u018f\38\38\39\59\u0195"+
		"\n9\39\39\3:\3:\3:\3;\3;\5;\u019e\n;\3<\3<\3=\3=\3\u0185\2>\3\3\5\4\7"+
		"\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22"+
		"#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C"+
		"#E$G%I&K\'M(O)Q*S+U,W-Y.[/]\60_\61a\62c\63e\64g\65i\66k\67m8o9q:s\2u\2"+
		"w\2y\2\3\2\5\4\2$$^^\5\2\13\f\16\17\"\"\4\2C\\c|\u01a8\2\3\3\2\2\2\2\5"+
		"\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2"+
		"\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33"+
		"\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2"+
		"\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2"+
		"\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2"+
		"\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K"+
		"\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2"+
		"\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2"+
		"\2e\3\2\2\2\2g\3\2\2\2\2i\3\2\2\2\2k\3\2\2\2\2m\3\2\2\2\2o\3\2\2\2\2q"+
		"\3\2\2\2\3{\3\2\2\2\5\u008d\3\2\2\2\7\u009e\3\2\2\2\t\u00b0\3\2\2\2\13"+
		"\u00b4\3\2\2\2\r\u00b6\3\2\2\2\17\u00b8\3\2\2\2\21\u00ba\3\2\2\2\23\u00bc"+
		"\3\2\2\2\25\u00be\3\2\2\2\27\u00c0\3\2\2\2\31\u00c2\3\2\2\2\33\u00c4\3"+
		"\2\2\2\35\u00c8\3\2\2\2\37\u00cb\3\2\2\2!\u00ce\3\2\2\2#\u00d0\3\2\2\2"+
		"%\u00d2\3\2\2\2\'\u00d4\3\2\2\2)\u00d6\3\2\2\2+\u00dc\3\2\2\2-\u00de\3"+
		"\2\2\2/\u00e0\3\2\2\2\61\u00e9\3\2\2\2\63\u00ec\3\2\2\2\65\u00ee\3\2\2"+
		"\2\67\u00f3\3\2\2\29\u00f7\3\2\2\2;\u00fd\3\2\2\2=\u0106\3\2\2\2?\u010b"+
		"\3\2\2\2A\u0110\3\2\2\2C\u0112\3\2\2\2E\u0115\3\2\2\2G\u0118\3\2\2\2I"+
		"\u0120\3\2\2\2K\u0125\3\2\2\2M\u0127\3\2\2\2O\u0129\3\2\2\2Q\u012b\3\2"+
		"\2\2S\u012d\3\2\2\2U\u0134\3\2\2\2W\u0139\3\2\2\2Y\u013f\3\2\2\2[\u014a"+
		"\3\2\2\2]\u0150\3\2\2\2_\u0155\3\2\2\2a\u0159\3\2\2\2c\u015d\3\2\2\2e"+
		"\u0163\3\2\2\2g\u0167\3\2\2\2i\u0170\3\2\2\2k\u0177\3\2\2\2m\u0181\3\2"+
		"\2\2o\u018d\3\2\2\2q\u0194\3\2\2\2s\u0198\3\2\2\2u\u019d\3\2\2\2w\u019f"+
		"\3\2\2\2y\u01a1\3\2\2\2{|\7k\2\2|}\7p\2\2}~\7h\2\2~\177\7q\2\2\177\u0080"+
		"\7t\2\2\u0080\u0081\7o\2\2\u0081\u0082\7c\2\2\u0082\u0083\7v\2\2\u0083"+
		"\u0084\7k\2\2\u0084\u0085\7q\2\2\u0085\u0086\7p\2\2\u0086\u0087\7/\2\2"+
		"\u0087\u0088\7r\2\2\u0088\u0089\7j\2\2\u0089\u008a\7c\2\2\u008a\u008b"+
		"\7u\2\2\u008b\u008c\7g\2\2\u008c\4\3\2\2\2\u008d\u008e\7k\2\2\u008e\u008f"+
		"\7p\2\2\u008f\u0090\7v\2\2\u0090\u0091\7g\2\2\u0091\u0092\7t\2\2\u0092"+
		"\u0093\7o\2\2\u0093\u0094\7g\2\2\u0094\u0095\7|\2\2\u0095\u0096\7|\2\2"+
		"\u0096\u0097\7q\2\2\u0097\u0098\7/\2\2\u0098\u0099\7r\2\2\u0099\u009a"+
		"\7j\2\2\u009a\u009b\7c\2\2\u009b\u009c\7u\2\2\u009c\u009d\7g\2\2\u009d"+
		"\6\3\2\2\2\u009e\u009f\7k\2\2\u009f\u00a0\7p\2\2\u00a0\u00a1\7v\2\2\u00a1"+
		"\u00a2\7g\2\2\u00a2\u00a3\7t\2\2\u00a3\u00a4\7c\2\2\u00a4\u00a5\7e\2\2"+
		"\u00a5\u00a6\7v\2\2\u00a6\u00a7\7k\2\2\u00a7\u00a8\7q\2\2\u00a8\u00a9"+
		"\7p\2\2\u00a9\u00aa\7/\2\2\u00aa\u00ab\7r\2\2\u00ab\u00ac\7j\2\2\u00ac"+
		"\u00ad\7c\2\2\u00ad\u00ae\7u\2\2\u00ae\u00af\7g\2\2\u00af\b\3\2\2\2\u00b0"+
		"\u00b1\5Q)\2\u00b1\u00b2\5Q)\2\u00b2\u00b3\5Q)\2\u00b3\n\3\2\2\2\u00b4"+
		"\u00b5\7-\2\2\u00b5\f\3\2\2\2\u00b6\u00b7\7/\2\2\u00b7\16\3\2\2\2\u00b8"+
		"\u00b9\7,\2\2\u00b9\20\3\2\2\2\u00ba\u00bb\7\61\2\2\u00bb\22\3\2\2\2\u00bc"+
		"\u00bd\7>\2\2\u00bd\24\3\2\2\2\u00be\u00bf\7@\2\2\u00bf\26\3\2\2\2\u00c0"+
		"\u00c1\7?\2\2\u00c1\30\3\2\2\2\u00c2\u00c3\7#\2\2\u00c3\32\3\2\2\2\u00c4"+
		"\u00c5\7/\2\2\u00c5\u00c6\7$\2\2\u00c6\u00c7\7/\2\2\u00c7\34\3\2\2\2\u00c8"+
		"\u00c9\7>\2\2\u00c9\u00ca\7/\2\2\u00ca\36\3\2\2\2\u00cb\u00cc\7/\2\2\u00cc"+
		"\u00cd\7@\2\2\u00cd \3\2\2\2\u00ce\u00cf\7}\2\2\u00cf\"\3\2\2\2\u00d0"+
		"\u00d1\7\177\2\2\u00d1$\3\2\2\2\u00d2\u00d3\7]\2\2\u00d3&\3\2\2\2\u00d4"+
		"\u00d5\7_\2\2\u00d5(\3\2\2\2\u00d6\u00d7\7d\2\2\u00d7\u00d8\7t\2\2\u00d8"+
		"\u00d9\7g\2\2\u00d9\u00da\7c\2\2\u00da\u00db\7m\2\2\u00db*\3\2\2\2\u00dc"+
		"\u00dd\7<\2\2\u00dd,\3\2\2\2\u00de\u00df\7.\2\2\u00df.\3\2\2\2\u00e0\u00e1"+
		"\7e\2\2\u00e1\u00e2\7q\2\2\u00e2\u00e3\7p\2\2\u00e3\u00e4\7v\2\2\u00e4"+
		"\u00e5\7k\2\2\u00e5\u00e6\7p\2\2\u00e6\u00e7\7w\2\2\u00e7\u00e8\7g\2\2"+
		"\u00e8\60\3\2\2\2\u00e9\u00ea\7f\2\2\u00ea\u00eb\7q\2\2\u00eb\62\3\2\2"+
		"\2\u00ec\u00ed\7\60\2\2\u00ed\64\3\2\2\2\u00ee\u00ef\7g\2\2\u00ef\u00f0"+
		"\7n\2\2\u00f0\u00f1\7u\2\2\u00f1\u00f2\7g\2\2\u00f2\66\3\2\2\2\u00f3\u00f4"+
		"\7g\2\2\u00f4\u00f5\7p\2\2\u00f5\u00f6\7f\2\2\u00f68\3\2\2\2\u00f7\u00f8"+
		"\7h\2\2\u00f8\u00f9\7c\2\2\u00f9\u00fa\7n\2\2\u00fa\u00fb\7u\2\2\u00fb"+
		"\u00fc\7g\2\2\u00fc:\3\2\2\2\u00fd\u00fe\7h\2\2\u00fe\u00ff\7w\2\2\u00ff"+
		"\u0100\7p\2\2\u0100\u0101\7e\2\2\u0101\u0102\7v\2\2\u0102\u0103\7k\2\2"+
		"\u0103\u0104\7q\2\2\u0104\u0105\7p\2\2\u0105<\3\2\2\2\u0106\u0107\7h\2"+
		"\2\u0107\u0108\7x\2\2\u0108\u0109\7c\2\2\u0109\u010a\7t\2\2\u010a>\3\2"+
		"\2\2\u010b\u010c\7i\2\2\u010c\u010d\7x\2\2\u010d\u010e\7c\2\2\u010e\u010f"+
		"\7t\2\2\u010f@\3\2\2\2\u0110\u0111\7%\2\2\u0111B\3\2\2\2\u0112\u0113\7"+
		"k\2\2\u0113\u0114\7h\2\2\u0114D\3\2\2\2\u0115\u0116\7p\2\2\u0116\u0117"+
		"\7q\2\2\u0117F\3\2\2\2\u0118\u0119\7p\2\2\u0119\u011a\7q\2\2\u011a\u011b"+
		"\7v\2\2\u011b\u011c\7j\2\2\u011c\u011d\7k\2\2\u011d\u011e\7p\2\2\u011e"+
		"\u011f\7i\2\2\u011fH\3\2\2\2\u0120\u0121\7p\2\2\u0121\u0122\7w\2\2\u0122"+
		"\u0123\7n\2\2\u0123\u0124\7n\2\2\u0124J\3\2\2\2\u0125\u0126\7*\2\2\u0126"+
		"L\3\2\2\2\u0127\u0128\7+\2\2\u0128N\3\2\2\2\u0129\u012a\7A\2\2\u012aP"+
		"\3\2\2\2\u012b\u012c\7$\2\2\u012cR\3\2\2\2\u012d\u012e\7t\2\2\u012e\u012f"+
		"\7g\2\2\u012f\u0130\7v\2\2\u0130\u0131\7w\2\2\u0131\u0132\7t\2\2\u0132"+
		"\u0133\7p\2\2\u0133T\3\2\2\2\u0134\u0135\7v\2\2\u0135\u0136\7j\2\2\u0136"+
		"\u0137\7g\2\2\u0137\u0138\7p\2\2\u0138V\3\2\2\2\u0139\u013a\7h\2\2\u013a"+
		"\u013b\7k\2\2\u013b\u013c\7p\2\2\u013c\u013d\7c\2\2\u013d\u013e\7n\2\2"+
		"\u013eX\3\2\2\2\u013f\u0140\7v\2\2\u0140\u0141\7t\2\2\u0141\u0142\7c\2"+
		"\2\u0142\u0143\7p\2\2\u0143\u0144\7u\2\2\u0144\u0145\7k\2\2\u0145\u0146"+
		"\7v\2\2\u0146\u0147\7k\2\2\u0147\u0148\7q\2\2\u0148\u0149\7p\2\2\u0149"+
		"Z\3\2\2\2\u014a\u014b\7u\2\2\u014b\u014c\7v\2\2\u014c\u014d\7c\2\2\u014d"+
		"\u014e\7t\2\2\u014e\u014f\7v\2\2\u014f\\\3\2\2\2\u0150\u0151\7v\2\2\u0151"+
		"\u0152\7t\2\2\u0152\u0153\7w\2\2\u0153\u0154\7g\2\2\u0154^\3\2\2\2\u0155"+
		"\u0156\7x\2\2\u0156\u0157\7c\2\2\u0157\u0158\7n\2\2\u0158`\3\2\2\2\u0159"+
		"\u015a\7x\2\2\u015a\u015b\7c\2\2\u015b\u015c\7t\2\2\u015cb\3\2\2\2\u015d"+
		"\u015e\7y\2\2\u015e\u015f\7j\2\2\u015f\u0160\7k\2\2\u0160\u0161\7n\2\2"+
		"\u0161\u0162\7g\2\2\u0162d\3\2\2\2\u0163\u0164\7{\2\2\u0164\u0165\7g\2"+
		"\2\u0165\u0166\7u\2\2\u0166f\3\2\2\2\u0167\u016c\5w<\2\u0168\u016b\5u"+
		";\2\u0169\u016b\5y=\2\u016a\u0168\3\2\2\2\u016a\u0169\3\2\2\2\u016b\u016e"+
		"\3\2\2\2\u016c\u016a\3\2\2\2\u016c\u016d\3\2\2\2\u016dh\3\2\2\2\u016e"+
		"\u016c\3\2\2\2\u016f\u0171\7/\2\2\u0170\u016f\3\2\2\2\u0170\u0171\3\2"+
		"\2\2\u0171\u0173\3\2\2\2\u0172\u0174\5y=\2\u0173\u0172\3\2\2\2\u0174\u0175"+
		"\3\2\2\2\u0175\u0173\3\2\2\2\u0175\u0176\3\2\2\2\u0176j\3\2\2\2\u0177"+
		"\u017c\7$\2\2\u0178\u017b\5s:\2\u0179\u017b\n\2\2\2\u017a\u0178\3\2\2"+
		"\2\u017a\u0179\3\2\2\2\u017b\u017e\3\2\2\2\u017c\u017a\3\2\2\2\u017c\u017d"+
		"\3\2\2\2\u017d\u017f\3\2\2\2\u017e\u017c\3\2\2\2\u017f\u0180\7$\2\2\u0180"+
		"l\3\2\2\2\u0181\u0185\5A!\2\u0182\u0184\13\2\2\2\u0183\u0182\3\2\2\2\u0184"+
		"\u0187\3\2\2\2\u0185\u0186\3\2\2\2\u0185\u0183\3\2\2\2\u0186\u0188\3\2"+
		"\2\2\u0187\u0185\3\2\2\2\u0188\u0189\5q9\2\u0189\u018a\3\2\2\2\u018a\u018b"+
		"\b\67\2\2\u018bn\3\2\2\2\u018c\u018e\t\3\2\2\u018d\u018c\3\2\2\2\u018e"+
		"\u018f\3\2\2\2\u018f\u018d\3\2\2\2\u018f\u0190\3\2\2\2\u0190\u0191\3\2"+
		"\2\2\u0191\u0192\b8\3\2\u0192p\3\2\2\2\u0193\u0195\7\17\2\2\u0194\u0193"+
		"\3\2\2\2\u0194\u0195\3\2\2\2\u0195\u0196\3\2\2\2\u0196\u0197\7\f\2\2\u0197"+
		"r\3\2\2\2\u0198\u0199\7^\2\2\u0199\u019a\t\2\2\2\u019at\3\2\2\2\u019b"+
		"\u019e\5w<\2\u019c\u019e\7a\2\2\u019d\u019b\3\2\2\2\u019d\u019c\3\2\2"+
		"\2\u019ev\3\2\2\2\u019f\u01a0\t\4\2\2\u01a0x\3\2\2\2\u01a1\u01a2\4\62"+
		";\2\u01a2z\3\2\2\2\r\2\u016a\u016c\u0170\u0175\u017a\u017c\u0185\u018f"+
		"\u0194\u019d\4\b\2\2\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}