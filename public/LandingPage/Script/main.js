require.config({

    //TODO  configurar a versão da aplicação aqui
    "urlArgs": "v=" + (new Date()).getTime(),

    paths: {
        'domready': "../Lib/domReady",
        'angular': "../Lib/angular/angular.min",
        'retina': "../Lib/retina.min",
        'jquery': "../Lib/jquery-1.10.2.min",
        'superslides': "../Lib/jquery.superslides.min",
        'placeholder': "../Lib/jquery.placeholder.min",
        'select2': "../Lib/select2-3.5.1/select2.min",
        'select2PtBR': "../Lib/select2-3.5.1/select2_locale_pt-BR"
    },

    shim: {
        'angular': {
            exports: 'angular'
        },
        'angular-route': {
            deps: ['angular']
        },
        'superslides': ['jquery'],
        'placeholder': ['jquery'],
        'select2': ['jquery'],
        'select2PtBR': ['select2']
    }
});

require(['angular', 'jquery', 'domready!', 'retina', 'superslides', 'placeholder', 'select2PtBR'], function (angular, $) {
    angular.module('App', [])
        .controller('Main', ['$scope', '$http', function ($scope, $http) {
            $http.get('/assets/LandingPage/Mockup/Tipos.json').success(function (data) {
                angular.extend($scope, data);
            });
        }]);
    angular.bootstrap(document, ['App']);

    $('#slides').superslides({
        play: 6000,
        pagination: false,
        animation_speed: 800,
        animation: 'fade'
    });

    $('input, textarea').placeholder();
});