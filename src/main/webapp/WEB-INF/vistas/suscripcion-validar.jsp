<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<!-- Bootstrap core CSS -->
	<link href="css/bootstrap.min.css" rel="stylesheet" >
	<!-- Bootstrap theme -->
	<link href="css/style.css" rel="stylesheet">
	<link href="css/reciboSuscripcion.css" rel="stylesheet">
<title>Suscripción elegida</title>
</head>

<body>
<br><br>
			
<table class="body-wrap">
    <tbody><tr>
        <td></td>
        <td class="container" width="600">
            <div class="content">
                <table class="main" width="100%" cellpadding="0" cellspacing="0">
                    <tbody><tr>
                        <td class="content-wrap aligncenter">
                            <table width="100%" cellpadding="0" cellspacing="0">
                                <tbody><tr>
                                    <td class="content-block">
                                        <h2>¡Gracias por elegirnos!</h2>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="content-block">
                                        <table class="invoice">
                                            <tbody><tr>
                                                <td>Email: ${usuarioSuscripto.getEmail()}
                                                <br>
                                                <c:set var="date" value="<%=new java.util.Date()%>" />Fecha:  
                                                <fmt:formatDate value="${date}"  
             									type="both" timeStyle="long" dateStyle="long" /></p>  
												<fmt:setTimeZone value="GMT-2" />  
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <table class="invoice-items" cellpadding="0" cellspacing="0">
                                                        <tbody>
                                                        <tr>
                                                            <td>Suscripcion: ${usuarioSuscripto.getSuscripcion().getDetalleSuscripcion().getTipo().toUpperCase()}</td>
                                                            <td class="alignright">${usuarioSuscripto.getSuscripcion().getDetalleSuscripcion().getCuota()}</td>
                                                        </tr>
                                                        <tr class="total">
                                                            <td class="alignright" width="80%">Total</td>
                                                            <td class="alignright">${usuarioSuscripto.getSuscripcion().getDetalleSuscripcion().getCuota()}</td>
                                                        </tr>
                                                    </tbody></table>
                                                </td>
                                            </tr>
                                        </tbody></table>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="content-block">
                                        <a href="inicio">Volver a Inicio</a>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="content-block">
                                        Cine App Company 1997
                                    </td>
                                </tr>
                            </tbody></table>
                        </td>
                    </tr>
                </tbody></table>
                </div>
        </td>
        <td></td>
    </tr>
</tbody></table>
			
<br><br>

		<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
</body>

</html>