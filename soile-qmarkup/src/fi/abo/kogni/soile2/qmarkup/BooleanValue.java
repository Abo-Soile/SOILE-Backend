package fi.abo.kogni.soile2.qmarkup;

public class BooleanValue extends ScalarValue {

    public BooleanValue() {
        super();
        this.value = null;
    }

    public BooleanValue(Boolean value) {
        this();
        this.value = value;
    }

    public BooleanValue(String s) {
        this();
        setValue(s);
    }

    @Override
    public void setValue(Object value) {
        if (value instanceof Boolean) {
            this.value = (Boolean) value;
        } else if (value instanceof String) {
            String s = (String) value;
            if (TRUE.compareToIgnoreCase(s) == 0) {
                this.value = Boolean.TRUE;
            } else {
                this.value = Boolean.FALSE;
            }
        }
    }
    
    @Override
    public Object asJavaObject() {
        return this.value;
    }
    
    public Boolean asBoolean() {
        return this.value;
    }
    
    @Override
    public String toString() {
        return value.toString();
    }


    private Boolean value;

    public static final String TRUE = "true";
    public static final String FALSE = "false";
}
