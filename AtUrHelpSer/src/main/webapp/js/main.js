(
	function () {
	
		'use strict';
		
		//Define global variable
		var $ATURHELP = window.$ATURHELP;
		
		if (! $ATURHELP || typeof $ATURHELP != 'object') {
			$ATURHELP = window.$ATURHELP = {};
		}
		
		var MainModule;
		
		MainModule = angular.module('MainModule', ['ngRoute']);
		
		//Add config
		MainModule.config(['$routeProvider', function($routeProvider) {
			
			$routeProvider.when('/aturhelp', {
				templateUrl : 'aturhelp.html',
				controller : $ATURHELP.AturhelpController
			}).when('/about', {
				templateUrl : 'about.html',
				controller : $ATURHELP.AboutController
			}).when('/blog', {
				templateUrl : 'blog.html',
				controller : $ATURHELP.BlogController
			}).when('/projects', {
				templateUrl : 'projects.html',
				controller : $ATURHELP.ProjectsController
			}).when('/contact', {
				templateUrl : 'contact.html',
				controller : $ATURHELP.ContactController
			}).when('/register', {
				templateUrl : 'register.html',
				controller : $ATURHELP.RegisterController
			}).otherwise({
				redirectTo : '/aturhelp'
			});
			
		}]);
		
	}	
)();