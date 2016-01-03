(function(){
	
	'use strict';
	
	$PROVIDER.DailyMilkController = ['$scope', '$http', '$location', 'printitems',  function ($scope, $http, $location, printitems) {
		
		$scope.printshow = false;
		//print
		$scope.print = function() {
			var headerItems = [];
			headerItems.push('Route');
			headerItems.push('Apartment Name');
			headerItems.push('Flat No');
			headerItems.push('Milk');
			headerItems.push('Quantity');
			printitems.addHeaderItems(headerItems);
			printitems.addBodyItems($scope.getflatdata);
			
			$location.path( '/milkprint' );
		};
		//end of print
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
		
		//Get milk data
		$scope.dailymilkdata = function (rid) {
			
			var fromDate = $scope.supplyDate;
			if (fromDate == undefined) {
				alert("Please select from From Date");
				return;
			}
			var fDate = moment($scope.supplyDate).format('MM/DD/YYYY');
			
			$http(
					{
					method : 'GET',
					url : $PROVIDER.providerMilkRest+'/dailymilkdetails?route_id='+rid+'&date='+fDate,
					headers : {
						'Content-Type' : 'application/json'
					}
					}
			).success(
			function (data) {
				if (data != "") {
					
					if (data.getflatdata.length == undefined) {
						$scope.getflatdata = data;
					} else {
						$scope.getflatdata = data.getflatdata;					
					}
					$scope.loading = false;
				} else {
					$scope.getflatdata = "";
					$scope.loading = false;
				}
			}		
			);
		};
		// End of get milk data
		
		$scope.changeDate = function() {
			 $scope.routesel.type = "0";
			 $scope.getflatdata = "";
		};
		
	}];

})();