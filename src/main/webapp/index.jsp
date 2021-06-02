<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="begin.jsp"%>

<form class="" action="controler" method="get">
    <label class="search_box">

        <svg viewBox="0 0 20 20">
            <use xlink:href="icons/icons.svg#search"></use>
        </svg>
        <input type="text" name="terme" value="" placeholder="Rechercher un livre, un auteur...">
        <input type="hidden" name="action" value="getLivresBy">

    </label>
</form>

<section class="hero">
    <div class="title">
        <span class="color-blue">Prêtez.</span><br>
        <span class="color-green">Recevez.</span><br>
        <span class="color-orange">Échangez !</span><br>
    </div>
</section>

<section class="">

    <h2>Ajoutés récemment :</h2>

    <div class="cards">
        <div class="card">
            <img src="img/book2.jpg" alt="">
        </div>
        <div class="card">
            <img src="img/book3.jpg" alt="">
        </div>
        <div class="card">
            <img src="img/book4.jpg" alt="">
        </div>
        <div class="card">
            <img src="img/book5.jpg" alt="">
        </div>
        <div class="card">
            <img src="img/book6.jpg" alt="">
        </div>
    </div>

</section>

<section class="categories">

    <h2>Catégories :</h2>

    <div class="cards">

        <a href="#" class="card" style="background-image: url('img/roman.jpg')">
            <p>Roman</p>
        </a>

        <a href="#" class="card" style="background-image: url('img/bd.jpg')">
            <p>Bande-dessinée</p>
        </a>

        <a href="#" class="card" style="background-image: url('img/ms.jpg')">
            <p>Manuels scolaires</p>
        </a>

    </div>

</section>

<%@include file="end.jsp"%>
