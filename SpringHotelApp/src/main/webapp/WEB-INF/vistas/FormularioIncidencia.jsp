<%@ page contentType="text/html;charset=UTF-8" language="java" %> <!-- se pueden poner br muchas encadenadas y así se deja más hueco -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><!-- librería jstl -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %><!-- librería jstl para formatear fechas -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %><!-- librería form tags -->

<html>
<head>
    <!-- CSS Incidencias -->
    <link rel="stylesheet"
    href="${pageContext.request.contextPath}/resources/css/huespedes.css">
    <title>Formulario Incidencia</title><!-- título de la pestaña del navegador -->
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
<p class="error-message">${error}</p><!-- el error si no se completan los campos -->
<h2 class="card-title"><!-- para cambiar entre guardar y editar --><!-- aquí no se llega según el rol porque ya se ocultó la opción en la lista-->
    <c:choose>
        <c:when test="${incidencia.idIncidencia != 0}">Editar Incidencia</c:when>
        <c:otherwise>Nueva Incidencia</c:otherwise>
    </c:choose>
</h2>

<form:form action="${pageContext.request.contextPath}/incidencias/guardar" 
           method="post" 
           modelAttribute="incidencia"><!-- formulario para actualizar manda los datos al controller -->
    <form:hidden path="idIncidencia"/><!-- guarda el id para actualizar pero no lo enseña y así sabe cuál es -->
    <div class="form-group">
        <label>Número de la habitación:</label><!-- aquí hay que poner lo que es el hueco de cada campo que hay que rellenar -->
		<select name="habitacion.idHabitacion" class="form-input">
   			 <c:forEach var="habitacion" items="${habitaciones}">
       			 <option value="${habitacion.idHabitacion}"
                     <c:if test="${incidencia.habitacion != null&& habitacion.idHabitacion == incidencia.habitacion.idHabitacion}">selected</c:if>>
                     ${habitacion.numeroHabitacion}
                 </option>
    		 </c:forEach>
		</select>
    </div>
    <div class="form-group">
        <label>Estado de la incidencia:</label>
        <form:select path="estadoIncidencia" class="form-input">
            <form:option value="ABIERTA">abierta</form:option>
            <form:option value="EN_CURSO">en curso</form:option>
            <form:option value="CERRADA">cerrada</form:option>
        </form:select>
    </div>
    <div class="form-group">
        <label>Prioridad de la incidencia:</label>
        <form:select path="prioridadIncidencia" class="form-input">
            <form:option value="BAJA">baja</form:option>
            <form:option value="MEDIA">media</form:option>
            <form:option value="ALTA">alta</form:option>
        </form:select>
    </div>
    <div class="form-group">
        <label>Descripción de la incidencia:</label>
        <form:input path="descripcionIncidencia" class="form-input"/>
    </div>
    <div class="form-group">
        <label>Fecha apertura:</label>
	    <form:input path="fechaApertura" 
                    type="date"
                    class="form-input"/>
    </div>
    <div class="form-group">
        <label>Fecha cierre:</label>
	    <form:input path="fechaCierre" 
                    type="date"
                    class="form-input"/>
    </div>
    <button type="submit" class="btn-add"><!-- para que salga un botón o el otro -->
	    <c:choose>
       		 <c:when test="${incidencia.idIncidencia != 0}">Actualizar</c:when>
	         <c:otherwise>Guardar</c:otherwise>
	    </c:choose>
	</button>
</form:form>
<br>
<a href="${pageContext.request.contextPath}/usuarios/principal"class="btn-volver">← Volver</a><!-- botón volver para cambiar de vista al listado-->
</div>
</body>
</html>

<!-- se usan solo algunas form tags porque se rompe el choose y pone editar y guardar y no funciona -->