
//Exelent little functions to use any time when class modification is needed
function hasClass(ele, cls) {
	return !!ele.className.match(new RegExp('(\\s|^)' + cls + '(\\s|$)'));
}

function addClass(ele, cls) {
	if (!hasClass(ele, cls)) ele.className += " " + cls;
}

function removeClass(ele, cls) {
	if (hasClass(ele, cls)) {
		var reg = new RegExp('(\\s|^)' + cls + '(\\s|$)');
		ele.className = ele.className.replace(reg, ' ');
	}
}

//Add event from js the keep the marup clean
function init() {
	removeClass(document.body, "opened");
	document.getElementById("menu-toggle").addEventListener("click", toggleMenu);
	document.querySelectorAll(".nav ul li a").forEach(item => {
		item.addEventListener('click', toggleMenu)
	});
}

//The actual fuction
function toggleMenu() {
	if (!hasClass(document.body, "opened")) {
		addClass(document.body, "opened");
	} else {
		removeClass(document.body, "opened");
	}
}

//Prevent the function to run before the document is loaded
document.addEventListener('readystatechange', function() {
	if (document.readyState === "complete") {
		init();
	}
});
