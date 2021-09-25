<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="com.kh.lecture.model.vo.Lecture" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> 강의 수정 화면</title>

<style>

.body{
	height: 2200px;
}

.modal {
	position: fixed;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	display: flex;
	justify-content: center;
	align-items: center;
	opacity: 1;
	transition: opacity 1s;
}

.modal.opaque {
	opacity: 1;
	transition: opacitiy 1s;
}

.modal.unstaged {
	bottom: -100px;
	height: 0;
}

.modal-close:hover {
	background-color: #ffd500;
}

.modal_overlay {
	background-color: rgba(0, 0, 0, .70);
	width: 100%;
	height: 100%;
	position: absolute;
}

.modal_content {
	background-color: rgb(242,242,242);
	padding: 10px 60px 50px 60px;
	align-items: center;
	text-align: center;
	position: relative;
	top: 0px;
	border-radius: 15px;
	box-shadow: 0 5px 20px rgba(0, 0, 0, .25), 0 10px 10px
		rgba(0, 0, 0, .80);
	width: 950px;
	height: 750px;;
	border: 2px solid rgb(203,203,203);
	overflow-y: scroll;
}

.hidden {
	display: none;
}

.top {
	background-color: rgb(80, 80, 80);
	border-radius: 10px;
	color: white;
	margin-bottom: 100px;
}

h2 {
	background-color: none;
	color: black;
	text-weight:bold;
	border-radius: 7px;
	padding: 5px 10px 5px 10px;
	font-size: 21px;
	width: 95px;
	height: 25px;
	margin: 0 auto;
}

.preview {
	margin: 0 auto;
	width: 500px;
	height: 300px;
}

.preview img {
	left: 0;
	bottom: 0;
	align-self: flex-start;
	border: 1px dashed black;
	
	height: 256px;
	width: 256px;
}

#selectImg {
	margin: 0px 0px 10px 10px;
	font-family: 'Nanum Brush Script', cursive;
	border: 0px;
	border-radius: 5px;
}

.box {
	position:relative;
	display:inline-block;
	height: 85px;
	width: 80%;
	margin: 40px 0px 0px 40px;
	text-align: center;
}

textarea {
	margin-top: 20px;
	margin-bottom: 20px;
	display: block;
	width: 600px;
}


.information{
	
	width: 100%
	
}

img{

	background-color:white;

}

.selectImg{
	
	background-color:rgb(233,236,239);
	
}

button {

	background-color:rgb(116,228,190);
	color:white;
	border:none;
	border-radius: 5px;
	width: 120px;
	height: 50px;
	font-size: 12px;
	margin-right: 15px;
	margin-left: 15px;
}

button:hover{

	cursor:pointer; 

}

.information > input{

	margin: 8px 0px 0px 5px;
	font-size:15px;
	height: 30px;
	
}



</style>
</head>
<body>		

				<div class="modal">
					<div class="modal_overlay"></div>
					<div class="modal_content">

						<div class="top">신규 강의 등록</div>

						<form class="regist" method="POST" action="<%= request.getContextPath()%>/lectureInsert.le"  enctype="multipart/form-data">
							<div class="preview" id="top">
								<img src="<%=request.getContextPath()%>/resources/lectureImage/0000000000000.png" class="lectureImage" name="lectureImage">
								<input type="file" class="selectImg" name="selectImg" value="사진 업로드" onchange="previewImg(event);"/>
							</div>
							<div class="information">
								<div class="box">
									<h2>강사</h2><br>
									<input type="text" id="lecturer" class="lecturer" name ="lecturer" placeholder="강사이름" style="width:68%;height: 30px; text-align:center;">
											<!--  <input type="text" value=" loginUser" readonly> -->
								</div>
								<br>
								<div class="box">
									<h2>강의 제목</h2><br>
									<input type="text" id="lectureTitle" class ="lectureTitle" name="lectureTitle" required style="width:68%; height: 30px; text-align:center;">
								</div>
								<br>
								<div class="box">
									<h2>강의 날짜</h2><br>
									<input id="lectureDate" class ="lectureDate" name ="lectureDate" type="date" required style="width:50%; height: 30px; text-align:center;">
								</div>
								<br>
								<div class="box" style="display:inline;">
									<h2>강의 주소</h2><br>
									<input type="text" id="lectureAddress"  class ="lectureAddress" name ="lectureAddress" readonly style="width:57%; height: 30px; text-align:center;">
										<input type="button" onclick="execDaumPostcode()" value="주소 검색" style="position:absolute; right:0; border:none; margin: 6px 6px 0px 0px;">
								</div>
								<br>
								<div class="box">
									<h2>강의 인원</h2><br>
									<input type="number" id="lectureNumber"  class ="lectureNumber" name="lectureNumber" required style="width:25%; height: 30px; ">
								</div>
								<br>
								<div class="box">
									<h2>강의 주제</h2><br>
									 <select id="lectureTopicr"  class ="lectureTopic" name ="lectureTopic" required style="width:47%;height: 30px; text-align:center;">
										<option value="펀딩투자강의"> 펀딩투자강의 </option>
										<option value="펀딩오픈강의"> 펀딩오픈강의 </option>
										<option value="펀딩입문강의"> 펀딩입문강의 </option>
										</select>
								</div>
								<br>
								<div class="box">
									<h2>강의 시간</h2><br>
									<input type="number" id="lectureTime" class ="lectureTime" name ="lectureTime" placeholder="강의 인원 (분)" required style="width:25%;height: 30px; text-align:center;">
								</div>
								<br>
								<div class="box">
									<h2>세부내용</h2><br>
									<textarea  rows="45" id="lectureContent" class ="lectureContent" name="lectureContent" style="width:100%;"></textarea>
								</div>
							</div>
							<br>
							<button type="submit" >등록</button>
							<button type="button" onclick="history.back();">취소</button>
						</form>

						

					</div>
				</div>
				<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
				<script src="//dapi.kakao.com/v2/maps/sdk.js?appkey=86371d8b099bdfe665381687000191ad&libraries=services"></script>
				
				<script>
					
				

		    function execDaumPostcode() {
		        new daum.Postcode({
		            oncomplete: function(data) {
		                var addr = data.address; // 최종 주소 변수

		                // 주소 정보를 해당 필드에 넣는다.
		                document.getElementById("lectureAddress").value = addr;
		                // 주소로 상세 정보를 검색
		                geocoder.addressSearch(data.address, function(results, status) {
		                    // 정상적으로 검색이 완료됐으면
		                });
		            }
		        }).open();
		    }
					
				
				
				
				
				
				
				
				
				
				
					function previewImg(event) {
					
					const reader = new FileReader();
					const preView = document.querySelector(".lectureImage");
					
					reader.onload = (event) => {
						preView.setAttribute("src", event.target.result);
					}
						reader.readAsDataURL(event.target.files[0]);
					}
					
					preView.setAttribute("height","256");
					preView.setAttribute("width","256");
					
					
					
					
					
					
				</script>
				

</body>
</html>