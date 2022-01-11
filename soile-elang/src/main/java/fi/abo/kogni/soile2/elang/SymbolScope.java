package fi.abo.kogni.soile2.elang;

import java.util.HashMap;

import fi.abo.kogni.soile2.elang.SymbolTable.Symbol;

public class SymbolScope {
    
    public SymbolScope() {
        this(null);
    }

    public SymbolScope(SymbolScope parent) {
        super();
        this.parent = parent;
        this.symbols = new HashMap<>();
    }
    
    public boolean contains(String name) {
        return has(name);
    }
    
    public boolean contains(Symbol s) {
        return has(s);
    }
    
    public boolean has(String name) {
        Symbol s = resolve(name);
        return (s != null);
    }
    
    public boolean has(Symbol s) {
        return has(s.name);
    }
    
    public Symbol resolve(String name) {
        return _resolve(name);
    }
    
    public void define(Symbol symbol) {
        define(symbol.name, symbol);
    }

    public void define(String name, Symbol symbol) {
        if (! symbols.containsKey(name)) {
            symbols.put(name, symbol);
        }
    }

    private Symbol _resolve(String name) {
        if (symbols.containsKey(name)) {
            return symbols.get(name);
        }
        if (parent == null) {
            return null;
        }
        return parent._resolve(name);
    }

    private HashMap<String, Symbol> symbols;
    private SymbolScope parent;
}
