define(['./__module__', 'jquery'], function (controllers, $) {
    'use strict';
    controllers.controller('NovaCarta', ['$scope', '$http', function ($scope, $http) {

        $scope.strings = {};
        $scope.selecao = {
            tipo: "-1",
            administradora: "-1",
            status: "-1",
            prazoRestante: "",
            valorCredito: "",
            valorEntrada: "",
            valorPrestacao: "",
            cota: ""
        };
        $scope.tipo = {
            "selecionado": "-1",
            "lista": []
        };
        $scope.administradora = {
            "selecionado": "-1",
            "lista": []
        };
        $scope.contemplacao = {
            "selecionado": "-1",
            "lista": []
        };

        function aoAbrir() {
            $http.get("/assets/App/Mockup/NovaCarta/strings.json").success(function (data) {
                angular.extend($scope.strings, data);
            });
            $http.get("/assets/App/Mockup/Filtros/tipo.json").success(function (data) {
                angular.extend($scope.tipo, data);
            });
            $http.get("/assets/App/Mockup/Filtros/administradora.json").success(function (data) {
                angular.extend($scope.administradora, data);

            });
            $http.get("/assets/App/Mockup/Filtros/contemplacao.json").success(function (data) {
                angular.extend($scope.contemplacao, data);
            });

            return;
            $http.get('/assets/App/Mockup/NovaCarta.json').success(function (data) {
                angular.extend($scope, data);
                console.log($scope);
            });
        }

        $scope.confirmar = function () {
            var query = $.param($scope.selecao);
            if (confirm($scope.strings.textoConfirmar))
                console.log(query);
            //window.location = "/novacarta" + query;
        };

        aoAbrir();
    }]);
});