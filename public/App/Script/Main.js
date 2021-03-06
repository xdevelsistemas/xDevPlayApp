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
        'datatablesBootstrap': '../Libs/DataTables/js/datatables-bootstrap2',
        'angularDatatables': '../Libs/angular-datatables/angular-datatables.min',
        'jqueryPlugins': '../Libs/jqueryPlugins',
        'select2': '../Libs/rs-plugin/select2/select2',
        'uiselect2': '../Libs/ui-select2/src/select2',
        'select2localePtBR': '../Libs/select2/select2_locale_pt-BR'
    },

    /**
     * for libs that either do not support AMD out of the box, or
     * require some fine tuning to dependency mgt'
     */
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
        'jqueryPlugins': {
            deps: ['jquery', 'domReady!']
        },
        'datatables': {
            deps: ['jquery', 'domReady!']
        },
        'datatablesBootstrap': {
            deps: ['datatables']
        },
        'select2': {
            deps: ['jquery']
        },
        'uiselect2': {
            deps: ['select2', 'angular']
        },
        'select2localePtBR': {
            deps: ['uiselect2']
        },
        'bootstrap': ["jquery", 'domReady!']
    },

    deps: [
        // kick start application... see Bootstrap.js
        './Bootstrap'
    ]
});
