<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<style type="text/css">
*{
	margin: 0;
	padding: 0;
}
li{
	list-style: none;
}
.slide_wrapper{
	position: relative;
	width: 1930px;
	margin: 0 auto;
	height: 330px;
	overflow: hidden;
	background-color: black;
}
.slides{
	position: absolute;
	left: 0;
	top: 0;
	transition:left 0.5s ease-out;
}
.slides li:not(:last-child){
	float: left;
	margin-right: 30px;
}
.controls{
	text-align: center;
}
.controls span{
	background: none;
	color: white;
	border: none;
	cursor: pointer;
	font-size: 20px;
	text-align: center;
	border-radius: 2em;
}
.controls span:hover{
	color: gray;
}
.controls span.prev{rigth:calc(100% + 50px);}
.controls span.next{left:calc(100% + 50px);}
.poster_img{
	border-radius: 5px;
}
</style>
</head>
<body>
	<div class = "slide_wrapper">
		<ul class="slides">
			<li><img src="${pageContext.request.contextPath}/images/poster/poster1.jpg" width="250" height="330" class="poster_img"></li>
			<li><img src="${pageContext.request.contextPath}/images/poster/poster2.jpg" width="250" height="330" class="poster_img"></li>
			<li><img src="${pageContext.request.contextPath}/images/poster/poster3.jpg" width="250" height="330" class="poster_img"></li>
			<li><img src="${pageContext.request.contextPath}/images/poster/poster4.jpg" width="250" height="330" class="poster_img"></li>
			<li><img src="${pageContext.request.contextPath}/images/poster/poster5.jpg" width="250" height="330" class="poster_img"></li>
			<li><img src="${pageContext.request.contextPath}/images/poster/poster6.jpg" width="250" height="330" class="poster_img"></li>
			<li><img src="${pageContext.request.contextPath}/images/poster/poster7.png" width="250" height="330" class="poster_img"></li>	
			<li><img src="${pageContext.request.contextPath}/images/poster/poster8.jpg" width="250" height="330" class="poster_img"></li>
			<li><img src="${pageContext.request.contextPath}/images/poster/poster9.jpg" width="250" height="330" class="poster_img"></li>
			<li><img src="${pageContext.request.contextPath}/images/poster/poster10.jpg" width="250" height="330" class="poster_img"></li>
			<li><img src="${pageContext.request.contextPath}/images/poster/poster11.jpg" width="250" height="330" class="poster_img"></li>
			<li><img src="${pageContext.request.contextPath}/images/poster/poster12.jpg" width="250" height="330" class="poster_img"></li>
		</ul>
	</div>
	<div class="controls">
		<span class="prev">◀</span>
		<span style="color:white; font-size:7px; justify-content: center; align-items: center; text-align:center;">Performance Poster</span>
		<span class="next">▶</span>
	</div>
</body>
<script type="text/javascript">

	var slides = document.querySelector('.slides'),
		slide = document.querySelectorAll('.slides li'),
		currentIdx = 0,
		slideCount = slide.length,
		prevBtn = document.querySelector('.prev'),
		slideWidth = 250,
		slideMargin = 30,
		nextBtn = document.querySelector('.next');
	
	slides.style.width = (slideWidth + slideMargin) * slideCount - slideMargin + 'px';
	
	setInterval('moveleft()',2500);

	function moveSlide(num) {
		slides.style.left = -num * 280 + 'px';
		currentIdx = num;
	}
	
	function moveleft() {
		if(currentIdx < slideCount - 7){
			moveSlide(currentIdx + 1);
			console.log(currentIdx);
		}else{
			moveSlide(0);
		}
	}
	
	nextBtn.addEventListener('click',function(){
		if(currentIdx < slideCount - 7){
			moveSlide(currentIdx + 1);
			console.log(currentIdx);
		}else{
			moveSlide(0);
		}
	});
	
	prevBtn.addEventListener('click',function(){
		if(currentIdx > 0){
			moveSlide(currentIdx - 1);
		}else{
			moveSlide(slideCount - 7);
		}	
	});

</script>
</html>
