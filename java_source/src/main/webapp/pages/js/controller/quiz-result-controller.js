appControllers.controller('QuizResultCtrl', ['$scope', '$routeParams', '$http', '$location', '$sce',
    function ($scope, $routeParams, $http, $location, $sce) {
        $scope.id = parseInt($routeParams.id);
        $scope.quizSession = {};
        $scope.quizAnswerList = [];
        $scope.selectedPage = 1;
        $scope.totalPages = 1;

        $scope.rightCount = 0;
        $scope.wrongCount = 0;

        $http.post("/rest/quiz-session/get/"+$scope.id).success(function(data, status) {
            $scope.quizSession = data;
            getAnswerQuestionList($scope,$http,$sce,getQuestionListFunc);
        });

    }]);

var getQuestionListFunc = function getQuestionList($scope,$http,$sce) {
    var quiz = $scope.quizSession.quiz;
    var criteria = {pageSize:paginationSize,pageNumber:1,pageSize:allRow,quizId:quiz.id};
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
            var answer = $scope.quizQuestionList[i].answer;
            if(answer!=null && answer== $scope.quizQuestionList[i].correctAnswer) {
                $scope.rightCount++;
            }
            else {
                $scope.wrongCount++;
            }
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