<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="com.kh.event.model.vo.Event"%>

<%
	Event e = (Event) request.getAttribute("event");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이벤트 상세보기</title>

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

.eventDetailForm {
	padding-bottom: 200px;
}

#pageTitle {
	font-size: 48px;
	text-align: center;
	font-family: 'Roboto', sans-serif;
	font-weight: bold!inmportant;
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

.eventDetailForm {
	justify-content: center;
	align-items: center;
	width: 1300px;
	margin: 0 auto;
}

.eName {
	margin-top: 100px;
	margin-bottom: 100px;
	text-decoration: underline;
	text-underline-position: under;
	text-align: center;
}

#deleteEventBtn {
	text-align: right;
	margin-bottom: 60px;
}

#deleteEventBtn input {
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

#deleteEventBtn input:hover {
	text-decoration: underline;
	font-style: normal;
}

.eContent {
	text-align: center;
}

.commentArea{
margin: 0 auto;
width:1300px;
border:1px solid gray;
padding:40px;
}

.commentArea textarea{

}

.comment{
resize:none;
width:80%;
height:100px;
align:center;
}

</style>

<body>


	<%@ include file="../common/menubar.jsp"%>



	<div class='box'>

		<div class="pageHead">
			<h1 id="pageTitle">EVENT</h1>
		</div>



		<div class="eventDetailForm">


			<%
				if (loginUser != null)
				if (loginUser.getUserCode().equals("01")) {
			%>
			<div id="deleteEventBtn">
				<form method='post'
					action="<%=request.getContextPath()%>/eDelete.do">
					<input type="hidden" name="eno" value="<%=e.geteNo()%>">
					<input type="submit" value="이벤트 삭제">
				</form>

			</div>
			<%
				}
			%>


			<div class="eName">
				<h3 style="font-weight: bold;"><%=e.geteName()%></h3>
			</div>

			<div class="eContent">
				<img alt="이미지로딩실패"
					src="<%=request.getContextPath()%>/resources/upfiles/<%=e.geteContent()%>">
			</div>
		</div>









		<div class="commentArea" >
			
			<h3>댓글 목록</h3>
			
			<div class="commentList">
			</div>
			<hr>
			<h3>댓글 작성</h3>
			
			<form method="post" action="<%=request.getContextPath()%>/enrollComment.ev">
			<input type="hidden" name="eno" value="<%=e.geteNo()%>">
			
				<%
					if (loginUser != null) {
				%>
				<input type="hidden" name="emailId"
					value="<%=loginUser.getEmailId()%>">
				<%
					} else {
				%>
				닉네임 : <input type="text" name="nickname">
				비밀번호 : <input type="password" name="cPwd" maxLength="6">

				<%
					}
				%>
				<textarea name="comment" class="comment"
					maxlength="1000" > </textarea>
	
				<input type="submit" id="commentBtn" value="댓글 등록">
			</form>
		</div>

	</div>
	</body>
	<%@ include file="../common/footer.jsp"%>
	
	
	<script>
	
	const commentBox = $(".commentList");
	
	$(function(){
		var eno = <%=e.geteNo()%>;
		console.log(eno);

		$.ajax({
			url:'commentList.ev',
			type: 'get',
			async:false,
			data : { eno : eno},
			success : function(list){

				list.forEach((cm => {
					console.log(cm);

					commentBox.append(`
							<br>
							<b>작성자 : </b> \${cm.name} 
							<br>
							
							<textarea name="comment" class="comment"  maxlength="1000" disabled> \${cm.comment}</textarea>
							<input type="button" class ="nestedComment" value="답글">

									<input type="button" class="updateBtn" value="수정">
									<input type="button" class="deleteBtn" value="삭제" >
									<input type="hidden" name="cNum" value=\${cm.cNum}>
	
							`)	
						selectNestedList(cm.cNum);
							
				}
				
				));
			
				

				$(".nestedComment").on("click",function(){
					var cno = $(this).next().next().next().val();
					console.log("답글버튼 눌렀을 때 부모 댓글번호 : " + cno);
 					$(this).next().next().after(`
 							<div class="replyBox" style="margin-left:50px;">
							<br>
							<form method="post" action="<%=request.getContextPath()%>/enrollReComment.ev"> 
							<input type="hidden" name="eno" value="<%=e.geteNo()%>">
							<input type="hidden" name="cNum" value=\${cno}>
									<h5>답글 작성 </h5>
							<%
								if (loginUser != null) {
							%>
							<input type="hidden" name="emailId"
								value="<%=loginUser.getEmailId()%>">
							<%
								} else {
							%>
							닉네임 : <input type="text" name="nickname">
							비밀번호 : <input type="password" name="cPwd" maxLength="6">

							<%
								}
							%>
				
							<textarea name="comment" class="comment"
								maxlength="1000" > </textarea>
				
							<input type="submit" id="commentBtn" value="댓글 등록" >
							<input type="button" value="닫기" onclick="removeReplyBox();">
							</form>
							</div>
							`) 
			

				})
				
				$(".updateBtn").on("click",function(){
					var cno = $(this).next().next().val();
					$(this).prev().prev().attr("disabled",false);
					
					
					/*   이렇게 작성하면 쿼리스트링으로 안넘어감
					$(this).prev().on("keyup",function(){
						comment = $(this).prev().val();
					})
					 */ 

					 $(this).prev().prev().trigger("keyup");
					comment = $(this).prev().prev().val();				 
					
					$(this).attr("value","완료").on("click",function(){
					 	var eno = <%=e.geteNo()%>;
						location.href="<%=request.getContextPath()%>/update.eco?cno="+cno+"&comment="+comment+"&eno="+eno;
					 
					
					}); 
					
				});
				
				
				$(".deleteBtn").on("click",function(){
					
					var cno = $(this).next().val();
					console.log(cno);
					location.href="<%=request.getContextPath()%>/delete.eco?cno="+cno+"&eno="+eno;

				})
				
				

				
			},error:function(e,e2){
				console.log("통신실패");
				console.log(e);
				console.log(e2);
			}
	
		});	
		
	});

	function removeReplyBox(){
			$(".replyBox").remove();
		}
	
	function updateReply(e){
		console.log(e);
		console.log($(this));
	}
	
	 function selectNestedList(pNo){
			//부모 댓글 번호
			console.log("부모댓글번호 : " + pNo);
			$.ajax({
				
				url : 'nestedCommentList.ev',
				type : 'post',
				async:false,
				dataType :'json',
				data : {
					cno : pNo
				},
				success :  function(list){
					console.log("통신성공");
					console.log("답글 : " + list);
					
					list.forEach((cm => {
						commentBox.append(`
								<div class="nestedreplyBox" style="margin-left:50px;">
								<br>
								ㄴ> <b>작성자 : </b> \${cm.name} 
								<br>
								
								<textarea name="comment" class="comment"  maxlength="1000" disabled> \${cm.comment}</textarea>
								<input type="button" class ="nestedComment" value="답글">

										<input type="button" class="updateBtn" value="수정">
										<input type="button" class="deleteBtn" value="삭제" >
										<input type="hidden" name="cNum" value=\${cm.cNum}>
								
								</div>
								`)	
					}))
					
				},
				error : function(e,e2){
					console.log("통신실패")
					console.log(e2);
				}
				
			})
				
		} 


	
	</script>
</html>