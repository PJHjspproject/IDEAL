
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html lang="ko">
<meta charset="UTF-8">
<head>
<title>INVESTMENT</title>
<link rel="stylesheet" href="css/normalize.css">
<link rel="stylesheet" href="css/main.css">
<script src="js/vendor/modernizr.custom.min.js"></script>
<script src="js/vendor/jquery-1.10.2.min.js"></script>
<script src="js/main.js"></script>

<script type="text/javascript" src="js/fusioncharts.js"></script>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/fusioncharts.jqueryplugin.js"></script>
<script src="../canvasjs/canvasjs.min.js"></script>

<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script>
	// Load Charts and the corechart package.
    google.charts.load('current', {'packages':['corechart']});

    // Draw the pie chart for Sarah's pizza when Charts is loaded.
   
    google.charts.setOnLoadCallback(drawChart2);
    google.charts.setOnLoadCallback(drawChart3);
    google.charts.setOnLoadCallback(drawVisualization);
	
	
</script>


</head>
<body>

	<header class="hero-header"> </header>

	<header class="page-header" role="banner">
		<div class="inner">
			<h1 class="site-logo">
				<a href="#"><img src="./img/logo.png" alt="Shiftbrain"
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
	if(email==null){
%>

			<div class="sub-nav">
				<ul>
					<li><input type="search" class="sear"></li>
					<li><a href="#"><input type="submit" value="SEARCH"></a></li>
					<li><a href="#login-box" class="login-window">login</a></li>
					<li><a href="./yak.mf'">Joinus</a></li>
				</ul>
			</div>

		<%}else{
					
		%>
			<div class="sub-nav">
				<ul>
					<li><input type="search" class="sear"></li>
					<li><a href="#"><input type="submit" value="SEARCH"></a></li>
					<li><%=email %>님 환영합니다!!</li>
					<li><a href="#">마이페이지</a></li>
				</ul>
			</div>
		
		<%} %>
			<div id="container"></div>
		</div>

	</header>
<body>



<!-- 헤더 슬라이드 쇼   -->
  	<div id="slideshow">
		<div class="slideshow-container">
  	<div class="mySlides fade">
  		<a href="#"><img src="img/img1.jpg" style="width:30%"></a>
  		<a href="#"><img src="img/img2.jpg" style="width:40%"></a>
  		<a href="#"><img src="img/img4.jpg" style="width:30%"></a>
  	</div>
 	<div class="mySlides fade">
 		<a href="#"><img src="img/img2.jpg" style="width:30%"></a>
  		<a href="#"><img src="img/img4.jpg" style="width:40%"></a>
  		<a href="#"><img src="img/img1.jpg" style="width:30%"></a>
  	</div>
  	<div class="mySlides fade"><a href="#"></a>
    	<a href="#"><img src="img/img4.jpg" style="width:30%"></a>
  		<a href="#"><img src="img/img1.jpg" style="width:40%"></a>
  		<a href="#"><img src="img/img2.jpg" style="width:30%"></a>
  	</div></div>
  	</div>

 <!-- Body content 영역  --> 	
	<div id="wrap2">
		
		<div id="body_txt3"><h2>오늘의</h2></div>
		<div class="login">안뇽</div>
		<div class="second_div"></div>
		<div class="category">
	<div id="chart-container">

	</div>
	
		</div>
		<div id="container"></div>
		<div id="body_txt4">카테고리</div>
		<div class="div_c1">A</div>
		<div class="div_c1">B</div>
		<div class="div_c1">C</div>
		<div class="div_c2">D</div>
	<div id="container"></div>
	
		<div id="body_txt1">안뇽</div>
		<div id="process_img">과정보여주는 이미지</div>
	<div id="container"></div>
	
		<div id="body_txt2">안뇽</div>
		<div class="div_g1 g0_a">
			 <div id="chart_div2" style="border: 1px solid #ccc;"></div>
		</div><!--  
		--><div class="div_g1 g0_b">
			<div id="chart_div3" style="border: 1px solid #ccc;"></div>
		</div><!-- 
		--><div class="div_g1 g0_c">
		 <div id="chart_div" style="border: 1px solid #ccc;"></div></div>
	<div id="container"></div>
	
		<div id="body_txt2">안뇽22</div>
		<div class="div_g1 g1_a">어디있는거지</div><!--
		--><div class="div_g1 g1_b">B</div><!-- 
		--><div class="div_g1 g1_c">c</div>
		<div id="container"></div>
		<div id="body_caminv">
		<div id="cam">캠페인 시작</div>
		<div id="inv">투자 시작</div>
		</div>
	<div id="container">
	
	</div>
		
		<div id="body_txt1">
		<div id="comlist1"><a href="#"><img src="img/amazon.png"></img></a></div>
		<div id="comlist1"><a href="#"><img src="img/google.png"></img></a></div>
		<div id="comlist1"><a href="#"><img src="img/sony.png"></img></a></div>
		<div id="comlist1"><a href="#"><img src="img/vimeo.png"></img></a></div>
		<div id="comlist1"><a href="#"><img src="img/youtbe.png"></img></a></div>
		<div id="comlist2"><a href="#"><img src="img/samsung.png"></img></a></div>
		
		</div>
	<div id="container"></div>




	</div>
</body>
<footer> 
	<button type="button" class="back-to-top">
    <h3><span class="label">Top</span></h3>
   	</button>
		<div id="foot_container">
			<div class="f_div1">eeeeeeeeeee11</div>
			<div class="f_div2">
			 
			 <small class="copyright">Copyright © investment</small>
			 
		   
		
			
		
		 <div class="inner clearfix">
        <aside class="social-links">
            <ul>
            	  <li class="item-twitter">
                    <a href="https://twitter.com" target="_blank">
 						 <img src="img/tw.jpg">                   
                    </a>
                </li>
                <li class="item-facebook">
                    <a href="https://www.facebook.com" target="_blank">
                        <img src="img/fb.jpg">
                    </a>
                </li>
              
            </ul>
        </aside>
         </div>
    </div>
		
		</div>
		
   


</footer>



</body>
</html>
