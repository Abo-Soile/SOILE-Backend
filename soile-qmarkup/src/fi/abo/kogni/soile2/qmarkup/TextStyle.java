package fi.abo.kogni.soile2.qmarkup;

import java.util.HashMap;

public class TextStyle {
    
    public static boolean defined(String name) {
        return styles.containsKey(name);
    }

    public static String get(String name) {
        String key = name.toLowerCase().trim();
        if (styles.containsKey(key)) {
            return styles.get(key);
        }
        return "EMPTY";
    }
    
    public static String indent() {
        return get("indent");
    }

    public static String aqua() {
        return get("aqua");
    }

    public static String black() {
        return get("black");
    }

    public static String blue() {
        return get("blue");
    }

    public static String fuchsia() {
        return get("fuchsia");
    }

    public static String gray() {
        return get("gray");
    }

    public static String green() {
        return get("green");
    }

    public static String lime() {
        return get("lime");
    }

    public static String maroon() {
        return get("maroon");
    }

    public static String navy() {
        return get("navy");
    }

    public static String olive() {
        return get("olive");
    }

    public static String orange() {
        return get("orange");
    }

    public static String purple() {
        return get("purple");
    }

    public static String red() {
        return get("red");
    }

    public static String silver() {
        return get("silver");
    }

    public static String teal() {
        return get("teal");
    }

    public static String monospace() {
        return get("monospace");
    }

    public static String sansserif() {
        return get("sansserif");
    }

    public static String serif() {
        return get("serif");
    }

    public static String small() {
        return get("small");
    }

    public static String large() {
        return get("large");
    }

    public static String italic() {
        return get("italic");
    }

    public static String bold() {
        return get("bold");
    }

    public static String linethrough() {
        return get("linethrough");
    }

    public static String overline() {
        return get("overline");
    }

    public static String underline() {
        return get("underline");
    }

    public static String capitalize() {
        return get("capitalize");
    }

    public static String lowercase() {
        return get("lowercase");
    }

    public static String uppercase() {
        return get("uppercase");
    }

    private static HashMap<String, String> styles;

    static {
        styles = new HashMap<>();
        styles.put("aqua", "color: aqua;");
        styles.put("black", "color: black;");
        styles.put("blue", "color: blue;");
        styles.put("bold", "font-weight:bold;");
        styles.put("capitalize", "text-transform: capitalize;");
        styles.put("fuchsia", "color: fuchsia;");
        styles.put("gray", "color: gray;");
        styles.put("green", "color: green;");
        styles.put("indent", "margin-left: 1em;");
        styles.put("italic", "font-style:italic;");
        styles.put("large", "font-size: x-large;");
        styles.put("lime", "color: lime;");
        styles.put("linethrough", "text-decoration: line-through;");
        styles.put("lowercase", "text-transform: lowercase;");
        styles.put("maroon", "color: maroon;");
        styles.put("monospace", "font-family: monospace;");
        styles.put("navy", "color: navy;");
        styles.put("olive", "color: olive;");
        styles.put("orange", "color: orange;");
        styles.put("overline", "text-decoration: overline;");
        styles.put("purple", "color: purple;");
        styles.put("red", "color: red;");
        styles.put("sansserif", "font-family: sans-serif;");
        styles.put("serif", "font-family: serif;");
        styles.put("silver", "color: silver;");
        styles.put("small", "font-size: smaller;");
        styles.put("teal", "color: teal;");
        styles.put("underline", "text-decoration: underline;");
        styles.put("uppercase", "text-transform: uppercase;");
    }

}
