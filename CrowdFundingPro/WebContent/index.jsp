
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>메인페이지</title>


<!-- 구글 폰트 -->
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap"
	rel="stylesheet">

<style>
//인코딩 테스트
.body{
	font-family: 'Noto Sans KR', 'sans-serif';

}

.container_filed {
	width: 100%;
	padding-bottom:100px;
}

.carousel-item {
	height: 370px;
}

.carousel-item>img {
	width: 100%;
	height: 100%;
	object-fit: cover;
}

.container {
	color: black;
}

h4 {
	font-family: 'Noto Sans KR', 'sans-serif';
	margin-top: 30px !important;
	margin-bottom: 10px !important;
	font-size: 28px !important;
	font-weight: 600 !important;
	letter-spacing: -1px;
}

.pCard:hover .card-title {
	text-decoration: underline;
}

.pCard:hover {
	cursor: pointer;
	z-index:2;
	box-shadow: 0 10px 20px rgba(0, 0, 0, 0.2), 0 0 1px rgba(0, 0, 0, 0.2);
	border:1.5px solid lightgray;
}

.persent {
	color: #00B2B2;
	font-weight: bold;

}

#present {
	font-size:13px;
	font-weight: bold;
	color: #90949C;
	
}
#categoryName{
	font-size:12px;
	font-weight: bold;
	color: rgba(0, 0, 0, .8);
}
#ddln{
	font-size:13px;
	font-weight: bold;
	color: #90949C;
}

.lankTitle{
font-size:15px;
font-weight:bold;
font-family: 'Noto Sans KR', 'sans-serif';
}

.pTitle{
	font-family: 'Noto Sans KR', 'sans-serif';

}

li:hover{
	box-shadow: 0 10px 20px rgba(0, 0, 0, 0.2), 0 0 1px rgba(0, 0, 0, 0.2);
	z-index:1;
	cursor:pointer;
}

.rank{
width:400px;
}

.openFunding:hover{
cursor:pointer;
}


</style>

</head>

<body>

	<%@ include file="views/common/menubar.jsp"%>
	<!-- master push -->
	<!-- test -->

	<div class="container_filed">

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
					<img src="resources/images/recruit_img1.png" alt="img1">
				</div>
				<div class="carousel-item">
					<img src="resources/images/recruit_img2.png" alt="img2">
				</div>
				<div class="carousel-item">
					<img src="resources/images/recruit_img3.png" alt="img3">
				</div>
			</div>

			<!-- left and right controls -->
			<a href="#intro" class="carousel-control-prev" data-slide="prev">
				<span class="carousel-control-prev-icon"></span>
			</a> <a href="#intro" class="carousel-control-next" data-slide="next">
				<span class="carousel-control-next-icon"></span>
			</a>
		</div>

		<div class="box" style="width: 1300px; margin: 0 auto; padding: 10px;">

			<div class="container" style="display: inline;">
				<div class="row">
					<!-- Blog entries-->

					<div class="col-lg-8">
						<!-- Featured blog post-->
						<div class="preview">
							<%
								if (loginUser != null) {
							%>
							<h4><%=loginUser.getUserName()%>님, 이 프로젝트 어때요 ?
							</h4>
							<%
								} else {
							%>
							<h4>이 프로젝트 어때요 ?</h4>
							<%
								}
							%>
						</div>

						<!-- Nested row for non-featured blog posts-->
						<div class="row project">

							<%--
							<div class="col-lg-4 " style="padding: 5px;">
								<!-- Blog post-->
								<div class="card mb-4 ">
									<a href="#!"><img class="card-img-top"
										src="https://dummyimage.com/700x350/dee2e6/6c757d.jpg"
										alt="..." /></a>
									<div class="card-body">
										<div class="small text-muted">January 1, 2021</div>
										<h2 class="card-title h4">프로젝트명</h2>

									</div>
								</div>
							</div>
							
							

							--%>
						</div>

					</div>
					<!-- Side widgets-->
					<div class="col-lg-4" >
						<div class="ranking">
							<h4 style="display:inline-block;">실시간 랭킹</h4>
							<span style="font-size:11px; color:lightgray;">( 서포터 수 기준 )</span>
							
						</div>
						<div class=" mb-4 rankingList" style="border:none;">
							<!-- <div class="card-header">실시간 랭킹</div> -->
							<div class="list-group mb-0 rank" >


							</div>

						</div>

					</div>

				</div>

				<h4>이런 강의도 있어요 !</h4>
				<br>
				<div class="card banner" id="banner" style="width:800px; overflow:hidden; border:none; display:flex" >
				 	<ul class="lecSlider" id="lecSlider" style="list-style:none; display:flex; position: relative; overflow:hidden; ">
					</ul>
				</div>

				
				
		</div>
	</div>
	
	
	<div id="footer" style="display:block;">
		<%@ include file="views/common/footer.jsp"%>
	</div>
</body>

<script>

/*
<div class="col-lg-4 " style="padding: 5px;">
<!-- Blog post-->
<div class="card mb-4 ">
	<a href="#!"><img class="card-img-top"
		src="https://dummyimage.com/700x350/dee2e6/6c757d.jpg"
		alt="..." /></a>
	<div class="card-body">
		<div class="small text-muted">January 1, 2021</div>
		<h2 class="card-title h4">프로젝트명</h2>

	</div>
</div>
</div>
*/
$(function(){
	const card = $('.project');
	const card2 = $('.lecSlider');
	const rank = $('.rank');
	const contextPath = "<%=request.getContextPath()%>";

	
	<!--펀딩 추천 리스트 -->

	$.ajax({
		url:'random.pro',
		success:function(pList){
			console.log(pList);

			pList.forEach((pj) => {
			const ratio = Math.floor((pj.amountPresent/pj.amountGoal)*100);
			card.append(`
					<div class="col-lg-4 pCard " style="padding: 5px;">
				    <input type="hidden" value="\${pj.projectCode}">
					<div class="card mb-4" style="height:300px; width:280px; border:none;">
						<img class="card-img-top" style="height:185px;"
							src="\${contextPath}/resources/images/project/\${pj.titleImg}"
							alt="..." />
						<div class="card-body" 
							style="padding:10px; border-bottom: 3px solid gray; position:relative;"  >
						<h2 class="card-title pTitle" 
							style='font-size:15px; font-weight:bold; height:55px;'">\${pj.projectName}</h2>
						<span class="persent"> \${ratio}%</span>
						<span id="present">&#12685 \${pj.amountPresent.toLocaleString()}원</span>
						<span id="ddln" style="position:absolute; right:0; bottom:0;">마감 \${dDay(pj.ddln)}일 전</span>

						</div>
					</div>
					</div>	
					`)
					
					// 이벤트 처리
					   $(".pCard").on("click",function(){
						   var pCode = $(this).children('input').val();
							location.href = "<%=request.getContextPath()%>/detail.do?pCode="+pCode;
						});
		
			
			
			function dDay(ddln){
				var now = new Date(); //시간정보를 포함하고 있는 현재 날짜

				var year = now.getFullYear(); //연도 추출
				var month = now.getMonth(); // 달 추출
				var day = now.getDate();	//일 추출

				var today = new Date(year, month, day); // 시간을 제외하고 오늘 날짜 추출
				//프로젝트의 마감일은 "9월 23, 2021" 와 같은 문자열로 넘어온다. 
				var ddlnArr = ddln.split(" "); //공백기준으로 문자열을 자르고 Date 형식에 맞게 가공 처리
				var ddlnYear = ddlnArr[2]; 
				var ddlnMonth = ddlnArr[0].substr(0,ddlnArr[0].length-1);
				var ddlnDay = ddlnArr[1].substr(0,ddlnArr[1].length-1)
				
				//프로젝트의 ㅁ
				var ddlnDate = new Date(ddlnYear, ddlnMonth, ddlnDay); 
		
				var btMs =  ddlnDate.getTime() - today.getTime();
				var btDay = (btMs / (1000*60*60*24));
		return btDay+1;
				
			}
			
			
			
					
			})
			
		},
		error:function(){
			console.log('통신실패1');
		}
	})
	

	
	
	
	
		<!--실시간 랭킹 리스트 -->

	$.ajax({
		url:'rank.pro',
		success:function(pList){
			console.log(pList);
 			var index = 1;
			pList.forEach((pj => {
			const ratio = Math.floor((pj.amountPresent/pj.amountGoal)*100);
				rank.append(`
						<li class="list-group-item pCard"
						style="padding:0px; width:100%; height:100px; 
								overflow:hidden; height:auto; border:none;
								border-bottom: 2px solid lightgray; 
								padding-top:10px; padding-bottom:5px;">
					    <input type="hidden" value="\${pj.projectCode}">
						<div style="font-size:1.5em; color:rgba(0,0,0,.84); 
									font-weight:bold; padding-left:5px; 
									width:15px; height:100px; float:left;">\${index++}</div>
						<div  class="lankTitle" style="width:240px; color:rgba(0,0,0,.64) padding-top:20px; padding-left:10px; height:70px; float:left;" >
						\${pj.projectName}
						</div>
						<div class="thumbnail" style="display:inline-block; width:140px; height:100px; background-color:black; float:left;">
						<img class="card-img-top" style="height:100px;"
							src="\${contextPath}/resources/images/project/\${pj.titleImg}"
							alt="..." />
						</div>

						<div class="persent" style=" font-size:13px; padding-left:30px; width:240px; float:left;">
										\${ratio}%
							<span style="color:gray;"> &#12685 \${pj.categoryName}</span>		
						</div>
						</li>
						`)

			}));
			

			
			// 이벤트 처리
			   $(".pCard").on("click",function(){
				   var pCode = $(this).children('input').val();
					location.href = "<%=request.getContextPath()%>/detail.do?pCode="+pCode;
				});
			
		},error:function(){
			console.log('통신에러');
		}
		
	})
	

	
	$.ajax({
		url:'random.le',
		success:
			function(lList){
			/*
				lectureCode: "1006"
				lectureNum: 80
				lectureTime: 0
				lectureTitle: "획기적인 것과 창의적인 것의 차이"
				lectureTopic: "펀딩오픈강의"
			    lecturer: "Ms.Kwon"
		    */
			lList.forEach((le) => {
 			card2.append(`
 						
 						<li class="slider_item">
 						<p class="lectureCode" name="lectureCode" style="display:none;">\${le.lectureCode} </p>
 							<img src="\${contextPath}/resources/lectureImage/\${le.lectureImage}" 
 							style="width:800px; height:285px; padding: 15px 95px 15px 95px;">
 						<div class="card-body">
						<h4 class="card-title">\${le.lectureTitle}</h4>
						<span style="text-weight:bold"><b>\${le.lectureTopic}</b></span><br>
						<span style="text-weight:bold"><b>강사 : \${le.lecturer}</b> </span><br>
						</div>
						</li>
						
						
					
					`)
			
 			var WrapperWidth = document.querySelector(".banner").clientWidth;
 			var total = document.querySelectorAll(".slider_item").length;
 			var lecSlider = document.querySelector(".lecSlider");

 			lecSlider.style.width = WrapperWidth * total +'px';
 			slideLecture();
 			var index = 0;

 			function slideLecture(){
 				
 				for ( var i = 0; i < total; i++){
 					lecSlider.style.left =  -(WrapperWidth * index ) + 'px';
 				}
 				index++;
 				if(index == total){
 					index = 0;
 				}
 				setTimeout(slideLecture,3000);
 			
					
			}
 			
 			$(".slider_item").click(function(){
 				var code = $(this).children().eq(0).text();
 				location.href="<%=request.getContextPath()%>/lectureDetail.le?code="+code;
 				console.log(code);
 				})
 				
		 })
		 
		 
		 
		 
		}
		,
		error:function(){
			console.log('통신실패1');
		}
	})
	
})

$(function(){
			$(".slider_Item").click(function(){
			var code = $(this).children().eq(0).text();
			location.href="<%=request.getContextPath()%>/lectureDetail.le?code="+code;
			console.log(code);
			})
		})



	
</script>
