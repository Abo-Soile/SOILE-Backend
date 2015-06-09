/*
Masterplan, laga ett bas object för enskilda object sparade i databasen.
Olika object kan sedan extenda detta och implementera sina egna metoder.

Samma sak med DAO-object, basobject med vanligaste functionerna, typ get, list
osv. Sedan extendas den med mera specifika, t.ex User-dao. 

funktioner i dao:n ska alltså då initiera och returnera object som extendar
data objecten

typ User-dao.getUserWithPass() returnerar ett user object.

*/

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

    this.components = [];

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
    BaseModel.call(this, arg);

    this._collection = Form.collection;
}

Form.prototype = new BaseModel();
Form.prototype.constructor = Form;
Form.collection = "forms";


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

module.exports.User = User;
module.exports.Test = Test;
module.exports.Form = Form;
module.exports.Experiment = Experiment;
module.exports.Training = Training