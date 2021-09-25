<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.kh.recruit.model.vo.Recruitment" %>
<%@ page import="com.kh.recruit.model.vo.RecruitCode" %>

<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>펀딩사이트 프로젝트 - 채용 공고 내용 페이지</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

    <style>
        .popover {
            max-width: 70% !important;
        }
               
        .carousel-item {
		    height: 300px;
		}
		
		.carousel-item>img {
		    width: 100%;
		    height: 100%;
		    object-fit: cover;
		}
		
		.tooltip-inner {
    		max-width: 100% !important;
		}
    </style>
    
    <script>
		
		const msg = '<%= (String)session.getAttribute("msg") %>';
		if (msg !== 'null') {
			alert(msg);
			<% session.removeAttribute("msg"); %> // msg 출력 후 제거
		}
	
	</script>
</head>

<%
	Recruitment r = (Recruitment) request.getAttribute("r");

	if (r == null) {
		session.setAttribute("msg", "공고 조회 실패");
		
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("location.href = '" + request.getContextPath() + "/views/common/errorPage.jsp';");
		script.println("</script>");
		script.close();
		
		return;
	}

	ArrayList<RecruitCode> code = (ArrayList<RecruitCode>) request.getAttribute("code");
	
	ArrayList<String> titles = (ArrayList<String>) request.getAttribute("titles");
%>

<body>
    <!-- navbar -->
    <%@ include file="../common/menubar.jsp" %>

    <!-- 채용공고 파트 소개 -->
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
                <img src="<%= request.getContextPath() %>/resources/images/recruit_img1.png" alt="img1">
            </div>
            <div class="carousel-item">
                <img src="<%= request.getContextPath() %>/resources/images/recruit_img2.png" alt="img2">
            </div>
            <div class="carousel-item">
                <img src="<%= request.getContextPath() %>/resources/images/recruit_img3.png" alt="img3">
            </div>
        </div>

        <!-- left and right controls -->
        <a href="#intro" class="carousel-control-prev" data-slide="prev">
            <span class="carousel-control-prev-icon"></span>
        </a>
        <a href="#intro" class="carousel-control-next" data-slide="next">
            <span class="carousel-control-next-icon"></span>
        </a>
    </div>

    <section class="container my-5">
        <!-- 공고 내용 -->
        <article class="container border p-5" style="position: relative;">
        	<button class="btn btn-outline-dark btn-sm <% if (loginUser == null || !loginUser.getUserCode().equals("01")) out.print("invisible"); %>" style="position: absolute; top: 5px; right: 5px;" data-toggle="modal" data-target="#recruit_remove_modal">삭제</button>
            <button class="btn btn-outline-dark btn-sm <% if (loginUser == null || !loginUser.getUserCode().equals("01")) out.print("invisible"); %>" style="position: absolute; top: 5px; right: 60px;" data-toggle="modal" data-target="#recruit_update_modal">수정</button>
        
            <div id="recruitDate" class="text-info text-right mb-3">
                <!-- 공고 종류 -->
                <span class="badge badge-info"><%= r.getTime() %></span>
                <!-- 공고 기간 -->
                <span>
                    <%= r.getDateWithDay() %>
                </span>
            </div>

            <!-- 공고명 -->
            <div id="recruitName">
                <h2><%= r.getTitle() %></h2>
                <hr>
            </div>

            <!-- 소개 -->
            <div id="recruitContent1" class="my-5">
                <%= r.getContent1().replaceAll("\n", "<br>") %>
            </div>

            <!-- 주요 업무 -->
            <div id="recruitContent2" class="my-5">
                <h6 class="mb-4 font-weight-bold">[주요 업무]</h6>
                <%= r.getContent2().replaceAll("\n", "<br>") %>
            </div>

            <!-- 자격 조건 -->
            <div id="recruitContent3" class="my-5">
                <h6 class="mb-4 font-weight-bold">[자격 조건]</h6>
                <%= r.getContent3().replaceAll("\n", "<br>") %>
            </div>

            <!-- 우대 사항 -->
            <div id="recruitContent4" class="my-5">
                <h6 class="mb-4 font-weight-bold">[우대 사항]</h6>
                <%= r.getContent4().replaceAll("\n", "<br>") %>
            </div>

            <!-- 혜택 및 복지 -->
            <div id="recruitContent5" class="my-5">
                <h6 class="mb-4 font-weight-bold">[혜택 및 복지]</h6>
                <%= r.getContent5().replaceAll("\n", "<br>") %>
            </div>

            <!-- 기타 사항 -->
            <div id="recruitContent6" class="my-5">
                <h6 class="mb-4 font-weight-bold">**기타 사항</h6>
                <%= r.getContent6().replaceAll("\n", "<br>") %>
            </div>
            
        </article>

        <div class="d-flex justify-content-center mt-4">
            <a href="<%= request.getContextPath() %>/recruitPage.do" class="btn btn-dark mx-2" style="width: 150px;">목록</a>
            <button class="btn btn-dark mx-2" style="width: 150px;" data-toggle="modal" data-target="#recruit_insert_modal">지원서 작성</button>
        </div>
    </section>

    <!-- footer -->
    <%@ include file="../common/footer.jsp" %>

    <!-- 공고 삭제 modal -->
    <div class="modal fade" id="recruit_remove_modal">
        <div class="modal-dialog modal-sm">
            <div class="modal-content">

                <div class="modal-header">
                    <h4 class="modal-title">공고 삭제 확인</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>

                <div class="modal-body">
					삭제하시겠습니까?
                 	<form id="recruit_delete_form" action="<%= request.getContextPath() %>/recruitDelete.do" method="post">
                 		<input type="hidden" class="form-control" name="recruitId" value="<%= r.getId() %>">
                 	</form>
                </div>

                <div class="modal-footer">
                    <button type="submit" form="recruit_delete_form" class="btn btn-danger">삭제하기</button>
                </div>

            </div>
        </div>
    </div>

    <!-- 공고 수정 modal -->
    <div class="modal fade" id="recruit_update_modal">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <!-- Modal header -->
                <div class="modal-header">
                    <h4 class="modal-title">공고 등록</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>

                <!-- Modal body -->
                <div class="modal-body">

                    <form id="recruit_update_form" action="<%= request.getContextPath() %>/recruitUpdate.do" method="post">

						<input type="hidden" class="form-control" name="recruitId" value="<%= r.getId() %>">

                        <!-- 공고 종류 일반 / 상시 -->
                        <label>공고 종류</label>
                        <div class="form-group">
                            <div class="custom-control custom-radio custom-control-inline">
                                <input type="radio" class="custom-control-input" id="recruitTime" name="recruitTime" value="일반 채용" 
                                <% if (r.getTime().equals("일반 채용")) out.print("checked"); %>
                                >
                                <label for="recruitTime" class="custom-control-label">일반 채용</label>
                            </div>
    
                            <div class="custom-control custom-radio custom-control-inline">
                                <input type="radio" class="custom-control-input" id="recruitTime2" name="recruitTime" value="상시 채용"
                                <% if (r.getTime().equals("상시 채용")) out.print("checked"); %>
                                >
                                <label for="recruitTime2" class="custom-control-label">상시 채용</label>
                            </div>
                        </div>

                        <!-- 공고 시작일 ~ 공고 종료일 달력 선택 -->
                        <!-- <form-group>
                                <label for="recruitStartDate">공고 시작일</label>
                                <input type="date" class="form-control" id="recruitStartDate">
                                <label for="recruitEndDate">공고 종료일</label>
                                <input type="date" class="form-control" id="recruitEndDate">
                            </form-group> -->

                        <!-- <input type="text" class="form-control" id="daterange" name="daterange"/> -->
                        <div class="form-group">
                            <label>공고기간</label>
                            <div id="daterange" class="form-control">
                                <i class="fa fa-calendar"></i>&nbsp;
                                <span></span>
                            </div>
                        </div>
                        <input form="recruit_update_form" type="hidden" id="recruitStartDate" name="recruitStartDate">
                        <input form="recruit_update_form" type="hidden" id="recruitEndDate" name="recruitEndDate">

                        <!-- 공고명 -->
                        <div class="form-group">
                            <label for="recruitUpdateName">공고명</label>
                            <input form="recruit_update_form" type="text" class="form-control" id="recruitUpdateName"
                                name="recruitName" placeholder="내용을 입력해주세요" required
                            	value="<%= r.getTitle() %>"    
                            >
                        </div>

                        <!-- 직무구분 -->
                        <!-- Custom Select Menu -->
                        <label>직무구분</label>
                        <select form="recruit_update_form" name="recruitCode" id="recruitCode" class="custom-select">
                        <% for (RecruitCode c : code) { %>
                            <option value="<%= c.getCode() %>" <% if (r.getCode().equals(c.getCode())) out.print("selected"); %>><%= c.getCode() %></option>
                        <% } %>
                        </select>

                    </form>

                    <!-- Nav pills or tabs with Dropdown -->
                    <ul class="nav nav-tabs mt-3" role="tablist">
                        <li class="nav-item">
                            <a href="#recruitContentTab1" data-toggle="tab"
                                class="text-secondary nav-link active">소개</a>
                        </li>
                        <li class="nav-item">
                            <a href="#recruitContentTab2" data-toggle="tab" class="text-secondary nav-link">주요 업무</a>
                        </li>

                        <li class="nav-item dropdown">
                            <a href="#" class="text-secondary nav-link dropdown-toggle" data-toggle="dropdown">요구 사항</a>
                            <div class="dropdown-menu">
                                <a href="#recruitContentTab3" data-toggle="tab" class="text-secondary nav-link">자격 요건</a>
                                <a href="#recruitContentTab4" data-toggle="tab" class="text-secondary nav-link">우대 사항</a>
                            </div>
                        </li>

                        <li class="nav-item dropdown">
                            <a href="#" class="text-secondary nav-link dropdown-toggle" data-toggle="dropdown">혜택 및 기타</a>
                            <div class="dropdown-menu">
                                <a href="#recruitContentTab5" data-toggle="tab" class="text-secondary nav-link">혜택 및 복지</a>
                                <a href="#recruitContentTab6" data-toggle="tab" class="text-secondary nav-link">기타 사항</a>
                            </div>
                        </li>
                    </ul>

                    <div class="tab-content mt-2">
                        <div id="recruitContentTab1" class="container tab-pane active">
                            <!-- 소개 -->
                            <div class="form-group">
                                <label for="recruitContent1">소개</label>
                                <textarea form="recruit_update_form" name="recruitContent1" id="recruitContent1"
                                    class="form-control" rows="10"><%= r.getContent1() %></textarea>
                            </div>
                        </div>

                        <div id="recruitContentTab2" class="container tab-pane fade">
                            <!-- 주요 업무 -->
                            <div class="form-group">
                                <label for="recruitContent2">주요 업무</label>
                                <textarea form="recruit_update_form" name="recruitContent2" id="recruitContent2"
                                    class="form-control" rows="10"><%= r.getContent2() %></textarea>
                            </div>
                        </div>

                        <div id="recruitContentTab3" class="container tab-pane fade">
                            <!-- 자격 요건 -->
                            <div class="form-group">
                                <label for="recruitContent3">자격 요건</label>
                                <textarea form="recruit_update_form" name="recruitContent3" id="recruitContent3"
                                    class="form-control" rows="10"><%= r.getContent3() %></textarea>
                            </div>
                        </div>

                        <div id="recruitContentTab4" class="container tab-pane fade">
                            <!-- 우대 사항 -->
                            <div class="form-group">
                                <label for="recruitContent4">우대 사항</label>
                                <textarea form="recruit_update_form" name="recruitContent4" id="recruitContent4"
                                    class="form-control" rows="10"><%= r.getContent4() %></textarea>
                            </div>
                        </div>

                        <div id="recruitContentTab5" class="container tab-pane fade">
                            <!-- 혜택 및 복지 -->
                            <div class="form-group">
                                <label for="recruitContent5">혜택 및 복지</label>
                                <textarea form="recruit_update_form" name="recruitContent5" id="recruitContent5"
                                    class="form-control" rows="10"><%= r.getContent5() %></textarea>
                            </div>
                        </div>

                        <div id="recruitContentTab6" class="container tab-pane fade">
                            <!-- 기타 사항 -->
                            <div class="form-group">
                                <label for="recruitContent6">기타 사항</label>
                                <textarea form="recruit_update_form" name="recruitContent6" id="recruitContent6"
                                    class="form-control" rows="10"><%= r.getContent6() %></textarea>
                            </div>
                        </div>

                        <div class="toast" style="position: absolute; bottom: 0; right: 0;">
                            <div class="toast-header">
								내용이 비어 있습니다. 채워 넣어주세요
                            </div>
                            <div class="toast-body">
                            </div>
                        </div>
                    </div>

                </div>

                <!-- Modal footer -->
                <div class="modal-footer">
                    <button id="recruitSubmitBtn" type="submit" form="recruit_update_form" class="btn btn-dark mx-auto">수정하기</button>
                </div>
            </div>
        </div>
    </div>

    <!-- Date Range Picker -->
    <script>
        $(function () {
            const startDate = moment('<%= r.getStartDate() %>', 'YYYY-MM-DD'); // 시작일
            const endDate = moment('<%= r.getEndDate() %>', 'YYYY-MM-DD'); // 종료일 -> 현재 시간 + 30일
            $('#recruitStartDate').val(startDate.format('YYYY-MM-DD'));
            $('#recruitEndDate').val(endDate.format('YYYY-MM-DD'));

            $('#daterange span').html(startDate.format('YYYY-MM-DD') + ' ~ ' + endDate.format('YYYY-MM-DD'));

            $('#daterange').daterangepicker({
                //opens: 'right',
                //drops: 'up',
                startDate, // 시작일 설정
                endDate // 종료일 설정
            }, function (start, end) {
                //console.log("A new date selection was made: " + start.format('YYYY-MM-DD') + ' to ' + end.format('YYYY-MM-DD'));
                const startDate = start.format('YYYY-MM-DD');
                const endDate = end.format('YYYY-MM-DD');
                $('#recruitStartDate').val(startDate);
                $('#recruitEndDate').val(endDate);

                $('#daterange span').html(start.format('YYYY-MM-DD') + ' ~ ' + end.format(
                    'YYYY-MM-DD'));
            });

            $('#daterange').on('apply.daterangepicker', function(ev, picker) {
                //console.log(picker.startDate.format('YYYY-MM-DD'));
                //console.log(picker.endDate.format('YYYY-MM-DD'));
                const startDate = picker.startDate.format('YYYY-MM-DD');
                const endDate = picker.endDate.format('YYYY-MM-DD');
                $('#recruitStartDate').val(startDate);
                $('#recruitEndDate').val(endDate);
            });
        });
    </script>

    <!-- form content check -->
    <!-- toast 사용 -->
    <script>
        $(function() {
            $('#recruit_update_form').on("submit", function(e) {

                let check = true;
                let content = '';
                $('.tab-content').find('textarea').each(function(index, item){
                    if ($(item).val() === '') {
                        //console.log($(item).parent().children('label').html());
                        content += $(item).parent().children('label').text() + '<br>';
                        check = false;
                    }
                });

                if (!check) {
                    $('.toast .toast-body').html(content);

                    $('.toast').toast({delay: 2000});
                    $('.toast').toast('show');
                }
                
                return check;
            })
        });
    </script>

    <!-- 지원서 작성하기 modal -->
    <div class="modal fade" id="recruit_insert_modal">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <!-- Modal Header -->
                <div class="modal-header">
                    <h4 class="modal-title">지원서 작성하기</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>
                
                <!-- Modal Body -->
                <div class="modal-body">
                    <form id="recruit_insert_form" class="was-validated" name="recruit_insert_form" action="recruitMemberInsert.do" method="POST" enctype="multipart/form-data">
                        
                        <input type="hidden" form="recruit_insert_form" class="form-control" name="recruitId" value="<%= r.getId() %>">
                        
                        <!-- 직무구분 dropdowns custom select -->
                        <div class="form-group">
                            <label for="recruitName">지원 포지션명</label>
                            <select name="recruitName" id="recruitName" class="custom-select">
                            <% for (String title : titles) { %>
                                <option value="<%= title %>" <% if (title.equals(r.getTitle())) out.print("selected"); %>><%= title %></option>
                            <% } %>
                            </select>
                        </div>

                        <!-- 성명 -->
                        <div class="form-group">
                            <label for="recruitMemberName">성명</label>
                            <input type="text" class="form-control" id="recruitMemberName" name="recruitMemberName" placeholder="이름을 입력해주세요" pattern="^[가-힣]+$" required>
                            <div class="invalid-feedback">한글 이름으로 입력해주시길 바랍니다.</div>
                        </div>

                        <!-- 연락처 -->
                        <div class="form-group">
                            <label for="recruitMemberPhone">연락처</label>
                            <input type="tel" class="form-control" id="recruitMemberPhone" name="recruitMemberPhone" placeholder="연락처를 입력해주세요" required>
                        </div>

                        <!-- 메일주소 -->
                        <div class="form-group">
                            <label for="recruitMemberEmail">이메일</label>
                            <input type="email" class="form-control" id="recruitMemberEmail" name="recruitMemberEmail" placeholder="이메일을 입력해주세요" aria-describedby="emailHelpBlock" required>
                            <div id="emailHelpBlock" class="small text-info"> 지원서 수정 및 발송을 위해 사용되는 이메일입니다.</div>
                        </div>

                        <!-- 학력사항 -->
                        <div class="form-group">
                            <label id="educationLabel" data-toggle="tooltip" for="recruitMemberEducation">학력사항</label>
                            <input type="text" class="form-control" id="recruitMemberEducation" name="recruitMemberEducation" placeholder="학력사항을 입력해주세요" required>
                        </div>

                        <!-- 경력사항 -->
                        <div class="form-group">
                            <label id="careerLabel" for="recruitMemberCareer" data-toggle="tooltip">경력사항</label>
                            <input type="text" class="form-control" id="recruitMemberCareer" name="recruitMemberCareer" placeholder="경력사항을 입력해주세요" required>
                        </div>

                        <!-- 이력서 및 경력기술서 / 포트폴리오 파일 올리는걸로 처리 -->
                        <div class="form-group">
                            <label id="portfolioLabel" data-toggle="tooltip" for="recruitPortfolio">이력서 및 경력기술서 / 포트폴리오 (선택)</label>
                            <div class="custom-file">
                                <input type="file" role="button" class="custom-file-input" id="recruitPortfolio" name="recruitPortfolio">
                                <label for="recruitPortfolio" class="custom-file-label" data-browse="업로드">파일을 올려주세요</label>
                            </div>
                        </div>
                        <!-- <div class="form-group">
                            <label for="recruitPortfolio">이력서 및 경력기술서 / 포트폴리오</label>
                            <input id="recruitPortfolio" type="file" class="form-control-file border">
                        </div> -->
                        
                        <!-- 개인정보 수집 이용 동의 체크박스 -->
                        <small> 내용을 확인하신 후 개인정보 수집 및 이용에 동의해주시기 바랍니다. </small> &nbsp;
                        
                        <!-- 개인정보 수집 및 이용수칙 내용 -->
                        <a role="button" id="privacy_popover" class="text-info small" data-toggle="popover">(개인정보 수집 및 이용수칙 내용 확인)</a>
                        <div id="privacy_popover_content" class="d-none popover">
                            &lt;개인정보 수집 및 이용&gt;<br>
                            1. 수집하는 개인정보 항목<br>
                            ​성명, 국적, 주소, 보훈 및 장애인 대상여부, 전화번호, 휴대전화번호, 학력, 성적, 병역, 경력, 해외 체류경험 및 연수활동,<br> 사회활동, 어학 및 기타자격, 수상경력, 취미, 특기, 자기소개<br>
                            <br>
                            2. 수집 및 이용 목적<br>
                            ​채용전형의 진행, 진행단계별 결과 등 채용정보 안내 및 인재풀 구성<br>
                            <br>
                            3. 개인정보의 보유 및 이용 기간<br>
                            ​지원서상에 작성하신 개인정보는 회사의 인재채용을 위한 인재풀로 활용될 예정으로 채용전형 결과 통보일로부터 2년까지 보관됩니다.<br>
                            지원자께서 삭제를 요청하실 경우 해당 개인정보는 삭제됩니다.<br>
                            <br>
                            &lt;민감정보 수집 및 이용&gt;<br>
                            1. 수집하는 민감정보 항목<br>
                            ​보훈 및 장애인 대상여부, 학력, 성적, 병역, 경력, 해외 체류경험 및 연수활동, 사회활동, 어학 및 기타자격, 수상경력, 취미, 특기, 자기소개<br>
                            <br>
                            ​2. 수집 및 이용 목적<br>
                            ​채용전형의 진행, 진행단계별 결과 등 채용정보 안내 및 인재풀 구성<br>
                            <br>
                            ​3. 민감정보의 보유 및 이용 기간<br>
                            ​지원서상에 작성하신 민감정보는 회사의 인재채용을 위한 인재풀로 활용될 예정으로 채용전형 결과 통보일로부터 2년까지 보관됩니다.<br>
                            지원자께서 삭제를 요청하실 경우 해당 민감정보는 삭제됩니다.<br>
                        </div>

                        <div class="custom-control custom-checkbox border-bottom mb-2">
                            <input type="checkbox" class="custom-control-input" id="customCheck" required>
                            <label for="customCheck" class="custom-control-label small">개인정보 수집 이용 동의</label>
                        </div>

                        <!-- 내 자신에게 보내기 체크박스 -->
                        <div class="custom-control custom-checkbox">
                            <input type="checkbox" class="custom-control-input" id="customCheck2" name="sendMyResume">
                            <label for="customCheck2" class="custom-control-label small">내 자신에게 복사본 전송하기</label>
                        </div>
                    </form>
                </div>
                
                <!-- Modal Footer -->
                <div class="modal-footer">

                    <!-- 지원하기 버튼 -->
                    <button form="recruit_insert_form" type="submit" class="btn btn-dark">지원하기</button>
                </div>
            </div>
        </div>
    </div>
    
     
    <!-- file upload 파일명 입력 처리 -->
    <script>
    	$(".custom-file-input").on("change", function() {
    		const fileName = $(this).val().split("\\").pop();
    		$(this).siblings(".custom-file-label").addClass("selected").html(fileName);
    	});
    </script>

    <script>
        $(function() {
		    <!-- 개인정보 수집 및 이용수칙 내용 popover -->
        	//$('[data-toggle="popover"]').popover();
            $('#privacy_popover').popover({
                html: true,
                placement: 'top',
                trigger: 'hover',
                title: '개인정보 수집 및 이용수칙',
                content: $('#privacy_popover_content').html()
            });
    	
            <!-- 경력사항, 이력사항 tooltip 처리 -->
            const delay = {show: 100, hide:1000};

            $('#educationLabel').tooltip({
            	placement: "right",
            	title: "예시) OO학교 OO학과 학사 졸업",
            	delay,
            });
            $('#careerLabel').tooltip({
            	placement: "right",
            	title: "예시) OO컴퍼니/OO팀, ㅁㅁ컴퍼니/ㅁㅁ팀 (최근 근무순)",
            	delay,
            });
            $('#portfolioLabel').tooltip({
            	placement: "right",
            	html: true,
            	title: "(선택/자유양식)<br> 이력서 또는 경력기술서를 첨부해주시기 바랍니다.",
            	delay,
            });
        });
    </script>
</body>

</html>