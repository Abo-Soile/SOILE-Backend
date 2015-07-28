var app = angular.module('trainingEdit', ['ui.tree', 'ui.select', 'ngSanitize']);


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

app.controller('componentController', function($scope, $http, $location) {
    var baseUrl = $location.absUrl();

    $scope.test = {};

    $scope.saveTraining = function save() {
        console.log("SSSSAAAVVE");
        var data = $scope.training;

        $http.post(baseUrl, data);
    }

    $scope.delComponent = function(type,index) {
        console.log("Deleting " + type + " : " + index);
        $scope.training.components[type].splice(index, 1);
    }

    function loadData() {
        $http.get("json").success(function(data,status) {
            console.log(data);
            $scope.training = data;
        });
    }


    $scope.addTest = function() {
      console.log($scope)
      var compObject = {};
      compObject.name = $scope.test.selected.name;
      compObject._id = $scope.test.selected._id;
      compObject.type = "test";

      $scope.training.components.training.push(compObject);

      $scope.test.selected = null

    }

    $scope.addForm = function() {
      $http.post('addform')
      .then(function(response) {
        var data = response.data;

        var compObject = {};
        compObject.name = "";
        compObject.id = data._id;
        compObject.type = "form";

        $scope.training.components.push(compObject);

        $scope.save();
      });
    };

    loadTests = function() {
      return $http.get('/test/json')
      .then(function(response) {
        $scope.tests = response.data;
      });
    }

    $scope.refreshTests = function(search) {
      return $http.get('/test/json')
      .then(function(response) {
        $scope.tests = response.data;
        console.log($scope.tests);
      });
    }

/*
    var arr = [];
    arr.push({name:"aaa", type:"test"});
    arr.push({name:"bbb", type:"form"});
    arr.push({name:"ccc", type:"test"});

    $scope.training = {};
    $scope.training.name = "TESTNAME";

    $scope.components = {};
    $scope.components.pre = [];
    $scope.components.training = [];
    $scope.components.post = [];

    $scope.components.pre = arr;
    $scope.components.training = arr;
    $scope.components.post = arr;

    $scope.training.components = $scope.components;
*/
    loadData();
    loadTests();

});
