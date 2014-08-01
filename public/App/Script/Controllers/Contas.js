define(['./__module__', 'jquery'], function (controllers, $) {
    'use strict';
    controllers.controller('Contas', ['$scope', '$http', function ($scope, $http) {

        $scope.strings = {};
        $scope.contas = {
            "padrao": "",
            "lista": []
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
        })();

    }]);
});