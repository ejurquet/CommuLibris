<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<%@include file="begin.jsp"%>

<%
    String successMessage = (String) request.getAttribute("success");
    successMessage = successMessage != null ? successMessage : "Vous avez réussi ! Mais aucune idée de ce que vous étiez en train de faire ...";
%>

<article>

    <div class="title">
        <span class="color-green">Bravo !</span>
    </div>

    <p><%= successMessage %></p>

    <p><a href="controler">Revenir sur la page d'accueil</a></p>

</article>

<%@include file="end.jsp"%>
