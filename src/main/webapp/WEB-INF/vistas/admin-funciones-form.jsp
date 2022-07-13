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
				<a class="navbar-brand" href="inicio">CineApp</a>

				<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>

				<div class="collapse navbar-collapse" id="navbarSupportedContent">
					<ul class="navbar-nav w-100 d-flex justify-content-evenly">
						<li class="nav-item"><a class="nav-link" href="admin">Inicio Administración</a></li>

						<li class="nav-item dropdown" style="display: flex;">
							<a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">${usuario.getNombre()}</a>
							<button type="button" class="btn btn-dark position-relative" data-bs-toggle="modal" data-bs-target="#exampleModal">
								<i class="bi bi-bell-fill text-secondary"></i>
								<c:if test="${notificaciones.size()>0}">
									<span class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">
										${notificaciones.size()} <span class="visually-hidden">unread messages</span>
									</span>
								</c:if>
							</button>

							<div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink" style="margin-top: 50px;">
								<a class="dropdown-item" href="cerrarSesion">Cerrar Session</a>
							</div>
						</li>
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

		<div class="formulario-cargar-funcion" style="padding: 1rem;">

			<form:form action="${elementoNuevo == true ? 'registrar-funcion-nueva' : 'actualizar-funcion-vieja?id='.concat(datosFuncion.getId())}" method="POST" modelAttribute="datosFuncion">
				<h4>Formulario Para Crear Funcion</h4>
				<hr class="colorgraph">
				<br>

				<form:label path="idCine">Seleccione el cine:</form:label>
				<form:select path="idCine" class="form-control">
					<c:forEach items="${listaCines}" var="cine">
						<form:option value="${cine.getId()}"
							label="id: ${cine.getId()} -nombre: ${cine.getNombreLocal()}" />
					</c:forEach>
				</form:select>
				<br>

				<form:label path="idSala">Seleccione la sala:</form:label>
				<form:select path="idSala" class="form-control">
					<c:forEach items="${listaSalas}" var="sala">
						<form:option value="${sala.getId()}"
							label="id: ${sala.getId()} -tipo de sala: ${sala.getTipo()} -de cine: ${sala.getCine().getNombreLocal()}" />
					</c:forEach>
				</form:select>
				<br>

				<form:label path="idPelicula">Seleccione la pelicula:</form:label>
				<form:select path="idPelicula" class="form-control">
					<c:forEach items="${listaPeliculas}" var="pelicula">
						<form:option value="${pelicula.getId()}"
							label="id: ${pelicula.getId()} -nombre: ${pelicula.getNombre()}" />
					</c:forEach>
				</form:select>
				<br>

				<form:label path="fechaHora">Introducir fecha:</form:label>
				<form:input path="fechaHora" type="date" id="start" name="trip-start" />
				<br>
				<br>

				<form:label path="hora">Horario (Formato hora:minutos):</form:label>
				<form:input path="hora" type="hora" class="form-control" />
				<br>

				<form:label path="precioMayor">Precio para adultos (ARS$):</form:label>
				<form:input path="precioMayor" type="precioMayor" class="form-control" />
				<br>

				<form:label path="precioMenor">Precio para menores (ARS$):</form:label>
				<form:input path="precioMenor" type="precioMenor" class="form-control" />
				<br>

				<c:if test="${not empty msgError}">
					<h5 class="p-3 mb-2 bg-danger text-white">${msgError}</h5>
				</c:if>

				<button class="btn btn-lg btn-primary btn-block" Type="Submit" >Enviar</button>
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