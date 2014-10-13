var vertx = require('vertx');
var console = require('vertx/console');
var container = require('vertx/container');

var templateManager = (function() {
  var templates = [];
  var isLoaded = false;
  var eb = vertx.eventBus;
  var i, sp;

  var debug = container.config.debug;
  var folder = container.config.template_folder;

  console.log("TEMPLATEFOLDER: " + folder + "DEBUG MODE: " + debug);
  vertx.fileSystem.readDir(folder, function(err, res) {
    for (i = 0; i < res.length; i++) {
      sp = res[i].lastIndexOf("/") + 1;
      //console.log(res[i].slice(sp).replace(".html",""));

      if(err) {
        console.log("Error in templatemanager: " + err);
      }

      templates.push(res[i].slice(sp).replace(".html", ""));
    }
    console.log(JSON.stringify(templates));

  });

  return {
    'load_template': function(templateName) {
      console.log("Loading template " + templateName);
      vertx.fileSystem.readFile(folder + templateName + ".html", function(err, res) {
        if (!err) {
          var templateContent = res.getString(0, res.length());
          eb.send("dust.compile", {'name': templateName, "source": templateContent}, function(reply) {
            console.log("Loading template " + JSON.stringify(reply));
          });
        } else {
          console.log(err);
        }
      });
    },
    'render_template': function(templateName, data, request) {

      data.URI = String(request.absoluteURI());
      data.token = request.session.getPersonToken();
      if(request.session.loggedIn()) {
        data.loggedIn = true;
        data.user = request.session.loggedIn();
      }
      else {
        data.loggedIn = false;
      }

      console.log(JSON.stringify(data));

      if (!isLoaded || debug) {
        this.load_template(templateName);
        vertx.setTimer(500, function() {
          eb.send("dust.render", {"name": templateName, "context": data}, function(reply) {
            request.response.end(reply.output);
          });
        });
      }else{
        eb.send("dust.render", {"name":templateName, "context":data}, function(reply) {
            request.response.end(reply.output);
            });
      }

    },
    'loadAll':function(){
      var i;
      if(!isLoaded||DEBUG){
        for(i=0; i<templates.length;i++){
          this.load_template(templates[i]);
        }
        isLoaded = true;
      }
    }
  };

});


module.exports = templateManager();