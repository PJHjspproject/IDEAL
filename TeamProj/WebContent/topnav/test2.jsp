
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html lang="ko">
<meta charset="UTF-8">
<head>
<title>INVESTMENT</title>
<link rel="stylesheet" href="css/normalize.css">
<link rel="stylesheet" href="css/main.css">
<!-- Jquery 코드 -->
<script src="js/vendor/modernizr.custom.min.js"></script>
<script src="js/vendor/jquery-1.10.2.min.js"></script>
<script src="js/main.js"></script>

<!-- 그래프 코드 -->
<script type="text/javascript" src="js/fusioncharts.js"></script>
<script type="text/javascript" src="js/jquery.js"></script>
<script src="../canvasjs/canvasjs.min.js"></script>

<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script>
	// 구글 차트 로드 코드
    google.charts.load('current', {'packages':['corechart']});	
</script>
</head>

<!-- body -->
<body>

	<!-- header logo nav -->
	<header class="hero-header"> </header>

	<header class="page-header" role="banner">
		<div class="inner">
			<h1 class="site-logo">
				<a href="#"><img src="./img/logo.png" alt="Shiftbrain"
					height="65" width="200"></a>
			</h1>
			
			<!-- 메뉴 이동 nav -->
			<nav class="primary-nav" role="navigation">
				<ul>
					<li><a href="#">투자하기</a></li>
					<li><a href="#">투자신청</a></li>
					<li><a href="#">이용안내</a></li>
					<li><a href="#">투자클럽</a></li>
				</ul>
				<!--         <div id="clear"></div> -->
			</nav>
			<!-- 검색, 로그인, 회원가입  -->
			<div class="sub-nav">
				<ul>
					<li><input type="search" class="sear"></li>
					<li><a href="#"><input type="submit" value="SEARCH"></a></li>
					<li><a href="#">login</a></li>
					<li><a href="#">Joinus</a></li>
				</ul>
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
			<div id="chart-container"></div>
		</div>
		
		<div id="body_txt4">카테고리</div>

    <div id="images">
        <div class="inner">
            <p class="pad1"><img src="img/tech1.jpg" width="286px"><strong>Technology</strong><span></span></p>		
        </div>
         <div class="inner">
            <p class="pad1"><img src="img/nat1.jpg" width="286px"><strong>Environment</strong><span></span></p>		
        </div>
         <div class="inner">
            <p class="pad1"><img src="img/edu1.jpg" width="286px"><strong>Education</strong><span></span></p>		
        </div>
         <div class="inner">
            <p><img src="img/art2.jpg" width="286px"><strong>Art/Music</strong><span></span></p>		
        </div>
	</div>
	
		
	<div id="clear-right"></div>
		
		<div class="body_txt1">안뇽</div>
		<div id="process_img">
			<div id="buttons3">
					<p>펀딩오픈 과정</p>
				<div class="inner2 clearfix">
					<button>
						<span> <img src="img/01_gr.png"> <img
							src="img/01_bl.png"><br> 투자유지신청
						</span>
					</button>
					<button>
						<span> <img src="img/02_gr.png"> <img
							src="img/02_bl.png"><br> 등록심사
						</span>
					</button>
					<button>
						<span> <img src="img/03_gr.png"> <img
							src="img/03_bl.png"><br> 계약체결
						</span>
					</button>
					<button>
						<span> <img src="img/04_gr.png"> <img
							src="img/04_bl.png"><br> 자료작성
						</span>
					</button>
					<button>
						<span> <img src="img/04_gr.png"> <img
							src="img/04_bl.png"><br> 펀딩오픈
						</span>
					</button>
				</div>
			</div>
		</div>
		<div id="clear"></div>
	
		<!-- google 그래프  -->
		<div id="body_txt2">안뇽</div>
		<div class="div_g1 g0_a">
			 <div id="chart_div2" style="border: 1px solid #ccc;"></div>
		</div><!--  
		--><div class="div_g1 g0_b">
			<div id="chart_div3" style="border: 1px solid #ccc;"></div>
		</div><!-- 
		--><div class="div_g1 g0_c">
		 <div id="chart_div" style="border: 1px solid #ccc;"></div></div>

		<!--   -->
		<div id="body_txt2">허전해서 이안에 뭐가 들어가야 할 것 같아</div>
		
		<!-- 투자상품 뿌려주는 부분  -->
		<div id="img3">
			<div class="inner3">
				<div class="div_g2 g1_a">
					<p>
						<img src="img/02_img.jpg"><strong><br/>Sitta1</strong><span></span>
					</p>
				</div><!--
				--><div class="div_g2 g1_a">
					<p>
						<img src="img/02_img.jpg"><strong><br/>Sitta2</strong><span></span>
					</p>
				</div><!--
				--><div class="div_g2 g1_b">
					<p>
						<img src="img/02_img.jpg"><strong><br/>Sitta3</strong><span></span>
					</p>
				</div><!--
				--><div class="div_g2 g1_c">
					<p>
						<img src="img/02_img.jpg"><strong><br/>Sitta4</strong><span></span>
					</p>
				</div>
				<div class="div_g2 g1_a">
					<p>
						<img src="img/02_img.jpg"><strong><br/>Sitta5</strong><span></span>
					</p>
				</div><!--
				--><div class="div_g2 g1_a">
					<p>
						<img src="img/02_img.jpg"><strong><br/>Sitta6</strong><span></span>
					</p>
				</div><!--
				--><div class="div_g2 g1_b">
					<p>
						<img src="img/02_img.jpg"><strong><br/>Sitta7</strong><span></span>
					</p>
				</div><!--
				--><div class="div_g2 g1_c">
					<p>
						<img src="img/02_img.jpg"><strong><br/>Sitta8</strong><span></span>
					</p>
				</div>
			</div>
		</div>
		
		
		<!-- 캠페인 시작/ 투자 시작 링크  -->
		<div id="body_caminv">
		<div id="cam">캠페인 시작</div>
		<div id="inv">투자 시작</div>
		</div>
		
		<div id="clear"></div>

	</div>
</body>

<!-- footer 부분  -->
<footer> 
	<!-- 상단 nav로 이동 버튼  -->
	<button type="button" class="back-to-top">
    <h3><span class="label">Top</span></h3>
   	</button>
   	
   		<!-- footer 영역 지정  -->
		<div id="foot_container">
		
		 <div class="body_txt1">
			<div class="comlist1 l1"><a href="#"><img src="img/amazon.png"></img></a></div>
			<div class="comlist1 l2"><a href="#"><img src="img/google.png"></img></a></div>
			<div class="comlist1 l3"><a href="#"><img src="img/sony.png"></img></a></div>
			<div class="comlist1 l4"><a href="#"><img src="img/vimeo.png"></img></a></div>
			<div class="comlist1 l5"><a href="#"><img src="img/youtbe.png"></img></a></div>
			<div class="comlist1 l6"><a href="#"><img src="img/samsung.png"></img></a></div>	
		 </div>
		
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
