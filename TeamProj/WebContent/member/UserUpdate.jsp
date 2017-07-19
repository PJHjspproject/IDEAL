<%@page import="net.member.db.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%
	request.setCharacterEncoding("UTF-8");
	//MemberDTO dto = (MemberDTO)session.getAttribute("mdto");
	
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
		if(document.f.phone.value=="")
		{
		 alert("휴대폰번호를 입력하지 않았습니다.")
		 document.f.phone.focus();
		 return false;
		}
		
		
		
		
	}

</script>

</head>
<body>
<div class="wrap">
	<div class="regi_form" align="center">
		<form action="./MemberUpdateProc.mf" method="post" name="f" onsubmit="return check();">
		
				이름 : <input type=text size=15 name=name id="name" value="${mdto.name}">	<br/>
				아이디(email) : <input type=text size=15 id="memberEmail" name="memberEmail" 
									value="${mdto.memberEmail}" readonly="readonly"><br/> 
				별명 : <input type="text" size=15 id=nickName name="nickName" value="${mdto.nickName}" readonly="readonly"><br/>		
				비밀번호 : <input type=password size=15 id="pass" name="pass" placeholder="비밀번호를 반드시 바꾸십시오"><br/> 
				비번확인 : <input type=password size=15 id="pass2" name="pass2" placeholder="비밀번호를 반드시 바꾸십시오"><br/> 
				<label for="birthday">생년월일 :</label>
				<input type="text" id="birth" name="birth" value="${mdto.birth}" readonly="readonly"><br/>
				휴대폰 : <input type="text" size="15" id="phone" name="phone" value="${mdto.phone}"><br/>
				<input type=submit value="가입">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
				<input type=reset value="취소">
			</form>
		</div>
	</div>
</body>
</body>
</html>