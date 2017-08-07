<%@page import="net.member.db.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/normalize1.css">
<link rel="stylesheet" href="css/main.css">
<script src="js/vendor/modernizr.custom.min.js"></script>
<script src="js/vendor/jquery-1.10.2.min.js"></script>
<script src="js/main.js"></script>
<script type="text/javascript" src="js/fusioncharts.js"></script>
<script type="text/javascript" src="js/jquery.js"></script>

<!-- <script src="./canvasjs/canvasjs.min.js"></script> -->
<%
String memberEmail = (String)session.getAttribute("memberEmail");
session.setAttribute("MemberEmail", memberEmail);
String nickName = (String)session.getAttribute("nickName");
%>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	$('a.login-window').click(function() {
				
		var loginBox = $(this).attr('href');
		$(loginBox).fadeIn(300);
		

		//중앙에 alignment padding + border
		var popMargTop = ($(loginBox).height() + 24) / 2; 
		var popMargLeft = ($(loginBox).width() + 24) / 2; 
		
		$(loginBox).css({ 
			'margin-top' : -popMargTop,
			'margin-left' : -popMargLeft
		});
				

		// 바디에 마스크를 추가
		$('body').append('<div id="mask"></div>');
		$('#mask').fadeIn(300);
		$("#mask").css("z-index", "0");
		$(".login-popup").css("z-index", "50");
		$("#memberEmail").focus();
		
		return false;
	});
	
	// When clicking on the button close or the mask layer the popup closed
	$('a.close, #mask').on('click', function() { 
	  $('#mask , .login-popup').fadeOut(300 , function() {
		$('#mask').remove();  
	}); 
	return false;
	});
});
</script>
<script type="text/javascript">
	var memberEmail = <%=memberEmail%>;
	$(function () {
		$('.investRequest').on('click', function () {
			if(memberEmail == null || memberEmail == ""){
				alert("로그인 후 진행해 주세요");
				$('a.login-window').trigger("click");
				return false;
			}
		});
	});
</script>

</head>
<body>

	<header class="hero-header"> </header>

	<header class="page-header" role="banner">
		<div class="inner">
			<h1 class="site-logo">
				<a href="./Main.ma"><img src="img/logo.png" alt="Shiftbrain"
					height="65" width="200"></a>
			</h1>
			<nav class="primary-nav" role="navigation">
				<ul>
					<li><a href="goInvestmentList.iR">투자하기</a></li>
					<li><a href="investRequest.iR" class="investRequest">캠페인 시작</a></li>
					<li><a href="informationUse.iU">이용안내</a></li>
					<li><a href="boardList.bo">친목게시판</a></li>
				</ul>
				<!--         <div id="clear"></div> -->
			</nav>
<%
	if(memberEmail==null){
%>


			<div class="sub-nav">
				<ul>
					<li><input type="search" class="sear"></li>
					<li><a href="#"><input type="submit" value="SEARCH"></a></li>
					<li><a href="#login-box" class="login-window">login</a></li>
					<li><a href="yak.mf">Joinus</a></li>
				</ul>
			</div>
		<div id="login-box" class="login-popup"><!-- 로그인박스 -->
        <a href="#" class="close"><img src="img/close_pop.png" class="btn_close" title="Close Window" alt="Close" /></a>
          <form method="post" class="signin" action="MemberLoginAction.mf">
                <fieldset class="textbox">
	            	<label class="memberEmail">
		                <span>E-mail</span>
		                <input id="memberEmail" name="memberEmail" type="text" placeholder="이메일을 입력하세요">
	                </label>	                
	                <label class="pass">
		                <span>비밀번호</span>
		                <input id="pass" name="pass" value="" type="password" placeholder="비밀번호를 입력하세요">
	                </label>                
                <input type="submit" class="submit button" value="로그인">
				</fieldset>
          </form>
		</div><!-- 로그인박스 END -->   
			
		<%}else{
					
		%>
			<div class="sub-nav">
				<ul>
					<li><input type="search" class="sear"></li>
					<li><a href="#"><input type="submit" value="SEARCH"></a></li>
					<li>${nickName}님 환영합니다!!</li>
					<li><a href="./Usercheckview.mf">마이페이지</a></li>
					<li><a href="./MemberLogoutAction.mf">로그아웃</a></li>
					<c:if test="${nickName.equals('Admin')}">
						<c:if test="${memberEmail.equals('Admin@Admin.com')}">
							<li><a href="Admin/AdminMain.jsp">관리자페이지</a></li>
						</c:if>
					</c:if>
				</ul>
			</div>
		
		<%} %>
			<div id="container"></div>
		</div>

	</header>
</body>
</html>