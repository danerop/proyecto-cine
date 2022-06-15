<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
<meta charset="ISO-8859-1">
	<!-- Bootstrap core CSS -->
	<link href="css/bootstrap.min.css" rel="stylesheet" >
	<!-- Bootstrap theme -->
	<link href="css/style.css" rel="stylesheet">
	<link href="css/generos.css" rel="stylesheet">
<title>Seleccionar Generos Favoritos</title>
</head>

<body>
<br><br>
<main class="container-fluid">

<form:form action="validarGenerosFavoritos?g=${g}&u=${u}" method="POST" modelAttribute="datosFavorito">
  <fieldset>
  <legend>Selecciona tus generos favoritos</legend>
    <div>
       <form:select id="generos" path="generos" items="${listaDeGeneros}" multiple="multiple"></form:select>
    </div>
    <div>
      <button value="submit" >Guardar</button>
    </div>
  </fieldset>
</form:form>

</main>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>

</body>
</html>