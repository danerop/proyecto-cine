<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
  <meta charset="ISO-8859-1">
  <!-- Bootstrap core CSS -->
  <link href="css/bootstrap.min.css" rel="stylesheet">
  <!-- Bootstrap theme -->
  <link href="css/style.css" rel="stylesheet">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
  <title>Inicio</title>
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
            <li class="nav-item"><a class="nav-link active" href="inicio">Inicio</a></li>
            <li class="nav-item"><a class="nav-link" href="mapa">Mapa de cines</a></li>
            <li class="nav-item"><a class="nav-link" href="suscripcion">Suscripciones</a></li>
            <li class="nav-item"><a class="nav-link" href="cerrarSesion">Iniciar Session</a></li>
          	<% if (request.getAttribute("usuario") != null) { %>
          	  <li class="nav-item"><a class="nav-link" href="historialcompras">Mis Boletos</a></li>
          	  <li class="nav-item">
          	    <button type="button" class="btn btn-dark position-relative" data-bs-toggle="modal" data-bs-target="#exampleModal">
                  <i class="bi bi-bell-fill text-secondary"></i>
            	  <c:if test="${notificaciones.size()>0}">
                    <span class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">
                      ${notificaciones.size()} <span class="visually-hidden">unread messages</span>
                    </span>
                  </c:if>
                </button>
              </li>
        	<% } %>
        	<li>
              <form:form action="buscar" method="GET" class="d-flex" modelAttribute="datosBuscar">
                <form:input class="form-control me-2" path="busqueda"/>
                <form:button class="btn btn-outline-success" type="submit">Search</form:button>
              </form:form>
            </li>
          </ul>
        </div>
      </div>
    </nav>
  </header>

  <!-- Modal -->
  <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">Notificaciones</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <c:forEach items="${notificaciones}" var="notificacion">
            <div class="alert alert-secondary">
              <h5>${notificacion.getNotificacion().getTitulo()}</h5>
              <hr>
              <p>${notificacion.getNotificacion().getTexto()}</p>
            </div>
          </c:forEach>

        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
        </div>
      </div>
    </div>
  </div>
  <!-- Modal -->

  <c:if test="${msg!=null}">
    <div class="alert alert-warning alert-dismissible fade show" role="alert">
      <strong>${msg}</strong>
      <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>
  </c:if>

  <% if (request.getAttribute("usuario") != null) { %>
  	<div class="container">
  	  <br>
      <h4 class="text-center text-light mb-4">Bienvenido, ${usuario.email} <br> Rol: ${usuario.getRol()}</h4>
    
      <c:if test="${usuario.getRol() == 'admin'}">
        <a class="btn btn-primary" role="button" href="http://localhost:8080/proyecto-cine/admin?sel=cargar-cine">Controles Administrador</a>
      </c:if>
      <c:if test="${usuario.getRol() == 'usuario'}">
        <a href="generos" class="btn btn-primary" role="button">Seleccionar generos favoritos</a>
      </c:if>
    </div>
  <% } %>
  
  <br>
  <br>
  <h3 class="text-center">Películas</h3>
  <br>
  <br>
  
  <div class="container">
    <div class="row justify-content-evenly row-cols-3">
      <!-- d-flex flex-column bd-highlight mb-3  -->
      <c:forEach items="${listaPeliculas}" var="pelicula">
        <div class="col d-flex justify-content-evenly" style="margin-bottom: 50px;">
          <a href="peliculas/${pelicula.getId()}" style="text-decoration: none;">
            <div class="card" style="width: 14rem;">
              <img src="${pelicula.getUrlImagenPelicula()}" class="card-img-top" alt="${pelicula.getNombre()}">
              <div class="card-body" style="text-align: center; background-color: black; height: 60px;">
                <h5 class="card-title" style="font-size: 15px; color: white;">${pelicula.getNombre()}</h5>
              </div>
            </div>
          </a>
          <!-- 	<img src="${pelicula.getUrlImagenPelicula()}" class="img-thumbnail peliculaItem" alt="..."> -->
        </div>
      </c:forEach>
      <br>
    </div>
    <br>
  </div>
  
  <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"
    integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous">

  </script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"
    integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous">

  </script>
  <script src="./js/bootstrap.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>