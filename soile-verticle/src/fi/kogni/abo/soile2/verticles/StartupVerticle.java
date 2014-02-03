package fi.kogni.abo.soile2.verticles;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.vertx.java.core.AsyncResult;
import org.vertx.java.core.AsyncResultHandler;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;

public final class StartupVerticle extends SoileVerticle {
    
    public StartupVerticle() {
        this.deployed = new HashSet<String>();
    }

    @Override
    public void start() {
        JsonArray deploy = getContainer().config().getArray("deploy");
        
        for (int i = 0; i < deploy.size(); ++i) {
            deployModule(deploy.<JsonObject>get(i));
        }
    }
    
    private void undeployModules() {
        if (! deployed.isEmpty()) {
            Iterator<String> it = deployed.iterator();
            while (it.hasNext()) {
                String id = it.next();
                getContainer().undeployModule(id);
            }
            deployed.clear();
        }
    }
    
    private void deployModule(JsonObject module) {
        final int defaultInstances = 1;
        final String moduleName = module.getString("module");
        String confName = module.getString("config");
        final int instances = module.getInteger("instances", defaultInstances);

        JsonObject conf = createConfig(confName);
        AsyncResultHandler<String> doneHandler = new AsyncResultHandler<String>() {

            public void handle(AsyncResult<String> asyncResult) {
                
                if (asyncResult.succeeded()) {
                    String id = asyncResult.result();
                    deployed.add(id);
                    String msg = String.format(handlerSuccessPattern, 
                            moduleName, id, instances);
                    System.out.println(msg);
                } else {
                    String msg = String.format(handlerFailurePattern, 
                            moduleName, 
                            asyncResult.cause().getMessage());
                    System.err.println(msg);
                    System.err.println("Undeploying all verticles... Shutting down!");
                    undeployModules();
                    System.err.println("Shut down - done!");
                    System.err.flush();
                    System.exit(1);
                }
            }
            
        };
        getContainer().deployModule(moduleName, conf, instances, doneHandler);
    }
    
    private JsonObject createConfig(String name) {
        JsonObject appConf = container.config();
        JsonObject sharedConf = appConf.getObject("shared");
        JsonObject verticleConf = appConf.getObject(name);
        
        if (verticleConf == null) {
            verticleConf = new JsonObject();
        }
        
        JsonObject config = new JsonObject();
        config.putObject("shared", sharedConf);
        config.putObject("config", verticleConf);
        return config;
    }

    private Set<String> deployed;
    private static final String handlerSuccessPattern = 
            "The module '%s' was deployed successfully with ID '%s' (number of instances: %d).";
    private static final String handlerFailurePattern = 
            "The module '%s' could not be deployed.\n%s.";
    
}
