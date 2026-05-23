<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!doctype html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.servletContext.contextPath}/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <!-- CSS Principal -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/Principal.css">
    <title>Principal</title>
</head>

<body>
    <!-- Cabecera -->
    <div class="page-header">
        <h3>Sistema de gestión de usuarios</h3>
        <h1>Hotel FundAula</h1>
    </div>
    <div class="divider"></div>

    <!-- Tarjeta de bienvenida -->
    <div class="welcome-card">
        <p class="welcome-message">Bienvenido</p>
        <h2 class="welcome-name">${sessionScope.user}</h2>
        <div class="welcome-divider"></div>

        <!-- Menú de navegación -->
        <nav class="nav-menu">
            <a href="${pageContext.request.contextPath}/habitaciones" 
               class="nav-link">
               Habitaciones
            </a>
            <span class="nav-separator">|</span>
            <a href="${pageContext.request.contextPath}/huespedes" 
               class="nav-link">
               Huéspedes
            </a>
            <span class="nav-separator">|</span>
            <a href="${pageContext.request.contextPath}/reservas" 
               class="nav-link">
               Reservas
            </a>
            <span class="nav-separator">|</span>
            <a href="${pageContext.request.contextPath}/incidencias" 
               class="nav-link">
               Incidencias
            </a>
            <span class="nav-separator">|</span>
            <a href="${pageContext.request.contextPath}/usuarios/logout" 
               class="nav-link nav-logout">
               Cerrar sesión
            </a>
        </nav>
    </div>

</body>
</html>