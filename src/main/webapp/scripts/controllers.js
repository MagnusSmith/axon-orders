'use strict';

/* Controllers */

//products list controller
axonOrdersApp.controller('ProductsController', ['$scope', '$location' , 'Product',
    function ($scope, $location, Product) {
        $scope.products = Product.findAll();

        $scope.newProductView = function () {
            $location.path('product/');
        }

        $scope.editProductView = function (aProductId) {
            $location.path('product/'+aProductId);
        }

        $scope.deleteProduct = function(aProductId) {
            Product.deleteOne({productId:aProductId});
            //TODO refresh list but not this way ??
            $scope.products = Product.findAll();
        }
    }]);

//product controller
axonOrdersApp.controller('ProductController', ['$scope', '$location' , 'Product',
    function ($scope, $location, Product) {

        //get product id from URL
        var aProductId = $location.path().split("/")[2]||"";

        console.log(aProductId + " @@@")

        if (aProductId != "") {
            //find and display product
            $scope.product = Product.findOne({productId: aProductId});
        }

        console.log($scope.product);

        //cancel form
        $scope.cancel = function () {
            $location.path('products');
        }

    }]);
