package fi.abo.kogni.soile2.qmarkup;

import java.util.HashMap;

public class Tag {
    
    private Tag(String name) {
        super();
        this.attributes = new StringBuilder();
        this.htmlTag = name2html(name);
    }
    
    public static Tag newTag(String name) {
        return newTag(name, false);
    }
    
    public static Tag newEmptyTag(String name) {
        return newTag(name, true);
    }
    
    private static Tag newTag(String name, boolean empty) {
        Tag tag = new Tag(name);
        tag.isEmpty = empty;
        return tag;
    }
    
    public static String closingTag(Tag tag) {
        return String.format("</%s>", tag.htmlTag);
    }
    
    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder();
        buf.append('<');
        buf.append(htmlTag);
        if (attributes.length() > 0) {
            buf.append(attributes.toString());
        }
        if (isEmpty) {
            buf.append(' ');
            buf.append('/');
        }
        buf.append('>');
        return buf.toString();
    }
    
    public void attribute(String name, String value) {
        attributes.append(' ');
        attributes.append(name);
        attributes.append('=');
        attributes.append('"');
        attributes.append(value);
        attributes.append('"');
    }
    
    public static String name2html(String name) {
        if (map.containsKey(name)) {
            return map.get(name);
        }
        return "";
    }
    
    public static final String PARAGRAPH = "p";
    public static final String LINEBREAK = "lb";
    
    private boolean isEmpty;
    private String htmlTag;
    private StringBuilder attributes;
    
    private static HashMap<String, String> map;
    
    static {
        map = new HashMap<>();
        map.put("bs", "div");
        map.put(LINEBREAK, "br");
        map.put("link", "a");
        map.put(PARAGRAPH, "p");
        map.put("pagetitle", "h1");
        map.put("subtitle", "h3");
        map.put("title", "h2");
        map.put("ts", "span");
    }
}
