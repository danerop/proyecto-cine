<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
      <div id="fecha-horario" class="collapse primersig">
        <h1 class="text-center">Compra</h1>
        <label for="selectcine">
          <h4 class="upspace">Seleccione un cine</h4>
        </label>
        <select id="selectcine" class="form-select" aria-label="Default select example">
          <option selected>Selecciona un cine</option>
          <option value="1">Cinehoyts</option>
          <option value="2">Cinemark San Justo</option>
          <option value="3">Cinemark Caballito</option>
        </select>

        <h4 class="upspace"> Seleccione fecha:</h4>
        <div class="btn-group container-fluid" role="group" aria-label="Button group with nested dropdown">
          <div class="row container-fluid">
            <input type="radio" class="btn-check" name="fecha" id="fecha1" autocomplete="off">
            <label class="btn btn-outline-primary col" for="fecha1">Hoy <br> 04/05</label>

            <input type="radio" class="btn-check" name="fecha" id="fecha2" autocomplete="off">
            <label class="btn btn-outline-primary col" for="fecha2">Mañana <br> xx/05</label>

            <input type="radio" class="btn-check" name="fecha" id="fecha3" autocomplete="off">
            <label class="btn btn-outline-primary col" for="fecha3">Dia <br> xx/05</label>

            <input type="radio" class="btn-check" name="fecha" id="fecha4" autocomplete="off">
            <label class="btn btn-outline-primary col" for="fecha4">Dia <br> xx/05</label>

            <input type="radio" class="btn-check" name="fecha" id="fecha5" autocomplete="off">
            <label class="btn btn-outline-primary col" for="fecha5">Dia <br> xx/05</label>

            <input type="radio" class="btn-check" name="fecha" id="fecha6" autocomplete="off">
            <label class="btn btn-outline-primary col" for="fecha6">Dia <br> xx/05</label>

            <button type="button" class="btn btn-outline-primary col">Siguiente <br> Semana</button>
          </div>
        </div>

        <h4 class="upspace">Horarios</h4>
        <div class="btn-group container-fluid" role="group" aria-label="Button group with nested dropdown">
          <div class="container-fluid row">
            <input type="radio" class="btn-check" name="horario" id="horario1" autocomplete="off">
            <label class="btn btn-outline-primary col" for="horario1">18:00</label>

            <input type="radio" class="btn-check" name="horario" id="horario2" autocomplete="off">
            <label class="btn btn-outline-primary col" for="horario2">19:00</label>

            <input type="radio" class="btn-check" name="horario" id="horario3" autocomplete="off">
            <label class="btn btn-outline-primary col" for="horario3">20:00</label>
          </div>
        </div>

        <div class="d-flex justify-content-center btncompraboleto">
          <button type="button" class="btn btn-primary" data-bs-toggle="collapse" data-bs-target=".primersig"
            aria-expanded="false" aria-controls="fecha-horario metodo-pago">Siguiente</button>
        </div>
      </div>

      <div id="metodo-pago" class="collapse primersig segundosig">
        <h2>metodo pago</h2>
        <div class="d-flex justify-content-center btncompraboleto">
          <button type="button" class="btn btn-secondary" data-bs-toggle="collapse" data-bs-target=".primersig"
            aria-expanded="false" aria-controls="metodo-pago fecha-horario">Volver</button>
          <button type="button" class="btn btn-primary" data-bs-toggle="collapse" data-bs-target=".segundosig"
            aria-expanded="false" aria-controls="metodo-pago confirmacion">Siguiente</button>
        </div>
      </div>

      <div id="confirmacion" class="collapse segundosig">
        <h2>Datos</h2>
        <h2>Confirmas?</h2>
        <div class="d-flex justify-content-center btncompraboleto">
          <button type="button" class="btn btn-secondary" data-bs-toggle="collapse" data-bs-target=".segundosig"
            aria-expanded="false" aria-controls="metodo-pago confirmacion">Volver</button>
          <a href="recibo" class="btn btn-primary" role="button">Comprar</a>
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