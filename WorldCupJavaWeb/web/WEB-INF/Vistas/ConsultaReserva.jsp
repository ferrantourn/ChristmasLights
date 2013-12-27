<%-- 
    Document   : ConsultaReserva
    Created on : Dec 25, 2013, 5:12:13 PM
    Author     : Yanick
--%>

<jsp:useBean id="reserva" type="Beans.Reserva" scope="request" />
<jsp:useBean id="estadio" type="Beans.Estadio" scope="request" />
<jsp:useBean id="partido" type="Beans.Partido" scope="request" />
<jsp:useBean id="modelo" type="Beans.ModeloFormBasico" scope="request" />
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="styles/style.css" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Consulta Reservas</title>
    </head>
    <body>
        <div id="wrapper">
            <div id="header">
                <h1>Consulta de Reservas</h1></div>
            <div id="page" style="height:300px">
                <div id="content" >
        <form action="/WorldCupJavaWeb/ConsultaReserva" method="post" accept-charset="ISO-8859-1">
            <h3>Ingrese su número de reserva</h3>
            <p><input type="text" id="txtReserva" name="txtReserva"><input type="submit" name="accion" id="btnBuscar" value="Buscar">
            </p>
        </form>
        <c:choose>
            <c:when test="${reserva != null && reserva.idReserva != 0}">
                <h3>Información de la reserva&nbsp; ${reserva.idReserva}</h3>
            <table id="table">
                <tr>
                    <td><b>Cedula:</b></td>
                    <td>${reserva.usuario.ci}</td>
                </tr>
                <tr>
                    <td><b>Nombre completo:</b></td>
                    <td>${reserva.usuario.nombre} &nbsp; ${reserva.usuario.apellido}</td>
                </tr>
                <tr>
                    <td><b>Usuario registrado:</b></td>
                    <td>${reserva.usuario.usuario} </td>
                </tr>
                <tr>
                    <td><b>Partido:</b></td>
                    <td>
                        <c:forEach items="${partido.equipos}" var="e">
                            <span>${e.pais}</span> &nbsp;
                        </c:forEach>

                    </td>
                </tr>
                
                <tr>
                    <td><b>Estadio:</b></td>
                    <td>${estadio.nombreEstadio}
                    </td>
                </tr>
                <tr>
                    <td colspan="2"><img width="200px" height="150px" src="${estadio.fotoUrl}" name="imgEstadio" id="imgEstadio"/></td>
                </tr>
            </table>
            </c:when>
            <c:otherwise>
                <p>${modelo.mensaje}</p>
            </c:otherwise>
        </c:choose>
                  <p><a href="Home">Volver</a></p>
                </div></div>
        </div>
    </body>
</html>
