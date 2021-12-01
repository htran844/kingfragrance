//1




function init() {
	var _url = location.href
	var gender = document.getElementsByClassName('gender-checkbox-filter');
	var price = document.getElementsByClassName('price-checkbox-filter');
	var orderby = document.getElementsByClassName('orderby');
	

	if (_url.match("price-desc")) {
		orderby[0].children[2].setAttribute("selected","selected");
	}
	else 
	if (_url.match("price")) {
		orderby[0].children[1].setAttribute("selected","selected");
	}
	if (_url.match('fillter_gender=nam')) {
		gender[0].checked = true;
	}
	else if (_url.match("fillter_gender=nu")) {
		gender[1].checked = true;
	}
	else if (_url.match("fillter_gender=unisex")) {
		gender[2].checked = true;
	}
	if (_url.match('fillter_money=1500000-3000000')) {
		price[0].checked = true;
	}
	else if (_url.match('fillter_money=3000000-5000000')) {
		price[1].checked = true;
	}
	else if (_url.match('fillter_money=5000000-100000000')) {
		price[2].checked = true;
	}
}
init();

var urlParams = new Array();
function sortBy() {

	var sortBy = document.getElementById('sort').value;
	if (location.href.match("order_by")) {
		var href = location.href.replace(location.href.slice(location.href.indexOf("order_by"), location.href.length), "order_by=" + sortBy);
		location.assign(
			href
		)
		return;
	}
	var orderBy;
	if (location.href.match("fillter_gender") || location.href.match("fillter_money")) {
		orderBy = "&&order_by=" + sortBy;
		location.assign(
			location.href + orderBy
		)
	}
	else {
		orderBy = "?order_by=" + sortBy;
		location.assign(
			location.href + orderBy
		)
	}


}
$('.checkbox-filter-sidebar').click(function (e) {
	var hrefLocation = location.href;
	var hrefFilter = "/product";
	var cookieGender = "/product?fillter_gender";
	var cookieMoney = "/product?fillter_money";

	if (hrefLocation.match("/product/thuonghieu")) {
		hrefFilter = location.pathname;
		cookieGender = hrefFilter + "?fillter_gender";
		cookieMoney = hrefFilter + "?fillter_money";
		document.cookie = "/product?fillter_money" + '=; expires=Thu, 01 Dec 2021 00:00:00 GMT;path=/;'
		document.cookie = "/product?fillter_gender" + '=; expires=Thu, 01 Dec 2021 00:00:00 GMT;path=/;'
		if (!hrefLocation.match("fillter_gender") && !hrefLocation.match("fillter_money")) {
			document.cookie = hrefFilter + "?fillter_gender" + '=; expires=Thu, 01 Dec 2021 00:00:00 GMT;path=/product/thuonghieu;'
			document.cookie = hrefFilter + "?fillter_money" + '=; expires=Thu, 01 Dec 2021 00:00:00 GMT;path=/product/thuonghieu;'
		}
	}
	var gender = document.getElementsByClassName('gender-checkbox-filter');
	var price = document.getElementsByClassName('price-checkbox-filter');
	cookieGender = cookieGender.replace(/%20/g, "-");
	cookieMoney = cookieMoney.replace(/%20/g, "-");
	if (!hrefLocation.match("fillter_gender") && !hrefLocation.match("fillter_money")) {
		document.cookie = "/product?fillter_money" + '=; expires=Thu, 01 Dec 2021 00:00:00 GMT;path=/;'
		document.cookie = "/product?fillter_gender" + '=; expires=Thu, 01 Dec 2021 00:00:00 GMT;path=/;'
	}

	if (this.className.match('gender-checkbox-filter')) {
		if (this.checked) {
			for (let index = 0; index < gender.length; index++) {
				gender[index].checked = false;
				this.checked = true;
			}
			urlParams.push('fillter_gender=' + this.value);
			var urlPr = urlParams.toString().replace(/,/g, "&&");
			url = `${hrefFilter}?${urlPr}`;
			document.cookie = url;
			window.location.assign(url)
		}
		else {
			document.cookie = hrefFilter + "?fillter_gender" + '=; expires=Thu, 01 Dec 2021 00:00:00 GMT;path=/product/thuonghieu;'
			document.cookie = "/product?fillter_gender" + '=; expires=Thu, 01 Dec 2021 00:00:00 GMT;path=/;'
			if (!getCookie(cookieGender) && !getCookie(cookieMoney)) {
				window.location.assign(hrefFilter + "?")
				return;
			}
			window.location.assign(cookieMoney + "=" + getCookie(cookieMoney))

		}

	}
	else if (this.className.match('price-checkbox-filter')) {
		if (this.checked) {
			for (let index = 0; index < price.length; index++) {
				price[index].checked = false;
				this.checked = true;
			}
			urlParams.push('fillter_money=' + this.value);
			var urlPr = urlParams.toString().replace(/,/g, "&&");
			url = `${hrefFilter}?${urlPr}`;
			document.cookie = url;
			window.location.assign(url)
		}
		else {
			document.cookie = "/product?fillter_money" + '=; expires=Thu, 01 Dec 2021 00:00:00 GMT;path=/;'
			document.cookie = hrefFilter + "?fillter_money" + '=; expires=Thu, 01 Dec 2021 00:00:00 GMT;path=/product/thuonghieu;'
			if (!getCookie(cookieGender) && !getCookie(cookieMoney)) {
				window.location.assign(hrefFilter + "?")
				return;
			}
			window.location.assign(cookieGender + "=" + getCookie(cookieGender))
		}

	}
	if (getCookie(cookieGender) && getCookie(cookieMoney)) {
		urlParams.splice(0, 1);
		urlParams.push('fillter_gender=' + getCookie(cookieGender));
		urlParams.push('fillter_money=' + getCookie(cookieMoney));
		var url;
		var urlPr = urlParams.toString().replace(/,/g, "&&");
		url = `${hrefFilter}?${urlPr}`;
		window.location.assign(url)

	}


});

function getCookie(cname) {
	let name = cname + "=";
	let decodedCookie = decodeURIComponent(document.cookie);
	let ca = decodedCookie.split(';');
	for (let i = 0; i < ca.length; i++) {
		let c = ca[i];
		while (c.charAt(0) == ' ') {
			c = c.substring(1);
		}
		if (c.indexOf(name) == 0) {
			return c.substring(name.length, c.length);
		}
	}
	return "";

}
