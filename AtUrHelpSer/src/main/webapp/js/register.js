(function() {

	'use strict';

	$ATURHELP.RegisterController = [
			'$scope',
			'$http',
			function($scope, $http) {

				$scope.register = function() {

					$scope.admininfo = {
						"name" : $scope.fullname,
						"email" : $scope.email,
						"mobileno" : $scope.number
					};

					// create http post request
					$http(
							{
								method : 'POST',
								url : $ATURHELP.aturhelpRest+'/insert/registeradmin',
								data : $scope.admininfo,
								headers : {
									'Content-Type' : 'application/json'
								}
							}).success(function(data) {
						if (data.status == 0) {
							BootstrapDialog.alert("succesfuly inserted");
						} else {
							BootstrapDialog.alert("fail to insert record");
						}
					});

				};

			} ];
}

)();