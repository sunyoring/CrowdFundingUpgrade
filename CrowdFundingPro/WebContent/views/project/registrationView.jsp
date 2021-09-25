<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>프로젝트 등록 페이지</title>
<style>

		.body{
		    font-family: 'Noto Sans KR', 'sans-serif';
		
		}

        .container_filed{
            width:1000px;
            margin: 0 auto ;
            padding-bottom:120px;

        }
        #btn1{
         width:100%;
         height:45px;
         background:rgb(116, 228, 190);
         font-size:25px;
         font-weight:bold;
         font-family: 'Noto Sans KR', 'sans-serif';
                 border:0.3px solid white;
         border-radius:10px;
         color:white;
        }
        #btn1:hover {
         border:none;
        	background:rgb(116, 240, 220);
		}
		

        
        #title{
        margin-top:120px;
        margin-bottom:100px;
        }
        
        #title h1{
        font-size:44px;
        font-weight:bold;
        }
        
        
        #insert{
         width:400px;
         font-family: 'Noto Sans KR', 'sans-serif';
         margin:0 auto;
        
        }

		#insert label{
		display:block;
		font-family: 'Noto Sans KR', 'sans-serif';
		font-size : 24px;
    	color: #212529;	
    	
    	}      
		
		.inputT {
		height:40px;
		border:none;
		border-bottom:2px solid rgba(0,0,0,.7);
		font-size : 21px;
		margin-bottom:10px;
		margin-top:10px;
		margin-right:5px;
		}
		
		.inputT::placeholder{
		font-size:16px;
		color:rgba(0,0,0,.4);
		padding-left:10px;
		}  
		
		.inputI{
		width:400px !important;
		}
		
		.labelA{
		margin-bottom:30px;
		
		}   
        
    </style>
</head>
<body>
	<%@ include file="../common/menubar.jsp"%>
	
	<div class="container_filed">
	

	
	
	<div id="insert">
	
		<div id="title">
		    <h1>프로젝트 등록</h1>
	</div>
	
	<form action="<%=request.getContextPath()%>/register.do" method="post" enctype="multipart/form-data">

        <label for="projectName"><b>프로젝트 제목</b></label>
        <input class="inputT" style="width:100%;"  type="text" name="projectName" placeholder="프로젝트명을 입력하세요." id="projectName"><br><br>


        <label for="amountGoal"><b>목표금액</b></label>
        <input class="inputT"  type="text" name="amountGoal" id="amountGoal" placeholder="목표금액을 입력하세요."><b>원</b><br><br>

        <label for="delivery"><b>배송료</b></label><br>
        <input class="inputT"  type="text" placeholder="가격을 입력하세요." name="delivery" id="delivery"><b>원</b><br><br>
		<hr>


		<label class="labelA" for="uploadfile"><b>대표이미지</b></label>
		<div class="titleImg">
			<div class="custom-file inputI">
				<input type="file" class="custom-file-input" id="uploadfile"
					name="uploadfile" required> 
				<label for="uploadfile"
					class="custom-file-label" data-browse="업로드">파일을 올려주세요</label>
			</div>
		</div> 
		
		

				<hr>

  	  <label  class="labelA" for='category'><b>카테고리선택</b></label>
    
        <select class='form-control inputI' name="category" id="category">
            <option value="1">문화생활</option>
            <option value="2">테크,가전</option>
            <option value="3">패션,잡화</option>
            <option value="4">뷰티</option>
            <option value="5">푸드</option>
            <option value="6">홈,리빙</option>
            <option value="7">여행,레저</option>
            <option value="8">스포츠,모빌리티</option>
            <option value="9">캐릭터,굿즈</option>
            <option value="10">베이비,키즈</option>
            <option value="11">반려동물</option>
            <option value="12">게임,취미</option>
            <option value="13">컬쳐,아티스트</option>
            <option value="14">클래스,컨설팅</option>
            <option value="15">출판</option>
            <option value="16">소셜,캠페인</option>
            <option value="17">기부,후원</option>
            <option value="0" selected>선택</option>

        </select><br><br>
		<hr>

        <label for="dateIn"  class="labelA" ><b>프로젝트 마감일</b></label>
        <input type="date" class="form-control inputI" name="dateInput" id="dateIn"><br><br>
		<hr>

        <label for="confirm1" class="labelA" ><b>성인인증</b></label>
        <input type="checkbox" id="confirm1" name="confirm1"  >
        <span >19세 이상 펀딩 가능한 리워드입니다.</span><br><br>
		<hr>

        <label><b>프로젝트 소개</b></label><br>
    <textarea name="detail" cols="40" rows="5" placeholder="간단한 설명을 기입해주세요." style="resize:none; border:0.5px solid lightgray;  padding:15px;" ></textarea><br><br>

		<hr>

    <label><b>와디즈 약관 동의</b></label><br>

	<div id="agree" style="border:0.5px solid lightgray; padding:15px; margin-bottom:50px;">
    <input type="checkbox" id="" name="confirm2" value="1">
    <span> <b>(필수)</b>크라우드 펀딩의 심의 가이드라인을 숙지하여야 합니다.</span><br><br>

    <input type="checkbox" id="" name="confirm2" value="2">
    <span> <b>(필수)</b>크라우드 펀딩의 담당기관의 해석을 우선시합니다.</span><br><br>

    <input type="checkbox" id="" name="confirm2" value="3">
    <span> 관계법령을 준수하여야 합니다.</span><br><br>

    <input type="checkbox" id="" name="confirm2" value="4">
    <span> 제 3자의 지적재산권을 침해하지 않아야 합니다.</span><br>
    <hr>
    <input type="checkbox" id="" name="confirm2All" value="5">
    <span> 모두 동의합니다.</span><br>
	</div>
        <input id="btn1" type="submit" value="등록하기"><br>
    </form>
    </div>
    </div>
   
    
    <%@ include file="../common/footer.jsp"%>
</body>
   <script>
  //동의 모두선택 / 해제
    const agreeChkAll = document.querySelector('input[name=confirm2All]');
        agreeChkAll.addEventListener('change', (e) => {
        let agreeChk = document.querySelectorAll('input[name=confirm2]');
        for(let i = 0; i < agreeChk.length; i++){
          agreeChk[i].checked = e.target.checked;
        }
    });
    </script>
</html>

