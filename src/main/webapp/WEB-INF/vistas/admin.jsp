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
      <button type="button" class="btn btn-secondary cargar-cine">Cargar Cine</button>
      <button type="button" class="btn btn-secondary cargar-sala">Cargar Sala</button>
      <button type="button" class="btn btn-secondary cargar-pelicula">Cargar Pelicula</button>
      <button type="button" class="btn btn-secondary cargar-funcion">Cargar Función</button>
    </div>
  </div>

  <br>

  <div class="container formularios bg-light rounded">
    <div class="formulario-cargar-cine" style="display: none; padding: 1rem;">
		
		<form:form action="agregar-cine" method="POST" modelAttribute="datosCine">
			<h4>Formulario Crear Cine</h4>
			<hr class="colorgraph"><br>
			
			<form:input path="nombreLocal" id="nombreLocal" type="nombreLocal" class="form-control" />
			<form:input path="direccion" id="direccion" type="direccion" class="form-control" />
			<form:input path="telefono" id="telefono" type="telefono" class="form-control" />
			<form:input path="email" id="email" type="email" class="form-control" />
			<form:input path="urlImagenCine" type="urlImagenCine" id="urlImagenCine" class="form-control"/>     		  
					
			<button class="btn btn-lg btn-primary btn-block" Type="Submit"/>Cargar Cine</button>
		</form:form>
		
		
		<br>
		<hr class="colorgraph"><br>
		<br>
		
		<form:form action="busqueda-cine" method="POST" modelAttribute="datosCine">
			<h4>Formulario buscar Cine</h4>
			<hr class="colorgraph"><br>
			
			<form:input path="id" type="id" class="form-control" />
			
			<button class="btn btn-lg btn-primary btn-block" Type="Submit"/>Buscar cine</button>
		</form:form>
		
		<c:if test="${not empty cinenombre}">
	        <h4><span>Cine Encontrado: ${cinenombre} dirección: ${cinedireccion}</span></h4>
		</c:if>
		
    </div>

	<div class="formulario-cargar-sala" style="display: none; padding: 1rem;">
      <h4>Formulario Crear Sala</h4>
    </div>

    <div class="formulario-cargar-pelicula" style="display: none; padding: 1rem;">
      <h4>Formulario Crear Película</h4>
    </div>

    <div class="formulario-cargar-funcion" style="display: none; padding: 1rem;">
      
      <form:form action="agregar-funcion" method="POST" modelAttribute="datosFuncion">
			<h4>Formulario Crear Funcion</h4>
			<hr class="colorgraph"><br>
			
			<form:input path="fechaHora" id="fechaHora" type="fechaHora" class="form-control" />
			<form:input path="precioMayor" id="precioMayor" type="precioMayor" class="form-control" />
			<form:input path="precioMenor" id="precioMenor" type="precioMenor" class="form-control" />
			
		
			<%--<label for="exampleFormControlSelect1">Example select</label> --%>
			<select class="form-control" id="exampleFormControlSelect1">
				
				<c:forEach items="${listaCines}" var="cine">
         			
         			<option>${cine.getNombreLocal()}</option>
         			
      			</c:forEach>
				
			</select>
			
			<button class="btn btn-lg btn-primary btn-block" Type="Submit"/>Cargar Cine</button>
		</form:form>
      
    </div>
  </div>


  <script src="js/jquery.min.js"></script>
  <script>
    //si se pulsa un botón, se muestran su formulario correspondiente.
    $(".cargar-cine").click(function () {
      $(".formularios>div").each(function () { $(this).hide(); });
      $(".formulario-cargar-cine").show();
    });
    $(".cargar-pelicula").click(function () {
      $(".formularios>div").each(function () { $(this).hide(); });
      $(".formulario-cargar-pelicula").show();
    });
    $(".cargar-funcion").click(function () {
      $(".formularios>div").each(function () { $(this).hide(); });
      $(".formulario-cargar-funcion").show();
    });
    $(".cargar-sala").click(function () {
      $(".formularios>div").each(function () { $(this).hide(); });
      $(".formulario-cargar-sala").show();
    });
  </script>

  <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"
    integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous">
  </script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"
    integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous">
  </script>
</body>

</html>