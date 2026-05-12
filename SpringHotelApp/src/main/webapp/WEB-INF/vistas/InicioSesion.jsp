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

    <!-- CSS Login -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/login.css">

    <title>Inicio de sesión</title>
</head>

<body>

    <!-- Cabecera -->
    <div class="page-header">
        <h3>Sistema de gestión de usuarios</h3>
        <h1>Hotel FundAula</h1>
        <div class="divider"></div>
    </div>

    <!-- Formulario de login -->
    <div class="login-card">
        <span class="acceso-label">Acceso de usuarios</span>

        <form id="login-usuarios" autocomplete="off" method="post" action="${pageContext.request.contextPath}/usuarios/login">

            <!-- Usuario -->
            <div class="input-group-login">
                <input name="usuario" type="text" class="form-control-login" placeholder="Usuario">
            </div>

            <!-- Password -->
            <div class="input-group-login">
                <input name="password" type="password" class="form-control-login" placeholder="Password">
            </div>

            <!-- Botón Login -->
            <div class="input-group-login">
                <button type="submit" class="btn-login">Login</button>
            </div>

        </form>

    </div>

</body>
</html>
