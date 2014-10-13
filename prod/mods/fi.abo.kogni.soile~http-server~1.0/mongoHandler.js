var vertx = require('vertx');
var console = require('vertx/console');

//TODO
//Load this from config
var mongoAddress = "vertx.mongo-persistor"

//Password hashing using SHA-256
function _hashPassword(password) {

  var messageDigest = java.security.MessageDigest.getInstance("SHA-256");
  var jpass = new java.lang.String(password);

  var bytes = messageDigest.digest(jpass.getBytes());

  var hexString = java.math.BigInteger(1, bytes).toString(16);

  console.log(hexString);

  return hexString;
}

var currentDate = new Date();
var millisecondsPerDay = 1000*3600*24

/*
Comparing start and end dates to calculate if an experiment 
should be active or not */
function _isActive(experiment) {
  sDate = new Date(experiment.startDate);
  eDate = new Date(experiment.endDate);

  if((sDate < currentDate)&&(currentDate<eDate)) {
    experiment.active = true;
    experiment.timedata = Math.ceil((eDate - currentDate)/millisecondsPerDay);
  }
  else{
    experiment.active = false;

    if(sDate > currentDate) {
      experiment.timedata = Math.ceil((sDate - currentDate)/millisecondsPerDay);
    }
  }

  console.log("IS ACTIVE RUNNING");
  // Experiment is inactive if no components exits
  if(!experiment.hasOwnProperty("components")) {
    console.log("components doesn't exist");
    experiment.active = false;
  }
  else {
    if (experiment.components.length == 0) {
          console.log("components is empty");

      experiment.active = false;
    }
  }

  return experiment;
}

var mongoHandler = {
  mongoAddress: "vertx.mongo-persistor",
  init: function(){
    this.setIndexes();
    this.ensureAdmin();
  },

  //Function that sets all indexes at startup
  setIndexes: function() {
    vertx.eventBus.send(mongoAddress, {"action": "command",
    "command":
      "{eval: 'function() {db.users.ensureIndex({username:1}, {unique: true});}', args: []}" }, 
      
      function(reply) {
        console.log("Setting user index");
        console.log(JSON.stringify(reply));
      })
  },

  ensureAdmin: function() {

    var pass = _hashPassword("admin")
    vertx.eventBus.send(mongoAddress, {"action":"save",
    "collection":"users", "document":{"_id": 1,
                                      "username":"admin",
                                      "password": pass,
                                      "admin":true }},
    function(reply) {
      console.log("Generated admin");
    });
  }
};

var user = {
  new: function(username, password, response) {

    var pass = _hashPassword(password);

    vertx.eventBus.send(mongoAddress, {"action": "save",
    "collection":"users", "document":{"username":username, "password":pass, "admin":false}},
     function(reply) {
      response(reply);
     })
  },

  get: function(userid, response) {
    vertx.eventBus.send(mongoAddress, {"action":"findone",
      "collection":"users","matcher":{"_id":userid}},
      function(reply) {
        console.log(JSON.stringify(reply));
        response(reply.result);
      });
  },

  getWithToken: function(token, response) {
    vertx.eventBus.send(mongoAddress, {"action":"findone",
      "collection":"users", "matcher":{"forgottenPasswordToken":token}},
      function(reply) {
        response(reply)
      });
  },

  update: function(userid, firstname, lastname, address1,
                      address2,postalcode, city, country, response) {
    vertx.eventBus.send(mongoAddress, {"action":"update",
        "collection":"users", "criteria": {"_id":userid},
        "objNew":{"$set":{
          "firstname": firstname,
          "lastname": lastname,
          "address1":address1,
          "address2": address2,
          "postalcode": postalcode,
          "city": city,
          "country": country
        }
      }
    },function(reply) {
      response(reply);
    });
  },

  auth: function(username, password, remember,response) {

    var pass = _hashPassword(password);
    var token = java.util.UUID.randomUUID().toString();

    vertx.eventBus.send(mongoAddress, {"action":"findone",
    "collection":"users", "matcher":{"username":username, "password":pass}},
    function(reply) {

      console.log("Finding user");
      console.log(JSON.stringify(reply));

      //No user found, incorrect credentials
      if(reply.result==null) {
        reply.status="notfound";
        reply.token = false;
        response(reply);
      } 

      //Found a user
      else {
        //Generating and storing a sessioncookie in the db
        if(remember){
          console.log("Logging in with remember me");
          reply.token = token;

          vertx.eventBus.send(mongoAddress, {"action":"update",
            "collection":"users",
            "criteria": {
              "_id":reply.result._id
            },
            "objNew": {
              "$set": {
                "sessiontoken":token
              }
            }
          }, function(replyNested) {
            console.log(JSON.stringify(replyNested));
            response(reply);
          })
        } 
        else {
          response(reply);
        }
      }
    })
  },

  /*Generates a token that can be used to access the password reset page*/
  forgotPassword: function(username, response) {
    var token = java.util.UUID.randomUUID().toString();

    vertx.eventBus.send(mongoAddress, {"action":"update",
      "collection": "users",
      "criteria": {"username":username},
       "objNew":{"$set":{
        "forgottenPasswordToken":token
      }},
      "multi":false
    }, function(reply) {
      console.log("Setting forgott password token for user: " + username + "to: " + token);
      reply.token = token
      response(reply)
    })
  },

  resetPassword: function(token, password,response) {
    vertx.eventBus.send(mongoAddress, {"action":"update",
      "collection":"users",
      "criteria":{"forgottenPasswordToken":token},
      "objNew":{"$set":{
        "forgottenPasswordToken":"",
        "password":_hashPassword(password)
      }},
      "multi":false
    }, function(reply) {
      console.log("Setting new password using reset token");
      response(reply);
    })
  },

  fromSession: function(session, response) {

    vertx.eventBus.send(mongoAddress, {"action":"findone",
      "collection":"users",
      "matcher": {"sessiontoken":session}},
      function(reply) {
        response(reply);
      }
    );
  },

  //Userid, completed:true/false
  _getCompleteOrIncompleteExperiments: function(userID, completed,response) {
    //TestData
    console.log("LOADING EXP STATES : " + completed);
    vertx.eventBus.send(mongoAddress, {"action":"find",
      "collection":"testdata",
      "matcher": {"userid":userID,
                  "confirmed": completed,
                  "phase": "0" }},
      function(replyTest) {
        //Formdata
        vertx.eventBus.send(mongoAddress, {"action":"find",
          "collection":"formdata",
          "matcher": {"userid":userID,
                      "confirmed": completed,
                      "phase": "0" }},
        function(replyForm) {
          var expIDs = []
          for(var i = 0; i<replyForm.results.length; i++) {
            expIDs.push(replyForm.results[i].expId)
            console.log("Pushing formid: " + replyForm.results[i].expId + " i: " + i)
          }

          for(var i = 0; i<replyTest.results.length; i++) {
            expIDs.push(replyTest.results[i].expId)
            console.log("Pushing formid: " + replyTest.results[i].expId + " i: " + i)
          }
          console.log(JSON.stringify(expIDs));

          vertx.eventBus.send(mongoAddress, {"action":"find",
            "collection":"experiment",
            "matcher": {"_id": {"$in":expIDs}}
            }, function(exps) {
              exps.list = expIDs;
              response(exps);
            })
          }
        );
      }
    );
  },

  //Returns a list of all completed and incompleted experiments for the current user. And a
  //array with their ID's
  _experimentStatus: function(userID, res) {
    //Incomplete experiments
    user._getCompleteOrIncompleteExperiments(userID, false,function (incompleted) {

      //Completed experiments
      user._getCompleteOrIncompleteExperiments(userID, true, function (completed) {
       
        var idList = incompleted.list.concat(completed.list);
        res({completed:   completed.results,
             incompleted: incompleted.results, 
             expList:     idList
           });
      })
    }) 
  },

  status: function(userID, response) {

    // vertx.eventBus.send(mongoAddress, {"action":"find",
    // "collection":"users",
    // "matcher": {"username":{"$in":["danno","admin","wqq"]}}
    // }, function(exps) {
    //   console.log(JSON.stringify(exps));
    // })


    this._experimentStatus(userID, function(r) {
      //console.log(JSON.stringify(r));
      Experiment.list(r.expList, function(r2) {

        response({newExps:r2.results, complete: r.completed, incomplete:r.incompleted});
      });
    }); 
  },
}

var Experiment = {

  get: function(id, response) {
    currentDate = new Date();
    vertx.eventBus.send("vertx.mongo-persistor",{"action":"findone", 
   "collection":"experiment","matcher":{"_id":id}},function(reply){

      if(reply.result) {/*
        sDate = new Date(reply.result.startDate);
        eDate = new Date(reply.result.endDate);

        if((sDate < currentDate)&& (currentDate < eDate)) {
          reply.result.active = true;
        } else { reply.result.active = false}*/

        reply.result = _isActive(reply.result);
      }

      

      response(reply);

    });
  },

  phaseCount: function(id, collection,response) {
    //{ distinct: "orders", key: "item.sku" }
    var formCommand = "{distinct:'formdata', key:'phase', query: {expId:'"+id+"'}}";
    var testCommand = "{distinct:'formdata', key:'phase', query: {expId:'"+id+"'}}";
    console.log(formCommand);
    if (collection==="form") {
      vertx.eventBus.send(mongoAddress, {"action":"command",
        "command":formCommand}, function(reply) {
          response(reply);
        }
      );
    }

    else if (collection==="test") {
      vertx.eventBus.send(mongoAddress, {"action":"command",
        "command":testCommand}, function(reply) {
          response(reply);
        }
      );
    }
    else {
      return 0;
    }
  },

  saveData: function(phase, experimentid ,data, userid,response) {
    var doc = {};
    doc.phase = phase;
    doc.expId = experimentid;
    doc.userid = userid
    doc.confirmed = false;
    doc.data = data;
    
  this.get(experimentid, function(r) {
      var type = r.result.components[phase].type;
      doc.type = type;

      console.log(mongoAddress);

      if(type === "form"){
        vertx.eventBus.send(mongoAddress, {"action":"save",
        "collection":"formdata", "document":doc}, function(reply) {
          response(reply);
        });
      }
      if(type==="test") {
        vertx.eventBus.send(mongoAddress, {"action":"save",
        "collection":"testdata", "document":doc}, function(reply) {
          response(reply);
        });
      }
    });
  },

  // Setting a confirmed flag on submitted data. 
  // This is run when an user successfully reaches the end
  // of an experiment.
  confirmData: function(expId, userid, response) {

    //Confirming testdata.
    vertx.eventBus.send(mongoAddress, {"action":"update",
    "collection":"testdata", "criteria":{"expId":expId, "userid":userid}, 
    "objNew":{"$set":{
        "confirmed":true
      }},
    "multi":true
    }, function(reply) {
      console.log("confirming testdata");
      console.log(JSON.stringify(reply));
    });

    //Confirming formdata
    vertx.eventBus.send(mongoAddress, {"action":"update",
    "collection":"formdata", "criteria":{"expId":expId, "userid":userid}, 
    "objNew":{"$set":{
        "confirmed":true
      }},
    "multi":true
    }, function(reply) {
      response(reply);
    })
  },

  formData: function(id, response) {
    vertx.eventBus.send(mongoAddress, {"action":"find",
    "collection":"formdata",
    "matcher":{"expId":id, "confirmed":true},
    "keys": {"confirmed":0}},  // Projection
     function(reply) {
      Experiment.phaseCount(id, "form", function(phases) {
        console.log(JSON.stringify(reply))
        console.log(JSON.stringify(phases));
        reply.phases = phases.result.values;
        response(reply);
      })
    })
  },

  testData: function(id, response) {
    vertx.eventBus.send(mongoAddress, {"action":"find", 
      "collection":"testdata",
      "matcher": {"expId":id, "confirmed":true},
      "keys": {"confirmed": 0}},   // Projection
      function(reply) {
        response(reply);
      }
    )
  },

  rawTestData: function(expId, phase, response) {
    vertx.eventBus.send(mongoAddress, {
        "action":"find",
        "collection":"testdata",
        "matcher":{"expId":expId, "phase":phase},
        "keys": {"data":1, "userid":1}},
      function(reply) {
        response(reply);
      }
    )
  },

  addForm: function(expid,formid, name,response) {
    vertx.eventBus.send(mongoAddress, {
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

  addTest: function(expid,testid, name,response) {
    vertx.eventBus.send(mongoAddress, {
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

  editFormName: function(expid, formid, name, response) {

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
    vertx.eventBus.send(mongoAddress, query, function(reply){
      response(reply);
    })
  },

  // http://stackoverflow.com/questions/4588303/in-mongodb-how-do-you-remove-an-array-element-by-its-index
// The above method could also be used 
  deleteComponent: function(expid, compid, response) {

    var query =  {
      "action":"update",
      "collection":"experiment",
      "criteria":{
        "_id":expid,
        "components.id":compid
      },
      "objNew":{"$pull":{"components":{"id":compid}}}
    };
    

    vertx.eventBus.send(mongoAddress, query, function(reply) {
      response(reply);
    });
  },

  deleteComponentByIndex: function(expid, index, response) {

    var comp = {}
    comp["components."+index] = 1;
    console.log(comp);
    var query1 =  {
      "action":"update",
      "collection":"experiment",
      "criteria":{
        "_id":expid
      },
      "objNew":{"$unset":comp}
    };

    var query2 = {
      "action":"update",
      "collection":"experiment",
      "criteria":{
        "_id":expid
      },
      "objNew":{"$pull":{"components": null}}
    };

    console.log("Deleting by component index");

    vertx.eventBus.send(mongoAddress, query1, function(reply1) {
      console.log(JSON.stringify(reply1));
      vertx.eventBus.send(mongoAddress, query2, function(reply2) {
        console.log(JSON.stringify(reply2));
        response(reply2);
       });
    });
  },

  //Returns all active experiments not in the ignore list
  list: function(ignore, response) {
    vertx.eventBus.send("vertx.mongo-persistor",{"action":"find",
    "collection":"experiment",
    "matcher": {"_id": {"$nin":ignore}}},
    function(reply){
      if(reply.results) {
        for(var i =0; i<reply.results.length;i++) {
          reply.results[i] = _isActive(reply.results[i]);
        }
      }
      response(reply);
    })
  },

  save: function(exp,response){
    exp.deleted = false;
    vertx.eventBus.send(mongoAddress, {"action":"save", 
      "collection":"experiment", "document":exp}, function(reply){
        response(reply);
      })
  },

  update: function(exp, id, response){
    vertx.eventBus.send(mongoAddress, {"action":"update", 
      "collection":"experiment", "criteria":{"_id":id},
      "objNew":{"$set":exp}}, function(reply){
        response(reply);
      })
  },


  // Returns the users current position in the experiment,
  userPosition: function(userid, experimentid, response) {

    vertx.eventBus.send(mongoAddress, {
      "action":"find",
      "collection":"formdata",
      "matcher":{
        "userid":userid,
        "expId":experimentid
      },
      "sort":{"phase":-1},
      "limit":1},
      function userFormData(replyForm) {

        vertx.eventBus.send(mongoAddress, {
          "action":"find",
          "collection":"testdata",
          "matcher":{
            "userid":userid,
            "expId":experimentid
          },
          "sort":{"phase":-1},
          "limit":1},
          function userTestData(replyTest) {

            var formPhase = -1;
            var testPhase = -1;

            if(replyForm.number == 1) {
              formPhase = (parseInt(replyForm.results[0].phase));
            }
            if(replyTest.number == 1) {
              testPhase = parseInt(replyTest.results[0].phase);
            }
              response(Math.max(formPhase, testPhase));
          }
        )
    });
  },

   //Refreshes the userid on submitted data, useable when
  //a user logs in or registers in the middle of an experiment
  updateDataIdentifier: function(userid, personToken,response) {

    vertx.eventBus.send(mongoAddress, {"action":"update",
      "collection":"formdata",
      "criteria": {
        "userid":personToken,
        "confirmed":false
      },
      "objNew": {
        "$set":{
          "userid":userid
        }
      },
      "multi": true
    },
    function(reply) {
      console.log(reply);
      response(reply);
    })
  }

  //end
}

//Refactoring Done
var Test = {

  get: function(id, response){
    vertx.eventBus.send(mongoAddress, {"action":"findone",
    "collection":"tests","matcher":{"_id":id}}, function(reply) {
      response(reply);
    });
  },

  list: function(response){
    vertx.eventBus.send(mongoAddress, {"action":"find",
    "collection":"tests"}, function(reply) {
      response(reply);
    });
  },

  save: function(test,response) {
    test.compiled = false;
    test.deleted = false;
    if(test.name==="") {
      test.name = "Unnamed";
    }
    vertx.eventBus.send(mongoAddress, {"action":"save",
    "collection":"tests","document":test}, function(reply) {
      response(reply)
    })
  },

  update: function(test,response) {
    vertx.eventBus.send(mongoAddress, {"action":"update",
    "collection":"tests", "criteria":{"_id":test._id},
    "objNew":{"$set":{
        "code": test.code,
        "js": test.js,
        "compiled":test.compiled
      }
    }}, function(reply) {
      response(reply)
    })
  },

}

//Refactoring Done
var Form = {

  //Saves a form, does 
  save: function(name, form, id, response) {
    vertx.eventBus.send("vertx.mongo-persistor",{"action":"save",
      "collection":"forms","document":{"form":form}}, function(reply){
        response(reply)
      })
  },

  get: function(id, response){
    vertx.eventBus.send("vertx.mongo-persistor", {"action":"findone",
    "collection":"forms","matcher":{"_id":id}}, function(reply) {
      response(reply);
    })
  }

}

module.exports.mongoHandler = mongoHandler;
module.exports.user = user;
module.exports.test = Test;
module.exports.form = Form;
module.exports.experiment = Experiment;