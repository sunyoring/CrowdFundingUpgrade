<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "java.util.ArrayList, com.kh.faq.model.vo.Faq"%>
    
<%
	ArrayList<Faq> list  = (ArrayList<Faq>)request.getAttribute("list");
	System.out.println("====테스트=========================");
	System.out.println(list);
	System.out.println(list.size());
%> 
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

    #under{
        display: inline-block;
        width: 100%;
        height : 5px;
        background : gray;
    }

    #content{
        display: inline-block;
        width: 1200px;
        height: 600px;
        margin-bottom: 40px;

    }
    
    .contents{
        display: inline-block;
        width: 500px;
        height: 100%;
        text-align: center;
        
        margin-left: 10px;
        margin-right: 10px;

		background-color: rgba(100, 200, 167, 0.2);
		border-radius: 25px;
    }

    .c1, .c2{
        width: 100%;
        box-sizing: border-box;
    }

    .top{
        display: inline-block inline-block;
        height: 100px;
        padding: 10px;
		text-align: left;        
    }
    .top p{
    	display: inline-block;
        font-size: 30px;
        font-weight: bold;
        border-radius: 10px;
       	margin-top: 40px;
       	margin-left: 10px;
       	margin-bottom: 0px;
    }

    .mid{
        display: inline-block inline-block;
        height: 450px;
        padding: 10px 20px;
    }
    
    .bot{
        display: inline-block inline-block;
        height: 50px;
    }
    
    #ldiv{
   		width: 474px;
   		max-height: 390px;
   		overflow-y: auto; 
   		overflow-x: hidden;
   		
   	}

    #lTable{
    	width: 460px;
        border: 1px solid rgb(100, 200, 167);
        font-size: 12px;
    }
    
    
    #lTable tbody{
    	background-color: white;
    }
    
    .Qs{
	   	border: none;
	   	background-color: white;
	   	width: 250px; 
	   	overflow:hidden;
	   	text-overflow:ellipsis; 
	   	white-space:nowrap;
	   	text-align: left;
	   	padding: 0px 5px;
    }

	#ddiv{
   		width: 474px;
   		max-height: 390px;
   		overflow-y: auto; 
   		overflow-x: hidden;
   	}

    #dTable{
        background-color: white;
        width: 460px;
        border: 1px solid rgb(100, 200, 167);
        font-size: 12px;
    }

    th, td{
        border: 1px solid black;
    }
    
    th{
    	 background-color: rgba(100, 200, 167, 0.8);
    }
    
    .btns2{
       	display: inline-block;
       	visibility: hidden;
       	margin-right: 20px;
    }
    
    #insert, .btns2{
    	width: 55px;
    	height: 30px;
    	border: 1px solid rgba(100, 200, 167, 0.8);
    	background-color: rgba(100, 200, 167, 0.8);
    	font-weight: bold;
    	border-radius: 5px;
    	font-size: 15px;
    }
    
    #insert{
    	display:inline-block; 
    	margin-right:20px; 
    	margin-bottom: 10px;
    }
    
    #insert:hover, .btns2:hover{
    	background-color: rgba(100, 200, 167, 0.6);
    }
    
    textarea {
    	width: 345px; 
		padding: 0px 5px;
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

#pageTitle {
    font-size: 48px;
    text-align: center;
    font-family: 'Roboto', sans-serif;
    font-weight: bold;
}
</style>
</head>
<body>
	<%@ include file="../common/menubar.jsp"%>
	
	<div class="container_filed">
        <div id = "top">
            <p id="faq">FAQ</p>
            <div id="under"></div>
        </div>
        
        <div id="content">
            <div class="contents" id="FAQList">
                <div class="c1 top" id="c1Top">
                    <p>목록</p>
                </div>
                <div class="c1 mid" id="c1Mid">
                    
                    <div id="ldiv">
                    <table id="lTable">
                    <thead>
                        <tr style="height: 30px;">
                            <th style="width: 100px;">글번호</th>
                            <th style="width: 250px;">질문 내용</th>
                            <th style="width: 150px;">작성인</th>
                        </tr>
					</thead>  
					<tbody>        
						<%if(list.size() == 0){ %>
						<tr style="height: 25px;">
							<td colspan="3">등록된 게시물이 없습니다.</td>
						</tr>
						<%} else{%>              
                        <%for(Faq f : list){ %>
                       	<tr style="height: 25px;">
                            <td style="width: 100px;"><%=f.getfNo() %></td>
                            <td style="width: 250px;"><input type="button" id=<%=f.getfNo() %> class="Qs" value="<%=f.getQuestion() %>"></td>
                            <td style="width: 150px; padding: 0px 5px;"><%=f.getCreatorId() %></td>
                       	</tr>
                        <%} }%>
                    </tbody>    
                    </table>
                 </div>
					
                </div>
                <div class="c1 bot" id="c1Bot" style="text-align: right;">                
                    <button id='insert' onclick = "location.href='insertForm.fq'">작성</button>
                </div>
            </div>
            <script type = "text/javascript">
                    
           		$(function(){
	            	$(".Qs").each(function(i, item){
	            		
	            		$(this).click(function(){
	            			var fno = $(this).attr('id');
	            			
	            			$.ajax({
	            				url : "select.fq",
	            				data : {
	            					fno : fno
	            				},
	            				type : "get",
	            				success : function(result){
	            					$("#c2Mid").html(result);
	            				}
	            			})
	            			
	            			$(".btns2").css('visibility', 'visible');
	            		})
	            	})
    			})
              
           	</script>
            <div class="contents" id="FAQDetail">
                <div class="c2 top" id="c2Top"> 
                    <p>상세</p>
                </div>
                <div class="c2 mid" id="c2Mid">
                
                </div>
                <div class="c2 bot" id="c2Bot" style="text-align: right;">                
                    <input form="detail" class="btns2" type="submit"  value="수정" onclick="return goUpdate();" formaction="<%=request.getContextPath()%>/update.fq"/>
                    <input form="detail" class="btns2" type="submit"  value="삭제" onclick="return goDelete();" formaction="<%=request.getContextPath()%>/delete.fq"/>
                </div>
                <script type="text/javascript">
                	function goUpdate(){
                		var area1 = $("textarea").eq(0).val().length;
                		var area2 = $("textarea").eq(1).val().length;
                		
                		if(confirm("변경사항을 저장하시겠습니까?")){
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
                	
                	function goDelete(){
                		if(confirm("게시물을 삭제하시겠습니까?\n삭제한 게시물은 되돌릴 수 없습니다.")){
                			return true;
                		}
                		else{
                			return false;
                		}
                	}
                </script>
            </div>
       
        </div>
    </div>  

    <script>
    
        $(function(){          

            $("#y").click(function()
            {
                    $("#y").attr('checked', true);
                    $("#n").attr('checked', false);
             
            })

            $("#n").click(function()
            {
                    $("#y").attr('checked', false);
                    $("#n").attr('checked', true);
             
            })

        })

    </script>
    
	<%@ include file="../common/footer.jsp"%>
</body>
</html>