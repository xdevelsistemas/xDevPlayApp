define(['./__module__', 'jquery'], function (controllers, $) {
    'use strict';
    controllers.controller('Pesquisa', ['$scope', '$http', 'QueryString', function ($scope, $http, QueryString) {
        $scope.strings = {
            "tituloCaixaFiltros": "Pesquisar Consórcios",
            "botaoFiltrar": "Filtrar!",
            "botaoLimpar": "Limpar Filtros",
            "tituloResultados": {
                "selecionado": "",
                "nenhum": "Nenhum resultado encontrado",
                "um": "Somente um resultados encontrado",
                "varios": "%t Resultados encontrados"
            },
            "subtituloResultados": {
                "exibido": "",
                "template": "Página %p de %t"
            },
            "detalhes": "Mais Informações",
            "proxima": "Próxima",
            "anterior": "Anterior",
            "ultima": "Última",
            "primeira": "Primeira"
        };
        $scope.resultados = {
            "total": "0",
            "itens_pagina": "12",
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
                "selecionado": "",
                "lista": []
            },
            "contemplacao": {
                "codigo": "contemplacao",
                "descricao": "Contemplação",
                "selecionado": "",
                "lista": []
            },
            "prazo_restante": {
                "codigo": "prazo_restante",
                "descricao": "Prazo restante",
                "selecionado": "",
                "lista": []
            },
            "tipo": {
                "codigo": "tipo",
                "descricao": "Quero consórcios de",
                "selecionado": "",
                "lista": []
            },
            "valor_credito_min": {
                "codigo": "valor_credito_min",
                "descricao": "Valor de Crédito Mínimo",
                "selecionado": "",
                "lista": []
            },
            "valor_credito_max": {
                "codigo": "valor_credito_min",
                "descricao": "Valor de Crédito Máximo",
                "selecionado": "",
                "lista": []
            },
            "valor_parcelas_min": {
                "codigo": "valor_parcelas_min",
                "descricao": "Valor das Parcelas Mínimo",
                "selecionado": "",
                "lista": []
            },
            "valor_parcelas_max": {
                "codigo": "valor_parcelas_max",
                "descricao": "Valor das Parcelas Máximo",
                "selecionado": "",
                "lista": []
            }
        }
        $scope.ordenadores = {
            "ordenador": {
                "codigo": "ordenador",
                "selecionado": "",
                "lista": []
            },
            "ordem": {
                "codigo": "ordem",
                "selecionado": "",
                "lista": []
            }
        }

        $scope.limpar = function () {
            event.preventDefault();
            $.each($scope.filtros, function (key, value) {
                value.selecionado = -1;
            });
        };
        $scope.buscarResultados = function (notAjax) {
            var xFiltersQuery = '?';

            if (notAjax) {
                console.log("======== not AJAX ========");
                $.each([
                    'tipo', 'contemplacao', 'prazo_restante', 'administradora',
                    'valor_credito_min', 'valor_credito_max', 'valor_parcelas_min', 'valor_parcelas_max'
                ], function (key, value) {
                    xFiltersQuery += value + '=' + $scope.filtros[value].selecionado + '&';
                });
                xFiltersQuery += $scope.ordenadores.ordenador.codigo + '=' + $scope.ordenadores.ordenador.selecionado + '&';
                xFiltersQuery += $scope.ordenadores.ordem.codigo + '=' + $scope.ordenadores.ordem.selecionado;
//                xFiltersQuery = xFiltersQuery.slice(0, xFiltersQuery.length - 1);
                xFiltersQuery = encodeURI(xFiltersQuery);
                window.location = '/pesquisa' + xFiltersQuery;
            } else {
                console.log("========== AJAX ==========");
                var data = {
                    "administradora": $scope.filtros.administradora.selecionado,
                    "contemplacao": $scope.filtros.contemplacao.selecionado,
                    "prazo_restante": $scope.filtros.prazo_restante.selecionado,
                    "tipo": $scope.filtros.tipo.selecionado,
                    "pagina": $scope.resultados.paginas.selecionada,
                    "ordenador": $scope.ordenadores.ordenador.selecionado,
                    "ordem": $scope.ordenadores.ordem.selecionado,
                    "valor_credito_min": $scope.filtros.valor_credito_min.selecionado,
                    "valor_credito_max": $scope.filtros.valor_credito_max.selecionado,
                    "valor_parcelas_min": $scope.filtros.valor_parcelas_min.selecionado,
                    "valor_parcelas_max": $scope.filtros.valor_parcelas_max.selecionado,
                    "itens_pagina": $scope.resultados.itens_pagina
                }
                console.log(">>>", "data: ", data);
                $http.post('/rest/grid/cartas/pesquisa', data)
                    .success(function (data) {
                        $.extend(true, $scope.resultados, data);
                        $scope.$emit('novosResultados');
                    });
            }
        };
        $scope.$on('novosResultados', function (event) {
            var resultados = $scope.resultados,
                titulo = $scope.strings.tituloResultados,
                subtitulo = $scope.strings.subtituloResultados;
            titulo.selecionado = (function () {
                var l = resultados.lista.length,
                    t = resultados.total;
                if (t == 0) return titulo.nenhum;
                else if (t == 1) return titulo.um;
                else return titulo.varios
                        .replace('%l', l).replace('%t', t);
            })();
            subtitulo.exibido = (function () {
                var t = resultados.paginas.total,
                    p = resultados.paginas.selecionada;
                return subtitulo.template
                    .replace('%t', t).replace('%p', p);
            })();
        });

        //Paginação
        $scope.irParaPagina = function (codigo) {
            $scope.resultados.paginas.selecionada = codigo;
            $scope.buscarResultados();
        };
        $scope.ordenarResultado = function () {
            $scope.irParaPagina(1);
        };
        $scope.passarPagina = function () {
            var p = parseInt($scope.resultados.paginas.selecionada),
                t = parseInt($scope.resultados.paginas.total);
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
            var p = parseInt($scope.resultados.paginas.selecionada),
                t = parseInt($scope.resultados.paginas.total);
            if (t < 5) return 1;
            else if (p > t - 2) return p - (4 - (t - p));
            else if (p > 2) return p - 2;
            else return 1;
        };
        $scope.paginasExibidas = function () {
            var t = parseInt($scope.resultados.paginas.total);
            return t < 5 ? t : 5;
        };
        //fim paginação


        //Ao iniciar a view
        (function () {
            //TODO conferir se é necessário por as strings em um arquivo separado
            //requisita as strings da página
//            $http.get('/assets/App/Mockup/Pesquisa/strings.json').success(function (data) {
//                angular.extend($scope.strings, data);
//            });
            //captura html query string da barra de endereço
            var QUERY_FILTROS = QueryString.removeMark(window.location.search),
                OBJ_FILTROS = QueryString.toObject(QUERY_FILTROS);
            //procura pelas listas de filtros e seleciona os campos select
            $.each({
                'administradora': '/rest/list/getadministradoras',
                'contemplacao': '/rest/list/getcontemplacao',
                'prazo_restante': '/rest/list/getprazorestante',
                'tipo': '/rest/list/gettipocarta'
            }, function (key, value) {
                $http.get(value).success(function (data) {
                    angular.extend($scope.filtros[key], data);
                });
                $scope.filtros[key].selecionado = OBJ_FILTROS[key];
            });
            //atribui os valores aos campos input
            function naoEeNum(s) {
                return !s || isNaN(parseFloat(s.replace('R$ ', '').split('.').join('').replace(',', '.')));
            };
            $.each(['valor_credito_min', 'valor_credito_max', 'valor_parcelas_min', 'valor_parcelas_max'
            ], function (k, value) {
                $scope.filtros[value].selecionado = naoEeNum(OBJ_FILTROS[value]) ? '' : OBJ_FILTROS[value];
            });
            //procura pelos ordenadores
            $.each({
                'ordenador': '/rest/list/getordenador',
                'ordem': '/rest/list/getordem'
            }, function (key, value) {
                var ordenadores = $scope.ordenadores[key];
                $http.get(value).success(function (data) {
                    angular.extend(ordenadores, data);
                });
                if (!!OBJ_FILTROS[key])
                    ordenadores.selecionado = OBJ_FILTROS[key];
                else ordenadores.selecionado = ordenadores.lista[0].codigo;
            });
            //chama a função sem passar true para requisitar em ajax
            $scope.buscarResultados();
        })();

    }]);
});