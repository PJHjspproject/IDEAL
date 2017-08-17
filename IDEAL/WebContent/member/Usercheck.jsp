<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/default.css"><!-- 약속된 css-->
<link rel="stylesheet" href="css/member1.css">
<%
	String email = (String)session.getAttribute("MemberEmail");
	session.setAttribute("MemberEmail", email);
	
	System.out.println(email);
%>
<title>Insert title here</title>
</head>
<body>
<h1>마이페이지</h1>

<div class="wrap0_user">
	<div class="usch_pos">
	<form action="./MemberPassCheckAction.mf" method="post">
		<label><b>비밀번호 입력 :</b></label>
		<input type="password" name="pass" class="passwdin" id="pass" placeholder="비밀번호를 입력하세요">
<!-- 		<br/> -->
		<input class="tomypage" type="submit" value="마이페이지로">
	</form>
	</div>
</div>
</body>
</html>