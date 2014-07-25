define(['./__module__'], function (services) {
    'use strict';
    services.factory('Location', [function () {
        return {
            toObject: function (query) {
                try {
                    return JSON.parse('{"' + decodeURI(query).replace(/"/g, '\\"').replace(/&/g, '","').replace(/=/g, '":"') + '"}');
                }
                catch (e) {
                    return new Object();
                }
            },
            search: {
                toObj: function () {
                    var search = location.search.substring(1);
                    try {
                        return JSON.parse('{"' + decodeURI(search).replace(/"/g, '\\"').replace(/&/g, '","').replace(/=/g, '":"') + '"}');
                    }
                    catch (e) {
                        return new Object();
                    }
                }
            }
        }
    }
    ]);
});
