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
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
<link rel="stylesheet" href="./css/pago.css">
<title>Home</title>
</head>

<body>
	<header>
		<nav class="navbar navbar-expand-lg navbar-dark bg-dark ">
			<div class="container-fluid">
				<!--  <div class="d-flex justify-content-evenly">...</div> -->
				<a class="navbar-brand" href="iniciorecepcionista">LOGO</a>
				<button class="navbar-toggler" type="button"
					data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
					aria-controls="navbarSupportedContent" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse" id="navbarSupportedContent">
					<ul class="navbar-nav w-100 d-flex justify-content-evenly">
						<li class="nav-item"><a class="nav-link active"
							aria-current="page" href="iniciorecepcionista">Home</a></li>

						<li class="nav-item"><a class="nav-link" href="cerrarSesion">Cerrar
								sesión</a></li>
					</ul>
					<form class="d-flex">
						<input class="form-control me-2" type="search"
							placeholder="Search" aria-label="Search">
						<button class="btn btn-outline-success" type="submit">Search</button>
					</form>
				</div>
			</div>
		</nav>
	</header>
	<main>

		<div class="container-fluid contenedorpago bg-white">
			<c:if test="${boletoGenerado==null || fechadistinta || fueusado}">
				<h1 class="text-danger text-center">
					<i class="bi bi-x-circle-fill display-1"></i> BOLETO NO VALIDO
				</h1>
				<br>
				<h2 class="text-center">${msg}</h2>
				<c:if test="${boletoGenerado==null}">
					<div class="d-flex justify-content-center btncompraboleto">
						<a href="#" class="btn btn-secondary">Volver</a>
					</div>
				</c:if>
			</c:if>
			<c:if test="${boletoGenerado!=null && !fechadistinta && !fueusado}">
				<h1 class="text-success">BOLETO VALIDO</h1>
				<br>
			</c:if>
			<c:if test="${boletoGenerado!=null}">
				<h2>ID Boleto: ${boletoGenerado.getId()}</h2>
				<br>
				<h4>Pelicula:
					${boletoGenerado.getFuncion().getPelicula().getNombre()}</h4>
				<h4>Cine:
					${boletoGenerado.getFuncion().getCine().getNombreLocal() }</h4>
				<h4>Sala: ${boletoGenerado.getFuncion().getSala().getId()} -
					${boletoGenerado.getFuncion().getSala().getTipo() }</h4>
				<h4>Número de butaca: ${boletoGenerado.getButaca().getId()}</h4>
				<br>
				<c:choose>
					<c:when test="${fechadistinta}">
						<div class="text-danger">
							<h4>Fecha: ${boletoGenerado.getFuncion().getFechaHora() }</h4>
							<h4>Horario: ${boletoGenerado.getFuncion().getHora() }hs</h4>
						</div>
					</c:when>
					<c:otherwise>
						<div>
							<h4>Fecha: ${boletoGenerado.getFuncion().getFechaHora() }</h4>
							<h4>Horario: ${boletoGenerado.getFuncion().getHora() }hs</h4>
						</div>
					</c:otherwise>
				</c:choose>
				<br>
				<h4>IdUsuario: ${boletoGenerado.getCliente().getId()}</h4>
				<h4>Usuario: ${boletoGenerado.getCliente().getEmail()}</h4>
				<br>
				<div class="d-flex justify-content-center btncompraboleto">
					<a href="#" class="btn btn-secondary">Volver</a>
					<c:if test="${fechadistinta==false}">
						<a href="registrar-asistencia-boleto?b=${b}"
							class="btn btn-primary">Activar</a>
					</c:if>

				</div>

			</c:if>
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
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
	<script type="text/javascript">
		
	</script>
</body>

</html>