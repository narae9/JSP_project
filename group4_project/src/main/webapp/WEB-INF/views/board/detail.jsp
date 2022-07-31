<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판상세글</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/board.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/board.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/board.reply.js"></script>
</head>
<body class="boardBody">
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="content-main">
		<br>
		<h2 id="title_s">${board.bo_title}</h2>
		<div id="reply_div2">
		<ul class="detail-info">
			<li>
			<br>
				작성일 : ${board.bo_reg_date}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				수정일 : ${board.bo_mod_date}
			</li>
		</ul>
		<div style="height: 500px; overflow: auto">
		<p id="write_s">
			${board.bo_write}
		</p>
		</div>
		
		<!-- 댓글 시작 -->
			<hr size="3" color="white" width="120px" align="left">
			<span class="re-title" onclick="location.href='detail.do?bo_key=${board.bo_key}'" style="cursor:pointer;">COMMENT</span>
			<form id="re_form">
				<input type="hidden" name="bo_key" value="${board.bo_key}" id="bo_key">
				
				<hr size="3" color="white" width="783px" align="right">
				
				<textarea rows="3" cols="50" name="bom_write" 
				  id="bom_write" class="rep-content"
				  <c:if test="${empty user_num}">disabled="disabled"</c:if>
				  ><c:if test="${empty user_num}">로그인해야 작성할 수 있습니다.</c:if></textarea>
				<c:if test="${!empty user_num}">
				<div id="re_first">
					<span class="letter-count">300/300</span>
				</div>
				<div id="re_second" class="align-right">
					<input type="submit" value="전송">
				</div>
				</c:if>
			</form>
		</div>
		<!-- 댓글 목록 출력 시작 -->
		<div id="output"></div>
		<div class="paging-button" style="display:none;">
			<input type="button" value="다음글 보기">
		</div>
		<div id="loading" style="display:none;">
			<img src="${pageContext.request.contextPath}/images/ajax-loader.gif">
		</div>
	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</div>
</body>
</html>





