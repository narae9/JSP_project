<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Calendar</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/calendar.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script src="${pageContext.request.contextPath}/js/calendar.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<h2 style="color:white; float:right;">전체 일정</h2>
<div class="calendar_main">
<span style="color:white; font-size:5px;">* 자세한 일정은 날짜 선택시 확인하실 수 있습니다.</span>
    <div class="wrap col-flex jcc aic">
        <div class="flex aic" style="gap: 40px">
            <button onclick="Calendar.addMonth(-1)">◀</button>
            <h1 class="cur-date" style="color:white;">2022. 7.</h1>
            <button onclick="Calendar.addMonth(1)">▶</button>
        </div>

        <div class="calendar">
            <div class="calendar-top">SUN</div>
            <div class="calendar-top">MON</div>
            <div class="calendar-top">TUE</div>
            <div class="calendar-top">WED</div>
            <div class="calendar-top">THR</div>
            <div class="calendar-top">FRI</div>
            <div class="calendar-top">SAT</div>
        </div>
    </div>
</div>

<!-- Model -->
    <div class="modal schedule">
        <div class="modal-bg">
            <div class="modal-form">
                <div class="modal-top flex aifs jcsb">
                <h1 class="modal-title"></h1>
                <button onclick="document.querySelector('.modal.schedule').classList.remove('show'),ScheduleManager.remove()">x</button>
                </div>

            <div class="modal-content">
                <div class="schedule-list">
                    <div class="flex aic jcc" style="width: 100%; height: 100%;">
                    
                    </div>
                </div>
	                <div class="form-inf">
	                   
	                </div>
	            </div>
	            </div>
	        </div>
	    </div>	
    <script>
        Calendar.$calendar = document.querySelector(".calendar");
        Calendar.$date = document.querySelector(".cur-date");
        Calendar.init();
    </script>
    <jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>