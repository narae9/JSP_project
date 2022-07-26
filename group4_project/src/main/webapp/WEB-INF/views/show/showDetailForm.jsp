<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
 </head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<h4>&nbsp;&nbsp;&nbsp;공연예매</h4>
	<div class="page-left">
		<ul>
			<li class="align-center">
				<img src="${pageContext.request.contextPath}/upload/${show.sh_img }">
				<br>
				<hr size="1" noshade="noshade" width="80%" color="white">
				${show.sh_date }${show.sh_time }
			</li>
			<li class="page-left">
			</li>
		</ul>
		<form style="border:none;" class="page-left">
			<ul>
				<li>
					<input type="text" name="re_spon" id="re_spon" placeholder="후원금액">
				</li>
				<li>
					<input type="submit" onclick="location.href='/show/reserveShow.do'" value="예매하기">
					<button type="button"  onclick="location.href='${pageContext.request.contextPath}/show/showListAction.do'">목록으로</button>
				</li>
			</ul>
		</form>
		
	</div>
	
	<div class="page-left">
		<ul>
			<li>
			 	<h2>${show.sh_title }</h2>
			</li>
			<li>
				${show.sh_detail}   
			</li>
		</ul>
		<ul>
			
		</ul>
	</div>
	
	<div>
	
	</div>
</div>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>
</body>
</html>