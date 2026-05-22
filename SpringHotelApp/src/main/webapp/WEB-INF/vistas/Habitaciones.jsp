<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- librería jstl -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %><!-- librería form tags -->

<html>
<head>


<!-- Etiquetas para caracteres y diseno responsivo -->

<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
    <title>Habitaciones</title> <!-- título de la pestaña del navegador -->

</head>
<body>

<h1>Lista de Habitaciones</h1><!-- título, se puede cambiar el tamaño poniendo h2 o h3 o lo que sea -->

<c:if test="${sessionScope.usuarioLogueado.rol=='RECEPCIONISTA'}"><!-- restricción por roles -->
    <a href="${pageContext.request.contextPath}/habitaciones/nueva">Nueva habitación</a>
</c:if>
<br><!-- espacio en blanco, deja una línea -->

<table border="1">
    <tr>
        <th>Id de la habitacion</th>
        <th>Número de la habitación</th>
        <th>Tipo de la habitación</th>
        <th>Precio por noche</th>
        <th>Disponibilidad de la habitación</th>
        <th>Orientación</th>
        <th>Acciones</th>
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
                <a href="${pageContext.request.contextPath}/habitaciones/detalle?id=${habitacion.idHabitacion}">Ver</a>
         		<c:if test="${sessionScope.usuarioLogueado.rol=='RECEPCIONISTA'}"><!-- restricción por roles -->
                <a href="${pageContext.request.contextPath}/habitaciones/editar?id=${habitacion.idHabitacion}">Editar</a>
                <a href="${pageContext.request.contextPath}/habitaciones/eliminar?id=${habitacion.idHabitacion}">Eliminar</a>
      			</c:if>
            </td>
        </tr>
    </c:forEach>
</table>

<p>${error}</p> <!-- error al borrar habitación, cambia solo el mensaje porque enlaza al error y muestra el texto que sea -->

<form:form action="${pageContext.request.contextPath}/vueltaPrincipal" method="get">

<!-- Al pulsar sobre el boton se vuelve al controlador del menu principal que envia la vista jsp 'Principal' -->
<button type="submit">Volver al menu principal</button>

</form:form>

</body>
</html>
<!-- no se pueden usar los form tags en este porque no funcionan con las tablas relacionadas solo en objetos libres -->