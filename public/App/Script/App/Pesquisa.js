var app = angular.module('App', []);
app.controller('Filtros', function ($scope) {
    $scope.tituloCaixaFiltros = "Pesquisar Consórcios";
    $scope.textoBotaoFiltrar = "Filtrar!";
    $scope.filtros = [];
    $scope.pay = function pay() {
        window.alert("Thanks!");
    };

});


//require(['Libs/domReady'], function (domReady) {
//    domReady(function () {
//            require(["jquery","knockout", "komapping"], function ($, ko, mapping) {
//                window.ko = ko;
//                window.ko.mapping = mapping;
//
//                require(["App"], function () {
//                    xapp = new App();
//                    xapp.Modelo.Pesquisa.filtros = xapp.aplicarModelo("Filtros", "#caixa-filtros");
//                    xapp.Modelo.Pesquisa.resultados = xapp.aplicarModelo("ResultadosPesquisa", "#resultadosPesquisa", function () {
//                        $('select[name=filter_sort_by]').change(function () {
//                            $('form.form-sort').submit();
//                        });
//                        $('select[name=filter_order]').change(function () {
//                            $('form.form-sort').submit();
//                        });
//                    });
//                });
//
//            });
//        });
//});