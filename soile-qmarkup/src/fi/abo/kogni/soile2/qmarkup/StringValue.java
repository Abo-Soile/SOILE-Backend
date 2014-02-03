package fi.abo.kogni.soile2.qmarkup;

public class StringValue extends ScalarValue {

    public StringValue() {
        super();
    }

    public StringValue(String value) {
        this();
        this.value = value;
    }

    @Override
    public void setValue(Object value) {
        if (value instanceof String) {
            this.value = (String) value;
        } else {
            this.value = value.toString();
        }
    }
    
    @Override
    public Object asJavaObject() {
        return this.value;
    }
    
    public String asString() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.value;
    }
    
    private String value;

}
