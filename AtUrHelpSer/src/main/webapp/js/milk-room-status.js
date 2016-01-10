(
		function() {
			
			'use strict';
			
			$PROVIDER.MilkRoomStatusController = ['$scope', '$http', '$location', '$compile', 'uiCalendarConfig', function ($scope, $http, $location, $compile, uiCalendarConfig) {
				
				
					  
					  
				//Get apartment details
				$scope.apartmentdetailsFun = function() {
					$scope.loading = true;
					$http (
						 {
							 method :  'GET',
							 url : $PROVIDER.providerMilkRest+'/appartmentdetails',
							 headers : {
									'Content-Type' : 'application/json'
								}
						 }).success(function(data) {
							 $scope.loading = false;
							 if (data != "") {
								 if (data.appartment.length == undefined) {
									 $scope.apartmentdetailsArray = [];
									 $scope.apartmentdetailsArray[0] = data.appartment;
									 $scope.apartmentdetails = $scope.apartmentdetailsArray;
									 $scope.apartmentsel = {type : $scope.apartmentdetailsArray[0].id};
								 } else  {
									 $scope.apartmentdetails = data.appartment;
									 $scope.apartmentsel = {type : $scope.apartmentdetails[0].id};
								 }	 
							 } else {
								 $scope.loading = false;
								 $scope.apartmentdetails = "";
								 alert("No Apartments found please create a Apartment and continue");
								 $location.path( '/milkappartment' );
							 }
						 }).error(function(data, status, headers, config) {
						 	alert("Fail to get route details, contact administrator for support");
						 });
				};
			  // End of apartment details
				$scope.apartmentdetailsFun();
				
				//Get flatno details
				$scope.flatnodetailsFun = function(id) {
					$scope.loading = true;
					$http (
						 {
							 method :  'GET',
							 url : $PROVIDER.providerMilkRest+'/getflatnodetails?app_id='+id+'',
							 headers : {
									'Content-Type' : 'application/json'
								}
						 }).success(function(data) {
							 $scope.loading = false;
							 if (data != "") {
								 if (data.flat.length == undefined) {
									 $scope.flatNodetailsArray = [];
									 $scope.flatNodetailsArray[0] = data.flat;
									 $scope.flatNodetails = $scope.flatNodetailsArray;
									 $scope.flatNosel = {type : $scope.flatNodetailsArray[0].id};
								 } else  {
									 $scope.flatNodetails = data.flat;
									 $scope.flatNosel = {type : $scope.flatNodetails[0].id};
								 }	 
							 } else {
								 $scope.apartmentdetails = "";
								 alert("No Flats found please create a Flat and continue");
								 $location.path( '/milkflatno' );
							 }
						 }).error(function(data, status, headers, config) {
							 $scope.loading = false;
						 	alert("Fail to get route details, contact administrator for support");
						 });
				};
				// End of flat details
				
				//Change app
				$scope.changeApp = function(id) {
					$scope.flatnodetailsFun(id);
				};
				//End of change app
				
				//Change room get all the details of required room
				$scope.changeroom = function(id) {
					
					var appId = $scope.apartmentsel.type;
					var roomId = id;

					$http (
						 {
							 method :  'GET',
							 url : $PROVIDER.providerMilkRest+'/getallnomilkdetails?room_id='+roomId+'&app_id='+appId,
							 headers : {
									'Content-Type' : 'application/json'
								}
						 }).success(function(data) {
								if (data != "") {
									var eventObj = [];
									if (data.getflatdata.length == undefined) {
										eventObj[0] = data.getflatdata;
										
									} else {
										var eventObj = data.getflatdata;
									}
									$scope.calEvent(eventObj);
								} else {
									$scope.getflatdata = "";
									$scope.events.length = 0;
								}
							}).error(function(data, status, headers, config) {
						 	alert("Fail to get route details, contact administrator for support");
						 });
				};
				//End of change room
				
				$scope.calEvent = function(eventObj) {
					$scope.events.length = 0;
					for (var i=0 ; i < eventObj.length ; i++) {
						var fromDateObj =  moment(eventObj[i].date, "YYYY-MM-DD").toDate();
						var toDateObj =  "";
						if (eventObj[i].todate == undefined) {
							toDateObj = new Date(fromDateObj.getFullYear()+2, fromDateObj.getMonth(), fromDateObj.getDate()+1);  
						} else {
							toDateObj =  moment(eventObj[i].todate, "YYYY-MM-DD").toDate();
							toDateObj = new Date(toDateObj.getFullYear(), toDateObj.getMonth(), toDateObj.getDate()+1);
						}
						$scope.events.push({
							title: 'No Milk',
							start: fromDateObj,
							end: toDateObj
						});
					}
				};
				
				
				
				
				var date = new Date();
			    var d = date.getDate();
			    var m = date.getMonth();
			    var y = date.getFullYear();
			    var currentView = "month";
			     
			     
			    //event source that pulls from google.com
//			    $scope.eventSource = {
//			            url: "http://www.google.com/calendar/feeds/usa__en%40holiday.calendar.google.com/public/basic",
//			            className: 'gcal-event',           // an option!
//			            currentTimezone: 'America/Chicago' // an option!
//			    };
			     
			     
			    //This will call onLoad and you can assign the values the way you want to the calendar
			    //here DataRetriever.jsp will give me array of JSON data generated from the database data
//			    $http.get('DataRetriever.jsp').success(function(data) {
//			        for(var i = 0; i < data.length; i++)
//			        {
//			            $scope.events[i] = {id:data[i].id, title: data[i].task,start: new Date(data[i].start), end: new Date(data[i].end),allDay: false};
//			        }
//			    });
			     
			    
			    //to explicitly add events to the calendar
			    //you can add the events in following ways
			    $scope.events = [
			     
			    ];
			    //we don't need it right now
			     
			     
			    //with this you can handle the events that generated by clicking the day(empty spot) in the calendar
			    $scope.dayClick = function( date, allDay, jsEvent, view ){
			    	alert("click on dayClick1");
			          $scope.alertMessage = ('Day Clicked ' + date);
			    };
			     
			     
			    //with this you can handle the events that generated by droping any event to different position in the calendar
			     $scope.alertOnDrop = function(event, dayDelta, minuteDelta, allDay, revertFunc, jsEvent, ui, view){
			          $scope.alertMessage = ('Event Droped to make dayDelta ' + dayDelta);
			    };
			     
			     
			    //with this you can handle the events that generated by resizing any event to different position in the calendar
			    $scope.alertOnResize = function(event, dayDelta, minuteDelta, revertFunc, jsEvent, ui, view ){
			          $scope.alertMessage = ('Event Resized to make dayDelta ' + minuteDelta);
			    };
			     
			    /*
			    //this code will add new event and remove the event present on index
			    //you can call it explicitly in any method
			      $scope.events.push({
			        title: 'New Task',
			        start: new Date(y, m, 28),
			        end: new Date(y, m, 29),
			        className: ['newtask']
			      });
			     
			    $scope.events.splice(index,1);*/
			     
			     
			    //with this you can handle the click on the events
			    $scope.eventClick = function(event){
			    	  alert("even click");
			          $scope.alertMessage = (event.title + ' is clicked');
			    };
			     
			    
			    //with this you can handle the events that generated by each page render process
			    $scope.renderView = function(view){    
			        var date = new Date(view.calendar.getDate());
			        if ($scope.flatNosel != undefined) {
			        	$scope.changeroom($scope.flatNosel.type);			        	
			        }
			        $scope.currentDate = date.toDateString();
			        $scope.alertMessage = ('Page render with date '+ $scope.currentDate);
			    };
			     
			 
			    //with this you can handle the events that generated when we change the view i.e. Month, Week and Day
			    $scope.changeView = function(view,calendar) {
			        currentView = view;
			        calendar.fullCalendar('changeView',view);
			          $scope.alertMessage = ('You are looking at '+ currentView);
			    };
			     
			     
			    /* config object */
			    $scope.uiConfig = {
			      calendar:{
			        height: 450,
			        editable: true,
			        header:{
			          left: 'title',
			          center: '',
			          right: 'today prev,next'
			        },
			        dayClick: $scope.dayClick,
			        eventDrop: $scope.alertOnDrop,
			        eventResize: $scope.alertOnResize,
			        eventClick: $scope.eventClick,
			        viewRender: $scope.renderView
			      }    
			    };
			    /* event sources array*/
				  $scope.eventSources = [$scope.events];
			}];
		}
)();