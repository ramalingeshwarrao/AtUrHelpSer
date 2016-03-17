(
		function() {
			
			'use strict';
			
			$PROVIDER.MilkRouteInactiveController = ['$scope', '$http', '$location', function ($scope, $http, $location) {

				$scope.inactiveroute = function() {
					$scope.loading = true;
					var routeId = $scope.routesel.type;
					
					if (routeId == "0") {
						 BootstrapDialog.alert("Kindly Select Route");
						 $scope.loading = false;
						 return ;
					}
					
					$http (
							 {
								 method :  'GET',
								 url : $PROVIDER.providerMilkRest+'/inactiveroute?routeid='+routeId,
								 headers : {
										'Content-Type' : 'application/json'
									}
							 }).success(function(data) {
								 $scope.loading = false;
									 if (data == 0) {
										 BootstrapDialog.alert("Sucessfully Updated");	 
									 } else {
										 BootstrapDialog.alert("Failed to update");
									 }
							 }).error(function(data, status, headers, config) {
								 $scope.loading = false;
								 BootstrapDialog.alert("Fail to inactive record, contact administrator for support");
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
								 $scope.loading = false;
								 $scope.routedetails = "";
								 BootstrapDialog.alert("No routes found please create a route and continue");
								 $location.path( '/milkroute' );
							 }
						 }).error(function(data, status, headers, config) {
							 BootstrapDialog.alert("Fail to get route details, contact administrator for support");
						 });
				};
				// End of route details
				$scope.routedetails();
				
			}];
			
		}
)();