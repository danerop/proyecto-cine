<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
  <meta charset="ISO-8859-1">
  <!-- Bootstrap core CSS -->
  <link href="css/bootstrap.min.css" rel="stylesheet">
  <!-- Bootstrap theme -->
  <link href="css/style.css" rel="stylesheet">
  <title>Busqueda</title>
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
              <a class="nav-link" href="mapa">Mapa de cines</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="suscripcion">Suscripciones</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="cerrarSesion">Iniciar Session</a>
            </li>
          </ul>
          <form:form action="buscar" method="GET" class="d-flex" modelAttribute="datosBuscar">
            <form:input class="form-control me-2" path="busqueda"/>
            <form:button class="btn btn-outline-success" type="submit">Search</form:button>
          </form:form>
        </div>
      </div>
    </nav>
  </header>

  <br>
  
  <div class="container">
    <div class="row justify-content-evenly row-cols-3">
      <!-- d-flex flex-column bd-highlight mb-3  -->
      <c:forEach items="${listaPeliculas}" var="pelicula">
        <div class="col d-flex justify-content-evenly" style="margin-bottom: 50px;">
          <a href="peliculas/${pelicula.getId()}">
            <div class="card" style="width: 14rem;">
              <img src="${pelicula.getUrlImagenPelicula()}" class="card-img-top" alt="...">
              <div class="card-body" style="text-align: center; background-color:black; height:60px;">
                <h5 class="card-title" style="font-size:15px; color:white;">${pelicula.getNombre()}</h5>
              </div>
            </div>
            <!-- 	<img src="${pelicula.getUrlImagenPelicula()}" class="img-thumbnail peliculaItem" alt="..."> -->
          </a>
        </div>
      </c:forEach>
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