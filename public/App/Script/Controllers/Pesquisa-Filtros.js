define(['./__module__'], function (controllers) {
    'use strict';
    controllers.controller('Pesquisa-Filtros', ['$scope', '$http', function ($scope, $http, Location) {
        $http.get('/assets/App/Mockup/Filtros.json'+window.location.search).success(function (data) {
            angular.extend($scope, data);
        });
        $scope.filtrar = function () {
            window.location = "/pesquisa";
        };
    }]);
});