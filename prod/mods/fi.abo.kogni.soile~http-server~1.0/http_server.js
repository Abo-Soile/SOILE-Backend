var vertx = require('vertx');
var container = require('vertx/container');
var console = require('vertx/console');

var server = vertx.createHttpServer();
var config = container.config;
var http_config = config.config;
var shared_config = config.shared;
var port = http_config.port;
var host = http_config.host;
var http_directory = http_config.directory;
var routeMatcher = new vertx.RouteMatcher();

function customMatcher() {
  var test = "6";
}

customMatcher.prototype = new vertx.RouteMatcher();

routeMatcher = new customMatcher();

var DEBUG = true;   //This variable could stored in configs

// function arrayContains(item, array){
//   return (arrhaystack.indexOf(needle) > -1);
// }

var utils = (function(conf) {

  var addresses = conf.addresses;
  var directories = conf.directories;

  return {

    'secure_path': function(path) {
      var secured = path.replace(/\.\.\//g, '');
      secured = secured.replace(/\.\//g, '');
      return secured;
    },

    'get_address': function(address) {
      return addresses[address];
    },

    'get_directory': function(dir) {
      return directories[dir];
    },

    // Get the base directory of the ENTIRE app.
    'get_basedir': function() {
      return this.get_directory('/');
    },

    // Get the base directory of the HTTP server.
    'get_serverdir': function() {
      return http_directory;
    },

    'file_exists': function(path) {
      var file = new java.io.File(path);
      return file.exists();
    },

    'file_from_serverdir': function(path) {
      return this.build_path(this.get_serverdir(), path);
    },

    'file_from_basedir': function(path) {
      return this.build_path(this.get_basedir(), path);
    },

    // https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Functions_and_function_scope/arguments
    'build_path': function() {
      var args = Array.slice(arguments);
      var path = args.join('/');
      return this.secure_path(path);
    },

    'build_url': function(host, port, resource) {
      return 'http://' + host + ':' + port + resource;
    }
  };

})(shared_config);

var queryMongo = require('mongoHandler');

var templateManager = (function(folder) {
  var templates = [];
  var isLoaded = false;
  var eb = vertx.eventBus;
  var i, sp;
  vertx.fileSystem.readDir(folder, function(err, res) {
    for (i = 0; i < res.length; i++) {
      sp = res[i].lastIndexOf("/") + 1;
      //console.log(res[i].slice(sp).replace(".html",""));
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
      if (!isLoaded || DEBUG) {
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

})(http_config.template_folder);


//Ugly hack to make sure that the template module is online before loading
//Base templates
var timerID = vertx.setTimer(2000, function() {
  console.log("\n ------Loading base templates------");
 // templateManager.load_template("header");
 // templateManager.load_template("footer");
  templateManager.loadAll();
});

var read_khtoken = (function() {
  var pattern = /khtoken=(\w+)/;
  return function(cookie) {
    var match = [];

    if (typeof cookie !== 'string') {
      return null;
    }

    match = cookie.match(pattern);
    if (match === null) {
      return null;
    }
    if (match.length > 1) {
      return match[1];
    }
    return null;
  };
})();


routeMatcher.get("/login", function(request) {
  templateManager.render_template('login', "",request);
});


routeMatcher.post("/login", function(request) {
  var data = new vertx.Buffer();

  request.dataHandler(function(buffer) {
    data.appendBuffer(buffer);
  });

  request.endHandler(function() {


    console.log("Data: " + data.getString(0, data.length()));

    request.response.end("Returning testpost");
  });
});

routeMatcher.get("/logout", function(request) {
  requst.response.end("Logging user out");
});

routeMatcher.get("/a", function(request){
  console.log(JSON.stringify(container.config));

  vertx.eventBus.send("vertx.mongo-persistor",{"action":"save", 
        "collection":"test","document":{"name":"Doe Joeddddd", "Age":10}},function(reply){
          console.log(JSON.stringify(reply));
        });

  vertx.eventBus.send("vertx.mongo-persistor",{"action":"find", 
      "collection":"test"},function(reply){
        console.log(JSON.stringify(reply));
      });

  templateManager.render_template('landing', {"name":"","test":"This is a test"},request);
});

routeMatcher.get("/aa", function(request){

  templateManager.render_template('landing', {"name":"Daniel Testing","test":"This is a test"},request);

});


routeMatcher.get('/dust', function(request){
  templateManager.loadAll();

  request.response.end("Updated templates");
});

routeMatcher.get('/dust1', function(request){
  var eb = vertx.eventBus;
  eb.send("dust.load", {"name":"test", "context": {"x":"world"}}, function(reply){
   request.response.end(JSON.stringify(reply));
  });
});

routeMatcher.get('/dust2', function(request){
  var eb = vertx.eventBus;
  eb.send("dust.render", {"name":"test", "context": {"x":"world"}}, function(reply){
   request.response.end(JSON.stringify(reply));
  });
});


routeMatcher.get("/experiment", function(request){
  queryMongo.getExperimentList(function(r){
    console.log(JSON.stringify(r.results));
    templateManager.render_template("experimentList", {"experiments":r.results}, request);
  });
});


routeMatcher.get("/experiment/new", function(request){
  templateManager.render_template("experimentform", {},request);
});


routeMatcher.post("/experiment/new", function(request) {
  var data = new vertx.Buffer();

  request.dataHandler(function(buffer){
    data.appendBuffer(buffer);
  });

  request.endHandler(function() {

    var id = "sdfj2834dfGER";
    var jsonData = JSON.parse(data.getString(0, data.length()));
    console.log(data.getString(0, data.length())); 

    var sDate = new Date(jsonData.startDate);
    var eDate = new Date(jsonData.endDate);


    console.log(sDate.toString());

    queryMongo.saveExperiment(jsonData, function(r){
      console.log(JSON.stringify(r));
      var resp = {
        "status":"ok",
        "id":r._id
      };
      request.response.putHeader("Content-Type", "application/json; charset=UTF-8");
      request.response.end(JSON.stringify(resp));

    });


    //request.response.end({"status":"ok","id":id});
  });
});

routeMatcher.get('/experiment/:id', function(request){
  var id = request.params().get('id');
    // eb.send("vertx.mongo-persistor",{"action":"findone", 
  //  "collection":"experiment","matcher":{"name":id}},function(reply){
  //     console.log(JSON.stringify(reply));
  //     var expname = reply.result.name
  //     

  //   });
  queryMongo.getExperiment(id,function(r){
    var expname = r.result.name;
    var experiment = r.result;
    console.log(JSON.stringify(r));
    templateManager.render_template("experiment", {"exp":experiment},request);
  });
});



routeMatcher.get('/experiment/:id/edit', function(request){
  var id = request.params().get('id');
  console.log(id);

  queryMongo.getExperiment(id,function(r){
    var experiment = r.result;
    console.log(JSON.stringify(r));
    templateManager.render_template("editexperiment", {"exp":experiment},request);
  });
});

routeMatcher.post('/experiment/:id/edit', function(request){
  var data = new vertx.Buffer();

  request.dataHandler(function(buffer){
    data.appendBuffer(buffer);
  });

  request.endHandler(function() {

    var id = request.params().get('id');
    var jsonData = JSON.parse(data.getString(0, data.length()));
    console.log(data.getString(0, data.length())); 

    var sDate = new Date(jsonData.startDate);
    var eDate = new Date(jsonData.endDate);


    console.log(sDate.toString());

    queryMongo.updateExperiment(jsonData, id,function(r){
      console.log(JSON.stringify(r));
      var resp = r;
      request.response.putHeader("Content-Type", "application/json; charset=UTF-8");
      request.response.end(JSON.stringify(resp));

    });
    //request.response.end({"status":"ok","id":id});
  });
});

routeMatcher.post('/experiment/:id/addform', function(request){
  var address = utils.get_address('questionnaire_render');
  var expId = request.params().get('id');


  // http://nelsonwells.net/2012/02/json-stringify-with-mapped-variables/#more-153
  var msg = {
    'markup': "",
    'action': "save"
  };

  vertx.eventBus.send(address, msg, function(reply) {
    var response = {};
    var id = reply.id;
    queryMongo.addFormToExperiment(expId,id,"Unnamed Form", function(r){
      response.id = id;

      request.response.putHeader("Content-Type", "application/json; charset=UTF-8");
      request.response.end(JSON.stringify(response));
    });
  });
});

routeMatcher.get('/experiment/:id/json', function(request){
  var expId = request.params().get('id');

  queryMongo.getExperiment(expId, function(r){
    request.response.putHeader("Content-Type", "application/json; charset=UTF-8");
    request.response.end(JSON.stringify(r.result));
  });
});


routeMatcher.get('/experiment/:id/phase/:phase', function(request) {
  var expID = request.params().get('id');
  var phase = request.params().get('phase');

  queryMongo.getExperiment(expID, function(r) {
    phase = r.result.components[phase];
    if(phase.type === "form") {
      console.log("Form ");

      queryMongo.getForm(phase.id, function(r2) {
        var form = r2.result.form;

        templateManager.render_template("formphase", {"form":form}, request);

       // request.response.end(form);
      });
    if(phase.type === "test") {
      console.log("test");
    }

    }
    else {
      console.log("Phase type is undefined");
    }
  });
});

routeMatcher.post('/experiment/:id/phase/:phase', function(request) {
  var expID = request.params().get('id');
  var phase = request.params().get('phase');

  var data = new vertx.Buffer();

  request.dataHandler(function(buffer) {
    data.appendBuffer(buffer);
  });

  request.endHandler(function() {
    var postData = data.getString(0, data.length());
    var postJson = JSON.parse(postData);
    console.log(postData);

    queryMongo.saveFormData(phase, expID, postJson, function(r){
      console.log(JSON.stringify(r));
      request.response.end("Data \n" + postData);
    });
  });
});


routeMatcher.get('/test/demo', function(request) {
  var file = 'demo.html';
  request.response.sendFile(utils.file_from_serverdir(file));
});


routeMatcher.post('/test/run', function(request) {
  var body = new vertx.Buffer();

  request.dataHandler(function(buffer) {
    body.appendBuffer(buffer);
  });
  request.endHandler(function() {
    var address = utils.get_address('experiment_language');
    var eb = vertx.eventBus;
    var msg = {
      'code': body.getString(0, body.length())
    };

    eb.send(address, msg, function(reply) {
      var response = {};

      if (reply.hasOwnProperty('errors') === true) {
        response.errors = reply.errors;
      } else {
        response.code = reply.code;
      }
      request.response.putHeader("Content-Type", "application/json; charset=UTF-8");
      request.response.end(JSON.stringify(response));
    });
  });
});

routeMatcher.get('/questionnaire', function(req) {
  var file = http_directory.concat('/questionnaire.html');

  req.response.sendFile(file);
});

routeMatcher.get('/questionnaire/guide', function(request) {
  var file = 'qml-guide.html';

  request.response.sendFile(utils.file_from_serverdir(file));
});

routeMatcher.post('/questionnaire/render', function(request) {
  var body = new vertx.Buffer();

  request.dataHandler(function(buffer) {
    body.appendBuffer(buffer);
  });

  request.endHandler(function() {
    var address = utils.get_address('questionnaire_render');
    var eb = vertx.eventBus;

    // http://nelsonwells.net/2012/02/json-stringify-with-mapped-variables/#more-153
    var msg = {
      'markup': body.getString(0, body.length()),
      'action': "save"
    };

    eb.send(address, msg, function(reply) {
      var response = {};
      var id = '', link = '';

      if (reply.hasOwnProperty('error') === true) {
        response.error = reply.error;
      } else {
        id = reply.id;
        link = utils.build_url(host, port, '/questionnaire/generated/'.concat(id));
        response.id = id;
        response.link = link;
      }

      request.response.putHeader("Content-Type", "application/json; charset=UTF-8");
      request.response.end(JSON.stringify(response));
    });
  });
});

routeMatcher.get('/questionnaire/generated/:id', function(request) {
  console.log(request.method);
  var id = request.params().get('id');
  var file = utils.build_path(utils.get_basedir(),
                              utils.get_directory('questionnaires'),
                              id);
  console.log(utils.build_path(utils.get_basedir(),utils.get_directory('questionnaires'),id));
  console.log(utils.get_basedir());
  vertx.fileSystem.readDir('', function(err, res){
    //console.log(err);
    var i;
    for (i = 0; i < res.length; i++) {
      console.log(res[i]);  
    }
  });
  request.response.sendFile(file);

});

routeMatcher.get('/questionnaire/mongo/:id', function(request){
  var id = request.params().get('id');
  queryMongo.getForm(id, function(r){
    //console.log(JSON.stringify(r))
    var form = r.result.form;
    var markup = r.result.markup;
    templateManager.render_template('displayForm', {"form":form,"markup":markup},request);
  });
});

routeMatcher.post('/questionnaire/mongo/:id', function(request) {
  var postdata = new vertx.Buffer();
  var id = request.params().get("id");

  request.dataHandler(function(data) {
    postdata.appendBuffer(data);
  });

  request.endHandler(function() {
    var markup = postdata.getString(0, postdata.length());

    var address = utils.get_address("questionnaire_render");

    var message = {
      "markup": markup,
      "action": "save",
      "id": id
    };

    vertx.eventBus.send(address, message, function(reply) {
      //console.log(JSON.stringify(reply));
      var response = {};
      if (reply.hasOwnProperty('error') === true) {
        response.error = reply.error;
      } else {
        response = {"test":"testresponse",
                      "data": reply.form};
                  };

      request.response.putHeader("Content-Type", "application/json; charset=UTF-8");
      request.response.end(JSON.stringify(response));
    });
  
  });
});

routeMatcher.get('/questionnaire/mongo/:id/getform', function(request) {
  var id = request.params().get('id');
  queryMongo.getForm(id,function(r) {
    var form = r.result.form;
    form = "<div id='formcol'>".concat(form,"</div>");
    request.response.end(form);
  });
});

routeMatcher.post('questionnaire/generated/:id', function(request) {
  console.log(request.method);
});

/* This will match static files. ('Static files' are files which 
 * have not been generated programmatically.) */
routeMatcher.allWithRegEx('.*\.(html|htm|css|js|png|jpg|jpeg|gif|ico)$', function(req) {
  req.response.sendFile(utils.file_from_serverdir(req.path()));
});

routeMatcher.allWithRegEx('.*/', function(req) {
  console.log(req.absoluteURI());
  console.log(req.uri());

  var url = req.uri().substring(0, req.uri().length - 1);

  console.log(url);

  req.response.statusCode(302);
  req.response.putHeader('Location', url);
  req.response.end();
});

routeMatcher.noMatch(function(req) {
  req.response.end("404");
});

/* Let this be the last specified match. */
routeMatcher.allWithRegEx('.+', function(req) {
  var file = http_directory.concat('/questionnaire.html');

  req.response.sendFile(file);
});

server.requestHandler(routeMatcher).listen(port, host);

function vertxStop() {
  server.close();
}
