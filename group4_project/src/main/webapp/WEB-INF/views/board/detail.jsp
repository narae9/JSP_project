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
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="content-main">
		<br>
		<h2 id="title_s">${board.bo_title}</h2>
		<ul class="detail-info">
			<li>
			<br><br>
				작성일 : ${board.bo_reg_date}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				수정일 : ${board.bo_mod_date}
			</li>
		</ul>
		<hr size="1" color="white" width="100%">
		<div style="height: 500px; overflow: auto">
		<p id="write_s">
			${board.bo_write}
		</p>
		</div>
		<hr size="1" color="white" width="100%">
		
		<!-- 댓글 시작 -->
		<div id="reply_div2">
			<span class="re-title">댓글 달기</span>
			<form id="re_form">
				<input type="hidden" name="bo_key" value="${board.bo_key}" id="bo_key">
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
		<!-- 댓글 목록 출력 끝 -->
		<!-- 댓글 끝 -->
		
		<!--
		<hr size="2" color="white" width="100%">
		<h3 id="nickname">닉네임님(me_key)</h3>
		<div id="reply_div">
			<input type="text" style="margin:0 15px; width:83%; height:20px;" 
			class="form-control"
				placeholder="댓글 작성란" onfocus="this.placeholder=''" 
				onblur="this.placeholder='댓글 작성란'"
				name="commentText">
			<input type="submit" class="btn-primary" value="등록">
		-->
			<!-- ??
			<form id="re_form">
				<input type="hidden" name="bo_key" value="${board.bo_key}" id="bo_key">
			</form>
			-->
			
		<!--
		</div>
		<ul>
			<li><br>닉네임(me_key)<br><br></li>
			<li>등록한 댓글(bom_key)</li>
			<li id="bom_regdate">작성일(bom_reg_date)</li>
			<li><br>
			<input type="submit" value="답글" onclick="">
			</li>
		</ul>
		-->
	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</div>
</body>
</html>





