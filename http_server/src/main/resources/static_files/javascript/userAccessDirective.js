var mApp = angular.module('useraccess', []);


/*
Directive for managing user access to a certain component. A component is viewable
by all admins and all editors who have been granted permission.
*/
mApp.directive('useraccess',  ['$http', function($http) {
    return {
        restrict: 'AE',
        scope: {
          users:"=",
          savefunction:"="
        },
        template: "<div class='ng-useraccess'><h1>Users</h1>"+
            "<div class='col-md-4 form-group form-inline'><button class='btn btn-primary' ng-click='addUser(selectedUser)'>Add</button>" +
            "<select style='width:200px;' class='form-inline form-control' ng-model='selectedUser'"+
                "ng-options='userList.indexOf(u) as u.username for u in userList'>" +
            "</select></div>" + 
            "<div class='col-md-8'><span ng-repeat='user in users'>{{user}}" + 
            "<span class='label label-danger' ng-click='removeUser(user)'>Remove</span></span>" +
            "</div></div>",
        link:function (scope, element, attrs) {

          //Array of already added users
          scope.addedUsers = [];

          scope.loadUsers = function() {
          
            var url = scope.address;
          };

          /*Load list of editors*/
          scope.loadUserList = function() {
            var userUrl = "/admin/user/json/editor/filter";

            $http.get(userUrl).success(function(data) {
              scope.userList = data;

              /*Removing already added users from the list*/
              for (var i = 0; i < scope.users.length; i++) {
                var usr = scope.users[i];
                for (var j = 0; j < scope.userList.length; j++) {
                  if (scope.userList[j].username === usr) {
                    scope.addedUsers.push(scope.userList.splice(j, 1)[0]);
                  }
                }
              }

            });
          };

          /*
            Add user to component and calls the save function
          */
          scope.addUser = function(userIndex) {
            if (typeof scope.users === "undefined") {
              scope.users = [];
            }

            var user = scope.userList[userIndex];

            var index = userIndex;
            if (index > -1) {
                scope.addedUsers.push(scope.userList.splice(index, 1)[0]);
            }

            scope.users.push(user.username);
            if(scope.savefunction) {
              scope.savefunction(scope.users);
            }
          };

          /*
            Removes user from the user list and re-adds him to the available users list 
          */
          scope.removeUser = function(user) {
            console.log("Removing useraccess " + user);

            var removeUser = scope.users.indexOf(user);
            scope.users.splice(removeUser, 1);

            for (var i = 0; i < scope.addedUsers.length; i++) {
              console.log(scope.addedUsers[i]);
              if (scope.addedUsers[i].username == user) {
                var u = scope.addedUsers.splice(i, 1)[0];
                scope.userList.push(u);
                break;
              }
            }

            console.log(scope.savefunction);
            console.log(typeof savefunction);
            if(scope.savefunction) {
              scope.savefunction(scope.users);
            }

          };
          scope.loadUserList();
        }
    };
}]);
