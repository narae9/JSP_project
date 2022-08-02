<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>회원탈퇴</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/mypage.css" type="text/css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/member.js"></script>
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="delete-box">
		<div class="title_d">회원탈퇴</div>
		<form action="deleteUser.do" method="post" id="delete_form">
			<div class="sub_D">
				<label for="id">아이디</label>
				<input type="text" name="id" id="id" maxlength="12">
			</div>
			<!-- 
			<div class="sub_D">
				<label for="email">이메일</label>
				<input type="text" name="email" id="email" maxlength="50">
			</div> -->
			
			<div class="sub_D">
				<label for="passwd">비밀번호</label>
				<input type="password" name="passwd" id="passwd" maxlength="15">
			</div>
		
			<div class="sub_D">
				<label for="cpasswd">비밀번호 확인</label>
					<input type="password" name="cpasswd" id="cpasswd" maxlength="15">
					<div id="message_id" style="width: 430px; text-align: right; font-size: 15px;"></div>       
			</div>
			
			<div style="margin: 45px 0 0;">
				<input type="submit" value="회원탈퇴">
				<input type="button" value="My페이지" onclick="location.href='myPage.do'">
			</div>
		</form>
	</div>
</div>
<div><jsp:include page="/WEB-INF/views/common/footer.jsp"/></div>
</body>
</html>