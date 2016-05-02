<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <!-- Barra de navegacion superior -->
    <nav class="navbar navbar-inverse navbar-fixed-top">
      
      <div class="container-fluid">
      	
      	<!-- Menu de hamburguesa para el responsive -->
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">Nombre Farmacia</a>
        </div>
        
        <!-- Cuerpo barra de navegacion -->
        <div id="navbar" class="navbar-collapse collapse">
          
          <!-- Buscador de la izquierda -->
          <form class="navbar-form navbar-left">
          	<div class="input-group">
				<input type="text" class="form-control" placeholder="Search" name="srch-term" id="srch-term">
				<div class="input-group-btn">
					<button id="searchTopNav" class="btn btn-default" type="submit"><i class="glyphicon glyphicon-search"></i></button>
				</div>
			</div>            
          </form>
          
          <!-- Botones de menu principal -->
          <ul class="nav navbar-nav navbar-right">
            <li><a class="btn btn-default btn-sm" href="#"><i class="fa fa-clock-o" aria-hidden="true"></i><span>Reservas</span></a></li>
            <li><a class="btn btn-default btn-sm" href="#"><i class="fa fa-shopping-basket" aria-hidden="true"></i><span>Pedidos</span></a></li>
            
            <!-- Dropdown Usuario -->
            <li class="dropdown">
			  <a class="btn btn-default btn-sm dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
			  <%
			    String username = null;
				if(session.getAttribute("user") != null)
					username = session.getAttribute("user").toString();
				%>
			    <i class="fa fa-user" aria-hidden="true"></i><span><% out.print(username); %></span>
			    <span class="caret"></span>
			  </a>
			  <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
			    <li><a href="account.jsp">Preferences</a></li>
			    <li role="separator" class="divider"></li>
			    <li><a href="../login?logout=yes">Sign out</a></li>
			  </ul>
			</li>
          </ul>
          
        </div> <!-- Cuerpo barra navegacion -->
      </div><!-- .container-fluid -->
    </nav>