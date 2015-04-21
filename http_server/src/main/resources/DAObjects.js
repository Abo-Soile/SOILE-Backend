var vertx = require('vertx');
var eb = vertx.eventBus;
var utils = require('utils');

var console = require('vertx/console');

var models = require('Models');
var User = models.User;

var mongoAddress = "vertx.mongo-persistor";

function BaseDAO() {
    this._collection = "temp";
    this._mongoAddress = mongoAddress;
}

BaseDAO.prototype.get = function(matcher, callback) {
    var that = this;

    var mongoCommand = {};
    mongoCommand.action = "findone";
    if(typeof matcher !== 'object') {
        mongoCommand.matcher = {_id:matcher};
    }else {
        mongoCommand.matcher = matcher;
    }
    //mongoCommand.collection = this._collection;

    this.sendToMongo(mongoCommand, function(mongoReply) {
        if (mongoReply.status === "ok") {
            var user = new that._baseObject(mongoReply.result);
            callback(user);
        }else{
            callback("", mongoReply.message);
        }
    });
};

BaseDAO.prototype.list = function(matcher, callback, limit, sort) {
    var mongoCommand = {};
    mongoCommand.action = "find";
    //mongoCommand.collection = this._collection;

    mongoCommand.matcher = matcher;
    if(typeof limit !== undefined && typeof limit === 'number') {
        mongoCommand.limit = limit;
    }

    if(typeof sort !== undefined) {
        mongoCommand.sort = sort;
    }

    this.sendToMongo(mongoCommand, function(mongoReply) {

    });
};


/*
Sends a command to mongo with the specified arguments.
See documentation for mongo-persitor for avalable  commands
*/
BaseDAO.prototype.sendToMongo = function(arg, callback) {
    arg.collection = this._collection;
    console.log(JSON.stringify(arg));
    eb.send(this._mongoAddress,
            arg,
            function(reply) {
                callback(reply);
            }
    );
};

function UserDAO() {
    BaseDAO.call(this);
    this._baseObject = models.User;
    this._collection = this._baseObject.collection;
}

UserDAO.prototype = new BaseDAO();
UserDAO.prototype.constructor = UserDAO;


function TestDAO() {
    BaseDAO.call(this);
    this._collection = ""
}

module.exports.UserDAO = UserDAO;