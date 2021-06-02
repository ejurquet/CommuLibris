<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="begin.jsp"%>

<article class="book_add">

    <form class="" action="controler" method="get">

        <div class="title">
            Nouveau livre
        </div>

        <!-- <label for="title">Titre :</label> -->
        <input type="text" name="nom" value="" id="title" placeholder="Titre">

        <!-- <label for="author">Auteur :</label> -->
        <input type="text" name="auteur" value="" id="author" placeholder="Auteur">

        <!--<label for="cover" class="cover_label">
            <svg><use xlink:href="icons/icons.svg#photo"></use></svg>
            <span>Uploader une photo</span>
        </label>
        <input type="file" name="couverture" value="" id="cover">-->

        <input type="text" name="image_url" value="" id="image" placeholder="Url de l'image de couverture">

        <!-- <label for="desc">Dexcription :</label> -->
        <textarea name="desc" rows="5" cols="40" id="desc" placeholder="Description"></textarea>

        <input type="hidden" name="action" value="addLivre">

        <h2>Catégories :</h2>
        <label for="roman"> Roman</label>
        <input type="checkbox" id="roman" name="roman" value="Roman">
        <label for="bd"> Bande dessinée</label>
        <input type="checkbox" id="bd" name="bd" value="Bande dessinée">
        <label for="manuel"> Manuel scolaire</label>
        <input type="checkbox" id="manuel" name="manuel" value="Manuel scolaire">

        <button class="btn" type="submit" name="button">Sauvegarder</button>

    </form>

</article>

<%@include file="end.jsp"%>
