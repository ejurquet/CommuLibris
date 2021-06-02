<%@ page import="fr.n7.commulibris.entities.Utilisateur" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="begin.jsp"%>

<%
    Utilisateur cible = (Utilisateur) request.getAttribute("cible");
%>

<article class="user_start_conv">

    <form class="" action="controler" method="get">

        <div class="title">
            Envoyez un message à <span class="color-blue">#<%= cible.getPseudonyme() %></span>
        </div>

        <textarea name="desc" rows="8" cols="40" id="desc" placeholder="Message"></textarea>

        <input type="hidden" name="action" value="startConv">
        <input type="hidden" name="cible" value="<%= cible.getId() %>">

        <button class="btn" type="submit">Envoyer</button>

    </form>

</article>

<%@include file="end.jsp"%>
