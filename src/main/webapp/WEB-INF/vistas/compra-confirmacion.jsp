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
							aria-current="page" href="inicio">Inicio</a></li>
						<li class="nav-item"><a class="nav-link" href="mapa">Mapa
								de cines</a></li>
						<li class="nav-item"><a class="nav-link" href="suscripcion">Suscripciones</a>
						</li>
						<li class="nav-item"><a class="nav-link"
							href="historialcompras">Mis Boletos</a></li>
						<li class="nav-item"><a class="nav-link" href="cerrarSesion">Iniciar
								Session</a></li>
						<li>
							<form class="d-flex">
								<input class="form-control me-2" type="search"
									placeholder="Search" aria-label="Search">
								<button class="btn btn-outline-success" type="submit">Search</button>
							</form>
						</li>
						<%
						if (request.getAttribute("usuario") != null) {
						%>
						<li>
							<button type="button" class="btn btn-dark position-relative"
								data-bs-toggle="modal" data-bs-target="#exampleModal">
								<i class="bi bi-bell-fill text-secondary"></i> <span
									class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">
									99+ <span class="visually-hidden">unread messages</span>
								</span>
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

	<main>
		<div class="container-fluid contenedorpago">
			<form:form action="validar-compra?p=${p}" method="POST"
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

				<div id="confirmacion">
					<h1>Confirmación</h1>

					<div class="row">
						<div class="col-3">
							<img alt="imgPelicula"
								src="${funcionElegida.getPelicula().getUrlImagenPelicula()}">
						</div>
						<div class="col-9">
							<h2>Pelicula:
								${boletoGenerado.getFuncion().getPelicula().getNombre()}</h2>
							<h2>Fecha: ${boletoGenerado.getFuncion().getFechaHora() }</h2>
							<h2>Horario: ${boletoGenerado.getFuncion().getHora() }</h2>
							<h2>Cine:
								${boletoGenerado.getFuncion().getCine().getNombreLocal() }</h2>
							<h2>Sala: ${boletoGenerado.getFuncion().getSala().getId()} -
								${boletoGenerado.getFuncion().getSala().getTipo() }</h2>
							<c:if test="${!usoEntradaGratis}">
								<h2>Precio: $${boletoGenerado.getPrecio()}</h2>
							</c:if>
							<h2>Número de butaca: ${boletoGenerado.getButaca().getId()}</h2>
							<c:if test="${usoEntradaGratis}">
								<h2 class="text-success">¡Se usara una de tus entradas gratis disponibles!</h2>
							</c:if>
						</div>
					</div>


					<div class="d-flex justify-content-center btncompraboleto">
						<button type="button" class="btn btn-secondary" onclick="history.back()">Volver</button>


						<c:choose>
							<c:when test="${usoEntradaGratis }">
								<form:button name="ep" value="true" type="button submit"
									class="btn btn-primary">
								Usar entrada gratis <span class="badge bg-success"
										data-bs-toggle="tooltip" data-bs-placement="right"
										title="¡Aún te quedan ${user.getSuscripcion().getCantidadDeBoletosGratisRestante()} entradas gratis por usar!">${user.getSuscripcion().getCantidadDeBoletosGratisRestante()}</span>

								</form:button>
							</c:when>
							<c:otherwise>
								<form:button type="button submit" class="btn btn-primary">Comprar</form:button>
							</c:otherwise>
						</c:choose>


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
	<script src="./js/bootstrap.min.js"></script>
	<script src="./js/pago.js"></script>



</body>

</html>