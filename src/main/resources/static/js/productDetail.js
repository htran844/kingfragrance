
// product Detail
 const url = window.location.href.split('/')
 let productDetail;
 let slugProduct = url[url.length - 1]
 async function getProductDetail(){
 productDetail = await $.ajax({
 url: `/admin/product-detail-slug/${slugProduct}`,
 type: 'GET',
 })
 for (let i = 0; i < productDetail.length; i++) {
 let optionHtml = ` ${productDetail[i].capacity}
 ml`;
 let optionValue = productDetail[i].capacity;
 let optionalCon = document.createElement("option");
 optionalCon.value = optionValue;
  optionalCon.innerHTML = optionHtml;
 document.querySelector("#capacity").appendChild(optionalCon);
 }

 let selectCapa = document.querySelector("#capacity");
 document.querySelector("#cost_pr").innerHTML = parseFloat(
 productDetail[0].cost) .toLocaleString("en")
 .replace(/,/g, ".") + " đ";
 selectCapa.addEventListener("change", function () {
 for (let i = 0; i < productDetail.length; i++) {
 if (selectCapa.value == productDetail[i].capacity) {
 document.querySelector("#cost_pr").innerHTML = parseFloat(productDetail[i].cost)
 .toLocaleString("en")
 .replace(/,/g, ".") + " đ";
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
        "slug": slugProduct,
        "quantity": quantity,
        "productDetailId": productDetail[i].id,
      }
    }
  }
  if (localStorage) {
	 
    let ListProduct = localStorage.getItem("ListProduct") ? JSON.parse(localStorage.getItem("ListProduct")) : [];
if (ListProduct.length>0) {
    		for (var i = 0; i < ListProduct.length; i++) {
    			if (JSON.parse(ListProduct[i]).slug ===Product.slug && 
    					JSON.parse(ListProduct[i]).productDetailId ===Product.productDetailId ) {
    				Product.quantity=Number(Product.quantity)+Number(JSON.parse(ListProduct[i]).quantity);
    					    				ListProduct.splice(i,1);
    			}
    		}
} 
Product= JSON.stringify(Product);
    		ListProduct.push(Product);
      var cart = $("#cart");
		cart.animate({opacity:0 } );
		cart.animate({opacity:1, });
    localStorage.setItem("ListProduct",JSON.stringify(ListProduct));
     ListProduct = localStorage.getItem("ListProduct") ? JSON.parse(localStorage.getItem("ListProduct")) : [];
	var length_cart = ListProduct.length;
	document.getElementById('number_cart').innerHTML=length_cart;
    document.getElementsByClassName("action-toast")[0].style.display="block";
	setTimeout(function(){ 
		document.getElementsByClassName("action-toast")[0].style.display="none";
	 }, 2000);
  }

}





