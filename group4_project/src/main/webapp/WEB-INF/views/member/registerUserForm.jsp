<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>회원가입</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/registeruser.css" type="text/css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">	
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/member.js"></script>
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="content">
		<br><br><br><br>
		<form id="register_form" action="registerUser.do" method="post">
			<!-- 회원 구분 -->
			<div class="form_path">
				<label><input type="radio" id="path" name="path" value="1" checked>일반회원</label>
				<label><input type="radio" id="path" name="path" value="2">공연자</label>
			</div>
			
			<!-- 아이디/중복확인 -->
			<div class="form_text">
				<span class="text_f">아이디</span><br>
				<input type="text" id="id" name="id" maxlength="10" autocomplete="off">
				<input type="button" id="id_check" value="중복확인"><br>
				<div class="form_notice">*영문 또는 숫자(4자~10자)</div>
				<span id="message_id" style="text-align: right;"></span>
			</div>
			
			<!-- 비밀번호 -->
			
		</form>
	</div>
</div>

</body>
</html>