// Generated from Command.g4 by ANTLR 4.0
package fi.abo.kogni.soile2.qmarkup;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.v4.runtime.tree.ErrorNode;

public class CommandBaseListener implements CommandListener {
	@Override public void enterQString(CommandParser.QStringContext ctx) { }
	@Override public void exitQString(CommandParser.QStringContext ctx) { }

	@Override public void enterNQString(CommandParser.NQStringContext ctx) { }
	@Override public void exitNQString(CommandParser.NQStringContext ctx) { }

	@Override public void enterInteger(CommandParser.IntegerContext ctx) { }
	@Override public void exitInteger(CommandParser.IntegerContext ctx) { }

	@Override public void enterPair(CommandParser.PairContext ctx) { }
	@Override public void exitPair(CommandParser.PairContext ctx) { }

	@Override public void enterVArray(CommandParser.VArrayContext ctx) { }
	@Override public void exitVArray(CommandParser.VArrayContext ctx) { }

	@Override public void enterVString(CommandParser.VStringContext ctx) { }
	@Override public void exitVString(CommandParser.VStringContext ctx) { }

	@Override public void enterObject(CommandParser.ObjectContext ctx) { }
	@Override public void exitObject(CommandParser.ObjectContext ctx) { }

	@Override public void enterVInteger(CommandParser.VIntegerContext ctx) { }
	@Override public void exitVInteger(CommandParser.VIntegerContext ctx) { }

	@Override public void enterVObject(CommandParser.VObjectContext ctx) { }
	@Override public void exitVObject(CommandParser.VObjectContext ctx) { }

	@Override public void enterString(CommandParser.StringContext ctx) { }
	@Override public void exitString(CommandParser.StringContext ctx) { }

	@Override public void enterCommand(CommandParser.CommandContext ctx) { }
	@Override public void exitCommand(CommandParser.CommandContext ctx) { }

	@Override public void enterBool(CommandParser.BoolContext ctx) { }
	@Override public void exitBool(CommandParser.BoolContext ctx) { }

	@Override public void enterVBool(CommandParser.VBoolContext ctx) { }
	@Override public void exitVBool(CommandParser.VBoolContext ctx) { }

	@Override public void enterArray(CommandParser.ArrayContext ctx) { }
	@Override public void exitArray(CommandParser.ArrayContext ctx) { }

	@Override public void enterEveryRule(ParserRuleContext ctx) { }
	@Override public void exitEveryRule(ParserRuleContext ctx) { }
	@Override public void visitTerminal(TerminalNode node) { }
	@Override public void visitErrorNode(ErrorNode node) { }
}