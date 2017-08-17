<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/default.css">
<link rel="stylesheet" href="css/member1.css">
<title>Insert title here</title>
<script type="text/javascript">
	
	function emailcheck() {
		var email = null; 
		if(document.getElementById("email")!=null){
			email = document.getElementById("email").value;
		}
		var url = "member/emailcheck.jsp?email="+email;
		open(url, "confirm",
		"toolbar=no, location=no,status=no,menubar=no,scrollbars=no,resizable=no,width=300, height=200");
	}
	function check() {
		var email  = null;
		var result = document.getElementById("result").value;
		if(document.getElementById("email") !=null){
		 	email = document.getElementById("email").value;
		}
		
		
		var url = "member/authon.jsp?email="+email+"&result="+result;
		var myWindow =window.open(url, "인증받기", "width=500,height=300");
		return false;
		}
		function move() {
		var result = document.getElementById("result").value;
		var email = document.getElementById("email").value;
			
		if(result==1){
			document.f.submit();
		}else{
			return false;
		}
		
	}
</script>
</head>
<body>
<div class="wrap0">
	<div align="center" class="auth">
	<form action="./Joinmember.mf" name="f" id="f" method="post" onsubmit="return check();">
	
	<b>이메일 :</b> <input class="emailin" type="text" id="email" name="email"><br/>
	<input class="mailcheck" type="button" value="1. 중복체크" onclick="return emailcheck();">
	<br/>
	<input type="hidden" name="result"  id="result" value="0">
		
	<input class="mailreceive" type="submit" value="2. 인증받기">
	</form>
	</div>
</div>
</body>
</html>