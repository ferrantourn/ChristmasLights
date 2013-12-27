<%-- 
    Document   : index
    Created on : Dec 13, 2013, 7:38:00 PM
    Author     : Ferran
--%>
<%--<jsp:useBean id="modelo" type="Beans.ModeloFormBasico" scope="request" />--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="styles/style.css" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bienvenido</title>
    </head>
    <body>
        <div id="wrapper">
            <div id="header">
                <h1>Bienvenido al sistema de gestión Brasil 2014</h1></div>
            
                <div id="menu">
                <ul>
                    <li><a href="/WorldCupJavaWeb/" >Partidos ya jugados</a></li>
                    <li><a href="/WorldCupJavaWeb/" >Próximos partidos</a></li>
                    <li><a href="/WorldCupJavaWeb/Consultas?accion=tabla">Tabla de goleadores</a></li>
                </ul>
            </div>
            <div id="page">
                <div id="content">
                    <h3>Login</h3>
                    
                    <form action="/WorldCupJavaWeb/Home" method="post" accept-charset="ISO-8859-1">
                        <table>
                            <tr>
                                <td>
                                    Usuario
                                </td>
                                <td>
                                    <input type="text" name="NombreUsuario">
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    Password
                                </td>
                                <td>
                                    <input type="text" name="Contrasena">
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2">
                                    <input type="submit" name="btnAutenticar" value="Autenticar">
                                </td>
                            </tr>
                        </table>
                    </form>
                </div>
            </div>
            
            <div style="clear:both; height:20px" />
            <p><c:if test="${request.getAttribute('error') != null}" > 
                    entro
                    ${request.getAttribute("error").toString()}
                </c:if></p>
            
            <%-- <c:if test="${modelo.descErrorInterno != null && modelo.descErrorInterno != ''}">
                <p>Error Interno: &nbsp; ${modelo.descErrorInterno}</p>
            </c:if>
            <%-- </c:when>
             <c:otherwise>
                 <p>Usted ya se encuentra logueado. <c:redirect url="Home"></c:redirect></p>
             </c:otherwise>
         </c:choose>--%>
            
        </div>
    </body>
</html>
