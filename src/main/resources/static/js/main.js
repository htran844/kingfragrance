$(document).ready(function () {
    AOS.init({
        //        once: true,
        offset: 0,
        easing: 'ease-in-out-cubic',
        duration: "500",
    });
    $(window).on("load", function () {
        if ($(this).scrollTop() > 0) {
            $("#header").addClass("scrolled");


        } else {
            $("#header").removeClass("scrolled");

        }
    });
    $(document).scroll(function () {
        if ($(this).scrollTop() > $("#main").offset().top) {
            $(".back-top").addClass("active");

        } else {
            $(".back-top").removeClass("active");

        }
        if ($(this).scrollTop() > 0) {
            $("#header").addClass("scrolled");


        } else {
            $("#header").removeClass("scrolled");

        }
    });
    $(".back-top").on("click", function () {
        $(".back-top").removeClass("active");
        $("html, body").animate({
            scrollTop: 0
        }, 1000);
    });

});
// 









///them vao gio hang 
$('#addToCart').click(function () {
    image_pr = document.getElementById('image_pr').getAttribute('src');
    productid_pr = document.getElementById('productid_pr').innerHTML;
    capacity_pr = document.getElementById('capacity_pr').value;
    quantity_pr = document.getElementById('quantity_pr').value;
    cost_pr = document.getElementById('cost_pr').innerHTML;
    name_pr = document.getElementById('name_pr').innerHTML;
    if (isNaN(capacity_pr)||capacity_pr==0) {
        alert('Bạn chưa chọn dung tích!');
        return;
    }
    if (localStorage) {
        var product = {
            "productid_pr": productid_pr,
            "capacity_pr": capacity_pr,
            "quantity_pr": quantity_pr,
            "cost_pr": cost_pr,
            "name_pr":name_pr,
            "image_pr":image_pr
        }
        var lengStorage = localStorage.length;
            localStorage.setItem('product' + lengStorage, JSON.stringify(product));
        alert('Thêm thành công!');
    }
});


var moneyReal = document.getElementById('cost_pr');
function getMoneyByCapacity() {
    capacity_pr = document.getElementById('capacity_pr').value;
    money = document.getElementById('cost_pr').innerHTML=moneyReal.innerHTML;
    capacity_pr =  Number(capacity_pr);
    money = Number(money);
    document.getElementById('cost_pr').innerHTML=money+'';
  
    //so tien tren 1ml
    var _1ml = money*0.01;
    var resultMoneyByMl = _1ml*capacity_pr;
    resultMoneyByMl=Math.round(resultMoneyByMl);
    document.getElementById('cost_pr').innerHTML=resultMoneyByMl+'';
}


var lengthOfLocalStorage = localStorage.length;
// push from localStorage to cart 
function getDataLocalStorageToCart(){
    var itemProduct = '';
    var product=null;
    if (localStorage) {
       for (let index = 0; index < lengthOfLocalStorage; index++) {
           product = JSON.parse( localStorage.getItem('product' + index));
        itemProduct += `
       <li class="item-product">

       <div class="icon-close" >
       <span style="display:none;" class="product_LocalStorage">${'product' + index}</span>
       <i  class="fas fa-times"></i></div>
      
       <hr>
       <div class="d-inline-block">
           <img src="${product.image_pr}" id="image-pr" width="180px" alt="">
       </div>
       <div class="d-inline-block">
           <h3 class="name-product"><a href="" id="name-pr">${product.name_pr} </a>-<br><span id="capacity-pr">${product.capacity_pr}</span></h3> <br>
           <span class="money" id="money-pr"> ${product.cost_pr}</span> <span> ₫</span>
       </div>
       <div class="d-inline-block">
           <div class="quantity">
               <label class="screen-reader-text" for=""></label>
               <input type="number" id="quantity-pr" class="input-text qty text"
                   step="1" min="0" max="" name=""
                   value="${product.quantity_pr}" title="SL" size="4" placeholder="" inputmode="numeric"  onclick="changeMoney()">
           </div>
       </div>
       </li>
      
       `
    }
}
    document.getElementById('list_pr').innerHTML=itemProduct
}
getDataLocalStorageToCart();



//remove cart
$('.icon-close').click(function () {
    if (localStorage) {
        var product=  $(this).children('span')[0].innerHTML;
        localStorage.removeItem(product)
        // lengthOfLocalStorage=1;
     }
     
    $(this).parent('.item-product').remove();
    getAllProduct();
    tinhTien();
});
function getAllProduct() {
    var arrayCart = document.querySelectorAll(".section-cart .module-left li");
    if (arrayCart.length == 0) {
        $('.module-cart').hide();
        $('#tm-main').show();
    }
    else {
        $('#tm-main').hide();
        $('.module-cart').show();
    }
}
getAllProduct();
function tinhTien() {
    var sum = 0;
    var ItemProduct = document.querySelectorAll('.item-product');

    for (let index = 0; index < ItemProduct.length; index++) {
        var getMoney = ItemProduct[index].children[3];
        var money = getMoney.children[2].innerHTML;
        var quantity = ItemProduct[index].children[4].children[0].children[1].value;
        quantity = Number(quantity);
        money = Number(money) * quantity;
        sum += money;
    }
    document.getElementById('totalMoney').innerHTML = sum + '';
    document.getElementById('tamTinh').innerHTML = sum + '';
}


function changeMoney() {
    tinhTien();
}
