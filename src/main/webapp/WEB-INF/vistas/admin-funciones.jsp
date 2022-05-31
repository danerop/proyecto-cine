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
  <title>Controles Administrador</title>
</head>

<body>
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
            <a class="nav-link" aria-current="page" href="inicio">Home</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="peliculas">Películas</a>
          </li>
          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown"
              aria-expanded="false">
              Suscripciones
            </a>
            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
              <li><a class="dropdown-item" href="#">Action</a></li>
              <li><a class="dropdown-item" href="#">Another action</a></li>
              <li>
                <hr class="dropdown-divider">
              </li>
              <li><a class="dropdown-item" href="#">Something else here</a></li>
            </ul>
          </li>
          <li class="nav-item">
            <a class="nav-link disabled">Disabled</a>
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
  <h3 class="text-center">Controles Administrador</h3>
  <br>

  <div class="btn-toolbar justify-content-center" role="toolbar" aria-label="Toolbar with button groups">
    <div class="btn-group mr-2" role="group">
      <a class="btn btn-secondary cargar-cine" href="http://localhost:8080/proyecto-cine/admin-cines">Cines</a>
      <a class="btn btn-secondary cargar-sala" href="http://localhost:8080/proyecto-cine/admin-salas">Salas</a>
      <a class="btn btn-secondary cargar-pelicula" href="http://localhost:8080/proyecto-cine/admin-peliculas">Peliculas</a>
      <a class="btn btn-secondary cargar-funcion" href="http://localhost:8080/proyecto-cine/admin-funciones">Funciones</a>
    </div>
  </div>

  <br>

  <div class="container formularios bg-light rounded" style="margin-bottom:25px;">

    <div class="formulario-cargar-funcion" style="padding: 1rem;">

      <c:if test="${not empty mens}">
        <h5 class="p-3 mb-2 bg-success text-white"> ${mens} </h5>
      </c:if>

      <form:form action="agregar-funcion" method="POST" modelAttribute="datosFuncion">
        <h4>Formulario Para Crear Funcion</h4>
        <hr class="colorgraph"><br>

		<form:label path="idCine">Seleccione el cine:</form:label>
        <form:select path="idCine" class="form-control">
          <c:forEach items="${listaCines}" var="cine">
            <form:option value="${cine.getId()}" label="id: ${cine.getId()} -nombre: ${cine.getNombreLocal()}" />
          </c:forEach>
        </form:select>
        <br>

        <form:label path="idSala">Seleccione la sala:</form:label>
        <form:select path="idSala" class="form-control">
          <c:forEach items="${listaSalas}" var="sala">
            <form:option value="${sala.getId()}" label="id: ${sala.getId()} -tipo de sala: ${sala.getTipo()} -de cine: ${sala.getCine().getNombreLocal()}" />
          </c:forEach>
        </form:select>
        <br>

        <form:label path="idPelicula">Seleccione la pelicula:</form:label>
        <form:select path="idPelicula" class="form-control">
          <c:forEach items="${listaPeliculas}" var="pelicula">
            <form:option value="${pelicula.getId()}" label="id: ${pelicula.getId()} -nombre: ${pelicula.getNombre()}" />
          </c:forEach>
        </form:select>
        <br>

        <form:label path="fechaHora">Introducir fecha:</form:label>
        <form:input path="fechaHora" type="date" id="start" name="trip-start" />
        <br><br>

        <form:label path="hora">Horario (Formato hora:minutos):</form:label>
        <form:input path="hora" type="hora" class="form-control" />
        <br>

        <form:label path="precioMayor">Precio para adultos (ARS$):</form:label>
        <form:input path="precioMayor" type="precioMayor" class="form-control" />
        <br>

        <form:label path="precioMenor">Precio para menores (ARS$):</form:label>
        <form:input path="precioMenor" type="precioMenor" class="form-control" />
        <br>

        <button class="btn btn-lg btn-primary btn-block" Type="Submit" />Cargar Función</button>
      </form:form>
    </div>

  </div>


  <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"
    integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous">
  </script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"
    integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous">
  </script>
</body>

</html>