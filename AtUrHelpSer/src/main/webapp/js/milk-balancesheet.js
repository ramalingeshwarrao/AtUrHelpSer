(
		function() {
			
			'use strict';
			
			$PROVIDER.MilkBalanceSheetController = ['$scope', '$http', '$location', function ($scope, $http, $location) {

				function roundToTwo(num) {    
				    return +(Math.round(num + "e+2")  + "e-2");
				}
				
				$scope.submit = function() {
					
					var amulLts = $scope.AMUL_LTS_TD;
					var heritageLts = $scope.HERITAGE_LTS_TD;
					var jerseyLts = $scope.JERSEY_LTS_TD;
					var nandaniLts = $scope.NADANI_LTS_TD;
					var sangamLts = $scope.SANGAM_LTS_TD;
					var tirmulaLts = $scope.TIRUMALA_LTS_TD;
					var vijayaLts = $scope.VIJAYA_LTS_TD;
					
					if (amulLts != "") {
						var diffAmul = amulLts - $scope.AMUL_LTS;
						$scope.AMUL_DIFF = roundToTwo(diffAmul);
					}
					if (heritageLts != "") {
						var diffHeritage = heritageLts - $scope.HERITAGE_LTS;
						$scope.HERITAGE_DIFF = roundToTwo(diffHeritage);
				    }
					if (jerseyLts != "") {
						var diffJersey = jerseyLts - $scope.JERSEY_LTS;
						$scope.JERSEY_DIFF = roundToTwo(diffJersey);
				    }
					if (nandaniLts != "") {
						var diffNandani = nandaniLts - $scope.NADANI_LTS;
						$scope.NADANI_DIFF = roundToTwo(diffNandani);
				    }
					if (sangamLts != "") {
						var diffSangam = sangamLts - $scope.SANGAM_LTS;
						$scope.SANGAM_DIFF = roundToTwo(diffSangam);
				    }
					if (tirmulaLts != "") {
						var diffTirmula = tirmulaLts - $scope.TIRUMALA_LTS;
						$scope.TIRUMALA_DIFF = roundToTwo(diffTirmula);
				    }
					if (vijayaLts != "") {
						var diffVijaya = vijayaLts - $scope.VIJAYA_LTS;
						$scope.VIJAYA_DIFF = roundToTwo(diffVijaya);
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
									 $scope.AMUL = $scope.balancesheet[0].category;
									 $scope.AMUL_LTS = $scope.balancesheet[0].liters;
									 $scope.HERITAGE = $scope.balancesheet[1].category;
									 $scope.HERITAGE_LTS = $scope.balancesheet[1].liters;
									 $scope.JERSEY = $scope.balancesheet[2].category;
									 $scope.JERSEY_LTS = $scope.balancesheet[2].liters;
									 $scope.NADANI = $scope.balancesheet[3].category;
									 $scope.NADANI_LTS = $scope.balancesheet[3].liters;
									 $scope.SANGAM = $scope.balancesheet[4].category;
									 $scope.SANGAM_LTS = $scope.balancesheet[4].liters;
									 $scope.TIRUMALA = $scope.balancesheet[5].category;
									 $scope.TIRUMALA_LTS = $scope.balancesheet[5].liters;
									 $scope.VIJAYA = $scope.balancesheet[6].category;
									 $scope.VIJAYA_LTS = $scope.balancesheet[6].liters;
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
				$scope.dateChange = function() {
					$scope.balancesheetdetails();					
				};
			}];
		}
)();