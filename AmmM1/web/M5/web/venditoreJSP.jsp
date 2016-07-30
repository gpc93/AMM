<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>HomeMade.com</title>
        <meta charset="UTF-8">
        <meta name="author" content="Gian Piero Cozzula">
        <meta name="description" content="Pagina venditore del sito HomeMade.com">
        <meta name="keywords" content="home made, idee regalo, cosmetici, utensili, fatto in casa">
        <link href="./style.css" rel="stylesheet" type="text/css" media="screen">
        
        <script type="text/javascript" src="js/jquery-2.2.4.min.js"></script>
        <script type="text/javascript" src="js/popoutEliminazione.js"></script>
    </head>
    <body>
        <div id="page">
            <jsp:include page="headerJSP.jsp" />
            
            <!-- sezione di navigazione del sito-->
            <div id="nav">
                <ul>      
                    <li><a href="./venditoreJSP.jsp">Home</a></li>
                    <li><a href="Login?exit=${true}">Logout</a></li>                     
                </ul>
            </div>
            <div id="sideBar">
                <h2>Area Area Personale dell'utente ${venditore.nome}</h2>
                <ul>
                    <li><a href="Venditore?sceltaMenu=${"inserisci"}">Inserisci Annuncio</a></li>
                    <li><a href="Venditore?sceltaMenu=${"modifica"}">Modifica Annuncio</a></li>
                    
                </ul>
            </div>
            <!--CONTENT-->
            <div id="content"> 
            <c:choose>
                
                <c:when test="${modifica.equals(true)}">
                    <div id="objTab">

                        <table>
                            <tr>
                                <th class="clientTabLarge">Foto</th>
                                <th>Nome</th>
                                <th>Quantit&agrave;</th>
                                <th>Prezzo</th>
                                <th>Modifica</th>
                            </tr>
                    <c:forEach var="prodotto" items="${listaProdotti}" >
                               <tr>
                                <td class="imgCol"><img title="${prodotto.nome}" src="${prodotto.linkFoto}" alt="${prodotto.descrizione}" class="objImg"/></td>
                                <td>${prodotto.getNome()}</td>
                                <td><div>${prodotto.quantita}</div></td>
                                <td>${prodotto.getPrezzo()}</td>
                                <td><a href="Venditore?idProdotto=${prodotto.idProdotto}"><img title="modifica" alt="modifica annuncio" src="./img/iconaMod.png" width="20" height="20"></a>
                                    <a href="Venditore?eliminaProdotto=${prodotto.idProdotto}" onClick="return conferma()" ><img title="elimina" alt="elimina annuncio" src="./img/iconaX.gif" width="20" height="20"> </a>
                                </td>
                            </tr> 

                    </c:forEach>
                        </table>
                    </div>
                    
                    
                </c:when>
                <c:when test="${formModifica.equals(true)}">
                    <h2 class="contentTitle">Modifica articolo</h2>
                <!--form modifica articolo-->
                <div class="input_form" id="vendorContent">
                    
                    <form method="post" action="Venditore">
                         <input type="hidden" name="idProdotto" value="${modProdotto.idProdotto}" />
                        <div>
                            <label for="objName">Nome dell'oggetto:
                                <input type="text" name="objName" id="objName" value="${modProdotto.nome}" />
                            </label>
                        </div>
                        <div>
                            <label for="urlPhoto">Carica una foto:
                                <input type="url" name="urlPhoto" id="urlPhoto" value="${modProdotto.linkFoto}"/>
                                
                            </label>
                        </div>
                        <textarea rows="5" cols="25" name="objDescr" id="objDescr" >${modProdotto.descrizione}</textarea>
                        <div>
                            <label for="objQt">Quantit&agrave;:
                                <input type="number" name="objQt" id="objQt" min="0" value="${modProdotto.quantita}"/>
                            </label>
                        </div>
                        <div>
                            <label for="objPrice">Prezzo:
                                <input type="number" name="objPrice" id="objPrice" min="0" step="0.1" value="${modProdotto.prezzo}"/>
                            </label>        
                        </div>
                        
                            <input type="submit" name="modificaProdotto" class="formButton" value="Modifica Prodotto"/>
                            <input type="reset" class="formButton" value="Reset"/>
                        
                    </form>
                </div>
                    
                    
                    
                    
                    
                </c:when>
                
                <c:when test="${inserisci.equals(true)}">
                    <h2 class="contentTitle">Aggiungi articolo</h2>
                <!--form pubblicazione articolo-->
                
                <div class="input_form" id="vendorContent">
                    
                    <form method="post" action="Venditore">
                        <div>
                            <label for="objName">Nome dell'oggetto:
                                <input type="text" name="objName" id="objName" placeholder="Inserire il nome del prodotto" />
                            </label>
                        </div>
                        <div>
                            <label for="urlPhoto">Carica una foto:
                                <input type="url" name="urlPhoto" id="urlPhoto" placeholder="Inserire url della foto dell'oggetto"/>
                                
                            </label>
                        </div>
                        <textarea rows="5" cols="25" name="objDescr" id="objDescr" placeholder="Inserire una descrizione del prodotto..."></textarea>
                        <div>
                            <label for="objQt">Quantit&agrave;:
                                <input type="number" name="objQt" id="objQt" min="0"/>
                            </label>
                        </div>
                        <div>
                            <label for="objPrice">Prezzo:
                                <input type="number" name="objPrice" id="objPrice" min="0" step="0.1"/>
                            </label>        
                        </div>
                        
                            <input type="submit" name="submit" class="formButton" value="Pubblica"/>
                            <input type="reset" class="formButton" value="Reset"/>
                        
                    </form>
                </div>
                </c:when>
                <c:otherwise>
                    <div>Scegliere un'opzione dall'elenco a sinistra</div>
                    
                </c:otherwise>
                
                
            </c:choose>
              
                

            </div>
            <div id="clear"></div>
             <jsp:include page="footerJSP.jsp" />

    </div>
    </body>
    
</html>
