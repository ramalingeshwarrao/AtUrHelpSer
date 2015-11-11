(function(){
	
	
    var aturhelpapp = angular.module('aturhelpapp',[]);
    aturhelpapp.controller('logincontroller', function($scope) {
    	$scope.username="";
    	$scope.password="";
    	$scope.login = function () {
    		alert("inside login method");
    	}
    });

})();