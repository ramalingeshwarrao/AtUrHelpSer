(
		function () {
		
			'use strict';
			
			$ATURHELP.AturhelpController = ['$scope', '$http', function ($scope, $http) {
				$scope.username = "";
				$scope.pass = "";
				$scope.login = function() {
					BootstrapDialog.alert("inside the login method 1234345");
				};
			}];
		}
		
)();