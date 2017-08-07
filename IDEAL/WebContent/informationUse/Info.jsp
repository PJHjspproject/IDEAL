<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%--jstl 라이브러리 사용을 위한 선언 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Insert title here</title>
</head>
<body>
<input type="button" class="btn1" value="FAQ">
<input type="button" class="btn2" value="공지사항">
<input type="button" class="btn3" value="1:1문의">
<form action="Information.iU" class="info" method="post"></form>
<form action="list.no" class="notice" method="post"></form>
<form action="question.qU" class="question" method="post"></form>
</body>
</html>