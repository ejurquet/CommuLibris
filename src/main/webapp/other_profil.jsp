<%@ page import="fr.n7.commulibris.entities.Utilisateur" %>
<%@ page import="fr.n7.commulibris.entities.Livre" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="begin.jsp"%>

<%
  Utilisateur u = (Utilisateur) request.getAttribute("utilisateur");
%>

<article class="profile">

  <div class="title">
    <span class="color-blue">#<%= u.getPseudonyme() %></span>
  </div>

  <section class="">

    <h2>Ajoutés récemment :</h2>

    <div class="cards">
      <% for (Livre l : u.getLivres()) { %>
      <div class="card">
        <a href="controler?livreId=<%= l.getId() %>&action=getLivre">
          <img src="<%= l.getImageUrl() %>" alt="<%= l.getNom() %>">
        </a>
      </div>
      <% } %>
    </div>

  </section>

  <div class="buttons">
    <a class="btn" href="controler?action=requestAddAvis&cible=<%= u.getId() %>">Donner un avis</a>
    <a class="btn" href="controler?action=requestAddMessage&cible=<%= u.getId() %>">Envoyer un message</a>
  </div>

</article>

<%@include file="end.jsp"%>
