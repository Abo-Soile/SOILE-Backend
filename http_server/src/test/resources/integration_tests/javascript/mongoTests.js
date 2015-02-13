var vertx = require("vertx")
var container = require("vertx/container");
var vertxTests = require("vertx_tests");
var vassert = require("vertx_assert");

var console = require('vertx/console');

var mongo = require("mongoHandler");
  
var async = require("async");


var mongoConfig = {
  "address": "vertx.mongo-persistor",
  "host": "127.0.0.1",
  "port": 27017,
  "db_name": "soileunittest"
}

console.log("Running mongo tests");

function resetMongo(callback) {
  vertx.eventBus.send(mongoConfig.address, {"action": "command",
    "command":
      "{dropDatabase: 1}"}, 
      function(reply) {
        console.log("Dropping database");
        callback(reply);
    })
}

function testSimple() {
  vassert.assertEquals('test', 'test');
  vassert.testComplete();
}

function testMongo() {
  vertx.eventBus.send(mongoConfig.address, {
  "action": "save",
  "collection": "testCol",
  "document": {"test":"test1"}
  }, function(r) {
    vassert.assertEquals(r.status, "ok");
    vassert.testComplete();
  })
}

function testInit() {
  mongo.mongoHandler.init();

  mongo.user.get(1, function(reply) {
    var user = reply;
    console.log(JSON.stringify(reply))
    vassert.assertEquals(user.username, "admin");
    vassert.assertEquals(user._id, 1, 0.0002);
    vassert.testComplete();
  })
}


function testUser() {
  async.waterfall([
    function(callback) {
      resetMongo(function(r){
        console.log("Database dropped")
        callback();
      })
    },
    function newUser(callback) {
      mongo.user.new("user1","user1", function(r) {
        console.log("Generating new user");
        callback(null,r)
      })
    },
    function checkNewUser(arg1, callback) {
      console.log("Checking new user");
      mongo.user.get(arg1._id, function(user) {
        vassert.assertEquals(user.username, "user1")
        console.log(user.username);
        callback(null, user)
      })
    },
    function setLoginToken(user, callback) {
      mongo.user.auth("user1", "user1", true, function(r) {
        console.log(JSON.stringify(r))
        callback(null, user, r.token);
      })
    },
    function checkToken(user, token, callback) {
      mongo.user.getWithToken(token, function(r){
        //vassert.assertEquals(user.username, r.username);
        console.log("Got user: " + user.username + " with token: " + token);
        callback()
      })
    }
  ], function end(err,result) {
    console.log("Err: " + JSON.stringify(err));
    vassert.assertEquals(typeof err, 'undefined');
    console.log("DONE");
    vassert.testComplete();

  })
}


var script = this
container.deployModule("io.vertx~mod-mongo-persistor~2.1.0", mongoConfig, function(err, deplId) {
  if(err != null) {
    err.printStackTrace();
    console.log("Mongo deployment failed");
  }else 
  {
    console.log("Mongo deployment succeeded");
    vertxTests.startTests(script);
  }
});
