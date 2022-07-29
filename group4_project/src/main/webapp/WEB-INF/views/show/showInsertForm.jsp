<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공연 등록하기</title>
<style type="text/css">
input[type=text] {
	border-radius:5px;
	width:300px;
	border:0;
	height:30px;
	margin: 0 0 10px 0;
}
textarea, input[type=date], input[type=time], select{
	border-radius: 5px;
}

.btnBlack{
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
li{
	margin:10px auto;
}

</style>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/show.css"/>

</head>
<body class="showBody">
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<h2>&nbsp;&nbsp;&nbsp;공연 등록하기</h2>
	<div class="content-main" style="color:white;margin:0 auto;" >
	<form action="showInsert.do" method="post" id="showInsertForm"
	   enctype="multipart/form-data" style="border:1px solid white;">
		<ul>
			<li>
				<label for="sh_title">제목</label>
				<input type="text" id="sh_title" name="sh_title">
			</li>
			<li>
				<label for="sh_date">공연날짜</label>
				<input type="date" id="sh_date" name="sh_date">			
			</li>
			<li>
				<label for="sh_time">공연시간</label>
				<input type="time" id="sh_time" name="sh_time">
			</li>
			<li>
				<label for="sh_place">공연장소</label>
				<select name="sh_place" form="showInsertForm">
					<option value="서울">서울</option>
					<option value="경기도">경기도</option>
					<option value="부산">부산</option>
					<option value="강원도">강원도</option>
					<option value="충남">충남</option>
					<option value="경북">경북</option>
					<option value="전북">전북</option>
					<option value="전남">전남</option>
					<option value="경남">경남</option>
					<option value="제주도">제주도</option>			
					<option value="인천">인천</option>	
				</select>
			</li>
			<li>
				<label for="sh_detail">공연설명</label>
				<textarea rows="5" cols="30" name="sh_detail" id="sh_detail"></textarea>
			</li>
			<li>
				<label for="sh_img">대표이미지</label>
				<input type="file" id="sh_img" name="sh_img" accept="image/gif,image/png,image/jpeg">
			</li>
		</ul>
		<div class="align-center">
			<input class="btnBlue" type="submit" value="등록" id="InsertSubmit_btn">
			<input class="btnBlack"type="button" value="홈으로" onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
		</div>								
	</form>   
	</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>

</div>

</body>
</html>