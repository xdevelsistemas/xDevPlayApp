define([
    'require',
    'angular',
    'OldApp',
    'routes'
], function (require, ng) {
    'use strict';

    require(['domReady!'], function (document) {
        ng.bootstrap(document, ['app']);
    });
});