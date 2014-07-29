define(['./__module__', 'jquery'], function (directives, $) {
    'use strict';
    directives.directive('xdMaskMoneyBrl', [function () {
        return {
            restrict: 'A',
            link: function (scope, elm, attr) {
                window.setTimeout(function () {
                    $(elm[0]).mask("#.##0,00", {
                        reverse: true,
                        maxlength: false,
                        placeholder: "R$"
                    });
                }, 20);
            }
        };
    }]);
});