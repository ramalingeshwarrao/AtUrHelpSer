(function () {
	var registerapp = angular.module('registerapp',[]);
	registerapp.controller('registercontroller', function($scope, $http) {
		
		$scope.register = function () {
			
			alert($scope.fullname);
			alert($scope.email);
			alert($scope.number);
			
			
			$scope.admininfo = {
					"name":$scope.fullname,
					"email":$scope.email,
					"mobileno":$scope.number
				};
			
			//create http post request
			$http({
				method : 'POST',
				url : 'http://localhost:8090/AtUrHelpSer/aturhelp/hr/insert/registeradmin',
				data : $scope.admininfo,
				headers : { 'Content-Type' : 'application/json'}
			}).success(function(data) {
				if (data.status == 0) {
					alert("succesfuly inserted");
				} else {
					alert("fail to insert record");
				}
			});
			
		};
	});
}
		
)();