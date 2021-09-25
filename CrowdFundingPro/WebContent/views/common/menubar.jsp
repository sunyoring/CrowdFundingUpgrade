<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.kh.user.model.vo.User"%>


<%
	User loginUser = (User) session.getAttribute("loginUser");
	String msg = (String) session.getAttribute("msg");
	String keyword = (String) session.getAttribute("keyword");
	session.setAttribute("location",request.getHeader("Referer"));
	System.out.println(request.getRequestURI());
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>




<!-- 다크모드 css -->
<link href="<%=request.getContextPath() %>/resources/css/darkTheme.css" rel="stylesheet">


<!-- bootstrap 4 -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<!-- bootstarp4 date range picker -->
<script type="text/javascript"
	src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
<script type="text/javascript"
	src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css" />


<!-- fontawesome bootstrap 4 용 icon -->
<link rel="stylesheet"
	href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
	integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p"
	crossorigin="anonymous" />
	
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap"
	rel="stylesheet">

</head>
<style>
#keyword{
width:250px;
height:35px;
background-color: rgba(255,255,255,.8);
border:none;
border-radius: 20px;
margin-right:10px;
margin-top:3px;
}
#keyword::placeholder{
font-size:13px;
text-align:center;
font-family: 'Noto Sans KR', 'sans-serif';

}

#keyword{
border:solid 0.2px white;
background-color: white;

}

#enrollPro{
color:white;
font-size:17px;
}
#enrollPro:hover{
color:#00B2B2;
text-decoration:underline;

}

.menubar{
font-family: 'Noto Sans KR', 'sans-serif';

}
</style>

<body>	
	<nav class="navbar navbar-expand-md bg-dark navbar-dark menubar"
		style="padding-left: 80px; padding-right: 80px;">
		<a href="<%=request.getContextPath()%>" class="navbar-brand"
			style="font-size: 23px;">CROWD FUND!NG</a>
		<!-- 클릭하면 메인 페이지로 이동  -->

		<ul class="navbar-nav">
			<li class="nav-item"><a href="<%= request.getContextPath() %>/projectPage.do" class="nav-link">펀딩하기</a></li> <!-- 펀딩페이지 링크 -->


			<li class="nav-item"><a
				href="<%=request.getContextPath()%>/recruitPage.do"
				class="nav-link">채용공고</a></li>

			<li class="nav-item dropdown"><a href="#"
				class="nav-link dropdown-toggle" id="navbardrop"
				data-toggle="dropdown"> 더보기 </a>
				<div class="dropdown-menu">


					<a href="<%= request.getContextPath() %>/eList.do" class="dropdown-item">EVENT</a> 
					<a href="<%= request.getContextPath() %>/lecture.le" class="dropdown-item">펀딩스쿨</a>
					<a href="#" class="dropdown-item" onclick="goFaq();">문의하기</a>
						
				</div>
			</li>

		</ul>
		<ul class="navbar-nav ml-auto">
		
			<!--  검색 아이콘   **추가기능** -->
			<input id="keyword"  type="text" onkeyup="enterkey();"  placeholder="어떤 프로젝트를 찾고 계신가요 ?" autocomplete="off">
			<li class="nav-item"><a id="searchForm" onclick="goSearch();" class="nav-link"> 
			<i class="fas fa-search fa-lg"></i>
			</a></li>			
			
			<%
				if (loginUser == null) {
			%>	
			<!-- 로그인 전 -->
			<li class="nav-item"><a
				href="<%=request.getContextPath()%>/views/user/userLoginForm.jsp" class="nav-link">로그인</a></li>
			<li class="nav-item"><a
				href="<%=request.getContextPath()%>/enrollForm.me" class="nav-link">회원가입</a></li>
			<%
				} else {
			%>
			<!-- 로그인 후 -->
			<li class="nav-item"><a
				href="<%=request.getContextPath()%>/mypage.me" class="nav-link">마이페이지</a></li>
			<li class="nav-item"><a
				href="<%=request.getContextPath()%>/logout.me" class="nav-link">로그아웃</a></li>
			<%
				}
			%>

			<li class="nav-item"><a id="enrollPro"
				href="views/project/registrationView.jsp" class="nav-link">[ 프로젝트 신청하기 ]</a></li>
			



		<%-- 
			<%if( theme == null || theme.equals("") || theme.equals("default") ) {%>
						<li class="nav-item"><a id="colorTheme" role="button"
								class="nav-link" onclick="changeDark();">다크모드</a></li>
			<%}else{ %>
						<li class="nav-item"><a id="colorTheme" role="button"
								class="nav-link" onclick="changeDefault();">기본모드</a></li>
			<%} %>
			--%>




		</ul>

	</nav>
</body>
<script>


/*  검색기능   */
function goSearch(){	
	var keyword = $("#keyword").val();
	if(keyword == '' || keyword ==null){
		alert("검색어를 입력해주세요")
	}else{
		location.href="<%=request.getContextPath()%>/search.do?keyword="+keyword;	
	}
}

function enterkey() {
	if(event.keyCode ==13){
		goSearch();	
	}
}



/* 문의사항 == FAQ */
function goFaq() {
	location.href="<%=request.getContextPath()%>/list.fq";
}






<%-- 

다크모드 ㅠ


function changeDark() {
	location.href='<%=request.getContextPath()%>/darkMode.do';
}

function changeDefault() {
	location.href='<%=request.getContextPath()%>/whiteMode.do';	
}



$(function(){
	var theme = '<%=theme%>';
	
	if(theme == null || theme =='' || theme=='default'){
		$('body').removeClass('dark-theme');

	}else{
		$('body').addClass('dark-theme');

	}
	
	
})
--%>


</script>
</html>