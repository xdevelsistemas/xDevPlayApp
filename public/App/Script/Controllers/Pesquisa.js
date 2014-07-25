define(['./__module__', 'jquery'], function (controllers, $) {
    'use strict';
    controllers.controller('Pesquisa', ['$scope', '$http', 'Location', function ($scope, $http, Location) {
        var QUERY_FILTROS = window.location.search;
        console.log('QUERY_FILTROS', ':', QUERY_FILTROS);

        $http.get('/assets/App/Mockup/Filtros.json' + QUERY_FILTROS).success(function (data) {
            angular.extend($scope, data);
        });
        $http.get('/assets/App/Mockup/ResultadosPesquisa.json' + QUERY_FILTROS).success(function (data) {
            angular.extend($scope, data);
        });

        $scope.buscarResultados = function () {
            var objQuery = Location.toObject(QUERY_FILTROS);
            console.log('objQuery', objQuery);
            var query = $.param(angular.extend(objQuery), {
                ordenador: $scope.ordenadores.selecionado,
                ordem: $scope.ordem.selecionada,
                pagina: $scope.paginas.selecionada
            });
            console.log(query);
            $http.get('/assets/App/Mockup/ResultadosPesquisa.json' + '?' + query).success(function (data) {
                angular.extend($scope, data);
            });
        };

        $scope.ordenarResultado = function () {
            $scope.paginas.selecionada = '1';
            $scope.buscarResultados();
        }

        $scope.passarPagina = function () {
            var p = parseInt($scope.paginas.selecionada);
            var t = parseInt($scope.paginas.total);
            console.log('p', p);
            if (p < t) {
                console.log('menor que o total(' + t + ')');
                $scope.paginas.selecionada = p + 1;
                $scope.buscarResultados();
            }
            console.log('selecionada', $scope.paginas.selecionada);
        }

        $scope.voltarPagina = function () {
            var p = parseInt($scope.paginas.selecionada);
            console.log('p', p);
            if (p > 1) {
                console.log('maior que 1');
                $scope.paginas.selecionada = p - 1;
                $scope.buscarResultados();
            }
            console.log('selecionada', $scope.paginas.selecionada);
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


/*

 {
 "codigo": ,
 "descricao": ,
 }

 {
 "ordenador": "preco",
 "ordem": "asc",
 "pagina": "1"
 }


 * */