<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>회원정보 수정</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/mypage.css" type="text/css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/member.js"></script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="content-box">
		<div class="menu-box">
			<div class="menu-title">회원정보 수정</div>
			<ul class="menu">
				<li><a href="${pageContext.request.contextPath}/member/myPage.do">마이 홈</a></li>
				<li><a class="active" href="${pageContext.request.contextPath}/member/modifyUserForm.do">회원정보 수정</a></li>
				<li><a href="#">예매/등록 정보</a></li>
			</ul>
		</div>		
		
		
		
	</div>
</body>
</html>