<%@ page import="fr.n7.commulibris.entities.Utilisateur" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="begin.jsp"%>

<%
    Utilisateur u = (Utilisateur) request.getAttribute("utilisateur");
%>

<article class="user_review">

    <form class="" action="controler" method="get">

        <div class="title">
            Laissez un avis à <span class="color-blue">#User</span>
        </div>

        <textarea name="desc" rows="8" cols="40" id="desc" placeholder="Description"></textarea>

        <div class="rate">
            <input type="radio" id="star5" name="note" value="5" />
            <label for="star5" title="text">5 stars</label>
            <input type="radio" id="star4" name="note" value="4" />
            <label for="star4" title="text">4 stars</label>
            <input type="radio" id="star3" name="note" value="3" />
            <label for="star3" title="text">3 stars</label>
            <input type="radio" id="star2" name="note" value="2" />
            <label for="star2" title="text">2 stars</label>
            <input type="radio" id="star1" name="note" value="1" />
            <label for="star1" title="text">1 star</label>
            <div style="clear:both"></div>
        </div>
        <!-- Implanter l'action reviewLivre -->
        <input type="hidden" name="action" value="review">
        <input type="hidden" name="cible" value="<%= u.getId() %>>">

        <button class="btn" type="submit">Envoyer</button>

    </form>

</article>

<%@include file="end.jsp"%>
