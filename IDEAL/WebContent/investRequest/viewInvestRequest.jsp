<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.StringTokenizer"%>
<%@page import="net.investRequest.db.InvestRequestDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
	@import url(//fonts.googleapis.com/earlyaccess/notosanskr.css);
	*{
		margin:0;
		padding:0;
		font-family: 'Noto Sans KR', sans-serif;
	}
	.wrap{
		width:1240px;
		margin:0 auto;
		position: relative;
	}
	#wrap3{
		width:1240px;
		margin:0 auto;
		position: absolute;
	}
	.clear{
		clear: both;
	}
	#body_section1{
 		min-height:500px; 
/* 		background-color : #eee; */
		padding-top:50px;
	}
	#body_section2{
		width:1240px;
		min-height:500px;
		background-color : #abc;
	}
	article, aside.junside{
		float:left;
	}
	article{
		width:816px;
/*  		background-color: #999; */
		min-height:500px;
	}
	aside.junside{
		width:392px;
		min-height:500px;
		margin-left:32px;
		background-color: #e9ecef;
	}
	div#aside_div{
		line-height: 1.5em;
	}
	
	span#tiny{
		font-size:13px;
	}
	span#smallgray{
		font-size:15px;
		color:gray;
	}
	span#middle{
		font-size:18px;
	}
	span#big{
		font-size:30px;
	}
	
	
	div#fotorama{
		padding:30px;
	}
	div#s1_a_w{
		padding:45px;
	}
	div#outProgressBar, div#innerProgressBar{
		height:10px;
	}
	div#outProgressBar{
		width:100%;
		background-color:gray;
	}
	div#innerProgressBar{
		width:0;
		background-color:skyblue;
	}
	
	button#investmentBtn{
		width:100%;
		height:60px;
		border-radius: 10px;
		border:none;
		background-color:#00A0F3;
		font-size:25px;
		color:#fff;
	}
	button#requiredLoginBtn{
		width:100%;
		height:60px;
		border-radius: 10px;
		border:none;
		background-color:#A50019;
		font-size:25px;
		color:#fff;
	}
	button#exitedBtn{
		width:100%;
		height:60px;
		border-radius: 10px;
		border:none;
		background-color:#444444;
		font-size:25px;
		color:#fff;
	}
	
	#section2_wrap{
		padding-top:50px;
	}
	img#mainImageShow{
		width:100%;
	}
</style>
<!-- jQuery -->
<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.2.1.min.js"></script>
<!-- fotorama -->
<link  href="http://cdnjs.cloudflare.com/ajax/libs/fotorama/4.6.4/fotorama.css" rel="stylesheet"> <!-- 3 KB -->
<script src="http://cdnjs.cloudflare.com/ajax/libs/fotorama/4.6.4/fotorama.js"></script> <!-- 16 KB -->
<script type="text/javascript">
$(function(){
	/* ProgressBar 처리 */
	var sm = ${irdto.successMoney};
	var nm = ${irdto.nowMoney};
	var persent = (nm/sm*100).toFixed(0);
// 	alert(persent);
	$("#persent").text(persent);
	if(persent>=100){
		$("#innerProgressBar").css("width", "100%")
	}else{
		$("#innerProgressBar").css("width", persent+"%")
	}
	
	/* 남은날 계산 */
	var sd = ${irdto.startDay.getTime()};
	var ed = ${irdto.endDay.getTime()};
	var dayCal = 24*60*60*1000;
// 	alert(sd+"\n"+ed);	
	var remainDay = (ed-sd)/dayCal;
// 	alert(remainDay);
	$("#remaindDay").text(remainDay);
});
</script>
</head>
<%
	InvestRequestDto irdto = (InvestRequestDto)request.getAttribute("irdto");
	String memberEmail = (String)session.getAttribute("memberEmail");
	
	String realFolder = request.getServletContext().getRealPath("image\\"+memberEmail);
// 	System.out.println("realFolder:"+realFolder);//
	
	String mainThumb = irdto.getMainThumb();
	StringTokenizer st = null;
	ArrayList<String> mt_arr = new ArrayList<String>(); 
	if(mainThumb!=null){
		st = new StringTokenizer(mainThumb, "$");
	}
	//메인 섬네일 이미지(fotorama)
	System.out.println("mainThumb_length:"+mainThumb.length());
	while(st.hasMoreTokens()){
// 		System.out.println("fotorama:"+st.nextToken());
		mt_arr.add(st.nextToken());
	}
	
	/* 시작일, 종료일 날짜 표시 및 남은날짜 계산 */
	SimpleDateFormat smt = new SimpleDateFormat("yyyy-MM-dd");
	java.util.Date date = new java.util.Date();
	String startDay = smt.format(irdto.getStartDay().getTime());
	String endDay = smt.format(irdto.getEndDay().getTime());
	String remainDay = smt.format(new java.util.Date(irdto.getEndDay().getTime()-irdto.getStartDay().getTime()));
	
	/* 메인 이미지 */
	String mainImage = irdto.getMainImage();
	StringTokenizer mi = null;
	ArrayList<String> mi_arr = new ArrayList<String>();
	if(mainImage!=null){
		mi = new StringTokenizer(mainImage, "$");
	}
	while(mi.hasMoreTokens()){
		mi_arr.add(mi.nextToken());
	}
	/* 메인 텍스트 */
	String mainText = irdto.getMainText();
	StringTokenizer mtxt = null;
	if(mainText!=null){
		mtxt = new StringTokenizer(mainText, "$");
	}
	ArrayList<String> mtxt_arr = new ArrayList<String>();
	while(mtxt.hasMoreTokens()){
		mtxt_arr.add(mtxt.nextToken());
	}
%>
<body>
	<div class="wrap">
			<section id="body_section1">
				<article>
					<div id="section1_article_wrap">
						<div id="fotorama">
							<div class="fotorama" data-arrow="true" data-width="90%" data-ratio="16/9" data-nav="thumbs" data-allowfullscreen="true">
<%
								for(int a=0;a<mt_arr.size();a++){
%>
									<a href="image/<%=irdto.getMemberEmail() %>/<%=mt_arr.get(a)%>"><img src="image/<%=irdto.getMemberEmail() %>/<%=mt_arr.get(a)%>" data-caption="mainThumb"></a>
<%
								}
%>
							</div>
						</div>
					</div>
				</article>
				<aside class="junside">
					<div id="section1_aside_wrap">
						<div id="s1_a_w">
							<div id="aside_div">
								<!-- 목표금액 & 달성금액 -->
								<span id="smallgray">목표금액 ${irdto.successMoney }원</span><br>
								<span id="big">${irdto.nowMoney }</span><span id="middle">원 달성</span>
							</div>
							<br>
							<div id="aside_div">
								<!-- 달성금액 비 모금금액 & ProgressBar max = 100% -->
								<span id="smallgray">달성률</span><br>
								<span id="big"><span id="persent"></span></span><span id="middle">%</span>
								<div id="outProgressBar">
									<div id="innerProgressBar"></div>
								</div>
							</div>
							<br>
							<div id="aside_div">
								<!-- 투자자수(참여자수) -->
								<span id="big"><!-- 투자자페이지랑 join해서 처리해야 하는 부분 -->0</span><span id="middle">명</span>
							</div>
							<br>
							<div id="aside_div">
								<!-- 기간 -->
								<span id="smallgray">기간</span><br>
								<span id="big"><span id="remaindDay"></span></span><span id="middle">일 남음</span><br>
								<span id="smallgray"><%=startDay %> ~ <%=endDay %></span>
							</div>
							<br>
							<div id="aside_div">
								<!-- 투자하기 버튼 -->
								<span id="tiny">목표금액 미달 시, 취소 및 환불 됩니다.</span><br>
								<c:if test="${irdto.fundSituation == 1 && sessionScope.memberEmail != null}">
									<a href="investment.im?investRequestNum=${irdto.investRequestNum }"><button id="investmentBtn">투자하기</button></a>
								</c:if>
								<c:if test="${irdto.fundSituation == 1 && sessionScope.memberEmail == null}">
									<button id="requiredLoginBtn">로그인 해주세요</button>
								</c:if>
								<c:if test="${irdto.fundSituation != 1}">
									<button id="exitedBtn">종료된 상품입니다</button>
								</c:if>
							</div>
						</div>
					</div>
				</aside>
			</section>
			<div class="clear"></div>
			<section id="body_section2">
				${irdto.program } Start By<br>
				${irdto.introduce } ${irdto.DName }
			</section>
			<section id="body_section2">
				<div id="section2_wrap">
					<div align="center">
<%
						for(int i=0;i<mi_arr.size();i++){
%>
							<img id="mainImageShow" src="image/${irdto.memberEmail }/<%=mi_arr.get(i)%>">
<%-- 							<textarea style="width:100%; height:40px;resize:none;font-size:30px;text-align: center;" readonly="readonly"><%=mtxt_arr.get(i) %></textarea> --%>
<%-- 							<p style="width:100%;text-align: center;"><%if(mtxt_arr.size()>1) out.println(mtxt_arr.get(i)); %></p> --%>
							<!-- 본문내용이 있을경우에 출력 -->
<%
						}
%>
					</div>
				</div>
			</section>
			<div class="clear"></div>
		<button onclick="location.href='goMain.ma'">메인으로</button>
	</div>
</body>
</html>