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

    <title>Hotel FundAula - Formulario Reserva</title>
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
                <%-- Si idReserva es 0 es nueva, si no es edicion --%>
                <c:choose>
                    <c:when test="${reserva.idReserva == 0}">Nueva reserva</c:when>
                    <c:otherwise>Editar reserva</c:otherwise>
                </c:choose>
            </h2>
        </div>

        <%-- FORMULARIO NUEVO — solo se muestra si idReserva es 0 --%>
        <c:if test="${reserva.idReserva == 0}">
            <form:form action="${pageContext.request.contextPath}/reservas/nuevo"
                       method="post" modelAttribute="reserva">

                <table class="tabla-detalle">
                    <caption>Formulario nueva reserva</caption>
                    <tr>
                        <th scope="row">Huesped:</th>
                        <td class="detalle-valor">
                            <select name="idHuesped" class="form-input">
    							<option value="">-- Selecciona huesped --</option>
    							<c:forEach var="h" items="${huespedes}">
        							<option value="${h.idHuesped}">${h.nombre} ${h.apellidos}</option>
    							</c:forEach>
							</select>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">Habitacion:</th>
                        <td class="detalle-valor">
                            <select name="idHabitacion" class="form-input">
    							<option value="">-- Selecciona habitacion --</option>
    							<c:forEach var="hab" items="${habitaciones}">
      					  			<option value="${hab.idHabitacion}">${hab.numeroHabitacion} - ${hab.tipo}</option>
    							</c:forEach>
							</select>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">Fecha entrada:</th>
                        <td class="detalle-valor"><input type="date" name="fechaEntrada" class="form-input"/></td>
                    </tr>
                    <tr>
                        <th scope="row">Fecha salida:</th>
                        <td class="detalle-valor"><input type="date" name="fechaSalida" class="form-input"/></td>
                    </tr>
                    <tr>
                        <th scope="row">Tipo pension:</th>
                        <td class="detalle-valor">
                            <form:select path="tipoPension" class="form-input">
                                <form:option value="" label="-- Selecciona tipo --"/>
                                <form:option value="ALOJAMIENTO" label="Solo alojamiento"/>
                                <form:option value="MEDIA" label="Media pension"/>
                                <form:option value="COMPLETA" label="Pension completa"/>
                            </form:select>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">Estado:</th>
                        <td class="detalle-valor">
                            <form:select path="estadoReserva" class="form-input">
                                <form:option value="" label="-- Selecciona estado --"/>
                                <form:option value="PENDIENTE" label="Pendiente"/>
                                <form:option value="CONFIRMADA" label="Confirmada"/>
                                <form:option value="CANCELADA" label="Cancelada"/>
                            </form:select>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">Numero huespedes:</th>
                        <td class="detalle-valor"><form:input path="numeroHuespedes" type="number" class="form-input"/></td>
                    </tr>
                    <tr>
                        <th scope="row">Observaciones:</th>
                        <td class="detalle-valor"><form:textarea path="observaciones" class="form-input"/></td>
                    </tr>
                    <tr>
                        <td colspan="2" class="detalle-valor botones-formulario">
                            <button type="submit" class="btn-add">Guardar</button>
                            <a href="${pageContext.request.contextPath}/reservas" class="btn-volver">Cancelar</a>
                        </td>
                    </tr>
                </table>

            </form:form>
        </c:if>

        <%-- FORMULARIO EDITAR — solo se muestra si idReserva no es 0 --%>
        <c:if test="${reserva.idReserva != 0}">
            <form:form action="${pageContext.request.contextPath}/reservas/editar?idReserva=${reserva.idReserva}"
                       method="post" modelAttribute="reserva">

                <%-- Campo oculto con el id — necesario para que Hibernate sepa que registro actualizar --%>
                <form:hidden path="idReserva"/>

                <table class="tabla-detalle">
                    <caption>Formulario editar reserva</caption>
                    <tr>
                        <th scope="row">Id:</th>
                        <td class="detalle-valor">${reserva.idReserva}</td>
                    </tr>
                    <tr>
                        <th scope="row">Huesped:</th>
                        <td class="detalle-valor">
                            <form:select path="huesped.idHuesped" class="form-input">
                                    <form:option value="0" label="-- Selecciona huesped --"/>
                                       <c:forEach var="h" items="${huespedes}">
                						<form:option value="${h.idHuesped}" label="${h.nombre} ${h.apellidos}" />
                                    
                                </c:forEach>
                            </form:select>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">Habitacion:</th>
                        <td class="detalle-valor">
                            <form:select path="habitacion.idHabitacion" class="form-input">
                                <form:option value="0" label="-- Selecciona habitación --"/>
                                    <c:forEach var="hab" items="${habitaciones}">
                					<%-- Spring comparará el idHabitacion de la reserva con el de la lista --%>
                					<%-- Si coinciden, pondrá el 'selected' automáticamente --%>
                					<form:option value="${hab.idHabitacion}" label="${hab.numeroHabitacion} - ${hab.tipo}" />
                                    
                                </c:forEach>
                            </form:select>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">Fecha entrada:</th>
                        <td class="detalle-valor"><form:input path="fechaEntrada" type="date" class="form-input"/></td>
                    </tr>
                    <tr>
                        <th scope="row">Fecha salida:</th>
                        <td class="detalle-valor"><form:input path="fechaSalida" type="date" class="form-input"/></td>
                    </tr>
                    <tr>
                        <th scope="row">Tipo pension:</th>
                        <td class="detalle-valor">
                            <form:select path="tipoPension" class="form-input">
                                <form:option value="ALOJAMIENTO" label="Solo alojamiento"/>
                                <form:option value="MEDIA" label="Media pension"/>
                                <form:option value="COMPLETA" label="Pension completa"/>
                            </form:select>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">Estado:</th>
                        <td class="detalle-valor">
                            <form:select path="estadoReserva" class="form-input">
                                <form:option value="PENDIENTE" label="Pendiente"/>
                                <form:option value="CONFIRMADA" label="Confirmada"/>
                                <form:option value="CANCELADA" label="Cancelada"/>
                            </form:select>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">Numero huespedes:</th>
                        <td class="detalle-valor"><form:input path="numeroHuespedes" type="number" class="form-input"/></td>
                    </tr>
                    <tr>
                        <th scope="row">Observaciones:</th>
                        <td class="detalle-valor"><form:textarea path="observaciones" class="form-input"/></td>
                    </tr>
                    <tr>
                        <td colspan="2" class="detalle-valor botones-formulario">
                            <button type="submit" class="btn-add">Guardar</button>
                            <a href="${pageContext.request.contextPath}/reservas" class="btn-volver">Cancelar</a>
                        </td>
                    </tr>
                </table>

            </form:form>
        </c:if>

    </div>

</body>
</html>