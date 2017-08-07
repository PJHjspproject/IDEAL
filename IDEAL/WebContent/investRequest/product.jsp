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


#eachir{
	display:inline-block;
	width:286px;
	height:310px;
/* 	background-color:skyblue; */
	box-sizing:border-box;
	border:1px solid #eee;
 	line-height: 1.5em; 
}

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
	var a = 1;
	$(function(){
		if(a == 1){
		
		$("body").html($("body").html());
		a++;
		}
	})
</script>
<script type="text/javascript">
	var memberEmail = '<%=memberEmail%>';
	
	alert(memberEmail);
	
	
	function startIR(){
		location.href="./investRequest.iR";
	}
	function viewIR(num){//캠페인 목록에서 캠페인 상세페이지로 넘어가는 메소드
		if(memberEmail == 'null'){
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
		})
	})
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
						--><strong><!--  
						-->${irdto.program }<br><!--  
						--></strong><!--
						--><c:if test="${fn:length(irdto.introduce)>15 }"><c:set var="introduce" value="${fn:substring(irdto.introduce, 0, 15) }..."/></c:if><!--
						--><c:if test="${fn:length(irdto.introduce)<15 }"><c:set var="introduce" value="${irdto.introduce }"/></c:if><!--  
						--><span>${introduce }</span><br><!--
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
		<div id="scrollDiv">
<!-- 			<button id="scrollBtn">더 읽어오기</button> -->
		</div>
	</div>
	<form action="viewInvestRequestAction.iR" method="post" id="viewActionForm"></form>
</body>
</html>