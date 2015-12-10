(function(){
	
	'use stict';
	
	$PROVIDER.ProviderProfileController = ['$scope', '$http', function ($scope, $http) {
		
		
		//Get admin profile data
		$scope.profile = function () {
			$http(
					{
					method : 'GET',
					url : $PROVIDER.providerRest+'/adminprofile',
					headers : {
						'Content-Type' : 'application/json'
					}
					}
			).success(
			function (data) {
				$scope.gender = data.gender;
				$scope.department = data.department;
				$scope.place = data.place;
				$scope.email = data.email;
				$scope.mobileno = data.mobileno;
				$scope.name = data.name;
			}		
			);
		};
		$scope.profile();
		
	}];

})();