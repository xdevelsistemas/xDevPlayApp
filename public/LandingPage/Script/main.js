require.config({

    //TODO  configurar a versão da aplicação aqui
    "urlArgs": "v=" + (new Date()).getTime(),

    paths: {
        'domready': "../Lib/domReady",
        'retina': "../Lib/retina.min",
        'jquery': "../Lib/jquery-1.10.2.min",
        'superslides': "../Lib/jquery.superslides.min",
        'placeholder': "../Lib/jquery.placeholder.min",
        'select2': "../Lib/select2-3.5.1/select2.min",
        'select2PtBR': "../Lib/select2-3.5.1/select2_locale_pt-BR"
    },

    shim: {
        'superslides': ['jquery'],
        'placeholder': ['jquery'],
        'select2': ['jquery'],
        'select2PtBR': ['select2']
    }
});

require(['jquery', 'domready!', 'retina', 'superslides', 'placeholder', 'select2PtBR'], function ($) {
    $('#slides').superslides({
        play: 6000,
        pagination: false,
        animation_speed: 800,
        animation: 'fade'
    });

    $('input, textarea').placeholder();
});