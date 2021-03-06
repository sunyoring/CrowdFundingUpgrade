<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.ArrayList"%>
<%@ page import="java.text.DecimalFormat"%>
<%@ page import="com.kh.project.model.vo.Project"%>


<%
	//ArrayList<Project> list = (ArrayList<Project>) request.getAttribute("list");
//DecimalFormat decFormat = new DecimalFormat("###,###");
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>


<style>

    .body{
    	font-family: 'Noto Sans KR', 'sans-serif';
    }

.carousel-item {
	height: 250px;
}

.carousel-item>img {
	width: 100%;
	height: 100%;
	object-fit: cover;
}

#box {
	width: 100%;
	margin: 0 auto;
	overflow: hidden;
	height: auto;
}

.container_filed {
	width: 1300px;
	overflow: hidden;
	height: auto;
	padding: 15px;
	margin: 0 auto;
	padding-top: 50px;
}

#categoryName h1 {
	width: 100%;
	font-family: 'Noto Sans KR', 'sans-serif';
	font-size: 35px;
	font-weight: 700;
	margin-top: 70px;
	margin-bottom: 40px;
	margin-left: 30px;
}

#categoryName p {
	width: 100%;
	font-family: 'Roboto', 'sans-serif';
	font-size: 25px;
	font-weight: 700;
	margin-top: 70px;
	margin-bottom: 40px;
	margin-left: 30px;
}

#celectCategory {
	width: 100%;
}

.header {
	border: 1px solid black;
	width: 100%;
	height: 200px;
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

#pCategory {
	font-size: 14px;
	font-weight: bold;
	font-family: 'Noto Sans KR', 'sans-serif';
	color: #90949C;
	border: none;
	padding-top: 2px;
}

#pAmount {
	font-size: 18px;
	font-family: 'Noto Sans KR', 'sans-serif';
	color: #90949C;
	border: none;
	padding-top: 2px;
}

.card {
	display: block;
	border: none;
	float: left !important;
}

.card:hover {
	cursor: pointer;
}

.card-body {
	padding: 0px !important;
}

#pBox {
	width: 1300px;
	margin: 0 auto;
	border: 1px solid red;
}

#persent {
	color: #00B2B2;
	font-weight: bold;
}

#present {
	font-weight: bold;
	color: #90949C;
}

#pSearch {
	height: 38px;
	width: 400px;
	border: 2px solid #00B2B2;
	border-radius: 20px;
	margin-left: 20px;
	margin-right: 20px;
	margin-bottom: 2px;
}

#pSearch:hover {
	background: #F4FFFF;
	opacity: 0.5;
}

#pSearch::placeholder {
	text-align: center;
}

#searchBtn {
	font-size: 22px;
	border: none;
	background: none;
	font-weight: bold;
	color: #00B2B2;
}
#ddln{
	font-size:13px;
	font-weight: bold;
	color: #90949C;
}
</style>
</head>
<body>

	<%@ include file="../common/menubar.jsp"%>

	<div id=box>

		<!-- carousel -->
		<div id="intro" class="carousel slide" data-ride="carousel">
			<!-- indicators -->
			<ul class="carousel-indicators">
				<li data-target="#intro" data-slide-to="0" class="active"></li>
				<li data-target="#intro" data-slide-to="1"></li>
				<li data-target="#intro" data-slide-to="2"></li>
			</ul>

			<!-- slideshow -->

			
						<div class="carousel-inner">
				<div class="carousel-item active">
					<img src="resources/images/mainBannerImg1.png" alt="img1">
				</div>
				<div class="carousel-item">
					<img src="resources/images/mainBannerImg2.png" alt="img2">
				</div>
				<div class="carousel-item">
					<img src="resources/images/mainBannerImg3.png" alt="img3">
				</div>
			</div>
			
			

			<!-- left and right controls -->
			<a href="#intro" class="carousel-control-prev" data-slide="prev">
				<span class="carousel-control-prev-icon"></span>
			</a> <a href="#intro" class="carousel-control-next" data-slide="next">
				<span class="carousel-control-next-icon"></span>
			</a>
		</div>



		<div class="container_filed">
			<form class="searchArea">
				<div id="selectCategory">
					<select id="cName" name="cName" class="form-control"
						style="width: 200px; display: inline;">
						<%-- ajax ?????? setCategory() --%>
					</select> 
					<input type="text" id="pSearch" name="search" placeholder="?????? ??????????????? ?????? ?????????????" style="text-align: center;"> 
					<input id="searchBtn" style="height: 38px;" type="button" value='??????' onclick="searchProject();"> <i class="fas fa-search fa-lg" style="color: #00B2B2"></i>
				</div>
			</form>

			<%-- ???????????? ??????????????? --%>
			<div id="cRadioButton"
				class="btn-group-vertical btn-group-toggle mt-3 mx-5" role="group"
				data-toggle="buttons">
				<%-- ajax ?????? setCategory() --%>
			</div>

			<div id="categoryName">
				<h1>?????? ??????????????? ??????????????? ?</h1>
				<p></p>
			</div>


			<%
				//for (Project pj : list) {
			%>
			<%-- 
        <div class="div1">
        	<input type="hidden" value="<%=pj.getProjectCode()%>">
         	<img  src="<%=request.getContextPath() %>/resources/images/project/<%=pj.getTitleImg()%>" width="100%" height="80%">                
            <p class="caption"><%=pj.getProjectName() %><br>
                	????????????:<%=pj.getAmountPresent() %>???
            </p>
        </div>&nbsp;
        --%>

			<%-- ajax ?????? setProject --%>

			<%
				//}
			%>
		</div>
	</div>

	<div id="footer" style="clear: both; display: block;">
		<%@ include file="../common/footer.jsp"%>
	</div>
</body>

<script>
		
	$(function(){
		// ??? ????????? ?????? ??? ?????? ?????? ??????
		localStorage.removeItem("page");
		localStorage.removeItem("categoryNo");
		localStorage.removeItem("searchValue");
		
		loadCategory();;
		
		infinityScroll();
	})	
	
	let isRead = false;
	
	// ?????? 
	function searchProject() {
		const value = $('#pSearch').val();
		
		const selectOption = $('#cName option:selected').val();
		//console.log(selectOption);
		$("#cRadioButton input[type=radio]").each(function(index, item) {
			if (item.value === selectOption) {
				//console.log(item);
				isRead = true;
				$('div.container_filed .card').remove();
				localStorage.setItem("searchValue", value);
				$(item).parent().trigger("click", true);
				return false;
			}
		});
	}
	
	// ????????????
	function loadCategory() {
		$.ajax({
			url: "categoryList.do",
			success: function(category) {
				//console.log(category);
				setCategory(category);
			},
			error: function(e) {
				console.log("ajax ?????? ??????");	
			}
		});
	}
	
	function setCategory({ category, categoryCnt }) {
		// ?????? option ??????
		const selectOption = $("#cName");
		let option = $('<option>').prop("selected", true);
		//prop()
		// ????????? ???????????? ?????? ????????? ????????? ???????????? ??????????????? ???????????? ??????
		option.text('??????');
		option.val(0);
		
		selectOption.append(option);
		
		Object.keys(category).forEach((key) => {
			selectOption.append(`
				<option value='\${key}'>\${category[key]}</option>		
			`);
		});		
		
		// ??????????????? ??????
		const radioButton = $("#cRadioButton");
		
		// ???????????? ?????? ?????? ?????? 
		const totalCnt = Object.keys(categoryCnt).reduce((sum, key) => sum + categoryCnt[key], 0);
		//console.log(totalCnt);
		
		// ?????? ?????? ??????
		// button group ??????
		let btnGroup = $('<div>').addClass("btn-group");
		radioButton.append(btnGroup);
		
		btnGroup.append(`
			<button type="button" class="btn" style="width: 130px;">
			   <input type="hidden" name="project_category_count" value="\${totalCnt}">
               <input type="radio" name="project_category" value="0" autocomplete="off" checked>
               <img src="resources/images/category/0.png" class="rounded-circle" style="width: 70px;"><br>
               <span class="text-dark font-weight-bold small">??????</span>
            </button>	
		`);
		
		// ????????? ?????? ??????
		Object.keys(category).forEach((key) => {
			// key??? 9??? ?????? ?????? ?????????
			if (key == '9') {
				btnGroup = $('<div>').addClass("btn-group");
				radioButton.append(btnGroup);				
			}
			btnGroup.append(`
				<button type="button" class="btn" style="width: 130px;">
				<input type="hidden" name="project_category_count" value="\${categoryCnt[key]}">
	               <input type="radio" name="project_category" value="\${key}" autocomplete="off">
	               <img src="resources/images/category/\${key}.jpg" class="rounded-circle" style="width: 70px;"><br>
	               <span class="text-dark font-weight-bold small">\${category[key]}</span>
	            </button>		
			`);
		});
		
		// ????????? ??????
		$("#cRadioButton button").on("click", function() {
			//console.log(($(this).children('input').val()));
			const categoryNo = $(this).children('input[type=radio]').val();
			localStorage.setItem("categoryNo", categoryNo);
			
			const categoryCnt = $(this).children('input[type=hidden]').val();
			
			const categoryName = $(this).children('span').text();
			$('#categoryName h1').text(`\${categoryName} (\${categoryCnt})`);
			
			$('div.container_filed .card').remove();
			localStorage.setItem("page", 1);
			
			isRead = true;
			readProject();
		});
	}
	
	// ?????? ????????????
	
	function infinityScroll() {
		readProject();
		
		const container = document.querySelector('div.container_filed');
		const screenHeight = screen.height;
		
		// ????????? ?????????
		document.addEventListener('scroll', function() {
			
			const fullHeight = container.clientHeight;
			const scrollPosition = pageYOffset;
			
			//console.log(fullHeight, screenHeight, scrollPosition, isRead);
			
			if (fullHeight - screenHeight / 2 <= scrollPosition && !isRead) {
				isRead = true; // ???????????? ????????? ??????
				readProject();
			}
			
		}, {passive : true});
	}
	
	// ???????????? 
	function readProject() {
		// localStorage??? ?????? ????????? ?????? ??????
		let curPage = localStorage.getItem("page");
		if (curPage === null) {
			curPage = 1;
		}
		
		let categoryNo = localStorage.getItem("categoryNo");
		if (categoryNo === null) {
			categoryNo = 0;
		}
		
		let searchValue = localStorage.getItem("searchValue");
		if (searchValue === null) {
			searchValue = '';
		}
		
		//console.log(curPage);
		
		// ajax ?????? project ?????????
		$.ajax({
			url : 'projectList.do',
			//beforeSend: loading(true), // ?????????????????? ??? ?????? ?????? ????????? ??????
			data : { 
				'page' : curPage, 
				'categoryNo' : categoryNo,
				'searchValue' : searchValue,
			},
			success: function(project) {
				console.log(project);
				setProject(project);
			},
			error: function(e) {
				console.log("ajax ?????? ??????");
			},
			complete: function() {
				//loadding(false), // ??????????????? ?????? ?????????
				curPage++;
				localStorage.setItem("page", curPage); // ?????? ????????? ?????? ??????????????? storage??? ??????
				
				isRead = false;
			},
		});
	}
   
	// project ???????????? function
	function setProject({ project:projects, search }) {
	   
	   const container = $('div.container_filed');
	   
	   const contextPath = "<%=request.getContextPath()%>";
	   
	   projects.forEach((pj) => {
		   const ratio = Math.floor((pj.amountPresent/pj.amountGoal)*100);
		   container.append(`
		  	<div class="card info project">
			    <input type="hidden" value="\${pj.projectCode}">
				<div class="card-img-top">
					<img id="pImg" src="\${contextPath}/resources/images/project/\${pj.titleImg}">
				</div>
				<div class="card-body">
					<h2 class="card-title h5" id="pTitle">\${pj.projectName}</h2>
					<p class="card-text" id="pCategory">\${pj.categoryName}</p>
					<div class="progress" style="height:3px;">
					  <div class="progress-bar bg-info progress-bar-striped progress-bar-animated" style="width:\${ratio > 100 ? 100 : ratio}%"></div>
					</div>
					<div>
						<span id="persent"> \${ratio}%</span>
						<span id="present">  \${pj.amountPresent.toLocaleString()}???</span>
						<span id="ddln" style="position:absolute; right:0; bottom:0;">?????? \${dDay(pj.ddln)}??? ???</span>
					</div>
				</div>
			</div>
		  `);
	   });
	   
		// ????????? ??????
	   $(".project").on("click",function(){
		   var pCode = $(this).children().eq(0).val();
		   	//console.log(pCode);
			location.href = "<%=request.getContextPath()%>/detail.do?pCode="+pCode;
		});
		
		// ????????? ??????
		//console.log(searchCnt);
		// ?????? ????????? ?????? ??????
		if (search.cnt != -1) {
			$('#categoryName p').text(`\${search.categoryName} ?????? : \${search.value} (\${search.cnt})`);
		} else {
			$('#categoryName p').text('');
		}
	}
	
	// ?????? ??????
	function dDay(ddln){
		//console.log(ddln);
		
		var now = new Date();

		var year = now.getFullYear();
		var month = now.getMonth();
		var day = now.getDate();

		var today = new Date(year, month, day);
		//console.log("?????? ?????? : " + today);

		var ddlnArr = ddln.split(" ");
		
		var ddlnYear = ddlnArr[2]; 
		var ddlnMonth = ddlnArr[0].substr(0,ddlnArr[0].length-1);
		var ddlnDay = ddlnArr[1].substr(0,ddlnArr[1].length-1)
		
		var ddlnDate = new Date(ddlnYear, ddlnMonth, ddlnDay);
		//console.log("?????? ?????? : " + ddlnDate);

		
		var btMs =  ddlnDate.getTime() - today.getTime();
		var btDay = (btMs / (1000*60*60*24));
		//console.log("d-day : " + btDay);

		return btDay+1;
		
	}
	
</script>





</html>