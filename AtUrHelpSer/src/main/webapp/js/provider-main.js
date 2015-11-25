(
		function () {
			
			'use strict';
			
			//Define global variable
			var $PROVIDER = window.$PROVIDER, providerRest;
			
			if (! $PROVIDER || typeof $PROVIDER != 'object') {
				$PROVIDER = window.$PROVIDER = {};
			}
			
			var ProviderModule ;
			
			$PROVIDER.providerRest = "/aturhelp/hr";
			ProviderModule = angular.module('ProviderModule', ['ngRoute']);
			
			//Add config
			ProviderModule.config(['$routeProvider', function($routeProvider) {
				
				$routeProvider.when('/frame', {
					templateUrl : 'provider-frame.html',
					controller : $PROVIDER.ProviderFrameController
				}).when('/providermenu', {
					templateUrl : 'provider-menu.html',
					controller : $PROVIDER.ProviderMenuController
				}).when('/providerlist', {
					templateUrl : 'provider-list.html',
					controller : $PROVIDER.ProviderListController
				}).otherwise({
					redirectTo : '/providerlist'
				});
				
			}]);
		}
)();