<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html> 
<head>    
	<meta charset="UTF-8">
	<title>로그인</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/loginform.css" type="text/css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/member.js"></script>
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="content"><br><br><br><br><br><br><br>
		<form id="login_form" action="login.do" method="post">  
			<div class="input">
				<input type="text" name="id" id="id" maxlength="12" placeholder="ID" autocomplete="off"><br>
				<input type="password" name="passwd" id="passwd" placeholder="PassWord" maxlength="12"><br>
				<input type="submit" value="로그인">
			</div> 
		</form>
	</div>
</div>
<div style="margin: 50px 0 0;"><jsp:include page="/WEB-INF/views/common/footer.jsp"/></div>
</body>
</html>