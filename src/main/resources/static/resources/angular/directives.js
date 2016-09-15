app.directive('navbar', function () {
    return {
        restrict: 'E',
        transclude: true,
        templateUrl: 'tags/navbar.html'
    }
});

app.directive('mensagens', function () {
    return {
        restrict: 'E',
        transclude: true,
        templateUrl: 'tags/mensagens.html'
    }
});