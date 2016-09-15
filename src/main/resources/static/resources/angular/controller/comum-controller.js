app.controller("comumCtrl", ["$scope", "$http", function ($scope, $http) {

    $scope.usuario = {};

    $scope.buscarUsuarioAtual = function () {
        $http.get("/servicos/usuario/buscarUsuarioAtual", {}).success(function (response) {
            $scope.usuario = response;
        });
    }
    $scope.buscarUsuarioAtual();

}]);