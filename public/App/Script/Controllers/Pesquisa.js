define(['./__module__', 'jquery'], function (controllers, $) {
    'use strict';
    controllers.controller('Pesquisa', ['$scope', '$http', 'Location', function ($scope, $http, Location) {
        var QUERY_FILTROS = window.location.search;

        $http.get('/assets/App/Mockup/Filtros.json' + QUERY_FILTROS).success(function (data) {
            angular.extend($scope, data);
        });
        $http.get('/assets/App/Mockup/ResultadosPesquisa.json' + QUERY_FILTROS).success(function (data) {
            angular.extend($scope, data);
        });

        $scope.buscarResultados = function () {
            var objQuery = Location.toObject(QUERY_FILTROS);
            var query = $.param(angular.extend(objQuery, {
                ordenador: $scope.ordenadores.selecionado,
                ordem: $scope.ordem.selecionada,
                pagina: $scope.paginas.selecionada
            }));
            $http.get('/assets/App/Mockup/ResultadosPesquisa.json' + '?' + query).success(function (data) {
                angular.extend($scope, data);
            });
        };

        $scope.ordenarResultado = function () {
            $scope.paginas.selecionada = 1;
            $scope.buscarResultados();
        }

        $scope.passarPagina = function () {
            var p = parseInt($scope.paginas.selecionada);
            var t = parseInt($scope.paginas.total);
            if (p < t) {
                $scope.paginas.selecionada = p + 1;
                $scope.buscarResultados();
            }
        }

        $scope.voltarPagina = function () {
            var p = parseInt($scope.paginas.selecionada);
            if (p > 1) {
                $scope.paginas.selecionada = p - 1;
                $scope.buscarResultados();
            }
        }

        $scope.ultimaPagina = function () {
            $scope.paginas.selecionada = $scope.paginas.total;
            $scope.buscarResultados();
        }
        $scope.primeiraPagina = function () {
            $scope.paginas.selecionada = 1;
            $scope.buscarResultados();
        }

    }]);
});