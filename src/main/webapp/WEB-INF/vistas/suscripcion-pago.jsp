<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="ISO-8859-1">
<link href="css/pago-suscripcion.css" rel="stylesheet">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
<title>Confirmar pago</title>
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
						<li class="nav-item"><a class="nav-link active" href="suscripcion">Suscripciones</a></li>
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
	
	<br>
	<br>
	<main>
		<div class="wrapper">
			<h4 class="text-uppercase">Detalles del pago</h4>
			<form:form class="form mt-4" action="procesarSuscripcion?d=${d}" method="POST" modelAttribute="datosSuscripcion">

				<input type="hidden" value="${datosSuscripcion.getIdDetalleSuscripcion()}" id="tempiddetallesuscripcion" name="idDetalleSuscripcion" path="idDetalleSuscripcion">
				<form:label for="tempiddetallesuscripcion" path="idDetalleSuscripcion"></form:label>

				<div class="form-group">
					<label for="name" class="text-uppercase">Nombre del titular</label>
					<input type="text" class="form-control" placeholder="Nicolos Flemming">
				</div>
				<div class="form-group">
					<label for="card" class="text-uppercase">Número de la tarjeta</label>
					<div class="card-number">
						<input type="text" class="card-no" step="4" placeholder="1234 4567 5869 1234" pattern="^[0-9].{15,}">
						<span class="">
							<img src="https://www.freepnglogos.com/uploads/mastercard-png/mastercard-marcus-samuelsson-group-2.png" alt="" width="30" height="30">
						</span>
					</div>
				</div>
				<div class="d-flex w-100">
					<div class="d-flex w-50 pr-sm-4">
						<div class="form-group">
							<label for="expiry" class="text-uppercase">Fecha de expiración</label>
							<input type="text" class="form-control" placeholder="03/2020">
						</div>
					</div>
					<div class="d-flex w-50 pl-sm-5 pl-3">
						<div class="form-group">
							<label for="cvv">CVV</label>
							<a href="#" title="CVV is a credit or debit card number, the last three digit number printed on the signature panel">¿Que es esto?</a>
							<input type="password" class="form-control pr-5" maxlength="3" placeholder="123">
						</div>
					</div>
				</div>
				<div class="form-inline pt-sm-3 pt-2">
					<input type="checkbox" name="address" id="address">
					<label for="address" class="px-sm-1 pl-1 pt-sm-0 pt-2">Mi dirección de facturación es la misma que la de envío</label>
				</div>
				<div class="form-inline py-sm-2">
					<input type="checkbox" name="details" id="details">
					<label for="details" class="px-sm-2 pl-2 pt-sm-0 pt-2">Guardar datos para futuras compras</label>
				</div>
				<div class="my-3">
					<!-- <input type="submit" class="text-uppercase btn btn-primary btn-block p-3" value="Suscribirse ${servicioElegido.tipo}"> -->
					<form:button type="button submit" class="text-uppercase btn btn-primary btn-block p-3">Suscribirse ${servicioElegido.getTipo()}</form:button>
				</div>
				<div class="py-sm-2 total">
					<p>TOTAL: ${servicioElegido.getCuota()}</p>
				</div>
				<div id="form-footer">
					<p>Al realizar su pedido, usted acepta nuestro</p>
					<p>
						<a href="#">aviso de privacidad</a> y <a href="#">términos de uso</a>.
					</p>
				</div>
				
			</form:form>
		</div>
	</main>
	<br>

	<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
		
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