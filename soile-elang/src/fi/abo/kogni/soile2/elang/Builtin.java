package fi.abo.kogni.soile2.elang;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;

/*

Afaik this is used to check if something is a builtin function
so this should be changed when a function is added/removed
from the soile VM.
 */

public class Builtin {
    public static boolean isBuiltinFunction(String name) {
        return builtinFunctions.contains(name);
    }
    
    public static void addBuiltins(SymbolTable symtab, STGroup tmpl) {
        Iterator<String> it1 = builtinFunctions.iterator();
        ST st = tmpl.getInstanceOf("ref2bin");
        while (it1.hasNext()) {
            String name = it1.next();
            st.add("name", name);
            symtab.addbuiltin(symtab.definefn(name), st.render());
            st.remove("name");
        }
    }
    
    private static Set<String> builtinFunctions;
    
    static {
        builtinFunctions = new HashSet<>();
        
        builtinFunctions.add("and");
        builtinFunctions.add("copydata");
        builtinFunctions.add("copystimulus");
        builtinFunctions.add("divide");
        builtinFunctions.add("eq");
        builtinFunctions.add("equals");
        builtinFunctions.add("greaterthan");
        builtinFunctions.add("gt");
        builtinFunctions.add("helptext");
        builtinFunctions.add("hide");
        builtinFunctions.add("hidemsg");
        builtinFunctions.add("imagefile");
        builtinFunctions.add("kbdactive");
        builtinFunctions.add("kbdconfig");
        builtinFunctions.add("kbdkey");
        builtinFunctions.add("length");
        builtinFunctions.add("lessthan");
        builtinFunctions.add("lt");
        builtinFunctions.add("minus");
        builtinFunctions.add("minutes");
        builtinFunctions.add("multiply");
        builtinFunctions.add("nkeypresses");
        builtinFunctions.add("nmouseclicks");
        builtinFunctions.add("onmouseclick");
        builtinFunctions.add("not");
        builtinFunctions.add("or");
        builtinFunctions.add("plus");
        builtinFunctions.add("position");
        builtinFunctions.add("recordts");
        builtinFunctions.add("seconds");
        builtinFunctions.add("setstimuli");
        builtinFunctions.add("show");
        builtinFunctions.add("showmsg");
        builtinFunctions.add("stimulus");
        builtinFunctions.add("tag");
        builtinFunctions.add("timeout");
        builtinFunctions.add("wait");
        builtinFunctions.add("resume");

        builtinFunctions.add("randominteger");
        builtinFunctions.add("randomnumber");
        builtinFunctions.add("seedrandom");
    }
}
