<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%-- Importamos libreria JSTL core --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%-- Importamos Form MVC Tags de Spring --%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!doctype html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.servletContext.contextPath}/css/bootstrap.min.css">

    <!-- CSS Huespedes -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/huespedes.css">

    <title>Hotel FundAula - Formulario Huésped</title>
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
    		<div class="error-message">
        		${error}
    		</div>
		</c:if>

        <div class="card-header-row">
            <h2 class="card-title">
                <%-- Si idHuesped es 0 es nuevo, si no es edición --%>
                <c:choose>
                    <c:when test="${huesped.idHuesped == 0}">Nuevo huésped</c:when>
                    <c:otherwise>Editar huésped</c:otherwise>
                </c:choose>
            </h2>
        </div>

        <%-- FORMULARIO NUEVO — solo se muestra si idHuesped es 0 --%>
        <c:if test="${huesped.idHuesped == 0}">
            <form:form action="${pageContext.request.contextPath}/huespedes/nuevo"
                       method="post" modelAttribute="huesped">

                <table class="tabla-detalle">
                	<caption>Formulario nuevo huésped</caption>
                    <tr>
                        <th scope="row">Nombre:</th>
                        <td class="detalle-valor"><form:input path="nombre" class="form-input"/></td>
                    </tr>
                    <tr>
                        <th scope="row">Apellidos:</th>
                        <td class="detalle-valor"><form:input path="apellidos" class="form-input"/></td>
                    </tr>
                    <tr>
                        <th scope="row">Dirección:</th>
                        <td class="detalle-valor"><form:input path="direccion" class="form-input"/></td>
                    </tr>
                    <tr>
                        <th scope="row">Teléfono:</th>
                        <td class="detalle-valor"><form:input path="telefono" class="form-input"/></td>
                    </tr>
                    <tr>
                        <th scope="row">Email:</th>
                        <td class="detalle-valor"><form:input path="email" class="form-input"/></td>
                    </tr>
                    <tr>
                        <td colspan="2" class="detalle-valor botones-formulario">
                            <button type="submit" class="btn-add">Guardar</button>
                            <a href="${pageContext.request.contextPath}/huespedes" class="btn-volver">Cancelar</a>
                        </td>
                    </tr>
                </table>

            </form:form>
        </c:if>

        <%-- FORMULARIO EDITAR — solo se muestra si idHuesped no es 0 --%>
        <c:if test="${huesped.idHuesped != 0}">
            <form:form action="${pageContext.request.contextPath}/huespedes/editar"
                       method="post" modelAttribute="huesped">

                <%-- Campo oculto con el id — necesario para que Hibernate sepa qué registro actualizar --%>
                <form:hidden path="idHuesped"/>

                <table class="tabla-detalle">
                	<caption>Formulario editar huésped</caption>
                    <tr>
                        <th scope="row">Id:</th>
                        <td class="detalle-valor">${huesped.idHuesped}</td>
                    </tr>
                    <tr>
                        <th scope="row">Nombre:</th>
                        <td class="detalle-valor"><form:input path="nombre" class="form-input"/></td>
                    </tr>
                    <tr>
                        <th scope="row">Apellidos:</th>
                        <td class="detalle-valor"><form:input path="apellidos" class="form-input"/></td>
                    </tr>
                    <tr>
                        <th scope="row">Dirección:</th>
                        <td class="detalle-valor"><form:input path="direccion" class="form-input"/></td>
                    </tr>
                    <tr>
                        <th scope="row">Teléfono:</th>
                        <td class="detalle-valor"><form:input path="telefono" class="form-input"/></td>
                    </tr>
                    <tr>
                        <th scope="row">Email:</th>
                        <td class="detalle-valor"><form:input path="email" class="form-input"/></td>
                    </tr>
                    <tr>
                        <td colspan="2" class="detalle-valor botones-formulario">
                            <button type="submit" class="btn-add">Guardar</button>
                            <a href="${pageContext.request.contextPath}/huespedes" class="btn-volver">Cancelar</a>
                        </td>
                    </tr>
                </table>

            </form:form>
        </c:if>

    </div>

</body>
</html>
