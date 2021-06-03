<%@ page import="fr.n7.commulibris.entities.Utilisateur" %>
<%@ page import="fr.n7.commulibris.entities.Livre" %>
<%@ page import="fr.n7.commulibris.entities.Avis" %>
<%@ page import="fr.n7.commulibris.entities.Conversation" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="begin.jsp"%>

<%
    Utilisateur u = (Utilisateur) request.getAttribute("utilisateur");
%>

<article class="profile">

    <div class="title">
        Bienvenue <span class="color-blue">#<%= u.getPseudonyme() %></span>
    </div>

    <section class="">

        <h2>Vos livres :</h2>

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
        <a class="btn" href="book_add.jsp">
            <svg class="icon2"><use xlink:href="icons/icons.svg#book"></use></svg>
            <span>Ajouter un livre</span>
        </a>
    </div>

    <section>

        <h2>Conversations :</h2>

        <table>
            <tr>
                <th>Converation</th>
                <th>Participants</th>
            </tr>

            <% for (Conversation conversation : u.getConversations()) { %>
            <tr>
                <td><a href="controler?action=accessConv&cible=<%= conversation.getId() %>"><%= conversation.getNom() %></a></td>
                <td>
                    <ul>
                        <% for (Utilisateur utilisateur : conversation.getParticipants()) { %>
                        <li><a href="controler?action=accessOtherProfil&cible=<%= utilisateur.getId() %>"><%= utilisateur.getPseudonyme() %></a></li>
                        <% } %>
                    </ul>
                </td>
            </tr>
            <% } %>
        </table>
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

</article>

<%@include file="end.jsp"%>
