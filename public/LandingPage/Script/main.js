require.config({

    //TODO  configurar a versão da aplicação aqui
    "urlArgs": "v=" + (new Date()).getTime(),

    paths: {
        'domReady': "../Lib/domReady",
        'angular': "../Lib/angular/angular.min",
        'retina': "../Lib/retina.min",
        'jquery': "../Lib/jquery-1.10.2.min",
        'superslides': "../Lib/jquery.superslides.min",
        'placeholder': "../Lib/jquery.placeholder.min"
    },

    shim: {
        'angular': {
            exports: 'angular',
            deps: ['jquery']
        },
        'angular-route': {
            deps: ['angular']
        },
        'domReady': {
            deps: ['angular']
        },
        'superslides': {
            deps: ['jquery']
        },
        'placeholder': {
            deps: ['jquery']
        }
    },

    deps: [
        './App']



});