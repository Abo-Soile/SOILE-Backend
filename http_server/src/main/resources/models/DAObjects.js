var vertx = require('vertx');
var eb = vertx.eventBus;
var utils = require('utils');

var console = require('vertx/console');

var models = require('models/Models');
var BaseDAO = require('models/baseDAO');

var User = models.User;
var Test = models.Test;
var Form = models.Form;
var Experiment = models.Experiment;
var Training = models.Training;
var TrainingData = models.TrainingData;


function UserDAO() {
    BaseDAO.call(this);
    this._baseObject = models.User;
    this._collection = this._baseObject.collection;
}

UserDAO.prototype = new BaseDAO();
UserDAO.prototype.constructor = UserDAO;


function TestDAO() {
    BaseDAO.call(this);
    this._baseObject = models.Test;
    this._collection = this._baseObject.collection;
}

TestDAO.prototype = new BaseDAO();
TestDAO.prototype.constructor = TestDAO;

function ExperimentDAO() {
    BaseDAO.call(this);
    this._baseObject = models.Experiment;
    this._collection = this._baseObject.collection;

}

ExperimentDAO.prototype = new BaseDAO();
ExperimentDAO.prototype.constructor = ExperimentDAO;


function FormDAO() {
    BaseDAO.call(this);
    this._baseObject = models.Form;
    this._collection = this._baseObject.collection;
}

FormDAO.prototype = new BaseDAO();
FormDAO.prototype.constructor = FormDAO;


function DataDAO() {
    BaseDAO.call(this);
    this._baseObject = models.Form;
    this._collection = this._baseObject.collection;
}

DataDAO.prototype = new BaseDAO();
DataDAO.prototype.constructor = DataDAO;


function TrainingDAO() {
    BaseDAO.call(this);
    this._baseObject = models.Training;
    this._collection = this._baseObject.collection;
}

TrainingDAO.prototype = new BaseDAO();
TrainingDAO.prototype.constructor = TrainingDAO;

TrainingDAO.prototype.addform = function(first_argument) {
    // body...
};


function TrainingDataDAO() {
    BaseDAO.call(this);
    this._baseObject = models.TrainingData;
    this._collection = this._baseObject.collection;
}

TrainingDataDAO.prototype = new BaseDAO();
TrainingDataDAO.prototype.constructor = TrainingDataDAO;

TrainingDataDAO.prototype.getOrGenerateGeneral = function(userid, trainingId, controlGroup,callback) {
  var that = this;
  that.get({userId:userid, type:"general", trainingId:trainingId}, 
        function(trainingData, message) {
    if (trainingData === "") {
        //console.log("Generating new data object");
        trainingData = new TrainingData();

        trainingData.userId = userid;
        trainingData.initGeneral(trainingId, controlGroup);

        trainingData.save(function(err) {
            return callback(trainingData);

        });
    } else {
        console.log("Training exists");
        callback(trainingData);
    }

  });
};

module.exports.BaseDAO = BaseDAO;

module.exports.UserDAO = UserDAO;

module.exports.TestDAO = new TestDAO();
module.exports.FormDAO = new FormDAO();

module.exports.ExperimentDAO = ExperimentDAO;
module.exports.DataDAO = DataDAO;

module.exports.TrainingDAO = TrainingDAO;
module.exports.TrainingDataDAO = TrainingDataDAO;