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
<title>Controles Administrador</title>
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
						<li class="nav-item"><a class="nav-link active" aria-current="page" href="admin">Inicio Administración</a></li>
						<li class="nav-item"><a class="nav-link" href="cerrarSesion">Cerrar Session</a></li>
					</ul>
				</div>
			</div>
		</nav>
	</header>
	
	<br>

	<div class="container formularios bg-light rounded" style="margin-bottom: 25px;">

		<div class="formulario-cargar-suscripcion" style="padding: 1rem;">

			<form:form action="${elementoNuevo == true ? 'registrar-suscripcion-nueva' : 'actualizar-suscripcion-vieja?id='.concat(datosSuscripcion.getId())}" method="POST" modelAttribute="datosSuscripcion">
				<h4>Formulario Para Crear Suscripción</h4>
				<hr class="colorgraph">
				<br>

				<form:label path="tipo">Nombre de suscripción:</form:label>
				<form:input path="tipo" id="tipo" type="tipo" class="form-control" />
				<br>
				<form:label path="descuentoEnBoletos">Descuento en boletos:</form:label>
				<form:input path="descuentoEnBoletos" id="descuentoEnBoletos" type="descuentoEnBoletos" class="form-control" />
				<br>
				<form:label path="cantidadBoletosGratis">Cantidad de boletos gratis al mes:</form:label>
				<form:input path="cantidadBoletosGratis" id="cantidadBoletosGratis" type="cantidadBoletosGratis" class="form-control" />
				<br>
				<form:label path="cuota">Cuota:</form:label>
				<form:input path="cuota" id="cuota" type="cuota" class="form-control" />
				<br>

				<c:if test="${not empty msgError}">
					<h5 class="p-3 mb-2 bg-danger text-white">${msgError}</h5>
				</c:if>

				<button class="btn btn-lg btn-primary btn-block" Type="Submit">Enviar</button>
			</form:form>

		</div>

	</div>
	
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"
		integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB"
		crossorigin="anonymous">
		
	</script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"
		integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13"
		crossorigin="anonymous">
		
	</script>
</body>

</html>