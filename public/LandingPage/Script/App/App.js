define(function(require) {

    var Fragment = require("Fragment");
    var Menu = require("App/Menu");
    var Home = require("App/Home");
    var Comprar = require("App/Comprar");
    var Vender = require("App/Vender");

    var App = Fragment.extend({
        view: $(require("text!Views/App.html")),
        init: function() {
            this._super();
            //Fragments instances
            this.menu = new Menu();
            this.home = new Home();
            this.comprar = new Comprar();
            this.vender = new Vender();
            //Appending the views
            this.view.prependTo($(document.body));
            this.home.view.prependTo(this.view.find(".container"));
            this.menu.view.prependTo(this.view.find(".container"));
            this.comprar.view.appendTo(this.view.find("#comprar-vender"));
            this.vender.view.appendTo(this.view.find("#comprar-vender"));
            //Setting Page title
            $("head title").text("ProConsórcio");

            //Starting background slides
            $('#slides').superslides({
                play: 6000,
                pagination: false,
                animation_speed: 800,
                animation: 'fade'
            });
        },
        openSection: function(yHref) {
            var xContainerView = this.view.children("#main-container");
            var xContentView = this.sections[yHref].view;
            xContainerView.fadeOut(function() {
                xContainerView.html(xContentView);
                xContainerView.fadeIn();
            });
        }
    });
    return App;
}); 