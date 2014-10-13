var vertx = require("vertx");
var console = require('vertx/console');
var mongo = require("mongoHandler");
var utils = require("utils");

var sessionMap = vertx.getMap("soile.session.map");

var sessionManager =  {

  cookies: null,
  request: null,

  loadManager: function(request) {
    this.cookies = request.headers().get("Cookie");
    this.request = request;

    return this;
  },

  createCookie: function(name, value, days) {
    var expires = "";

    if(days) {
      var date = new Date();
      date.setTime(date.getTime()+(days*24*60*60*1000));
      expires = "; expires="+date.toGMTString();
    }

    return name+"="+value+expires+"; path=/";
  },

  readCookie: function(name) {
    var nameEQ = name + "=";
    var i;
    
    //Dont do anything if no cookies exist
    if(!this.cookies) {
      return 0; 
    }
    
    var ca = this.cookies.split(';');

    for(i=0;i < ca.length;i++) {
      var c = ca[i];
      while (c.charAt(0)==' ') {c = c.substring(1,c.length);}
      if (c.indexOf(nameEQ) == 0) {return c.substring(nameEQ.length,c.length);}
    }
    return 0;
  },

  eraseCookie: function(name) {
    var c = this.createCookie(name,"", -1);
    this.request.response.headers().add("Set-Cookie", c);
    //this.request.response.headers().set("Set-Cookie", c);
  },

  setPersonToken: function() {
    //console.log(this.readCookie("PersonToken"));
    if(!this.readCookie("PersonToken")) {
      var c = this.createCookie("PersonToken", utils.getRandomInt(0, 10000000000000000), 900);
      this.request.response.headers().add("Set-Cookie",c);
    }
  },

  getPersonToken: function() {
    return this.readCookie("PersonToken");
  },

  setSessionCookie: function(key) {
    var c = this.createCookie("Session", key, 1);
    this.request.response.headers().add("Set-Cookie", c);
    //this.request.response.headers().set("Set-Cookie", c);
  },

  getSessionCookie: function() {
    return this.readCookie("Session");
  },


  login: function(id,username, admin, token) {
      console.log("----Logging in-----");
      //console.log(JSON.stringify(r));
      var sessionKey;

      if(token) {
        sessionKey = token;
      }
      else {
        sessionKey = java.util.UUID.randomUUID().toString();
      }

      console.log(this.getPersonToken());
      this.setSessionCookie(sessionKey);

      //Setting a new persontoken to avoid problems with clashing
      //tokens, doesn't work yet, only one cookie is set for some
      //reason
      this.eraseCookie("PersonToken");
      this.setPersonToken();

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

      this.eraseCookie("PersonToken");
      this.setSessionCookie("");

      //console.log(JSON.stringify(this.request.response.headers()));

    } else {
      console.log("there was no data");
    }   
  },

  checkSession: function(callback) {
    var session = this.getSessionCookie();

    mongo.user.fromSession(session, function(r) {
      console.log("---------Checking session--------");
      console.log(JSON.stringify(r));
      callback(r);
    });
  }

};

module.exports = sessionManager;