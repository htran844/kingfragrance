
$("#blog-images").on("change", function () {
	let files = this.files;
	if (files.length == 0) return;
	const previewImages = $(".blog-images");
	previewImages.empty();
	for (let i = 0; i < files.length; i++) {
	  $("<img />", {
		src: URL.createObjectURL(files[i]),
	  }).appendTo(previewImages);
  
	}
  });

  async function update_blog(){
	const url = window.location.href.split('/')
	
	
	const title = document.querySelector("#blog-title");
	const content = document.querySelector("#blog-content");
	const previewImages = $(".blog-images img");
	const idBlog = url[url.length - 1];
	if (previewImages.length == 0) {
	  alert("Vui lòng chọn ảnh blog");
	  return;
	}
	// add lazing add product
	const lazy = document.querySelector(".lazy-loading");
	lazy.classList.toggle("hide");
	const dataBlog = {
		id:idBlog,
	  title: title.value,
	  image: "",
	  content: content.value,
	}
	const dataRespon = await $.ajax({
	  url: '/admin/blog-main',
	  headers: {
		"Content-Type": "application/json"
	  },
	  type: 'PUT',
	  data: JSON.stringify(dataBlog),
	  success: function (data) {
	  }
	})
  
	const BlogId = await dataRespon.id;
  
	const imageProduct = $('#blog-images')[0].files[0]
	var dataImage = new FormData();
   dataImage.append("file", imageProduct);
	 dataImage.append("id", BlogId);
	const sendImage = await $.ajax({
	  type: "POST",
	  enctype: 'multipart/form-data',
	  url: "/admin/blog-up",
	  data: dataImage,
	  processData: false,
	  contentType: false,
	  cache: false,
	  timeout: 1000000,
	  success: function (data, textStatus, jqXHR) {
		lazy.classList.toggle("hide");
		alert("Sửa blog thành công");
		window.location.reload();
	  }
	});
  
  }