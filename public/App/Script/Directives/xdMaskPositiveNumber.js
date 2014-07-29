define(['./__module__', 'jquery'], function (directives, $) {
    'use strict';
    directives.directive('xdMaskPositiveNumber', [function () {
        return {
            restrict: 'A',
            link: function (scope, elm, attr) {
                window.setTimeout(function () {
                    $(elm[0]).mask("#0", {reverse: true, maxlength: false});
                }, 20);
            }
        };
    }]);
});