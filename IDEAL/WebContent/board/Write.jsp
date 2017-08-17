<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="./css/board1.css" type="text/css" rel="stylesheet">
<script type="text/javascript">
$(function(){
	$("#file").on("change", function(){
		var file = $("#file").val();
		var file_arr = file.split("\\");
		var filename = "";
		for(a=0;a<file_arr.length;a++){
			filename = file_arr[a];
		}
// 		alert(filename);//파일 이름 확인
	})
})
function boardCheck(f){
	if(f.title.value==""){
		alert("제목을 입력해 주세요.");
		f.title.focus();
		return false;
	}else if(f.content.value==""){
		alert("내용을 입력해 주세요.");
		f.content.focus();
		return false;
	}else{
		f.submit();	
	}
}
function check(){

	var size = document.getElementById("file").files[0].size;
	var file = document.getElementById("file");
	var filevalue = document.getElementById("file").value;//acb.png
	var name = filevalue.slice(filevalue.indexOf(".") +1).toLowerCase(); //파일 확장자를 잘라내고, 비교를 위해 소문자로 만듬
// 	alert(size);
// 	alert(filevalue);
// 	alert(name);
	if(name != "jpg" && name != "png" &&  name != "gif"){
		alert("jpg,gif,png만 지원합니다.");
		document.getElementById('fileck').innerHTML='jpg,gif,png만 지원합니다.';
		file.value ="";
		
		
	}else if(size>10*1024*1024){
		alert("파일이 10MB가 넘습니다");
		document.getElementById('fileck').innerHTML='파일이 10MB가 넘습니다.';
		file.value = "";
		
		
	}else{
		document.getElementById('fileck').innerHTML='';
	}
}
	
	
</script>
<%
	String memberEmail = (String)request.getAttribute("memberEmail");
	String nickName = (String)request.getAttribute("nickName");
%>

</head>
<script>
	
</script>
<body>
<div class="wrap">
<form action="addpost.bo" enctype="multipart/form-data"  method="post">
	<table width="1028px" class="tablewri">
		<tr class="boardtr"> 
			<th colspan="2">글제목<input  class="thtitle" type="text" name="title"></th>
			<th>회원이름<input type="text" name="nickName" readonly="readonly" value="${nickName}"></th>
		</tr>
		<tr>
			
		</tr>
		<tr>
			<td colspan="4">
			<input type="text" name="content" id="content" class="incontent"></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="file" id="file" name="file" onchange="check()"><span id="fileck"></span>
				<br/>용량 10M미만 / 영문,숫자 파일명 권장 / 등록가능 확장자 png, gif, jpg
			</td>
		
			<td colspan="2"><input class="wrbt" type="submit" value="글쓰기">
			<input class="rewrbt" type="reset" value="다시입력"></td>
		</tr>
	</table>
	<div class="clear"></div>
</form>
</div>
</body>
</html>