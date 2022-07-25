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

</head>
<body>
<div id="contents">
		<div class="main-slider">
			<div class="ticket_logo"
				onclick="location.href='${pageContext.request.contextPath}/board/list.do'">

			</div>
			<div class="arrow_logo" id="test1" onclick="location.href='main.do'"></div>
		</div>
		<div class="page-main">
		<jsp:include page="/WEB-INF/views/common/header.jsp"/>
		<jsp:include page="/WEB-INF/views/common/slider.jsp"/>
		<div class="content-main">
		</div>
		<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
	</div>
</div>
</body>
</html>





