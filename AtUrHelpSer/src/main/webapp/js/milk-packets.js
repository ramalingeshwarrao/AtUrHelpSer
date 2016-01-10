(
		function() {
			
			'use strict';
			
			$PROVIDER.MilkPacketController = ['$scope', '$http', '$location', function ($scope, $http, $location) {
				
				$scope.submit = function() {

					$scope.loading = true;
					$scope.milkdata = {
						"subject" : $scope.subject,
						"milkname" : $scope.milkid,
						"cost" : $scope.cost
					};
					
					// create http post request
					$http(
							{
								method : 'POST',
								url : $PROVIDER.providerMilkRest+'/insert/milkpackets',
								data : $scope.milkdata,
								headers : {
									'Content-Type' : 'application/json'
								}
							}).success(function(data) {
								$scope.loading = false;
						if (data.status == 0) {
							BootstrapDialog.alert("succesfuly inserted");
							$scope.subject = "";
							$scope.milkid = "";
							$scope.cost = "";
						} else {
							BootstrapDialog.alert("fail to insert record");
						}
					});

				};
			}];
		}
)();