(function(){
	
	'use strict';
	
	$PROVIDER.MilkDetailsController = ['$scope', '$http', function ($scope, $http) {
		
		
		$scope.userName = "";
		$scope.userType = "";
		
		//Pagenated attributes
		$scope.currentPage = 1;
		$scope.numPerPage = 10;
		$scope.maxSize = 5;
		
		
		//Get bootstrap data
		$scope.bootstrap = function () {
			$http(
					{
					method : 'GET',
					url : $PROVIDER.providerRest+'/bootstrapdata',
					headers : {
						'Content-Type' : 'application/json'
					}
					}
			).success(
			function (data) {
				$scope.userName = data.un;
				$scope.userType = data.ut;
				$scope.providerName = data.pn;
				$scope.milkdatacount($scope.providerName, $scope.userType, $scope.userName, $scope.numPerPage, 0);
			}		
			);
		};
		
		//Get Log data count
		$scope.milkdatacount = function (providerName, userType, userName, recordsPerPage, fromPage) {
			$http(
					{
					method : 'GET',
					url : $PROVIDER.providerMilkRest+'/milkcount',
					headers : {
						'Content-Type' : 'application/json'
					}
					}
			).success(
			function (data) {
				if (data != "" && data != null) {
						$scope.count = data;
						$scope.milkdata($scope.providerName, $scope.userType, $scope.userName, recordsPerPage, fromPage);
				} else {
					$scope.count = 0;
					$scope.loading = false;
				}
			}		
			);
		};
		
		//Get Log data
		$scope.milkdata = function (providerName, userType, userName, recordsPerPage, fromPage) {
			$http(
					{
					method : 'GET',
					url : $PROVIDER.providerMilkRest+'/flatdetails?providername='+providerName+'&providerloc='+userType+'&name='+userName+'&rpp='+recordsPerPage+'&fr='+fromPage+'',
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
		$scope.bootstrap();
		// End of get Log data
		
		$scope.wat = function() {
		
			$scope.loading = true; 
			//Pagenation
			$scope.$watch('currentPage + numPerPage', function() {
			    var begin = (($scope.currentPage - 1) * $scope.numPerPage)
			    , end = begin + $scope.numPerPage;
			    if ($scope.userType != "" && $scope.userName != "" && $scope.providerName != "") {
			    	$scope.milkdatacount($scope.providerName, $scope.userType, $scope.userName, $scope.numPerPage, begin);
			    } else {
			    	$scope.bootstrap();
			    }
			  });
			//Pagenation
		};
		$scope.wat();
		
	}];

})();