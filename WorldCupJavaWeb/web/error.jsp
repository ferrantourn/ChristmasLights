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
        <link rel="stylesheet" type="text/css" href="styles/style.css" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error</title>
    </head>
    <body>
        <div id="wrapper">
            <div id="header">
                <h1>No te puedo creer!</h1>
                <h4>Error no controlado en la aplicación.</h4></div>
            <div id="page" style="height:300px">
                <div id="content" >
                    <p>${modelo.mensaje}</p>
                    <p>${modelo.descErrorInterno}</p>
                </div></div>
        </div>
    </body>
</html>
