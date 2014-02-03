package elang;

import org.stringtemplate.v4.ST;

public class Test0002 {
    public static void main(String[] args) {
        ST tmpl = new ST("__gvars['$name$']", '$', '$');
        tmpl.remove("name");
        tmpl.add("name", "x");
        System.out.println(tmpl.render());
        tmpl.remove("name");
        tmpl.add("name", "y");
        System.out.println(tmpl.render());
    }
}
