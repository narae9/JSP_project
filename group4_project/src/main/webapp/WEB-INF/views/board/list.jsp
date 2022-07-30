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
		<br>
		<div style="text-align:center;">
		<span class="image blinking">
		<img id="boardlist_title" src="../images/title_ss.png" onclick="location.href='list.do'">
		</span>
		</div>
		<form id="search_form2" action="list.do" method="get">
			<ul class="search2">
				<li>
					<select name="keyfield" style="display:none">
						<option value="1"></option>
					</select>
				</li>
				<li>
					<input type="search" name="keyword" id="keyword_board2" value="${param.keyword}">
				</li>
				<li>
					<input type="submit" value="검색" id="keyword_btn2">
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
			<tr id="board-listth" style="box-shadow:0 0 5px 5px #5690a5;">
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
		<br><br>
		<div class="align-center">
			${page}
		</div>
		</c:if>
	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</div>
</body>
</html>







