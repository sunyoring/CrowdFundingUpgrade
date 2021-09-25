<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.kh.project.model.vo.Project"%>
<%@ page import="com.kh.lecture.model.vo.Lecture"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.text.DecimalFormat"%>

<%
	ArrayList<Project> pList = (ArrayList<Project>) session.getAttribute("pListResult");
//	ArrayList<Lecture> lList = session.getAttribute("lListResult");
DecimalFormat decFormat = new DecimalFormat("###,###");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>검색결과</title>
</head>
<!-- 구글 폰트 -->
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap"
	rel="stylesheet">

<style>
.box {
	width: 1300px;
	margin: 0 auto;
	padding: 10px;
}

.info {
	width: 350px;
	height: 360px;
	float: left;
	margin: 30px;
	border: none !important;
}

.card-img-top>#pImg {
	width: 100%;
	height: 220px;
	object-fit: cover;
}

#pTitle {
	font-size: 20px;
	font-weight: bold;
	font-family: 'Noto Sans KR', 'sans-serif';
	padding: 5px;
}

#pTitle:hover {
	text-decoration: underline;
}

#pAmount {
	font-size: 18px;
	font-family: 'Noto Sans KR', 'sans-serif';
	color: #90949C;
	border: none;
	border-top: 4px solid #00B2B2;
	padding-top: 2px;
}

.card {
	display: block !important;
	float: left !important;
	border: none;
	float: left !important;
}

.card:hover {
	cursor: pointer;
}


.card:hover .pTitle{
	text-decoration:underline;
}


.card-body {
	padding: 0px !important;
}

#persent {
	color: #00B2B2;
	font-weight: bold;
}

#present {
	font-size:12px;
	font-weight: bold;
	color: #90949C;
}

#categoryName{
	margin-left:40px;
	font-size:12px;
	font-weight: bold;
	color: rgba(0, 0, 0, .8);
}


#result {
	height: 100px;
	margin-top: 120px;
	margin-bottom: 100px;
	border-bottom: 1px solid rgba(0, 0, 0, .2);
}

#resultTitle {
	font-weight: bold;
	font-family: 'Noto Sans KR', sans-serif;
}
</style>

<body>
	<%@ include file="menubar.jsp"%>

	<div class="box">


		<div id="result">


			<h1 id="resultTitle"><%=keyword%><span style="color: gray">
					검색결과</span>
			</h1>
		</div>

		<%
			if (pList.isEmpty()) {
		%>
		<div id="projectTitle"
			style="font-size: 17px; font-weight: bold; color: rgba(0, 0, 0, .87);">프로젝트
			검색결과가 없습니다.</div>
		<br>

		<%
			} else {
		%>

		<div id="projectTitle"
			style="font-size: 17px; font-weight: bold; color: rgba(0, 0, 0, .87);">
			프로젝트 <b
				style="color: #00B2B2; font-family: 'Noto Sans KR', sans-serif;"><%=pList.size()%></b>
			개
		</div>
		<br>
		<div class="row project">
			<%
				for (Project pj : pList) {
			%>
			<div class="col-lg-4 pCard " style="padding: 5px;">
			<input type="hidden" value="<%=pj.getProjectCode()%>">
				<div class="card mb-4"
					style="height: 280px; width: 360px; border: none;">
					<img class="card-img-top" style="height: 210px;"
						src="<%=request.getContextPath()%>/resources/images/project/<%=pj.getTitleImg()%>">
					<div class="card-body"
						style="padding: 10px; border-bottom: 3px solid gray;">
						<h2 class="card-title pTitle"
							style='font-size: 15px; font-weight: bold; height: 40px;'><%=pj.getProjectName()%></h2>
						<span id="persent"> <%=(int) Math.floor(((double) pj.getAmountPresent() / pj.getAmountGoal()) * 100)%>%
						</span> <span id="present"> &#12685 <%=decFormat.format(pj.getAmountPresent())%>원</span>
						<span id="categoryName"><%=pj.getCategoryName() %> </span>
					</div>
				</div>
			</div>
			<%
				}
			}
			%>
		</div>
		<%-- 	
	<div class="row recture">


			<div class="col-lg-4 pCard " style="padding: 5px;">
				    <input type="hidden" value="">
					<div class="card mb-4" style="height:300px; width:260px; border:none;">
						<img class="card-img-top" style="height:185px;"
							src="\${contextPath}/resources/images/project/\${pj.titleImg}"
							alt="..." />
						<div class="card-body" style="padding:10px; border-bottom: 3px solid gray;" >
						<h2 class="card-title pTitle" style='font-size:15px; font-weight:bold; height:55px;'">\${pj.projectName}</h2>
						<span class="persent"> \${ratio}%</span>
						<span id="present"> &#12685 \${pj.amountPresent.toLocaleString()}원</span>
						</div>
					</div>
					</div>	
	
	</div>
--%>
	</div>


	<%@ include file="footer.jsp"%>

</body>
<script>
$(".pCard").on("click",function(){
	   var pCode = $(this).children('input').val();
	   console.log(pCode);
		location.href = "<%=request.getContextPath()%>/detail.do?pCode="+pCode;
	});
</script>
</html>