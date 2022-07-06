<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Compra Boleto</title>
<link rel="stylesheet" href="./css/bootstrap.min.css">
<link rel="stylesheet" href="./css/pago.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
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
						<li class="nav-item"><a class="nav-link active" href="inicio">Inicio</a></li>
						<li class="nav-item"><a class="nav-link" href="mapa">Mapa
								de cines</a></li>
						<li class="nav-item"><a class="nav-link" href="suscripcion">Suscripciones</a></li>
						<li class="nav-item"><a class="nav-link" href="cerrarSesion">Iniciar
								Session</a></li>
						<%
						if (request.getAttribute("usuario") != null) {
						%>
						<li class="nav-item"><a class="nav-link"
							href="historialcompras">Mis Boletos</a></li>
						<li class="nav-item">
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
						</li>
						<%
						}
						%>

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
		<div class="container-fluid contenedorpago">
			<form:form action="comprar-confirmar?p=${p}" method="POST"
				modelAttribute="datosCompraBoleto">

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
				<input type="hidden" value="${datosCompraBoleto.getIdButaca()}"
					id="tempidbutaca" name="idButaca" path="idButaca">
				<form:label for="tempidbutaca" path="idButaca"></form:label>
				<input type="hidden" value="${datosCompraBoleto.getPrecio()}"
					id="tempprecio" name="precio" path="precio">
				<form:label for="tempprecio" path="precio"></form:label>

				<h1>Metodo pago</h1>
				<!-- aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa -->


				<c:if
					test="${user.getSuscripcion().getDetalleSuscripcion().getId()!=null && user.getSuscripcion().getDetalleSuscripcion().getId()!=1}">
					<c:choose>
						<c:when
							test="${user.getSuscripcion().getCantidadDeBoletosGratisRestante()>0}">
							<form:button name="ep" value="true" type="button submit"
								class="btn btn-primary">
								Usar entrada gratis <span class="badge bg-success" 
									data-bs-toggle="tooltip" data-bs-placement="right"
									title="¡Aún te quedan ${user.getSuscripcion().getCantidadDeBoletosGratisRestante()} entradas gratis por usar!">${user.getSuscripcion().getCantidadDeBoletosGratisRestante()}</span>

							</form:button>
						</c:when>
						<c:otherwise>

							<button type="button" class="btn btn-primary" disabled>
								Usar entrada gratis 
							</button> <span class="badge bg-secondary" 
									data-bs-toggle="tooltip" data-bs-placement="right"
									title="¡Ya usaste todas tus entradas gratis del mes!"><i
									class="bi bi-exclamation-diamond-fill"></i></span>

						</c:otherwise>
					</c:choose>

				</c:if>


				<!-- zzzzzzzzzzzzzzzzzzzzzzzzzz -->

				<div class="d-flex justify-content-center btncompraboleto">
					<button type="button" class="btn btn-secondary" onclick="history.back()">Volver</button>
					<form:button type="button submit" class="btn btn-primary">Siguiente</form:button>
				</div>
		</div>


		</form:form>


		</div>




	</main>

	<footer> </footer>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"
		integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"
		integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13"
		crossorigin="anonymous">
		
	</script>
	<script src="./js/bootstrap.min.js"></script>
	<script src="./js/pago.js"></script>

</body>

</html>