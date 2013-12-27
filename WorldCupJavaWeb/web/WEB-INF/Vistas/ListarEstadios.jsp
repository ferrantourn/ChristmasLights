<jsp:useBean id="modelo" type="Beans.ModeloFormBasico" scope="request" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="styles/style.css" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Estadios</title>
    </head>
    <body>
        <div id="wrapper">
            <div id="header"><h1>Estadios</h1></div>
             <div id="page" style="height:300px">
                <div id="content" >
        <%--<form action="/WorldCupJavaWeb/Equipo" method="post" accept-charset="ISO-8859-1">
        <p>Buscar: &nbsp; <input type="text" id="txtBuscar" name="pais" >&nbsp;<input type="submit" value="Buscar" name="btnBuscar"></p>
        </form>--%>
        <table id="table">
            <tr>
                <th><th>NOMBRE</th><th>CAPACIDAD</th><th>TIPO CÉSPED</th><th></th>
            </tr>
                
            <c:forEach items="${estadios}" var="e">
                <tr>
                    <td><a href="/WorldCupJavaWeb/Estadio?accion=Buscar&idEstadio=${e.idEstadio}">Seleccionar</a></td>
                    <td>${e.nombreEstadio}</td>
                    <td>${e.capacidad}</td>
                    <td>${e.cesped}</td>
                </tr>
            </c:forEach>
        </table>
        
        <p><a href="Home">Volver</a> o <a href="/WorldCupJavaWeb/Estadio?accion=nuevo" >Agregar Estadio</a></p> 
        
        <c:if test="${!empty modelo.descErrorInterno}">
            <p>Error interno: modelo.descErrorInterno</p>
        </c:if>
                </div></div></div>
    </body>
</html>
