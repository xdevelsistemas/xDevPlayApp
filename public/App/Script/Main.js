require.config({
        "baseUrl": '/assets/App/Script',
        "urlArgs": "v=" + (new Date()).getTime(),
        "paths": {
            'text': 'Libs/text',
            'modernizr' : 'Libs/modernizr',
            'knockout': 'Libs/knockout',
            'komapping': 'Libs/knockout.mapping',
            'jquery': 'Libs/jquery',
            'maskedinput': 'Libs/jquery.maskedinput',
            'domReady': 'Libs/domReady',
            'Fragment': 'Frag/Fragment',
            'App': 'App/App',
            'Home': 'App/Home',
            'Views': '../Views',
            // injecoes da home
            'aviatorsmap': 'js/aviators-map',
            'gmap3': 'js/gmap3.infobox.min',
            'bootstrap': '../Content/libraries/bootstrap2/js/bootstrap.min',
            'retina': 'js/retina',
            'gmap3clusterer': 'js/gmap3.clusterer',
            'js-ezmark': 'js/jquery.ezmark',
            'carousel': 'js/carousel',
            'jsbxslider': 'js/jquery.bxslider',
            'aviatorssettings' : 'js/aviators-settings',
            'properta' : 'js/properta',
            'chosen' : '../Content/libraries/chosen/chosen.jquery.min',
            'select2' : '../Content/libraries/select2/select2',
            'select2-ptbr' : '../Content/libraries/select2/select2_locale_pt-BR',
            'ajax-chosen' : '../Content/libraries/chosen/ajax.chosen',
            'themepunch.revolution' : '../Content/libraries/rs-plugin/js/jquery.themepunch.revolution.min',
            'themepunch.plugins' : '../Content/libraries/rs-plugin/js/jquery.themepunch.plugins.min',
            'bootstrap-datepicker': '../Content/libraries/datepicker/js/bootstrap-datepicker'
        }

});

require(['Libs/domReady'], function (domReady) {
    domReady(function () {
        require(['jquery'], function (jquery) {
            require(['js-ezmark', 'chosen' , 'jsbxslider', 'maskedinput', 'select2', 'bootstrap-datepicker'], function (ezmark, chosen, jsbxslider,maskedinput, select2 , bootstrapdatepicker ) {
                jquery.select2 = select2;
                require(['ajax-chosen','properta', 'aviatorsmap', 'bootstrap', 'retina', 'gmap3clusterer', 'carousel', 'aviatorssettings', 'themepunch.revolution', 'themepunch.plugins'], function (
                    ajaxchosen,
                    properta,
                    aviatorsmap,
                    bootstrap,
                    retina,
                    gmap3clusterer,
                    carousel,
                    aviatorssettings,
                    themepunchrevolution,
                    themepunchplugins
                    ) {

                });
            });
        });
    });
});
