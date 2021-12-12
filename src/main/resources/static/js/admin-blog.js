let search = "null";
let gen = "null";
document.querySelector('.action-add').addEventListener('click', (e) => {
	window.location.href = '/admin/add-blogs'
})
function renderListOfBlog(blogs) {
	let html = ''
	blogs.forEach((blogs, index) => {
		html+= ` <tr>
                                <td class="stt-product">${index + 1}</td>
								<td class="name-product">${blogs.title}</td>

                                <td class="cover-product">
                                    <img src="${blogs.image}"
                                        alt="" />
                                </td>
                                <td>${blogs.content}</td>

                                <td class="action-product">

                                    <a href="/admin/blogs/${blogs.id}">
                                        <button>
                                            <i class="fas fa-edit"></i>
                                        </button>
                                    </a>
                                    <div  onclick="deleteBlogCode('${blogs.id}')">
                                        <button>
                                            <i class="far fa-trash-alt"></i>
                                        </button>
                                    </div>

                                </td>
                            </tr>`
	})
	createTable('table_blog', html, blogs.length)
	document.querySelector('#total-blogs').innerHTML = blogs.length
}

async function getBlogs() {
	if(!checkOnline()){
		alert('Không có kết nối internet')
		return
	}
	showLazy()
	const blogs =  await $.ajax({
  	url: "/admin/blog-mainAll",
  	type: 'GET'
  })
	renderListOfBlog(blogs);
	hideLazy()
}
const processChange = debounce(() => searchProduct())

// search blogs
let inputSearch = $('.blogs-action .action-search input')
function searchProduct() {
	
	search = inputSearch.val()
	
	if(!search.length>0){
		search="null"
	}
	
	getBlogs()
}
$('.blogs-action .action-search input').on('keyup', processChange)
function handleFilter(obj) {

	gen = obj.value

	getBlogs()
}
getBlogs()




async function deleteBlogCode(BlogId) {
	const conf = confirm('Bạn chắc chắn muốn xóa blog?')
	if (!conf) return
	
	const deleteProduct =  await	$.ajax({
  	url: `/admin/blog-main/${BlogId}`,
  	type: 'DELETE',
	  success: function (response) {
		console.log(response);
	}
  })
	getBlogs()
	alert('xóa blog thành công')
}
