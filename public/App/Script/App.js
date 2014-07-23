/**
 * loads sub modules and wraps them up into the main module
 * this should be used for top-level module definitions only
 */
define([
    'angular',
    'angular-route',
    './Controllers/__index__',
    './Directives/__index__',
    './Filters/__index__',
    './Services/__index__'
], function (angular) {
    'use strict';

    return angular.module('App', [
        'App.Controllers',
        'App.Directives',
        'App.Filters',
        'App.Services',
        'ngRoute'
    ]);
});
