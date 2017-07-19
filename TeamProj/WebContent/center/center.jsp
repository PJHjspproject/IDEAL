<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<script src="canvasjs/canvasjs.min.js"></script>

<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script>
	// Load Charts and the corechart package.
    //google.charts.load('current', {'packages':['corechart']});

    // Draw the pie chart for Sarah's pizza when Charts is loaded.
   
    google.charts.setOnLoadCallback(drawChart2);
    google.charts.setOnLoadCallback(drawChart3);
    google.charts.setOnLoadCallback(drawVisualization);
	
	
</script>
</head>
<body>

<!-- 헤더 슬라이드 쇼   -->
  	<div id="slideshow">
		<div class="slideshow-container">
  	<div class="mySlides fade">
  		<a href="#"><img src="topnav/img/img1.jpg" style="width:30%"></a>
  		<a href="#"><img src="topnav/img/img2.jpg" style="width:40%"></a>
  		<a href="#"><img src="topnav/img/img4.jpg" style="width:30%"></a>
  	</div>
 	<div class="mySlides fade">
 		<a href="#"><img src="topnav/img/img2.jpg" style="width:30%"></a>
  		<a href="#"><img src="topnav/img/img4.jpg" style="width:40%"></a>
  		<a href="#"><img src="topnav/img/img1.jpg" style="width:30%"></a>
  	</div>
  	<div class="mySlides fade"><a href="#"></a>
    	<a href="#"><img src="topnav/img/img4.jpg" style="width:30%"></a>
  		<a href="#"><img src="topnav/img/img1.jpg" style="width:40%"></a>
  		<a href="#"><img src="topnav/img/img2.jpg" style="width:30%"></a>
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
		<div id="comlist1"><a href="#"><img src="topnav/img/amazon.png"></img></a></div>
		<div id="comlist1"><a href="#"><img src="topnav/img/google.png"></img></a></div>
		<div id="comlist1"><a href="#"><img src="topnav/img/sony.png"></img></a></div>
		<div id="comlist1"><a href="#"><img src="topnav/img/vimeo.png"></img></a></div>
		<div id="comlist1"><a href="#"><img src="topnav/img/youtbe.png"></img></a></div>
		<div id="comlist2"><a href="#"><img src="topnav/img/samsung.png"></img></a></div>
		
		</div>
	<div id="container"></div>




	</div>
</body>
</html>