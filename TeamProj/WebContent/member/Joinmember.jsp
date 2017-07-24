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
	function check(f) {
		var re =  /^[0-9]*$/;
		//이름 공백여부
		if(f.name.value==""){
			document.getElementById('nameck').innerHTML='이름을 입력하세요';
			f.name.focus();
// 			return false;
		}
		//별명 공백여부
		else if(f.nickName.value==""){
			document.getElementById('nickck').innerHTML='별명을 입력하세요';
			f.nickname.focus();
// 			return false;
		}
		//비밀번호 입력여부 체크

		else if(f.pass.value==""){
		 	document.getElementById('pwsame').innerHTML='비밀번호를 입력하시오';
		 	f.pass.focus();
// 		 	return false;
		}
		//비밀번호 길이 체크(4~15자 까지 허용)
		else if (f.pass.value.length<4 || f.pass.value.length>15){
			document.getElementById('pwsame').innerHTML='비밀번호는 4자리이상 15자리 이하입니다.';
		 	f.pass.focus();
		 	f.pass.select();
// 		 	return false;
		}
		//비밀번호와 비밀번호 확인 일치여부 체크
		else if (f.pass.value!=f.pass2.value){
		 	document.getElementById('pwsame').innerHTML = '비밀번호가 다릅니다 다시 확인하세요';
		 	f.pass.value="";
		 	f.pass2.value="";
		 	f.pass.focus();
// 		 	return false;
		}
		//생년월일 공백 여부
		else if(f.birth.value==""){
			document.getElementById('birthck').innerHTML='생년월일을 입력하시오';
			f.birth.focus();
// 			return false;
		}
		else if(!re.test(f.birth.value)) {
        	document.getElementById('birthck').innerHTML='생년월일은 반드시 숫자여야합니다.';
	        f.birth.value="";
	        f.birth.focus();
// 	      	return false;
	      
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
		<form action="./MemberJoin.mf" method="post" name="f">
				이름 : <input type=text size=15 name="name" id="name">
				<span id="nameck" style="color: red;"></span><br/>
				
				아이디(email) : <input type=text size=15 id="memberEmail" name="memberEmail" 
									value="<%=email%>" readonly="readonly"><br/> 
									
				별명 : <input type="text" size=15 id=nickName name="nickName">
				<span id="nickck" style="color: red;"></span><br/>	
				
				비밀번호 : <input type="password" size="15" id="pass" name="pass"><br/>
				 
				비번확인 : <input type="password" size="15" id="pass2" name="pass2">
				<span id="pwsame" style="color: red;"></span><br/>
				
				<label for="birthday">생년월일 :</label>
				<input type="text" id="birth" name="birth" placeholder="주민등록번호6자리">
				<span id="birthck" style="color: red;"></span><br/> 
				
				휴대폰 : <input type="text" size="15" id="phone" name="phone" placeholder="-를 제외하고 입력하세요"><br/>
				<span id="phoneck" style="color: red;"></span><br/>
				<input type=button value="가입"  onclick="check(this.form)">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
				<input type=reset value="취소">
			</form>
		</div>
	</div>
</body>
</html>