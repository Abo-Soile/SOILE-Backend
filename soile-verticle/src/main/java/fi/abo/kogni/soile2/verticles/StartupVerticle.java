package fi.abo.kogni.soile2.verticles;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import io.vertx.core.AsyncResult;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

public final class StartupVerticle extends SoileVerticle {
    
    public StartupVerticle() {
        this.deployed = new HashSet<String>();
    }

    @Override
    public void start() {
    	
        JsonArray deploy = vertx.getOrCreateContext().config().getJsonArray("deploy");
        
        for (int i = 0; i < deploy.size(); ++i) {
            deployModule(deploy.getJsonObject(i));
        }
    }
    
   
    private void undeployModules() {
        if (! deployed.isEmpty()) {
            Iterator<String> it = deployed.iterator();
            while (it.hasNext()) {
                String id = it.next();
                vertx.undeploy(id);
            }
            deployed.clear();
        }
    }
   
    //From what I understand from the docs of vertx, this needs to be done differently now. Lets check what happens without
    private void deployModule(JsonObject module) {
        final int defaultInstances = 1;
        final String moduleName = module.getString("module");
        String confName = module.getString("config");
        final int instances = module.getInteger("instances", defaultInstances);
        JsonObject conf = createConfig(confName);
        DeploymentOptions opts = new DeploymentOptions(conf);
        opts.setInstances(instances);        
        
        Handler<AsyncResult<String>> doneHandler = new Handler<AsyncResult<String>>() {

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
                    //From my understanding this should happen automatically
                    undeployModules();
                    System.err.println("Shut down - done!");
                    System.err.flush();
                    System.exit(1);
                }
            }
            
        };
        vertx.deployVerticle(moduleName, opts, doneHandler);
    }
    
    private JsonObject createConfig(String name) {
        JsonObject appConf = config();
        JsonObject sharedConf = appConf.getJsonObject("shared");
        JsonObject verticleConf = appConf.getJsonObject(name);
        
        if (verticleConf == null) {
            verticleConf = new JsonObject();
        }
        
        JsonObject config = new JsonObject();
        config.put("shared", sharedConf);

        //TODO Refactor and remove the duplicated config object
        config.put("config", verticleConf);  //Refactor away at some point

        //Inserts the config in the jsonroot, vertx expects this so this method should be used
        config.mergeIn(verticleConf);
        //System.out.println(config.toString());
        return config;
    }

    private Set<String> deployed;
    private static final String handlerSuccessPattern = 
            "The module '%s' was deployed successfully with ID '%s' (number of instances: %d).";
    private static final String handlerFailurePattern = 
            "The module '%s' could not be deployed.\n%s.";
    
}
