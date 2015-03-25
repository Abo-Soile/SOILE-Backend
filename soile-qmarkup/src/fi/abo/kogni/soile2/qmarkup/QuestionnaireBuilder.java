package fi.abo.kogni.soile2.qmarkup;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;

import fi.abo.kogni.soile2.qmarkup.typespec.MalformedCommandException;
import fi.abo.kogni.soile2.qmarkup.typespec.Validator;
import fi.abo.kogni.soile2.utils.generator.UniqueStringGenerator;
import org.stringtemplate.v4.STGroupFile;


public class QuestionnaireBuilder implements QuestionnaireProcessor {

    public QuestionnaireBuilder(String template) {
        super();
        this.pendingTags = new ArrayDeque<>();
        this.tsStack = new ArrayDeque<>();
        this.bsStack = new ArrayDeque<>();
    //    this.group = template.getTemplate("questionnaire_proto.stg");
    //    this.group = template.getTemplate("questionnaire_embedded.stg");
        this.group = new STGroupFile(template);
        this.questionnaire = questionnaireST();
        this.spacer = group.getInstanceOf("vspacer");
        this.body = new StringBuilder();
        this.validStmt = new StringBuilder();
        
        Tag tag = Tag.newTag("p");
        this.paragraphOpenTag = tag.toString();
        this.paragraphCloseTag = Tag.closingTag(tag);
        this.questionnaireId = "";
        this.encryptionKey = "";
        
        this.inParagraph = false;
        this.textAsArgument = false;
        this.expectPageTitle = false;
        this.addSpacer = false;
        AtomicInteger counter = UniqueStringGenerator.createCounter();
        this.idGen = new UniqueStringGenerator("id");
        this.idGen.setCounter(counter);
        this.nameGen = new UniqueStringGenerator("name");
        this.nameGen.setCounter(counter);
    }

    public void questionnaireId(String id) {
        this.questionnaireId = id;
    }
    
    public String questionnaireId() {
        return this.questionnaireId;
    }

    public void encryptionKey(String key) {
        this.encryptionKey = key;
    }
    
    public String encryptionKey() {
        return this.encryptionKey;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void processCommand(String command, ArrayList<String> args) throws MalformedCommandException {
        Tag tag = Tag.newTag(command);
        
        if (addSpacer) {
            addTag(spacer.render());
            addSpacer = false;
        }
        
        if (textAsArgument) {
            /*
             * We were probably expecting text, but 
             * never mind. Proceed as usual, but close the pending tag.
             */
            textAsArgument = false;
            clearPendingTag();
        }
 
        switch (command) {
        
        // Process those commands which take an object as argument.
        
        case "dropdownmenu":
            if (args.size() > 0) {
                //closeParagraph();
                Value value = Value.parse(args.get(0));
                Validator validator = Validator.validatorFor(command);
                validator.validate(value);
                ST tmpl = group.getInstanceOf(command);
                DropDownMenuWidgetData ddmwd = this.new DropDownMenuWidgetData();
                ValidateWidget vw = this.new ValidateWidget();
                String id = idGen.generate();
                ddmwd.setId(id);
                vw.setId(id);
                Boolean inline = ((BooleanValue) value.getValue("inline")).asBoolean();
                if(! inline) {
                    closeParagraph();
                }
                tmpl.add("id", id);
                tmpl.add("label", value.getValue("label").toString());
                String field = createColumnName(questionnaireId(),
                        value.getValue("dbcolumn").toString());
                ddmwd.setColumn(encrypt(field));
                Object o = value.getValue("options").asJavaObject();
                ArrayList<Value> options = (ArrayList<Value>) o;
                Iterator<Value> it = options.iterator();
                while (it.hasNext()) {
                    Value v = it.next();
                    tmpl.addAggr("options.{short, long}",
                                 v.getValue("dbvalue"), v.getValue("text"));
                }
                addTag(tmpl.render());
                validationCode(ddmwd);

                if(! inline) {
                    addSpacer = true;
                }
            }
            break;
        case "multiselect":
            if (args.size() > 0) {
                Value value = Value.parse(args.get(0));
                Validator validator = Validator.validatorFor(command);
                validator.validate(value);

                Boolean inline = ((BooleanValue) value.getValue("inline")).asBoolean();
                if(! inline) {
                    closeParagraph();
                }

                ST tmpl = group.getInstanceOf("select");
                String defaultValue = value.getValue("default_value").toString();
                ArrayList<Value> options = 
                        (ArrayList<Value>)  value.getValue("options").asJavaObject();
                int numColumns = options.size();
                int width = CONTENT_WIDTH_PX  / numColumns;
                width -= 5;
                MultiselectWidgetData mswd = this.new MultiselectWidgetData();
                mswd.setDefaultValue(defaultValue);
                String name = nameGen.generate();
                Iterator<Value> it = options.iterator();
                while (it.hasNext()) {
                    // Iterating over columns, basically.
                    
                    Value c = it.next();
                    ST columnTmpl = group.getInstanceOf("selection_column");
                    columnTmpl.add("width", width);
                    ArrayList<Value> column = (ArrayList<Value>) c.asJavaObject();
                    Iterator<Value> iter = column.iterator();
                    while (iter.hasNext()) {
                        // Fill one column.
                        
                        Value opt = iter.next();
                        ST optTmpl = group.getInstanceOf("checkbox");
                        String colname = createColumnName(questionnaireId(),
                                opt.getValue("dbcolumn").toString());
                        String id = idGen.generate();
                        optTmpl.add("id", id);
                        optTmpl.add("name", name);
                        String dbvalue = opt.getValue("dbvalue").toString();
                        optTmpl.add("value", dbvalue);
                        optTmpl.add("label", opt.getValue("text"));
                        Boolean checked = (Boolean) opt.getValue("checked").asJavaObject();
                        if (checked == false) {
                            checked = null;
                        }
                        optTmpl.add("checked", checked);
                        columnTmpl.addAggr("elems.{content}", optTmpl.render());
                        mswd.addId(id);
                        mswd.addColumn(encrypt(colname));
                        mswd.addValue(dbvalue);
                    }
                    tmpl.addAggr("columns.{content}", columnTmpl.render());
                }
                validationCode(mswd);
                addTag(tmpl.render());
                if(! inline) {
                    addSpacer = true;
                }
            }
            break;
        case "numberfield":
            if (args.size() > 0) {
                Value value = Value.parse(args.get(0));
                Validator validator = Validator.validatorFor(command);
                validator.validate(value);

                Boolean inline = ((BooleanValue) value.getValue("inline")).asBoolean();

                if(!inline) {
                    closeParagraph();
                }

                ST tmpl = group.getInstanceOf(command);
                NumberFieldWidgetData nfwd = this.new NumberFieldWidgetData();
                String id = idGen.generate();
                tmpl.add("id", id);
                tmpl.add("name", nameGen.generate());
                tmpl.add("label", value.getValue("label"));
                nfwd.setId(id);
                String field = createColumnName(questionnaireId(),
                        value.getValue("dbcolumn").toString());
                nfwd.setColumn(encrypt(field));
                Value min = value.getValue("minimum");
                Value max = value.getValue("maximum");
                Value incr = value.getValue("increment");
                int width = max.toString().trim().length() + 2;
                tmpl.add("minimum", min);
                tmpl.add("maximum", max);
                tmpl.add("increment", incr);
                tmpl.add("width", width);
                tmpl.add("value", value.getValue("value"));
                addTag(tmpl.render());
                validationCode(nfwd);
                if(! inline) {
                    addSpacer = true;
                }
            }
            break;
        case "singleselect":
            if (args.size() > 0) {
                Value value = Value.parse(args.get(0));
                Validator validator = Validator.validatorFor(command);
                validator.validate(value);
                ST tmpl = group.getInstanceOf("select");
                SingleselectWidgetData sswd = this.new SingleselectWidgetData();
                String colname = createColumnName(questionnaireId(),
                        value.getValue("dbcolumn").toString());
                sswd.setColumn(colname);
                String defaultValue = value.getValue("default_value").toString();
                Boolean inline = ((BooleanValue) value.getValue("inline")).asBoolean();

                if(!inline){
                    closeParagraph();
                }

                sswd.setDefaultValue(defaultValue);
                ArrayList<Value> options = 
                        (ArrayList<Value>)  value.getValue("options").asJavaObject();
                int numColumns = options.size();
                int width = CONTENT_WIDTH_PX  / numColumns;
                width -= 5;
                String name = nameGen.generate();
                Iterator<Value> optionsIt = options.iterator();
                while (optionsIt.hasNext()) {
                    // Iterating over columns, basically.
                    
                    Value c = optionsIt.next();
                    ST columnTmpl = group.getInstanceOf("selection_column");
                    columnTmpl.add("width", width);
                    ArrayList<Value> column = (ArrayList<Value>) c.asJavaObject();
                    Iterator<Value> iter = column.iterator();
                    while (iter.hasNext()) {
                        // Fill one column.
                        
                        Value opt = iter.next();
                        ST optTmpl = group.getInstanceOf("radiobutton");
                        String id = idGen.generate();
                        optTmpl.add("id", id);
                        optTmpl.add("name", name);
                        String dbvalue = opt.getValue("dbvalue").toString();
                        optTmpl.add("value", dbvalue);
                        optTmpl.add("label", opt.getValue("text"));
                        Boolean checked = (Boolean) opt.getValue("checked").asJavaObject();
                        if (checked == false) {
                            checked = null;
                        }
                        optTmpl.add("checked", checked);
                        columnTmpl.addAggr("elems.{content}", optTmpl.render());
                        sswd.addId(id);
                        sswd.addValue(dbvalue);
                    }
                    tmpl.addAggr("columns.{content}", columnTmpl.render());
                }
                validationCode(sswd);
                addTag(tmpl.render());
                if(! inline) {
                    addSpacer = true;
                }
            }
            break;
        case "slider":
            if (args.size() > 0) {
                closeParagraph();
                Value value = Value.parse(args.get(0));
                Validator validator = Validator.validatorFor(command);
                validator.validate(value);
                ST tmpl = group.getInstanceOf(command);
                SliderWidgetData swd = this.new SliderWidgetData();
                String id = idGen.generate();
                tmpl.add("id", id);
                swd.setId(id);
                Integer min = (Integer) value.getValue("minimum").asJavaObject();
                Integer max = (Integer) value.getValue("maximum").asJavaObject();
                Integer incr = (Integer) value.getValue("increment").asJavaObject();
                Integer select = (Integer) value.getValue("select").asJavaObject();
                tmpl.add("minimum", min);
                tmpl.add("maximum", max);
                tmpl.add("increment", incr);
                tmpl.add("select", select);
                String field = createColumnName(questionnaireId(),
                        value.getValue("dbcolumn").toString());
                swd.setColumn(encrypt(field));
                Object o = value.getValue("labels").asJavaObject();
                ArrayList<Value> labels = (ArrayList<Value>) o;
                int count = labels.size();
                Iterator<Value> it = labels.iterator();
                while (it.hasNext()) {
                    tmpl.addAggr("labels.{value}", it.next());
                }
                tmpl.add("count", count);
                addTag(tmpl.render());
                validationCode(swd);
                addSpacer = true;
            }
            break;
        case "textarea":
            if (args.size() > 0) {
                closeParagraph();
                Value value = Value.parse(args.get(0));
                Validator validator = Validator.validatorFor(command);
                validator.validate(value);
                ST tmpl = group.getInstanceOf(command);
                String id = idGen.generate();
                TextareaWidgetData tawd = this.new TextareaWidgetData();
                String field = createColumnName(questionnaireId(),
                        value.getValue("dbcolumn").toString());
                Value maxlen = value.getValue("length");
                Boolean required = false;
                required = ((BooleanValue) value.getValue("optional")).asBoolean();
                tmpl.add("id", id);
                tmpl.add("rows", value.getValue("rows"));
                tmpl.add("columns", value.getValue("columns"));
                tmpl.add("text", value.getValue("text"));
                tmpl.add("label", value.getValue("label"));
                tmpl.add("required", required);
                tawd.setId(id);
                tawd.setColumn(encrypt(field));
                tawd.setMaxLength(maxlen.toString());
                addTag(tmpl.render());
                Boolean optional = ((BooleanValue) value.getValue("optional")).asBoolean();
                if (optional == false) {
                    NonemptyTextWidget ntw = this.new NonemptyTextWidget();
                    ntw.setId(id);
                    validationCode(ntw);
                }
                validationCode(tawd);
                addSpacer = true;
            }
            break;
        case "textbox":
            if (args.size() > 0) {
                
                /*
                 * Text boxes are "in-line elements." That is, they can appear only 
                 * inside a paragraph.
                 */
                if (inParagraph == false) {
                    addTag(paragraphOpenTag);
                    inParagraph = true;
                }
                
                Value value = Value.parse(args.get(0));
                Validator validator = Validator.validatorFor(command);
                validator.validate(value);
                ST tmpl = group.getInstanceOf(command);
                String id = idGen.generate();
                TextboxWidgetData tbwd = this.new TextboxWidgetData();
                Boolean linebreak = ((BooleanValue) value.getValue("linebreak")).asBoolean();
                String field = createColumnName(questionnaireId(),
                        value.getValue("dbcolumn").toString());
                String label = value.getValue("label").toString();
                Boolean required = false;
                System.out.println(required);
                required = !((BooleanValue) value.getValue("optional")).asBoolean();

                System.out.println("after: " +required);
                String separator = "&nbsp;";
                if (linebreak) {
                    separator = Tag.newEmptyTag(Tag.LINEBREAK).toString();
                }
                if (label.isEmpty() == true) {
                    label = null;
                }
                tmpl.add("id", id);
                tmpl.add("separator", separator);
                tmpl.add("label", label);
                Value maxlen = value.getValue("length");
                tmpl.add("length", maxlen);
                tmpl.add("text", value.getValue("text"));
                tmpl.add("required", required);
                tbwd.setId(id);
                tbwd.setColumn(encrypt(field));
                tbwd.setMaxLength(maxlen.toString());
                addTag(tmpl.render());
                BooleanValue optional = (BooleanValue) value.getValue("optional");
                if (optional.asBoolean() == false) {
                    NonemptyTextWidget ntw = this.new NonemptyTextWidget();
                    ntw.setId(id);
                    validationCode(ntw);
                }
                validationCode(tbwd);
            }
            break;
            
        // Process other commands (with simple arguments).
            
        case "bs":          // "Block Style"
            if (args.size() > 0) {
                closeParagraph();
                workOnStack(args, bsStack, tag);
            }
            break;
        case "lb":          // "Line Break"
            tag = Tag.newEmptyTag(command);
            addTag(tag);
            break;
        case "link":
            if (args.size() > 0) {
                tag.attribute("href", args.get(0));
                addTag(tag);
                textAsArgument = true;
                setPendingTag(tag);
            }
            break;
        case "p":           // "Paragraph"
            closeParagraph();
            inParagraph = true;
            addTag(tag);
            break;
        case "pagetitle":
            textAsArgument = true;
            expectPageTitle = true;
            break;
        case "pb":          // "Paragraph Break"
            closeParagraph();
            break;
        case "subtitle":
            addTag(tag);
            textAsArgument = true;
            setPendingTag(tag);
            break;
        case "title":
            addTag(tag);
            textAsArgument = true;
            setPendingTag(tag);
            break;
        case "ts":          // "Text Style"
            if (inParagraph && args.size() > 0) {
                workOnStack(args, tsStack, tag);
            }
            break;
        case "if":
            break;
        case "elseif":
            break;
        case "else":
            break;
        case "endif":
            break;
        default:
            String msg = String.format("Unknown command: '%s'.", command);
            throw new MalformedCommandException(msg);
        }
    }

    @Override
    public void processText(String text) {
        if (expectPageTitle) {
            questionnaire.add("title", text);
            expectPageTitle = false;
            textAsArgument = false;
            return;
        }
        body.append(text);
        if (textAsArgument) {
            clearPendingTag();
            textAsArgument = false;
        }
        body.append(' ');
    }
    
    @Override
    public void finish() {
        closeParagraph();
        emptyBsStack();
    }

    @Override
    public String output() {
        questionnaire.add("body", body.toString());
        questionnaire.add("stmts", validStmt.toString());
        return questionnaire.render();
    }
    
    public void reset() {
        pendingTags.clear();
        tsStack.clear();
        bsStack.clear();
        if (body.length() > 0) {
            body.delete(0, body.length());
        }
        if (validStmt.length() > 0) {
            validStmt.delete(0, validStmt.length());
        }
//        questionnaire.remove("body");
//        questionnaire.remove("stmts");
//        questionnaire.remove("title");
        questionnaire = questionnaireST();
        inParagraph = false;
        textAsArgument = false;
        addSpacer = false;
        questionnaireId = "";
    }
    
    private void setPendingTag(Tag tag) {
        // Only one tag may be pending at any given time.
        
        if (pendingTags.isEmpty() == false) {
            pendingTags.clear();
        }
        pendingTags.push(Tag.closingTag(tag));
    }
    
    private void clearPendingTag() {
        if (pendingTags.isEmpty() == false) {
            addTag(pendingTags.pop());
            
            // Only one tag is allowed to be pending at a time.
            pendingTags.clear();
        }
    }
    
    private void emptyTsStack() {
        while (tsStack.isEmpty() == false) {
            body.append(Tag.closingTag(tsStack.pop()));
        }
    }
    
    private void emptyBsStack() {
        while (bsStack.isEmpty() == false) {
            body.append(Tag.closingTag(bsStack.pop()));
        }
    }
    
    private void closeParagraph() {
        if (inParagraph) {
            clearPendingTag();
            emptyTsStack();
            addTag(paragraphCloseTag);
            inParagraph = false;
        }
    }

    private void addTag(Tag tag) {
        addTag(tag.toString());
    }

    private void addTag(String tag) {
        body.append('\n');
        body.append(tag);
        body.append('\n');
    }
    
    private void workOnStack(ArrayList<String> args, ArrayDeque<Tag> stack, Tag tag) {
        String arg = decodeStackArg(args.get(0));
        
        switch (arg) {
        case "push":
            if (args.size() > 1) {
                StringBuilder sb = new StringBuilder();
                for (int i = 1; i < args.size(); ++i) {
                    String a = args.get(i);
                    if (TextStyle.defined(a)) {
                        sb.append(TextStyle.get(a));
                        sb.append(' ');
                    }
                }
                tag.attribute("style", sb.toString());
            }
            stack.push(tag);
            addTag(tag);
            break;

        case "pop":
            if (stack.isEmpty() == false) {
                addTag(Tag.closingTag(stack.pop()));
            }
            break;

        case "empty":
            while (stack.isEmpty() == false) {
                addTag(Tag.closingTag(stack.pop()));
            }
            break;
        default:
            String msg = String.format("Unknown stack argument: '%s'", arg);
            throw new MalformedCommandException(msg);
        }
    }
    
    private String decodeStackArg(String arg) {
        if (arg.length() == 1) {
            if (arg.charAt(0) == '+') {
                return "push";
            }
            if (arg.charAt(0) == '-') {
                return "pop";
            }
            if (arg.charAt(0) == '!') {
                return "empty";
            }
        }
        return arg;
    }
    
    private String createColumnName(String qid, String col) {
        int len = qid.length() + col.length() + 1;
        StringBuilder sb = new StringBuilder(len);
        sb.append(qid);
        sb.append(':');
        sb.append(col);
        return sb.toString();
    }
    
    private void validationCode(Qdata data) {
        validationCode(data.render());
    }

    private void validationCode(String code) {
        validStmt.append(code);
        validStmt.append('\n');
    }

    private String encrypt(String field) {
        // TODO Really encrypt the field.
        //return Blowfish.encrypt(field, encryptionKey());
        return field;
    }

    private ST questionnaireST() {
        return group.getInstanceOf("questionnaire_html");
    }
    
    private ArrayDeque<String> pendingTags;
    private ArrayDeque<Tag> tsStack;
    private ArrayDeque<Tag> bsStack;
    
    // The body of the questionnaire
    private StringBuilder body;
    
    // Validation statements.
    private StringBuilder validStmt;
    
    private STGroup group;
    private ST spacer;
    private ST questionnaire;
    private String paragraphOpenTag;
    private String paragraphCloseTag;
    private String questionnaireId;
    private String encryptionKey;
    private boolean expectPageTitle;
    private boolean inParagraph;
    private boolean textAsArgument;
    private boolean addSpacer;
    private UniqueStringGenerator idGen;
    private UniqueStringGenerator nameGen;
    
    private abstract class Qdata {
        public Qdata(String n) {
            this.tmpl = group.getInstanceOf(n);
        }
        
        protected ST getTmpl() {
            return tmpl;
        }

        public String render() {
            return getTmpl().render();
        }
        
        private ST tmpl;
    }

    private class ValidateWidget extends Qdata {
        public ValidateWidget() {
            super("qdata_validate_widget");
        }

        public ValidateWidget setId(String id) {
            getTmpl().add("id", id);
            return this;
        }
    }

    private class NonemptyTextWidget extends Qdata {
        public NonemptyTextWidget() {
            super("qdata_nonempty_text_widget");
        }

        public NonemptyTextWidget setId(String id) {
            getTmpl().add("id", id);
            return this;
        }
    }
    
    private class DropDownMenuWidgetData extends Qdata {
        public DropDownMenuWidgetData() {
            super("qdata_widgetdata_dropdownmenu");
        }

        public void setId(String id) {
            getTmpl().add("id", id);
        }
        
        public void setColumn(String col) {
            getTmpl().add("column", col);
        }
        
    }

    private class SingleselectWidgetData extends Qdata {
        public SingleselectWidgetData() {
            super("qdata_widgetdata_singleselect");
        }

        public void addId(String id) {
            getTmpl().addAggr("ids.{text}", id);
        }
        
        public void setColumn(String col) {
            getTmpl().add("columnName", col);
        }
        
        public void addValue(String col) {
            getTmpl().addAggr("values.{text}", col);
        }
        
        public void setDefaultValue(String dw) {
            getTmpl().add("defaultValue", dw);
        }
        
    }

    private class MultiselectWidgetData extends Qdata {
        public MultiselectWidgetData() {
            super("qdata_widgetdata_multiselect");
        }

        public void addId(String id) {
            getTmpl().addAggr("ids.{text}", id);
        }
        
        public void addColumn(String col) {
            getTmpl().addAggr("columns.{text}", col);
        }
        
        public void addValue(String col) {
            getTmpl().addAggr("values.{text}", col);
        }
        
        public void setDefaultValue(String dw) {
            getTmpl().add("defaultValue", dw);
        }
        
    }

    private class NumberFieldWidgetData extends Qdata {
        public NumberFieldWidgetData() {
            super("qdata_widgetdata_numberfield");
        }

        public void setId(String id) {
            getTmpl().add("id", id);
        }
        
        public void setColumn(String col) {
            getTmpl().add("columnName", col);
        }
        
    }

    private class SliderWidgetData extends Qdata {
        public SliderWidgetData() {
            super("qdata_widgetdata_slider");
        }

        public void setId(String id) {
            getTmpl().add("id", id);
        }
        
        public void setColumn(String col) {
            getTmpl().add("columnName", col);
        }
        
    }

    private class TextareaWidgetData extends Qdata {
        public TextareaWidgetData() {
            super("qdata_widgetdata_textarea");
        }
        
        public void setId(String id) {
            getTmpl().add("id", id);
        }
        
        public void setColumn(String col) {
            getTmpl().add("columnName", col);
        }
        
        public void setMaxLength(String len) {
            getTmpl().add("maxlength", len);
        }
    }
    
    private class TextboxWidgetData extends Qdata {
        public TextboxWidgetData() {
            super("qdata_widgetdata_textbox");
        }
        
        public void setId(String id) {
            getTmpl().add("id", id);
        }
        
        public void setColumn(String col) {
            getTmpl().add("columnName", col);
        }
        
        public void setMaxLength(String len) {
            getTmpl().add("maxlength", len);
        }
    }
    
    private static final int CONTENT_WIDTH_PX = 600;

}
