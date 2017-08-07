<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html lang="ko">
<meta charset="UTF-8">
<head>
<title>IdeaL</title>
<link rel="stylesheet" href="css/main.css">
</head>	
<!-- Body 영역  --> 
<c:set var="center" value="${param.center}"/>

<c:if test="${center == null }">
	<script>
		location.href='./goMain.ma';
	</script>
</c:if>

<body>
	<header>
	<!-- Top include 영역  --> 	]
	<jsp:include page="inc/Top.jsp"/> 
	</header>
	
   	<jsp:include page="${center}"/> 
   	
    
	<!-- footer 영역  -->   	
	<footer>
	<jsp:include page="inc/footer1.jsp"/> 
	</footer>
</body>
</html>
