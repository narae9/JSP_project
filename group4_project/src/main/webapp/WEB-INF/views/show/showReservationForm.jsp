<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/show.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/show.js"></script>
<style type="text/css">

#search_form{
	float:right;
	margin: 0 0 0 90%;  
}
table td, table th {
	border:0;
    padding: 10px;
    color: white;   
}
hr{
	noshade:noshade;
	color:white;   
}
select{
	border-radius: 10px;
	height:27px;
}

</style>
</head>
<body class="showBody">
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<h2>&nbsp;&nbsp;&nbsp;공연목록</h2>
	<!-- 지역별 찾기, 검색창 -->
	<form id="search_form" action ="searchList.do" method ="get" > <!-- 검색할땐 get을 주로 씀 -->
		<ul class="search" >
			<li>
				<input type="search" name="keyword" id="keyword">
			</li>
			<li>
				<input type="submit" value="검색" id="keyword_btn">
			</li>
			<li>
				<select name="keyfield">
					<option value="1">제목</option>
					<option value="2">지역</option>
					<option value="3">내용</option>
				</select>
			</li>
		</ul>
	<br>
	</form>
	
	<div class="align-left" style="margin:0 4% 0 0">
	<ul>
		<li>
		<!-- 리스트 띄우기 -->
		<table class="borderNone"> 
			<tr bgcolor="#5690a5">
				<th style="width:80px;border-radius:10px 0 0 10px;">대표이미지</th>
				<th>제목</th>
				<th>지역</th>
				<th>날짜</th>
				<th style="border-radius:0 10px 10px 0;">평점</th>
			</tr>
			<c:forEach var="show" items="${list }"> 
			<tr style="text-size:30px;border-bottom:2px solid white;">
				<c:if test="${!empty show.sh_img}">
					<td><a href="showDetailForm.do?sh_key=${show.sh_key }"><img src="${pageContext.request.contextPath}/upload/${show.sh_img}" class="photo_size"></a></td>
				</c:if>
				<c:if test="${empty show.sh_img }">
					<td><a href="showDetailForm.do?sh_key=${show.sh_key }"><img src="${pageContext.request.contextPath}/images/blank2.png" class="photo_size"></a></td>
				</c:if>
				<td><h3><a href="showDetailForm.do?sh_key=${show.sh_key }">${show.sh_title }</a></h3></td>
				<td><h3>${show.sh_place }</h3></td>
				<td><h3>${show.sh_date } 시간[${show.sh_time }]</h3></td>
				<td><h3>${show.sre_gpa }</h3></td>
			</tr>
			
			</c:forEach>
		</table>
		</li>   
	</ul>
	</div>
	<br>
	<div class="align-center">
		${page }
	</div>
	<br><br><br>
	
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
	
</div>

</body>
</html>