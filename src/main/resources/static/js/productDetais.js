const url = window.location.href.split('/')  
let productDetail;
let slugProduct = url[url.length - 1]
async function getProductDetail(){
	 productDetail =  await	$.ajax({	
			url: `/admin/product-detail-slug/${slugProduct}`,
			type: 'GET',	
	})
	  

	for (let i = 0; i < productDetail.length; i++) {
		  let optionHtml = ` <option value="">${productDetail[i].capacity} ml</option>`;
		  let optionValue = productDetail[i].capacity;
		  let optionalCon = document.createElement("option");
		  optionalCon.value = optionValue;
		  optionalCon.innerHTML = optionHtml;
		  document.querySelector("#capacity").appendChild(optionalCon);
		}
		let selectCapa = document.querySelector("#capacity");
		selectCapa.addEventListener("change", function () {
		  for (let i = 0; i < productDetail.length; i++) {
		    if (selectCapa.value == productDetail[i].capacity) {
		      document.querySelector("#cost_pr").innerHTML = parseFloat(
		        productDetail[i].cost
		      )
		        .toLocaleString("en")
		        .replace(/,/g, ".");
		    }
		  }
		});
}
getProductDetail()

function pushLocalStorage() {
  var Product;
  var capacity = document.getElementById('capacity').value;
  var money = document.getElementById("cost_pr").innerHTML;
  var quantity = document.getElementById('quantity_pr').value;
  for (let i = 0; i < productDetail.length; i++) {
    if (productDetail[i].capacity == capacity) {
      Product = {
        "productId": productDetail[i].productId,
        "quantity": quantity,
        "productDetailId": productDetail[i].id,
      }
    }
  }

  if (localStorage) {
	  Product= JSON.stringify(Product);
    let ListProduct = localStorage.getItem("ListProduct") ? JSON.parse(localStorage.getItem("ListProduct")) : [];
    	ListProduct.push(Product);
    	
    	delete ListProduct[3];
    localStorage.setItem("ListProduct",JSON.stringify(ListProduct) );
  }
}


//loi 
//async function requestServerCart() {
//	let dataFra = {
//			so1:3,
//		}
//	let cart = await $.ajax({
//	        url: '/gio-hang',
//	  	  	headers: {
//	  		  "Content-Type":"application/json"
//	  	  		},
//	  		type: 'GET',
//	  		data: JSON.stringify(dataFra),
//	});
//
//
//}
//requestServerCart()
