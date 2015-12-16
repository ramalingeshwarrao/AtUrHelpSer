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
			$PROVIDER.providerMilkRest = "/AtUrHelpSer/aturhelp/milk";
			ProviderModule = angular.module('ProviderModule', ['ngRoute', 'ui.bootstrap','ngMaterial']);
			
			/*
			//Date picker config
			ProviderModule.config(function($mdDateLocaleProvider) {
			    $mdDateLocaleProvider.formatDate = function(date) {
			       return moment(date).format('YYYY-MM-DD');
			    };
			});
			*/
			
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
				}).when('/provider', {
					templateUrl : 'provider.html',
					controller : $PROVIDER.ProviderController
				}).when('/milkroute', {
					templateUrl : 'milk-route.html',
					controller : $PROVIDER.MilkRouteController
				}).when('/milklocation', {
					templateUrl : 'milk-location.html',
					controller : $PROVIDER.MilkLocationController
				}).when('/milkappartment', {
					templateUrl : 'milk-appartment.html',
					controller : $PROVIDER.MilkAppartmentController
				}).when('/milkpacket', {
					templateUrl : 'milk-packets.html',
					controller : $PROVIDER.MilkPacketController
				}).when('/milkflatno', {
					templateUrl : 'milk-flatno.html',
					controller : $PROVIDER.MilkFlatNoController
				}).when('/milkflatnovsmilk', {
					templateUrl : 'milk-flatnoVSMilk.html',
					controller : $PROVIDER.MilkFlatNoVsMilkController
				}).when('/milkdetails', {
					templateUrl : 'milk-details.html',
					controller : $PROVIDER.MilkDetailsController
				}).when('/nomilk', {
					templateUrl : 'no-milk.html',
					controller : $PROVIDER.NoMilkController
				})
				.otherwise({
					redirectTo : '/provider'
				});
				
			}]);
		}
)();