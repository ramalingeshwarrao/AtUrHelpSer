(
		function() {
			
			'use strict';
			
			$PROVIDER.MilkCommentController = ['$scope', '$http', '$location', function ($scope, $http, $location) {

				//Submit fun
				$scope.submit = function() {
					var rid = $scope.flatNosel.type;
					var comment = $scope.comment;
					
					$scope.loading = true;
					$http (
						 {
							 method :  'GET',
							 url : $PROVIDER.providerMilkRest+'/updatecomment?roomid='+rid+'&comment='+comment,
							 headers : {
									'Content-Type' : 'application/json'
								}
						 }).success(function(data) {
							 if (data.status == "0") {
								 BootstrapDialog.alert("Successfuly Inserted");
							 } else {
								 BootstrapDialog.alert("Failed");
							 }
							 $scope.loading = false;
						 }).error(function(data, status, headers, config) {
							 $scope.loading = false;
							 BootstrapDialog.alert("Fail to get data, contact administrator for support");
						 });
					
				}
				//End of submit fun
				
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
				$scope.changeFlat = function(id) {
					$scope.getcomments(id);
				};
				//Change flat
				//End of change flat
				
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
				
				
				//Get comment details
				$scope.getcomments = function(id) {
					$scope.loading = true;
					$http (
						 {
							 method :  'GET',
							 url : $PROVIDER.providerMilkRest+'/comment?roomid='+id,
							 headers : {
									'Content-Type' : 'application/json'
								}
						 }).success(function(data) {
							 if (data != "") {
								 $scope.comment = data.comment.name;
								 
							 }
							 $scope.loading = false;
						 }).error(function(data, status, headers, config) {
							 $scope.loading = false;
							 BootstrapDialog.alert("Fail to get data, contact administrator for support");
						 });
				};
				// End of comment details
				
				
			}];
			
		}
)();