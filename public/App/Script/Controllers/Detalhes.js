define(['./__module__', 'jquery'], function (controllers, $) {
    'use strict';
    controllers.controller('Detalhes', ['$scope', '$http', function ($scope, $http) {
        $scope.strings = {
            "valorDoBem": "Valor do Bem",
            "codigoDaCarta": "Código da Carta",
            "status": "Status",
            "valorEntrada": "Valor de Entrada",
            "prazoRestante": "Prazo Restante (meses)"
        };
        $scope.carta = {};
        $scope.historico = [];
        var cartaMock = {
                "codigo": "000104",
                "valordoBem": "R$ 60.000,00",
                "valorEntrada": "R$ 8.000,00",
                "prazoRestante": "20",
                "tipo": {
                    "codigo": "0000",
                    "nome": "Tipo da Carta"
                },
                "administradora": {
                    "codigo": "0000",
                    "nome": "Nome da Administradora"
                },
                "status": {
                    "codigo": "0000",
                    "nome": "Status Atual"
                },
                "acoes": [
                    {
                        "codigo": "acao_01",
                        "nome": "Ação 01"
                    },
                    {
                        "codigo": "acao_02",
                        "nome": "Ação 02",
                        "icone": "icon-trash"
                    },
                    {
                        "codigo": "acao_03",
                        "nome": "Ação 03",
                        "icone": "icon-edit"
                    }
                ]
            },
            historicoMock = [
                {
                    "hora": "dd/MM/YYYY HH:mm:ss",
                    "estagio_anterior": "O estágio anterior",
                    "estagio_posterior": "O estágio posterior",
                    "usuario": "O usuário",
                    "justificativa": "A justificativa"
                }
            ];


        $scope.acaoMock = function (acao) {
            window.alert("Este botão chamou a ação " + acao.nome +
                "\n\nO código desta ação é " + acao.codigo);
        };

        //Ao inicializar
        (function () {
            var codigo = window.location.hash.replace('#', '');
            if (!codigo)
                return console.log("!!! ==> Código não encontrado no hash!");
            if (codigo == "mock") {
                angular.extend($scope.carta, cartaMock);
                angular.extend($scope.historico, historicoMock);
            }
            $http.get('/rest/grid/cartas/detalhes/' + codigo).success(function (data) {
                if (data.resp.result != 1)
                    return console.log("!!! ==> Código não encontrado no servidor!");
                else {
                    angular.extend($scope.carta, data.carta);
                    if (!!data.historico)
                        angular.extend($scope.historico, data.historico);
                }
            });
        })();

    }]);
});