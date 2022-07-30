<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문의 및 공지사항</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/board.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/board.js"></script>
</head>
<body class="boardBody">
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="content-main">
		<br><br><br>
		<h3 onclick="location.href='list.do'" style="width:100px; cursor:pointer">문의게시판</h3>
		<hr size="4" color="white" width="95px" align="left">
		<form id="search_form" action="list.do" method="get">
			<ul class="search">
				<li>
					<input type="search" size="16" 
					  name="keyword" id="keyword_board"
					  value="${param.keyword}">
				</li>
				<li>
					<input type="submit" value="검색">
				</li>
			</ul>
		</form>
		<c:if test="${count == 0}">
		<div class="result-display">
			표시된 게시물이 없습니다.
		</div>
		</c:if>
		<c:if test="${count > 0}">
		<div class="board-listtable">
		<table id="list_table">
			<tr bgcolor="#5690a5" id="board-listth">
				<th width="10%" style="border-radius:10px 0 0 10px;">번호</th>
				<th width="50%">제목</th>
				<th width="20%">작성일</th>
				<th width="20%" style="border-radius:0 10px 10px 0;">수정일</th>
			</tr>
			<c:forEach var="board" items="${list}">
			<tr id="table-border">
				<td align="center" style="font-weight:bold">${board.bo_key}</td>
				<td style="font-size:18px;"><a href="detail.do?bo_key=${board.bo_key}">${board.bo_title}</a></td>
				<td align="center">${board.bo_reg_date}</td>
				<td align="center">${board.bo_mod_date}</td>
			</tr>
			</c:forEach>
		</table>
		</div>
		<div class="align-center">
		
			${page}
		</div>
		</c:if>
	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</div>
</body>
</html>







