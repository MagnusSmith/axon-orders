'use strict';

/* Controllers */

axonOrdersApp.controller('ProductController', ['$scope', '$location' , 'Products',
    function ($scope, $location, Products) {
      $scope.products = Products.get();

      $scope.newProductView = function() {
          $location.path('newProduct');
      }

    }]);
