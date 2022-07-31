<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/main.js"></script>
</head>
<body>
<div class="contents">
		<div class="main-slider" id="test3">
			<div class="ticket_logo" id="test5"
				onclick="location.href='${pageContext.request.contextPath}/show/showReservationForm.do'">

			</div>
		<div class="arrow_logo" id="test1">
		</div>
		</div>
		<div class="page-main-first" id="test2">
		<jsp:include page="/WEB-INF/views/common/header.jsp"/>
		<div class="slider_main">
			<div class="slider_main_img1_box">
				<img src="${pageContext.request.contextPath}/images/poster/slider_main_img1.jpg" class="slider_main_img1" style="width: 100%; height: 100%; border-radius: 2em;">
			</div>
			<div class="slider_main_font1">
				<!-- <img src="${pageContext.request.contextPath}/images/poster/font1.png" style="width: 100%; height: 100%;">-->
				<span>
					ENJOY<br>
					PARADE
				</span>
			</div>
			<div class="slider_main_font2">
				<img src="${pageContext.request.contextPath}/images/poster/font2.png" style="width: 100%; height: 100%;">
			</div>
			<div class="slider_main_img2_box">
				<img src="${pageContext.request.contextPath}/images/poster/slider_main_img2.jpg" class="slider_main_img2" style="width: 100%; height: 100%; border-radius: 0.5em;">
			</div>
			<div class="slider_main_font3">
				<span>
					RESERVE NOW
				</span>
			</div>
			<div class="slider_main_font4">
				<img src="${pageContext.request.contextPath}/images/poster/font5.png" style="width: 100%; height: 100%;">
			</div>
			<div class="slider_main_font5">
				<span>
					RESERVE INTERESTING COLORFUL@VARIOUS
				</span>
			</div>
		</div>
		<div class = "middle-line">
			I HATE BEING BORED
		</div>		
		<jsp:include page="/WEB-INF/views/common/slider1.jsp"/>
		<div class = "middle-line2">
			SOMETHING AMAZING IS ABOUT TO HAPPEN
		</div>
		<div class="content-main-first">
			<div class="content-main-first_1">
				<img src="${pageContext.request.contextPath}/images/poster/content-main-first_1.gif" style="width: 80%; height: 90%; border-radius:5px;">
				<div class="content-main-first_1_1">
					<img src="${pageContext.request.contextPath}/images/poster/content-main-first_1_1.gif" style="width: 70%; height: 90%; box-shadow: 1px 1px 1px 1px #adb5bd; border-radius:5px;">
				</div>
			</div>
			<div class="content-main-first_1">
				<img src="${pageContext.request.contextPath}/images/poster/content-main-first_2.jpg" style="width: 80%; height: 90%; border-radius:5px;">
				<div class="content-main-first_1_1">
					<img src="${pageContext.request.contextPath}/images/poster/content-main-first_2_1.gif" style="width: 70%; height: 90%; box-shadow: 1px 1px 1px 1px #adb5bd; border-radius:5px;">
				</div>
			</div>
			<div class="content-main-first_1">
				<img src="${pageContext.request.contextPath}/images/poster/content-main-first_3.jpg" style="width: 80%; height: 90%; border-radius:5px;">
				<div class="content-main-first_1_1">
					<img src="${pageContext.request.contextPath}/images/poster/content-main-first_3_1.png" style="width: 70%; height: 90%; box-shadow: 1px 1px 1px 1px #adb5bd; border-radius:5px;">
				</div>
			</div>
			<div class="content-main-first_1">
				<img src="${pageContext.request.contextPath}/images/poster/content-main-first_4.jpg" style="width: 80%; height: 90%; border-radius:5px;">
				<div class="content-main-first_1_1">
					<img src="${pageContext.request.contextPath}/images/poster/content-main-first_4_1.gif" style="width: 70%; height: 90%; box-shadow: 1px 1px 1px 1px #adb5bd; border-radius:5px;">
				</div>
			</div>
			<div class="content-main-font3">
				<img src="${pageContext.request.contextPath}/images/poster/font4.png" style="width: 100%; height: 100%;">
			</div>
		</div>
		<div class = "middle-line">
			FOR A GOOD TIME CALL
		</div>
		<div class="content-main-second">
			<div class="content-main-second-table">
				<table id="main-table">
					<tr style="background:rgba(255,134,113);">
						<th id="main-tableth">문의 및 공지</th>
						<th id="main-tableth"></th>
					</tr>
					<c:forEach var="board" items="${boardList}">
					<tr>
						<td id="main-tabletd" style="background:rgba(255,134,113);"><a href="${pageContext.request.contextPath}/board/detail.do?bo_key=${board.bo_key}" target="_blank">${fn:substring(board.bo_title,0,12)}</a></td>
						<td id="main-tabletd" style="font-size:13px; text-align:right; background:rgba(255,134,113);">(${board.bo_reg_date})</td>
					</tr>
					</c:forEach>
				</table>
			</div>
			<div class="content-main-second-video">
				<video controls muted poster="../images/mp4poster.png" preload="none">
 					<source src="../images/king01.mp4" type="video/mp4">
				</video>
			</div>
		</div>
		<div class="arrow_logo2" id="test4" onclick="location.href='main.do'">
		</div>

	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</div>
</body>
</html>





