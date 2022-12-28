angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/app/api/v1';

    $scope.fillTable = function (pageIndex = 1){
        $http({
           url: contextPath + '/products',
           method: 'GET',
           params: {
               // если фильтр существует то ложим в параметы запроса значение поля, иначе ложим нулл
               min_price: $scope.filter ? $scope.filter.min_price : null,
               max_price: $scope.filter ? $scope.filter.max_price : null,
               title_part: $scope.filter ? $scope.filter.title_part : null,
               pageIndex: pageIndex
           }
        }).then(function (response){
                $scope.ProductsPage = response.data;
            console.log($scope.ProductsPage);
                $scope.PaginationArray = $scope.generatePagesIndexes(1, $scope.ProductsPage.totalPages);
            })
    }

    $scope.generatePagesIndexes = function (startPage, endPage) {
        let arr = [];
        for (let i = startPage; i < endPage + 1; i++){
            arr.push(i);
        }
        return arr;
    }

    $scope.fillCart = function (){
        $http({
            url: contextPath + '/cart',
            method: 'GET'
        }).then(function (response){
            $scope.ProductsCart = response.data;
            console.log($scope.ProductsCart);
        })
    }

    $scope.addProductToCart = function (productId){

        $http.get(contextPath + '/cart/add/' + productId)
            .then(function (response){
                $scope.fillCart();
            })
    }

    $scope.resetFilter = function (){
        $scope.filter = null;
        $scope.fillTable();
    }

    $scope.deleteProductById = function (productId) {
        $http.delete(contextPath + '/products/' + productId)
            .then(function (response){
            $scope.fillTable()
        });
    };

    $scope.submitCreateProduct = function (){
        $http.post(contextPath + '/products', $scope.newProduct)
            .then(function (response){
                $scope.newProduct = null;
                $scope.fillTable();
            });
    };

    $scope.fillTable();
    $scope.fillCart();
});