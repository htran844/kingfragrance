function createMoreProductItem(capacity = '', cost = '', quantity = '') {
	const moreProductItem = `<div class="col-3">
<label class="mb-1">Chọn dung tích</label>
 <input type="number" name="capacity" class="product-capacity" placeholder="Dung tích" value="${capacity}" required />
</div>
<div class="col-3">
<label class="mb-1">Nhập giá</label>
<input type="number" name="cost" class="product-cost" placeholder="Giá" value="${cost}" required />
</div>
<div class="col-3">
<label class="mb-1">Số lượng</label>
<input type="number" name="quantity" class="product-quantity" placeholder="Số lượng" value="${quantity}"
	required />
</div>
<div class="col-3 more-product-delete-btn mt-3">
<button type="button" onclick="$(this).parent().parent().remove()">
	Xóa
</button>
</div>`
	const moreProduct = document.createElement('div')
	moreProduct.className = 'row more-product-item d-flex align-items-center'
	moreProduct.innerHTML = moreProductItem // Insert text
	document.querySelector('.more-product-wrap').appendChild(moreProduct)
	return moreProduct
}
$("#product-images").on("change", function () {
  let files = this.files;
  // console.log(files)
  if (files.length == 0) return;

  const previewImages = $(".preview-images");

  previewImages.empty();


  for (let i = 0; i < files.length; i++) {
    $("<img />", {
      src: URL.createObjectURL(files[i]),
    }).appendTo(previewImages);

  }
});
const url = window.location.href.split('/')
let slugProduct = url[url.length - 1]
  const name = document.querySelector("#product-name");
  const brand = document.querySelector("#product-brand");
  const check = document.querySelector("#product-hot");
  const gender = document.querySelector("#product-type");
  const description = document.querySelector("#product-description");
  const imageProduct = $('#product-images')
async function getInfoProduct() {
	const products =  await	$.ajax({
  	url: `/admin/product-main/${slugProduct}`,
  	type: 'GET'
  })
	const productId = products.id;
	name.value = products.name;
	brand.value = products.brand;
	if(products.hot === true){
		check.checked = true
	} else{
		check.checked = false
	}
	gender.value = products.gender
	console.log(products.image)
	imageProduct.src = `${products.image}` ;
	description.value = products.description;
	const productDetail =  await	$.ajax({
  	url: `/admin/product-detail/${productId}`,
  	type: 'GET'
  })
	
	for (let product of productDetail) {
		const moreProduct = createMoreProductItem(product.capacity, product.cost, product.quantity)
	}
	
}
getInfoProduct()