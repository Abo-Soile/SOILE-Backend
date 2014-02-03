// Generated from TypeSpec.g4 by ANTLR 4.0
package fi.abo.kogni.soile2.qmarkup.typespec;
import org.antlr.v4.runtime.tree.*;
import org.antlr.v4.runtime.Token;

public interface TypeSpecListener extends ParseTreeListener {
	void enterSpec(TypeSpecParser.SpecContext ctx);
	void exitSpec(TypeSpecParser.SpecContext ctx);

	void enterBody(TypeSpecParser.BodyContext ctx);
	void exitBody(TypeSpecParser.BodyContext ctx);

	void enterBoolean_type(TypeSpecParser.Boolean_typeContext ctx);
	void exitBoolean_type(TypeSpecParser.Boolean_typeContext ctx);

	void enterCompound_type(TypeSpecParser.Compound_typeContext ctx);
	void exitCompound_type(TypeSpecParser.Compound_typeContext ctx);

	void enterObject(TypeSpecParser.ObjectContext ctx);
	void exitObject(TypeSpecParser.ObjectContext ctx);

	void enterType(TypeSpecParser.TypeContext ctx);
	void exitType(TypeSpecParser.TypeContext ctx);

	void enterInteger_type(TypeSpecParser.Integer_typeContext ctx);
	void exitInteger_type(TypeSpecParser.Integer_typeContext ctx);

	void enterObject_type(TypeSpecParser.Object_typeContext ctx);
	void exitObject_type(TypeSpecParser.Object_typeContext ctx);

	void enterField(TypeSpecParser.FieldContext ctx);
	void exitField(TypeSpecParser.FieldContext ctx);

	void enterRepeat_type(TypeSpecParser.Repeat_typeContext ctx);
	void exitRepeat_type(TypeSpecParser.Repeat_typeContext ctx);

	void enterDef(TypeSpecParser.DefContext ctx);
	void exitDef(TypeSpecParser.DefContext ctx);

	void enterCommand(TypeSpecParser.CommandContext ctx);
	void exitCommand(TypeSpecParser.CommandContext ctx);

	void enterSimple_type(TypeSpecParser.Simple_typeContext ctx);
	void exitSimple_type(TypeSpecParser.Simple_typeContext ctx);

	void enterString_type(TypeSpecParser.String_typeContext ctx);
	void exitString_type(TypeSpecParser.String_typeContext ctx);

	void enterArray_type(TypeSpecParser.Array_typeContext ctx);
	void exitArray_type(TypeSpecParser.Array_typeContext ctx);

	void enterCommandname(TypeSpecParser.CommandnameContext ctx);
	void exitCommandname(TypeSpecParser.CommandnameContext ctx);
}