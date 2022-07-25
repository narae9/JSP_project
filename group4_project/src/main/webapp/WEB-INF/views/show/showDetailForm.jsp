<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>공연 상세보기</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<h4>&nbsp;&nbsp;&nbsp;공연예매</h4>
	<div class="page-left">
		<ul>
			<li>
				${show.img }
			</li>
		</ul>
		<ul>
			<li>
				<button type="button" class="btn btn-primary" onclick="location.href='#'">예매하기</button>
				<button type="button" class="btn btn-secondary" onclick="location.href='${pageContext.request.contextPath}/main/main.do'">홈으로</button>
			</li>
		</ul>
		
	</div>
	
	<div class="page-right">
		
	</div>
</div>

</body>
</html>