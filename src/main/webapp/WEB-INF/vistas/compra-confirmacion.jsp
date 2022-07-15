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
		<div class="container-fluid contenedorpago">


			<div id="confirmacion">
				<h1>Confirmación</h1>

				<div class="row">
					<div class="col-3">
						<img class="img-fluid" alt="imgPelicula" src="${funcionElegida.getPelicula().getUrlImagenPelicula()}">
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

						<h2>Precio: $${boletoGenerado.getPrecio()}</h2>

						<h2>Número de butaca: ${boletoGenerado.getButaca().getId()}</h2>
						<%-- 						<c:if test="${usoEntradaGratis}">
								<h2 class="text-success">¡Se usara una de tus entradas
									gratis disponibles!</h2>
							</c:if> --%>
					</div>
				</div>


				<div class="d-flex justify-content-center btncompraboleto">
					<button type="button" class="btn btn-secondary"
						onclick="history.back()">Volver</button>


					<c:if
						test="${user.getSuscripcion().getDetalleSuscripcion().getId()!=null && user.getSuscripcion().getDetalleSuscripcion().getId()!=1}">
						<c:choose>
							<c:when
								test="${user.getSuscripcion().getCantidadDeBoletosGratisRestante()>0}">
								<a href="validar-compra?ep=true" class="btn btn-primary">
									Usar entrada gratis <span class="badge bg-success"
									data-bs-toggle="tooltip" data-bs-placement="right"
									title="¡Aún te quedan ${user.getSuscripcion().getCantidadDeBoletosGratisRestante()} entradas gratis por usar!">${user.getSuscripcion().getCantidadDeBoletosGratisRestante()}</span>
								</a>

							</c:when>
							<c:otherwise>

								<button type="button" class="btn btn-primary" disabled>
									Usar entrada gratis</button>
								<span class="badge bg-secondary" data-bs-toggle="tooltip"
									data-bs-placement="right"
									title="¡Ya usaste todas tus entradas gratis del mes!"><i
									class="bi bi-exclamation-diamond-fill"></i></span>

							</c:otherwise>
						</c:choose>

					</c:if>

					<div class="botonmp"></div>


				</div>



				<div class="d-none preferencia">${producto}</div>
	</main>

	<footer> </footer>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"
		integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB"
		crossorigin="anonymous"></script>
	<script src="./js/bootstrap.min.js"></script>
	<script src="./js/pago.js"></script>


	<script src="https://sdk.mercadopago.com/js/v2"></script>
	<script>
		let idpref = $(".preferencia").html();
		// Agrega credenciales de SDK
		const mp = new MercadoPago("TEST-3e3a930f-e8b4-48d7-98f2-adc0ad1e336d",
				{
					locale : "es-AR",
				});
		// Inicializa el checkout
		mp.checkout({
			preference : {
				id : idpref,
			},
			render : {
				container : ".botonmp", // Indica el nombre de la clase donde se mostrará el botón de pago
				label : "Comprar", // Cambia el texto del botón de pago (opcional)
			},
		});
	</script>
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