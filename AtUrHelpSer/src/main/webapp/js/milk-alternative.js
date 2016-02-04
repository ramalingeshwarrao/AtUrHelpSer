(
		function() {
			
			'use strict';
			
			$PROVIDER.MilkAlternativeController = ['$scope', '$http', '$location', function ($scope, $http, $location) {

				$scope.submit = function() {
					$scope.loading = true;
					var flatid = $scope.flatNosel.type;
					var milkid = $scope.milksel.type;
					var alterCount = $scope.alternativecount;
					
					$http (
							 {
								 method :  'GET',
								 url : $PROVIDER.providerMilkRest+'/updatealternative?roomid='+flatid+'&milkid='+milkid+'&altercount='+alterCount,
								 headers : {
										'Content-Type' : 'application/json'
									}
							 }).success(function(data) {
								 $scope.loading = false;
									 if (data == 0) {
										 BootstrapDialog.alert("Sucessfully Inserted");	 
									 } else {
										 BootstrapDialog.alert("Failed to update");
									 }
							 }).error(function(data, status, headers, config) {
								 $scope.loading = false;
								 BootstrapDialog.alert("Fail to update alternative record, contact administrator for support");
							 });
					
				};
				
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
								 BootstrapDialog.alert("No Flats found please create a Flat and continue");
								 $location.path( '/milkflatno' );
							 }
						 }).error(function(data, status, headers, config) {
							 BootstrapDialog.alert("Fail to get route details, contact administrator for support");
						 });
				};
				// End of flat details
				
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
							 if (data != "") {
								 $scope.loading = false;
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
				
				//Change app
				$scope.changeApp = function(id) {
					$scope.flatnodetailsFun(id);
				};
				//End of change app
				
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
				
				//Change Milk
				$scope.changeMilk = function(milkId) {
					
					$scope.loading = true;
					var flatid = $scope.flatNosel.type;
					
					$http (
							 {
								 method :  'GET',
								 url : $PROVIDER.providerMilkRest+'/milkquantity?roomid='+flatid+'&milkid='+milkId,
								 headers : {
										'Content-Type' : 'application/json'
									}
							 }).success(function(data) {
								 $scope.loading = false;
								 $scope.actualcount = data;
							 }).error(function(data, status, headers, config) {
								 BootstrapDialog.alert("Fail to get milk details by room id and milkid, contact administrator for support");
							 });
					//Get packets for this flatid
					
				};
				//End of Change milk
				
				//Get packet details
				$scope.changeFlat = function(flatid) {
					
					$scope.loading = true;
					
					$http (
							 {
								 method :  'GET',
								 url : $PROVIDER.providerMilkRest+'/milkdetailsbyid?roomid='+flatid,
								 headers : {
										'Content-Type' : 'application/json'
									}
							 }).success(function(data) {
								 $scope.loading = false;
								 if (data != "") {
									 if (data.milk.length == undefined) {
										 $scope.milkdetailsArray = [];
										 $scope.milkdetailsArray[0] = data.milk;
										 $scope.milkdetails = $scope.milkdetailsArray;
										 $scope.milksel = {type : $scope.milkdetailsArray[0].id};
									 } else  {
										 $scope.milkdetails = data.milk;
										 $scope.milksel = {type : $scope.milkdetails[0].id};
									 }	 
								 } else {
									 $scope.loading = false;
									 $scope.milkdetails = "";
								 }
							 }).error(function(data, status, headers, config) {
								 BootstrapDialog.alert("Fail to get milk details by room id, contact administrator for support");
							 });
					//Get packets for this flatid
					
				};
				//End of packet details
				
				
			}];
			
		}
)();