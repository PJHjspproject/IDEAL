

<%@page import="net.Email.action.ActionForward"%>
<%@page import="java.util.StringTokenizer"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.ArrayList"%>

<%@page import="java.util.Vector"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="" rel="stylesheet" type="text/css">

<!--[if lt IE 9]>
<script src="http://ie7-js.googlecode.com/svn/version/2.1(beta4)/IE9.js" type="text/javascript"></script>
<script src="http://ie7-js.googlecode.com/svn/version/2.1(beta4)/ie7-squish.js" type="text/javascript"></script>
<script src="http://html5shim.googlecode.com/svn/trunk/html5.js" type="text/javascript"></script>
<![endif]-->
<!--[if IE 6]>
 <script src="../script/DD_belatedPNG_0.0.8a.js"></script>
 <script>
   /* EXAMPLE */
   DD_belatedPNG.fix('#wrap');
   DD_belatedPNG.fix('#main_img');   

 </script>
 <![endif]-->
<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.2.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.6.5/sweetalert2.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.6.5/sweetalert2.min.css">
</head>
<body>
<%
	//한글 처리
	request.setCharacterEncoding("utf-8");

// 	String id = (String)session.getAttribute("id");
//테스트용 임시
String senderEmail = (String)session.getAttribute("memberEmail");
String senderNic = (String)session.getAttribute("nickName");
String admin = "idealDRT@gmail.com";
//로그인 안할시
if(senderNic==null){
	senderNic="비회원";
	senderEmail="비회원";
}

// 	//객체 생성
// 	MemberDAO mdao = new MemberDAO();
	
// 	String toemail = mdao.getMemberEmail(toid);
// 	String sendemail = mdao.getMemberEmail(id);
	
//메일 발송여부 알려주는 텍스트
	String result="";
//발송여부 알려주는 check 값
	String check = (String)request.getAttribute("check");
//check 값 넘어왔는지 consol확인
 	System.out.print(check);
//check 값으로 메일 발송 진행상황 뿌려줌
	if(check!=null){
		if (check.equals("1")){
			result="메일 발송 완료";
			
		}else if(check.equals("0")){result="메일 발송실패";}
	//바깥 if 가 null값이면 발송 실패
	}else if(check==null){
		 result = "메일 발송 대기중";
	}
	
%>
	<div id="wrap">

		<article>
			<form action="MailtoAdmin.em" method="post">
				
				<table id="emailTable">
					<tr>
						<td>받는이</td>
						<td><input type="text" name="admin"readonly="readonly" value="관리자" id="hideinput"></td>
					</tr>
					<tr>
						<td>Email</td>
						<td><input type="text" name="admail" readonly="readonly" value="<%=admin %>" id="hideinput"></td>
					</tr>
					<tr>
						<td>보내는이</td>
						<td><input type="text" name="nickname" value="<%=senderNic%>"></td>
					</tr>
					<tr>
						<td>Email</td>
						<td><input type="text" name="email" value="<%=senderEmail%>"></td>
					</tr>
<!-- 					<tr> 비밀 번호 입력하지 않는걸로~ (관리자 계정 사용)-->
<!-- 						<td>비밀번호 입력</td> -->
<!-- 						<td><input type="password" name="pass"></td> -->
<!-- 					</tr> -->
					<tr>
						<td>글제목</td>
						<td><input type="text" name="subject"></td>
					</tr>
					<tr>
						<td>글내용</td>
						<td>
							<textarea rows="13" cols="40" name="content"></textarea>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<input type="submit" value="메일 보내기" >
							<%--체크값 도 비움 --%>
							<input type="reset" value="다시쓰기" <%check=null; %> onclick="self.open('mailtoadmin.jsp', '메일발송페이지')"  >
							<input type="button" value="닫기" onclick="self.close()">
						</td>
					</tr>
					<tr>
					<%--메일 발송 여부 뿌려주는 곳 --%>
						<td><label><%=result %></label></td>
					</tr>
				</table>
			</form>
		</article>

	</div>
	<script>
// 		function mailCheck(f){
// 			if(f.sendpassword.value==""){
// // 				alert("비밀번호를 입력하세요.");
// // 				f.sendpassword.focus();
// // 				return false;
// 				swal({
// 					title:'비밀번호를 입력해주세요.',
// 					type:'error'
// 				}).then(function(){
// 					f.sendpassword.focus();
// 				})
// 				return false;
// 			}
// 			if(f.subject.value==""){
// // 				alert("글 제목을 입력해 주세요.");
// // 				f.subject.focus();
// // 				return false;
// 				swal({
// 					title:'글 제목을 입력해주세요.',
// 					type:'error'
// 				}).then(function(){
// 					f.subject.focus();
// 				})
// 				return false;
// 			}
// 			if(f.content.value==""){
// // 				alert("내용을 입력해 주세요.");
// // 				f.content.focus();
// // 				return false;
// 				swal({
// 					title:'내용을 입력해주세요.',
// 					type:'error'
// 				}).then(function(){
// 					f.content.focus();
// 				})
// 				return false;
// 			}
// 		}
	</script>
</body>
</html>