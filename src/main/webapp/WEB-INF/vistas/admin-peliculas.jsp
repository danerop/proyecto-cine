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
						<li class="nav-item"><a class="nav-link active"
							aria-current="page" href="admin">Inicio Administración</a></li>
						<li class="nav-item"><a class="nav-link" href="suscripcion">Suscripciones</a>
						</li>
						<li class="nav-item"><a class="nav-link" href="cerrarSesion">Iniciar
								Session</a></li>
					</ul>
					<form class="d-flex">
						<input class="form-control me-2" type="search"
							placeholder="Search" aria-label="Search">
						<button class="btn btn-outline-success" type="submit">Search</button>
					</form>
				</div>
			</div>
		</nav>
	</header>

	<br>
	<h3 class="text-center">Controles Administrador</h3>
	<br>

	<div class="btn-toolbar justify-content-center" role="toolbar"
		aria-label="Toolbar with button groups">
		<div class="btn-group mr-2" role="group">
			<a class="btn btn-secondary cargar-cine"
				href="http://localhost:8080/proyecto-cine/admin-cines">Cines</a> <a
				class="btn btn-secondary cargar-sala"
				href="http://localhost:8080/proyecto-cine/admin-salas">Salas</a> <a
				class="btn btn-secondary cargar-pelicula"
				href="http://localhost:8080/proyecto-cine/admin-peliculas">Peliculas</a>
			<a class="btn btn-secondary cargar-funcion"
				href="http://localhost:8080/proyecto-cine/admin-funciones">Funciones</a>
			<a class="btn btn-secondary cargar-suscripcion"
				href="http://localhost:8080/proyecto-cine/admin-suscripciones">Suscripciones</a>
			<a class="btn btn-secondary cargar-notificacion"
				href="http://localhost:8080/proyecto-cine/admin-notificaciones">Notificación</a>
			<a class="btn btn-secondary cargar-notificacion"
				href="http://localhost:8080/proyecto-cine/admin-asignargeneros">Asignar
				Géneros</a>
		</div>
	</div>

	<br>

	<div class="container">
		<h4 class="text-white">
			Lista de Películas
			</h5>
			<div class="row">
				<div class="col-4">
					<!-- Acá estará la lista de los elementos registrados -->
					<div class="list-group" id="list-tab" role="tablist">
						<c:forEach items="${listaPeliculas}" var="pelicula">
							<a class="list-group-item list-group-item-action"
								data-toggle="list" href="#list-peliculas${pelicula.getId()}"
								role="tab">${pelicula.getNombre()} (${pelicula.getAnio()})</a>
						</c:forEach>
					</div>
				</div>
				<div class="col-8">
					<!-- Acá va a aparecer el detalle -->
					<div class="tab-content">
						<c:forEach items="${listaPeliculas}" var="pelicula">
							<div class="tab-pane fade p-3 mb-2 bg-white rounded"
								id="list-peliculas${pelicula.getId()}" role="tabpanel">
								<div class="row">
									<div class="col-8">
										<span style="font-size: small; font-weight: lighter;">id:${pelicula.getId()}</span>
										<span style="font-size: large; font-weight: bold;">${pelicula.getNombre()}</span>
										<br>
										<p>
											año: ${pelicula.getAnio()} <br> Duración:
											${pelicula.getDuracion()} min <br> Descripción:
											${pelicula.getDescripcion()} <br>
										</p>
									</div>
									<div class="col-4">
										<img class="img-fluid"
											src="${pelicula.getUrlImagenPelicula()}"
											alt="imagen de pelicula">
									</div>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
	</div>

	<br>

	<div class="container formularios bg-light rounded"
		style="margin-bottom: 25px;">

		<div class="formulario-cargar-pelicula" style="padding: 1rem;">

			<c:if test="${not empty msgExito}">
				<h5 class="p-3 mb-2 bg-success text-white">${msgExito}</h5>
			</c:if>

			<form:form action="agregar-pelicula" method="POST"
				modelAttribute="datosPelicula">
				<h4>Formulario Para Crear Pelicula</h4>
				<hr class="colorgraph">
				<br>

				<form:label path="nombre">Nombre:</form:label>
				<form:input path="nombre" id="nombre" type="nombre"
					class="form-control" />
				<br>
				<form:label path="anio">Año:</form:label>
				<form:input path="anio" id="anio" type="anio" class="form-control" />
				<br>
				<form:label path="descripcion">Descripción:</form:label>
				<form:input path="descripcion" id="descripcion" type="descripcion"
					class="form-control" />
				<br>
				<form:label path="duracion">Duración:</form:label>
				<form:input path="duracion" id="duracion" type="duracion"
					class="form-control" />
				<br>
				<form:label path="urlImagenPelicula">Url de la portada:</form:label>
				<form:input path="urlImagenPelicula" type="urlImagenPelicula"
					id="urlImagenPelicula" class="form-control" />
				<br>

				<c:if test="${not empty msgError}">
					<h5 class="p-3 mb-2 bg-danger text-white">${msgError}</h5>
				</c:if>

				<button class="btn btn-lg btn-primary btn-block" Type="Submit" />Cargar Pelicula</button>
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
	<script src="js/jquery.min.js"></script>
	<script>
		$('#list-tab a').on('click', function(e) {
			e.preventDefault()
			$(this).tab('show')
		})
	</script>
</body>

</html>