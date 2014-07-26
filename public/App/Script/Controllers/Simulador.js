define(['./__module__', 'jquery'], function (controllers, $) {
    'use strict';
    controllers.controller('Simulador', ['$scope', '$http', function ($scope, $http) {

        angular.extend($scope, {
            valorCredito: '',
            valorPrestacao: '',
            prazoRestante: '',
            resultado: '',
            //TODO  revisar a fórmula
            calcular: function () {
                var T = 0.0075;
                var c = parseFloat(this.valorCredito);
                var p = parseFloat(this.valorPrestacao);
                var t = parseFloat(this.prazoRestante);
                var X = Math.pow((1+T), t);
                var r = c - ( p * ( (X-1) / (X*T) );
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