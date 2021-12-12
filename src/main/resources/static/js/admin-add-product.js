const moreProductItem = `<div class="col-3">
<label class="mb-1">Chọn dung tích</label>
<input type="number" name="capacity" class="product-capacity" min="10" placeholder="Dung tích" required />
</div>
<div class="col-3">
<label class="mb-1">Nhập giá</label>
<input type="number" name="cost" class="product-cost" min="1000" placeholder="Giá" required />
</div>
<div class="col-3">
<label class="mb-1">Số lượng</label>
<input type="number" name="quantity" class="product-quantity" min="1" placeholder="Số lượng"
	required />
</div>
<div class="col-3 more-product-delete-btn mt-3">
<button type="button" onclick="$(this).parent().parent().remove()">
	Xóa
</button>
</div>`;

function createMoreProductItem() {
  const moreProduct = document.createElement("div");
  moreProduct.className = "row more-product-item d-flex align-items-center";
  moreProduct.innerHTML = moreProductItem; // Insert text
  document.querySelector(".more-product-wrap").appendChild(moreProduct);
}

const form = document.querySelector("form");

createMoreProductItem();

// change color

// add images
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

// add product
form.addEventListener("submit", async  (e) => {
  e.preventDefault();

  // báo lỗi nếu không có sản phẩm được tạo
  const products = document.querySelectorAll(
    ".more-product-wrap .more-product-item"
  );
 
     if (products.length == 0) {
      alert("Vui lòng thêm chi tiết sản phẩm: màu sắc, size và số lượng...");
      return;
   }

  const name = document.querySelector("#product-name");
  const slug = name.value.replace(/\s+/g, "-").toLowerCase();

  const brand = document.querySelector("#product-brand");
  var hot = true;
  const check = document.querySelector("#product-hot");
  if (check.checked === true) {
    hot = true;
  } else {
    hot = false;
  }
  //    check.checked == true ? hot=true : hot=false;
  const gender = document.querySelector("#product-type");
  // const color = document.querySelector('.product-color')
  // const total = document.querySelector('.product-total')
  const description = document.querySelector("#product-description");


  const previewImages = $(".preview-images img");
  if (previewImages.length == 0) {
    alert("Vui lòng chọn ảnh sản phẩm");
    return;
  }
  // add lazing add product
  const lazy = document.querySelector(".lazy-loading");
  lazy.classList.toggle("hide");
const dataProduct = {
			name: name.value,
			brand: brand.value,
			slug: slug,
			gender: gender.value,
			image: "",
			description: description.value,
			hot: hot
		}
const dataRespon =  await	$.ajax({
  	url: '/admin/product-main',
  	  headers: {
  		  "Content-Type":"application/json"
  	  },
  	type: 'POST',
  	data: JSON.stringify(dataProduct),
//      success: function(data) {
//  		const productId = data.id;
//  		alert(data)
//    }
  })
	
	const productId = await dataRespon.id;

  // firstly, create productCode, then create product
	if(typeof productId === 'undefined'){
		alert("Thêm thất bại! Tên sản phẩm đã tồn tại");
		lazy.classList.toggle("hide");
		return;
	}
	var postStatus = true;
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
  		type: 'POST',
  		data: JSON.stringify(dataProductDetail),
      });
      // nếu có 1 sản phẩm không thành công thì cũng báo lỗi
		if(typeof resultProductDetail.id === 'undefined'){
		alert("Thêm dung tích thất bại. Sản phẩm này đã có dung tích " + capacityDetail + "ml");
		lazy.classList.toggle("hide");
		}
      if (resultProductDetail.status == "fail") postStatus = false;
    }
	// tao bang recommended
	const seasonRec = document.querySelector("#product-season").value;
	const timeRec = document.querySelector("#product-time").value;
	const dataRec = {
		productId: productId,
		season: seasonRec,
		time: timeRec
	}
	let resultProductRec = await $.ajax({
        url: '/admin/product-rec',
  	  	headers: {
  		  "Content-Type":"application/json"
  	  		},
  		type: 'POST',
  		data: JSON.stringify(dataRec),
      });
	if(typeof resultProductRec.id === 'undefined'){
		alert("Thêm dung bảng khuyên dùng thất bại");
		lazy.classList.toggle("hide");
		}
		// tao bang fragrance
	let tone = document.querySelector("#product-tone").value;
	let first = document.querySelector("#product-first").value;
	let midle = document.querySelector("#product-midle").value;
	let last = document.querySelector("#product-last").value;
	let dataFra = {
		productId: productId,
		tone: tone,
		first: first,
		midle: midle,
		last: last
	}
	let resultProductFra = await $.ajax({
        url: '/admin/product-fra',
  	  	headers: {
  		  "Content-Type":"application/json"
  	  		},
  		type: 'POST',
  		data: JSON.stringify(dataFra),
      });

	if(typeof resultProductFra.id === 'undefined'){
		alert("Thêm dung bảng mùi hương thất bại");
		lazy.classList.toggle("hide");
		}
		// tao bang characteristic
	let release = document.querySelector("#product-release").value;
	let genderCha = document.querySelector("#product-gender").value;
	console.log(genderCha)
	let age = document.querySelector("#product-age").value;
	let retention = document.querySelector("#product-retention").value;
	let dataCha = {
		productId: productId,
		release: release,
		gender: genderCha,
		age: age,
		retention: retention
	}
	let resultProductCha = await $.ajax({
        url: '/admin/product-cha',
  	  	headers: {
  		  "Content-Type":"application/json"
  	  		},
  		type: 'POST',
  		data: JSON.stringify(dataCha),
      });

	if(typeof resultProductCha.id === 'undefined'){
		alert("Thêm dung bảng đặc điểm thất bại");
		lazy.classList.toggle("hide");
		}
		
	const imageProduct = $('#product-images')[0].files[0]

		var dataImage = new FormData();
		dataImage.append("file", imageProduct);
		dataImage.append("slug", slug);
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
				lazy.classList.toggle("hide");
				alert("Thêm sản phẩm thành công");
        }
    });
  
  //   if (postStatus) alert("Thêm sản phẩm thành công");
  //   else
  //     alert(
  //       "Vui lòng kiểm tra và tiến hành tạo sản phẩm lại, vì do có 1 vài sản phẩm tạo lỗi"
  //     );
});
