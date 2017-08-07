<%@page import="net.member.db.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/default.css"><!-- 약속된 css-->
<title>Insert title here</title>
<%
	request.setCharacterEncoding("UTF-8");
	//MemberDTO dto = (MemberDTO)session.getAttribute("mdto");
	
%>
<script type="text/javascript">
function check(f) {

	if(f.pass.value==""){
	 	document.getElementById('pwsame').innerHTML='비밀번호를 입력하시오';
	 	f.pass.focus();

	}
	//비밀번호 길이 체크(4~15자 까지 허용)
	else if (f.pass.value.length<4 || f.pass.value.length>15){
		document.getElementById('pwsame').innerHTML='비밀번호는 4자리이상 15자리 이하입니다.';
	 	f.pass.focus();
	 	f.pass.select();
//		 	return false;
	}
	//비밀번호와 비밀번호 확인 일치여부 체크
	else if (f.pass.value!=f.pass2.value){
	 	document.getElementById('pwsame').innerHTML = '비밀번호가 다릅니다 다시 확인하세요';
	 	f.pass.value="";
	 	f.pass2.value="";
	 	f.pass.focus();
//		 	return false;
	}else if(f.phone.value==""){
    	document.getElementById('phoneck').innerHTML='휴대폰번호는 반드시 입력해야합니다.';
    	f.phone.focus();
	
    }else{

		f.submit();
    }
}	
</script>

</head>
<body>
<div class="wrap">
	<div class="regi_form" align="center">
		<form action="./MemberUpdateProc.mf" method="post" id="f" name="f">
		
				이름 : <input type=text size=15 name=name id="name" value="${mdto.name}">
						<span id="nameck" style="color: red;"></span><br/>
				아이디(email) : <input type=text size=15 id="memberEmail" name="memberEmail" 
									value="${mdto.memberEmail}" readonly="readonly"><br/> 
				별명 : <input type="text" size=15 id=nickName name="nickName" value="${mdto.nickName}" readonly="readonly"><br/>		
				비밀번호 : <input type=password size=15 id="pass" name="pass" placeholder="비밀번호를 반드시 바꾸십시오"><br/> 
				비번확인 : <input type=password size=15 id="pass2" name="pass2" placeholder="비밀번호를 반드시 바꾸십시오">
				<span id="pwsame" style="color: red;"></span><br/> 
				<label for="birthday">생년월일 :</label>
				<input type="text" id="birth" name="birth" value="${mdto.birth}" readonly="readonly"><br/>
				휴대폰 : <input type="text" size="15" id="phone" name="phone" value="${mdto.phone}">
				<span id="phoneck" style="color: red;"></span><br/>
				<input type=button value="회원수정" onclick="check(this.form)">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
				<input type=reset value="취소">
			</form>
		</div>
	</div>
</body>
</html>