<%@ page import="fr.n7.commulibris.entities.Livre" %>
<%@ page import="java.util.List" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="begin.jsp"%>

<section class="">

<%
    String terme = (String) request.getAttribute("terme");
    List<?> livres = (List<?>) request.getAttribute("livres");
%>

    <h2>Résultats pour <span class="color-blue"><%= terme %></span></h2>

    <% if (!livres.isEmpty()) { %>
    <div class="cards">

        <%
            for (Object o : livres) {
            Livre l = (Livre) o;
        %>
            <div class="card">
                <a href="controler?action=getLivre&livreId=<%= l.getId() %>"><img src="<%= l.getImageUrl()%>" alt="<%= l.getNom() %>"></a>
            </div>
        <% } %>

    </div>
    <% } else { %>
    <h2>Aucun résultat</h2>
    <% } %>

</section>

<%@include file="end.jsp"%>
