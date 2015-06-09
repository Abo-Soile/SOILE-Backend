var vertx = require('vertx');
var eb = vertx.eventBus;
var utils = require('utils');

var console = require('vertx/console');
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
            //console.log(JSON.stringify(mongoReply));
            var obj = new that._baseObject(mongoReply.result);
            //obj._id = mongoReply.result._id;
            callback(obj);
        }else{
            callback("", mongoReply.message);
        }
    });
};

BaseDAO.prototype.list = function(matcher, callback, limit, sort) {
    var mongoCommand = {};
    var that = this;
    mongoCommand.action = "find";
    //mongoCommand.collection = this._collection;
    if(matcher !== null) {
        mongoCommand.matcher = matcher;
    } else {
        mongoCommand.matcher = {};
    }
    if(typeof limit !== undefined && typeof limit === 'number') {
        mongoCommand.limit = limit;
    }

    if(typeof sort !== undefined) {
        mongoCommand.sort = sort;
    }

    this.sendToMongo(mongoCommand, function(mongoReply) {
        if(mongoReply.status === "ok") {
            var resultObjects = [];

            var start = new Date().getTime();
            for (var i = 0; i < mongoReply.results.length; i++) {
                //console.log("Building object");
                resultObjects.push(new that._baseObject(mongoReply.results[i]));
            }
            console.log("Timetaken: " + (new Date().getTime() - start));
            callback(resultObjects);
        }

        else {
            callback(false);
        }
    });
};

BaseDAO.prototype.update = function(matcher, objnew, callback) {
    var mongoCommand = {};

    if (typeof matcher === 'undefined' ||
        typeof objnew === 'undefined') {
        callback(false);
    }

    mongoCommand.action = "update";
    mongoCommand.criteria = matcher;
    mongoCommand.objNew = objnew;

    this.sendToMongo(mongoCommand, function(mongoReply) {
        if(mongoReply.status === "ok") {
            return callback(true);
        }
        return callback(false);
    });
};

BaseDAO.prototype.count = function(matcher, callback) {
    var mongoCommand = {};
    mongoCommand.matcher = {};

    mongoCommand.action = "count"
    mongoCommand.matcher = matcher;

    this.sendToMongo(mongoCommand, function(mongoReply) {
        if (mongoReply.status === "ok") {
            return callback(mongoReply.count);
        }
        return callback(0);
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
                console.log("####Result from mongo");
                console.log(JSON.stringify(reply));
                callback(reply);
            }
    );
};

module.exports = BaseDAO;
