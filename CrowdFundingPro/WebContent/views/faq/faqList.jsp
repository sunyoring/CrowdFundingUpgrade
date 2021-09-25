<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "java.util.ArrayList, com.kh.faq.model.vo.Faq"%>
    
<%
	ArrayList<Faq> list  = (ArrayList<Faq>)request.getAttribute("list");
	
	ArrayList<Faq> sList = new ArrayList<Faq>();
	ArrayList<Faq> mList = new ArrayList<Faq>();

	if(list != null){
		for(Faq f : list){
			if(f.getTargetUser() == 'S'){ //서포터이면
				sList.add(f);
			}
			else if(f.getTargetUser() == 'M'){	//메이커이면
				mList.add(f);
			}
		} 
	} 
	else{
		sList = null;
		mList = null;
	}
%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/3.7.1/gsap.min.js" integrity="sha512-UxP+UhJaGRWuMG2YC6LPWYpFQnsSgnor0VUF3BHdD83PS/pOpN+FYbZmrYN+ISX8jnvgVUciqP/fILOXDjZSwg==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<link
	href="https://fonts.googleapis.com/css2?family=Black+Han+Sans&family=Jua&fa
         mily=Nanum+Gothic&family=Roboto&display=swap"
	rel="stylesheet">
	
<style>      
    .container_filed{
        width:100%;
        height: auto;
		text-align: center;
    }

    #top{
        width: 1200px;
        text-align: center;
        margin: 0 auto;
        padding-bottom: 40px;
        margin-top: 20px;
    }

    #top p{
        font-weight: bold;
        font-size: 50px;
        margin-bottom: 0px;
    }

    #under{
        display: inline-block;
        width: 100%;
        height : 5px;
        background : gray;
    }

    #contents{
        display: inline-block;
        text-align: center;
        width: 1000px;
        height: auto;
    }

    .userType{
        display: inline-block;
        text-align: center;
        width: 350px;
        height: auto;
        margin: 20px;
        margin-bottom: 40px;
 		background-color: rgba(100, 200, 167, 0.9);
        border-radius: 30px;
        border : 1px solid rgba(100, 200, 167, 1);
    }

    .userType1{
        display: inline-block;
        text-align:center;
        width: auto;
        height: auto;
        min-height: 50px;
       	margin: 45% 20px;
        font-size: 50px;
        font-weight: bold;    
        -webkit-text-stroke: 1px rgba(100, 200, 167, 1);	/* 텍스트 외곽선 */
        color: white;  
        font-family: 'Roboto', sans-serif;
    }

    .boxS, .boxM {
        display: none;
        word-wrap: break-word;
        width: 80%; 
        min-height: 50px;
        text-align: center;
        margin: 1% 10%;
        float: left;
        background-color: rgba(255, 255, 255, 0.7);
        border: 1px solid rgba(255, 255, 255, 1);
        border-radius: 25px;
    }
    
    .boxS:hover, .boxM:hover{
    	 background-color: rgb(255, 255, 255);
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
		url('<%=request.getContextPath()%>/resources/images/faqBannerImg.jpg');
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
 	<!-- <div id = "top">
        <p>FAQ</p>
        <div id="under"></div>
    </div> -->
    
    <div class="pageHead">
			<h1 id="pageTitle">FAQ</h1>
	</div>
		
    <div id="contents">
        <div class="userType" id="t1">
            <div class="userType1" id="s">서포터</div>
            
            <%if(sList != null) {%>
        	<% for(Faq f : sList){ %>
				<button class="box boxS" id=<%=f.getfNo() %>><%= f.getQuestion() %></button>
			<%} }%>

        </div>
        <div class="userType" id="t2">
            <div class="userType1" id="m">메이커</div>
            
            <%if(sList != null) {%>
            <% for(Faq f : mList){ %>
				<button class="box boxM" id=<%=f.getfNo() %>><%= f.getQuestion() %></button>
			<%} }%>

        </div>
    </div>
		
	<script>
		//각 질문을 클릭하면 상세로 이동
		$(function(){
        	$(".box").each(function(i, item){
	            
	            var fNo = $(item).attr('id');	//버튼의 id값 가져옴
	            var id = "#" + fNo;	//버튼의 id값에  '#'을 붙여 id지시자로 만듦
	            
	            $(id).click(function(){
	            	location.href="<%=request.getContextPath()%>/detail.fq?fNo="+fNo;
            	});
        	})
         
		})
	</script>
    <script>
        // userType(t1, t2)에 마우스를 올리면
        // userType1이 마진 20px (위로 올라가게)
        
        var t1 = document.getElementById("t1");
        var t2 = document.getElementById("t2");
        
        var height = document.getElementById("t1").offsetHeight;
        
        // 서포터
        t1.addEventListener('mouseover', (event) => {
        	
            var s = gsap.timeline();
            s.to("#s", {duration: 1, y: -120, opacity: 1})

            var tl = gsap.timeline();
            tl.to(".boxS", {duration: 1, y: -250, display: "block",  opacity: 1});
            
            //div높이 조절
            var btns = document.getElementsByClassName("boxS").length;
            
			var h = (height - 150 + 50 * (btns-1)) + "px";
            document.getElementById("t1").style.height = h;
        });

        t1.addEventListener('mouseout', (event) => {
        	
            var s = gsap.timeline();
            s.to("#s", {duration: 1, y: 0, opacity: 1}).to(".boxS", { opacity: 0}, "-=1");

            var tl = gsap.timeline();
            tl.to(".boxS", {duration: 1,y: 0, display: "none"}).to(".boxS", { opacity: 0}, "-=1"); 
            
            document.getElementById("t1").style.height = "390.19px";
        });

        // 메이커
        t2.addEventListener('mouseover', (event) => {

            var m = gsap.timeline();
            m.to("#m", {duration: 1, y: -120, opacity: 1})

            var t2 = gsap.timeline();
            t2.to(".boxM", {duration: 1, y: -250, display: "block",  opacity: 1});

			var btns = document.getElementsByClassName("boxM").length;
            
			var h = (height - 150 + 50 * (btns-1)) + "px";
            
            document.getElementById("t2").style.height = h;

        });

        t2.addEventListener('mouseout', (event) => {

            var m = gsap.timeline();
            m.to("#m", {duration: 1, y: 0, opacity: 1}).to(".boxM", { opacity: 0}, "-=1");

            var t2 = gsap.timeline();
            t2.to(".boxM", {duration: 1,y: 0, display: "none"}).to(".boxM", { opacity: 0}, "-=1");  
            
            document.getElementById("t2").style.height = "390.19px";
        });
    </script>
    </div>
    <%@ include file="../common/footer.jsp"%>
</body>
</html>