<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>

    .container_filed{
        width:100%;
        height: auto;
        text-align: center;
    }

    #top{
        width: 1200px;
        text-align: left;
        margin: 0 auto;
        padding-bottom: 40px;
        margin-top: 20px;
    }

    #faq{
        display: inline-block;
        font-weight: bold;
        font-size: 50px;
        margin-left: 20px;
        margin-bottom: 0px;
        
    }

    #ftype {
        display: inline-block;
        font-weight: bold;
        font-size: 25px;
        
        margin-bottom: 0px;
        margin-left: 20px;
    }

    #under{
        display: inline-block;
        width: 100%;
        height : 5px;
        background : gray;
            
    }

    table{
        display: inline-block;
        width: 800px;
       	border-collapse: collapse;
       	border-spacing: 0; 
    }

    th{
        width: 100px;
        background-color: rgba(100, 200, 167, 0.8);
        font-size: 12px;
        border: 1px solid black;
    }

    td{
        width: 700px;
        text-align: left;
        font-size: 11px;
        border: 1px solid black;
    }

	select{
		height: 30px;
	}

    #btns{
        display: inline-block;
        text-align: right;
        width: 800px;
    }

    .btns{
        display: inline-block;
        margin-top: 20px;
        margin-bottom: 40px;
        margin-left: 5px;
        
        width: 80px;
        height: 35px;
        border: 1px solid rgba(100, 200, 167, 0.8);
    	background-color: rgba(100, 200, 167, 0.8);
        font-weight: bold;
        border-radius: 5px;
        font-size: 15px;
        
    }

	.btns:hover{
    	background-color: rgba(100, 200, 167, 0.6);
    }
    
    textarea{
    	width: 700px;
    }
    
</style>
</head>
<body>
	<%@ include file="../common/menubar.jsp"%>
	<div class="container_filed">
        <div id = "top">
            <p id="faq">FAQ</p>
            <p id="ftype">- 추가</p>
            <div id="under"></div>
        </div>
        <form method="POST" id="insert">
        <table>
            <tr style="height: 30px;">
                <th>대상</th>
                <td> 
                    <select name="targetUser">
                        <option value="S">서포터</option>
                        <option value="M">메이커</option>
                    </select>
                </td>
                
            </tr>
            <tr style="height: 100px;">
                <th>질문</th>
                <td>
                    <textarea name="question" style='height: 100px; resize: none;' placeholder="내용을 입력해주세요" required></textarea>
                </td>
                
            </tr>
            <tr style="height: 170px;">
                <th>답변</th>
                <td>
                    <textarea name="answer" style='height: 170px; resize: none;' placeholder="내용을 입력해주세요" required></textarea>
                </td>
                
            </tr>
        </table>
        </form>
        <div id="btns">
    		<input type="button" class="btns" value="이전으로" onclick="back();">
       		<input type="submit" form="insert" class="btns" value="등록" onclick="return goInsert();" formaction="<%=request.getContextPath()%>/insert.fq"/>
        </div>
        
    </div>  
    <script type="text/javascript">
    function goInsert(){
		var area1 = $("textarea").eq(0).val().length;
		var area2 = $("textarea").eq(1).val().length;
		
		if(confirm("게시물을 등록하시겠습니까?")){
    		if(area1 > 1000){
    			alert('질문 내용은 1000자 이하로 작성하세요(공백포함)');
    			return false;
    		}
    		
			if(area2 > 1000){
				alert('답변 내용은 1000자 이하로 작성하세요(공백포함)');
    			return false;
			}
    		
    		return true;
		} else{
			return false;
		}
	}
       	
       	function back(){
       		if (confirm("이전으로 돌아가시겠습니까?\n작성중인 내용은 사라집니다.")) {
   	            alert("목록으로 돌아갑니다.");
   	            history.back();
   	        }
       	}
       </script>

	<%@ include file="../common/footer.jsp"%>
</body>
</html>