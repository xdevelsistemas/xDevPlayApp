define(['./__module__', 'jquery'], function (controllers, $) {
    'use strict';
    controllers.controller('Simulador', ['$scope', '$http', function ($scope, $http) {
        $scope.strings = {
        };
        function inicializarModeloForm() {
            $scope.formData = {
                "result": "",
                "message": "",
                "fields": {
                    "valorCredito": {
                        "value": "",
                        "error": ""
                    },
                    "valorPrestacoes": {
                        "value": "",
                        "error": ""
                    },
                    "prazoRestante": {
                        "value": "",
                        "error": ""
                    },
                    "resultado": {
                        "value": "",
                        "error": ""
                    }
                }
            };
        };
        inicializarModeloForm();
        //TODO  revisar a fórmula
        $scope.calcular = function (form, isInvalid) {
            $scope.formData.fields.valorCredito.error = form.valorCredito.$invalid ? $scope.strings.campoObrigatorio : null;
            $scope.formData.fields.valorPrestacoes.error = form.valorPrestacoes.$invalid ? $scope.strings.campoObrigatorio : null;
            $scope.formData.fields.prazoRestante.error = form.prazoRestante.$invalid ? $scope.strings.campoObrigatorio : null;
            if (isInvalid) return console.log("formulário inválido!");
//            console.log("formulário válido!");
//            console.log($scope.formData.fields.valorCredito.value);
//            console.log($scope.formData.fields.valorPrestacoes.value);
//            console.log($scope.formData.fields.prazoRestante.value);
            var T = 0.0075;
            var c = parseFloat($scope.formData.fields.valorCredito.value.replace('R$ ', '').split('.').join('').replace(',', '.'));
            var p = parseFloat($scope.formData.fields.valorPrestacoes.value.replace('R$ ', '').split('.').join('').replace(',', '.'));
            var t = parseInt($scope.formData.fields.prazoRestante.value);
//            console.log("depois do parse");
//            console.log(c);
//            console.log(p);
//            console.log(t);
            console.log("t: ", t);
            var X = Math.pow((1 + T), t);
            console.log("X: ", X);
            var r = c - ( p * ( (X - 1) / (X * T) ) );
            console.log("teste r antes de isNaN: ", r);
            if (isNaN(r)) r = '';
            console.log("teste r depois de isNaN: ", r);
            r = r.toString().split('.');
            if (r.length > 1) {
                r[1] = r[1].slice(0, 2);
                r = r.join('');
            }
            $scope.formData.fields.resultado.value = r;
            console.log("resultado: ", r);
            window.setTimeout(function () {
                $("input[name='resultado']").mask("#.##0,00", {
                    reverse: true,
                    maxlength: false
                });
            }, 10);
        };
        $http.get('/assets/App/Mockup/Simulador/strings.json').success(function (data) {
            angular.extend($scope, data);
        });
    }
    ])
    ;
})
;