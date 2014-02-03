// Generated from TypeSpec.g4 by ANTLR 4.0
package fi.abo.kogni.soile2.qmarkup.typespec;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class TypeSpecParser extends Parser {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__9=1, T__8=2, T__7=3, T__6=4, T__5=5, T__4=6, T__3=7, T__2=8, T__1=9, 
		T__0=10, TYPE_INTEGER=11, TYPE_STRING=12, TYPE_BOOLEAN=13, BOOLEAN=14, 
		INTEGER=15, STRING=16, REQUIRED=17, ID=18, WS=19;
	public static final String[] tokenNames = {
		"<INVALID>", "']'", "'{'", "')'", "'command'", "','", "'['", "'*'", "':'", 
		"'('", "'}'", "'Integer'", "'String'", "'Boolean'", "BOOLEAN", "INTEGER", 
		"STRING", "'required'", "ID", "WS"
	};
	public static final int
		RULE_spec = 0, RULE_command = 1, RULE_commandname = 2, RULE_body = 3, 
		RULE_object = 4, RULE_def = 5, RULE_field = 6, RULE_type = 7, RULE_simple_type = 8, 
		RULE_compound_type = 9, RULE_integer_type = 10, RULE_string_type = 11, 
		RULE_boolean_type = 12, RULE_array_type = 13, RULE_repeat_type = 14, RULE_object_type = 15;
	public static final String[] ruleNames = {
		"spec", "command", "commandname", "body", "object", "def", "field", "type", 
		"simple_type", "compound_type", "integer_type", "string_type", "boolean_type", 
		"array_type", "repeat_type", "object_type"
	};

	@Override
	public String getGrammarFileName() { return "TypeSpec.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public TypeSpecParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class SpecContext extends ParserRuleContext {
		public CommandContext command(int i) {
			return getRuleContext(CommandContext.class,i);
		}
		public List<CommandContext> command() {
			return getRuleContexts(CommandContext.class);
		}
		public SpecContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_spec; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeSpecListener ) ((TypeSpecListener)listener).enterSpec(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeSpecListener ) ((TypeSpecListener)listener).exitSpec(this);
		}
	}

	public final SpecContext spec() throws RecognitionException {
		SpecContext _localctx = new SpecContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_spec);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(33); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(32); command();
				}
				}
				setState(35); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==4 );
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

	public static class CommandContext extends ParserRuleContext {
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
		public CommandnameContext commandname() {
			return getRuleContext(CommandnameContext.class,0);
		}
		public CommandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_command; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeSpecListener ) ((TypeSpecListener)listener).enterCommand(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeSpecListener ) ((TypeSpecListener)listener).exitCommand(this);
		}
	}

	public final CommandContext command() throws RecognitionException {
		CommandContext _localctx = new CommandContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_command);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(37); match(4);
			setState(38); commandname();
			setState(39); body();
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

	public static class CommandnameContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(TypeSpecParser.ID, 0); }
		public CommandnameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_commandname; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeSpecListener ) ((TypeSpecListener)listener).enterCommandname(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeSpecListener ) ((TypeSpecListener)listener).exitCommandname(this);
		}
	}

	public final CommandnameContext commandname() throws RecognitionException {
		CommandnameContext _localctx = new CommandnameContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_commandname);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(41); match(ID);
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

	public static class BodyContext extends ParserRuleContext {
		public List<DefContext> def() {
			return getRuleContexts(DefContext.class);
		}
		public DefContext def(int i) {
			return getRuleContext(DefContext.class,i);
		}
		public BodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_body; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeSpecListener ) ((TypeSpecListener)listener).enterBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeSpecListener ) ((TypeSpecListener)listener).exitBody(this);
		}
	}

	public final BodyContext body() throws RecognitionException {
		BodyContext _localctx = new BodyContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_body);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(43); match(2);
			setState(44); def();
			setState(49);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==5) {
				{
				{
				setState(45); match(5);
				setState(46); def();
				}
				}
				setState(51);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(52); match(10);
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
		public List<DefContext> def() {
			return getRuleContexts(DefContext.class);
		}
		public DefContext def(int i) {
			return getRuleContext(DefContext.class,i);
		}
		public ObjectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_object; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeSpecListener ) ((TypeSpecListener)listener).enterObject(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeSpecListener ) ((TypeSpecListener)listener).exitObject(this);
		}
	}

	public final ObjectContext object() throws RecognitionException {
		ObjectContext _localctx = new ObjectContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_object);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(54); match(2);
			setState(55); def();
			setState(60);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==5) {
				{
				{
				setState(56); match(5);
				setState(57); def();
				}
				}
				setState(62);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(63); match(10);
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

	public static class DefContext extends ParserRuleContext {
		public FieldContext field() {
			return getRuleContext(FieldContext.class,0);
		}
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public DefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_def; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeSpecListener ) ((TypeSpecListener)listener).enterDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeSpecListener ) ((TypeSpecListener)listener).exitDef(this);
		}
	}

	public final DefContext def() throws RecognitionException {
		DefContext _localctx = new DefContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_def);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(65); field();
			setState(66); match(8);
			setState(67); type();
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

	public static class FieldContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(TypeSpecParser.ID, 0); }
		public FieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_field; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeSpecListener ) ((TypeSpecListener)listener).enterField(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeSpecListener ) ((TypeSpecListener)listener).exitField(this);
		}
	}

	public final FieldContext field() throws RecognitionException {
		FieldContext _localctx = new FieldContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_field);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(69); match(ID);
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

	public static class TypeContext extends ParserRuleContext {
		public Simple_typeContext simple_type() {
			return getRuleContext(Simple_typeContext.class,0);
		}
		public Compound_typeContext compound_type() {
			return getRuleContext(Compound_typeContext.class,0);
		}
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeSpecListener ) ((TypeSpecListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeSpecListener ) ((TypeSpecListener)listener).exitType(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_type);
		try {
			setState(73);
			switch (_input.LA(1)) {
			case TYPE_INTEGER:
			case TYPE_STRING:
			case TYPE_BOOLEAN:
				enterOuterAlt(_localctx, 1);
				{
				setState(71); simple_type();
				}
				break;
			case 2:
			case 6:
				enterOuterAlt(_localctx, 2);
				{
				setState(72); compound_type();
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

	public static class Simple_typeContext extends ParserRuleContext {
		public Boolean_typeContext boolean_type() {
			return getRuleContext(Boolean_typeContext.class,0);
		}
		public String_typeContext string_type() {
			return getRuleContext(String_typeContext.class,0);
		}
		public Integer_typeContext integer_type() {
			return getRuleContext(Integer_typeContext.class,0);
		}
		public Simple_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_simple_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeSpecListener ) ((TypeSpecListener)listener).enterSimple_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeSpecListener ) ((TypeSpecListener)listener).exitSimple_type(this);
		}
	}

	public final Simple_typeContext simple_type() throws RecognitionException {
		Simple_typeContext _localctx = new Simple_typeContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_simple_type);
		try {
			setState(78);
			switch (_input.LA(1)) {
			case TYPE_INTEGER:
				enterOuterAlt(_localctx, 1);
				{
				setState(75); integer_type();
				}
				break;
			case TYPE_STRING:
				enterOuterAlt(_localctx, 2);
				{
				setState(76); string_type();
				}
				break;
			case TYPE_BOOLEAN:
				enterOuterAlt(_localctx, 3);
				{
				setState(77); boolean_type();
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

	public static class Compound_typeContext extends ParserRuleContext {
		public Object_typeContext object_type() {
			return getRuleContext(Object_typeContext.class,0);
		}
		public Repeat_typeContext repeat_type() {
			return getRuleContext(Repeat_typeContext.class,0);
		}
		public Array_typeContext array_type() {
			return getRuleContext(Array_typeContext.class,0);
		}
		public Compound_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_compound_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeSpecListener ) ((TypeSpecListener)listener).enterCompound_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeSpecListener ) ((TypeSpecListener)listener).exitCompound_type(this);
		}
	}

	public final Compound_typeContext compound_type() throws RecognitionException {
		Compound_typeContext _localctx = new Compound_typeContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_compound_type);
		try {
			setState(83);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(80); array_type();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(81); repeat_type();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(82); object_type();
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

	public static class Integer_typeContext extends ParserRuleContext {
		public TerminalNode INTEGER() { return getToken(TypeSpecParser.INTEGER, 0); }
		public TerminalNode TYPE_INTEGER() { return getToken(TypeSpecParser.TYPE_INTEGER, 0); }
		public Integer_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_integer_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeSpecListener ) ((TypeSpecListener)listener).enterInteger_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeSpecListener ) ((TypeSpecListener)listener).exitInteger_type(this);
		}
	}

	public final Integer_typeContext integer_type() throws RecognitionException {
		Integer_typeContext _localctx = new Integer_typeContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_integer_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(85); match(TYPE_INTEGER);
			setState(89);
			_la = _input.LA(1);
			if (_la==9) {
				{
				setState(86); match(9);
				setState(87); match(INTEGER);
				setState(88); match(3);
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

	public static class String_typeContext extends ParserRuleContext {
		public TerminalNode TYPE_STRING() { return getToken(TypeSpecParser.TYPE_STRING, 0); }
		public TerminalNode STRING() { return getToken(TypeSpecParser.STRING, 0); }
		public String_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_string_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeSpecListener ) ((TypeSpecListener)listener).enterString_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeSpecListener ) ((TypeSpecListener)listener).exitString_type(this);
		}
	}

	public final String_typeContext string_type() throws RecognitionException {
		String_typeContext _localctx = new String_typeContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_string_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(91); match(TYPE_STRING);
			setState(95);
			_la = _input.LA(1);
			if (_la==9) {
				{
				setState(92); match(9);
				setState(93); match(STRING);
				setState(94); match(3);
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

	public static class Boolean_typeContext extends ParserRuleContext {
		public TerminalNode BOOLEAN() { return getToken(TypeSpecParser.BOOLEAN, 0); }
		public TerminalNode TYPE_BOOLEAN() { return getToken(TypeSpecParser.TYPE_BOOLEAN, 0); }
		public Boolean_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_boolean_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeSpecListener ) ((TypeSpecListener)listener).enterBoolean_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeSpecListener ) ((TypeSpecListener)listener).exitBoolean_type(this);
		}
	}

	public final Boolean_typeContext boolean_type() throws RecognitionException {
		Boolean_typeContext _localctx = new Boolean_typeContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_boolean_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(97); match(TYPE_BOOLEAN);
			setState(101);
			_la = _input.LA(1);
			if (_la==9) {
				{
				setState(98); match(9);
				setState(99); match(BOOLEAN);
				setState(100); match(3);
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

	public static class Array_typeContext extends ParserRuleContext {
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public Array_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_array_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeSpecListener ) ((TypeSpecListener)listener).enterArray_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeSpecListener ) ((TypeSpecListener)listener).exitArray_type(this);
		}
	}

	public final Array_typeContext array_type() throws RecognitionException {
		Array_typeContext _localctx = new Array_typeContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_array_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(103); match(6);
			setState(104); type();
			setState(109);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==5) {
				{
				{
				setState(105); match(5);
				setState(106); type();
				}
				}
				setState(111);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(112); match(1);
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

	public static class Repeat_typeContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public Repeat_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_repeat_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeSpecListener ) ((TypeSpecListener)listener).enterRepeat_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeSpecListener ) ((TypeSpecListener)listener).exitRepeat_type(this);
		}
	}

	public final Repeat_typeContext repeat_type() throws RecognitionException {
		Repeat_typeContext _localctx = new Repeat_typeContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_repeat_type);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(114); match(6);
			setState(115); type();
			setState(116); match(7);
			setState(117); match(1);
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

	public static class Object_typeContext extends ParserRuleContext {
		public ObjectContext object() {
			return getRuleContext(ObjectContext.class,0);
		}
		public Object_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_object_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeSpecListener ) ((TypeSpecListener)listener).enterObject_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeSpecListener ) ((TypeSpecListener)listener).exitObject_type(this);
		}
	}

	public final Object_typeContext object_type() throws RecognitionException {
		Object_typeContext _localctx = new Object_typeContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_object_type);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(119); object();
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
		"\2\3\25|\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t"+
		"\t\4\n\t\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21"+
		"\t\21\3\2\6\2$\n\2\r\2\16\2%\3\3\3\3\3\3\3\3\3\4\3\4\3\5\3\5\3\5\3\5\7"+
		"\5\62\n\5\f\5\16\5\65\13\5\3\5\3\5\3\6\3\6\3\6\3\6\7\6=\n\6\f\6\16\6@"+
		"\13\6\3\6\3\6\3\7\3\7\3\7\3\7\3\b\3\b\3\t\3\t\5\tL\n\t\3\n\3\n\3\n\5\n"+
		"Q\n\n\3\13\3\13\3\13\5\13V\n\13\3\f\3\f\3\f\3\f\5\f\\\n\f\3\r\3\r\3\r"+
		"\3\r\5\rb\n\r\3\16\3\16\3\16\3\16\5\16h\n\16\3\17\3\17\3\17\3\17\7\17"+
		"n\n\17\f\17\16\17q\13\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\21\3\21"+
		"\3\21\2\22\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \2\2w\2#\3\2\2\2\4\'"+
		"\3\2\2\2\6+\3\2\2\2\b-\3\2\2\2\n8\3\2\2\2\fC\3\2\2\2\16G\3\2\2\2\20K\3"+
		"\2\2\2\22P\3\2\2\2\24U\3\2\2\2\26W\3\2\2\2\30]\3\2\2\2\32c\3\2\2\2\34"+
		"i\3\2\2\2\36t\3\2\2\2 y\3\2\2\2\"$\5\4\3\2#\"\3\2\2\2$%\3\2\2\2%#\3\2"+
		"\2\2%&\3\2\2\2&\3\3\2\2\2\'(\7\6\2\2()\5\6\4\2)*\5\b\5\2*\5\3\2\2\2+,"+
		"\7\24\2\2,\7\3\2\2\2-.\7\4\2\2.\63\5\f\7\2/\60\7\7\2\2\60\62\5\f\7\2\61"+
		"/\3\2\2\2\62\65\3\2\2\2\63\61\3\2\2\2\63\64\3\2\2\2\64\66\3\2\2\2\65\63"+
		"\3\2\2\2\66\67\7\f\2\2\67\t\3\2\2\289\7\4\2\29>\5\f\7\2:;\7\7\2\2;=\5"+
		"\f\7\2<:\3\2\2\2=@\3\2\2\2><\3\2\2\2>?\3\2\2\2?A\3\2\2\2@>\3\2\2\2AB\7"+
		"\f\2\2B\13\3\2\2\2CD\5\16\b\2DE\7\n\2\2EF\5\20\t\2F\r\3\2\2\2GH\7\24\2"+
		"\2H\17\3\2\2\2IL\5\22\n\2JL\5\24\13\2KI\3\2\2\2KJ\3\2\2\2L\21\3\2\2\2"+
		"MQ\5\26\f\2NQ\5\30\r\2OQ\5\32\16\2PM\3\2\2\2PN\3\2\2\2PO\3\2\2\2Q\23\3"+
		"\2\2\2RV\5\34\17\2SV\5\36\20\2TV\5 \21\2UR\3\2\2\2US\3\2\2\2UT\3\2\2\2"+
		"V\25\3\2\2\2W[\7\r\2\2XY\7\13\2\2YZ\7\21\2\2Z\\\7\5\2\2[X\3\2\2\2[\\\3"+
		"\2\2\2\\\27\3\2\2\2]a\7\16\2\2^_\7\13\2\2_`\7\22\2\2`b\7\5\2\2a^\3\2\2"+
		"\2ab\3\2\2\2b\31\3\2\2\2cg\7\17\2\2de\7\13\2\2ef\7\20\2\2fh\7\5\2\2gd"+
		"\3\2\2\2gh\3\2\2\2h\33\3\2\2\2ij\7\b\2\2jo\5\20\t\2kl\7\7\2\2ln\5\20\t"+
		"\2mk\3\2\2\2nq\3\2\2\2om\3\2\2\2op\3\2\2\2pr\3\2\2\2qo\3\2\2\2rs\7\3\2"+
		"\2s\35\3\2\2\2tu\7\b\2\2uv\5\20\t\2vw\7\t\2\2wx\7\3\2\2x\37\3\2\2\2yz"+
		"\5\n\6\2z!\3\2\2\2\f%\63>KPU[ago";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
	}
}