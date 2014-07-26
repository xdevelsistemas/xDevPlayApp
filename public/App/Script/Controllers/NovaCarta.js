define(['./__module__', 'jquery'], function (controllers, $) {
    'use strict';
    controllers.controller('NovaCarta', ['$scope', '$http', function ($scope, $http) {

        angular.extend($scope, {
            "selecao": {
                tipo: "-1",
                administradora: "-1",
                status: "-1",
                prazoRestante: "",
                valorCredito: "",
                valorEntrada: "",
                valorPrestacao: "",
                cota: ""
            },
            tipos: []
        });

        $http.get('/assets/App/Mockup/NovaCarta.json').success(function (data) {
            angular.extend($scope, data);
            console.log($scope);
        });

        $scope.confirmar = function () {
            var query = $.param($scope.selecao);
            if (confirm($scope.strings.textoConfirmar))
                console.log(query);
            //window.location = "/novacarta" + query;
        };

    }]);
});