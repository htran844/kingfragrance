function createMoreProductItem(capacity = '', cost = '', quantity = '', disable = '') {
	const moreProductItem = `<div class="col-3">
<label class="mb-1">Chọn dung tích</label>
 <input type="number" name="capacity" class="product-capacity" placeholder="Dung tích" value="${capacity}" required ${disable} />
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
<div onclick="deleteDetail('${productId}', '${capacity}')"><button type="button" id="bt" onclick="$(this).parent().parent().parent().remove()">
	Xóa
</button></div>

</div>`
	const moreProduct = document.createElement('div')
	moreProduct.className = 'row more-product-item d-flex align-items-center'
	moreProduct.innerHTML = moreProductItem // Insert text
	document.querySelector('.more-product-wrap').appendChild(moreProduct)
	return moreProduct
	
}
async function deleteDetail(productId, capacity){
	
	const deleteProducts =  await	$.ajax({
  	url: `/admin/product-detail/${productId}/${capacity}`,
  	type: 'DELETE'
  })
	alert(deleteProducts)
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
document
  .querySelector(".more-product-btn button")
  .addEventListener("click", function (e) {
    createMoreProductItem();
  });
const url = window.location.href.split('/')
let slugProduct = url[url.length - 1]
//product
  const name = document.querySelector("#product-name");
  const brand = document.querySelector("#product-brand");
  const check = document.querySelector("#product-hot");
  const gender = document.querySelector("#product-type");
  const description = document.querySelector("#product-description");
  const imageProduct = $('#product-images')
let image = "";
let productId = "";
//product recommened
	const seasonRec = document.querySelector("#product-season");
	const timeRec = document.querySelector("#product-time");
// product fragrance
	const tone = document.querySelector("#product-tone");
	const first = document.querySelector("#product-first");
	const midle = document.querySelector("#product-midle");
	const last = document.querySelector("#product-last");
// product characteristic
	const release = document.querySelector("#product-release");
	const genderCha = document.querySelector("#product-gender");
	const age = document.querySelector("#product-age");
	const retention = document.querySelector("#product-retention");
async function getInfoProduct() {
	const products =  await	$.ajax({
  	url: `/admin/product-main/${slugProduct}`,
  	type: 'GET'
  })
	image = products.image;
	 productId = products.id;
	name.value = products.name;
	brand.value = products.brand;
	if(products.hot === true){
		check.checked = true
	} else{
		check.checked = false
	}
	gender.value = products.gender
	
	const preview = $(".preview-images");
	$("<img />", {
      src: products.image,
    }).appendTo(preview);
	description.value = products.description;
	const productDetail =  await	$.ajax({
  	url: `/admin/product-detail/${productId}`,
  	type: 'GET'
  })
	
	for (let product of productDetail) {
		const moreProduct = createMoreProductItem(product.capacity, product.cost, product.quantity, "disabled")
	}
	const productRec =  await	$.ajax({
  	url: `/admin/product-rec/${productId}`,
  	type: 'GET'
  })
	seasonRec.value = productRec.season;
	timeRec.value = productRec.time;
	const productFra =  await	$.ajax({
  	url: `/admin/product-fra/${productId}`,
  	type: 'GET'
  })
  
	tone.value = productFra.tone;
	first.value = productFra.first;
	midle.value = productFra.midle;
	last.value = productFra.last;
	const productCha =  await	$.ajax({
  	url: `/admin/product-cha/${productId}`,
  	type: 'GET'
  })
	release.value = productCha.release
	genderCha.value = productCha.gender
	age.value = productCha.age
	retention.value = productCha.retention
}
getInfoProduct()
const form = document.querySelector("form");
form.addEventListener('submit', async (e) => {
	e.preventDefault();
	const products = document.querySelectorAll(
    ".more-product-wrap .more-product-item"
  );
 
     if (products.length == 0) {
      alert("Vui lòng thêm chi tiết sản phẩm: màu sắc, size và số lượng...");
      return;
   }
	
	const check = document.querySelector("#product-hot");
	  if (check.checked === true) {
	    hot = true;
	  } else {
	    hot = false;
	  }
	const lazy = document.querySelector(".lazy-loading");
	  lazy.classList.toggle("hide");
	const dataProduct = {
				name: name.value,
				brand: brand.value,
				slug: slugProduct,
				gender: gender.value,
				image: image,
				description: description.value,
				hot: hot
			}
	const dataRespon =  await	$.ajax({
  	url: '/admin/product-main',
  	  headers: {
  		  "Content-Type":"application/json"
  	  },
  	type: 'PUT',
  	data: JSON.stringify(dataProduct),
  	})
	
	
	for (let item of products) {
    	let capacityDetail = item.querySelector(".product-capacity").value;
		let costDetail = item.querySelector(".product-cost").value;
        let quantityDetail = item.querySelector(".product-quantity").value;
	    let dataProductDetail = {
		productId: productId,
		capacity: capacityDetail,
		cost: costDetail,
		quantity: quantityDetail
	}
      let resultProductDetail = await $.ajax({
        url: '/admin/product-detail',
  	  	headers: {
  		  "Content-Type":"application/json"
  	  		},
  		type: 'PUT',			
  		data: JSON.stringify(dataProductDetail),
      });
	
      // nếu có 1 sản phẩm không thành công thì cũng báo lỗi	
    }
	const dataRec = {
		productId: productId,
		season: seasonRec.value,
		time: timeRec.value
	}
	let resultProductRec = await $.ajax({
        url: '/admin/product-rec',
  	  	headers: {
  		  "Content-Type":"application/json"
  	  		},
  		type: 'PUT',
  		data: JSON.stringify(dataRec),
      });
	let dataFra = {
		productId: productId,
		tone: tone.value,
		first: first.value,
		midle: midle.value,
		last: last.value
	}
	let resultProductFra = await $.ajax({
        url: '/admin/product-fra',
  	  	headers: {
  		  "Content-Type":"application/json"
  	  		},
  		type: 'PUT',
  		data: JSON.stringify(dataFra),
      });
	let dataCha = {
		productId: productId,
		release: release.value,
		gender: genderCha.value,
		age: age.value,
		retention: retention.value
	}
	let resultProductCha = await $.ajax({
        url: '/admin/product-cha',
  	  	headers: {
  		  "Content-Type":"application/json"
  	  		},
  		type: 'PUT',
  		data: JSON.stringify(dataCha),
      });
	const imageProduct = $('#product-images')[0].files[0]
	if(typeof imageProduct != 'undefined'){
		var dataImage = new FormData();
		dataImage.append("file", imageProduct);
		dataImage.append("slug", slugProduct);
		const sendImage = await $.ajax({
        type: "POST",
        enctype: 'multipart/form-data',
        url: "/admin/product-up",
        data: dataImage,

        // prevent jQuery from automatically transforming the data into a query string
        processData: false,
        contentType: false,
        cache: false,
        timeout: 1000000,
        success: function(data, textStatus, jqXHR) {
				
        }
    });
		}
	
lazy.classList.toggle("hide");
alert("Sửa sản phẩm thành công");
})