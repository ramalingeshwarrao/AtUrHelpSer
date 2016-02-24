(function(){
	
	'use strict';
	
	$PROVIDER.DailyMilkController = ['$scope', '$http', '$location', 'printitems', 'dailymilkedit',  function ($scope, $http, $location, printitems, dailymilkedit) {
		
		$scope.printshow = false;
		
		//edit daily milk data
		$scope.edit = function(obj) {
			var fDate = moment($scope.supplyDate).format('MM/DD/YYYY');
			var dailyobj = {};
			dailyobj.appsubject = obj.appsubject;
			dailyobj.routename = obj.routename;
			dailyobj.roomid = obj.roomid; 
			dailyobj.milkid = obj.milkid;
			dailyobj.quantity = obj.quantity; 
			dailyobj.cost = obj.cost;
			dailyobj.packetprimarykey = obj.ppk;
			dailyobj.flatprimarykey = obj.fpk;
			dailymilkedit.addDailyMilkOjb(dailyobj);
			dailymilkedit.addDailyMilkDate(fDate);
			$location.path( '/milkedit' );
		};
		
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
						 $scope.routedetails = "";
						 BootstrapDialog.alert("No routes found please create a route and continue");
						 $location.path( '/milkroute' );
					 }
				 }).error(function(data, status, headers, config) {
					$scope.loading = false;
					BootstrapDialog.alert("Fail to get route details, contact administrator for support");
				 });
		};
		// End of route details
		$scope.routedetails();
		
		//Get milk data
		$scope.dailymilkdata = function (rid) {
			$scope.loading = true;
			
			var fromDate = $scope.supplyDate;
			if (fromDate == undefined) {
				BootstrapDialog.alert("Please select from From Date");
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