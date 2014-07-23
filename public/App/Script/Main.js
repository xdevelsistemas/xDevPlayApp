require.config({
    "baseUrl": '/assets/App/Script',
    "urlArgs": "v=" + (new Date()).getTime(),
    "paths": {
        //'text': 'Libs/text',
        'domReady': '../Libs/domReady',
        'angular': '../Libs/angular.min',
        'modernizr': '../Libs/modernizr',
        'jquery': '../Libs/jquery',
        'jqPlugins': 'App/jqPlugins',
        'select2localePtBR': '../Content/libraries/select2/select2_locale_pt-BR',
        'properta': 'js/properta',
        'app': 'App/App'
    },
    // angular does not support AMD out of the box, put it in a shim
    shim: {
        /*'angular': {
            exports: 'angular'
        },*/
        'angular':['domReady!'],
        'app': ['angular'],
        'jqPlugins': ['jquery', 'domReady!'],
        'select2localePtBR': ['jqPlugins'],
        'properta': ['select2localePtBR']
    }
});


require(['domReady!', 'angular', 'app', 'jquery', 'jqPlugins'],
    function () {
        $("select").chosen();
        angular.bootstrap(document, ['Pesquisa']);
        $("#all-content").css({opacity:'1'});
    });
