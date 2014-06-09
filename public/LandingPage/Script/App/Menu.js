define(function(require) {

    var Fragment = require("Fragment");

    var Menu = Fragment.extend({
        init: function() {
            this._super();
            this.view = $(require("text!Views/Menu.html"));
            this.requireModel("/LandingPage/Mockup/Menu.json");
        }
    });

    return Menu;
});