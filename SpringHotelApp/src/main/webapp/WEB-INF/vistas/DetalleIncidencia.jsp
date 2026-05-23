<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %><!-- librería form tags -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><!-- jstl -->

<html>
<head>
    <title>Detalle Incidencia</title><!-- título de la pestaña del navegador -->
    <!-- CSS Incidencias -->
    <link rel="stylesheet"
    href="${pageContext.request.contextPath}/resources/css/huespedes.css">
</head>

<body>

<!-- Cabecera -->
<div class="page-header">
    <h3>Sistema de gestión de incidencias</h3>
    <h1>Hotel FundAula</h1>
</div>
<div class="divider"></div>

<!-- Contenedor principal -->
<div class="main-card">
    <div class="card-header-row">
        <h2 class="card-title">Detalle Incidencia</h2>
    </div>
    <!-- Tabla detalle -->
    <table class="tabla-detalle">
        <tr>
            <td class="detalle-label">ID de la incidencia</td>
            <td class="detalle-valor">${incidencia.idIncidencia}</td>
        </tr>
        <tr>
            <td class="detalle-label">Nº de la habitación</td>
            <td class="detalle-valor">${incidencia.habitacion.numeroHabitacion}</td>
        </tr>
        <tr>
            <td class="detalle-label">Estado de la incidencia</td>
            <td class="detalle-valor">${incidencia.estadoIncidencia}</td>
        </tr>
        <tr>
            <td class="detalle-label">Prioridad de la incidencia</td>
            <td class="detalle-valor">${incidencia.prioridadIncidencia}</td>
        </tr>
        <tr>
            <td class="detalle-label">Descripción de la incidencia</td>
            <td class="detalle-valor">${incidencia.descripcionIncidencia}</td>
        </tr>
        <tr>
            <td class="detalle-label">Fecha apertura</td>
            <td class="detalle-valor">${incidencia.fechaApertura}</td>
        </tr>
        <tr>
            <td class="detalle-label">Fecha cierre</td>
            <td class="detalle-valor">${incidencia.fechaCierre}</td>
        </tr>
    </table>

    <div class="footer-nav">
        <a href="${pageContext.request.contextPath}/incidencias"class="btn-volver">← Volver</a>
    </div>
</div>

</body>
</html>