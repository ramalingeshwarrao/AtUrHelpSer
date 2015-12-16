(
		function() {
			
			'use strict';
			
			$PROVIDER.NoMilkController = ['$scope', '$http', function ($scope, $http) {
				
				$scope.myDate = new Date();

				  $scope.minDate = new Date(
				      $scope.myDate.getFullYear(),
				      $scope.myDate.getMonth(),
				      $scope.myDate.getDate());

				  $scope.maxDate = new Date(
				      $scope.myDate.getFullYear()+2,
				      $scope.myDate.getMonth() ,
				      $scope.myDate.getDate());
				  
				  //Submit function
				  $scope.submit = function() {
					  var fDate = moment($scope.fromDate).format('YYYY-MM-DD')
					alert(fDate);
					alert($scope.toDate)
				  };
				  //End of submit function
				  
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
					
					
					//Get flatno details
					$scope.flatnodetailsFun = function(id) {
						$http (
							 {
								 method :  'GET',
								 url : $PROVIDER.providerMilkRest+'/getflatnodetails?app_id='+id+'',
								 headers : {
										'Content-Type' : 'application/json'
									}
							 }).success(function(data) {
								 if (data != "") {
									 if (data.flat.length == undefined) {
										 $scope.flatNodetailsArray = [];
										 $scope.flatNodetailsArray[0] = data.flat;
										 $scope.flatNodetails = $scope.flatNodetailsArray;
										 $scope.flatNosel = {type : $scope.flatNodetailsArray[0].id};
									 } else  {
										 $scope.flatNodetails = data.flat;
										 $scope.flatNosel = {type : $scope.flatNodetails[0].id};
									 }	 
								 } else {
									 $scope.apartmentdetails = "";
									 alert("No Flats found please create a Flat and continue");
									 $location.path( '/milkflatno' );
								 }
							 }).error(function(data, status, headers, config) {
							 	alert("Fail to get route details, contact administrator for support");
							 });
					};
					// End of flat details
					
					//Change app
					$scope.changeApp = function(id) {
						$scope.flatnodetailsFun(id);
					};
					//End of change app
			}];
		}
)();