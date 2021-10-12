<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<tags:templete>
	<jsp:attribute name="header">
     <jsp:include page="../include/header.jsp"></jsp:include>
    </jsp:attribute>
	<jsp:attribute name="menu">
      <jsp:include page="../include/menu.jsp"></jsp:include>
    </jsp:attribute>
	<jsp:attribute name="footer">	
       <jsp:include page="../include/footer.jsp"></jsp:include>
    </jsp:attribute>
	<jsp:body>		
       <div style="text-align:center;">
       Home page.
       </div>
	</jsp:body>
</tags:templete>