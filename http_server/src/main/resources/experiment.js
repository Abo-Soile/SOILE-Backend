var vertx = require("vertx");
var console = require('vertx/console');
var utils = require("utils");

var CustomMatcher = require('router');
var router = new CustomMatcher();

var templateManager = require('templateManager');

var experimentModel = require("models/Models").Experiment;
var experimentDAO = require("models/DAObjects").ExperimentDAO;

var dataDAO = require("models/DAObjects").DataDAO;

var formModel = require("models/Models").Form;
var dataModel = require("models/Models").Data;

var formDAO = require("models/DAObjects").FormDAO;
var testDAO = require("models/DAObjects").TestDAO;

var requireAdmin = require('middleware').requireAdmin;

var bowser = require("node_modules/bowser/bowser");
//var lodash = require("node_modules/lodash");

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


router.get("/experiment/new", requireAdmin,function(request){
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


//Shortned url
router.get("/e/:name",function(request){
  var name = request.params().get('name');

  experimentDAO.get({"shortname":name}, function(exp) {
    if(exp != "") {
      return request.redirect("/experiment/" + exp._id);
    }
    else {
      return request.notfound();
    }
  });
});


router.get('/experiment/:id', function(request){
  var id = request.params().get('id');
  var userAgent = request.headers().get("User-Agent");

  //Keeping stuff DRY
  function renderExp(exp, admin) {
    var experiment = exp;
    var hidelogin = false;

    if(typeof experiment.hidelogin !== undefined) {
      if(experiment.hidelogin){
        hidelogin = true;
      }
    }

    //Replacing newlines with html linebreaks when displaying the description
    if(typeof experiment.description !== 'undefined') {
      experiment.description = experiment.description.replace(/(?:\r\n|\r|\n)/g, '<br />');
    }
    //console.log(JSON.stringify(r));
    if (admin) {
      experimentDAO.countParticipants(id, function(count) {
        experiment.participants = count;
        console.log(JSON.stringify(count));
  
        templateManager.render_template("experimentAdmin", {"exp":experiment, "hideLogin":hidelogin},request);
      });
    } else{

      res = bowser._detect(userAgent);
      var blockUa = res.tablet||res.mobile;

      if (experiment.allowMobile) {
        blockUa = false;
      }

      templateManager.render_template("experiment", {"exp":experiment, "hideLogin":hidelogin, "blockUa":blockUa},request);
    }
  }

  experimentDAO.get(id, function(exp) {
    //404 if experiment doesn't exist
    if(exp === "") {
      return request.notfound();
    }

    //If normal user, check if user has filled in something before
    if(!request.session.isAdmin()) {
      var userID = request.session.getPersonToken();

      if(request.session.loggedIn()) {
        userID = request.session.loggedIn().id;
      }

      /*
      Checking for userdata and generating it when needed.
      */
      dataDAO.getOrGenerateGeneral(userID, exp, request, function(userdata) {
        if(userdata.position > 0) {          
            request.redirect(request.absoluteURI() + "/phase/" + (userdata.position));
          }
        else {
          renderExp(exp, false);
        }
      });
    }
    //Admin, navigation controls dont apply here, just show the view
    else {
      renderExp(exp, true); 
    }
  });
});

router.get('/experiment/:id/phase/:phase', function(request) {
  var expID = request.params().get('id');
  var phaseNo = request.params().get('phase');
  var phase;

  var userID = request.session.getPersonToken();

  dataDAO.get({"userid":userID, "expId": expID, "type":"general"}, function(userdata) {
    var reg = /phase\/\d*/;

    //Checking if user has visited the landing page
    if(userdata === "") {
      console.log("No userdata, redirecting ");
      return request.redirect(request.absoluteURI().toString().replace(reg,""));
    }

    //Checking if user is in the wrong phase
    if(userdata.position != phaseNo) {
      console.log("Wrong position, redirecting to phase " + userdata.position);
      if (userdata.position == 0) {
        return request.redirect(request.absoluteURI().toString().replace(reg,""));
      }
      return request.redirect(request.absoluteURI().toString().replace(reg, "phase/" + (userdata.position)));
    } 

    else {
      experimentDAO.get(expID, function(exp) {
        phase = exp.components[phaseNo];

        //Redirecting to experiment end
        if(phase===undefined) {
          var url = request.absoluteURI().toString();
          var cut = url.indexOf("/phase/");
          url = url.substr(0,cut) + "/end";

          return request.redirect(url);
        }

        if(exp.loginrequired && !request.session.loggedIn()) {
          var url = "/experiment/"+expID;
          return request.redirect(url);
        }

        //Calculating how much of the experiment is completed
        var noOfPhases = parseInt(exp.components.length);
        phaseNo = parseInt(phaseNo);
        var context = {"completed":(phaseNo+1)/noOfPhases*100, "phasesLeft":phaseNo+1+"/"+noOfPhases};


        if (typeof exp.hidelogin !== 'undefined') {
          context.hideLogin = exp.hidelogin;
        }

        if (exp.israndom) {
          console.log("------Translating phase number---------");
          console.log(phaseNo + " -> " + userdata.randomorder[phaseNo]);
          phase = exp.components[userdata.randomorder[phaseNo]]; 
        }

        //Formphase, rendering form template
        if(phase.type === "form") {
          console.log("Form ");

          if (exp.submitbutton) {
            context.submitbutton = exp.submitbutton;
          }

          formDAO.get(phase.id, function(form) {
            context.form = form.form;

            templateManager.render_template("formphase", context, request);
          });
        }
        //Testphases, rendering test template
        if(phase.type === "test") {
          console.log("test");

          testDAO.get(phase.id, function(experiment) {
            context.experiment = experiment.js.replace(/(\r\n|\n|\r)/gm,"");

            templateManager.render_template("testphase", context, request);
          });
        }
        
        else {
          console.log(phase.type);
          console.log("Phase type is undefined");
        }
      });
    }
  });
});


/*
/ Saves data from a certain phase, while also checking that the phase hasn't been 
/
*/
router.post('/experiment/:id/phase/:phase', function(request) {
  var expID = request.params().get('id');
  var phase = request.params().get('phase');

  var userID = request.session.getPersonToken();
  if(request.session.loggedIn()) {
    userID = request.session.loggedIn().id;
  }

  var data = new vertx.Buffer();

  request.dataHandler(function(buffer) {
    data.appendBuffer(buffer);
  });

  request.endHandler(function() {
    var postData = data.getString(0, data.length());
    var postJson = JSON.parse(postData);

    var expData = postJson.exp;
    var duration = postJson.duration;
    var score = postJson.score;

    var clientStartTime = postJson.clienttime;
    var timezone = postJson.timezone;

    var dataObj = new dataModel();

    dataObj.phase = parseInt(phase);
    dataObj.expId = expID;
    dataObj.userid = userID;
    dataObj.duration = duration;
    dataObj.score = score;

    dataObj.clientstarttime = clientStartTime;
    dataObj.timezone = timezone;

    dataObj.data = expData;

    experimentDAO.completePhase(dataObj, expID,function(r){
      //console.log(JSON.stringify(r));
      request.response.end(200);
    });
  });
});


router.get("/experiment/:id/edit", requireAdmin,function(request) {
  templateManager.render_template('a_experimentEdit', {}, request);
});

router.post("/experiment/:id/edit", requireAdmin,function(request) {
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

router.post("/experiment/:id/addform", requireAdmin,function(request) {
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