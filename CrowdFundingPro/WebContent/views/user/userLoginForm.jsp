<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="com.kh.user.model.vo.User"%>

<%
String contextPath = request.getContextPath();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">


<title>LoginPage</title>

<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap"
	rel="stylesheet">
<style>

    .body{
    	font-family: 'Noto Sans KR', 'sans-serif';
    }

.login-form {
	justify-content: center;
	align-items: center;
	width: 500px;
	height: 100vh;
	text-align: center;
	margin: 0 auto;
}

.login-form h1 {
	font-size: 30px;
	text-align: left;
	margin-top: 150px;
	margin-bottom: 100px;
	font-family: 'Noto Sans KR', 'sans-serif';
		font-weight:bold;
}

.login-btn {
	background: rgb(100, 200, 167);
	border: none;
	width: 100%;
	height: 50px;
	margin-top: 45px;
	border-radius: 10px;
	opacity: 0.8;
	color: white;
	font-size: 1.5em;
	text-align: center;
	font-family: 'Noto Sans KR', 'sans-serif';
	}

.login-btn:hover {
	cursor: pointer;
	background: rgb(100, 220, 167);
}

.int-area {
	width: 500px;
	position: relative;
}

.int-area input {
	width: 100%;
	padding: 20px 10px 10px;
	background: transparent;
	border-style: none;
	border-bottom: 2px solid black;
	font-size: 18px;
	color: black;
	outline: none;
	text-align: center;
}

.int-area label {
	position: absolute;
	left: 10px;
	top: 15px;
	font: 18px;
	color: #999;
}

.int-area input:focus+label, .int-area input:valid+label {
	top: 0;
}

.caption {
	margin-top: 20px;
	position: relative;
}

::placeholder {
	font-size: 0.7em;
	text-align: center;
	opacity: 0.6;
}

.caption {
	text-align: left;
}

#joinBtn {
	width: 120px;
	height: 30px;
	margin-left: 380px;
	background: rgb(0, 0, 0, 0.1);
	border: none;
	border-radius: 5px;
	opacity: 0.8;
	color: rgba(0, 0, 0, 0.7);
	font-weight: 600;
	font-family: 'Noto Sans KR', 'sans-serif';
	}

#joinBtn:hover {
	cursor: pointer;
	background: rgb(0, 0, 0, 0.3);
	color: black;
	font-size: 14px;
}


#googleBtn{
	margin-top:50px;
	margin-left:190px;
}

</style>



</head>

<body>


	<%@ include file="../common/menubar.jsp"%>



	<section class="login-form">
		<h1>로그인</h1>
		<form id="loginForm" action="<%=request.getContextPath()%>/login.me"
			method="post">
			<div class="int-area">
				<input 
				class="input-box" 
				type="email" 
				name="emailId" 
				id="emailId"
				autocomplete="on" 
				required placeholder="이메일 아이디"> 
				<label for="id" >ID</label>
			</div>
			<div class="int-area">

				<input 
				class="input-box" 
				type="password" 
				name="userPwd" 
				id="userPwd"
				autocomplete="off" 
				required
				placeholder="비밀번호 (영문, 숫자, 특수문자 포함 8자 이상 20자 이내)"> 
				<label for="pwd">PWD</label>

				<button class="login-btn" id="loginBtn" type="submit">LOGIN</button>
			</div>

		</form>
		<div class="caption"><br>
			 <a href="<%=request.getContextPath()%>/enrollForm.me"
				style="font-size: 1em; diplay:inline">아직 계정이 없으신가요?</a>
		<button id="joinBtn" onclick="enrollPage();" style="">회원가입</button>

		</div>

	</section>

	<script>
	
	<%-- 구글 로그인 기능
	
				<!--  구글 로그인(테스트중)
			<div id="googleBtn" class="g-signin2" data-onsuccess="onSignIn"></div>
			 -->
	
	
	<!-- 앱의 클라이언트 ID 지정 -->
<meta name="google-signin-client_id" content="31102830978-o5gc3ru1pkhi7nfe10ucob89s12t95ej.apps.googleusercontent.com">
	
	function onSignIn(googleUser) {
		  var profile = googleUser.getBasicProfile();
		  console.log('ID: ' + profile.getId()); // Do not send to your backend! Use an ID token instead.
		  console.log('Name: ' + profile.getName());
		  console.log('Image URL: ' + profile.getImageUrl());
		  console.log('Email: ' + profile.getEmail()); // This is null if the 'email' scope is not present.
		  var emailId = profile.getEmail();
		  var name = profile.getName();
		  var pImg = profile.getImageUrl();

	    	if(profile != null ){
				$.ajax({
					url : "gLogin.me",
					type : "post",
					dataType : "text",
					data : {
						emailId : emailId,
						name : name,
						pImg : pImg
					},
					async: false,
					success : function(result){
						console.log("서버 통신 성공");
						console.log("googleloginresult : " + result);

						if(msg == "gLoginSuccess"){
							location.href="<%= request.getContextPath()%>"; 
						}else if(test == "duplicate"){
							signOut();
							alert("이미 구글 이메일아이디로 가입되어있습니다. 가입 된 계정으로 로그인해주세요.");
						}
					},
					error : function(){
						console.log("서버 통신 실패");
					}
				})	    		
	    	} else{
	    		alert('goole 계정으로 로그인 실패');
	    	}
		  
		}
	
	function signOut(){
		gapi.auth2.getAuthInstance().disconnect();
	}
	--%>

	function enrollPage(){
		location.href = "<%= request.getContextPath()%>/enrollForm.me";
		}
	
	

</script>
</body>
</html>