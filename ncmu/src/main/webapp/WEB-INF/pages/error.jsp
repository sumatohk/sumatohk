<%@taglib prefix="c" tagdir="/WEB-INF/tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Error</title>
</head>
<body>
	<h2>
		<c:out value="${url}" />
	</h2>
	<h2>
		<c:out value="${STATUS}" />		
	</h2>
	<div>
		<c:forEach items="${exception}" var="e">
			<div>
				<c:out value="${e}"></c:out>
			</div>

		</c:forEach>
	</div>
</body>
</html>