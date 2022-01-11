package fi.abo.kogni.soile2.qmarkup;

public class IntegerValue extends ScalarValue {

    public IntegerValue() {
        super();
        this.value = null;
    }

    public IntegerValue(Integer i) {
        this();
        setValue(i);
    }

    public IntegerValue(String s) {
        this();
        setValue(s);
    }

    @Override
    public void setValue(Object value) {
        if (value instanceof Integer) {
            this.value = (Integer) value;
        } else if (value instanceof String) {
            String s = (String) value;
            Integer i = Integer.parseInt(s);
            this.value = i;
        }
    }
    
    @Override
    public Object asJavaObject() {
        return this.value;
    }
    
    public Integer asInteger() {
        return this.value;
    }
    
    @Override
    public String toString() {
        return value.toString();
    }

    private Integer value;

}
