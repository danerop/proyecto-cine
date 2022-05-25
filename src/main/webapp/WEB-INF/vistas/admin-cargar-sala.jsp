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
            <a class="nav-link" href="peliculas">Pel�culas</a>
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
      <a class="btn btn-secondary cargar-cine" href="http://localhost:8080/proyecto-cine/admin-cargar-cine">Cargar Cine</a>
      <a class="btn btn-secondary cargar-sala" href="http://localhost:8080/proyecto-cine/admin-cargar-sala">Cargar Sala</a>
      <a class="btn btn-secondary cargar-pelicula" href="http://localhost:8080/proyecto-cine/admin-cargar-pelicula">Cargar Pelicula</a>
      <a class="btn btn-secondary cargar-funcion" href="http://localhost:8080/proyecto-cine/admin-cargar-funcion">Cargar Funci�n</a>
    </div>
  </div>

  <br>

  <div class="container formularios bg-light rounded" style="margin-bottom:25px;">
  
	  <div class="formulario-cargar-sala" style="padding: 1rem;">
	  
	    <c:if test="${not empty mens}">
  	  		<h5 class="p-3 mb-2 bg-success text-white"> ${mens} </h5>
  	  	</c:if>
	  
      	<form:form action="agregar-sala" method="POST" modelAttribute="datosSala">
	    	<h4>Formulario Para Crear Sala</h4>
			<hr class="colorgraph"><br>
			
			<form:label path="idCine">Seleccione el cine:</form:label>
			<form:select path="idCine" class="form-control">
				<c:forEach items="${listaCines}" var="cine">
         			<form:option value="${cine.getId()}" label="id: ${cine.getId()} nombre: ${cine.getNombreLocal()}"/>
      			</c:forEach>
			</form:select>
			<br>
			<form:label path="tipo">Seleccione el tipo de Sala:</form:label>
			<form:select path="tipo" class="form-control">
				<form:option value="1" label="Comun"/>
				<form:option value="2" label="Sala3D"/>
				<form:option value="3" label="Sala4D"/>
				<form:option value="4" label="GoldenClass"/>
			</form:select>
			<br>
			<button class="btn btn-lg btn-primary btn-block" Type="Submit"/>Cargar Sala</button>
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