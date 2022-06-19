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
  <link rel="stylesheet" href="./css/tabla-butacas.css">
  <title>Controles Administrador</title>
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
              <a class="nav-link active" aria-current="page" href="admin">Inicio Administración</a>
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
  	<h4 class="text-white">Lista de Salas</h5>
    <div class="row">
      <div class="col-4">
        <!-- Acá estará la lista de los elementos registrados -->
        <div class="list-group" id="list-tab" role="tablist">
          <c:forEach items="${listaSalas}" var="sala">
            <a class="list-group-item list-group-item-action" data-toggle="list" href="#list-sala${sala.getId()}" role="tab">Sala id ${sala.getId()} del cine: ${sala.getCine().getNombreLocal()}</a>  
          </c:forEach>
        </div>
      </div>
      <div class="col-8">
        <!-- Acá va a aparecer el detalle -->
        <div class="tab-content">
          <c:forEach items="${listaSalas}" var="sala">
            <div class="tab-pane fade p-3 mb-2 bg-white rounded" id="list-sala${sala.getId()}" role="tabpanel">
              <div class="row">
                <div class="col-8">
                  <span style="font-size: small; font-weight: lighter;">Sala id:${sala.getId()}</span>
                  <span style="font-size: large; font-weight: bold;">Pertenece a ${sala.getCine().getNombreLocal()}</span>
                  <p>
                    Tipo de sala: ${sala.getTipo()}
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

    <div class="formulario-cargar-sala" style="padding: 1rem;">

      <c:if test="${not empty msgExito}">
        <h5 class="p-3 mb-2 bg-success text-white"> ${msgExito} </h5>
      </c:if>

      <form:form action="agregar-sala" method="POST" modelAttribute="datosSala">
        <h4>Formulario Para Crear Sala</h4>
        <hr class="colorgraph"><br>

        <form:label path="idCine">Seleccione el cine:</form:label>
        <form:select path="idCine" class="form-control">
          <c:forEach items="${listaCines}" var="cine">
            <form:option value="${cine.getId()}" label="id: ${cine.getId()} nombre: ${cine.getNombreLocal()}" />
          </c:forEach>
        </form:select>
        <br>
        <form:label path="tipo">Seleccione el tipo de Sala:</form:label>
        <form:select path="tipo" class="form-control">
          <form:option value="1" label="Comun" />
          <form:option value="2" label="Sala3D" />
          <form:option value="3" label="Sala4D" />
          <form:option value="4" label="GoldenClass" />
        </form:select>
        <div>
          <br>
          Butacas: <br>
          <button type="button" class="btn btn-lg btn-primary p-3" disabled></button> Butaca disponible

        </div>
        <div class="table-responsive">
          <table class="table">
            <tbody>
              <tr>
                <td>&nbsp;</td>
                <c:forEach var="h" begin="1" end="32">
                  <td class="text-center">${h}</td>
                </c:forEach>
              </tr>

              <c:forEach var="fil" begin="1" end="16">
                <tr>
                  <td class="align-middle">${fil}</td>
                  <c:forEach var="col" begin="1" end="32">
                    <td>
                      <input type="checkbox" class="btn-check position-fixed" name="butacas"
                        id="btncheck${col+(32*(fil-1))}" autocomplete="off" value="${col+(32*(fil-1))}">
                      <form:label class="btn btn-outline-primary p-2" for="btncheck${col+(32*(fil-1))}" path="butacas">
                        &nbsp;&nbsp;&nbsp;</form:label>
                    </td>
                  </c:forEach>
                </tr>
              </c:forEach>

            </tbody>

          </table>
        </div>
        <br>
        
        <c:if test="${not empty msgError}">
          <h5 class="p-3 mb-2 bg-danger text-white"> ${msgError} </h5>
      	</c:if>
        
        <button class="btn btn-lg btn-primary btn-block" Type="Submit" />Cargar Sala</button>
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