<%@ page import="fr.n7.commulibris.entities.Utilisateur" %>
<%@ page import="fr.n7.commulibris.entities.Livre" %>
<%@ page import="fr.n7.commulibris.entities.Avis" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="begin.jsp"%>

<%
  Utilisateur u = (Utilisateur) request.getAttribute("utilisateur");
%>

<article class="profile">

  <div class="title">
    <a class="color-blue" href="controler?action=accessOtherProfil&cible=<%= u.getId() %>">#<%= u.getPseudonyme() %></a>
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

  <section>

    <h2>Avis reçus :</h2>

    <table>
      <tr>
        <th>Utilisateur</th>
        <th>Note</th>
        <th>Avis</th>
      </tr>

      <% for (Avis avis : u.getAvisRecu()) { %>
      <tr>
        <td><a href="controler?action=accessOtherProfil&cible=<%= avis.getSource().getId() %>"><%= avis.getSource().getPseudonyme() %></a></td>
        <td><%= avis.getNote() %> / 5</td>
        <td><%= avis.getTexte() %></td>
      </tr>
      <% } %>
    </table>
  </section>

  <section>

    <h2>Avis donnés :</h2>

    <table>
      <tr>
        <th>Utilisateur</th>
        <th>Note</th>
        <th>Avis</th>
      </tr>

      <% for (Avis avis : u.getAvisDonnes()) { %>
      <tr>
        <td><a href="controler?action=accessOtherProfil&cible=<%= avis.getCible().getId() %>"><%= avis.getCible().getPseudonyme() %></a></td>
        <td><%= avis.getNote() %> / 5</td>
        <td><%= avis.getTexte() %></td>
      </tr>
      <% } %>
    </table>
  </section>

  <% if(request.getSession().getAttribute("utilisateur") != null) { %>
  <div class="buttons">
    <a class="btn" href="controler?action=requestAddAvis&cible=<%= u.getId() %>">Donner un avis</a>
    <a class="btn" href="controler?action=requestStartConv&cible=<%= u.getId() %>">Envoyer un message</a>
  </div>
  <% } %>

</article>

<%@include file="end.jsp"%>
