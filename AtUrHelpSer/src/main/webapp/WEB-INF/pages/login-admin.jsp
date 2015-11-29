<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <title>AtUrHelp Admin Login</title>
  <link href="css/login.css" rel="stylesheet"  type="text/css">
</head>
<body>
  <section class="container">
    <div class="login">
      <h1>Login to your account</h1>
      <c:if test="${not empty error}">
			<div class="error">${error}</div>
		</c:if>
		<c:if test="${not empty msg}">
			<div class="msg">${msg}</div>
		</c:if>
      <form name='loginForm' action="j_spring_security_check" method='POST'>
        <p><input type="text" name="username" value="" placeholder="username"></p>
        <p><input type="password" name="password" value="" placeholder="password"></p>
        <p class="submit"><input type="submit" name="submit" value="Login"></p>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
      </form>
    </div>

    <div class="login-help">
      <p>Forgot your password? <a href="index.html">Click here to reset it</a>.</p>
    </div>
  </section>
</body>
</html>
