<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>MY페이지</title>
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
				<li><a href="${pageContext.request.contextPath}/member/modifyUserForm.do">회원정보 수정</a></li>
				<li><a href="#">예매/등록 정보</a></li>
			</ul>
		</div>		  
		
		<div class="profile-box">
			<div class="profile-title">내 정보</div>
			<div class="profile-name">
				<span id="name">${member.me_name}</span> 
				<c:if test="${member.me_path == 1}">[<span id="path">일반 회원</span>]</c:if>
				<c:if test="${member.me_path == 2}">[<span id="path">공연자</span>]</c:if>
			</div>
			<div style="width: 400px"><hr></div>
			<div class="profile-detail">
				 생년월일 | ${member.me_agecode} <br>
				 전화번호 | ${member.me_phone} <br>
				 이메일 | ${member.me_email} <br>
				 주소 | (${member.me_zipcode}) ${member.me_add1} ${member.me_add2}
			</div>
			
		</div>
	</div>
</body>
</html>