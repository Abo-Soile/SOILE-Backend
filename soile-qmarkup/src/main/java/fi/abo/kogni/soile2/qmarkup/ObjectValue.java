package fi.abo.kogni.soile2.qmarkup;

import java.util.HashMap;
import java.util.Map;

public class ObjectValue extends CompoundValue {

    public ObjectValue() {
        super();
        this.values = new HashMap<>();
    }

    public ObjectValue(Map<String, Value> values) {
        this();
        this.values = values;
    }

    @Override
    public Value getValue(Object index) {
        if (index instanceof String || index instanceof StringValue) {
            String key = index.toString();
            if (values.containsKey(key)) {
                return values.get(key);
            }
        }
        return null;
    }

    @Override
    public void setValue(String index, Object value) {
        if (value instanceof Value) {
            values.put(index, (Value) value);
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

    private Map<String, Value> values;
}
