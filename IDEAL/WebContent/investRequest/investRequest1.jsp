<%@page import="java.util.Locale"%>
<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	div.wrap{
		width:1240px;
		margin:0 auto;
		padding-top: 100px;
	}
	.clear{
		clear: both;
	}
	section, aside.junside{
		float:left;
	}
	#none_alert{
		font-size: 15px;
		color:red;
		display:none;
	}
	input[type=file]{
		border:none;
	}
	input[type=text]{
		font-size:20px;
	}
	
	/* section */
	section{
		width:816px;
		min-height:800px;
		opacity: 0.5;
	}
	div.div_section, div.div_aside{
		padding:30px;
	}
	h4{
		color:#888;
	}
	input#program, input#dName, input#hashtag{
		border:1px solid #666;
		padding: 15px;
		width:500px;
	}
	textarea#introduce{
		padding:15px;
		resize:none;
	}
	select#category{
		padding:10px;
		width:150px;
		font-size: 20px;
	}
	input#startDay, input#endDay, input#payDay{
		padding 10px;
		font-size: 20px;
		text-align: center;
	}
	input#successMoney{
		padding: 10px;
		font-size: 20px;
		width:300px
	}
	span#sMoney{
		font-size: 20px;
		font-weight: bold;
	}
	p#sMoney_p{
		background-color: #e9ecef;
		padding: 20px;
		width: 85%;
		margin:0 auto;
		font-size: 13px;
		border-radius:10px;
	}
	
	div#submitdiv{
		width:100%;
		text-align: center;
	}
	input#ir1submit{
		width:60%;
		padding:15px;
	}
	
	
	/* section */
	/* aside */
	aside.junside{
		width:392px;
		min-height:800px;
		margin-left:32px;
		background-color: #e9ecef;
		opacity: 0.8;
	}
	aside.junside div#holder{
/* 		border:1px solid red; */
		width:300px;
		height:200px;
		margin:0 auto;
	}
	.thumb_none{
		background-color:#444;
		text-align: center;
	}
	aside.junside div#holder_bottom{
/* 		border:1px solid blue; */
		width:280px;
		height:150px;
		margin:0 auto;
		background-color:#fff;
		padding:10px;
	}
	textarea#aside_text{
		border:none;
		width:280px;
		height:130px;
	}
	div#aside_example{
		width:280px;
		height:20px;
	}
	span#aside_example_money{
		font-size:18px;
		font-weight: normal;
	}
	span#aside_example_text{
		font-size:14px;
		color:#888;
	}
	/* aside */
</style>
<!-- JQuery CDN -->
<script
  src="https://code.jquery.com/jquery-3.2.1.min.js"
  integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
  crossorigin="anonymous"></script>
<script>
	function add_day(d){//펀드 최대 날짜는 60일로 고정하기 위해서 startDay의 날짜를 기점으로 endDay의 max값을 제한하는 함수
		var d_arr = d.split('-');
		
		var nowdate = new Date(d_arr[0], d_arr[1], d_arr[2]);
// 		alert(nowdate);
		var day = nowdate.getDate();
		nowdate.setDate(day+60);
// 		alert(nowdate);
		
		var y = nowdate.getFullYear();
		var m = nowdate.getMonth();
		if(m<10) m="0"+m;
		var d = nowdate.getDate();
		if(d<10) d="0"+d;
		return y+"-"+m+"-"+d;
	}
	
	
	$(function(){
		/* 
			목표 기간 설정에서 startDay의 값을 정한다면(startDay의 값이 바뀐다면)
			endDay의 readonly속성이 없어져서 선택이 가능해지며, 
			endDay의 min값이 startDay의 선택값이 들어가지게 된다.
		*/
		$("#startDay").on("change", function(){
			var min = $("#startDay").val();
// 			alert("min:"+min);
			var max = add_day(min);
// 			alert("max:"+max);
			$("#endDay").removeAttr("readonly");
			$("#endDay").attr("min", min);
			$("#endDay").attr("max", max);
		})
		/* endDay값 설정시 startDay와 endDay의 값을 계산하여 총 펀딩 날짜 계산하여 출력 */
		$("#endDay").on("change", function(){
			var start =  $("#startDay").val();//시작 날짜
			var end = $("#endDay").val();//종료 날짜
			
			var st_arr = start.split('-');//시작 날짜를 -로 구분하여 자름
			var ed_arr = end.split('-');//종료 날짜를 -로 구분하여 자름
			var st_dat = new Date(st_arr[0], st_arr[1], st_arr[2]);//자른 시작날짜 값들로 Date객체 생성
			var ed_dat = new Date(ed_arr[0], ed_arr[1], ed_arr[2]);//자른 종료날짜 값들로 Date객체 생성
			
			var totalday = (ed_dat-st_dat)/(24*60*60*1000);//총 날짜 계산
			$("#totalDay").html("총 <b>"+totalday+"</b>일");//종료날짜까지 선택하였을 경우 총 날짜를 표시
			$("#totalDay").css("font-size", "20px");
			
			/* endDay값을 설정후 교부날짜의 min값 설정 */
			$("#payDay").removeAttr("readonly");
			$("#payDay").attr("min", end);
			
			/* startDay값을 설정하고, endDay값을 설정한 후, 다시startDay값을 변경하였을 때도 총 날짜 계산되게 추가된 부분 */
			$("#startDay").on("change", function(){
				var start =  $("#startDay").val();//시작 날짜
				var end = $("#endDay").val();//종료 날짜
				
				var st_arr = start.split('-');//시작 날짜를 -로 구분하여 자름
				var ed_arr = end.split('-');//종료 날짜를 -로 구분하여 자름
				var st_dat = new Date(st_arr[0], st_arr[1], st_arr[2]);//자른 시작날짜 값들로 Date객체 생성
				var ed_dat = new Date(ed_arr[0], ed_arr[1], ed_arr[2]);//자른 종료날짜 값들로 Date객체 생성

				var totalday = (ed_dat-st_dat)/(24*60*60*1000);//총 날짜 계산(여기서는 +1하면 종료날짜 포함진행/+1하지 않으면 종료날짜는 진행일에 포함하지 않으며, 해당 날가 됨과 동시에 종료)
				$("#totalDay").html("총 <b>"+totalday+"</b>일");//종료날짜까지 선택하였을 경우 총 날짜를 표시
				$("#totalDay").css("font-size", "20px");
			})
		})
		/* 교부날짜를 지정했을때 */
		$("#payDay").on("change", function(){
			var start =  $("#endDay").val();//종료 날짜
			var end = $("#payDay").val();//교부 날짜
			
			var st_arr = start.split('-');//종료 날짜를 -로 구분하여 자름
			var ed_arr = end.split('-');//교부 날짜를 -로 구분하여 자름
			var st_dat = new Date(st_arr[0], st_arr[1], st_arr[2]);//자른 종료날짜 값들로 Date객체 생성
			var ed_dat = new Date(ed_arr[0], ed_arr[1], ed_arr[2]);//자른 교부날짜 값들로 Date객체 생성

			var totalday = (ed_dat-st_dat)/(24*60*60*1000);//총 날짜 계산
			$("#payDay_day").html("종료일로부터  <b>"+totalday+"</b>일후");//교부날짜까지 선택하였을 경우 총 날짜를 표시
			$("#payDay_day").css("font-size", "20px");
		})
		
		//파일 등록시 aside 썸네일 이미지 구현 부분
		var upload = document.getElementById("thumbimage"), 
		holder = document.getElementById("holder"),
		size = document.getElementById("filesize");
		//
		
		var mimes = ["jpg", "png", "gif","JPG","PNG","GIF"];
		upload.onchange = function(e) {
			e.preventDefault();//페이지에서 발생하는 이벤트 외에 별도로 발생하는 브라우저의 행동을 막는 메소드
								//EX)이게 a태그에서 사용되면 a태그의 클릭 이벤트는 발생하나 페이지가 실제로 이동하지 않는다.
			//
			var image = $("#thumbimage").val();
			var imageLength = image.length;
			var imageMime = image.substr(imageLength-3,imageLength);
			var mimechk = false;
//			alert(image+"/"+imageLength+"/"+imageMime);
// 			alert(imageMime);
			for(i=0;i<mimes.length;i++){
				if(imageMime==mimes[i]){
					mimechk = true;
				}
			}
// 			alert(mimechk)
			if(mimechk){
				
			}else{
				alert("이미지 파일만 등록 가능합니다.\njpb, png, gif\n파일확장자를 확인해주세요.");
				upload.value="";
				holder.innerHTML = "<br><br><br>가로:286px 세로:200px을 권장합니다.<br>용량 1M미만 / 영문,숫자 파일명 권장<br>등록가능 확장자 png, gif, jpg";
				size.innerHTML = "";
				holder.classList.add("thumb_none");
				return;
			}
	
			var file = upload.files[0],//input type=file 에 올려진 파일
				reader = new FileReader();//파일
// 				alert(file);
			reader.onload = function(event) {
				var img = new Image();
				img.src = event.target.result;//여기서 event.target은 이벤트가 발생한 요소
//				alert(event.target);//여기서 event.target > object FileReader
//				alert(event.target.result);//여기서 event.target.result > 업로드한 이미지 파일정보
				img.width = 300;//썸네일 이미지 width값 300으로 고정
				img.height = 180;//썸네일 이미지 height값 200으로 고정
				holder.classList.remove("thumb_none");
				holder.innerHTML = "";
				holder.appendChild(img);
				var filesize = "";
				if(file.size<=1024){
					filesize = file.size+"byte";
				}else if(file.size>1024 && file.size<=1024*1024){
					filesize = (file.size/1024).toFixed(2);//소수점 2자리까지 출력
					filesize += "Kbyte";
				}else{
					filesize = (file.size/(1024*1024)).toFixed(2);//소수점 2자리까지 출력
					filesize += "Mbyte 파일크기 너무 큽니다.";
					alert("파일 크기가 너무 큽니다");
				}
				size.innerHTML = filesize;//파일 사이즈 출력
				$("#holder").append("<input type='button' value='삭제' id='del_thumb'>");
				$("#del_thumb").on("click", function() {//썸네일 이미지 등록시 나오는 삭제 버튼 클릭시 호출
					holder.innerHTML = "<br><br><br>가로:286px 세로:200px을 권장합니다.<br>용량 1M미만 / 영문,숫자 파일명 권장<br>등록가능 확장자 png, gif, jpg";//썸네일 이미지 삭제 & 내용 넣기
					holder.classList.add("thumb_none");
					upload.value = "";//선택된 파일 선택 해제
					if(upload.value==""){
						size.innerHTML = "";
					}
					
				})
			};
			reader.readAsDataURL(file);//FileReader.readAsDataURL()은 컨텐츠를 특정 file에서 읽어오는 역할을 한다. 
			
			return false;
		};
		
		/* 프로그램 간략 소개 작성시 aside썸네일 부분에 갱신 */
		$("#introduce").bind("input propertychange", function(){
// 			alert($("#introduce").val());
// 			alert($("#holder_bottom").html());
			$("#aside_text").html($("#introduce").val());
			document.getElementById("text_count").innerHTML = $("#introduce").val().length;
		})
		
		/* 각각 입력부분 공란 체크 */
		$("#ir1submit").on("click", function(){
			//공란 체크 확인용 변수
			var thumbimagechk = false, 
				programchk = false, 
				dNamechk = false, 
				introducechk = false,
				startDaychk = false, 
				endDaychk = false, 
				payDaychk = false, 
				successMoneychk = false;
			
			//썸네일
			if($("#thumbimage").val()==null || $("#thumbimage").val()==""){//썸네일 이미지 선택란이 비어있을 때
// 				alert($("#thumbimage").val());
				$("#thumbimage").siblings("h2").children("#none_alert").css("display", "inline");
// 				$("#thumbimage").focus();
				thumbimagechk = false;
			}else{
				$("#thumbimage").siblings("h2").children("#none_alert").css("display", "none");
				thumbimagechk = true;
			}
			//프로그램 이름
			if($("#program").val()==null || $("#program").val()==""){//프로그램 이름입력란이 비어있을 때
				$("#program").siblings("h2").children("#none_alert").css("display", "inline");
// 				$("#program").focus();
				programchk = false;
			}else{
				$("#program").siblings("h2").children("#none_alert").css("display", "none");
				programchk = true;
			}
			//대표 이름
			if($("#dName").val()==null || $("#dName").val()==""){//대표 이름 입력란이 비어있을 때
				$("#dName").siblings("h2").children("#none_alert").css("display", "inline");
// 				$("#dName").focus();
				dNamechk = false;
			}else{
				$("#dName").siblings("h2").children("#none_alert").css("display", "none");
				dNamechk = true;
			}
			//프로그램 간략 소개
			if($("#introduce").val()==null || $("#introduce").val()==""){//프로그램 간략 소개란이 비어있을때
				$("#introduce").siblings("h2").children("#none_alert").css("display", "inline");
// 				$("#introduce").focus();
				introducechk = false;
			}else{
				$("#introduce").siblings("h2").children("#none_alert").css("display", "none");
				introducechk = true;
			}
			//시작일
			if($("#startDay").val()==null || $("#startDay").val()==""){//시작일이 비어있을 때
				$("#startDay").siblings("h2").children("#none_alert").css("display", "inline");
// 				$("#startDay").focus();
				startDaychk = false;
			}else{
				$("#startDay").siblings("h2").children("#none_alert").css("display", "none");
				startDaychk = true;
			}
			//종료일
			if($("#endDay").val()==null || $("#endDay").val()==""){//종료일이 비어있을 때
				$("#endDay").siblings("h2").children("#none_alert").css("display", "inline");
// 				$("#endDay").focus();
				endDaychk = false;
			}else{
				$("#endDay").siblings("h2").children("#none_alert").css("display", "none");
				endDaychk = true;
			}
			//교부예정일
			if($("#payDay").val()==null || $("#payDay").val()==""){//교부예정일이 비어잇을 때
				$("#payDay").siblings("h2").children("#none_alert").css("display", "inline");
// 				$("#payDay").focus();
				payDaychk = false;
			}else{
				$("#payDay").siblings("h2").children("#none_alert").css("display", "none");
				payDaychk = true;
			}
			//목표 금액
			if($("#successMoney").val()==null || $("#successMoney").val()==""){//
				$("#successMoney").siblings("h2").children("#none_alert").css("display", "inline");
// 				$("#successMoney").focus();
				successMoneychk = false;
			}else{
				$("#successMoney").siblings("h2").children("#none_alert").css("display", "none");
				successMoneychk = true;
			}

			
			/* submit */
			if(thumbimagechk == true && programchk == true && dNamechk == true && introducechk == true &&startDaychk == true && endDaychk == true && payDaychk == true && successMoneychk == true){
				$("#ir1form").submit();
			}else{
// 				$("body").scrollTop(0);
				$('html, body').animate({scrollTop: '0px'}, 300);
			}
		})
		
		/* 목표 금액 입력시 금액 콤마처리 및 숫자만 입력받기 */
		$("#successMoney").keyup(function(event) {
			inputNumberFormat(this);
		});
	})
	
	/* 목표 금액 입력시 금액 콤마처리 및 숫자만 입력받기 */
	//콤마찍기
	function comma(str) {
	    str = String(str);
	    return str.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
	}
	
	
	//콤마풀기
	function uncomma(str) {
	    str = String(str);
	    return str.replace(/[^\d-]+/g, '');
	}
	
	//천 단위마다 콤마찍기(단위 증가 시 콤마를 풀고 다시 찍음)
	function inputNumberFormat(obj) {
	    obj.value = comma(uncomma(obj.value));
	}
	
</script>
</head>
<body>
<%
	Calendar cal = new GregorianCalendar(Locale.KOREA);
	cal.setTime(new Date(System.currentTimeMillis()+(7*24*60*60*1000)));//오늘의 날짜에서 +7일(7*24*60*60*1000)한값, 신청 수락 기간동안 바로 업로드 안되게 지정
	SimpleDateFormat today = new SimpleDateFormat("yyyy-MM-dd");
%>
	<!-- 본문 전체 감싸는 div -->
	<div class="wrap">
		<!-- 좌측 데이터 입력부분 -->
		<section>

			<div class="div_section">
<!--		 		<form action="investRequest.do" method="post"> -->
				<form action="investRequestAction1.iR" method="post" id="ir1form" enctype="multipart/form-data">
					<div>
						<h2>대표 썸네일 이미지<span id='none_alert'>* 썸네일 이미지를 등록해주세요.</span></h2><br>
						<input type="file" name="thumbimage" id="thumbimage"><span id="filesize"></span>
						<h4>캠페인을 잘 나타내며, 멋지게 홍보할 수 있는 대표 썸네일을 등록해주세요<br>가로:286px 세로:200px을 권장합니다.<br>용량 1M미만 / 영문,숫자 파일명 권장 / 등록가능 확장자 png, gif, jpg</h4>
					</div>
					<br><br>
					
					<div>
						<h2>투자 프로그램 이름<span id='none_alert'>* 투자 프로그램 이름을 입력해주세요</span></h2><br>
						<input type="text" name="program" id="program">
						<h4>캠페인을 쉽게 이해할 수 있는 제목을 지어주세요. 홍보에 도움이 됩니다.</h4>
					</div>
					<br><br>
					
					<div>
						<h2>대표자 이름<span id='none_alert'>* 대표자 이름을 입력해주세요</span></h2>
						<input type="text" name="dName" id="dName"><br>
						<h4></h4>
					</div>
					<br><br>
					
					<div>
						<h2>프로그램 간략 소개<span id='none_alert'>* 프로그램 간략 소개를 입력해주세요</span></h2>
						<textarea rows="5" cols="60" name="introduce" id="introduce" maxlength="100" max-text-length="100"></textarea>
						<div style="display:inline-block; padding-top:130px;">
							<span id="text_count">0</span>/100
						</div>
						<h4>캠페인에 대한 간략한 소개를 적어주세요. 홍보에 도움이 됩니다.</h4>
					</div>
					<br><br>
					
					<div>
						<h2>카테고리 설정</h2>
						<select id="category" name="category">
							<option value="TechDev" selected>기술/개발</option>
							<option value="Art">예술</option>
							<option value="Env">환경</option>
							<option value="Edu">교육</option>
						</select>
						<h4>캠페인에 잘 맞는 카테고리를 설정해주세요.</h4>
					</div>
					<br><br>
					
					<div>
						<h2>해쉬태그</h2>
						<input type="text" name="hashtag" id="hashtag">
						<h4>해쉬태그는 머릿말에 #을 붙여서 구분해주세요.</h4>
					</div>
					<br><br>
					
					<div>
						<h2>목표 기간<span id='none_alert'>* 목표기간을 확인해주세요</span></h2>
						<input type="date" name="startDay" id="startDay" min="<%=today.format(cal.getTime())%>"> 부터
						<input type="date" name="endDay" id="endDay" readonly="readonly"> 까지 &nbsp;&nbsp; 
						<span id="totalDay"></span>
						<h4>
							약 30일의 목표기간을 추천합니다. 기간이 너무 짧으면 목표금액을 달성하기에 너무 촉박할 수 있습니다.
							또한 기간이 너무 길어질 경우엔 참여자들이 부담을 느껴 참여율이 떨어지기 때문입니다.<br>
							최대 기간은 60일입니다. 시작일 설정시 심사기간을 고려해주세요.
						</h4>
					</div>
					<br><br>
					
					<div>
						<h2>교부 예정일<span id='none_alert'>* 교부 예정일을 확인해주세요</span></h2>
						<input type="date" name="payDay" id="payDay" readonly="readonly">&nbsp;&nbsp;
						<span id="payDay_day"></span>
						<h4>운용한 자금을 투자자 분들에게 원금과 수익금을 돌려 드리는 날입니다. 운용기간은 3개월 ~ 2년 이하  기간으로 날짜를 정하여 등록하는 것을 추천드립니다. </h4>
					</div>
					<br><br>
					
					<div>
						<h2>목표 금액<span id='none_alert'>* 목표 금액을 입력해주세요</span></h2>
						<input type="text" name="successMoney" id="successMoney"><span id="sMoney">원</span>
						<br><br>
						<p id="sMoney_p">
							목표금액은 캠페인을 실행하기 위해 필요한 비용과 리워드 제작비, 배송비, 유캔스타트 수수료를 모두 고려한 최소한의 금액으로 설정하세요.<br>
							모금액이 목표금액 이상 모여야만 스타터에게 전달되고, 미달 될 경우에는 참여자에게 모두 환불되기 때문입니다.<br>
							하지만 목표금액 이상 펀딩 되는 것에는 제한이 없기 때문에 목표금액 보다 더 많은 모금액을 기대할 수 있습니다.
						</p>
					</div>
					<br><br>
					
					<div id="submitdiv"><!-- button -->
						<input type="button" id="ir1submit" value="다음 단계">
					</div>
				</form>
			</div>
		</section>
		<!-- 좌측 데이터 입력부분 -->
		<!-- 우측 가이드  -->
		<aside class="junside">
			<div class="div_aside">
				<div>
					<h2>썸네일 미리보기</h2>
					<h4>리스트 화면에 노출되는 카드 썸네일을 확인하시고, 진행하시는 캠페인이 잘 설명되었는지 확인해보세요.</h4>
				</div>
				<div id="holder" class="thumb_none">
					<br><br><br>
					가로:286px 세로:200px을 권장합니다.
					<br>
					용량 1M미만 / 영문,숫자 파일명 권장<br>
					등록가능 확장자 png, gif, jpg
				</div>
				<div id="holder_bottom">
					<textarea id="aside_text" readonly="readonly" style="resize:none;"></textarea>
					<div id="aside_example">
						<span id="aside_example_money">123,456,789원</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span id="aside_example_text">80%달성</span> &nbsp;&nbsp; <span id="aside_example_text">30일남음</span>
					</div>
				</div>
			</div>
		</aside>
		<!-- 우측 가이드  -->
		
	</div>
	<!-- 본문 전체 감싸는 div -->
	<div class="clear"></div>
</body>
</html>