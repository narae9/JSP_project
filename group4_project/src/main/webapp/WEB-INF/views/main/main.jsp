<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
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
			<table id="main-table">
				<tr bgcolor="#5690a5">
					<th id="main-tableth">문의 및 공지</th>
					<th></th>
				</tr>
				<c:forEach var="board" items="${boardList}">
				<tr>
					<td><a href="${pageContext.request.contextPath}/board/detail.do?bo_key=${board.bo_key}" target="_blank">${fn:substring(board.bo_title,0,12)}</a></td>
					<td style="font-size:13px; text-align:right">(${board.bo_reg_date})</td>
				</tr>
				</c:forEach>
			</table>
		<video controls muted poster="../images/mp4poster.png" preload="none">
 				<source src="../images/king01.mp4" type="video/mp4">
				</video>
		</div>
		<div class="arrow_logo2" id="test4" onclick="location.href='main.do'">
		</div>
		<br><br>
	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</div>
</body>
</html>





