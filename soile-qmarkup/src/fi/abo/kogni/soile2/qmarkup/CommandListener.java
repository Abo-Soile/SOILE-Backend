// Generated from Command.g4 by ANTLR 4.0
package fi.abo.kogni.soile2.qmarkup;
import org.antlr.v4.runtime.tree.*;
import org.antlr.v4.runtime.Token;

public interface CommandListener extends ParseTreeListener {
	void enterQString(CommandParser.QStringContext ctx);
	void exitQString(CommandParser.QStringContext ctx);

	void enterNQString(CommandParser.NQStringContext ctx);
	void exitNQString(CommandParser.NQStringContext ctx);

	void enterInteger(CommandParser.IntegerContext ctx);
	void exitInteger(CommandParser.IntegerContext ctx);

	void enterPair(CommandParser.PairContext ctx);
	void exitPair(CommandParser.PairContext ctx);

	void enterVArray(CommandParser.VArrayContext ctx);
	void exitVArray(CommandParser.VArrayContext ctx);

	void enterVString(CommandParser.VStringContext ctx);
	void exitVString(CommandParser.VStringContext ctx);

	void enterObject(CommandParser.ObjectContext ctx);
	void exitObject(CommandParser.ObjectContext ctx);

	void enterVInteger(CommandParser.VIntegerContext ctx);
	void exitVInteger(CommandParser.VIntegerContext ctx);

	void enterVObject(CommandParser.VObjectContext ctx);
	void exitVObject(CommandParser.VObjectContext ctx);

	void enterString(CommandParser.StringContext ctx);
	void exitString(CommandParser.StringContext ctx);

	void enterCommand(CommandParser.CommandContext ctx);
	void exitCommand(CommandParser.CommandContext ctx);

	void enterBool(CommandParser.BoolContext ctx);
	void exitBool(CommandParser.BoolContext ctx);

	void enterVBool(CommandParser.VBoolContext ctx);
	void exitVBool(CommandParser.VBoolContext ctx);

	void enterArray(CommandParser.ArrayContext ctx);
	void exitArray(CommandParser.ArrayContext ctx);
}