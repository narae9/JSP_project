<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>커뮤니티 게시판</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/community.js"></script>
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="content-main">
		<br><br><br>
		<h3 onclick="location.href='list.do'" style="width:90px; cursor:pointer">커뮤니티</h3>
		<hr size="4" color="white" width="8%" align="left">
		<br>
		<form id="search_form" action="list.do" 
		                                    method="get">
			<ul class="search">
				<li>
					<select name="keyfield">
						<option value="1">제목</option>
						<option value="2">작성자</option>
					</select>
				</li>
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
		<div class="list-space align-right">
		    <c:if test="${!empty user_num}">
		    <input type="button" value="글쓰기"
			   onclick="location.href='writeForm.do'">
			</c:if>   
			<input type="button" value="목록"
			       onclick="location.href='list.do'"> 
			<input type="button" value="홈으로"
			 onclick="location.href='${pageContext.request.contextPath}/main/main.do'">         
		</div>
		<c:if test="${count == 0}">
		<div class="result-display">
			표시된 게시물이 없습니다.
		</div>
		</c:if>
		<c:if test="${count > 0}">
		<table>
			<tr bgcolor="D3D3D3">
				<th width="8%" style="color:black">번호</th>
				<th width="43%" style="color:black">제목</th>
				<th width="17%" style="color:black">작성자</th>
				<th width="12%" style="color:black">작성일</th>
				<th width="12%" style="color:black">수정일</th>
				<th width="8%" style="color:black">조회</th>
			</tr>
			<c:forEach var="board" items="${list}">
			<tr>
				<td align="center" style="font-weight:bold">${comm.co_key}</td>
				<td style="text-decoration:underline"><a href="detail.do?co_key=${comm.co_key}">${comm.co_title}</a></td>
				<td align="center">${comm.me_id}</td>
				<td align="center">${comm.co_reg_date}</td>
				<td align="center">${comm.co_mod_date}</td>
				<td align="center">${comm.co_index}</td>
			</tr>
			</c:forEach>
		</table>
		<div class="align-center">
			${page}
		</div>
		</c:if>
	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</div>
</body>
</html>







