<%-- 
    Document   : Partido
    Created on : Dec 26, 2013, 9:32:23 PM
    Author     : Ferran
--%>


<jsp:useBean id="partido" type="Beans.Partido" scope="request" />
<jsp:useBean id="modelo" type="Beans.ModeloFormBasico" scope="request" />

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edición de Partidos</title>
    </head>
    <body>
        <form action="/WorldCupJavaWeb/Partido" method="post" accept-charset="ISO-8859-1">
            <h1>Mantenimiento de Partidos</h1>
            <input type="hidden" id="hiddenIdPartido" name="hiddenIdPartido" value="${partido.idPartido}">
            <table>
                <tr>
                    <td>Fecha</td><td><input type="text" id="txtFecha" name="txtFecha" value="${partido.fecha}"/> * Ingrese la fecha con formato d/M/YYYY</td>
                </tr>
                <tr>
                    <td>Estadio</td><td><select id="ddlEstadio" name="ddlEstadio">
                            <c:forEach items="${estadios}" var="e" varStatus="count">
                                <option value="${e.idEstadio}" <c:if test="${selectedEstadioId == e.idEstadio}">selected</c:if> >${e.nombreEstadio}</option>
                            </c:forEach>
                        </select></td>
                </tr>
                <tr>
                    <td>Equipo 1</td><td><select id="ddlEquipo1" name="ddlEquipo1">
                            <c:forEach items="${equipos1}" var="e" varStatus="count">
                                <option value="${e.idEquipo}" <c:if test="${selectedEquipoId1 == e.idEquipo}">selected</c:if> >${e.pais}</option>
                            </c:forEach>
                            
                        </select></td>
                </tr>
                <tr>
                    <td>Equipo 2</td><td><select id="ddlEquipo2" name="ddlEquipo2">
                            <c:forEach items="${equipos2}" var="e2" varStatus="count2">
                                <option value="${e2.idEquipo}" <c:if test="${selectedEquipoId2 == e2.idEquipo}">selected</c:if> >${e2.pais}</option>
                            </c:forEach>
                        </select></td>
                </tr>
            </table>
            <c:choose>
                <c:when test="${partido.idPartido == null || partido.idPartido == 0}">
                    <input type="submit" name="accion" id="btnGuardar" value="Guardar"/>
                </c:when>
                <c:otherwise>
                    <input type="submit"  name="accion" id="btnActualizar" value="Actualizar"/>
                    <input type="submit"  name="accion" id="btnEliminar" value="Eliminar"/>
                </c:otherwise> 
            </c:choose>
            <div style="clear:both; height:20px" />
            <p>${modelo.mensaje}</p>
            <c:if test="${modelo.descErrorInterno != null && modelo.descErrorInterno != ''}">
                <p>Error Interno: &nbsp; ${modelo.descErrorInterno}</p>
            </c:if>
            
        </form>
        <p><a href="Home">Volver</a></p>
    </body>
</html>
