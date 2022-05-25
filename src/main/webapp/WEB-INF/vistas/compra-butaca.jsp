<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<html>
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Compra Boleto</title>
  <link rel="stylesheet" href="./css/bootstrap.min.css">
  <link rel="stylesheet" href="./css/pago.css">
  <link rel="stylesheet" href="./css/tabla-butacas.css">
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
	
	<main class="container-fluid contenedorpago">
	
		<h3 class="text-center">Elegí tu butaca</h3>
		<form:form action="validar-compra?p=${p}&u=${u}" method="POST" modelAttribute="datosCompraBoleto">
		
			<h4>CINE: ${datosCompraBoleto.getIdcine()}</h4>
			<h4>fecha: ${datosCompraBoleto.getFecha()}</h4>
			<h4>hora: ${datosCompraBoleto.getHora()}</h4>
			<h4>butacaelegida: ${datosCompraBoleto.getIdButaca()}</h4>
		
		<br>
  <div class="table-responsive">
  <div class="btn-group" role="group" aria-label="Basic example">
  <table class="table w-auto testo">
		  <tbody>
		  		<tr>
		  		<td>&nbsp;</td>
		  		<c:forEach var="h" begin="1" end="32">
				  <td class="text-center p-4">${h}</td>
				</c:forEach>
		  		</tr>
			<c:forEach var="fil" begin="1" end="16">
			  <tr>
			  	<td class="align-middle p-4">${fil}</td>
				<c:forEach var="col" begin="1" end="32">
				  <td> 
				  	<c:forEach items="${butacas}" var="butaca">
						<c:if test="${butaca.getNroUbicacion()==(col+(32*(fil-1)))}">
							<c:choose>
								<c:when test="${!butaca.getOcupada()}">
									<input type="radio" class="btn-check position-fixed" name="idButaca" id="btncheck${col+(32*(fil-1))}" autocomplete="off" value="${butaca.getId()}">
  									<form:label class="btn btn-outline-primary p-4" for="btncheck${col+(32*(fil-1))}" path="idButaca">${butaca.getId()}</form:label>
								</c:when>
								<c:otherwise>
									<button class="btn-check btn btn-danger p-4" disabled>${butaca.getId()}</button>
								</c:otherwise>
							</c:choose>
	
						</c:if>
						
								  	
				  	</c:forEach>
				  </td>
				</c:forEach>
			  </tr>
			  </c:forEach>
		 
		  </tbody>

  </table>
 </div>
 </div>
		<br>
		
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
       	
		
		<form:button type="button submit" class="btn btn-primary">Comprar</form:button>
		
		</form:form>
	</main>
	
  
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  
  <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"
    integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB"
    crossorigin="anonymous"></script>
  <script src="./js/bootstrap.min.js"></script>
</body>
</html>