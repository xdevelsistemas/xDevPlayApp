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
        'jquery': '../Libs/jquery',
        'jqueryPlugins': '../Libs/jqueryPlugins',
        'select2localePtBR': '../Libs/select2/select2_locale_pt-BR',
        'properta': 'js/properta'
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
        'select2localePtBR': ['jqueryPlugins'],
        'properta': ['select2localePtBR']
    },

    deps: [
        // kick start application... see Bootstrap.js
        './Bootstrap'
    ]
});
