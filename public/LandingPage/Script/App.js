define(['angular', 'jquery', 'domReady!', 'retina', 'superslides', 'placeholder'], function (angular, $) {
    angular.module('App', [])
        .controller('Main', ['$scope', '$http', function ($scope, $http) {
            $scope.tipos = {
                "lista": []
            };
            $scope.formPesquisa = {
                tipo: ""
            };
            $http.get('/rest/list/gettipocarta').success(function (data) {
                angular.extend($scope.tipos, data);
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