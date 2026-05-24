<%@ page contentType="text/html;charset=UTF-8" language="java" %> <!-- esta vista es para probar cambiar luego -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %><!-- librería form tags -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><!-- jstl -->

<html>
<head>
<link rel="stylesheet"
href="${pageContext.request.contextPath}/resources/css/huespedes.css">
    <title>Detalle Habitación</title><!-- título de la pestaña del navegador -->
</head>
<body>

<div class="page-header">

    <h3>Hotel FundAula</h3>
    <h1>Detalle Habitación</h1>

</div>
<div class="divider"></div>
<div class="main-card">
    <table class="tabla-detalle">
        <tr>
            <td class="detalle-label">ID</td>
            <td class="detalle-valor">${habitacion.idHabitacion}</td>
        </tr>
        <tr>
            <td class="detalle-label">Número</td>
            <td class="detalle-valor">${habitacion.numeroHabitacion}</td>
        </tr>
        <tr>
            <td class="detalle-label">Tipo</td>
            <td class="detalle-valor">${habitacion.tipo}</td>
        </tr>
        <tr>
            <td class="detalle-label">Precio</td>
            <td class="detalle-valor">${habitacion.precioPorNoche}</td>
        </tr>
        <tr>
            <td class="detalle-label">Orientación</td>
            <td class="detalle-valor">${habitacion.orientacionHabitacion}</td>
        </tr>
    </table>
    <a href="${pageContext.request.contextPath}/habitaciones"class="btn-volver">Volver</a>
</div>

</body>
</html>