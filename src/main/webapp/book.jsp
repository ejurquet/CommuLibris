<%@ page import="fr.n7.commulibris.entities.Livre" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="begin.jsp"%>

<%
    Livre livre = (Livre) request.getAttribute("livre");
%>

<article class="book">

    <section class="top_desc">
        <img src="<%= livre.getImageUrl() %>" alt="" class="cover">

        <h2><%= livre.getAuteur() %></h2>
        <h3><%= livre.getNom() %></h3>
    </section>

    <section class="bottom_desc">
        <h2>Description</h2>

        <p><%= livre.getDescription() %></p>

        <h2>Prêté par <a class="color-blue" href="controler?action=accessOtherProfil&cible=<%= livre.getProprietaire().getId() %>">#<%= livre.getProprietaire().getPseudonyme() %></a></h2>

        <img src="" alt="">

        <div class="buttons">
            <button class="contact" type="button" name="button">Contacter</button>
            <button class="borrow" type="button" name="button">Voir le profil</button>
        </div>
    </section>

</article>

<%@include file="end.jsp"%>
