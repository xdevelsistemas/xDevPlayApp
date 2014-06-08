define(function(require) {

    var Fragment = require("Fragment/Fragment");

    var Comprar = Fragment.extend({
        init: function() {
            this._super();
            this.view = $(require("text!Views/Comprar.html"));
            this.requireModel("Mockup/Comprar.json");
        }
    });

    return Comprar;
});