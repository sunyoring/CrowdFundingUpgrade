<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="com.kh.lecture.model.vo.Lecture"%>
<%@ page import="com.kh.user.model.vo.User"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.kh.user.model.vo.ULecture"%>

<%
	Lecture lecture = (Lecture) request.getAttribute("lecture");
String count = (request.getAttribute("count")).toString();
User loginUser = (User) session.getAttribute("loginUser");
String isDuplicate = (String) request.getAttribute("isDuplicate");
ArrayList<ULecture> ulList = (ArrayList<ULecture>) session.getAttribute("ulList");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>lectureDetail</title>

<style>
#box {
	width: 1300px;
	overflow: hidden;
	height: auto;
	margin: 0 auto;
}

.topThumbnail {
	margin-top: 25px;
	text-align: center;
	justify-content: center;
	width: 100%;
	hieght: 450px;
}

.topThumbnail>img {
	padding: 10px 5px 10px 5px;
	margin: 10px 20px 10px 20px;
	height: 350px;
	width: 700px;
}

.mainContent {
	text-align: center;
	border: 0.5px solid rgba(0, 0, 0, .3);
	border-radius: 8px;
	margin: 10px 15px 10px 25px;
	overflow: hidden;
	height: auto;
	padding-top: 60px;
	padding-bottom: 80px;
	margin-bottom: 80px;
	margin-top: 100px;
}

.lecDe_Wrapper {
	text-align: center;
	margin: 50px 25px 50px 25px;
	padding: 15px 10px 15px 10px;
}

.lectureContent {
	font-size: 14px;
}

.delModal {
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	display: flex;
	justify-content: center;
	align-items: center;
}

.delModal_overlay {
	width: 100%;
	height: 100%;
	position: absolute;
}

.delModal_content {
	margin: 0 auto;
	background-color: white;
	padding: 10px 12px 10px 12px;
	align-items: center;
	text-align: center;
	position: relative;
	top: 0px;
	border-radius: 15px;
	box-shadow: 0 10px 16px rgba(0, 0, 0, .35), 0 8px 8px rgba(0, 0, 0, .60);
	width: 235px;
	border: 1px solid gray;
}

.hidden {
	display: none;
}

.buttonArea {
	display: block;
	right: 0;
	bottom: 0;
	position: fixed;
	border: 1px solid: gray;
	border-radius: 5px;
	box-shadow: 0 3px; 7 px; rgba (0,0,0,0.08), 0 4px 4px rgba(0,0,0,0.09);
	margin: 0px 10px 15px 0px;
}

.buttonArea>Button {
	background-color: rgba(211, 212, 221, 0.87);
	color: rgb(52, 58, 64);
	font-weight: bold;
	border-radius: 5px;
	border: 1px solid white;
}

.lecInfo {
	text-align: center;
	font-family: 'Noto Sans KR', 'sans-serif';
	font-size: 20px;
	color: rgba(0, 0, 0, .78);
	font-weight: bold;
	width: 500px;
	padding-left: 50px;
	margin: 0 auto;
}

.lecInfo>p>.smallLabel {
	display: inline-block;
	width: 100px;
}

#lectureContent {
	font-family: 'Noto Sans KR', 'sans-serif';
	font-size: 28px;
	margin-top: 50px;
	font-weight: bold;
	margin-bottom: 100px;
}

.smallLabel {
	display: block;
	font-size: 16px;
	color: rgba(0, 0, 0, .5);
	margin-bottom: 0;
	margin-top: 10px;
}
</style>

</head>
<body>

	<jsp:include page="/views/common/menubar.jsp" />

	<div id="box">

		<div class="topThumbnail">
			<img id="thumbnail"
				src="<%=request.getContextPath()%>/resources/lectureImage/<%=lecture.getLectureImage()%>">
		</div>
		<div class="mainContent" style="padding: 70px;">

			<div class="lecInfo">
				<p class="lectureCode" style="display: none;"><%=lecture.getLectureCode()%>
				</p>
				<p class="smallLabel">강사</p>
				<%=lecture.getLecturer()%><br>
				<p class="smallLabel">타이틀</p>
				<%=lecture.getLectureTitle()%><br>
				<p class="smallLabel">주제</p>
				<%=lecture.getLectureTopic()%><br>
				<p class="smallLabel">일시</p>
				<%=lecture.getLectureDate()%>
				<br>
				<p class="smallLabel">주소</p>
				<%=lecture.getLectureAddress()%>
				<span class="smallLabel""> 참여 인원</span>
				<p class="lectrueNumber">
					<%=count%>
					/
					<%=lecture.getLectureNum()%></p>
			</div>
			<br> <br>
			<div style="border-top: solid 4px rgba(0, 0, 0, .1);">
				<p id="lectureContent">
					<%=lecture.getLectureContent()%>
				</p>
			</div>

			<div class="buttonArea">

				<%
					if (!ulList.isEmpty()) { //디테일페이지 켜질 때 해당 프로젝트 pCode로 관심테이블에서 리스트 가져옴
					for (ULecture u : ulList) { // 리스트 반복문 돌리면서
						if (u.getUserNo() == loginUser.getUserNo()) { //로그인 유저 넘버랑 일치하는 거 있으면
				%>
				<button type="button" class="signInBtn" onclick="cancleLec()">
					수강취소</button>
				<%
					//관심 해제 버튼 노출
				break;

				} else {
				%>
				<button type="button" class="signInBtn" onclick="checkLogin()">
					수강등록</button>
				<%
					}
				break;

				}
				} else {
				%>
				<button type="button" class="signInBtn" onclick="checkLogin()">
					수강등록</button>
				<%
					}
				%>

				<button type="button" onclick="window.history.back()">이전으로</button>
				<button type="button" class="signInBtn" onclick="updateLecture()">수정하기</button>
				<button type="button" class="deleteBtn">강의삭제</button>
				<button class="toTheTop" onclick="location.href='#thumbnail'">
					<b>▲ </b>
				</button>
			</div>

		</div>


		<div class="delModal hidden" id="delModal">
			<div class="delModal_overlay">
				<div class="delModal_content">
					<p>정말로 해당 강의를 삭제합니까?</p>
					<button type="button" onclick="deleteLecture()">삭제하기</button>
					<button type="button" class="cancleBtn">취소</button>
				</div>
			</div>
		</div>

	</div>
	<script>
        <%--
     	widnow.onload = function(){
     			document.querySelector(".deleteBtn").classList.remove("hidden");
     	} 
     	--%> 
     
     
     	const deleteBtn = document.querySelector(".deleteBtn");
        const delModal = document.querySelector(".delModal");
        const cancleBtn = document.querySelector(".cancleBtn");
        
        const openDelBox = ()=> {
        	delModal.classList.remove("hidden");
        }
        const closeDelBox = () => {
        	delModal.classList.add("hidden");        	
        }
        
        deleteBtn.addEventListener("click",openDelBox);
        cancleBtn.addEventListener("click",closeDelBox);
        
        function checkLogin(){
        	
			<%if ((loginUser != null) && (loginUser.getUserCode() != "01")) {%>
			
			var code = document.querySelector(".lectureCode").textContent;
        	location.href = '<%=request.getContextPath()%>/signIn.le?code='+code;
        	
        	<%} else {%>
        	
        	alert("수강 신청을 위해선 로그인 하셔야합니다.");
        	location.href ='<%=request.getContextPath()%>/loginForm.me';
        	<%}%>
        	
        }
       	
        function deleteLecture(){
			var code = document.querySelector(".lectureCode").textContent;
			location.href="<%=request.getContextPath()%>/lectureDelete.le?code="+code;
	
        }
        
        function updateLecture(){
        	var code = document.querySelector(".lectureCode").textContent;
        	location.href="<%=request.getContextPath()%>/lecUpdateForm.le?code="+code;
        	
        }
        
        function cancleLec(){
        	var code = document.querySelector(".lectureCode").textContent;
        	location.href="<%=request.getContextPath()%>/lecCancle.le?code="+code;
        }
        
        
     
     
    </script>



	<jsp:include page="/views/common/footer.jsp" />




</body>
</html>