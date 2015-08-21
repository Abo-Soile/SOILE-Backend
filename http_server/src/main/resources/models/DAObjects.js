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

/*
    Returns score from the previous training phase. 
*/
TrainingDataDAO.prototype.getScore = function(trainingId, userid, callback) {
    var iteration = null;
    var mode = null;
    var that = this;

    that.get({userId:userid, type:"general", trainingId:trainingId}, function(general) {
        if(general.mode === "training" && general.trainingIteration > 0) {
            iteration = general.trainingIteration - 1;
            mode = "training";
        }
        else if (general.mode === "post") {
            mode = "training";
            iteration = general.trainingIteration;
        }

        else if (general.mode === "done") {
            mode = "post";
        } else {
            mode = "pre";
        }

        var matcher = {
            "trainingId":general.trainingId,
            "userId":general.userId
        };

        if(mode) {
            matcher.mode = mode;
        }

        if(iteration !== null) {
            matcher.trainingIteration = iteration;
        }

        that.list(matcher, function(scoreList) {
            var totalScore = 0;
            var scores = [];

            for (var i = 0; i < scoreList.length; i++) {
                if(typeof scoreList[i].score !== "undefined") {
                    totalScore += scoreList[i].score.score;
                    scores.push(scoreList[i].score);
                }
            }
            
            callback(totalScore, scores); 
        });
    });
};

/*
    Returns score statistics. 
*/
TrainingDataDAO.prototype.getScoreHistory = function(trainingId, userid, callback) {
    var that = this;

       
    var matcher = {
        "trainingId":trainingId,
        "userId":userid,
        "mode":"training"
    };

    that.list(matcher, function(scoreList) {
        var iterationScores = [];

        for (var i = 0; i < scoreList.length; i++) {
            if(typeof scoreList[i].score !== "undefined") {
                if (typeof iterationScores[scoreList[i].trainingIteration] === "undefined") {
                    iterationScores[scoreList[i].trainingIteration] = 0;
                }
                iterationScores[scoreList[i].trainingIteration] += scoreList[i].score.score;
            }
        }
        
        callback(iterationScores); 
    });

};

TrainingDataDAO.prototype.getPrePostScore = function(trainingId, userid, callback) {
    var that = this;



};

module.exports.BaseDAO = BaseDAO;

module.exports.UserDAO = UserDAO;

module.exports.TestDAO = new TestDAO();
module.exports.FormDAO = new FormDAO();

module.exports.ExperimentDAO = ExperimentDAO;
module.exports.DataDAO = DataDAO;

module.exports.TrainingDAO = TrainingDAO;
module.exports.TrainingDataDAO = TrainingDataDAO;