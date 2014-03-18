package fi.abo.kogni.soile2.qmarkup.typespec;

import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.antlr.v4.runtime.ParserRuleContext;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;

import fi.abo.kogni.soile2.qmarkup.Template;
import fi.abo.kogni.soile2.qmarkup.Value;
import fi.abo.kogni.soile2.qmarkup.typespec.TypeSpecParser.Array_typeContext;
import fi.abo.kogni.soile2.qmarkup.typespec.TypeSpecParser.BodyContext;
import fi.abo.kogni.soile2.qmarkup.typespec.TypeSpecParser.CommandContext;
import fi.abo.kogni.soile2.qmarkup.typespec.TypeSpecParser.DefContext;
import fi.abo.kogni.soile2.qmarkup.typespec.TypeSpecParser.ObjectContext;
import fi.abo.kogni.soile2.qmarkup.typespec.TypeSpecParser.Object_typeContext;
import fi.abo.kogni.soile2.qmarkup.typespec.TypeSpecParser.Repeat_typeContext;
import fi.abo.kogni.soile2.qmarkup.typespec.TypeSpecParser.TypeContext;



public class Pass2 extends Pass {

    public Pass2(IdentityHashMap<ParserRuleContext, NodeData> nd) {
        super(nd);
        Template template = new Template("./soile-qmarkup/spec");
        tgroup = template.getTemplate("validator.stg");
        validator = tgroup.getInstanceOf("body");
        generatedTypes = new HashSet<>();
        methodSignatures = new HashMap<>();
        commandSignatures = new HashMap<>();
        nameGen = new NameGenerator();
        nameGen.setCommandCounter(new AtomicInteger(0));
    }
    
    public String output() {
        return validator.render();
    }

    @Override
    public void exitCommand(CommandContext ctx) {
        String text = ctx.body().getText();
        String signature = Signature.compute(text);
        String method = commandMethod(signature, ctx);
        ST tmpl = tgroup.getInstanceOf("command_class");
        String commandName = ctx.commandname().getText();
        tmpl.add("class_name", commandName);
        tmpl.add("method_name", method);
        addInnerClass(tmpl);
        addValidator(commandName);
    }

    @Override
    public void exitType(TypeContext ctx) {
        NodeData nd = getNodeData(ctx);
        if (nd.isObjectType()) {
            if (nd.getTypeContext() instanceof Object_typeContext) {
                doObject(nd, (Object_typeContext) nd.getTypeContext());
            }
        } else if (nd.isArrayType()) {
            if (nd.getTypeContext() instanceof Array_typeContext) {
                doArray(nd, (Array_typeContext) nd.getTypeContext());
            }
        } else if (nd.isRepeatType()) {
            if (nd.getTypeContext() instanceof Repeat_typeContext) {
                doRepeat(nd, (Repeat_typeContext) nd.getTypeContext());
            }
        }
    }

    private void doRepeat(NodeData nd, Repeat_typeContext context) {
        String signature = nd.getSignature();
        if (generatedTypes.contains(signature) == true) {
            return;
        }

        TypeContext type = context.type();
        NodeData typeData = getNodeData(type);
        ST tmpl = tgroup.getInstanceOf("validate_repeat");
        tmpl.add("method_name", getValidationMethod(nd));
        tmpl.add("validation_method", getValidationMethod(typeData));
        addMethod(tmpl);
        generatedTypes.add(signature);
    }


    private void doArray(NodeData nd, Array_typeContext ctx) {
        String signature = nd.getSignature();
        if (generatedTypes.contains(signature) == true) {
            return;
        }

        int size = ctx.type().size();
        int index = 0;
        final boolean hasDefault = nd.hasDefaultValue();
        ST vaTmpl = tgroup.getInstanceOf("validate_array");
        vaTmpl.add("method_name", getValidationMethod(nd));
        vaTmpl.add("size", size);
        ST dvTmpl = tgroup.getInstanceOf("default_array_value");
        dvTmpl.add("method_name", getDefaultValueMethod(nd));
        Iterator<TypeContext> it = ctx.type().iterator();
        while (it.hasNext()) {
            TypeContext typeContext = it.next();
            NodeData typeData = getNodeData(typeContext);
            
            vaTmpl.addAggr("statements.{method, index}", 
                           getValidationMethod(typeData), 
                           index);
            
            if (hasDefault) {
                Value value = typeData.getDefaultValue();
                dvTmpl.addAggr("statements.{index, name, value}", 
                               index, 
                               getDefaultValueMethod(typeData), 
                               value);
            }
            ++index;
        }
        addMethod(vaTmpl);
        if (hasDefault) {
            addMethod(dvTmpl);
        }
        generatedTypes.add(signature);
    }


    private void doObject(NodeData nd, Object_typeContext ctx) {
        ObjectContext object = ctx.object();
        String signature = nd.getSignature();
        if (generatedTypes.contains(signature) == true) {
            return;
        }
        
        final boolean hasDefault = nd.hasDefaultValue();
        Methods m = methodSignatures(signature, nd);
        ST voTmpl = tgroup.getInstanceOf("validate_object");
        voTmpl.add("method_name", m.getValidator());
        ST dvTmpl = tgroup.getInstanceOf("default_object_value");
        dvTmpl.add("method_name", m.getDefaultValue());
        doDefs(voTmpl, dvTmpl, hasDefault, object.def());
        addMethod(voTmpl);
        if (hasDefault) {
            addMethod(dvTmpl);
        }
        generatedTypes.add(signature);
        
   }
    
    private void doDefs(ST voTmpl, 
                        ST dvTmpl, 
                        boolean hasDefault,
                        List<DefContext> defs) {
        Iterator<DefContext> it = defs.iterator();
        while (it.hasNext()) {
            DefContext def = it.next();
            String field = def.field().getText();
            TypeContext typeCtx = def.type();
            NodeData typeData = getNodeData(typeCtx);
            String key = field;
            String validationMethod = getValidationMethod(typeData);
            String defaultValueMethod = null;
            Value value = typeData.getDefaultValue();
            if (typeData.hasDefaultValue()) {
                defaultValueMethod = getDefaultValueMethod(typeData);
            }
            if (hasDefault) {
                dvTmpl.addAggr("statements.{key, method}", 
                               key, 
                               defaultValueMethod);
            }
            voTmpl.addAggr("statements.{key, validator_method, value_method, value}",
                           key, 
                           validationMethod, 
                           defaultValueMethod, 
                           value);
        }
    }


    private void addInnerClass(ST st) {
        validator.addAggr("innerclasses.{body}", st.render());
    }
    
    private String commandMethod(String signature, CommandContext ctx) {
        if (commandSignatures.containsKey(signature) == false) {
            final int id = nameGen.commandID();
            String method = NameGenerator.validateCommand(id);
            commandSignatures.put(signature, method);
            ST tmpl = tgroup.getInstanceOf("validate_command");
            tmpl.add("method_name", method);
            BodyContext body = ctx.body();
            doDefs(tmpl, null, false, body.def());
            addMethod(tmpl);
        }
        return commandSignatures.get(signature);
    }

    private String getDefaultValueMethod(String signature, NodeData typeData) {
        return methodSignatures(signature, typeData).getDefaultValue();
    }

    private String getDefaultValueMethod(NodeData typeData) {
        String signature = typeData.getSignature();
        return getDefaultValueMethod(signature, typeData);
    }

    private String getValidationMethod(String signature, NodeData typeData) {
        return methodSignatures(signature, typeData).getValidator();
    }

    private String getValidationMethod(NodeData typeData) {
        String signature = typeData.getSignature();
        return getValidationMethod(signature, typeData);
    }

    private Methods methodSignatures(String signature, 
                                     NodeData typeData) {
        Methods m = methodSignatures.get(signature);
        
        if (m == null) {
            m = new Methods();
            m.setDefaultValue(typeData.getDefaultValueMethod());
            m.setValidator(typeData.getValidationMethod());
            methodSignatures.put(signature, m);
        } else {
            typeData.setDefaultValueMethod(m.getDefaultValue());
            typeData.setValidationMethod(m.getValidator());
        }
        return m;
    }

    private void addMethod(ST st) {
        validator.addAggr("methods.{method}", st.render());
    }

    private void addValidator(String commandName) {
        validator.addAggr("validators.{name}", commandName);
    }

    private STGroup tgroup;
    private ST validator;
    
    private HashSet<String> generatedTypes;
    private HashMap<String, Methods> methodSignatures;
    private HashMap<String, String> commandSignatures;
    private NameGenerator nameGen;
    
    private class Methods {
        
        public String getValidator() {
            return validator;
        }
        public void setValidator(String validator) {
            this.validator = validator;
        }
        public String getDefaultValue() {
            return defaultValue;
        }
        public void setDefaultValue(String defaultValue) {
            this.defaultValue = defaultValue;
        }
        
        public String validator;
        public String defaultValue;
    }

}