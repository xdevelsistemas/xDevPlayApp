define(['./__module__', 'jquery'], function (controllers, $) {
    'use strict';
    controllers.controller('Escritorio', ['$scope', '$http', function ($scope, $http) {

        $scope.strings = {};
        $scope.compras = {
            "lista": []
        }
        $scope.vendas = {
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

        //Ao inicializar
        (function () {
            var hash = window.location.hash;
            $("#painel a[href='" + hash + "']").trigger('click');
            $http.get("/assets/App/Mockup/Escritorio/compras.json").success(function (data) {
                angular.extend($scope.compras, data);
                aplicarDatatables("#tab-compras table");
            });
            $http.get("/assets/App/Mockup/Escritorio/vendas.json").success(function (data) {
                angular.extend($scope.vendas, data);
                aplicarDatatables("#tab-vendas table");
            });
        })();

    }]);
});