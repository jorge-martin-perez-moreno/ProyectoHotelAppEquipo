<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><!-- librería jstl -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %><!-- librería form tags -->

<html>
<head>
    <!-- CSS Incidencias -->
    <link rel="stylesheet"
    href="${pageContext.request.contextPath}/resources/css/huespedes.css">
    <title>Incidencias</title><!-- título de la pestaña del navegador -->
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
        <h2 class="card-title">Lista de Incidencias</h2><!-- título, se puede cambiar el tamaño poniendo h2 o h3 o lo que sea -->

        <c:if test="${sessionScope.rol=='RECEPCIONISTA'}"><!-- restricción por roles -->
            <a href="${pageContext.request.contextPath}/incidencias/nueva"class="btn-add">+ Nueva incidencia</a>
        </c:if>
    </div>

    <!-- Tabla incidencias -->
    <table class="tabla">
        <tr>
            <th>ID</th>
            <th>Habitación</th>
            <th>Estado</th>
            <th>Prioridad</th>
            <th>Descripción</th>
            <th>Fecha apertura</th>
            <th>Fecha cierre</th>
            <th>Detalle</th>

            <c:if test="${sessionScope.rol=='RECEPCIONISTA'}"><!-- restricción por roles -->
                <th>Editar</th>
                <th>Eliminar</th>
            </c:if>
        </tr>

        <c:forEach var="incidencia" items="${incidencias}"><!-- recorre la list y cada objeto de incidencia es una incidencia -->
            <tr>
                <td>${incidencia.idIncidencia}</td><!-- $expresion languages para llamar y traerse algo como un get -->
                <td>${incidencia.habitacion.numeroHabitacion}</td>
                <td>${incidencia.estadoIncidencia}</td>
                <td>${incidencia.prioridadIncidencia}</td>
                <td>${incidencia.descripcionIncidencia}</td>
                <td>${incidencia.fechaApertura}</td>
                <td>${incidencia.fechaCierre}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/incidencias/detalle?id=${incidencia.idIncidencia}"class="btn-detalle">Ver</a>
                </td>

                <c:if test="${sessionScope.rol=='RECEPCIONISTA'}"><!-- restricción por roles -->
                    <td>
                        <a href="${pageContext.request.contextPath}/incidencias/editar?id=${incidencia.idIncidencia}"class="btn-editar">Editar</a>
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/incidencias/eliminar?id=${incidencia.idIncidencia}"class="btn-eliminar">Eliminar</a>
                    </td>
                 </c:if>
            </tr>
        </c:forEach>
    </table>

    <p class="error-message">${error}</p><!-- error al borrar habitación, cambia solo el mensaje porque enlaza al error y muestra el texto que sea -->

    <div class="footer-nav">
        <a href="${pageContext.request.contextPath}/usuarios/principal">
            <button type="button" class="btn-volver">← Volver al menu principal</button>
        </a>
    </div>
</div>
</body>
</html>

<!-- no se pueden usar los form tags en este porque no funcionan con las tablas relacionadas solo en objetos libres -->