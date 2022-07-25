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
	width: 960px;
	margin: 0 auto;
	height: 300px;
	overflow: hidden;
}
.slides{
	position: absolute;
	left: 0;
	top: 0;
	transition:left 0.5s ease-out scale( 2.0 );
}
.slides li:not(:last-child){
	float: left;
	margin-right: 30px;
}
.controls{
	text-align: center;
	margin-top: 50px;
}
.controls span{
	background: none;
	color: white;
	border: none;
	cursor: pointer;
	font-size: 25px;
	text-align: center;
	border-radius: 2em;
}
.controls span:hover{
	color: gray;
}
.controls span.prev{rigth:calc(100% + 50px);}
.controls span.next{left:calc(100% + 50px);}
</style>
</head>
<body>
	<div class = "slide_wrapper">
		<ul class="slides">
			<li><img src="${pageContext.request.contextPath}/images/poster/poster1.jpg" width="300" height="300"></li>
			<li><img src="${pageContext.request.contextPath}/images/poster/poster2.jpg" width="300" height="300"></li>
			<li><img src="${pageContext.request.contextPath}/images/poster/poster3.jpg" width="300" height="300"></li>
			<li><img src="${pageContext.request.contextPath}/images/poster/poster4.jpg" width="300" height="300"></li>
			<li><img src="${pageContext.request.contextPath}/images/poster/poster5.jpg" width="300" height="300"></li>	
		</ul>
	</div>
	<p class="controls">
		<span class="prev">◀</span>
		<span class="next">▶</span>
	</p>
</body>
<script type="text/javascript">

	var slides = document.querySelector('.slides'),
		slide = document.querySelectorAll('.slides li'),
		currentIdx = 0,
		slideCount = slide.length,
		prevBtn = document.querySelector('.prev'),
		slideWidth = 300,
		slideMargin = 30,
		nextBtn = document.querySelector('.next');
	
	slides.style.width = (slideWidth + slideMargin) * slideCount - slideMargin + 'px';
	
	function moveSlide(num) {
		slides.style.left = -num * 330 + 'px';
		currentIdx = num;
	}
	
	nextBtn.addEventListener('click',function(){
		if(currentIdx < slideCount - 3){
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
			moveSlide(slideCount - 3);
		}	
	});

</script>
</html>
