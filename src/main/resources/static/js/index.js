var productId_detailt;
var redirecTocart = false;
var id;
async function getProductDetail(slugProduct,e,trangProduct) {
	await $.ajax({
		url: `/admin/product-detail-slug/${slugProduct}`,
		type: "GET",
		success: function (response) {
			productId_detailt = response;
		}
	})
	for (let i = 0; i < productId_detailt.length; i++) {
		
		if (trangProduct==="pr") {
			if (e.parentElement.children[0].children[3].children[0].value == productId_detailt[i].capacity) {
				id=productId_detailt[i].id;
			}
		}
		else{
			if (e.parentElement.children[3].children[0].value == productId_detailt[i].capacity) {
				id=productId_detailt[i].id;
			}
		}
	}
	var Product;
	Product = {
		"slug": slugProduct,
		"quantity": 1,
		"productDetailId": id,
	}

	if (localStorage) {

		let ListProduct = localStorage.getItem("ListProduct") ? JSON.parse(localStorage.getItem("ListProduct")) : [];
		if (ListProduct.length > 0) {
			for (var i = 0; i < ListProduct.length; i++) {
				if (JSON.parse(ListProduct[i]).slug === Product.slug &&
					JSON.parse(ListProduct[i]).productDetailId === Product.productDetailId) {
					Product.quantity = Number(Product.quantity) + Number(JSON.parse(ListProduct[i]).quantity);
					ListProduct.splice(i, 1);
				}
			}
		}
		Product = JSON.stringify(Product);
		ListProduct.push(Product);
		localStorage.setItem("ListProduct", JSON.stringify(ListProduct));
		if (redirecTocart == true) {
			window.location = "/gio-hang";
		}

		var cart = $("#cart");
		cart.animate({ opacity: 0 });
		cart.animate({ opacity: 1, });
		document.getElementsByClassName("action-toast")[0].style.display = "block";
		setTimeout(function () {
			document.getElementsByClassName("action-toast")[0].style.display = "none";
		}, 2000);
	}

}


function pushLocalStorage(e) {
	var _url;
	let slugProduct;
	var trangProduct;
	if (e.parentElement.className.match("pr")) {
		trangProduct="pr";
		_url = e.parentElement.children[0].children[0].href.split('/');
				slugProduct = _url[_url.length - 1]
	}
	else {
		_url = e.parentElement.parentElement.children[0].href.split('/');
		slugProduct = _url[_url.length - 1]
	}
	getProductDetail(slugProduct,e,trangProduct);
}
async function buyNow(e) {
	redirecTocart = true;
	pushLocalStorage(e);
}
// let productDetail;
// var item_product = document.getElementsByClassName("card");
// var ProductSize = item_product.length;
// async function getProductDetail_getCapacity() {
// 	for (let index = 0; index < ProductSize; index++) {
// 		
// 		var cost_pr  = document.getElementsByClassName("cost_pr");
// var capacity = document.getElementsByClassName("capacity");
// var optionChild="";
// 			for (let i = 0; i < productDetail.length; i++) {
// 				let optionHtml = `<option value=${productDetail[i].capacity}> ${productDetail[i].capacity}
// 			 ml</option>`;
// 				optionChild+=optionHtml;
// 			}
// 			capacity[index].innerHTML=optionChild
// 			// document.getElementsByClassName("cost_pr")[index].innerHTML = parseFloat(
// 			// 	productDetail[0].cost).toLocaleString("en")
// 			// 	.replace(/,/g, ".") + " đ";

// 			// let selectCapa = document.getElementsByClassName(".capacity");

// 			// selectCapa.addEventListener("change", function () {
// 			// 	for (let i = 0; i < productDetail.length; i++) {
// 			// 		if (selectCapa.value == productDetail[i].capacity) {
// 			// 			document.getElementsByClassName(".cost_pr")[index].innerHTML = parseFloat(productDetail[i].cost)
// 			// 				.toLocaleString("en")
// 			// 				.replace(/,/g, ".") + " đ";
// 			// 		}
// 			// 	}
// 			// });
// 	}
// }

// getProductDetail_getCapacity()

// let selectCapa = document.querySelector("#capacity");
//  document.querySelector("#cost_pr").innerHTML = parseFloat(
//  productDetail[0].cost) .toLocaleString("en")
//  .replace(/,/g, ".") + " đ";
// selectCapa.addEventListener("change", function () {
// 	for (let i = 0; i < productDetail.length; i++) {
// 		if (selectCapa.value == productDetail[i].capacity) {
// 			document.querySelector("#cost_pr").innerHTML = parseFloat(productDetail[i].cost)
// 				.toLocaleString("en")
// 				.replace(/,/g, ".") + " đ";
// 		}
// 	}
// });


async function getMoneyByCapacity(e) {

	var _url;
			let slugProduct;
			if (e.parentElement.parentElement.parentElement.className.match("pr")) {
				_url = e.parentElement.parentElement.parentElement.children[0].children[0].href.split('/');
				slugProduct = _url[_url.length - 1]
			}
			else {
				_url = e.parentElement.parentElement.parentElement.children[0].href.split('/');
				slugProduct = _url[_url.length - 1]
			}
			productDetail = await $.ajax({
				url: `/admin/product-detail-slug/${slugProduct}`,
				type: 'GET',
				success: function (response) {
				}
			})
	var moneyUpdate = e.parentElement.parentElement.children[2].children[0];
	for (let i = 0; i < productDetail.length; i++) {
		if (e.value == productDetail[i].capacity) {
			moneyUpdate.innerHTML=parseFloat(productDetail[i].cost).toLocaleString("en")
			.replace(/,/g, ".") + " đ";;
		}
	}
}