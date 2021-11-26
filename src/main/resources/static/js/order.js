async function getIdOrder() {
	
	 var name = document.getElementById('name').value;
	var phone = document.getElementById('phone').value;
	var email = document.getElementById('email').value;
	var address = document.getElementById('address').value;
	if (name==="") {
		alert("Không được để trống tên!")
		return;
	}
	if (email==="") {
		alert("Không được để trống email!")
		return;
	}
	if (phone==="") {
		alert("Không được để trống số điện thoại!")
		return;
	}
	
	if (address==="") {
		alert("Không được để trống address!")
		return;
	}
	var confirmDatHang = confirm("Bạn có chắc chắn muốn đặt hàng không?")
	if (!confirmDatHang) {
		return;
	}
	
	var userId = null;
	var comfirmAt = null;
	var finishedAt = null;
	var currentdate = new Date();
	var note = document.getElementById('note').value;
	var status = "waiting";
	var total_cost = totalCost;
	const order = {
		name : name,
		phone : phone,
		email : email,
		note : note,
		address : address,
		status : status,
		totalCost : total_cost,
		comfirmAt : comfirmAt,
		finishedAt : finishedAt,
		userId : userId
	}
	 showLazy()
	 const orderId = await $.ajax({
	 url : `/admin/orders`,
	 headers : {
	 "Content-Type" : "application/json"
	 },
	 type : "POST",
	 data : JSON.stringify(order),
			
	 success : function(response) {
				
	 }
	 })
	 var id = orderId;
	for (var i = 0; i < ListProduct.length; i++) {

		let Dataproduct = {
			slug : JSON.parse(ListProduct[i]).slug,
			productDetailId : JSON.parse(ListProduct[i]).productDetailId,
		}
		products = await
		$.ajax({
			url : `/getCartFromLocalStorage`,
			headers : {
				"Content-Type" : "application/json"
			},
			type : "GET",
			data : Dataproduct,
			success : function(response) {
				
			}
		})
		
		let DataOrderDetail = {
	 orderId:id,
	 productId: products.productDetails[0].productId,
	 capacity:products.productDetails[0].capacity,
	 cost: products.productDetails[0].cost,
	 quantity:JSON.parse(ListProduct[i]).quantity,
		 total: Number(products.productDetails[0].cost)*Number(JSON.parse(ListProduct[i]).quantity)
	 }
		
	 await $.ajax({
	 url : `/admin/order-detail`,
	 headers : {
	 "Content-Type" : "application/json"
	 },
	 type : "POST",
	 data : JSON.stringify(DataOrderDetail),
				
	 success : function(response) {
		 console.log(response)
	 }
	 })
	} 
	hideLazy()
	setTimeout(() => {
		localStorage.clear();
		 alert("Đặt hàng thành công!")
		 
}, 200);
	

}



