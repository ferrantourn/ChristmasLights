<%-- 
    Document   : ReservaEntradas
    Created on : Dec 24, 2013, 3:47:34 PM
    Author     : Ferran
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="reserva" type="Beans.Reserva" scope="request" />
<jsp:useBean id="modelo" type="Beans.ModeloFormBasico" scope="request" />
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reserva Entradas</title>
    </head>
    <body>
        <h1>Reserva de entradas</h1>
        <form action="/WorldCupJavaWeb/ReservaEntradas" method="post" accept-charset="ISO-8859-1">
            <table>
                <tr>
                    <td>Seleccione partido</td><td><select id="ddlPartidos" name="ddlPartidos">
                            <c:forEach items="${estadios}" var="e">
                                <option value="${e.idEstadio}">${e.nombreEstadio}</option></c:forEach>
                            </select></td>
                    </tr>
                    <tr>
                        <td>Ingrese cedula</td><td><input type="text" name="txtCedula"></td>
                    </tr>
                    <tr>
                        <td colspan="2"><input type="submit" value="Reservar" name="accion"></td>
                    </tr>
                </table>
            </form>
        <c:if test="${reserva.idReserva != null}">
            <p>Su numero de reserva es: &nbsp; ${reserva.idReserva}</p>
        </c:if>           
        <div style="clear:both; height:20px" />
        <p>${modelo.mensaje}</p>
        <c:if test="${modelo.descErrorInterno != null && modelo.descErrorInterno != ''}">
            <p>Error Interno: &nbsp; ${modelo.descErrorInterno}</p>
        </c:if>                        
    </body>
</html>
