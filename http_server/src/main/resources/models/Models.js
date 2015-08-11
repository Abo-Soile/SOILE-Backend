/*
Masterplan, laga ett bas object för enskilda object sparade i databasen.
Olika object kan sedan extenda detta och implementera sina egna metoder.

Samma sak med DAO-object, basobject med vanligaste functionerna, typ get, list
osv. Sedan extendas den med mera specifika, t.ex User-dao. 

funktioner i dao:n ska alltså då initiera och returnera object som extendar
data objecten

typ User-dao.getUserWithPass() returnerar ett user object.

*/

var vertx = require('vertx');
var console = require('vertx/console');

var utils = require('utils');
var BaseModel = require('models/baseModel');

function User(arg) {
    this.isAdmin = false;

    BaseModel.call(this, arg);
 
    //this._collection = "users"
    this._collection = User.collection;
}

User.prototype = new BaseModel();
User.prototype.constructor = User;
User.collection = "users";


//User.collection = "user";

User.prototype.setPassword = function(password) {
    this.password = utils.hashPassword(password);
};


function Experiment(arg) {

    this.components = [];

    BaseModel.call(this, arg);

    this._collection = Experiment.collection;
}

Experiment.prototype = new BaseModel();
Experiment.prototype.constructor = Experiment;
Experiment.collection = "experiment";

Experiment.prototype.addComponent = function(id, type, name) {
  var comp = {};
  comp.id = id;
  comp.type = type;
  comp.name = name;
  this.components.push(comp);
};

Experiment.prototype.removeComponent = function(index) {
  this.components.splice(index, 1);
};

Experiment.prototype.renameComponent = function(index, name) {
  this.components[index].name = name;
};

/*
##########
TRAINING
*/
function Training(arg) {

    this.components = {};

    this.components.pre = [];
    this.components.training = [];
    this.components.post = [];

    BaseModel.call(this, arg);

    this._collection = Training.collection;
}

Training.prototype = new BaseModel();
Training.prototype.constructor = Training;
Training.collection = "training";

/*
####
TEST
*/
function Test(arg) {
    BaseModel.call(this, arg);

    this._collection = Test.collection;
}

Test.prototype = new BaseModel();
Test.prototype.constructor = Test;
Test.collection = "tests";


/*
####
FORM
*/
function Form(arg) {
  this.markup = "";
  BaseModel.call(this, arg);

  this._collection = Form.collection;
}

Form.prototype = new BaseModel();
Form.prototype.constructor = Form;
Form.collection = "forms";


Form.prototype.saveAndRender = function(callback) {
    var address = utils.get_address("questionnaire_render");

    var msg = {
      'markup':this.markup,
      "action":"save"
    };

    var newForm = false;

    if (typeof this.id !== undefined) {
      msg.id = this.id;
      newForm = true;
    }

    var that = this;
    vertx.eventBus.send(address, msg, function(reply) {
      var id = reply.id;
      if (newForm) {
        that.id = id;
      }
      callback(reply);
    });
};


/*
####
DATA
*/
function Data(arg) {
  this.confirmed = false;
  this.timestamp = new new Date().toISOString();

  BaseModel.call(this, arg);
  this._collection = Data.collection;
}

Data.prototype = new BaseModel();
Data.prototype.constructor = Data;
Data.collection = "data";

//Form.collection = "forms"

function TrainingData(arg) {  
  this.confirmed = false;
  this.timestamp = new Date().toISOString();

  BaseModel.call(this, arg);
  this._collection = TrainingData.collection;
}

TrainingData.prototype = new BaseModel();
TrainingData.prototype.constructor = TrainingData;
TrainingData.collection = "trainingdata";


/*
  Sets the general datafiled to the initial state. 
*/
TrainingData.prototype.initGeneral = function(trainingid, control) {
  this.type = "general";

  this.mode = "pre";
  this.position = 0;
  this.trainingIteration = 0;

  this.inControlGroup = false;

  if (control) {
    this.inControlGroup = false;
    // 50/50 chance to be put in control group
    if (Math.floor(Math.random() * 2 + 1) === 1) {
        this.inControlGroup = true;
    }
  }

  //When the next session is opened// DATE
  this.nextTask = new Date();

  this.trainingId = trainingid;
};


/*
  Generates a date x hours in the future
*/
function hoursFromNow(hours) {
  var date = new Date();

  hours = parseInt(hours);
  date.setHours(hours + date.getHours());

  return date;
}

/*Increment use position when a phase is completed*/
TrainingData.prototype.completePhase = function(training) {
  //If last phase, complete the whole set.
  //  If pre -> go to training + waittime
  //           -> or to control 
  //  If training -> training if iterations left
  //              -> post if no iterations left
  //  if post -> finish experiment
  //If not last phase -> phase += 1
  var mode = this.mode;
  var components = training.components[mode];
  
  var lastPhase = false;

  if(components.length === (this.position + 1)) {
    lastPhase = true;
  }

  //LastPhase
  if (lastPhase) {

    console.log("IN LAST PHASE")

    if (mode === "pre") {
      this.nextTask = hoursFromNow(training.repeatpause);

      if (this.inControlGroup) {
        this.mode = "control";
      } else {
        this.mode = "training";
      }
    }


    if (mode === "training" || mode === "control") {
      this.nextTask = hoursFromNow(training.repeatpause);

      if (training.repeatcount == (this.trainingIteration + 1)) {
        this.mode = "post";
      } else {
        this.trainingIteration += 1;
      }
    }

    if (mode === "post") {
      this.mode = "done";
    } 

    //Resetting task position
    this.position = 0;

  } else {
    this.position += 1;
  }
};


module.exports.User = User;
module.exports.Test = Test;
module.exports.Form = Form;

module.exports.Experiment = Experiment;
module.exports.Data = Data;

module.exports.Training = Training;
module.exports.TrainingData = TrainingData;