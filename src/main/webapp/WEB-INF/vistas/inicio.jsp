<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<!-- Bootstrap core CSS -->
	<link href="css/bootstrap.min.css" rel="stylesheet" >
	<!-- Bootstrap theme -->
	<link href="css/style.css" rel="stylesheet">
<title>Home</title>
</head>

<body>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark ">
	  <div class="container-fluid"> 
	  <!--  <div class="d-flex justify-content-evenly">...</div> -->
	    <a class="navbar-brand" href="inicio">LOGO</a>
	    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
	      <span class="navbar-toggler-icon"></span>
	    </button>
	    <div class="collapse navbar-collapse" id="navbarSupportedContent">
	      <ul class="navbar-nav w-100 d-flex justify-content-evenly">
	        <li class="nav-item">
	          <a class="nav-link active" aria-current="page" href="inicio">Home</a>
	        </li>
	        <li class="nav-item">
	          <a class="nav-link" href="peliculas">Peliculas</a>
	        </li>
   			<li class="nav-item">
   			  <a class="nav-link" href="cerrarSesion">Cerrar sesión</a>
   			</li>
	        <li class="nav-item dropdown">
	          <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
	            Suscripciones
	          </a>
	          <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
	            <li><a class="dropdown-item" href="#">Action</a></li>
	            <li><a class="dropdown-item" href="#">Another action</a></li>
	            <li><hr class="dropdown-divider"></li>
	            <li><a class="dropdown-item" href="#">Something else here</a></li>
	          </ul>
	        </li>
	      </ul>
	      <form class="d-flex">
	        <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
	        <button class="btn btn-outline-success" type="submit">Search</button>
	      </form>
	    </div>
	  </div>
	</nav>
<br>
			<%
				if (request.getAttribute("usuario") != null) {
			%>
			<h2 class="text-center text-light mb-4">Bienvenido,
				${usuario.email}.</h2>
				<h2 class="text-center text-light mb-4">Rol:
				${usuario.getRol().getNombre()}.</h2>
			<%
				}
			%>
			
	      <c:if test = "${usuario.getRol().getNombre() == 'admin'}">
  		<div class="">
	  		<a class="btn btn-primary" role="button" href="http://localhost:8080/proyecto-cine/admin?sel=cargar-cine">Controles Administrador</a>
		</div>
		<br>	
    	 </c:if>



<select class="form-select-bg-size cine-selection" aria-label="Default select example">
  <option selected>Selecciona un cine</option>
  <option value="1">Hoyts Morón</option>
  <option value="2">Cinemark San Justo</option>
  <option value="3">Cinemark Caballito</option>
</select>
<select class="form-select-bg-size" aria-label="Default select example">
  <option selected>Selecciona una película</option>
  <option value="1">Batman</option>
  <option value="2">Spiderman</option>
  <option value="3">Uncharted</option>
</select>
<br>
<br>
<h3 class="text-center">Películas</h3>
<br>
<br>
<div class="container">
  <div class="row justify-content-evenly row-cols-3">	<!-- d-flex flex-column bd-highlight mb-3  -->
	<c:forEach items="${listaPeliculas}" var="pelicula">
     	<div class="col d-flex justify-content-evenly" style="margin-bottom: 50px;">
     		<div class="card" style="width: 14rem;">
  			<img src="${pelicula.getUrlImagenPelicula()}" class="card-img-top" alt="...">
			  <div class="card-body" style="text-align: center; background-color:black; height:60px;">
			    <h5 class="card-title" style="font-size:15px; color:white;">${pelicula.getNombre()}</h5>
			    <a href="compra?p=${pelicula.getId()}&u=${usuario.getId()}" class="btn btn-primary" role="button">COMPRAR</a>
			  </div>
			</div>
		<!-- 	<img src="${pelicula.getUrlImagenPelicula()}" class="img-thumbnail peliculaItem" alt="..."> -->
 		</div>				
	</c:forEach>
	<br>
  </div>
  <br>

</div>
		<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
</body>

</html>