var app = angular.module("selecaoApp", ['ngRoute', 'ngMask']);

app.config(function ($routeProvider, $httpProvider) {

    $httpProvider.interceptors.push('responseObserver');

    $routeProvider.when("/cadastro", {
        templateUrl: "/publico/cadastro.html",
        controller: "cadastroCtrl"
    }).when("/login", {
        templateUrl: "/publico/login.html",
        controller: "loginCtrl"
    }).when("/admin", {
        templateUrl: "/admin/admin.html",
        controller: "adminCtrl"
    }).when("/comum", {
        templateUrl: "/comum/comum.html",
        controller: "comumCtrl"
    }).otherwise({
        templateUrl: "/publico/login.html"
    });
});

app.factory('responseObserver', function responseObserver($q, $window) {
    return {
        'responseError': function (errorResponse) {
            switch (errorResponse.status) {
                case 401:
                    $window.location = '/#/login';
                    break;
                case 403:
                    $window.location = '/#/login';
            }
            return $q.reject(errorResponse);
        }
    };
});