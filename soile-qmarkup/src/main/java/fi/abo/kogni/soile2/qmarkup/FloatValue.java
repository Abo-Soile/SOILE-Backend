package fi.abo.kogni.soile2.qmarkup;

/**
 * Created by danno on 3/18/14.
 */
public class FloatValue extends ScalarValue {

    public FloatValue() {
        super();
        this.value = null;
    }

    public FloatValue(Float f) {
        this();
        setValue(f);
    }

    public FloatValue(String s) {
        this();
        setValue(s);
    }

    @Override
    public void setValue(Object value) {
        if (value instanceof Float) {
            this.value = (Float) value;
        }else if (value instanceof String) {
            String s = (String) value;
            Float f = Float.parseFloat(s);
            this.value = f;
        }
    }

    @Override
    public Object asJavaObject() {
        return this.value;
    }

    public Float asFloat() {
        return this.value;
    }

    @Override
    public String toString() {
        return value.toString();
    }


    private Float value;
}
