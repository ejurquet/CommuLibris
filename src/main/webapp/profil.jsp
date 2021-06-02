<%@ page import="fr.n7.commulibris.entities.Utilisateur" %>
<%@ page import="fr.n7.commulibris.entities.Livre" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="begin.jsp"%>

<%
    Utilisateur u = (Utilisateur) request.getAttribute("utilisateur");
%>

<div class="title">
    <span class="color-blue"><%= u.getPseudonyme() %>></span>
</div>

<div class="title">
    <span class="color-grey">Vos livres</span>
</div>

<% for (Livre l : u.getLivres()) { %>
    <a href="controler?livreId=<%= l.getId() %>>&mdp=dd&action=getLivre">
        <div class="card">
            <img src="<%= l.getImageUrl() %>" alt="<%= l.getNom() %>">
        </div>
    </a>
<% } %>

<a href="book_add.jsp">
    <svg class="icon"><use xlink:href="icons/icons.svg#book"></use></svg>
    <span>Ajouter un livre</span>
</a>

<div class="title">
    <span class="color-grey">Vos avis</span>
</div>

<!--
    A faire :
        style
        avis correctement
-->

<%@include file="end.jsp"%>
