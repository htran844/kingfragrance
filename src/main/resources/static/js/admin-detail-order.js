let search = "null";
const url = window.location.href.split('/')
let orderId = url[url.length - 1]
async function getInfo(){
	const order = await $.ajax({
		url: `/admin/order/${orderId}`,
		type: "GET"
	})
	
	document.querySelector('#name-nn').value = order.name;
	document.querySelector('#phone-nn').value = order.phone;
	document.querySelector('#address-nn').value = order.address;
	document.querySelector('#note-nn').value = order.note;
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
	let html = `		<div > <label class="mb-1">ID</label>
	<input type="text" name="detail-id" style="display: none" class="order-detail-id" placeholder="Tên" value="${idDetail}" required />
	                        	<input type="text" name="id" class="product-id" placeholder="Tên" value="${id}" required />
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
	                        
	                        <div class=" more-product-delete-btn mb-2 ml-2 px-2">
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
function tinhtien(){
	let quantitys = document.getElementsByName('quantity');
	let costs = document.getElementsByName('cost');
	let tong = 0;
	for(let i = 0; i< quantitys.length; i++){
		
		tong += Number(quantitys[i].value) * Number(costs[i].value)
		
	}
	document.getElementById('tong-tien').innerHTML = tong.toLocaleString('en')
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
	let name = document.querySelector('#name-nn').value 
	let phone = document.querySelector('#phone-nn').value 
	let address = document.querySelector('#address-nn').value 
	let note = document.querySelector('#note-nn').value
	let order = {
		id: orderId,
		name: name,
		phone: phone,
		address: address,
		note: note
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
	for (let item of orderDetails) {
		let capacity = item.querySelector(".product-capacity").value;
		let orderDetailId = item.querySelector(".order-detail-id").value;
console.log(orderDetailId)
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
		console.log(orderDt)
		const updateDetail = await $.ajax({
		url: '/admin/order-detail/update',
		headers: {
  		  "Content-Type":"application/json"
  	  },
		type: "PUT",
		data: JSON.stringify(orderDt),
	})
	console.log(updateDetail)
	}
	alert(updateOd)
}
