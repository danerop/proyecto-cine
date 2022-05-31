<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="ISO-8859-1">
<link href="css/pago-suscripcion.css" rel="stylesheet">
<link href="css/bootstrap.min.css" rel="stylesheet">
<title>Confirmar pago</title>
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
						<li class="nav-item"><a class="nav-link" href="suscripcion">Suscripciones</a>
						</li>
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
	<br>
	<br>
  <main>
	<div class="wrapper">
		<h4 class="text-uppercase">Detalles del pago</h4>
		<form:form class="form mt-4" action="procesarSuscripcion?s=${s}&u=${u}" method="POST" modelAttribute="datosSuscripcion">
		
		<input type="hidden" value="${datosSuscripcion.getIdUsuario()}" id="tempidusuario" name="idUsuario" path="idUsuario">
       	<form:label for="tempidusuario" path="idUsuario"></form:label>
       	<input type="hidden" value="${datosSuscripcion.getIdSuscripcion()}" id="tempidsuscripcion" name="idSuscripcion" path="idSuscripcion">
       	<form:label for="tempidsuscripcion" path="idSuscripcion"></form:label>
       	
			<div class="form-group">
				<label for="name" class="text-uppercase">Nombre del titular</label>
				<input type="text" class="form-control"
					placeholder="Nicolos Flemming">
			</div>
			<div class="form-group">
				<label for="card" class="text-uppercase">Número de la
					tarjeta</label>
				<div class="card-number">
					<input type="text" class="card-no" step="4"
						placeholder="1234 4567 5869 1234" pattern="^[0-9].{15,}">
					<span class=""> <img
						src="https://www.freepnglogos.com/uploads/mastercard-png/mastercard-marcus-samuelsson-group-2.png"
						alt="" width="30" height="30">
					</span>
				</div>
			</div>
			<div class="d-flex w-100">
				<div class="d-flex w-50 pr-sm-4">
					<div class="form-group">
						<label for="expiry" class="text-uppercase">Fecha de
							expiración</label> <input type="text" class="form-control"
							placeholder="03/2020">
					</div>
				</div>
				<div class="d-flex w-50 pl-sm-5 pl-3">
					<div class="form-group">
						<label for="cvv">CVV</label> <a href="#"
							title="CVV is a credit or debit card number, the last three digit number printed on the signature panel">¿Que
							es esto? </a> <input type="password" class="form-control pr-5"
							maxlength="3" placeholder="123">
					</div>
				</div>
			</div>
			<div class="form-inline pt-sm-3 pt-2">
				<input type="checkbox" name="address" id="address"> <label
					for="address" class="px-sm-1 pl-1 pt-sm-0 pt-2">Mi
					dirección de facturación es la misma que la de envío</label>
			</div>
			<div class="form-inline py-sm-2">
				<input type="checkbox" name="details" id="details"> <label
					for="details" class="px-sm-2 pl-2 pt-sm-0 pt-2">Guardar
					datos para futuras compras</label>
			</div>
			<div class="my-3">
				<!-- <input type="submit" class="text-uppercase btn btn-primary btn-block p-3" value="Suscribirse ${servicioElegido.tipo}"> -->
				<form:button type="button submit" class="text-uppercase btn btn-primary btn-block p-3">Suscribirse ${servicioElegido.tipo}</form:button> 
			</div>
			<div class="py-sm-2 total">
				<p>TOTAL: ${servicioElegido.cuota} </p>
			</div>
			<div id="form-footer">
				<p>Al realizar su pedido, usted acepta nuestro</p>
				<p>
					<a href="#">aviso de privacidad</a> y <a href="#">términos de
						uso</a>.
				</p>
			</div>
			

       	
		</form:form>
	</div>
	
	  </main>
	<br>
	<br>
	
</body>

</html>