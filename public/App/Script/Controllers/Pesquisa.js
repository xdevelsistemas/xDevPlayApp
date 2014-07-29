define(['./__module__', 'jquery'], function (controllers, $) {
    'use strict';
    controllers.controller('Pesquisa', ['$scope', '$http', 'QueryString', function ($scope, $http, QueryString) {
        var QUERY_FILTROS = window.location.search;

        $scope.strings = {
            "tituloCaixaFiltros": "Pesquisar Consórcios",
            "botaoFiltrar": "Filtrar!",
            "botaoLimpar": "Limpar Filtros",
            "tituloResultados": {
                "nenhum": "Nenhum resultado encontrado",
                "um": "Somente um resultados encontrado",
                "varios": "%l Resultados encontrados"
            },
            "detalhes": "Mais Informações"
        };
        $scope.resultados = {
            "total": "0",
            "lista": [],
            "paginas": {
                "total": "1",
                "selecionada": "1"
            },
            "ordenadores": {
                "selecionado": "preco",
                "lista": [
                    {
                        "codigo": "preco",
                        "descricao": "Preço"
                    },
                    {
                        "codigo": "administradora",
                        "descricao": "Administradora"
                    }
                ]
            },
            "ordem": {
                "selecionada": "asc",
                "lista": [
                    {
                        "codigo": "asc",
                        "descricao": "Crescente"
                    },
                    {
                        "codigo": "desc",
                        "descricao": "Decrescente"
                    }
                ]
            }
        };

        $scope.filtros = {
            "administradora": {
                "codigo": "administradora",
                "descricao": "Administradora",
                "selecionado": "-1",
                "lista": [
                    {
                        "codigo": "-1",
                        "descricao": "-"
                    }
                ]
            },
            "contemplacao": {
                "codigo": "contemplacao",
                "descricao": "Contemplação",
                "selecionado": "-1",
                "lista": [
                    {
                        "codigo": "-1",
                        "descricao": "-"
                    }
                ]
            },
            "prazoRestante": {
                "codigo": "prazo_restante",
                "descricao": "Prazo restante",
                "selecionado": "-1",
                "lista": [
                    {
                        "codigo": "-1",
                        "descricao": "-"
                    }
                ]
            },
            "tipo": {
                "codigo": "tipo",
                "descricao": "Quero consórcios de",
                "selecionado": "-1",
                "lista": [
                    {
                        "codigo": "-1",
                        "descricao": "-"
                    }
                ]
            },
            "valorCredito": {
                "codigo": "valor_credito",
                "descricao": "Valor de Crédito",
                "selecionado": "-1",
                "lista": [
                    {
                        "codigo": "-1",
                        "descricao": "-"
                    }
                ]
            },
            "valorParcelas": {
                "codigo": "valor_parcelas",
                "descricao": "Valor das parcelas",
                "selecionado": "-1",
                "lista": [
                    {
                        "codigo": "-1",
                        "descricao": "-"
                    }
                ]
            }
        }

        /*$http.get('/assets/App/Mockup/Filtros.json' + QUERY_FILTROS).success(function (data) {
         angular.extend($scope, data);
         });
         $http.get('/assets/App/Mockup/ResultadosPesquisa.json' + QUERY_FILTROS).success(function (data) {
         angular.extend($scope, data);
         });*/

        $scope.limpar = function () {
            event.preventDefault();
            $.each($scope.filtros, function (key, value) {
                value.selecionado = -1;
                console.log(value);
            });
            $("#caixa-filtros select").val(-1).trigger("liszt:updated");
        };

        $scope.buscarResultados = function () {
            var qFiltros = "";
            var xFiltros = $scope.filtros;
            for (var k in xFiltros) {
                qFiltros += xFiltros[k].codigo + "=" + xFiltros[k].selecionado;
                if (k < Object.keys(xFiltros).length - 1)
                    qFiltros += "&";
            }
            var objQuery = QueryString.toObject(qFiltros);
            var query = $.param(angular.extend(objQuery, {
                ordenador: $scope.ordenadores.selecionado,
                ordem: $scope.ordem.selecionada,
                pagina: $scope.paginas.selecionada
            }));
            $http.get('/assets/App/Mockup/ResultadosPesquisa.json' + '?' + query).success(function (data) {
                angular.extend($scope, data);
            });
        };

        $scope.tituloResultados = function () {
            var l = $scope.resultados.lista.length;
            if (l == 0)return $scope.strings.tituloResultados.nenhum;
            else if (l == 1)return $scope.strings.tituloResultados.um;
            else return $scope.strings.tituloResultados.varios.replace("%l", l);
        };

        $scope.irParaPagina = function (codigo) {
            $scope.paginas.selecionada = codigo;
            $scope.buscarResultados();
        };

        $scope.ordenarResultado = function () {
            $scope.irParaPagina(1);
        };

        $scope.passarPagina = function () {
            var p = parseInt($scope.paginas.selecionada);
            var t = parseInt($scope.paginas.total);
            if (p < t) {
                $scope.irParaPagina(p + 1);
            }
        };
        $scope.voltarPagina = function () {
            var p = parseInt($scope.paginas.selecionada);
            if (p > 1) {
                $scope.irParaPagina(p - 1);
            }
        };
        $scope.ultimaPagina = function () {
            $scope.irParaPagina($scope.paginas.total);
        };
        $scope.primeiraPagina = function () {
            $scope.irParaPagina(1);
        };

        $scope.paginaSelecionada = function (codigo) {
            return (parseInt(codigo) == $scope.paginas.selecionada);
        };
        $scope.paginasPassadas = function () {
            var p = parseInt($scope.paginas.selecionada);
            var t = parseInt($scope.paginas.total);
            if (t < 5) return 1;
            else if (p > t - 2) return p - (4 - (t - p));
            else if (p > 2) return p - 2;
            else return 1;
        };
        $scope.paginasExibidas = function () {
            var t = parseInt($scope.resultados.paginas.total);
            return t < 5 ? t : 5;
        }
    }
    ]);
});