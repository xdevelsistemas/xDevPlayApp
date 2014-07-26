define(['./__module__', 'jquery'], function (directives) {
    'use strict';
    directives.directive('xdPageFadeIn', [function () {
        return {
            restrict: 'E',
            replace: true,
            template: '<span><!--  www.xdevel.com.br -->',
            link: function (scope, elm, attr) {
                window.setTimeout(function () {
                    $("#all-content").css({opacity: '1'});
                }, 20);
            }
        };
    }]);
});