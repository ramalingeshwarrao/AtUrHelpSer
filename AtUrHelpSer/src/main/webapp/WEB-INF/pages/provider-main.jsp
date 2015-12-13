<!doctype html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>AtUrHelp.</title>
	<meta name="keywords" content="aturhelp web page, aturhelp web page template" />
	<meta name="description" content="This is aturhelp style web pag" />
	
	 
	<link rel="stylesheet" href = "http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="css/menu.css" />
	
	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>	
	<script type="text/javascript" src="js/angular.min.js"></script>
	<script type="text/javascript" src="js/angular-route.min.js"></script>
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
	
	<style>
	.btn-xlarge {
    padding: 38px 28px 38px 28px;
    font-size: 22px; //change this to your desired size
    line-height: normal;
    -webkit-border-radius: 8px;
       -moz-border-radius: 8px;
            border-radius: 8px;
	}
	
	//filedset
		fieldset{
	    background: #FCFCFC;
	    padding: 16px;
	    border: 1px solid #D5D5D5;
	}
	.addfields{
	    margin: 10px 0;
	}
	
	#choicesDisplay {
	    padding: 10px;
	    background: rgb(227, 250, 227);
	    border: 1px solid rgb(171, 239, 171);
	    color: rgb(9, 56, 9);
	}
	.remove{
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
	input[type="text"],
	select{
	    padding:5px;
	}
	//End of fieldset
	
	
	</style>
	<script>
		function formSubmit() {
			document.getElementById("logoutForm").submit();
		}
	</script>

</head>
<body ng-app="ProviderModule" ng-conroller="ProviderController" >

	<div id='cssmenu'>
		<ul>
			<li style="float: left"><a href="#provider"><span>Home</span></a></li>	
			<c:url value="/j_spring_security_logout" var="logoutUrl" />
			<!-- csrt for log out-->
			<form action="${logoutUrl}" method="post" id="logoutForm">
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
			</form>
			<c:if test="${pageContext.request.userPrincipal.name != null}">
			<li style="float: right"><a href="javascript:formSubmit()"><span>Logout</span></a></li>
			<li style="float: right"><a href='#'>Welcome : ${pageContext.request.userPrincipal.name} | </a></li>
			</c:if>
			<li class='has-sub' style="float: right"><a href='#'><span>Profile</span></a>
				<ul>
					<li><a href='#providerprofile'><span>View Profile</span></a></li>
					<li><a href='#providerclosecomplaints'><span>Change Password</span></a></li>
				</ul></li>
		</ul>
	</div>
	
	<center>
     <div ng-view>
     ..loading
     </div>
  </center>
</body>
</html>