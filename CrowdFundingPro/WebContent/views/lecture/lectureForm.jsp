<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.kh.user.model.vo.User"%>
<%@ page import="com.kh.lecture.model.vo.Lecture"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.kh.common.model.vo.PageInfo"%>

<%
	String contextPath = request.getContextPath();
ArrayList<Lecture> lectureList = (ArrayList<Lecture>) request.getAttribute("lectureList");
PageInfo pi = (PageInfo) request.getAttribute("pi");

System.out.println(contextPath);

int listCount = pi.getListCount();
System.out.println("count:" + listCount);
int currentPage = pi.getCurrentPage();
System.out.println("current:" + currentPage);
int maxPage = pi.getMaxPage();
System.out.println("max:" + maxPage);
int startPage = pi.getStartPage();
System.out.println("start:" + startPage);
int endPage = pi.getEndPage();
System.out.println("end:" + endPage);
%>


<%
	//User loginUser = (User) request.getAttribute("loginUser");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>

<link href="resources/css/menubar.css" rel="stylesheet" type="text/css">
<link
	href="https://fonts.googleapis.com/css2?family=Black+Han+Sans&family=Jua&fa
            mily=Nanum+Gothic&family=Roboto&display=swap"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap"
	rel="stylesheet">
<style>
@charset "UTF-8";

.body {
	font-family: 'Noto Sans KR', 'sans-serif';
}

ul {
	list-style: none;
}

.main {
	width: 100%;
	overflow: hidden;
	height: auto;
}

.main_top {
	display: flex;
	justify-content: center;
	align-items: center;
	margin-top: 50px;
	margin-bottom: 80px;
	padding: 30px;
}

.carousel-item {
	height: 350px;
}

.carousel-item>img {
	width: 100%;
	height: 100%;
	object-fit: cover;
}

#pageTitle {
	font-family: 'Noto Sans KR', 'sans-serif';
	margin-top: 150px;
	margin-bottom: 100px;
	font-size: 40px;
	font-weight: bold;
}

#top_banner {
	width: 830px;
	height: 400px;
	position: relative;
	background-color: rgb(52, 58, 64);
	overflow: hidden;
}

.slider {
	display: flex;
	position: absolute;
	top: 0;
	left: 0;
}

.slider_item {
	margin: 15px 0px 0px 0px;
	height: 400px;
	text-align: center;
}

ul img {
	width: 600px;
	height: 400px;
}

#top_banner ul {
	-webkit-transition: all 0.25s cubic-bezier(1, .01, .32, 1);
	-o-transition: all 0.25s cubic-bezier(1, .01, .32, 1);
	transition: all 0.25s cubic-bezier(1, .01, .32, 1);
}

.center {
	width: 100%;
	/* display: inline-flex; */
	display: flex;
	margin-bottom: 100px;
}

.left {
	height: 1200px;
	width: 50%;
	float:
}

.right {
	height: 1200px;
	width: 50%;
}

.lecWrapper {
	float: right;
	padding-top: 20px;
	padding-bottom: 20px;
}

.lec_Item {
	display: block;
	border: 2px solid rgb(25, 25, 25, .44);
	width: 650px;
	margin-bottom: 20px;
	border-radius: 10px;
	overflow: hidden;
	height: auto;
}

#lImg {
	display: block;
	float: left;
	width: 220px;
	height: 200px;
	backgound: black;

}

.details {
	float: left;
	background-color: white;
	width: 420px;
	text-align: center;
	overflow: hidden;
	height: 200px;
	padding-top: 10px;
	padding-right: 10px;
}

.details pre {
	font-size: 20px;
	font-family: 'Nanum Brush Script', cursive;
}

.lecturePage {
	margin: 15px 10px 15px 10px;
	width: 100%;
}

.lecturePage>button {
	background-color: rgb(255, 255, 255);
	color: rgb(0, 123, 255);
	font-size: 18px;
	font-weight: bold;
	font-family: 'inherit';
	border: 1px solid white;
}

.pageButton {
	margin: 10px 15px 10px 15px;
}

.lecturePage>button:hover {
	background-color: rgb(0, 123, 255);
	color: white;
	cursor: pointer;
}

.pageButton:active {
	background-color: rgb(0, 79, 154);
	color: rgb(190, 190, 190);
}

.area {
	background-color: rgb(240, 215, 210);
	width: 300px;
	height: 550px;
	margin-top: 50px;
	margin-left: 40px;
	border-radius: 10px;
}

#regist {
	background-color: paleturquoise;
	right: 0%;
	bottom: 0%;
	font-family: 'Nanum Brush Script', cursive;
	align-self: flex-end;
}

#inserLecture {
	border-radius: 5px;
	background-color: gray;
	color: white;
}

input {
	margin-left: 25px;
}

.registBox {
	position: relative;
	right: 0;
	bottom: 0;
	magin: 0px 15px 10px 0px;
}

.title {
	font-size: 23px;
	font-weight: bold;
}

.topic {
	font-weight: bold;
	text-align: right;
	color: #00B2B2;;
	font-size: 14px;
	font-family: 'Noto Sans KR', 'sans-serif';
	margin-right:30px;
}

.lec_Item:hover .title {
	text-decoration: underline;
}

.lec_Item:hover {
	cursor: pointer;
}

#lInfo {
	font-weight: bold;
	text-align: center;
	color: black;
	font-size: 18px;
	font-family: 'Noto Sans KR', 'sans-serif';
}

#lInfo li {
	list-style-type: none;
}

.smallLabel {
	font-size: 15px;
    color: rgba(0, 0, 0, .78);
}

#insertLectureBtn {
	width: 160px;
	background: none;
	border: none;
	font-size: 20px;
	text-decoration: underline;
	text-underline-position: under;
	font-style: italic;
	font-weight: bold;
	color: rgb(0, 0, 0, .7);
	margin-top: 30px;
	margin-bottom: 30px;
	margin-left: 550px;
}

#insertLectureBtn:hover {
	text-decoration: underline;
	font-style: normal;
}
</style>

<link href="resources/css/menubar.css" rel="stylesheet" type="text/css">

</head>
<body>

	<%--
	<jsp:include page="/views/common/menubar.jsp" />
 --%>
	<%@ include file="../common/menubar.jsp"%>

	<!-- 화면 중앙 구현 부-->
	<div class="main">

		<!-- carousel -->
		<div id="intro" class="carousel slide" data-ride="carousel">
			<!-- indicators -->
			<ul class="carousel-indicators">
				<li data-target="#intro" data-slide-to="0" class="active"></li>
				<li data-target="#intro" data-slide-to="1"></li>
			</ul>

			<!-- slideshow -->


			<div class="carousel-inner">
				<div class="carousel-item active">
					<img src="resources/images/lectureBannerImg1.jpg" alt="img1">
				</div>
				<div class="carousel-item">
					<img src="resources/images/lectureBannerImg2.jpg" alt="img2">
				</div>

			</div>



			<!-- left and right controls -->
			<a href="#intro" class="carousel-control-prev" data-slide="prev">
				<span class="carousel-control-prev-icon"></span>
			</a> <a href="#intro" class="carousel-control-next" data-slide="next">
				<span class="carousel-control-next-icon"></span>
			</a>
		</div>







		<!-- 화면 상단 이미지 슬라이드-->
		<h2 id="pageTitle"
			style="margin-top: 25px; text-align: center; text-shadow: 5px; 1px; 2px 4px rgb(184,184,184);">
			펀딩스쿨에 오신 것을 환영합니다!</h2>






		<%-- 
				<div class="main_top">
		
			<div id="top_banner">
				<p style="color: white;">&lt;</p>
				<ul class="slider">
					<li class="slider_item"><img
						src="resources/lectureImage/lectureImage_4.png"></li>
					<li class="slider_item"><img
						src="resources/lectureImage/lectureImage_8.png"></li>
					<li class="slider_item"><img
						src="resources/lectureImage/lectureImage_9.png"></li>
				</ul>
				<p>&gt;</p>
			</div>
					</div>
				<script src="resources/script/lecture/lectureSlider.js"></script>
		<!-- 이미지 슬라이드 끝-->
			--%>



		<div class="center">
			<div class="">

				<div class="lecWrapper">


					<%
						if (loginUser == null || loginUser.getUserCode().equals("02") || loginUser.getUserCode().equals("03")) {
					%>
					<h4 style="font-weight: bold; color: rgba(0, 0, 0, 0.87); margin-bottom:25px;">이런
						강의 들이 있어요!</h4>
					<%
						} else {
					%>

					<button id="insertLectureBtn" type="button"
						onclick="location.href=' <%=request.getContextPath()%>/lecInsertForm.le'">
						강의 등록</button>
					<%
						}
					%>



					<%
						if (lectureList != null && !(lectureList.isEmpty())) {
					%>
					<%
						for (Lecture l : lectureList) {
					%>
					<div class="lec_Item">
						<p style="display: none"><%=l.getLectureCode()%></p>
						<div id="lImg"s
							style="background:url('<%=request.getContextPath()%>/resources/lectureImage/<%=l.getLectureImage()%>');">
							
						</div>


						<div class="details">
							<div id="lTitle" style="margin-bottom: 15px;">
								<span class="title"><%=l.getLectureTitle()%></span>
								<li class="topic" style="list-style: none;"><%=l.getLectureTopic()%></li>

							</div>
							<div id="lInfo">

								<li class="date"><span class="smallLabel"> 강의 날짜 > </span><%=l.getLectureDate()%></li>
								<li class="human"><span class="smallLabel"> 강사 > </span> <%=l.getLecturer()%></li>
								<li class="time" "><span class="smallLabel"> 총 강의 시간 > </span> <%=l.getLectureTime()%>분 </li>
								<li class="number""><span class="smallLabel"> 수용 인원 > </span> <%=l.getLectureNum()%>명</li>
							</div>


						</div>
					</div>
					<%
						}
					} else {
					%>
					<div class="lec_Item">
						<img src="resources/images/NoImage.png">
						<div class="details">
							<div class="content">
								<table>
									<tbody>
										<tr>
											<td>등록된 강의 정보가 없습니다.</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>
					<%
						}
					%>



					<div class="lecturePage" align="center">

						<button class="pageButton"
							onclick="location.href='<%=request.getContextPath()%>/list.le?currentPage=1'">
							&lt;&lt;</button>
						<%
							if (currentPage == 1) {
						%>
						<button class="pageButton" disabled>&lt;</button>
						<%
							} else {
						%>
						<button
							onclick="location.href='<%=request.getContextPath()%>/list.le?currentPage=<%=currentPage - 1%>'">
							&lt;</button>
						<%
							}
						%>

						<!-- 페이지 목록 -->
						<%
							for (int i = 1; i <= endPage; i++) {
						%>

						<%
							if (i == currentPage) {
						%>
						<button class="pageButton" disabled>
							<%=i%>
						</button>
						<%
							} else {
						%>
						<button class="pageButton"
							onclick="location.href='<%=request.getContextPath()%>/lecture.le?currentPage=<%=i%>'">
							<%=i%>
						</button>
						<%
							}
						%>
						<%
							}
						%>
						<%
							if (currentPage == maxPage) {
						%>
						<button class="pageButton" disabled>&gt;</button>
						<%
							} else {
						%>
						<button class="pageButton"
							onclick="location.href='<%=request.getContextPath()%>/lecture..le?currentPage=<%=currentPage + 1%>'">
							&gt;</button>
						<%
							}
						%>
						<%
							if (currentPage == maxPage) {
						%>
						<button class="pageButton" disabled>&gt;&gt;</button>
						<%
							} else {
						%>
						<button class="pageButton"
							onclick="location.href='<%=request.getContextPath()%>/lecture..le?currentPage=<%=maxPage%>'">
							&gt;&gt;</button>
						<%
							}
						%>
					</div>
				</div>



			</div>




			<script>
					$(function(){
						$(".lec_Item").click(function(){
							var code = $(this).children().eq(0).text();
							location.href="<%=request.getContextPath()%>/lectureDetail.le?code="+code;
							console.log(code);
						})
					})
				
			<%--
			const moveToDetail () => {
				
				var lecCode = 
					
				window.location.href = " <%=request.getContextPath()%>/leDetail.le?lId=";
				
				<%  if(!list.isEmpty()){ %>
				$(function(){
					$(".listArea>tbody>tr").click(function(){
						var nno = $(this).children().eq(0).text();
						
						
						location.href="<%=request.getContextPath()%>/detail.no?nno="+nno;
					})
				})
				
				<% } %> 
			}  --%>
			
			
				
			<%-- 
				window.addEvenetListener('scroll',() = > {
					
					const {scrollheight,scrollTop,clientheight} = document.documentElement;
					if(scrollTop + clientHeight >= scrollHeight -5) {
						
						<% if ( count <= lectureList.size()){
							l = lectureList.get(count++); %>
							setTimeout(addLec,1500);
						<% } %>
					  }
					}
					
				})
				
				function addLec(){
					
					const lec = document.createElement('div');
					lec.className = 'lec_Item';
					lec.innerHTML = '
						<img src="<%l.getLectureImage();%>">
					<div class="details">
						<div class="content">
							<table>
								<tr>
									<td> 주제 :<%=l.getLectureTopic()%> </td>
								</tr>
								<tr>
									<td> 제목 :<%=l.getLectureTitle()%></td>
								</tr>
								<tr>
									<td> 날짜 :<%=l.getLectureDate()%> </td>
								</tr>
								<tr>
									<td> 강사 :<%=l.getLecturer()%>
									</td>
								</tr>
								<tr>
									<td> 강의 시간: <%=l.getLectureTime()%><p>/</p></td>
									<td> 강의 인원:<%=l.getLectureNum()%>
								</tr>
							</table>
						</div>
					</div>
					';
					
					
					document.querySelector(".lecWrapper").appendChild(lec);
					
				}
	 		--%>		
			</script>
			<%-- 
			<div class="right">
				<div class="area">
					<div class="lecture_Rank">
					</div>
					
				
				
				
				</div>
				<div class="registBox">
					<ul>
						<b style="font-weight:bold; font-size:12px; text-shadow: -1px 0 black, 0 1px black, 1px 0 black, 0 -1px black; color:rgb(211,211,211);">+</b></button></li>
					</ul>
				</div>
			</div>
--%>

		</div>



	</div>



	<jsp:include page="/views/common/footer.jsp" />






</body>
</html>