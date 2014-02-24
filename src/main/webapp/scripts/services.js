
axonOrdersApp.factory('Products', ['$resource',
    function ($resource) {
        return $resource('rest/products', {}, {
            'get': { method: 'GET', isArray: true}
        });
    }]);