<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> checkPage</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<body>
	<script>
	
	$(document).ready(function(){
		var message = "<%=request.getAttribute("msg")%>";
		alert(message);
		location.href="<%=request.getContextPath()%>/lecture.le";
	});
	</script>
	
</body>
</html>