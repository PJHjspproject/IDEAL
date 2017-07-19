<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet" type="text/css" href="css/regi.css">
<%
	String email = (String)request.getAttribute("email");

%>
<script type="text/javascript">
	function check() {
		//비밀번호 입력여부 체크
		if(document.f.pass.value=="")
		{
		 alert("비밀번호를 입력하지 않았습니다.")
		 document.f.pass.focus();
		 return false;
		}
		//비밀번호 길이 체크(4~15자 까지 허용)
		if (document.f.pass.value.length<4 || document.f.pass.value.length>15)
		{
		 alert ("비밀번호를 4~15자까지 입력해주세요.");
		 document.f.pass.focus();
		 document.f.pass.select();
		 return false;
		}

		//비밀번호와 비밀번호 확인 일치여부 체크
		if (document.f.pass.value!=document.f.pass2.value)
		{
		 alert("비밀번호가 일치하지 않습니다")
		 document.f.pass.value="";
		 document.f.pass2.value="";
		 document.f.pass.focus();
		 return false;
		}
		//이름 공백여부
		if(document.f.name.value==""){
			alert("이름를 입력하지 않았습니다.");
			document.f.name.focus();
			return false;
		}
		//생년월일 공백 여부
		if(document.f.date.value==""){
			alert("생년월일를 입력하지 않았습니다.");
			document.f.birthday.focus();
			return false;
		}
		//별명 공백여부
		if(document.f.nickname.value==""){
			alert("별명을 입력하지 않았습니다. 별명을 입력하세요");
			document.f.nickname.focus();
			return false;
		}
		
		document.f.submit();
	}	
</script>
</head>
<body>
<div class="wrap">
	<div class="regi_form" align="center">
		<form action="./MemberJoin.mf" method="post" name="f" onsubmit="return check();">
				이름 : <input type=text size=15 name=name>	<br/>
				아이디(email) : <input type=text size=15 id="memberEmail" name="memberEmail" 
									value="<%=email%>" readonly="readonly"><br/> 
				별명 : <input type="text" size=15 id=nickName name="nickName"><br/>		
				비밀번호 : <input type=password size=15 id="pass" name="pass"><br/> 
				비번확인 : <input type=password size=15 id="pass2" name="pass2"><br/> 
				<label for="birthday">생년월일 :</label>
				<input type="text" id="birth" name="birth" placeholder="주민등록번호6자리"><br/>
				휴대폰 : <input type="text" size="15" id="phone" name="phone"><br/>
				<input type=submit value="가입">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
				<input type=reset value="취소">
			</form>
		</div>
	</div>
</body>
</html>