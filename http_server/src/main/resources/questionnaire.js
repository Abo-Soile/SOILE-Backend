var vertx = require("vertx");
var CustomMatcher = require('router');
var console = require('vertx/console');

var templateManager = require('templateManager');
var router = new CustomMatcher();

var utils = require("utils");
var mongo = require('mongoHandler');

var requireAdmin = utils.requireAdmin;


router.get('/questionnaire/mongo/:id', requireAdmin(function(request){
  var id = request.params().get('id');
  mongo.form.get(id, function(r){
    //console.log(JSON.stringify(r))
    var form = r.result.form;
    var markup = r.result.markup;
    templateManager.render_template('displayForm', {"form":form,"markup":markup},request);
  });
}));


router.post('/questionnaire/mongo/:id', requireAdmin(function(request) {
  var postdata = new vertx.Buffer();
  var id = request.params().get("id");

  request.dataHandler(function(data) {
    postdata.appendBuffer(data);
  });

  request.endHandler(function() {
    var markup = postdata.getString(0, postdata.length());

    var address = utils.get_address("questionnaire_render");

    var message = {
      "markup": markup,
      "action": "save",
      "id": id
    };

    vertx.eventBus.send(address, message, function(reply) {
      //console.log(JSON.stringify(reply));
      var response = {};
      if (reply.hasOwnProperty('error') === true) {
        response.error = reply.error;
      } else {
        response = {
          "test":"testresponse",
          "data": reply.form
          };
      }

      request.response.putHeader("Content-Type", "application/json; charset=UTF-8");
      request.response.end(JSON.stringify(response));
    });
  });
}));


router.get('/questionnaire/mongo/:id/getform', function(request) {
  var id = request.params().get('id');
  mongo.form.get(id,function(r) {
    var form = r.result.form;
    form = "<div id='formcol'>".concat(form,"</div>");
    request.response.end(form);
  });
});