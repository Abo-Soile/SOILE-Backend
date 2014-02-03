package fi.abo.kogni.soile2.qmarkup.typespec;

import java.util.BitSet;

import org.antlr.v4.runtime.ParserRuleContext;

import fi.abo.kogni.soile2.qmarkup.Value;
import fi.abo.kogni.soile2.qmarkup.typespec.TypeSpecParser.FieldContext;



public class NodeData {
    
    public NodeData() {
        super();
        flags = new BitSet(NUMBER_OF_FLAGS);
        value = null;
        typeContext = null;
        valueClassName = "";
        id = 0;
        relatedField = null;
        signature = null;
    }
    
    public void setRelatedField(FieldContext relatedField) {
        this.relatedField = relatedField;
    }

    public FieldContext getRelatedField() {
        return relatedField;
    }

    public ParserRuleContext getTypeContext() {
        return typeContext;
    }

    public void setTypeContext(ParserRuleContext typeContext) {
        this.typeContext = typeContext;
    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public Value getDefaultValue() {
        return this.value;
    }

    public void setDefaultValue(Value v) {
        setDefaultValue();
        value = v;
    }

    public void setDefaultValue() {
        setFlag(FLAG_DEFAULT_VALUE);
    }

    public boolean hasDefaultValue() {
        return getFlag(FLAG_DEFAULT_VALUE);
    }
    
    public void setStringType(String name) {
        setFlag(FLAG_STRING);
        this.valueClassName = name;
        this.validationMethod = NameGenerator.validateXXX(this.valueClassName);
        this.defaultValueMethod = NameGenerator.defaultXXXValue(this.valueClassName);
    }
    
    public void setStringType() {
        setStringType("String");
    }
    
    public void setIntegerType(String name) {
        setFlag(FLAG_INTEGER);
        this.valueClassName = name;
        this.validationMethod = NameGenerator.validateXXX(this.valueClassName);
        this.defaultValueMethod = NameGenerator.defaultXXXValue(this.valueClassName);
    }
    
    public void setIntegerType() {
        setIntegerType("Integer");
    }
    
    public void setBooleanType(String name) {
        setFlag(FLAG_BOOLEAN);
        this.valueClassName = name;
        this.validationMethod = NameGenerator.validateXXX(this.valueClassName);
        this.defaultValueMethod = NameGenerator.defaultXXXValue(this.valueClassName);
    }
    
    public void setBooleanType() {
        setBooleanType("Boolean");
     }
    
    public void setArrayType(int id) {
        setFlag(FLAG_ARRAY);
        setCompound();
        setID(id);
        this.valueClassName = "Array";
        this.validationMethod = NameGenerator.validateArray(getID());
        this.defaultValueMethod = NameGenerator.defaultArrayValue(getID());
    }

    public void setObjectType(int id) {
        setFlag(FLAG_OBJECT);
        setCompound();
        setID(id);
        this.valueClassName = "Object";
        this.validationMethod = NameGenerator.validateObject(getID());
        this.defaultValueMethod = NameGenerator.defaultObjectValue(getID());
    }
    
    public void setRepeatType(int id) {
        setFlag(FLAG_REPEAT);
        setCompound();
        setID(id);
        this.valueClassName = "Repeat";
        this.validationMethod = NameGenerator.validateRepeat(getID());
        this.defaultValueMethod = "";
    }

    public String getValidationMethod() {
        return validationMethod;
    }

    public String getDefaultValueMethod() {
        return defaultValueMethod;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    public void setValidationMethod(String validationMethod) {
        this.validationMethod = validationMethod;
    }

    public void setDefaultValueMethod(String defaultValueMethod) {
        this.defaultValueMethod = defaultValueMethod;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFlags(BitSet flags) {
        this.flags = flags;
    }

    public String getValueClassName() {
        return valueClassName;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public boolean isStringType() {
        return getFlag(FLAG_STRING);
    }

    public boolean isIntegerType() {
        return getFlag(FLAG_INTEGER);
    }

    public boolean isBooleanType() {
        return getFlag(FLAG_BOOLEAN);
    }

    public boolean isArrayType() {
        return getFlag(FLAG_ARRAY);
    }

    public boolean isObjectType() {
        return getFlag(FLAG_OBJECT);
    }
    
    public boolean isRepeatType() {
        return getFlag(FLAG_REPEAT);
    }
    
    public boolean isCompound() {
        return getFlag(FLAG_COMPOUND);
    }
    
    public boolean isPrimitive() {
        return (isCompound() ? false : true);
    }
    
    public void setPrimitiveFields() {
        setFlag(FLAG_PRIMITIVE_FIELDS);
    }

    public boolean onlyPrimitiveFields() {
        return getFlag(FLAG_PRIMITIVE_FIELDS);
    }

    private void setCompound() {
        setFlag(FLAG_COMPOUND);
    }
    
    private void setFlag(int flag) {
        flags.set(flag);
    }
    
    private boolean getFlag(int flag) {
        return flags.get(flag);
    }
    
    private Value value;
    
    private ParserRuleContext typeContext;

    private String valueClassName;
    
    private String validationMethod;
    
    private String defaultValueMethod;
    
    // For instances of TypeContext class.
    private FieldContext relatedField;

    private int id;
    
    // For type signature.
    private String signature;

    private BitSet flags;
    
    private static final int NUMBER_OF_FLAGS = 32;
    
    private static final int FLAG_ARRAY;
    private static final int FLAG_BOOLEAN;
    private static final int FLAG_COMPOUND;
    private static final int FLAG_DEFAULT_VALUE;
    private static final int FLAG_INTEGER;
    private static final int FLAG_OBJECT;
    private static final int FLAG_PRIMITIVE_FIELDS;
    private static final int FLAG_REPEAT;
    private static final int FLAG_STRING;
    
    static {
        int i = 0;
        FLAG_ARRAY =                  i;          i += 1;
        FLAG_BOOLEAN =                i;          i += 1;
        FLAG_COMPOUND =               i;          i += 1;
        FLAG_DEFAULT_VALUE =          i;          i += 1;
        FLAG_INTEGER =                i;          i += 1;
        FLAG_OBJECT =                 i;          i += 1;
        FLAG_PRIMITIVE_FIELDS =       i;          i += 1;
        FLAG_REPEAT =                 i;          i += 1;
        FLAG_STRING =                 i;          i += 1;
    }
}