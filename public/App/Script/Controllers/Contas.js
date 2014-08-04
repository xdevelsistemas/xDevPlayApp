define(['./__module__', 'jquery'], function (controllers, $) {
    'use strict';
    controllers.controller('Contas', ['$scope', '$http', function ($scope, $http) {

        $scope.strings = {};
        $scope.contas = {
            "padrao": "",
            "lista": []
        }

        $scope.bancos = {
            "codigo": "",
            "descricao": "",
            "lista": []
        };

        function inicializarModeloForm() {
            $scope.formData = {
                "result": "",
                "message": "",
                "fields": {
                    "numBanco": {
                        "value": "",
                        "error": ""
                    },
                    "nomeBanco": {
                        "value": "",
                        "error": ""
                    },
                    "agencia": {
                        "value": "",
                        "error": ""
                    },
                    "conta": {
                        "value": "",
                        "error": ""
                    },
                    "padrao": {
                        "value": true,
                        "error": ""
                    }
                }
            };
        };
        inicializarModeloForm();

        $scope.adicionarConta = function (form, isInvalid) {
            $scope.formData.fields.numBanco.error = form.numBanco.$invalid ? "Este é um campo obrigatório." : null;
            $scope.formData.fields.agencia.error = form.agencia.$invalid ? "Este é um campo obrigatório." : null;
            $scope.formData.fields.conta.error = form.conta.$invalid ? "Este é um campo obrigatório." : null;
            if (isInvalid) return;
            var param = $.extend(true, {}, $scope.formData);
            param.fields.padrao.value = param.fields.padrao.value ? '1' : '0';
            var nb = param.fields.numBanco.value.split(':');
            param.fields.numBanco.value = nb[0];
            param.fields.nomeBanco.value = nb[1];
            console.log("formData ==> ", $scope.formData);
            console.log(">>>", "param ==> ", param);
            $http.post('/rest/grid/contas/add', param)
                .success(function (data) {
                    data.fields.padrao.value = data.fields.padrao.value == '1' ? true : false;
                    angular.extend($scope.formData, data);
                    //em caso da resposta ter sido positiva
                    if ($scope.formData.resp.result == '1')
                        $http.get("/rest/grid/contas/list").success(function (data2) {
                            angular.extend($scope.contas, data2);
                            $("#tabela-contas").dataTable().fnDestroy();
                            aplicarDatatables("#tabela-contas");
                            $('#modal-novaConta').modal("hide");
                            inicializarModeloForm();
                        });
                });
        };

        $scope.removerConta = function (conta) {
            if (window.confirm("Tem certeza de que quer remover esta conta?" +
                "\n\n" + conta.banco.nome + "\nAG: " + conta.agencia + "\nCC: " + conta.conta + "\n\n")) {
                $http.post("/rest/grid/contas/remove/" + conta.codigo).success(function (data) {
                    console.log(data);
                    console.log('Conta removida!');
                    $http.get("/rest/grid/contas/list").success(function (data2) {
                        angular.extend($scope.contas, data2);
                        aplicarDatatables("#tabela-contas", true);
                    });
                });
            }
        };

        $scope.tornarPadrao = function (conta) {
            $http.post("/rest/grid/contas/setpadrao/" + conta.codigo).success(function (data) {
                console.log(data);
                console.log('Conta marcada como padrão!');
                $http.get("/rest/grid/contas/list").success(function (data2) {
                    angular.extend($scope.contas, data2);
                    aplicarDatatables("#tabela-contas", true);
                });
            });
        };


        function aplicarDatatables(elmId, again) {
            if (again)$(elmId).dataTable().fnDestroy();
            window.setTimeout(function () {
                $(elmId).dataTable({
                    language: {
                        "sEmptyTable": "Nenhum registro encontrado",
                        "sInfo": "Mostrando de _START_ até _END_ de _TOTAL_ registros",
                        "sInfoEmpty": "Mostrando 0 até 0 de 0 registros",
                        "sInfoFiltered": "(Filtrados de _MAX_ registros)",
                        "sInfoPostFix": "",
                        "sInfoThousands": ".",
                        "sLengthMenu": "Mostrar _MENU_ resultados por página",
                        "sLoadingRecords": "Carregando...",
                        "sProcessing": "Processando...",
                        "sZeroRecords": "Nenhum registro encontrado",
                        "sSearch": "Pesquisar",
                        "oPaginate": {
                            "sNext": "Próximo",
                            "sPrevious": "Anterior",
                            "sFirst": "Primeiro",
                            "sLast": "Último"
                        },
                        "oAria": {
                            "sSortAscending": ": Ordenar colunas de forma ascendente",
                            "sSortDescending": ": Ordenar colunas de forma descendente"
                        }
                    },
                    "aoColumnDefs": [
                        { "bSortable": false, "aTargets": [ 0, 4 ] }
                    ],
                    "aaSorting": [
                        [1, 'asc']
                    ]
                });
            }, 100);
        }

        //Inicializador
        $http.get("/rest/grid/contas/list").success(function (data) {
            angular.extend($scope.contas, data);
            aplicarDatatables("#tabela-contas");
        });
        $http.get("/rest/list/getbanco").success(function (data) {
            $.extend(true, $scope.bancos, data);
        });

        //hack to prevent the ENTER key to close the modal
        $('input').bind('keypress', function (e) {
            if (e.keyCode == 13) { //Enter keycode
                e.preventDefault();
            }
        });

    }]);
});