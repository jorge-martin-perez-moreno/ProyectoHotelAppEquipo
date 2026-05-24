<%@ page contentType="text/html;charset=UTF-8" language="java" %> <!-- esta vista es para probar cambiar luego -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %><!-- librería form tags -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><!-- jstl -->

<html>
<head>
    <title>Detalle Reserva</title><!-- título de la pestaña del navegador -->
    <!-- CSS Reservas -->
    <link rel="stylesheet"
    href="${pageContext.request.contextPath}/resources/css/huespedes.css">
</head>

<body>
<!-- Cabecera -->
<div class="page-header">
    <h3>Sistema de gestión de reservas</h3>
    <h1>Hotel FundAula</h1>
</div>
<div class="divider"></div>

<!-- Contenedor principal -->
<div class="main-card">
    <div class="card-header-row">
        <h2 class="card-title">Detalle Reserva</h2>
    </div>
    <!-- Tabla detalle -->
    <table class="tabla-detalle">
        <tr>
            <td class="detalle-label">Id de la reserva</td>
            <td class="detalle-valor">${reserva.idReserva}</td>
        </tr>
        <tr>
            <td class="detalle-label">Habitación</td>
            <td class="detalle-valor">${reserva.habitacion.numeroHabitacion}</td>
        </tr>
        <tr>
            <td class="detalle-label">Fecha de entrada</td>
            <td class="detalle-valor">${reserva.fechaEntrada}</td>
        </tr>
        <tr>
            <td class="detalle-label">Fecha de salida</td>
            <td class="detalle-valor">${reserva.fechaSalida}</td>
        </tr>
        <tr>
            <td class="detalle-label">Tipo de pensión</td>
            <td class="detalle-valor">${reserva.tipoPension}</td>
        </tr>
        <tr>
            <td class="detalle-label">Estado de la reserva</td>
            <td class="detalle-valor">${reserva.estadoReserva}</td>
        </tr>
        <tr>
            <td class="detalle-label">Número de huéspedes</td>
            <td class="detalle-valor">${reserva.numeroHuespedes}</td>
        </tr>
        <tr>
            <td class="detalle-label">Observaciones</td>
            <td class="detalle-valor">${reserva.observaciones}</td>
        </tr>
    </table>

    <div class="footer-nav">
        <a href="${pageContext.request.contextPath}/reservas"class="btn-volver">← Volver</a><!-- botón volver para cambiar de vista al listado-->
    </div>
</div>

</body>
</html>