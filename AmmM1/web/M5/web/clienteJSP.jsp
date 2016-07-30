<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>HomeMade.com</title>
        <meta charset="UTF-8">
        <meta name="author" content="Gian Piero Cozzula">
        <meta name="description" content="Pagina cliente del sito HomeMade.com">
        <meta name="keywords" content="home made, idee regalo, cosmetici, utensili, fatto in casa">
        <link href="./style.css" rel="stylesheet" type="text/css" media="screen">
        
        <script type="text/javascript" src="js/jquery-2.2.4.min.js"></script>
        <script type="text/javascript" src="js/search.js"></script>
        
        
    </head>
    <body>
        <div id="page">           
                <jsp:include page="headerJSP.jsp" />
            
            <!-- sezione di navigazione del sito-->
            <div id="nav">
                <ul>
                    <li><a href="./clienteJSP.jsp">Home</a></li>
                    <li><a href="Login?exit=${true}">Logout</a></li>
                </ul>
            </div>
            <div id="sideBar">
                <h2>Area Personale dell'utente ${cliente.nome}</h2>
                <ul>
                    <li><a>Carrello</a></li>
                    <li><a>Traccia Ordine</a></li>
                    <li><a>Storico Acquisti</a></li>
                    </ul>
            </div>
            <!--CONTENT-->
            <div id="content">
                <h2 class="contentTitle">Articoli</h2>
                <!-- tabella degli annunci -->
                <c:choose>
                    <c:when test="${resoconto.equals(true)}">
                         <div class="contentConferma">
                        <form method="post" action="Cliente">                        
                           
                                <h2 class="contentTitle">Resoconto</h2>
                                <input type="hidden" name="idProdotto" value="${nuovoProdotto.idProdotto}" />
                                <h3>Nome Prodotto:</h3><p>${nuovoProdotto.nome}</p>
                                <h3>Descrizione:</h3><p>${nuovoProdotto.descrizione}</p>
                                <h3>Quantit&agrave;</h3>
                                <p>
                                <select name="quantitaProdotto" id="quantitaProdotto">
                                    <c:forEach begin="1" end="${nuovoProdotto.quantita}" var="i">
                                        <option value="${i}">${i}</option>

                                     </c:forEach>    

                                </select>
                                </p>
                                <h3>Prezzo:</h3><p>${nuovoProdotto.prezzo}</p>
                                <input type="submit" name="confermaAcquisto" id="acquistoButton" value="Conferma Acquisto" /> 
                                
                            
                            
                            </form>
                            </div>
                    </c:when>
                    <c:when test="${transazione.equals(false)}">
                        <p>Fondi insufficienti per eseguire la transazione</p>
                    </c:when>
                    <c:when test="${transazione.equals(true)}">
                        <p>Transazione eseguita con successo</p>
                    </c:when>
                    <c:otherwise>
                    <div id="objTab">
                            
                             <input id="ricerca" placeholder="Ricerca un articolo"/>
                        <table>
                            <tr id="headers">
                                <th class="clientTabLarge">Foto</th>
                                <th>Nome</th>
                                <th>Quantit&agrave;</th>
                                <th>Prezzo</th>
                                <th>Aggiungi</th>
                            </tr>
                            
                            <c:forEach var="prodotto" items="${listaProdotti}" >
                               <tr>
                                <td class="imgCol"><img title="${prodotto.nome}" src="${prodotto.linkFoto}" alt="${prodotto.descrizione}" class="objImg"/></td>
                                <td>${prodotto.getNome()}</td>
                                <td>
                                    
                                        <c:choose>
                                            <c:when test="${prodotto.getQuantita()>0}">
                                                <div>${prodotto.quantita}</div>
                                            </c:when>
                                            <c:otherwise>
                                                <div>Prodotto terminato</div>
                                            </c:otherwise>
                                                
                                        </c:choose>
                                    
                                    </td>
                                <td>${prodotto.getPrezzo()}</td>
                                <td><a href="Cliente?idProdotto=${prodotto.idProdotto}"><img title="add" alt="aggiungi al carrello" src="./img/tabIcon.png" width="20" height="20"></a></td>
                            </tr> 
                            </c:forEach>


                        </table>
                    </div>
                    </c:otherwise>

                        
                        
                </c:choose>
                
                
            </div>
            <div id="clear"></div>
             <jsp:include page="footerJSP.jsp" />
        </div>
    </body>
</html>
