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
<title>Controles Administrador</title>
</head>

<body>
	<header>
		<nav class="navbar navbar-expand-lg navbar-dark bg-dark ">
			<div class="container-fluid">
				<!--  <div class="d-flex justify-content-evenly">...</div> -->
				<a class="navbar-brand" href="inicio">LOGO</a>
				<button class="navbar-toggler" type="button"
					data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
					aria-controls="navbarSupportedContent" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse" id="navbarSupportedContent">
					<ul class="navbar-nav w-100 d-flex justify-content-evenly">
						<li class="nav-item"><a class="nav-link active" aria-current="page" href="admin">Inicio Administración</a></li>
						<li class="nav-item"><a class="nav-link" href="cerrarSesion">Cerrar Session</a></li>
					</ul>
				</div>
			</div>
		</nav>
	</header>

	<br>
	<h3 class="text-center">Controles Administrador</h3>
	<br>

  <div class="btn-toolbar justify-content-center" role="toolbar" aria-label="Toolbar with button groups">
    <div class="btn-group mr-2" role="group">
      <a class="btn btn-secondary cargar-cine" href="http://localhost:8080/proyecto-cine/admin-cines">Cines</a>
      <a class="btn btn-secondary cargar-sala" href="http://localhost:8080/proyecto-cine/admin-salas">Salas</a>
      <a class="btn btn-secondary cargar-pelicula" href="http://localhost:8080/proyecto-cine/admin-peliculas">Películas</a>
      <a class="btn btn-secondary cargar-funcion" href="http://localhost:8080/proyecto-cine/admin-funciones">Funciones</a>
      <a class="btn btn-secondary cargar-suscripcion" href="http://localhost:8080/proyecto-cine/admin-suscripciones">Suscripciones</a>
      <a class="btn btn-secondary cargar-notificacion" href="http://localhost:8080/proyecto-cine/admin-notificaciones">Notificación</a>
      <a class="btn btn-secondary cargar-notificacion" href="http://localhost:8080/proyecto-cine/admin-recepcionistas">Recepcionistas</a>
	  <a class="btn btn-secondary cargar-notificacion" href="http://localhost:8080/proyecto-cine/admin-asignargeneros">Asignar Géneros</a>
    </div>
  </div>

	<br>

	<div class="container formularios bg-light rounded" style="margin-bottom: 25px;">
		<div class="formulario-cargar-sala" style="padding: 1rem;">

			<form:form action="registrar-recepcionista-nuevo" method="POST" modelAttribute="datosRecepcionista">
				<h4>Formulario Para Crear Recepcionista</h4>
				<hr class="colorgraph">
				<br>

				<form:label path="email">Email:</form:label>
				<form:input path="email" id="email" type="email" class="form-control" />
				<br>
				<form:label path="password">password:</form:label>
				<form:input path="password" id="password" type="password" class="form-control" />

				<c:if test="${not empty msgError}">
					<h5 class="p-3 mb-2 bg-danger text-white">${msgError}</h5>
				</c:if>

				<button class="btn btn-lg btn-primary btn-block" Type="Submit">Enviar</button>
			</form:form>

		</div>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"
		integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB"
		crossorigin="anonymous">
		
	</script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"
		integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13"
		crossorigin="anonymous">
		
	</script>
</body>

</html>