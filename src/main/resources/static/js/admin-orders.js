let search = "null";
let status = "null";
activeNavItem('orders')
async function getOrders() {
	if (!checkOnline()) {
		alert('Không có kết nối internet')
		return
	}

	showLazy()

	const orders = await $.ajax({
		url: `/admin/orders/${search}/${status}`,
		method: 'GET',
	})
	renderListOfOrders(orders)
	//showPagination(totalPages, 'orders')
	//document.querySelector('.total-orders span').innerHTML = totalOrderWaiting
	hideLazy()
}
getOrders()
function renderListOfOrders(orders) {
	let html = ''
	orders.forEach((order, i) => {
		// console.log(order)
		html += `
        <tr>
                    <td> ${i + 1} </td>
                    <td>
                        ${order.name}
                    </td>
                    <td>
                        ${order.phone}
                    </td>
                    
                    <td> ${formatDate(order.createAt)}</td>
                    <td> ${order.totalCost.toLocaleString()} </td>
                    
                    <td class="order-status ${order.status}"> ${
			order.status
		} </td>
                    <td class="button-action ${order.status}">
                        <button class="detail-order">
                            <a href="/admin/orders/${order.id}">
                                Chi tiết
                            </a>
                        </button>
                        <button class="delivery-order" onclick="deliveryOrder(event)" data-id="${order.id}">
                            Giao hàng
                        </button>
                        <a href="/invoices/${order.id}" target="_blank">
							<button class="bill-order">
								Hóa đơn
							</button>
						</a>
                        <button class="cancel-order" onclick="cancelOrder(event)" data-id="${order.id}">
                            Hủy đơn
                        </button>
						 <button class="refund-order" onclick="refundOrder(event)" data-id="${order.id}">
                            Hoàn đơn
                        </button>
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