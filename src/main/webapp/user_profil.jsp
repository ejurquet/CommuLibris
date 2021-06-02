<%@ page import="fr.n7.commulibris.entities.Utilisateur" %>
<%@ page import="fr.n7.commulibris.entities.Livre" %>
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

</article>

<%@include file="end.jsp"%>
