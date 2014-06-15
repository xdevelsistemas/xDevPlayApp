function App() {
    this.Modelo = {
        Pesquisa: {
            resultados: {}
        }
    };
}

App.prototype.getHtml = function(yUrl, yCallback) {
    $.ajax({
        url: "/assets/App/Views/" + yUrl + ".html",
        dataType: 'text',
        cache: false,
        type: 'GET',
        success: function(data, textStatus, jqXHR) {
            if (typeof yCallback == 'function')
                yCallback(data, textStatus, jqXHR);
            return data;
        }
    });
};

App.prototype.incluirHtml = function(yUrl, yParent, yCallback) {
    this.getHtml(yUrl, function(data) {
        $(yParent).append($(data));
        if (typeof yCallback == 'function')
            yCallback();
    });
};

App.prototype.abrirPagina = function(yUrl) {
    var xApp = this;
    var xContainer = $("#mainView-container");
    var xParent = $("#mainView");
    xContainer.css({opacity: 0});
    window.setTimeout(function() {
        xApp.getHtml(yUrl, function(data) {
            xParent.html(data);
            xContainer.css({opacity: 1});
        });
    }, 500);
};
App.prototype.selecionarMenu = function(yElement) {
    event.preventDefault();
    var xUrl = $(yElement).data("url");
    if (xUrl === '' || xUrl === '#')
        return;
    $("#nav li").removeClass("active");
    $($(yElement).closest("li.menu-first")).addClass("active");
    this.abrirPagina(xUrl);
};

App.prototype.aplicarModelo = function(yUrl, yView, yCallback) {
    var xModel = ko.mapping.fromJS({});
    var xView = $(yView);
    $.ajax({
        url: "/assets/App/Mockup/" + yUrl + ".json",
        dataType: 'json',
        cache: false,
        type: 'GET',
        success: function(data, textStatus, jqXHR) {
            ko.mapping.fromJS(data, xModel);
            ko.applyBindings(xModel, xView[0]);
            if (typeof yCallback === 'function')
                yCallback();
        }
    });
    return xModel;
};