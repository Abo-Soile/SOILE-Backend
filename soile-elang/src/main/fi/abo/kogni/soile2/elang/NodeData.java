package fi.abo.kogni.soile2.elang;

import java.util.ArrayList;


public class NodeData {

    public NodeData() {
        super();
        this.jsref = "";
        this.jsstmt = "";
        this.varType = VarType.NA;
        this.paramList = "";
        this.params = null;
    }

    /**
     * JavaScript reference to an expression.
     * <p><ul>
     * <li>[1 2 3 'string'] -&gt; [1, 2, 3, 'string']
     * <li>'func(p1 p2 p3)' -&gt; 'func(p1, p2, p3)'.
     * <li>global variable <i>x</i> -&gt; '__gvars["x"]'.
     * <li>global constant <i>x</i> -&gt; '__vals["x"]'.
     */
    public String jsref;
    
    /**
     * Formatted JavaScript statement.
     */
    public String jsstmt;
    
    /**
     * The type of user-defined variable.
     */
    public VarType varType;
    
    /**
     * For a function definition, this is the string
     * representation of the parameter list.
     */
    public String paramList;
    
    /**
     * For function call parameters, this contains
     * an array of parameters. The reason we have 
     * this is that for some built-in functions we
     * add extra parameters. Every element of this 
     * array is a "jsref."
     */
    public ArrayList<FunctionCallParam> params;
    
    /**
     * A user-defined variable can be either a global variable (gvar),
     * a (global) constant (val), a (global) function (fn), a 
     * "function-private" variable (fvar), or a regular variable (var)
     * local to a function. 'NA' denotes "not applicable."
     */
    public enum VarType {
        GVAR, VAL, FN, FVAR, VAR, NA
    }
}
