const url = window.location.href.split('/')
let orderId = url[url.length - 1]

function createMoreProduct(name, capacity, quantity, cost, total){
	const more = `<tr style="border-top: 1px solid #4647b2">
								<td id="info-product">${name} ${capacity}ml (${quantity})</td>
								<td id="cost-product">${cost.toLocaleString()}</td>
							</tr>
							<tr>
								<td></td>
								<td id="price-product">
									${total}
								</td>
							</tr>`;
	const infofirst = `<td id="info-product">${name} ${capacity}ml (${quantity})</td>
								<td id="cost-product">${cost}</td>`;
	const infosecond = `<td></td>
								<td id="price-product">
									${total}
								</td>`
	const morefirst = document.createElement('tr');
	const moresecond = document.createElement('tr');
	morefirst.innerHTML = infofirst;
	morefirst.style="border-top: 1px solid #4647b2"
	moresecond.innerHTML = infosecond;
	document.querySelector('#listbody').appendChild(morefirst)
	document.querySelector('#listbody').appendChild(moresecond)
}
//createMoreProduct("dior", "100", "2", parseFloat("400000").toLocaleString("en"),parseFloat("800000").toLocaleString("en"))
//createMoreProduct("creed aventus", "100", "2", parseFloat("400000").toLocaleString("en"),parseFloat("800000").toLocaleString("en"))
async function getinfo(){
	const order = await $.ajax({
		url: `/admin/order/${orderId}`,
		type: 'GET'
	})
	document.querySelector('#order-id').innerHTML = order.id
	document.querySelector('#total-cost').innerHTML =  parseFloat(order.totalCost).toLocaleString("en")
	
	const result = await $.ajax({
		url: `/admin/invoices/${orderId}`,
		type: 'GET'
	})
	console.log(result[0].productId)
	result.forEach(async(orderDetail, index) =>{
		const product = await $.ajax({
			url: `/admin/product-mainid/${orderDetail.productId}`,
			type: 'GET'
		})
		
		createMoreProduct(product.name, orderDetail.capacity, orderDetail.quantity, parseFloat(orderDetail.cost).toLocaleString("en"),parseFloat(orderDetail.total).toLocaleString("en"))
	})
}
getinfo()