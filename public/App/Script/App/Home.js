/**
 * Created by claytonsantosdasilva on 20/06/14.
 */

require(['Libs/domReady'], function (domReady) {
    domReady(function () {
        require(['jquery'], function(jquery){
            require(['aviatorsmap','js-ezmark','bootstrap','retina','gmap3clusterer','carousel','jsbxslider','aviatorssettings','chosen', 'themepunch.revolution', 'themepunch.plugins'], function(){});
        });
    });
});



