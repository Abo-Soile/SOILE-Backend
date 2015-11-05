var app = angular.module('trainingAdmin', ['angularMoment']);

app.config(function($interpolateProvider){
    $interpolateProvider.startSymbol('[([').endSymbol('])]');
});


/*Marks html as safe*/
app.filter('unsafe', function($sce) { return $sce.trustAsHtml; });


app.controller('userProgressController', function($scope, $http, $location) {
  var baseUrl = $location.absUrl();

  $scope.rowClass = function(user){
    if (user.mode == "done") {
      return "completed"
    }
    else {
      return "";
    }
  };


  $scope.loadData = function() {
    $http.get($location.absUrl() + "/useroverview").success(function(data,status) {
        console.log("Load ing data");
        console.log(data);
      //$scope.participants = JSON.parse(data.participants);
      $scope.participants = data.participants.map(function(obj){return JSON.parse(obj)});
      $scope.training = JSON.parse(data.training);

      for (var i = 0; i < $scope.participants.length; i++) {

        $scope.participants[i].timestamp = new Date($scope.participants[i].timestamp);

        var hoursTilDone = 0;
        hoursTilDone = (2 + $scope.training.repeatcount) * $scope.training.maxpause;

        var p = $scope.participants[i];
        if(p.mode === "done") {
          $scope.participants[i].percentageDone = 100;
          hoursTilDone = 0
        }

        if(p.mode === "pre") {
          $scope.participants[i].percentageDone = 0;
        }

        if(p.mode === "training") {
          console.log("Pos " + (parseInt(p.trainingIteration)+1) + " repeatcount " + (parseInt($scope.training.repeatcount)+2)  + " res " + (parseInt(p.position) + 1)/(parseInt($scope.training.repeatcount) + 2));
          $scope.participants[i].percentageDone = parseInt((parseInt(p.trainingIteration) + 1)/(parseInt($scope.training.repeatcount) + 2)*100);
          hoursTilDone -= (1 + p.trainingIteration) * $scope.training.maxpause;
        }

        if(p.mode === "post") {
          $scope.participants[i].percentageDone = ($scope.training.repeatcount + 2 - 1)/($scope.training.repeatcount + 2)*100;
          hoursTilDone = $scope.training.maxpause;
        }

        p.hoursTilDone = moment().add(hoursTilDone, "hours");

      }

    $scope.participants.sort(function(a, b){
        if(a.timestamp < b.timestamp) return -1;
        if(a.timestamp > b.timestamp) return 1;
        return 0;
    });

    });
  };

  $scope.loadData();
});