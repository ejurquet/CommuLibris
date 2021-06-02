<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<%@include file="begin.jsp"%>

<%
    String errorMessage = (String) request.getAttribute("erreur");
    errorMessage = errorMessage != null ? errorMessage : "Quelque chose s'est mal passé. Réessayez ultèrieurement !";
%>

<article>

    <div class="title">
        <span class="color-orange">Erreur !</span>
    </div>

    <p><%= errorMessage %></p>

    <p><a href="controler">Revenir sur le droit chemin</a></p>

</article>

<%@include file="end.jsp"%>
