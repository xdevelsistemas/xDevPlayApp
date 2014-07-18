define(function(require) {

    var Fragment = require("Fragment");

    var Home = Fragment.extend({
        init: function() {
            this._super();
            this.view = $(require("text!Views/Home.html"));
            this.requireModel("/assets/LandingPage/Mockup/Home.json");
        }
    });

    return Home;
});