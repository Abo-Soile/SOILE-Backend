var app = angular.module('experimentEdit', ['ui.tree', 'ui.bootstrap']);


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

    $scope.delComponent = function(type,index) {
        console.log("Deleting " + type + " : " + index);
        $scope.training.components[type].splice(index, 1);
    }

});

app.controller('experimentController', function($scope, $http, $location) {
    var baseUrl = $location.absUrl();

    $scope.formats = ['dd-MMMM-yyyy', 'yyyy/MM/dd', 'dd.MM.yyyy', 'shortDate'];
    $scope.format = $scope.formats[0];

    function loadData() {
      $http.get("json").success(function(data,status) {
          console.log(data)
          $scope.experiment = data;
      });
    }

    $scope.save = function save() {
        console.log("SSSSAAAVVE")
        var data = $scope.experiment;

        $http.post(baseUrl, data)
    }

    loadData();
});