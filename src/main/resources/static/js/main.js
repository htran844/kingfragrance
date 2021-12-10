$(document).ready(function () {
    AOS.init({
                once: true,
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
var ListProduct = localStorage.getItem("ListProduct") ? JSON.parse(localStorage.getItem("ListProduct")) : [];
var length_cart = ListProduct.length;
document.getElementById('number_cart').innerHTML=length_cart;










