<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
                    <li><a href="./descrizione.html">Descrizione</a></li>
                </ul>
            </div>
            <div id="sideBar">
                <h2>Istruzioni</h2>
                <p> Pagina di login al sito, è possibile effettuare l'accesso come venditore o compratore</p>
            </div>
            <!--CONTENT-->
            
            <c:if test="${empty loggedIn}">
                <div id="content">
                    <!-- form di login -->
                    <h2 class="contentTitle">Login</h2>
                    <div class="input_form" id="loginContent">

                        <form method="post" action="Login">
                            <label for="username">Username</label>
                            <input type="text" name="username" id="username" placeholder="Inserire l'username" />
                            <br/>
                            <label for="pswd">Password</label>
                            <input type="password" name="pswd" id="pswd" placeholder="•••••••••">
                            <br/>
                            <input type="submit" name="submit" class="formButton" value="Login" />
                            <input type="reset" class="formButton" value="Reset" />


                        </form>
                        <c:if test="${not empty loginError}">
                            <div id="loginError">Credenziali non valide, reinserire i dati</div>
                       
                        </c:if> 
                    </div>
                </div>
            </c:if>
            <c:if test="${not empty loggedIn}">
                <div id="content">
                    <div class="input_form" id="loginContent">
                        <div id="loginError">Utente gi&agrave; loggato
                        <c:if test="${venditoreLogged.equals(true)}">
                            <a href="venditoreJSP.jsp">Torna alla Home</a>
                        </c:if>
                        <c:if test="${clienteLogged.equals(true)}">
                            <a href="clienteJSP.jsp">Torna alla Home</a>
                        </c:if>
                        </div>
                    </div>
                </div>
            </c:if>
            
            <div id="clear"></div>
            <jsp:include page="footerJSP.jsp" />
        </div>
        
    </body>
    
</html>
