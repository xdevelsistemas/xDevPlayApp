define(['./__module__', 'jquery'], function (directives, $) {
    'use strict';
    directives.directive('xdChosen', [function () {
        return {
            restrict: 'A',
            link: function (scope, elm, attr) {
                window.setTimeout(function () {
                    //$(elm[0]).chosen().trigger("chosen:updated");
                    //$(elm[0]).chosen().trigger("liszt:updated");
                    //$(elm[0]).chosen();
                }, 20);
            }
        }
    }]);
});