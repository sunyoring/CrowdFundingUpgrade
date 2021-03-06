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
				<h2>??????????????????</h2>
				<br> <label class="pInfol">???????????? </label><span class='pInfot'><%=decFormat.format(pj.getAmountGoal())%>???</span><br>
				<label class="pInfol">???????????? </label><span class='pInfot'><%=decFormat.format(pj.getAmountPresent())%>???</span><br>
				<label class="pInfol">????????? </label><span id="persent"><%=(int) Math.floor(((double) pj.getAmountPresent() / pj.getAmountGoal()) * 100)%>%</span><br>
				<label class="pInfol">????????? </label><span class='pInfot'><%=pj.getDdln()%></span><br>

				<label class="pInfol">???????????? </label><span class=pInfot><%=pj.getDeliveryCharge()%>???</span><br>
				<input type="text" name="amount" id="input1" value="1" size="3"
					onchange="change();"> <br>


				<form action="" id="postForm" method="post">

					<input type="hidden" name="pCode"
						value="<%=pj.getProjectCode()%>"> <input type="hidden"
						name="fileNo" value="<%=pj.getFileNo()%>">


					<button class="fBtn" id="btn1" type="button" onclick="signIn();">????????????</button>
					<%
						if (!ipList.isEmpty()) { //?????????????????? ?????? ??? ?????? ???????????? pCode??? ????????????????????? ????????? ?????????
						for (IProject p : ipList) {	// ????????? ????????? ????????????
							if (p.getUserNo() == loginUser.getUserNo()) { //????????? ?????? ????????? ???????????? ??? ?????????
					%>  
						<button class="fBtn" id="btn3" type="button"
						onclick="deleteInterestIn();">???</button>
					<% //?????? ?????? ?????? ??????
					break;

							}else{
								%>
									<button class="fBtn" id="btn3" type="button"
						onclick="interestIn();">???</button>
								<%
							}
					}
					}else{
						%>	
						
								
						<button class="fBtn" id="btn3" type="button"
						onclick="interestIn();">???</button>
						<% 
					}
					%>
					<button class="fBtn" id="btn2" type="button"
						onclick="updateForm();">????????????</button>


				</form>
			</div>
		</div>
		<div class="wrap3">
			<div class="content1">
				<label class="pInfo"> <span>????????????????????? </span> <br>

				</label><br>
				<%=pj.getDetailIntro()%>
			</div>
			<div class="content2">
				<div class="side">
					<h3>??????????????????</h3>
				</div>
				<div class="side">
					<h3>??????????????????</h3>
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
                		
                		var input = confirm('????????? ??????????????????? ?????? ??????????????? ????????? ???????????? ???????????????.')
                		
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