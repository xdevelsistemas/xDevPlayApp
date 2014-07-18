require.config({
//    urlArgs: "bust=" + (new Date()).getTime(),
    baseUrl: '/assets/LandingPage/Script',
    urlArgs: "v=" + (new Date()).getTime(),
    paths: {
        'text': 'Libs/text',
        'knockout': 'Libs/knockout',
        'ko.mapping': 'Libs/knockout.mapping',
        'jquery': 'Libs/jquery',
        'domReady': 'Libs/domReady',
        'Fragment': 'Frag/Fragment',
        'App': 'App',
        'Views': '../Views'
    }
});

var app;

require(["App/App", "domReady!"], function(App) {
    app = new App();
});