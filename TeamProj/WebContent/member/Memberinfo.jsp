<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%
	String email = (String)session.getAttribute("MemberEmail");

	session.setAttribute("MemberEmail", email);
%>
<title>Insert title here</title>

<style type="text/css">
.content{
	text-align: center;
}
</style>
</head>
<body>
<div class="wrap">
	<div class="content">
	<h1>회원 정보 보기</h1>
	
	<a href="./MemberView.mf">회원 수정</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
	<a href="./MemberOutAction.mf">회원 탈퇴</a>
	</div> 
</div>
</body>
</html>