var app = angular.module('experimentAdmin', []);

app.config(function($interpolateProvider){
    $interpolateProvider.startSymbol('[([').endSymbol('])]');
});

app.controller('experimentDataFilterController', function($scope, $http, $location) {
  var baseUrl = $location.absUrl();
  $scope.components = [];
  $scope.testComponents = [];

  $scope.jsonData = {};

  $http.get(baseUrl+"/json").success(function(data) {
    var comps = [];
    for (var i = 0; i < data.components.length; i++) {
      comps.push(i+1);
      if (data.components[i].type === "test") {
        $scope.testComponents.push(i+1);
      }
    }

    $scope.components = comps;
    $scope.f3Components = $scope.getF3Components();
  });

  $scope.getComponents = function() {
    return [1,2,3,4,5,6,7,8];
  };

  $scope.getF3Components = function() {
    return ["test", "form", "all"].concat($scope.components);
  };

  $scope.buildQuery = function() {
    var base = baseUrl + "/loaddata?";
    var query = base;

    if ($scope.filter1 === "single" || $scope.filter1 === "raw") {
      $scope.filter4 = undefined;

      if($scope.filter2 === "confirmed") {
        $scope.filter3 = undefined;
      }
    } 

    if ($scope.filter1 === "raw") {

    }

    query += "f1=" + ($scope.filter1 ? $scope.filter1 : "") + "&";
    query += "f2=" + ($scope.filter2 ? $scope.filter2 : "") + "&";
    query += "f3=" + ($scope.filter3 ? $scope.filter3 : "") + "&";
    query += "f4=" + ($scope.filter4 ? $scope.filter4 : "") + "&";

    console.log(query);

    console.log($scope);

    $http.get(query).success(function(data, status) {
      //console.log(data)

       //var anchor = angular.element('<a/>');
       var anchor = angular.element( document.querySelector( '#dlLink' ) );

       anchor.attr({
           href: 'data:attachment/csv;charset=utf-8,' + encodeURI(data),
           target: '_blank',
           download: 'data.csv'
       })
       //[0].click();

      CSV.RELAXED = true;
      CSV.COLUMN_SEPARATOR = ";";

      var jsonData = CSV.parse(data);

      $scope.datarows = jsonData;
      $scope.downloadData = true;

      console.log($scope.datarows)

    });
  };
});