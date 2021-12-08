//1


var redirect_Page;


function init() {
	let ListFilter= localStorage.getItem("ListFilter") ? JSON.parse(localStorage.getItem("ListFilter")) : [];
	if (ListFilter.length>0&&localStorage.getItem("confirm_Page")) {
		var urlParams = new Array();
	for (let index = 0; index < ListFilter.length; index++) {
		if (ListFilter[index].match("gender")) {
			urlParams.push(JSON.parse(ListFilter[index]).gender);
		}	
		else{
			urlParams.push(JSON.parse(ListFilter[index]).money);
		}
		
	}

	///load trang 
	var urlPr = urlParams.toString().replace(/,/g, "&&");
	
		var hrefFilter =location.pathname;
		url = `${hrefFilter}?${urlPr}`;
		window.location.assign(url)

	}
	

///////////////////////////////////
	var _url = location.href
	var gender = document.getElementsByClassName('gender-checkbox-filter');
	var price = document.getElementsByClassName('price-checkbox-filter');
	var orderby = document.getElementsByClassName('orderby');

	if (location.href.match("fillter")||location.href.match("order_by")) {
		 	$("html, body").animate({
		 		scrollTop: 140
		 	}, 1000);
		 }	
	if (!_url.match("fillter_gender")&&!_url.match("fillter_money")&&!_url.match("order_by")&& !localStorage.getItem("confirm_Page")) {
		localStorage.removeItem("ListFilter");
	}
	if (_url.match("price-desc")) {
		orderby[0].children[2].setAttribute("selected", "selected");
	}
	else
		if (_url.match("price")) {
			orderby[0].children[1].setAttribute("selected", "selected");
		}
	if (_url.match('fillter_gender=nam')) {
		gender[0].checked = true;
	}
	else if (_url.match("fillter_gender=nu")) {
		gender[1].checked = true;
	}
	else if (_url.match("fillter_gender=unisex")) {
		gender[2].checked = true;
	}
	if (_url.match('fillter_money=1500000-3000000')) {
		price[0].checked = true;
	}
	else if (_url.match('fillter_money=3000000-5000000')) {
		price[1].checked = true;
	}
	else if (_url.match('fillter_money=5000000-100000000')) {
		price[2].checked = true;
	}
		localStorage.removeItem("confirm_Page");

}
init();




//update
function sortBy(e) {
	let ListFilter= localStorage.getItem("ListFilter") ? JSON.parse(localStorage.getItem("ListFilter")) : [];
	var check = "orderBy";
	pushFilterToLocal(e, check,ListFilter);

var urlParams = new Array();
	for (let index = 0; index < ListFilter.length; index++) {
		if (ListFilter[index].match("gender")) {
			urlParams.push(JSON.parse(ListFilter[index]).gender);
		}	
		else if(ListFilter[index].match("money")){
			urlParams.push(JSON.parse(ListFilter[index]).money);
		}
		else{
			urlParams.push(JSON.parse(ListFilter[index]).order);

		}
		
	}

	///load trang 
	var urlPr = urlParams.toString().replace(/,/g, "&");
	
		var hrefFilter =location.pathname;
		url = `${hrefFilter}?${urlPr}`;
		window.location.assign(url)
}


$('.checkbox-filter-sidebar').click(function (e) {
let ListFilter= localStorage.getItem("ListFilter") ? JSON.parse(localStorage.getItem("ListFilter")) : [];

	//xu li checkbox
	var gender = document.getElementsByClassName('gender-checkbox-filter');
	var price = document.getElementsByClassName('price-checkbox-filter');
	
	if (this.className.match('gender-checkbox-filter')) {
		if (this.checked==false) {
			for (let index = 0; index < ListFilter.length; index++) {
				if (ListFilter[index].match("gender")) {
					ListFilter.splice(index,1);
				}	
			}
			localStorage.setItem("ListFilter", JSON.stringify(ListFilter));
		}
		else if (this.checked) {

			for (let index = 0; index < gender.length; index++) {
				gender[index].checked = false;
				this.checked = true;
			}
			var check = "gender";
			pushFilterToLocal(this, check,ListFilter);
		}
		
	}
	if (this.className.match('price-checkbox-filter')) {
		if (this.checked==false) {
			for (let index = 0; index < ListFilter.length; index++) {
				if (ListFilter[index].match("money")) {
					ListFilter.splice(index,1);
				}	
			}
			localStorage.setItem("ListFilter", JSON.stringify(ListFilter));
		}
		else if (this.checked) {

			for (let index = 0; index < price.length; index++) {
				price[index].checked = false;
				this.checked = true;
			}
			var check = "money";
			pushFilterToLocal(this, check,ListFilter);
		}
		
	}
	var urlParams = new Array();
	for (let index = 0; index < ListFilter.length; index++) {
		if (ListFilter[index].match("gender")) {
			urlParams.push(JSON.parse(ListFilter[index]).gender);
		}	
		else{
			urlParams.push(JSON.parse(ListFilter[index]).money);
		}
		
	}

	///load trang 
	var urlPr = urlParams.toString().replace(/,/g, "&&");
	
		var hrefFilter =location.pathname;
		url = `${hrefFilter}?${urlPr}`;
		window.location.assign(url)
//end load trang 
})

function pushFilterToLocal(e, check,ListFilterParam) {
	let ListFilter=ListFilterParam;
	if (localStorage) {
		var Filter;
		if (check === "gender") {
			Filter = {
				"gender": "fillter_gender=" + e.value,
			};
		}
		else if(check==="money") {
			Filter = {
				"money": "fillter_money=" + e.value,
			};
		}
		else if(check==="orderBy"){
			Filter = {
				"order": "order_by=" + e.value,
			};
		}
		Filter = JSON.stringify(Filter);

		var countFilterGender=0;
		var countFilterMoney=0;
		var countFilterOder=0;
		for (let index = 0; index < ListFilter.length; index++) {
			if (ListFilter[index].match("gender")) {
				countFilterGender++;
			}
			if (ListFilter[index].match("money")) {
				countFilterMoney++;
			}
			if (ListFilter[index].match("order")) {
				countFilterOder++;
			}
		}
		if (ListFilter.length>0) {
		for (let index = 0; index < ListFilter.length; index++) {
			if (ListFilter[index].match("gender")&&countFilterGender==1&&Filter.match("fillter_gender")) {
				ListFilter.splice(index, 1);
			}
			else if (ListFilter[index].match("money")&&countFilterMoney==1&&Filter.match("fillter_money")) {
					ListFilter.splice(index, 1);
				}
				else if (ListFilter[index].match("order")&&countFilterOder==1&&Filter.match("order_by")) {
					ListFilter.splice(index, 1);
				}
		}
		ListFilter.push(Filter);
		}
		
		if (ListFilter.length==0) {
		ListFilter.push(Filter);
		}
		localStorage.setItem("ListFilter", JSON.stringify(ListFilter));
	}
}





function redirectPage(){
	localStorage.setItem("confirm_Page","oke")
}