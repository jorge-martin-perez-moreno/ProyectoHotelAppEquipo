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

    <title>Hotel FundAula - Huéspedes</title>
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

        <div class="card-header-row">
            <h2 class="card-title">Huéspedes</h2>
            <!-- Botón Añadir huésped -->
            <c:if test="${rol == 'RECEPCIONISTA'}">
    			<a href="${pageContext.request.contextPath}/huespedes/nuevo" class="btn-add">+ Añadir huésped</a>
    		</c:if>
        </div>

        <!-- Tabla de huéspedes -->
        <table class="tabla">
        	<caption>Listado de huéspedes</caption>
            <thead>
                <tr>
                    <th scope="col">Id</th>
                    <th scope="col">Nombre</th>
                    <th scope="col">Apellidos</th>
                    <th scope="col">Dirección</th>
                    <th scope="col">Teléfono</th>
                    <th scope="col">Email</th>
                    <th scope="col"></th>
                    <th scope="col"></th>
                    <th scope="col"></th>
                </tr>
            </thead>
            <tbody>
                <%-- Recorremos la lista de huéspedes con JSTL --%>
                <c:forEach var="huesped" items="${huespedes}">
                <tr>
                    <td>${huesped.idHuesped}</td>
                    <td>${huesped.nombre}</td>
                    <td>${huesped.apellidos}</td>
                    <td>${huesped.direccion}</td>
                    <td>${huesped.telefono}</td>
                    <td>${huesped.email}</td>
                    <%-- Enlace Detalle --%>
                    <td><a href="${pageContext.request.contextPath}/huespedes/detalle?id=${huesped.idHuesped}" class="btn-detalle">Detalle</a></td>
                    <c:if test="${rol == 'RECEPCIONISTA'}">
                    	<%-- Enlace Editar --%>
    					<td><a href="${pageContext.request.contextPath}/huespedes/editar?id=${huesped.idHuesped}" class="btn-editar">Editar</a></td>
    					<%-- Enlace Eliminar con confirmación --%>
    					<td><a href="${pageContext.request.contextPath}/huespedes/eliminar?id=${huesped.idHuesped}" class="btn-eliminar" onclick="return confirm('¿Seguro que quieres eliminar este huésped?')">Eliminar</a></td>
                    </c:if>
                </tr>
                </c:forEach>
            </tbody>
        </table>

        <!-- Volver al menú -->
        <div class="footer-nav">
            <a href="${pageContext.request.contextPath}/usuarios/principal" class="btn-volver">← Volver al menú</a>
        </div>

    </div>

</body>
</html>
