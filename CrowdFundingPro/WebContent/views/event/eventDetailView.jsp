<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="com.kh.event.model.vo.Event"%>

<%
	Event e = (Event) request.getAttribute("event");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이벤트 상세보기</title>

<link
	href="https://fonts.googleapis.com/css2?family=Black+Han+Sans&family=Jua&fa
            mily=Nanum+Gothic&family=Roboto&display=swap"
	rel="stylesheet" />


</head>
<style>
.box {
	width: 100%;
	margin: 0 auto;
}


.eventDetailForm{
padding-bottom : 200px;
}

#pageTitle {
	font-size: 48px;
	text-align: center;
	font-family: 'Roboto', sans-serif;
	font-weight: bold !inmportant;
}

.pageHead {
	width: 100%;
	height: 250px;
	padding-top: 100px;
	margin-bottom: 50px;
	position: relative;
	z-index: 1;
}
.pageHead::after {
	width: 100%;
	height: 250px;
	content: "";
	background:
		url('<%=request.getContextPath()%>/resources/images/eventBannerImg.jpg');
	opacity: 0.5;
	position: absolute;
	top: 0;
	left: 0;
	z-index: -1;
}
.eventDetailForm {
	justify-content: center;
	align-items: center;
	width: 1300px;
	margin: 0 auto;
}


.eName{

	margin-top : 100px;
	margin-bottom: 100px;
	text-decoration : underline;
	text-underline-position: under;
	text-align:center;

}


#deleteEventBtn {
	text-align: right;
	margin-bottom: 60px;
}

#deleteEventBtn input {
	width: 160px;
	background: none;
	border: none;
	font-size: 20px;
	text-decoration: underline;
	text-underline-position: under;
	font-style: italic;
	font-weight: bold;
	color: rgb(0, 0, 0, .7);
}

#deleteEventBtn input:hover {
	text-decoration: underline;
	font-style: normal;
}

.eContent{
text-align:center;
}




</style>

<body>


	<%@ include file="../common/menubar.jsp"%>



	<div class='box'>

		<div class="pageHead">
			<h1 id="pageTitle">EVENT</h1>
		</div>

		

		<div class="eventDetailForm">
		
		
		<%
			if (loginUser != null)
			if (loginUser.getUserCode().equals("01")) {
		%>
		<div id="deleteEventBtn">
			<form method='post' action="<%=request.getContextPath()%>/eDelete.do" >
				<input type="hidden" name="eno" value="<%= e.geteNo() %>">
				<input type="submit" value="이벤트 삭제">
			</form>

		</div>
		<%
			}
		%>
		

		<div class="eName">
			<h3 style="font-weight:bold;"><%=e.geteName() %></h3>
		</div>
		
		<div class="eContent">
		<img alt="이미지로딩실패" src= "<%=request.getContextPath()%>/resources/upfiles/<%=e.geteContent()%>">
		</div>

		</div>
	</div>
	
	
	<%@ include file="../common/footer.jsp"%>

</html>