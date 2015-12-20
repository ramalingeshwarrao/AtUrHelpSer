(
		function() {
			
			'use strict';
			
			$PROVIDER.MilkPrintController = ['$scope', '$http', '$location', 'printitems', function ($scope, $http, $location, printitems) {
				
				$scope.headerlist = printitems.getHeaderItems();
				$scope.bodylist = printitems.getBodyItems();
				$scope.print = function() {
					window.print();
				};
				
			}];
			
		}
)();