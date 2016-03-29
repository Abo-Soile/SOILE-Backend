/*
  This controller contains admin releted funcions. 
*/

var vertx = require('vertx');
var container = require('vertx/container');
var console = require('vertx/console');
var CustomMatcher = require('router');

var templateManager = require('templateManager');
var router = new CustomMatcher();

var utils = require("utils");

var requireAdmin = require('middleware').requireAdmin;
var requireLogin = require('middleware').requireLogin;
var userDAO = require("models/DAObjects").UserDAO;


router.get("/admin", requireAdmin,function(request){
  request.response.end("ADMIN");
});


/*Lists users*/
router.get("/admin/user", requireAdmin,function(request){
  templateManager.render_template("userList",{}, request);
});


router.get("/admin/user/current", requireLogin,function(request){
  var user = request.session.currentUser;

  request.response.putHeader("Content-Type", "application/json; charset=UTF-8");
  request.response.end(JSON.stringify(user));
});

/*JSON list of admin users*/
router.get("/admin/user/json", requireAdmin,function(request){
  userDAO.list({role:{$ne: "user"}},function(users) {
    request.response.putHeader("Content-Type", "application/json; charset=UTF-8");
    request.response.end(JSON.stringify(users));
  });
});

/*JSON list of editor users, without the currently logged in user*/
router.get("/admin/user/json/editor/filter", requireLogin, function(request) {

  var userId = request.session.currentUser._id;
  userDAO.list({role:"editor","_id":{"$ne":userId}},function(users) {
    request.response.putHeader("Content-Type", "application/json; charset=UTF-8");
    request.response.end(JSON.stringify(users));
  });
});

/*Specific user*/
router.get("/admin/user/:id", requireAdmin,function(request){
  var id = request.params().get('id');

  userDAO.get(id, function(user) {
    request.response.end(JSON.stringify(user));
  });
});


/*Specific user*/
router.post("/admin/user/:id", requireAdmin,function(request){
  var id = request.params().get('id');
  var data = new vertx.Buffer();

  request.dataHandler(function(buffer) {
    data.appendBuffer(buffer);
  });

  request.endHandler(function() {

    data = JSON.parse(data);
    console.log(JSON.stringify(data));

    delete data._collection;
    delete data._mongoAddress;

    userDAO.get(id, function(user) {

      user = utils.merge_options(user, data);

      if (user.forgottenPasswordToken === true) {
        user.generatePasswordResetToken();
      }

      if (user.forgottenPasswordToken === false) {
        user.deletePasswordResetToken();
      }

      user.save(function() {
        request.response.end(JSON.stringify(user));
      });
    });
  });
});