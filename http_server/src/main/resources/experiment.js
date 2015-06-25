var vertx = require("vertx");
var console = require('vertx/console');

var CustomMatcher = require('router');
var customMatcher = new CustomMatcher();

var templateManager = require('templateManager');

var experimentModel = require("models/Models").Experiment;
var experimentDAO = require("models/DAObjects").ExperimentDAO;
experimentDAO = new experimentDAO();


function merge_options(obj1,obj2){
    var obj3 = {};
    for (var attrname in obj1) { obj3[attrname] = obj1[attrname]; }
    for (var attrname in obj2) { obj3[attrname] = obj2[attrname]; }
    return obj3;
}

customMatcher.get("/a_experiment/:id/edit", function(request) {
  templateManager.render_template('a_experimentEdit', {}, request);
});

customMatcher.post("/a_experiment/:id/edit", function(request) {
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


customMatcher.get("/a_experiment/:id/json", function(request) {
  var id = request.params().get('id');

  experimentDAO.get(id, function(experiment) {
    var js = experiment.toJson();

    request.response.putHeader("Content-Type", "application/json; charset=UTF-8");
    request.response.end(js);
  })
})