/**
 * Created by claytonsantosdasilva on 20/06/14.
 */
require(['Libs/domReady'], function (domReady) {
    domReady(function () {
        require(['jquery'], function (jquery) {
            require(['js-ezmark', 'chosen' , 'jsbxslider', 'maskedinput', 'select2', 'bootstrap-datepicker'], function (ezmark, chosen, jsbxslider,maskedinput, select2 , bootstrapdatepicker ) {
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
