(function(){
	
	'use stict';
	
	$PROVIDER.ProviderListController = ['$scope', '$http', function ($scope, $http) {
		
		
		$scope.userName = "";
		$scope.userType = "";
		
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
					alert("succesfuly Updated");
					//refresh page
					$scope.logdata($scope.providerName, $scope.userType, $scope.userName);
					 $scope.loading = true;
				} else {
					alert("fail to update record");
					$scope.loading = true;
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
				$scope.logdata($scope.providerName, $scope.userType, $scope.userName);
			}		
			);
		};
		$scope.bootstrap();
		
		//Get Log data
		$scope.logdata = function (providerName, userType, userName) {
			$http(
					{
					method : 'GET',
					url : $PROVIDER.providerRest+'/logdataadmin?providername='+providerName+'&providerloc='+userType+'&name='+userName+'',
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
		
	}];

})();