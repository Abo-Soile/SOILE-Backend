var app = angular.module('trainingAdmin', ['angularMoment']);

app.config(function($interpolateProvider){
    $interpolateProvider.startSymbol('[([').endSymbol('])]');
});


/*Marks html as safe*/
app.filter('unsafe', function($sce) { return $sce.trustAsHtml; });

app.service('overviewService', function($http, $location, $q) {
    this.users = [];
    this.training = null;

    var promise = false;

    var deferred = $q.defer();

    var that = this;

    //Gets the list of nuclear weapons
    this.getUsers = function() {
      if (!promise) {
        promise = $http.get($location.absUrl() + "/useroverview").success(function(data,status) {
          //users = JSON.parse(data.participants);
          that.users = data.participants.map(function(obj){return JSON.parse(obj)});
          that.training = JSON.parse(data.training);

          console.log("RESOLVING THEN")
          deferred.resolve("success");

        });
      }

      return deferred.promise;
    };

    // Fill the list with actual nukes, async why not.
    //this.getUsers();

    /*return {
      users:users,
      training:training
    };*/


        // expose more functions or data if you want

});


app.controller('userProgressController', function($scope, $http, $location, overviewService) {
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
    /*$http.get($location.absUrl() + "/useroverview").success(function(data,status) {
        console.log("Load ing data");
      console.log(data);
      //$scope.participants = JSON.parse(data.participants);
      $scope.participants = data.participants.map(function(obj){return JSON.parse(obj)});
      $scope.training = JSON.parse(data.training);*/
    overviewService.getUsers().then(function() {

      $scope.participants = overviewService.users;
      $scope.training = overviewService.training;

      for (var i = 0; i < $scope.participants.length; i++) {

        $scope.participants[i].timestamp = new Date($scope.participants[i].timestamp);

        var hoursTilDone = 0;
        hoursTilDone = (2 + parseInt($scope.training.repeatcount)) * $scope.training.maxpause;
        var p = $scope.participants[i];
        if(p.mode === "done") {
          $scope.participants[i].percentageDone = 100;
          hoursTilDone = 0;
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


app.controller('trainingDataFilterController', function($scope, $http, $location, overviewService) {
  var baseUrl = $location.absUrl();

  $scope.downloadData = false;

  $scope.getUsers = function() {
    return $scope.users;
  };


  /*
    Returns a array with trainingiterations
  */
  $scope.getIterations = function() {
    var arr = [];
    var iterations = $scope.training.repeatcount;
    for (var i = 0; i < iterations; i++) {
      arr.push(i+1);
    }

    return arr;
  };
  /*
    Returns an array with component numbers
  */
  $scope.getComponentIterations = function(phase) {
    var arr = [];
    var iterations = $scope.training.components[phase].length;
    console.log($scope.training.components[phase].length);
    for (var i = 0; i < iterations; i++) {
      arr.push(i+1);
    }

    return arr;
  };

  $scope.buildQuery = function() {
    var base = baseUrl + "/loaddata?";
    var query = base;

    if ($scope.filter1 === "pre" || $scope.filter1 === "post") {
      $scope.filter4 = undefined;

      if($scope.filter2 === "single") {
        $scope.filter3 = undefined;
      }
    } 

    if ($scope.filter1 === "training") {

      if($scope.filter3 === "single") {
        $scope.filter4 = undefined;
      }
    }

    query += "f1=" + ($scope.filter1 ? $scope.filter1 : "") + "&";
    query += "f2=" + ($scope.filter2 ? $scope.filter2 : "") + "&";
    query += "f3=" + ($scope.filter3 ? $scope.filter3 : "") + "&";
    query += "f4=" + ($scope.filter4 ? $scope.filter4 : "") + "&";

    console.log(query);

    $http.get(query).success(function(data, status) {

      $scope.downloadData = true;
      var link = angular.element( document.querySelector( '#dlLink' ) );

       link.attr({
           href: 'data:attachment/csv;charset=utf-8,' + encodeURI(data),
           target: '_blank',
           download: 'data.csv'
       });

       console.log(link)

       CSV.RELAXED = true;
       CSV.COLUMN_SEPARATOR = ";";

       var jsonData = CSV.parse(data);

       $scope.datarows = jsonData;


    });
  };

  overviewService.getUsers().then(function() {
    $scope.users = overviewService.users;
    $scope.training = overviewService.training;
  });

 /* overviewService.loadData(function(data) {
    $scope.users = data.users;
  });*/
});