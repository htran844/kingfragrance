//1

function init() {
	var _url = location.href
	var gender = document.getElementsByClassName('gender-checkbox-filter');
	var price = document.getElementsByClassName('price-checkbox-filter');
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
function renderListOfProducts(products) {
	var productHTML = "";
	var cols = "";
	var ArrayCols = "";

	if (products.length == 0) {
		return;
	}
	products.forEach((products, index) => {
		if (products.productDetails.length == 0) {
			return;
		}


		col = `
		 <div class="bs-col tn-25-10">
		  <li
		 class="woman-attr product type-product post-3855 status-publish first
		 instock product_cat-armaf product_tag-chanel-coco-mademoselle
		 product_tag-club product_tag-club-de-nuit product_tag-coco-chanel
		 has-post-thumbnail shipping-taxable purchasable product-type-variable">
		 <a href="${/product-detailts/ + products.product.slug}"
		 class="woocommerce-LoopProduct-link woocommerce-loop-product__link"><img
		 width="100%" src="${products.product.image}"
		 class="attachment-woocommerce_thumbnail size-woocommerce_thumbnail"
		 alt="Armaf Club de Nuit Woman" loading="lazy"
		 sizes="(max-width: 300px) 100vw, 300px"> </a>
		 <div class="name-product">
		 <a href=""
		 class="woocommerce-LoopProduct-link woocommerce-loop-product__link">
		 <span class="brand" >${products.product.brand}</span>
		 </a><a href=""
		 class="product-name woocommerce-loop-product__title"
		 >${products.product.name}</a>
		 </div> <span class="price"> <span class="cart-text1"

		 > ${products.productDetails[0].cost}</span>
		 </span>
		 </li>
		 </div> 
	`
		ArrayCols += col;
	})
	productHTML = `
	 <div class="bs-row">
	 ${ArrayCols}
	  </div>
	`
	document.getElementById('productssss').innerHTML = productHTML;
}
//
//
//
// //2
// async function getListBrand() {
//	
// const brands = await
// $.ajax({
// url : `/brand/crud`,
// type : 'GET',
// success : function(response) {
//			
// }
// })
// renderListBrand(brands);
// }
//
//
//
//
// //3
function sortBy() {
	var sortBy = document.getElementById('sort').value;
	getProducts_By(sortBy);
}

// 
// 
// 
// //4
async function getProducts_By(sortBy) {
	showLazy()
	const productBy = await
		$.ajax({
			url: `/getAllProductResult/${sortBy}`,
			type: 'GET',
			success: function (response) {
				console.log(response)
			}
		})

	renderListOfProducts(productBy);
	hideLazy()
}
// 
// //5
// 
// async function getProducts() {
// showLazy()
// const products = await
// $.ajax({
// url : `/getAllProductResult`,
// type : 'GET',
// success : function(response) {
//			
// }
// })
// getListBrand()
// renderListOfProducts(products);
// hideLazy()
//
// }
//
//
//
// //6
// function renderListBrand(listBrand) {
// var brandList = "";
// listBrand.forEach((listBrand, index) =>{
// brandList+=`
// <tr>
// <td><a href="/product/thuonghieu/${listBrand.name}"
// class="">${listBrand.name}</a></td>
// </tr>
// `
// })
// document.getElementById('myTable').innerHTML=brandList;
// }
//
// 
// 
// // function showPagination(totalPages, page) {
// // const containerPagination = document.querySelector('.pagination')
// // let html = ''
// // for (let i = 1; i <= totalPages; i++) {
// // html += `
// // <a href="/admin/${page}?page=${i}" onclick="handlePagination(event)">
// // ${i}
// // </a>
// // `
// // }
// // containerPagination.innerHTML = html
// // }
//
// // runned
// //getProducts();

// alert(url)
// });
// 
// 
// 



var urlParams = new Array();
$('.checkbox-filter-sidebar').click(function (e) {

	var gender = document.getElementsByClassName('gender-checkbox-filter');
	var price = document.getElementsByClassName('price-checkbox-filter');

	var hrefLocation = location.href;
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
			url = `/product?${urlPr}`;
			document.cookie = url;
			window.location.assign(url)
		}
	}
	if (this.className.match('price-checkbox-filter')) {
		if (this.checked) {
			for (let index = 0; index < price.length; index++) {
				price[index].checked = false;
				this.checked = true;
			}
			urlParams.push('fillter_money=' + this.value);
			var url;
			var urlPr = urlParams.toString().replace(/,/g, "&&");
			url = `/product?${urlPr}`;
			document.cookie = url;
			window.location.assign(url)
		}
	}

	if (getCookie("/product?fillter_gender") && getCookie("/product?fillter_money")) {
		urlParams.splice(0, 1);
		urlParams.push('fillter_gender=' + getCookie("/product?fillter_gender"));
		urlParams.push('fillter_money=' + getCookie("/product?fillter_money"));
		var url;
		var urlPr = urlParams.toString().replace(/,/g, "&&");
		url = `/product?${urlPr}`;
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
