<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>Slide Project</title>
  <style>
*, *:before, *:after { 
	box-sizing: inherit; 
}
.slider_main {
	margin-top: 80px;
	padding-top: 30px;
	width: 100%; 
	height: 690px;
	background-color: rgba(251,176,76);
}
.clearfix:after { 
	content: ''; 
	display: block; 
	clear: both; 
	float: none; 
}
/* container - body */
#container { 
	width: 1000px; 
	margin: auto; 
}
.slide_wrap { 
	position: relative; 
	width: 400px; 
	margin: auto; 
	padding-bottom: 30px; 
}
.slide_box { 
	width: 100%; 
	margin: auto; 
	overflow-x: hidden;
	
}
.slide_content { 
	display: table; 
	float: left; 
	width: 400px; 
	height: 570px; 
}

.slide_content > p { 
	display: table-cell; 
	vertical-align: middle; 
	text-align: center; 
	font-size: 100px; 
	font-weight: bold; 
	color: #555;
}
.slide_content.slide01 { 
	color: white;
	text-align: center;
	font-weight: bold;
	font-size: 25px;  
}
.slide_content.slide02 { 
	color: white;
	text-align: center;
	font-weight: bold;
	font-size: 25px; 
}
.slide_content.slide03 { 
	color: white;
	text-align: center;
	font-weight: bold;
	font-size: 25px; 
}
.slide_content.slide04 { 
	color: white;
	text-align: center;
	font-weight: bold;
	font-size: 25px; 
}
.slide_content.slide05 { 
	color: white;
	text-align: center;
	font-weight: bold;
	font-size: 25px; 
}
.slide_btn_box > button { 
	position: absolute; 
	top: 50%; 
	margin-top: -45px; 
	width: 60px; 
	height: 60px; 
	font-size: 30px; 
	color: white; 
	background: none; 
	cursor: pointer;
	border: none;
	z-index: 2; 
}
.slide_btn_box > .slide_btn_prev { 
	left: -100px; 
}
.slide_btn_box > .slide_btn_next { 
	right: -100px; 
}
.slide_btn_box > button:hover {
	color:gray;
}
.slide_pagination { 
	position: absolute; 
	left: 50%; 
	bottom: 0; 
	list-style: none; 
	margin: 0; 
	padding: 0; 
	transform: translateX(-50%);
	z-index: 3;  
}
.slide_pagination .dot { 
	display: inline-block; 
	width: 15px; 
	height: 15px; 
	margin: 0 5px; 
	overflow: hidden; 
	background: #ddd; 
	border-radius: 50%; 
	transition: 0.3s; 
}
.slide_pagination .dot.dot_active { 
	background: #333; 
}
.slide_pagination .dot a { 
	display: block; 
	width: 100%; 
	height: 100%; 
}
/*slider_second*/
.slider_second{
	position: absolute;
	top: 1350px;
	width:100%;
	height: 580px;
	z-index: 1;
}
.slider_second_box1{
	width:380px;
    height:500px;
    overflow:hidden;
    position: absolute;
  	top: 10%;
  	left: 80px;
  	background-image: url(${pageContext.request.contextPath}/images/poster/slider_second_img1.webp);
}
.slider_second_box1:hover {
  transform: scale(1.2);
  transition: transform 1s;
  filter: brightness(80%);
}
.slider_second_box1:hover #explain{
	opacity: 1; 
}
.slider_second_box1:hover #explain1{
	opacity: 1; 
}
.slider_second_box1 > p {
	opacity: 0;
	vertical-align: middle; 
	text-align: center; 
	font-size: 75px;
	font-weight: bold; 
	color: white; 
}
.slider_second_box1_text1 > p{
	writing-mode: vertical-rl;
	opacity: 0;
}
.slider_second_box2_1{
	width:300px;
    height:400px;
    overflow:hidden;
    position: absolute;
  	left:1600px;
}
.slider_second_box2_1 > img{
	position: absolute;
	top: 0px;
	left: 0px;
	width: 100%;
	height: 100%;
}
.slider_second_box2_2{
	width:400px;
    height:500px;
    overflow:hidden;
    position: absolute;
    top:50px;
  	left:1250px;
}
.slider_second_box2_2 > img{
	position: absolute;
	top: 0px;
	left: 0px;
	width: 100%;
	height: 100%;
}
  </style>
</head>
<body>
<div class="slider_main">
	  <div id="container">
	    <div class="slide_wrap">
	      <div class="slide_box">
	        <div class="slide_list clearfix">
	          <div class="slide_content slide01">
	          	<img src="${pageContext.request.contextPath}/images/poster/poster1.jpg" width="400px" height="570px"><br>
	          	홍대 라이브 버스킹데이<br>
	          	5.27 13:10
	          </div>
	          <div class="slide_content slide02">
	          <img src="${pageContext.request.contextPath}/images/poster/poster2.jpg" width="400px" height="570px"><br>
	         	헬로우 버스킹<br>
	          	8.8 18:30
	          </div>
	          <div class="slide_content slide03">
	          <img src="${pageContext.request.contextPath}/images/poster/poster3.jpg" width="400px" height="570px"><br>
	          	간이역 버스킹<br>
	          	5.31 18:00
	          </div>
	          <div class="slide_content slide04">
	          <img src="${pageContext.request.contextPath}/images/poster/poster4.jpg" width="400px" height="570px"><br>
	          	힐링 버스킹<br>
	          	11.21 17:30
	          </div>
	          <div class="slide_content slide05">
	          <img src="${pageContext.request.contextPath}/images/poster/poster5.jpg" width="400px" height="570px"><br>
	          	문화의 거리 버스킹<br>
	          	9.10 17:30
	          </div>
	        </div>
	        <!-- // .slide_list -->
	      </div>
	      <!-- // .slide_box -->
	      <div class="slide_btn_box">
	        <button type="button" class="slide_btn_prev">◀</button>
	        <button type="button" class="slide_btn_next">▶</button>
	      </div>
	      <!-- // .slide_btn_box -->
	      <ul class="slide_pagination"></ul>
	      <!-- // .slide_pagination -->
	    </div>
	    <!-- // .slide_wrap -->
	  </div>
	  <!-- // .container -->
</div>
<!-- // .slider_main -->
<div class="slider_second">
	<div class="slider_second_box1"><p id = "explain">FIND OUT IF YOU REALLY EXIST</p></div>
	<div class="slider_second_box1_text1"><p id = "explain1">Let's have fun with Illu, I hate being still</p></div>
	<!--  <div class="slider_second_box2_1"><img src="${pageContext.request.contextPath}/images/poster/slider_second_img2_1.webp"></div >-->
	<!-- <div class="slider_second_box2_2"><img src="${pageContext.request.contextPath}/images/poster/slider_second_img2_2.gif"></div>-->
	<div class="slider_second_box2_3"></div>
	<div class="slider_second_box2_4"></div>
</div>
<!-- // .slider_second -->
  <script>
    (function () {
      const slideList = document.querySelector('.slide_list');  // Slide parent dom
      const slideContents = document.querySelectorAll('.slide_content');  // each slide dom
      const slideBtnNext = document.querySelector('.slide_btn_next'); // next button
      const slideBtnPrev = document.querySelector('.slide_btn_prev'); // prev button
      const pagination = document.querySelector('.slide_pagination');
      const slideLen = slideContents.length;  // slide length
      const slideWidth = 400; // slide width
      const slideSpeed = 300; // slide speed
      const startNum = 0; // initial slide index (0 ~ 4)
      
      slideList.style.width = slideWidth * (slideLen + 2) + "px";
      
      // Copy first and last slide
      let firstChild = slideList.firstElementChild;
      let lastChild = slideList.lastElementChild;
      let clonedFirst = firstChild.cloneNode(true);
      let clonedLast = lastChild.cloneNode(true);

      // Add copied Slides
      slideList.appendChild(clonedFirst);
      slideList.insertBefore(clonedLast, slideList.firstElementChild);

      // Add pagination dynamically
      let pageChild = '';
      for (var i = 0; i < slideLen; i++) {
        pageChild += '<li class="dot';
        pageChild += (i === startNum) ? ' dot_active' : '';
        pageChild += '" data-index="' + i + '"><a href="#"></a></li>';
      }
      pagination.innerHTML = pageChild;
      const pageDots = document.querySelectorAll('.dot'); // each dot from pagination

      slideList.style.transform = "translate3d(-" + (slideWidth * (startNum + 1)) + "px, 0px, 0px)";

      let curIndex = startNum; // current slide index (except copied slide)
      let curSlide = slideContents[curIndex]; // current slide dom
      curSlide.classList.add('slide_active');

      /** Next Button Event */
      slideBtnNext.addEventListener('click', function() {
        if (curIndex <= slideLen - 1) {
          slideList.style.transition = slideSpeed + "ms";
          slideList.style.transform = "translate3d(-" + (slideWidth * (curIndex + 2)) + "px, 0px, 0px)";
        }
        if (curIndex === slideLen - 1) {
          setTimeout(function() {
            slideList.style.transition = "0ms";
            slideList.style.transform = "translate3d(-" + slideWidth + "px, 0px, 0px)";
          }, slideSpeed);
          curIndex = -1;
        }
        curSlide.classList.remove('slide_active');
        pageDots[(curIndex === -1) ? slideLen - 1 : curIndex].classList.remove('dot_active');
        curSlide = slideContents[++curIndex];
        curSlide.classList.add('slide_active');
        pageDots[curIndex].classList.add('dot_active');
      });

      /** Prev Button Event */
      slideBtnPrev.addEventListener('click', function() {
        if (curIndex >= 0) {
          slideList.style.transition = slideSpeed + "ms";
          slideList.style.transform = "translate3d(-" + (slideWidth * curIndex) + "px, 0px, 0px)";
        }
        if (curIndex === 0) {
          setTimeout(function() {
            slideList.style.transition = "0ms";
            slideList.style.transform = "translate3d(-" + (slideWidth * slideLen) + "px, 0px, 0px)";
          }, slideSpeed);
          curIndex = slideLen;
        }
        curSlide.classList.remove('slide_active');
        pageDots[(curIndex === slideLen) ? 0 : curIndex].classList.remove('dot_active');
        curSlide = slideContents[--curIndex];
        curSlide.classList.add('slide_active');
        pageDots[curIndex].classList.add('dot_active');
      });

      /** Pagination Button Event */
      let curDot;
      Array.prototype.forEach.call(pageDots, function (dot, i) {
        dot.addEventListener('click', function (e) {
          e.preventDefault();
          curDot = document.querySelector('.dot_active');
          curDot.classList.remove('dot_active');
          
          curDot = this;
          this.classList.add('dot_active');

          curSlide.classList.remove('slide_active');
          curIndex = Number(this.getAttribute('data-index'));
          curSlide = slideContents[curIndex];
          curSlide.classList.add('slide_active');
          slideList.style.transition = slideSpeed + "ms";
          slideList.style.transform = "translate3d(-" + (slideWidth * (curIndex + 1)) + "px, 0px, 0px)";
        });
      });
      
      /*background*/
    })();
  </script>
</body>
</html>    

