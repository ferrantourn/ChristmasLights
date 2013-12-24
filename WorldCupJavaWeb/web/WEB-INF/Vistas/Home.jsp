<%-- 
    Document   : Home
    Created on : Dec 24, 2013, 12:23:16 PM
    Author     : Ferran
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="usuario" type="Beans.Usuario" scope="session" />
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
    </head>
    <body>
        <h1>Home</h1>
        <c:choose >
            <c:when test="${usuario.tipoUsuario.equalsIgnoreCase('ADMINISTRADOR')}">
                <p><a href="/WorldCupJavaWeb/ListarEquipos" >Equipos</a></p>
                <p><a href="/WorldCupJavaWeb/ListarEstadios" >Estadios</a></p>
            </c:when>
            <c:when test="${usuario.tipoUsuario.equalsIgnoreCase('VENDEDOR')}">
                <p><a href="/WorldCupJavaWeb/" >Consulta de reservas</a></p>
            </c:when>    
            <c:when test="${usuario.tipoUsuario.equalsIgnoreCase('USUARIO')}">
                <p><a href="/WorldCupJavaWeb/ReservaEntradas" >Reserva de entradas</a></p>
            </c:when>
        </c:choose>
        <p><a href="/WorldCupJavaWeb/" >Partidos ya jugados</a></p>
        <p><a href="/WorldCupJavaWeb/" >Próximos partidos</a></p>
        <p><a href="/WorldCupJavaWeb/" >Tabla de goleadores</a></p>
    </body>
</html>
