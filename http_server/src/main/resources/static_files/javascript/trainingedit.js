var app = angular.module('trainingEdit', ['ui.tree']);


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

    $scope.saveTraining = function save() {
        console.log("SSSSAAAVVE")
        var data = $scope.training;

        $http.post(baseUrl, data)
    }

    $scope.delComponent = function(type,index) {
        console.log("Deleting " + type + " : " + index);
        $scope.training.components[type].splice(index, 1);
    }

    function loadData() {
        $http.get("json").success(function(data,status) {
            console.log(data)
            $scope.training = data;
        });
    }

    var arr = [];
   /* arr.push({name:"aaa", type:"test"});
    arr.push({name:"bbb", type:"form"});
    arr.push({name:"ccc", type:"test"});*/

    $scope.training = {};
    $scope.training.name = "TESTNAME";

   /* $scope.components = {};
    $scope.components.pre = [];
    $scope.components.training = [];
    $scope.components.post = [];

    $scope.components.pre = arr;
    $scope.components.training = arr;
    $scope.components.post = arr;

    $scope.training.components = $scope.components*/

    loadData();
});
