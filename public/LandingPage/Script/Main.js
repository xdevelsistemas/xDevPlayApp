require.config({
//    urlArgs: "bust=" + (new Date()).getTime(),
    urlArgs: "v=" + (new Date()).getTime(),
    paths: {
        'text': 'Libs/text',
        'knockout': 'Libs/knockout',
        'ko.mapping': 'Libs/knockout.mapping',
        'jquery': 'Libs/jquery',
        'domReady': 'Libs/domReady',
        'Fragment': 'Fragment',
        'App': 'App',
        'Views': 'LandingPage/Views'
    }
});

var app;

require(["App/App", "domReady!"], function(App) {
    app = new App();
});