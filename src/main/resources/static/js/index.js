
function pushLocalStorage(e) {
	var url;
	let slugProduct;
	if (e.parentElement.className.match("pr")) {
		url = e.parentElement.children[0].children[0].href.split('/');
		slugProduct = url[url.length - 1]
	}
	else {
		url = e.parentElement.parentElement.children[0].href.split('/');
		slugProduct = url[url.length - 1]
	}
	getProductDetail(slugProduct);
	_productDetailId = _productDetailId[0].id;
	console.log(_productDetailId);
	var Product;
	Product = {
		"slug": slugProduct,
		"quantity": 1,
		"productDetailId": _productDetailId,
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
		var cart = $("#cart");
		cart.animate({opacity:0 } );
		cart.animate({opacity:1, });
		document.getElementsByClassName("action-toast")[0].style.display="block";
		setTimeout(function(){ 
			document.getElementsByClassName("action-toast")[0].style.display="none";
		 }, 1500);

	}
}



var _productDetailId = "";
async function getProductDetail(slugProduct) {
	productDetailId = await $.ajax({
		url: `/admin/product-detail-slug/${slugProduct}`,
		type: 'GET',
	})
	_productDetailId = productDetailId;
}




	


