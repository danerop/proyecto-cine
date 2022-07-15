<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Compra Boleto</title>
<link rel="stylesheet" href="./css/bootstrap.min.css">
<link rel="stylesheet" href="./css/pago.css">
<link rel="stylesheet" href="./css/tabla-butacas.css">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
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
						<li class="nav-item"><a class="nav-link" href="inicio">Inicio</a></li>
						<li class="nav-item"><a class="nav-link" href="mapa">Mapa de cines</a></li>
						<li class="nav-item"><a class="nav-link" href="suscripcion">Suscripciones</a></li>
						<li>
							<form:form action="buscar" method="GET" class="d-flex" modelAttribute="datosBuscar">
								<form:input class="form-control me-2" path="busqueda" />
								<form:button class="btn btn-outline-success" type="submit">Buscar</form:button>
							</form:form>
						</li>

						<%
						if (request.getAttribute("usuario") != null) {
						%>
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
								<a class="dropdown-item" href="generos">Mis Generos Favoritos</a>
								<a class="dropdown-item" href="historialcompras">Mis Boletos</a>
								<a class="dropdown-item" href="cerrarSesion">Cerrar Session</a>
							</div>
						</li>
						<%
						} else {
						%>
						<li class="nav-item"><a class="nav-link" href="cerrarSesion">Iniciar Sesion</a></li>
						<%
						}
						%>
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
	
	<main class="container-fluid contenedorpago">

		<h3 class="text-center">Elegí tu butaca</h3>
		<form:form action="comprar-pago?p=${p}" method="POST"
			modelAttribute="datosCompraBoleto">


			<br>
			<div class="table-responsive container">
				<div class="btn-group container" role="group"
					aria-label="Basic example">
					<table class="table w-auto testo container">
						<tbody>
							<tr>
								<td>&nbsp;</td>
								<c:forEach var="h" begin="1" end="32">
									<td class="text-center p-2">${h}</td>
								</c:forEach>
							</tr>
							<c:forEach var="fil" begin="1" end="16">
								<tr>
									<td class="align-middle p-2" style="padding:2px; width:42px;">${fil}</td>
									<c:forEach var="col" begin="1" end="32">
										<td style="padding:2px; width:42px;">
											<c:forEach items="${butacas}" var="butaca">
												<c:if test="${butaca.getButaca().getNroUbicacion()==(col+(32*(fil-1)))}">
													<c:choose>
														<c:when test="${!butaca.getOcupada()}">
															<input type="radio" class="btn-check position-fixed"
																name="idButaca" id="btncheck${col+(32*(fil-1))}"
																autocomplete="off" value="${butaca.getButaca().getId()}">
															<form:label class="btn btn-outline-primary p-2" for="btncheck${col+(32*(fil-1))}" 
															path="idButaca" style="width:42px;">${butaca.getButaca().getNroUbicacion()}</form:label>
														</c:when>
														<c:otherwise>
															<button class="btn btn-primary p-2" style="width:42px;" disabled>${butaca.getButaca().getNroUbicacion()}</button>
														</c:otherwise>
													</c:choose>
												</c:if>
											</c:forEach>
										</td>
									</c:forEach>
								</tr>
							</c:forEach>

						</tbody>

					</table>
				</div>
			</div>
			<br>

			<input type="hidden" value="${datosCompraBoleto.getIdSala()}"
				id="tempidsala" name="idSala" path="idSala">
			<form:label for="tempidsala" path="idSala"></form:label>
			<input type="hidden" value="${datosCompraBoleto.getIdUsuario()}"
				id="tempidusuario" name="idUsuario" path="idUsuario">
			<form:label for="tempidusuario" path="idSala"></form:label>
			<input type="hidden" value="${datosCompraBoleto.getIdPelicula()}"
				id="tempidpelicula" name="idPelicula" path="idPelicula">
			<form:label for="tempidpelicula" path="idPelicula"></form:label>
			<input type="hidden" value="${datosCompraBoleto.getHora()}"
				id="temphora" name="hora" path="hora">
			<form:label for="temphora" path="hora"></form:label>
			<input type="hidden" value="${datosCompraBoleto.getFecha()}"
				id="tempfecha" name="fecha" path="fecha">
			<form:label for="tempfecha" path="fecha"></form:label>
			<input type="hidden" value="${datosCompraBoleto.getIdcine()}"
				id="tempidcine" name="idcine" path="idcine">
			<form:label for="tempidcine" path="idcine"></form:label>
			<input type="hidden" value="${datosCompraBoleto.getPrecio()}"
				id="tempprecio" name="precio" path="precio">
			<form:label for="tempprecio" path="precio"></form:label>

			<button type="button" class="btn btn-secondary" onclick="history.back()">Volver</button>
			<form:button type="button submit" class="btn btn-primary">Siguiente</form:button>

		</form:form>
	</main>


	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"
		integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB"
		crossorigin="anonymous"></script>
	<script src="./js/bootstrap.min.js"></script>
	
	<script src="js/jquery.min.js"></script>
	<script>
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