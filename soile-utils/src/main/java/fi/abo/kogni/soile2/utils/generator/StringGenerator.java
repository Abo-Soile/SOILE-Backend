package fi.abo.kogni.soile2.utils.generator;

public class StringGenerator {
    
    public static void main(String[] args) {
        System.out.println(name());
        System.out.println(name());
    }
    
    public static synchronized String id() {
        String id = String.format("id%d", idId);
        ++idId;
        return id;
    }
    
    public static synchronized String name() {
        String id = String.format("name%05d", nameId);
        ++nameId;
        return id;
    }
    
    private static int nameId = 1;
    private static long idId = 12013939483L;
}
