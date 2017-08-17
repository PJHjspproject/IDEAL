<%@page import="net.investRequest.db.InvestRequestDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%
String memberEmail = (String)session.getAttribute("memberEmail");
System.out.println(memberEmail);
%>
<style>
@import url(//fonts.googleapis.com/earlyaccess/notosanskr.css);

* {
	margin: 0;
	padding: 0;
	font-family: 'Noto Sans KR', DEF_FONT, sans-serif;
}

div.wrap {
 	width: 1240px;
 	min-height:0px; 
 	/*max-height:740px;/* 수정 */
	overflow:hidden;/* 수정 */
	margin: 0 auto;
	padding-top:100px;
}


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
	color:#102E3F;
	letter-spacing: 0.1em;
	font-size:12px;
	font-weight: bold;
	text-align: right;
	padding-right:2px;
}
span#nowMoney{
	font-size:1.1em;
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

div#div_search{
	padding:20px;
	text-align: center;
}

select#category{
	font-size:30px; 
	border:none; 
	background-image:url('image/downArrow.png');
	background-repeat: no-repeat;
	background-position: 100% 50%;
	background-size: 20%;
	-webkit-appearance: none; -moz-appearance: none; appearance: none;/* 네이티브 외형 감추기 */
}
input[type=text]#search{
	font-size:30px;
	border:none;
	border-bottom: 2px solid black;
}
.search_icon{
	background-image: url("image/search.png");
	background-repeat: no-repeat;;
	background-position:0% 50%;
	background-size:10%;
}
img#search_image{
	width:40px;
	height:40px;
	margin-top:-15px;
}


div#scrollDiv{/* 수정 */
	margin:0 auto;
/* 	border:1px solid blue; */
}
button#scrollBtn{/* 수정 */
	font-size:30px; 
	width:300px; 
	border:1px solid red; 
	z-index:1000;
	position:fixed;
	margin:0 auto;
}
</style>
<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
// 	var a = 1;
// 	$(function(){
// 		if(a == 1){
		
// 		$("body").html($("body").html());
// 		a++;
// 		}
// 	})
</script>
<script type="text/javascript">
	function startIR(){
		location.href="./investRequest.iR";
	}
	function viewIR(num){//캠페인 목록에서 캠페인 상세페이지로 넘어가는 메소드
		if(memberEmail == "null" || memberEmail == ""){
		$('a.login-window').trigger("click");
		return false;
		}else{
			var frm = $("#viewActionForm");
			frm.html("<input type='hidden' name='investRequestNum' value='"+num+"'>");
			frm.submit();
		}
	}
</script>
<script>
// 	$(function(){
// 		$("#search").blur(function(){//검색어 입력란에 focus가 해제되었을 때 동작
// 			if($("#search").val()==""){//만약 검색어가 비어있다면
// 				$("#search").addClass("search_icon");//search_icon을 입력해주는 class
// 			}
// 		})
// 		.focus(function(){//검색어 입력란에 focus가 되었을 때 동작
// 			$("#search").removeClass("search_icon");//search_icon을 제거해주는 class
// 		})
// 	})
	$(function(){
		$("#search_image").on("click", function(){
			var $p = $("#search_image").parent();
			$p.submit();
		});
	});
	
	$(function(){
		//
		var duration = 300;
		//irlistwrap > eachir
		var $items = $(".irlistwrap #eachir");
		//
		$items.find("strong#blackShadow").stop(true).animate({opacity:0,left:"-200%"}, duration);
//	 	$items.find("span#yellowShadow").stop(true).animate({opacity:0}, duration);
		$items.filter("#eachir")
		.on("mouseover", function(){
			$(this).css("border", "2px solid #A50019");
			$(this).find("strong#blackShadow").stop(true).animate({opacity: 1, left: "0%"}, duration);
//			$(this).find("span#yellowShadow").stop(true).animate({opacity: 1}, duration);
		})
		.on("mouseout", function(){
			$(this).css("border", "2px solid #eee");
			$(this).find("strong#blackShadow").stop(true).animate({opacity: 0, left: "-200%"}, duration);
//			$(this).find("span#yellowShadow").stop(true).animate({opacity: 0}, duration);
		});
	});
</script>
</head>
<%
	
	InvestRequestDto irdto = (InvestRequestDto)request.getAttribute("irdto");
	
	System.out.println("product.jsp irdto:"+irdto);
	String realFolder = request.getServletContext().getRealPath("image\\"+memberEmail);
// 	System.out.println("realFolder:"+realFolder);
	String category = (String)session.getAttribute("category");//selected된 category를 고정해주기 위해 받아옴
	if(category==null) category = "All";
// 	System.out.println("product.jsp > "+category);
%>
<body>
	<div class="wrap">
		<div id="div_search">
			<form action="goInvestmentList.iR" method="post">
				<select name="category" id="category">
					<option value="All" <%if(category!=null && category.equals("All")){ %>selected<%} %>>ALL</option>
					<optgroup label="Category">
						<option value="TechDev" <%if(category!=null && category.equals("TechDev")){ %>selected<%} %>>Technology</option>
						<option value="Env" <%if(category!=null && category.equals("Env")){ %>selected<%} %>>Environment</option>
						<option value="Edu" <%if(category!=null && category.equals("Edu")){ %>selected<%} %>>Education</option>
						<option value="Art" <%if(category!=null && category.equals("Art")){ %>selected<%} %>>Art/Music</option>
					</optgroup>
				</select>
				<input type="text" name="search" id="search" placeholder="Search">
				<img src="image/search.png" id="search_image">
			</form>
		</div>
		<div class="irlistwrap"><!-- 
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
						--><div id="outProgressBar"><f:parseNumber var="percent" integerOnly="true" value="${irdto.nowMoney/irdto.successMoney*100 }"/><!--  
							--><div id="innerProgressBar" style="width:${percent}%"></div><!--  
						--></div><!--  						
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
		<div id="scrollDiv">
<!-- 			<button id="scrollBtn">더 읽어오기</button> -->
		</div>
	</div>
	<form action="viewInvestRequestAction.iR" method="post" id="viewActionForm"></form>
</body>
</html>