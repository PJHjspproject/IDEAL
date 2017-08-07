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
	div.wrap{
		width:1240px;
		margin:0 auto;
		padding-top:100px;
	}
	.clear{
		clear: both;
	}
	section, aside{
		float:left;
	}
	section{
		width:816px;
		min-height:800px;
		opacity: 0.8;
/* 		background-color:#999; */
	}
	aside.junside{
		width:392px;
		min-height:800px;
		margin-left:32px;
		background-color: #e9ecef;
		opacity: 0.8;
	}
	input{
		font-size: 15px;
	}
	img{
		width:100%;
		display:none;
	}
	
	input[type=button]{
		padding:13px;
 		border-radius:5px;
 		border:1px solid #fff;
	}
	input[type=file]{
		padding:10px;
	}
	
	div#div_add{
		height:250px;
	}
	div#div_add, div#holder{
	 float:left;
	}
	div#holder{
		width:300px;
		height:250px;
		background-color:#444;
		background-repeat: no-repeat;
		background-size:300px 250px;
		background-image: url("");
	}
	.thumb_none{
		background-color:#444;
		text-align: center;
	}
	.section_wrap{
		padding:30px;
	}
	input#file{
		display:none;
	}
	input#addfilebtn{
		display:block;
	}
	div#mainImagediv{
		width:100%;
		height:20%;
		margin-left: 30px;
	}
	div#hiddenfile{
		display:none;
	}
	
	
	div#div_mainadd div{
 		display:none;
	}
	textarea{
		width:100%;
		height:150px;
	}
	input#mainfile{
/* 		display:inline; */
	}
	input#addmainbtn{
		width:100%;
		padding:10px;
		text-align:center;
		line-height:100%;
		display:block;
		background-image: url("./image/whitedownarrow.png");
		background-repeat: no-repeat;
		background-position: 0%;
		background-size: 5% 100%;
	}
	div#submitdiv{
		width:100%;
		text-align: center;
	}
	input#filesubmit{
		width:60%;
		padding:15px;
		width:300px;
/* 		display:none; */
	}
	
	div#asidewrap{
		padding:30px;
	}
	div#storyguidewrap{
/* 		width:100%; */
	}
	div#storyguide{
		width:90%;
 		margin:0 auto;
		min-height:10px;
		padding:20px;
		border-radius:15px;
		background-color:#789;
		display:none;
	}
	
</style>
<script src="https://code.jquery.com/jquery-3.2.1.min.js" integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4=" crossorigin="anonymous"></script>
<script type="text/javascript">
var nchk = 1;
var mtc = "";
var mfc = "";


/* 모든 type=file의 내용을 담을 array생성 */
/* 
var inputfile = new Array(11);
$(function(){
	var mimechk = false;
	$("input[type=file]").on("change", function(event){
		var upload = event.target;  
		var filename = upload.value;
		var holder = $("#holder");
		var mimes = ["jpg", "png", "gif"];
		
// 		alert(filename);
		var imagelength = filename.length;
// 		alert(imagelength);
		var imagemime = filename.substr(imagelength-3, imagelength);
// 		alert(imagemime);
		
		for(i=0;i<mimes.length;i++){
			if(imagemime==mimes[i]) mimechk = true;
		}
		
		if(mimechk){
// 			alert("이미지입니다");
		}else{
			alert("이미지 파일만 등록 가능합니다.\njpb, png, gif\n파일확장자를 확인해주세요.");
			upload.value="";
			holder.innerHTML = "<br><br><br><br>720*280 사이즈 / 2M미만 / 영문,숫자 파일명 권장 / 등록가능 확장자 png, gif, jpg";
			holder.classList.add("thumb_none");
			return;
		}
		
		var file = upload.files[0],
			reader = new FileReader();
// 			alert(file);
// 			alert(reader);
		reader.onload = function(event){
// 			alert("!");
		}
// 			alert(reader.onload);
		
		
	})
})
 */

function openAddFile(d){
	var filediv =  d.nextSibling.nextSibling;//파일추가 버튼
	var ifile = filediv.firstChild.nextSibling;//input[type=file]태그
// 	alert(filediv);
// 	alert(ifile)
	filediv.style.display = "block";
	ifile.style.display = "inline";
	d.style.display = "none";
	
}

/* 파일추가 버튼 클릭하여 추가된 input태그(감싸고 있는 div태그 포함) 제거함수 */
function delfile(d){
// 	alert(d);
	var fileinput = d.previousSibling;//input[type=file]버튼
	fileinput.value = "";
// 	alert(fileinput.style.display);
	fileinput.style.display = "";
	var filediv = d.parentNode;
// 	alert(filediv);
	filediv.style.display = "none";
	var addbtn = filediv.previousSibling.previousSibling;//파일추가 버튼
// 	alert(addbtn);
	addbtn.style.display = "block";
}

/* 스토리 시작하기 버튼을 클릭하여 본문 내용을 입력하는 태그 노출 */
function openAddMain(d){
	var maindiv = d.nextSibling.nextSibling;//본문 내용 입력하는 버튼 클릭시 클릭된 버튼의 다음 형제 <div>태그. 여기서는 다음 본문내용 입력하는 내용을 담고 잇는 <div>
// 	alert(maindiv);
// 	alert(maindiv.innerHTML);
	maindiv.style.display = "block";
	var namevalue = d.getAttribute("name");//attribute중 name의 값
	var storyguide = document.getElementById("storyguide");//<aside>태그 부분의 도움말이 들어갈 부분
	if(namevalue=="addmainbtn1"&&nchk==1){//여기서 nchk는 상태 체크용 변수. 만약 상태체크가 되지 않는다면 클릭할 때 마다 도움말 부분에 내용이 계속 추가된다.
		d.style.backgroundImage = "url('none')";
		if(nchk==1)nchk+=1;//상태 체크 및 상태 체크용 변수값 변경
		storyguide.style.display = "block";
		storyguide.innerHTML+="<h3>1. 캠페인 소개</h3><br><p>- 캠페인 전체를 요약하는 간략한 소개를 해주세요.</p><br>"
													+"<p>- 이 캠페인가 여러분에게 왜 중요한가요?</p><br>";
		location.href = "#filesubmit";
	}else if(namevalue=="addmainbtn2"&&nchk==2){
		d.style.backgroundImage = "url('none')";
		if(nchk==2)nchk+=1;
		storyguide.innerHTML+="<h3>2. 캠페인 상세 소개</h3><br><p>- 이 캠페인를 통해 무엇을 하고자 하나요?</p><br>"
														+"<p>- 투명하고 구체적으로 사람들에게 무엇을 하고자 하는지 자세하게 알려주세요.</p><br>"
														+"<p>- 캠페인의 계획, 기능, 특징, 스펙 등을 이미지와 영상을 통해 알려주셔도 좋고, 펀딩한 사람들에게 어떠한 특별한 보상이 주어지나요? 사람들에게 이미지를 보여주세요!</p><br>"
														+"<p>- 캠페인를 성공하게 되면 다음의 진행 계획은 어떻게 되는지 같이 알려주세요.</p><br>";
		location.href = "#filesubmit";
	}else if(namevalue=="addmainbtn3"&&nchk==3){
		d.style.backgroundImage = "url('none')";
		if(nchk==3)nchk+=1;
		storyguide.innerHTML+="<h3>3. 팀소개</h3><br><p>- 캠페인를 함께 진행할 구성원들을 소개해주세요.</p><br>"
												+"<p>- 팀이 아닌 혼자일 경우에도 경력이나 프로필 등 당신이 누군지 소개해주세요.</p><br>";
		location.href = "#filesubmit";
	}
}

/*check() -> 글쓰기또는 답글쓰기시 체크 function*/
//function check(){
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
			
			
			
// 			alert(f.elements[i].style);
			//만약에 <input type="file">태그 중에서 업로드할 파일 경로가 비어있으면(업로드할 파일을 지정하지 않았다면)
			if(f.elements[i].value=="" && (f.elements[i].style.display!="")){
// 				alert(f.elements[i].style.display);
				//x번째 <input type="file">내용이 비었습니다. 경고메세지
				var msg = filecnt+"번째 대표 이미지 파일 정보가 누락되었습니다.\n올바른 입력을 해주세요.";//
				alert(msg);
				//포커스 주기
				f.elements[i].focus();
				
				return;
			}else if(f.elements[i].value=="" && filecnt>5){
				var msg = "";
				switch(filecnt){
				case 6:
					msg = "캠페인 소개 ";
					break;
				case 7:
					msg = "캠페인 상세 소개 ";
					break;
				case 8:
					msg = "팀 소개 "
					break;
				}
				msg += "이미지 파일 정보가 누락되었습니다.\n올바른 입력을 해주세요.";
				mtc="";//전송 버튼 누를때 마다 전체 체크를 하여 mtc와 mfc에 추가되므로 초기화 해 주지 않으면 누적입력된다
				mfc="";//전송 버튼 누를때 마다 전체 체크를 하여 mtc와 mfc에 추가되므로 초기화 해 주지 않으면 누적입력된다
				alert(msg);
				f.elements[i].focus();
				
				return;
			}else if(f.elements[i].value!=""){//모든 파일 elements를 가져와서 그 값이 비어있지 않을때, 즉 이미지 파일이 load됫을때
				tmpfile = f.elements[i].value;
// 				alert(tmpfile);
				//c:\\fakepath\\파일이름 형태로 저장되기 때문에 파일이름만 추출하기 위한 작업
				filesplit = tmpfile.split("\\");
				for(j=0;j<filesplit.length;j++){
					tmp = filesplit[j];
				}
// 				alert(tmp);
				/* 같은 이름의 파일을 올렸을때, 서로 모두 체크해서 둘다 넘쳐버린다 */
				if(filecnt<=5){//mainThumb
					if(mtc!="") mtc+="$"+tmp;
					else mtc+=tmp;
// 					alert(cnt+"/"+filecnt+"/"+tmp);
				}else{//mainImage
					if(mfc!="")	mfc+="$"+tmp;
					else mfc+=tmp;
// 					alert(cnt+"/"+filecnt+"/"+tmp);
				}
			}	
		}//바깥 if
	}//end of for

	$("#mainthumbcheck").val(mtc);
	$("#mainimagecheck").val(mfc);
	//<form>태그 전송
	alert("파일 전송");
// 	alert("inputfile\n"+inputfile);
	f.submit();
}//end of check()

//메인 이미지첨부시 각 파트별로 등록된 이미지파일 유무 체크 및 미리보기 제공
function mainImagePreview(d){
	var previewimg = d.previousSibling.previousSibling;//파일 업로드시 미리보기를 뿌려줄 <input type="file">태그의 이전 형제 <img>태그
// 	alert(d.value);
	var file = d.files[0];//<input type="file">태그의 첫번째 파일 > 여러개 올리는것도 가능하지만 이경우에는 하나만 올리게 되어 잇으므로 0번째 배열의 값을 가져오는것이다.
	
	var IMG_FORMAT = "\\.(gif|jpg|jpeg|png)$";
	 
    if(!(new RegExp(IMG_FORMAT, "i")).test(d.value)){//정규표현식으로 파일 확장자를 학인한 후 이미지 파일이 아닌경우
    	alert("이미지 파일만 등록 가능합니다.\njpb, png, gif\n파일확장자를 확인해주세요.");
    	previewimg.style.display = "none";
    	previewimg.src = "";
    }else{
//     	alert("이미지 파일 입니다.");
		var reader = new FileReader();//FileReader객체 생성
		reader.addEventListener("load", function(){//정규표현식으로 파일 확장자를 학인한 후 이미지 파일인 경우
			previewimg.style.display = "block";
			previewimg.src = reader.result;
// 			alert(reader);
// 			alert(reader.result);
		}, false)
    }
//     if(file){
    	reader.readAsDataURL(file);
    	return false;
//     }
	
}

//메인 섬네일 이미지 등록시 이미지파일 유무 체크 및 좌측 썸네일 출력부 썸네일 미리보기 출력
function mainThumbPreview(d){
	var holder = document.getElementById("holder");
	var file = d.files[0];
	var IMG_FORMAT = "\\.(gif|jpg|jpeg|png)$";
	 
    if(!(new RegExp(IMG_FORMAT, "i")).test(d.value)){//정규표현식으로 파일 확장자를 학인한 후 이미지 파일이 아닌경우
    	alert("이미지 파일만 등록 가능합니다.\njpb, png, gif\n파일확장자를 확인해주세요.");
    	$("#holder").css({"background-image":"none", "background-color":"#444"});
    	$("#holder").html("<br><br><br><br>570*380 사이즈 / 2M미만 / 영문,숫자 파일명 권장 / 등록가능 확장자 png, gif, jpg");
    	d.value = "";
    }else{
//     	alert("이미지 파일 입니다.");
		var tmpname = "";
		var tmpname_arr = d.value.split("\\");
		for(a=0;a<tmpname_arr.length;a++){
			tmpname = tmpname_arr[a];
		}
		var reader = new FileReader();//FileReader객체 생성
		reader.addEventListener("load", function(event){//정규표현식으로 파일 확장자를 학인한 후 이미지 파일인 경우
// 			holder.style.backgroundImage = "url('"+event.target.result+"');";
// 			holder.style.backgroundImage = "none";
			$("#holder").html("");
			$("#holder").css({"background-image":"url("+event.target.result+")","background-color":"transparent"});
		}, false)
    }
    if(file){
    	reader.readAsDataURL(file);
    	return false;
    }else{
    	$("holder").html("이미지 파일만 등록 가능합니다.\njpb, png, gif\n파일확장자를 확인해주세요.");
    }
}



</script>
</head>
<body>
	<div class="wrap">
		<section>
			<div class="section_wrap">
				
				<form action="investRequestAction2.iR" method="post" enctype="multipart/form-data">
					<input type="hidden" name="mainthumbcheck" id="mainthumbcheck" value="">
					<input type="hidden" name="mainimagecheck" id="mainimagecheck" value="">
					<h2>대표 이미지 선택</h2>${nickName }
					<br>
					<hr>
					<br>
					<div>
						<!-- 대표 이미지 썸네일 출력 부분 -->
						<div id="holder" class="thumb_none"><br><br><br><br>720*280 사이즈 / 2M미만 / 영문,숫자 파일명 권장 / 등록가능 확장자 png, gif, jpg</div>
						<!-- 대표 이미지 입력받는 부분 -->
						<div id="div_add">
							<div id="mainImagediv">
<!-- 								<input type='button' id="addfilebtn" value="파일 추가" name="filebtn1" onclick="openAddFile(this)"> -->
<!-- 								<div id="hiddenfile"> -->
									<input type="file" id="file" name="mainThumb1" style="display:block;" onchange="mainThumbPreview(this)">
<!-- 								</div> -->
							</div>
							<div id="mainImagediv">
								<input type='button' id="addfilebtn" value="파일 추가" name="filebtn2" onclick="openAddFile(this)">
								<div id="hiddenfile">
									<input type="file" id="file" name="mainThumb2" onchange="mainThumbPreview(this)"><input type="button" value="삭제" onclick="delfile(this)">
								</div>
							</div>
							<div id="mainImagediv">
								<input type='button' id="addfilebtn" value="파일 추가" name="filebtn3" onclick="openAddFile(this)">
								<div id="hiddenfile">
									<input type="file" id="file" name="mainThumb3" onchange="mainThumbPreview(this)"><input type="button" value="삭제" onclick="delfile(this)">
								</div>
							</div>
							<div id="mainImagediv">
								<input type='button' id="addfilebtn" value="파일 추가" name="filebtn4" onclick="openAddFile(this)">
								<div id="hiddenfile">
									<input type="file" id="file" name="mainThumb4" onchange="mainThumbPreview(this)"><input type="button" value="삭제" onclick="delfile(this)">
								</div>
							</div>
							<div id="mainImagediv">
								<input type='button' id="addfilebtn" value="파일 추가" name="filebtn5" onclick="openAddFile(this)">
								<div id="hiddenfile">
									<input type="file" id="file" name="mainThumb5" onchange="mainThumbPreview(this)"><input type="button" value="삭제" onclick="delfile(this)">
								</div>
							</div>
						</div>
					</div>
					<div class="clear"></div>
					<br>
					<hr>
					<br>
					
					<!-- 스토리(본문내용) 입력받는 부분 -->
					<div id="div_mainadd">
						<input type="button" id="addmainbtn" value="캠페인 소개" name="addmainbtn1" onclick="openAddMain(this)">
						<div>
							<img src="">
							<input type="file" id="mainfile" name="mainimage1" onchange="mainImagePreview(this)"><br>
							<textarea id="maintext" name="maintext1"></textarea>
							<br>
							<input type="button" id="addmainbtn" value="캠페인 상세 소개" name="addmainbtn2" onclick="openAddMain(this)">
							<div>
								<img src="">
								<input type="file" id="mainfile" name="mainimage2" onchange="mainImagePreview(this)"><br>
								<textarea id="maintext" name="maintext2"></textarea>
								<br>
								<input type="button" id="addmainbtn" value="팀 소개" name="addmainbtn3" onclick="openAddMain(this)">
								<div>
									<img src="">
									<input type="file" id="mainfile" name="mainimage3" onchange="mainImagePreview(this)"><br>
									<textarea id="maintext" name="maintext3"></textarea>
									<br>
								</div>
							</div>
						</div>
					</div>
					<br>
					<div id="submitdiv">
						<input type="button" value="전송" id="filesubmit" onclick="isFileCheck(this.form)">
					</div>
				</form>
			</div>
		</section>
		<aside class="junside">
			<div id="asidewrap">
				<h3>스토리 가이드</h3>
				<br><br>
				<h4>대표 이미지</h4>
				<p>
					캠페인을 잘 나타내며, 멋지게 홍보할 수 있는 이미지를 등록해주세요. 최대 5장 등록이 가능합니다.
				</p>
				<br><br>
				<p>
					570*380 사이즈 <span id="font-gray">/ 2M미만 / 영문,숫자 파일명 권장 / 등록가능 확장자 png, gif, jpg</span>
				</p>
				<br><br>
				
				<div id="storyguidewrap">
					<div id="storyguide"></div>
				</div>
			</div>
		</aside>
		<div class="clear"></div>
	</div>
</body>
</html>