define(['./__module__'], function (filters) {
    'use strict';

    return filters.filter('Range', [function () {
        return function (input, total) {
            total = parseInt(total);
            for (var i = 0; i < total; i++)
                input.push(i);
            return input;
        };
    }]);
});
