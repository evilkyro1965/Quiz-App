var baseUrl = "http://localhost:8080";
var baseRestUrl = baseUrl + "/rest";
var paginationSize = 5;

var quizApp = angular.module('quizApp', [
    'ngRoute',
    'appControllers'
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
        when('/quiz-question-create/:id', {
            templateUrl: 'partials/quiz-question-create.html',
            controller: 'QuizQuestionCreateCtrl'
        }).
        otherwise({
            templateUrl: 'partials/quiz-list.html',
            controller: 'QuizListCtrl'
        });
    }]);