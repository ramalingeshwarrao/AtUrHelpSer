(
		function() {
			
			'use strict';
			
			$PROVIDER.MilkLocationController = ['$scope', '$http', function ($scope, $http) {
				
				$scope.submit = function() {

					$scope.loading = true;
					$scope.routedata = {
						"subject" : $scope.subject,
						"name" : $scope.location
					};
					
					// create http post request
					$http(
							{
								method : 'POST',
								url : $PROVIDER.providerMilkRest+'/insert/location',
								data : $scope.routedata,
								headers : {
									'Content-Type' : 'application/json'
								}
							}).success(function(data) {
								$scope.loading = false;
						if (data.status == 0) {
							alert("succesfuly inserted");
						} else {
							alert("fail to insert record");
						}
					});

				};
			}];
		}
)();