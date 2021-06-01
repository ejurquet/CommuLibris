<%--
  Created by IntelliJ IDEA.
  User: elios
  Date: 01/06/2021
  Time: 18:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" language="java" %>
<%@include file="begin.jsp"%>

<h1>Erreur ç</h1>

<%
    String errorMessage = (String) request.getAttribute("erreur");
    errorMessage = errorMessage != null ? errorMessage : "Erreur inconnue";
%>
<%= errorMessage %>

<%@include file="end.jsp"%>
