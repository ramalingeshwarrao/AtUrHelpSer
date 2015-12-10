(
		function () {
			
			'use strict';
			
			//Define global variable
			var $PROVIDER = window.$PROVIDER, providerRest;
			
			if (! $PROVIDER || typeof $PROVIDER != 'object') {
				$PROVIDER = window.$PROVIDER = {};
			}
			
			var ProviderModule ;
			
			$PROVIDER.providerRest = "/AtUrHelpSer/aturhelp/hr";
			ProviderModule = angular.module('ProviderModule', ['ngRoute', 'ui.bootstrap']);
			
			//Add config
			ProviderModule.config(['$routeProvider', function($routeProvider) {
				
				$routeProvider.when('/frame', {
					templateUrl : 'provider-frame.html',
					controller : $PROVIDER.ProviderFrameController
				}).when('/providermenu', {
					templateUrl : 'provider-menu.html',
					controller : $PROVIDER.ProviderMenuController
				}).when('/provideropencomplaints', {
					templateUrl : 'provider-opencomplaints.html',
					controller : $PROVIDER.ProviderOpenComplaintController
				}).when('/providerclosecomplaints', {
					templateUrl : 'provider-closecomplaints.html',
					controller : $PROVIDER.ProviderCloseComplaintController
				}).when('/providerprofile', {
					templateUrl : 'provider-profile.html',
					controller : $PROVIDER.ProviderProfileController
				}).otherwise({
					redirectTo : '/provideropencomplaints'
				});
				
			}]);
		}
)();