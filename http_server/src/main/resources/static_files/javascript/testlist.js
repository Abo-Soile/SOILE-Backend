var app = angular.module('testlist', []);


app.config(function($interpolateProvider){
    $interpolateProvider.startSymbol('[([').endSymbol('])]');
});

app.service('testService', function($http) {

    var folderUrl = "/test/folder"

    function getFolderUrl(foldername) {
      return "/test/folder/" + foldername + "/json";
    }


    this.listFolders = function() {
      var url = "/test/folder/json";

      return( $http.get(url))
    }
 
    this.listTests = function(folder) {
      
      var url = "";
      //No folder, getting all tests
      if(typeof folder === 'undefined' || folder === "") {
        url = "/test/json";
        return($http.get(url));
      } else {
        url = getFolderUrl(folder);
        return($http.get(url));
      }         
    };
});

app.controller('TestListController', function($scope, $http, $location, testService) {
  $scope.selectedFolder = "All";
  $scope.active = 0;


  testService.listFolders().then(function(result) {
    $scope.folders = result.data;
    $scope.folders.unshift("All");
    $scope.folders.unshift("unspecified");
  });

  testService.listTests().then(function(result) {
    $scope.tests = result.data;
  });

  $scope.pickFolder = function(folderID) {
    $scope.selectedFolder = $scope.folders[folderID];
    $scope.active = folderID;

    var folderName = "";

    if($scope.selectedFolder !== "All") {
      folderName = $scope.selectedFolder;
    }

    testService.listTests(folderName).then(function(res) {
      $scope.tests = res.data;
    })
  }
})