define(['./__module__', 'jquery'], function (controllers, $) {
    'use strict';
    controllers.controller('Simulador', ['$scope', '$http', function ($scope, $http) {

        angular.extend($scope, {
            //TODO  revisar a fórmula
            calcular: function () {
                var T = 0.0075;
                var c = parseFloat($("input[name='valorCredito']").cleanVal());
                var p = parseFloat($("input[name='valorPrestacao']").cleanVal());
                var t = parseFloat($("input[name='prazoRestante']").cleanVal());
                var X = Math.pow((1 + T), t);
                var r = c - ( p * ( (X - 1) / (X * T) ) );
                if (isNaN(r)) r = '';
                r = r.toString().split('.');
                r[1] = r[1].slice(0, 2);
                r.join();
                $("input[name='resultado']").val(r)
                    .mask("#.##0,00", {reverse: true, maxlength: false});
            }
        });

        $http.get('/assets/App/Mockup/Simulador.json').success(function (data) {
            angular.extend($scope, data);
        });
    }]);
});