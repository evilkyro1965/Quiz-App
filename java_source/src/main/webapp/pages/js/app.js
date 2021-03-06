var baseUrl = "http://localhost:8080";
var baseRestUrl = baseUrl + "/rest";
var paginationSize = 5;
var allRow = 1000000;
var multipleChoiceSelect = [{id:'A',name:'A'},{id:'B',name:'B'},{id:'C',name:'C'},{id:'D',name:'D'}];

var quizApp = angular.module('quizApp', [
    'ngRoute',
    'appControllers',
    'ngSanitize',
    'ui.tinymce'
]);

var appControllers = angular.module('appControllers', []);

quizApp.config(['$routeProvider',
    function($routeProvider) {
        $routeProvider.
        when('/quiz-list', {
            templateUrl: 'partials/quiz-list.html',
            controller: 'QuizListCtrl'
        }).
        when('/quiz-create', {
            templateUrl: 'partials/quiz-create.html',
            controller: 'QuizCreateCtrl'
        }).
        when('/quiz-update/:id', {
            templateUrl: 'partials/quiz-update.html',
            controller: 'QuizUpdateCtrl'
        }).
        when('/quiz-question-list/:id', {
            templateUrl: 'partials/quiz-question-list.html',
            controller: 'QuizQuestionListCtrl'
        }).
        when('/quiz-question-create/:id', {
            templateUrl: 'partials/quiz-question-create.html',
            controller: 'QuizQuestionCreateCtrl'
        }).
        when('/quiz-question-update/:quizId/:questionId', {
            templateUrl: 'partials/quiz-question-update.html',
            controller: 'QuizQuestionUpdateCtrl'
        }).
        when('/quiz-search', {
            templateUrl: 'partials/quiz-search.html',
            controller: 'QuizSearchCtrl'
        }).
        when('/quiz-session-run/:id', {
            templateUrl: 'partials/quiz-session-running.html',
            controller: 'QuizSessionRunCtrl'
        }).
        when('/quiz-sessions', {
            templateUrl: 'partials/quiz-sessions.html',
            controller: 'QuizSessionListCtrl'
        }).
        when('/quiz-result/:id', {
            templateUrl: 'partials/quiz-result.html',
            controller: 'QuizResultCtrl'
        }).
        otherwise({
            templateUrl: 'partials/landing.html',
            controller: 'LandingCtrl'
        });
    }]);

$.ajax({
    method: "POST",
    url: "/rest/login/get-logged-user"
})
.done(function( user ) {
    if(user.userType=="LECTURER") {
        $("#lecturerMenu").show();
        $("#studentMenu").hide();
    }
    else {
        $("#lecturerMenu").hide();
        $("#studentMenu").show();
    }
});