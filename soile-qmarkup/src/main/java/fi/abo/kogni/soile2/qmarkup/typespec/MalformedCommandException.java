package fi.abo.kogni.soile2.qmarkup.typespec;

public class MalformedCommandException extends RuntimeException {
    
    /**
     * 
     */
    private static final long serialVersionUID = -8023786090013447951L;

    public MalformedCommandException() {
        super();
    }

    public MalformedCommandException(String msg) {
        super(msg);
    }

}
