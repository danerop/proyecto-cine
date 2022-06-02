<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            <a class="nav-link" aria-current="page" href="inicio">Home</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="peliculas">Películas</a>
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

  <br>
  <h3 class="text-center">Controles Administrador</h3>
  <br>

  <div class="btn-toolbar justify-content-center" role="toolbar" aria-label="Toolbar with button groups">
    <div class="btn-group mr-2" role="group">
      <a class="btn btn-secondary cargar-cine" href="http://localhost:8080/proyecto-cine/admin-cines">Cines</a>
      <a class="btn btn-secondary cargar-sala" href="http://localhost:8080/proyecto-cine/admin-salas">Salas</a>
      <a class="btn btn-secondary cargar-pelicula" href="http://localhost:8080/proyecto-cine/admin-peliculas">Peliculas</a>
      <a class="btn btn-secondary cargar-funcion" href="http://localhost:8080/proyecto-cine/admin-funciones">Funciones</a>
      <a class="btn btn-secondary cargar-suscripcion" href="http://localhost:8080/proyecto-cine/admin-suscripciones">Suscripciones</a>
    </div>
  </div>

  <br>
  
  <div class="container">
  	<h4 class="text-white">Lista de Suscripciones</h5>
    <div class="row">
      <div class="col-4">
        <!-- Acá estará la lista de los elementos registrados -->
        <div class="list-group" id="list-tab" role="tablist">
          <c:forEach items="${listaDetalleSuscripciones}" var="suscripcion">
            <a class="list-group-item list-group-item-action" data-toggle="list" href="#list-suscripcion${suscripcion.getId()}" role="tab">${suscripcion.getTipo()}</a>  
          </c:forEach>
        </div>
      </div>
      <div class="col-8">
        <!-- Acá va a aparecer el detalle -->
        <div class="tab-content">
          <c:forEach items="${listaDetalleSuscripciones}" var="suscripcion">
            <div class="tab-pane fade p-3 mb-2 bg-white rounded" id="list-suscripcion${suscripcion.getId()}" role="tabpanel">
              <div class="row">
                <div class="col-8">
                  <span style="font-size: small; font-weight: lighter;">id:${suscripcion.getId()}</span>
                  <span style="font-size: large; font-weight: bold;">${suscripcion.getTipo()}</span>
                  <br>
                  <p>
                    Descuento: ${suscripcion.getDescuentoEnBoletos()} <br>
                    Cantidad de Boletos Gratis: ${suscripcion.getCantidadBoletosGratis()} <br>
                    Cuota: ${suscripcion.getCuota()} <br>
                  </p>
                </div>
                <div class="col-4">
                  
                </div>
              </div>
            </div>
          </c:forEach>
        </div>
      </div>
    </div>
  </div>

  <br>

  <div class="container formularios bg-light rounded" style="margin-bottom:25px;">

    <div class="formulario-cargar-suscripcion" style=" padding: 1rem;">

      <c:if test="${not empty mens}">
        <h5 class="p-3 mb-2 bg-success text-white"> ${mens} </h5>
      </c:if>

      <form:form action="agregar-suscripcion" method="POST" modelAttribute="datosSuscripcion">
        <h4>Formulario Para Crear Suscripción</h4>
        <hr class="colorgraph"><br>

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
        <button class="btn btn-lg btn-primary btn-block" Type="Submit">Cargar Suscripción</button>
      </form:form>

    </div>

  </div>


  <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"
    integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous">
  </script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"
    integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous">
  </script>
  <script src="js/jquery.min.js"></script>
  <script>
  	$('#list-tab a').on('click', function (e) {
	  e.preventDefault()
	  $(this).tab('show')
	})
  </script>
</body>

</html>