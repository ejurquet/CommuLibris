<!DOCTYPE html>
<html lang="fr" dir="ltr">
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>Projet</title>
		<link rel="stylesheet" href="css/master.css">
	</head>
	<body>

		<nav class="nav">
			<ul>
				<li>
					<a href="#">
						<svg class="icon"><use xlink:href="icons/icons.svg#home"></use></svg>
						<span>Acceuil</span>
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

		<!-- MAIN PART -->
		<main class="main">

			<?php
				include('pages/' . $_GET['p'] . '.php');
			?>

		</main>

		<!-- <footer class="footer">

			<button class="home_icon">
				<svg viewBox="0 0 100 100">
					<use xlink:href="icons/icons.svg#home" fill="#0e63d9"></use>
				</svg>
			</button>

			<button class="search_icon">
				<svg viewBox="0 0 100 100">
					<use xlink:href="icons/icons.svg#search"></use>
				</svg>
			</button>

			<button class="user_icon">
				<svg viewBox="0 0 100 100">
					<use xlink:href="icons/icons.svg#user"></use>
				</svg>
			</button>

		</footer> -->


		</div>

		<script src="js/menu.js" charset="utf-8"></script>
	</body>
</html>
