<%@page import="net.member.db.MemberDAO"%>
<%@page import="net.member.db.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTf-8"
    pageEncoding="UTf-8"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   

  
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTf-8">

<title>Insert title here</title>

</head>




<style>

@import url(//fonts.googleapis.com/earlyaccess/notosanskr.css);
*{
margin:0;
padding:0;
font-family:'Noto Sans KR', sans-serif';
}

div.wrap{

width:1240px;
margin: 0 auto;
position: relative;
padding: 0px;
padding-top: 70px;
padding-bottom: 100px;

}

.user_info{
width:590px;
height: 480px;
float: left;
margin:0 auto;
position:relative;
padding-left:10px;
border: 1px solid #aaa;
/* box-sizing: border-box; */
}



.card{
width:590px;
height: 480px;
float: left;
margin:0 auto;
position:relative;
padding-left: 10px;
left: 32px;
border: 1px solid #aaa;
/* box-sizing:border-box; */
}

#input{
width:80px; 
height: 30px;
}

#cardBank{
width:50px;
height: 32px;
margin-top: 2px;
}

#title{
margin-top: 10px;
}

#member_input{
width:120px; 
height: 30px;
}
#member_input1{
float: left;
position: relative;
}

#title_immoney1{ 
float: left; 
position: relative;
} 

#title_immoney2{ 
padding-left:250px;
} 

.clear{
	clear: both;
}


#pinfo{
overflow: scroll; 
width: 604px; 
height: 300px; 
margin: 30px auto;
position: relative;
float: left;
}

#payment{
overflow: scroll; 
width: 604px; 
height: 300px; 
margin: 30px auto;
position: relative;
float: left;
left: 32px;
}

#123{
padding-left: 30px;
padding-bottom: 50px;
}



#pinfo_ck{

width: 1240px;
float: left;
position:relative;
margin: 10px auto;

}

#payment_ck{

margin-left:32px;
float: left;
position:relative;
}

#button{

margin:0 auto;
float: left;
width:1240px;
position:relative;

}



</style>

<!-- JQuery CDN -->
<script
  src="https://code.jquery.com/jquery-3.2.1.js"
  integrity="sha256-DZAnKJ/6XZ9si04Hgrsxu/8s717jcIzLy3oi35EouyE="
  crossorigin="anonymous"></script>
<!-- JQuery CDN 끝 -->

<!-- 카드 정보 입력 시 공란 입력 방지 및 투자 가능 금액 보다 높은 금액 투자 시 경고 메세지 function -->
<script type="text/javascript">
function checkinput(fr) {
	
	document.getElementById("cardck").innerHTML='';
	document.getElementById("passck").innerHTML='';
	document.getElementById("dayck").innerHTML='';
	document.getElementById("birthck").innerHTML='';
	document.getElementById("moneyck").innerHTML='';
	
	if(document.fr.cardNum1.value == ""){
		document.getElementById("cardck").innerHTML='카드번호를 입력하십시오';
		document.fr.cardNum1.focus();
		//return false;
		
	}
	
	else if(document.fr.cardNum2.value == ""){
		
		document.getElementById("cardck").innerHTML='카드번호를 입력하십시오';
		document.fr.cardNum2.focus();
		//return false;
		
	}
	
	else if(document.fr.cardNum3.value == ""){
		document.getElementById("cardck").innerHTML='카드번호를 입력하십시오';
		document.fr.cardNum3.focus();	
		//return false;		
	}
	
	else if(document.fr.cardNum4.value == ""){
		document.getElementById("cardck").innerHTML='카드번호를 입력하십시오';
		document.fr.cardNum4.focus();
		//return false;
		
	}
	
	else if(document.fr.cardPass.value == ""){
		document.getElementById("passck").innerHTML='비밀번호를 입력하시오';
		document.fr.cardPass.focus();	
		//return false;
		
	}
	
	else if(document.fr.cardExpiryM.value == ""){
		document.getElementById("dayck").innerHTML='카드 유효기간 월을 입력하세요';
		document.fr.cardExpiryM.focus();
		//return false;
		
	}
	
	else if(document.fr.cardExpiryY.value == ""){
		document.getElementById("dayck").innerHTML='카드 유효기간 년도를 입력하세요';
		document.fr.cardExpiryY.focus();	
		//return false;
		
	}
	
	
	else if(document.fr.memberBirth.value == ""){
		document.getElementById("birthck").innerHTML='생년월일을 확인하세요';
		document.fr.cardBirth.focus();
		//return false;
		
	}
	
	else if(document.fr.InvestMoney.value == ""){
		document.getElementById("moneyck").innerHTML='반드시 투자금액을 입력해야함';
		document.fr.InvestMoney.focus();
		//return false;
		
	}
	
	else if(document.fr.InvestMoney.value == 0){
		document.getElementById("moneyck").innerHTML='0원은 입력이 불가능함';
		document.fr.InvestMoney.focus();
		//return false;
		
	}
	
	else if(document.fr.userInfoCk.checked == false){
		alert("개인정보 제3자 제공 및 처리위탁 동의 및 \n결제 및 취소・환불 관련 유의사항에 체크 해주세요");
		document.fr.userInfoCk.focus();	
		
	}
	
	else if(isNaN(document.fr.InvestMoney) > isNaN(document.fr.impInvestMoney)){
		document.getElementById("moneyck").innerHTML='투자가능 금액 보다 높은 금액은 투자하실 수 없습니다.';
		alert(InvestMoney);
		alert(impInvestMoney);
		document.fr.InvestMoney.focus();
		return false;
		
	}else{
		fr.submit();
	}
	
	
}



// $(function(){
// 	$("#InvestMoney").keyup(function(event){
// 		inputNumberFormat(this);
// 	});
// });

// /* 목표 금액 입력시 금액 콤마처리 및 숫자만 입력받기 */
// //콤마찍기
// function comma(str) {
//     str = String(str);
//     return str.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
// }


// //콤마풀기
// function uncomma(str) {
//     str = String(str);
//     return str.replace(/[^\d-]+/g, '');
// }

// //천 단위마다 콤마찍기(단위 증가 시 콤마를 풀고 다시 찍음)
// function inputNumberFormat(obj) {
//     obj.value = comma(uncomma(obj.value));
// }

</script>
<!-- 카드 정보 입력 시 공란 입력 방지 및 투자 가능 금액 보다 높은 금액 투자 시 경고 메세지 function 끝-->


<body>
<%
	int InvestRequestNum = Integer.parseInt(request.getParameter("investRequestNum"));
%>
<!-- InvestRequest 값과 회원정보 넘겨 주기 위한 Top.jsp (합칠 시 삭제) --> 
<jsp:include page="../inc/Top.jsp"/>
<!-- InvestRequest 값과 회원정보 넘겨 주기 위한 Top.jsp 끝 --> 
<div class="wrap">



	<%--회원정보 폼 시작 --%>
	<form action="investmentAction.im"  method="post" name="fr" id="investment">
	<input type="hidden" name="InvestRequestNum" value="<%=InvestRequestNum%>">
	<fieldset class="user_info">
	<legend><h3>결제 유의사항</h3></legend>
	<div id="member_input1">
		<dl>
		
			<dt id="title">회원 이름</dt>
				<dd><input type="text"  id="member_input" value="${meDto.name }" readonly="readonly" name="investName"></dd>
			<dt id="title">회원 이메일</dt>
				<dd><input type="text"  id="member_input" value="${meDto.memberEmail  }" readonly="readonly" name="memberEmail"></dd>
			<dt id="title">전화번호</dt>
				<dd><input type="text"  id="member_input" value="${meDto.phone }" readonly="readonly"></dd>
			<dt id="title">생년월일</dt>
				<dd><input type="text"  id="member_input" value="${meDto.birth  }" readonly="readonly"></dd>
			<dt id="title">투자 프로그램</dt>
				<dd><input type="text"  id="member_input" value="${ivrDto.program  }" readonly="readonly" name="program"></dd>
	</div>	
				<dd><img alt="투자 프로세스" src="img/investment.png" width="450px" height="400px" id="123"></dd>
				
		</dl>
		
	</fieldset>
	<%--회원정보 폼 끝 --%>
	
	<%--카드정보 폼 시작 --%>

	<fieldset class="card">
	<legend><h3>결제 정보</h3> </legend>
	<div>
		<div>
			<dl>
				<dt id="title">신용(체크)카드번호 </dt>
					<dd>
					<select name="cardBank" id="cardBank">
						<option value="KB" selected>국민</option>
						<option value="Woori">우리</option>
						<option value="Hana">하나</option>
						<option value="Shinhan">신한</option>
						<option value="NH">농협</option>
					</select>
						<input type="text" maxlength="4" name="cardNum1" id="input">
						<input type="text" maxlength="4" name="cardNum2" id="input">
						<input type="password" maxlength="4" name="cardNum3" id="input">
						<input type="password" maxlength="4" name="cardNum4" id="input">
						<span id="cardck" style="color: red;"></span>
					</dd>
				
				<dt id="title">비밀번호 앞 2자리</dt>
					<dd id="cardPwd">
						<input type="password" maxlength="2" name="cardPass" style="width:80px; height: 30px;">
						<span id="passck" style="color: red;"></span>
					</dd>
					
				<dt id="title">유효기간</dt>
					<dd>
						<input type="text" maxlength="2" name="cardExpiryM" placeholder="월" style="width:100px; height: 30px;">
						<input type="text" maxlength="2" name="cardExpiryY" placeholder="년" style="width:100px; height: 30px;">
						<span id="dayck" style="color: red;"></span>
					</dd>
					
				<dt id="title">생년월일(주민등록번호 앞 6자리)</dt>
					<dd>
						<input type="text" name="memberBirth"  style="width:160px; height: 30px;" maxlength="6">
						<span id="birthck" style="color: red;"></span>
					</dd>
				
				
				
				<div id="title_immoney1">
				<dt id="title">투자 가능 금액</dt>
					<c:set var="impMoney" value="${ivrDto.successMoney-ivrDto.nowMoney}"/>
					<dd><input type="text" name="impInvestMoney" id="impInvestMoney" style="width:160px; height: 30px;" value="${impMoney}" readonly="readonly">원</dd>
				</div>
				
				<div id="title_immoney2">
				<dt id="title">프로젝트 투자 금액</dt>
					<dd><input type="text" name="InvestMoney" id="InvestMoney" style="width:160px; height: 30px;">원
					&nbsp; <span id="moneyck" style="color: red;"></span></dd>
				</div>
					
			</dl>
		<p>
		<table style="margin-top: 10px;">
			<tr>
				<td style="background-color:#D5D5D5;">
					결제 대행사 : NICE페이먼츠㈜
					<p>
					* 신용(체크)카드 결제만 제공 중으로 기타 결제 수단도 향후 추가 예정 입니다.
				</td>
			</tr>
		</table>
	</div>
	</div>
	</fieldset>
	<%--카드정보 폼 끝 --%>

	
	
	
	
	<%--개인 정보 처리 위탁 동의 체크 폼 --%>
	<div class="agree">
	
	<div id="pinfo">
	
		개인정보 제 3자 제공 및 처리위탁 동의<br/><br/>
	
		1. 회원의 개인정보는 회사의 개인정보 처리방침에 따라 안전하게 보호됩니다.<br/>
		o 회사는 이용자들의 개인정보를 "개인정보 처리방침 "에서 고지한 범위 내에서 사용하며, 이용자의 사전 동의 없이는 동 범위를 초과하여 이용하거나 원칙적으로 이용자의 개인정보를 외부에 공개하지 않습니다.<br/>
		o 회사가 제공하는 서비스를 통하여 결제가 이루어진 경우 참여자 확인 및 결제대행사 정보제공, 프로젝트 개설자(스타터) 등에게 위하여 관련된 정보를 필요한 범위 내에서 거래 업체에게 제공합니다.<br/><br/>
		
		① 개인정보 제 3자 제공: 프로젝트 개설자(스타터)에게 정보제공 안내<br/>
		1) 제공목적: 리워드 구매 확인 및 배송, 이벤트 참여, CS<br/>
		2) 제공정보: IDEAL Crowd Funding 아이디, 성명, 이메일, 휴대폰번호, 상품옵션, 수취인 정보(이름, 주소, 연락처),<br/>
		3) 제공기간: 프로젝트 종료 후 1영업일 이내  제공, 제공 이후 프로젝트 개설자와 유캔스타트가 맺은 계약서의 의거 파기<br/>
		② 개인정보 처리위탁: 수탁업체 정보제공 안내<br/>
		1) 결제대행사: NICE페이먼츠㈜ (NICEPAY)<br/>
		2) 제공정보: 신용/체크카드: 카드번호, 유효기간, 비밀번호, 생년월일<br/><br/>
		
		※ 동의 거부권 등에 대한 고지<br/>
		개인정보 제공은 프로젝트 참여를 위해 꼭 필요합니다. 개인정보 제공을 거부하실 수 있으나, 이 경우 프로젝트 참여에 제한 혹은 리워드를 수령하실 수 없습니다.<br/>
		
		
	</div>
	
	
	

	
	<div id="payment">
		제1조 결제<br/><br/>
		
		① 회원은 회사가 제공하는 결제수단을 통하여 후원에 참여할 수 있습니다.<br/>
		② 회원은 하나의 프로젝트에 중복으로 참여할 수 있으며, 신용카드 및 체크카드로 결제 시 최저 금액은 1원 이상입니다.<br/>
		③ 후원 취소는 프로젝트 마감기한 이내에만 가능하며, 후원 금액을 수정하고 싶은 경우 이전 결제를 취소한 후 재결제를 하여야 합니다.<br/><br/>
		
		제2조 취소 및 환불<br/><br/>
		
		① 결제의 취소 및 환불 규정은 전자상거래 등에서의 소비자 보호에 관한 법률 등 관련 법령을 준수합니다.<br/>
		② 결제의 취소는 프로젝트의 진행 기간 중에만 가능하며, 마감 기한이 지나면 취소 할 수 없습니다. 이는 성공과 동시에 카드사에서 잔액이 참가 되기 때문입니다.<br/>
		③ 정해진 기간 내에 목표 금액이 달성되지 않을 경우에는 프로젝트는 실패하게 되며, 지체 없이 펀딩 기간 종료시까지 모집된 후원금의 예약 결제를 일괄적으로 취소하며, 관련 결제 내역은 자동으로 삭제됩니다.<br/>
		④ 개별 서비스의 성격에 따라 회사는 별도 약관 및 이용조건에 따른 결제 취소 및 환불 규정을 정할 수 있으며, 이 경우 개별 약관 및 이용조건 상의 결제 취소 및 환불규정이 우선 적용됩니다.<br/>
		⑤ 기타 본 약관 및 사이트의 이용안내에 규정되지 않은 결제 취소 및 환불에 대한 사항에 대해서는 소비자피해보상규정에서 정한 바에 따릅니다.<br/>
		⑥ 후원 취소 및 환불 수수료는 결제 수단에 따라 다르게 처리되며, 후원 취소 및 환불 수수료 부과 방법은 회사가 별도로 정한 규정에 따라 달라질 수 있습니다. 후원에 대한 취소 및 환불에 관한 자세한 사항은 IDEAL Crowd Funding 이용약관 “제 25조 취소 및 환불”을 참조하시기 바랍니다.<br/>	
	</div>
	</div>
	
	
			<div id="pinfo_ck">
				<center><input type="checkbox" name="userInfoCk" value="1"> 개인정보 제3자 제공 및 처리위탁 및 결제 및 취소・환불 관련 유의사항에 동의합니다.</center>
			</div>
			<%--개인 정보 처리 위탁 동의 체크 폼 끝--%>
		
		<%--결제 하기 버튼 --%>
	<div id="button">
			<center><input type="button" class="submit" value="결제하기" style="width: 30spx; height: 30px" onclick="checkinput(this.form);">		
	</div>	
	<%--결제 하기 버튼 끝--%>
</form>
<div class="clear"></div>
</div>



</body>
</html>