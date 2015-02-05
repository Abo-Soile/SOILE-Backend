var vertx = require('vertx');
var container = require('vertx/container');
var console = require('vertx/console');

var server = vertx.createHttpServer();

var config = container.config;
var shared_config = config.shared;
var port = config.port;
var host = config.host;

var testImages = config.directory + "/testimages";

//var routeMatcher = new vertx.RouteMatcher();

var sessionMap = vertx.getMap("soile.session.map");

messageDigest = java.security.MessageDigest.getInstance("SHA-256");

var a = new java.lang.String("sdfsdfs");
console.log(a.hashCode());
console.log(JSON.stringify(container.config));

// This function returns a function that calls the requesthandler
// which makes it possible to run arbitrary code before the request
function sessionTest(func) {
  return function(request) {
    // console.log("this should be seen before the request")

    //request.headers().forEach(function(key,value){
      //console.log(key + " - " + value);
    //});

    //console.log("Cookies:: " + request.headers().get("Cookie"));

    request.redirect = function(url) {
      console.log("Redirecting to " + url);
      console.log(this.remoteAddress());

      this.response.statusCode(302);
      this.response.putHeader('Location', url);
      this.response.end();
    };

    request.unauthorized = function() {
      this.response.statusCode(401);

      var context = {};
      context.short = "Not authorized";
      context.long =  "You're not authorized to view this content. Try logging in";

      templateManager.render_template("error", context, this);
      //this.response.end("401, Unauthorized");
    };

    request.notfound = function() {
      this.response.statusCode(404);
      
      var context = {};
      context.short = "404, not found" ;
      context.long =  "The content you're looking for couldn't be found.";

      templateManager.render_template("error", context, this);
    };

    var session = sessionManager.loadManager(request);
    session.setPersonToken();
    request.session = session;

    //Check if a db session exists
    if((!session.loggedIn()) 
        && (session.getSessionCookie()) 
        && request.method()==="GET") {
      console.log("Checking session");
      session.checkSession(function callback(r) {
        //Sending the session manager with the request
        if(r.result) {
          console.log("Logging in from token");
          session.login(r.result._id, r.result.username, r.result.admin, r.result.sessiontoken)
        }
        func(request);
      });
    }
    else {
      console.log("Skipping session check");
      func(request);
    }
  };
}


//Decorator ish function to ensure that the user is admin
function requireAdmin(func) {
  return function(request) {
    console.log("Require Admin running " + request.session.isAdmin());
    if (!request.session.isAdmin()) {
      request.unauthorized();
    }else {
      func(request);
    }
  };
}

function sendEmail(subject, body, address, func) {
  var mailAddress = "soile.my_mailer";

  var mail = {}
  mail.from = "kogni@abo.fi";
  mail.to = address;
  mail.subject = subject;
  mail.body = body;

  vertx.eventBus.send(mailAddress, mail, func);

}

function looksLikeMail(str) {
    var lastAtPos = str.lastIndexOf('@');
    var lastDotPos = str.lastIndexOf('.');
    return (lastAtPos < lastDotPos &&  // @ before last .
      lastAtPos > 0 &&                 // Something before @
      str.indexOf('@@') == -1 &&       // No double @
      lastDotPos > 2 &&                // 3 chars before .com
      (str.length - lastDotPos) > 2);  // domain = min 2 chars
}

function customMatcher() {
  return;
}

customMatcher.prototype = new vertx.RouteMatcher();

//more methods from the routematcher should be implementd as needed.
customMatcher.get = function(pattern, handler) {
  routeMatcher.get(pattern, sessionTest(handler));
};

customMatcher.post = function(pattern, handler) {
  routeMatcher.post(pattern, sessionTest(handler));
};

customMatcher.delete = function(pattern, handler) {
  routeMatcher.delete(pattern, sessionTest(handler));
}

customMatcher.allWithRegEx = function(pattern, handler) {
  routeMatcher.allWithRegEx(pattern, sessionTest(handler));
};

customMatcher.noMatch = function(handler) {
  routeMatcher.noMatch(sessionTest(handler));
};


// Generates  a new customMatcher and sets it to routmatcher
// this matcher is then bound to de server object at the bottom
// of this file. The normal routematcher can also be called if 
// needed.
var routeMatcher = new customMatcher();

var DEBUG = true;   //This variable could stored in configs

// function arrayContains(item, array){
//   return (arrhaystack.indexOf(needle) > -1);
// }

var utils = require("utils");

//var user = require('mongoHandler').user;
//var queryMongo = require('mongoHandler').mongoHandler;

var mongo = require('mongoHandler');
mongo.mongoHandler.init();

var templateManager = require('templateManager');
var mailManager = require('mailManager');

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

var sessionManager = require("sessionManager");

//var sessionManager = require("sessionManager").sessionManager;

//Injects session code that is run before the actual request
//It would probably be best to generalize this abit more to make it extendable
//with other functionality as well

//DEPRECATED, see sessiontest instead
function session(func) {
  console.log("test");
  return function (request) {
    console.log("Before returnfunction");
    // request.headers().forEach(function(key,value){
    //   console.log(key + " - " + value);
    // });

    console.log("Cookies:: " + request.headers().get("Cookie"));

    var session = sessionManager.loadManager(request);
    session.setPersonToken();

    request.prototype.redirect = function(url) {
      console.log("Redirecting to " + url);
      console.log(this.remoteAddress());

      this.response.statusCode(302);
      this.response.putHeader('Location', url);
      this.response.end();
    };

    //Sending the session manager with the request
    request.session = session;

    func(request);
  };
}

customMatcher.get("/login", function(request) {
 // request.response.putHeader("Set-Cookie","MySessionToken");
 // request.response.putHeader("Set-Cookie","MyAuthToken");

  //var previous = utils.getUrlParams(request.query()).url;

  //console.log(previous);

  //Saving refere/previous url to enable redirects
  //console.log(request.headers().get("Referer"));
  var previous = request.headers().get("Referer");
  
  templateManager.render_template('login', {"origin":previous},request);
});


customMatcher.post("/login", function(request) {
  var data = new vertx.Buffer();

  request.dataHandler(function(buffer) {
    data.appendBuffer(buffer);
  });

  request.endHandler(function() {

    var params = data.getString(0, data.length());
    params = utils.getUrlParams(params);

    var username = params.username;
    var password = params.password;
    var remember = params.remember;

    if (remember) {
      remember = true;
    }else {
      remember = false;
    }

    var origin = params.origin;

    var templateVars = {};

    templateVars.origin = decodeURIComponent(origin);

    mongo.user.auth(username, password, remember, function(r) {
      
      console.log(JSON.stringify(r));
      //Status ok, user found
      if (r.status==="ok") {
        request.session.login(r.result._id, r.result.username,r.result.admin, r.token);
        mongo.experiment.updateDataIdentifier(r.result._id, 
          request.session.getPersonToken(), function(s) {

          if(origin){
            return request.redirect(decodeURIComponent(origin));
          }
          return request.redirect("/");
        })
        
      }
      //No user was found, error
      else {

        templateVars.errors = "Wrong username or password";
        console.log(JSON.stringify(templateVars));
        templateManager.render_template('login', templateVars, request);
      }

      //request.response.end("Returning testpost");
    });
  });
});

customMatcher.get("/login/forgotten", function(request) {
    templateManager.render_template("forgotten", {}, request)
});

customMatcher.post("/login/forgotten", function(request) {

  var data = new vertx.Buffer();

  request.dataHandler(function(buffer) {
    data.appendBuffer(buffer);
  });

  request.endHandler(function() {
    var params = data.getString(0, data.length());
    params = utils.getUrlParams(params);

    var username = params.username;

    mongo.user.forgotPassword(username, function(r) {
      console.log(JSON.stringify(r))

      var templateParams = {};
      templateParams.success = true;
      templateParams.email = username;

      var uri = request.absoluteURI() + "/" + r.token;

      //TODO actually send the email
      mailManager.passwordReset(username, uri, function(r) {
        console.log("Reset mail sent to: " + username + " " + JSON.stringify(r));
        templateManager.render_template("forgotten", templateParams, request)
        
      })

    });
  });
});

customMatcher.get("/login/forgotten/:token", function(request) { 
    var token = request.params().get('token');

    mongo.user.getWithToken(token, function(r) {
      console.log(JSON.stringify(r));
      if(!r.result) {
        request.notfound();
      }else {
        templateManager.render_template("resetpassword", {}, request)
      }
    })
});

customMatcher.post("/login/forgotten/:token", function(request) { 
  var data = new vertx.Buffer();
  var token = request.params().get('token');

  request.dataHandler(function(buffer) {
    data.appendBuffer(buffer);
  });

  request.endHandler(function() { 
    var params = data.getString(0, data.length());
    params = utils.getUrlParams(params);

    if (params.password === params.passwordAgain) {
      mongo.user.resetPassword(token, params.password, function(r) {
        console.log(r);
        templateManager.render_template("resetpassword",{"success":true}, request);
      })

    }
    else {
      templateManager.render_template("resetpassword",{"error":"The password didn't match, please try again"}, request);
    }
  });
});

customMatcher.get("/logout", function(request) {
  var uname = request.session.loggedIn();

  request.session.logout();

  //request.response.end("Logging user out " + JSON.stringify(uname));
  request.redirect("/");
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
    var passwdAgain = params.passwdAgain;

    var origin = params.origin

    var templateVars = {};
    templateVars.username = email;
    templateVars.origin = decodeURIComponent(origin);

    //console.log(data);
    console.log(JSON.stringify(params));
    console.log(passwd + "===" + passwdAgain);


    if(!(email && passwd && passwdAgain)) {
      templateVars.registererrors = "All fields are required";
      //templateManager.render_template('signup', templateVars,request);
      templateManager.render_template('login', templateVars,request);

      return;
    }

    if(!looksLikeMail(email)) {
      templateVars.registererrors = "Enter a valid email address";
      templateManager.render_template('login', templateVars,request);
      return;
    }

    if(!(passwd===passwdAgain)) {
      templateVars.registererrors = "Password didn't match";
      //templateManager.render_template('signup', templateVars,request);
      templateManager.render_template('login', templateVars,request);

      return
    }

    mongo.user.new(email, passwd, function(r) {
      console.log("Trying to create new user");
      console.log(JSON.stringify(r));
      if (r.status==="ok") {
        //templateManager.render_template('landing', {}, request);
        console.log(origin);
        request.session.login(r._id, email,false);
        return request.redirect(decodeURIComponent(origin));
      }
      else {
        templateVars.registererrors = "Username already exists!, try logging in";
        //templateManager.render_template('signup', templateVars, request);
        templateManager.render_template('login', templateVars, request);

      }
    });
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
  console.log("Testing sending of emails");

  sendEmail("testmail from aa", "This is a test email", "test@danielwarna.com", function(res) {
    console.log("Email sent");
    console.log(JSON.stringify(res));
    templateManager.render_template('landing', {"name":"Daniel Testing","test":"This is a test"},request);
  })

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

customMatcher.get('/users', requireAdmin(function(request){
  mongo.user.list(true, function(r) {
      var admins = r.results;
      console.log(JSON.stringify(r));
      templateManager.render_template("userList",{"users":admins}, request);
    })
}));

customMatcher.get("/experiment", function(request){
  mongo.experiment.list([], function(r){

    templateManager.render_template("experimentList", {"experiments":r.results}, request);
  });
});


/*
Creates a new empty experiment and redirects the user to the new 
experiments' edit page
*/
customMatcher.get("/experiment/new", function(request){
  //templateManager.render_template("experimentform", {},request);


  var sDate = Date.now()
  var eDate = Date.now() + (1000*60*60*24*14)  //14 days in the future

  var expData = {};

  expData.name =  "";
  expData.startDate = new Date(sDate);
  expData.endDate = new Date(eDate);

  mongo.experiment.save(expData, function(r){
      console.log(JSON.stringify(r));
      var resp = {
        "status":"ok",
        "id":r._id
      };
      request.redirect("/experiment/"+r._id+"/edit");
      request.response.end();

    });
});


customMatcher.post("/experiment/new", function(request) {
  var data = new vertx.Buffer();

  request.dataHandler(function(buffer){
    data.appendBuffer(buffer);
  });

  request.endHandler(function() {

    var jsonData = JSON.parse(data.getString(0, data.length()));
    console.log(data.getString(0, data.length())); 

    var sDate = new Date(jsonData.startDate);
    var eDate = new Date(jsonData.endDate);

    if(jsonData.name === "") {
      jsonData.name = "Unnamed experiment"
    }


    console.log(sDate.toString());

    mongo.experiment.save(jsonData, function(r){
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
    var experiment = r.result;

    //Replacing newlines with html linebreaks when displaying the description
    experiment.description = experiment.description.replace(/(?:\r\n|\r|\n)/g, '<br />');
    //console.log(JSON.stringify(r));
    templateManager.render_template("experiment", {"exp":experiment},request);
  }

  mongo.experiment.get(id,function(r){
    //404 if experiment doesn't exist
    if(!r.result) {
      return request.notfound();
    }

    var exp = r.result;

    //If normal user, check if user has filled in something before
    if(!request.session.isAdmin()) {
      var userID = request.session.getPersonToken();

      if(request.session.loggedIn()) {
        userID = request.session.loggedIn().id;
      }
      //mongo.experiment.userPosition(userID, id, function(re) {
      mongo.experiment.getUserData(userID, id, function(userdata) {
      //console.log("Position = " + re);
        if (userdata) {
          console.log("Userdata exists " + JSON.stringify(userdata))
          // TODO: This one should be changed to > 0
          if(userdata.position > 0) {          
            request.redirect(request.absoluteURI() + "/phase/" + (userdata.position));
          }
          else{
            renderExp(r)
          }
        }
        else { 
          console.log("No userdata");
          var userdata = {}
          // TODO: userposition should be set to 0, instead of -1
          userdata.position = 0;
          userdata.randomorder = false;
          /*for (var i = 0; i < exp.components.length; i++) {
            if (exp.components[i].random) {
              randomize = true
            }
          };*/

          if (exp.israndom) {
            var order = mongo.experiment.generateRandomOrder(exp);
            console.log("Generated random order " + JSON.stringify(order));
            userdata.randomorder = order; 
          }
          mongo.experiment.initUserData(userdata, userID, exp._id, function(r2){
            renderExp(r);
          })
        }
      })
    } 
    //Admin, navigation controls dont apply here, just show the view
    else {
      mongo.experiment.countParticipants(id, function(r2) {
        r.result.participants = r2;
        console.log(JSON.stringify(r));
        renderExp(r); 
      })
    }
  });
});


customMatcher.get('/experiment/:id/edit', requireAdmin(function(request){

  var id = request.params().get('id');
  console.log(id);

  mongo.experiment.get(id,function(r){
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

      var loginRequired = jsonData.loginrequired

      console.log(sDate.toString());

      mongo.experiment.update(jsonData, id,function(r){
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
    mongo.experiment.addForm(expId,id,"Unnamed Form", function(r){
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

    mongo.experiment.editFormName(expId, formid, name, function(r){
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

    if (data.name === "" || data.testId === "") {
      return request.response.end(JSON.stringify({error:"No experiment specified"}))
    }

    mongo.experiment.addTest(expId, data.testId, data.name, function(r) {
      
      var resp = r;
      resp.name = data.name;
      resp.id = data.testId;
     
      console.log(JSON.stringify(resp));
      request.response.end(JSON.stringify(resp));
    });
  });
}));

customMatcher.post("/experiment/:id/randomizeorder", requireAdmin(function(request) {
  var expId = request.params().get('id');
  var data = new vertx.Buffer();

  request.dataHandler(function(buffer) {
    data.appendBuffer(buffer);
  });

  request.endHandler(function() {
    var jsonData = (JSON.parse(data.getString(0, data.length())));

    console.log(JSON.stringify(jsonData));

    mongo.experiment.setRandom(expId, jsonData.index, jsonData.value, function(r) {
      console.log("Setting random, phase: " + jsonData.index + " v:" + jsonData.value);
      request.response.end("Ending");
    })
  });

}))


customMatcher.post('/experiment/:id/deletecomponent', requireAdmin(function(request) {
  var expId = request.params().get('id');
  var data = new vertx.Buffer();

  request.dataHandler(function(buffer){
    data.appendBuffer(buffer);
  });

  request.endHandler(function() {
    var jsonData = (JSON.parse(data.getString(0, data.length())));
    console.log(JSON.stringify(jsonData));

    /*mongo.experiment.deleteComponent(expId, jsonData.id, function(r) {
      console.log(JSON.stringify(r));

      request.response.end(JSON.stringify(r.result));
    });*/

    mongo.experiment.deleteComponentByIndex(expId, jsonData.index, function(r) {
      console.log(JSON.stringify(r));

      request.response.end(JSON.stringify(r.result));
    });

  });
}));

customMatcher.get('/experiment/:id/json', function(request){
  var expId = request.params().get('id');

  mongo.experiment.get(expId, function(r){
    request.response.putHeader("Content-Type", "application/json; charset=UTF-8");
    request.response.end(JSON.stringify(r.result));
  });
});


//Shows a specific phase, if phase doesn't exist, assume last phase
//and redirect to some kind of final page
customMatcher.get('/experiment/:id/phase/:phase', function(request) {
  var expID = request.params().get('id');
  var phaseNo = request.params().get('phase');
  var phase;

  var userID = request.session.getPersonToken();

  mongo.experiment.userPosition(userID, expID, function(userdata) {
    
    //Checking if user is in the wrong phase
    if(userdata.position != phaseNo && (userdata.position > 0)) {
      var reg = /phase\/\d*/;;
      request.redirect(request.absoluteURI().toString().replace(reg, "phase/" + (userdata.position)));
    } 

    else {
      mongo.experiment.get(expID, function(r) {
        var exp  = r.result; 
        phase = exp.components[phaseNo];

        //Redirecting to experiment end
        if(phase===undefined) {
          var url = request.absoluteURI().toString();
          var cut = url.indexOf("/phase/");
          console.log(cut);
          url = url.substr(0,cut) + "/end";

          console.log(url);

          return request.redirect(url);
        }

        if(r.result.loginrequired && !request.session.loggedIn()) {
          var url = "/experiment/"+expID
          return request.redirect(url);
        }

        //Calculating how much of the experiment is completed
        var noOfPhases = parseInt(r.result.components.length);
        phaseNo = parseInt(phaseNo);
        var context = {"completed":(phaseNo+1)/noOfPhases*100, "phasesLeft":phaseNo+1+"/"+noOfPhases};

        if (exp.israndom) {
          console.log("------Translating phase number---------");
          console.log(phaseNo + " -> " + userdata.ranComp);
          phase = exp.components[userdata.randomorder[phaseNo]]; 
        }

        //Formphase, rendering form template
        if(phase.type === "form") {
          console.log("Form ");

          mongo.form.get(phase.id, function(r2) {
            var form = r2.result.form;
            context.form = form;

            templateManager.render_template("formphase", context, request);

           // request.response.end(form);
          });
        }
        //Testphases, rendering test template
        if(phase.type === "test") {
          console.log("test");

          mongo.test.get(phase.id, function(r2) {
            var experiments = r2.result.js;
            context.experiment = experiments.replace(/(\r\n|\n|\r)/gm,"");

            templateManager.render_template("testphase", context, request);
          });
        }
        
        else {
          console.log(phase.type);
          console.log("Phase type is undefined");
        }
      });
    }
  })
});


customMatcher.get('/experiment/:id/phase/:phase/json', function(request) {
  var expID = request.params().get('id');
  var phaseNo = request.params().get('phase'); 
  var phase;

  var userID = request.session.getPersonToken();


  mongo.experiment.userPosition(userID, expID, function(userdata) {
    phaseNo = userdata.position;
    if (userdata.randomorder) {
      phaseNo = userdata.randomorder[userdata.position];
    }
    mongo.experiment.get(expID, function(r) {
      phase = r.result.components[phaseNo];

      mongo.test.get(phase.id, function(r2) {

        request.response.end(r2.result.js);
      });
    });
  })

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

    mongo.experiment.saveData(phase, expID, postJson, userID, function(r){
      console.log(JSON.stringify(r));
      request.response.end("Data \n" + postData);
    });
  });
});

customMatcher.get('/experiment/:id/end', function(request) {
  var expID = request.params().get('id');
  var userID = request.session.getPersonToken();
  if(request.session.loggedIn()) {
    userID = request.session.loggedIn().id;
  }

  mongo.experiment.confirmData(expID, userID, function(r) {
    console.log("confirmed submitted data");
    console.log(JSON.stringify(r));
    templateManager.render_template('end', {},request);
  });

});


//Performs a custom crafted join on gathered data.
customMatcher.get('/experiment/:id/data', requireAdmin(function(request) {
  var expID = request.params().get('id');
  mongo.experiment.formData(expID, function(r) {
		  
	var data = r.results;
    console.log(JSON.stringify(r));

    var sep = "; ";

    var fields = [];
    var userData = {};
	

    //finding max phase an
    var i;
    for(i in data) {
      var item = data[i];
      phase = parseInt(item.phase);

      if(!("userid" in item)) {
        item.userid = "Missing";
      }

      //Writing table headers
      if (!fields[phase]) {
        fields[phase] = [];
        var prop;
        for (prop in item.data) {
          //console.log(prop);
       //   if(!(prop=="_id"||prop=="phase"||prop=="userid"||prop=="expId")) {
            fields[phase].push(prop.slice(17, prop.length));
        //  }
        }
      }
      if(!userData[item.userid]) {
        userData[item.userid] = [];
      }
      //Writing table data for each 
      userData[item.userid][phase] = [];
      var j;
      for(j in item.data) {
        //if(!(j=="_id"||j=="phase"||j=="userid"||j=="expId")) {
          userData[item.userid][phase].push(item.data[j]);
       // }
      }
    }
    
	fields = utils.cleanArray(fields);
	for(var d in userData) {
		userData[d] = utils.cleanArray(userData[d]);
	}
	var mergedFields = [];
    mergedFields = (["userid"]).concat(mergedFields.concat.apply(mergedFields, fields));  

    var stringFields = mergedFields.join(sep);
	
	console.log("\n\n\n" + JSON.stringify(userData));
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
  });
}))


// Does pretty much the same as the form data method, 
// Might generate empty fields when using phase no as array index
customMatcher.get('/experiment/:id/testdata', requireAdmin(function(request) {
  var expID = request.params().get('id');
  console.log("Testing testdata");

  mongo.experiment.testData(expID, function(r) {
    var data = r.results;
    var sep = ";"

	//console.log(JSON.stringify(data));

    var fields = [];
    var userData = {};
    for(i in data) {
      var item = data[i];
      console.log("\n" + i + " Index\n"  + JSON.stringify(item));
	  item.single = item.data.single;
      
	  var phase = parseInt(item.phase);

      if(!("userid" in item)) {
        item.userid = "Missing";
      }

      //Writing headers
      if(!fields[phase]) {

        //indexes before phase will be set to null
        fields[phase] = [];

        var prop;
        for (prop in item.single) {
          fields[phase].push(prop);
        }
      }

      if(!userData[item.userid]) {
        userData[item.userid] = [];
      }

      userData[item.userid][phase] = [];
      var j;
      for(j in item.single) {
        userData[item.userid][phase].push(item.single[j]);
      }
    }
	
	fields = utils.cleanArray(fields);
	for(var d in userData) {
		userData[d] = utils.cleanArray(userData[d]);
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
    request.response.putHeader("Content-Disposition", "attachment; filename=testdata.csv");

    request.response.end("\ufeff " + stringFields+"\n"+ userFields);
  })
 
}));
// /experiment/:id/phase/:phase/rawdata'
customMatcher.get('/experiment/:id/rawdata', requireAdmin(function(request) {
  var expID = request.params().get('id');
  mongo.experiment.testData(expID, function(r) {
    var data = r.results;

    request.response.end(JSON.stringify(data));
  });
}));


// Returns raw tesdata from a testphase as a csv, data is formatted 
// Doesn't format correcly if some fields are missing from the data
customMatcher.get('/experiment/:id/phase/:phase/rawdata', requireAdmin(function(request) {
  var expId = request.params().get('id');
  var phase = request.params().get('phase');
  mongo.experiment.rawTestData(expId, phase, function(r) {
    var data = r.results;
    var sep =";"

    var csvData = "";

    for (var el in data){
      var element = data[el];
      console.log("dataa" + JSON.stringify(element._id))
      //Inserting username
      csvData += "userID:" + sep +  element.userid + sep + "\n";

      //inserting keynames names
      for (var key in element.data.rows[0]) {
        csvData += key + sep;
      }

      csvData += "\n";

      //Inserting values
      for (var r in element.data.rows) {
        var row= element.data.rows[r];
        for (var rowkey in row) {
          csvData += JSON.stringify(row[rowkey]) + sep;
        }
        csvData += "\n";
      }
    }

    request.response.putHeader("Content-Type", "text/csv; charset=utf-8");
    request.response.putHeader("Content-Disposition", "attachment; filename=phase"+phase+"RawData.csv");

    request.response.end("\ufeff " + csvData);
  })


}));

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
  var file = config.directory.concat('/questionnaire.html');

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
  mongo.form.get(id, function(r){
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
  mongo.form.get(id,function(r) {
    var form = r.result.form;
    form = "<div id='formcol'>".concat(form,"</div>");
    request.response.end(form);
  });
});

customMatcher.post('questionnaire/generated/:id', function(request) {
  console.log(request.method);
});

customMatcher.get('/test', function(request) {
  mongo.test.list(function(r) {
    templateManager.render_template('testlist', {"tests":r.results},request);
  })

});

customMatcher.get('/test/json', function(request) {
  mongo.test.list(function(r) {
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
    var params = utils.getUrlParams(data);
    var name = decodeURIComponent(params.name).split("+").join(" ");

    console.log("name1: " + name);

    mongo.test.save({"name":name}, function(r) {
      console.log(JSON.stringify(r));

      var dirName = testImages + "/" + r._id

      vertx.fileSystem.mkDir(dirName, true, function(err, res) {
        console.log(err + "  " + res);
        if (!err) {
          console.log('Directory created ok');
          return request.redirect("/test/"+r._id);
        }
      });

      
    })
  });
}));


customMatcher.get('/test/:id', requireAdmin(function(request) {
  var id = request.params().get('id');
  var code = "sadas";
  var files = [];
  vertx.fileSystem.readDir(testImages + "/" + id, function(err, res) {
    if (!err) {
      //files = res;
       for (var i = 0; i < res.length; i++) { 
          var img = res[i].toString();
          var file = {}
          file.url = img.substring(img.indexOf("testimages"));
          file.name = img.substring(img.lastIndexOf("/")+1);
          files.push(file);
        }
        console.log(JSON.stringify(files));
        console.log("\n\n\n");
    }
    mongo.test.get(id, function(r) {
      templateManager.render_template('testEditor', 
        {"code":code, "test":r.result, "files":files}, request);
    })
  });
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
        response.errors = reply.errors.split("\n");
        console.log(reply.errors);

        test.js = "";
        test.compiled = false;
      } else {
        response.code = reply.code;
        test.js = response.code;
        test.compiled = true;
      }

      mongo.test.update(test, function() {
        request.response.putHeader("Content-Type", "application/json; charset=UTF-8");
        request.response.end(JSON.stringify(response));
      });
    });
    //send to compiler -> send to mongo

  });
}));

customMatcher.post("/test/:id/imageupload", function(request) {

  request.expectMultiPart(true);
  var id = request.params().get('id');

  request.uploadHandler(function(upload) {
      //var path = testImages + id + "/" + upload.filename()
      var fixedFilename = upload.filename()

      //Replacing and removing unwanted characters from filename
      fixedFilename = fixedFilename.replace(/[å+ä]/gi, "a");
      fixedFilename = fixedFilename.replace("ö", "o"); 
      fixedFilename = fixedFilename.replace(/[^a-z0-9+.]/gi, '_').toLowerCase();

      var path = testImages + "/" + id +"/" + fixedFilename;
      //var path = testImages + "/" + id +"/" + upload.filename()
      console.log("Uploading image to "+ path);
      upload.streamToFileSystem(path);
  });

  request.endHandler(function() {

    console.log("Uploading");

    request.response.end(200);
  });
});

customMatcher.delete("/test/:id/imageupload/:imageName", function(request) {
  var id = request.params().get('id');
  var imgName = request.params().get('imageName');

  console.log("DELETING IMAGE");

  request.endHandler(function() {
    var filename = testImages + "/" + id + "/" + imgName;
    vertx.fileSystem.delete(filename, function(err, res) {
      if(!err) {
            request.response.end(200);
          }
      else {
        request.response.end(400);
      }
    })
  });
});

customMatcher.get('/test/:id/imagelist', function(request) {
  var id = request.params().get('id');
  var files = [];

  vertx.fileSystem.readDir(testImages + "/" + id, function(err, res) {
    if (!err) {
      //files = res;
       for (var i = 0; i < res.length; i++) { 
          var img = res[i].toString();
          var file = {}
          file.url = img.substring(img.indexOf("testimages"));
          file.name = img.substring(img.lastIndexOf("/")+1);
          files.push(file);
        }
        var fileJSON = JSON.stringify(files);

        request.response.end(fileJSON);
    }
  });
});

customMatcher.post('/test/:id/editname', requireAdmin(function(request) {
  var id = request.params().get('id');
  var data = new vertx.Buffer();

  request.dataHandler(function(buffer){
    data.appendBuffer(buffer);
  });

  request.endHandler(function() {
    var jsonData = (JSON.parse(data.getString(0, data.length())));

    var name = jsonData.name;

    mongo.test.editName(id, name, function(r){
      console.log(JSON.stringify(r));
      request.response.end(JSON.stringify(r.result));
    });
  });
}));

customMatcher.post('/user', function(request) {
  var data = new vertx.Buffer();

  request.dataHandler(function(buffer) {
    data.appendBuffer(buffer);
  });

  request.endHandler(function() {

    var params = {};

    data = data.getString(0, data.length());
    params = utils.getUrlParams(data);

    var userid = request.session.loggedIn().id
    var firstname  = params.firstname;
    var lastname   = params.lastname;
    var address1   = params.address1;
    var address2   = params.address2;
    var postalcode = params.postalcode;
    var city       = params.city;
    var country    = params.country;

    mongo.user.update(userid, firstname, lastname, address1, address2,
      postalcode, city, country, function(r) {
        console.log(r);
        return request.redirect("/");
      })
  })
});


customMatcher.get('/', function(request) {

  // Admin showing admin controls
  if (request.session.isAdmin()) {
    mongo.experiment.list([], function(r) {
      mongo.test.list(function(s) {
        templateManager.render_template('admin', {"experiments":r.results,"tests":s.results},request);
        //templateManager.render_template('testlist', {"tests":r.results},request);
      });

    });
  }
  else {
    // User logged in showing user controls
    if (request.session.loggedIn()) {
      var userid = request.session.loggedIn().id;
      mongo.user.status(userid, function(r) {
        //console.log(JSON.stringify(r));
        var openExperiments = [];
        for (var i = r.newExps.length - 1; i >= 0; i--) {
          if(r.newExps[i].active) {
            openExperiments.push(r.newExps[i])
          }
        };
        r.newExps = openExperiments;
        console.log("\n\n" + userid);

        mongo.user.get(userid, function(userdetails) {
          r.u = userdetails;
          //console.log(JSON.stringify(r));
          templateManager.render_template('user', r, request);
        })

      })
    }

    // Anonymous user, showing ladning page
    else {
      templateManager.render_template('landing', {} ,request);
    }
  }
});

/*
  Matches static files. Uses the normal routmatcher so that session stuff is 
  ignored when sending static files. 
*/
routeMatcher.allWithRegEx('.*\.(html|htm|css|js|png|jpg|jpeg|gif|ico|md|wof|ttf|svg|woff)$', function(req) {
  req.response.sendFile(utils.file_from_serverdir(req.path()));
});

customMatcher.allWithRegEx('.*/', function(req) {
  console.log(req.absoluteURI());
  console.log(req.uri());

  var url = req.uri().substring(0, req.uri().length - 1);

  console.log(url);

  req.response.statusCode(302);
  req.response.putHeader('Location', url);
  req.response.end()
;});

customMatcher.noMatch(function(request) {
  return request.notfound();
});

/* Let this be the last specified match. */
// customMatcher.allWithRegEx('.+', function(req) {
//   var file = http_directory.concat('/questionnaire.html');

//   req.response.sendFile(file);
// });

server.requestHandler(routeMatcher).listen(port, host);

function vertxStop() {
  server.close();
}
