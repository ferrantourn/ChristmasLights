<%-- 
    Document   : GolesPartido
    Created on : Dec 27, 2013, 4:26:46 PM
    Author     : Yanick
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="modelo" type="Beans.ModeloFormBasico" scope="request" />
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="styles/style.css" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Goles Partido</title>
    </head>
    <body>
        <div id="wrapper">
            <div id="header">
                <h1>Goles Partido</h1>
            </div>
            <div id="page" style="height:300px">
                <div id="content" >
                    <table id="table">
                        <tr>
                            <th>MINUTO</th><th>JUGADOR</th><th>EQUIPO</th>
                        </tr>
                        
                        <c:forEach items="${goles}" var="g">
                            <tr>
                                <td>${g.minuto}''</td>
                                <td>${g.jugadorNombre} &nbsp;${g.jugadorApellido}</td>
                                <td>${g.nombreEquipo}</td>
                            </tr>
                        </c:forEach>
                    </table>
                    <p><a href="PartidosJugados?accion=jugados">Volver</a></p> 
                    
                    <c:if test="${!empty modelo.descErrorInterno}">
                        <p>Error interno: modelo.descErrorInterno</p>
                    </c:if>
                </div>
            </div>
        </div>
        
    </body>
</html>
