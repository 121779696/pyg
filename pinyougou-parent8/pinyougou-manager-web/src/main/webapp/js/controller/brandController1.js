app.controller("brandController",function ($scope,$controller, brandService) {

    $controller("baseController",{$scope:$scope});

    $scope.finAll=function () {
        brandService.findAll().success(
            function (response) {
                $scope.list= response;
            }
        );
    }
    //分页
    $scope.findPage = function (page, size) {
        brandService.findPage().success(
            function (response) {
                $scope.list = response.rows;
                $scope.paginationConf.totalItems = response.total;//更新总记录数

            }
        );
    }
    //保存
    $scope.save = function () {
        var methodName = 'add';//方法名称
        if ($scope.entity.id != null) {//如果有ID
            methodName = 'update';//则执行修改方法
        }
        $http.post('../brand/' + methodName + '.do', $scope.entity).success(
            function (response) {
                if (response.success) {
                    //重新查询
                    $scope.reloadList();//重新加载
                } else {
                    alert(response.message);
                }
            }
        );
    }
    //查询实体
    $scope.findOne = function (id) {
        $http.get('../brand/findOne.do?id=' + id).success(
            function (response) {
                $scope.entity = response;
            }
        );
    }
    //批量删除
    $scope.dele = function () {
        $http.get('../brand/delete.do?ids=' + $scope.selectIds).success(
            function (response) {
                if (response.success) {
                    $scope.reloadList();
                } else {
                    alert(response.message);
                }
            }
        );
    }
    $scope.searchEntity={};//定义搜索对象，初始化。。
    //条件查询
    $scope.search=function (page, size) {
        $http.post("../brand/search.do?page="+page+"&size=" + size,
            $scope.searchEntity).success(
            function (response) {
                $scope.list=response.rows;//给列表变量赋值
                $scope.paginationConf.totalItems = response.total;//总记录数
            }
        )
    }

});