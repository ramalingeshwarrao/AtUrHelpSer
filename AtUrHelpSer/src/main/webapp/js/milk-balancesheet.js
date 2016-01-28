(
		function() {
			
			'use strict';
			
			$PROVIDER.MilkBalanceSheetController = ['$scope', '$http', '$location', function ($scope, $http, $location) {

				function roundToTwo(num) {    
				    return +(Math.round(num + "e+2")  + "e-2");
				}
				
				$scope.submit = function() {
					
					for (var i=0; i< $scope.balancesheetarray.length; i ++ ) {
						var expectedliters = $scope.balancesheetarray[i].expectedliters;
						var todayLts = $scope.balancesheetarray[i].todaylts;
						var diffLts = expectedliters - todayLts;
						$scope.balancesheetarray[i].diff = roundToTwo(diffLts);
					}
				};
				
				
				//Get balancesheet details
				$scope.balancesheetdetails = function() {
					$scope.loading = true;
					
					
					var fromDate = $scope.supplyDate;
					if (fromDate == undefined) {
						BootstrapDialog.alert("Please select from From Date");
						return;
					}
					var fDate = moment($scope.supplyDate).format('MM/DD/YYYY');
					
					$http (
						 {
							 method :  'GET',
							 url : $PROVIDER.providerMilkRest+'/balancesheetdetails?date='+fDate,
							 headers : {
									'Content-Type' : 'application/json'
								}
						 }).success(function(data) {
							 $scope.loading = false;
							 if (data != "") {
									 $scope.balancesheet = data.balancesheet;
									 $scope.balancesheetarray = [];
									 for (var i = 0 ; i < $scope.balancesheet.length; i++ ) {
										$scope.balancesheetarray.push({
											id: 'sheet'+i,
											category : $scope.balancesheet[i].category,
											expectedliters : $scope.balancesheet[i].liters,
										}); 
									 }
									 var test = "hi";
							 } else {
								 $scope.balancesheet = "";
								 BootstrapDialog.alert("No data found");
							 }
						 }).error(function(data, status, headers, config) {
							$scope.loading = false;
							BootstrapDialog.alert("Something went wrong please contact administrator");
						 });
				};
				// End of balancesheet details
				
				//Get balancesheet details by route id
				$scope.balancesheetroutedetails = function() {
					$scope.loading = true;
					
					var fromDate = $scope.supplyDate;
					if (fromDate == undefined) {
						BootstrapDialog.alert("Please select from From Date");
						return;
					}
					var fDate = moment($scope.supplyDate).format('MM/DD/YYYY');
					
					$http (
						 {
							 method :  'GET',
							 url : $PROVIDER.providerMilkRest+'/balancesheetdetailsbyroute?date='+fDate,
							 headers : {
									'Content-Type' : 'application/json'
								}
						 }).success(function(data) {
							 $scope.loading = false;
							 if (data != "") {
									 $scope.balancesheetbyroute = data.balancesheet;
							 } else {
								 $scope.balancesheetbyroute = "";
								 BootstrapDialog.alert("No data found");
							 }
						 }).error(function(data, status, headers, config) {
							$scope.loading = false;
							BootstrapDialog.alert("Something went wrong please contact administrator");
						 });
				};
				// End of balancesheet details by route id
				
				$scope.dateChange = function() {
					$scope.balancesheetdetails();			
					$scope.balancesheetroutedetails();
				};
			}];
		}
)();