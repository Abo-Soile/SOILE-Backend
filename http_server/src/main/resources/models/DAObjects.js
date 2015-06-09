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


module.exports.BaseDAO = BaseDAO;

module.exports.UserDAO = UserDAO;
module.exports.TestDAO = TestDAO;
module.exports.ExperimentDAO = ExperimentDAO;
module.exports.FormDAO = FormDAO;
module.exports.DataDAO = DataDAO;
module.exports.TrainingDAO = TrainingDAO;