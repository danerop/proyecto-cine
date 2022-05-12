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
      <a class="btn btn-secondary cargar-cine" href="http://localhost:8080/proyecto-cine/admin?sel=cargar-cine">Cargar Cine</a>
      <a class="btn btn-secondary cargar-sala" href="http://localhost:8080/proyecto-cine/admin?sel=cargar-sala">Cargar Sala</a>
      <a class="btn btn-secondary cargar-pelicula" href="http://localhost:8080/proyecto-cine/admin?sel=cargar-pelicula">Cargar Pelicula</a>
      <a class="btn btn-secondary cargar-funcion" href="http://localhost:8080/proyecto-cine/admin?sel=cargar-funcion">Cargar Función</a>
    </div>
  </div>

  <br>

  <div class="container formularios bg-light rounded" style="margin-bottom:25px;">
  
   	<c:if test="${seleccion == 'cargar-cine'}">
      <div class="formulario-cargar-cine" style=" padding: 1rem;">
		
		<form:form action="agregar-cine" method="POST" modelAttribute="datosCine">
			<h4>Formulario Para Crear Cine</h4>
			<hr class="colorgraph"><br>
			
			<form:label path="nombreLocal">Nombre Del Local:</form:label>
			<form:input path="nombreLocal" id="nombreLocal" type="nombreLocal" class="form-control" />
			<br>
			<form:label path="direccion">Dirección:</form:label>
			<form:input path="direccion" id="direccion" type="direccion" class="form-control" />
			<br>
			<form:label path="telefono">Telefono:</form:label>
			<form:input path="telefono" id="telefono" type="telefono" class="form-control" />
			<br>
			<form:label path="email">Email:</form:label>
			<form:input path="email" id="email" type="email" class="form-control" />
			<br>
			<form:label path="urlImagenCine">Url de la portada:</form:label>
			<form:input path="urlImagenCine" type="urlImagenCine" id="urlImagenCine" class="form-control"/>     		  
			<br>
			<button class="btn btn-lg btn-primary btn-block" Type="Submit"/>Cargar Cine</button>
		</form:form>
		
		<%--
		<br>
		<hr class="colorgraph"><br>
		<br>
		
		<form:form action="busqueda-cine" method="POST" modelAttribute="datosCine">
			<h4>Formulario buscar Cine</h4>
			<hr class="colorgraph"><br>
			
			<form:input path="id" type="id" class="form-control" />
			
			<input type="submit" />
		</form:form>
		
		<c:if test="${not empty cinenombre}">
	        <h4><span>Cine Encontrado: ${cinenombre} dirección: ${cinedireccion}</span></h4>
		</c:if>
		--%>
      </div>
    </c:if>

	<c:if test="${seleccion == 'cargar-sala'}">
	  <div class="formulario-cargar-sala" style="padding: 1rem;">
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
	</c:if>
	
	<c:if test="${seleccion == 'cargar-pelicula'}">
      <div class="formulario-cargar-pelicula" style="padding: 1rem;">
      <form:form action="agregar-pelicula" method="POST" modelAttribute="datosPelicula">
			<h4>Formulario Para Crear Pelicula</h4>
			<hr class="colorgraph"><br>
			
			<form:label path="nombre">Nombre:</form:label>
			<form:input path="nombre" id="nombre" type="nombre" class="form-control" />
			<br>
			<form:label path="anio">Año:</form:label>
			<form:input path="anio" id="anio" type="anio" class="form-control" />
			<br>
			<form:label path="descripcion">Descripción:</form:label>
			<form:input path="descripcion" id="descripcion" type="descripcion" class="form-control" />
			<br>
			<form:label path="duracion">Duración:</form:label>
			<form:input path="duracion" id="duracion" type="duracion" class="form-control" />
			<br>
			<form:label path="urlImagenPelicula">Url de la portada:</form:label>
			<form:input path="urlImagenPelicula" type="urlImagenPelicula" id="urlImagenPelicula" class="form-control"/>     		  
			<br>
			<button class="btn btn-lg btn-primary btn-block" Type="Submit"/>Cargar Pelicula</button>
		</form:form>
      </div>
	</c:if>
	
	<c:if test="${seleccion == 'cargar-funcion'}">
      <div class="formulario-cargar-funcion" style="padding: 1rem;">
        <form:form action="agregar-funcion" method="POST" modelAttribute="datosFuncion">
			<h4>Formulario Para Crear Funcion</h4>
			<hr class="colorgraph"><br>
			
			<%-- Selector de fecha de función --%>
			<form:label path="fechaHora">Introducir fecha:</form:label>
			<form:input path="fechaHora" type="date" id="start" name="trip-start"/>
			<br><br>
			<form:label path="precioMayor">Precio para adultos (ARS$):</form:label>
			<form:input path="precioMayor" type="precioMayor" class="form-control" />
			<br>
			<form:label path="precioMenor">Precio para menores (ARS$):</form:label>
			<form:input path="precioMenor" type="precioMenor" class="form-control" />
			
			<br>
			<%-- De momento va a ser así, pero en el final el selector de sala mostrará solo las salas del cine--%>
			<form:label path="idCine">Seleccione el cine:</form:label>
			<form:select path="idCine" class="form-control">
				<c:forEach items="${listaCines}" var="cine">
         			<form:option value="${cine.getId()}" label="id: ${cine.getId()} -nombre: ${cine.getNombreLocal()}"/>
      			</c:forEach>
			</form:select>
			<br>
			<form:label path="idSala">Seleccione la sala:</form:label>
			<form:select path="idSala" class="form-control">
				<c:forEach items="${listaSalas}" var="sala">
         			<form:option value="${sala.getId()}" label="id: ${sala.getId()} -tipo de sala: ${sala.getTipo()} -de cine: ${sala.getCine().getNombreLocal()}"/>
      			</c:forEach>
			</form:select>
			<br>
			<form:label path="idPelicula">Seleccione la pelicula:</form:label>
			<form:select path="idPelicula" class="form-control">
				<c:forEach items="${listaPeliculas}" var="pelicula">
         			<form:option value="${pelicula.getId()}" label="id: ${pelicula.getId()} -nombre: ${pelicula.getNombre()}"/>
      			</c:forEach>
			</form:select>
			<br>
			<button class="btn btn-lg btn-primary btn-block" Type="Submit"/>Cargar Función</button>
		</form:form>
      </div>
    </c:if>
    
  </div>


  <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"
    integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous">
  </script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"
    integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous">
  </script>
</body>

</html>