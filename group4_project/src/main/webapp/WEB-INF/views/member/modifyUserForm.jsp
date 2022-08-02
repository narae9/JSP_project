<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
			<div class="menu-title">마이페이지</div>
			<ul class="menu">
				<li><a href="${pageContext.request.contextPath}/member/myPage.do">마이 홈</a></li>
				<li><a class="active" href="${pageContext.request.contextPath}/member/modifyUserForm.do">회원정보 수정</a></li>
				<li><a href="#">예매/등록 정보</a></li>  
			</ul>
		</div>	  	
		
		<div class="profile-box">
			<div class="modify-title">회원 정보 수정</div>
			
			<form id="modify_form" action="modifyUser.do" method="post">
				<div class="left">
					<!-- ID -->
					<div>
						<div class="sub_title">ID |</div>
						<div class="me_id">${member.me_id}</div>
					</div>
					
					<!-- PW -->
					<div style="margin-top: 5px;">
						<div class="sub_title">PW |</div>
						<!-- 비밀번호 설정 페이지로 넘어가게 버튼 만들기 -->
						<input type="button" value="비밀번호 재설정" onclick="#">
					</div>
					
					<!-- 이름 -->
					<div style="margin-top: 5px;">
						<div class="sub_title">이름 |</div>
						<input type="text" value="${member.me_name}" name="name" id="name" maxlength="15">
					</div>
					
					<!-- 생년월일 -->
					<div style="margin-top: 10px;">
						<div class="sub_title">생년월일 |</div>
						<input type="date" value="${member.me_agecode}" name="agecode" id="agecode">
					</div>
					
					<!-- 이메일 -->
					<div style="margin-top: 10px;">
						<div class="sub_title">E-mail |</div>
						<input type="text" value="${member.me_email}" name="email" id="email" maxlength="50">
					</div>
					
					<!-- 전화번호 -->
					<div style="margin-top: 10px;">
						<div class="sub_title">전화번호 |</div>
						<input type="text" value="${member.me_phone}" name="phone" id="phone" maxlength="15">
					</div>
				</div>
				
				<div class="right">
					<!-- 우편번호 -->
					<div style="margin-top: 10px;">
						<script type="text/javascript">
							var zipcode = ${member.me_zipcode};
							var result = "0"+zipcode;
							result = result.slice(-5);
							
							console.log(result);							
						</script>
						<div class="sub_title">우편번호 | <input type="button" onclick="sample2_execDaumPostcode()" value="우편번호 찾기"></div>
						<input type="text" value="${member.me_add1}" name="zipcode" id="zipcode" maxlength="5">
					</div>
					
					<!-- 기본주소 --><!-- 상세주소 -->
					<div style="margin-top: 10px;">
						<div class="sub_title">주소 |</div>
						<input type="text" value="${member.me_add1}" name="add1" id="add1" maxlength="100">
						<input type="text" value="${member.me_add2}" name="add2" id="add2" maxlength="100">
					</div>
					
					<!-- 수정버튼 -->
					<input type="submit" value="수정">
				</div>
				
			</form>
		</div>
		
	</div>
</body>
</html>