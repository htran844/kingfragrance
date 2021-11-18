let search = "null";
let gen = "null";
document.querySelector('.action-add').addEventListener('click', (e) => {
	window.location.href = '/admin/add-product'
})
function renderListOfProducts(products) {
	let html = ''
	products.forEach((products, index) => {
		html+= ` <tr>
                                <td class="stt-product">${index + 1}</td>

                                <td class="cover-product">
                                    <img src="${products.image}"
                                        alt="" />
                                </td>
                                <td class="name-product">${products.name}</td>
                                <td>${products.brand}</td>
                                <td class="cost-product">${products.gender}</td>
                                <td class="last-product">${products.hot}</td>

                                <td class="action-product">

                                    <a href="/admin/products/${products.slug}">
                                        <button>
                                            <i class="fas fa-edit"></i>
                                        </button>
                                    </a>
                                    <div  onclick="deleteProductCode('${products.id}')">
                                        <button>
                                            <i class="far fa-trash-alt"></i>
                                        </button>
                                    </div>

                                </td>
                            </tr>`
	})
	createTable('admin-products', html, products.length)
	document.querySelector('.total-products span').innerHTML = products.length
}
async function deleteProductCode(productId) {
	const conf = confirm('Bạn chắc chắn muốn xóa sản phẩm')
	if (!conf) return
	
	const deleteProduct =  await	$.ajax({
  	url: `/admin/product-main/${productId}`,
  	type: 'DELETE'
  })
	const deleteProductDetail =  await	$.ajax({
  	url: `/admin/product-detail/${productId}`,
  	type: 'DELETE'
  })
	const deleteProducRec =  await	$.ajax({
  	url: `/admin/product-rec/${productId}`,
  	type: 'DELETE'
  })
	const deleteProducFra =  await	$.ajax({
  	url: `/admin/product-fra/${productId}`,
  	type: 'DELETE'
  })
	const deleteProducCha =  await	$.ajax({
  	url: `/admin/product-cha/${productId}`,
  	type: 'DELETE'
  })
	alert('xóa sản phẩm thành công')
	getProducts()
	
}
async function getProducts() {
	if(!checkOnline()){
		alert('Không có kết nối internet')
		return
	}
	showLazy()
	
	const products =  await	$.ajax({
  	url: `/admin/product-main/2/${search}/${gen}`,
  	type: 'GET'
  })
	renderListOfProducts(products);
	hideLazy()
}
getProducts()
const processChange = debounce(() => searchProduct())

// search products
let inputSearch = $('.products-action .action-search input')
function searchProduct() {
	
	search = inputSearch.val()
	
	if(!search.length>0){
		search="null"
	}
	
	getProducts()
}
$('.products-action .action-search input').on('keyup', processChange)
function handleFilter(obj) {

	gen = obj.value

	getProducts()
}
