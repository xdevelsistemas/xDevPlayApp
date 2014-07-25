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
            var query = $.param(angular.extend(Location.toObject(QUERY_FILTROS), {
                ordenador: $scope.ordenadores.selecionado,
                ordem: $scope.ordem.selecionada,
                pagina: $scope.paginas.selecionada
            }));
            console.log(query);
            $http.get('/assets/App/Mockup/ResultadosPesquisa.json' + '?' + query).success(function (data) {
                angular.extend($scope, data);
            });
        };

        $scope.ordenarResultado = function () {
            $scope.paginas.selecionada = '1';
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