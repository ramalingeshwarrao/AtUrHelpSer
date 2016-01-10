(
		function() {
			
			'use strict';
			
			$PROVIDER.MilkAppartmentController = ['$scope', '$http', '$location', function ($scope, $http, $location) {
				
				$scope.submit = function() {

					if ($scope.routesel.type == undefined || $scope.routesel.type == "0") {
						BootstrapDialog.alert("Please select route");
						return;
					}
					
					$scope.loading = true;
					$scope.apartmentdata = {
						"appsubject" : $scope.subject,
						"appname" : $scope.appname,
						"routeid" : $scope.routesel.type
					};
					
					// create http post request
					$http(
							{
								method : 'POST',
								url : $PROVIDER.providerMilkRest+'/insert/appartment',
								data : $scope.apartmentdata,
								headers : {
									'Content-Type' : 'application/json'
								}
							}).success(function(data) {
								$scope.loading = false;
						if (data.status == 0) {
							 BootstrapDialog.alert("succesfuly inserted");
							$scope.subject = "";
							$scope.appname = "";
							$scope.routesel.type = "0";
						} else {
							$scope.loading = false;
							BootstrapDialog.alert("fail to insert record");
						}
					});

				};
				
				//Get route details
				$scope.routedetails = function() {
					$scope.loading = true;
					$http (
						 {
							 method :  'GET',
							 url : $PROVIDER.providerMilkRest+'/routedetails',
							 headers : {
									'Content-Type' : 'application/json'
								}
						 }).success(function(data) {
							 $scope.loading = false;
							 if (data != "") {
								 if (data.route.length == undefined) {
									 $scope.routedetailsArray = [];
									 $scope.routedetailsArray[0] = data.route;
									 $scope.routedetails = $scope.routedetailsArray;
									 $scope.routesel = {type : $scope.routedetailsArray[0].id};
								 } else  {
									 $scope.routedetails = data.route;
									 $scope.routesel = {type : $scope.routedetails[0].id};
								 }	 
							 } else {
								 $scope.routedetails = "";
								 BootstrapDialog.alert("No routes found please create a route and continue");
								 $location.path( '/milkroute' );
							 }
						 }).error(function(data, status, headers, config) {
							$scope.loading = false;
							BootstrapDialog.alert("Fail to get route details, contact administrator for support");
						 });
				};
				// End of route details
				$scope.routedetails();
			}];
		}
)();