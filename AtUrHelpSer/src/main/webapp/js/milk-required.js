(
		function() {
			
			'use strict';
			
			$PROVIDER.MilkRequiredController = ['$scope', '$http', function ($scope, $http) {
				
				  
				  //Submit function
				  $scope.submit = function() {

						$scope.loading = true;
						var toDate = $scope.toDate;
						if (toDate == undefined) {
							alert("Please select from To Date");
							return;
						}
						toDate = moment(toDate).format('MM/DD/YYYY');
						
						$scope.nomilk = {
							"todate" : toDate,
							"rid" : $scope.flatNosel.type
						};
						
						// create http post request
						$http(
								{
									method : 'POST',
									url : $PROVIDER.providerMilkRest+'/updatenomilktogetmilk',
									data : $scope.nomilk,
									headers : {
										'Content-Type' : 'application/json'
									}
								}).success(function(data) {
									$scope.loading = false;
							if (data.status == 0) {
								alert("succesfuly Update");
								 $scope.isShow = false;
								 $scope.apartmentsel.type = "0";
								 $scope.flatNosel.type = "0";
								 $scope.toDate = "";
								
							} else {
								alert("fail to update record");
							}
						});

					};
				  //End of submit function
				  
				//Get apartment details
					$scope.apartmentdetailsFun = function() {
						$scope.loading = true;
						$http (
							 {
								 method :  'GET',
								 url : $PROVIDER.providerMilkRest+'/appartmentdetails',
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
									 $scope.apartmentdetails = "";
									 alert("No Apartments found please create a Apartment and continue");
									 $location.path( '/milkappartment' );
								 }
							 }).error(function(data, status, headers, config) {
								$scope.loading = false;
							 	alert("Fail to get route details, contact administrator for support");
							 });
					};
				  // End of apartment details
					$scope.apartmentdetailsFun();
					
					
					//Get flatno details
					$scope.flatnodetailsFun = function(id) {
						$scope.loading = true;
						$http (
							 {
								 method :  'GET',
								 url : $PROVIDER.providerMilkRest+'/getflatnodetails?app_id='+id+'',
								 headers : {
										'Content-Type' : 'application/json'
									}
							 }).success(function(data) {
								 $scope.loading = false;
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
								$scope.loading = false;
							 	alert("Fail to get route details, contact administrator for support");
							 });
					};
					// End of flat details
					
					//Change app
					$scope.changeApp = function(id) {
						$scope.flatnodetailsFun(id);
					};
					//End of change app
					
					//Change room get all the details of required room
					$scope.changeroom = function(id) {
						
						var appId = $scope.apartmentsel.type;
						var roomId = id;

						$http (
							 {
								 method :  'GET',
								 url : $PROVIDER.providerMilkRest+'/getnomilkdetails?room_id='+roomId+'&app_id='+appId,
								 headers : {
										'Content-Type' : 'application/json'
									}
							 }).success(function(data) {
								 if (data != "") {
									 $scope.isShow = true;
									 $scope.appName = data.appname;
									 $scope.roomId = data.roomid;
									 $scope.date = data.date;
								 } else {
									 $scope.isShow = false;
									 alert("No Record Found");
									 $scope.apartmentsel.type = "0";
									 $scope.flatNosel.type = "0";
									 
								 }
							 }).error(function(data, status, headers, config) {
							 	alert("Fail to get route details, contact administrator for support");
							 });
					
						
					};
					//End of change room
			}];
		}
)();