package fi.abo.kogni.soile2.qmarkup.typespec;

/******************************************************************
 * This file has been DYNAMICALLY generated! Do not edit by hand! *
 * Instead, make changes to the "./spec/t.typespec" file, and     *
 * run GenerateValidator.java. Finally, copy the generated code   *
 * to this file.                                                  *
 ******************************************************************/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import fi.abo.kogni.soile2.qmarkup.ArrayValue;
import fi.abo.kogni.soile2.qmarkup.BooleanValue;
import fi.abo.kogni.soile2.qmarkup.IntegerValue;
import fi.abo.kogni.soile2.qmarkup.ObjectValue;
import fi.abo.kogni.soile2.qmarkup.StringValue;
import fi.abo.kogni.soile2.qmarkup.Value;

@SuppressWarnings("unused")
public abstract class Validator {

    public static Validator validatorFor(String name) {
        return validators.get(name);
    }

    public abstract String name();
    public abstract void validate(Value value) throws MalformedCommandException;

    public static class dropdownmenu extends Validator {
        @Override
        public void validate(Value value) throws MalformedCommandException {
            validateCommand1(value);
        }

        @Override
        public String name() {
            return "dropdownmenu";
        }
    }

    public static class slider extends Validator {
        @Override
        public void validate(Value value) throws MalformedCommandException {
            validateCommand2(value);
        }

        @Override
        public String name() {
            return "slider";
        }
    }

    public static class singleselect extends Validator {
        @Override
        public void validate(Value value) throws MalformedCommandException {
            validateCommand3(value);
        }

        @Override
        public String name() {
            return "singleselect";
        }
    }

    public static class multiselect extends Validator {
        @Override
        public void validate(Value value) throws MalformedCommandException {
            validateCommand4(value);
        }

        @Override
        public String name() {
            return "multiselect";
        }
    }

    public static class numberfield extends Validator {
        @Override
        public void validate(Value value) throws MalformedCommandException {
            validateCommand5(value);
        }

        @Override
        public String name() {
            return "numberfield";
        }
    }

    public static class textarea extends Validator {
        @Override
        public void validate(Value value) throws MalformedCommandException {
            validateCommand6(value);
        }

        @Override
        public String name() {
            return "textarea";
        }
    }

    public static class textbox extends Validator {
        @Override
        public void validate(Value value) throws MalformedCommandException {
            validateCommand7(value);
        }

        @Override
        public String name() {
            return "textbox";
        }
    }




    @SuppressWarnings("unchecked")
    protected void validateObject_2147483646(Value value)
            throws MalformedCommandException {

        validateObject(value);
        Map<String, Value> object = (Map<String, Value>) value.asJavaObject();

        if (object.containsKey("dbvalue")) {
            validateString(object.get("dbvalue"));
        }
        else {
            String msg = String.format("Missing object element: '%s'.", "dbvalue");
            throw new MalformedCommandException(msg);
        }
        if (object.containsKey("text")) {
            validateString(object.get("text"));
        }
        else {
            String msg = String.format("Missing object element: '%s'.", "text");
            throw new MalformedCommandException(msg);
        }
    }



    @SuppressWarnings("unchecked")
    protected void validateRepeat_2147483645(Value value)
            throws MalformedCommandException {
        validateArray(value);
        ArrayList<Value> array = (ArrayList<Value>) value.asJavaObject();
        Iterator<Value> it = array.iterator();
        while (it.hasNext()) {
            validateObject_2147483646(it.next());
        }
    }


    @SuppressWarnings("unchecked")
    protected void validateCommand1(Value value)
            throws MalformedCommandException {
        validateObject(value);
        Map<String, Value> object = (Map<String, Value>) value.asJavaObject();
        if (object.containsKey("dbcolumn")) {
            validateString(object.get("dbcolumn"));
        }
        else {
            String msg = String.format("Missing object element: '%s'.", "dbcolumn");
            throw new MalformedCommandException(msg);
        }
        if (object.containsKey("numeric")) {
            validateBoolean(object.get("numeric"));
        }
        else {
            object.put("numeric", defaultBooleanValue( true ));
        }
        if (object.containsKey("label")) {
            validateString(object.get("label"));
        }
        else {
            String msg = String.format("Missing object element: '%s'.", "label");
            throw new MalformedCommandException(msg);
        }
        if (object.containsKey("inline")) {
            validateBoolean(object.get("inline"));
        }
        else {
            object.put("inline", defaultBooleanValue( false ));
        }
        if (object.containsKey("optional")) {
            validateBoolean(object.get("optional"));
        }
        else {
            object.put("optional", defaultBooleanValue( false ));
        }
        if (object.containsKey("options")) {
            validateRepeat_2147483645(object.get("options"));
        }
        else {
            String msg = String.format("Missing object element: '%s'.", "options");
            throw new MalformedCommandException(msg);
        }
    }



    @SuppressWarnings("unchecked")
    protected void validateRepeat_2147483644(Value value)
            throws MalformedCommandException {
        validateArray(value);
        ArrayList<Value> array = (ArrayList<Value>) value.asJavaObject();
        Iterator<Value> it = array.iterator();
        while (it.hasNext()) {
            validateInteger(it.next());
        }
    }


    @SuppressWarnings("unchecked")
    protected void validateCommand2(Value value)
            throws MalformedCommandException {
        validateObject(value);
        Map<String, Value> object = (Map<String, Value>) value.asJavaObject();
        if (object.containsKey("dbcolumn")) {
            validateString(object.get("dbcolumn"));
        }
        else {
            String msg = String.format("Missing object element: '%s'.", "dbcolumn");
            throw new MalformedCommandException(msg);
        }
        if (object.containsKey("numeric")) {
            validateBoolean(object.get("numeric"));
        }
        else {
            object.put("numeric", defaultBooleanValue( true ));
        }
        if (object.containsKey("labels")) {
            validateRepeat_2147483644(object.get("labels"));
        }
        else {
            String msg = String.format("Missing object element: '%s'.", "labels");
            throw new MalformedCommandException(msg);
        }
        if (object.containsKey("minimum")) {
            validateInteger(object.get("minimum"));
        }
        else {
            String msg = String.format("Missing object element: '%s'.", "minimum");
            throw new MalformedCommandException(msg);
        }
        if (object.containsKey("maximum")) {
            validateInteger(object.get("maximum"));
        }
        else {
            String msg = String.format("Missing object element: '%s'.", "maximum");
            throw new MalformedCommandException(msg);
        }
        if (object.containsKey("increment")) {
            validateInteger(object.get("increment"));
        }
        else {
            String msg = String.format("Missing object element: '%s'.", "increment");
            throw new MalformedCommandException(msg);
        }
        if (object.containsKey("select")) {
            validateInteger(object.get("select"));
        }
        else {
            String msg = String.format("Missing object element: '%s'.", "select");
            throw new MalformedCommandException(msg);
        }
    }



    @SuppressWarnings("unchecked")
    protected void validateObject_2147483643(Value value)
            throws MalformedCommandException {

        validateObject(value);
        Map<String, Value> object = (Map<String, Value>) value.asJavaObject();

        if (object.containsKey("dbvalue")) {
            validateString(object.get("dbvalue"));
        }
        else {
            String msg = String.format("Missing object element: '%s'.", "dbvalue");
            throw new MalformedCommandException(msg);
        }
        if (object.containsKey("text")) {
            validateString(object.get("text"));
        }
        else {
            String msg = String.format("Missing object element: '%s'.", "text");
            throw new MalformedCommandException(msg);
        }
        if (object.containsKey("checked")) {
            validateBoolean(object.get("checked"));
        }
        else {
            object.put("checked", defaultBooleanValue( false ));
        }
    }



    @SuppressWarnings("unchecked")
    protected void validateRepeat_2147483642(Value value)
            throws MalformedCommandException {
        validateArray(value);
        ArrayList<Value> array = (ArrayList<Value>) value.asJavaObject();
        Iterator<Value> it = array.iterator();
        while (it.hasNext()) {
            validateObject_2147483643(it.next());
        }
    }


    @SuppressWarnings("unchecked")
    protected void validateRepeat_2147483641(Value value)
            throws MalformedCommandException {
        validateArray(value);
        ArrayList<Value> array = (ArrayList<Value>) value.asJavaObject();
        Iterator<Value> it = array.iterator();
        while (it.hasNext()) {
            validateRepeat_2147483642(it.next());
        }
    }


    @SuppressWarnings("unchecked")
    protected void validateCommand3(Value value)
            throws MalformedCommandException {
        validateObject(value);
        Map<String, Value> object = (Map<String, Value>) value.asJavaObject();
        if (object.containsKey("numeric")) {
            validateBoolean(object.get("numeric"));
        }
        else {
            object.put("numeric", defaultBooleanValue( true ));
        }
        if (object.containsKey("default_value")) {
            validateString(object.get("default_value"));
        }
        else {
            String msg = String.format("Missing object element: '%s'.", "default_value");
            throw new MalformedCommandException(msg);
        }
        if (object.containsKey("colalign")) {
            validateBoolean(object.get("colalign"));
        }
        else {
            object.put("colalign", defaultBooleanValue( true ));
        }
        if (object.containsKey("inline")) {
            validateBoolean(object.get("inline"));
        }
        else {
            object.put("inline", defaultBooleanValue( false ));
        }
        if (object.containsKey("dbcolumn")) {
            validateString(object.get("dbcolumn"));
        }
        else {
            String msg = String.format("Missing object element: '%s'.", "dbcolumn");
            throw new MalformedCommandException(msg);
        }
        if (object.containsKey("options")) {
            validateRepeat_2147483641(object.get("options"));
        }
        else {
            String msg = String.format("Missing object element: '%s'.", "options");
            throw new MalformedCommandException(msg);
        }
    }



    @SuppressWarnings("unchecked")
    protected void validateObject_2147483640(Value value)
            throws MalformedCommandException {

        validateObject(value);
        Map<String, Value> object = (Map<String, Value>) value.asJavaObject();

        if (object.containsKey("dbcolumn")) {
            validateString(object.get("dbcolumn"));
        }
        else {
            String msg = String.format("Missing object element: '%s'.", "dbcolumn");
            throw new MalformedCommandException(msg);
        }
        if (object.containsKey("dbvalue")) {
            validateString(object.get("dbvalue"));
        }
        else {
            String msg = String.format("Missing object element: '%s'.", "dbvalue");
            throw new MalformedCommandException(msg);
        }
        if (object.containsKey("text")) {
            validateString(object.get("text"));
        }
        else {
            String msg = String.format("Missing object element: '%s'.", "text");
            throw new MalformedCommandException(msg);
        }
        if (object.containsKey("checked")) {
            validateBoolean(object.get("checked"));
        }
        else {
            object.put("checked", defaultBooleanValue( false ));
        }
    }



    @SuppressWarnings("unchecked")
    protected void validateRepeat_2147483639(Value value)
            throws MalformedCommandException {
        validateArray(value);
        ArrayList<Value> array = (ArrayList<Value>) value.asJavaObject();
        Iterator<Value> it = array.iterator();
        while (it.hasNext()) {
            validateObject_2147483640(it.next());
        }
    }


    @SuppressWarnings("unchecked")
    protected void validateRepeat_2147483638(Value value)
            throws MalformedCommandException {
        validateArray(value);
        ArrayList<Value> array = (ArrayList<Value>) value.asJavaObject();
        Iterator<Value> it = array.iterator();
        while (it.hasNext()) {
            validateRepeat_2147483639(it.next());
        }
    }


    @SuppressWarnings("unchecked")
    protected void validateCommand4(Value value)
            throws MalformedCommandException {
        validateObject(value);
        Map<String, Value> object = (Map<String, Value>) value.asJavaObject();
        if (object.containsKey("numeric")) {
            validateBoolean(object.get("numeric"));
        }
        else {
            object.put("numeric", defaultBooleanValue( true ));
        }
        if (object.containsKey("default_value")) {
            validateString(object.get("default_value"));
        }
        else {
            String msg = String.format("Missing object element: '%s'.", "default_value");
            throw new MalformedCommandException(msg);
        }
        if (object.containsKey("colalign")) {
            validateBoolean(object.get("colalign"));
        }
        else {
            object.put("colalign", defaultBooleanValue( true ));
        }
        if (object.containsKey("inline")) {
            validateBoolean(object.get("inline"));
        }
        else {
            object.put("inline", defaultBooleanValue( false ));
        }
        if (object.containsKey("options")) {
            validateRepeat_2147483638(object.get("options"));
        }
        else {
            String msg = String.format("Missing object element: '%s'.", "options");
            throw new MalformedCommandException(msg);
        }
    }



    @SuppressWarnings("unchecked")
    protected void validateCommand5(Value value)
            throws MalformedCommandException {
        validateObject(value);
        Map<String, Value> object = (Map<String, Value>) value.asJavaObject();
        if (object.containsKey("dbcolumn")) {
            validateString(object.get("dbcolumn"));
        }
        else {
            String msg = String.format("Missing object element: '%s'.", "dbcolumn");
            throw new MalformedCommandException(msg);
        }
        if (object.containsKey("numeric")) {
            validateBoolean(object.get("numeric"));
        }
        else {
            object.put("numeric", defaultBooleanValue( true ));
        }
        if (object.containsKey("label")) {
            validateString(object.get("label"));
        }
        else {
            String msg = String.format("Missing object element: '%s'.", "label");
            throw new MalformedCommandException(msg);
        }
        if (object.containsKey("value")) {
            validateInteger(object.get("value"));
        }
        else {
            String msg = String.format("Missing object element: '%s'.", "value");
            throw new MalformedCommandException(msg);
        }
        if (object.containsKey("minimum")) {
            validateInteger(object.get("minimum"));
        }
        else {
            String msg = String.format("Missing object element: '%s'.", "minimum");
            throw new MalformedCommandException(msg);
        }
        if (object.containsKey("maximum")) {
            validateInteger(object.get("maximum"));
        }
        else {
            String msg = String.format("Missing object element: '%s'.", "maximum");
            throw new MalformedCommandException(msg);
        }
        if (object.containsKey("increment")) {
            validateFloat(object.get("increment"));
        }
        else {
            String msg = String.format("Missing object element: '%s'.", "increment");
            throw new MalformedCommandException(msg);
        }
        if (object.containsKey("inline")) {
            validateBoolean(object.get("inline"));
        }
        else {
            object.put("inline", defaultBooleanValue( true ));
        }
    }



    @SuppressWarnings("unchecked")
    protected void validateCommand6(Value value)
            throws MalformedCommandException {
        validateObject(value);
        Map<String, Value> object = (Map<String, Value>) value.asJavaObject();
        if (object.containsKey("dbcolumn")) {
            validateString(object.get("dbcolumn"));
        }
        else {
            String msg = String.format("Missing object element: '%s'.", "dbcolumn");
            throw new MalformedCommandException(msg);
        }
        if (object.containsKey("rows")) {
            validateInteger(object.get("rows"));
        }
        else {
            object.put("rows", defaultIntegerValue( 4 ));
        }
        if (object.containsKey("columns")) {
            validateInteger(object.get("columns"));
        }
        else {
            object.put("columns", defaultIntegerValue( 80 ));
        }
        if (object.containsKey("label")) {
            validateString(object.get("label"));
        }
        else {
            String msg = String.format("Missing object element: '%s'.", "label");
            throw new MalformedCommandException(msg);
        }
        if (object.containsKey("length")) {
            validateInteger(object.get("length"));
        }
        else {
            String msg = String.format("Missing object element: '%s'.", "length");
            throw new MalformedCommandException(msg);
        }
        if (object.containsKey("optional")) {
            validateBoolean(object.get("optional"));
        }
        else {
            object.put("optional", defaultBooleanValue( true ));
        }
        if (object.containsKey("text")) {
            validateString(object.get("text"));
        }
        else {
            object.put("text", defaultStringValue( "" ));
        }
    }



    @SuppressWarnings("unchecked")
    protected void validateCommand7(Value value)
            throws MalformedCommandException {
        validateObject(value);
        Map<String, Value> object = (Map<String, Value>) value.asJavaObject();
        if (object.containsKey("dbcolumn")) {
            validateString(object.get("dbcolumn"));
        }
        else {
            String msg = String.format("Missing object element: '%s'.", "dbcolumn");
            throw new MalformedCommandException(msg);
        }
        if (object.containsKey("label")) {
            validateString(object.get("label"));
        }
        else {
            object.put("label", defaultStringValue( "" ));
        }
        if (object.containsKey("linebreak")) {
            validateBoolean(object.get("linebreak"));
        }
        else {
            object.put("linebreak", defaultBooleanValue( false ));
        }
        if (object.containsKey("length")) {
            validateInteger(object.get("length"));
        }
        else {
            String msg = String.format("Missing object element: '%s'.", "length");
            throw new MalformedCommandException(msg);
        }
        if (object.containsKey("optional")) {
            validateBoolean(object.get("optional"));
        }
        else {
            object.put("optional", defaultBooleanValue( true ));
        }
        if (object.containsKey("text")) {
            validateString(object.get("text"));
        }
        else {
            object.put("text", defaultStringValue( "" ));
        }
    }


    protected Value defaultStringValue(String s) {
        return new StringValue(s);
    }

    protected Value defaultIntegerValue(Integer i) {
        return new IntegerValue(i);
    }

    protected Value defaultIntegerValue(String s) {
        return new IntegerValue(s);
    }

    protected Value defaultBooleanValue(Boolean b) {
        return new BooleanValue(b);
    }

    protected Value defaultBooleanValue(String s) {
        return new BooleanValue(s);
    }

    protected void validateBoolean(Value value)
            throws MalformedCommandException {
        Object obj = value.asJavaObject();
        if (!(obj instanceof Boolean)) {
            String msg = String.format("Value '%s' is not Boolean.", value);
            throw new MalformedCommandException(msg);
        }
    }

    protected void validateInteger(Value value)
            throws MalformedCommandException {
        Object obj = value.asJavaObject();
        if (!(obj instanceof Integer)) {
            String msg = String.format("Value '%s' is not Integer.", value);
            throw new MalformedCommandException(msg);
        }
    }

    protected void validateFloat(Value value)
            throws MalformedCommandException {
        Object obj = value.asJavaObject();
        Float f = Float.parseFloat(obj.toString());
        if (!(f instanceof Float)) {
            String msg = String.format("Value '%s' is not Float. ", value);
            throw new MalformedCommandException(msg);
        }
    }

    protected void validateString(Value value) throws MalformedCommandException {
        Object obj = value.asJavaObject();
        if (!(obj instanceof String)) {
            String msg = String.format("Value '%s' is not String.", value);
            throw new MalformedCommandException(msg);
        }
    }

    protected void validateObject(Value value) throws MalformedCommandException {
        Object obj = value.asJavaObject();
        if (!(obj instanceof Map<?, ?>)) {
            String msg = String.format("Value '%s' is not Object.", value);
            throw new MalformedCommandException(msg);
        }
    }

    protected void validateArray(Value value) throws MalformedCommandException {
        Object obj = value.asJavaObject();
        if (!(obj instanceof ArrayList<?>)) {
            String msg = String.format("Value '%s' is not Array.", value);
            throw new MalformedCommandException(msg);
        }
    }

    public static final HashMap<String, Validator> validators;

    static {
        validators = new HashMap<>();
        validators.put("dropdownmenu", new dropdownmenu());
        validators.put("slider", new slider());
        validators.put("singleselect", new singleselect());
        validators.put("multiselect", new multiselect());
        validators.put("numberfield", new numberfield());
        validators.put("textarea", new textarea());
        validators.put("textbox", new textbox());
    }

}