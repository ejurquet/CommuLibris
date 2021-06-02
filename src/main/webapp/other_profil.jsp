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

<h2><span class="color-grey">Ses livres</span></h2>

<% for (Livre l : u.getLivres()) { %>
<a href="controler?livreId=<%= l.getId() %>&action=getLivre">
  <div class="card">
    <img src="<%= l.getImageUrl() %>" alt="<%= l.getNom() %>">
    <%= l.getNom() %>
  </div>
</a>
<% } %>

<h2><span class="color-grey">Ses avis reçus</span></h2>

<a href="controler/target=<%= u.getId() %>&action=requestAddAvis">Donner un avis</a>

<h2><span class="color-grey">Ses avis donnés</span></h2>

<%@include file="end.jsp"%>
