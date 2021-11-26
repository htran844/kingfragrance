//1
function renderListOfProducts(products) {
	var productHTML ="";
	var cols = "";
	var ArrayCols="";
	
	if (products.length==0) {
		return;
	}
	products.forEach((products, index) => {
		if (products.productDetails.length==0) {
			return;
		}
		
	
	col=`
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
		ArrayCols+=col;
	})
	productHTML=`
	 <div class="bs-row">
	 ${ArrayCols}
	  </div>
	`
	document.getElementById('productssss').innerHTML=productHTML;
	var card_text1 = document.querySelectorAll('.cart-text1');
	for (var i = 0; i < card_text1.length; i++) {
		card_text1[i].innerHTML = parseFloat(card_text1[i].innerHTML)
				.toLocaleString("en").replace(/,/g, ".")
				+ ' đ';
	}
}



//2
async function getListBrand() {
	
	const brands = await
	$.ajax({
		url : `/brand/crud`,
		type : 'GET',
		success : function(response) {
			
		}
	})
	renderListBrand(brands);
}




//3
 function sortBy() {
 var sortBy = document.getElementById('sort').value;
	
 getProducts_By(sortBy);
 }
 
 
 
 
 //4
 async function getProducts_By(sortBy) {
		showLazy()
		const productBy = await
		$.ajax({
			url : `/getAllProductResult/${sortBy}`,
			type : 'GET',
			success : function(response) {
				console.log(response)
			}
		})
		
	renderListOfProducts(productBy);
		hideLazy()
	}
 
 //5
 
async function getProducts() {
	showLazy()
	const products = await
	$.ajax({
		url : `/getAllProductResult`,
		type : 'GET',
		success : function(response) {
			
		}
	})
 getListBrand()
renderListOfProducts(products);
	hideLazy()

}



//6
 function renderListBrand(listBrand) {
 var brandList = "";
 listBrand.forEach((listBrand, index) =>{
 brandList+=`
 <tr>
 <td><a href="" class="">${listBrand.name}</a></td>
 </tr>
 `
 })
 document.getElementById('myTable').innerHTML=brandList;
 }

 
 
// function showPagination(totalPages, page) {
// const containerPagination = document.querySelector('.pagination')
// let html = ''
// for (let i = 1; i <= totalPages; i++) {
// html += `
// <a href="/admin/${page}?page=${i}" onclick="handlePagination(event)">
// ${i}
// </a>
// `
// }
// containerPagination.innerHTML = html
// }

// runned
getProducts()




