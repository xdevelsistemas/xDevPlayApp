define(['./__module__'], function (services) {
    'use strict';
    services.factory('QueryString', [function () {
        return {
            removeMark: function (s) {
                return s[0] == '?' ? s.slice(1, s.length) : s;
            },
            _parse: function (query, start, connector, separator, end, type) {
                try {
                    return JSON.parse(start + decodeURI(this.removeMark(query)).replace(/"/g, '\\"').replace(/&/g, separator).replace(/=/g, connector) + end);
                    //return JSON.parse('{"' + decodeURI(this.removeMark(query)).replace(/"/g, '\\"').replace(/&/g, '","').replace(/=/g, '":"') + '"}');
                }
                catch (error) {
                    console.log("QueryString", "=>", error);
                    return new window[type]();
                }
            },
            toObject: function (query) {
                return this._parse(query, '{"', '":"', '","', '"}', 'Object');
            },
            toArray: function (query) {
                return this._parse(query, '[{"', '":"', '"},{"', '"}]', 'Array');
            },
            toJson: function (query) {
                return JSON.stringify(this.toObject(query));
            }
        }
    }]);
});