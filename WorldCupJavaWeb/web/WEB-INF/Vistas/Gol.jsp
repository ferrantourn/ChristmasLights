<%-- 
    Document   : Gol
    Created on : Dec 27, 2013, 12:29:19 AM
    Author     : Ferran
--%>

<jsp:useBean id="gol" type="Beans.Gol" scope="request" />
<jsp:useBean id="modelo" type="Beans.ModeloFormBasico" scope="request" />

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edición de Goles</title>
    </head>
    <body>
        <form action="/WorldCupJavaWeb/Gol" method="post" accept-charset="ISO-8859-1">
            <h1>Mantenimiento de Goles</h1>
            <input type="hidden" id="hiddenIdGol" name="hiddenIdPartido" value="${gol.idGol}">
            <table>
                <tr>
                    <td>Minuto</td><td><input type="text" id="txtMinuto" name="txtMinuto" value="${gol.minuto}"/></td>
                </tr>
                <tr>
                    <td>Partido</td><td><select id="ddlPartidos" name="ddlPartidos">
                            <c:forEach items="${partidos}" var="p" varStatus="count">
                                <option value="${p.idPartido}" <c:if test="${selectedPartidoId == p.idPartido}">selected</c:if> >
                                    <c:forEach items="${p.equipos}" var="e">
                                    <span>${e.pais}</span> &nbsp;
                                </c:forEach>
                                    
                                </option>
                            </c:forEach>
                        </select></td>
                </tr>
                <tr>
                    <td>Jugador</td><td><select id="ddlJugadores" name="ddlJugadores">
                            <c:forEach items="${jugadores}" var="j" varStatus="count">
                                <option value="${j.idJugador}" <c:if test="${selectedJugadorId == j.idJugador}">selected</c:if> >${j.nombre}&nbsp;${j.apellido}</option>
                            </c:forEach>
                        </select></td>
                </tr>
                
            </table>
            <c:choose>
                <c:when test="${gol.idGol == null || gol.idGol == 0}">
                    <input type="submit" name="accion" id="btnGuardar" value="Guardar"/>
                </c:when>
                <c:otherwise>
                   <!-- <input type="submit"  name="accion" id="btnActualizar" value="Actualizar"/>
                    <input type="submit"  name="accion" id="btnEliminar" value="Eliminar"/> -->
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
