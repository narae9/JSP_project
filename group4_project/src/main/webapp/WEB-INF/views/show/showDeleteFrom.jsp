<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/show.css"/>
</head>
<body>
<div class="page-main">
	<div class="align-center">
		<h1>삭제가 완료되었습니다.</h1>
		<br>
		<ul>
			<li>
				<input type="button" value="목록으로" onclick="location.href='${pageContext.request.contextPath}/show/showReservationForm.do">
				<input type="button" value="메인으로" onclick="location.href='${pageContext.request.contextPath}/main/main.do">		
			</li>
		</ul>
	</div>
</div>

</body>
</html>