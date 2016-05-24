<%-- 
    Document   : errorJSP
    Created on : 18-mag-2016, 16.08.41
    Author     : gpc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>HomeMade.com</title>
        <meta charset="UTF-8">
        <meta name="author" content="Gian Piero Cozzula">
        <meta name="description" content="Pagina di login del sito HomeMade.com">
        <meta name="keywords" content="home made, idee regalo, cosmetici, utensili, fatto in casa">
        <link href="./style.css" rel="stylesheet" type="text/css" media="screen">
    </head>
    <body>
        <div id="page">
            <jsp:include page="headerJSP.jsp" />
            <div id="nav">
                <ul>
                    <li><a href="/loginJSP">Login</a></li>
                </ul>
            </div>
            <div id="content">

                
               
                <h2 class="contentTitle">Errore</h2>
                <p>La pagina alla quale vuoi accedere non &egrave; disponibile, 
                    effettua il logine per accedere al sito</p>
                
                </div>

            </div>
            <div id="clear"></div>
            <jsp:include page="footerJSP.jsp" />
        </div>
        
    </body>
</html>
