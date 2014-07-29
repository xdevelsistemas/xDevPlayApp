/**
 * configure RequireJS
 * prefer named modules to long paths, especially for version mgt
 * or 3rd party libraries
 */
require.config({

    //TODO  configurar a versão da aplicação aqui
    "urlArgs": "v=" + (new Date()).getTime(),

    paths: {
        'angular': '../Libs/angular/angular',
        'angular-route': '../Libs/angular-route/angular-route',
        'domReady': '../Libs/requirejs-domready/domReady',
        "bootstrap": '../Libs/rs-plugin/bootstrap2/js/bootstrap.min',
        'jquery': '../Libs/jquery',
        'datatables': '../Libs/DataTables/js/jquery.dataTables',
        'jqueryPlugins': '../Libs/jqueryPlugins',
        'select2localePtBR': '../Libs/select2/select2_locale_pt-BR'
    },

    /**
     * for libs that either do not support AMD out of the box, or
     * require some fine tuning to dependency mgt'
     */
    shim: {
        'angular': {
            exports: 'angular'
        },
        'angular-route': {
            deps: ['angular']
        },
        'jquery': {
            deps: ['domReady!']
        },
        'bootstrap': ["jquery", 'domReady!'],
        'jqueryPlugins': ['jquery', 'domReady!'],
        'datatables': ['jquery'],
        'select2localePtBR': ['jqueryPlugins']
    },

    deps: [
        // kick start application... see Bootstrap.js
        './Bootstrap'
    ]
});
