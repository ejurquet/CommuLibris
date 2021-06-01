<%@ page import="fr.n7.commulibris.entities.Livre" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="begin.jsp"%>
<article class="book">

    <section class="top_desc">
        <img src="img/book5.jpg" alt="" class="cover">

        <%
            Livre livre = (Livre) request.getAttribute("livre");
        %>

        <h2><%= livre.getNom() %></h2>
        <h3><%= livre.getAuteur() %></h3>
    </section>

    <section class="bottom_desc">
        <h2>Description</h2>

        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>

        <h2>Prêté par <span class="color-blue">#<%= livre.getProprietaire() %></span></h2>

        <img src="" alt="">

        <div class="buttons">
            <button class="contact" type="button" name="button">Contacter</button>
            <button class="borrow" type="button" name="button">Emprunter</button>
        </div>
    </section>

</article>

<%@include file="end.jsp"%>