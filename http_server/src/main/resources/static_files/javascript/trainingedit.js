var app = angular.module('trainingEdit', []);


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

    var arr = [];
    arr.push({name:"aaa", type:"test"});
    arr.push({name:"bbb", type:"form"});
    arr.push({name:"ccc", type:"test"});

    $scope.components = {};
    $scope.components.pre = [];
    $scope.components.training = [];
    $scope.components.post = [];

    $scope.components.pre = arr;
    $scope.components.training = arr;
    $scope.components.post = arr;


});
