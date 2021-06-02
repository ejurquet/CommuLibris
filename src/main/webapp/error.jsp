<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<%@include file="begin.jsp"%>

<div class="title">
    <span class="color-orange">Erreur !</span>
</div>

<%
    String errorMessage = (String) request.getAttribute("erreur");
    errorMessage = errorMessage != null ? errorMessage : "Quelque chose s'est mal passé. Réessayez ultèrieurement !";
%>
<p><%= errorMessage %></p>

<%@include file="end.jsp"%>
