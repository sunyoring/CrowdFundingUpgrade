<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.ArrayList"%>
<%@ page import="java.text.DecimalFormat"%>
<%@ page import="com.kh.project.model.vo.Project"%>
<%@ page import="com.kh.project.model.vo.IProject"%>


<%
	Project pj = (Project) (session.getAttribute("pj"));
//Attachment at=(Attachment)(request.getAttribute("at"));
DecimalFormat decFormat = new DecimalFormat("###,###");
ArrayList<IProject> ipList = (ArrayList<IProject>) session.getAttribute("interPj");
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.body {
	font-family: 'Noto Sans KR', 'sans-serif';
}

.container_filed {
	width: 1300px;
	height: 1700px;
	margin: 0 auto;
	padding: 20px;
	padding-top: 120px;
}

.header {
	width: 100%;
	height: 80px;
	padding-left: 20px;
	border-bottom: 3px solid gray;
	margin-bottom: 50px;
}

.wrap2 {
	/*border:1px solid green;*/
	width: 100%;
	height: 30%;
	display: flex;
	margin: 25px;
}

.information {
	width: 50%;
	height: 90%;
	padding-left: 40px;
}

.information h2 {
	font-weight: bold;
}

#info {
	border: 1px solid black;
}

.wrap3 {
	width: 100%;
	height: 60%;
	display: flex;
	margin: 25px;
}

.content1 {
	border: 1px solid black;
	width: 70%;
	height: 100%;
	padding: 20px;
}

.content2 {
	/* border:1px solid blue; */
	width: 20%;
	height: 100%;
}

.side {
	padding: 10px;
	border: 1px solid black;
	width: 100%;
	height: 40%;
	margin-left: 20px;

	/* top:500px;
            left:700px;
            position: fixed; */
}

.fBtn {
	margin-top: 15px;
	width: 260px;
	height: 40px;
	background: none;
	border: 2px solid #00B2B2;
	border-radius: 10px;
}

.fBtn:hover {
	background: #F4FFFF;
	box-shadow: 2px 2px lightgray;
}

#imgBox {
	width: 800px;
	height: 330px;
	position: relative;
	z-index: 1;
}

#imgBox::after {
	width: 100%;
	height: 330px;
	content: "";
	background:
		url('<%=request.getContextPath()%>/resources/images/project/<%=pj.getTitleImg()%>');
	background-size: contain;
	position: absolute;
	top: 0;
	left: 0;
	z-index: -1;
}

#pTitle {
	font-size: 30px;
	font-weight: bold;
}

.pInfol {
	font-size: 16px;
	font-weight: bold;
	margin-right: 10px;
	width: 130px;
}

#persent {
	font-size: 20px;
	color: #00B2B2;
	font-weight: bold;
}

.pInfot {
	font-size: 20px;
	font-weight: 600;
}

.pInfo {
	font-size: 18px;
	font-weight: bold;
	margin-right: 10px;
}

.pInfo span {
	font-size: 25px;
	font-weight: bold;
	display: block;
	margin-bottom: 50px;
}

/*
         .information{
            display:flex;

            flex-direction: column;
            align-items: center;
            justify-content:center; 
        } */
#btn3 {
	font-size: 25px;
}
</style>
</head>
<body>
	<%@ include file="../common/menubar.jsp"%>

	<div class="container_filed">


		<div class="header">
			<h1 id=pTitle>
				<%=pj.getProjectName()%>
			</h1>
		</div>


		<div class="wrap2">
			<div id="imgBox" class="information"></div>
			<div class="information">
				<h2>프로젝트정보</h2>
				<br> <label class="pInfol">목표금액 </label><span class='pInfot'><%=decFormat.format(pj.getAmountGoal())%>원</span><br>
				<label class="pInfol">현재금액 </label><span class='pInfot'><%=decFormat.format(pj.getAmountPresent())%>원</span><br>
				<label class="pInfol">달성률 </label><span id="persent"><%=(int) Math.floor(((double) pj.getAmountPresent() / pj.getAmountGoal()) * 100)%>%</span><br>
				<label class="pInfol">마감일 </label><span class='pInfot'><%=pj.getDdln()%></span><br>

				<label class="pInfol">상품가격 </label><span class=pInfot><%=pj.getDeliveryCharge()%>원</span><br>
				<input type="text" name="amount" id="input1" value="1" size="3"
					onchange="change();"> <br>


				<form action="" id="postForm" method="post">

					<input type="hidden" name="pCode"
						value="<%=pj.getProjectCode()%>"> <input type="hidden"
						name="fileNo" value="<%=pj.getFileNo()%>">


					<button class="fBtn" id="btn1" type="button" onclick="signIn();">펀딩하기</button>
					<%
						if (!ipList.isEmpty()) { //디테일페이지 켜질 때 해당 프로젝트 pCode로 관심테이블에서 리스트 가져옴
						for (IProject p : ipList) {	// 리스트 반복문 돌리면서
							if (p.getUserNo() == loginUser.getUserNo()) { //로그인 유저 넘버랑 일치하는 거 있으면
					%>  
						<button class="fBtn" id="btn3" type="button"
						onclick="deleteInterestIn();">♥</button>
					<% //관심 해제 버튼 노출
					break;

							}else{
								%>
									<button class="fBtn" id="btn3" type="button"
						onclick="interestIn();">♡</button>
								<%
							}
					}
					}else{
						%>	
						
								
						<button class="fBtn" id="btn3" type="button"
						onclick="interestIn();">♡</button>
						<% 
					}
					%>
					<button class="fBtn" id="btn2" type="button"
						onclick="updateForm();">수정하기</button>


				</form>
			</div>
		</div>
		<div class="wrap3">
			<div class="content1">
				<label class="pInfo"> <span>프로젝트스토리 </span> <br>

				</label><br>
				<%=pj.getDetailIntro()%>
			</div>
			<div class="content2">
				<div class="side">
					<h3>인기프로젝트</h3>
				</div>
				<div class="side">
					<h3>연관프로젝트</h3>
				</div>

				<script>
                	function updateForm(){
                		
                		$("#postForm").attr("action", "<%=request.getContextPath()%>/update.do");
                		$("#postForm").submit();
        				
        			}
                	
                </script>

				<script>
                	function interestIn(){
                		$("#postForm").attr("action","<%=request.getContextPath()%>/interestIn.do");
                		$("#postForm").submit();
                	}
                	function deleteInterestIn(){
                		$("#postForm").attr("action","<%=request.getContextPath()%>/deleteInter.pro");
                		$("#postForm").submit();
                	}
                	
                	
                	
                </script>

				<script>
                	function signIn(){
                		
                		var input = confirm('펀딩을 신청하십니까? 보유 포인트에서 금액이 자동으로 차감됩니다.')
                		
                		if(input){	
                			$("#postForm").attr("action","<%=request.getContextPath()%>/signIn.do");
                    		$("#postForm").submit();
                		}
                		
                		
                	}
                </script>

				<script>
                	function test2(){
                		$("#postForm").attr("action","<%=request.getContextPath()%>
					/interestIn.do");
						$("#postForm").submit();
					}
				</script>


			</div>
		</div>

	</div>


	<%@ include file="../common/footer.jsp"%>


</body>
</html>