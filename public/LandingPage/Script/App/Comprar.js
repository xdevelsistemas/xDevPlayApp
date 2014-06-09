define(function(require) {

    var Fragment = require("Fragment");

    var Comprar = Fragment.extend({
        init: function() {
            this._super();
            this.view = $(require("text!Views/Comprar.html"));
            this.requireModel("/assets/LandingPage/Mockup/Comprar.json");
        }
    });

    return Comprar;
});