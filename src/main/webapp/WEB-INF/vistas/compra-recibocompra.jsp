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

<svg xmlns="http://www.w3.org/2000/svg" style="display: none;">
  <symbol id="check-circle-fill" fill="currentColor" viewBox="0 0 16 16">
    <path
		d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0zm-3.97-3.03a.75.75 0 0 0-1.08.022L7.477 9.417 5.384 7.323a.75.75 0 0 0-1.06 1.06L6.97 11.03a.75.75 0 0 0 1.079-.02l3.992-4.99a.75.75 0 0 0-.01-1.05z" />
  </symbol>


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
	<main>

		<c:if test="${nuevacompra!=null}">
			<div
				class="alert alert-success d-flex align-items-center alert-dismissible"
				role="alert">
				<svg class="bi flex-shrink-0 me-2" width="24" height="24" role="img"
					aria-label="Success:">
					<use xlink:href="#check-circle-fill" /></svg>
				<div>Gracias por su compra!</div>
				<button type="button" class="btn-close" data-bs-dismiss="alert"
					aria-label="Close"></button>
			</div>
		</c:if>



		<div class="container-fluid contenedorpago">
			<h1 class="text-center">RECIBO</h1>
			<div class="row">
				<div class="col-6">
					<h2>N�mero de boleto: ${boletoGenerado.getId()} </h2>
					<h4>Pelicula:
						${boletoGenerado.getFuncion().getPelicula().getNombre()}</h4>
					<h4>Fecha: ${boletoGenerado.getFuncion().getFechaHora() }</h4>
					<h4>Horario: ${boletoGenerado.getFuncion().getHora() }</h4>
					<h4>Cine:
						${boletoGenerado.getFuncion().getCine().getNombreLocal() }</h4>
					<h4>Sala: ${boletoGenerado.getFuncion().getSala().getId()} -
						${boletoGenerado.getFuncion().getSala().getTipo() }</h4>
					<c:if test="${!boletoGenerado.getFueAdquiridoConEntradaGratis()}">
					<h4>Precio: $${boletoGenerado.getPrecio()}</h4>
					</c:if>
					<h4>N�mero de butaca: ${boletoGenerado.getButaca().getId()}</h4>
					<c:if test="${boletoGenerado.getFueAdquiridoConEntradaGratis()}">
					<h4 class="text-success">�Este boleto fue adquirido con una entrada gratuita!</h4>
					</c:if>
					<br>
					<button class="btn btn-primary mx-auto d-block">Imprimir</button>
				</div>
				<div class="col-6">
					<img class="img-responsive w-50 mx-auto d-block" alt="codigoqr"
						src="http://api.qrserver.com/v1/create-qr-code/?data=http://localhost:8080/proyecto-cine/validar-boleto?b=${idBoleto}&size=500x500">
					<br>
					<h6 class="text-center">�Mostr� est� c�digo qr para entrar a
						la sala!</h6>
				</div>
			</div>

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