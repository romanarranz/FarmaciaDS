<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="include/heads/general.jsp" />
<body>
	
	<jsp:include page="include/topNavbar.jsp" />
	
	<!-- Contenedor fluido -->
    <div class="container-fluid">
      
      <!-- Agrupamiento del contenigo en filas -->
      <div class="row">
		
		<jsp:include page="include/leftNavbar.jsp" />
		<jsp:include page="include/content/content_product.jsp" />

	  </div>
    </div>

 	<jsp:include page="include/scripts/general.jsp"></jsp:include>
  </body>
</html>