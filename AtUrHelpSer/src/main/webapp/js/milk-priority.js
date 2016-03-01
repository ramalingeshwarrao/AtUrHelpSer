(
		function() {
			
			'use strict';
			
			$PROVIDER.MilkPriorityController = ['$scope', '$http', '$location', function ($scope, $http, $location) {
				
				$scope.submit = function() {
					$scope.prioritylist = {"plist":$scope.priorityarray};
					$scope.loading = true;
					$http (
							 {
								 method :  'POST',
								 url : $PROVIDER.providerMilkRest+'/updatepriority',
								 data : $scope.prioritylist,
								 headers : {
										'Content-Type' : 'application/json'
									}
							 }).success(function(data) {
								 $scope.loading = false;
								 if (data == "0") {
									 BootstrapDialog.alert("Succesfuly Update");
								 } else {
									 BootstrapDialog.alert("Fail to update record, kindly try once");
								 }
							 }).error(function(data, status, headers, config) {
								$scope.loading = false;
								BootstrapDialog.alert("Fail to get route details, contact administrator for support");
							 });
					
				
					
				};
				
				$scope.routeChange = function(rid) {
				
					$scope.loading = true;
					$http (
							 {
								 method :  'GET',
								 url : $PROVIDER.providerMilkRest+'/priority?routeid='+rid,
								 headers : {
										'Content-Type' : 'application/json'
									}
							 }).success(function(data) {
								 $scope.loading = false;
								 if (data != null || data != "") {
									 $scope.priority = data.priority;
									 $scope.priorityarray = [];
									 for (var i=0 ; i < $scope.priority.length; i ++ ) {
										 $scope.priorityarray.push({
											 id : 'psheet'+i,
											 appname : $scope.priority[i].appname,
											 pnumber : $scope.priority[i].pnumber,
											 appid : $scope.priority[i].appid
										 });
									}
								 }
							 }).error(function(data, status, headers, config) {
								$scope.loading = false;
								BootstrapDialog.alert("Fail to get route details, contact administrator for support");
							 });
					
				};
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
			}];
		}
)();