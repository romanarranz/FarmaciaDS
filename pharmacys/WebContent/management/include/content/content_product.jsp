<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<jsp:include page="modal_insert_product.jsp" />
<jsp:include page="modal_edit_product.jsp" />
<jsp:include page="modal_delete_product.jsp" />

<!-- Bloque central que ocupa un 75% de la pantalla, 100% en responsive movil -->
<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">	
        
	<div class="row">
	<%  
    List<String> msg = (ArrayList<String>) session.getAttribute("msg");
    List<String> errors = (ArrayList<String>) session.getAttribute("errors");
    if(msg != null && !msg.isEmpty()){
    	
    	out.println("<div class=\"alert alert-success\" role=\"alert\">");
    	out.println("<p class=\"glyphicon glyphicon-ok\" aria-hidden=\"true\">");
  		for(String s : msg)
  			out.println(s);
  		out.println("</p>");    	
		out.println("</div>");	
    }
    session.removeAttribute("msg");
    		
    if(errors != null && !errors.isEmpty()){
       	out.println("<div class=\"alert alert-danger\" role=\"alert\">");
    	out.println("<p class=\"glyphicon glyphicon-exclamation-sign\" aria-hidden=\"true\">");   		        	
    	for(String s : errors)
      		out.println(s);
      	out.println("</span>");
        	
    	out.println("</div>");    	
    }
    session.removeAttribute("errors");
    %>
	<div class="row placeholders" style="padding:0 20px">
    	<h1 class="page-header text-left">Últimas consultas</h1>
	    <div class="col-xs-6 col-sm-3 placeholder">
	    	<img src="http://10.211.55.6/img/products/img_no_aviable.png" width="200" height="200" class="img-responsive" alt="Generic placeholder thumbnail">
	        <h4>Label</h4>
	        <span class="text-muted">Something else</span>
	    </div>
	    <div class="col-xs-6 col-sm-3 placeholder">
	       	<img src="http://10.211.55.6/img/products/img_no_aviable.png" width="200" height="200" class="img-responsive" alt="Generic placeholder thumbnail">
	        <h4>Label</h4>
	     	<span class="text-muted">Something else</span>
	   	</div>
		<div class="col-xs-6 col-sm-3 placeholder">
	       	<img src="http://10.211.55.6/img/products/img_no_aviable.png" width="200" height="200" class="img-responsive" alt="Generic placeholder thumbnail">
	        <h4>Label</h4>
	        <span class="text-muted">Something else</span>
	  	</div>
	   	<div class="col-xs-6 col-sm-3 placeholder">
	      	<img src="http://10.211.55.6/img/products/img_no_aviable.png" width="200" height="200" class="img-responsive" alt="Generic placeholder thumbnail">
	      	<h4>Label</h4>
	     	<span class="text-muted">Something else</span>
		</div>
	</div>
    
    <script>
    function idSorter(a, b) {
        if (a > b) return 1;
        if (a < b) return -1;
        return 0;
    }
	</script>
	<div class="table-responsive" style="background-color: #eee; border-radius: 20px; padding: 20px;">          	
    	<h2 class="sub-header">Listado de productos</h2>
    	
    	<!-- Add product -->
    	<button type="button" class="btn btn-primary" style="position:relative; top: 45px" data-toggle="modal" data-target="#insert">Añadir</button>
    	
    	<!-- Table -->
       	<table class="table table-striped" data-toggle="table" data-height="460" data-sort-name="id" data-sort-order="desc"
       	data-side-pagination="server" data-show-columns="true" data-pagination="true" data-page-size="10" data-page-list="[10,20,30]" 
       	data-show-toggle="true" data-search="true" data-mobile-responsive="true">
       		<thead>
           		<tr>
                	<th data-field="id" data-sortable="true" data-switchable="false">#</th>
                  	<th data-field="name" data-sortable="true" data-switchable="false">Nombre</th>
                  	<th data-field="laboratory" data-sortable="true" data-visible="false">Laboratorio</th>
                  	<th data-field="category" data-sortable="true">Categoria</th>
                  	<th data-field="subcategory" data-sortable="true" data-visible="false">Subcategoria</th>
                  	<th data-sortable="false" data-switchable="false">Edit</th>
                  	<th data-sortable="false" data-switchable="false">Delete</th>
                </tr>
          	</thead>
          	<tbody>
            <%@ page import="dao.DBConnector, java.util.*, model.Product" %>
          	<%
          		DBConnector dbc = new DBConnector();
          		List<Product> productlist = dbc.getAllProducts();
          		for(Product p : productlist){
          			out.println("<tr>");
          			out.println("<td>"+p.getId()+"</td>");
          			out.println("<td>"+p.getName()+"</td>");
          			out.println("<td>"+p.getLaboratory()+"</td>");          			
          			out.println("<td>"+p.getCategory()+"</td>");
          			out.println("<td>"+p.getUnits()+"</td>");
          			out.println("<td><i class=\"fa fa-pencil\" aria-hidden=\"true\" data-toggle=\"modal\" data-target=\"#edit\"></i></td>");
          			out.println("<td><i class=\"fa fa-trash\" aria-hidden=\"true\" data-toggle=\"modal\" data-target=\"#delete\"></i></td>");
          			out.println("</tr>");
          		}
        	%>          
            </tbody>
       	</table>
            
       	<!-- Paginador de productos -->
        <nav class="text-center">
			<ul class="pagination">
				<li>
			    	<a href="#" aria-label=Anterior>
			        	<span aria-hidden="true">&laquo;</span>
			      	</a>
			    </li>
			    <li><a href="#">1</a></li>
			    <li><a href="#">2</a></li>
			    <li><a href="#">3</a></li>
			    <li><a href="#">...</a></li>
			    <li><a href="#">5</a></li>
			    <li>
			      	<a href="#" aria-label="Siguiente">
			        	<span aria-hidden="true">&raquo;</span>
			      	</a>
			    </li>
			</ul>
		</nav>
 	</div><!-- table-responsive -->
            
	</div><!-- row -->
</div> <!-- main -->