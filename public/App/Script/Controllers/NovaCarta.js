define(['./__module__', 'jquery'], function (controllers, $) {
    'use strict';
    controllers.controller('NovaCarta', ['$scope', '$http', function ($scope, $http) {
        $scope.strings = {};
        $scope.tipo = {};
        $scope.administradora = {};
        $scope.contemplacao = {};
        $scope.contas = {};

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
                    },
                    "conta": {
                        "value": "",
                        "error": ""
                    },
                    "numCodigo": {
                        "value": "",
                        "error": ""
                    }
                }
            };
        };
        inicializarModeloForm();

        function naoEeNum(s) {
            return s === undefined || s === null || isNaN(parseFloat(s.replace('R$ ', '').split('.').join('').replace(',', '.')));
        };

        $scope.formatConta = function (conta) {
            var abre = '';
            var fecha = '';
            if (conta.padrao == '1') {
                abre = '__ABRE__'
                fecha = '__FECHA__'
            }
            return abre + 'AG: ' + conta.agencia + ' / ' + 'CC: ' + conta.conta + fecha;
        };

        function formatResult(item) {
            return item.text.replace('__ABRE__', '<span class="select-conta-padrao"><i class="icon-white icon-star"></i> (Padrão) ')
                .replace('__FECHA__', '</span>');

        }

        function formatSelection(item) {
            return item.text.replace('__ABRE__', '<span class="select-conta-padrao"><i class="icon icon-star"></i> (Padrão) ')
                .replace('__FECHA__', '</span>');

        }

        function aplicarSelect2Contas(elm_name, again) {
            if (again) $(elm_name).select2("destroy")
            $(elm_name).select2({'width': '100%', formatResult: formatResult, formatSelection: formatSelection});
        };


        $scope.enviarFormNovaCarta = function (form, isInvalid) {
            console.log('>>>', $scope.formData);
            $scope.formData.fields.tipo.error = form.tipo.$invalid ? $scope.strings.campoObrigatorio : null;
            $scope.formData.fields.administradora.error = form.administradora.$invalid ? $scope.strings.campoObrigatorio : null;
            $scope.formData.fields.contemplacao.error = form.contemplacao.$invalid ? $scope.strings.campoObrigatorio : null;
            $scope.formData.fields.prazoRestante.error = form.prazoRestante.$invalid ? $scope.strings.campoObrigatorio : null;
            $scope.formData.fields.valorCredito.error = form.valorCredito.$invalid || naoEeNum($scope.formData.fields.valorCredito.value) ? $scope.strings.campoObrigatorio : null;
            $scope.formData.fields.valorEntrada.error = form.valorEntrada.$invalid || naoEeNum($scope.formData.fields.valorEntrada.value) ? $scope.strings.campoObrigatorio : null;
            $scope.formData.fields.valorPrestacoes.error = form.valorPrestacoes.$invalid || naoEeNum($scope.formData.fields.valorPrestacoes.value) ? $scope.strings.campoObrigatorio : null;
            $scope.formData.fields.cota.error = form.cota.$invalid || naoEeNum($scope.formData.fields.cota.value) ? $scope.strings.campoObrigatorio : null;
            if (isInvalid) return console.log("formulário inválido!");
            console.log("formulário válido!");
            $("#modal-selecionarConta").modal('show');
        };

        $scope.enviarFormConta = function (form, isInvalid) {
            $scope.formData.fields.conta.error = form.conta.$invalid ? $scope.strings.campoObrigatorio : null;
            $scope.formData.fields.numCodigo.error = form.numCodigo.$invalid ? $scope.strings.campoObrigatorio : null;
            console.log('>>>', $scope.formData);
            if (isInvalid) {
                console.log("formulário inválido!");
                return;
            }
            console.log("formulário válido!");
            inicializarModeloForm();
            $("#modal-selecionarConta").modal('hide');
            //TODO  retirar este mockup
            $scope.formData = {
                "resp": {
                    "result": "1",
                    "message": "Carta adicionada com sucesso! Acesse o Escritório Online para maiores detalhes."
                }
            };
        };

        //Inicializador
        $http.get("/assets/App/Mockup/NovaCarta/strings.json").success(function (data) {
            angular.extend($scope.strings, data);
        });
        $http.get("/rest/list/gettipocarta").success(function (data) {
            angular.extend($scope.tipo, data);
        });
        $http.get("/rest/list/getadministradoras").success(function (data) {
            angular.extend($scope.administradora, data);
        });
        $http.get("/rest/list/getcontemplacao").success(function (data) {
            angular.extend($scope.contemplacao, data);
        });
        $http.get("/rest/grid/contas/list").success(function (data) {
            angular.extend($scope.contas, data);
            aplicarSelect2Contas("select[name='conta']");
        });

    }
    ])
    ;
});