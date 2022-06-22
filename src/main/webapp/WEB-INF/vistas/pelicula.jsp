<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
  <meta charset="ISO-8859-1">
  <!-- Bootstrap core CSS -->
  <link href="../css/bootstrap.min.css" rel="stylesheet">
  <!-- Bootstrap theme -->
  <link href="../css/style.css" rel="stylesheet">
  <title>ProyectoCine ${pelicula.getNombre()}</title>
</head>

<body>
  <header>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark ">
      <div class="container-fluid">
        <!--  <div class="d-flex justify-content-evenly">...</div> -->
        <a class="navbar-brand" href="../inicio">LOGO</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
          aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
          <ul class="navbar-nav w-100 d-flex justify-content-evenly">
            <li class="nav-item"><a class="nav-link" href="../inicio">Inicio</a></li>
            <li class="nav-item"><a class="nav-link" href="../mapa">Mapa de cines</a></li>
            <li class="nav-item"><a class="nav-link" href="../suscripcion">Suscripciones</a></li>
            <li class="nav-item"><a class="nav-link" href="../cerrarSesion">Iniciar Session</a></li>
          	<% if (request.getAttribute("usuario") != null) { %>
          	  <li class="nav-item"><a class="nav-link" href="../historialcompras">Mis Boletos</a></li>
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
              <form:form action="../buscar" method="GET" class="d-flex" modelAttribute="datosBuscar">
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
  
  <br>
  
  <div class="container">
    <div class="row bg-white rounded p-3 mb-2">
      <div class="col-5">
        <img class="img-fluid" src="../${pelicula.getUrlImagenPelicula()}" alt="imagen de pelicula">
        <br>
        <a href="../compra?p=${pelicula.getId()}" class="btn btn-primary" role="button">COMPRAR</a>
      </div>
      <div class="col-7">
        <span style="font-size: large; font-weight: bold;">${pelicula.getNombre()}</span>
        <br>
        <p>
          año: ${pelicula.getAnio()} <br>
          Duración: ${pelicula.getDuracion()} min <br>
          Descripción: ${pelicula.getDescripcion()} <br>
        </p>
        
        <c:forEach items="${listaCines}" var="cine">
          <div>${cine.getNombreLocal()}</div>
        </c:forEach>
        <c:forEach items="${listaFuncion}" var="funcion">
          <div>${funcion.getId()}</div>
        </c:forEach>
      </div>
    </div>
  </div>
	
  <br>
  
  <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"
    integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous">
  </script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"
    integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous">
  </script>
</body>

</html>