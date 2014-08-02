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
                    "banco": {
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
                    }
                }
            };
        };
        inicializarModeloForm();

        $scope.adicionarConta = function (form, isInvalid) {
            $scope.formData.fields.banco.error = form.banco.$invalid ? "Este é um campo obrigatório." : null;
            $scope.formData.fields.agencia.error = form.agencia.$invalid ? "Este é um campo obrigatório." : null;
            $scope.formData.fields.conta.error = form.conta.$invalid ? "Este é um campo obrigatório." : null;
            if (isInvalid) return;
            $http.get('http://localhost:9000/assets/App/Mockup/Contas/nova_conta.json')
                .success(function (data) {
                    angular.extend($scope.formData, data);
                    //em caso da resposta ter sido positiva
                    if (parseInt($scope.formData.result) === 1)
                        $http.get("/assets/App/Mockup/Contas/contas2.json").success(function (data2) {
                            angular.extend($scope.contas, data2);
                            $('#modal-novaConta').modal("hide");
                            inicializarModeloForm();
                        });
                });

        }


        function aplicarDatatables(elmId) {
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
                    }
                });
            }, 100);
        }

        //Inicializador
        (function () {
            $http.get("/assets/App/Mockup/Contas/contas.json").success(function (data) {
                angular.extend($scope.contas, data);
                aplicarDatatables("#tabela-contas");
            });
            $http.get("/assets/App/Mockup/Bancos.json").success(function (data) {
                $.extend(true, $scope.bancos, data);
            });

            //hack to prevent the ENTER key to close the modal
            $('input').bind('keypress', function (e) {
                if (e.keyCode == 13) { //Enter keycode
                    e.preventDefault();
                }
            });

        })();

    }]);
});