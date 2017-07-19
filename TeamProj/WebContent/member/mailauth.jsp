<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function check() {
		var email  = null;
		var result = document.getElementById("result").value;
		if(document.getElementById("email") !=null){
		 	email = document.getElementById("email").value;
		}
		
		
		var url = "member/authon.jsp?email="+email+"&result="+result;
		var myWindow =window.open(url, "인증받기", "width=500,height=300");
		
		
	}
	function move(){
		
		var result = document.getElementById("result").value;
		var email = document.getElementById("email").value;
		if(result==1){
			
			document.f.submit();
		}
		return false;
	}
	

</script>
</head>
<body>
	<div class="auth">
	<form action="./Joinmember.mf" name="f" id="f" method="post" onsubmit="return move();">
	
	이메일 : <input type="text" id="email" name="email"><br/>
	<input type="hidden" name="result"  id="result" value="0">
	
	&nbsp;&nbsp;&nbsp;&nbsp;
	<input type="button" value="인증받기" onclick="return check();">
	<input type="submit" value="가입하기">
	</form>
</div>
</body>
</html>