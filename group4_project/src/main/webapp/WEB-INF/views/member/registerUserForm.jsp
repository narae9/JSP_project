<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>회원가입</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
<%-- 	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/registeruser.css" type="text/css">
 --%>	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/member.js"></script>
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="content-main">
		<form id="register_form" action="registerUser.do" method="post">
			<ul>
				<!-- 회원 구분 -->
				<li>
					<label><input type="radio" id="path" name="path" value="1" checked>일반회원</label>
					<label><input type="radio" id="path" name="path" value="2">공연자</label><br><br>
				</li>
				
				<!-- 아이디 // 수정예정 -->
				<li>
					<label for="id">아이디</label>
					<input type="text" id="id" name="id" maxlength="10" autocomplete="off">
					<!-- <input type="button" id="id_check" value="ID중복체크">
					<span id="message_id"></span>
					<div class="form-notice">*영문 또는 숫자(4자~10자)</div><br> -->
				</li>
				
				<!-- 비밀번호 -->
				<li>					
					<label for="passwd">비밀번호</label>
					<input type="password" name="passwd" id="passwd" maxlength="15"><br>
				</li>
				
				<!-- 비밀번호 재확인 -->
				<li>
					
				</li>
				
				<!-- 이름 -->
				<li>
					<label for="name">이름</label>
					<input type="text" id="name" name="name" maxlength="20"><br>
				</li>
				
				<!-- 생년월일 -->
				<li>
					<label for="name">생년월일</label>
					<input type="date" id="agecode" name="agecode"><br>
				</li>
				
				<!-- 이메일 -->
				<li>
					<label for="email">이메일</label>
					<input tpye="text" id="email" name="email" maxlength="50"><br>
				</li>
				
				<!-- 전화번호 -->
				<li>
					<label for="phone">전화번호</label>
					<input type="text" id="phone" name="phone" maxlength="15">
				</li>
				
				<!-- 우편번호 // 수정예정 -->
				<li>
					<label for="zipcode">우편번호</label>
					<input type="text" id="zipcode" name="zipcode" maxlength="5" autocomplete="off">
					<!-- 우편번호 찾기 버튼 -->
				</li>
				
				<!-- 기본주소 -->
				<li>
					<label for="add1">주소</label>
					<input type="text" id="add1" name="add1" maxlength="100">
				</li>
				
				<!-- 상세주소 -->
				<li>
					<label for="add2">주소</label>
					<input type="text" id="add2" name="add2" maxlength="100">
				</li>
				
			</ul>
			
			<input type="submit" value="등록">
		</form>
	</div>
</div>
</body>
</html>