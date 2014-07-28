define(['./__module__', 'jquery'], function (directives) {
    'use strict';
    directives.directive('xdChosen', [function () {
        return function (scope, elm, attr) {
            window.setTimeout(function () {
                elm.chosen();
            }, 20);
        };
    }]);
});