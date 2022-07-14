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



	<br>

	<div class="container formularios bg-light rounded"
		style="margin-bottom: 25px;">

		<div class="formulario-cargar-pelicula" style="padding: 1rem;">

			<c:if test="${not empty msgExito}">
				<h5 class="p-3 mb-2 bg-success text-white">${msgExito}</h5>
			</c:if>


			<%-- comienzo --%>
			<h2>Asignar generos a pelicula</h2>
			<form:form action="validarGenerosAsignados" method="POST"
				modelAttribute="datosAsignarGeneroPelicula">

				<div class="container">
					<h4 class="">Selecccione una película</h4>
					<div class="row">
						<div class="col-4">
							<!-- Acá estará la lista de los elementos registrados -->
							<div class="list-group" id="list-tab" role="tablist">
								<c:forEach items="${listaPeliculas}" var="pelicula">
									<form:radiobutton class="d-none" name="pelicula"
										id="peli-${pelicula.getId()}" path="idPelicula"
										value="${pelicula.getId()}" />

									<form:label path="idPelicula" for="peli-${pelicula.getId()}"
										class="list-group-item list-group-item-action templist"
										data-toggle="list" href="#list-peliculas${pelicula.getId()}"
										role="tab" value="${pelicula.getId()}">${pelicula.getNombre()} (${pelicula.getAnio()})</form:label>


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
													${pelicula.getDescripcion()} <br> Generos:
													<c:forEach items="${registrosGeneroPelicula}"
														var="registro">
														<c:if
															test="${pelicula.getId()==registro.getPelicula().getId() && registro.getActivo()}">
															<div class="d-inline-block rounded-3 p-1 bg-primary">${registro.getGenero().getNombre()}
																<a href="eliminargeneropelicula?g=${registro.getId()}"
																	type="button" class="btn-close enabled"
																	aria-label="Close"></a>
															</div>
														</c:if>

													</c:forEach>
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

				<%-- fin --%>


				<br>


				<div>
					<h4 class="">Seleccione géneros</h4>
					<br>
					<form:select id="generos" path="idsGeneros" multiple="multiple">
						<c:forEach items="${listaDeGeneros}" var="generos">
							<form:option value="${generos.getId()}">
           						  ${generos.getNombre()}
           					</form:option>
						</c:forEach>
					</form:select>
				</div>

				<button class="btn btn-lg btn-primary btn-block" Type="Submit" />Asignar generos</button>
			</form:form>
		</div>

	</div>


	<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>

	<script src="js/jquery.min.js"></script>
	<script>
		$('#list-tab label').on('click', function(e) {
			$(this).tab('show');
		});
		$('#navbarDropdownMenuLink').on('click', function(e) {
			e.preventDefault();
			if ($(".dropdown-menu").hasClass('show')) {
				$(".dropdown-menu").removeClass('show');
			} else {
				$(".dropdown-menu").addClass('show');
			}
		});
	</script>
</body>

</html>