<%-- 
    Document   : Equipo
    Created on : Dec 14, 2013, 1:08:08 PM
    Author     : Yanick
--%>
<jsp:useBean id="equipo" type="Beans.Equipo" scope="request" />
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
        <title>Edición de Equipos</title>
    </head>
    <body>
        <div id="wrapper">
            <form action="/WorldCupJavaWeb/Equipo" method="post" accept-charset="ISO-8859-1">
                <div id="header">
                    <h1>Mantenimiento de Equipos</h1></div>
                <div id="page" style="height:300px">
                    <div id="content" >
                        <input type="hidden" id="hiddenIdEquipo" name="hiddenIdEquipo" value="${equipo.idEquipo}">
                        <table>
                            <tr>
                                <td>Selección</td><td><input type="text" id="txtSeleccion" name="txtSeleccion" value="${equipo.pais}"/></td>
                            </tr>
                            <tr>
                                <td>Entrenador</td><td><input type="text" id="txtEntrenador" name="txtEntrenador" value="${equipo.entrenador}"/></td>
                            </tr>
                            <tr>
                                <td>Cabeza de serie</td><td><input type="checkbox" name="chkCabezaSerie"  id="chkCabezaSerie" <c:if test="${equipo.cabezaSerie}">checked</c:if>></td>
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
                        <p><a href="ListarEquipos">Volver</a></p>
                    </div></div>
        </div>
    </body>
</html>
