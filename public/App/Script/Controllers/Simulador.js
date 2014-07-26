define(['./__module__', 'jquery'], function (controllers, $) {
    'use strict';
    controllers.controller('Simulador', ['$scope', '$http', function ($scope, $http) {

        angular.extend($scope, {
            valorCredito: '',
            valorPrestacao: '',
            prazoRestante: '',
            resultado: '',
            calcular: function () {
                var c = parseFloat(this.valorCredito);
                var p = parseFloat(this.valorPrestacao);
                var t = parseFloat(this.prazoRestante);
                var r = c + p + t;
                if (isNaN(r)) r = '';
                this.resultado = r;
            },
            limpar: function () {
                this.valorCredito =
                    this.valorPrestacao =
                        this.prazoRestante = '';
            }
        });

        $http.get('/assets/App/Mockup/Simulador.json').success(function (data) {
            angular.extend($scope, data);
        });
    }]);
});