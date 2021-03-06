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
            <p id="ftype">- ??????</p>
            <div id="under"></div>
        </div>
        <form method="POST" id="insert">
        <table>
            <tr style="height: 30px;">
                <th>??????</th>
                <td> 
                    <select name="targetUser">
                        <option value="S">?????????</option>
                        <option value="M">?????????</option>
                    </select>
                </td>
                
            </tr>
            <tr style="height: 100px;">
                <th>??????</th>
                <td>
                    <textarea name="question" style='height: 100px; resize: none;' placeholder="????????? ??????????????????" required></textarea>
                </td>
                
            </tr>
            <tr style="height: 170px;">
                <th>??????</th>
                <td>
                    <textarea name="answer" style='height: 170px; resize: none;' placeholder="????????? ??????????????????" required></textarea>
                </td>
                
            </tr>
        </table>
        </form>
        <div id="btns">
    		<input type="button" class="btns" value="????????????" onclick="back();">
       		<input type="submit" form="insert" class="btns" value="??????" onclick="return goInsert();" formaction="<%=request.getContextPath()%>/insert.fq"/>
        </div>
        
    </div>  
    <script type="text/javascript">
    function goInsert(){
		var area1 = $("textarea").eq(0).val().length;
		var area2 = $("textarea").eq(1).val().length;
		
		if(confirm("???????????? ?????????????????????????")){
    		if(area1 > 1000){
    			alert('?????? ????????? 1000??? ????????? ???????????????(????????????)');
    			return false;
    		}
    		
			if(area2 > 1000){
				alert('?????? ????????? 1000??? ????????? ???????????????(????????????)');
    			return false;
			}
    		
    		return true;
		} else{
			return false;
		}
	}
       	
       	function back(){
       		if (confirm("???????????? ?????????????????????????\n???????????? ????????? ???????????????.")) {
   	            alert("???????????? ???????????????.");
   	            history.back();
   	        }
       	}
       </script>

	<%@ include file="../common/footer.jsp"%>
</body>
</html>