var app = angular.module("app",['ngRoute']);
app.config(function ($routeProvider){
    $routeProvider.when('/',{
        templateUrl:"main.html",
        controller:"AppCtrl"
    });
});
app.controller("AppCtrl", function($scope){
    $scope.test = "hello";
});