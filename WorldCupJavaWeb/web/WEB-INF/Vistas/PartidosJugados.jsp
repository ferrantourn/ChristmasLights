<%-- 
    Document   : PartidosJugados
    Created on : Dec 27, 2013, 4:04:00 PM
    Author     : Yanick
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="modelo" type="Beans.ModeloFormBasico" scope="request" />
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="styles/style.css" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Partidos Jugados</title>
    </head>
    <body>
        <div id="wrapper">
            <div id="header">
                <h1>Partidos Jugados</h1>
            </div>
            <div id="page" style="height:300px">
                <div id="content" >
                   
                    <table id="table">
                        <tr>
                            <th>FECHA</th><th>ESTADIO</th><th>RESULTADO</th><th></th>
                        </tr>
                        
                        <c:forEach items="${partidos}" var="j">
                            <tr>
                                <td>
                                    <fmt:formatDate type="both" pattern="dd/MM/yyyy"
                                                    dateStyle="short" timeStyle="short" 
                                                    value="${j.fechaPartido}" />
                                    </td>
                                <td>${j.nombreEstadio}</td>
                                <td>${j.equipo1}&nbsp;${j.golesEquipo1}&nbsp; -&nbsp;
                                    ${j.equipo2}&nbsp;${j.golesEquipo2}</td>
                                <td><a href="/WorldCupJavaWeb/GolesPartido?accion=goles&idPartido=${j.idPartido}" id="goleslink" >Detalle goles</a></td>
                            </tr>
                        </c:forEach>
                    </table>
                    <p><a href="Home">Volver</a></p> 
                    
                    <c:if test="${!empty modelo.descErrorInterno}">
                        <p>Error interno: modelo.descErrorInterno</p>
                    </c:if>
                </div>
            </div>
        </div>
        
    </body>
</html>