var app = angular.module('trainingEdit', 
                      ['ui.tree',
                       'ui.select',
                       'ngSanitize',
                       'ui.bootstrap']);


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

app.filter('range', function() {
  return function(input, min, max) {
    min = parseInt(min); //Make string input int
    max = parseInt(max);
    for (var i=min; i<max; i++){
          input.push(i);
        }
    return input;
  };
});

app.controller('trainingController', function($scope, $http, $location) {
  var baseUrl = $location.absUrl();

  $scope.format = 'yyyy/MM/dd';


  $scope.training = {};

  $scope.saveTraining = function save() {
      console.log("SSSSAAAVVE");
      var data = $scope.training;

      console.log($scope.training);

      $http.post(baseUrl, data);
  };

  $scope.loadData = function() {
      $http.get("json").success(function(data,status) {
          console.log(data);
          $scope.training = data;
          console.log($scope);
      });
  };

  $scope.toggleControlGroup = function(event) {
      console.log(event);
      if ($scope.training.controlgroup) {
          $scope.training.components.control = [];
      } else {
          delete($scope.training.components.control);
      }
  };

  $scope.open = function($event) {
    $event.preventDefault();
    $event.stopPropagation();

    console.log("OPEN");

    $scope.opened = true;
  };

  $scope.dateOptions = {
    formatYear: 'yy',
    startingDay: 1
  };


  $scope.loadData();
});

app.controller('componentController', function($scope, $http, $location) {

  $scope.test = {};

  $scope.delComponent = function(type,index) {
      console.log("Deleting " + type + " : " + index);
      $scope.training.components[type].splice(index, 1);
  };


  $scope.addTest = function() {
    console.log($scope);
    var compObject = {};
    compObject.name = $scope.test.selected.name;
    compObject.id = $scope.test.selected._id;
    compObject.type = "test";

    $scope.training.components.training.push(compObject);

    $scope.test.selected = null;

  };

  $scope.addForm = function() {
    $http.post('addform')
    .then(function(response) {
      var data = response.data;

      $scope.loadData();
    });
  };

  var loadTests = function() {
    return $http.get('/test/json/compiled')
    .then(function(response) {
      $scope.tests = response.data;
    });
  };

  $scope.refreshTests = function(search) {
    return $http.get('/test/json/compiled')
    .then(function(response) {
      $scope.tests = response.data;
      console.log($scope.tests);
    });
  };

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
  loadTests();
});
