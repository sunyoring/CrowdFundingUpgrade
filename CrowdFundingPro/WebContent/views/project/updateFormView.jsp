<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="com.kh.project.model.vo.Project" %>
<%@ page import="com.kh.common.model.vo.Attachment" %>    
    
<%
	Project pj=(Project)(request.getAttribute("pj"));
	Attachment at=(Attachment)(request.getAttribute("at"));
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>프로젝트 수정 페이지</title>
<style>


		.body{
		    font-family: 'Noto Sans KR', 'sans-serif';
		    background:#f2f2f2;
		    margin : 0 auto; 
		}

        .container_filed{
            width:100%;
            margin: 0 auto ;
            padding-bottom:120px;
            padding-top:100px;

        }
        
        
         #update{
         width:800px;
         font-family: 'Noto Sans KR', 'sans-serif';
         margin:0 auto;
         padding:50px;
         border:0.2px solid lightgray;
         text-align:center;
         background:white;
         		    background:#f2f2f2;
         
        }

		#update label{
		display:block;
		font-family: 'Noto Sans KR', 'sans-serif';
		font-size : 24px;
    	color: #212529;
    	margin-bottom:60px;	
    	}  
		



        #btn1{
         width:100%;
         height:50px;
         background:rgb(116, 228, 190);
         font-size:25px;
         font-weight:bold;
         font-family: 'Noto Sans KR', 'sans-serif';
                 border:0.3px solid white;
         border-radius:10px;
         color:white;
         margin-bottom:100px;
		 margin-top:100px;

        }
        #btn1:hover {
         border:none;
        	background:rgb(116, 240, 220);
		}
		
		#title{
        margin-top:60px;
        margin-bottom:120px;
        font-family: 'Noto Sans KR', 'sans-serif';
        text-align:center;
        }
        #title h1{
        font-size:44px;
        font-weight:bold;
        
        }

		.inputT {
		height:40px;
		border:none;
		border-bottom:2px solid rgba(0,0,0,.2);
		font-size : 21px;
		margin-bottom:10px;
		margin-top:10px;
		margin-right:5px;
		text-align:center;
		}
		
		.inputT::placeholder{
		font-size:16px;
		color:rgba(0,0,0,.4);
		padding-left:10px;
		}  
		
		.labelA{
		margin-bottom:30px;
		}

		.inputI{
		width:400px !important;
		}
    </style>
</head>
<body>
	<%@ include file="../common/menubar.jsp"%>

		<div class="container_filed">
		<br>
		<div id="title">
		    <h1>수정하기</h1>
		</div>
		<div id="update">
		
		
	
		
		<form action="<%=request.getContextPath()%>/updateB.do" method="post" enctype="multipart/form-data">
        
		<input type="hidden" name="pCode" value="<%=pj.getProjectCode()%>">
		
        <label><b>프로젝트명</b></label>
        <input  class="inputT" style="width:100%;" type="text" name="projectName" value="<%=pj.getProjectName()%>"><br><br>

        <label><b>목표금액</b></label>
        <input  class="inputT" style="text-align:center;" type="text" name="amountGoal" value="<%=pj.getAmountGoal()%>"><b style="font-size:20px;">원</b><br><br>

        <label><b>배송료</b></label><br>
        <input  class="inputT" style="text-align:center;" type="text" placeholder="가격을 입력하세요." name="delivery"  value="<%=pj.getDeliveryCharge() %>" ><b style="font-size:20px;">원</b><br><br>

<hr>

    	<input type='hidden' name='titleImg' value='<%=at.getChangeName() %>'>
    	<input type='hidden' name='fileNo' value='<%=at.getFileNo() %>'>    	
    	<label class="labelA" for="uploadfile"><b>대표이미지</b></label>
		<div class="titleImg " style="margin-bottom:50px;">
		    <div style=font-size:21px;>	<%=at.getOriginName() %>  </div> <br>
			<div class="custom-file inputI">
				<input type="file" class="custom-file-input" id="uploadfile"
					name="uploadfile" required> 
				<label for="titleImg" style="text-align:left; font-size:19px;"
					class="custom-file-label" data-browse="업로드">파일 업로드</label>
			</div>
		</div>
      <hr>
      
      <div style=display:inline-block>
        <label for="dateIn"  class="labelA" ><b>프로젝트 마감일</b></label>
        <input type="date" style="text-align:center;" class="form-control inputI" name="dateInput" id="dateIn"  value="<%=pj.getDdln()%>"><br><br>
      </div>
      
		<hr>

  	 <label><b>프로젝트 소개</b></label><br>
  	  <textarea name="detail" cols="40" rows="5" placeholder="간단한 설명을 기입해주세요." style=" width:100%; resize:none; border:0.5px solid lightgray;  padding:15px;" ><%=pj.getDetailIntro() %></textarea><br><br>

        <input id="btn1" type="submit" value="수정 완료"><br>
    </form>
    </div>
    </div>
       <%@ include file="../common/footer.jsp"%>
   
</body>
</html>