<%@page import="net.member.db.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/member1.css">
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
<style type="text/css">
.content{
	text-align: center;
}

</style>
</head>
<body>
<div class="wrapup">
	<div class="regi_form" align="center">
		<form action="./MemberUpdateProc.mf" method="post" id="f" name="f">
			<table class="centerup">
				<tr>
					<td class="td1"><b>이름 :</b></td>
					<td><input type=text size=15 name=name class="tdnameup" id="name" value="${mdto.name}"></td>
					<td><span id="nameck" style="color: red;"></span><br/></td>
				</tr>
				<tr>
					<td class="td1"><b>아이디</b>(Email) :</td>
					<td><input type=text size=15 id="memberEmail" class="tdemailup" name="memberEmail" 
									value="${mdto.memberEmail}" readonly="readonly"><br/></td> 
				</tr>
				<tr>
					<td class="td1"><b>별명 :</b></td>
					<td><input type="text" size=15 id=nickName class="tdnameup" name="nickName" value="${mdto.nickName}" readonly="readonly"><br/></td>	
				</tr>
				<tr>
					<td class="td1"><b>비밀번호 :</b></td> 
					<td><input type=password size=15 id="pass" class="tdpassup" name="pass" placeholder="비밀번호를 반드시 바꾸십시오"><br/></td> 
				</tr>
				<tr>
					<td class="td1"><b>비번확인:</b></td> 
					<td><input type=password size=15 id="pass2" class="tdpass2up" name="pass2" placeholder="비밀번호를 반드시 바꾸십시오"></td>
					<td><span id="pwsame" style="color: red;"></span><br/></td>
				</tr>
				<tr>
					<td class="td1"><label for="birthday"><b>생년월일 :</b></label></td>
					<td><input type="text" id="birth" class="tdbirthup" name="birth" value="${mdto.birth}" readonly="readonly"><br/></td>
				</tr>
				<tr>
					<td class="td1"><b>휴대폰 :</b></td> 
					<td><input type="text" size="15" id="phone" class="tdphoneup" name="phone" value="${mdto.phone}"></td>
					<td><span id="phoneck" style="color: red;"></span><br/></td>
				</tr>
				<tr>
					<td colspan="2">
					<input class="canbt1" type="button" onclick="history.back()" value="취소">
					<input class="modify1" type="button" value="회원수정" onclick="check(this.form)">
					</td>
				</tr>
			</table>
			<div class="clear"></div>
		</form>
		</div>
	</div>
</body>
</html>