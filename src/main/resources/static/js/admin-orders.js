let search = "null";
let status = "null";
activeNavItem('orders')
async function getOrders(page = 1) {
	if (!checkOnline()) {
		alert('Không có kết nối internet')
		return
	}

	showLazy()

	const orders = await $.ajax({
		url: `/admin/orders/${search}/${status}`,
		method: 'GET',
	})
	let don =0;
	orders.forEach((order)=>{
		if(order.status == 'waiting'){
			don ++;
		//	document.querySelector('.total-orders span').innerHTML = don;
			document.getElementById('tongsodon').innerHTML = don
		}
		
	})
	
	let total = orders.length
	showPagination(total)
	let onePage = 5
	let listOrder = orders.slice((page -1)*onePage, page*onePage)
	renderListOfOrders(listOrder, page)
	hideLazy()
}
getOrders(1)
function showPagination(leng){
	document.querySelector(".pagination").innerHTML = ''
	let tongsotrang = leng / 5;
	if(tongsotrang > parseInt(tongsotrang)){
		tongsotrang = parseInt(tongsotrang) +1;
	} else{
		tongsotrang = parseInt(tongsotrang)
	}
	
	let html = ``;
	let curent = 'curently';
	for(let i=0; i<= tongsotrang-1; i++){
		html += `<div class="page-link " onclick="getOrders(${i+1})">${i+1}</div>`;
	}
	const pa = document.createElement("li");
	pa.className = "page-item";
	pa.innerHTML = html;
	document.querySelector(".pagination").appendChild(pa);
}

function renderListOfOrders(orders, page) {
	//document.querySelector('.total-orders span').innerHTML = 0
	let html = ''
	orders.forEach((order, i) => {
		
		// set default value
		let stt = order.status;
		switch (stt) {
		  case "waiting":
		    var waiting = "selected";
		    break;
		  case "delivery":
		   var delivery = "selected";
		    break;
		  case "cancelled":
		    var cancelled = "selected";
		    break;
		  case "refund":
		    var refund = "selected";
		    break;
		  case "success":
		    var success = "selected";
		    break;
		  default:
		    ;
		}
		// tạo html
		html += `
        <tr>
                    <td> ${(page-1)*5 + i+1} </td>
                    <td>
						<a href="/admin/orders/${order.id}">${order.id}</a>
					  </td>
                    <td>
                        ${order.name}
                    </td>
                    <td>
                        ${order.phone}
                    </td>
                    
                    <td> ${formatDate(order.createAt)}</td>
                    <td> ${order.totalCost.toLocaleString()} </td>
                    
                    <td class="order-status ${order.status}"> 
					<select id="status-order" class="form-select" onchange="changeStatus(event)" data-id="${order.id}" status-now="${order.status}">
					  <option value="waiting" ${waiting}>waiting</option>
					  <option value="delivery" ${delivery}>delivery</option>
					  <option value="cancelled" ${cancelled}>cancelled</option>
					  <option value="refund" ${refund}>refund</option>
					  <option value="success" ${success}>success</option>
					</select>
					 </td>
                    <td class="button-action ${order.status}">
                        <button class="detail-order">
                            <a href="/admin/orders/${order.id}">
                                Chi tiết
                            </a>
                        </button>
                        
                        <a href="/invoices/${order.id}" target="_blank">
							<button class="bill-order">
								Hóa đơn
							</button>
						</a>
                        
                    </td>
                </tr>
        
        `
	})
	createTable('admin-orders', html, orders.length)
}
const processChange = debounce(() => searchOrder())

// search orders
let inputSearch = $('.orders-action .action-search input')
function searchOrder() {
	search = inputSearch.val()
	if(!search.length>0){
		search="null"
	}
	getOrders()
}

$('.orders-action .action-search input').on('keyup', processChange)
function handleFilter(obj) {
	status = obj.value
	getOrders()
}
// change o select
async function changeStatus(event){
	event.preventDefault();
	let statusNow = event.target.getAttribute('status-now')
	const idOrder = event.target.getAttribute('data-id')
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
			url: `/admin/delivery/${idOrder}`,
			type: 'put',
		})
		hideLazy()
		if (resultDe.length>0) {
			alert('chuyển trạng thái Giao hàng thành công')
			event.target.setAttribute("status-now", "delivery");
			document.querySelector('.total-orders span').innerHTML--
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
				url: `/admin/cancel/${idOrder}`,
				type: 'put',
			})
			hideLazy()
			if (resultCa.length>0) {
				alert('Huỷ đơn hàng thành công')
				event.target.setAttribute("status-now", "cancelled");
				document.querySelector('.total-orders span').innerHTML--
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
				url: `/admin/refund/${idOrder}`,
				type: 'put',
			})
			hideLazy()
			if (resultRe.length>0) {
				alert('Hoàn đơn hàng thành công')
				event.target.setAttribute("status-now", "refund");
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
				url: `/admin/success/${idOrder}`,
				type: 'put',
			})
			hideLazy()
			if (resultSu.length>0) {
				alert('Đơn hàng giao thành công')
				event.target.setAttribute("status-now", "success");
			} else {
				alert('đơn hàng giao thất bại')
			}
	    break;
	  default:
		alert("Không thể chuyển sang trạng thái đang chờ")
	    event.target.value =statusNow
	}
}


// change trang thai don hang
/*
async function changeStatusOrder(event){
	event.preventDefault();
	const conf = confirm('Xác nhận chuyển trạng thái?')
	if (!conf) return
	let statusNow = event.target.getAttribute('status-now')
	const idOrder = event.target.getAttribute('data-id')
	// lấy value the select option
	let status = event.target.parentNode.previousElementSibling.querySelector("#status-order").value
	switch (status) {
	  case "delivery":
		if(statusNow != "waiting"){
			alert("Chỉ đơn hàng đang chờ mới có thể chuyển qua trạng thái giao hàng")
			event.target.parentNode.previousElementSibling.querySelector("#status-order").value =statusNow
			return
			}
		// call api chuyển sang giao hàng
		showLazy()
		let resultDe = await $.ajax({
			url: `/admin/delivery/${idOrder}`,
			type: 'put',
		})
		hideLazy()
		if (resultDe.length>0) {
			alert('chuyển trạng thái Giao hàng thành công')
			event.target.setAttribute("status-now", "delivery");
			document.querySelector('.total-orders span').innerHTML--
		} else {
			alert('Chuyển sang giao hàng thất bại')
		}
	    break;
	  case "cancelled":
		if(statusNow != "waiting"){
			alert("Chỉ đơn hàng đang chờ mới có thể chuyển qua trạng thái hủy")
			return
			}
			// call api sang hủy
			showLazy()
			let resultCa = await $.ajax({
				url: `/admin/cancel/${idOrder}`,
				type: 'put',
			})
			hideLazy()
			if (resultCa.length>0) {
				alert('Huỷ đơn hàng thành công')
				event.target.setAttribute("status-now", "cancelled");
			} else {
				alert('Hủy đơn hàng thất bại')
			}
	    break;
	  case "refund":
	    if(statusNow != "delivery"){
			alert("Chỉ đơn hàng đang giao mới có thể chuyển qua trạng thái hoàn")
			return
			}
			// call api sang hoàn
			showLazy()
			const resultRe = await $.ajax({
				url: `/admin/refund/${idOrder}`,
				type: 'put',
			})
			hideLazy()
			if (resultRe.length>0) {
				alert('Hoàn đơn hàng thành công')
				event.target.setAttribute("status-now", "refund");
			} else {
				alert('Hoàn đơn hàng thất bại')
			}
	    break;
	  case "success":
	    text = "On";
	    break;
	  default:
	    text = "No value found";
	}
}

// chuyen trang thai sang giao hang
async function deliveryOrder(event){
	event.preventDefault()
	const td = event.target.closest('.button-action')

	const conf = confirm('Chuẩn bị giao hàng')
	if (!conf) return

	const idOrder = event.target.getAttribute('data-id')
	console.log(idOrder)

	// add lazing add product
	showLazy()

	const result = await $.ajax({
		url: `/admin/delivery/${idOrder}`,
		type: 'put',
	})

	// console.log(result)

	hideLazy()
	if (result.length>0) {
		alert('chuyển trạng thái Giao hàng thành công')

		document.querySelector('.total-orders span').innerHTML--

		// set status order again
		setStatusOrder(td, 'delivery')

	} else {
		alert('Giao hàng thất bại')
	}
}
function setStatusOrder(td, status){
	td.className = `button-action ${status}`
	const pre = td.previousElementSibling
	pre.className = `order-status ${status}`
	pre.innerHTML = status
}
async function cancelOrder(event) {
	event.preventDefault()
	const td = event.target.closest('.button-action')

	const conf = confirm('Bạn chắc chắn muốn hủy đơn hàng')
	if (!conf) return

	const idOrder = event.target.getAttribute('data-id')
	// console.log(idOrder)

	// add lazing add product
	showLazy()

	const result = await $.ajax({
		url: `/admin/cancel/${idOrder}`,
		type: 'put',
	})

	// console.log(result)

	hideLazy()
	if (result.length>0) {
		alert('Huỷ đơn hàng thành công')

		document.querySelector('.total-orders span').innerHTML--

		// set status order again
		setStatusOrder(td, 'cancelled')
	} else {
		alert('Hủy đơn hàng thất bại')
	}
}
async function refundOrder(event) {
	event.preventDefault()
	const td = event.target.closest('.button-action')
	
	const conf = confirm('Bạn chắc chắn muốn hoàn đơn hàng')
	if (!conf) return

	const idOrder = event.target.getAttribute('data-id')
	// console.log(idOrder)

	// add lazing add product
	showLazy()

	const result = await $.ajax({
		url: `/admin/refund/${idOrder}`,
		type: 'put',
	})

	// console.log(result)

	hideLazy()
	if (result.length>0) {
		alert('Hoàn đơn hàng thành công')

		document.querySelector('.total-orders span').innerHTML--

		// set status order again
		setStatusOrder(td, 'refund')
	} else {
		alert('Hoàn đơn hàng thất bại')
	}
}
*/