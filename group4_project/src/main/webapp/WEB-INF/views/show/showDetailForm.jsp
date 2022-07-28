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

<style type="text/css">
img{
width:300px;
height:200px;
}
.modify{
	float:right;
	margin: 0 5% 0 0 ;
	
}
</style>

</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<h4>&nbsp;&nbsp;&nbsp;공연예매</h4>
	<div class="page-left">
		<ul>
			<li class="align-center">
				<img src="${pageContext.request.contextPath}/upload/${show.sh_img}">
				<br><br><br>
				<hr size="5" noshade="noshade" width="80%" color="white">
				<h1 style="color:white;">${show.sh_date }[${show.sh_time }]</h1>
			</li>
			<li class="page-left">
			</li>
		</ul>
		
		<form style="border:none;">
			<ul>
				<li>
					<input type="text" name="re_spon" id="re_spon" placeholder="후원금액">
				</li>
				<li>
					<input type="submit" onclick="location.href='${pageContext.request.contextPath}/show/reserveShow.do'" value="예매하기">
					<button type="button"  onclick="location.href='${pageContext.request.contextPath}/show/showListAction.do'">목록으로</button>
				</li>
			</ul>
		</form>
		
		
	</div>
	
		<input type="button" value="수정하기" class="modify" onclick="location.href='${pageContext.request.contextPath}/show/showModifyForm.do?sh_key=${show.sh_key}'">
		<input type="button" value="삭제" class="delete" onclick="location.href='${pageContext.request.contextPath}/show/showDeleteFrom.do?sh_key=${show.sh_key}'">
	
	<div class="page-left">
		<ul>			
			<li>
			 	<h1>${show.sh_title }</h1>
			</li>
			<li>
				${show.sh_detail}  
				<br> 
			</li>
		</ul>
		<ul>
			
		</ul>
	</div>
	
	<div>
	
	</div>
</div>
</body>
</html>