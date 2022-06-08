<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0zm-3.97-3.03a.75.75 0 0 0-1.08.022L7.477 9.417 5.384 7.323a.75.75 0 0 0-1.06 1.06L6.97 11.03a.75.75 0 0 0 1.079-.02l3.992-4.99a.75.75 0 0 0-.01-1.05z"/>
  </symbol>

</head>

<body>

  <header>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark ">
      <div class="container-fluid">
        <!--  <div class="d-flex justify-content-evenly">...</div> -->
        <a class="navbar-brand" href="inicio">LOGO</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
          aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
          <ul class="navbar-nav w-100 d-flex justify-content-evenly">
            <li class="nav-item">
              <a class="nav-link active" aria-current="page" href="inicio">Inicio</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="suscripcion">Suscripciones</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="cerrarSesion">Iniciar Session</a>
            </li>
          </ul>
          <form class="d-flex">
            <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
            <button class="btn btn-outline-success" type="submit">Search</button>
          </form>
        </div>
      </div>
    </nav>
  </header>

  <main>
  
  <c:if test="${nuevacompra!=null}">
   <div class="alert alert-success d-flex align-items-center alert-dismissible" role="alert">
  <svg class="bi flex-shrink-0 me-2" width="24" height="24" role="img" aria-label="Success:"><use xlink:href="#check-circle-fill"/></svg>
  <div>
    Gracias por su compra!
  </div>
  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
</div>
  </c:if>

    
    
    <div class="container-fluid contenedorpago">
			<h1 class="text-center">RECIBO</h1>
			<div class="row">
			<div class="col-6">
				<h4>Pelicula: ${boletoGenerado.getFuncion().getPelicula().getNombre()}</h4>
            	<h4>Fecha: ${boletoGenerado.getFuncion().getFechaHora() }</h4>
            	<h4>Horario: ${boletoGenerado.getFuncion().getHora() } </h4>
            	<h4>Cine: ${boletoGenerado.getFuncion().getCine().getNombreLocal() }</h4>
            	<h4>Sala: ${boletoGenerado.getFuncion().getSala().getId()} -  ${boletoGenerado.getFuncion().getSala().getTipo() }</h4>
				<h4>Precio: $${boletoGenerado.getPrecio()}</h4>
				<h4>Número de butaca: ${boletoGenerado.getButaca().getId()}</h4>
				<br>
				<button class="btn btn-primary mx-auto d-block">Imprimir</button>
			</div>
			<div class="col-6">
				<img class="img-responsive w-50 mx-auto d-block" alt="codigoqr" src="./img/boletoqr/boleto${boletoGenerado.getId()}.jpg">
				<br>
				<h6 class="text-center">¡Mostrá esté código qr para entrar a la sala!</h6>
			</div>
			</div>

    </div>




  </main>

  <footer>

  </footer>
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"
    integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB"
    crossorigin="anonymous"></script>
  <script src="./js/bootstrap.min.js"></script>
  <script src="./js/pago.js"></script>
</body>

</html>