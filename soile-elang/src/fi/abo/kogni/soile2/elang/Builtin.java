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
        builtinFunctions.add("modulo");
        builtinFunctions.add("round");
        builtinFunctions.add("nkeypresses");
        builtinFunctions.add("nmouseclicks");
        builtinFunctions.add("onmouseclick");
        builtinFunctions.add("not");
        builtinFunctions.add("or");
        builtinFunctions.add("plus");
        builtinFunctions.add("position");
        builtinFunctions.add("recordts");
        builtinFunctions.add("starttimer");
        builtinFunctions.add("elapsedtime");
        builtinFunctions.add("seconds");
        builtinFunctions.add("setstimuli");
        builtinFunctions.add("addstimuli");
        builtinFunctions.add("show");
        builtinFunctions.add("showmsg");
        builtinFunctions.add("msgbox");
        builtinFunctions.add("stimulus");
        builtinFunctions.add("tag");
        builtinFunctions.add("timeout");
        builtinFunctions.add("wait");
        builtinFunctions.add("resume");

        builtinFunctions.add("append");

        //time
        builtinFunctions.add("randominteger");
        builtinFunctions.add("randomnumber");
        builtinFunctions.add("seedrandom");

        //Data logging
        builtinFunctions.add("storesingle");
        builtinFunctions.add("storerow");
        builtinFunctions.add("newrow");
        builtinFunctions.add("average");
        builtinFunctions.add("median");
        builtinFunctions.add("standarddeviation");
        builtinFunctions.add("outliers");
        builtinFunctions.add("count");

        //Score
        builtinFunctions.add("savescore");

        //Persistant data
        builtinFunctions.add("savevariable");
        builtinFunctions.add("loadvariable");

        //Keys
        builtinFunctions.add("onkeypress");
        builtinFunctions.add("resumeonkey");
        builtinFunctions.add("onanykey");
        builtinFunctions.add("getlastkey");

        builtinFunctions.add("elementatindex");

        builtinFunctions.add("rectangle");

        builtinFunctions.add("shufflestimuli");
        builtinFunctions.add("pickstimulisubset");
        builtinFunctions.add("emptystimuli");
    }
}
