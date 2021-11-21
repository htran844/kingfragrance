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











// var lengthOfLocalStorage = localStorage.length;
// // push from localStorage to cart 
// function getDataLocalStorageToCart(){
//     var itemProduct = '';
//     var product=null;
//     if (localStorage) {
//        for (let index = 0; index < lengthOfLocalStorage; index++) {
//            product = JSON.parse( localStorage.getItem('product' + index));
//         itemProduct += `
//        <li class="item-product">

//        <div class="icon-close" >
//        <span style="display:none;" class="product_LocalStorage">${'product' + index}</span>
//        <i  class="fas fa-times"></i></div>  
//        <hr>
//        <div class="d-inline-block">
//            <img src="${product.image_pr}" id="image-pr" width="180px" alt="">
//        </div>
//        <div class="d-inline-block">
//            <h3 class="name-product"><a href="" id="name-pr">${product.name_pr} </a>-<br><span id="capacity-pr">${product.capacity_pr}</span></h3> <br>
//            <span class="money" id="money-pr"> ${product.cost_pr}</span> <span> ₫</span>
//        </div>
//        <div class="d-inline-block">
//            <div class="quantity">
//                <label class="screen-reader-text" for=""></label>
//                <input type="number" id="quantity-pr" class="input-text qty text"
//                    step="1" min="0" max="" name=""
//                    value="${product.quantity_pr}" title="SL" size="4" placeholder="" inputmode="numeric"  onclick="changeMoney()">
//            </div>
//        </div>
//        </li>
      
//        `
//     }
// }
//     document.getElementById('list_pr').innerHTML=itemProduct
// }
// getDataLocalStorageToCart();





