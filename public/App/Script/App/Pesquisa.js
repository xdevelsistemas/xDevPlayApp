angular.module('Pesquisa', [])
    .controller('Filtros', function ($scope, $http) {
        $http.get('/assets/App/Mockup/Filtros.json').success(function (data) {
            angular.extend($scope, data);
        });
        $scope.filtrar = function () {
            window.alert("Thanks!");
        };
    })
    .controller('Resultados', function ($scope, $http) {
        $http.get('/assets/App/Mockup/ResultadosPesquisa.json').success(function (data) {
            angular.extend($scope, data);
        });
        $scope.filtrar = function () {
            window.alert("Thanks!");
        };
    });