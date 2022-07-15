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
<link rel="stylesheet" href="./css/pago.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
<title>Home</title>
</head>

<body>
	<header>
		<nav class="navbar navbar-expand-lg navbar-dark bg-dark ">
			<div class="container-fluid">

				<a class="navbar-brand" href="inicio">CineApp</a>

				<button class="navbar-toggler" type="button"
					data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
					aria-controls="navbarSupportedContent" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>

				<div class="collapse navbar-collapse" id="navbarSupportedContent">
					<ul class="navbar-nav w-100 d-flex justify-content-evenly">
						<li class="nav-item"><a class="nav-link active"
							href="iniciorecepcionista">Inicio Recepciï¿½n</a></li>

						<li class="nav-item dropdown" style="display: flex;"><a
							class="nav-link dropdown-toggle" href="#"
							id="navbarDropdownMenuLink" role="button" data-toggle="dropdown"
							aria-haspopup="true" aria-expanded="false">${usuario.getNombre()}</a>
							<button type="button" class="btn btn-dark position-relative"
								data-bs-toggle="modal" data-bs-target="#exampleModal">
								<i class="bi bi-bell-fill text-secondary"></i>
								<c:if test="${notificaciones.size()>0}">
									<span
										class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">
										${notificaciones.size()} <span class="visually-hidden">unread
											messages</span>
									</span>
								</c:if>
							</button>

							<div class="dropdown-menu"
								aria-labelledby="navbarDropdownMenuLink"
								style="margin-top: 50px;">
								<a class="dropdown-item" href="cerrarSesion">Cerrar Session</a>
							</div></li>
					</ul>

				</div>
			</div>
		</nav>
	</header>

	<!-- Modal -->
	<div class="modal fade" id="exampleModal" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Notificaciones</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
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
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">Cerrar</button>
				</div>
			</div>
		</div>
	</div>
	<!-- Modal -->
	<main>
		<br>
		<%
		if (request.getAttribute("usuario") != null) {
		%>
		<h2 class="text-center text-light mb-4">Bienvenido,
			${usuario.email}.</h2>
		<h2 class="text-center text-light mb-4">Rol:
			${usuario.getRol().getNombre()}.</h2>
		<%
		}
		%>

		<div class="container-fluid contenedorpago bg-white">

			<c:if test="${funcionesdehoy==null || funcionesdehoy.size()==0}">
				<h1 class="text-danger text-center m-5">
					<i class="bi bi-x-circle-fill display-1"></i> No se han encontrado
					funciones para el día de hoy.
				</h1>
			</c:if>

			<c:if test="${funcionesdehoy!=null && funcionesdehoy.size()>0}">
				<c:forEach items="${funcionesdehoy}" var="funcion">
					<div class="row mb-3">
						<div class="col-6">
							<img class="img-fluid" alt="peli"
								src="${funcion.getPelicula().getUrlImagenPelicula()}">
						</div>
						<div class="col-6">
							<h2>Pelicula: ${funcion.getPelicula().getNombre()}</h2>
							<h2>
								Fecha: <br> ${funcion.getFechaHora() }
							</h2>
							<h2>Horario: ${funcion.getHora() }</h2>
							<h2>Cine: ${funcion.getCine().getNombreLocal() }</h2>
							<h2>Sala Id: ${funcion.getSala().getId()}</h2>
							<h2>Tipo de sala: ${funcion.getSala().getTipo() }</h2>
							<a class="btn btn-primary mt-4"
								href="asociarfuncionautomatica?f=${funcion.getId()}">Seleccionar</a>
						</div>
						<hr class="mt-4">
					</div>
				</c:forEach>
			</c:if>


		</div>







	</main>

	<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>

	<script src="./js/bootstrap.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>

	<script src="js/jquery.min.js"></script>


</body>
</html>