package fi.abo.kogni.soile2.qmarkup;

public class MalformedQuestionnaireException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = -37877797068320611L;

    public MalformedQuestionnaireException() {
    }

    MalformedQuestionnaireException(String reason) {
        super(reason);
    }
    
}
