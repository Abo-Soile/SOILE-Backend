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
var eb = vertx.eventBus;
var utils = require('utils');

var console = require('vertx/console')

if (!Object.assign) {
  Object.defineProperty(Object, 'assign', {
    enumerable: false,
    configurable: true,
    writable: true,
    value: function(target, firstSource) {
      'use strict';
      if (target === undefined || target === null) {
        throw new TypeError('Cannot convert first argument to object');
      }

      var to = Object(target);
      for (var i = 1; i < arguments.length; i++) {
        var nextSource = arguments[i];
        if (nextSource === undefined || nextSource === null) {
          continue;
        }

        var keysArray = Object.keys(Object(nextSource));
        for (var nextIndex = 0, len = keysArray.length; nextIndex < len; nextIndex++) {
          var nextKey = keysArray[nextIndex];
          var desc = Object.getOwnPropertyDescriptor(nextSource, nextKey);
          if (desc !== undefined && desc.enumerable) {
            to[nextKey] = nextSource[nextKey];
          }
        }
      }
      return to;
    }
  });
}

function replacer(key, value) {
    if (key == "_collection"||
        key == "_mongoAddress"
        ) {
        return undefined;
    }
    return value;
}


var mongoAddress = "vertx.mongo-persistor";


function BaseModel(arg) {
    //this._collection = "temp";
    this._mongoAddress = mongoAddress;
}

BaseModel.prototype.save = function(callback) {
    console.log("SAVING!!!")
    
    var obj = {"action":"save"}
    obj.document = this.filter();

    console.log(JSON.stringify(obj))

    var self = this;
    this.sendToMongo(obj, function(reply) {
        console.log(JSON.stringify(reply))
        self._id = reply._id;
        callback(reply)
    })
};

BaseModel.prototype.update = function(objNew, callback) {

};

BaseModel.prototype.delete = function() {
    console.log("DELETE" + this._collection);    // body...
};

BaseModel.prototype.sendToMongo = function(arg, callback) {
    console.log("This collection " + this._collection)
    arg.collection = this._collection;
    console.log(JSON.stringify(arg))
    eb.send(this._mongoAddress,
            arg,
            function(reply) {
                callback(reply)
            }
    )
}

BaseModel.prototype.filter = function () {
    /*var obj = this;
    Object.keys(obj).forEach(function (i) { if (i[0] === '_') delete obj[i]; });
    return obj
*/
    return JSON.parse(JSON.stringify(this, replacer));
}

/*Populating fields on the object*/
BaseModel.prototype.populateFields = function(fields) {
  Object.assign(this, fields)
}


function User(arg) {
    BaseModel.call(this, arg);
 
    this._collection = "users"

    this.email;
    this.password;
    this.isAdmin;
}

User.prototype = new BaseModel()
User.prototype.constructor = User;

//User.collection = "user";

User.prototype.setPassword = function(password) {
    this.password = utils.hashPassword(password);
};

User.prototype.isAdmin = function(first_argument) {
    // body...
};

function Experiment() {
    BaseModel.call(this);

    this._collection = "experiment";
}
Experiment.prototype = new BaseModel()
Experiment.prototype.constructor = Experiment;

//Experiment.collection = "experiment";

function Test() {
    BaseModel.call(this);

    this._collection = "tests";
}

Test.prototype = new BaseModel()
Test.prototype.constructor = Test;

//Test.collection = "tests"

function Form() {
    BaseModel.call(this);

    this._collection = "forms"
}

Form.prototype = new BaseModel()
Form.prototype.constructor = Form;

//Form.collection = "forms"

module.exports.User = User;
module.exports.Test = Test;
module.exports.Form = Form;
module.exports.Experiment = Experiment;