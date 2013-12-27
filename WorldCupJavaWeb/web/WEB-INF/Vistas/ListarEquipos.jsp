<%-- 
    Document   : ListarEquipos
    Created on : Dec 22, 2013, 4:13:05 PM
    Author     : Yanick
--%>
<jsp:useBean id="modelo" type="Beans.ModeloFormBasico" scope="request" />

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="styles/style.css" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Equipos</title>
    </head>
    <body>
        <div id="wrapper">
            <div id="header"><h1>Equipos</h1></div>
            <div id="page" style="height:900px">
                <div id="content" >
                    <table id="table">
                        <tr>
                            <th></th><th>PAÍS</th><th>ENTRENADOR</th><th>CABEZA SERIE</th>
                        </tr>
                        
                        <c:forEach items="${equipos}" var="e">
                            <tr>
                                <td><a href="/WorldCupJavaWeb/Equipo?accion=Buscar&idEquipo=${e.idEquipo}">Seleccionar</a></td>
                                <td>${e.pais}</td>
                                <td>${e.entrenador}</td>
                                <td><input type="checkbox" id="${e.pais}" <c:if test="${e.cabezaSerie}">checked</c:if>></td>
                                    
                                </tr>
                        </c:forEach>
                    </table>
                    
                    <p><a href="Home">Volver</a> o <a href="/WorldCupJavaWeb/Equipo?accion=nuevo" >Agregar Equipo</a></p> 
                    
                    <c:if test="${!empty modelo.descErrorInterno}">
                        <p>Error interno: modelo.descErrorInterno</p>
                    </c:if>
                </div></div></div>
    </body>
</html>
