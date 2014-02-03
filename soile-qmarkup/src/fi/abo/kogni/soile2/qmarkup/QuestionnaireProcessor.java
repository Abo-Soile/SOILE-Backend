package fi.abo.kogni.soile2.qmarkup;

import java.util.ArrayList;

import fi.abo.kogni.soile2.qmarkup.typespec.MalformedCommandException;


public interface QuestionnaireProcessor {
    public void processCommand(String command, ArrayList<String> args) 
                throws MalformedCommandException;
    
    public void processText(String text);
    
    public void finish();
    
    public String output();
}
