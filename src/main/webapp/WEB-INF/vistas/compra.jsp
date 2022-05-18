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
      <form:form action="validar-compra?p=${p}&u=${u}" method="POST" modelAttribute="datosCompraBoleto">
        <div id="fecha-horario" class="collapse primersig">
          <h1 class="text-center">Compra</h1>

          <label for="selectcine">
            <h4 class="upspace">Seleccione un cine</h4>
          </label>
          <form:select id="selectcine" class="form-select" aria-label="Default select example" path="idcine">
             <option selected>Selecciona un cine</option>
            <c:forEach items="${cinesDisponibles}" var="cines">
            	<form:option path="idcine" value="${cines.getId()}">${cines.getNombreLocal()}</form:option>
            </c:forEach>

          </form:select>

          <h4 class="upspace"> Seleccione fecha y sala:</h4>
          <div class="btn-group container-fluid" role="group" aria-label="Button group with nested dropdown">
            <div class="row container-fluid">

              
              <c:forEach items="${funcionesDisponibles}" var="fechasMostrar">
              
              <input type="radio" class="btn-check fechas fechas-${fechasMostrar.getCine().getId()}" name="fecha" id="fecha-${fechasMostrar.getId()}" autocomplete="off" value="${fechasMostrar.getFechaHora()}"
                path="fecha">
              <form:label class="btn btn-outline-primary col fechas fechas-${fechasMostrar.getCine().getId()}" for="fecha-${fechasMostrar.getId()}" value="${fechasMostrar.getFechaHora()}" path="fecha">${fechasMostrar.getFechaHora()} <br><span id="idSala">${fechasMostrar.getSala().getId()}</span>-${fechasMostrar.getSala().getTipo()}</form:label>
              
              
              </c:forEach>
  
            </div>
          </div>

          <h4 class="upspace">Horarios</h4>
          
          
          <div class="btn-group container-fluid" role="group" aria-label="Button group with nested dropdown">
            <div class="container-fluid row">
              
			<c:forEach items="${funcionesDisponibles}" var="horarios">
			
			  <input type="radio" class="btn-check horarios ${horarios.getFechaHora()}-${horarios.getSala().getId()}" name="hora" id="horario-${horarios.getId() }" autocomplete="off" path="hora" value="${horarios.getHora()}">
              <form:label value="${horarios.getHora()}" path="hora" class="btn btn-outline-primary col horarios ${horarios.getFechaHora()}-${horarios.getSala().getId()}" for="horario-${horarios.getId() }">${horarios.getHora()}</form:label>
			
			</c:forEach>
              

              
            </div>
          </div>

          <div  class="d-flex justify-content-center btncompraboleto">
            <button id="primerSiguiente" type="button" class="btn btn-primary" data-bs-toggle="collapse" data-bs-target=".primersig"
              aria-expanded="false" aria-controls="fecha-horario metodo-pago">Siguiente</button>
          </div>
        </div>

        <div id="metodo-pago" class="collapse primersig segundosig">
          <h1>Metodo pago</h1>
          <!-- aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa -->

         

          <!-- zzzzzzzzzzzzzzzzzzzzzzzzzz -->
          <div class="d-flex justify-content-center btncompraboleto">
            <button type="button" class="btn btn-secondary" data-bs-toggle="collapse" data-bs-target=".primersig"
              aria-expanded="false" aria-controls="metodo-pago fecha-horario">Volver</button>
            <button type="button" class="btn btn-primary" data-bs-toggle="collapse" data-bs-target=".segundosig"
              aria-expanded="false" aria-controls="metodo-pago confirmacion">Siguiente</button>
          </div>
        </div>
        
        <input type="hidden" value="99" id="tempidsala" name="idSala" path="idSala">
       	<form:label for="tempidsala" path="idSala"></form:label>
        
        <div id="confirmacion" class="collapse segundosig">
          <h1>Datos</h1>
          <img alt="imgPelicula" src="${peliculaElegida.getUrlImagenPelicula()}">
          <h2>Pelicula: ${peliculaElegida.getNombre()} </h2>  
          <h2 id="cineS">Cine:</h2>
          <h2 id="fechaSalaS">Fecha y sala:</h2>
          <h2 id="horaS">Hora:</h2>
          <div class="d-flex justify-content-center btncompraboleto">
            <button type="button" class="btn btn-secondary" data-bs-toggle="collapse" data-bs-target=".segundosig"
              aria-expanded="false" aria-controls="metodo-pago confirmacion">Volver</button>
            <form:button type="button submit" class="btn btn-primary">Comprar</form:button>
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
  
  <script type="text/javascript">
  $(document).ready (function(){
	 var nombreCine;
	 var fechaysala;
	 var horario;
	 var idSala;
	 $("#siempreOculto").hide();
	 $(".fechas").hide();
	 $(".horarios").hide();
	 $("#primerSiguiente").hide();
	 $("#selectcine").change(function(){
		 var seleccionado=$("#selectcine option:selected").val();
		 $(".horarios").hide();
		 $(".fechas").hide();
		 $("#primerSiguiente").hide();
		 $('.fechas-'+seleccionado).show();
		nombreCine=$("#selectcine option:selected").html();
	 });
	 $(".fechas").click(function(){
		idSala=$("input[name='fecha']:checked").next().find("span").html();
		console.log(idSala);
			
		var radVal = $("input[name='fecha']:checked").val();
		$(".horarios").hide();
		$("#primerSiguiente").hide();
		$('.'+radVal +'-'+ idSala).show();
		fechaysala=$("input[name='fecha']:checked").next().html();
		

	 });
	 $(".horarios").click(function(){
		 $("#primerSiguiente").show();
		 horario=$("input[name='hora']:checked").next().html();
		 
			
	 });
	 $("#primerSiguiente").click(function(){
		$("#cineS").html("Cine: "+nombreCine); 
		$("#fechaSalaS").html("Fecha y sala: "+fechaysala);
		$("#horaS").html("Hora: "+horario);
		

		$("#tempidsala").val(idSala);
	 });
  });
  </script>
</body>

</html>