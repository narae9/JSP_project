<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	
	<ul>
		<li>
		<!-- 지역별 찾기, 검색창 -->
		<form id="search_form" action ="list.do" method ="get"> <!-- 검색할땐 get을 주로 씀 -->
		<br><br><br>
			<ul class="search">
				<li>
					<input type="search" size="16" name="keyword" id="keyword" 
					value="${param.keyword}" placeholder="제목검색">
				</li>
				<li>
					<input type="submit" value="검색">
				</li>
			</ul>
		<br>
		</form>
		</li>
		
		<li>
		<!-- 리스트 띄우기 -->
		<table style="border-collapse: collapse">
			<tr>
				<th>대표이미지</th>
				<th>제목</th>
				<th>지역</th>
				<th>날짜</th>
				<th>평점</th>
			</tr>
			<c:forEach var="show" items="${list }"> 
			<tr>
				<td>${show.image }</td>
				<td>${show.title }</td>
				<td>${show.place }</td>
				<td>${show.date } 시간[${show.time }]</td>
				<td>${show.gpa }</td>
			</tr>
			</c:forEach>
		</table>
		</li>
	</ul>
	
	
	
</div>

</body>
</html>