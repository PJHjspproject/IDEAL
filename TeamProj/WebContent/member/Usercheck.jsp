<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<%
	String email = (String)session.getAttribute("MemberEmail");
	session.setAttribute("MemberEmail", email);
	
	System.out.println(email);
%>
<title>Insert title here</title>
</head>
<body>
<h1>마이페이지</h1>

<center>
	<form action="./MemberPassCheckAction.mf" method="post">
		<label>비밀번호 입력 :</label>
		<input type="password" name="pass" id="pass" placaholder="비밀번호를 입력하세요">
		<input type="submit" value="마이페이지로">
	</form>
</center>
</body>
</html>