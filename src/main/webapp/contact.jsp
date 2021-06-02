<%@ page import="fr.n7.commulibris.entities.Conversation" %>
<%@ page import="fr.n7.commulibris.entities.Message" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<%@include file="begin.jsp"%>

<article class="conversation">

<%
    Conversation conv = new Conversation();
%>

<% for (Message m : conv.getMessages()) { %>
    <div class="card">
        <%= m.getAuteur()%> : <%= m.getTexte() %>
    </div>
<% } %>


<form class="" action="controler" method="get">

    <div class="title">
        Votre message :
    </div>

    <textarea name="desc" rows="8" cols="40" id="desc" placeholder="Écrivez votre message ici."></textarea>

    <!-- Implanter l'action addMessage -->
    <input type="hidden" name="action" value="addMessage">
    <input type="hidden" name="conversation" value="<%= conv %>>">

    <button class="btn" type="submit">Envoyer</button>

</form>

</article>

<%@include file="end.jsp"%>
