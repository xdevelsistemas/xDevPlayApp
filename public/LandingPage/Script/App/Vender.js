define(function(require) {

    var Fragment = require("Fragment");

    var Vender = Fragment.extend({
        init: function() {
            this._super();
            this.view = $(require("text!Views/Vender.html"));
            this.requireModel("/assets/LandingPage/Mockup/Vender.json");
        }
    });

    return Vender;
});