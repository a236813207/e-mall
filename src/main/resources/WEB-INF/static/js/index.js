// JavaScript Document
$(document).ready(function() {
    var num1 = 0;
    var timer1 = null;
    // var imagesWidth = $('.banner-pic img').eq(0).width();
    var imagesWidth = $(document).width();
    onResizeEvent();
    $(window).resize(function() {
      onResizeEvent();
    });
    var mySwiper = new Swiper('.swiper-container',{
        speed:500,
        pagination: '.pagination',
        loop:true,
        grabCursor: true,
        paginationClickable: true,
        autoplay:5000
    })
    $('.arrow-left').on('click', function(e){
        e.preventDefault()
        mySwiper.swipePrev()
    })
    $('.arrow-right').on('click', function(e){
        e.preventDefault()
        mySwiper.swipeNext()
    })
});

function onResizeEvent(){
		var imagesWidth = $('body').width();
		var wn=imagesWidth/640;
		var hh=280*wn;
		$('.banner ,.swiper-container').css({
			'width':imagesWidth,
			'height':hh
		});
		$('.swiper-container .swiper-wrapper .swiper-slide img').css({
			'width':imagesWidth,
			'height':hh
		});
};

