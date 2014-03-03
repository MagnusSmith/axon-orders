
axonOrdersApp.factory('Product', ['$resource',
    function ($resource) {
        return $resource('rest/products/:productId', {}, {
            'findAll': { method: 'GET', isArray: true,  params:{productId:''}},
            'findOne': {method: 'GET'},
            'deleteOne': {method:'DELETE'},
            'create': {method:'POST'},
            'update': {method:'PUT'}
        });
    }]);