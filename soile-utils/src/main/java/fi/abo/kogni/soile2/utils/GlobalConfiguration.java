package fi.abo.kogni.soile2.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.Properties;

public final class GlobalConfiguration {
    
    private GlobalConfiguration() {
        this.properties = new Properties();
    }
    
    static {
        instance = new GlobalConfiguration();
    }
    
    public static GlobalConfiguration getInstance() {
        return instance;
    }
    
    public String get(String key) {
        return properties.getProperty(key);
    }
    
    public String get(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }
    
    public void set(String key, String value) {
        properties.setProperty(key, value);
    }
    
    public String getTemplateDirectory() {
        return get(KEY_TEMPLATE_DIRECTORY, DEFAULT_TEMPLATE_DIRECTORY);
    }
    
    public void load(Reader reader) {
        try {
            properties.load(reader);
        } catch (IOException e) {
            System.err.println("WARNING: Could not load properties.");
        }
    }
    
    public void load(InputStream inStream) {
        try {
            properties.load(inStream);
        } catch (IOException e) {
            System.err.println("WARNING: Could not load properties.");
        }
    }
    
    private static GlobalConfiguration instance;
    
    private static final String KEY_TEMPLATE_DIRECTORY = "template-directory";
    private static final String DEFAULT_TEMPLATE_DIRECTORY = "./templates";
    
    private Properties properties;
}
