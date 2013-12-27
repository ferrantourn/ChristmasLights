<%-- 
    Document   : ProximosPartidos
    Created on : Dec 27, 2013, 1:14:53 PM
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
        <title>Próximos Partidos</title>
    </head>
    <body>
        <div id="wrapper">
            <div id="header">
                <h1>Próximos Partidos</h1>
                
            </div>
            <div id="page" style="height:300px">
                <div id="content" >
                    <form>
                        <p>Filtrar por equipo: <select id="ddlEquipo" name="ddlEquipo">
                                <option value="0">-- Todos --</option>
                                <c:forEach items="${equipos}" var="e" varStatus="count">
                                    <option value="${e.idEquipo}" <c:if test="${selectedEquipoId == e.idEquipo}">selected</c:if> >${e.pais}</option>
                                </c:forEach>
                            </select></p>
                        <input type="submit" name="accion" id="proxpartidos" value="Buscar">
                    </form>
                    <table id="table">
                        <tr>
                            <th>FECHA</th><th>ESTADIO</th><th>EQUIPOS</th>
                        </tr>
                        
                        <c:forEach items="${partidos}" var="j">
                            <tr>
                                <td><fmt:formatDate type="both" pattern="dd/MM/yyyy" 
                                                    dateStyle="short" timeStyle="short" 
                                                    value="${j.fechaPartido}" /></td>
                                <td>${j.nombreEstadio}</td>
                                <td>${j.equipo1} vs ${j.equipo2}</td>
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
