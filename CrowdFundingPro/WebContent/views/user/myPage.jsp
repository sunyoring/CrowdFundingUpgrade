<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="com.kh.user.model.vo.ULecture"%>
<%@ page import="com.kh.user.model.vo.UProject"%>
<%@ page import="com.kh.user.model.vo.IProject"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.text.DecimalFormat"%>


<%
ArrayList<ULecture> lList = (ArrayList<ULecture>) request.getAttribute("ULectureList");
ArrayList<UProject> UPList = (ArrayList<UProject>) request.getAttribute("UProjectList");
ArrayList<IProject> IPList = (ArrayList<IProject>) request.getAttribute("InProjectPList");
DecimalFormat decFormat = new DecimalFormat("###,###");

%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>마이페이지</title>

<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap"
	rel="stylesheet">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">

<style>
.body {
	font-family: 'Noto Sans KR', 'sans-serif';
}

.box {
	width: 1300px;
	overflow:hidden;
	height: auto;
	margin: 0 auto;
}

.content1 {
	width: 260px;
	float: left;
	margin: 0 auto;
}

.profile {
	width: 100%;
	border: none;
	border-right: lightgray solid 0.1px;
}

.profile div {
	float: left;
	width: 100%;
	padding: 40px 20px 20px 10px;
	text-align: center;
}

.content2 {
	width: 1040px;
	float: left;
}

.section {
	width: 50%;
	float: left;
	padding-left: 30px;
	padding-right: 30px;
	
}

.section h3 {
	font-size: 1.4em;
	color: rgba(0, 0, 0, .8);
	margin-bottom: 20px;
	font-weight: bold;
}

.section li {
	display: block;
	width: 80%;
	height: 50px;
	border: 1px solid rosybrown;
	margin-bottom: 10px;
}

.section li a {
	text-decoration: none;
}

h4{
	font-family: 'Noto Sans KR', 'sans-serif';
	font-weight:bold;
}

#myPageTitle {
	width: 100%;
	height: 250px;
	padding-bottom: 40px;
	padding-top: 100px;
	margin-bottom: 20px;
	position: relative;
	z-index: 1;
}

#myPageTitle::after {
	width: 100%;
	height: 250px;
	content: "";
	background:
		url('<%=request.getContextPath()%>/resources/images/myPageBannerImg.jpg');
	opacity: .7;
	position: absolute;
	top: 0;
	left: 0;
	z-index: -1;
}

#myPageTitle h1 {
	margin-left: 20%;
	font-size: 42px;
	font-family: 'Noto Sans KR', 'sans-serif';
	font-weight: bold;
}

.usericon {
	font-size: 150px;
	opacity: 0.1;
}

.modify .profileBtn {
	width: 100%;
	height: 40px;
	border-radius: 30px;
	border: 2px solid rgb(100, 220, 167, .5);
	margin: 10px;
	font-size: 1.3em;
	color: gray;
	background: white;
}

.modify .profileBtn:hover {
	cursor: pointer;
	background: rgb(100, 220, 177, .8);
	color: white;
}

.card {
	width: 100%;
	margin-bottom: 10px;
	padding: 5px;
}

.card-img-top {
	width: 100%;
}

.card-body {
	height: 120px;
	padding: 5px !important;
}

.card-body h2 {
	font-size: 1rem;
	font-family: 'Noto Sans KR', 'sans-serif';
	font-weight: 600;
}

.card-title {
	margin-bottom: 5px;
	margin-top: 5px;
}

.card:hover {
	box-shadow: 0 10px 20px rgba(0, 0, 0, 0.2), 0 0 1px rgba(0, 0, 0, 0.2);
	cursor: pointer;
}

#point {
	font-size: 18px;
	font-family: 'Noto Sans KR', 'sans-serif';
}

#newEventBtn {
	text-align: right;
	margin-bottom: 60px;
}

.moreBtn {
	width: 120px;
	background: none;
	border: none;
	font-size: 17px;
	text-decoration: underline;
	text-underline-position: under;
	font-style: italic;
	font-weight: bold;
	color: rgb(0, 0, 0, .4);
}

.moreBtn:hover {
	text-decoration: underline;
	font-style: normal;
}

.point2{
	position:relative;
    display: block;
    outline: none;
    padding: 16px 0px;
    width: 400px;
    height: 56px;
    text-align: left;
    font-size:18px;
    font-weight:bold;
    color: rgba(0,0,0,.6);
    font-family: 'Noto Sans KR', 'sans-serif';
}

#point3{
position:absolute;
right:10px;
}


.chargeLabel{
    font-family: 'Noto Sans KR', 'sans-serif';
width:100%;
font-size:22px;
font-weight:bold;
border-bottom: 0.5px solid lightgray;
padding:5px;
margin-bottom:10px;
}

.chargeInput{
	padding:5px;
    margin-bottom:10px;
	font-family: 'Noto Sans KR', 'sans-serif';
	
}

#rechargeBtn{
width:100px;
font-family: 'Noto Sans KR', 'sans-serif';
font-size:18px;

}

</style>

</head>
<body>
	<%@ include file="../common/menubar.jsp"%>
	<div id="myPageTitle">

		<%
			if (loginUser.getUserCode().equals("01")) {
		%>
		<h1>관리자 페이지</h1>
		<%
			} else {
		%>
		<h1>마이 페이지</h1>

		<%
			}
		%>
	</div>

	<div class="box">
		<div class="content1">
			<div class="profile">
				<div class="profileImg" style="margin: 0 auto;">
					<img id="pImg" src="" width=200px; height=200px; /> <br>
					<p id="point" style="font-size: 20px;">
						<i class="fas fa-user"></i> <b> <%=loginUser.getUserName()%></b>님
						><br> <span
							style="font-size: 13px; color: gray; font-weight: 700;"> <%
						 	if (loginUser.getUserCode().equals("02")) {
						 %>일반회원 <%
						 	} else {
						 %> 사업자회원 <%
						 	}
 						%>
						</span><br>
					<i class="far fa-wallet"></i>						 
					<b style="font-size: 20px;"> <%= decFormat.format(loginUser.getPoint())%>원</b>
					</p>

				</div>
				<div class="modify" style="margin: 0 auto;">

					<input class="profileBtn" type='button' value='정보수정'
						onclick="checkPwd();"></input> <br> <input class="profileBtn"
						type='button' value='로그아웃' onclick="logout();"></input>
				</div>
			</div>
		</div>

		<div class="content2" style="height:auto;">
		
		
			<div class="section" id="middleInfo"
				style="padding-left: 30px; margin-bottom:40px;  height: auto; width: 100%; display: block; float: left; overflow: hidden; border-bottom:0.5px solid lightgray; ">
				<a type="button" class="point2" style="display:inline-block; margin-right:120px;" data-toggle="modal" data-target="#pointCharge">
				<span class="far fa-usd-circle"> </span>
				<span> 포인트 충전하기</span><span id="point3">></span>
				</a>
				<a type="button" class="point2" href="<%=request.getContextPath()%>/list.fq" style="display:inline;">
				<span class="fa fa-question-circle"> </span>
				
				<span> 문의하러 가기</span><span id="point3">></span>
				</a>
			</div>
		
		
		
		
		
		
		
		
			<div class="section project ">
				<h3>내가 참여한 프로젝트</h3>

				<%
					if (!UPList.isEmpty()) {
					if (UPList.size() < 3) { // 참여 프로젝트가 3개 이하이면 
						for (int i = 0; i < UPList.size(); i++) {
				%>
				<div class="card pro">
					<div class="card-img-top"></div>
					<div class="card-body" id="cBody">
						<input type="hidden" value="<%=UPList.get(i).getProjectCode()%>">
						<h2 class="card-title h5"><%=UPList.get(i).getProjectName()%></h2>
						<div class="small text-muted"><%=UPList.get(i).getProDetail()%></div>
					</div>
				</div>
				<%
					}
				} else { //참여 프로젝트가 3개 이상이면
				for (int i = 0; i < 3; i++) { //3개 까지 노출시키고 더 보기 버튼 보이기
				%>

				<div class="card pro">
					<div class="card-img-top"></div>
					<div class="card-body" id="cBody">
						<input type="hidden" value="<%=UPList.get(i).getProjectCode()%>">
						<h2 class="card-title h5"><%=UPList.get(i).getProjectName()%></h2>
						<div class="small text-muted"><%=UPList.get(i).getProDetail()%></div>
					</div>
				</div>

				<%
					}
				%>
				<div id="moreUPro">
					<input class="moreBtn" type="button" value="더 보기"
						data-toggle="modal" data-target="#moreUP">
				</div>
				<%
					}
				} else {
				%>
				<div class="card noPro">
					<div class="card-img-top"></div>
					<div class="card-body" id="cBody">
						<h2 class="card-title h5">
							<a href="<%=request.getContextPath()%>/projectPage.do">프로젝트
								구경가기</a>
						</h2>
						<div class="small text-muted">
							<p>아직 참여중인 프로젝트가 없으시네요!</p>
						</div>
					</div>
				</div>
				<%
					}
				%>
			</div>

			<div id="projectSection" style="height:auto;">
			<div class="section project ">
				<h3>내가 관심있는 프로젝트</h3>

				<%
					if (!IPList.isEmpty()) {
					if (IPList.size() < 4) { // 참여 프로젝트가 3개 이하이면 
						for (int i = 0; i < IPList.size(); i++) {
				%>
				<div class="card pro">
					<div class="card-img-top"></div>
					<div class="card-body" id="cBody">
						<input type="hidden" value="<%=IPList.get(i).getProjectCode()%>">
						<h2 class="card-title h5"><%=IPList.get(i).getProjectName()%></h2>
						<div class="small text-muted"><%=IPList.get(i).getProDetail()%></div>
					</div>
				</div>
				<%
					}
				} else { //관심 프로젝트가 3개 이상이면
				for (int i = 0; i < 3; i++) { //3개 까지 노출시키고 더 보기 버튼 보이기
				%>

				<div class="card pro">
					<div class="card-img-top"></div>
					<div class="card-body" id="cBody">
						<input type="hidden" value="<%=IPList.get(i).getProjectCode()%>">
						<h2 class="card-title h5"><%=IPList.get(i).getProjectName()%></h2>
						<div class="small text-muted"><%=IPList.get(i).getProDetail()%></div>
					</div>
				</div>

				<%
					}
				%>
				<div id="moreIPro">
					<input class="moreBtn" type="button" value="더 보기"
						data-toggle="modal" data-target="#moreIP">
				</div>
				<%
					}
				} else {
				%>
				<div class="card noPro">
					<div class="card-img-top"></div>
					<div class="card-body" id="cBody">
						<h2 class="card-title h5">
							<a href="<%=request.getContextPath()%>/projectPage.do">프로젝트
								구경가기</a>
						</h2>
						<div class="small text-muted">
							<p>아직 관심있는 프로젝트가 없으시네요!</p>
						</div>
					</div>
				</div>
				<%
					}
				%>
			</div>

		</div>


			<div class="section lecture" style="width: 100%; margin-top:30px;">
				<h3>내가 듣는 강의</h3>
				<%
					if (!lList.isEmpty()) {
					if (lList.size() < 4) {
						for (ULecture u : lList) {
				%>
				<div class="card lec">
					<div class="card-img-top"></div>
					<div class="card-body">
						<input type="hidden" value="<%=u.getLCode()%>">
						<h2 class="card-title h5"><%=u.getLTitle()%></h2>
						<div class="small text-muted"><%=u.getLTopic()%>
							<div class="small text-muted"><%=u.getLDate()%>
							</div>

						</div>
					</div>
				</div>
				<%
					}

				} else {
				for (int i = 0; i < 4; i++) {
				%>
				<div class="card lec">
					<div class="card-img-top"></div>
					<div class="card-body lec">
						<input type="hidden" value="<%=lList.get(i).getLCode()%>">
						<h2 class="card-title h5"><%=lList.get(i).getLTitle()%></h2>
						<div class="small text-muted"><%=lList.get(i).getLTopic()%>
							<div class="small text-muted"><%=lList.get(i).getLDate()%></div>

						</div>
					</div>
				</div>
				<%
					}
				%>
				<div id="moreLecture">
					<input class="moreBtn" type="button" value="더 보기"
						data-toggle="modal" data-target="#moreLec">
				</div>
				<%
					}

				} else {
				%>

				<div class="card nolec">
					<div class="card-img-top"></div>
					<div class="card-body" id="cBody">
						<h2 class="card-title h5">
							<a href="<%= request.getContextPath() %>/lecture.le">펀딩스쿨
								구경가기</a>
						</h2>
						<div class="small text-muted">
							<p>아직 수강중인 강의가 없으시네요!</p>
						</div>
					</div>
				</div>


				<%
					}
				%>
			</div>
		</div>
	</div>
	<div class="footer">
		<%@ include file="../common/footer.jsp"%>

	</div>


	<div class="modal fade" id="moreUP">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">
				<!-- Modal header -->
				<div class="modal-header">
					<h4 class="modal-title">참여중인 프로젝트</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				<!-- Modal body -->
				<div class="modal-body">

					<%
						for (UProject up : UPList) {
					%>
					<div class="card pro">
						<div class="card-img-top"></div>
						<div class="card-body">
							<input type="hidden" value="<%=up.getProjectCode()%>">
							<h2 class="card-title h5"><%=up.getProjectName()%></h2>
							<div class="small text-muted"><%=up.getProDetail()%></div>
						</div>
					</div>
					<%
						}
					%>

				</div>

			</div>
		</div>
	</div>



	<div class="modal fade" id="moreIP">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">
				<!-- Modal header -->
				<div class="modal-header">
					<h4 class="modal-title">관심있는 프로젝트</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				<!-- Modal body -->
				<div class="modal-body">

					<%
						for (IProject ip : IPList) {
					%>
					<div class="card pro">
						<div class="card-img-top"></div>
						<div class="card-body">
							<input type="hidden" value="<%=ip.getProjectCode()%>">
							<h2 class="card-title h5"><%=ip.getProjectName()%></h2>
							<div class="small text-muted"><%=ip.getProDetail()%></div>
						</div>
					</div>
					<%
						}
					%>

				</div>

			</div>
		</div>
	</div>








	<!-- 강의 모달 -->
	<div class="modal fade" id="moreLec">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">
				<!-- Modal header -->
				<div class="modal-header">
					<h4 class="modal-title">수강신청한 강의</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				<!-- Modal body -->
				<div class="modal-body">
					<%
						for (ULecture u : lList) {
					%>
					<div class="card lec">
						<div class="card-img-top"></div>
						<div class="card-body">
							<input type="hidden" value="<%=u.getLCode()%>">
							<h2 class="card-title h5"><%=u.getLTitle()%></h2>
							<div class="small text-muted"><%=u.getLTopic()%>
								<div class="small text-muted"><%=u.getLDate()%></div>

							</div>
						</div>
					</div>
					<%
						}
					%>

				</div>
			</div>
		</div>
	</div>




	<div class="modal fade" id="pointCharge">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">포인트 충전하기</h4>
					<button type="button" class="close" data-dismiss="modal"> &times;</button>
				</div>
				<div class="modal-body">
				<form method='post' action='charge.me'>
				<input type="hidden" name="userCode" value=<%=loginUser.getUserNo() %>>
	
				<label for="recharge" class="chargeLabel" style="display:block">충전금액</label>
				
				<label class="chargeInput"  style="display:block">		
				<input type="radio" name="recharge"  value="10000" checked> 10,000원	 </label>	
				<label class="chargeInput" style="display:block"> 		
				<input type="radio"  name="recharge" class="chargeInput" value="20000"> 20,000원	 </label>	
				<label class="chargeInput" style="display:block"> 			
				<input type="radio"  name="recharge" class="chargeInput" value="50000"> 50,000원</label>	
				<label class="chargeInput" style="display:block"> 		
				<input type="radio"  name="recharge" class="chargeInput" value="100000"> 100,000원	</label>	

				<input type="submit" id="rechargeBtn" class="btn btn-secondary"value="충전하기">
				</form>
				</div>
				</div>
			</div>
		</div>


</body>

<script>
	
	
	   $(".pro").on("click",function(){
	   var pCode = $(this).find('input').val();
		location.href = "<%=request.getContextPath()%>/detail.do?pCode="+pCode;
	});
	   
	   
 	   $(".lec").on("click",function(){
	   var lecCode = $(this).find('input').val();
		location.href = "<%=request.getContextPath()%>/lectureDetail.le?code="+lecCode;
	});
		 
	
		 
    function checkPwd(){
    
    	var input = prompt('비밀번호를 입력해주세요',"");
    	if(input == "<%=loginUser.getUserPwd()%>" ) {
    		update();
    	} else if(input == null) {
    		// prompt의 취소를 누르면 null을 반환 -> 아무 동작 없음
    	} else{
    		alert('비밀번호가 일치하지 않습니다.');

    	}
    }
 
	function logout(){
		location.href = "<%=request.getContextPath()%>/logout.me ";
		}
	function update(){
		location.href = "<%=request.getContextPath()%>/updateForm.me ";
		}
	
	$(function(){
		
		// 성별에 따른 동적 프로필 이미지 src 할당
		
		var ssn = "<%=loginUser.getUserSsn()%>";
		var g = ssn.charAt(7);
		if (g == '2' || g == '4') {
			//여자 프로필이미지
			$("#pImg").attr("src","https://us.123rf.com/450wm/thesomeday123/thesomeday1231712/thesomeday123171200008/91087328-%EC%97%AC%EC%84%B1%EC%9A%A9-%EA%B8%B0%EB%B3%B8-%EC%95%84%EB%B0%94%ED%83%80-%ED%94%84%EB%A1%9C%ED%95%84-%EC%95%84%EC%9D%B4%EC%BD%98-%ED%9A%8C%EC%83%89-%EC%82%AC%EC%A7%84-%EC%9E%90%EB%A6%AC-%ED%91%9C%EC%8B%9C-%EC%9E%90-%EC%9D%BC%EB%9F%AC%EC%8A%A4%ED%8A%B8-%EB%B2%A1%ED%84%B0.jpg?ver=6")
			console.log($("#pImg"))

		} else {
			//남자 프로필이미지

			$("#pImg")
					.attr("src","https://us.123rf.com/450wm/thesomeday123/thesomeday1231712/thesomeday123171200009/91087331-%EB%82%A8%EC%84%B1%EC%9D%84%EC%9C%84%ED%95%9C-%EA%B8%B0%EB%B3%B8-%EC%95%84%EB%B0%94%ED%83%80-%ED%94%84%EB%A1%9C%ED%95%84-%EC%95%84%EC%9D%B4%EC%BD%98-%ED%9A%8C%EC%83%89-%EC%82%AC%EC%A7%84-%EC%9E%90%EB%A6%AC-%ED%91%9C%EC%8B%9C-%EC%9E%90-%EC%9D%BC%EB%9F%AC%EC%8A%A4%ED%8A%B8-%EB%B2%A1%ED%84%B0.jpg?ver=6")
			console.log($("#pImg"))
		}
	});
</script>
</html>