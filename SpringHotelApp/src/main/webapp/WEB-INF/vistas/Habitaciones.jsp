<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- librería jstl -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %><!-- librería form tags -->

<html>
<head>

<!-- Etiquetas para caracteres y diseno responsivo -->

<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- CSS Habitaciones -->
<link rel="stylesheet"
href="${pageContext.request.contextPath}/resources/css/huespedes.css">

<title>Habitaciones</title> <!-- título de la pestaña del navegador -->
</head>

<body>
<!-- Cabecera -->
<div class="page-header">
    <h3>Sistema de gestión de habitaciones</h3>
    <h1>Hotel FundAula</h1>
</div>
<div class="divider"></div>
<!-- Contenedor principal -->
<div class="main-card">
    <div class="card-header-row">
        <h2 class="card-title">Lista de Habitaciones</h2><!-- título, se puede cambiar el tamaño poniendo h2 o h3 o lo que sea -->
        <c:if test="${sessionScope.rol=='RECEPCIONISTA'}"><!-- restricción por roles -->
            <a href="${pageContext.request.contextPath}/habitaciones/nueva"class="btn-add">+ Nueva habitación</a>
        </c:if>
    </div>

    <!-- Tabla habitaciones -->
    <table class="tabla">
        <tr>
            <th>Id habitacion</th>
            <th>Nº hab</th>
            <th>Tipo hab</th>
            <th>Precio noche</th>
            <th>Disponibilidad</th>
            <th>Orientación</th>
            <th>Detalle</th>
            <c:if test="${sessionScope.rol=='RECEPCIONISTA'}"><!-- restricción por roles -->
                <th>Editar</th>
                <th>Eliminar</th>
            </c:if>
        </tr>
        <c:forEach var="habitacion" items="${habitaciones}">
            <tr>
                <td>${habitacion.idHabitacion}</td><!-- $expresion languages para llamar y traerse algo como un get -->
                <td>${habitacion.numeroHabitacion}</td>
                <td>${habitacion.tipo}</td>
                <td>${habitacion.precioPorNoche}</td>
			    <td>${habitacion.disponibilidad}</td>
			    <td>${habitacion.orientacionHabitacion}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/habitaciones/detalle?id=${habitacion.idHabitacion}"class="btn-detalle">Ver</a>
                </td>
         		<c:if test="${sessionScope.rol=='RECEPCIONISTA'}"><!-- restricción por roles -->
                    <td>
                        <a href="${pageContext.request.contextPath}/habitaciones/editar?id=${habitacion.idHabitacion}"class="btn-editar">Editar</a>
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/habitaciones/eliminar?id=${habitacion.idHabitacion}"class="btn-eliminar">Eliminar</a>
                    </td>
      			</c:if>
            </tr>
        </c:forEach>
    </table>
    <p class="error-message">${error}</p> <!-- error al borrar habitación, cambia solo el mensaje porque enlaza al error y muestra el texto que sea -->
    <div class="footer-nav">
        <form:form action="${pageContext.request.contextPath}//usuarios/principal" method="get">
        <!-- Al pulsar sobre el boton se vuelve al controlador del menu principal que envia la vista jsp 'Principal' -->
        <button type="submit" class="btn-volver">← Volver al menu principal</button>
        </form:form>
    </div>
</div>

</body>
</html>

<!-- no se pueden usar los form tags en este porque no funcionan con las tablas relacionadas solo en objetos libres -->