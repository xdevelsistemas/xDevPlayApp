define(['./__module__', 'jquery'], function (directives, $) {
    'use strict';
    directives.directive('xdMaskMoneyBrl', [function () {
        return {
            restrict: 'A',
            link: function (scope, elm, attr) {
                elm.css('text-align', 'right');
                window.setTimeout(function () {
                    $(elm[0]).maskMoney({
                        prefix: 'R$ ',
                        decimal: ',',
                        thousands: '.'
                    });
                }, 20);
            }
        };
    }]);
});