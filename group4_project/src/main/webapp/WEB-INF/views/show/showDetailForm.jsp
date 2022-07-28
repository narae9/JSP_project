<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/show.css" type="text/css"/>

<style type="text/css">
img{
width:300px;
height:200px;
}
.modify{
	float:right;
	margin: 0 5% 0 0 ;
	
}
#mdifyDelete_btn{
	float:right;
	margin:0 5% 0 0
}
input[type=text] {
	border-radius:5px;
	width:300px;
	border:0;
	height:30px;
	margin: 0 0 10px 0;
}
.btnBlack {
	background-color: #333;
	color: #fff;
	border-radius:5px;
	border-color: #333;
	width:100px; 
	height:30px;
}
.btnBlack:hover {
	background-color:#646566;
	color:#fff;
	border-color:#646566;
	width:100px; 
	height:30px;
}

.btnBlue{
	background-color: #2b8eff;
	color: #fff;
	border-radius:5px;
	border-color:#2b8eff;
	width:100px; 
	height:30px;
}
.btnBlue:hover {
	background-color:#78b7ff;
	color:#fff;
	border-color:#78b7ff;
	width:100px; 
	height:30px;
}
h2{
	color:white;
}


</style>

</head>
<body class="showBody">
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<h2>&nbsp;&nbsp;&nbsp;공연예매</h2>
	<div>
	<div class="page-left">
		<ul>
			<li class="align-center">
				<img src="${pageContext.request.contextPath}/upload/${show.sh_img}">
				<br><br><br>
				<hr size="5" noshade="noshade" width="70%" color="white">
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
					<input class="btnBlue" type="submit" onclick="location.href='${pageContext.request.contextPath}/show/reserveShow.do'" value="예매하기">
					<button class="btnBlack" type="button"  onclick="location.href='${pageContext.request.contextPath}/show/showListAction.do'">목록으로</button>
				</li>
			</ul>
		</form>
		
		
	</div>
	<div id="mdifyDelete_btn">
		<input class="btnBlack" type="button" value="수정하기" onclick="location.href='${pageContext.request.contextPath}/show/showModifyForm.do?sh_key=${show.sh_key}'">
		<input class="btnBlack" type="button" value="삭제"  onclick="location.href='${pageContext.request.contextPath}/show/showDeleteFrom.do?sh_key=${show.sh_key}'">	
	</div>
	<br>
	<div class="page-right">
		<ul>			
			<li>
			 	<h1>${show.sh_title }</h1>
			</li>
			<li style="color:white;">
				${show.sh_detail}  
				<br> 
			</li>
		</ul>
		<ul>
			
		</ul>
	</div>
	
	</div>
<div>
		<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</div>
</div>
</body>
</html>