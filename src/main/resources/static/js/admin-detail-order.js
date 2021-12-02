let search = "null";
const url = window.location.href.split('/')
let orderId = url[url.length - 1]
let statusNow = '';
// thay đổi trạng thái đơn hàng
async function changeStatus(event){
	event.preventDefault();
	let status = event.target.value
	const conf = confirm('Xác nhận chuyển trạng thái?')
	if (!conf){
		event.target.value =statusNow
		return
	} 
	switch (status) {
	  case "delivery":
		if(statusNow != "waiting"){
			alert("Chỉ đơn hàng đang chờ mới có thể chuyển qua trạng thái giao hàng")
			event.target.value =statusNow
			return
			}
		// call api chuyển sang giao hàng
		showLazy()
		let resultDe = await $.ajax({
			url: `/admin/delivery/${orderId}`,
			type: 'put',
		})
		hideLazy()
		if (resultDe.length>0) {
			alert('chuyển trạng thái Giao hàng thành công')
			statusNow = 'delivery';
		} else {
			alert('Chuyển sang giao hàng thất bại')
		}
	    break;
	  case "cancelled":
		if(statusNow != "waiting"){
			alert("Chỉ đơn hàng đang chờ mới có thể chuyển qua trạng thái hủy")
			event.target.value =statusNow
			return
			}
			// call api sang hủy
			showLazy()
			let resultCa = await $.ajax({
				url: `/admin/cancel/${orderId}`,
				type: 'put',
			})
			hideLazy()
			if (resultCa.length>0) {
				alert('Huỷ đơn hàng thành công')
				statusNow = 'cancelled';
			} else {
				alert('Hủy đơn hàng thất bại')
			}
	    break;
	  case "refund":
	    if(statusNow != "delivery"){
			alert("Chỉ đơn hàng đang giao mới có thể chuyển qua trạng thái hoàn")
			event.target.value =statusNow
			return
			}
			// call api sang hoàn
			showLazy()
			const resultRe = await $.ajax({
				url: `/admin/refund/${orderId}`,
				type: 'put',
			})
			hideLazy()
			if (resultRe.length>0) {
				alert('Hoàn đơn hàng thành công')
				statusNow = 'refund';
			} else {
				alert('Hoàn đơn hàng thất bại')
			}
	    break;
	  case "success":
		if(statusNow != "delivery"){
			alert("Chỉ đơn hàng đang giao mới có thể chuyển qua trạng thái thành công")
			event.target.value =statusNow
			return
			}
		// call api sang thành công
			showLazy()
			const resultSu = await $.ajax({
				url: `/admin/success/${orderId}`,
				type: 'put',
			})
			hideLazy()
			if (resultSu.length>0) {
				alert('Đơn hàng giao thành công')
				statusNow = 'success';
			} else {
				alert('đơn hàng giao thất bại')
			}
	    break;
	  default:
		alert("Không thể chuyển sang trạng thái đang chờ")
	    event.target.value =statusNow
	}
}

async function getInfo(){
	const order = await $.ajax({
		url: `/admin/order/${orderId}`,
		type: "GET"
	})
	statusNow = order.status
	document.querySelector('#name-nn').value = order.name;
	document.querySelector('#phone-nn').value = order.phone;
	document.querySelector('#address-nn').value = order.address;
	document.querySelector('#note-nn').value = order.note;
	document.querySelector('#status-order').value = order.status;
}
getInfo()
async function getDetail(){
	const details = await $.ajax({
		url: `/admin/invoices/${orderId}`,
		type: "GET"
	})
	
	details.forEach( async (detail, index) =>{
		const product = await $.ajax({
			url: `/admin/product-mainid/${detail.productId}`,
			type: 'GET'
		})
		addProductDetail(detail.id, detail.productId, product.name,detail.capacity, detail.quantity,detail.cost)
		tinhtien()
	})
	 
}
getDetail()


function createDetail(name = '',capacity = '', cost = '', id='', detailId = '' ){
	let html = `<td>${name}</td>
					<td>${capacity}ml</td>
					<td>${cost}</td>
					<td><button  onclick="addProductDetail('${detailId}','${id}','${name}', '${capacity}', '1','${cost}')">Thêm</button></td>`;
	const moreProduct = document.createElement('tr');
	moreProduct.innerHTML = html;
	document.querySelector('.add-product-detail').appendChild(moreProduct)
}
function addProductDetail(idDetail = '', id = '', name = '', capacity = '', quantity = '1', cost = ''){
	let html = `		<div > 
	<input type="text" name="detail-id" style="display: none" class="order-detail-id" placeholder="Tên" value="${idDetail}" required />
	                        	<input type="text" style="display: none" name="id" class="product-id" placeholder="Tên" value="${id}" required />
	                        </div>	
		<div > <label class="mb-1">Tên sản phẩm</label>
	                        	<input type="text" name="namepro" class="product-name" placeholder="Tên" value="${name}" required />
	                        </div>
	                        <div >
						<label class="mb-1">Dung tích (ml)</label>
	                        	<input type="number" name="capacity" class="product-capacity" placeholder="Dung tích" value="${capacity}"  />	                        
	                        </div>
	                        <div >
						<label class="mb-1">Số lượng</label>
		                        <input type="number" name="quantity" class="product-quantity" min="1" max="100" placeholder="Số lượng" value="${quantity}" onChange="tinhtien()" required />
	                        </div>
	                        <div >
						<label class="mb-1">Đơn giá</label>
		                        <input type="number" name="cost" class="product-cost" min="0"  placeholder="Giá" value="${cost}" required />
	                        </div>
	                        
	                        <div class=" more-product-delete-btn mb-2 ml-2 px-2" onclick="tinhtien()">
								<button type="button" id="bt" onclick="$(this).parent().parent().remove()">
									Xóa
								</button>                       
                        	</div>`;
	const moreE = document.createElement('div');
	moreE.className = 'more-product-item d-flex align-items-center';
	moreE.innerHTML = html;
	document.querySelector('.more-product-wrap').appendChild(moreE);
	tinhtien()
	return moreE
}
// tinh tong tien
let tong = 0;
function tinhtien(){
	let quantitys = document.getElementsByName('quantity');
	let costs = document.getElementsByName('cost');
	tong = 0;
	for(let i = 0; i< quantitys.length; i++){
		
		tong += Number(quantitys[i].value) * Number(costs[i].value)
		
	}
	document.getElementById('tong-tien').innerHTML = tong.toLocaleString('en') + ' VNĐ'
}
//setTimeout(function(){ tinhtien(); }, 1000);
async function getProducts() {
	document.querySelector('.add-product-detail').innerHTML = '';
	if(!checkOnline()){
		alert('Không có kết nối internet')
		return
	}
	
	
	const products =  await	$.ajax({
  	url: `/admin/product-main/2/${search}/null`,
  	type: 'GET'
  })
	if(products.length == 0 || search === 'null'){return}
	let dem = 3;
	if(products.length < 3){dem = products.length}

	for(let i=0; i<dem; i++){
		const proDetails =  await	$.ajax({
		  	url: `/admin/product-detail/${products[i].id}`,
		  	type: 'GET'
  		})
		proDetails.forEach((detail, index)=>{
			createDetail(products[i].name, detail.capacity, detail.cost, products[i].id, detail.id);
		})
	}
		
}
const processChange = debounce(() => searchProduct())
let inputSearch = $('.action-search input')
function searchProduct() {
	
	search = inputSearch.val()
	
	if(!search.length>0){
		search="null"
	}
	
	getProducts()
	
}
$('.action-search input').on('keyup', processChange)

// update
async function updateOrder(){
	// cap nhat bang order
	if(statusNow != 'waiting'){
		alert('Chỉ đơn hàng đang chờ mới có thể cập nhật')
		return
	}
	let name = document.querySelector('#name-nn').value 
	let phone = document.querySelector('#phone-nn').value 
	let address = document.querySelector('#address-nn').value 
	let note = document.querySelector('#note-nn').value
	let order = {
		id: orderId,
		name: name,
		phone: phone,
		address: address,
		note: note,
		totalCost: tong
	}
	const updateOd = await $.ajax({
		url: '/admin/order/update',
		headers: {
  		  "Content-Type":"application/json"
  	  },
		type: "PUT",
		data: JSON.stringify(order),
	})
	const orderDetails = document.querySelectorAll(
    ".more-product-wrap .more-product-item"
  );
	// delete all orderdetail
	const deleteAll = await $.ajax({
		url: `/admin/order-detail/update/${orderId}`,
		type: "DELETE",
	})
	console.log(deleteAll)
// cap nhat order detail
	for (let item of orderDetails) {
		let capacity = item.querySelector(".product-capacity").value;
		let orderDetailId = item.querySelector(".order-detail-id").value;
		let cost = item.querySelector(".product-cost").value;
        let quantity = item.querySelector(".product-quantity").value;
        let name = item.querySelector(".product-name").value;
        let productId = item.querySelector(".product-id").value;
		let total = Number(cost) * Number(quantity)
		let orderDt = {
			id: orderDetailId,
			orderId: orderId,
			productId: productId,
			capacity: capacity,
			cost: cost,
			quantity: quantity,
			total: total,
		}
		
		const updateDetail = await $.ajax({
		url: '/admin/order-detail/update',
		headers: {
  		  "Content-Type":"application/json"
  	  },
		type: "PUT",
		data: JSON.stringify(orderDt),
	})
	
	}
	alert(updateOd)
}
