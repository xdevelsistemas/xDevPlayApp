define(function(require) {

    var Fragment = require("Fragment");

    var Menu = Fragment.extend({
        init: function() {
            this._super();
            this.view = $(require("text!Views/Menu.html"));
            this.requireModel("/assets/LandingPage/Mockup/Menu.json");
        }
    });

    return Menu;
});