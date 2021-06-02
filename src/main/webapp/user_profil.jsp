<%@ page import="fr.n7.commulibris.entities.Utilisateur" %>
<%@ page import="fr.n7.commulibris.entities.Livre" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="begin.jsp"%>

<%
    Utilisateur u = (Utilisateur) request.getAttribute("utilisateur");
%>

<div class="title">
    <span class="color-blue"><%= u.getPseudonyme() %></span>
</div>

<h2><span class="color-grey">Vos livres</span></h2>

<% for (Livre l : u.getLivres()) { %>
    <a href="controler?livreId=<%= l.getId() %>&action=getLivre">
        <div class="card">
            <img src="<%= l.getImageUrl() %>" alt="<%= l.getNom() %>">
        </div>
    </a>
<% } %>

<a href="book_add.jsp">
    <svg class="icon"><use xlink:href="icons/icons.svg#book"></use></svg>
    <span>Ajouter un livre</span>
</a>

<h2><span class="color-grey">Vos avis reçus</span></h2>

<!--
    A faire :
        style
        avis correctement
-->

<%@include file="end.jsp"%>
