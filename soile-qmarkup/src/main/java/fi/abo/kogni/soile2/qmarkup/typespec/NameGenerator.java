package fi.abo.kogni.soile2.qmarkup.typespec;

import java.util.concurrent.atomic.AtomicInteger;

public class NameGenerator {
    
    public NameGenerator() {
        AtomicInteger counter = new AtomicInteger(Integer.MIN_VALUE + 1);
        objectCounter = counter;
        arrayCounter = counter;
        repeatCounter = counter;
        commandCounter = counter;
    }
    
    public void setCommandCounter(AtomicInteger counter) {
        this.commandCounter = counter;
    }

    public void setObjectCounter(AtomicInteger counter) {
        this.objectCounter = counter;
    }

    public void setArrayCounter(AtomicInteger counter) {
        this.arrayCounter = counter;
    }

    public void setRepeatCounter(AtomicInteger counter) {
        this.repeatCounter = counter;
    }

    public static String defaultXXXValue(String className) {
        return String.format("default%sValue", className);
    }
    
    public static String validateXXX(String className) {
        return String.format("validate%s", className);
    }
    
    public static String id2string(final int id) {
        return (id < 0) ? String.format("_%d", Math.abs(id))
                        : String.format("%d", id);
    }
    
    public static String defaultObjectValue(final int id) {
        return String.format("%s%s", defaultXXXValue("Object"), id2string(id));
    }
    
    public static String validateObject(final int id) {
        return String.format("%s%s", validateXXX("Object"), id2string(id));
    }
    
    public static String defaultArrayValue(final int id) {
        return String.format("%s%s", defaultXXXValue("Array"), id2string(id));
    }
    
    public static String validateArray(final int id) {
        return String.format("%s%s", validateXXX("Array"), id2string(id));
    }
    
    public static String validateRepeat(final int id) {
        return String.format("%s%s", validateXXX("Repeat"), id2string(id));
    }
    
    public static String validateCommand(final int id) {
        return String.format("%s%s", validateXXX("Command"), id2string(id));
    }
    
    public int arrayID() {
        return nextID(arrayCounter);
    }
    
    public int objectID() {
        return nextID(objectCounter);
    }
    
    public int repeatID() {
        return nextID(repeatCounter);
    }
    
    public int commandID() {
        return nextID(commandCounter);
    }
    
    private int nextID(AtomicInteger counter) {
        return counter.incrementAndGet();
    }
    
    private AtomicInteger objectCounter;
    private AtomicInteger arrayCounter;
    private AtomicInteger repeatCounter;
    private AtomicInteger commandCounter;
}