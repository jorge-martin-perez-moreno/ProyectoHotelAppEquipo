<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><!-- librería jstl -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %><!-- librería form tags -->



<html>
<head>

<link rel="stylesheet"
    href="${pageContext.request.contextPath}/resources/css/huespedes.css">
    
</head>
<body>
<h2>Formulario Habitación</h2>

<div class="page-header">

    <h3>Hotel FundAula</h3>
    
<h1><!-- para cambiar entre guardar y editar -->
    <c:choose>
        <c:when test="${habitacion.idHabitacion != 0}">
            Editar Habitación
        </c:when>
        <c:otherwise>
            Nueva Habitación
        </c:otherwise>
    </c:choose>
</h1>

</div>

<div class="divider"></div>

<div class="main-card">
<form action="${pageContext.request.contextPath}/habitaciones/guardar" method="post">

    <input type="hidden" name="idHabitacion" value="${habitacion.idHabitacion}">

<div class="form-group">
    <label>Número de la habitación:</label>
    <input type="text" name="numeroHabitacion" class="form-input" value="${habitacion.numeroHabitacion}">
</div>

<div class="form-group">
    <label>Tipo de la habitación:</label>
    <select name="tipo" class="form-input">
        <option value="INDIVIDUAL" <c:if test="${habitacion.tipo == 'INDIVIDUAL'}">selected</c:if>>Individual</option>
        <option value="DOBLE"<c:if test="${habitacion.tipo == 'DOBLE'}">selected</c:if>>Doble</option>
        <option value="SUITE"<c:if test="${habitacion.tipo == 'SUITE'}">selected</c:if>>Suite</option>
    </select>
</div>
    
<div class="form-group">
    <label>Precio de la habitación:</label>
    <input type="text" name="precioPorNoche" class="form-input" value="${habitacion.precioPorNoche}">
</div>

<div class="form-group">
    <label>Disponibilidad de la habitación:</label>
    <select name="disponibilidad" class="form-input">
        <option value="DISPONIBLE"<c:if test="${habitacion.disponibilidad == 'DISPONIBLE'}">selected</c:if>>disponible</option>
        <option value="OCUPADA" <c:if test="${habitacion.disponibilidad == 'OCUPADA'}">selected</c:if>>ocupada</option>
        <option value="LIMPIEZA" <c:if test="${habitacion.disponibilidad == 'LIMPIEZA'}">selected</c:if>>limpieza</option>
        <option value="MANTENIMIENTO" <c:if test="${habitacion.disponibilidad == 'MANTENIMIENTO'}">selected</c:if>>mantenimiento</option>
    </select>
</div>
    
<div class="form-group">
    <label>Orientación de la habitación:</label>
    <select name="orientacionHabitacion" class="form-input">
        <option value="INTERIOR" <c:if test="${habitacion.orientacionHabitacion == 'INTERIOR'}">selected</c:if>>Interior</option>
        <option value="EXTERIOR" <c:if test="${habitacion.orientacionHabitacion == 'EXTERIOR'}">selected</c:if>>Exterior</option>
    </select>
</div>

	<p class="error-message">${error}</p><!-- el error si no se completan los campos -->
	
    <button type="submit" class="btn-add">
        <c:choose>
            <c:when test="${habitacion.idHabitacion != 0}">
                Actualizar
            </c:when>
            <c:otherwise>
                Guardar
            </c:otherwise>
        </c:choose>
    </button>
</form>



<a href="${pageContext.request.contextPath}/habitaciones" class="btn-volver">Volver</a>

</div>

</body>
</html>

<!-- no se pueden usar form tags porque se rompe el choose y pone editar y guardar y no funciona -->