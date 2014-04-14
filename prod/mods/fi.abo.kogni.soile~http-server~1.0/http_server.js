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
//var routeMatcher = new vertx.RouteMatcher();

var sessionMap = vertx.getMap("soile.session.map");


messageDigest = java.security.MessageDigest.getInstance("SHA-256");

var a = new java.lang.String("sdfsdfs");
console.log(a.hashCode());

// This function returns a function that calls the requesthandler
// which makes it possible to run arbitrary code before the request
function sessionTest(func) {
  return function(request) {
    //console.log("this should be seen before the request")

    request.headers().forEach(function(key,value){
      //console.log(key + " - " + value);
    });

    //console.log("Cookies:: " + request.headers().get("Cookie"));

    request.redirect = function(url) {
      console.log("Redirecting to " + url);
      console.log(this.remoteAddress());

      this.response.statusCode(302);
      this.response.putHeader('Location', url);
      this.response.end();
    }

    request.unauthorized = function() {
      this.response.statusCode(401);

      var context = {};
      context.short = "Not authorized";
      context.long =  "You're not authorized to view this content. Try logging in";

      templateManager.render_template("error", context, this);
      //this.response.end("401, Unauthorized");
    }

    request.notfound = function() {
      this.response.statusCode(404);
      
      var context = {};
      context.short = "404, not found" ;
      context.long =  "The content you're looking for couldn't be found.";

      templateManager.render_template("error", context, this);
    }

    var session = sessionManager.loadManager(request);
    session.setPersonToken();

    //Sending the session manager with the request
    request.session = session;

    func(request);
  }
}


//Decorator ish function to ensure that the user is admin
function requireAdmin(func) {
  return function(request) {
    console.log("Require Admin running " + request.session.isAdmin())
    if (!request.session.isAdmin()) {
      request.unauthorized();
    }else {
      func(request);
    }
  }
}


function customMatcher() {
  var test = "6";
}

customMatcher.prototype = new vertx.RouteMatcher();

//more methods from the routematcher should be implementd as needed.
customMatcher.get = function(pattern, handler) {
  routeMatcher.get(pattern, sessionTest(handler));
}

customMatcher.post = function(pattern, handler) {
  routeMatcher.post(pattern, sessionTest(handler));
}

customMatcher.allWithRegEx = function(pattern, handler) {
  routeMatcher.allWithRegEx(pattern, sessionTest(handler));
}

customMatcher.noMatch = function(pattern, handler) {
  routeMatcher.noMatch(pattern, sessionTest(handler));
}


// Generates  a new customMatcher and sets it to routmatcher
// this matcher is then bound to de server object at the bottom
// of this file. The normal routematcher can also be called if 
// needed.
var routeMatcher = new customMatcher();

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
    },

    'getRandomInt': function(min, max) {
      return Math.floor(Math.random() * (max - min + 1)) + min;
    },
    'getUrlParams': function(params) {
      var paramsObject = {};

      params = params.split('&');
      for(var i = 0; i<params.length;i++) {
        var datapart = params[i].split('=');
        paramsObject[datapart[0]] = datapart[1];
      }

      return paramsObject;
    }
  };

})(shared_config);


var queryMongo = require('mongoHandler');

queryMongo.init();

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

var sessionManager =  {

  cookies: null,
  request: null,

  loadManager: function(request) {
    this.cookies = request.headers().get("Cookie")
    this.request = request;
    return this;
  },

  createCookie: function(name, value, days) {
    if(days) {
      var date = new Date();
      date.setTime(date.getTime()+(days*24*60*60*1000));
      var expires = "; expires="+date.toGMTString();
    }
    else var expires = "";

    return name+"="+value+expires+"; path=/";
  },

  readCookie: function(name) {
    var nameEQ = name + "=";
    
    //Dont do anything if no cookies exist
    if(!this.cookies) return 0;
    
    var ca = this.cookies.split(';');

    for(var i=0;i < ca.length;i++) {
      var c = ca[i];
      while (c.charAt(0)==' ') c = c.substring(1,c.length);
      if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length,c.length);
    }
    return 0;
  },

  eraseCookie: function(name) {
    this.createCookie(name,"", -1);
  },

  setPersonToken: function() {
    //console.log(this.readCookie("PersonToken"));
    if(!this.readCookie("PersonToken")) {
      var c = this.createCookie("PersonToken", utils.getRandomInt(0, 10000000000000000), 900);
      this.request.response.putHeader("Set-Cookie",c);
    }
  },

  getPersonToken: function() {
    return this.readCookie("PersonToken");
  },

  setSessionCookie: function(key) {
    var c = this.createCookie("Session", key, 1);
    this.request.response.putHeader("Set-Cookie", c);
  },

  getSessionCookie: function() {
    return this.readCookie("Session");
  },


  login: function(id,username, admin) {
      console.log("----Logging in-----")
      //console.log(JSON.stringify(r));

      var sessionKey = java.util.UUID.randomUUID().toString();
      console.log(this.getPersonToken());
      this.setSessionCookie(sessionKey);

      var timerID = vertx.setTimer(1000*60*60*24, function(timerID) {
        sessionMap.put(sessionKey, null);
      });

      sessionMap.put(sessionKey, JSON.stringify({"username":username, "timerID": timerID,
                                                 "admin":admin,"id":id}));
  },

  loggedIn: function() {
    var sessionData = sessionMap.get(this.getSessionCookie());
    if(sessionData == null) {
      return false;
    }
    else {return JSON.parse(sessionData);}
      
  },

  isAdmin: function() {
    var sessionData = sessionMap.get(this.getSessionCookie());
    if(sessionData == null) {
      return false;
    }
    else {
      return JSON.parse(sessionData).admin;
    }
  },

  logout: function() {
    var data = this.loggedIn();
    if(data) {
      console.log("Logging out");
      console.log(JSON.stringify(data));
      vertx.cancelTimer(data.timerID);
      sessionMap.put(this.getSessionCookie(), "");

      this.setSessionCookie("");
    } else {
      console.log("there was no data");
    }   
  }

}

//Injects session code that is run before the actual request
//It would probably be best to generalize this abit more to make it extendable
//with other functionality as well

//DEPRECATED, see sessiontest instead
function session(func) {
  console.log("test");
  return function (request) {
    console.log("Before returnfunction");
    request.headers().forEach(function(key,value){
      //console.log(key + " - " + value);
    });

    console.log("Cookies:: " + request.headers().get("Cookie"));

    var session = sessionManager.loadManager(request);
    session.setPersonToken();

    request.prototype.redirect = function(url) {
      console.log("Redirecting to " + url);
      console.log(this.remoteAddress());

      this.response.statusCode(302);
      this.response.putHeader('Location', url);
      this.response.end();
    }

    //Sending the session manager with the request
    request.session = session;

    func(request)
  }
}

customMatcher.get("/login", function(request) {
 // request.response.putHeader("Set-Cookie","MySessionToken");
 // request.response.putHeader("Set-Cookie","MyAuthToken");
  
  templateManager.render_template('login', "",request);
});


customMatcher.post("/login", function(request) {
  var data = new vertx.Buffer();

  request.dataHandler(function(buffer) {
    data.appendBuffer(buffer);
  });

  request.endHandler(function() {

    var params = data.getString(0, data.length());
    params = utils.getUrlParams(params);

    var username = params.uername;
    var password = params.password;

    var templateVars = {};

    queryMongo.authUser(username, password, function(r) {
      
      console.log(JSON.stringify(r));
      if (r.status==="ok") {
        request.session.login(r.result._id, r.result.username,r.result.admin);
        request.redirect("/");
        return 
      }
      else {

        templateVars.errors = "Wrong username or password";
        templateManager.render_template('login', templateVars, request);
      }

      //request.response.end("Returning testpost");
    })
  });
});

customMatcher.get("/logout", function(request) {
  var uname = request.session.loggedIn();

  request.session.logout();

  request.response.end("Logging user out " + JSON.stringify(uname));
});

customMatcher.get('/signup', function(request) {

  templateManager.render_template('signup', {},request);

});

customMatcher.post("/signup", function(request) {
  var data = new vertx.Buffer();

  request.dataHandler(function(buffer) {
    data.appendBuffer(buffer);
  });

  request.endHandler(function() {

    var params = {};

    data = data.getString(0, data.length());

    // data = data.split('&');
    // for(var i = 0; i<data.length;i++) {
    //   datapart = data[i].split('=');
    //   params[datapart[0]] = datapart[1];
    // }

    params = utils.getUrlParams(data);

    var email = params.email;
    var passwd = params.passwd;

    var templateVars = {}
    templateVars.username = email;

    //console.log(data);
    console.log(JSON.stringify(params));

    if(!(email&&passwd)) {
      templateVars.errors = "Both fields are required";
      templateManager.render_template('signup', templateVars,request);
      return;
    }

    queryMongo.newUser(email, passwd, function(r) {
      console.log("Trying to create new user");
      console.log(JSON.stringify(r));
      if (r.status==="ok") {
        templateManager.render_template('landing', {}, request);
      }
      else {
        templateVars.errors = "Username already exists!"
        templateManager.render_template('signup', templateVars, request);
      }
    })


  });
});


customMatcher.get("/a", requireAdmin(function(request){
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
}));

customMatcher.get("/aa", function(request){

  templateManager.render_template('landing', {"name":"Daniel Testing","test":"This is a test"},request);

});


customMatcher.get('/dust', function(request){
  templateManager.loadAll();

  request.response.end("Updated templates");
});

customMatcher.get('/dust1', function(request){
  var eb = vertx.eventBus;
  eb.send("dust.load", {"name":"test", "context": {"x":"world"}}, function(reply){
   request.response.end(JSON.stringify(reply));
  });
});

customMatcher.get('/dust2', function(request){
  var eb = vertx.eventBus;
  eb.send("dust.render", {"name":"test", "context": {"x":"world"}}, function(reply){
   request.response.end(JSON.stringify(reply));
  });
});


customMatcher.get("/experiment", function(request){
  queryMongo.getExperimentList(function(r){

    templateManager.render_template("experimentList", {"experiments":r.results}, request);
  });
});


customMatcher.get("/experiment/new", function(request){
  templateManager.render_template("experimentform", {},request);
});


customMatcher.post("/experiment/new", function(request) {
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

customMatcher.get('/experiment/:id', function(request){
  var id = request.params().get('id');

  //Keeping stuff DRY
  function renderExp(r) {
    var expname = r.result.name;
    var experiment = r.result;
    console.log(JSON.stringify(r));
    templateManager.render_template("experiment", {"exp":experiment},request);
  }

  queryMongo.getExperiment(id,function(r){
    //404 if experiment doesn't exist
    if(!r.result) {
      return request.notfound();
    }

    //If normal user, check if user has filled in something before
    if(!request.session.isAdmin()) {
      var userID = request.session.getPersonToken();
      if(request.session.loggedIn()) {
        userID = request.session.loggedIn().id;
      }
      queryMongo.getUserPosition(userID, id, function(re) {
        console.log("Position = " + re);

        if(re >= 0) {
          request.redirect(request.absoluteURI() + "/phase/" + (re+1));
        } 
        else { renderExp(r); }
      })
    } 

    //Admin, navigation controls dont apply here, just show the view
    else { renderExp(r); }
  });
});


customMatcher.get('/experiment/:id/edit', requireAdmin(function(request){

  var id = request.params().get('id');
  console.log(id);

  queryMongo.getExperiment(id,function(r){
    var experiment = r.result;
    console.log(JSON.stringify(r));
    templateManager.render_template("editexperiment", {"exp":experiment},request);
  });
 
}));


customMatcher.post('/experiment/:id/edit', requireAdmin(function(request){
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
}));

customMatcher.post('/experiment/:id/addform', requireAdmin(function(request){

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
}));

customMatcher.post('/experiment/:id/editformname', requireAdmin(function(request){
  var expId = request.params().get('id');
  var data = new vertx.Buffer();

  request.dataHandler(function(buffer){
    data.appendBuffer(buffer);
  });

  request.endHandler(function() {
    var jsonData = (JSON.parse(data.getString(0, data.length())));
    console.log(JSON.stringify(jsonData));

    var name = jsonData.name;
    var formid = jsonData.id; 

    queryMongo.editExperimentFormName(expId, formid, name, function(r){
      console.log(JSON.stringify(r));
      request.response.end(JSON.stringify(r.result));
    });
  });
}));

customMatcher.post("/experiment/:id/addtest", requireAdmin(function(request) {
  var expId = request.params().get('id');
  var data = new vertx.Buffer();

  request.dataHandler(function(buffer) {
    data.appendBuffer(buffer);
  });

  request.endHandler(function() {

    data = data.getString(0, data.length());
    data = JSON.parse(data);

    queryMongo.addTestToExperiment(expId, data.testId, data.name, function(r) {
      
      var resp = r;
      resp.name = data.name,
      resp.id = data.testId
      console.log(JSON.stringify(resp))
      request.response.end(JSON.stringify(resp));
    })

  });
}));

customMatcher.post('/experiment/:id/deletecomponent', requireAdmin(function(request) {
  var expId = request.params().get('id');
  var data = new vertx.Buffer();

  request.dataHandler(function(buffer){
    data.appendBuffer(buffer);
  });

  request.endHandler(function() {
    var jsonData = (JSON.parse(data.getString(0, data.length())));
    console.log(JSON.stringify(jsonData));

    queryMongo.deleteComponentFromExperiment(expId, jsonData.id, function(r) {
      console.log(JSON.stringify(r));

      request.response.end(JSON.stringify(r.result));
    })

  });
}));

customMatcher.get('/experiment/:id/json', function(request){
  var expId = request.params().get('id');

  queryMongo.getExperiment(expId, function(r){
    request.response.putHeader("Content-Type", "application/json; charset=UTF-8");
    request.response.end(JSON.stringify(r.result));
  });
});


//Shows a specific phase, if phase doesn't exist, assume last phase
//and redirect to some kind of final page
customMatcher.get('/experiment/:id/phase/:phase', function(request) {
  var expID = request.params().get('id');
  var phaseNo = request.params().get('phase');

  queryMongo.getExperiment(expID, function(r) {
    phase = r.result.components[phaseNo];

    //Redirecting to experiment end
    if(phase===undefined) {
        var url = request.absoluteURI().toString();
        var cut = url.indexOf("/phase/");
        console.log(cut)
        url = url.substr(0,cut) + "/end";

        console.log(url);

        request.response.statusCode(302);
        request.response.putHeader('Location', url);
        request.response.end();
        return;
      }

    //Calculating how much of the experiment is completed
    var noOfPhases = r.result.components.length;
    var context = {"completed":(phaseNo+1)/noOfPhases*100, "phasesLeft":phaseNo+1+"/"+noOfPhases}

    //Formphase, rendering form template
    if(phase.type === "form") {
      console.log("Form ");

      queryMongo.getForm(phase.id, function(r2) {
        var form = r2.result.form;
        context.form = form;

        templateManager.render_template("formphase", context, request);

       // request.response.end(form);
      });
    }
    //Testphases, rendering test template
    if(phase.type === "test") {
      console.log("test");

      queryMongo.getTest(phase.id, function(r2) {
        var experimentJs = r2.result.js;
        context.experiment = experimentJs.replace(/(\r\n|\n|\r)/gm,"");

        templateManager.render_template("testphase", context, request);
      })
    }
    
    else {
      console.log(phase.type);
      console.log("Phase type is undefined");
    }
  });
});

customMatcher.get('/experiment/:id/phase/:phase/json', function(request) {
  var expID = request.params().get('id');
  var phaseNo = request.params().get('phase'); 

  queryMongo.getExperiment(expID, function(r) {
    phase = r.result.components[phaseNo];

    queryMongo.getTest(phase.id, function(r2) {

      request.response.end(r2.result.js)
    });
  });

});


//Records data from a certain phase,
customMatcher.post('/experiment/:id/phase/:phase', function(request) {
  var expID = request.params().get('id');
  var phase = request.params().get('phase');

  var userID = request.session.getPersonToken();
  if(request.session.loggedIn()) {
    userID = request.session.loggedIn().id;
  }

  var data = new vertx.Buffer();

  request.dataHandler(function(buffer) {
    data.appendBuffer(buffer);
  });

  request.endHandler(function() {
    var postData = data.getString(0, data.length());
    var postJson = JSON.parse(postData);
    console.log(postData);

    queryMongo.saveFormData(phase, expID, postJson,  userID,function(r){
      console.log(JSON.stringify(r));
      request.response.end("Data \n" + postData);
    });
  });
});

customMatcher.get('/experiment/:id/end', function(request) {
  var expID = request.params().get('id');

  queryMongo.confirmExperimentData(expID, request.session.getPersonToken(), function(r) {
    console.log("confirmed submitted data")
    console.log(JSON.stringify(r));
    templateManager.render_template('end', {},request);
  })


});


customMatcher.get('/experiment/:id/data', requireAdmin(function(request) {
  var expID = request.params().get('id');
  queryMongo.getExperimentFormData(expID, function(r) {
    data = r.results;

    var sep = "; ";

    var phases = 0;

    var fields = [];
    //fields.push("userid");

    var userData = {};

    //finding max phase an
    for(var i in data) {
      var item = data[i];
      phase = parseInt(item.phase)

      if(!("userid" in item)) {
        item.userid = "Missing";
      }

      if ((phase+1) > phases) {
        phases = parseInt(item.phase);
        // console.log(phases);
      }

      if (!fields[phase]) {
        // console.log("newPhase");
        fields[phase] = []

        for (var prop in item) {
          //console.log(prop);
          if(!(prop=="_id"||prop=="phase"||prop=="userid"||prop=="expId")) {
            fields[phase].push(prop.slice(17, prop.length));
          }
        }
      }
      if(!userData[item.userid]) {
        userData[item.userid] = [];
      }
      userData[item.userid][phase] = [];
      for( j in item) {
        if(!(j=="_id"||j=="phase"||j=="userid"||j=="expId")) {
          userData[item.userid][phase].push(item[j]);
        }
      }
    }
    var mergedFields = [];
    mergedFields = (["userid"]).concat(mergedFields.concat.apply(mergedFields, fields));  
    
    var stringFields = mergedFields.join(sep);

    var userFields = ""
    for(id in userData) {
      var mergedUserData = [];
      mergedUserData = ([id]).concat(mergedUserData.concat.apply(mergedUserData, userData[id]));

      userFields += mergedUserData.join(sep);
      userFields += "\n";


    }
    request.response.putHeader("Content-Type", "text/csv; charset=utf-8");
    request.response.putHeader("Content-Disposition", "attachment; filename=questioneerdata.csv");

    request.response.end("\ufeff " + stringFields+"\n"+ userFields);

     // request.response.end(JSON.stringify(fields) + "\n\n\n" +JSON.stringify(userData));
     
     //request.response.end(JSON.stringify(r.results));
  });
}))

customMatcher.get('/test/demo', function(request) {
  var file = 'demo.html';
  request.response.sendFile(utils.file_from_serverdir(file));
});


customMatcher.post('/test/run', function(request) {
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

customMatcher.get('/questionnaire', function(req) {
  var file = http_directory.concat('/questionnaire.html');

  req.response.sendFile(file);
});

customMatcher.get('/questionnaire/guide', function(request) {
  var file = 'qml-guide.html';

  request.response.sendFile(utils.file_from_serverdir(file));
});

customMatcher.post('/questionnaire/render', function(request) {
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

customMatcher.get('/questionnaire/generated/:id', function(request) {
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

customMatcher.get('/questionnaire/mongo/:id', requireAdmin(function(request){
  var id = request.params().get('id');
  queryMongo.getForm(id, function(r){
    //console.log(JSON.stringify(r))
    var form = r.result.form;
    var markup = r.result.markup;
    templateManager.render_template('displayForm', {"form":form,"markup":markup},request);
  });
}));

customMatcher.post('/questionnaire/mongo/:id', requireAdmin(function(request) {
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
}));

customMatcher.get('/questionnaire/mongo/:id/getform', function(request) {
  var id = request.params().get('id');
  queryMongo.getForm(id,function(r) {
    var form = r.result.form;
    form = "<div id='formcol'>".concat(form,"</div>");
    request.response.end(form);
  });
});

customMatcher.post('questionnaire/generated/:id', function(request) {
  console.log(request.method);
});

customMatcher.get('/test', function(request) {
  queryMongo.getTestList(function(r) {
    templateManager.render_template('testlist', {"tests":r.results},request);
  })

});

customMatcher.get('/test/json', function(request) {
  queryMongo.getTestList(function(r) {
    request.response.end(JSON.stringify(r.results))
  })
})


customMatcher.post("/test", requireAdmin(function(request) {
  var data = new vertx.Buffer();

  request.dataHandler(function(buffer) {
    data.appendBuffer(buffer);
  });

  request.endHandler(function() {

    data = data.getString(0, data.length());
    var name = data.split("=")[1];

    queryMongo.saveTest({"name":name}, function(r) {
      console.log(JSON.stringify(r));

      request.response.putHeader("Location", request.absoluteURI + name)
      request.response.end("");
      
    })
  });
}));


customMatcher.get('/test/:id', requireAdmin(function(request) {
  var id = request.params().get('id');
  var code = "sadas";
  queryMongo.getTest(id, function(r) {
    templateManager.render_template('testEditor', {"code":code,"test":r.result}, request);
  })
}));


customMatcher.post("/test/:id", requireAdmin(function(request) {
  var data = new vertx.Buffer();
  var id = request.params().get('id');

  request.dataHandler(function(buffer) {
    data.appendBuffer(buffer);
  });

  request.endHandler(function() {

    data = data.getString(0, data.length());
    var code = JSON.parse(data).code;

    var address = utils.get_address('experiment_language');
    var eb = vertx.eventBus;
    var msg = {
      'code': code
    };



    eb.send(address, msg, function(reply) {
      var response = {};

      var test = {};

      test._id = id;
      test.code = code;

      if (reply.hasOwnProperty('errors') === true) {
        response.errors = reply.errors;

        test.js = "";
        test.compiled = false;
      } else {
        response.code = reply.code;
        test.js = response.code;
        test.compiled = true;
      }

      queryMongo.updateTest(test, function() {
        request.response.putHeader("Content-Type", "application/json; charset=UTF-8");
        request.response.end(JSON.stringify(response));
      });
    });
    //send to compiler -> send to mongo

  });
}));

customMatcher.get('/', function(request) {

  queryMongo.getExperimentList(function(r) {

    templateManager.render_template('landing', {"experiments":r.results,"test":"This is a test"},request);
  });
});

/* This will match static files. ('Static files' are files which 
 * have not been generated programmatically.) */
customMatcher.allWithRegEx('.*\.(html|htm|css|js|png|jpg|jpeg|gif|ico)$', function(req) {
  req.response.sendFile(utils.file_from_serverdir(req.path()));
});

customMatcher.allWithRegEx('.*/', function(req) {
  console.log(req.absoluteURI());
  console.log(req.uri());

  var url = req.uri().substring(0, req.uri().length - 1);

  console.log(url);

  req.response.statusCode(302);
  req.response.putHeader('Location', url);
  req.response.end();
});

customMatcher.noMatch(function(req) {
  req.response.end("404");
});

/* Let this be the last specified match. */
customMatcher.allWithRegEx('.+', function(req) {
  var file = http_directory.concat('/questionnaire.html');

  req.response.sendFile(file);
});

server.requestHandler(routeMatcher).listen(port, host);

function vertxStop() {
  server.close();
}
