package fi.abo.kogni.soile2.qmarkup;

import java.util.ArrayList;

public class ArrayValue extends CompoundValue {

    public ArrayValue(ArrayList<Value> values) {
        super();
        this.values = values;
    }

    public ArrayValue() {
        this(new ArrayList<Value>());
    }

    @Override
    public Value getValue(Object index) {
        int i = -1;
        if (index instanceof Integer) {
            i = (Integer) index;
        } else if (index instanceof String) {
            String s = (String) index;
            i = Integer.parseInt(s, 10);
        }
        if (i < 0) {
            return null;
        }
        if (i < values.size()) {
            return values.get(i);
        }
        return null;
    }

    @Override
    public void setValue(String var, Object value) {
        if (value instanceof Value) {
            Value v = (Value) value;
            values.add(v);
        }
    }

    @Override
    public Object asJavaObject() {
        return this.values;
    }

    @Override
    public String toString() {
        return values.toString();
    }

    private ArrayList<Value> values;

}
