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
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/principal.css">

    <title>Hotel FundAula</title>
</head>

<body>

    <!-- Cabecera -->
    <div class="page-header">
        <h3>Sistema de gestión de usuarios</h3>
        <h1>Hotel FundAula</h1>
        <div class="divider"></div>
    </div>

    <!-- Tarjeta principal -->
    <div class="login-card">
        <span class="acceso-label">Bienvenido</span>

        <!-- Botón Iniciar sesión -->
        <form method="get" action="usuarios/login">
            <div class="input-group-login">
                <button type="submit" class="btn-login">Iniciar sesión</button>
            </div>
        </form>

        <!-- Botón Nuevo usuario -->
        <form method="get" action="usuarios/nuevo">
            <div class="input-group-login">
                <button type="submit" class="btn-nuevo">Nuevo usuario</button>
            </div>
        </form>

    </div>

</body>
</html>
