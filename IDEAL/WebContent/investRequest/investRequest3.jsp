<%@page import="net.investRequest.db.InvestRequestDto"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<style>
	@import url(//fonts.googleapis.com/earlyaccess/notosanskr.css);
	*{
		margin:0;
		padding:0;
		font-family: 'Noto Sans KR', sans-serif;
	}
	div.wrap{
		width:1240px;
		margin:0 auto;
	}
	.clear{
		clear: both;
	}
	section, aside{
		float:left;
	}
	section{
		width:816px;
		min-height:500px;
		opacity: 0.8;
/* 		background-color:#999; */
	}
	aside.junside{
		width:392px;
		min-height:500px;
		margin-left:32px;
		background-color: #e9ecef;
		opacity: 0.8;
	}
	a{
		text-decoration: none;
		color:#003399;
	}
	#sectionwrap{
		padding:30px;
	}
	input{
		font-size: 15px;
	}
	
	input[type=button]{
		padding:13px;
 		border-radius:5px;
 		border:1px solid #fff;
	}
	input[type=file]{
		width:80%;
		padding:10px;
	}
	div#div_add{
		height:250px;
	}
	input#file{
		display:none;
	}
	input#addfilebtn{
		display:block;
	}
	div#mainthumbdiv{
		width:100%;
		height:20%;
		margin-left: 30px;
	}
	div#hiddenfile{
		display:none;
	}
	
	
	div#asidewrap{
		padding:30px;
	}
	div#storyguidewrap{
		padding-top: 60px;
/* 		width:100%; */
	}
	div#storyguide{
		width:90%;
 		margin:0 auto;
		min-height:10px;
		padding:20px;
		border-radius:15px;
		background-color:#789;
	}
	
</style>
<script type="text/javascript">
function openAddFile(d){
	var filediv =  d.nextSibling.nextSibling;
	var ifile = filediv.firstChild.nextSibling;
// 	alert(filediv);
// 	alert(ifile)
	filediv.style.display = "block";
	ifile.style.display = "inline";
	d.style.display = "none";
	
}
function delfile(d){
// 	alert(d);
	var fileinput = d.previousSibling;
	fileinput.value = "";
// 	alert(fileinput.style.display);
	fileinput.style.display = "";
	var filediv = d.parentNode;
// 	alert(filediv);
	filediv.style.display = "none";
	var addbtn = filediv.previousSibling.previousSibling;
// 	alert(addbtn);
	addbtn.style.display = "block";
}


function isFileCheck(f){//변수 f 는 document.forms[1] 이다
	/* 두번째 form에 접근하여 폼안에 있는 모든 input태그 수 구하기 */
	//forms[]배열 : <form>에 접근 방법
	//elements : <form>태그 안에 모든 <input>요소를 말함
	//length : 개수 , 길이
	var cnt = f.elements.length;
	
	/* 위 cnt변수에 들어갈 <input>태그의 개수에 대한 설명 */
	//-> 두번째 <form>태그에서 고정된 <input type="hidden"> 3개,
	//	<input type="button"> 1개 이므로 총 4개

	//누락된 파일 업로드위치를 나타낼 수 있는 변수
	var filecnt = 0;
	
	/* 
		<form>태그에서 저희는 업로드된 파일개수를 사용자에 의해서 동적으로 입력받기 때문에
		<form>태그 안의 for문을 이용하여 <input type="file">태그가 동적으로 만들어진다
		동적으로 만들어진 <input type="file">태그중에서 업로드할 파일 경로를 지정하지 않은
		<input type="file">태그가 있다면 x번째 파일 정보가 누락되었습니다 라고 경고메세지 띄워 줘야 한다.
	 */

	//for반복문을 이용해서 <form>태그 안에 있는 모든 <input>태그의 갯수만큼 반복
	for(i=0;i<cnt;i++){
		//만약에 <form>내부에 있는 <input>태그의 type이 file과 같고
		if(f.elements[i].type=="file"){
			//<input>태그의 type이 file일때만 filecnt값 ++ 하기
			filecnt++;
			
			var tmpfile = null;
			var tmp = "";
			var filesplit = "";
			if(f.elements[i].value!=""){
				tmpfile = f.elements[i].value;
// 				alert(tmpfile);
				filesplit = tmpfile.split("\\");
				for(j=0;j<filesplit.length;j++){
					tmp = filesplit[j];
				}
// 				alert(tmp);
			}else if(f.elements[i].value=="" && (f.elements[i].style.display!="")){
// 				alert(f.elements[i].style.display);
				//x번째 <input type="file">내용이 비었습니다. 경고메세지
				var msg = filecnt+"번째 대표 이미지 파일 정보가 누락되었습니다.\n올바른 입력을 해주세요.";//
				alert(msg);
				//포커스 주기
				f.elements[i].focus();
				
				return;
			}
		}//바깥 if
	}//end of for

	//<form>태그 전송
	alert("파일 전송");
// 	alert("inputfile\n"+inputfile);
	f.submit();
}//end of check()
</script>
<body>
	<div class="wrap">
		<section>
			<div id="sectionwrap">
				<h1>참고 자료</h1>
				<br><br>
				<form action="./investRequestAction3.iR" method="post" enctype="multipart/form-data">
					<div id="div_add">
						<div id="mainthumbdiv">
							<input type='button' id="addfilebtn" value="파일 추가" name="filebtn1" onclick="openAddFile(this)">
							<div id="hiddenfile">
								<input type="file" id="file" name="mainthumb1"><input type="button" value="삭제" onclick="delfile(this)">
							</div>
						</div>
						<div id="mainthumbdiv">
							<input type='button' id="addfilebtn" value="파일 추가" name="filebtn2" onclick="openAddFile(this)">
							<div id="hiddenfile">
								<input type="file" id="file" name="mainthumb2"><input type="button" value="삭제" onclick="delfile(this)">
							</div>
						</div>
						<div id="mainthumbdiv">
							<input type='button' id="addfilebtn" value="파일 추가" name="filebtn3" onclick="openAddFile(this)">
							<div id="hiddenfile">
								<input type="file" id="file" name="mainthumb3"><input type="button" value="삭제" onclick="delfile(this)">
							</div>
						</div>
						<div id="mainthumbdiv">
							<input type='button' id="addfilebtn" value="파일 추가" name="filebtn4" onclick="openAddFile(this)">
							<div id="hiddenfile">
								<input type="file" id="file" name="mainthumb4"><input type="button" value="삭제" onclick="delfile(this)">
							</div>
						</div>
						<div id="mainthumbdiv">
							<input type='button' id="addfilebtn" value="파일 추가" name="filebtn5" onclick="openAddFile(this)">
							<div id="hiddenfile">
								<input type="file" id="file" name="mainthumb5"><input type="button" value="삭제" onclick="delfile(this)">
							</div>
						</div>
					</div>
					<br><br>
					<div style="text-align: center">
						<input type="button" value="전송" name="submitbtn" onclick="isFileCheck(this.form)" style="width:60%;">
					</div>
				</form>
			</div>
		</section>
		<aside class="junside">
			<div id="asidewrap">
				<div id="storyguidewrap">
					<div id="storyguide">
						<h2>중앙기록관리관에 대한 관련정보 제공 의무</h2>
						<h5><a href="https://txsi.hometax.go.kr/docs/common/customer/popup_law_jomun.jsp?thetext=%EC%A1%B0%EC%84%B8%ED%8A%B9%EB%A1%80%EC%A0%9C%ED%95%9C%EB%B2%95%EC%A0%9C117%EC%A1%B0" target="_blank">(법 117의13)</a></h5>
						<h4 style="padding:10px;">온라인소액투자중개업자는 증권의 발행한도 및 투자한도 관리 등의 업무를 자본시장법에 따라 중앙기록관리관에 위탁하도록 되어 있으며, 중개업자는 발행인의 모집의뢰나 투자자의 청약주문을 받은 경우에 해당 의뢰와 주문에 대한 자료를 중악기록관리기관에 제공하여야 합니다.</h4>
						<br>
						<h5>예) 회사정관, 결산재무제표, 벤처기업확인서, 사업자등록증, 법인등기부등본 등을 올려주세요.</h5>
					</div>
				</div>
			</div>
		</aside>
		<div class="clear"></div>
	</div>
</body>
</html>