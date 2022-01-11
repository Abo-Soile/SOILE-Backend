package fi.abo.kogni.soile2.qmarkup;

public abstract class ScalarValue extends Value {

    @Override
    public void setValue(String var, Object value) {
        setValue(value);
    }

    public abstract void setValue(Object value);

}
