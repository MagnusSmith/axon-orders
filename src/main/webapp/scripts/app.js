'use strict';

/* App Module */

var axonOrdersApp = angular.module('axonOrdersApp', ['ngResource', 'ngRoute']);

axonOrdersApp
    .config(['$routeProvider', '$httpProvider',
    function($routeProvider, $httpProvider) {
        $routeProvider
            .when('/product', {
                templateUrl: 'views/product.html',
                controller: 'ProductController'
            }).when('/test', {
                template:"This is a test"
            })
    }])

