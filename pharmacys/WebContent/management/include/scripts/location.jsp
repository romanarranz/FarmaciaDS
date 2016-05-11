<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dao.DBConnector, model.Pharmacy" %>
    
    <jsp:include page="general.jsp" />
	
	<!-- Get current position by GPS -->
	<script src="../assets/js/geo.js"></script>
	
	<!-- Drag Marker Google Maps -->
    <script src="http://maps.google.com/maps/api/js?sensor=false"></script>
	<script src="../js/googleMapsDraggableMarker.js"></script>
	
	<%
	String cif = (String) session.getAttribute("cif");
	
	if(cif != null || cif != ""){
		DBConnector dbc = new DBConnector();
		Pharmacy pharmacy = dbc.getPharmacyByCIF(cif);
		
		if(pharmacy != null){
			if(pharmacy.getLatitude() != 0.0 && pharmacy.getLongitude() != 0.0)
				out.println("<script>loadMap("+pharmacy.getLatitude()+","+pharmacy.getLongitude()+");</script>");
			else {
				String browserType = request.getHeader("User-Agent");
				out.println("<script>alert('"+browserType+"');</script>");
				out.println("<script>asyncGetGeo(); checkCurrentGeo();</script>");
			}
		}
	}
	%>