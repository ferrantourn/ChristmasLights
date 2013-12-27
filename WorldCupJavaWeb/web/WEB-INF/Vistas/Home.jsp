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
        <link rel="stylesheet" type="text/css" href="styles/style.css" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
    </head>
    <body>
        <div id="wrapper">
            <div id="header">
                <div style="width:300px;float:right"><form action="/WorldCupJavaWeb/Home" method="post" accept-charset="ISO-8859-1"><input type="submit" name="accion" value="Salir" ></form></div>
                <h1>Home</h1>
                
            </div>
            <div id="menu">
                <ul>
                    <c:choose >
                        <c:when test="${usuario.tipoUsuario.equalsIgnoreCase('ADMINISTRADOR')}">
                            <li><a href="/WorldCupJavaWeb/ListarEquipos" >Equipos</a></li>
                            <li><a href="/WorldCupJavaWeb/ListarEstadios" >Estadios</a></li>
                            <li><a href="/WorldCupJavaWeb/ListarJugadores" >Jugadores</a></li>
                            <li><a href="/WorldCupJavaWeb/Partido?accion=nuevo" >Nuevo Partido</a></li>
                            <li><a href="/WorldCupJavaWeb/Gol?accion=nuevo" >Ingresar Goles</a></li>
                            </c:when>
                            <c:when test="${usuario.tipoUsuario.equalsIgnoreCase('VENDEDOR')}">
                            <li><a href="/WorldCupJavaWeb/ConsultaReserva" >Consultar Reserva</a></li>
                            </c:when>    
                            <c:when test="${usuario.tipoUsuario.equalsIgnoreCase('USUARIO')}">
                            <li><a href="/WorldCupJavaWeb/ReservaEntradas" >Reservar Entrada</a></li>
                            </c:when>
                        </c:choose>
                </ul>
            </div>
            <div id="page" style="height:300px">
                <div id="content" >
                    <h3>Bienvenido&nbsp;${usuario.usuario}</h3>
                    <ul>
                        <li><a href="/WorldCupJavaWeb/" >Partidos ya jugados</a></li>
                        <li><a href="/WorldCupJavaWeb/" >Próximos partidos</a></li>
                        <li><a href="/WorldCupJavaWeb/Consultas?accion=tabla">Tabla de goleadores</a></li>
                    </ul>
                </div>
            </div>
        </div>
</body>
</html>
