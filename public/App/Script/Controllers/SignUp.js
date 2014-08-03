define(['./__module__', 'jquery'], function (controllers, $) {
    'use strict';
    controllers.controller('SignUp', ['$scope', function ($scope) {

        $('#birthDate').inputmask("dd/mm/yyyy"); //nascimento
        $('#numCep').inputmask("99999-999"); //cep
        $('#docFederal').inputmask("999.999.999-99"); //Inicia o campo como CPF
        $('.tpDoc').change(function () {
            $('#docFederal').unmask(); //Remove a mascara
            if ($(this).val() == "cpf") { //Caso seja CPF
                $('#docFederal').inputmask("999.999.999-99");
            } else { //Acaso seja Cnpj
                $('#docFederal').inputmask("99.999.999/9999-99");
            }
        });

        $("#uf").select2({
            placeholder: "Selecione um estado",
            ajax: { // instead of writing the function to execute the request we use Select2's convenient helper
                url: "/rest/list/getuf",
                dataType: 'json',
                results: function (data, page) { // parse the results into the format expected by Select2.
                    // since we are using custom formatting functions we do not need to alter remote JSON data
                    return {results: data};
                }
            },
            initSelection: function (element, callback) {
                // the input tag has a value attribute preloaded that points to a preselected movie's id
                // this function resolves that id attribute to an object that select2 can render
                // using its formatResult renderer - that way the movie name is shown preselected
                var id = $(element).val();
                if (id !== "") {
                    $.ajax("/rest/list/getuf", {
                        dataType: "json"
                    }).done(function (data) {
                        var data_filter = [];
                        $.each(data, function (index, elem) {
                            if (id == elem.id) {
                                callback(elem);
                            }
                        })
                    });
                }
            },
            width: '100%',
            escapeMarkup: function (m) {
                return m;
            } // we do not want to escape markup since we are displaying html in results
        });

        $("#numBanco").select2({
            placeholder: "Selecione um Banco",
            ajax: { // instead of writing the function to execute the request we use Select2's convenient helper
                url: "/rest/list/getbanco",
                dataType: 'json',
                results: function (data, page) { // parse the results into the format expected by Select2.
                    // since we are using custom formatting functions we do not need to alter remote JSON data
                    return {results: data};
                }
            },
            width: '100%',
            escapeMarkup: function (m) {
                return m;
            } // we do not want to escape markup since we are displaying html in results
        });


        $('button#btnCep').unbind('click').click(function () {
            var url = '/rest/getendereco/' + $('#numCep').val();
            $.getJSON(url, function (data) {
                $("#uf").select2("val", data.uf);
                $('#cidade').val(data.cidade);
                $('#bairro').val(data.bairro);
                $('#logradouro').val(data.tipo_logradouro + " " + data.logradouro);


            });
            return false;
        });

    }]);
});