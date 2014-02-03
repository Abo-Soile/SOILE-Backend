// Generated from Command.g4 by ANTLR 4.0
package fi.abo.kogni.soile2.qmarkup;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CommandLexer extends Lexer {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__7=1, T__6=2, T__5=3, T__4=4, T__3=5, T__2=6, T__1=7, T__0=8, ZERO=9, 
		NONZERO=10, NQSTRING=11, STRING=12, WS=13;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"']'", "'{'", "','", "'['", "':'", "'false'", "'}'", "'true'", "'0'", 
		"NONZERO", "NQSTRING", "STRING", "WS"
	};
	public static final String[] ruleNames = {
		"T__7", "T__6", "T__5", "T__4", "T__3", "T__2", "T__1", "T__0", "ZERO", 
		"NONZERO", "NQSTRING", "STRING", "ESC", "WS"
	};


	public CommandLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Command.g4"; }

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
		case 13: WS_action((RuleContext)_localctx, actionIndex); break;
		}
	}
	private void WS_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0: skip();  break;
		}
	}

	public static final String _serializedATN =
		"\2\4\17`\b\1\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4"+
		"\t\t\t\4\n\t\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\3\2\3\2\3"+
		"\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\t\3\t"+
		"\3\t\3\t\3\t\3\n\3\n\3\13\5\13:\n\13\3\13\3\13\7\13>\n\13\f\13\16\13A"+
		"\13\13\3\f\3\f\7\fE\n\f\f\f\16\fH\13\f\3\r\3\r\3\r\7\rM\n\r\f\r\16\rP"+
		"\13\r\3\r\3\r\3\16\3\16\3\16\3\16\5\16X\n\16\3\17\6\17[\n\17\r\17\16\17"+
		"\\\3\17\3\17\3N\20\3\3\1\5\4\1\7\5\1\t\6\1\13\7\1\r\b\1\17\t\1\21\n\1"+
		"\23\13\1\25\f\1\27\r\1\31\16\1\33\2\1\35\17\2\3\2\7\3\63;\3\62;\5C\\a"+
		"ac|\6\62;C\\aac|\5\13\f\17\17\"\"e\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2"+
		"\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3"+
		"\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\35\3\2\2\2\3\37\3\2\2"+
		"\2\5!\3\2\2\2\7#\3\2\2\2\t%\3\2\2\2\13\'\3\2\2\2\r)\3\2\2\2\17/\3\2\2"+
		"\2\21\61\3\2\2\2\23\66\3\2\2\2\259\3\2\2\2\27B\3\2\2\2\31I\3\2\2\2\33"+
		"W\3\2\2\2\35Z\3\2\2\2\37 \7_\2\2 \4\3\2\2\2!\"\7}\2\2\"\6\3\2\2\2#$\7"+
		".\2\2$\b\3\2\2\2%&\7]\2\2&\n\3\2\2\2\'(\7<\2\2(\f\3\2\2\2)*\7h\2\2*+\7"+
		"c\2\2+,\7n\2\2,-\7u\2\2-.\7g\2\2.\16\3\2\2\2/\60\7\177\2\2\60\20\3\2\2"+
		"\2\61\62\7v\2\2\62\63\7t\2\2\63\64\7w\2\2\64\65\7g\2\2\65\22\3\2\2\2\66"+
		"\67\7\62\2\2\67\24\3\2\2\28:\7/\2\298\3\2\2\29:\3\2\2\2:;\3\2\2\2;?\t"+
		"\2\2\2<>\t\3\2\2=<\3\2\2\2>A\3\2\2\2?=\3\2\2\2?@\3\2\2\2@\26\3\2\2\2A"+
		"?\3\2\2\2BF\t\4\2\2CE\t\5\2\2DC\3\2\2\2EH\3\2\2\2FD\3\2\2\2FG\3\2\2\2"+
		"G\30\3\2\2\2HF\3\2\2\2IN\7$\2\2JM\5\33\16\2KM\13\2\2\2LJ\3\2\2\2LK\3\2"+
		"\2\2MP\3\2\2\2NO\3\2\2\2NL\3\2\2\2OQ\3\2\2\2PN\3\2\2\2QR\7$\2\2R\32\3"+
		"\2\2\2ST\7^\2\2TX\7$\2\2UV\7^\2\2VX\7^\2\2WS\3\2\2\2WU\3\2\2\2X\34\3\2"+
		"\2\2Y[\t\6\2\2ZY\3\2\2\2[\\\3\2\2\2\\Z\3\2\2\2\\]\3\2\2\2]^\3\2\2\2^_"+
		"\b\17\2\2_\36\3\2\2\2\n\29?FLNW\\";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
	}
}