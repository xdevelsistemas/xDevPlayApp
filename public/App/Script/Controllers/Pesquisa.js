define(['./__module__', 'jquery'], function (controllers, $) {
    'use strict';
    controllers.controller('Pesquisa', ['$scope', '$http', 'QueryString', function ($scope, $http, QueryString) {
        $scope.strings = {
            "tituloCaixaFiltros": "Pesquisar Consórcios",
            "botaoFiltrar": "Filtrar!",
            "botaoLimpar": "Limpar Filtros",
            "tituloResultados": {
                "nenhum": "Nenhum resultado encontrado",
                "um": "Somente um resultados encontrado",
                "varios": "%t Resultados encontrados"
            },
            "subtituloResultados": "Página %p de %t",
            "detalhes": "Mais Informações"
        };
        $scope.resultados = {
            "total": "0",
            "lista": [],
            "paginas": {
                "total": "1",
                "selecionada": "1"
            }
        };
        $scope.filtros = {
            "administradora": {
                "codigo": "administradora",
                "descricao": "Administradora",
                "selecionado": "-",
                "lista": []
            },
            "contemplacao": {
                "codigo": "contemplacao",
                "descricao": "Contemplação",
                "selecionado": "-",
                "lista": []
            },
            "prazo_restante": {
                "codigo": "prazo_restante",
                "descricao": "Prazo restante",
                "selecionado": "-",
                "lista": []
            },
            "tipo": {
                "codigo": "tipo",
                "descricao": "Quero consórcios de",
                "selecionado": "-",
                "lista": []
            },
            "valor_credito": {
                "codigo": "valor_credito",
                "descricao": "Valor de Crédito",
                "selecionado": "-",
                "lista": []
            },
            "valor_parcelas": {
                "codigo": "valor_parcelas",
                "descricao": "Valor das parcelas",
                "selecionado": "-",
                "lista": []
            }
        }
        $scope.ordenadores = {
            "ordenador": {
                "codigo": "ordenador",
                "selecionado": "preco",
                "lista": []
            },
            "ordem": {
                "codigo": "ordem",
                "selecionado": "asc",
                "lista": []
            }
        }

        //função chamadao ao fim do controle
        $scope.aoAbrir = function () {
            //requisita as strings da página
            $http.get('/assets/App/Mockup/Pesquisa/strings.json').success(function (data) {
                angular.extend($scope.strings, data);
            });
            //captura html query string da barra de endereço
            var QUERY_FILTROS = QueryString.removeMark(window.location.search);
            var FILTROS = QueryString.toObject(QUERY_FILTROS);
            console.log(FILTROS);
            //procura pelos filtros
            $.each(['administradora', 'contemplacao', 'prazo_restante', 'tipo', 'valor_credito', 'valor_parcelas'], function (key, value) {
                $scope.filtros[value].selecionado = FILTROS[value];
                console.log(">>>", $scope.filtros[value]);
                $http.get('/assets/App/Mockup/Filtros/' + $scope.filtros[value].codigo + '.json').success(function (data) {
                    angular.extend($scope.filtros[value], data);
                    console.log("<<<", $scope.filtros[value]);
                });
            });
            //procura pelos ordenadores
            $.each(['ordenador', 'ordem'], function (key, value) {
                if (FILTROS[value] != undefined)
                    $scope.ordenadores[value].selecionado = FILTROS[value] != undefined;
                console.log(">>>", $scope.ordenadores[value]);
                $http.get('/assets/App/Mockup/Filtros/' + $scope.ordenadores[value].codigo + '.json').success(function (data) {
                    angular.extend($scope.ordenadores[value], data);
                    console.log("<<<", $scope.ordenadores[value]);
                });
            });
            //chama a função sem passar true para requisitar em ajax
            $scope.buscarResultados();
        };

        $scope.limpar = function () {
            event.preventDefault();
            $.each($scope.filtros, function (key, value) {
                value.selecionado = -1;
            });
        };

        $scope.buscarResultados = function (notAjax) {
            var xFiltersQuery = '?';
            $.each($scope.filtros, function (key, value) {
                xFiltersQuery += value.codigo + '=' + value.selecionado + '&';
            });

            if (notAjax) {
                xFiltersQuery = encodeURI(xFiltersQuery);
                window.location = '/pesquisa' + xFiltersQuery;
            } else {
                xFiltersQuery += 'pagina=' + $scope.resultados.paginas.selecionada + '&'
                xFiltersQuery += 'ordenador=' + $scope.ordenadores.ordenador.selecionado + '&';
                xFiltersQuery += 'ordem=' + $scope.ordenadores.ordem.selecionado;
                xFiltersQuery = encodeURI(xFiltersQuery);
                $http.get('/assets/App/Mockup/Pesquisa/resultados.json' + xFiltersQuery)
                    .success(function (data) {
                        $.extend(true, $scope.resultados, data);
                    });
            }
        };

        $scope.tituloResultados = function () {
            var l = $scope.resultados.lista.length;
            var t = $scope.resultados.total;
            if (t == 0)return $scope.strings.tituloResultados.nenhum;
            else if (t == 1)return $scope.strings.tituloResultados.um;
            else return $scope.strings.tituloResultados.varios
                    .replace('%l', l).replace('%t', t);
        };

        $scope.subtituloResultados = function () {
            var t = $scope.resultados.paginas.total;
            var p = $scope.resultados.paginas.selecionada;
            return $scope.strings.subtituloResultados
                .replace('%t', t).replace('%p', p);
        };

        $scope.irParaPagina = function (codigo) {
            $scope.resultados.paginas.selecionada = codigo;
            $scope.buscarResultados();
        };

        $scope.ordenarResultado = function () {
            $scope.irParaPagina(1);
        };

        $scope.passarPagina = function () {
            var p = parseInt($scope.resultados.paginas.selecionada);
            var t = parseInt($scope.resultados.paginas.total);
            if (p < t) {
                $scope.irParaPagina(p + 1);
            }
        };
        $scope.voltarPagina = function () {
            var p = parseInt($scope.resultados.paginas.selecionada);
            if (p > 1) {
                $scope.irParaPagina(p - 1);
            }
        };
        $scope.ultimaPagina = function () {
            $scope.irParaPagina($scope.resultados.paginas.total);
        };
        $scope.primeiraPagina = function () {
            $scope.irParaPagina(1);
        };

        $scope.paginaSelecionada = function (codigo) {
            return (parseInt(codigo) == $scope.resultados.paginas.selecionada);
        };
        $scope.paginasPassadas = function () {
            var p = parseInt($scope.resultados.paginas.selecionada);
            var t = parseInt($scope.resultados.paginas.total);
            if (t < 5) return 1;
            else if (p > t - 2) return p - (4 - (t - p));
            else if (p > 2) return p - 2;
            else return 1;
        };
        $scope.paginasExibidas = function () {
            var t = parseInt($scope.resultados.paginas.total);
            return t < 5 ? t : 5;
        }


        $scope.aoAbrir();
    }]);
});