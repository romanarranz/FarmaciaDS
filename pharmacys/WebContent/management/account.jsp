<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="util.ServerConfig" %>

<jsp:include page="include/heads/general.jsp" />
<body>

	<%
	if(session.isNew()||session.getAttribute("user") == null){
	%>
		<div class="col-md-12">
			<h1>Usted no tiene permiso para acceder aqui</h1>
			<a href="http://<% out.print(ServerConfig.server); %>:8080/pharmacys/">Volver</a>
		</div>
	<%
	}
	else {%>
	<jsp:include page="include/topNavbar.jsp" />

	<!-- Contenedor fluido -->
    <div class="container-fluid">
      
      <!-- Agrupamiento del contenigo en filas -->
      <div class="row">
      	
      	<jsp:include page="include/leftNavbar.jsp" />
		<jsp:include page="include/content/content_account.jsp" />
		               
      </div>
    </div>

    <jsp:include page="include/scripts/general.jsp"></jsp:include>
 	<%} %>
  </body>
</html>

