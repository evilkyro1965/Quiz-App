appControllers.controller('QuizCreateCtrl', ['$scope', '$http', '$location',
    function ($scope, $http, $location) {
        $scope.validation = [
            {'fieldName':'name','validationType':'IsNotEmpty','errorDiv':'#nameErrorMessage'}
            //{'fieldName':'categoryId','validationType':'IsNotEmpty','errorDiv':'#categoryErrorMessage'}
        ];
        $scope.name = "";
        $scope.categoryId = "";
        $scope.quizCategories = [];
        $http.post("/rest/quiz-category").success(function(searchResult, status) {
            $scope.quizCategories = searchResult.values;
            $scope.quizCategories.unshift({id:'',name:'- Category -'});
        });

        $scope.submit = function() {
            var saveValidator = new ValueValidator($scope,$scope.validation);
            var isValid = saveValidator.validate();

            if(isValid) {
                var quiz = getQuizJsonObject($scope);
                $http.post("/rest/quiz/create",quiz).success(function(searchResult, status) {
                    $location.path("/quiz-list");
                });
            }
        }
    }]);

appControllers.controller('QuizUpdateCtrl', ['$scope', '$routeParams', '$http', '$location',
    function ($scope, $routeParams, $http, $location) {
        $scope.id = $routeParams.id;

        $scope.validation = [
            {'fieldName':'name','validationType':'IsNotEmpty','errorDiv':'#nameErrorMessage'},
            {'fieldName':'categoryId','validationType':'IsNotEmpty','errorDiv':'#categoryErrorMessage'}
        ];
        $scope.name = "";
        $scope.category = {id:""};
        $scope.quizCategories = [];
        $http.post("/rest/quiz-category").success(function(searchResult, status) {
            $scope.quizCategories = searchResult.values;
            $scope.quizCategories.unshift({id:'',name:'- Category -'});

            $http.post("/rest/quiz/get/"+$scope.id).success(function(quiz, status) {
                $scope.name = quiz.name;
                if(quiz.category!=null) {
                    $scope.category.id = quiz.category.id;
                    console.log($scope.category);
                }
            });

        });

        $scope.submit = function() {
            $scope.categoryId = String($scope.category.id);
            var saveValidator = new ValueValidator($scope,$scope.validation);
            var isValid = saveValidator.validate();

            if(isValid) {
                var quiz = getQuizJsonObject($scope);
                quiz.id = $scope.id;
                $http.post("/rest/quiz/update/"+$scope.id,quiz).success(function(searchResult, status) {
                    $location.path("/quiz-list");
                });
            }
        }
    }]);

appControllers.controller('QuizListCtrl', ['$scope', '$http',
    function ($scope, $http) {
        var criteria = {pageSize:paginationSize,pageNumber:1};
        $http.post("/rest/quiz/search",criteria).success(function(searchResult, status) {
            console.log(searchResult);
            $scope.quizList = searchResult.values;
            $scope.selectedPage = 1;
            $scope.totalPages = searchResult.totalPages;
            $scope.arrPages = [];
            if($scope.totalPages > 0) {
                for(var i=0;i<$scope.totalPages;i++ ){
                    $scope.arrPages[i] = i + 1;
                }
            }
        });

        $scope.gotoPage = function(page) {
            $scope.selectedPage = page;
            criteria.pageNumber = page;
            $http.post("/rest/quiz/search",criteria).success(function(searchResult, status) {
                console.log(searchResult);
                $scope.quizList = searchResult.values;
            });
        }
    }]);

function getQuizJsonObject($scope) {
    var quiz = {};
    var category = {};
    category.id = $scope.category.id;
    quiz.name = $scope.name;
    quiz.category = category;

    return quiz;
}