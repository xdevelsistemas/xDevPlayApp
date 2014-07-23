define(['./__module__'], function (controllers) {
    'use strict';
    controllers.controller('Pesquisa-Resultados', function ($scope, $http) {
        $http.get('/assets/App/Mockup/ResultadosPesquisa.json').success(function (data) {
            angular.extend($scope, data);
        });
        $scope.filtrar = function () {
            window.alert("Thanks!");
        };
    });
});
