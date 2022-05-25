<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/style.css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
</head>
<body>
	<div class="container">


		<h2 class="text-center text-white mt-5"">Bienvenido a CineApp</h2>

		<div class="container-fluid bg-image" id="loginbox">
			<div class="container my-5 p-4 col-12 col-md-6 col-lg-4"
				style="border-radius: 10px; backdrop-filter: blur(15px); box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.8);">
				<form:form class="d-flex flex-column" action="validar-login"
					method="POST" modelAttribute="usuario">

					<h3 style="color: whitesmoke">Ingresar</h3>
					<div class="mb-3" style="color: whitesmoke">
						<label for="exampleInputEmail1" class="form-label">Email</label>
						<form:input path="email" id="email" type="email"
							class="form-control text-light"
							style="background-color: transparent; box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.65); border-radius: 10px; border-color: transparent;"
							aria-describedby="emailHelp" />
					</div>

					<div class="mb-3" style="color: whitesmoke">
						<label for="exampleInputPassword1" class="form-label">Contraseña</label>
						<form:input path="password" type="password" id="password"
							class="form-control"
							style="background-color: transparent; box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.65); border-radius: 10px; border-color: transparent; color: whitesmoke;" />
					</div>


					<div class="mb-3 form-check">
						<input type="checkbox" class="form-check-input" id="exampleCheck1" />
						<label class="form-check-label" for="exampleCheck1"
							style="color: whitesmoke">Recuerdame</label>
					</div>

					<button Type="Submit" class="btn mt-3 bg-info"
						style="border-radius: 10px; color: white;">Ingresar</button>

					<!--  <a href="#" id="contraseñaOlvidada"
						class="mt-3 d-flex justify-content-center"
						style="color: rgb(216, 216, 216)"> Olvidé mi contraseña </a> -->
					
					<a href="registroUsuario" id="insertarUsuario"
						class="mt-3 d-flex justify-content-center"
						style="color: rgb(216, 216, 216)"> Registrarse </a>

				</form:form>

					<br>
					
					<c:if test="${not empty exito}">
					<h5 class="text-white text-center">
						<span>${exito}</span>
					</h5>
					</c:if>
				<c:if test="${not empty error}">
					<h4>
						<span>${error}</span>
					</h4>
					<br>
				</c:if>

			</div>
		</div>


	</div>

	<!-- Placed at the end of the document so the pages load faster -->
	<script src="js/bootstrap.min.js"></script>
</body>
