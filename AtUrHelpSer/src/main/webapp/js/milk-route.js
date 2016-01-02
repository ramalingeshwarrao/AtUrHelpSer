(
		function() {
			
			'use strict';
			
			$PROVIDER.MilkRouteController = ['$scope', '$http', function ($scope, $http) {
				
				$scope.submit = function() {

					$scope.loading = true;
					$scope.routedata = {
						"routesubject" : $scope.subject,
						"routeid" : $scope.route
					};
					
					// create http post request
					$http(
							{
								method : 'POST',
								url : $PROVIDER.providerMilkRest+'/insert/route',
								data : $scope.routedata,
								headers : {
									'Content-Type' : 'application/json'
								}
							}).success(function(data) {
								$scope.loading = false;
						if (data.status == 0) {
							alert("succesfuly inserted");
							//After succesful insertion empty the fields
							$scope.subject = "";
							$scope.route = "";
						} else {
							alert("fail to insert record");
						}
					});

				};
			}];
		}
)();