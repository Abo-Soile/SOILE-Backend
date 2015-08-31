var vertx = require("vertx");
var CustomMatcher = require('router');
var console = require('vertx/console');

var templateManager = require('templateManager');
var router = new CustomMatcher();

var utils = require("utils");
var mongo = require('mongoHandler');

var requireAdmin = utils.requireAdmin;

var testDAO = require("models/DAObjects").TestDAO;

var container = require('vertx/container');
var config = container.config;
var testImages = config.directory + "/testimages";


router.get('/test', function(request) {
  mongo.test.list(function(r) {
    templateManager.render_template('testlist', {"tests":r.results},request);
  });
});


router.get('/test/json', function(request) {
  mongo.test.list(function(r) {
    request.response.end(JSON.stringify(r.results));
  });
});

router.get("/test/json/compiled", function(request) {
  testDAO.list({"compiled":true}, function(result) {
    request.response.end(JSON.stringify(result)); 
  });
});

router.post("/test", requireAdmin(function(request) {
  var data = new vertx.Buffer();

  request.dataHandler(function(buffer) {
    data.appendBuffer(buffer);
  });

  request.endHandler(function() {

    data = data.getString(0, data.length());
    var params = utils.getUrlParams(data);
    var name = decodeURIComponent(params.name).split("+").join(" ");

    console.log("name1: " + name);

    mongo.test.save({"name":name}, function(r) {
      console.log(JSON.stringify(r));

      var dirName = testImages + "/" + r._id;

      vertx.fileSystem.mkDir(dirName, true, function(err, res) {
        console.log(err + "  " + res);
        if (!err) {
          console.log('Directory created ok');
          return request.redirect("/test/"+r._id);
        }
      });
    });
  });
}));


router.get('/test/:id', requireAdmin(function(request) {
  var id = request.params().get('id');
  var code = "sadas";
  var files = [];
  vertx.fileSystem.readDir(testImages + "/" + id, function(err, res) {
    if (!err) {
      //files = res;
       for (var i = 0; i < res.length; i++) { 
          var img = res[i].toString();
          var file = {};
          file.url = img.substring(img.indexOf("testimages"));
          file.name = img.substring(img.lastIndexOf("/")+1);
          files.push(file);
        }
        console.log(JSON.stringify(files));
        console.log("\n\n\n");
    }
    mongo.test.get(id, function(r) {
      templateManager.render_template('testEditor', 
        {"code":code, "test":r.result, "files":files}, request);
    });
  });
}));


router.post("/test/:id", requireAdmin(function(request) {
  var data = new vertx.Buffer();
  var id = request.params().get('id');

  request.dataHandler(function(buffer) {
    data.appendBuffer(buffer);
  });

  request.endHandler(function() {

    data = data.getString(0, data.length());
    var code = JSON.parse(data).code;

    var address = utils.get_address('experiment_language');
    var eb = vertx.eventBus;
    var msg = {
      'code': code
    };

    eb.send(address, msg, function(reply) {
      var response = {};

      var test = {};

      test._id = id;
      test.code = code;

      if (reply.hasOwnProperty('errors') === true) {
        response.errors = reply.errors.split("\n");
        console.log(reply.errors);

        test.js = "";
        test.compiled = false;
      } else {
        response.code = reply.code;
        test.js = response.code;
        test.compiled = true;
      }

      mongo.test.update(test, function() {
        request.response.putHeader("Content-Type", "application/json; charset=UTF-8");
        request.response.end(JSON.stringify(response));
      });
    });
  });
}));


router.post("/test/:id/imageupload", function(request) {

  request.expectMultiPart(true);
  var id = request.params().get('id');

  request.uploadHandler(function(upload) {
      //var path = testImages + id + "/" + upload.filename()
      var fixedFilename = upload.filename();

      //Replacing and removing unwanted characters from filename
      fixedFilename = fixedFilename.replace(/[å+ä]/gi, "a");
      fixedFilename = fixedFilename.replace("ö", "o"); 
      fixedFilename = fixedFilename.replace(/[^a-z0-9+.]/gi, '_').toLowerCase();

      var path = testImages + "/" + id +"/" + fixedFilename;
      //var path = testImages + "/" + id +"/" + upload.filename()
      console.log("Uploading image to "+ path);
      upload.streamToFileSystem(path);
  });

  request.endHandler(function() {

    console.log("Uploading");

    request.response.end(200);
  });
});


router.delete("/test/:id/imageupload/:imageName", function(request) {
  var id = request.params().get('id');
  var imgName = request.params().get('imageName');

  console.log("DELETING IMAGE");

  request.endHandler(function() {
    var filename = testImages + "/" + id + "/" + imgName;
    vertx.fileSystem.delete(filename, function(err, res) {
      if(!err) {
            request.response.end(200);
          }
      else {
        request.response.end(400);
      }
    });
  });
});


router.get('/test/:id/imagelist', function(request) {
  var id = request.params().get('id');
  var files = [];

  vertx.fileSystem.readDir(testImages + "/" + id, function(err, res) {
    if (!err) {
      //files = res;
      for (var i = 0; i < res.length; i++) { 
        var img = res[i].toString();
        var file = {};
        file.url = img.substring(img.indexOf("testimages"));
        file.name = img.substring(img.lastIndexOf("/")+1);
        files.push(file);
      }

      var fileJSON = JSON.stringify(files);
      request.response.end(fileJSON);
    }
  });
});


router.post('/test/:id/editname', requireAdmin(function(request) {
  var id = request.params().get('id');
  var data = new vertx.Buffer();

  request.dataHandler(function(buffer){
    data.appendBuffer(buffer);
  });

  request.endHandler(function() {
    var jsonData = (JSON.parse(data.getString(0, data.length())));

    var name = jsonData.name;

    mongo.test.editName(id, name, function(r){
      console.log(JSON.stringify(r));
      request.response.end(JSON.stringify(r.result));
    });
  });
}));