(
		function() {
			
			'use strict';
			
			$PROVIDER.MilkFlatNoVsMilkController = ['$scope', '$http', '$location', function ($scope, $http, $location) {

				$scope.choices = [{id: 'choice1'}, {id: 'choice2'}];
				
				$scope.submit = function() {

					$scope.loading = true;
					$scope.flatNoId = $scope.flatNosel.type;
					$scope.milks = [];
					for (var i = 0; i < $scope.choices.length; i++) {
						$scope.milk = {
								"roomid" : $scope.flatNoId,
								"milkid" : $scope.choices[i].type,
								"quantity":$scope.choices[i].mquant
							};
						$scope.milks.push($scope.milk);
					}
					
//					$scope.flatnotdata = {
//						"roomid" : $scope.flatNoId,
//						"milkid" : $scope.choices[i].type,
//						"quantity":$scope.choices[i].mquant
//					};
					
					// create http post request
					$http(
							{
								method : 'POST',
								url : $PROVIDER.providerMilkRest+'/insert/roommilk',
								data : $scope.milks,
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
				
				//Get apartment details
				$scope.apartmentdetailsFun = function(id) {
					$http (
						 {
							 method :  'GET',
							 url : $PROVIDER.providerMilkRest+'/appartmentdetails?route_id='+id+'',
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
								 alert("No routes found please create a route and continue");
								 $location.path( '/milkroute' );
							 }
						 }).error(function(data, status, headers, config) {
						 	alert("Fail to get route details, contact administrator for support");
						 });
				};
				// End of route details
				$scope.routedetails();
				
				//Packet details
				$scope.packetdetails = function() {
					$http (
						 {
							 method :  'GET',
							 url : $PROVIDER.providerMilkRest+'/milkdetails',
							 headers : {
									'Content-Type' : 'application/json'
								}
						 }).success(function(data) {
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
								 $scope.milkdetails = "";
								 alert("No milk packets found please create a milk packets and continue");
								 $location.path( '/milkroute' );
							 }
						 }).error(function(data, status, headers, config) {
						 	alert("Fail to get route details, contact administrator for support");
						 });
				};
				//End of Packet details
				$scope.packetdetails();
				
				$scope.addNewChoice = function() {
				    var newItemNo = $scope.choices.length+1;
				    $scope.choices.push({'id':'choice'+newItemNo});
				  };
				    
			  $scope.removeChoice = function() {
				    var lastItem = $scope.choices.length-1;
				    $scope.choices.splice(lastItem);
			  	};
				
			}];
			
		}
)();