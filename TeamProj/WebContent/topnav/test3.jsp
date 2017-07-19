
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


<script type="text/javascript">
FusionCharts.ready(function () {
    var revenueChart = new FusionCharts({
        type: 'column2d',
        renderAt: 'chart-container',
        width: '604',
        height: '350',
        dataFormat: 'json',
        dataSource: {
            "chart": {
                "caption": "가장 투자자가 많은 투자상품",
                "subCaption": "The most popular investment product",
                "xAxisName": "Month",
                "yAxisName": "Revenues (In USD)",
                "numberPrefix": "$",
                "paletteColors": "#0075c2",
                "bgColor": "#ffffff",
                "borderAlpha": "20",
                "canvasBorderAlpha": "0",
                "usePlotGradientColor": "0",
                "plotBorderAlpha": "10",
                "placevaluesInside": "1",
                "rotatevalues": "1",
                "valueFontColor": "#ffffff",                
                "showXAxisLine": "1",
                "xAxisLineColor": "#999999",
                "divlineColor": "#999999",               
                "divLineIsDashed": "1",
                "showAlternateHGridColor": "0",
                "subcaptionFontBold": "0",
                "subcaptionFontSize": "12"
            },            
            "data": [
                {
                    "label": "Jan",
                    "value": "420000"
                }, 
                {
                    "label": "Feb",
                    "value": "810000"
                }, 
                {
                    "label": "Mar",
                    "value": "720000"
                }, 
                {
                    "label": "Apr",
                    "value": "550000"
                }, 
                {
                    "label": "May",
                    "value": "910000"
                }, 
                {
                    "label": "Jun",
                    "value": "510000"
                }, 
                {
                    "label": "Jul",
                    "value": "680000"
                }, 
                {
                    "label": "Aug",
                    "value": "620000"
                }, 
                {
                    "label": "Sep",
                    "value": "610000"
                }, 
                {
                    "label": "Oct",
                    "value": "490000"
                }, 
                {
                    "label": "Nov",
                    "value": "900000"
                }, 
                {
                    "label": "Dec",
                    "value": "730000"
                }
            ],
            "trendlines": [
                {
                    "line": [
                        {
                            "startvalue": "700000",
                            "color": "#1aaf5d",
                            "valueOnRight": "1",
                            "displayvalue": "Average Investor"
                        }
                    ]
                }
            ]
        }
    }).render();
});






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
<!-- <div id="header_slide"> -->
<!-- 	<div class="slideshow-container"> -->
<!--   	<div class="mySlides fade"> -->
<!--   		<a href=""><img src="img/img1.jpg"></a> -->
<!--   	</div> -->
<!--  	<div class="mySlides fade"> -->
<!--    		 <img src="img/img2.jpg"> -->
<!--   	</div> -->
<!--   	<div class="mySlides fade"> -->
<!--     	<img src="img/img3.jpg"> -->
<!--   	</div></div> -->
  	

<!-- <div class="slideshow-container"> -->
<!-- 	<div class="mySlides fade">  -->
<!-- 	<a href="#"><img src="img/img1.jpg"></a>  -->
<!-- </div> -->
<!-- <div class="mySlides fade">  -->
<!-- <img src="img/img2.jpg">  -->
<!-- </div>  -->
<!-- <div class="mySlides fade">  -->
<!-- <img src="img/img3.jpg">  -->
<!-- </div></div>  -->
  	

<!-- <div class="slideshow-container">  -->
<!-- <div class="mySlides fade">  -->
<!-- <a href=""><img src="img/img1.jpg"></a>  -->
<!-- </div>  -->
<!-- <div class="mySlides fade"> -->
<!--  <img src="img/img2.jpg">  -->
<!-- </div>  -->
<!-- <div class="mySlides fade">  -->
<!-- 	<img src="img/img3.jpg">  -->
<!-- </div></div></div> -->


  	<div id="slideshow">
		<div class="slide1"><img src=""></div><div
		 class="slide2"><img src=""></div><div
		  class="slide3"><img src="">
		</div>
  	
  	
	<div id="wrap2">
		<div id="body_txt3"><h2>오늘의</h2></div>
		<div class="login">안뇽</div>
		<div class="second_div"></div>
		<div class="category">
	<div id="chart-container">FusionCharts will render here</div>
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
