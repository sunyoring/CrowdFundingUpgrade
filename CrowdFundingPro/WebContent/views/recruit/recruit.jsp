<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.kh.recruit.model.vo.Recruitment" %>
<%@ page import="com.kh.recruit.model.vo.RecruitCode" %>
<%@ page import="com.kh.common.model.vo.PageInfo" %>

<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>펀딩사이트 프로젝트 - 채용 공고 페이지</title>

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

    <!-- bootstrap-steps -->
    <!-- <link rel="stylesheet" href="../css/bootstrap-steps.min.css"> -->
    <!-- cdn -->
    <!-- <link  href="https://cdn.jsdelivr.net/npm/bootstrap-steps@%5E1.0/dist/bootstrap-steps.min.css" rel="stylesheet"> -->

    <style>
    
    	.body{
    	  	font-family: 'Noto Sans KR', 'sans-serif';
    	
    	}
    
        .jumbotron {
            background: #fff;
        }

        #jumbotron-head>h2 {
            font-weight: bold;
        }

        #jumbotron-body li {
            font-size: 0.85rem;
            font-weight: bold;
        }

        #recruit_code button {
            margin: 0.1rem;
            border-radius: 5rem;
        }

        #recruit_table {
            position: relative;
        }

        #recruit_create_btn {
            position: absolute;
            right: 0;
            bottom: 0;
            visibility: show;
        }
        
        #recruitmember_update_btn {
            position: absolute;
            left: 0;
            bottom: 0;
        }

        .tooltip-inner {
    		max-width: 100% !important;
		}
        
        .carousel-item {
		    height: 300px;
		}
		
		.carousel-item>img {
		    width: 100%;
		    height: 100%;
		    object-fit: cover;
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

    <!-- 채용 진행과정 일단 jumbotron과 이미지로 대체-->
    <div class="container-lg mt-5">
        <div class="jumbotron">
            <h5>PROCESS</h5>
            <br>
            <div class="row mb-5">
                <div id="jumbotron-head" class="col-md-6">
                    <h2>더 나은 세상을 함께 <br> 만들어갈 당신을 기다립니다</h2>
                </div>
                <div id="jumbotron-body" class="col-md-6">
                    <ul>
                        <li>상황에 따라 1,2차 면접이 1day 면접으로 진행될 수 있습니다.</li>
                        <li>개발 직군의 경우 '코딩 테스트'를 진행합니다.</li>
                        <li>직무에 따라 면접에 과제 발표가 포함될 수 있습니다.</li>
                        <li>2차 면접 합격 시 평판 조회가 진행될 수 있습니다.</li>
                        <li>채용 완료 시 공고가 조기 마감될 수 있습니다.</li>
                    </ul>
                </div>
            </div>
            <div class="d-flex justify-content-center">
            	<img class="rounded" src="<%= request.getContextPath() %>/resources/images/recruit_process.png" alt="recruit_process">
            </div>
        </div>

        <!-- 채용 진행과정 step 구현 시도 -->
        <article>
            <!-- <ul class="steps">
                <li class="step">
                    <div class="step-content">
                        <span class="step-circle">1</span>
                        <span class="step-text">Step 1</span>
                    </div>
                </li>
                <li class="step">
                    <div class="step-content">
                        <span class="step-circle">1</span>
                        <span class="step-text">Step 1</span>
                    </div>
                </li>
                <li class="step">
                    <div class="step-content">
                        <span class="step-circle">1</span>
                        <span class="step-text">Step 1</span>
                    </div>
                </li>
            </ul> -->
        </article>
    </div>

    <section class="container mt-5">
        <!-- 직무 구분 카테고리 button groups badges -->
        <div id="recruit_code" class="btn-group-vertical btn-group-toggle" role="group" data-toggle="buttons">
        	<%-- ajax 처리 시 setCode --%>
        </div>

        <!-- 직무 검색 input groups -->
        <div class="input-group mt-5">
            <input id="recruit_search" type="text" class="form-control" placeholder="직무 검색하기">
            <div class="input-group-append">
                <button class="btn btn-secondary" onclick="getData();">
                    <i class="fas fa-search fa-lg"></i>
                </button>
            </div>
        </div>

        <!-- 공고 리스트 table -->
        <article id="recruit_table">
            <table class="table table-hover mt-5">
                <tbody>
                	<%-- ajax 처리시 setTable--%>
                </tbody>
            </table>

            <!--  pagination -->
            <ul class="pagination justify-content-center">
            	<%-- ajax 처리시 setPageInfo --%>
            </ul>

			<button type="button" id="recruitmember_update_btn" class="btn btn-outline-dark" data-toggle="modal"
                data-target="#recruitmember_update_modal">공고 지원 수정</button>

            <button type="button" id="recruit_create_btn" class="btn btn-dark <% if (loginUser == null || !loginUser.getUserCode().equals("01")) out.print("invisible"); %>" data-toggle="modal"
                data-target="#recruit_create_modal">공고 등록</button>
        </article>
    </section>

   	<!-- footer -->
   	<%@ include file="../common/footer.jsp" %>

	<!-- form 태그 submit시 페이지 이동 막기 위한 iframe -->
	<iframe id="iframe1" name="iframe1" style="display:none;"></iframe>

    <!-- 공고 지원서 수정 modal -->
    <div class="modal fade" id="recruitmember_update_modal">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">

                <!-- header -->
                <div class="modal-header">
                    <h4 class="modal-title">공고 지원서 수정</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>

                <!-- body -->
                <div class="modal-body">
                    <form id="recruitmember_update_form" action="recruitMemberUpdate.do" method="POST" enctype="multipart/form-data" target="iframe1">
                        
                        <!-- 직원 공고 선택 dropdowns custom select -->
                        <div class="form-group">
                            <label for="recruitId">지원 공고 선택</label>
                            <select name="recruitId" id="recruitId" class="custom-select">
                                <%-- ajax 처리시 setModalIdAndTitle --%>
                            </select>
                        </div>

                        <!-- 공고 조회를 위한 이메일 입력 및 비밀번호 발송 -->
                        <div class="form-group">
                            <label for="recruitMemberEmail">공고 지원서 조회</label>
                            <div class="input-group">
                                <input type="text" id="recruitCheckEmail" class="form-control form-control-sm" placeholder="email 입력해주세요">
                                <div class="input-group-append">
                                    <button onclick="sendRecruitMemberPassword();" class="btn btn-outline-info btn-sm" type="button">비밀번호 발송</button>
                                </div>
                            </div>
                        </div>

                        <!-- 공고 조회를 위한 비밀번호 입력 및 조회 -->
                        <div class="form-group">
                            <div class="input-group">
                                <input type="text" id="recruitMemberPassword" class="form-control form-control-sm" placeholder="발급 받은 비밀번호를 입력해주세요">
                                <div class="input-group-append">
                                    <button onclick="searchRecruitMember();" class="btn btn-outline-dark btn-sm" type="button">지원서 조회</button>
                                </div>
                            </div>
                        </div>

                        <fieldset style="border: 1px dotted gray; padding: 20px;">
                        	<input type="hidden" id="recruitAttachmentNo" name="recruitAttachmentNo">
                        	<input type="hidden" id="recruitMemberId" name="recruitMemberId">
                        
                            <div class="form-row">
                                <!-- 성명 -->
                                <div class="form-group col">
                                    <label for="recruitMemberName">성명</label>
                                    <input type="text" class="form-control" id="recruitMemberName" name="recruitMemberName" placeholder="이름을 입력해주세요">
                                </div>
        
                                <!-- 연락처 -->
                                <div class="form-group col">
                                    <label for="recruitMemberPhone">연락처</label>
                                    <input type="tel" class="form-control" id="recruitMemberPhone" name="recruitMemberPhone" placeholder="연락처를 입력해주세요">
                                </div>
                            </div>

                            <!-- 메일주소 -->
                            <div class="form-group">
                                <label for="recruitMemberEmail">이메일</label>
                                <input type="email" class="form-control" id="recruitMemberEmail" name="recruitMemberEmail" placeholder="이메일을 입력해주세요">
                            </div>

                            <!-- 학력사항 -->
                            <div class="form-group">
                                <label id="educationLabel" data-toggle="tooltip" for="recruitMemberEducation">학력사항</label>
                                <input type="text" class="form-control" id="recruitMemberEducation" name="recruitMemberEducation" placeholder="학력사항을 입력해주세요">
                            </div>

                            <!-- 경력사항 -->
                            <div class="form-group">
                                <label id="careerLabel" for="recruitMemberCareer" data-toggle="tooltip">경력사항</label>
                                <input type="text" class="form-control" id="recruitMemberCareer" name="recruitMemberCareer" placeholder="경력사항을 입력해주세요">
                            </div>

                            <!-- 이력서 및 경력기술서 / 포트폴리오 파일 올리는걸로 처리 -->
                            <div class="form-group">
                                <label id="portfolioLabel" data-toggle="tooltip" for="recruitPortfolio">이력서 및 경력기술서 / 포트폴리오</label>
                                <div class="custom-file">
                                    <input type="file" role="button" class="custom-file-input" id="recruitPortfolio" name="recruitPortfolio">
                                    <label for="recruitPortfolio" class="custom-file-label" data-browse="업로드">파일을 올려주세요</label>
                                </div>
                            </div>
                            <!-- <div class="form-group">
                                <label for="recruitPortfolio">이력서 및 경력기술서 / 포트폴리오</label>
                                <input id="recruitPortfolio" type="file" class="form-control-file border">
                            </div> -->

                            <!-- 내 자신에게 보내기 체크박스 -->
                            <div class="custom-control custom-checkbox">
                                <input type="checkbox" class="custom-control-input" id="customCheck2" name="sendMyResume" checked>
                                <label for="customCheck2" class="custom-control-label small">내 자신에게 수정된 지원서 전송하기</label>
                            </div>
                        </fieldset>
                    </form>
                </div>
                
                <!-- Modal Footer -->
                <div class="modal-footer">
                    <!-- 수정하기 버튼 -->
                    <button onclick="closeUpdateModal();" form="recruitmember_update_form" id="updateRecruitMemberBtn" type="submit" class="btn btn-dark disabled" disabled>수정하기</button>
                </div>
            </div>
        </div>
    </div>
    
    <!-- 공고 지원서 메일 발송 및 조회 스크립트 -->
    <script>   
    	function closeUpdateModal() {
    		$('#recruitmember_update_modal').modal('hide')
    	}
    
    	async function sendRecruitMemberPassword() {
    		const rid = document.querySelector("#recruitId").value;
    		const email = document.querySelector("#recruitCheckEmail").value;
    		
    		//console.log(rid, email);
    		
    		try {
    			const response = await fetch('recruitMemberPassword.do', {
    				method: "post",
    				body : JSON.stringify({
    					"rid" : rid,
    					"email" : email,
    				})
    			});
    			
   				const result = await response.text();
   				//console.log(result);
   				
   				if (result === "fail") {
   					showNoFoundRecruitAlert();
   				} else {
   					showFoundRecruitAlert();
   				}
    			
    		} catch (error) {
    			console.log(error);
    		}
    	}
    	
    	function showNoFoundRecruitAlert() {
    		$('#recruitmember_update_form').prepend(`
   	            <div id="noFoundRecruitAlert" class="alert alert-warning alert-dismissible fade show" role="alert" style="position: absolute; top: -65px; left: 12px;">
   	                <strong>공고 지원서가 없습니다!</strong> 
   	                <p style="font-size: 15px;">
   	                    공고에 해당 이메일로 등록된 지원서가 존재하지 않습니다. <br> 다시 확인해주세요
   	                </p>
   	                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
   	                  <span aria-hidden="true">&times;</span>
   	                </button>
   	            </div>`);
			setTimeout(function() {
	                $('#noFoundRecruitAlert').alert('close');
	        }, 3000);
    	}
    	
    	function showFoundRecruitAlert() {
    		$('#recruitmember_update_form').prepend(`
   	            <div id="foundRecruitAlert" class="alert alert-success alert-dismissible fade show" role="alert" style="position: absolute; top: -65px; left: 12px;">
   	                <strong>이메일로 비밀번호를 발송하였습니다.</strong> 
   	                <p style="font-size: 15px;">
						공고 지원서에 등록한 이메일로 비밀번호를 발송하였습니다. <br> 확인하여 비밀번호를 입력해주세요
   	                </p>
   	                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
   	                  <span aria-hidden="true">&times;</span>
   	                </button>
   	            </div>`);
   			setTimeout(function() {
   	                $('#foundRecruitAlert').alert('close');
   	        }, 4000);
    	}
    	
    	async function searchRecruitMember() {
    		const rid = document.querySelector("#recruitId").value;
    		const email = document.querySelector("#recruitCheckEmail").value;
    		const password = document.querySelector('#recruitMemberPassword').value;
    		
    		try {
    			
    			const response = await fetch('recruitMemberList.do', {
    				method: "post",
    				body : JSON.stringify({
    					"rid" : rid,
    					"email" : email,
    					"password" : password,
    				})
    			});
    			
    			const result = await response.text();
    			
    			//console.log(result);
    			if (result === 'search fail') {
    				showNoFoundRecruitAlert();
    			} else if(result === 'password fail') {
    				showPasswordWrongAlert();
    			} else {    				
    				const jsonResult = JSON.parse(result);
    				
  					console.log(jsonResult);
  					
  					const { at, rm } = jsonResult;
    				
    				const rmName = document.querySelector('#recruitMemberName');
    				const rmPhone = document.querySelector('#recruitMemberPhone');
    				const rmEmail = document.querySelector('#recruitMemberEmail');
    				const rmEducation = document.querySelector('#recruitMemberEducation');
    				const rmCareer = document.querySelector('#recruitMemberCareer');
    				const rmAttachment = document.querySelector('.custom-file-label');
    				const rmAttachmentNo = document.querySelector('#recruitAttachmentNo');
    				const rmId = document.querySelector('#recruitMemberId');
    				
    				rmName.value = rm.name;
    				rmPhone.value = rm.phone;
    				rmEmail.value = rm.email;
    				rmEducation.value = rm.education;
    				rmCareer.value = rm.career;
    				rmId.value = rm.id;
    				
    				rmAttachment.classList.add('selected');
    				rmAttachment.innerHTML = at.originName;
    				rmAttachmentNo.value = at.fileNo;
    				
    				document.querySelector('#updateRecruitMemberBtn').classList.remove('disabled');
    				document.querySelector('#updateRecruitMemberBtn').disabled = false;
    			}
    			
    		} catch (error) {
    			console.log(error);
    		}
    	}
    	
    	function showPasswordWrongAlert() {
    		$('#recruitmember_update_form').prepend(`
   	            <div id="passwordWrongAlert" class="alert alert-danger alert-dismissible fade show" role="alert" style="position: absolute; top: -60px; left: 22px;">
   	                <strong>비밀번호가 잘못되었습니다!</strong> 
   	                <p style="font-size: 15px;">
   	                    	입력한 비밀번호가 잘못되었습니다! 다시 확인해주세요
   	                </p>
   	                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
   	                  <span aria-hidden="true">&times;</span>
   	                </button>
   	            </div>`);

            setTimeout(function() {
                $('#passwordWrongAlert').alert('close');
            }, 3000);
    	}
    	
    	// ajax 처리  async await 이용
    	window.addEventListener("DOMContentLoaded", setModalIdAndTitle);
    	
    	async function setModalIdAndTitle() {
    		try {
    			const response = await fetch('recruitListTitle.do', {
    				method : "post",
    				/*body : JSON.stringify({
    					"name" : "hello"	
    				})*/
    			});
    			const result = await response.json();
    			//console.log(result);
    			
    			const recruitTitles = document.querySelector('#recruitId');
    			Object.keys(result).forEach((id) => {
    				const $option = document.createElement('option')
    				$option.value = id;
    				$option.textContent = result[id];
    				recruitTitles.appendChild($option);
    			});
    			
    		} catch (error) {
    			console.log(error);
    		}
    	}
    </script>

    <!-- file upload 파일명 입력 처리 -->
    <script>
    	$(".custom-file-input").on("change", function() {
    		const fileName = $(this).val().split("\\").pop();
    		$(this).siblings(".custom-file-label").addClass("selected").html(fileName);
    	});
    </script>

    <!-- 공고 등록 modal -->
    <div class="modal fade" id="recruit_create_modal">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <!-- Modal header -->
                <div class="modal-header">
                    <h4 class="modal-title">공고 등록</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>

                <!-- Modal body -->
                <div class="modal-body">

                    <form method="post" id="recruit_create_form" action="<%=request.getContextPath()%>/recruitInsert.do">

						<!-- 공고 종류 일반 / 상시 -->
                        <label>공고 종류</label>
                        <div class="form-group">
                            <div class="custom-control custom-radio custom-control-inline">
                                <input type="radio" class="custom-control-input" id="recruitTime" name="recruitTime" value="일반 채용" checked>
                                <label for="recruitTime" class="custom-control-label">일반 채용</label>
                            </div>
    
                            <div class="custom-control custom-radio custom-control-inline">
                                <input type="radio" class="custom-control-input" id="recruitTime2" name="recruitTime" value="상시 채용">
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
                        <input form="recruit_create_form" type="hidden" id="recruitStartDate" name="recruitStartDate">
                        <input form="recruit_create_form" type="hidden" id="recruitEndDate" name="recruitEndDate">

                        <!-- 공고명 -->
                        <div class="form-group">
                            <label for="recruitName">공고명</label>
                            <input form="recruit_create_form" type="text" class="form-control" id="recruitName"
                                name="recruitName" placeholder="내용을 입력해주세요" required>
                        </div>

                        <!-- 직무구분 -->
                        <!-- Custom Select Menu -->
                        <label>직무구분</label>
                        <select form="recruit_create_form" name="recruitCode" id="recruitCode" class="custom-select">
                        	<%-- ajax 처리 setModalCode --%>
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
                                <textarea form="recruit_create_form" name="recruitContent1" id="recruitContent1"
                                    class="form-control" rows="10"></textarea>
                            </div>
                        </div>

                        <div id="recruitContentTab2" class="container tab-pane fade">
                            <!-- 주요 업무 -->
                            <div class="form-group">
                                <label for="recruitContent2">주요 업무</label>
                                <textarea form="recruit_create_form" name="recruitContent2" id="recruitContent2"
                                    class="form-control" rows="10"></textarea>
                            </div>
                        </div>

                        <div id="recruitContentTab3" class="container tab-pane fade">
                            <!-- 자격 요건 -->
                            <div class="form-group">
                                <label for="recruitContent3">자격 요건</label>
                                <textarea form="recruit_create_form" name="recruitContent3" id="recruitContent3"
                                    class="form-control" rows="10"></textarea>
                            </div>
                        </div>

                        <div id="recruitContentTab4" class="container tab-pane fade">
                            <!-- 우대 사항 -->
                            <div class="form-group">
                                <label for="recruitContent4">우대 사항</label>
                                <textarea form="recruit_create_form" name="recruitContent4" id="recruitContent4"
                                    class="form-control" rows="10"></textarea>
                            </div>
                        </div>

                        <div id="recruitContentTab5" class="container tab-pane fade">
                            <!-- 혜택 및 복지 -->
                            <div class="form-group">
                                <label for="recruitContent5">혜택 및 복지</label>
                                <textarea form="recruit_create_form" name="recruitContent5" id="recruitContent5"
                                    class="form-control" rows="10"></textarea>
                            </div>
                        </div>

                        <div id="recruitContentTab6" class="container tab-pane fade">
                            <!-- 기타 사항 -->
                            <div class="form-group">
                                <label for="recruitContent6">기타 사항</label>
                                <textarea form="recruit_create_form" name="recruitContent6" id="recruitContent6"
                                    class="form-control" rows="10"></textarea>
                            </div>
                        </div>
                    </div>
                    
					<!-- toast -->
					<div class="toast" style="position: absolute; bottom: 0; right: 0;">
						<div class="toast-header">
							내용이 비어 있습니다. 채워 넣어주세요
						</div>
						<div class="toast-body">
						</div>
					</div>
                </div>


                <!-- Modal footer -->
                <div class="modal-footer">
                    <button type="submit" form="recruit_create_form" class="btn btn-dark mx-auto">등록하기</button>
                </div>
            </div>
        </div>
    </div>

    <!-- Date Range Picker -->
    <script>
        $(function () {
            const startDate = moment(); // 시작일
            const endDate = moment().add(30, 'day'); // 종료일 -> 현재 시간 + 30일
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

                $('#daterange span').html(start.format('YYYY-MM-DD') + ' ~ ' + end.format('YYYY-MM-DD'));
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
            $('#recruit_create_form').on("submit", function(e) {

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
    
    <!-- ajax -->
    <script>
    
		$(function() {
			getData();
			
			$.ajax({
				url : 'recruitListCode.do',
				beforeSend: loading(),
				success : function(code) {
					setCode(code);
					setModalCode(code);
				},
				error : function(e) {
					console.log("ajax 통신 실패");
				}
			});
			
			// enter key 이벤트 처리
			$('#recruit_search').on("keyup", function(key) {
				//console.log("test", key.key, key.code);
				if(key.code === 'Enter') {
					getData();
				}
			});
		});

		function getData(currentPage=1) {
			let code = $('#recruit_code label.active input').attr('id');
			code = code === '전체' ? '' : code;
			//console.log(code);
			
			const title = $('#recruit_search').val();
			
			$.ajax({
				url : "recruitList.do",
				type : 'get',
				data : {
					currentPage,
					code,
					title
				},
				success : function(result) {
					//console.log(result);
					setTable(result.list);
					setPageInfo(result.pi);
				},
				error : function(e) {
					console.log("ajax 통신 실패");
				}
			});
		}
	 
		function setCode(codes) {
			const totalCnt = codes.reduce((sum, code) => sum + code.count, 0);
			
			const width = window.innerWidth;
			
			console.log(width);
			
			$("#recruit_code").html('');
			
			let btnGroup = $('<div>').addClass("btn-group");
			$('#recruit_code').append(btnGroup);
			let cnt = 1;
			
			btnGroup.append(`
				<label class="btn btn-secondary rounded-pill m-1 active">
                	<input type="radio" name="recruit_code" id="전체" autocomplete="off" checked>
                	#전체
                	<span class="badge badge-light">\${totalCnt}</span>
              	</label>
			`);
			
			codes.forEach((code) => {
				if (cnt % 5 === 0) {
					btnGroup = $('<div>').addClass("btn-group");
					$('#recruit_code').append(btnGroup);	
				}
				btnGroup.append(`
	          		<label class="btn btn-secondary rounded-pill m-1">
	                	<input type="radio" name="recruit_code" id="\${code.code}" autocomplete="off" checked>
	                	#\${code.code}
	                	<span class="badge badge-light">\${code.count}</span>
	              	</label>
				`);
				cnt++;
			});
			
			$("#recruit_code input").on("click", function() {
				getData();
			});
		}
		
		function setModalCode(codes) {
			$('#recruitCode').html('');
			
			codes.forEach((code) => {
				$('#recruitCode').append(`
					<option value="\${code.code}">\${code.code}</option>
				`);
			});
		}
		
		function loading() {
			$("#recruit_code").html(`
				<label class="btn btn-secondary rounded-pill m-1 active">
               	<span class="spinner-border spinner-border-sm"></span>
               	<input type="radio" name="recruit_code" id="전체" autocomplete="off" checked>
               	Loading...
              	</label>
			`);
			
			$("#recruit_table table tbody").html(`
				<tr>
            		<td class="text-center">
                        <span class="spinner-grow spinner-grow-sm"></span>&nbsp;&nbsp;
                        <span class="spinner-grow spinner-grow-sm"></span>&nbsp;&nbsp;
                        <span class="spinner-grow spinner-grow-sm"></span>
                    </td>
            	</tr>
			`);
		}
		
		function setTable(list) {
			$("#recruit_table table tbody").html('');
			
			list.forEach((r) => {
				$("#recruit_table table tbody").append(`
					<tr>
	                    <td class="table-recruit-category">
	                    	<a class="text-decoration-none text-info"
	                            href="<%= request.getContextPath() %>/recruitContentList.do?rid=\${r.id}">[\${r.code}]</a>
	                    </td>
	                    <td class="table-recruit-name" style="min-width: 200px;">
	                    	<a class="text-decoration-none text-dark"
	                            href="<%= request.getContextPath() %>/recruitContentList.do?rid=\${r.id}">\${r.title}</a>
	                    </td>
	                    <td class="table-recruit-kind"><span
	                            class="border border-info rounded-lg py-1 px-3 text-info">\${r.time}</span></td>
	                    <td class="table-recruit-date"><span class="text-secondary">\${getDate(r.start, r.end)}</span></td>
	                </tr>
				`);
				
			});
		}
		
		function getDate(startDate, endDate) {
			//console.log(startDate.replace(/[월,]/g, '').split(' '));
			let [month, day, year] = startDate.replace(/[월,]/g, '').split(' ');
			let date = [year, month, day].join('.');
			const start = date;
			
			[month, day, year] = endDate.replace(/[월,]/g, '').split(' ');
			date = [year, month, day].join('.');
			const end = date;
			
			return start + " ~ " + end;
		}
		
		function setPageInfo(pi) {
			const pg = $('#recruit_table ul.pagination');
			pg.html('');
			
			const {currentPage, startPage, endPage, maxPage} = {...pi};
			let tag = '';
			if (currentPage === 1) {
				tag += '<li class="page-item px-5 disabled">';
			} else {
				tag += '<li class="page-item px-5">';
			}
			
			tag += `
					<button onclick="getData(\${currentPage - 1})" class="page-link border-0">&lt;</button>
				</li>
			`;
			
			for (let p = startPage; p <= endPage; p++) {
				
				if (p === currentPage) {
					tag += '<li class="page-item px-3 active">';
				} else {
					tag += '<li class="page-item px-3">';
				}
				tag += `
						<button onclick="getData(\${p})" class="page-link border-0">\${p}</button>
	                </li>
				`;
			}
			
			if (currentPage === maxPage) {
				tag += '<li class="page-item px-5 disabled">';
			} else {
				tag += '<li class="page-item px-5">';
			}
			tag += `
				<button onclick="getData(\${currentPage + 1})" class="page-link border-0">&gt;</button>
	            </li>
			`;
			
			pg.append(tag);
		}
    
    </script>
</body>

</html>