<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- Importamos libreria JSTL core --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.servletContext.contextPath}/css/bootstrap.min.css">
    <!-- CSS Huespedes -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/huespedes.css">
    <title>Hotel FundAula - Reservas</title>
</head>
<body>
    <!-- Cabecera -->
    <div class="page-header">
        <h3>Sistema de gestión de usuarios</h3>
        <h1>Hotel FundAula</h1>
        <div class="divider"></div>
    </div>
    <!-- Contenedor principal -->
    <div class="main-card">

        <%-- Mensaje de error --%>
        <c:if test="${not empty error}">
            <div class="error-message">${error}</div>
        </c:if>

        <div class="card-header-row">
            <h2 class="card-title">Reservas</h2>
            <%-- Boton Nueva reserva — solo RECEPCIONISTA --%>
            <c:if test="${rol == 'RECEPCIONISTA'}">
                <a href="${pageContext.request.contextPath}/reservas/nuevo" class="btn-add">+ Nueva reserva</a>
            </c:if>
        </div>

        <!-- Tabla de reservas -->
        <table class="tabla">
            <caption>Listado de reservas</caption>
            <thead>
                <tr>
                    <th scope="col">Id Reserva</th>
                    <th scope="col">Id Huesped</th>
                    <th scope="col">Nº Habitacion</th>
                    <th scope="col">Fecha entrada</th>
                    <th scope="col">Fecha salida</th>
                    <th scope="col">Estado</th>
                    <th scope="col">Tipo</th>
                    <th scope="col">Nº Huespedes</th>
                    <th scope="col"></th>
                    <th scope="col"></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="reserva" items="${reservas}">
                <tr>
                    <td>${reserva.idReserva}</td>
                    <td>${reserva.huesped.idHuesped}</td>
                    <td>${reserva.habitacion.numeroHabitacion}</td>
                    <td>${reserva.fechaEntrada}</td>
                    <td>${reserva.fechaSalida}</td>
                    <td>${reserva.estadoReserva}</td>
                    <td>${reserva.tipoPension}</td>
                    <td>${reserva.numeroHuespedes}</td>
                    <%-- Enlace Detalle --%>
                    <td><a href="${pageContext.request.contextPath}/reservas/detalle?id=${reserva.idReserva}" class="btn-detalle">Ver</a></td>
                    <c:if test="${rol == 'RECEPCIONISTA'}">
                        <%-- Enlace Editar --%>
                        <td><a href="${pageContext.request.contextPath}/reservas/editar?idReserva=${reserva.idReserva}" class="btn-editar">Editar</a></td>
                        <%-- Enlace Eliminar con confirmacion --%>
                        <td><a href="${pageContext.request.contextPath}/reservas/eliminar?idReserva=${reserva.idReserva}" class="btn-eliminar" onclick="return confirm('¿Seguro que quieres eliminar esta reserva?')">Eliminar</a></td>
                    </c:if>
                </tr>
                </c:forEach>
            </tbody>
        </table>

        <!-- Volver al menu -->
        <div class="footer-nav">
            <a href="${pageContext.request.contextPath}/usuarios/principal" class="btn-volver">← Volver al menu</a>
        </div>
    </div>
</body>
</html>