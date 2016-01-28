(
		function() {
			
			'use strict';
			
			$PROVIDER.MilkCategoryController = ['$scope', '$http', function ($scope, $http) {
				
				$scope.submit = function() {

					$scope.loading = true;
					$scope.categorydata = {
						"categoryname" : $scope.category
					};
					
					// create http post request
					$http(
							{
								method : 'POST',
								url : $PROVIDER.providerMilkRest+'/insert/category',
								data : $scope.categorydata,
								headers : {
									'Content-Type' : 'application/json'
								}
							}).success(function(data) {
								$scope.loading = false;
						if (data.status == 0) {
							BootstrapDialog.alert("succesfuly inserted");
							//After succesful insertion empty the fields
							$scope.subject = "";
							$scope.route = "";
						} else {
							BootstrapDialog.alert("fail to insert record");
						}
					});

				};
			}];
		}
)();