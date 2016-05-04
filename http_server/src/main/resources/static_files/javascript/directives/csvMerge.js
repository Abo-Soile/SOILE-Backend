var myApp = angular.module('csvmerge', ['angularFileUpload']);

myApp.config(['$compileProvider', function ($compileProvider) {
    $compileProvider.aHrefSanitizationWhitelist(/^\s*(|blob|):/);
}]);


myApp.directive('csvmerge', ['$sce','$http','FileUploader', function($sce,$http, FileUploader) {
  return {
    restrict:"AE",
    templateUrl:"/javascript/directives/csvMerge.html",
    controller: function($scope, $window) {
     
      var dsv = d3.dsv(";","text/plain");
      var levenshtein = window.Levenshtein;

      CSV.RELAXED = true;
      CSV.COLUMN_SEPARATOR = ";";

      $scope.file1 = null;
      $scope.file2 = null;

      $scope.csv1 = null;
      $scope.csv2 = null;

      $scope.mergeString1 = "";
      $scope.mergeString2 = "";

      $scope.mergeOptions1 = [];
      $scope.mergeOptions2 = [];

      $scope.mergedData = [];

      $scope.fuzzy = false;
      $scope.fuzzyThreshold = 0;

      function combineObjects(obj1, obj2) {
        var obj3 = {};
        for (var attrname in obj1) { obj3[attrname] = obj1[attrname]; }
        for (var attrname in obj2) { obj3[attrname] = obj2[attrname]; }
        return obj3;
      }

      function findHeaders (dataArray) {
        var headers = [];
        for (var i = 0; i < dataArray.length; i++) {
          for(var key in dataArray[i]) {
            if (dataArray[i].hasOwnProperty(key)) {
              if (headers.indexOf(key) == -1) {
                headers.push(key);
              }
            }
          }
        }

        return headers;
      };

      var uploader1 = $scope.uploader1 = new FileUploader({
      });

      uploader1.onAfterAddingFile = function(fileItem) {
          $scope.file1 = fileItem._file;
          var reader = new FileReader();
          reader.readAsText($scope.file1);

          reader.onload = function() {
            var res = reader.result;
            $scope.csv1 = [];

            dsv.parse(res, function(d) {
              
              $scope.csv1.push(d);
            });

            $scope.mergeOptions1 = findHeaders($scope.csv1);
            $scope.$apply();
          };
      };

      var uploader2 = $scope.uploader2 = new FileUploader({
      });

      uploader2.onAfterAddingFile = function(fileItem) {
          $scope.file2 = fileItem._file;

          var reader = new FileReader();
          reader.readAsText($scope.file2);

          reader.onload = function() {
            var res = reader.result;
            $scope.csv2 = [];

            dsv.parse(res, function(d) {
              
              $scope.csv2.push(d);
            });

            $scope.mergeOptions2 = findHeaders($scope.csv2);
            $scope.$apply();
          };
      };

      $scope.mergeData = function() {

        $scope.mergedData = [];

        $scope.csv1.forEach(function(row) {
          var resObj = {};
          //console.log(row);
          var matche = _.find($scope.csv2, function(data) {

            if (data[$scope.mergeString2] == "" || row[$scope.mergeString1] == "") {
              return false;
            }

            if ($scope.fuzzy) {
                console.log("Levenshtein distance :" + $scope.fuzzyThreshold + " - " + levenshtein.get(data[$scope.mergeString2], row[$scope.mergeString1]) +
                 " -- " + data[$scope.mergeString2] + " == " + row[$scope.mergeString1])
              if(levenshtein.get(data[$scope.mergeString2], row[$scope.mergeString1]) <= $scope.fuzzyThreshold) {
               
                return true;
              }
              return false;

            }else {
              if (data[$scope.mergeString2] == row[$scope.mergeString1]) {
                console.log(data[$scope.mergeString2] + " == " + row[$scope.mergeString1])
                return true;
              } else {
                return false;
              }
            }
          });
          resObj = combineObjects(row, matche);
          //console.log(matches);          

          $scope.mergedData.push(resObj);
        });

        console.log($scope.mergedData);
        
        $scope.mergedFile = dsv.format($scope.mergedData);

        var blob= new Blob([$scope.mergedFile], {type: 'text/csv'});

        $scope.url = $window.URL || $window.webkitURL;
        var url = $scope.url.createObjectURL(blob);
        $scope.fileUrl = $sce.trustAsResourceUrl(url);
        $scope.downloadData = true;
        console.log("MERGEEEEE")

        //console.log($scope.mergedFile);
      };
      
    }
  };
}]);