var app = angular.module('testEditor', ['ui.ace', 'angularFileUpload', 'xeditable'])


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
		var imgUrl = baseUrl + "/imageupload"
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

  ace.config.set("modePath", "/javascript");

	$scope.aceLoaded = function(_editor) {
    // Options
    $scope.editor = _editor;
    //$scope.editor.config.set("modePath", "/javascript");
    $scope.editor.getSession().setMode("ace/mode/elanghighlightrules");
    $scope.editor.renderer.setShowGutter(true); 

    $scope.lastSave = $scope.editor.getValue();

  };

  $scope.compileTest = function() {
  	var code = {"code":$scope.editor.getValue()};

    $scope.savebutton = '<i class="fa fa-spinner fa-spin"></i> Compiling';

  	$http.post($location.absUrl(), code).success(function(data, status, headers, config) {
  		
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

      $scope.$apply();

      console.log($scope);
  };

  $scope.runTest = function() {
    $scope.runbutton = "Running";

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

  var windowElement = angular.element($window);
  windowElement.on('beforeunload', function (event) {
    if ($scope.editor.getValue() != $scope.lastSave) {
      var answer = confirm("Are you sure you want to leave this page?")
      if (!answer) {
        event.preventDefault();
        return "There are unsaved changed, are you sure you want to leave this page?";
      }
    }
  });
});

app.controller('editNameController', function($scope, $http, $location) {
  var editnameurl = $location.absUrl() + "/editname";

  $scope.updatename = function(data) {
    console.log("Updating " + $scope.testname + "    " + data);
    $scope.testname = data;
    $http.post(editnameurl, {name: $scope.testname});
  };

  $scope.initname = function(name) {
    console.log("initing name: " + name);
    $scope.testname = name;
  };
});

//Showing mouse coordinates when hovering over the test display
var mousePos = document.getElementById("mouseposition");

var mouseMove = function (e){
  var displayRect = display.getBoundingClientRect();
  var x = e.clientX - displayRect.left + 0.5;
  var y = e.clientY - displayRect.bottom + displayRect.height;
  var cursor = "Mouse Position: Top " + y.toFixed(0) + " Left: " + x.toFixed(0) ;
  mousePos.innerHTML = cursor;
};

function stopTracking(){
    mousePos.innerHTML="";
}

var display = document.getElementById("display");
var displayRect = display.getBoundingClientRect();

display.onmousemove = mouseMove;
display.onmouseout = stopTracking;


document.addEventListener("keydown", function (e) {
  if([37/*,38,39*/,40].indexOf(e.keyCode) > -1){
    e.preventDefault();
    // Do whatever else you want with the keydown event (i.e. your navigation).
  }
}, false);