<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="begin.jsp"%>


<?php
    include('pages/' . $_GET['p'] . '.php');
?>

<form action="actionGetLivre" method="GET">
    Id : <input type="text" name="id"><br/>

        <input type="submit" name="action" value="actionAddLivre">
</form>

<%@include file="end.jsp"%>
