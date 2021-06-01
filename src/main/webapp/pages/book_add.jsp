<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="begin.jsp"%>


<?php
    include('pages/' . $_GET['p'] . '.php');
?>

<form action="actionGetLivre" method="GET">
    Titre : <input type="text" name="titre"><br/>
    Auteur : <input type="text" name="auteur"><br/>

        <input type="submit" name="action" value="actionAddLivre">
</form>

<%@include file="end.jsp"%>
