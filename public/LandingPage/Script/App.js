define(['angular', 'jquery', 'domReady!', 'retina', 'superslides', 'placeholder'], function (angular, $) {
    angular.module('App', [])
        .controller('Main', ['$scope', '$http', function ($scope, $http) {
            $scope.formPesquisa = {
                tipo: ""
            };
            $http.get('/assets/LandingPage/Mockup/Tipos.json').success(function (data) {
                angular.extend($scope, data);
            });
        }]);
    angular.bootstrap(document, ['App']);

    $('#slides').superslides({
        play: 6000,
        pagination: false,
        animation_speed: 800,
        animation: 'fade'
    });

    $('input, textarea').placeholder();

});