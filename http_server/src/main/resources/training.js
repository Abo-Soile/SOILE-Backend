var vertx = require("vertx");
var CustomMatcher = require('router');
var console = require('vertx/console');

var templateManager = require('templateManager');
var customMatcher = new CustomMatcher();

var trainingModel = require("models/Models").Training;
var trainingDAO = require("models/DAObjects").TrainingDAO;

var formModel = require("models/Models").Form;
trainingDAO = new trainingDAO();

var trainingDataDAO = require("models/DAObjects").TrainingDataDAO;
trainingDataDAO = new trainingDataDAO();
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
customMatcher.get("/training", function(request) {
  console.log("TRAAAAINNNING!!!")

});

//Create a new training task 
customMatcher.post("/training", function(request) {

});

//View  training experiment
customMatcher.get("/training/:id", function(request) {
  var id = request.params().get('id');
  var userid = request.session.getUserId();

  // Get training
  trainingDAO.get(id, function(trainingObject) {

    trainingDataDAO.getOrGenerateGeneral(userid, id,function(training) {
      templateManager.render_template('trainingUser', {training:trainingObject}, request);
    });

  });
});



//Save data to the experiment
customMatcher.post("/training/:id", function(request) {

});

//Pre test
customMatcher.get("/training/:id/pre", function(request) {

});

//Post test
customMatcher.get("/training/:id/post", function(request) {

});

//Repeated training task 
customMatcher.get("/training/:id/task", function(request) {

})

customMatcher.get("/training/:id/edit", function(request) {

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

customMatcher.post("/training/:id/edit", function(request) {
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

customMatcher.post("/training/:id/addform", function(request) {
  var id = request.params().get('id');

  var newForm = new formModel();

  newForm.saveAndRender(function(status) {

      trainingDAO.get(id, function(training) {

        training.components.training.push({"type":"form", "name": "Unamed Form", "_id":newForm.id});

        training.save(function(stat) {
          request.response.putHeader("Content-Type", "application/json; charset=UTF-8");
          request.response.end(JSON.stringify({"id":newForm.id}));
        });
      });
  });
});


customMatcher.get("/training/:id/json", function(request) {
  var id = request.params().get('id');

  trainingDAO.get(id, function(training) {
    var js = training.toJson();

    request.response.putHeader("Content-Type", "application/json; charset=UTF-8");
    request.response.end(js);
  });
});