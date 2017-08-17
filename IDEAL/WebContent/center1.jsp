
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE HTML>
<html lang="ko">
<meta charset="UTF-8">
<head>
<title>IdeaR</title>
<!-- css 링크-->
<link rel="stylesheet" href="css/normalize1.css">
<link rel="stylesheet" href="css/main1.css">
<!-- Jquery 코드 -->
<script src="js/vendor/modernizr.custom.min.js"></script>
<script src="js/vendor/jquery-1.10.2.min.js"></script>
<script src="js/main.js"></script>
<script type="text/javascript">
var queryObject = "";
var queryObjectLen = "";
var queryObject1 = "";
var queryObjectLen1 = "";
var jsonOb = "";
//컬럼차트
function chartload() {
	$.ajax({
        type : 'POST',
        url : 'testchart.jsp',
        dataType : 'json',
        success : function(data) {
            
           	
            queryObject = eval('(' + JSON.stringify(data,null, 2) + ')'); 
            // stringify : 인자로 전달된 자바스크립트의 데이터(배열)를 JSON문자열로 바꾸기.   
            // eval: javascript 코드가 맞는지 검증하고 문자열을 자바스크립트 코드로 처리하는 함수 
            // queryObject.barlist[0].city ="korea"
 			
            queryObjectLen = queryObject.barlist.length;
            // queryObject.empdetails 에는 4개의 Json 객체가 있음 
 
           
            // alert(queryObject.barlist[0].city +queryObject.barlist[0].per );
            google.charts.load('current', {
        		packages : [ 'corechart', 'bar' ]
        	});
        	google.charts.setOnLoadCallback(drawBasic);
        	
        	
        },
        error : function(xhr, type) {
            alert('server error occoured');
        }
    });
}
//바차트
function barchartload() {
	$.ajax({
        type : 'POST',
        url : 'barchart.jsp',
        dataType : 'json',
        success : function(data) {
            
           	
            queryObject1 = eval('(' + JSON.stringify(data,null, 2) + ')'); 
            // stringify : 인자로 전달된 자바스크립트의 데이터(배열)를 JSON문자열로 바꾸기.   
            // eval: javascript 코드가 맞는지 검증하고 문자열을 자바스크립트 코드로 처리하는 함수 
            // queryObject.barlist[0].city ="korea"
 			
            queryObjectLen1 = queryObject1.barList.length;
            // queryObject.empdetails 에는 4개의 Json 객체가 있음 
 
           
            // alert(queryObject.barlist[0].city +queryObject.barlist[0].per );
            google.charts.load('current', {
            	packages : [ 'corechart', 'bar' ]
        	});
        	google.charts.setOnLoadCallback(drawTitleSubtitle);

        },
        error : function(xhr, type) {
            alert('server error occoured');
        }
    });
}
//파이차트
function piechartload() {
	$.ajax({
		data:'post',
		dataType:'json',
		url:'pieChart.jsp',
		success: function (data) {
			
			jsonOb = eval('('+JSON.stringify(data)+')');
			
			google.charts.load('current', {'packages':['corechart']});
			google.charts.setOnLoadCallback(drawChart);
			
		}
	});
}

//바차트
function drawTitleSubtitle() {

	var data = new google.visualization.DataTable();
	data.addColumn('string', '카테고리');
	data.addColumn('number', '목표액');
	data.addColumn('number', '투자액');
	for (var i = 0; i < queryObjectLen1; i++) {
		var category = queryObject1.barList[i].category;
		if(category=='TechDev') category = 'Technology';
		else if(category=='Art') category = 'Art/Music';
		else if(category=='Env') category = 'Environment';
		else if(category=='Edu') category = 'Education';
		var successMoney = queryObject1.barList[i].successMoney;
		var nowMoney = queryObject1.barList[i].nowMoney;
		
		data.addRows([ [ category, successMoney, nowMoney ] ]);
	}
      var materialOptions = {
        chart: {
          title: 'Population of Largest U.S. Cities',
          subtitle: 'Based on most recent and previous census data'
        },
        hAxis: {
          title: '금액',
          min : 0
          
        },
        vAxis: {
          title: '카테고리',
         
        },
        bars: 'horizontal',
        width: 602,
		height: 250,
		colors: ['#A50019', '#1e597a', '#A50019', '#1e597a']
      };
      var materialChart = new google.visualization.BarChart(document.getElementById('barchart_div'));
      materialChart.draw(data, materialOptions);
   }
//컬럼차트
function drawBasic() {

	var data = new google.visualization.DataTable();
	data.addColumn('string', '프로그램명');
	data.addColumn('number', '투자금액');
	data.addColumn('number', 'Average');
	var aver = queryObject.aver;
	for (var i = 0; i < queryObjectLen; i++) {
		var program = queryObject.barlist[i].program;
		var nowMoney = queryObject.barlist[i].nowMoney;
		
		data.addRows([ [ program, nowMoney, aver ] ]);
	}

	var options = {
		title : '투자금액 가장 높은 투자상품',
		hAxis : {
			title : '프로그램명'
		},
		vAxis : {
			title : '투자금액',
			viewWindow: {
		       min: [0]
		            
		    }
		},
		colors: ['#A50019'],
		width: 604,
		height: 350,
		series : {
			1: {
				type: 'line',
				color: '#6DBCDB'
				}
		}
	};

	var columnchart = new google.visualization.ColumnChart(document.getElementById('columnchart_div'));

	columnchart.draw(data, options);
	
}	
function drawChart() {

  var data = google.visualization.arrayToDataTable([
    ['Test', '연령대별 사용자인원'],
    ['20대',     jsonOb.pielist[0]],
    ['30대',      jsonOb.pielist[1]],
    ['40대',  jsonOb.pielist[2]],
    ['50대', jsonOb.pielist[3]],
    ['60대이상',    jsonOb.pielist[4]]
  ]);
                                                                                                                 
  var options = {
    width:602,
    height:250,
    pieSliceText: 'percentage',
    colors: ['#A50019', '#6DBCDB', '#E84B4B', '#1e597a', '#333']
  };

  var piechart = new google.visualization.PieChart(document.getElementById('piechart_div'));

  piechart.draw(data, options);
}
function start() {
	 chartload();
	 barchartload();
	 piechartload();
	 return setInterval(function(){
		 chartload();
		 barchartload();
		 piechartload();
		 },5000);
}
start();


</script>
<style type="text/css">
div#outProgressBar, div#innerProgressBar{
	height:10px;
	border-radius:4px;
}
div#outProgressBar{
	width:100%;
	background-color:gray;
}
div#innerProgressBar{
	width:0;
	background-color:skyblue;
}

div.irlistwrap{
	width:100%;
/* 	background-color:#eee; */
}

/* 캠페인 CSS + Hover Event */
#eachir{
	position:relative;
	display:inline-block;
	width:286px;
	height:310px;
/* 	background-color:skyblue; */
	box-sizing:border-box;
/* 	border:1px solid #A50019; */
	border:1px solid #eee;
	overflow:hidden;
 	line-height: 1.5em; 
 	margin-top:5px;
}

#eachir img{
	z-index = 0;
}
#eachir strong#blackShadow{
	color:#fff;
	font-size: 20px;
}
#eachir strong#blackShadow {
    position: absolute;
    display: block;
    z-index: 1;
    padding:0;
    bottom: -0;
    width: 286px;
    height: 310px;
/*     background: rgba(0, 0, 0, 0.9); */
	background:linear-gradient(rgba(0,0,0,0.7), rgba(0,0,0,0.95));
    text-align: left;
    padding: 20px;
    box-sizing:border-box;
}

#eachir span#yellowShadow {
    position: absolute;
    display: block;
    z-index: 0;
    top: 0;
    width: 286px;
    height: 310px;
    box-shadow: inset 0 0 50px rgba(50, 30, 0, 0.6),
                inset 0 0 100px rgba(50, 30, 0, 0.3);
    background: rgba(255, 155, 0, 0.2);
    opacity: 0;
    left:0%;
}

#eachir strong#blackShadow {
    opacity: 0;
    left: -200%;
}
span#hover_prog{
	color:#ff003f;
	font-size: 20px;
}
span#hover_intro{
	color: rgba(255,255,255,0.8);
	font-size: 16px;
	font-weight: normal;
}
span#hover_d{
	color: rgba(255,255,255,0.8);
	font-size: 14px;
}
/* 캠페인 CSS + Hover Event */

span#remainDay{
	color:#ccc;
	letter-spacing: 0.1em;
}
span#nowMoney{
	font-size:1.3em;
	width:50%;
	text-align: left;
	display:inline-block;
}
span#nowMoney span#skyblue{
	color:skyblue;
}
span#persent{
	width:50%;
	text-align: right;
	display:inline-block;
}
span#idealColor{
	color:#A50019;
}
span#intro_size{
	font-size:0.8em;
}


div#emptydiv{
	width:32px;
	height:32px;
	display:inline-block;
}

#eachir img{
	width:286px;
	height:200px;
}
.login img{
	width:350px;
/* 	padding-left:100px; */
	
}

.login_Procbtn:hover{
	color: #A50019;
    margin: 1px;
    background-color: #fff;
    border-radius: 5px;
    width: 160px;
    height: 40px;
    letter-spacing: 1px;
    border: 3px solid #A50019;
    font-weight: bold;
	
}
.login_Procbtn{
	color: #102E3F;
    margin: 1px;
    background-color: #fff;
    border-radius: 5px;
    width: 160px;
    height: 40px;
    letter-spacing: 1px;
    border: 3px solid #102E3F;
    font-weight: bold;
}
.login .investProc_info{
	width: 604px;
	margin: 0 auto;
}
.login .investProc_info td{
	text-align: center;
}
.riblist1 {
	width:100%;
/* 	background-color:#333; */
/* 	text-align: center; */
	height: 40px;
	padding-top: 10px;
	margin-top: 150px;
	margin-bottom: 30px;
}
.riblist2 {
	width:100%;
/* 	background-color:#333; */
/* 	text-align: center; */
	height: 40px;
	padding-top: 10px;
	margin-bottom: 30px;
}
.barchart1 {
	width: 602px;
	height: 350px;
/* 	border: 1px solid #c0c0c0; */
	margin-right: 32px;
	display: inline-block;
	float: left;
	overflow: hidden;
}
.piechart1 {
	width: 602px;
	height: 350px;
/* 	border: 1px solid #c0c0c0; */
	display: inline-block;
	overflow: hidden;
}
.graphspan {
	padding-left:250px;
	padding-top: 100px;
}

</style>
<script>
function viewIR(num){
//		alert(num);
	var frm = $("#viewActionForm");
	frm.html("<input type='hidden' name='investRequestNum' value='"+num+"'>");
	frm.submit();
}

$(function(){
	//
	var duration = 300;
	//irlistwrap > eachir
	var $items = $(".irlistwrap #eachir");
	//
	$items.find("strong#blackShadow").stop(true).animate({opacity:0,left:"-200%"}, duration);
// 	$items.find("span#yellowShadow").stop(true).animate({opacity:0}, duration);
	$items.filter("#eachir")
	.on("mouseover", function(){
		$(this).css("border", "2px solid #A50019");
		$(this).find("strong#blackShadow").stop(true).animate({opacity: 1, left: "0%"}, duration);
//		$(this).find("span#yellowShadow").stop(true).animate({opacity: 1}, duration);
	})
	.on("mouseout", function(){
		$(this).css("border", "2px solid #eee");
		$(this).find("strong#blackShadow").stop(true).animate({opacity: 0, left: "-200%"}, duration);
//		$(this).find("span#yellowShadow").stop(true).animate({opacity: 0}, duration);
	});
});
</script>
</head>


	<!-- 헤더 슬라이드 쇼   -->
  	<div id="slideshow">
		<div class="slideshow-container">
  	<div class="mySlides fade">
  		<a href="#"><img src="img/img1.jpg" style="width:33%;height:380px;"></a>
  		<a href="#"><img src="img/img2.jpg" style="width:34%;height:380px;"></a>
  		<a href="#"><img src="img/img4.jpg" style="width:33%;height:380px;"></a>
  	</div>
 	<div class="mySlides fade">
 		<a href="#"><img src="img/img2.jpg" style="width:33%;height:380px;"></a>
  		<a href="#"><img src="img/img4.jpg" style="width:34%;height:380px;"></a>
  		<a href="#"><img src="img/img1.jpg" style="width:33%;height:380px;"></a>
  	</div>
  	<div class="mySlides fade"><a href="#"></a>
    	<a href="#"><img src="img/img4.jpg" style="width:33%;height:380px;"></a>
  		<a href="#"><img src="img/img1.jpg" style="width:34%;height:380px;"></a>
  		<a href="#"><img src="img/img2.jpg" style="width:33%;height:380px;"></a>
  	</div></div>
  	</div>

	<!-- content 영역   wrap2 --> 	
	<div id="wrap2">
		
		<div id="body_txt3"><h2>Today's PICK</h2></div>
		
		<c:if test="${sessionScope.memberEmail != null }">
			<div class="login" style="background-color: #fff;"><!-- 
				--><!-- 투자 신청 리스트 --><!--
				--><div class="irlistwrap"><!--
				--><div class="deco1"><b>최신 투자 상품 안내</b></div><!-- 
 				 --><div class="deco2"><b>가장 투자 금액이 많은 상품</b></div><div style="clear:both"></div><!-- 
				--><c:if test="${sessionScope.lirlist!=null}"><!-- 
					--><c:forEach var="lirdto" items="${sessionScope.lirlist }" varStatus="s"><!--
						--><c:if test="${lirdto!=null }"><!--
						--><div id="eachir" onclick="viewIR(${lirdto.investRequestNum});"><!-- 
							--><img src="image/${lirdto.memberEmail }/${lirdto.thumbImage}"><!--
							--><div style="width:286px; height:110px; box-sizing:border-box; padding:0 20px;"><!--  
								--><strong id="blackShadow"><!--
									--><br><!--
									--><span id="hover_prog">${lirdto.program }</span><!--
									--><br><br><!--  
									--><span id="hover_intro">${lirdto.introduce }</span><!--
									--><br><br><!--
									--><span id="hover_d">Start By ${lirdto.DName }</span><!--  
								--></strong><!--  
								--><span id="yellowShadow"></span><!--  
								--><strong><!--  
									--><c:if test="${fn:length(lirdto.program) > 17 }"><c:set var="program" value="${fn:substring(lirdto.program, 0, 17) }..."/></c:if><!--
									--><c:if test="${fn:length(lirdto.program) < 17 }"><c:set var="program" value="${lirdto.program }"/></c:if><!--
									--><span>${program }</span><br><!--  
								--></strong><!--
								--><c:if test="${fn:length(lirdto.introduce) > 20 }"><c:set var="introduce" value="${fn:substring(lirdto.introduce, 0, 20) }..."/></c:if><!--
								--><c:if test="${fn:length(lirdto.introduce) < 20 }"><c:set var="introduce" value="${lirdto.introduce }"/></c:if><!--  
								--><span id="intro_size">${introduce }</span><br><!--
								--><span id="remainDay">${lirdto.startDay } ~ ${lirdto.endDay }</span><br><!--
								--><span id="nowMoney"><span id="skyblue">${lirdto.nowMoney }</span>원</span><!--  
								--><span id="persent"><!--
								--><f:formatNumber value="${lirdto.nowMoney/lirdto.successMoney }" type="percent"/><!-- 
									 --><c:if test="${lirdto.fundSituation == 2 }"><!--  
										-->[<span id="idealColor">종료</span>]<!--  
									--></c:if><!--  
									--><c:if test="${lirdto.fundSituation == 4 }"><!--  
										-->[<span id="idealColor">종료</span>]<!--  
									--></c:if><!--  
								--></span><br><!--  
								--><div id="outProgressBar"><f:parseNumber var="percent" integerOnly="true" value="${lirdto.nowMoney/lirdto.successMoney*100 }"/><!--  
									--><div id="innerProgressBar" style="width:${percent}%"></div><!--  
								--></div><!--  
							--></div><!-- 
						--></div><!-- 
						--></c:if><!-- 
							--><c:if test="${s.count%4==0 }"><!--  
								--><br><br><!--  
							--></c:if><!--  
							--><c:if test="${s.count%4!=0 }"><!--  
								--><div id="emptydiv"></div><!--  
							--></c:if><!--  
					--></c:forEach><!--  
				--></c:if><!--  
			--></div>
<%-- 				<form action="viewInvestRequestAction.iR" method="post" id="viewActionForm"></form> --%>
				<!-- 투자 신청 리스트 종료 -->
	 		</div>
 		</c:if>
 		<c:if test="${sessionScope.memberEmail == null }">
 			<div class="login">
 				<!-- 로그인 안햇을때 넣는부분 -->
 				<div class="logimgdiv">
 			 	 <table class = "investProc_info">
		 			<tr>
		 				<td><button class="login_Procbtn"  onclick="processingInfo(1)">1.투자자철차</button></td>
		 				<td><button class="login_Procbtn"  onclick="processingInfo(2)">2.투자요청절차</button></td>
		 				<td><button class="login_Procbtn"  onclick="processingInfo(3)">3.투자진행과정</button></td>
		 			</tr>
	 			 </table>
 				</div>
 				
 			</div>
 		</c:if>
		<div class="second_div"></div>
		<div class="category">
			<div id="columnchart_div"></div>
		</div>
		
		<div id="body_txt4"><h2>Categories</h2></div>
	
	<!-- 카테고리 이동 --> 
    <div id="images">
        <div class="inner">
            <p class="pad1"><a href="goInvestmentList.iR?category=TechDev"><img src="img/tech1.jpg" width="286px"><strong>Technology</strong><span></span></a></p>		
        </div>
         <div class="inner">
            <p class="pad1"><a href="goInvestmentList.iR?category=Env"><img src="img/nat1.jpg" width="286px"><strong>Environment</strong><span></span></a></p>		
        </div>
         <div class="inner">
            <p class="pad1"><a href="goInvestmentList.iR?category=Edu"><img src="img/edu1.jpg" width="286px"><strong>Education</strong><span></span></a></p>		
        </div>
         <div class="inner">
            <p><a href="goInvestmentList.iR?category=Art"><img src="img/art2.jpg" width="286px"><strong>Art/Music</strong><span></span></a></p>		
        </div>
	</div>
	
		
	<div id="clear-right"></div>
		
		<!-- 카테고리 이동 --> 
		<div class="body_txt1"></div>
		
		<!-- 펀딩 오픈 과정 아이콘 --> 
		<div id="process_img">
			<div id="buttons3">
				<div class="inner2 clearfix">
					<button>
						<span> <img src="img/01_bl.png"> <img
							src="img/01_gr.png"><br> 투자유지신청
						</span>
					</button>
					<button>
						<span> <img src="img/02_bl.png"> <img
							src="img/02_gr.png"><br> 등록심사
						</span>
					</button>
					<button>
						<span> <img src="img/03_bl.png"> <img
							src="img/03_gr.png"><br> 계약체결
						</span>
					</button>
					<button>
						<span> <img src="img/04_bl.png"> <img
							src="img/04_gr.png"><br> 자료작성
						</span>
					</button>
					<button>
						<span> <img src="img/05_bl.png"> <img
							src="img/05_gr.png"><br> 펀딩오픈
						</span>
					</button>
				</div>
			</div>
		</div>
		<div id="clear"></div>
	
		<!-- google 그래프  -->
		<div id="body_txt2"></div>
		<div class="div_g1 g0_a"><span class="graphspan"><b>분야별 투자 최고 금액</b></span>
			 <div id="barchart_div"></div>
		</div><!--  
		--><div class="div_g1 g0_b"><span class="graphspan"><b>투자자 연령통계</b></span>
			<div id="piechart_div"></div>
		</div><!-- 
		-->
		<!-- 슬로건  -->
		<div id="body_txt2"></div>
		
		<!-- 투자상품 뿌려주는 부분  -->
		<!-- 투자 신청 리스트 -->
		<div class="irlistwrap"><!-- 
		--><div class="riblist1"><img class="tit1" src="img/tit1.png"></div><!--
		--><c:if test="${sessionScope.irlist!=null}"><!-- 
			--><c:forEach var="irdto" items="${sessionScope.irlist }" varStatus="s"><!-- 
				--><div id="eachir" onclick="viewIR(${irdto.investRequestNum});"><!-- 
					--><img src="image/${irdto.memberEmail }/${irdto.thumbImage}"><!--
					--><div style="width:286px; height:110px; box-sizing:border-box; padding:0 20px;"><!--  
						--><strong id="blackShadow"><!--
							--><br><!--
							--><span id="hover_prog">${irdto.program }</span><!--
							--><br><br><!--  
							--><span id="hover_intro">${irdto.introduce }</span><!--
							--><br><br><!--
							--><span id="hover_d">Start By ${irdto.DName }</span><!--  
						--></strong><!--  
						--><span id="yellowShadow"></span><!--  
						--><strong><!--  
							--><c:if test="${fn:length(irdto.program) > 17 }"><c:set var="program" value="${fn:substring(irdto.program, 0, 17) }..."/></c:if><!--
							--><c:if test="${fn:length(irdto.program) < 17 }"><c:set var="program" value="${irdto.program }"/></c:if><!--
							--><span>${program }</span><br><!--  
						--></strong><!--
						--><c:if test="${fn:length(irdto.introduce) > 20 }"><c:set var="introduce" value="${fn:substring(irdto.introduce, 0, 20) }..."/></c:if><!--
						--><c:if test="${fn:length(irdto.introduce) < 20 }"><c:set var="introduce" value="${irdto.introduce }"/></c:if><!--  
						--><span id="intro_size">${introduce }</span><br><!--
						--><span id="remainDay">${irdto.startDay } ~ ${irdto.endDay }</span><br><!--
						--><span id="nowMoney"><span id="skyblue">${irdto.nowMoney }</span>원</span><!--  
						--><span id="persent"><!--
						--><f:formatNumber value="${irdto.nowMoney/irdto.successMoney }" type="percent"/><!-- 
							 --><c:if test="${irdto.fundSituation == 2 }"><!--  
								-->[<span id="idealColor">종료</span>]<!--  
							--></c:if><!--  
							--><c:if test="${irdto.fundSituation == 4 }"><!--  
								-->[<span id="idealColor">종료</span>]<!--  
							--></c:if><!--  
						--></span><br><!--  
						--><div id="outProgressBar"><f:parseNumber var="percent" integerOnly="true" value="${irdto.nowMoney/irdto.successMoney*100 }"/><!--  
							--><div id="innerProgressBar" style="width:${percent}%"></div><!--  
						--></div><!--  
					--></div><!-- 
				--></div><!--  
					--><c:if test="${s.count%4==0 }"><!--  
						--><br><br><!--  
					--></c:if><!--  
					--><c:if test="${s.count%4!=0 }"><!--  
						--><div id="emptydiv"></div><!--  
					--></c:if><!--  
			--></c:forEach><!--  
		--></c:if><!--  
	--></div>
		<form action="viewInvestRequestAction.iR" method="post" id="viewActionForm"></form>
		<!-- 투자 신청 리스트 종료 -->
		
			<div class="riblist2"><img class="tit1" src="img/tit2.png"></div>
				
		<!-- 등록 대기 캠페인 미리 4개 뿌려주는곳 -->
		<!-- 투자 신청 리스트 -->
		<div class="irlistwrap"><!-- 
	
		--><c:if test="${sessionScope.eirlist!=null}"><!-- 
			--><c:forEach var="eirdto" items="${sessionScope.eirlist }" varStatus="s"><!-- 
				--><div id="eachir" onclick="viewIR(${eirdto.investRequestNum});"><!-- 
					--><img src="image/${eirdto.memberEmail }/${eirdto.thumbImage}"><!--
					--><div style="width:286px; height:110px; box-sizing:border-box; padding:0 20px;"><!--  
						--><strong id="blackShadow"><!--
							--><br><!--
							--><span id="hover_prog">${eirdto.program }</span><!--
							--><br><br><!--  
							--><span id="hover_intro">${eirdto.introduce }</span><!--
							--><br><br><!--
							--><span id="hover_d">Start By ${eirdto.DName }</span><!--  
						--></strong><!--  
						--><span id="yellowShadow"></span><!--  
						--><strong><!--  
							--><c:if test="${fn:length(eirdto.program) > 17 }"><c:set var="program" value="${fn:substring(eirdto.program, 0, 17) }..."/></c:if><!--
							--><c:if test="${fn:length(eirdto.program) < 17 }"><c:set var="program" value="${eirdto.program }"/></c:if><!--
							--><span>${program }</span><br><!--  
						--></strong><!--
						--><c:if test="${fn:length(eirdto.introduce) > 20 }"><c:set var="introduce" value="${fn:substring(eirdto.introduce, 0, 20) }..."/></c:if><!--
						--><c:if test="${fn:length(eirdto.introduce) < 20 }"><c:set var="introduce" value="${eirdto.introduce }"/></c:if><!--  
						--><span id="intro_size">${introduce }</span><br><!--
						--><jsp:useBean id="now" class="java.util.Date" /><!--
						--><f:formatDate var="a" value="${now }" pattern="yyyyMMdd"/><!--
						--><f:formatDate var="b" value="${eirdto.startDay }" pattern="yyyyMMdd"/><!--
						--><!--
						--><!--
						--><span id="remaindDay">D - ${b-a } 남음[${eirdto.startDay }]</span><br><!--
						--><span id="nowMoney"><span id="skyblue">${eirdto.nowMoney }</span>원</span><!--  
						--><span id="persent"><!--
						--><f:formatNumber value="${eirdto.nowMoney/eirdto.successMoney }" type="percent"/><!-- 
							 --><c:if test="${eirdto.fundSituation == 2 }"><!--  
								-->[<span id="idealColor">종료</span>]<!--  
							--></c:if><!--  
							--><c:if test="${eirdto.fundSituation == 4 }"><!--  
								-->[<span id="idealColor">종료</span>]<!--  
							--></c:if><!--  
						--></span><br><!--  
						--><div id="outProgressBar"><f:parseNumber var="percent" integerOnly="true" value="${eirdto.nowMoney/eirdto.successMoney*100 }"/><!--  
							--><div id="innerProgressBar" style="width:${percent}%"></div><!--  
						--></div><!--  
					--></div><!-- 
				--></div><!--  
					--><c:if test="${s.count%4==0 }"><!--  
						--><br><br><!--  
					--></c:if><!--  
					--><c:if test="${s.count%4!=0 }"><!--  
						--><div id="emptydiv"></div><!--  
					--></c:if><!--  
			--></c:forEach><!--  
		--></c:if><!--  
	--></div>
		<!-- 등록 대기 캠페인 미리 4개 뿌려주는곳 끝 -->
		
		
		<!-- 캠페인 시작/ 투자 시작 링크  -->
		<div class="body_caminv">
			<div class="imgbut">
				<div id="cam"><a href="goInvestmentList.iR" class="investRequest"><h4>투자 시작</h4></a></div>
				<div id="inv"><a href="investRequest.iR"><h4>캠페인 시작</h4></a></div>
			</div>	
		</div>
		
		<div id="clear"></div>

	</div>




	<!-- 상단 nav로 이동 버튼  -->
<!-- 	<button type="button" class="back-to-top"> -->
<!--     <h3><span class="label">Top</span></h3> -->
<!--    	</button> -->
 	
 	<!--  회사 리스트는 메인에서만 보이기 때문에 footer 안에 넣지 않았습니다. -->   	
	 <div class="footer_txt1"> 
	 	<div class="txt1_comlist">
		<div class="comlist1 l1"><a href="#"><img src="img/amazon.png"></img></a></div> 
		<div class="comlist1 l2"><a href="#"><img src="img/google.png"></img></a></div> 
		<div class="comlist1 l3"><a href="#"><img src="img/sony.png"></img></a></div> 
		<div class="comlist1 l4"><a href="#"><img src="img/vimeo.png"></img></a></div> 
		<div class="comlist1 l5"><a href="#"><img src="img/youtbe.png"></img></a></div> 
		<div class="comlist1 l6"><a href="#"><img src="img/samsung.png"></img></a></div>	
	 	</div>
	 </div> 

