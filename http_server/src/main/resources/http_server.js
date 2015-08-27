var vertx = require('vertx');
var container = require('vertx/container');
var console = require('vertx/console');

var server = vertx.createHttpServer();

var config = container.config;
var shared_config = config.shared;
var port = config.port;
var host = config.host;

var testImages = config.directory + "/testimages";

var babyparser = require("libs/babyparse");
//var routeMatcher = new vertx.RouteMatcher();

var sessionMap = vertx.getMap("soile.session.map");

var logger = container.logger;

var messageDigest = java.security.MessageDigest.getInstance("SHA-256");

var testDAO = require("models/DAObjects").TestDAO;

var a = new java.lang.String("sdfsdfs");
console.log(a.hashCode());
console.log(JSON.stringify(container.config));

var utils = require("utils");

var requireAdmin = utils.requireAdmin;

function sendEmail(subject, body, address, func) {
  var mailAddress = "soile.my_mailer";

  var mail = {};
  mail.from = "kogni@abo.fi";
  mail.to = address;
  mail.subject = subject;
  mail.body = body;

  vertx.eventBus.send(mailAddress, mail, func);

}

/*
  Checking if a string seems to be an email address
*/
function looksLikeMail(str) {
    var lastAtPos = str.lastIndexOf('@');
    var lastDotPos = str.lastIndexOf('.');
    return (lastAtPos < lastDotPos &&  // @ before last .
      lastAtPos > 0 &&                 // Something before @
      str.indexOf('@@') === -1 &&       // No double @
      lastDotPos > 2 &&                // 3 chars before .com
      (str.length - lastDotPos) > 2);  // domain = min 2 chars
}

// Generates  a new customMatcher and sets it to routmatcher
// this matcher is then bound to de server object at the bottom
// of this file. The normal routematcher can also be called if 
// needed.
//var routeMatcher = new CustomMatcher();


var customMatcher = require('router')();

// TODO: Load this from config
var DEBUG = true;   //This variable could stored in configs

var mongo = require('mongoHandler');
mongo.mongoHandler.init();

var templateManager = require('templateManager');
var mailManager = require('mailManager');

//Ugly hack to make sure that the template module is online before loading
//Base templates
var timerID = vertx.setTimer(3000, function() {
  console.log("\n ------Loading  templates------");
 // templateManager.load_template("header");
 // templateManager.load_template("footer");
  templateManager.loadAll();
});


var sessionManager = require("sessionManager");

//var sessionManager = require("sessionManager").sessionManager;

require('testroute.js');
require('training.js');

require('experiment.js');
require('experiment_old.js');
require('questionnaire.js');
require('test.js');

customMatcher.get("/login", function(request) {
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
        });
        
      }
      //No user was found, error
      else {
        templateVars.errors = "Wrong username or password";
        console.log(JSON.stringify(templateVars));
        templateManager.render_template('login', templateVars, request);
      }
    });
  });
});


customMatcher.get("/login/forgotten", function(request) {
    templateManager.render_template("forgotten", {}, request);
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
      console.log(JSON.stringify(r));

      var templateParams = {};
      templateParams.success = true;
      templateParams.email = username;

      var uri = request.absoluteURI() + "/" + r.token;

      //TODO: actually send the email
      mailManager.passwordReset(username, uri, function(r) {
        console.log("Reset mail sent to: " + username + " " + JSON.stringify(r));
        templateManager.render_template("forgotten", templateParams, request);
        
      });

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
        templateManager.render_template("resetpassword", {}, request);
      }
    });
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
      });

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
    params = utils.getUrlParams(data);

    var email = params.email;
    var passwd = params.passwd;
    var passwdAgain = params.passwdAgain;

    var origin = params.origin;

    var templateVars = {};
    templateVars.username = email;
    templateVars.origin = decodeURIComponent(origin);

    console.log(JSON.stringify(params));
    console.log(passwd + "===" + passwdAgain);


    if(!(email && passwd && passwdAgain)) {
      templateVars.registererrors = "All fields are required";
      templateManager.render_template('login', templateVars,request);

      return;
    }

    if(!looksLikeMail(email)) {
      templateVars.registererrors = "Enter a valid email address";
      templateManager.render_template('login', templateVars,request);
      return;
    }

    if((passwd !== passwdAgain)) {
      templateVars.registererrors = "Password didn't match";
      //templateManager.render_template('signup', templateVars,request);
      templateManager.render_template('login', templateVars,request);

      return;
    }

    mongo.user.new(email, passwd, function(r) {
      console.log("Trying to create new user");
      console.log(JSON.stringify(r));
      if (r.status==="ok") {
        //templateManager.render_template('landing', {}, request);
        console.log(origin);
        request.session.login(r._id, email,false);
        if(origin){
          return request.redirect(decodeURIComponent(origin));
        }
        return request.redirect('/');
      }
      else {
        templateVars.registererrors = "Username already exists!, try logging in";
        //templateManager.render_template('signup', templateVars, request);
        templateManager.render_template('login', templateVars, request);

      }
    });
  });
});


customMatcher.get('/users', requireAdmin(function(request){
  mongo.user.list(true, function(r) {
      var admins = r.results;
      console.log(JSON.stringify(r));
      templateManager.render_template("userList",{"users":admins}, request);
    });
}));


/*
customMatcher.get('/questionnaire', function(req) {
  var file = config.directory.concat('/questionnaire.html');

  req.response.sendFile(file);
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


customMatcher.post('questionnaire/generated/:id', function(request) {
  console.log(request.method);
});
*/


customMatcher.post('/user', function(request) {
  var data = new vertx.Buffer();

  request.dataHandler(function(buffer) {
    data.appendBuffer(buffer);
  });

  request.endHandler(function() {

    var params = {};

    data = data.getString(0, data.length());
    params = utils.getUrlParams(data);

    var userid = request.session.loggedIn().id;
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
      }
    );
  });
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
            openExperiments.push(r.newExps[i]);
          }
        }
        r.newExps = openExperiments;
        console.log("\n\n" + userid);

        mongo.user.get(userid, function(userdetails) {
          r.u = userdetails;
          //console.log(JSON.stringify(r));
          templateManager.render_template('user', r, request);
        });
      });
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
customMatcher.routeMatcher.allWithRegEx('.*\.(html|htm|css|js|png|jpg|jpeg|gif|ico|md|wof|ttf|svg|woff)$', function(request) {
  //logHttp(request);
  request.response.sendFile(utils.file_from_serverdir(request.path()));
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

server.requestHandler(customMatcher.routeMatcher).listen(port, host);

function vertxStop() {
  server.close();
}
