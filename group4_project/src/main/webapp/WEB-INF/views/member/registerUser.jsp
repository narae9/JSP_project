<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>회원가입</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/loginform.css" type="text/css">
</head>
<body>
	<div class="page-main">
		<jsp:include page="/WEB-INF/views/common/header.jsp"/>
		<div class="content"><br><br><br><br>
			<div class="result_regist">
				<div class="align-center">
					<span class="message">회원가입이 완료되었습니다!</span>
					<br>
					<input type="button" value="첫 화면으로 돌아가기" onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
				</div>
			</div>
		</div>
	</div>	
</body>
</html>