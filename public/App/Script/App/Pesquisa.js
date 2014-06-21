/**
 * Created by claytonsantosdasilva on 18/06/14.
 */
require(['Libs/domReady'], function (domReady) {
    domReady(function () {
            require(["knockout", "komapping"], function (ko, mapping) {
                window.ko = ko;
                window.ko.mapping = mapping;

                require(["App"], function () {
                    xapp = new App();
                    xapp.Modelo.Pesquisa.filtros = xapp.aplicarModelo("Filtros", "#caixa-filtros");
                    xapp.Modelo.Pesquisa.resultados = xapp.aplicarModelo("ResultadosPesquisa", "#resultadosPesquisa", function () {
                        $('select[name=filter_sort_by]').change(function () {
                            $('form.form-sort').submit();
                        });
                        $('select[name=filter_order]').change(function () {
                            $('form.form-sort').submit();
                        });
                    });
                });

            });
        });
});