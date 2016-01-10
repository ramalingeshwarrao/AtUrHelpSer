(function(){
	
	'use strict';
	
	$PROVIDER.ProviderOpenComplaintController = ['$scope', '$http', function ($scope, $http) {
		
		
		$scope.userName = "";
		$scope.userType = "";
		
		//Pagenated attributes
		$scope.currentPage = 1;
		$scope.numPerPage = 10;
		$scope.maxSize = 5;
		
		$scope.editUser = function(ticketid) {
			$scope.loading = true;
			$scope.updateObj = {"ticketno":ticketid};
			//update on server
			$http (
				 {
					method : 'POST',
					url : $PROVIDER.providerRest+'/updateticketid',
					data : $scope.updateObj,
					headers : {
						'Content-Type' : 'application/json'
					}
				 }		
			).success(function(data) {
				if (data.status == 0) {
					BootstrapDialog.alert("succesfuly Updated");
					//refresh page
					//$scope.logdatacount($scope.providerName, $scope.userType, $scope.userName);
					$scope.wat();
				} else {
					$scope.loading = false;
					BootstrapDialog.alert("fail to update record");
				}
			});
		};
		
		
		
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
					url : $PROVIDER.providerRest+'/logdataadmincount?providername='+providerName+'&providerloc='+userType+'&name='+userName+'',
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
					$scope.loading = false;
				}
			}		
			);
		};
		
		//Get Log data
		$scope.logdata = function (providerName, userType, userName, recordsPerPage, fromPage) {
			$http(
					{
					method : 'GET',
					url : $PROVIDER.providerRest+'/logdataadmin?providername='+providerName+'&providerloc='+userType+'&name='+userName+'&rpp='+recordsPerPage+'&fr='+fromPage+'',
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
					$scope.loading = false;
				} else {
					$scope.log = "";
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