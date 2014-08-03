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
        $scope.administradora = {
            "selecionado": "-1",
            "lista": []
        };
        $scope.contemplacao = {
            "selecionado": "-1",
            "lista": []
        };

        function inicializarModeloForm() {
            $scope.formData = {
                "resp": {
                    "result": "",
                    "message": ""
                },
                "fields": {
                    "tipo": {
                        "value": "",
                        "error": ""
                    },
                    "administradora": {
                        "value": "",
                        "error": ""
                    },
                    "contemplacao": {
                        "value": "",
                        "error": ""
                    },
                    "prazoRestante": {
                        "value": "",
                        "error": ""
                    },
                    "valorCredito": {
                        "value": "",
                        "error": ""
                    },
                    "valorEntrada": {
                        "value": "",
                        "error": ""
                    },
                    "valorPrestacoes": {
                        "value": "",
                        "error": ""
                    },
                    "cota": {
                        "value": "",
                        "error": ""
                    }
                }
            };
        };
        inicializarModeloForm();


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

        $scope.enviarForm = function () {
            var query = $.param($scope.selecao);
            if (confirm($scope.strings.textoConfirmar))
                console.log(query);
            //window.location = "/novacarta" + query;
        };

        aoAbrir();
    }]);
});