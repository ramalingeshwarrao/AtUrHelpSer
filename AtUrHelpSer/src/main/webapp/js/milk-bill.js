(
		function() {
			
			'use strict';
			
			$PROVIDER.MilkBillController = ['$scope', '$http', function ($scope, $http) {
				
				$scope.myDate = new Date();

				  $scope.minDate = new Date(
				      $scope.myDate.getFullYear(),
				      $scope.myDate.getMonth(),
				      $scope.myDate.getDate());

				  $scope.maxDate = new Date(
				      $scope.myDate.getFullYear()+2,
				      $scope.myDate.getMonth() ,
				      $scope.myDate.getDate());
				  
				//Get apartment details
					$scope.apartmentdetailsFun = function() {
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
				  // End of apartment details
					$scope.apartmentdetailsFun();
					
					
					//Change app
					$scope.changeApp = function(id) {
						
						var fromDate = $scope.fromDate;
						var toDate = $scope.toDate;
						if (fromDate == undefined) {
							alert("Please select from From Date");
							return;
						}
						if (toDate == undefined) {
							alert("Please select from To Date");
							return;
						}
						
						if (fromDate > toDate) {
							alert("From Date must be less than To Date");
							return;
						}
						
						var fDate = moment($scope.fromDate).format('MM/DD/YYYY');
						var tDate = moment($scope.toDate).format('MM/DD/YYYY');
						
						$http (
								 {
									 method :  'GET',
									 url : $PROVIDER.providerMilkRest+"/getbill?fromdate="+fDate+"&todate="+tDate+"&app_id="+id,
									 headers : {
											'Content-Type' : 'application/json'
										}
								 }).success(function(data) {
									 if (data != "") {
										 if (data.roombill.length == undefined) {
											 $scope.roombillArray = [];
											 $scope.roombillArray[0] = data.roombill;
											 $scope.roombill = $scope.roombillArray;
										 } else  {
											 $scope.roombill = data.roombill;
										 }	 
									 } else {
										 $scope.roombill = "";
										 alert("No records found please create a Apartment and continue");
									 }
								 }).error(function(data, status, headers, config) {
								 	alert("Fail to get bill details, contact administrator for support");
								 });
						};
					//End of change app
						
					$scope.changeDate = function() {
						$scope.apartmentsel.type = "0";
						$scope.roombill = [];
					};	
			}];
		}
)();