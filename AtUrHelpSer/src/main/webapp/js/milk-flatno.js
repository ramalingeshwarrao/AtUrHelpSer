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
							alert("succesfuly inserted");
						} else {
							alert("fail to insert record");
						}
					});

				};
				
				//Get route details
				$scope.apartmentdetails = function() {
					$http (
						 {
							 method :  'GET',
							 url : $PROVIDER.providerMilkRest+'/appartmentdetails',
							 headers : {
									'Content-Type' : 'application/json'
								}
						 }).success(function(data) {
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
								 $scope.apartmentdetails = "";
								 alert("No Apartments found please create a Apartment and continue");
								 $location.path( '/milkappartment' );
							 }
						 }).error(function(data, status, headers, config) {
						 	alert("Fail to get route details, contact administrator for support");
						 });
				};
				// End of route details
				$scope.apartmentdetails();
			}];
		}
)();