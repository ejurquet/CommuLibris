<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="begin.jsp"%>

<article class="user_signup">

    <form class="" action="controler" method="get">

        <div class="title">
            <span class="color-blue">H</span>
            <span class="color-green">e</span>
            <span class="color-orange">y</span>,<br>
            Inscrivez-vous.
        </div>

        <p>Déjà inscrit ? <a href="user_login.jsp">Connectez-vous</a>.</p>

        <input type="text" name="pseudonyme" value="" id="pseudonyme" placeholder="Pseudonyme">

        <input type="password" name="mdp" value="" id="mdp" placeholder="Mot de passe">

        <input type="hidden" name="action" value="createUtilisateur">

        <button class="btn" type="submit">S'inscrire</button>

    </form>

</article>

<%@include file="end.jsp"%>
