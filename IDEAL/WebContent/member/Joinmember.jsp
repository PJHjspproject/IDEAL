<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/member1.css">
<meta charset="utf-8">

<%
	String email = (String)request.getAttribute("email");

%>
<script type="text/javascript">

	function nickcheck(){
		
		var nickName = null; 
		if(document.getElementById("nickName")!=null){
			nickName = document.getElementById("nickName").value;
		}
		var url = "member/nickcheck.jsp?nickName="+nickName ;
		open(url, "confirm",
		"toolbar=no, location=no,status=no,menubar=no,scrollbars=no,resizable=no,width=300, height=200");
	}
	
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
    	
        }else if(document.getElementById("result").value==1){

			f.submit();
        }else{
        	alert("닉네임 중복확인을 반드시 해주세요");
        	return false;
        }
	}	
</script>
</head>
<body>
	<div class="wrap0_join">
	<div class="joinmemdiv" align="center">
		<form action="MemberJoin.mf" method="post" name="f">
				<table class="centerpos">
					<tr>
						<td class="td1">
							<p><b>이름 :</b></p>
						</td>
						<td><input type=text size=15 name="name" class="tdname" id="name"></td> 
						<td><span id="nameck" style="color: #a50019;font-weight: 500;"></span><br /></td>
					</tr>
					<tr>
						<td class="td1"><p><b>아이디</b>(Email) :</p></td>
						<td><input type=text size=15 id="memberEmail" class="tdemail"
							name="memberEmail" value="<%=email%>" readonly="readonly"></td>
						<td></td>
					</tr>
					<tr>
						<td class="td1"><p><b>별명 :</b></p></td>
						<td><input type="text" size=15 id=nickName class="tdname" name="nickName"></td>
						<td><input type="button" value="중복확인" onclick="nickcheck()"></td>
						<td><span id="nickck" style="color: #a50019;"></span></td>
					</tr>
					<tr>
						<td class="td1"><p><b>비밀번호 :</b></p></td>
						<td><input type="password" size="15" id="pass" class="tdpass" name="pass">
							</td>
						<td></td>
					</tr>
					<tr>
						<td class="td1"><p><b>비번확인 :</b></p></td>
						<td><input type="password" size="15" id="pass2" class="tdpass2" name="pass2"></td>
						<td><span id="pwsame" style="color: #a50019;"></span></td>
					</tr>
					<tr>
						<td class="td1"><label for="birthday"><p><b>생년월일 :</b></p></label></td>
						<td><input type="text" size="15" id="birth" name="birth" class="tdbirth"
							placeholder="주민등록번호6자리"> </td>
						<td><span id="birthck"
							style="color: #a50019;"></span></td>
					</tr>
					<tr>
						<td class="td1"><p><b>휴대폰 :</b></p></td>
						<td><input type="text" size="15" id="phone" name="phone" class="tdphone"
							placeholder="-를 제외하고 입력하세요"></td>
						<td><span id="phoneck" style="color: #a50019;"></span></td>
					</tr>
				</table>
				<input type="hidden" id="result" name="result" value="0">
				<input class="membercan1" type=reset value="취소">
				<input class="memberjoin1" type=button value="가입" onclick="check(this.form)"> 
				
				
		</form>
	</div>
	</div>

</body>
</html>