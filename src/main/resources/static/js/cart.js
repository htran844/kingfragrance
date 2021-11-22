var fillCart = "";
var ListProduct = localStorage.getItem("ListProduct") ? JSON.parse(localStorage.getItem("ListProduct")) : [];
async function fillProductToCart() {
	for (var i = 0; i < ListProduct.length; i++) {

		let Dataproduct = {
			slug : JSON.parse(ListProduct[i]).slug,
			productDetailId : JSON.parse(ListProduct[i]).productDetailId,
		}
		const products = await
		$.ajax({
			url : `/getCartFromLocalStorage`,
			headers : {
				"Content-Type" : "application/json"
			},
			type : "GET",
			data : Dataproduct,
			  success: function (response) {
				  
			    }
		})	
		 fillCart +=` 
			 <li class="item-product bs-row">
	                                    <div class="icon-close" onclick="removeCart(this)"><span style="display:none;">${i}</span><i class="fas fa-times"></i></div>
	                                    <div class="bs-col tn-25">
	                                        <img src="${products.product.image}" alt="" width="100%">
	                                    </div>
	                                    <div class="bs-col tn-70 pt-5">
	                                        <h3 class="title">
	                                            <span>${products.product.name}</span>-<span>${products.productDetails[0].capacity}</span>
	                                        </h3>
	                                       
	                                        <h4 class="cost_pr">
	                                            <span class="cost_product_cart">${products.productDetails[0].cost}</span><span>đ</span>
	                                        </h4>
	                                        <div class="quantity pt-5" >
	                                            <input type="number" id="" class="input-text qty text" step="1" min="1"
	                                                max="" name="" value="${JSON.parse(ListProduct[i]).quantity}" title="SL" size="4" placeholder=""
	                                                inputmode="numeric" onclick="changeMoney()">
	                                        </div>
	                                    </div>
	                                </li>`	
	}
	document.getElementById('list_pr').innerHTML = fillCart
	tinhTien();
	getAllProduct();
}

fillProductToCart()


function tinhTien() {
    var sum = 0;
    var ItemProduct = document.querySelectorAll('.item-product');
    for (let index = 0; index < ItemProduct.length; index++) {
        var getMoney = ItemProduct[index].children[2].children[1].children[0];
        
        var money = getMoney.innerHTML;
        console.log(money);

        var quantity = ItemProduct[index].children[2].children[2].children[0].value;
        money = Number(money) * Number(quantity);
        sum += money;
    }
    document.getElementById('totalMoney').innerHTML = sum + '';
    document.getElementById('tamTinh').innerHTML = sum + '';
}	
function changeMoney() {
    tinhTien();
}
function getAllProduct() {
    var arrayCart = document.querySelectorAll(".section-cart .module-left li");
    if (arrayCart.length == 0) {
        $('.module-cart').hide();
        $('#tm-main').show();
    }
    else {
        $('#tm-main').hide();
        $('.module-cart').show();
    }
}
function removeCart(e) {
	var index = e.children[0].innerHTML;
	if (localStorage) {
         	ListProduct.splice(index,1);     	
            localStorage.setItem("ListProduct",JSON.stringify(ListProduct));
            e.parentElement.remove();
            fillCart = "";
            fillProductToCart();
            getAllProduct();
	}
}






