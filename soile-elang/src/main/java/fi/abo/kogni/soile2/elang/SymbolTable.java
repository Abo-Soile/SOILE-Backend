package fi.abo.kogni.soile2.elang;

import java.util.IdentityHashMap;

import org.antlr.v4.runtime.ParserRuleContext;

public class SymbolTable {
    
    public SymbolTable() {
        scopes = new IdentityHashMap<>();
        scopeChain = new SymbolScope[3];
        scopeChain[BSCOPE] = new SymbolScope();
        scopeChain[GSCOPE] = new SymbolScope(scopeChain[BSCOPE]);
        currentScope = scopeChain[GSCOPE];
        depth = GSCOPE;
    }
    
    public void newLocalScope(ParserRuleContext ctx) {
        SymbolScope scope = new SymbolScope(scopeChain[GSCOPE]);
        scopes.put(ctx, scope);
    }
    
    public void intoLocalScope(ParserRuleContext ctx) {
        scopeChain[LSCOPE] = scopes.get(ctx);
        currentScope = scopeChain[LSCOPE];
        depth = LSCOPE;
    }
    
    public void intoGlobalScope() {
        scopeChain[LSCOPE] = null;
        currentScope = scopeChain[GSCOPE];
        depth = GSCOPE;
    }
    
    public boolean isbuiltin(String name) {
        return scopeChain[BSCOPE].has(name);
    }
    
    public boolean isglobal(String name) {
        return (scopeChain[GSCOPE].has(name)
                &&
                ! scopeChain[BSCOPE].has(name));
    }
    
    public boolean isgvar(String name) {
        Symbol s = scopeChain[GSCOPE].resolve(name);
        if (s != null) {
            return s.varType == SymbolType.GVAR;
        }
        return false;
    }
    
    public boolean isval(String name) {
        Symbol s = scopeChain[GSCOPE].resolve(name);
        if (s != null) {
            return s.varType == SymbolType.VAL;
        }
        return false;
    }
    
    public boolean isfunction(String name) {
        Symbol s = scopeChain[GSCOPE].resolve(name);
        if (s != null) {
            return s.varType == SymbolType.FN;
        }
        return false;
    }
    
    public boolean isfvar(String name) {
        Symbol s = currentScope.resolve(name);
        if (s != null) {
            return s.varType == SymbolType.FVAR;
        }
        return false;
    }
    
    public boolean isvar(String name) {
        Symbol s = currentScope.resolve(name);
        if (s != null) {
            return s.varType == SymbolType.VAR;
        }
        return false;
    }
    
    public void addbuiltin(Symbol s, String ref) {
        addbuiltin(s);
        s.jsref = ref;
    }
    
    public void addbuiltin(Symbol s) {
        if (! scopeChain[BSCOPE].has(s)) {
            scopeChain[BSCOPE].define(s);
        }
        s.varType = SymbolType.BUILTIN;
    }
    
    public Symbol definegvar(String name) {
        Symbol s = gvar(name);
        scopeChain[GSCOPE].define(s);
        return s;
    }
    
    public Symbol defineval(String name) {
        Symbol s = null;
        if (inglobal()) {
            s = val(name);
            scopeChain[GSCOPE].define(s);
        }
        return s;
    }
    
    public Symbol definefn(String name) {
        Symbol s = null;
        if (inglobal()) {
            s = function(name);
            scopeChain[GSCOPE].define(s);
        }
        return s;
    }
    
    public Symbol definefvar(String name) {
        if (inlocal()) {
            Symbol s = fvar(name);
            scopeChain[LSCOPE].define(s);
            return s;
        }
        return null;
    }
    
    public Symbol definevar(String name) {
        if (inlocal()) {
            Symbol s = var(name);
            scopeChain[LSCOPE].define(s);
            return s;
        }
        return null;
    }
    
    public Symbol defineparam(String name) {
        if (inlocal()) {
            Symbol s = param(name);
            scopeChain[LSCOPE].define(s);
            return s;
        }
        return null;
    }
    
    public void jsref(String name, String ref) {
        jsref(currentScope.resolve(name), ref);
    }
    
    public void jsref(Symbol s, String ref) {
        s.jsref = ref;
    }
    
    public String jsref(String name) {
        Symbol s = currentScope.resolve(name);

        if(s == null) {
            return "???";
        } else {
            return s.jsref;
        }
        //return (s == null? "???" : s.jsref);
    }
    
    public boolean defined(String name) {
        return currentScope.has(name);
    }
    
    public Symbol lookup(String name) {
        return currentScope.resolve(name);
    }

    /**
     * A variable can be either a builtin (builtin), 
     * a global variable (gvar), a (global) constant (val),
     * a (global) function (fn), a "function-private" 
     * variable (fvar), or a regular variable (var)
     * local to a function.
     */
    public enum SymbolType {
        BUILTIN, GVAR, VAL, FN, FVAR, VAR, PARAM, UNKNOWN
    }
    
    public class Symbol {
        private Symbol(String name, SymbolType varType) {
            super();
            this.name = name;
            this.varType = varType;
            this.jsref = "";
        }
        
        String name;
        String jsref;
        SymbolType varType;
    }
    
    private boolean inglobal() {
        return depth == GSCOPE;
    }

    private boolean inlocal() {
        return depth == LSCOPE;
    }

    private Symbol gvar(String name) {
        return new Symbol(name, SymbolType.GVAR);
    }
    
    private Symbol val(String name) {
        return new Symbol(name, SymbolType.VAL);
    }
    
    private Symbol function(String name) {
        return new Symbol(name, SymbolType.FN);
    }
    
    private Symbol fvar(String name) {
        return new Symbol(name, SymbolType.FVAR);
    }
    
    private Symbol var(String name) {
        return new Symbol(name, SymbolType.VAR);
    }
    
    private Symbol param(String name) {
        return new Symbol(name, SymbolType.PARAM);
    }
    
    private IdentityHashMap<ParserRuleContext, SymbolScope> scopes;
    private SymbolScope[] scopeChain;
    private SymbolScope currentScope;
    
    /**
     * The "scope depth." 0 denotes the builtin scope,
     * 1 denotes the global scope, and 2 denotes the
     * local scope.
     */
    private int depth;
    
    /*
     * Implementation note: At any given point, there are
     * at most three symbol scopes: the scope enclosing 
     * the built-in definitions ("built-in scope"), the
     * scope enclosing global definitions ("global scope"),
     * and the scope enclosing local definitions ("local
     * scope" or "function scope").
     */
    private static final int BSCOPE = 0;
    private static final int GSCOPE = 1;
    private static final int LSCOPE = 2;
    
}
