appControllers.controller('QuizQuestionCreateCtrl', ['$scope', '$routeParams', '$http', '$location',
    function ($scope, $routeParams, $http, $location) {
        $scope.tinymceOptions = {
            plugins: [
                'advlist autolink lists link image charmap print preview hr anchor pagebreak',
                'searchreplace wordcount visualblocks visualchars code fullscreen',
                'insertdatetime media nonbreaking save table contextmenu directionality',
                'emoticons template paste textcolor colorpicker textpattern imagetools'
            ],
            toolbar1: 'insertfile undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image'
        };

        $scope.id = $routeParams.id;
        $scope.multipleChoiceSelect = multipleChoiceSelect;

        $scope.quiz = {};
        $scope.uploadedImages = [];
        $scope.question = "";
        $scope.choiceA = "";
        $scope.choiceB = "";
        $scope.choiceC = "";
        $scope.choiceD = "";
        $scope.correctAnswer = "";
        $scope.no = "";

        $http.post("/rest/quiz/get/"+$scope.id).success(function(quiz, status) {
            $scope.quiz = quiz;
        });

        $('#fileupload').fileupload({
            dataType: 'json',
            multipart: true,
            url: baseRestUrl+'/quiz-question/upload-image'
        })
        .on('fileuploadadd', function (e, data) {

        })
        .on('fileuploadsend', function (e, data) {
            $(".progress-animated").show();
        })
        .on('fileuploadalways', function (e, data) {
            $(".progress-animated").hide();
            var result = data._response.result;
            var id = result.id;
            var filename = result.imgSrc;
            $scope.uploadedImages.push(id);
            data.context = $('<div/>').appendTo('#uploadedFiles');
                var node = $('<p class="file" />')
                    .append("<img src='"+baseUrl+"/images/"+filename+"' alt='' />")
                    .append($('<p/>').text("/images/"+filename));
                node.appendTo(data.context);
        });

        $scope.submit = function() {

            var quiz = {id:$scope.id};
            var quizQuestion = {};
            quizQuestion.no = $scope.no;
            quizQuestion.question = $scope.question;
            quizQuestion.choiceA = $scope.choiceA;
            quizQuestion.choiceB = $scope.choiceB;
            quizQuestion.choiceC = $scope.choiceC;
            quizQuestion.choiceD = $scope.choiceD;
            quizQuestion.correctAnswer = $scope.correctAnswer.id;
            quizQuestion.quiz = quiz;

            console.log(quizQuestion);

            if($scope.uploadedImages.length>0) {
                var questionImageList = [];
                for(var i=0;i<$scope.uploadedImages.length;i++) {
                    var choiceImage = {id:$scope.uploadedImages[i]};
                    questionImageList.push(choiceImage);
                }
                quizQuestion.questionImageList = questionImageList;
            }

            $http.post("/rest/quiz-question/create",quizQuestion).success(function(data, status) {
                location.href = baseUrl+"/pages/index.html#/quiz-question-list/"+$scope.quiz.id;
            });
        }

    }]);

appControllers.controller('QuizQuestionUpdateCtrl', ['$scope', '$routeParams', '$http', '$location',
    function ($scope, $routeParams, $http, $location) {
        $scope.tinymceOptions = {
            plugins: [
                'advlist autolink lists link image charmap print preview hr anchor pagebreak',
                'searchreplace wordcount visualblocks visualchars code fullscreen',
                'insertdatetime media nonbreaking save table contextmenu directionality',
                'emoticons template paste textcolor colorpicker textpattern imagetools'
            ],
            toolbar1: 'insertfile undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image'
        };

        $scope.id = $routeParams.questionId;
        $scope.quizId = $routeParams.quizId;
        $scope.multipleChoiceSelect = multipleChoiceSelect;

        $scope.quiz = {};
        $scope.uploadedImages = [];
        $scope.questionImageList = []
        $scope.question = "";
        $scope.choiceA = "";
        $scope.choiceB = "";
        $scope.choiceC = "";
        $scope.choiceD = "";
        $scope.correctAnswer = "";
        $scope.no = "";

        $http.post("/rest/quiz/get/"+$scope.quizId).success(function(quiz, status) {
            $scope.quiz = quiz;
        });

        $http.post("/rest/quiz-question/get/"+$scope.id).success(function(question, status) {
            $scope.question = question.question;
            $scope.choiceA = question.choiceA;
            $scope.choiceB = question.choiceB;
            $scope.choiceC = question.choiceC;
            $scope.choiceD = question.choiceC;
            $scope.correctAnswer = {id:question.correctAnswer};
            $scope.no = question.no;
            $scope.questionImageList = question.questionImageList;
            if($scope.questionImageList!=null) {
                for(var i=0;i<$scope.questionImageList.length;i++) {
                    var imgSrc = $scope.questionImageList[i].imgSrc;
                    $scope.questionImageList[i].imgUrl = baseUrl+"/images/"+imgSrc;
                    $scope.questionImageList[i].imgDesc = "/images/"+imgSrc;
                }
            }
        });

        $('#fileupload').fileupload({
                dataType: 'json',
                multipart: true,
                url: baseRestUrl+'/quiz-question/upload-image'
            })
            .on('fileuploadadd', function (e, data) {

            })
            .on('fileuploadsend', function (e, data) {
                $(".progress-animated").show();
            })
            .on('fileuploadalways', function (e, data) {
                $(".progress-animated").hide();
                var result = data._response.result;
                var id = result.id;
                var filename = result.imgSrc;
                $scope.uploadedImages.push(id);
                data.context = $('<div/>').appendTo('#uploadedFiles');
                var node = $('<p class="file" />')
                    .append("<img src='"+baseUrl+"/images/"+filename+"' alt='' />")
                    .append($('<p/>').text("/images/"+filename));
                node.appendTo(data.context);
            });

        $scope.submit = function() {

            var quiz = {id:$scope.quizId};
            var quizQuestion = {};
            quizQuestion.id = $scope.id;
            quizQuestion.no = $scope.no;
            quizQuestion.question = $scope.question;
            quizQuestion.choiceA = $scope.choiceA;
            quizQuestion.choiceB = $scope.choiceB;
            quizQuestion.choiceC = $scope.choiceC;
            quizQuestion.choiceD = $scope.choiceD;
            quizQuestion.correctAnswer = $scope.correctAnswer.id;

            console.log(quizQuestion);

            if($scope.uploadedImages.length>0) {
                var questionImageList = [];
                for(var i=0;i<$scope.uploadedImages.length;i++) {
                    var choiceImage = {id:$scope.uploadedImages[i]};
                    questionImageList.push(choiceImage);
                }
                quizQuestion.questionImageList = questionImageList;
            }

            $http.post("/rest/quiz-question/update/"+quizQuestion.id,quizQuestion).success(function(data, status) {
                location.href = baseUrl+"/pages/index.html#/quiz-question-list/"+$scope.quizId;
            });
        }

    }]);

appControllers.controller('QuizQuestionListCtrl', ['$scope', '$routeParams', '$http', '$location', '$sce',
    function ($scope, $routeParams, $http, $location, $sce) {
        $scope.quizId = parseInt($routeParams.id);
        $scope.quiz = {name:""};
        var criteria = {pageSize:paginationSize,pageNumber:1,quizId:$scope.quizId};
        $http.post("/rest/quiz-question/search",criteria).success(function(searchResult, status) {
            console.log(searchResult);
            $scope.quizQuestionList = searchResult.values;
            for(var i=0;i<$scope.quizQuestionList.length;i++) {
                $scope.quizQuestionList[i].questionSanitize = $sce.trustAsHtml($scope.quizQuestionList[i].question);
            }
            $scope.selectedPage = 1;
            $scope.totalPages = searchResult.totalPages;
            $scope.arrPages = [];
            if($scope.totalPages > 0) {
                for(var i=0;i<$scope.totalPages;i++ ){
                    $scope.arrPages[i] = i + 1;
                }
            }
        });

        $http.post("/rest/quiz/get/"+$scope.quizId).success(function(data, status) {
            $scope.quiz = data;
        });

        $scope.gotoPage = function(page) {
            $scope.selectedPage = page;
            var criteria = {pageSize:paginationSize,pageNumber:$scope.selectedPage,quizId:$scope.quizId};
            $http.post("/rest/quiz-question/search",criteria).success(function(searchResult, status) {
                console.log(searchResult);
                $scope.quizQuestionList = searchResult.values;
                for(var i=0;i<$scope.quizQuestionList.length;i++) {
                    $scope.quizQuestionList[i].questionSanitize = $sce.trustAsHtml($scope.quizQuestionList[i].question);
                }
                $scope.selectedPage = $scope.selectedPage;
            });
        }

        $scope.createQuiz = function() {
            location.href = baseUrl+"/pages/index.html#/quiz-question-create/"+$scope.quizId;
        }
    }]);