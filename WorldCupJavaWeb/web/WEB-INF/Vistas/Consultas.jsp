<%-- 
    Document   : TablaGoleadores
    Created on : Dec 27, 2013, 1:06:48 AM
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
        <title>Tabla de Goleadores</title>
    </head>
    <body>
        <div id="wrapper"><div id="header">
        <h1>Tabla de goleadores</h1>
       </div>
            <div id="page" style="height:300px">
                <div id="content" >
        <table id="table">
            <tr>
                <th>TOTAL GOLES<th>NOMBRE</th><th>APELLIDO</th>
            </tr>
                
            <c:forEach items="${jugadores}" var="j">
                <tr>
                    <td>${j.totalGoles}</td>
                    <td>${j.nombre}</td>
                    <td>${j.apellido}</td>
                </tr>
            </c:forEach>
        </table>
        
        <p><a href="Home">Volver</a> </p> 
        
        <c:if test="${!empty modelo.descErrorInterno}">
            <p>Error interno: modelo.descErrorInterno</p>
        </c:if>
                </div></div>
        </div>
    </body>
    
</html>
