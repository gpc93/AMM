    <%-- 
    Document   : clientTableJson
    Created on : 1-lug-2016, 19.16.36
    Author     : gpc
--%>

<%@ page contentType="application/json" pageEncoding="UTF-8"%>
<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<json:array>
    <c:forEach var="prd" items="${listaProdotti}">
        <json:object>
            <json:property name="id" value="${prd.idProdotto}"/>
            <json:property name="nome" value="${prd.nome}"/>
            <json:property name="linkFoto" value="${prd.linkFoto}"/>
            <json:property name="descrizione" value="${prd.descrizione}"/>
            <json:property name="quantita" value="${prd.quantita}"/>
            <json:property name="prezzo" value="${prd.prezzo}"/>  
        </json:object>
    </c:forEach>
</json:array>
