appControllers.controller('QuizSearchCtrl', ['$scope', '$http',
    function ($scope, $http) {
        $scope.quizName = "";
        $scope.category = "";
        var criteria = {pageSize:paginationSize,pageNumber:1};
        $scope.quizCategories = [];
        $http.post("/rest/quiz-category").success(function(searchResult, status) {
            $scope.quizCategories = searchResult.values;
            $scope.quizCategories.unshift({id:'',name:'- Category -'});
        });

        $http.post("/rest/quiz-session/search-quiz",criteria).success(function(searchResult, status) {
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
            criteria.categoryId = $scope.category.id;
            criteria.name = $scope.quizName;
            $http.post("/rest/quiz-session/search-quiz",criteria).success(function(searchResult, status) {
                console.log(searchResult);
                $scope.quizList = searchResult.values;
            });
        }

        $scope.search = function() {
            criteria.pageNumber = 1;
            criteria.categoryId = $scope.category.id;
            criteria.name = $scope.quizName;
            $http.post("/rest/quiz-session/search-quiz",criteria).success(function(searchResult, status) {
                console.log(searchResult);
                $scope.quizList = searchResult.values;
            });
        }

        $scope.takeQuiz = function(quizId) {
            var quizSession = {quiz:{id:quizId}};
            $http.post("/rest/quiz-session/create",quizSession).success(function(data, status) {
                var id = data.id;
                location.href = "/pages/index.html#/quiz-session-run/"+id;
            });
        }

    }]);

appControllers.controller('QuizSessionListCtrl', ['$scope', '$http',
    function ($scope, $http) {
        $scope.quizName = "";
        $scope.filterSelect = [{id:null,name:"- All -"},{id:"false",name:"Not Completed"},{id:"true",name:"Completed"}]
        $scope.filter = $scope.filterSelect[0];

        var isCompletedFilter = $scope.filter.id==null ? null : $.parseJSON($scope.filter.id);
        var criteria = {pageSize:paginationSize,pageNumber:1,completed:isCompletedFilter};

        $http.post("/rest/quiz-session/search",criteria).success(function(searchResult, status) {
            console.log(searchResult);
            $scope.sessionList = searchResult.values;
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
            var isCompletedFilter = $scope.filter.id==null ? null : $.parseJSON($scope.filter.id);
            criteria.completed = isCompletedFilter;
            $http.post("/rest/quiz-session/search",criteria).success(function(searchResult, status) {
                console.log(searchResult);
                $scope.sessionList = searchResult.values;
                $scope.totalPages = searchResult.totalPages;
                $scope.arrPages = [];
                if($scope.totalPages > 0) {
                    for(var i=0;i<$scope.totalPages;i++ ){
                        $scope.arrPages[i] = i + 1;
                    }
                }
            });
        }

        $scope.search = function() {
            criteria.pageNumber = 1;
            var isCompletedFilter = $scope.filter.id==null ? null : $.parseJSON($scope.filter.id);
            criteria.completed = isCompletedFilter;
            $http.post("/rest/quiz-session/search",criteria).success(function(searchResult, status) {
                console.log(searchResult);
                $scope.selectedPage = 1;
                $scope.sessionList = searchResult.values;
                $scope.totalPages = searchResult.totalPages;
                $scope.arrPages = [];
                if($scope.totalPages > 0) {
                    for(var i=0;i<$scope.totalPages;i++ ){
                        $scope.arrPages[i] = i + 1;
                    }
                }
            });
        }

    }]);

appControllers.controller('QuizSessionRunCtrl', ['$scope', '$routeParams', '$http', '$location', '$sce',
    function ($scope, $routeParams, $http, $location, $sce) {
        $scope.id = parseInt($routeParams.id);
        $scope.quizSession = {};
        $scope.quizAnswerList = [];
        $scope.selectedPage = 1;
        $scope.totalPages = 1;

        $http.post("/rest/quiz-session/get/"+$scope.id).success(function(data, status) {
            $scope.quizSession = data;
            getAnswerQuestionList($scope,$http,$sce,getQuestionListFunc);
        });

        $scope.nextPage = function() {
            $scope.selectedPage++;
            saveProgress($scope,$http,$sce);
            getAnswerQuestionList($scope,$http,$sce,getQuestionListFunc);
        }

        $scope.prevPage = function() {
            $scope.selectedPage--;
            saveProgress($scope,$http,$sce);
            getAnswerQuestionList($scope,$http,$sce,getQuestionListFunc);
        }

        $scope.saveProgress = function() {
            saveProgress($scope,$http,$sce,false);
        }

        $scope.saveAndQuit = function() {
            saveProgress($scope,$http,$sce,true);
        }

        $scope.complete = function() {
            complete($scope,$http,$sce);
        }


        $scope.createQuiz = function() {
            location.href = "/pages/index.html#/quiz-question-create/"+$scope.quizId;
        }

    }]);

var getQuestionListFunc = function getQuestionList($scope,$http,$sce) {
    var quiz = $scope.quizSession.quiz;
    var criteria = {pageSize:paginationSize,pageNumber:$scope.selectedPage,quizId:quiz.id};
    console.log(criteria);
    $http.post("/rest/quiz-question/search",criteria).success(function(searchResult, status) {
        console.log(searchResult);
        $scope.quizQuestionSearchResult = searchResult;
        $scope.quizQuestionList = searchResult.values;
        for(var i=0;i<$scope.quizQuestionList.length;i++) {
            var question = $scope.quizQuestionList[i];
            $scope.quizQuestionList[i].question = $sce.trustAsHtml($scope.quizQuestionList[i].question);
            $scope.quizQuestionList[i].choiceA = $sce.trustAsHtml($scope.quizQuestionList[i].choiceA);
            $scope.quizQuestionList[i].choiceB = $sce.trustAsHtml($scope.quizQuestionList[i].choiceB);
            $scope.quizQuestionList[i].choiceC = $sce.trustAsHtml($scope.quizQuestionList[i].choiceC);
            $scope.quizQuestionList[i].choiceD = $sce.trustAsHtml($scope.quizQuestionList[i].choiceD);
            $scope.quizQuestionList[i].answer = getAnsweredOfQuestion(question.id,$scope,$http);
        }

        $scope.totalPages = searchResult.totalPages;
    });
}

function getAnswerQuestionList($scope,$http,$sce,callback) {
    $http.post("/rest/quiz-answer/get-session-answers/"+$scope.id).success(function(data, status) {
        console.log(data);
        $scope.quizAnswerList = data;
        callback($scope,$http,$sce);
    });
}

function getAnsweredOfQuestion(questionId,$scope,$http,$sce) {
    var quizAnswerList = $scope.quizAnswerList;
    for(var i=0;i<quizAnswerList.length;i++) {
        var quizAnswer = quizAnswerList[i];
        if(questionId==quizAnswer.question.id) {
            return quizAnswer.answer;
        }
    }
    return null;
}

function saveProgress($scope,$http,$sce,isQuit) {
    var arrAnswer = [];

    for(var i=0;i<$scope.quizQuestionList.length;i++) {
        var question = $scope.quizQuestionList[i];
        var answer = question.answer;
        var answer = {question:{id:question.id},quizSession:{id:$scope.quizSession.id},answer:answer};
        arrAnswer.push(answer);
    }
    $http.post("/rest/quiz-answer/create-all",arrAnswer).success(function(data, status) {
        console.log(status);
        if(isQuit) {
            location.href = "/pages/index.html#/quiz-sessions";
        }
    });
}

function complete($scope,$http,$sce) {
    var arrAnswer = [];

    for(var i=0;i<$scope.quizQuestionList.length;i++) {
        var question = $scope.quizQuestionList[i];
        var answer = question.answer;
        var answer = {question:{id:question.id},quizSession:{id:$scope.quizSession.id},answer:answer};
        arrAnswer.push(answer);
    }
    $http.post("/rest/quiz-answer/create-all-complete",arrAnswer).success(function(data, status) {
        console.log(status);
        location.href = "/pages/index.html#/quiz-result/"+$scope.quizSession.id;
    });
}

function getQuizJsonObject($scope) {
    var quiz = {};
    var category = {};
    category.id = $scope.category.id;
    quiz.name = $scope.name;
    quiz.category = category;

    return quiz;
}