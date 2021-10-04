<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="com.kh.event.model.vo.Event"%>
<%@ page import="com.kh.common.model.vo.PageInfo"%>
<%@ page import="java.util.ArrayList"%>

<%@ page import="java.sql.Date"%>
<%@ page import="java.time.*"%>

<%
	
	String contextPath = request.getContextPath();
	ArrayList<Event> eList = (ArrayList<Event>) request.getAttribute("eList");
	PageInfo pi = (PageInfo) request.getAttribute("pi");
	
	int listCount = pi.getListCount();
	int currentPage = pi.getCurrentPage();
	int maxPage = pi.getMaxPage();
	int startPage = pi.getStartPage();
	int endPage = pi.getEndPage();
	
	LocalDate currentDate = LocalDate.now();	
	Date today = Date.valueOf(LocalDate.of(currentDate.getYear(),currentDate.getMonth(),currentDate.getDayOfMonth()));

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이벤트 페이지</title>

<link
	href="https://fonts.googleapis.com/css2?family=Black+Han+Sans&family=Jua&fa
            mily=Nanum+Gothic&family=Roboto&display=swap"
	rel="stylesheet" />


</head>
<style>
.box {
	width: 100%;
	margin: 0 auto;
}

#pageTitle {
	font-size: 48px;
	text-align: center;
	font-family: 'Roboto', sans-serif;
	font-weight: bold;
}

.pageHead {
	width: 100%;
	height: 250px;
	padding-top: 100px;
	margin-bottom: 50px;
	position: relative;
	z-index: 1;
}

.pageHead::after {
	width: 100%;
	height: 250px;
	content: "";
	background:
		url('<%=request.getContextPath()%>/resources/images/eventBannerImg.jpg');
	opacity: 0.5;
	position: absolute;
	top: 0;
	left: 0;
	z-index: -1;
}

.event-form {
	justify-content: center;
	align-items: center;
	width: 900px;
	margin: 0 auto;
	padding-bottom:200px;
}

.info {
	width: 900px;
	height: 170px;
	border-bottom: solid 0.2px rgb(0, 0, 0, .2);
	margin-bottom: 20px;
	padding-bottom: 20px;
}

.title {
	width: 700px;
	height: 120px;
	font-size: 20px;
	color: #1d2129;
	font-family: 'Roboto', sans-serif;
	font-weight: 600;
	font-size: 20px;
	float: left;
}

.eStatus{
	float:left;
	width:700px;
	height:20px;
	margin-bottom:10px;
	color:rgb(0,0,0,.7);
	}

#img {
	float:left;
	width: 200px;
	height: 120px;
	overflow:hidden;
	text-align:right;
}

#thumbnail{
    width:100%;
    height:initial;
    object-fit:cover;
    margin-top:-15%;
}



#newEventBtn {
	text-align: right;
	margin-bottom: 60px;
}

#newEventBtn input {
	width: 160px;
	background: none;
	border: none;
	font-size: 20px;
	text-decoration: underline;
	text-underline-position: under;
	font-style: italic;
	font-weight: bold;
	color: rgb(0, 0, 0, .7);
}

#newEventBtn input:hover {
	text-decoration: underline;
	font-style: normal;
}

.info:hover{
    cursor:pointer;
}

.info:hover > .title{
	text-decoration: underline;
}

</style>

<body>


	<%@ include file="../common/menubar.jsp"%>



	<div class='box'>

		<div class="pageHead">
			<h1 id="pageTitle">EVENT</h1>
		</div>

		<div class="event-form">

			<%
				if (loginUser != null)
				if (loginUser.getUserCode().equals("01")) {
			%>
			<div id="newEventBtn">
				<input type="button" value="새 이벤트 등록" data-toggle="modal"
					data-target="#new_event_modal">
			</div>
			<%
				}
			%>

			<%
				if (!eList.isEmpty()) {
				for (Event e : eList) {
			%>
			<div class="info">
				
				<div class=eStatus>
				
				<%
					if ((!today.before(e.getStartDate()) || e.getStartDate().equals(today)) && ( !today.after(e.getEndDate())
						|| e.getEndDate().equals(today))) {
				%>
				<b style="color:#4462EE; font-szie:13px; font-family: 'Roboto', sans-serif">[진행중]</b>
				<%
					} else {
				%>
				<b style="color:#696F75; font-szie:13px; font-family: 'Roboto', sans-serif">[시작 예정 이거나 종료 된 이벤트입니다]</b>
				<%
					}
				%>
				</div>				
				<div class="title"><%=e.getEName()%></div>				
				<div id="img">
				<img id="thumbnail" alt="이미지로딩실패" src= "<%=request.getContextPath()%>/resources/upfiles/<%=e.getEContent()%>">
				</div>
				
				<input type="hidden" id="eno" name="eno" value=<%=e.getENo() %>>			
	 		</div>
			<%
				}
			} else {
			%>
			<div class="info">
				
				<div class="title">현재 진행 중인 이벤트가 없습니다.</div>
				</div>
			</div>
			<%
			}
			%>
			
			
				<div class="pagingArea" align="center">
			<!-- 이전페이지로(<) -->
			<%if(currentPage == 1){ %>
			<button disabled> &lt; </button>
			<%}else{ %>
			<button onclick="location.href='<%= contextPath %>/eList.do?currentPage=<%= currentPage-1 %>'"> &lt; </button>
			<%} %>
			
			<!-- 페이지 목록 -->
			<%for(int p=startPage; p<=endPage; p++){ %>
				
				<%if(p == currentPage){ %>
				<button disabled> <%= p %> </button>
				<%}else{ %>
				<button onclick="location.href='<%=contextPath %>/eList.do?currentPage=<%= p %>'"> <%= p %> </button>
				<%} %>
				
			<%} %>
			
			<!-- 다음페이지로(>) -->
			<%if(currentPage == maxPage){ %>
			<button disabled> &gt; </button>
			<%}else { %>
			<button onclick="location.href='<%= contextPath %>/eList.do?currentPage=<%= currentPage+1 %>'"> &gt; </button>
			<%} %>
		
			<!-- 맨 끝으로 (>>) -->
			<button onclick="location.href='<%=contextPath%>/eList.do?currentPage=<%=maxPage%>'"> &gt;&gt; </button>
		
		</div>
		</div>
		
	
	<script>
	
		$(function(){
		
			$(".info").click(function(){
				
				var eno = $(this).children("#eno").val();
				if(eno != null || eno==''){
					location.href="<%=request.getContextPath()%>/eDetail.do?eno="+eno;
				}
			})
	
		})
		</script>

	<%@ include file="../common/footer.jsp"%>

	<!-- 이벤트 등록 modal -->
	<div class="modal fade" id="new_event_modal">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">
				<!-- Modal header -->
				<div class="modal-header">
					<h4 class="modal-title">이벤트 등록</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				<!-- Modal body -->
				<div class="modal-body">
					<form method="post" id="event_form"
						action="<%=request.getContextPath()%>/eventInsert.do" enctype="multipart/form-data"  >
						<div class="form-group">
							<label>이벤트 기간</label>
							<div id="daterange" class="form-control">
								<i class="fa fa-calendar"></i>&nbsp; <span></span>
							</div>
						</div>
						<input form="event_form" type="hidden" id="eventStartDate"
							name="eventStartDate" required>  
						<input form="event_form"
							type="hidden" id="eventEndDate" name="eventEndDate" required>

						<div class="form-group">
							<label for="eventName">이벤트 명</label> <input form="event_form"
								type="text" class="form-control" id="eventName" name="eventName"
								placeholder="내용을 입력해주세요" required>
						</div>


						<div class="form-group">
							<label for="eventio">컨텐츠 이미지</label>
							<div class="custom-file">
								<input type="file" class="custom-file-input"
									id="eventio" name="eventio" required>
									 <label for="eventio"
									class="custom-file-label" data-browse="업로드">파일을 올려주세요</label>
							</div>
						</div>

					</form>
				</div>
				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="submit" form="event_form"
						class="btn btn-dark mx-auto">이벤트 등록하기</button>
				</div>
			</div>
		</div>
	</div>
</body>
<script>

$(".custom-file-input").on("change", function() {
	const fileName = $(this).val().split("\\").pop();
	$(this).siblings(".custom-file-label").addClass("selected").html(fileName);
});

</script>
<!-- Date Range Picker -->
<script>
        $(function () {
            const startDate = moment(); // 시작일 //moment() : 날짜 관련함수 
            const endDate = moment().add(30, 'day'); // 종료일 -> 현재 시간 + 30일
            $('#eventStartDate').val(startDate.format('YYYY-MM-DD'));
            $('#eventEndDate').val(endDate.format('YYYY-MM-DD'));

            $('#daterange span').html(startDate.format('YYYY-MM-DD') + ' ~ ' + endDate.format('YYYY-MM-DD'));

            $('#daterange').daterangepicker({
                //opens: 'right',
                //drops: 'up',
                startDate,  
                endDate 
            }, function (start, end) {
                //console.log("A new date selection was made: " + start.format('YYYY-MM-DD') + ' to ' + end.format('YYYY-MM-DD'));
                const startDate = start.format('YYYY-MM-DD');
                const endDate = end.format('YYYY-MM-DD');
                $('#eventStartDate').val(startDate);
                $('#eventEndDate').val(endDate);

                $('#daterange span').html(start.format('YYYY-MM-DD') + ' ~ ' + end.format(
                    'YYYY-MM-DD'));
            });

            $('#daterange').on('apply.daterangepicker', function(ev, picker) {
                //console.log(picker.startDate.format('YYYY-MM-DD'));
                //console.log(picker.endDate.format('YYYY-MM-DD'));
                const startDate = picker.startDate.format('YYYY-MM-DD');
                const endDate = picker.endDate.format('YYYY-MM-DD');
                $('#eventStartDate').val(startDate);
                $('#eventEndDate').val(endDate);
            });
        });
        
    </script>
</html>