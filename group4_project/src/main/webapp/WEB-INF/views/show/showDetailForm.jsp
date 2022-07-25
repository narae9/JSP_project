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
<!-- <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
 -->
 </head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<h4>&nbsp;&nbsp;&nbsp;공연예매</h4>
	<div class="page-left">
		<ul>
			<li class="align-center">
				${show.sh_img }
				<br>
				<hr size="1" noshade="noshade" width="80%" color="white">
				${show.sh_date }${show.sh_time }
			</li>
		</ul>
		<ul>
			<li>
				<button type="button" class="btn btn-primary" onclick="location.href='#'">예매하기</button>
				<button type="button" class="btn btn-secondary" onclick="location.href='${pageContext.request.contextPath}/main/main.do'">홈으로</button>
			</li>
		</ul>
		
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