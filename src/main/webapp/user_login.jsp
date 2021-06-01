<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="begin.jsp"%>

<article class="user_login">

    <form class="" action="controler" method="get">

        <div class="title">
            <span class="color-blue">H</span class="color-green"><span class="color-green">e</span><span class="color-orange">y</span>,<br>
            Connectez-vous.
        </div>

        <p>Pas encore inscrit ? <a href="#">Inscrivez-vous</a>.</p>

        <!-- <label for="title">Titre :</label> -->
        <input type="text" name="pseudonyme" value="" id="pseudonyme" placeholder="Pseudonyme">

        <input type="password" name="mdp" value="" id="mdp" placeholder="Mot de passe">

        <input type="hidden" name="action" value="authenticateUtilisateur">

        <button class="btn" type="submit">Se connecter</button>

    </form>

</article>

<%@include file="end.jsp"%>
