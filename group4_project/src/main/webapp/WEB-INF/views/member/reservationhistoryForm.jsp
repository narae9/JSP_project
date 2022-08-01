<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>예매내역</title>
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
				<li><a class="active" href="${pageContext.request.contextPath}/member/myPage.do">마이 홈</a></li>
				<li><a href="#">회원정보 수정</a></li>
				<li><a href="${pageContext.request.contextPath}/member/reservationhistoryForm.do" style="font-weight: bold; color:white;">예매/등록 정보</a></li>
			</ul>
		</div>		
		<div>
		<h2>게시판 목록</h2>
		<div>
		<form id="search_form" action="list.do" method="get">
			<ul class="search">
				<li>
					<select name="keyfield">
						<option value="1">제목</option>
						<option value="2">작성자</option>
						<option value="3">내용</option>
					</select>
				</li>
				<li>
					<input type="search" size="16" name="keyword" id="keyword" value="${param.keyword}">
				</li>
				<li>
					<input type="submit" value="검색">
				</li>
			</ul>		
		</form>		
		<div class="list-space align-right">
		<c:if test="${!empty user_num}">
			<input type="button" value="글쓰기" onclick="location.href='writeForm.do'">
		</c:if>
			<input type="button" value="목록" onclick="location.href='list.do'">
			<input type="button" value="홈으로" onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
		</div>
		<c:if test="${count==0}">
			<div class="result-display">
				표시할 게시물이 없습니다.
			</div>
		</c:if>
		<c:if test="${count>0}">
		<table>
			<tr>
				<th>글번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>조회</th>
			</tr>
			<c:forEach var="board" items="${list}">
			<tr>
				<td>${board.board_num}</td>
				<td><a href="detail.do?board_num=${board.board_num}">${board.title}</a></td>
				<td>${board.id}</td>
				<td>${board.reg_date}</td>
				<td>${board.hit}</td>
			</tr>
			</c:forEach>
		</table>
		<div class="align-center">
			${page}
		</div>
		</c:if>
	</div>
</div>
</div>
</body>
</html>