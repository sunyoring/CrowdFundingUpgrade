<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
        <footer class="container-fluid" style="margin-bottom: 50px; margin-top: 50px;">
            <!-- navbar -->

            <nav
                class="navbar navbar-expand-md navbar-light mb-5"
                style="border-top: 3px solid rgba(0, 0, 0, .5); border-bottom: 3px solid rgba(0, 0, 0, .5);  padding-left: 100px; padding-right:100px;">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a href="#" class="nav-link">정책.약관</a>
                    </li>
                    <li class="nav-item">
                        <a href="#" class="nav-link">개인정보처리방침</a>
                    </li>
                </ul>

                <ul class="navbar-nav ml-auto">
                    <li class="navbar-item">
                        <a href="#" class="nav-link">공지사항</a>
                    </li>

                    <li class="navbar-item">
                        <a href="#" class="nav-link" onclick="goFaq();">문의사항</a>
                    </li>
                </ul>
            </nav>
			<script>
				/* 문의사항 == FAQ */
				function goFaq(){
					location.href="<%=request.getContextPath()%>/list.fq";
				}
			</script>

            <div class="row">
                <div class="col-md-3">
                    <button class="btn btn-dark ml-4" style="width: 200px;">
                        <i class="far fa-question-circle"></i>
                        문의하기</button>
                </div>
                <div class="col-md-3"></div>
                <div class="col-md-6">
                    <b>KH(주)| 대표이사 ooo | 사업자 등록번호 000-00-000000</b>
                    <br>
                    <small>서울특별시 강남구 강남구 테헤란로14길 6</small>
                </div>

            </div>
            <div class="media ml-3">
                <img src="<%= request.getContextPath() %>/resources/images/support_icon.png" alt="support_icon">
                <div class="media-body">
                    <b>고객센터 000-0000</b><br>
                    <small>상담 가능 시간 : 평일 오전 9시 ~ 오후 6시
                        <br>
                        (주말, 공휴일 제외)</small>
                </div>
            </div>
            <!-- 문의하기 버튼 -->
            <!-- 주소 및 사업자 등록번호 -->
            <!-- 고객센터 -->
        </footer>
</body>
</html>