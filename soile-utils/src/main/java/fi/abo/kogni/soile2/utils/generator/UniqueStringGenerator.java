package fi.abo.kogni.soile2.utils.generator;

import java.util.concurrent.atomic.AtomicInteger;

public final class UniqueStringGenerator {
    
    public static AtomicInteger createCounter() {
        return new AtomicInteger(FIRST_ID_VALUE);
    }
    
    public UniqueStringGenerator(String prefix) {
        this();
        this.setPrefix(prefix);
    }
    
    public UniqueStringGenerator() {
        this(DEFAULT_ID);
    }
    
    public UniqueStringGenerator(AtomicInteger counter) {
        id = counter;
        prefix = "";
    }
    
    public String generate() {
        return String.format("%s%s", prefix, id2string());
    }
    
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
    
    public void setCounter(AtomicInteger counter) {
        this.id = counter;
    }

    private String id2string() {
        final int i = id.getAndIncrement();
        return (i < 0) ? String.format("_%s", Integer.toHexString(Math.abs(i)))
                       : String.format("%s", Integer.toHexString(i));
    }
    
    private AtomicInteger id;
    private String prefix;
    
    public static final int FIRST_ID_VALUE = Integer.MIN_VALUE + 1;
    private static final AtomicInteger DEFAULT_ID = new AtomicInteger(FIRST_ID_VALUE);
}