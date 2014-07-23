/**
 * bootstraps angular onto the window.document node
 * NOTE: the ng-app attribute should not be on the index.html when using ng.bootstrap
 */
define([
    'require',
    'angular',
    'jquery',
    'jqueryPlugins',
    'App',
    'Routes'
], function (require, ng) {
    'use strict';

    /*
     * place operations that need to initialize prior to app start here
     * using the `run` function on the top-level module
     */


    require(['domReady!'], function (document) {
        ng.bootstrap(document, ['App']);
        //TODO arrancar essa gambiarra e passar para uma directive
        window.setTimeout(function () {
            $("select").chosen();
            $("#all-content").css({opacity: '1'});
        }, 250);
    });
});
