<%@include file="begin.jsp"%>
<h1>Bienvenue sur CommuLibris !</h1>

<nav class="nav">
    <ul>
        <li>
            <a href="#">
                <svg class="icon"><use xlink:href="icons/icons.svg#home"></use></svg>
                <span>Accueil</span>
            </a>
        </li>
        <li>
            <a href="#">
                <svg class="icon"><use xlink:href="icons/icons.svg#categories"></use></svg>
                <span>Catégories</span>
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
            <a href="#">
                <svg class="icon"><use xlink:href="icons/icons.svg#logout"></use></svg>
                <span>Se déconnecter</span>
            </a>
        </li>
    </ul>
</nav>

<div class="page_wrapper">

    <header class="header">
        <h1>CommuLibris</h1>

        <button class="menu_icon" id="menu-toggle">
            <svg viewBox="0 0 100 100">
                <use xlink:href="icons/icons.svg#menu"></use>
            </svg>
        </button>
    </header>

    <?php
		include('pages/' . $_GET['p'] . '.php');
	?>

</div>

<%@include file="end.jsp"%>
