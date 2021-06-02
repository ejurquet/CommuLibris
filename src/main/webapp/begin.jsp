<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="fr" dir="ltr">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CommuLibris</title>
    <link rel="stylesheet" href="css2/main.css">
</head>
<body>

<header>
    <!-- return -->
    <button class="menu_icon" id="menu-back" onclick="window.history.back()">
        <svg><use xlink:href="icons/icons.svg#back"></use></svg>

    </button>

    <!-- logo -->
    <a href="controler">
        <img class="logo" src="img/logo.png" alt="">
    </a>

    <!-- menu -->
    <button class="menu_icon" id="menu-toggle">
        <svg><use xlink:href="icons/icons.svg#menu"></use></svg>

    </button>
</header>

<nav class="nav">
    <ul>
        <% if(request.getSession().getAttribute("utilisateur") == null) { %>
        <li>
            <a href="index.jsp">
                <svg class="icon"><use xlink:href="icons/icons.svg#home"></use></svg>
                <span>Acceuil</span>
            </a>
        </li>
        <li>
            <a href="user_login.jsp">
                <svg class="icon"><use xlink:href="icons/icons.svg#user"></use></svg>
                <span>Se connecter</span>
            </a>
        </li>
        <!--<li>
            <a href="#">
                <svg class="icon"><use xlink:href="icons/icons.svg#categories"></use></svg>
                <span>Catégories</span>
            </a>
        </li>-->

        <% } else { %>

        <li>
            <a href="controler?action=accessProfil">
                <svg class="icon"><use xlink:href="icons/icons.svg#user"></use></svg>
                <span>Voir mon profil</span>
            </a>
        </li>
        <li>
            <a href="#">
                <svg class="icon"><use xlink:href="icons/icons.svg#message"></use></svg>
                <span>Messages</span>
            </a>
        </li>
        <li>
            <a href="#">
                <svg class="icon"><use xlink:href="icons/icons.svg#book"></use></svg>
                <span>Mes livres</span>
            </a>
        </li>
        <li>
            <a href="controler?action=logoutUtilisateur">
                <svg class="icon"><use xlink:href="icons/icons.svg#logout"></use></svg>
                <span>Se déconnecter</span>
            </a>
        </li>
        <% } %>
    </ul>
</nav>

<main class="main">