<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/slider.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/main.js"></script>
</head>
<body>
<div class="contents">
		<div class="main-slider" id="test3">
			<div class="ticket_logo" id="test5"
				onclick="location.href='${pageContext.request.contextPath}/show/showReservationForm.do'">

			</div>
		<div class="arrow_logo" id="test1">
		</div>
		</div>
		<div class="page-main" id="test2">
		<jsp:include page="/WEB-INF/views/common/header.jsp"/>
		<jsp:include page="/WEB-INF/views/common/slider.jsp"/>
		<div class="content-main">
		</div>
		<div class="arrow_logo2" id="test4" onclick="location.href='#test2'">
		</div>
		<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
	</div>
</div>
</body>
</html>





