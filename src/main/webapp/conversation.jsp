<%@ page import="fr.n7.commulibris.entities.Conversation" %>
<%@ page import="fr.n7.commulibris.entities.Message" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<%@include file="begin.jsp"%>

<article class="conversation">

<%
    Conversation conv = (Conversation) request.getAttribute("conv");
%>

<form class="message" action="controler" method="get">

    <div class="title">
        <%= conv.getNom() %>
    </div>

    <section class="chat">


    </section>

    <% for (Message m : conv.getMessages()) { %>
        <p><b><%= m.getAuteur().getPseudonyme() %></b> : <%= m.getTexte() %></p>
    <% } %>

    <textarea name="desc" rows="2" cols="40" id="desc" placeholder="Message"></textarea>

    <!-- Implanter l'action addMessage -->
    <input type="hidden" name="action" value="addMessage">
    <input type="hidden" name="cible" value="<%= conv.getId() %>">

    <button class="btn" type="submit">Envoyer</button>

</form>

</article>

<%@include file="end.jsp"%>
