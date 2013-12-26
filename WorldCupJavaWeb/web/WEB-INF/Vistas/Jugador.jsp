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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edición de Jugadores</title>
    </head>
    <body>
        <form action="/WorldCupJavaWeb/Jugador" method="post" accept-charset="ISO-8859-1">
            <h1>Mantenimiento de Jugador</h1>
            <input type="hidden" id="hiddenIdJugador" name="hiddenIdJugador" value="${jugador.IdJugador}">
            <table>
                <tr>
                    <td>Nombre</td><td><input type="text" id="txtNombre" name="txtNombre" value="${jugador.Nombre}"/></td>
                </tr>
                <tr>
                    <td>Apellido</td><td><input type="text" id="txtApellido" name="txtApellido" value="${jugador.Apellido}"/></td>
                </tr>
                <tr>
                    <td>Posición</td><td><input type="text" id="txtPosicion" name="txtPosicion" value="${jugador.Posicion}"/></td>
                </tr>
            </table>
                    <c:choose >
                        <c:when test="${equipo.pais == null}">
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
            <p><a href="ListarJugadores">Volver</a></p>
    </body>
</html>
