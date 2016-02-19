var vertx = require('vertx');
var eb = vertx.eventBus;
var utils = require('utils');

var container = require('vertx/container');
var logger = container.logger;

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

    if (typeof mongoCommand.matcher.deleted === 'undefined') {
        mongoCommand.matcher.deleted = {$in: [null, false]};
    }
    //mongoCommand.collection = this._collection;

    this.sendToMongo(mongoCommand, function(mongoReply) {
        if (mongoReply.status === "ok" && (typeof mongoReply.result !== "undefined")) {
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
    if((typeof matcher === 'object')) {
        mongoCommand.matcher = matcher;
    } else {
        mongoCommand.matcher = {};
    }

/*
    First parameter as callback if callback is undefined and matcher a
    function
*/
    if(typeof matcher === 'function' && typeof callback === 'undefined') {
        callback = matcher;
    }

    if(typeof limit !== undefined && typeof limit === 'number') {
        mongoCommand.limit = limit;
    }

    if(typeof sort !== undefined) {
        mongoCommand.sort = sort;
    }

    if (typeof mongoCommand.matcher.deleted === 'undefined') {
        mongoCommand.matcher.deleted = {$in: [null, false]};
    }

    this.sendToMongo(mongoCommand, function(mongoReply, replier) {
        console.log("Find command done - " + mongoReply.status);
        if(mongoReply.status === "more-exist"){
            console.log("More exists");

            replier({}, that.handleMore(that, mongoReply.results, callback));
        }

        else if(mongoReply.status === "ok") {
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
/*
 * Update the element specified in matcher with data specified in objNew
 * Will update multiple objects by default, set multi to false to only update
 * one. 
 */

BaseDAO.prototype.update = function(matcher, objnew, callback, multi) {
    var mongoCommand = {};

    if (typeof matcher === 'undefined' ||
        typeof objnew === 'undefined') {
        callback(false);
    }

    mongoCommand.action = "update";
    mongoCommand.criteria = matcher;
    mongoCommand.objNew = objnew;
    mongoCommand.multi = true;

    if (multi === false) {
        mongoCommand.multi = false;
    }

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

    mongoCommand.action = "count";
    mongoCommand.matcher = matcher;

    if (typeof mongoCommand.matcher.deleted === 'undefined') {
        mongoCommand.matcher.deleted = {$in: [null, false]};
    }

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
    logger.info("Mongo request: " + JSON.stringify(arg));
    eb.send(this._mongoAddress,
            arg,
            function(reply, replier) {
                if(typeof arg.action !== 'undefined'){
                    console.log("####Result from mongo " + arg.action);
                }
                else {
                    console.log("####Result from mongo ");
                }
                //console.log(arg.action);
                callback(reply, replier);
            }
    );
};

BaseDAO.prototype.aggregate = function(pipeline, callback) {
    var obj = {}
    obj.pipelines = pipeline;
    obj.action = "aggregate";

    this.sendToMongo(obj, function(res) {
        if (res.status=="ok") {
            return callback(res.results);
        }
        return callback(false)
    });
};

BaseDAO.prototype.handleMore = function(obj, data, callback) {
    //console.log("Building new replier")
    return function(reply, replier) {
        var result = data.concat(reply.results);

        if(reply.status==="more-exist") {
            replier({}, obj.handleMore(obj, result, callback));
        }
        else {
            var resultObjects = [];
            for (var i = 0; i < result.length; i++) {
                resultObjects.push(new obj._baseObject(result[i]));
            }
            callback(resultObjects);
        }
        
    };
};

module.exports = BaseDAO;
