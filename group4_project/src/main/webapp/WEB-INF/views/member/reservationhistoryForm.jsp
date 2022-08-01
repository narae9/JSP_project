<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>예매내역</title>
<style type="text/css">
.btn_main{
	width: 100%;
	margin-left: 105px;
}
#btn {
	box-shadow:inset 0px 0px 0px 0px #a6827e;
	background:linear-gradient(to bottom, rgba(255,134,113) 5%, rgba(255,134,113) 100%);
	background-color: rgba(255,134,113);
	border-radius:5px;
	border:2px solid rgba(230,90,65);
	display:inline-block;
	cursor:pointer;
	color:#ffffff;
	font-family:Arial;
	font-size:13px;
	padding:4px 50px;
	text-decoration:none;
	text-shadow:2px 2px 10px #4d3534;
}
#btn:hover {
	font-weight: bold;
}
#btn:active {
	position:relative;
	top:1px;
}
.reserve_form{
	width: 100%;
}
.re_table{
	margin: 0px;
	margin-left: 5px;
}
.re_table td, .re_table th{
	padding: 5px;
	font-weight: bold;
}
</style>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/mypage.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/member.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<div class="content-box">
	<div class="menu-box">
		<div class="menu-title">마이페이지</div>
			<ul class="menu">
				<li><a class="active" href="${pageContext.request.contextPath}/member/myPage.do">마이 홈</a></li>
				<li><a href="#">회원정보 수정</a></li>
				<li><a href="${pageContext.request.contextPath}/member/reservationhistoryForm.do" style="font-weight: bold; color:white;">예매/등록 정보</a></li>
			</ul>
		</div>		
		<div class="reserve_form">
		<h2 style="margin:20px;">예매내역</h2>
		<div>
		<form id="search_form" action="reservationhistoryForm.do" method="get">
			<div class="btn_main">
					<input type="hidden" name="keyword" id="keyword" value="1">
					<button name="keyfield" id="btn" value="2">전체 내역</button>
					<button name="keyfield" id="btn" value="0">예약 내역</button>
					<button name="keyfield" id="btn" value="1">취소 내역</button>
			</div>
		</form>		
		<c:if test="${count==0}">
			<div class="result-display">
				표시할 게시물이 없습니다.
			</div>
		</c:if>
		<c:if test="${count>0}">
		<table class="re_table">
			<tr>
				<th>공연제목</th>
				<th>공연장소</th>
				<th>공연일</th>
				<th>공연시간</th>
			</tr>
			<c:forEach var="board" items="${list}">
			<tr>
				<td>${board.sh_title}</td>
				<td><a href="#">${board.sh_place}</a></td>
				<td>${board.sh_date}</td>
				<td>${board.sh_time}</td>
			</tr>
			</c:forEach>
		</table>
		<div class="align-center">
			${page}
		</div>
		</c:if>
	</div>
</div>
</div>
</body>
</html>