<%-- 
    Document   : ConsultaReserva
    Created on : Dec 25, 2013, 5:12:13 PM
    Author     : Ferran
--%>
<jsp:useBean id="reserva" type="Beans.Reserva" scope="request" />
<jsp:useBean id="modelo" type="Beans.ModeloFormBasico" scope="request" />
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Consulta Reservas</title>
    </head>
    <body>
        <h1>Consulta de Reservas</h1>
        
        <h3>Ingrese su número de reserva</h3>
        <p><input type="text" id="txtReserva" name="txtReserva"><input type="submit" name="accion" id="btnBuscar" value="Buscar">
        </p>
        
        <c:if test="${reserva.idReserva != null && reserva.idReserva != 0}">
            <h3>Información de la reserva</h3>
            <table>
                <tr>
                    <td>Cedula:</td>
                    <td>${reserva.usuario.ci}</td>
                </tr>
                <tr>
                    <td>Partido:</td>
                    <td>${reserva.partido.equipos[0]} vs ${reserva.partido.equipos[1]}</td>
                </tr>
                 <tr>
                    <td>Numero:</td>
                    <td>${reserva.idReserva}</td>
                </tr>
            </table>
        </c:if>
    </body>
</html>
