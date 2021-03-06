var fillCart = "";
var totalCost = 0;
var ListProduct = localStorage.getItem("ListProduct") ? JSON.parse(localStorage.getItem("ListProduct")) : [];
async function fillProductToCart() {
 showLazy()
	for (var i = 0; i < ListProduct.length; i++) {
		let Dataproduct;

		try {
			 Dataproduct = {
				slug : JSON.parse(ListProduct[i]).slug,
				productDetailId : JSON.parse(ListProduct[i]).productDetailId,
			}
		 } catch (error) {
			 alert("Lỗi truy vấn! Nhấn ok để về trang chủ và đặt lại hàng!")
			 localStorage.clear();
			 window.location="/"
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
				 try {
					var qtt = JSON.parse(ListProduct[i]).quantity;
					var cost = products.productDetails[0].cost;
					totalCost += Number(cost) * Number(qtt);
				 } catch (error) {
			 alert("Lỗi truy vấn! Nhấn ok để về trang chủ và đặt lại hàng!")

					 localStorage.clear();
					 window.location="/"
					 
				 }
		 fillCart +=`   <hr>
			 <li class="item-product bs-row">
			 
	                                    <div class="icon-close" onclick="removeCart(this)"><span style="display:none;">${i}</span><i class="fas fa-times"></i></div>
	                                    <div class="bs-col tn-15">
	                                        <img src="${products.product.image}" alt="" width="100%">
	                                    </div>
	                                    <div class="bs-col tn-70 pt-3 pl-4">
	                                        <h3 class="title">
	                                            <span>${products.product.name}</span>-<span>${products.productDetails[0].capacity} ml</span>
	                                        </h3>
	                                       
	                                        <h4 class="cost_pr">
	                                            <span class="cost_product_cart">${products.productDetails[0].cost}</span><span> đ</span>
	                                        </h4>
	                                        <div class="quantity" >
	                                            <input type="number" id="" class="input-text qty text" step="1" min="1" 
	                                                max="" name="" value="${JSON.parse(ListProduct[i]).quantity}" title="SL" size="4" placeholder=""
	                                                inputmode="numeric"  onchange="changeMoney(this)">
	                                               
	                                        </div>
	                                    
	                                    </div>
	                                      
	                                </li>
	                                 
	                                `	
	}
	document.getElementById('list_pr').innerHTML = fillCart
	tinhTien();
	getAllProduct();
	var cost_product_cart = document.querySelectorAll(".cost_product_cart");
	for (var i = 0; i < cost_product_cart.length; i++) {
		cost_product_cart[i].innerHTML=parseFloat(cost_product_cart[i].innerHTML)
		 .toLocaleString("en")
		 .replace(/,/g, ".");
	}

 hideLazy()
}

fillProductToCart()


function tinhTien(cost) {
    var sum = 0;
    var ItemProduct = document.querySelectorAll('.item-product');
    for (let index = 0; index < ItemProduct.length; index++) {
        var getMoney = ItemProduct[index].children[2].children[1].children[0];
        
      
        var money = getMoney.innerHTML.replace(/\./g, '');
       

        var quantity = ItemProduct[index].children[2].children[2].children[0].value;
        money = Number(money) * Number(quantity);
        sum += money;
    }
 

    document.getElementById('totalMoney').innerHTML = parseFloat(sum).toLocaleString("en")
	 .replace(/,/g, ".") ;
    document.getElementById('tamTinh').innerHTML = parseFloat(sum).toLocaleString("en")
	 .replace(/,/g, ".") ;
}	
function changeMoney(e) {
    tinhTien();
   
    var index = e.parentElement.parentElement.parentElement.children[0].children[0].innerHTML;
    
   
	 Product = {
	            "slug": JSON.parse(ListProduct[index]).slug,
	            "quantity": e.value,
	            "productDetailId": JSON.parse(ListProduct[index]).productDetailId
	            }
	  
	 ListProduct[index]=
		 JSON.stringify(Product);

 localStorage.setItem("ListProduct",JSON.stringify(ListProduct));  
  fillCart = "";
  totalCost = 0;
 fillProductToCart()
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
    ListProduct = localStorage.getItem("ListProduct") ? JSON.parse(localStorage.getItem("ListProduct")) : [];
	var length_cart = ListProduct.length;
	document.getElementById('number_cart').innerHTML=length_cart;
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
function RemoveAllCart() {
	localStorage.removeItem("ListProduct");
	var list_pr = document.getElementById("list_pr").innerHTML="";
	 getAllProduct();
	 $("html, body").animate({
		scrollTop: 0
	}, 1000);

	
}


$(document).scroll(function () {
	var lengthOfListPr =$("#list_pr").height();
	
	if ($(this).scrollTop() >= lengthOfListPr-265) {
	$(".module-right-money").addClass("active")

	} else {
		$(".module-right-money").removeClass("active");

	}
});

