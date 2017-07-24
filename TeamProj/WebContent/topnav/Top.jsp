<%@page import="net.member.db.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="topnav/css/normalize.css">
<link rel="stylesheet" href="topnav/css/main.css">
<script src="topnav/js/vendor/modernizr.custom.min.js"></script>
<script src="topnav/js/vendor/jquery-1.10.2.min.js"></script>
<script src="topnav/js/main.js"></script>
<script type="text/javascript" src="topnav/js/fusioncharts.js"></script>
<script type="text/javascript" src="topnav/js/jquery.js"></script>

<script src="./canvasjs/canvasjs.min.js"></script>

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
</head>
<body>

	<header class="hero-header"> </header>

	<header class="page-header" role="banner">
		<div class="inner">
			<h1 class="site-logo">
				<a href="#"><img src="topnav/img/logo.png" alt="Shiftbrain"
					height="65" width="200"></a>
			</h1>
			<nav class="primary-nav" role="navigation">
				<ul>
					<li><a href="#">투자하기</a></li>
					<li><a href="#">투자신청</a></li>
					<li><a href="#">이용안내</a></li>
					<li><a href="#">투자클럽</a></li>
				</ul>
				<!--         <div id="clear"></div> -->
			</nav>
<%
	String email = (String)session.getAttribute("MemberEmail");
	session.setAttribute("MemberEmail", email);
	String nickName = (String)session.getAttribute("nickName");
	if(email==null){
%>


			<div class="sub-nav">
				<ul>
					<li><input type="search" class="sear"></li>
					<li><a href="#"><input type="submit" value="SEARCH"></a></li>
					<li><a href="#login-box" class="login-window">login</a></li>
					<li><a href="./yak.mf">Joinus</a></li>
				</ul>
			</div>
		<div id="login-box" class="login-popup"><!-- 로그인박스 -->
        <a href="#" class="close"><img src="images/member/close_pop.png" class="btn_close" title="Close Window" alt="Close" /></a>
          <form method="post" class="signin" action="./MemberLoginAction.mf">
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
				</ul>
			</div>
		
		<%} %>
			<div id="container"></div>
		</div>

	</header>
</body>
</html>