appControllers.controller('QuizQuestionCreateCtrl', ['$scope', '$routeParams', '$http', '$location',
    function ($scope, $routeParams, $http, $location) {
        $scope.id = $routeParams.id;

        $scope.quiz = {};
        $scope.no = "";

        $http.post("/rest/quiz/get/"+$scope.id).success(function(quiz, status) {
            $scope.quiz = quiz;
        });

        $('#fileupload').fileupload({
            dataType: 'json',
            multipart: true,
            url: baseRestUrl+'/quiz-question/upload-image'
        });
    }]);