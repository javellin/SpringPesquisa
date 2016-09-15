app.controller("loginCtrl", ["$scope", "$http", "$routeParams", function ($scope, $http, $routeParams) {
    $scope.erro = $routeParams.error;
}]);