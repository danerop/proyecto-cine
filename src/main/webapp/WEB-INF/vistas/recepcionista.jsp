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
<title>Recepci�n</title>
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
						<li class="nav-item"><a class="nav-link active" href="iniciorecepcionista">Inicio Recepci�n</a></li>

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
	
	<main>
		<br>

		<div class="container-fluid contenedorpago bg-white">
			<div class="row">
				<div class="col-6">
					<form id="formidboleto" action="validar-boleto?b=" method="POST">
						<label>Ingrese la ID del boleto:</label> 
						<input id="idboleto" class="form-control" /> <br>
						<button class="btn btn-primary" type="submit">Validar boleto</button>
					</form>
				</div>
			</div>
		</div>







	</main>

	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"
		integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"
		integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13"
		crossorigin="anonymous"></script>

	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script src="./js/bootstrap.min.js"></script>

	<script type="text/javascript">
		$(document).ready(function() {
			$("#idboleto").change(function() {
				var idBoleto;
				idBoleto = $("#idboleto").val();
				$("#formidboleto").attr('action','validar-boleto?b='+idBoleto);
			});
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