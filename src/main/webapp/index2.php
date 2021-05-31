<!DOCTYPE html>
<html lang="fr" dir="ltr">
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>Projet</title>
		<link rel="stylesheet" href="css2/main.css">
	</head>
	<body>

		<header>
			<button class="menu_icon" id="menu-toggle">
				<svg><use xlink:href="icons/icons.svg#back"></use></svg>
			</button>

			<img class="logo" src="img/logo.png" alt="">

			<button class="menu_icon" id="menu-toggle">
				<svg><use xlink:href="icons/icons.svg#menu"></use></svg>
			</button>
		</header>

		<main class="main">
			<?php
				include('pages/' . $_GET['p'] . '.php');
			?>
		</main>

	</body>
</html>