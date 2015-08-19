var vertx = require("vertx");
var CustomMatcher = require('router');
var console = require('vertx/console');

var templateManager = require('templateManager');
var router = new CustomMatcher();

var trainingModel = require("models/Models").Training;
var trainingDAO = require("models/DAObjects").TrainingDAO;

var TrainingData = require("models/Models").TrainingData;

var formModel = require("models/Models").Form;
trainingDAO = new trainingDAO();

var trainingDataDAO = require("models/DAObjects").TrainingDataDAO;
trainingDataDAO = new trainingDataDAO();

var testDAO = require("models/DAObjects").TestDAO;
var formDAO = require("models/DAObjects").FormDAO;

var moment = require("libs/moment");

/*
Architectural ideas. 

Tränings experimentet sparar:
  pre komponenter
  post komponenter
  training komponenter
  dataumintervall
  
Per användare sparas: 
  Var i testen en person er: pre - träning - post?
  Data för varje fas, samma som i ett vanligt experiment
  Datum intervall för nästa träning

Urlar:
  /training       -  allmän information samt status för var i testet användaren e.
  /training/pre   -  pre
  /training/post  -  post
  /training/task  -  träningsuppgift
  
  Skippa juttun med faser, istället visas bara rätt experiment/form
  kan ju ändu int navigera mellan olika juttun så, och soile sköter 
  ändå om allt redirectande.

  såå flöde /training -> training/pre -> /training/task (repeat) -> training/post -> /training
*/

/*
Overwrites obj1's values with obj2's and adds obj2's if non existent in obj1
*/
function merge_options(obj1,obj2){
    var obj3 = {};
    for (var attrname in obj1) { obj3[attrname] = obj1[attrname]; }
    for (var attrname in obj2) { obj3[attrname] = obj2[attrname]; }
    return obj3;
}

//Handles saving of posted data from tests
function handleResultData(data, datatype, callback) {

}

//Admin view, show list of training experiments
router.get("/training", function(request) {
  console.log("TRAAAAINNNING!!!")

});

//Create a new training task 
router.post("/training", function(request) {

});

//View  training experiment
router.get("/training/:id", function(request) {
  var id = request.params().get('id');
  var userid = request.session.getUserId();

  // Get training
  trainingDAO.get(id, function(training) {

    trainingDataDAO.getOrGenerateGeneral(userid, id, training.controlgroup, function(trainingData) {

      var status = {};
      status.open = false;
      status.state = trainingData.mode;
      status.nextRound = training.nextTask;

      trainingData.nextTask = new Date(trainingData.nextTask);

      var timeString = false;
      if(trainingData.nextTask - Date.now() > 0) {
        timeString = moment(trainingData.nextTask).fromNow();
      }

      status.timeLeft = timeString;

      status.totalTimeLeft = 2;
      status.roundsLeft = training.trainingIteration + "/" + (trainingData.position +1);

      templateManager.render_template('trainingUser', {training:training, status:status}, request);
    });

  });
});


//Save data to the experiment
router.post("/training/:id", function(request) {

});

function getTrainingAndUserData(trainingid, userid, callback) {

  trainingDAO.get(trainingid, function(training) {

    trainingDataDAO.get({userId:userid, trainingId:trainingid, type:"general"}, function(trainingData) {
      var data = {};
      data.training = training;
      data.trainingData = trainingData;

      console.log(JSON.stringify(data));

      callback(data.training, data.trainingData);
    });
  });
}

function renderTrainingPhase(components, position, request, persistantData) {

  var component = components[position];
  var id = component.id;
  var template = "";
  var dao = {};
  var context = {};
  var contextObj = "";
  var childObj = 0;

  var phasesLeft = components.length - position;

  if(component.type === "form") {
    dao =formDAO;
    template = "formphase";
    contextObj = "form";
    childObj = "form";
  }

  if(component.type === "test") {
    dao = testDAO;
    template = "testphase";
    contextObj = "test";
    context.persistantData = persistantData;
  }

  context.completed = ((position+1)/components.length * 100);
  context.phasesLeft = (parseInt(position) + 1) + "/" + components.length;

  dao.get(id, function(phase) {

    context[contextObj] = phase;

    if (childObj !== 0) {
      context[contextObj] = phase[childObj];
    }

    console.log(JSON.stringify(context));

    templateManager.render_template(template, context, request);
  });

}

//Execute current training phase
router.get("/training/:id/execute", function(request) {
  var id = request.params().get('id');
  var userid = request.session.getUserId();

  getTrainingAndUserData(id, userid, function(training, trainingData) {

    if(trainingData.mode === "done") {
      return request.redirect("/training/" + id);
    }

    var modeComponents = training.components[trainingData.mode];
    var positionInMode = trainingData.position;

    var phasesLeft = modeComponents.length - (positionInMode);

    console.log("Executin training");
    console.log("mode = " + trainingData.mode + " position:" + positionInMode);
    console.log("Component:" + JSON.stringify(modeComponents[positionInMode]));

    var nextTaskTime = new Date(trainingData.nextTask);

    //if (phasesLeft == 0) {
    if(Date.now() - nextTaskTime < 0 || trainingData.mode === "done") {
      return request.redirect("/training/" + id);
    }
     else {
      renderTrainingPhase(modeComponents, positionInMode, request, trainingData.persistantData);
    }
  });

});

//Recieve and stora data from training phase
router.post("/training/:id/execute", function(request) {
  var id = request.params().get('id');
  var userid = request.session.getUserId();
  var postData = new vertx.Buffer();

  request.dataHandler(function(buffer){
    postData.appendBuffer(buffer);
  });

  request.endHandler(function() {

    //Figures out the current phase and creates a data object.
    getTrainingAndUserData(id, userid, function(training, generalData) {
      var jsonData = JSON.parse(postData.getString(0, postData.length()));

      //var modeComponents = training.components[generalData.mode];
      //var positionInMode = generalData.position;

      var tData = new TrainingData();
      tData.data = jsonData.exp;
      tData.mode = generalData.mode;
      tData.phase = generalData.position;
      tData.trainingId = id;

      tData.userId = generalData.userId;

      tData.duration = jsonData.duration;
      tData.score = jsonData.score;

      if(generalData.mode === "training") {
        tData.trainingIteration = generalData.trainingIteration;
      }

      console.log(JSON.stringify(jsonData));

      tData.save(function(status) {
        console.log("SAVED TDATA" + JSON.stringify(status));

        var isLastPhase = generalData.isLastPhase(training);
        generalData.completePhase(training);

        generalData.persistantData = merge_options(generalData.persistantData,
                                                   jsonData.persistantData);

        generalData.save(function() {
          
          //TODO: Check if there is any stored score
          if (isLastPhase) {
            request.jsonRedirect("/training/"+id+"/score");
          }
          else {
            request.jsonRedirect("/training/"+id+"/execute");
          }

        });
      });
    
    });
  });
});

//Execute current training phase
/*
router.get("/training/:id/execute/json", function(request) {
  var id = request.params().get('id');
  var userid = request.session.getUserId();

  getTrainingAndUserData(id, userid, function(d) {
    var modeComponents = training.components[trainingData.mode];
    var positionInMode = trainingData.position;

    var phasesLeft = modeComponents.length - positionInMode;

    console.log("Executin training");
    console.log("mode = " + trainingData.mode + " position:" + positionInMode);
    console.log("Component:" + JSON.stringify(modeComponents[positionInMode]));
    if (phasesLeft = 0) {

    } else {
      renderTrainingPhase(modeComponents, positionInMode, request);
    }
  });

});
*/

router.get("/training/:id/score", function(request) {
  var id = request.params().get('id');
  var userid = request.session.getUserId();

  var context = {};

  trainingDataDAO.getScore(id, userid, function(totalScore, scores) {

    console.log(JSON.stringify(scores));
    var scoreContext = {};

    scoreContext.totalScore = totalScore;
    scoreContext.scores = [];

    for (var i = 0; i < scores.length; i++) {
      var s = scores[i];
      scoreContext.scores[i] = {"score":s.score};
      scoreContext.scores[i].subscores = [];
      for(var key in s) {
        if (key !== "score") {
          var subScore = {};
          subScore.name = key;
          subScore.score = s[key];

          scoreContext.scores[i].subscores.push(subScore);
        }
      }
    }

    context.score = scoreContext;

    JSON.stringify(scoreContext);

    templateManager.render_template("endoftrainingphase", context, request);
  });
    //request.response.end("SCORE!!!")


});

//Pre test
router.get("/training/:id/pre", function(request) {

});

//Post test
router.get("/training/:id/post", function(request) {

});

//Repeated training task 
router.get("/training/:id/task", function(request) {

})

router.get("/training/:id/edit", function(request) {

  templateManager.render_template('trainingEdit', {}, request);

})

//JSON structure
/*
{
  pre:[],
  post[],
  training[]
}
*/

router.post("/training/:id/edit", function(request) {
  var id = request.params().get('id');
  var data = new vertx.Buffer();

  request.dataHandler(function(buffer){
    data.appendBuffer(buffer);
  });

  request.endHandler(function() {
    var dataObj = JSON.parse(data.getString(0, data.length()));
    trainingDAO.get(id, function(training) {

      console.log(JSON.stringify(training));

      training = merge_options(training, dataObj);

      training.save(function() {
        request.response.end(200);
      });
    });
  });
});

router.post("/training/:id/addform", function(request) {
  var id = request.params().get('id');

  var newForm = new formModel();

  newForm.saveAndRender(function(status) {

      trainingDAO.get(id, function(training) {

        training.components.training.push({"type":"form", "name": "Unamed Form", "id":newForm.id});

        training.save(function(stat) {
          request.response.putHeader("Content-Type", "application/json; charset=UTF-8");
          request.response.end(JSON.stringify({"id":newForm.id}));
        });
      });
  });
});


router.get("/training/:id/json", function(request) {
  var id = request.params().get('id');

  trainingDAO.get(id, function(training) {
    var js = training.toJson();

    request.response.putHeader("Content-Type", "application/json; charset=UTF-8");
    request.response.end(js);
  });
});