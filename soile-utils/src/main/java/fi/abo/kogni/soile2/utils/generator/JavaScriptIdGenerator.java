package fi.abo.kogni.soile2.utils.generator;

import java.util.HashMap;


public class JavaScriptIdGenerator {
    
    public JavaScriptIdGenerator() {
        init(this.new Counter(), "");
    }
    
    public JavaScriptIdGenerator(Counter c, String prefix) {
        init(c, prefix);
    }
    
    public JavaScriptIdGenerator(int value, String prefix) {
        init(value, prefix);
    }
    
    public JavaScriptIdGenerator(String prefix) {
        init(this.new Counter(), prefix);
    }
    
    public JavaScriptIdGenerator(Counter c) {
        init(c, "");
    }
    
    public void addClass(String name, Counter counter, String prefix) {
        Data d = new Data(counter, prefix);
        classes.put(name, d);
    }
    
    public void setDefault(Counter counter, String prefix) {
        _default.counter = counter;
        _default.prefix = prefix;
    }
    
    public void setDefault(String name) {
        if (classes.containsKey(name)) {
            this._default = classes.remove(name);
        }
    }
    
    public String generate() {
        return gen(_default);
    }
    
    public String generateFor(String name) {
        Data d = this._default;
        if (classes.containsKey(name)) {
            d = classes.get(name);
        }
        return gen(d);
    }
    
    public final class Counter {
        
        public Counter() {
            this(0);
        }
        
        public Counter(int value) {
            this.value = value;
        }
        
        public void increment() {
            increment(1);
        }

        public void increment(int by) {
            this.value += by;
        }
        
        public int get() {
            return this.value;
        }
        
        public int getAndIncrement() {
            int oldValue = get();
            increment();
            return oldValue;
        }
        
        public int incrementAndGet() {
            increment();
            return get();
        }

        private int value;
    }
    
    private void init(Counter c, String prefix) {
        this._default = new Data(c, prefix);
        this.classes = new HashMap<>();
    }
    
    private void init(int value, String prefix) {
        init(this.new Counter(value), prefix);
    }
    
    private String gen(Data d) {
        return String.format("%s%d", d.prefix, d.counter.getAndIncrement());
    }
    
    private final class Data {
        public Data(Counter counter, String prefix) {
            super();
            this.counter = counter;
            this.prefix = prefix;
        }
        Counter counter;
        String prefix;
    }
    
    private Data _default;
    private HashMap<String, Data> classes;
    
}
