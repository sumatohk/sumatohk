<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@attribute name="header" fragment="true"%>
<%@attribute name="menu" fragment="true"%>
<%@attribute name="nav" fragment="true"%>
<%@attribute name="footer" fragment="true"%>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<body>
	<div id="pageheader">
		<jsp:invoke fragment="header" />
	</div>
	<div id="menu">
		<jsp:invoke fragment="menu" />
	</div>
	<div id="nav">
		<jsp:invoke fragment="nav" />
	</div>
	<div id="body">
		<jsp:doBody />
	</div>
	<div id="pagefooter">
		<jsp:invoke fragment="footer" />
	</div>
</body>
</html>