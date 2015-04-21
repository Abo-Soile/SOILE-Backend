var vertx = require("vertx")
var container = require("vertx/container");
var vertxTests = require("vertx_tests");
var vassert = require("vertx_assert");

var console = require('vertx/console');

var mongo = require("mongoHandler");
var async = require("async");

var models = require("Models")
var dao = require('DAObjects')

var userDao = new dao.UserDAO();

var mongoConfig = {
  "address": "vertx.mongo-persistor",
  "host": "127.0.0.1",
  "port": 27017,
  "db_name": "soilemodeltest"
}

function resetMongo(callback) {
  vertx.eventBus.send(mongoConfig.address, {"action": "command",
    "command":
      "{dropDatabase: 1}"}, 
      function(reply) {
        console.log("Dropping database");
        callback(reply);
    })  
}

function testUser() {
  async.waterfall([
    function(callback) {
      /*resetMongo(function(r){
        console.log("Database dropped")
        callback();
      })*/
      console.log("Firest stop")
      callback()
    },
    function newUser(callback) {
      /*mongo.user.new("user1","user1", function(r) {
        console.log("Generating new user");
        callback(null,r)
      })*/
      var u = new models.User({email:"testuser2",testfield:"field"});
      u.delete();
      //u.email = "testemail"
      u.setPassword("testpassword");

      //console.log("\n\n ------------ \n " + u._collection)

      u.save(function(r) {
        console.log("User created")
        console.log(JSON.stringify(u));
        callback(null,u._id);
      })
    },

    function getUser(arg, callback) {
      userDao.get(arg, function(reply) {
        console.log("\n################# got user")
        console.log(JSON.stringify(reply))
        callback();
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
