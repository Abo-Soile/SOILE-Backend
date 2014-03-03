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
      response.reply()
    })
  },

  getTest: function(id, response){
    vertx.eventBus.send(this.mongoAddress, {"action":"findone",
    "collection":"tests","matcher":{"_id":id}}, function(reply) {
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