<%-- 
    Document   : ListarEquipos
    Created on : Dec 22, 2013, 4:13:05 PM
    Author     : Ferran
--%>
<jsp:useBean id="modelo" type="Beans.ModeloFormBasico" scope="request" />

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Equipos</title>
    </head>
    <body>
        <h1>Equipos</h1>
        <%--<form action="/WorldCupJavaWeb/Equipo" method="post" accept-charset="ISO-8859-1">
        <p>Buscar: &nbsp; <input type="text" id="txtBuscar" name="pais" >&nbsp;<input type="submit" value="Buscar" name="btnBuscar"></p>
        </form>--%>
        <table>
            <tr>
                <th><th>PAÍS</th><th>ENTRENADOR</th><th>CABEZA SERIE</th><th></th>
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
        
        <p><a href="index.jsp">Volver</a> o <a href="/WorldCupJavaWeb/Equipo?accion=nuevo" >Agregar Equipo</a></p> 
        
        <c:if test="${!empty modelo.descErrorInterno}">
            <p>Error interno: modelo.descErrorInterno</p>
        </c:if>
    </body>
    </body>
</html>
