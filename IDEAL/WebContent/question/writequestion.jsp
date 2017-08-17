<%@page import="net.question.db.questionDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="./css/question1.css" type="text/css" rel="stylesheet">

<script src="//code.jquery.com/jquery.min.js"></script>
<script type="text/javascript">
 function check(){

	var size = document.getElementById("file1").files[0].size;
	var file = document.getElementById("file1");
	var filevalue = document.getElementById("file1").value;//acb.png
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

</head>
<%


String memberEmail = (String)session.getAttribute("memberEmail");
String nickName = (String)session.getAttribute("nickName");

%>
<body>
<div class="wrap">
<center>
		<form action="insertquestion.qU" enctype="multipart/form-data" method="post">
		<table width="1028px" class="tableAll">
			<tr class="boardtr">
				<th colspan="2">글제목<input class="thtitle" type="text" name="title"></th>
				<th>회원이름<input type="text" value="${nickName }" name="nickName" readonly="readonly"></th>
			</tr>
			<tr>
				<td colspan="4">
				<textarea rows="5" cols="100" name="content" style="width: 1028px;" class="txtarere">
				</textarea>
				</td>
			</tr>
			<tr>
				<td>
					<input type="file" name="file1" id="file1" onchange="check()"><span id="fileck"></span>
					<br/>용량 10M미만 / 영문,숫자 파일명 권장 / 등록가능 확장자 png, gif, jpg
				</td>
				<br/>
				<td colspan="4" class="reseend">
					<input class="write1" type="submit" value="작성" >
					<input class="rewrite1" type="reset" value="다시 작성">
					<input class="tolist1" type="button" value="목록으로" onclick="location.href='question.qU'">
				</td>
			</tr>
		
		</table>
		<div class="clear"></div>
		
		</form>
		</center>
	</div>
</body>
</html>

<%-- <style type="text/css">
.tableAll{
border: none;
border-collapse: collapse;
}

.tableAll tr.boardtr{
	background-image: url("img/boardbg.jpg");
	background-repeat: repeat-x;
	border: 1px solid;
	height: 40px;
	letter-spacing: 0.1em;
}
.wrap .reseend input{
float: right;
margin-left: 10px;
width: 150px;
}
div.wrap{
padding-top: 70px;

}
span{
	color: red;
}


</style>
<script src="//code.jquery.com/jquery.min.js"></script>
<script type="text/javascript">
 function check(){

	var size = document.getElementById("file1").files[0].size;
	var file = document.getElementById("file1");
	var filevalue = document.getElementById("file1").value;//acb.png
	var name = filevalue.slice(filevalue.indexOf(".") +1).toLowerCase(); //파일 확장자를 잘라내고, 비교를 위해 소문자로 만듬
	alert(size);
	alert(filevalue);
	alert(name);
	if(name != "jpg" && name != "png" &&  name != "gif"){
		alert("jpg,gif,png만 지원합니다.");
		document.getElementById('fileck').innerHTML='jpg,gif,png만 지원합니다.';
		file.value ="";
		
		
	}else if(size>1024*1024){
		alert("파일이 1MB가 넘습니다");
		document.getElementById('fileck').innerHTML='파일이 1MB가 넘습니다.';
		file.value = "";
		
		
	}else{
		document.getElementById('fileck').innerHTML='';
	}
	

		
		
	}
</script>

</head>
<%


String memberEmail = (String)session.getAttribute("memberEmail");
String nickName = (String)session.getAttribute("nickName");

%>
<body>
<div class="wrap">
<center>
		<form action="insertquestion.qU" enctype="multipart/form-data" method="post">
		<table width="1028px" class="tableAll">
			<tr class="boardtr">
				<th>회원이름</th>
				<td> <input type="text" value="${nickName } " name="nickName" width="300px"></td>
				<th>글제목</th>
				<td><input type="text" name="title" width="300px"></td>
			</tr>
			<tr>
				
				<td colspan="4">
				<textarea rows="5" cols="100" name="content" style="resize:none; width: 1028px;" class="txtarere">
				</textarea>

				</td>
			</tr>
			<tr>
			<td><input type="file" name="file1" id="file1" onchange="check()"><span id="fileck"></span>
			<br/>용량 1M미만 / 영문,숫자 파일명 권장 / 등록가능 확장자 png, gif, jpg</td>
				
				<td colspan="4" class="reseend">
				<input type="submit" value="작성" >
				<input type="reset" value="다시 작성"> 
				</td>
			</tr>
		
		</table>
		
		
		</form>
		</center>
	</div>
</body>
</html> --%>