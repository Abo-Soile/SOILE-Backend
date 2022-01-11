package fi.abo.kogni.soile2.elang;

public class FunctionCallParam {
    
    public FunctionCallParam(String jsref) {
        super();
        this.jsref = jsref;
    }

    public void jsref(String s) {
        this.jsref = s;
    }

    public String jsref() {
        return this.jsref;
    }

    private String jsref;
}
