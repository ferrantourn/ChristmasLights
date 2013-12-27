<%-- 
    Document   : Estadio
    Created on : Dec 23, 2013, 2:57:14 PM
    Author     : Yanick
--%>

<jsp:useBean id="estadio" type="Beans.Estadio" scope="request" />
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
        <title>Edición de Estadios</title>
    </head>
    <body>
        <div id="wrapper">
            <form enctype="multipart/form-data" action="/WorldCupJavaWeb/Estadio" method="post" accept-charset="ISO-8859-1">
                <div id="header"><h1>Mantenimiento de Estadios</h1></div>
                <div id="page" style="height:300px">
                <div id="content" >
                <input type="hidden" id="hiddenIdEstadio" name="hiddenIdEstadio" value="${estadio.idEstadio}">
                <table>
                    <tr>
                        <td>Nombre de Estadio</td><td><input type="text" id="txtNombreEstadio" name="txtNombreEstadio" value="${estadio.nombreEstadio}"/></td>
                    </tr>
                    <tr>
                        <td>Capacidad</td><td><input type="text" id="txtCapacidad" name="txtCapacidad" value="${estadio.capacidad}"/></td>
                    </tr>
                    <tr>
                        <td>Tipo de Cesped</td><td><select id="ddlTipoCesped" name="ddlTipoCesped">
                                <option value="Natural">Natural</option>
                                <option value="Sintetico">Sintetico</option></select>      
                            </select></td>
                    </tr>
                    <tr>
                        <td>Ingresar Foto</td>
                        <td><input name="foto" type="file" /></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><img style="width:200px; height:150px" name="imgEstadio" src="${estadio.fotoUrl}"/></td>
                    </tr>
                </table>
                <c:choose>
                    <c:when test="${estadio.nombreEstadio == null}">
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
            <p><a href="ListarEstadios">Volver</a></p>
        </div></div></div>
    </body>
</html>

