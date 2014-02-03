package fi.abo.kogni.soile2.qmarkup.typespec;

import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicInteger;

import org.antlr.v4.runtime.ParserRuleContext;

import fi.abo.kogni.soile2.qmarkup.BooleanValue;
import fi.abo.kogni.soile2.qmarkup.IntegerValue;
import fi.abo.kogni.soile2.qmarkup.ScalarValue;
import fi.abo.kogni.soile2.qmarkup.StringValue;
import fi.abo.kogni.soile2.qmarkup.typespec.TypeSpecParser.Array_typeContext;
import fi.abo.kogni.soile2.qmarkup.typespec.TypeSpecParser.Boolean_typeContext;
import fi.abo.kogni.soile2.qmarkup.typespec.TypeSpecParser.DefContext;
import fi.abo.kogni.soile2.qmarkup.typespec.TypeSpecParser.FieldContext;
import fi.abo.kogni.soile2.qmarkup.typespec.TypeSpecParser.Integer_typeContext;
import fi.abo.kogni.soile2.qmarkup.typespec.TypeSpecParser.ObjectContext;
import fi.abo.kogni.soile2.qmarkup.typespec.TypeSpecParser.Object_typeContext;
import fi.abo.kogni.soile2.qmarkup.typespec.TypeSpecParser.Repeat_typeContext;
import fi.abo.kogni.soile2.qmarkup.typespec.TypeSpecParser.String_typeContext;
import fi.abo.kogni.soile2.qmarkup.typespec.TypeSpecParser.TypeContext;


public class Pass1 extends Pass {
    
    public Pass1(IdentityHashMap<ParserRuleContext, NodeData> nd) {
        super(nd);
        fields = new Stack<>();
        types = new Stack<>();
        primitiveSignatures = new HashMap<>();
        nameGen = new NameGenerator();
        AtomicInteger counter = new AtomicInteger(Integer.MIN_VALUE + 1);
        nameGen.setObjectCounter(counter);
        nameGen.setArrayCounter(counter);
        nameGen.setRepeatCounter(counter);
    }
    
    @Override
    public void enterField(FieldContext ctx) {
        fields.push(ctx);
        newNodeData(ctx);
    }

    @Override
    public void enterType(TypeContext ctx) {
        types.push(ctx);
        if (fields.isEmpty() == false) {
            getNodeData(ctx).setRelatedField(fields.pop());
        }
    }

    @Override
    public void exitType(TypeContext ctx) {
        types.pop();
    }

    @Override
    public void exitRepeat_type(Repeat_typeContext ctx) {
        TypeContext typeContext = types.peek();
        NodeData typeData = getNodeData(typeContext);
        typeData.setRepeatType(nameGen.repeatID());
        typeData.setTypeContext(ctx);
        typeData.setSignature(Signature.compute(ctx.getText()));
    }

    @Override
    public void exitArray_type(Array_typeContext ctx) {
        TypeContext type = types.peek();
        NodeData typeData = getNodeData(type);
        typeData.setArrayType(nameGen.arrayID());
        typeData.setTypeContext(ctx);
        typeData.setSignature(Signature.compute(ctx.getText()));
        boolean hasDefault = true;
        boolean onlyPrimitive = true;
        Iterator<TypeContext> it = ctx.type().iterator();
        while (it.hasNext()) {
            TypeContext def = it.next();
            NodeData nd = getNodeData(def);
            if (nd.hasDefaultValue() == false) {
                hasDefault = false;
            }
            if (nd.isPrimitive() == false) {
                onlyPrimitive = false;
            }
        }
        if (hasDefault) {
            typeData.setDefaultValue();
        }
        if (onlyPrimitive) {
            typeData.setPrimitiveFields();
        }
    }
    
    @Override
    public void exitObject_type(Object_typeContext ctx) {
        boolean hasDefault = true;
        boolean onlyPrimitive = true;
        ObjectContext object = ctx.object();
        TypeContext type = types.peek();
        NodeData typeData = getNodeData(type);
        typeData.setObjectType(nameGen.objectID());
        typeData.setTypeContext(ctx);
        typeData.setSignature(Signature.compute(ctx.object().getText()));
        List<DefContext> defs = object.def();
        Iterator<DefContext> it = defs.iterator();
        while (it.hasNext()) {
            DefContext def = it.next();
            NodeData nd = getNodeData(def.type());
            if (nd.hasDefaultValue() == false) {
                hasDefault = false;
            }
            if (nd.isPrimitive() == false) {
                onlyPrimitive = false;
            }
        }
        if (hasDefault) {
            typeData.setDefaultValue();
        }
        if (onlyPrimitive) {
            typeData.setPrimitiveFields();
        }
    }

    @Override
    public void exitBoolean_type(Boolean_typeContext ctx) {
        TypeContext type = types.peek();
        NodeData nd = getNodeData(type);
        if (ctx.BOOLEAN() != null) {
            ScalarValue v = new BooleanValue();
            v.setValue(ctx.BOOLEAN().getText());
            nd.setDefaultValue(v);
        }
        String name = ctx.TYPE_BOOLEAN().getText();
        nd.setSignature(primitiveSignature(name));
        nd.setBooleanType(name);
        nd.setTypeContext(ctx);
    }

    @Override
    public void exitInteger_type(Integer_typeContext ctx) {
        TypeContext type = types.peek();
        NodeData nd = getNodeData(type);
        if (ctx.INTEGER() != null) {
            ScalarValue v = new IntegerValue();
            v.setValue(ctx.INTEGER().getText());
            nd.setDefaultValue(v);
        }
        String name = ctx.TYPE_INTEGER().getText();
        nd.setSignature(primitiveSignature(name));
        nd.setIntegerType(name);
        nd.setTypeContext(ctx);
    }

    @Override
    public void exitString_type(String_typeContext ctx) {
        TypeContext type = types.peek();
        NodeData nd = getNodeData(type);
        if (ctx.STRING() != null) {
            ScalarValue v = new StringValue();
            v.setValue(ctx.STRING().getText());
            nd.setDefaultValue(v);
        }
        String name = ctx.TYPE_STRING().getText();
        nd.setSignature(primitiveSignature(name));
        nd.setStringType(name);
        nd.setTypeContext(ctx);
    }
    
    private String primitiveSignature(String type) {
        if (primitiveSignatures.containsKey(type) == false) {
            primitiveSignatures.put(type, Signature.compute(type));
        }
        return primitiveSignatures.get(type);
    }

    private Stack<FieldContext> fields;
    private Stack<TypeContext> types;
    private NameGenerator nameGen;
    private HashMap<String, String> primitiveSignatures;
} 