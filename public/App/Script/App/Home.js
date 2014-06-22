/**
 * Created by claytonsantosdasilva on 20/06/14.
 */
require(['Libs/domReady'], function (domReady) {
    domReady(function () {
        require(['jquery'], function (jquery) {
            require(['js-ezmark', 'chosen', 'jsbxslider'], function (ezmark, chosen, jsbxslider) {
                require(['properta', 'aviatorsmap', 'bootstrap', 'retina', 'gmap3clusterer', 'carousel', 'aviatorssettings', 'themepunch.revolution', 'themepunch.plugins'], function () {
                });
            });
        });
    });
});
