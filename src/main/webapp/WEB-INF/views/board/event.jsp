<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../includes/header.jsp"%>

<!DOCTYPE html>
<style>
html, body, button{
   font-family:Arial, "돋움", Dotum, "굴림", Gulim, "Apple SD Gothic Neo", AppleGothic, sans-serif;
}

button{border:0;
	margin:0;
	padding:0;
	color:#04c35c;
	font-family: 'Noto Sans KR';
	font-size: 18px;
	font-weight: 500;
	font-style: normal;
	line-height: normal;}

.title{margin-top:50px;
	text-align:center;
	font-family: 'Noto Sans KR';
	font-size: 30px;
	font-weight: 500;
	font-style: normal;
	line-height: normal;
	color: #04c35c;}

.box-roulette{position:relative;margin:50px auto;width:500px;height:500px;border:10px solid #ccc;border-radius:50%;background:#ccc;overflow:hidden;}
.box-roulette .markers{position:absolute;left:50%;top:0;margin-left:-25px;width:0;height:0;border:25px solid #fff;border-left-color:transparent;border-right-color:transparent;border-bottom-color:transparent;z-index:9999;}
.box-roulette .roulette{position:relative;width:100%;height:100%;overflow:hidden;}
.box-roulette .item{position:absolute;top:0;width:0;height:0;border:0 solid transparent;transform-origin:0 100%;}
.box-roulette .label{position:absolute;left:0;top:0;color:#fff;white-space:nowrap;transform-origin:0 0;}
.box-roulette .label .text{display:inline-block;font-size:20px;font-weight:bold;line-height:1;vertical-align:middle;}

#btn-start{display:block;position:absolute;left:50%;top:50%;margin:-50px 0 0 -50px;width:100px;height:100px;font-weight:bold;background:#fff;border-radius:50px;z-index:9999;cursor:pointer;}
</style>

<div class="recipe_page">
<p class="title">오늘 뭐 먹지?</p>

<div class="box-roulette">
   <div class="markers"></div>
   <button type="button" id="btn-start">
      돌려 돌려<br>돌림판
   </button>
   <div class="roulette" id="roulette"></div>
</div>

 <%@ include file="../includes/footer.jsp"%>
</div>
 
<script>
(function($) {
     $.fn.extend({

       roulette: function(options) {

         var defaults = {
           angle: 0,
           angleOffset: -45,
           speed: 5000,
           easing: "easeInOutElastic",
         };
         
         var opt = $.extend(defaults, options);

         return this.each(function() {
           var o = opt;
         
           var data = [
                  {
               color: '#3f297e',
               text: '한식'
               
                  },
             
             {
               color: '#1d61ac',
               text: '중식'
             },
             {
               color: '#169ed8',
               text: '일식'
             },
             {
               color: '#209b6c',
               text: '양식'
             },
             {
               color: '#60b236',
               text: '한식'
             },
             {
               color: '#efe61f',
               text: '중식'
             },
             {
               color: '#f7a416',
               text: '일식'
             },
             {
               color: '#e6471d',
               text: '양식'
             },
             {
               color: '#dc0936',
               text: '한식'
             },
             {
               color: '#e5177b',
               text: '중식'
             },
             {
               color: '#be107f',
               text: '일식'
             },
             {
               color: '#881f7e',
               text: '양식'
             }
           ];

           var $wrap = $(this);
           var $btnStart = $wrap.find("#btn-start");
           var $roulette = $wrap.find(".roulette");
           var wrapW = $wrap.width();
           var angle = o.angle;
           var angleOffset = o.angleOffset;
           var speed = o.speed;
           var esing = o.easing;
           var itemSize = data.length;
           var itemSelector = "item";
           var labelSelector = "label";
           var d = 360 / itemSize;
           var borderTopWidth = wrapW;
           var borderRightWidth = tanDeg(d);

           for (i = 1; i <= itemSize; i += 1) {
             var idx = i - 1;
             var rt = i * d + angleOffset;
             var itemHTML = $('<div class="' + itemSelector + '">');
             var labelHTML = '';
                 labelHTML += '<p class="' + labelSelector + '">';
                 labelHTML += '   <span class="text">' + data[idx].text + '<\/span>';
                 labelHTML += '<\/p>';

             $roulette.append(itemHTML);
             $roulette.children("." + itemSelector).eq(idx).append(labelHTML);
             $roulette.children("." + itemSelector).eq(idx).css({
               "left": wrapW / 2,
               "top": -wrapW / 2,
               "border-top-width": borderTopWidth,
               "border-right-width": borderRightWidth,
               "border-top-color": data[idx].color,
               "transform": "rotate(" + rt + "deg)"
             });
             
             var textH = parseInt(((2 * Math.PI * wrapW) / d) * .5);

             $roulette.children("." + itemSelector).eq(idx).children("." + labelSelector).css({
               "height": textH + 'px',
               "line-height": textH + 'px',
               "transform": 'translateX(' + (textH * 1.3) + 'px) translateY(' + (wrapW * -.3) + 'px) rotateZ(' + (90 + d * .5) + 'deg)'
             });
             
             
           }
      
           function tanDeg(deg) {
             var rad = deg * Math.PI / 180;
             return wrapW / (1 / Math.tan(rad));
           }
           


           $btnStart.on("click", function() {
             rotation();
           });
   
           
           function rotation() {

             var completeA = 360 * r(5, 10) + r(0, 360);

             $roulette.rotate({
               angle: angle,
               animateTo: completeA,
               center: ["50%", "50%"],
               easing: $.easing.esing,
               callback: function() {
                 var currentA = $(this).getRotateAngle();

                 console.log(currentA);

                 var angle = currentA%360 + 15;
                 var part = Math.floor(angle/30);
                 
                 
                if(part == 12 || part == 8 || part ==4){
                	alert("오늘의 메뉴는 한식으로!")
                	location.href="ko"
                }else if(part == 11 || part ==7 || part ==3){
                	alert("오늘의 메뉴는 중식으로!")
                	location.href="co"
                }else if(part == 10 || part == 6 || part ==2){
                	alert("오늘의 메뉴는 일식으로!")
                	location.href="jo"
                }else {
                	alert("오늘의 메뉴는 양식으로!")
                	location.href="mo"
                }
                
               },
               duration: speed
             });
           }
           

           function r(min, max) {
             return Math.floor(Math.random() * (max - min + 1)) + min;
           }

         }); 
       }
     }); 
   })(jQuery);

   $(function() {

     $('.box-roulette').roulette();

   });
</script>