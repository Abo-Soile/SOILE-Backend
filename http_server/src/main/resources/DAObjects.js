var vertx = require('vertx');
var eb = vertx.eventBus;
var utils = require('utils');

var console = require('vertx/console')

var models = require('Models')

var mongoAddress = "vertx.mongo-persistor";

function BaseDAO() {
    this._collection = "temp"
    this._mongoAddress = mongoAddress;
}

BaseDAO.prototype.get = function(matcher, callback) {
    var mongoCommand = {};
    mongoCommand.action = "findone";
    if(typeof matcher !== 'object') {
        mongoCommand.matcher = {_id:matcher};
    }else {
        mongoCommand.matcher = matcher;
    }
    //mongoCommand.collection = this._collection;

    this.sendToMongo(mongoCommand, callback);
};

BaseDAO.prototype.list = function(matcher, callback, limit, sort) {
    mongoCommand = {}
    mongoCommand.action = "find"
    //mongoCommand.collection = this._collection;

    mongoCommand.matcher = matcher;
    if(typeof limit !== undefined && typeof limit === 'number') {
        mongoCommand.limit = limit
    }

    this.sendToMongo(mongoCommand, callback);
};

BaseDAO.prototype.sendToMongo = function(arg, callback) {
    arg.collection = this._collection;
    console.log("mongo command");
    console.log(JSON.stringify(arg))
    eb.send(this._mongoAddress,
            arg,
            function(reply) {
                callback(reply)
            }
    )
}

function UserDAO() {
    BaseDAO.call(this);
    this._collection = "users";
    this._baseObject = models.User;
}

UserDAO.prototype = new BaseDAO()
UserDAO.prototype.constructor = UserDAO;

module.exports.UserDAO = UserDAO;