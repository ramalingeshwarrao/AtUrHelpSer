(function(){
	
	'use strict';
	
	$PROVIDER.MilkEditController = ['$scope', '$http', '$location', 'printitems', 'dailymilkedit',  function ($scope, $http, $location, printitems, dailymilkedit) {
		
		//Get the daily milk data from dailymilkedit service
		$scope.milkObj = dailymilkedit.getDailyMilkObj();
		var supplyDate = dailymilkedit.getDailyMilkDate();
		$scope.edit = {};
		$scope.edit.milkid = false;
		$scope.edit.quantity = false;
		
		$scope.milkfun = function() {
			$scope.edit.notreq = false;
		};
		
		$scope.editQuan = function() {
			$scope.edit.notreq = false;
			$scope.editquantity = $scope.milkObj.quantity;			
		}; 
		
		$scope.deleteRec = function() {
			$scope.loading = true;
			$http (
					 {
						 method :  'GET',
						 url : $PROVIDER.providerMilkRest+'/dmt?roomid='+$scope.milkObj.flatprimarykey+'&milkid='+$scope.milkObj.packetprimarykey+'&sd='+supplyDate,
						 headers : {
								'Content-Type' : 'application/json'
							}
					 }).success(function(data) {
						 $scope.loading = false;
						 if (data == 0) {
							 BootstrapDialog.alert("Succesfuly Deleted");
							 $location.path( '/dailymilk' );
						} else {
							$scope.loading = false;
							BootstrapDialog.alert("Fail To Updtea Record");
						}
						 
					 }).error(function(data, status, headers, config) {
						 $scope.loading = false;
						 BootstrapDialog.alert("Fail to delete record contact admin for the support");
					 });
		};
		
		$scope.delfun = function() {
			$scope.edit.quantity = false;
			$scope.edit.milkid = false;
		};
		
		$scope.submit = function() {
			var isMilkIdSelect = $scope.edit.milkid;
			var changeMilkId = null;
			if (isMilkIdSelect) {
				changeMilkId = $scope.milksel.type;
				if (changeMilkId == "0") {
					BootstrapDialog.alert("Kindly Provide Valid Milk Id");
					return ;
				}
			}
			var isQuantitySelect = $scope.edit.quantity;
			var editQuantity = null;
			if (isQuantitySelect) {
				editQuantity = $scope.editquantity;
				if (editQuantity == "0" || editQuantity == "") {
					BootstrapDialog.alert("Kindly Provide Valid Quantity");
					return;
				}
			}
			$scope.loading = true;
			$scope.upatemilk = {
					"supplydate" : supplyDate,
					"newmilkid" : changeMilkId,
					"newquantity" : editQuantity,
					"mrmilkid" : $scope.milkObj.packetprimarykey,
					"mrroomid" : $scope.milkObj.flatprimarykey,
					"mtmilkid" : $scope.milkObj.packetprimarykey,
					"mtroomid" : $scope.milkObj.flatprimarykey
			};
			// create http post request
			$http(
					{
						method : 'POST',
						url : $PROVIDER.providerMilkRest+'/editmilk',
						data : $scope.upatemilk,
						headers : {
							'Content-Type' : 'application/json'
						}
					}).success(function(data) {
						$scope.loading = false;
				if (data == 0) {
					 BootstrapDialog.alert("Succesfuly Updated");
					 $location.path( '/dailymilk' );
				} else {
					$scope.loading = false;
					BootstrapDialog.alert("Fail To Updtea Record");
				}
			});
			
			
		};
		//Packet details
		$scope.packetdetails = function() {
			$http (
				 {
					 method :  'GET',
					 url : $PROVIDER.providerMilkRest+'/milkdetails',
					 headers : {
							'Content-Type' : 'application/json'
						}
				 }).success(function(data) {
					 if (data != "") {
						 if (data.milk.length == undefined) {
							 $scope.milkdetailsArray = [];
							 $scope.milkdetailsArray[0] = data.milk;
							 $scope.milkdetails = $scope.milkdetailsArray;
							 $scope.milksel = {type : $scope.milkdetailsArray[0].id};
						 } else  {
							 $scope.milkdetails = data.milk;
							 $scope.milksel = {type : $scope.milkdetails[0].id};
						 }	 
					 } else {
						 $scope.milkdetails = "";
						 BootstrapDialog.alert("No milk packets found please create a milk packets and continue");
						 $location.path( '/milkroute' );
					 }
				 }).error(function(data, status, headers, config) {
					 BootstrapDialog.alert("Fail to get route details, contact administrator for support");
				 });
		};
		//End of Packet details
		$scope.packetdetails();
		
	}];

})();