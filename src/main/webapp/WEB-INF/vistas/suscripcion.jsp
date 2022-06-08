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
	<link href="css/suscripcion.css" rel="stylesheet">
<title>Suscripciones</title>
</head>

<body>
  <header>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark ">
      <div class="container-fluid">
        <!--  <div class="d-flex justify-content-evenly">...</div> -->
        <a class="navbar-brand" href="inicio">LOGO</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
          aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
          <ul class="navbar-nav w-100 d-flex justify-content-evenly">
            <li class="nav-item">
              <a class="nav-link active" aria-current="page" href="inicio">Inicio</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="suscripcion">Suscripciones</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="cerrarSesion">Iniciar Session</a>
            </li>
          </ul>
          <form class="d-flex">
            <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
            <button class="btn btn-outline-success" type="submit">Search</button>
          </form>
        </div>
      </div>
    </nav>
  </header>
<br><br>

<h2 align="center" style="color:white; text-decoration: underline;">¡Conocé nuestras suscripciones!</h2>

	<div class="container-fluid">
     <div class="container p-5">
      <div class="row justify-content-evenly">  
      <c:forEach items="${listaDeDetallesSuscripciones}" var="suscripcion" varStatus="sub" begin="1">
      
      	<c:choose>
	     <c:when test="${suscripcion.tipo == 'gold'}">
	         <div class="col-lg-4 col-md-12 mb-4">
	          <div class="card card1 h-100">
	            <div class="card-body">
	              <h2 class="card-title">${suscripcion.tipo.toUpperCase()}</h2>	<!-- ${listaDeSuscripciones[sub.index].tipo}  -->
	              <br>
	              <span class="h2">$${suscripcion.cuota}</span>/mes
	              <br><br>
	              <div class="d-grid my-3">
              		<a href="pago-suscripcion?d=${suscripcion.id}" class="btn btn-outline-dark btn-block" role="button">Seleccionar</a>
	              </div>
	              <ul>
	                <li>¡${suscripcion.cantidadBoletosGratis} entradas mensuales gratis!</li>
	                <li>¡${suscripcion.descuentoEnBoletos.intValue()} OFF en entradas todos los días!</li>
	              </ul>
	            </div>
	          </div>
	        </div>
	    </c:when>
	        
	    <c:otherwise>
	        <div class="col-lg-4 col-md-12 mb-4">
	          <div class="card card3 h-100">
	            <div class="card-body">
	              <h2 class="card-title">${suscripcion.tipo.toUpperCase()}</h2>
	              <br>
	              <span class="h2">$${suscripcion.cuota}</span>/mes
	              <br><br>
	              <div class="d-grid my-3">
	              	<a href="pago-suscripcion?d=${suscripcion.id}" class="btn btn-outline-dark btn-block" role="button">Seleccionar</a>
	              </div>
	              <ul>
	                <li>¡${suscripcion.cantidadBoletosGratis} entradas mensuales gratis!</li>
	                <li>¡${suscripcion.descuentoEnBoletos.intValue()} OFF en entradas todos los días!</li>
	              </ul>
	            </div>
	          </div>
	        </div>
	    </c:otherwise>
       </c:choose>
       
      </c:forEach>
	  </div>
    </div>
   </div>

	<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
</body>

</html>