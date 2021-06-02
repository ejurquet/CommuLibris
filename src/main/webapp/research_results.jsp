<%@ page import="fr.n7.commulibris.entities.Livre" %>
<%@ page import="java.util.List" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="begin.jsp"%>

<section class="">

<%
    List<Livre> results = (List<Livre>) request.getAttribute("livres");
%>

    <h2>Résultats :</h2>

    <div class="cards">

        <% for (Livre livre : results) { %>
            <div class="card">
                <img src="<%= livre.getImageUrl()%>" alt="">
            </div>
        <% } %>

    </div>

</section>

<%@include file="end.jsp"%>
