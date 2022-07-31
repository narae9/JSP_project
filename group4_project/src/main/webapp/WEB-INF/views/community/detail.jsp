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
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/community.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/community.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/community.fav.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/community.reply.js"></script>
</head>
<body class="boardBody">
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="content-main">
		<br>
		<h2 id="title_s">${comm.co_title}</h2>
		<div id="reply_div2">
		<ul class="detail-info">
			<li>
			<br>
				작성일 : ${comm.co_reg_date}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				수정일 : ${comm.co_mod_date}
			</li>
			<li>
			<br>
			<img id="eyes" src="../images/eye5.png">
			(${comm.co_index})
			</li>
		</ul>
		<div style="height: 500px; overflow: auto">
		<p id="write_s">
			${comm.co_write}
		</p>
		</div>
		
		<ul class="detail-sub">
			<li>
				<%-- 좋아요 --%>
				<img id="output_fav2" src="${pageContext.request.contextPath}/images/nolove.png">
				<span id="output_fcount2"></span>
			</li>
			<li>
				<%-- 로그인한 회원번호와 작성자 회원번호가 일치해야 수정,삭제 가능 --%>
				<c:if test="${user_num == comm.me_key}">
				<input type="button" value="수정" 
				 onclick="location.href='updateForm.do?board_num=${board.board_num}'">
				<input type="button" value="삭제" id="delete_btn">
				<script type="text/javascript">
					let delete_btn = document.getElementById('delete_btn');
					//이벤트 연결
					delete_btn.onclick=function(){
						let choice = confirm('삭제하시겠습니까?');
						if(choice){
							location.replace('delete.do?board_num=${board.board_num}');
						}
					};
				</script>
				</c:if>
			</li>
		</ul>
		
		<!-- 댓글 시작 -->
			<hr size="3" color="white" width="120px" align="left">
			<span class="re-title" onclick="location.href='detail.do?co_key=${comm.co_key}'" style="cursor:pointer;">COMMENT</span>
			<form id="re_form">
				<input type="hidden" name="co_key" value="${comm.co_key}" id="co_key">
				
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





