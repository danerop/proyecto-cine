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
<title>Compras realizadas</title>
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
	<c:if test="${boletosadquiridos!=null && boletosadquiridos.size()>=1}">
		<h1 class="text-center text-success">Ultimos boletos adquiridos</h1>
		<div class="container-fluid h-75 w-75 p-4 bg-light rounded-3">
			<div class="row">
				<div class="col-4">
					<div class="container-fluid">
						<img class="col6" alt="peli"
							src="${boletosadquiridos.get(boletosadquiridos.size()-1).getFuncion().getPelicula().getUrlImagenPelicula()}">
						<div class="col6">
							Pelicula:
							${boletosadquiridos.get(boletosadquiridos.size()-1).getFuncion().getPelicula().getNombre()}
							<br> Cine:
							${boletosadquiridos.get(boletosadquiridos.size()-1).getFuncion().getCine().getNombreLocal()}
							<br> Fecha compra:
							${boletosadquiridos.get(boletosadquiridos.size()-1).getFechaComprado()}
							<br>
							<c:if
								test="${boletosadquiridos.get(boletosadquiridos.size()-1).getUsado()}">
					¡Este boleto ya fue usado!
				</c:if>
							<br> <a
								href="recibo?b=${boletosadquiridos.get(boletosadquiridos.size()-1).getId()}"
								class="btn btn-primary">Ver Detalle</a>
						</div>
					</div>

				</div>
				<div class="col-4">
					<c:if test="${boletosadquiridos.size()>=2}">
						<div class="container-fluid border-end border-start border-dark">
							<img class="col" alt="peli"
								src="${boletosadquiridos.get(boletosadquiridos.size()-2).getFuncion().getPelicula().getUrlImagenPelicula()}">
							<div class="col">
								Pelicula:
								${boletosadquiridos.get(boletosadquiridos.size()-2).getFuncion().getPelicula().getNombre()}
								<br> Cine:
								${boletosadquiridos.get(boletosadquiridos.size()-2).getFuncion().getCine().getNombreLocal()}
								<br> Fecha compra:
								${boletosadquiridos.get(boletosadquiridos.size()-2).getFechaComprado()}
								<br>
								<c:if
									test="${boletosadquiridos.get(boletosadquiridos.size()-2).getUsado()}">
					¡Este boleto ya fue usado!
				</c:if>
								<br> <a
									href="recibo?b=${boletosadquiridos.get(boletosadquiridos.size()-1).getId()}"
									class="btn btn-primary">Ver Detalle</a>
							</div>
						</div>
					</c:if>


				</div>
				<div class="col-4">
					<c:if test="${boletosadquiridos.size()>=3}">
						<div class="container-fluid">
							<img class="col" alt="peli"
								src="${boletosadquiridos.get(boletosadquiridos.size()-3).getFuncion().getPelicula().getUrlImagenPelicula()}">
							<div class="col">
								Pelicula:
								${boletosadquiridos.get(boletosadquiridos.size()-3).getFuncion().getPelicula().getNombre()}
								<br> Cine:
								${boletosadquiridos.get(boletosadquiridos.size()-3).getFuncion().getCine().getNombreLocal()}
								<br> Fecha compra:
								${boletosadquiridos.get(boletosadquiridos.size()-3).getFechaComprado()}
								<br>
								<c:if
									test="${boletosadquiridos.get(boletosadquiridos.size()-3).getUsado()}">
					¡Este boleto ya fue usado!
				</c:if>
								<br> <a
									href="recibo?b=${boletosadquiridos.get(boletosadquiridos.size()-3).getId()}"
									class="btn btn-primary">Ver Detalle</a>
							</div>
						</div>
					</c:if>

				</div>
			</div>

		</div>
		<br>
		<h1 class="text-center text-success">Boletos adquiridos</h1>
		<div class="container-fluid h-25 w-25 p-4 bg-light rounded-3">
			<c:forEach items="${boletosadquiridos}" var="boleto">
				<div class="row border-bottom border-dark m-2">
					<div class="col-12">
						<img class="" alt="peli"
							src="${boleto.getFuncion().getPelicula().getUrlImagenPelicula()}">
						<div class="">
							Pelicula: ${boleto.getFuncion().getPelicula().getNombre()} <br>
							Cine: ${boleto.getFuncion().getCine().getNombreLocal()} <br>
							Fecha compra: ${boleto.getFechaComprado()} <br>
							<c:if test="${boleto.getUsado()}">
					¡Este boleto ya fue usado!
							</c:if>
							<br> <a href="recibo?b=${boleto.getId()}"
								class="btn btn-primary">Ver Detalle</a> <br> &nbsp;
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</c:if>
	<c:if test="${boletosadquiridos==null || boletosadquiridos.size()<1}">
		<h1 class="text-center text-success">Todavia no compraste ningun boleto</h1>
	</c:if>

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
	<script src="./js/bootstrap.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>