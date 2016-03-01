<!doctype html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en" moznomarginboxes mozdisallowselectionprint>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>AtUrHelp.</title>
<meta name="keywords" content="aturhelp web page, aturhelp web page template" />
<meta name="description" content="This is aturhelp style web pag" />

<link type="text/css" rel="stylesheet" href="css/leftmenu.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap3-dialog/1.34.7/css/bootstrap-dialog.min.css">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<!--  New UI-->
<link type="text/css" rel="stylesheet" href="css/jquery-ui-1.10.4.custom.min.css">
<link type="text/css" rel="stylesheet" href="css/font-awesome.min.css">



<link rel="stylesheet" type="text/css" href="css/menu.css" />
<link rel="stylesheet" type="text/css" href="css/fullcalendar.min.css" />

<script type="text/javascript" src="js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="js/jquery-migrate-1.2.1.min.js"></script>
<script type="text/javascript" src="js/jquery-ui.js"></script>
<script type="text/javascript" src="js/bootstrap-hover-dropdown.js"></script>

<script type="text/javascript" src="js/jquery.menu.js"></script>
<script type="text/javascript" src="js/moment.js"></script>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery-ui.custom.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/fullcalendar.min.js"></script>
<script type="text/javascript" src="js/angular.min.js"></script>
<script type="text/javascript" src="js/angular-route.min.js"></script>
<script type="text/javascript" src="js/calendar.js"></script>
<script src="http://angular-ui.github.io/bootstrap/ui-bootstrap-tpls-0.12.1.min.js"></script>
<script type="text/javascript" src="js/provider-main.js"></script>
<script type="text/javascript" src="js/provider-frame.js"></script>
<script type="text/javascript" src="js/provider-menu.js"></script>
<script type="text/javascript" src="js/provider-opencomplaints.js"></script>
<script type="text/javascript" src="js/provider-closecomplaints.js"></script>
<script type="text/javascript" src="js/provider-profile.js"></script>
<script type="text/javascript" src="js/provider.js"></script>
<script type="text/javascript" src="js/milk-route.js"></script>
<script type="text/javascript" src="js/milk-location.js"></script>
<script type="text/javascript" src="js/milk-appartment.js"></script>
<script type="text/javascript" src="js/milk-packets.js"></script>
<script type="text/javascript" src="js/milk-flatno.js"></script>
<script type="text/javascript" src="js/milk-flatnoVSMilk.js"></script>
<script type="text/javascript" src="js/milk-details.js"></script>
<script type="text/javascript" src="js/no-milk.js"></script>
<script type="text/javascript" src="js/milk-dailymilk.js"></script>
<script type="text/javascript" src="js/milk-print.js"></script>
<script type="text/javascript" src="js/milk-required.js"></script>
<script type="text/javascript" src="js/milk-room-status.js"></script>
<script type="text/javascript" src="js/milk-bill.js"></script>
<script type="text/javascript" src="js/milk-balancesheet.js"></script>
<script type="text/javascript" src="js/milk-category.js"></script>
<script type="text/javascript" src="js/milk-comment.js"></script>
<script type="text/javascript" src="js/milk-alternative.js"></script>
<script type="text/javascript" src="js/milk-flat-inactive.js"></script>
<script type="text/javascript" src="js/milk-edit.js"></script>
<script type="text/javascript" src="js/milk-priority.js"></script>

<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular-animate.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular-aria.min.js"></script>
<script type="text/javascript" src="js/angular-material.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap3-dialog/1.34.7/js/bootstrap-dialog.min.js"></script>
<script src="https://s3-us-west-2.amazonaws.com/s.cdpn.io/t-114/assets-cache.js"></script>


<link rel="stylesheet" href="css/angular-material.css">




<style>
.fc-time {
	display: none;
}

@page {
	size: auto; /* auto is the initial value */
	margin: 0mm; /* this affects the margin in the printer settings */
}

.btn-xlarge {
	padding: 38px 28px 38px 28px;
	font-size: 22px; // change this to your desired size line-height :
	normal;
	-webkit-border-radius: 8px;
	-moz-border-radius: 8px;
	border-radius: 8px;
}

//
filedset
		fieldset {
	background: #FCFCFC;
	padding: 16px;
	border: 1px solid #D5D5D5;
}

.addfields {
	margin: 10px 0;
}

#choicesDisplay {
	padding: 10px;
	background: rgb(227, 250, 227);
	border: 1px solid rgb(171, 239, 171);
	color: rgb(9, 56, 9);
}

.remove {
	background: #C76868;
	color: #FFF;
	font-weight: bold;
	font-size: 21px;
	border: 0;
	cursor: pointer;
	display: inline-block;
	padding: 4px 9px;
	vertical-align: top;
	line-height: 100%;
}

input[type="text"],select {
	padding: 5px;
}

//
End of fieldset
	
	
	.highPriority {
	background-color: red;
	color: red;
}
</style>
<script>
		function formSubmit() {
			document.getElementById("logoutForm").submit();
		}
	</script>

</head>
<body ng-app="ProviderModule" ng-conroller="ProviderController">


	<div id="header-topbar-option-demo" class="page-header-topbar">
		<c:url value="/j_spring_security_logout" var="logoutUrl" />
		<!-- csrt for log out-->
		<form action="${logoutUrl}" method="post" id="logoutForm">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		</form>
		<nav id="topbar" role="navigation" style="margin-bottom: 0;" data-step="3" class="navbar navbar-default navbar-static-top">
			<div class="navbar-header">
				<button type="button" data-toggle="collapse" data-target=".sidebar-collapse" class="navbar-toggle">
					<span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span>
				</button>
				<a id="logo" href="index.html" class="navbar-brand"><span class="fa fa-rocket"></span><span class="logo-text">AtUrHelp</span><span
					style="display: none" class="logo-text-icon">µ</span></a>
			</div>
			<div class="topbar-main">
				<a id="menu-toggle" href="#" class="hidden-xs"><i class="fa fa-bars"></i></a>



				<ul class="nav navbar navbar-top-links navbar-right mbn">
					<li class="dropdown"><a data-hover="dropdown" href="#route" class="dropdown-toggle"><i class="fa fa-home"></i></a>
					</li>
					<li class="dropdown"><a data-hover="dropdown" href="#" class="dropdown-toggle"><i class="fa fa-bell fa-fw"></i><span
							class="badge badge-green">0</span></a>
					</li>
					<li class="dropdown topbar-user"><a data-hover="dropdown" href="#" class="dropdown-toggle"><img src="images/48.jpg" alt=""
							class="img-responsive img-circle" />&nbsp;<span class="hidden-xs;">${pageContext.request.userPrincipal.name} </span>&nbsp;<span class="caret"></span></a>
						<ul class="dropdown-menu dropdown-user pull-right">
							<li><a href="#"><i class="fa fa-user"></i>My Profile</a></li>
							<li><a href="#"><i class="fa fa-tasks"></i>My Tasks<span class="badge badge-success">0</span></a></li>
							<li class="divider"></li>
							<li><a href="javascript:formSubmit()"><i class="fa fa-key"></i>Log Out</a></li>
						</ul></li>

				</ul>
			</div>
		</nav>
		<!--BEGIN MODAL CONFIG PORTLET-->

		<!--END MODAL CONFIG PORTLET-->
	</div>

	<center>
		<div ng-view>..loading</div>
	</center>
</body>
</html>