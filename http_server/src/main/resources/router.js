var vertx = require('vertx');
var container = require('vertx/container');
var console = require('vertx/console');

var templateManager = require('templateManager');
var sessionManager = require("sessionManager");

var logger = container.logger;


var router = new vertx.RouteMatcher();

function logHttp(request) {
  var method = request.method();
  var url = request.absoluteURI();
  var remoteAddress = request.remoteAddress().getHostString();
  var userAgent = request.headers().get("User-Agent");

  logger.info("HTTP " + method + "--" + remoteAddress + "  " + url + " Agent:" + userAgent);
}

//
// Adds some usefull functions to the request object
function extendRequest(request, func) {
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
  if((!session.loggedIn()) && 
      (session.getSessionCookie()) && 
      request.method()==="GET") {
    console.log("Checking session");
    session.checkSession(function callback(r) {
      //Sending the session manager with the request
      if(r.result) {
        console.log("Logging in from token");
        session.login(r.result._id, r.result.username, r.result.admin, r.result.sessiontoken);
      }
      func(request);
    });
  }
  else {
    console.log("Skipping session check");
    func(request);
  }

  logHttp(request);
}


function CustomMatcher() {
  this.routeMatcher = router;
  return;
}


// Handles arguments sent to the router to preserv backwards
// compatibility
CustomMatcher.prototype.handleArgs = function(arg) {
  var middleware = [];
  var handler = "";

  if(arg.length === 2) {
      handler = arg[1];
  }

  if(arg.length === 3) {
    middleware = arg[1];
    handler = arg[2];
  }

  return {h:handler, m:middleware};
};


// 
// Runs middleware and 
CustomMatcher.prototype.handleRequest = function(callback, middleware) {
  return function(request) {
    
    request = extendRequest(request, function(req) {

      for (var i = 0; i < middleware.length; i++) {
        req = middleware[i](req);
      }
           
      callback(req); 
    });

  }; 
};

//More methods from the routematcher should be implementd as needed.
CustomMatcher.prototype.get = function(pattern, handler) {
  var arg = this.handleArgs(arguments);
  this.routeMatcher.get(pattern, this.handleRequest(arg.h, arg.m));
  //routeMatcher.get(pattern, sessionTest(handler));
};

CustomMatcher.prototype.post = function(pattern, handler) {
  var arg = this.handleArgs(arguments);
  this.routeMatcher.post(pattern, this.handleRequest(arg.h, arg.m));

  //this.routeMatcher.post(pattern, sessionTest(handler));
};

CustomMatcher.prototype.delete = function(pattern, handler) {
  var arg = this.handleArgs(arguments);
  this.routeMatcher.delete(pattern, this.handleRequest(arg.h, arg.m));

  //this.routeMatcher.delete(pattern, sessionTest(handler));
};

CustomMatcher.prototype.allWithRegEx = function(pattern, handler) {
  var arg = this.handleArgs(arguments);
  this.routeMatcher.allWithRegEx(pattern, this.handleRequest(arg.h, arg.m));

  //this.routeMatcher.allWithRegEx(pattern, sessionTest(handler));
};

CustomMatcher.prototype.noMatch = function(handler) {
  //var arg = this.handleArgs(arguments);
  this.routeMatcher.noMatch(this.handleRequest(handler, []));
  //this.routeMatcher.noMatch(sessionTest(handler));
};


module.exports = CustomMatcher;