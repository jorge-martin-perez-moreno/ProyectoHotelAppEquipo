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
    <title>Hotel FundAula - Detalle Huésped</title>
</head>

<body>
    <!-- Cabecera -->
    <div class="page-header">
        <h3>Sistema de gestión de usuarios</h3>
        <h1>Hotel FundAula</h1>
    </div>
    <div class="divider"></div>

    <!-- Contenedor principal -->
    <div class="main-card">

        <div class="card-header-row">
            <h2 class="card-title">Detalle del huésped</h2>
        </div>

        <!-- Tabla de detalle -->
        <table class="tabla-detalle">
        	<caption>Detalle del huésped</caption>
            <tr>
                <td class="detalle-label">Id</td>
                <td class="detalle-valor">${huesped.idHuesped}</td>
            </tr>
            <tr>
                <td class="detalle-label">Nombre</td>
                <td class="detalle-valor">${huesped.nombre}</td>
            </tr>
            <tr>
                <td class="detalle-label">Apellidos</td>
                <td class="detalle-valor">${huesped.apellidos}</td>
            </tr>
            <tr>
                <td class="detalle-label">Dirección</td>
                <td class="detalle-valor">${huesped.direccion}</td>
            </tr>
            <tr>
                <td class="detalle-label">Teléfono</td>
                <td class="detalle-valor">${huesped.telefono}</td>
            </tr>
            <tr>
                <td class="detalle-label">Email</td>
                <td class="detalle-valor">${huesped.email}</td>
            </tr>

        </table>
        <!-- Botones de acción -->
        <div class="footer-nav">
            <c:if test="${sessionScope.rol == 'supervisor'}">
    			<a href="${pageContext.request.contextPath}/huespedes/editar?id=${huesped.idHuesped}" class="btn-editar">Editar</a>
			</c:if>
            <a href="${pageContext.request.contextPath}/huespedes" class="btn-volver">← Volver al listado</a>
        </div>
    </div>

</body>
</html>