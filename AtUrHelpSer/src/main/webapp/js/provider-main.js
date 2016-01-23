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
			ProviderModule = angular.module('ProviderModule', ['ngRoute', 'ui.bootstrap', 'ngMaterial', 'ui.calendar']);
			$PROVIDER.providerModule = ProviderModule;
			
			ProviderModule.factory('printitems', function() {
				var headerItems = [];
				var bodyItems = [];
				var itemsService = {};
				
				itemsService.addHeaderItems = function(items) {
					headerItems = items;
				};
				
				itemsService.addBodyItems = function(items) {
					bodyItems = items;
				};
				
				itemsService.getHeaderItems = function() {
					return headerItems;
				};
				
				itemsService.getBodyItems = function() {
					return bodyItems;
				};
				
				return itemsService;
			});
			
			
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
				}).when('/dailymilk', {
					templateUrl : 'milk-dailymilk.html',
					controller : $PROVIDER.DailyMilkController
				}).when('/milkprint', {
					templateUrl : 'milk-print.html',
					controller : $PROVIDER.MilkPrintController
				}).when('/milkrequired', {
					templateUrl : 'milk-required.html',
					controller : $PROVIDER.MilkRequiredController
				}).when('/milkroomstatus', {
					templateUrl : 'milk-room-status.html',
					controller : $PROVIDER.MilkRoomStatusController
				}).when('/milkbill', {
					templateUrl : 'milk-bill.html',
					controller : $PROVIDER.MilkBillController
				}).when('/milkbalancesheet', {
					templateUrl : 'milk-balancesheet.html',
					controller : $PROVIDER.MilkBillController
				})
				.otherwise({
					redirectTo : '/provider'
				});
				
			}]);
		}
)();