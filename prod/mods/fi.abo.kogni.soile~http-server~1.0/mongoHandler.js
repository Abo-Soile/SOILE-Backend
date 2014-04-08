var vertx = require('vertx');
var console = require('vertx/console');

function _hashPassword(password) {

  var messageDigest = java.security.MessageDigest.getInstance("SHA-256");
  var jpass = new java.lang.String(password);

  var bytes = messageDigest.digest(jpass.getBytes());

  var hexString = java.math.BigInteger(1, bytes).toString(16);

  console.log(hexString);

  return hexString;
}

var mongoHandler = {
  mongoAddress: "vertx.mongo-persistor",
  init: function(){
    this.setIndexes();
    this.ensureAdmin();
  },

  getExperiment: function(id, response) {
    vertx.eventBus.send("vertx.mongo-persistor",{"action":"findone", 
   "collection":"experiment","matcher":{"_id":id}},function(reply){
      response(reply);
    });
  },

  getExperimentFormData: function(id, response) {
    vertx.eventBus.send(this.mongoAddress, {"action":"find",
    "collection":"formdata","matcher":{"expId":id}}, function(reply) {
      response(reply);
    })
  }
  ,

  addFormToExperiment: function(expid,formid, name,response) {
    vertx.eventBus.send(this.mongoAddress, {
      "action":"update",
      "collection":"experiment",
      "criteria":{
        "_id":expid
      },
      "objNew": {
        "$push":{
          "components":{
            "id":formid,
            "name":name, 
            "type":"form"
          }
        }
      }
    }, function(reply){
      console.log(JSON.stringify(reply))
      response(reply);

    })
  },

  addTestToExperiment: function(expid,testid, name,response) {
    vertx.eventBus.send(this.mongoAddress, {
      "action":"update",
      "collection":"experiment",
      "criteria":{
        "_id":expid
      },
      "objNew": {
        "$push":{
          "components":{
            "id":testid,
            "name":name, 
            "type":"test"
          }
        }
      }
    }, function(reply){
      console.log(JSON.stringify(reply))
      response(reply);

    })
  },
/*
  Mongo example that should work

db.experiment.update({_id:"c2aa8664-05b7-4870-a6bc-68450951b345",
"components.id":"59cecd81aca2c289942422d904ef495dfc21a6a3"},
{$set:{"components.$.name":"MY new name"}})

*/

  editExperimentFormName: function(expid, formid, name, response) {

    var query = {
      "action":"update",
      "collection":"experiment",
      "criteria":{
        "_id":expid,
        "components.id":formid
      },
      "objNew":{"$set":{"components.$.name":name}}
      }
    //var command = "db.experiment.update({'_id':'"+expid+"','components.id':'"+formid+"'},{$set:{'components.$.name':'"+name+"''}})";
    // console.log("\n"+command+"\n");
    vertx.eventBus.send(this.mongoAddress, query, function(reply){
      response(reply);
    })
  },


// http://stackoverflow.com/questions/4588303/in-mongodb-how-do-you-remove-an-array-element-by-its-index
// The above method could also be used 
  deleteComponentFromExperiment: function(expid, compid, response) {

    var query =  {
      "action":"update",
      "collection":"experiment",
      "criteria":{
        "_id":expid,
        "components.id":compid
      },
      "objNew":{"$pull":{"components":{"id":compid}}}
    };
    

    vertx.eventBus.send(this.mongoAddress, query, function(reply) {
      response(reply);
    });
  },

  getExperimentList: function(response) {
    vertx.eventBus.send("vertx.mongo-persistor",{"action":"find",
    "collection":"experiment"},function(reply){
      response(reply);
    })
  },

  
  saveExperiment: function(exp,response){
    vertx.eventBus.send(this.mongoAddress, {"action":"save", 
      "collection":"experiment", "document":exp}, function(reply){
        response(reply);
      })
  },

  updateExperiment: function(exp, id, response){
    vertx.eventBus.send(this.mongoAddress, {"action":"update", 
      "collection":"experiment", "criteria":{"_id":id},
      "objNew":{"$set":exp}}, function(reply){
        response(reply);
      })
  },

  //Saves a form, does 
  saveForm: function(name, form, id, response) {
    vertx.eventBus.send("vertx.mongo-persistor",{"action":"save",
      "collection":"forms","document":{"form":form}}, function(reply){
        response(reply)
      })
  },

  getForm: function(id, response){
    vertx.eventBus.send("vertx.mongo-persistor", {"action":"findone",
    "collection":"forms","matcher":{"_id":id}}, function(reply) {
      response(reply);
    })
  },

  saveTest: function(test,response) {
    vertx.eventBus.send(this.mongoAddress, {"action":"save",
    "collection":"tests","document":test}, function(reply) {
      response(reply)
    })
  },
  updateTest: function(test,response) {
    vertx.eventBus.send(this.mongoAddress, {"action":"update",
    "collection":"tests", "criteria":{"_id":test._id},
    "objNew":{"$set":{
        "code": test.code,
        "js": test.js,
        "compiled":test.compiled
      }
    }}, function(reply) {
      response(reply)
    })
  }
  ,

  getTest: function(id, response){
    vertx.eventBus.send(this.mongoAddress, {"action":"findone",
    "collection":"tests","matcher":{"_id":id}}, function(reply) {
      response(reply);
    });
  },

  getTestList: function(response){
    vertx.eventBus.send(this.mongoAddress, {"action":"find",
    "collection":"tests"}, function(reply) {
      response(reply);
    });
  },

  saveFormData: function(phase, experimentid ,data, userid,response) {
    var doc = data;
    data.phase = phase;
    data.expId = experimentid;
    data.userid = userid
    vertx.eventBus.send(this.mongoAddress, {"action":"save",
    "collection":"formdata", "document":doc}, function(reply) {
      response(reply);
    })
  },

  getUserPosition: function(userid, experimentid, response) {

    vertx.eventBus.send(this.mongoAddress, {
      "action":"find",
      "collection":"formdata",
      "matcher":{
        "userid":userid,
        "expId":experimentid
      },
      "sort":{"phase":-1},
      "limit":1},
      function(reply) {
        console.log(JSON.stringify(reply));
        phase = reply.results[0].phase;
        if(phase) {
          response(phase)
        }else {
          response(-1);
        }

      })
  },

  confirmExperimentData: function(expId, userid, response) {

    vertx.eventBus.send(this.mongoAddress, {"action":"update",
    "collection":"formdata", "criteria":{"expId":expId, "userid":userid}, 
    "objNew":{"$set":{
        "confirmed":true
      }},
    "multi":true
    }, function(reply) {
      response(reply);
    })
  },

  authUser: function(username, password, response) {

    var pass = _hashPassword(password);

    vertx.eventBus.send(this.mongoAddress, {"action":"findone",
    "collection":"users", "matcher":{"username":username, "password":pass}},
    function(reply) {

      console.log("Finding user");
      console.log(JSON.stringify(reply));
      if(reply.result==null) {
        reply.status="notfound";
      }
      
      response(reply);
    })
  },

  newUser: function(username, password, response) {

    var pass = _hashPassword(password);

    vertx.eventBus.send(this.mongoAddress, {"action": "save",
    "collection":"users", "document":{"username":username, "password":pass, "admin":false}},
     function(reply) {
      response(reply);
     })
  },

  //Function that sets all indexes at startup
  setIndexes: function() {
    vertx.eventBus.send(this.mongoAddress, {"action": "command",
    "command":
      "{eval: 'function() {db.users.ensureIndex({username:1}, {unique: true});}', args: []}" }, 
      
      function(reply) {
        console.log("Setting user index");
        console.log(JSON.stringify(reply));
      })
  },

  ensureAdmin: function() {

    var pass = _hashPassword("admin")
    vertx.eventBus.send(this.mongoAddress, {"action":"save",
    "collection":"users", "document":{"_id": 1,
                                      "username":"admin",
                                      "password": pass,
                                      "admin":true }},
    function(reply) {
      console.log("Generated admin");
    });
  }

  // _hashPassword: function(password) {
  //   var messageDigest = java.security.MessageDigest.getInstance("SHA-256");
  //   var jpass = new java.lang.String(password);

  //   var bytes = messageDigest.digest(jpass.getBytes());

  //   console.log(bytes);
  // }
};

module.exports = mongoHandler;