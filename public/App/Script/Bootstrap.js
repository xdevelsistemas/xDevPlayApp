/**
 * bootstraps angular onto the window.document node
 * NOTE: the ng-app attribute should not be on the index.html when using ng.bootstrap
 */
define([
    'require',
    'angular',
    'jquery',
    'bootstrap',
    'uiselect2',
    'datatables',
    'datatablesBootstrap',
    'jqueryPlugins',
    'App',
    'Routes'
], function (require, ng) {
    'use strict';

    /*
     * place operations that need to initialize prior to app start here
     * using the `run` function on the top-level module
     */


    require(['domReady!'], function () {
        $('.datepicker').datepicker();
        //$('.datepicker').mask('99/99/9999');

        ng.bootstrap(document, ['App']);
    });
});
