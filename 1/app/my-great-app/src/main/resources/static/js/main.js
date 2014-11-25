angular.module("myGreatApp", [])
    .controller("mainCtrl", ['$scope', '$http', function($scope, $http) {
    $scope.initialize = function() {
        $scope.firstname = '';
        $scope.lastname = '';
        $scope.email = '';
        $scope.contacts = [];
        $http.get('/address').success(function(data) {
            $scope.contacts = data;
        });
    };

    $scope.add = function() {
        var contact = {
            firstname:  $scope.firstname,
            lastname:   $scope.lastname,
            email:      $scope.email
        };

        $http.post("/address", contact).success(function() {
            $scope.initialize();
        });
    };

    $scope.del = function(id) {
        $http.delete('/address/'+id).success(function() {
            $scope.initialize();
        });
    };

    $scope.initialize();
}]);