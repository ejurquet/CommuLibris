<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<%@include file="begin.jsp"%>

<div class="title">
    <span class="color-green">Bravo !</span>
</div>

<%
    String successMessage = (String) request.getAttribute("success");
    successMessage = successMessage != null ? successMessage : "Vous avez réussi ! Mais aucune idée de ce que vous étiez en train de faire ...";
%>
<p><%= successMessage %></p>

<%@include file="end.jsp"%>
