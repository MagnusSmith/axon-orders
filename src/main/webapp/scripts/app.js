'use strict';

/* App Module */

var axonOrdersApp = angular.module('axonOrdersApp', ['ngResource', 'ngRoute']);

axonOrdersApp
    .config(['$routeProvider', '$httpProvider',
    function($routeProvider, $httpProvider) {
        $routeProvider
            .when('/products', {
                templateUrl: 'views/products.html',
                controller: 'ProductsController'
            }).when('/test', {
                template:"This is a test"
            }).when('/product/:productId?', {
                templateUrl: 'views/product.html',
                controller: 'ProductController'
            }).otherwise({
                redirectTo:'/'
            });
    }])

