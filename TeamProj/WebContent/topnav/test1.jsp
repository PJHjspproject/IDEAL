
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

  <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
     <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
  <script type="text/javascript">
    google.charts.load("current", {packages:['corechart']});
    google.charts.setOnLoadCallback(drawChart);
    function drawChart() {
      var data = google.visualization.arrayToDataTable([
        ["Element", "Density", { role: "style" } ],
        ["Copper", 8.94, "#b87333"],
        ["Silver", 10.49, "silver"],
        ["Gold", 19.30, "gold"],
        ["Platinum", 21.45, "color: #e5e4e2"]
      ]);

      var view = new google.visualization.DataView(data);
      view.setColumns([0, 1,
                       { calc: "stringify",
                         sourceColumn: 1,
                         type: "string",
                         role: "annotation" },
                       2]);

      var options = {
        title: "Density of Precious Metals, in g/cm^3",
        width: 600,
        height: 400,
        bar: {groupWidth: "95%"},
        legend: { position: "none" },
      };
      var chart = new google.visualization.ColumnChart(document.getElementById("columnchart_values"));
      chart.draw(view, options);
  }
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

			<div class="sub-nav">
				<ul>
					<li><input type="search" class="sear"></li>
					<li><a href="#"><input type="submit" value="SEARCH"></a></li>
					<li><a href="#">login</a></li>
					<li><a href="#">Joinus</a></li>
				</ul>
			</div>

		</div>

	</header>
<body>
	<div id="slideshow">
		<div class="slide1"><img src=""></div><div
		 class="slide2"><img src=""></div><div
		  class="slide3"><img src="">
		</div>
	</div>
	<div id="wrap2">
		<div id="body_txt3"><h2>오늘의</h2></div>
		<div class="login">안뇽</div>
		<div class="second_div"></div>
		<div class="category">
		  <div id="columnchart_values" style="width: 300px; height: 200px;"></div>
		</div>
		
		<div id="body_txt4">안뇽22</div>
		<div id="div_c1">A</div>
		<div id="div_c1">B</div>
		<div id="div_c1">C</div>
		<div id="div_c2">D</div>

		<div id="body_txt1">안뇽</div>
		<div id="process_img">안뇽</div>

		<div id="body_txt2">안뇽</div>
		<div id="div_g1">A</div>
		<div id="div_g1">B</div>
		<div id="div_g2">c</div>
	
		<div id="body_txt2">안뇽22</div>
		<div id="div_g1">A</div>
		<div id="div_g1">B</div>
		<div id="div_g2">c</div>
		
		<div id="body_caminv">
		<div id="cam">캠페인 시작</div>
		<div id="inv">투자 시작</div>
		</div>
		
		<div id="body_txt1">
		<div id="comlist1">1</div>
		<div id="comlist1">2</div>
		<div id="comlist1">3</div>
		<div id="comlist1">4</div>
		<div id="comlist1">5</div>
		<div id="comlist2">6</div>
		
		</div>





	</div>
</body>
<footer> 
	<button type="button" class="back-to-top">
    <span class="label"><h3>Top</h3></span>
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
