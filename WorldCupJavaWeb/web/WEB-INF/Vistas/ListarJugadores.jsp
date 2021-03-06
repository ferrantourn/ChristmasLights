<%-- 
    Document   : ListarJugadores
    Created on : Dec 26, 2013, 6:26:32 PM
    Author     : Yanick
--%>

<jsp:useBean id="modelo" type="Beans.ModeloFormBasico" scope="request" >
</jsp:useBean>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="styles/style.css" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Jugadores</title>
    </head>
    <body>
        <div id="wrapper">
            <div id="header"><h1>Jugadores</h1></div>
        <div id="page" style="height:300px">
                <div id="content" >
        <table id="table">
            <tr>
                <th><th>NOMBRE</th><th>APELLIDO</th><th>POSICION</th>
            </tr>
                
            <c:forEach items="${jugadores}" var="j">
                <tr>
                    <td><a href="/WorldCupJavaWeb/Jugador?accion=Buscar&idJugador=${j.idJugador}">Seleccionar</a></td>
                    <td>${j.nombre}</td>
                    <td>${j.apellido}</td>
                    <td>${j.posicion}</td>
                </tr>
            </c:forEach>
        </table>
        
        <p><a href="Home">Volver</a> o <a href="/WorldCupJavaWeb/Jugador?accion=nuevo" >Agregar Jugador</a></p> 
        
        <c:if test="${!empty modelo.descErrorInterno}">
            <p>Error interno: modelo.descErrorInterno</p>
        </c:if>
                </div></div></div>
    </body>
</html>
