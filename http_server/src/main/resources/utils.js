var vertx = require('vertx');
var container = require('vertx/container');
var console = require('vertx/console');

var shared_config = container.config.shared;

console.log(JSON.stringify(container.config));

var utils = (function() {

  /*var addresses = shared_config.addresses;
  var directories = shared_config.directories;
  var http_directory = container.config.directory;*/

  return {
    'secure_path': function(path) {
      var secured = path.replace(/\.\.\//g, '');
      secured = secured.replace(/\.\//g, '');
      return secured;
    },

    'get_address': function(address) {
      return shared_config.addresses[address];
    },

    'get_directory': function(dir) {
      return shared_config.directories[dir];
    },

    // Get the base directory of the ENTIRE app.
    'get_basedir': function() {
      return this.get_directory('/');
    },

    // Get the base directory of the HTTP server.
    'get_serverdir': function() {
      return container.config.directory;
    },

    'file_exists': function(path) {
      var file = new java.io.File(path);
      return file.exists();
    },

    'file_from_serverdir': function(path) {
      return this.build_path(this.get_serverdir(), path);
    },

    'file_from_basedir': function(path) {
      return this.build_path(this.get_basedir(), path);
    },

    // https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Functions_and_function_scope/arguments
    'build_path': function() {
      var args = Array.slice(arguments);
      var path = args.join('/');
      return this.secure_path(path);
    },

    'build_url': function(host, port, resource) {
      return 'http://' + host + ':' + port + resource;
    },

    'getRandomInt': function(min, max) {
      return Math.floor(Math.random() * (max - min + 1)) + min;
    },
    'getUrlParams': function(params) {
      var paramsObject = {};
      var datapart;
      var i;

      params = params.split('&');
      for(i = 0; i<params.length;i++) {
        datapart = params[i].split('=');
        paramsObject[datapart[0]] = this.cleanUriParam(datapart[1]);
      }

      return paramsObject;
    },

    'cleanArray':function(arr) {
      for (var i = 0; i < arr.length; i++) {
        if (arr[i] == null) {         
            arr.splice(i, 1);
            i--;
        }
      }
      return arr;
    },
    //Decode uri params and remove +-signs
    'cleanUriParam': function(param) {
      return decodeURIComponent(param).split("+").join(" ");
    },

    'shuffle': function shuffle(array) {
      var currentIndex = array.length, temporaryValue, randomIndex ;

      // While there remain elements to shuffle...
      while (0 !== currentIndex) {

        // Pick a remaining element...
        randomIndex = Math.floor(Math.random() * currentIndex);
        currentIndex -= 1;

        // And swap it with the current element.
        temporaryValue = array[currentIndex];
        array[currentIndex] = array[randomIndex];
        array[randomIndex] = temporaryValue;
      }

      return array;
    },

    'hashPassword': function _hashPassword(password) {
      var messageDigest = java.security.MessageDigest.getInstance("SHA-256");
      var jpass = new java.lang.String(password);

      var bytes = messageDigest.digest(jpass.getBytes());

      var hexString = java.math.BigInteger(1, bytes).toString(16);

      console.log(hexString);

      return hexString;
    }
  };

});

module.exports = utils();
