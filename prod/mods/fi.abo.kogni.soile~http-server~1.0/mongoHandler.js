var vertx = require('vertx');
var console = require('vertx/console');

var mongoHandler = {
  mongoAddress: "vertx.mongo-persistor",
  test: function(){
    console.log("This is a test");
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

  saveFormData: function(phase, experimentid ,data, response) {
    var doc = data;
    data.phase = phase;
    data.expId = experimentid;
    vertx.eventBus.send(this.mongoAddress, {"action":"save",
    "collection":"formdata", "document":doc}, function(reply) {
      response(reply);
    })
  }
};

module.exports = mongoHandler;