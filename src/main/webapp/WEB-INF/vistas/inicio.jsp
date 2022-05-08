<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<!-- Bootstrap core CSS -->
	<link href="css/bootstrap.min.css" rel="stylesheet" >
	<!-- Bootstrap theme -->
	<link href="css/style.css" rel="stylesheet">
<title>Home</title>
</head>

<body>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark ">
	  <div class="container-fluid"> 
	  <!--  <div class="d-flex justify-content-evenly">...</div> -->
	    <a class="navbar-brand" href="inicio">LOGO</a>
	    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
	      <span class="navbar-toggler-icon"></span>
	    </button>
	    <div class="collapse navbar-collapse" id="navbarSupportedContent">
	      <ul class="navbar-nav w-100 d-flex justify-content-evenly">
	        <li class="nav-item">
	          <a class="nav-link active" aria-current="page" href="inicio">Home</a>
	        </li>
	        <li class="nav-item">
	          <a class="nav-link" href="peliculas">Películas</a>
	        </li>
	        <li class="nav-item dropdown">
	          <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
	            Suscripciones
	          </a>
	          <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
	            <li><a class="dropdown-item" href="#">Action</a></li>
	            <li><a class="dropdown-item" href="#">Another action</a></li>
	            <li><hr class="dropdown-divider"></li>
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
	<div id="carouselExampleIndicators" class="carousel slide" data-bs-ride="carousel">
  <div class="carousel-indicators">
    <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
    <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1" aria-label="Slide 2"></button>
    <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2" aria-label="Slide 3"></button>
  </div>
  <div class="carousel-inner">
    <div class="carousel-item active">
      <img src="https://static.cinemarkhoyts.com.ar/Images/Highlights/443.png" class="d-block w-100" alt="...">
    </div>
    <div class="carousel-item">
      <img src="https://static.cinemarkhoyts.com.ar/Images/Highlights/442.png" class="d-block w-100" alt="...">
    </div>
    <div class="carousel-item">
      <img src="https://static.cinemarkhoyts.com.ar/Images/Highlights/452.png" class="d-block w-100" alt="...">
    </div>
  </div>
  <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
    <span class="visually-hidden">Previous</span>
  </button>
  <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
    <span class="carousel-control-next-icon" aria-hidden="true"></span>
    <span class="visually-hidden">Next</span>
  </button>
</div>
<br>
<select class="form-select-bg-size cine-selection" aria-label="Default select example">
  <option selected>Selecciona un cine</option>
  <option value="1">Hoyts Morón</option>
  <option value="2">Cinemark San Justo</option>
  <option value="3">Cinemark Caballito</option>
</select>
<select class="form-select-bg-size" aria-label="Default select example">
  <option selected>Selecciona una película</option>
  <option value="1">Batman</option>
  <option value="2">Spiderman</option>
  <option value="3">Uncharted</option>
</select>
<br>
<br>
<h3 class="text-center">Películas</h3>
<br>
<br>
<div class="d-flex flex-column bd-highlight mb-3">
  <div class="d-flex justify-content-evenly">
	<img src="${pelicula.urlImagenPelicula}" class="img-thumbnail" alt="...">
	<img src="https://static.cinemarkhoyts.com.ar/Images/Posters/ebf9bbf852aefab0c08cf992217808ab.jpg?v=51032022" class="img-thumbnail" alt="...">
	<img src="https://static.cinemarkhoyts.com.ar/Images/Posters/ebf9bbf852aefab0c08cf992217808ab.jpg?v=51032022" class="img-thumbnail" alt="...">
  </div>
  <br>
  <div class="d-flex justify-content-evenly">
	<img src="https://static.cinemarkhoyts.com.ar/Images/Posters/ebf9bbf852aefab0c08cf992217808ab.jpg?v=51032022" class="img-thumbnail" alt="...">
	<img src="https://static.cinemarkhoyts.com.ar/Images/Posters/ebf9bbf852aefab0c08cf992217808ab.jpg?v=51032022" class="img-thumbnail" alt="...">
	<img src="https://static.cinemarkhoyts.com.ar/Images/Posters/ebf9bbf852aefab0c08cf992217808ab.jpg?v=51032022" class="img-thumbnail" alt="...">
  </div>
</div>
		<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
</body>

</html>