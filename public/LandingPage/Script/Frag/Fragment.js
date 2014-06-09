define(function(require) {

    var Fragment = Class.extend({
        model: {},
        view: "",
        init: function() {
        },
        requireModel: function(yURL, yCallback) {
            this.model = ko.mapping.fromJS(this.model);
            var that = this;
            $.ajax(yURL, {
                dataType: 'json',
                cache: false,
                type: 'GET',
                success: function(data, textStatus, jqXHR) {
                    ko.mapping.fromJS(data, that.model);
                    ko.applyBindings(that.model, that.view[0]);
                    if (typeof yCallback === 'function')
                        yCallback();
                }
            });
        }
    });

    return Fragment;
});