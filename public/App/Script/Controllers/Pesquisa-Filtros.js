define(['./__module__'], function (controllers) {
    'use strict';
    controllers.controller('Pesquisa-Filtros', function ($scope, $http) {
        $http.get('/assets/App/Mockup/Filtros.json').success(function (data) {
            angular.extend($scope, data);
        });
        $scope.filtrar = function () {
            window.alert("Thanks!");
        };
    })
});
