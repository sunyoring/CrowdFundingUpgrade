<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.kh.user.model.vo.User"%>
<%@page import="java.util.*"%>


<%
	ArrayList<User> UList = (ArrayList<User>) request.getAttribute("UserList");
%>


<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>

<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap"
	rel="stylesheet">
<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>

<script>
	

	google.charts.load('current', {
		'packages' : [ 'corechart' ]
	});
	google.charts.setOnLoadCallback(drawChart);
	google.charts.setOnLoadCallback(drawChart2);

	var countN = 0;
	var countB = 0;
	
	<%for (User u : UList) {
		if (u.getUserCode().equals("02")) {%>
			countN = countN + 1;
		<%} else if (u.getUserCode().equals("03")) {%>
			countB = countB + 1;
		<%}
			}
		%>
		
	function drawChart() {
		var data = google.visualization.arrayToDataTable([ 
			[ '회원 종류', '회원 수', {role : 'style'} ],
		[ '사업자회원', countB, 'gray' ], 
		[ '일반회원', countN, '#76A7FA' ],

		]);
		var options = {
			title : '총 회원 ' + (countN + countB) + "명"
		};
		var chart1 = new google.visualization.BarChart(document
				.getElementById('myBarChart'));
		chart1.draw(data, options);
	}


	// chart 조회 하기 async await 사용
	async function drawChart2() {
		// ajax 통신과 같은 의미
		const response = await fetch("projectCategoryList.do"); // 비동기 함수 호출
		const json = await response.json(); // 받은 값을 json 형태로 변환
		//console.log(json);
		// 맵 key 가져와서 map 함수로 [key, value] 배열 만들어서 반환
		const table = Object.keys(json).map((key) => [key, json[key]]); // 앞에 header 필요 
		console.log(table);
		//const table_temp = [
		//	[ 'Task', 'Hours per Day' ], [ 'Work', 11 ], [ 'Eat', 2 ],
		//	[ 'Commute', 2 ], [ 'Watch TV', 2 ], [ 'Sleep', 7 ] ];
		table.unshift(['카테고리명', '갯수']); // header unshift 배열 맨 앞에 넣는거
		
		var data = google.visualization.arrayToDataTable(table);


		var options = {
			is3D : true,
		};
		var chart = new google.visualization.PieChart(document
				.getElementById('myPieChart'));
		chart.draw(data, options);
	}

	
</script>

<style>

    .body{
    	font-family: 'Noto Sans KR', 'sans-serif';
    }

.container_field {
	width: 1200px;
	margin: 0 auto;
}

#pageTitle {
	margin-bottom: 60px;
	margin-top: 100px !important;
	font-size: 42px;
font-family: 'Noto Sans KR', 'sans-serif';
	font-weight: bold;
}


#tableChart{
height:800px !important;
overflow:scroll;
}

#searchText{
    width: 220px;
    margin-left: 10px;
    background-color: white;
    border: 1px solid rgba(0,0,0,.225);
    text-align: center;
   }


tbody tr:hover{
background-color:rgba(0,0,0,.1);
text-weight:bold;
}

#statusCheck{
width:100%;
text-align:right;
}

</style>

</head>

<body>
	<%@ include file="../common/menubar.jsp"%>

	<div class="container_field">
		<div id="layoutSidenav_content">
			<main>
				<div class="container-fluid px-4">
					<h1 id="pageTitle" class="mt-4">관리자 페이지</h1>
					<ol class="breadcrumb mb-4">
					</ol>

					<div class="row">
						<div class="col-lg-6">
							<div class="card mb-4">
								<div class="card-header">
									<i class="fas fa-chart-bar me-1"></i> 회원 비율
								</div>
								<div class="card-body">
									<div id="myBarChart" width="100%" height="50"></div>
								</div>
								<div class="card-footer small text-muted">실시간 조회 : 탈퇴 회원 포함</div>
							</div>
						</div>
						<div class="col-lg-6">
							<div class="card mb-4">
								<div class="card-header">
									<i class="fas fa-chart-pie me-1"></i> 펀딩 카테고리 통계
								</div>
								<div class="card-body">
									<div id="myPieChart" width="100%" height="50"></div>
								</div>
								<div class="card-footer small text-muted">실시간 조회</div>
							</div>
						</div>

					</div>
					<div class="card mb-4">
						<div class="card-header">
							<i class="fas fa-chart-area me-1"></i> 전체 회원 조회 : 
							<div class="search" style="display:inline-block;">
							 <input type="text" id="searchText" placeholder="조회내용 입력">
									
							</div>
							<div class="custom-control custom-switch" style="display:inline-block;" id="statusCheck">
							   		<input type="checkbox" class="custom-control-input" id="customSwitch1">
								    <label class="custom-control-label" for="customSwitch1"><small>탈퇴 회원 안보기</small></label>
							</div>		
						</div>
						<div class="card-body">
							<div id="tableChart" >
								<table class="table table-bordered" id="userTable">
									<thead>
										<tr>
											<th>이름</th>
											<th>이메일</th>
											<th>생년월일</th>
											<th>연락처</th>
											<th>가입일자</th>
											<th>탈퇴여부</th>
										</tr>
									<thead>
									<tbody>
										<%
											if (UList.isEmpty()) {
										%>
										<tr>
											<td colspan="6">조회된 리스트가 없습니다.</td>
										</tr>
										<%
											} else {
										%>
										<%
											for (User u : UList) {
										%>
										<tr>
											<td><%=u.getUserName()%></td>
											<td><%=u.getEmailId()%></td>
											<td><%=u.getUserSsn().substring(0,8)%>******</td>
											<td><%=u.getUserPhone()%></td>
											<td><%=u.getJoinDate()%></td>
											<td><%=u.getStatus()%></td>
										</tr>
										<%
											}
										%>
										<%
											}
										%>
									</tbody>
								</table>

							</div>
						</div>
						<div class="card-footer small text-muted">실시간 반영</div>
					</div>


				</div>
			</main>
		</div>


	</div>

</body>

<script>


$(function(){
	

	$("#searchText").on("keyup",function(){
		var value = $(this).val().toLowerCase();
		$("tbody tr").filter(function(){
			$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
		});
	});
	
	

	
	   $("#customSwitch1").on("change",function(){
		      $("tbody tr").hide();
		      if ($("#customSwitch1").is(":checked")) {
		         $("tbody tr").filter(function(){
		        	 console.log($(this).children().eq(5).text());
		            return $(this).children().eq(5).text().indexOf('N') > -1;
		         }).show(); 
		      } else {
		         $('tbody tr').show();
		      }
		   }); 
	
})	



	<%--
	
	
		$("#customSwitch1").on("change",function(){
		var value = $(this).val().toLowerCase();
		$("tbody tr").filter(function(){
			$(this).toggle($(this).children().eq(5).text().indexOf('N') > -1 ,false) 
		});
	});
	 
	
	
	
	
	
	
	$("#customSwitch1").change(function() {
			$("tbody tr").hide();
			
			 if($('#customSwitch1').is(":checked")){
				console.log($('#customSwitch1').is(":checked"));
				$("tbody tr").filter(function() {
					$(this).children().eq(5).text().indexOf('N') > -1
				
				}).show();
			}else{
				$("tbody tr").show();
			}
 
		})

		
		
			$("#customSwitch1").click(function() {
			
			 if($('#customSwitch1').is(":checked")){
				console.log($('#customSwitch1').is(":checked"));
				$("tbody tr").filter(function() {
					($(this).children().eq(5).text().indexOf('N') > -1)
				
				}).show();
			}else{
				$("tbody tr").show();
			}
 
		})
		
		
		
	--%>	
		
	
<%--
	$("#customSwitch1").toggle(function(){
		$(this).filter(function(){
			$("tbody tr").filter(function() {
				$(this).toggle($(this).children().eq(5).text().indexOf('N') > -1)
			})
			
		})
		
		})
			}
		 --%>

	

<%-- 구글 테이블 차트 나중에 다시 공부하자 
google.charts.load('current', {'packages':['table']});   
google.charts.setOnLoadCallback(drawTable2);

function drawTable2(arr) {
  var data = new google.visualization.DataTable();
  data.addColumn('string', '이름');
  data.addColumn('string', '이메일');
  data.addColumn('string', '생년월일');
  data.addColumn('string', '연락처');
  data.addColumn('string', '가입일자');
  data.addColumn('boolean', '탈퇴여부');
  data.addRows([

	  
	  
	  ]);
  var table = new google.visualization.Table(document.getElementById('tableChart'));

  table.draw(data, {showRowNumber: true, width: '100%', height: '100%'});
    
}
--%>
	
</script>
</html>