(function(){
	
	'use stict';
	
	$PROVIDER.ProviderCloseComplaintController = ['$scope', '$http', function ($scope, $http) {
		
		
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
				$scope.logdatacount($scope.providerName, $scope.userType, $scope.userName, $scope.numPerPage, 0);
			}		
			);
		};
		
		//Get Log data count
		$scope.logdatacount = function (providerName, userType, userName, recordsPerPage, fromPage) {
			$http(
					{
					method : 'GET',
					url : $PROVIDER.providerRest+'/closelogdataadmincount?providername='+providerName+'&providerloc='+userType+'&name='+userName+'',
					headers : {
						'Content-Type' : 'application/json'
					}
					}
			).success(
			function (data) {
				if (data != "" && data != null) {
						$scope.count = data-1;
						$scope.logdata($scope.providerName, $scope.userType, $scope.userName, recordsPerPage, fromPage);
				} else {
					$scope.count = 0;
				}
			}		
			);
		};
		
		//Get Log data
		$scope.logdata = function (providerName, userType, userName, recordsPerPage, fromPage) {
			$http(
					{
					method : 'GET',
					url : $PROVIDER.providerRest+'/closelogdataadmin?providername='+providerName+'&providerloc='+userType+'&name='+userName+'&rpp='+recordsPerPage+'&fr='+fromPage+'',
					headers : {
						'Content-Type' : 'application/json'
					}
					}
			).success(
			function (data) {
				if (data != "") {
					
					if (data.help.length == undefined) {
						$scope.log = data;
					} else {
						$scope.log = data.help;					
					}
				} else {
					$scope.log = "";
				}
			}		
			);
		};
		$scope.bootstrap();
		// End of get Log data
		
		$scope.wat = function() {
		
			//Pagenation
			$scope.$watch('currentPage + numPerPage', function() {
			    var begin = (($scope.currentPage - 1) * $scope.numPerPage)
			    , end = begin + $scope.numPerPage;
			    if ($scope.userType != "" && $scope.userName != "" && $scope.providerName != "") {
			    	$scope.logdatacount($scope.providerName, $scope.userType, $scope.userName, $scope.numPerPage, begin);
			    } else {
			    	$scope.bootstrap();
			    }
			  });
			//Pagenation
		};
		$scope.wat();
		
	}];

})();