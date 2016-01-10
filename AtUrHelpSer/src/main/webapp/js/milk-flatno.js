(
		function() {
			
			'use strict';
			
			$PROVIDER.MilkFlatNoController = ['$scope', '$http', '$location', function ($scope, $http, $location) {
				
				$scope.submit = function() {

					$scope.loading = true;
					$scope.flatnotdata = {
						"roomno" : $scope.faltno,
						"appid" : $scope.apartmentsel.type
					};
					
					// create http post request
					$http(
							{
								method : 'POST',
								url : $PROVIDER.providerMilkRest+'/insert/flatno',
								data : $scope.flatnotdata,
								headers : {
									'Content-Type' : 'application/json'
								}
							}).success(function(data) {
								$scope.loading = false;
						if (data.status == 0) {
							BootstrapDialog.alert("succesfuly inserted");
							$scope.faltno = "";
							$scope.apartmentsel.type = "0";
						} else {
							BootstrapDialog.alert("fail to insert record");
						}
					});

				};
				
				//Get apartment details
				$scope.apartmentdetailsFun = function(id) {
					$scope.loading = true;
					$http (
						 {
							 method :  'GET',
							 url : $PROVIDER.providerMilkRest+'/appartmentdetails?route_id='+id+'',
							 headers : {
									'Content-Type' : 'application/json'
								}
						 }).success(function(data) {
							 $scope.loading = false;
							 if (data != "") {
								 if (data.appartment.length == undefined) {
									 $scope.apartmentdetailsArray = [];
									 $scope.apartmentdetailsArray[0] = data.appartment;
									 $scope.apartmentdetails = $scope.apartmentdetailsArray;
									 $scope.apartmentsel = {type : $scope.apartmentdetailsArray[0].id};
								 } else  {
									 $scope.apartmentdetails = data.appartment;
									 $scope.apartmentsel = {type : $scope.apartmentdetails[0].id};
								 }	 
							 } else {
								 $scope.loading = false;
								 $scope.apartmentdetails = "";
								 BootstrapDialog.alert("No Apartments found please create a Apartment and continue");
								 $location.path( '/milkappartment' );
							 }
						 }).error(function(data, status, headers, config) {
							 BootstrapDialog.alert("Fail to get route details, contact administrator for support");
						 });
				};
				// End of apartment details
				
				//Change Route
				$scope.changeRoute = function(id) {
					$scope.apartmentdetailsFun(id);
				};
				//End of change route
				
				//Get route details
				$scope.routedetails = function() {
					$http (
						 {
							 method :  'GET',
							 url : $PROVIDER.providerMilkRest+'/routedetails',
							 headers : {
									'Content-Type' : 'application/json'
								}
						 }).success(function(data) {
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
							 BootstrapDialog.alert("Fail to get route details, contact administrator for support");
						 });
				};
				// End of route details
				$scope.routedetails();
				
				
				
				
			}];
		}
)();