<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<head>
<title>Login Page</title>
<style>
.error {
	padding: 15px;
	margin-bottom: 20px;
	border: 1px solid transparent;
	border-radius: 4px;
	color: #a94442;
	background-color: #f2dede;
	border-color: #ebccd1;
}

.msg {
	padding: 15px;
	margin-bottom: 20px;
	border: 1px solid transparent;
	border-radius: 4px;
	color: #31708f;
	background-color: #d9edf7;
	border-color: #bce8f1;
}

#login-box {
	width: 300px;
	padding: 20px;
	margin: 100px auto;
	background: #fff;
	-webkit-border-radius: 2px;
	-moz-border-radius: 2px;
	border: 1px solid #000;
}
</style>
<script
	src="https://cdn.staticfile.org/vue-resource/1.5.1/vue-resource.min.js"></script>
<script>
	function getCode(obj) {
		obj.src = "/vercode";
	}
</script>
</head>
<body onload='document.loginForm.username.focus();'>
	<h1>Spring Security Login Form (Database Authentication)</h1>
	<div id="login-box">
		<h2>Login with User name and Password</h2>
		<c:if test="${not empty error}">
			<div class="error">${error}</div>
		</c:if>
		<c:if test="${not empty msg}">
			<div class="msg">${msg}</div>
		</c:if>

		<form name="loginForm" action="/login" method="POST">
			<table>
				<tr>
					<td>User:</td>
					<td><input type="text" name="username" value="${username}"></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type="password" name="password" value="${password}"></td>
				</tr>
				<tr>
					<td><img src="/vercode" alt="Code"
						onclick="javascript:getCode(this)"></td>
					<td><input type="text" name="requestCode" id="code"></td>
				</tr>
				<tr>
					<td colspan='2'><input name="submit" type="submit"
						value="submit" /></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>