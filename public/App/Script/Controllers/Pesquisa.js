define(['./__module__', 'jquery'], function (controllers, $) {
    'use strict';
    controllers.controller('Pesquisa', ['$scope', '$http', 'QueryString', function ($scope, $http, QueryString) {
        var QUERY_FILTROS = window.location.search;

        $scope.paginas = {
            selecionada: 1,
            total: 10
        }

        $http.get('/assets/App/Mockup/Filtros.json' + QUERY_FILTROS).success(function (data) {
            angular.extend($scope, data);
        });
        $http.get('/assets/App/Mockup/ResultadosPesquisa.json' + QUERY_FILTROS).success(function (data) {
            angular.extend($scope, data);
        });

        $scope.buscarResultados = function () {
            var objQuery = QueryString.toObject(QUERY_FILTROS);
            var query = $.param(angular.extend(objQuery, {
                ordenador: $scope.ordenadores.selecionado,
                ordem: $scope.ordem.selecionada,
                pagina: $scope.paginas.selecionada
            }));
            $http.get('/assets/App/Mockup/ResultadosPesquisa.json' + '?' + query).success(function (data) {
                angular.extend($scope, data);
            });
        };

        $scope.irParaPagina = function (codigo) {
            $scope.paginas.selecionada = codigo;
            $scope.buscarResultados();
        };

        $scope.ordenarResultado = function () {
            $scope.paginas.selecionada = 1;
            $scope.buscarResultados();
        };

        $scope.passarPagina = function () {
            var p = parseInt($scope.paginas.selecionada);
            var t = parseInt($scope.paginas.total);
            if (p < t) {
                $scope.paginas.selecionada = p + 1;
                $scope.buscarResultados();
            }
        };
        $scope.voltarPagina = function () {
            var p = parseInt($scope.paginas.selecionada);
            if (p > 1) {
                $scope.paginas.selecionada = p - 1;
                $scope.buscarResultados();
            }
        };

        $scope.ultimaPagina = function () {
            $scope.paginas.selecionada = $scope.paginas.total;
            $scope.buscarResultados();
        };
        $scope.primeiraPagina = function () {
            $scope.paginas.selecionada = 1;
            $scope.buscarResultados();
        };
        $scope.paginaSelecionada = function (codigo) {
            return (parseInt(codigo) == $scope.paginas.selecionada);
        };
        $scope.paginasPassadas = function () {
            var p = parseInt($scope.paginas.selecionada);
            var t = parseInt($scope.paginas.total);
            if (t < 5) return 1;
            else if (p > t - 2) return p - (4 - (t - p));
            else if (p > 2) return p - 2;
            else return 1;
        };
        $scope.paginasExibidas = function () {
            var t = parseInt($scope.paginas.total);
            return t < 5 ? t : 5;
        }
    }]);
});