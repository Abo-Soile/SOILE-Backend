var app = angular.module('testEditor', ['ui.ace', 'angularFileUpload', 'xeditable']);
var disp = document.getElementById("display");

var autoCompleteList = [
  {"meta":"elang-keyword",  "word":"intermezzo-phase\nend"},
  {"meta":"elang-keyword",  "word":"interaction-phase\n  iteration\n  end\nend"},
  {"meta":"elang-keyword",  "word":"while \ndo \nend"},
  {"meta":"elang-keyword",  "word":"if \n then \nend"},
  {"meta":"elang-keyword",  "word":"if \nthen \nelse \nend"},
  {"meta":"elang-keyword",  "word":"function functionName() \nend"},
  {"meta":"elang-function", "word":"eq()"},
  {"meta":"elang-function", "word":"lt()"},
  {"meta":"elang-function", "word":"gt()"},
  {"meta":"elang-function", "word":"stimulus()"},
  {"meta":"elang-function", "word":""},
  {"meta":"elang-function", "word":""},
  {"meta":"elang-function", "word":""},
  {"meta":"elang-function", "word":""},
  {"meta":"elang-function", "word":""},
  {"meta":"elang-function", "word":""},
  {"meta":"elang-function", "word":""},
  {"meta":"elang-function", "word":""},
  {"meta":"elang-function", "word":""},
  {"meta":"elang-function", "word":""},
  {"meta":"elang-function", "word":""},
  {"meta":"elang-function", "word":""},
  {"meta":"elang-function", "word":""},
  {"meta":"elang-function", "word":""},
  {"meta":"elang-function", "word":""},
  {"meta":"elang-function", "word":""},
  {"meta":"elang-function", "word":""},
  {"meta":"elang-function", "word":""},
  {"meta":"elang-function", "word":""},
  {"meta":"elang-function", "word":""},
  {"meta":"elang-function", "word":""},
  {"meta":"elang-function", "word":""},
  {"meta":"elang-function", "word":""},
  {"meta":"elang-function", "word":""},
  {"meta":"elang-function", "word":""},
  {"meta":"elang-function", "word":""},
  {"meta":"elang-function", "word":""},
  {"meta":"elang-function", "word":""},
  {"meta":"elang-function", "word":""},
  {"meta":"elang-function", "word":""},
];

ace.config.set("modePath", "/javascript");

app.run(function(editableOptions) {
  editableOptions.theme = 'bs3'; // bootstrap3 theme. Can be also 'bs2', 'default'
});

app.config(function($interpolateProvider){
    $interpolateProvider.startSymbol('[([').endSymbol('])]');
});

/*Marks html as safe*/
app.filter('unsafe', function($sce) { return $sce.trustAsHtml; });

//Reverses the order of an array
app.filter("reverse", function(){
    return function(items){
        return items.slice().reverse();
    };
});

app.controller('fileController', function($scope, $http, $location, FileUploader) {
		var baseUrl = $location.absUrl();
		var imgUrl = baseUrl + "/imageupload";
		var onComplete = function(item, response, status, headers) {
			loadImages();
		};

		$scope.uploader = new FileUploader({url:imgUrl});
		$scope.images = [];

		$scope.uploader.autoUpload = true;
		$scope.uploader.removeAfterUpload = true;
		$scope.uploader.onCompleteItem = onComplete;

		function loadImages() {
			$http.get(baseUrl + '/imagelist').
		  success(function(data, status, headers, config) {
		    $scope.images = data;
		  }).
		  error(function(data, status, headers, config) {
		    // log error
		    console.log("ERRRROR");
			});
		}

		function delImage(imageIndex) {
			var img = $scope.images[imageIndex];
			var url = imgUrl + "/" + img.name;
			$http.delete(url).
				success(function(data, status, headers, config) {
					console.log("Deleted image");
					$scope.images.splice(imageIndex, 1);
		  });
		}

		$scope.delImage = delImage;

		$scope.useImage = function(image) {
			var name = image.name;
      var humanName = name.substring(0, name.lastIndexOf("."));
      var url = "/" + image.url;

      var str = "val "+ humanName +' <- imagefile("'+ url+ '") \n';

			$scope.editor.insert(str);
		};

		loadImages();
});

app.controller('expEditController', function($scope, $http, $location, $timeout, $sce, $window) {
	$scope.compileErrors = "";
	$scope.soileLog  = [];

  $scope.savebutton = "Save&Compile";
  $scope.runbutton = "Run";
  $scope.compiled = false;

  $scope.running = false;

  $scope.test = {};

  $scope.showAdvanced = false;

	$scope.aceLoaded = function(_editor) {
    // Options
    $scope.editor = _editor;
  
    $scope.editor.renderer.setShowGutter(true); 

    $scope.lastSave = $scope.editor.getValue();

    $scope.editor.getSession().setTabSize(2);

    $scope.editor.setOptions({"enableBasicAutocompletion": true,"enableLiveAutocompletion": false,"enableSnippets": true});
    $scope.editor.getSession().setMode("ace/mode/elang");

    var autocompleter = {
    getCompletions: function(editor, session, pos, prefix, callback) {
      if (prefix.length === 0) { callback(null, []); return}
      // wordList like [{"word":"flow","freq":24,"score":300,"flags":"bc","syllables":"1"}]
      callback(null, autoCompleteList.map(function(ea) {
          return {name: ea.word, value: ea.word, score: ea.score, meta:ea.meta};
        }));
      }
    };

    window.langTool.addCompleter(autocompleter);
  };

  $scope.showhideadvance = function() {
    $scope.showAdvanced = !$scope.showAdvanced
  }

  $scope.compileTest = function() {
  	var code = {"code":$scope.editor.getValue()};

    $scope.savebutton = '<i class="fa fa-spinner fa-spin"></i> Compiling';

    var url = $location.absUrl() + "/compile";

  	$http.post(url, code).success(function(data, status, headers, config) {
  		
      $scope.savebutton = "Save&Compile";

      $scope.compileErrors = data.errors;
  		$scope.compiledCode = data.code;

      $scope.lastSave = $scope.editor.getValue();

      if (typeof data.errors === 'undefined') {
        $scope.compiled = true;
      }else {
        $scope.compiled = false;
      }
  	});
  };

  $scope.logFunc = function(message) {
  	var logRow = {timestamp: Date.now() - $scope.testStartTime, message:message};
  	logRow.timestamp = logRow.timestamp/1000 + " s";
  	$scope.soileLog.push(logRow);
  	$scope.$apply();

  };

  $scope.endFunc = function(data, duration, score, persistantData) {
      console.log("it's over");
      console.log(data);

      $scope.runbutton = "Run";
      $scope.running = false;


    /*   This part pretty much generate a bucket for all distinct fields
       in the result object. These buckets are then filled sequentially
       so that all fields are filled int the right order and missing values
       are replaced with a -*/
      var set = {};
      var rowCount = data.rows.length;

      for (var i = 0; i<rowCount; i++) {
        for(var j in data.rows[i]) {
          set[j] = {valid:true, data:[]};
        }
      }

      for (i = 0; i<rowCount; i++) {
        for(var s in set) {
          if ( data.rows[i].hasOwnProperty(s) ) {
            set[s].data.push(data.rows[i][s]);
          }else {
            set[s].data.push("-");
          }
        }
      }

      var headers = [];
      var rows = [];

      for(var col in set) {
        headers.push(col);
      }

      for (i = 0; i < rowCount; i++) {
        rows[i] = [];
        for(col in set) {
          rows[i].push(JSON.stringify(set[col].data[i]));
          //rows[i][col] = JSON.stringify(set[col].data[i]);
        }
      }

      $scope.singleData = data.single;
      $scope.rawHeaders = headers;
      $scope.rawData = rows;

      if (score || persistantData) {
        $scope.trainingdata = true;
      } 
      else {
        $scope.trainingdata = false;
      }

      $scope.score = score;
      $scope.storedVariables = persistantData;

      $scope.$apply();

      console.log($scope);
  };

  $scope.runTest = function() {
    $scope.runbutton = "Restart";
    $scope.running = true;

    $scope.soileLog = [];
		$scope.testStartTime = Date.now();

    console.log("Executing soile");

    SOILE2.util.eval($scope.compiledCode);
    SOILE2.util.setEndFunction($scope.endFunc);
    SOILE2.util.setLogFunction($scope.logFunc);

    SOILE2.util.resetData();
    $timeout(function() {
      //SOILE2.run()
      SOILE2.rt.exec_pi();
    }, 1500);
  };

  var editnameurl = $location.absUrl() + "/editname";

  $scope.updateMeta = function() {
    var data = {};
    data.name = $scope.test.name;
    data.folder = $scope.test.folder;
    data.published = $scope.test.published;
    $http.post($location.absUrl(), data);
  };

  $scope.updatename = function(data) {
    console.log("Updating " + $scope.testname + "    " + data);
    $scope.testname = data;
    $scope.test.name = data;
    $http.post(editnameurl, {name: $scope.testname});
    $scope.updateMeta();
  };

  var timer = null;
  var delay = 1000;
  $scope.$watch('test.folder', function(){
    console.log("Watch");
  if(timer){
      $timeout.cancel(timer);
    }  
    timer= $timeout(function(){
        $scope.saveTest();
     },delay);
});

  $scope.initname = function(name) {
    $scope.testname = name;
  };

  $scope.getTest = function() {
    $http({
      url:$location.absUrl() + "/json",
      method:"GET"
    }).then(function(response) {
      $scope.test = response.data;
      console.log(response);
      console.log($scope.test);
    });
  };

  $scope.saveTest = function() {
    $http({
      url:$location.absUrl(),
      method:"POST",
      data:$scope.test
    }).then(function(response) {
      console.log(response);
    });
  };


  $scope.getTest();

  var windowElement = angular.element($window);
  windowElement.on('beforeunload', function (event) {
    if ($scope.editor.getValue() != $scope.lastSave) {
      var answer = confirm("Are you sure you want to leave this page?");
      if (!answer) {
        event.preventDefault();
        return "There are unsaved changed, are you sure you want to leave this page?";
      }
    }
  });

  /* Preventing scoll on arrowkeys and space if test is running and the mouse
     is hovering above the test display */
  document.addEventListener("keydown", function (e) {

    if(!$scope.running) {
      return;
    }

    if([37,38,39,40,32].indexOf(e.keyCode) > -1 && e.target.tagName == "INPUT") {
      return;
    }

    if([37,38,39,40,32].indexOf(e.keyCode) > -1 && mouseTestHover){
      e.preventDefault();
    }

  }, false);
});


//Showing mouse coordinates when hovering over the test display
var mousePos = document.getElementById("mouseposition");
var mouseTestHover = false;

var mouseMove = function (e){
  var displayRect = display.getBoundingClientRect();
  var x = e.clientX - displayRect.left + 0.5;
  var y = e.clientY - displayRect.bottom + displayRect.height;
  var cursor = "Mouse Position: Top " + y.toFixed(0) + " Left: " + x.toFixed(0) ;
  mousePos.innerHTML = cursor;
  mouseTestHover = true;
};

function stopTracking(){
    mousePos.innerHTML="";
    mouseTestHover = false;
}

var display = document.getElementById("display");
var displayRect = display.getBoundingClientRect();

display.onmousemove = mouseMove;
display.onmouseout = stopTracking;

/*Prevent navigation in some cases*/
