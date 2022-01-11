package fi.abo.kogni.soile2.qmarkup;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

public class ParseTreePropertyHelper {

    public ParseTreePropertyHelper(ParseTreeProperty<Value> values) {
        super();
        this.values = values;
    }

    public void set(ParseTree node, Value value) {
        values.put(node, value);
    }

    public Value get(ParseTree node) {
        return values.get(node);
    }
    
    public void move(ParseTree from, ParseTree to) {
        set(to, values.removeFrom(from));
    }

    public void put(ParseTree node, Value value) {
        set(node, value);
    }

    private ParseTreeProperty<Value> values;

}
