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
              <a class="nav-link active" aria-current="page" href="inicio">Home</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="peliculas">Peliculas</a>
            </li>
            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown"
                aria-expanded="false">
                Suscripciones
              </a>
              <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                <li><a class="dropdown-item" href="#">Action</a></li>
                <li><a class="dropdown-item" href="#">Another action</a></li>
                <li>
                  <hr class="dropdown-divider">
                </li>
                <li><a class="dropdown-item" href="#">Something else here</a></li>
              </ul>
            </li>
            <li class="nav-item">
              <a class="nav-link disabled">Disabled</a>
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
    <div class="container-fluid contenedorpago">
      <form:form action="comprar-confirmar?p=${p}&u=${u}" method="POST" modelAttribute="datosCompraBoleto">
      	
      	<input type="hidden" value="${datosCompraBoleto.getIdSala()}" id="tempidsala" name="idSala" path="idSala">
       	<form:label for="tempidsala" path="idSala"></form:label>
       	<input type="hidden" value="${datosCompraBoleto.getIdUsuario()}" id="tempidusuario" name="idUsuario" path="idUsuario">
       	<form:label for="tempidusuario" path="idSala"></form:label>
       	<input type="hidden" value="${datosCompraBoleto.getIdPelicula()}" id="tempidpelicula" name="idPelicula" path="idPelicula">
       	<form:label for="tempidpelicula" path="idPelicula"></form:label>
       	<input type="hidden" value="${datosCompraBoleto.getHora()}" id="temphora" name="hora" path="hora">
       	<form:label for="temphora" path="hora"></form:label>
       	<input type="hidden" value="${datosCompraBoleto.getFecha()}" id="tempfecha" name="fecha" path="fecha">
       	<form:label for="tempfecha" path="fecha"></form:label>
       	<input type="hidden" value="${datosCompraBoleto.getIdcine()}" id="tempidcine" name="idcine" path="idcine">
       	<form:label for="tempidcine" path="idcine"></form:label>
       	<input type="hidden" value="${datosCompraBoleto.getIdButaca()}" id="tempidbutaca" name="idButaca" path="idButaca">
       	<form:label for="tempidbutaca" path="idButaca"></form:label>
      	
          <h1>Metodo pago</h1>
          <!-- aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa -->

         

          <!-- zzzzzzzzzzzzzzzzzzzzzzzzzz -->

          <div class="d-flex justify-content-center btncompraboleto">
            <button type="button" class="btn btn-secondary">Volver</button>
            <form:button type="button submit" class="btn btn-primary">Siguiente</form:button>
          </div>
        </div>
        
        
     </form:form>


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