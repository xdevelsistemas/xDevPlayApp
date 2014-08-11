define(['./__module__', 'jquery'], function (controllers, $) {
    'use strict';
    controllers.controller('NovaCarta', ['$scope', '$http', '$sce', '$rootScope', function ($scope, $http, $sce, $rootScope) {
        $scope.strings = {};
        $scope.tipo = {};
        $scope.administradora = {};
        $scope.contemplacao = {};
        $scope.contas = {};
        $scope.formData = {
            "resp": {
                "result": "",
                "message": ""
            }
        };
        $scope.conta = "";
        //Funções
        function inicializarModeloForm() {
            $scope.formData.fields = {
                "codigo": {
                    "value": "",
                    "error": ""
                },
                "id": {
                    "value": "",
                    "error": ""
                },
                "codigo_statuscarta": {
                    "value": "",
                    "error": ""
                },
                "codigo_tipocarta": {
                    "value": "",
                    "error": ""
                },
                "codigo_administradora": {
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
                "valorPrestacao": {
                    "value": "",
                    "error": ""
                },
                "valorCota": {
                    "value": "",
                    "error": ""
                },
                "numCodigo": {
                    "value": "",
                    "error": ""
                },
                "codigo_conta": {
                    "value": "",
                    "error": ""
                }
            };
        };
        function naoEeNum(s) {
            return s === undefined || s === null || isNaN(parseFloat(s.replace('R$ ', '').split('.').join('').replace(',', '.')));
        };
        function formatResult(item) {
            return item.text.replace('__ABRE__', '<span class="select-conta-padrao"><i class="icon-white icon-star"></i> (Padrão) ')
                .replace('__FECHA__', '</span>');
        };
        function formatSelection(item) {
            return item.text.replace('__ABRE__', '<span class="select-conta-padrao"><i class="icon icon-star"></i> (Padrão) ')
                .replace('__FECHA__', '</span>');

        };
        function aplicarSelect2Contas(elm_name, again) {
            if (again) $(elm_name).select2("destroy")
            $(elm_name).select2({'width': '100%', formatResult: formatResult, formatSelection: formatSelection});
        };
        $scope.abrirModalNovaConta = function () {
            $("#modal-selecionarConta").modal('hide');
            $("#modal-novaConta").modal('show');
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
        $scope.enviarFormNovaCarta = function (form, isInvalid) {
            console.log(form);
            console.log('>>>', $scope.formData);
            $scope.formData.fields.valorCredito.error = naoEeNum($scope.formData.fields.valorCredito.value) ? $scope.strings.campoObrigatorio : null;
            $scope.formData.fields.valorEntrada.error = naoEeNum($scope.formData.fields.valorEntrada.value) ? $scope.strings.campoObrigatorio : null;
            $scope.formData.fields.valorPrestacao.error = naoEeNum($scope.formData.fields.valorPrestacao.value) ? $scope.strings.campoObrigatorio : null;
            $scope.formData.fields.valorCota.error = naoEeNum($scope.formData.fields.valorCota.value) ? $scope.strings.campoObrigatorio : null;
            $.each([
                'codigo_tipocarta',
                'codigo_administradora',
                'codigo_statuscarta',
                'prazoRestante',
                'valorCredito',
                'valorEntrada',
                'valorPrestacao',
                'valorCota'
            ], function (k, v) {
                $scope.formData.fields[v].error = form[v].$invalid ? $scope.strings.campoObrigatorio : null;
            });
            if (isInvalid) return console.log("formulário inválido!");
            console.log("formulário válido!");
            $("#modal-selecionarConta").modal('show');
        };

        function recebeuResp(data) {
            console.log('<<<', data);
            if (data.resp != undefined) $.extend(true, $scope.formData, data);
            else $.extend(true, $scope.formData.resp, data);
            $scope.formData.resp.message = $sce.trustAsHtml($scope.formData.resp.message);
            $("#modal-selecionarConta").modal('hide');
        };
        $scope.enviarFormConta = function (form, isInvalid) {
            $scope.formData.fields.codigo_conta.error = form.codigo_conta.$invalid ? $scope.strings.campoObrigatorio : null;
            $scope.formData.fields.numCodigo.error = form.numCodigo.$invalid ? $scope.strings.campoObrigatorio : null;
            console.log('>>>', $scope.formData);
            if (isInvalid) {
                console.log("formulário inválido!");
                return;
            }
            console.log("formulário válido!");
            $http.post("/rest/grid/cartas/add", $scope.formData).success(function (data) {
                recebeuResp(data)
                if ($scope.formData.resp.result == '1') {
                    $("form[name='formSelecionarConta'] select[name='codigo_conta']").select2('val', '');
                    inicializarModeloForm();
                    $scope.formData.resp.message = $sce.trustAsHtml('Carta adicionada com sucesso! Acesse o <a href="/escritorio#vendas"><strong>Escritório Online</strong></a> para maiores detalhes.');
                }
            }).error(function (data) {
                recebeuResp(data);
            });
        };

        //Inicializador
        inicializarModeloForm();
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

        $scope.$on('contaAdicionada', function (event, ergs) {
            $http.get("/rest/grid/contas/list").success(function (data) {
                angular.extend($scope.contas, data);
                aplicarSelect2Contas("select[name='codigo_conta']");
            });
        });
        $rootScope.$broadcast('contaAdicionada');


//        $http.get("/rest/grid/contas/list").success(function (data) {
//            angular.extend($scope.contas, data);
//            aplicarSelect2Contas("select[name='codigo_conta']");
//        });


    }]);
});