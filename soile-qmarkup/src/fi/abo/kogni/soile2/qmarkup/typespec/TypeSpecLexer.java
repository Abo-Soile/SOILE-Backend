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
		T__0=10, TYPE_INTEGER=11, TYPE_STRING=12, TYPE_BOOLEAN=13, BOOLEAN=14, 
		INTEGER=15, STRING=16, REQUIRED=17, ID=18, WS=19;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"']'", "'{'", "')'", "'command'", "','", "'['", "'*'", "':'", "'('", "'}'", 
		"'Integer'", "'String'", "'Boolean'", "BOOLEAN", "INTEGER", "STRING", 
		"'required'", "ID", "WS"
	};
	public static final String[] ruleNames = {
		"T__9", "T__8", "T__7", "T__6", "T__5", "T__4", "T__3", "T__2", "T__1", 
		"T__0", "TYPE_INTEGER", "TYPE_STRING", "TYPE_BOOLEAN", "BOOLEAN", "INTEGER", 
		"STRING", "REQUIRED", "ID", "INT", "WS"
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
		case 19: WS_action((RuleContext)_localctx, actionIndex); break;
		}
	}
	private void WS_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0: skip();  break;
		}
	}

	public static final String _serializedATN =
		"\2\4\25\u0096\b\1\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b"+
		"\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20"+
		"\t\20\4\21\t\21\4\22\t\22\4\23\t\23\4\24\t\24\4\25\t\25\3\2\3\2\3\3\3"+
		"\3\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t"+
		"\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r"+
		"\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3"+
		"\17\3\17\3\17\3\17\3\17\3\17\5\17f\n\17\3\20\5\20i\n\20\3\20\3\20\3\21"+
		"\3\21\7\21o\n\21\f\21\16\21r\13\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22"+
		"\3\22\3\22\3\22\3\22\3\23\3\23\7\23\u0081\n\23\f\23\16\23\u0084\13\23"+
		"\3\24\3\24\3\24\7\24\u0089\n\24\f\24\16\24\u008c\13\24\5\24\u008e\n\24"+
		"\3\25\6\25\u0091\n\25\r\25\16\25\u0092\3\25\3\25\2\26\3\3\1\5\4\1\7\5"+
		"\1\t\6\1\13\7\1\r\b\1\17\t\1\21\n\1\23\13\1\25\f\1\27\r\1\31\16\1\33\17"+
		"\1\35\20\1\37\21\1!\22\1#\23\1%\24\1\'\2\1)\25\2\3\2\b\7\"\"\62;C\\aa"+
		"c|\5C\\aac|\6\62;C\\aac|\3\63;\3\62;\5\13\f\17\17\"\"\u009b\2\3\3\2\2"+
		"\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3"+
		"\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2"+
		"\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2"+
		"\2\2\2)\3\2\2\2\3+\3\2\2\2\5-\3\2\2\2\7/\3\2\2\2\t\61\3\2\2\2\139\3\2"+
		"\2\2\r;\3\2\2\2\17=\3\2\2\2\21?\3\2\2\2\23A\3\2\2\2\25C\3\2\2\2\27E\3"+
		"\2\2\2\31M\3\2\2\2\33T\3\2\2\2\35e\3\2\2\2\37h\3\2\2\2!l\3\2\2\2#u\3\2"+
		"\2\2%~\3\2\2\2\'\u008d\3\2\2\2)\u0090\3\2\2\2+,\7_\2\2,\4\3\2\2\2-.\7"+
		"}\2\2.\6\3\2\2\2/\60\7+\2\2\60\b\3\2\2\2\61\62\7e\2\2\62\63\7q\2\2\63"+
		"\64\7o\2\2\64\65\7o\2\2\65\66\7c\2\2\66\67\7p\2\2\678\7f\2\28\n\3\2\2"+
		"\29:\7.\2\2:\f\3\2\2\2;<\7]\2\2<\16\3\2\2\2=>\7,\2\2>\20\3\2\2\2?@\7<"+
		"\2\2@\22\3\2\2\2AB\7*\2\2B\24\3\2\2\2CD\7\177\2\2D\26\3\2\2\2EF\7K\2\2"+
		"FG\7p\2\2GH\7v\2\2HI\7g\2\2IJ\7i\2\2JK\7g\2\2KL\7t\2\2L\30\3\2\2\2MN\7"+
		"U\2\2NO\7v\2\2OP\7t\2\2PQ\7k\2\2QR\7p\2\2RS\7i\2\2S\32\3\2\2\2TU\7D\2"+
		"\2UV\7q\2\2VW\7q\2\2WX\7n\2\2XY\7g\2\2YZ\7c\2\2Z[\7p\2\2[\34\3\2\2\2\\"+
		"]\7v\2\2]^\7t\2\2^_\7w\2\2_f\7g\2\2`a\7h\2\2ab\7c\2\2bc\7n\2\2cd\7u\2"+
		"\2df\7g\2\2e\\\3\2\2\2e`\3\2\2\2f\36\3\2\2\2gi\7/\2\2hg\3\2\2\2hi\3\2"+
		"\2\2ij\3\2\2\2jk\5\'\24\2k \3\2\2\2lp\7$\2\2mo\t\2\2\2nm\3\2\2\2or\3\2"+
		"\2\2pn\3\2\2\2pq\3\2\2\2qs\3\2\2\2rp\3\2\2\2st\7$\2\2t\"\3\2\2\2uv\7t"+
		"\2\2vw\7g\2\2wx\7s\2\2xy\7w\2\2yz\7k\2\2z{\7t\2\2{|\7g\2\2|}\7f\2\2}$"+
		"\3\2\2\2~\u0082\t\3\2\2\177\u0081\t\4\2\2\u0080\177\3\2\2\2\u0081\u0084"+
		"\3\2\2\2\u0082\u0080\3\2\2\2\u0082\u0083\3\2\2\2\u0083&\3\2\2\2\u0084"+
		"\u0082\3\2\2\2\u0085\u008e\7\62\2\2\u0086\u008a\t\5\2\2\u0087\u0089\t"+
		"\6\2\2\u0088\u0087\3\2\2\2\u0089\u008c\3\2\2\2\u008a\u0088\3\2\2\2\u008a"+
		"\u008b\3\2\2\2\u008b\u008e\3\2\2\2\u008c\u008a\3\2\2\2\u008d\u0085\3\2"+
		"\2\2\u008d\u0086\3\2\2\2\u008e(\3\2\2\2\u008f\u0091\t\7\2\2\u0090\u008f"+
		"\3\2\2\2\u0091\u0092\3\2\2\2\u0092\u0090\3\2\2\2\u0092\u0093\3\2\2\2\u0093"+
		"\u0094\3\2\2\2\u0094\u0095\b\25\2\2\u0095*\3\2\2\2\n\2ehp\u0082\u008a"+
		"\u008d\u0092";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
	}
}