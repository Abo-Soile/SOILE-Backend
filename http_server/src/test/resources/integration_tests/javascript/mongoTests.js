var vertx = require("vertx")
var container = require("vertx/container");
var vertxTests = require("vertx_tests");
var vassert = require("vertx_assert");

var console = require('vertx/console');

var mongo = require("mongoHandler");

var mongoConfig = {
    "address": "vertx.mongo-persistor",
    "host": "127.0.0.1",
    "port": 27017,
    "db_name": "soileunittest"
  }

console.log("Running mongo tests");

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
