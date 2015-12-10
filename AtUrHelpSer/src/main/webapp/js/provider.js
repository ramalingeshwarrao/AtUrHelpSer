(function(){
	
	'use stict';
	
	$PROVIDER.ProviderController = ['$scope', '$http', '$location', function ($scope, $http, $location) {
		
		$scope.gotoLoc = function(path) {
			
			$location.path( path );
			
		};
		
	}];

})();