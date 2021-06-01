<%@ page pageEncoding="UTF-8" language="java" %>
<%@include file="begin.jsp"%>

<div class="title">
    <span class="color-red">Erreur !</span>
</div>

<%
    String errorMessage = (String) request.getAttribute("erreur");
    errorMessage = errorMessage != null ? errorMessage : "Erreur inconnue";
%>
<%= errorMessage %>

<%@include file="end.jsp"%>
