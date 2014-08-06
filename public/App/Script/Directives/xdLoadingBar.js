define(['./__module__', 'jquery'], function (directives, $) {
    'use strict';
    directives.directive('xdLoadingBar', [function () {
        return {
            restrict: 'E',
            template: '<div id="loading-splash" ng-cloak><div id="loading-bar" class="progress progress-striped active"><div class="bar"></div></div></div>',
            replace: true
        };
    }]);
});