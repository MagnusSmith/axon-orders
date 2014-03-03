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

        //index - is an index in a table that we display on page so we can delete a product from UI
        $scope.deleteProduct = function(aProduct) {
            Product.deleteOne({productId:aProduct.id}).$promise.then(
                //success
                function( value ){
                    $scope.products.splice( $scope.products.indexOf(aProduct), 1 );
                },
                //error
                function( error ){alert('there was a problem')}
            );
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

        //save form
        $scope.save = function () {
            console.log("SUBMIT FORM");
            console.log($scope.product);

            //need to find how to optimize error handling

            if($scope.product.id == undefined ) {
                console.log("NEW PRODUCT");
                //new product
                Product.create($scope.product).$promise.then(
                    //success
                    function( value ){
                        $scope.products = Product.findAll();
                        //go back to products page
                        $location.path('products');
                    },
                    //error
                    function( error ){alert('there was a problem')}
                );
            }
            else {
                console.log("UPDATE PRODUCT");
                //update product
                Product.update($scope.product).$promise.then(
                    //success
                    function( value ){
                        $scope.products = Product.findAll();
                        //go back to products page
                        $location.path('products');
                    },
                    //error
                    function( error ){alert('there was a problem')}
                );
            }
        }

    }]);
