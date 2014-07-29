define(['./__module__', 'jquery'], function (directives) {
    'use strict';
    directives.directive('xdDataTable', [function () {
        return {
            restrict: 'A',
            link: function (scope, elm, attr) {
                window.setTimeout(function () {
                    //elm.dataTable();
                }, 20);
            }
        };
    }]);
});