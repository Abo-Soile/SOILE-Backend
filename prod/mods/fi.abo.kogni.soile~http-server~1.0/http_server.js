var vertx = require('vertx');
var container = require('vertx/container');
var console = require('vertx/console');

var server = vertx.createHttpServer();
var config = container.config;
var http_config = config['config'];
var shared_config = config['shared'];
var port = http_config['port'];
var host = http_config['host'];
var http_directory = http_config['directory'];
var routeMatcher = new vertx.RouteMatcher();


var utils = (function(conf){

  var addresses = conf['addresses'];
  var directories = conf['directories'];

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
    'get_basedir': function(){
      return this.get_directory('/');
    },

    // Get the base directory of the HTTP server.
    'get_serverdir': function(){
      return http_directory;
    },

    'file_exists': function(path){
      var file = new java.io.File(path);
      return file.exists();
    },

    'file_from_serverdir': function(path){
      return this.build_path(this.get_serverdir(), path);
    },

    'file_from_basedir': function(path){
      return this.build_path(this.get_basedir(), path);
    },

    // https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Functions_and_function_scope/arguments
    'build_path': function(){
      var args = Array.slice(arguments);
      var path = args.join('/');
      return this.secure_path(path);
    },

    'build_url': function(host, port, resource) {
      return 'http://' + host + ':' + port + resource;
    }

  };

})(shared_config);

var templateManager = (function(folder){
  var templates = [];
  vertx.fileSystem.readDir(folder, function(err,res){
    for (var i = 0; i < res.length; i++) {
      console.log(res[i]);
      templates.push(res[i]);
      console.log(JSON.stringify(templates));

    }  
  });

})(http_config.template_folder);

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

routeMatcher.get("/a", function(request){
  request.response.end("This is a test");
});

routeMatcher.get('/dust', function(request){
  var eb = vertx.eventBus;

  eb.send("dust.compile", {'name':'test', "source":"hello {x}"}, function(reply){
      request.response.end(JSON.stringify(reply));
  });
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


routeMatcher.get('/experiment/demo', function(request) {
  var file = 'demo.html';
  request.response.sendFile(utils.file_from_serverdir(file));
});


routeMatcher.post('/experiment/run', function(request) {
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
        response['errors'] = reply['errors'];
      } else {
        response['code'] = reply['code'];
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
      'markup': body.getString(0, body.length())
    };

    eb.send(address, msg, function(reply) {
      var response = {};
      var id = '', link = '';

      if (reply.hasOwnProperty('error') === true) {
        response['error'] = reply['error'];
      } else {
        id = reply['id'];
        link = utils.build_url(host, port, '/questionnaire/generated/'.concat(id));
        response['id'] = id;
        response['link'] = link;
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
    for (var i = 0; i < res.length; i++) {
      console.log(res[i]);  
    };
  });
  request.response.sendFile(file);

});

routeMatcher.post('questionnaire/generated/:id', function(request) {
  console.log(request.method);
});

/* This will match static files. ('Static files' are files which 
 * have not been generated programmatically.) */
routeMatcher.allWithRegEx('.*\.(html|htm|css|js|png|jpg|jpeg|gif|ico)$', function(req) {
  req.response.sendFile(utils.file_from_serverdir(req.path()));
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
