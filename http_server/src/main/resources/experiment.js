var vertx = require("vertx");
var console = require('vertx/console');
var utils = require("utils");

var CustomMatcher = require('router');
var router = new CustomMatcher();

var templateManager = require('templateManager');

var experimentModel = require("models/Models").Experiment;
var experimentDAO = require("models/DAObjects").ExperimentDAO;
experimentDAO = new experimentDAO();

var formModel = require("models/Models").Form;


function merge_options(obj1,obj2){
    var obj3 = {};
    for (var attrname in obj1) { obj3[attrname] = obj1[attrname]; }
    for (var attrname in obj2) { obj3[attrname] = obj2[attrname]; }
    return obj3;
}

router.get("/experiment", function(request){
  experimentDAO.list(function(r){

    templateManager.render_template("experimentList", {"experiments":r}, request);
  });
});


router.get("/experiment/new", function(request){
  //templateManager.render_template("experimentform", {},request);
  var sDate = Date.now();
  var eDate = Date.now() + (1000*60*60*24*30);  //30 days in the future

  var expData = {};

  var newExp = new experimentModel();
  newExp.startDate = new Date(sDate);
  newExp.endDate = new Date(eDate);
  newExp.name = "";

  newExp.save(function(r){
      console.log(JSON.stringify(r));

      request.redirect("/experiment/"+newExp._id+"/edit");
      request.response.end();

    });
});

router.get("/experiment/:id/edit", function(request) {
  templateManager.render_template('a_experimentEdit', {}, request);
});

router.post("/experiment/:id/edit", function(request) {
  var id = request.params().get('id');
  var data = new vertx.Buffer();

  request.dataHandler(function(buffer){
    data.appendBuffer(buffer);
  });

  request.endHandler(function() {
    var dataObj = JSON.parse(data.getString(0, data.length()));
    experimentDAO.get(id, function(experiment) {

      console.log(JSON.stringify(experiment));

      experiment = merge_options(experiment, dataObj);

      experiment.save(function() {
        request.response.end(200);
      });
    });
  });
});


router.get("/experiment/:id/json", function(request) {
  var id = request.params().get('id');

  experimentDAO.get(id, function(experiment) {
    var js = experiment.toJson();

    request.response.putHeader("Content-Type", "application/json; charset=UTF-8");
    request.response.end(js);
  });
});

router.post("/experiment/:id/addform", function(request) {
  var id = request.params().get('id');
  var address = utils.get_address('questionnaire_render');

  var msg = {
    'markup': "",
    'action': "save"
  };

  vertx.eventBus.send(address, msg, function(reply) {
    var response = {};
    var id = reply.id;

    var form = new formModel();
    form._id = id;
    form.name = "";
    form.save(function() {
      request.response.putHeader("Content-Type", "application/json; charset=UTF-8");
      request.response.end(form.toJson());
    });
  });
}); 