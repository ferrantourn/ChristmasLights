<%-- 
    Document   : Jugador
    Created on : 25/12/2013, 09:39:11 PM
    Author     : FitoServer
--%>

<jsp:useBean id="jugador" type="Beans.Jugador" scope="request" />
<jsp:useBean id="modelo" type="Beans.ModeloFormBasico" scope="request" />

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="styles/style.css" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edición de Jugadores</title>
    </head>
    <body>
        <div id="wrapper">
        <form action="/WorldCupJavaWeb/Jugador" method="post" accept-charset="ISO-8859-1">
            <div id="header"><h1>Mantenimiento de Jugador</h1></div>
            <div id="page" style="height:300px">
                <div id="content" >
            <input type="hidden" id="hiddenIdJugador" name="hiddenIdJugador" value="${jugador.idJugador}">
            <table id="table">
                <tr>
                    <td>Nombre</td><td><input type="text" id="txtNombre" name="txtNombre" value="${jugador.nombre}"/></td>
                </tr>
                <tr>
                    <td>Apellido</td><td><input type="text" id="txtApellido" name="txtApellido" value="${jugador.apellido}"/></td>
                </tr>
                <tr>
                    <td>Posición</td><td><input type="text" id="txtPosicion" name="txtPosicion" value="${jugador.posicion}"/></td>
                </tr>
                <tr>
                    <td>Equipo</td><td><select id="ddlEquipo" name="ddlEquipo">
                            <c:forEach items="${equipos}" var="e" varStatus="count">
                                <option value="${e.idEquipo}" <c:if test="${selectedEquipoId == e.idEquipo}">selected</c:if> >${e.pais}</option>
                            </c:forEach>
                                
                        </select></td>
                </tr>
            </table>
                    <c:choose >
                        <c:when test="${jugador.idJugador == null || jugador.idJugador == 0}">
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
                </div></div>
        </form>
            <p><a href="ListarJugadores">Volver</a></p>
            
</div>
    </body>
</html>
