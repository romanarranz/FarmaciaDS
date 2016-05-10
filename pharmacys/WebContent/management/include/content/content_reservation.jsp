<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*" %>
<%@ page import="dao.DBConnector, model.Reservation" %>

 <!-- Bloque central que ocupa un 75% de la pantalla, 100% en responsive movil -->
 <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main" style="min-height: 100%; height: 100vh;">
  
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
</div>

<div class="col-md-12" style="background: white; height: auto; min-height: 90%; border-radius: 12px;">
	<h2>Reservation List</h2>
	<div class="table-responsive">
		<table class="table table-striped">
			<thead>
	        	<tr>
	            	<th>#</th>
	                <th>Email</th>
	                <th>ProductId</th>
	                <th>Quantity</th>
	            </tr>
	       	</thead>
	       	<tbody>
	       		<%
	       		DBConnector dbc = new DBConnector();
	       		
	       		String cif = (String) session.getAttribute("cif");
	        	
	        	if(cif != null && !cif.equals(null) && cif != ""){
	       			List<Reservation> reservationList = dbc.getAllReservationByCIF(cif);
	       			if(reservationList != null){
	       				
	       				for(Reservation r : reservationList){
	       					out.println("<tr>");
	       					out.println("<td>"+r.getEmail()+"</td>");
	       					out.println("<td>"+r.getProductId()+"</td>");
	       					out.println("<td>"+r.getQuantity()+"</td>");
	       					out.println("<td><i class=\"fa fa-eye\" aria-hidden=\"true\"></i></td>");
	       					out.println("</tr>");
	       				}
	       			}
	        	}
	       		%>
	       		<tr></tr>
	       	</tbody>
		</table>
	</div>
</div>

</div>