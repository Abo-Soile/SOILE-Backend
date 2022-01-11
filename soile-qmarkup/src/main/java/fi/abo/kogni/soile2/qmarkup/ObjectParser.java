package fi.abo.kogni.soile2.qmarkup;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.List;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

import fi.abo.kogni.soile2.qmarkup.CommandParser.ArrayContext;
import fi.abo.kogni.soile2.qmarkup.CommandParser.BoolContext;
import fi.abo.kogni.soile2.qmarkup.CommandParser.CommandContext;
import fi.abo.kogni.soile2.qmarkup.CommandParser.IntegerContext;
import fi.abo.kogni.soile2.qmarkup.CommandParser.NQStringContext;
import fi.abo.kogni.soile2.qmarkup.CommandParser.ObjectContext;
import fi.abo.kogni.soile2.qmarkup.CommandParser.PairContext;
import fi.abo.kogni.soile2.qmarkup.CommandParser.QStringContext;
import fi.abo.kogni.soile2.qmarkup.CommandParser.StringContext;
import fi.abo.kogni.soile2.qmarkup.CommandParser.VArrayContext;
import fi.abo.kogni.soile2.qmarkup.CommandParser.VBoolContext;
import fi.abo.kogni.soile2.qmarkup.CommandParser.VIntegerContext;
import fi.abo.kogni.soile2.qmarkup.CommandParser.VObjectContext;
import fi.abo.kogni.soile2.qmarkup.CommandParser.VStringContext;
import fi.abo.kogni.soile2.qmarkup.CommandParser.ValueContext;


public class ObjectParser extends CommandBaseListener {
    
    public ObjectParser() {
        super();
        this.values = new ParseTreeProperty<>();
        this.objects = new ArrayDeque<>();
    }

    public void setValue(ParseTree node, Value value) {
        values.put(node, value);
    }

    public Value getValue(ParseTree node) {
        return values.get(node);
    }
    
    private void moveValue(ParseTree from, ParseTree to) {
        setValue(to, values.removeFrom(from));
    }

    @Override
    public void exitCommand(CommandContext ctx) {
        moveValue(ctx.object(), ctx);
    }

    @Override
    public void enterObject(ObjectContext ctx) {
        objects.push(new ObjectValue());
    }

    @Override
    public void exitObject(ObjectContext ctx) {
        setValue(ctx, objects.pop());
    }

    @Override
    public void exitPair(PairContext ctx) {
        ObjectValue obj = objects.pop();
        Value name = getValue(ctx.name());
        Value value = getValue(ctx.value());
        obj.setValue(name.toString(), value);
        objects.push(obj);
    }

    @Override
    public void exitQString(QStringContext ctx) {
        moveValue(ctx.string(), ctx);
    }

    @Override
    public void exitNQString(NQStringContext ctx) {
        ScalarValue v = new StringValue();
        v.setValue(ctx.getText());
        setValue(ctx, v);
    }

    @Override
    public void exitInteger(IntegerContext ctx) {
        ScalarValue v = new IntegerValue();
        v.setValue(ctx.getText());
        setValue(ctx, v);
    }

    @Override
    public void exitString(StringContext ctx) {
        String s = ctx.getText();
        if (s.charAt(0) == '"') {
            int len = s.length();
            s = s.substring(1, len - 1);
        }
        ScalarValue v = new StringValue();
        v.setValue(s);
        setValue(ctx, v);
    }

    @Override
    public void exitBool(BoolContext ctx) {
        ScalarValue v = new BooleanValue();
        v.setValue(ctx.getText());
        setValue(ctx, v);
    }

    @Override
    public void exitArray(ArrayContext ctx) {
        ArrayValue v = new ArrayValue();
        List<ValueContext> values = ctx.value();
        Iterator<ValueContext> it = values.iterator();
        while (it.hasNext()) {
            v.setValue("", getValue(it.next()));
        }
        setValue(ctx, v);
    }

    @Override
    public void exitVArray(VArrayContext ctx) {
        moveValue(ctx.array(), ctx);
    }

    @Override
    public void exitVString(VStringContext ctx) {
        moveValue(ctx.string(), ctx);
    }

    @Override
    public void exitVInteger(VIntegerContext ctx) {
        moveValue(ctx.integer(), ctx);
    }

    @Override
    public void exitVObject(VObjectContext ctx) {
        moveValue(ctx.object(), ctx);
    }

    @Override
    public void exitVBool(VBoolContext ctx) {
        moveValue(ctx.bool(), ctx);
    }

    private ParseTreeProperty<Value> values;
    private ArrayDeque<ObjectValue> objects;
}