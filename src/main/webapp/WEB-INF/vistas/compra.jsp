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
			<form:form action="comprar-tipoentrada?p=${p}" method="POST"
				modelAttribute="datosCompraBoleto">
				<div id="fecha-horario" class="collapse primersig">
					<h1 class="text-center">Compra</h1>

					<label for="selectcine">
						<h4 class="upspace">Seleccione un cine</h4>
					</label>
					<form:select id="selectcine" class="form-select"
						aria-label="Default select example" path="idcine">
						<form:option path="idcine" value="-1">Seleccione cine</form:option>
						<c:forEach items="${cinesDisponibles}" var="cines">
							<form:option path="idcine" value="${cines.getId()}">${cines.getNombreLocal()}</form:option>
						</c:forEach>

					</form:select>

					<h4 class="upspace">Seleccione fecha y sala:</h4>
					<div class="btn-group container-fluid" role="group"
						aria-label="Button group with nested dropdown">
						<div class="row container-fluid">


							<c:forEach items="${funcionesDisponibles}" var="fechasMostrar">

								<input type="radio"
									class="btn-check fechas fechas-${fechasMostrar.getCine().getId()}"
									name="fecha" id="fecha-${fechasMostrar.getId()}"
									autocomplete="off" value="${fechasMostrar.getFechaHora()}"
									path="fecha">
								<form:label
									class="btn btn-outline-primary col fechas fechas-${fechasMostrar.getCine().getId()}"
									for="fecha-${fechasMostrar.getId()}"
									value="${fechasMostrar.getFechaHora()}" path="fecha">${fechasMostrar.getFechaHora()} <br>
									<span id="idSala">${fechasMostrar.getSala().getId()}</span>-${fechasMostrar.getSala().getTipo()}</form:label>


							</c:forEach>

						</div>
					</div>

					<h4 class="upspace">Horarios</h4>


					<div class="btn-group container-fluid" role="group"
						aria-label="Button group with nested dropdown">
						<div class="container-fluid row">

							<c:forEach items="${funcionesDisponibles}" var="horarios">

								<input type="radio"
									class="btn-check horarios ${horarios.getFechaHora()}-${horarios.getSala().getId()}"
									name="hora" id="horario-${horarios.getId() }"
									autocomplete="off" path="hora" value="${horarios.getHora()}">
								<form:label value="${horarios.getHora()}" path="hora"
									class="btn btn-outline-primary col horarios ${horarios.getFechaHora()}-${horarios.getSala().getId()}"
									for="horario-${horarios.getId() }">${horarios.getHora()}</form:label>

							</c:forEach>



						</div>
						<input type="hidden" value="99" id="tempidsala" name="idSala"
							path="idSala">
						<form:label for="tempidsala" path="idSala"></form:label>
					</div>

					<div class="d-flex justify-content-center btncompraboleto">
						<form:button id="primerSiguiente" type="button submit"
							class="btn btn-primary">Siguiente</form:button>
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

	<script type="text/javascript">
		$(document).ready(
				function() {
					var idSala;
					$("#siempreOculto").hide();
					$(".fechas").hide();
					$(".horarios").hide();
					$("#primerSiguiente").hide();
					$("#selectcine").change(
							function() {
								var seleccionado = $(
										"#selectcine option:selected").val();
								$(".horarios").hide();
								$(".fechas").hide();
								$("#primerSiguiente").hide();
								$('.fechas-' + seleccionado).show();
							});
					$(".fechas").click(
							function() {
								idSala = $("input[name='fecha']:checked")
										.next().find("span").html();

								var radVal = $("input[name='fecha']:checked")
										.val();
								$(".horarios").hide();
								$("#primerSiguiente").hide();
								$('.' + radVal + '-' + idSala).show();
								$("#tempidsala").val(idSala);
							});
					$(".horarios").click(
							function() {
								$("#primerSiguiente").show();
								horario = $("input[name='hora']:checked")
										.next().html();
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