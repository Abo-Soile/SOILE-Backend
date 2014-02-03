package fi.abo.kogni.soile2.qmarkup;

import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

public class Template {
    
    public Template(String dir) {
        this.directory = dir;
    }
    
    public STGroup getTemplate(String name) {
        String sep = System.getProperty("file.separator");
        String wholeName = directory.concat(sep).concat(name);
        return new STGroupFile(wholeName);
    }
    
    private String directory;
    
}
