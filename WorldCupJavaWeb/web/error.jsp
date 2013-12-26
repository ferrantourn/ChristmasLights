<%-- 
    Document   : error
    Created on : Dec 26, 2013, 4:36:54 PM
    Author     : Ferran
--%>
<jsp:useBean id="modelo" type="Beans.ModeloFormBasico" scope="request" />
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error</title>
    </head>
    <body>
        <h1>Oooops!</h1>
           <p>${modelo.mensaje}</p>
        <p>${modelo.descErrorInterno}</p>
     
    </body>
</html>
