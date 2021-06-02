<%@ page import="fr.n7.commulibris.entities.Livre" %>
<%@ page import="java.util.List" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="begin.jsp"%>

<%
    List<?> livres = (List<?>) request.getAttribute("livres");
%>

<form class="" action="controler" method="get">
    <label class="search_box">

        <svg viewBox="0 0 20 20">
            <use xlink:href="icons/icons.svg#search"></use>
        </svg>
        <input type="text" name="terme" id="terme" value="" placeholder="Rechercher un livre, un auteur...">
        <input type="hidden" name="action" value="getLivresBy">

    </label>
</form>

<section class="hero">
    <div class="title">
        <span class="color-blue">Prêtez.</span><br>
        <span class="color-green">Recevez.</span><br>
        <span class="color-orange">Échangez !</span><br>
    </div>
</section>

<section class="">

    <% if (!livres.isEmpty()) { %>
    <h2>Ajoutés récemment :</h2>

    <div class="cards">
    <%
        for (Object o : livres) {
        Livre l = (Livre) o;
    %>
        <div class="card">
            <a href="controler?action=getLivre&livreId=<%= l.getId() %>"><img src="<%= l.getImageUrl() %>" alt="<%= l.getNom() %>"></a>
        </div>
    <% } %>
    </div>
    <% } %>

</section>

<section class="categories">

    <h2>Catégories :</h2>

    <div class="cards">

        <a href="#" class="card" style="background-image: url('img/roman.jpg')">
            <p>Romans</p>
        </a>

        <a href="#" class="card" style="background-image: url('img/bd.jpg')">
            <p>Bandes dessinées</p>
        </a>

        <a href="#" class="card" style="background-image: url('img/ms.jpg')">
            <p>Manuels scolaires</p>
        </a>

    </div>

</section>

<%@include file="end.jsp"%>
