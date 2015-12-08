var vertx = require("vertx");
var CustomMatcher = require('router');
var console = require('vertx/console');

var templateManager = require('templateManager');
var router = new CustomMatcher();

var utils = require("utils");
var mongo = require('mongoHandler');

var testModel = require('models/Models').Test;
var testDAO = require("models/DAObjects").TestDAO;

var container = require('vertx/container');
var config = container.config;
var testImages = config.directory + "/testimages";

var requireAdmin = require('middleware').requireAdmin;

router.get('/test', function(request) {
  mongo.test.list(function(r) {
    templateManager.render_template('testlist', {"tests":r.results},request);
  });
});

router.get('/test/json', function(request) {
  mongo.test.list(function(r) {

    request.response.putHeader("Content-Type", "application/json; charset=UTF-8");
    request.response.end(JSON.stringify(r.results));
  });
});

router.get("/test/folder/json", requireAdmin, function(request) {
  testDAO.listFolders(function(folders) {
    console.log(JSON.stringify(folders));

    request.response.putHeader("Content-Type", "application/json; charset=UTF-8");
    request.response.end(JSON.stringify(folders));
  });
});

router.get("/test/json/compiled", function(request) {
  testDAO.list({"compiled":true}, function(result) {
    request.response.putHeader("Content-Type", "application/json; charset=UTF-8");
    request.response.end(JSON.stringify(result)); 
  });
});

router.get("/test/folder/:foldername/json", requireAdmin, function(request) {
  var folder = request.params().get('foldername');

  var query = {"folder":folder};

  if (folder == "unspecified") {
    query = {$or: [{"folder" : { "$exists" : false }}, {folder:""}] };
  }

  testDAO.list(query,function(result) {
    request.response.putHeader("Content-Type", "application/json; charset=UTF-8");
    request.response.end(JSON.stringify(result));
  });
});

router.post("/test", requireAdmin,function(request) {
  var data = new vertx.Buffer();

  request.dataHandler(function(buffer) {
    data.appendBuffer(buffer);
  });

  request.endHandler(function() {

    data = data.getString(0, data.length());
    var params = utils.getUrlParams(data);
    var name = decodeURIComponent(params.name).split("+").join(" ");

    console.log("name1: " + name);

    var test = new testModel();

    test.name = name;

    test.init(function(err, result) {
      return request.redirect("/test/"+test._id);
    });
  });
});


router.get('/test/:id', requireAdmin, function(request) {
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
    testDAO.get(id, function(test) {
      templateManager.render_template('testEditor', 
        {"code":test.code, "test":test, "files":files}, request);
    });
  });
});

router.post("/test/:id", requireAdmin, function(request) {
  var data = new vertx.Buffer();
  var id = request.params().get('id');

  request.dataHandler(function(buffer) {
    data.appendBuffer(buffer);
  });

  request.endHandler(function() {

    data = data.getString(0, data.length());
    data = JSON.parse(data);

    var name = data.name;
    var published = data.published;
    var folder = data.folder;

    if (typeof folder === "undefined" || folder === "") {
      folder = "Unspecified";
    }
    
    testDAO.get(id, function(test) {

      test.name = name;
      test.published = published;
      test.folder = folder;

      test.save(function(response) {
        request.response.putHeader("Content-Type", "application/json; charset=UTF-8");
        request.response.end(JSON.stringify({"status":"ok"}));
      });
    });
  });
});

router.get('/test/:id/copy', requireAdmin, function(request) {
  var id = request.params().get('id');

  testDAO.get(id, function(test) {
    test.copy(request.session.getUserId(), function(newTest) {
      request.redirect("/test/" + newTest._id);
    });
  });
});

router.post("/test/:id/compile", requireAdmin, function(request) {
  var data = new vertx.Buffer();
  var id = request.params().get('id');

  request.dataHandler(function(buffer) {
    data.appendBuffer(buffer);
  });

  request.endHandler(function() {

    data = data.getString(0, data.length());
    var code = JSON.parse(data).code;

    testDAO.get(id, function(test) {

      test.compile(code, function(response) {

        request.response.putHeader("Content-Type", "application/json; charset=UTF-8");
        request.response.end(JSON.stringify(response));
      });
    });
  });
});


router.post("/test/:id/imageupload", requireAdmin,function(request) {

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


router.delete("/test/:id/imageupload/:imageName", requireAdmin,function(request) {
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


router.get('/test/:id/imagelist', requireAdmin,function(request) {
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


router.post('/test/:id/editname', requireAdmin,function(request) {
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
});

router.get('/test/:id/json', requireAdmin, function(request) {
  var id = request.params().get('id');

  testDAO.get(id, function(test) {
    var json = test.toJson();

    request.response.putHeader("Content-Type", "application/json; charset=UTF-8");
    request.response.end(json);
  });

});